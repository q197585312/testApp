package com.nanyang.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.NetWorkUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.reactivestreams.Subscription;

import cn.finalteam.toolsfinal.DeviceUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

public abstract class BaseToolbarActivity<T extends IBasePresenter> extends BaseActivity<T> {
    @Nullable
    protected
    TextView tvToolbarTitle;
    @Nullable
    protected
    TextView tvToolbarRight;
    @Nullable
    protected
    Toolbar toolbar;

    private volatile CompositeDisposable mCompositeSubscription;
    int errorCount = 0;

    @Override
    public void initData() {
        super.initData();
        mCompositeSubscription = new CompositeDisposable();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        assert tvToolbarTitle != null;
        tvToolbarTitle.setBackgroundResource(R.mipmap.logo);
        tvToolbarTitle.getLayoutParams().width = DeviceUtils.dip2px(mContext, 80);
        tvToolbarTitle.getLayoutParams().height = DeviceUtils.dip2px(mContext, 40);
        updateBalanceTv(getApp().getUser().getBalance());
        startUpdateState();

    }

    void stopUpdateState() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }


    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Disposable subscribe = getService(ApiService.class).getData(AppConstant.URL_UPDATE_STATE)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .takeWhile(new Predicate<String>() {
                        @Override
                        public boolean test(String s) throws Exception {
                            return NetWorkUtil.isNetConnected(mContext);
                        }
                    })
                    .subscribe(new Consumer<String>() {//onNext
                                   @Override
                                   public void accept(String allData) throws Exception {
                                       if (!allData.trim().equals("100")) {
                                           reLoginPrompt("", null);
                                           errorCount = 0;
                                       }

                                   }
                               }, new Consumer<Throwable>() {
                                   @Override
                                   public void accept(Throwable throwable) {
                                       ToastUtils.showShort(throwable.getMessage());
                                       checkError();
                                   }
                               }, new Action() {//完成
                                   @Override
                                   public void run() throws Exception {
                                   }
                               }, new Consumer<Subscription>() {//开始绑定
                                   @Override
                                   public void accept(Subscription subscription1) throws Exception {
                                       if (!NetWorkUtil.isNetConnected(mContext)) {
                                           subscription1.cancel();
                                           checkError();
                                       }
                                       subscription1.request(Long.MAX_VALUE);
                                   }
                               }

                    );
            mCompositeSubscription.add(subscribe);
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };

    private void checkError() {
        errorCount++;
        if (errorCount > 4) {
            reLoginPrompt(getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    errorCount = 0;
                }
            });
        }
    }


    Handler updateHandler = new Handler();

    public void startUpdateState() {
        stopUpdateState();
        updateHandler.postDelayed(dataUpdateRunnable, 2000);// 打开定时器，执行操作
        updateBalance();

    }

    public void updateBalance() {

        Disposable updateBalanceSubscribe = getService(ApiService.class).getData(AppConstant.URL_UPDATE_BALANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                               @Override
                               public void accept(String allData) throws Exception {
                                   updateBalanceTv(allData);
                                   getApp().getUser().setBalance(allData);
                               }
                           }, new Consumer<Throwable>() {//错误
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                               }
                           }
                );
        mCompositeSubscription.add(updateBalanceSubscribe);
    }

    protected void updateBalanceTv(String allData) {
        tvToolbarRight.setText(allData);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUpdateState();
    }

    @Nullable
    public TextView getTvToolbarTitle() {
        return tvToolbarTitle;
    }

    @Nullable
    public TextView getTvToolbarRight() {
        return tvToolbarRight;
    }

    @Nullable
    public Toolbar getToolbar() {
        return toolbar;
    }

    public AfbApplication getApp() {
        return (AfbApplication) getApplication();
    }

    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        createPopupWindow(pop);
        popWindow.showPopupGravityWindow(center, 0, 0);
    }

    public void onBetSuccess(String betResult) {
        if (popWindow != null)
            popWindow.closePopupWindow();
        updateBalance();
    }

    public Activity getContextActivity() {
        return this;
    }

    public void reLoginPrompt(String msg, final SportContract.CallBack callBack) {
        BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
            @Override
            protected void clickSure(View v) {
                Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            protected void clickCancel(View v) {
                super.clickCancel(v);
                if (callBack != null) {
                    callBack.clickCancel(v);
                }
            }
        };
        pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
        if (msg.isEmpty())
            msg = getString(R.string.another_login);
        pop.getChooseMessage().setText(msg);
        pop.getChooseSureTv().setText(getString(R.string.sure));
        pop.getChooseCancelTv().setText(getString(R.string.cancel));
        onPopupWindowCreated(pop, Gravity.CENTER);
    }


}
