package com.nanyang.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.home.discount.DiscountActivity;
import com.nanyang.app.main.home.huayThai.HuayThaiActivity;
import com.nanyang.app.main.home.keno.KenoActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
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

    private CompositeDisposable mCompositeSubscription;
    int errorCount = 0;
    protected TextView tvToolbarRight1;
    protected TextView tvToolbarLeft;

    @Override
    public void initData() {
        super.initData();
        mCompositeSubscription = new CompositeDisposable();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarRight1 = (TextView) findViewById(R.id.tv_toolbar_right1);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);
        tvToolbarLeft = (TextView) findViewById(R.id.tv_toolbar_left);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
//        toolbar.setBackgroundResource();
        dynamicAddView(toolbar, "background", R.mipmap.toolbar_bg);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackCLick(v);
            }
        });
        assert tvToolbarTitle != null;
        tvToolbarTitle.setBackgroundResource(R.mipmap.logo);
        tvToolbarTitle.getLayoutParams().width = DeviceUtils.dip2px(mContext, 100);

        tvToolbarLeft.setBackgroundResource(R.mipmap.sport_home_white_24dp);
        tvToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMenus(v);
            }
        });
        updateBalanceTv(getApp().getUser().getBalance());
        if(AppConstant.IS_AGENT){
            initAgent();
        }

    }

    public void initAgent() {
        tvToolbarLeft.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startUpdateState();
    }

    protected void onBackCLick(View v) {
        finish();
    }

    void stopUpdateState() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }


    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Disposable subscribe = getService(ApiService.class).getData(AppConstant.getInstance().URL_UPDATE_STATE)
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
                                           reLoginPrompt("", new SportContract.CallBack() {
                                               @Override
                                               public void clickCancel(View v) {
                                                   Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                   startActivity(intent);
                                               }
                                           });
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

        Disposable updateBalanceSubscribe = getService(ApiService.class).getData(AppConstant.getInstance().URL_UPDATE_BALANCE).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
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
        tvToolbarRight.setText(getString(R.string.Bet_Credit) + "\n" + allData);
    }


    @Override
    protected void onStop() {
        super.onStop();
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
        if (center == -2) {
            popWindow.showPopupDownWindow();
        } else
            popWindow.showPopupGravityWindow(center, 0, 0);
    }

    public void onPopupWindowCreated(BasePopupWindow pop, int center, int x, int y) {
        createPopupWindow(pop);
        popWindow.showPopupGravityWindow(center, x, y);
    }

    public void onBetSuccess(String betResult) {
        if (popWindow != null)
            popWindow.closePopupWindow();
        getApp().setBetParList(null);
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

    protected void gameMenus(View v) {
        popWindow = new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_all_game;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                BaseRecyclerAdapter adapter = AfbUtils.getGamesAdapter(mContext, rv);
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        switchSkipAct(item.getType());
                        closePopupWindow();

                    }
                });
            }
        };
        popWindow.showPopupCenterWindow();
    }

    public void defaultSkip(String type) {
        MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, getString(R.string.Today));
        menuItemInfo.setType("Today");
        menuItemInfo.setParent(type);
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
        skipAct(SportActivity.class, b);
    }

    public void switchSkipAct(String gameType) {
        switch (gameType) {
            case "SportBook":
            case "Financial":
            case "Specials_4D":
            case "Muay_Thai":
            case "E_Sport":
            case "Myanmar_Odds":
            case "Europe":
                defaultSkip(gameType);
           /*             createPopupWindow(getPopupWindow(item.getType()));
                        popWindow.showPopupCenterWindow();*/
                break;
            case "Huay_Thai":
                skipAct(HuayThaiActivity.class);
                break;
            case "Live_Casino":
                Bundle b = new Bundle();
                b.putString("activity", "Live");
                ToastUtils.showShort(R.string.coming_soon);
//                skipAct(PokerCasinoActivity.class, b);
                break;
            case "Poker":
                ToastUtils.showShort(R.string.coming_soon);
                break;
            case "Discount":
                skipAct(DiscountActivity.class);
                break;
            case "Keno":
                skipAct(KenoActivity.class);
                break;
            default:
                ToastUtils.showShort(R.string.coming_soon);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 7 && resultCode == 8) {
            String gameType = data.getStringExtra("gameType");
            againLogin(gameType);
        }
    }

    public void againLogin(String gameType) {

    }

    public int getHomeColor() {
        switch (getString(R.string.app_name)) {
            case "Afb88":
                return 0xff0d5924;
            case "I1bet88":
                return 0xff0E3D59;
            case "AP889":
                return 0xff300F2D;
        }
        return 0xff0d5924;
    }
}
