package com.nanyang.app;

import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.load.login.LoginActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.reactivestreams.Publisher;

import java.util.concurrent.TimeUnit;

import cn.finalteam.toolsfinal.DeviceUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
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
    private Disposable updateDisposable;

    @Override
    public void initData() {
        super.initData();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        toolbar.setBackgroundResource(R.drawable.rectangle_green_gradient_line);
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
//        startUpdateState();
    }

    void stopUpdateState() {
        if (updateDisposable != null) {
            updateDisposable.dispose();
            updateDisposable = null;
        }
    }

    public void startUpdateState() {
        stopUpdateState();
        updateDisposable = Flowable.interval(2, 60, TimeUnit.SECONDS).flatMap(new Function<Long, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Long aLong) throws Exception {

                return getService(ApiService.class).getData(AppConstant.URL_UPDATE_STATE);

            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        if(!allData.trim().equals("100")){
                            BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
                                @Override
                                protected void clickSure(View v) {
                                    skipAct(LoginActivity.class);
                                }
                            };
                            pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
                            pop.getChooseMessage().setText(R.string.another_login);
                            pop.getChooseSureTv().setText(getString(R.string.sure));
                            pop.getChooseCancelTv().setText(getString(R.string.cancel));
                            pop.showPopupCenterWindow();
                        }

                    }
                },new Consumer<Throwable>() {//错误
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                               }
                           }
                );
        updateBalance();

    }

    private void updateBalance() {

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
}
