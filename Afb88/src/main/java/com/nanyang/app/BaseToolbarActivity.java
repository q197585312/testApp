package com.nanyang.app;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.Utils.LogIntervalUtils;
import com.nanyang.app.common.IGetRefreshMenu;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.huayThai.HuayThaiActivity;
import com.nanyang.app.main.home.keno.KenoActivity;
import com.nanyang.app.main.home.keno.WebActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.NetWorkUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.BaseYseNoChoosePopupWindow;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;
import okhttp3.Request;
import retrofit2.Response;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;
import static com.unkonw.testapp.libs.api.Api.getService;

public abstract class BaseToolbarActivity<T extends BaseRetrofitPresenter> extends BaseActivity<T> implements IGetRefreshMenu {
    public BetGoalWindowUtils BetGoalWindowUtils = new BetGoalWindowUtils();
    @Nullable
    public
    TextView tvToolbarTitle;
    @Nullable
    public
    TextView tvToolbarRight;
    @Nullable
    public
    TextView tvTime;
    @Nullable
    protected
    Toolbar toolbar;
    @Nullable
    public
    LinearLayout llRight;


    protected List<String> lastWaitDataBeanList;
    public CompositeDisposable mCompositeSubscription;
    public int waitSize;
    int errorCount = 0;
    public TextView tvToolbarRight1;
    public TextView tvToolbarLeft;

    @Override
    public void init() {
        initLanguage();
        super.init();

    }

    @Override
    public void initData() {
        super.initData();
        mCompositeSubscription = new CompositeDisposable();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvToolbarRight = (TextView) findViewById(R.id.tv_toolbar_right);
        tvToolbarRight1 = (TextView) findViewById(R.id.tv_toolbar_right1);
        tvTime = (TextView) findViewById(R.id.tv_time);
        tvToolbarTitle = (TextView) findViewById(R.id.tv_toolbar_title);

        tvToolbarLeft = (TextView) findViewById(R.id.tv_toolbar_left);
        llRight = (LinearLayout) findViewById(R.id.ll_right);
        toolbar.setNavigationIcon(R.mipmap.arrow_white_back);
        toolbar.setBackgroundResource(R.color.green_black_word);
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackCLick(v);
            }
        });

        assert tvToolbarTitle != null;
