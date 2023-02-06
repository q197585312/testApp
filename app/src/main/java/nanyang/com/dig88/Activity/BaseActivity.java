package nanyang.com.dig88.Activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import org.reactivestreams.Subscription;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nanyang.com.dig88.Base.BaseHttpRequestPresenter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.GameBalanceBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.MsgNoReadBean;
import nanyang.com.dig88.Entity.SendTelBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.popupwindow.DigYesNoChoosePop;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.BlockDialog;
import nanyang.com.dig88.Util.ChoicePicHelper;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.ImageBase64;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.RequestUtils;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;

/**
 * Created by Administrator on 2015/10/19.
 */
public abstract class BaseActivity<T extends BaseRetrofitPresenter> extends xs.com.mylibrary.base.component.BaseActivity implements IBaseContext {

    private final int maxErrorCount = 4;
    public int screenWidth;
    public int screenHeight;
    protected BlockDialog dialog;
    protected boolean shouldShowExit = true;
    protected boolean hasAttached = false;
    protected T presenter;
    Handler handler = new Handler();
    int moneyErrorCount = 0;
    HttpClient httpClient;
    Gson gson = new Gson();
    //    参数：web_id,member_id,session_id,type
//    type = 1 : 获取所有发送给会员的信息
//    type = 2 : 更改信息状态从未读（0）去读(1)，还要发多一个参数 msg_id 信息的id
//    type = 0 : 未读信息总数
    Thread msgThread = null;
    Runnable digitalMoneyRunnable = new Runnable() {
        @Override
        public void run() {
            if (getApp().getUserInfo() == null || getApp().getUserInfo().getSession_id() == null || getApp().getUserInfo().getSession_id().equals("")) {
                removeDigitalMoneyUpdate();
                return;
            }
            refreshUserInfo();
            msgThread = new Thread(new MsgRunnable());
            msgThread.start();
            handler.postDelayed(this, 15000);// 50是延时时长
        }
    };
    private BaseFragment uploadPicFragment;

    public Handler getHandler() {
        return handler;
    }

    @Override
    public void showLoadingDialog() {
        if (hasAttachedToWindow() && dialog != null)
            dialog.show();
    }

    @Override
    public void hideLoadingDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.hide();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        hasAttached = true;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        hasAttached = false;
    }

    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Dig88Utils.setLang(mContext);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;         // 屏幕宽度（像素）
        screenHeight = dm.heightPixels;       // 屏幕高度（像素）
        setDialog(new BlockDialog(mContext, getString(R.string.loading)));
        httpClient = new HttpClient("");
    }

    public void showMoney(String money) {
        if (!TextUtils.isEmpty(money)) {
            rightTv.setText(money + "(" + getCurrency() + ")");
        }
    }

    public void showMoney() {
        String s = "0.00";
        if (getUserInfoBean().getMoneyBalance() != null && getUserInfoBean().getMoneyBalance().getBalance() != null && !getUserInfoBean().getMoneyBalance().getBalance().equals(""))
            s = StringUtils.floatDecimalFormat(Double.valueOf(getUserInfoBean().getMoneyBalance().getBalance()));
        rightTv.setText(s + "(" + getCurrency() + ")");
    }

    public void refreshMoney(String money) {

    }

    protected void loginDigital() {
        removeDigitalMoneyUpdate();
        mainLogout();
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Dig88Utils.setLang(mContext);
    }

    public UserInfoBean getUserInfoBean() {
        return ((DigApp) getApplication()).getUserInfo();
    }

    public void setUserInfoBean(UserInfoBean userInfoBean) {
        ((DigApp) getApplication()).setUserInfo(userInfoBean);
    }

    public DigApp getApp() {
        return (DigApp) getApplication();
    }

    public void updateDigitalGamesMoney() {
        removeDigitalMoneyUpdate();
        handler.post(digitalMoneyRunnable);// 打开定时器，执行操作
    }

    public void removeDigitalMoneyUpdate() {
        handler.removeCallbacks(digitalMoneyRunnable);// 关闭定时器处理
    }

    public void onGetMsgBoxCount(String count) {

    }

    public void refreshUserInfo() {
        HashMap<String, String> params = new HashMap<>();
        params.put("web_id", WebSiteUrl.WebId);
        params.put("user_id", getUserInfoBean().getUser_id());
        params.put("session_id", getUserInfoBean().getSession_id());
        Disposable disposable = Api.getService(ApiService.class).doPostMap(WebSiteUrl.CreditUpdate, params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String data) throws Exception {
                        Log.d("refreshUserInfo", data);
                        NyBaseResponse<List<GameBalanceBean>> data1 = gson.fromJson(data, new TypeToken<NyBaseResponse<List<GameBalanceBean>>>() {
                        }.getType());
                        List<GameBalanceBean> gameBalanceBeanList = data1.getData();
                        String balance = gameBalanceBeanList.get(0).getBalance();
                        if (balance.contains("#")) {
                            gameBalanceBeanList.get(0).setBalance(balance.split("#")[0]);
                        }
                        if (data1.getCode().equals("1") && gameBalanceBeanList != null && gameBalanceBeanList.size() > 0) {
                            moneyErrorCount = 0;
                            getUserInfoBean().setMoneyBalance(gameBalanceBeanList.get(0));
                            showMoney();
                            refreshMoney(gameBalanceBeanList.get(0).getBalance());
                        } else {
                            loginDigital();
                        }
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        loginDigital();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Long.MAX_VALUE);
                    }
                });
    }


    public BlockDialog getDialog() {
        return dialog;
    }

    public void setDialog(BlockDialog dialog) {
        this.dialog = dialog;
    }

    public void showBlockDialog() {
        if (!isFinishing() && dialog != null) {
            dialog.dismiss();
            dialog.setOutCancle(false);
            dialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissBlockDialog();
        if (presenter != null)
            presenter.unSubscribe();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void dismissBlockDialog() {
        if (dialog != null && dialog.isShowing() && getWindow().isActive()) {
            dialog.dismiss();
        }
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            titleTv.setBackgroundResource(0);
            titleTv.setText(title);
        }
    }

    @Override
    protected void leftClick() {
        finish();
    }

    public void setleftViewEnable(boolean enable) {
        if (enable) {
            toolbar.setBackgroundResource(0);
            toolbar.setNavigationIcon(R.mipmap.back_new);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftClick();
                }
            });
        } else {
            toolbar.setNavigationIcon(null);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            toolbar.setNavigationIcon(null);
            rightTv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.wallet_white, 0, 0, 0);
        }

    }

    protected void mainLogout() {
        if (getUserInfoBean() == null || getUserInfoBean().getUser_id() == null) {
            return;
        }
        HashMap<String, String> params = new HashMap<>();
        params.put("web_id", WebSiteUrl.WebId);
        params.put("user_id", getUserInfoBean().getUser_id());
        params.put("session_id", getUserInfoBean().getSession_id());
        Disposable disposable = Api.getService(ApiService.class).doPostMap(WebSiteUrl.Dig88Logout, params).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String data) throws Exception {
//                        AppTool.saveObjectData(mContext, "loginInfo", null);
                        setUserInfoBean(null);
                        Intent intent = new Intent(mContext, MainTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
//                        AppTool.saveObjectData(mContext, "loginInfo", null);
                        setUserInfoBean(null);
                        Intent intent = new Intent(mContext, MainTabActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Long.MAX_VALUE);
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        RequestUtils.cancelAll(mContext);
    }

    public String getCurrency() {
        if (getUserInfoBean() == null || getUserInfoBean().getId_mod_currency() == null) {
            return "";
        }
        String currencyId = getUserInfoBean().getId_mod_currency();
        String currency;
        if (currencyId.equals("1")) {
            currency = "USD";
        } else if (currencyId.equals("2")) {
            currency = "SGD";
        } else if (currencyId.equals("3")) {
            currency = "MYR";
            if (WebSiteUrl.WebId.equals("155")) {
                if (getUserInfoBean().getPoker_id().equals("1")) {
                    currency = "BND";
                }
            }
        } else if (currencyId.equals("4")) {
            currency = "THB";
        } else if (currencyId.equals("5")) {
            currency = "CNY";
        } else if (currencyId.equals("6")) {
            currency = "HKD";
        } else if (currencyId.equals("8")) {
            currency = "VND";
        } else if (currencyId.equals("9")) {
            currency = "TEST";
        } else if (currencyId.equals("11")) {
            currency = "IDR";
        } else if (currencyId.equals("12")) {
            currency = "KRW";
        } else if (currencyId.equals("13")) {
            currency = "EUR";
        } else if (currencyId.equals("14")) {
            currency = "GBP";
        } else if (currencyId.equals("15")) {
            currency = "SEK";
        } else if (currencyId.equals("16")) {
            currency = "TWD";
        } else if (currencyId.equals("17")) {
            currency = "ZAR";
        } else if (currencyId.equals("18")) {
            currency = "MMK";
        } else if (currencyId.equals("19")) {
            currency = "BT4";
        } else if (currencyId.equals("20")) {
            currency = "BND";
        } else if (currencyId.equals("21")) {
            currency = "LTC";
        } else {
            currency = "USD";
        }
        return currency;
    }

    public String getCurrency(String currencyId) {
        String currency;
        if (currencyId.equals("1")) {
            currency = "USD";
        } else if (currencyId.equals("2")) {
            currency = "SGD";
        } else if (currencyId.equals("3")) {
            currency = "MYR";
            if (WebSiteUrl.WebId.equals("155")) {
                if (getUserInfoBean().getPoker_id().equals("1")) {
                    currency = "BND";
                }
            }
        } else if (currencyId.equals("4")) {
            currency = "THB";
        } else if (currencyId.equals("5")) {
            currency = "CNY";
        } else if (currencyId.equals("6")) {
            currency = "HKD";
        } else if (currencyId.equals("8")) {
            currency = "VND";
        } else if (currencyId.equals("9")) {
            currency = "TEST";
        } else if (currencyId.equals("11")) {
            currency = "IDR";
        } else if (currencyId.equals("12")) {
            currency = "KRW";
        } else if (currencyId.equals("13")) {
            currency = "EUR";
        } else if (currencyId.equals("14")) {
            currency = "GBP";
        } else if (currencyId.equals("15")) {
            currency = "SEK";
        } else if (currencyId.equals("16")) {
            currency = "TWD";
        } else if (currencyId.equals("17")) {
            currency = "ZAR";
        } else if (currencyId.equals("18")) {
            currency = "MMK";
        } else if (currencyId.equals("19")) {
            currency = "BT4";
        } else if (currencyId.equals("20")) {
            currency = "BND";
        } else if (currencyId.equals("21")) {
            currency = "LTC";
        } else {
            currency = "";
        }
        return currency;
    }

    public String getLocalLanguage() {
        String appLanguage = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(appLanguage)) {
            appLanguage = "en";
        }
        return appLanguage;
    }

    public boolean hasLoginInfo() {
        UserInfoBean userInfo = getUserInfoBean();
        if (userInfo == null) {
            return false;
        }
        String sessionId = userInfo.getSession_id();
        String userId = userInfo.getUser_id();
        LoginInfoBean info = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        if (info == null || TextUtils.isEmpty(sessionId) || TextUtils.isEmpty(userId)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean hasAttachedToWindow() {
        return hasAttached;
    }

    @Override
    public com.unkonw.testapp.libs.base.BaseActivity getBaseActivity() {
        return this;
    }

    public void setUploadPicFragment(BaseFragment uploadPicFragment) {
        this.uploadPicFragment = uploadPicFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null && !TextUtils.isEmpty(data.getData().toString()) && uploadPicFragment != null) {
            Uri uri = data.getData();
            uploadPicFragment.showChoosePic(uri);
            String path_above19 = ImageBase64.getPath_above19(mContext, uri);
            ChoicePicHelper.uploadPic(this, uploadPicFragment, path_above19, getUserInfoBean());
        }
    }

    public void onGetSendTel(SendTelBean sendTelBean) {

    }

    public void onGetVerificationCode(SendTelBean sendTelBean) {

    }

    class MsgRunnable implements Runnable {

        @Override
        public void run() {
            UserInfoBean userInfoBean = getUserInfoBean();
            if (userInfoBean == null) {
                return;
            }
            String baseParam = "web_id=" + WebSiteUrl.WebId + "&member_id=" + userInfoBean.getUser_id() + "&session_id=" + userInfoBean.getSession_id() + "&type=";
            try {
                if (httpClient == null) {
                    httpClient = new HttpClient("");
                }
                String msgNoReadResult = httpClient.sendPost(WebSiteUrl.MsgBoxUrl, baseParam + "0");
                String msgCount;
                if (msgNoReadResult.contains("success")) {
                    MsgNoReadBean msgNoReadBean = gson.fromJson(msgNoReadResult, MsgNoReadBean.class);
                    msgCount = msgNoReadBean.getTotal_msg();
                } else {
                    msgCount = "0";
                }
                onGetMsgBoxCount(msgCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