//        tvToolbarTitle.setBackgroundResource(R.mipmap.logo);
//        tvToolbarTitle.getLayoutParams().width = DeviceUtils.dip2px(mContext, 100);
        tvToolbarLeft.setBackgroundResource(R.mipmap.sport_home_white_24dp);
        tvToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                gameMenus(v);
            }
        });
    }

    public void goWebActivity(String url, String string, boolean canFinish) {

        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString(AppConstant.KEY_STRING, string);
        bundle.putBoolean(AppConstant.KEY_BOOLEAN, canFinish);
        skipAct(WebActivity.class, bundle);
    }

    public void initAgent() {
        tvToolbarLeft.setVisibility(View.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startUpdateState();
        if (AppConstant.getInstance().IS_AGENT) {
            initAgent();
        }
    }

    public void initLanguage() {
        String language = AfbUtils.getLanguage(this);
        if (!TextUtils.isEmpty(language)) {
            AfbUtils.switchLanguage(language, this);
        }
    }

    protected void restart() {

    }

    public void onBackCLick(View v) {
        finish();
    }

    protected void stopUpdateState() {
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
                                       LogUtil.d("OkHttp", "allData:----" + allData);
                                       if (!allData.trim().equals("100")) {
                                           reLoginPrompt("", new SportContract.CallBack() {
                                               @Override
                                               public void clickCancel(View v) {
                                                   errorCount = 0;
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


    Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 7:
                    tvTime.setText((String) msg.obj);
                    break;
            }
        }
    };

    public void startUpdateState() {
        stopUpdateState();
        updateHandler.postDelayed(dataUpdateRunnable, 2000);// 打开定时器，执行操作
        updateBalance();

    }

    public void updateBalance() {
        String language = new LanguageHelper(getBaseActivity()).getLanguage();
        LoadMainDataHelper helper = new LoadMainDataHelper(new Api(), getBaseActivity(), mCompositeSubscription);
        helper.doRetrofitApiOnUiThread(new LoginInfo.LanguageWfBean("AppGetDate", language, AppConstant.wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                personalInfo.setPassword(((AfbApplication) getBaseActivity().getApplication()).getUser().getPassword());
                personalInfo.setLoginUrl(((AfbApplication) getBaseActivity().getApplication()).getUser().getLoginUrl());
                ((AfbApplication) getBaseActivity().getApplication()).setUser(personalInfo);
                updateBalanceTv(personalInfo.getCredit2());
            }
        });
    }

    protected void updateBalanceTv(String allData) {
        tvToolbarRight.setText(allData);
    }


    @Override
    protected void onStop() {
        super.onStop();
        Log.d(getClass().getSimpleName(), "onStop: ");
        stopUpdateState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FragmentManager fragmentManager = getFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStack();
        }
        Log.d(getClass().getSimpleName(), "onDestroy: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(getClass().getSimpleName(), "onRestart: ");
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

    public void onPopupWindowCreatedAndShow(BasePopupWindow pop, int center) {

        createPopupWindow(pop);
        if (center == -2) {
            if (isHasAttached())
                popWindow.showPopupDownWindow();
        } else {
//            popWindow.showPopupGravityWindow(center, 0, 0);
            if (isHasAttached())
                popWindow.showAtLocation(Gravity.TOP, AfbUtils.dp2px(mContext, 2), AfbUtils.dp2px(mContext, 200));//默认 显示在视频下面 不然不会被输入法挤上去
        }
    }


    public void onBetSuccess(String betResult) {
        LogUtil.d("BetPop", "setBetAfbList:onBetSuccess:" + null);
        getApp().setBetAfbList(null);
        updateBalance();

    }

    public void getSkipGd88Data() {


        LogIntervalUtils.logTime("请求数据" + BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx");
//        https://www.i1bet99.com/_View/LiveDealerGDC.aspx
//        https://www.i1bet99.com/_View/LiveDealerGDC.aspx
        Disposable subscription = getService(ApiService.class).getResponse(BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response responseBodyResponse) throws JSONException {
                        int code = responseBodyResponse.code();
                        String body = responseBodyResponse.body() != null ? responseBodyResponse.body().toString() : "null";
                        LogIntervalUtils.logTime("请求数据完成开始解析s:" + body + ",code=" + code);
                        Request rawRequest = responseBodyResponse.raw().request();
                        HttpUrl url1 = rawRequest.url();

                        String url = url1.url().toString();
                        if (url.contains("login.jsp")) {

                            Bundle intent = new Bundle();
                            PersonalInfo info = getApp().getUser();
                            SettingAllDataBean bean = getApp().getSettingAllDataBean();
                            intent.putString("username", info.getLoginName());
                            intent.putString("password", info.getPassword());
                            intent.putString("language", "en");
                            intent.putString("web_id", "-1");
                            intent.putInt("gameType", 5);
                            intent.putString("balance", info.getCredit2());
                            intent.putString("curCode", bean.getCurCode());
                            LogIntervalUtils.logTime("请求数据完成开始跳转");
                            getBaseActivity().skipFullNameActivity(intent, "gaming178.com.casinogame.Activity.LobbyBaccaratActivity");
                        } else if (code == 200 && body.contains("not online")) {
                            ToastUtils.showShort("User not online");
                            reLogin();

                        } else {
                            ToastUtils.showShort("not find agent!Please contact your agent!");
                        }
                        hideLoadingDialog();
                    }

                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Long.MAX_VALUE);
                        showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void reLoginPrompt(String msg, final SportContract.CallBack callBack) {
//        ToastUtils.showShort(getString(R.string.another_login));
//        updateHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//            }
//        }, 1000);
        BaseYseNoChoosePopupWindow pop = new BaseYseNoChoosePopupWindow(mContext, new View(mContext)) {
            @Override
            protected void clickSure(View v) {
                if (AppConstant.IS_AGENT) {
                    finish();
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            protected void clickCancel(View v) {
                super.clickCancel(v);
                reLogin();
                if (callBack != null) {
                    callBack.clickCancel(v);
                } else {

                }
            }
        };
        pop.getChooseTitleTv().setText(getString(R.string.confirm_or_not));
        if (msg.isEmpty())
            msg = getString(R.string.another_login);
        pop.getChooseMessage().setText(msg);
        pop.getChooseSureTv().setText(getString(R.string.sure));
        pop.getChooseCancelTv().setText(getString(R.string.Cancel));
        onPopupWindowCreatedAndShow(pop, Gravity.CENTER);
    }

    public void defaultSkip(String type) {
        MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, (R.string.running));
        menuItemInfo.setType("Running");
        menuItemInfo.setParent(type);
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
        skipAct(SportActivity.class, b);
    }

    public void switchSkipAct(String gameType) {
        SportIdBean sportIdBean = getApp().getSportByG(gameType);
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
            reLogin();
        }
    }

    public void reLogin() {
        if (presenter != null && getApp().getUser() != null &&
                getApp().getUser().getLoginUrl() != null) {
            presenter.doRetrofitApiOnUiThread(getService(ApiService.class).getData(getApp().getUser().getLoginUrl())
                    , new BaseConsumer<String>(this) {
                        @Override
                        protected void onBaseGetData(String s) throws JSONException {
                            if (s.contains("Maintenance")) {
                                Exception exception = new Exception((baseContext.getBaseActivity()).getString(R.string.System_maintenance));
                                onError(exception);
                            } else {
                                ToastUtils.showLong(R.string.Login_Success);
                            }


                        }

                        @Override
                        protected void onHideDialog() {
                        }

                        @Override
                        protected void onError(final Throwable throwable) {
                            super.onError(throwable);
                            (baseContext.getBaseActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showShort(throwable.getMessage());
                                }
                            });

                        }
                    });
        }

    }


    public void setToolbarVisibility(int b) {
        toolbar.setVisibility(b);
    }

    private Runnable refreshMenuRunnable = new Runnable() {
        MainPresenter switchLanguage = new MainPresenter(getBaseActivity());

        @Override
        public void run() {
            if (!isHasAttached()) {
                return;
            }
            LinkedHashMap<String, String> menuParamMap = new LinkedHashMap<>();
            menuParamMap.put("ACT", "Getmenu");
            menuParamMap.put("PT", AppConstant.wfMain);
            menuParamMap.put("ot", String.valueOf(getOtType().charAt(0)).toLowerCase());
            switchLanguage.refreshMenu(menuParamMap);
            updateHandler.postDelayed(this, 10000);
        }
    };

    public void startRefreshMenu() {
        updateHandler.postDelayed(refreshMenuRunnable, 1500);
    }

    public void stopRefreshMenu() {
        if (updateHandler != null) {
            updateHandler.removeCallbacks(refreshMenuRunnable);
        }
    }

    public String getOtType() {
        return "Running";
    }

    public void onGetRefreshMenu(List<String> beanList) {
        if (lastWaitDataBeanList == null) {
            lastWaitDataBeanList = beanList;
        } else {
            for (int i = 0; i < lastWaitDataBeanList.size(); i++) {
                String waitNum = lastWaitDataBeanList.get(i);
                Log.d("onGetRefreshMenu", "lastWaitDataBeanList: " + lastWaitDataBeanList.toString());
                Log.d("onGetRefreshMenu", "beanList: " + beanList.toString());
                if (!beanList.contains(waitNum)) {
                    String accType = getOtType();
                    BetGoalWindowUtils.showBetWindow(accType, waitNum, this, true);
                    waitSize = Integer.parseInt(waitNum);
                } else {
                    waitSize = 0;
                }
            }
            lastWaitDataBeanList = beanList;
        }
    }

    public void onAddWaiteCount(int waitNumber) {
        stopRefreshMenu();
        startRefreshMenu();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean requestPermission(String[] PERMISSIONS, int PERMISSIONS_CODE) {
        boolean isGranted = true;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            for (String s : PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(this, s) != PERMISSION_GRANTED) {
                    //如果没有写sd卡权限
                    isGranted = false;
                }
            }
            if (!isGranted) {
                requestPermissions(
                        PERMISSIONS,
                        PERMISSIONS_CODE);
            }
        }
        return isGranted;
    }


}
