package com.nanyang.app.common;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Been.AppVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.LogIntervalUtils;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.Setting.SettingFragment;
import com.nanyang.app.main.home.LoadPCasinoDataHelper;
import com.nanyang.app.main.home.keno.WebActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.finalteam.toolsfinal.ApkUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Request;
import retrofit2.Response;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class MainPresenter extends BaseSwitchPresenter {
    private static final String TAG = "MainPresenter";
    IGetRefreshMenu iGetRefreshMenu;


    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public MainPresenter(IBaseContext iBaseContext) {
        super(iBaseContext);
//        changeLanguageFragment = (ILanguageView) iBaseContext;
        if (iBaseContext instanceof IGetRefreshMenu) {
            iGetRefreshMenu = (IGetRefreshMenu) iBaseContext;
        } else if (iBaseContext instanceof SettingFragment) {

        } else {
            iGetRefreshMenu = null;
        }
    }

    //    https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"GetTT","lang":"ZH-CN","pgLable":"0.18120996831154568","vsn":"4.0.12","PT":"wfLoginH50"}&_db={}
/*    public void switchLanguage(String lang) {
        doRetrofitApiOnDefaultThread(switchLanguage.switchLanguage(lang), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                changeLanguageFragment.onLanguageSwitchSucceed(data);
            }
        });
    }*/

    public void getSetting(final CallBack<SettingAllDataBean> back) {
        LoadMainDataHelper<LoginInfo.LanguageWfBean> dataHelper = new LoadMainDataHelper<>(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        dataHelper.doRetrofitApiOnUiThread(new LoginInfo.LanguageWfBean(new LanguageHelper(baseContext.getBaseActivity()).getLanguage()), new CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                if (data.contains("Maintenance")) {
                    ToastUtils.showLong(R.string.System_maintenance);
                    return;
                }
                SettingAllDataBean settingAllDataBean = gson.fromJson(data, SettingAllDataBean.class);
                if (settingAllDataBean == null) {
                    AppConstant.IS_AGENT = true;
                    getSetting(back);
                } else {
                    ((AfbApplication) baseContext.getBaseActivity().getApplication()).setSettingAllDataBean(settingAllDataBean);
                    ((AfbApplication) baseContext.getBaseActivity().getApplication()).setQuickAmount(settingAllDataBean.getAccamount() + "");
                    AfbUtils.setChipStatusMap(settingAllDataBean.getChipSetChoose());
                    back.onBack(settingAllDataBean);
                }
            }
        }, "^.*wsParam=([^;]+);.*?");
    }

    public void getSkipGd88Data() {
        LogIntervalUtils.logTime("请求数据" + BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx");
        Disposable subscription = getService(ApiService.class).getResponse(BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response responseBodyResponse) throws JSONException {

                        LogIntervalUtils.logTime("请求数据完成开始解析");
                        okhttp3.Response response = responseBodyResponse.raw().priorResponse();
                        if (response != null) {
                            Request request = response.request();
                            String url = request.url().toString();
                            MainActivity mainActivity = (MainActivity) baseContext;
                            if (ApkUtils.isAvilible(mainActivity, "gaming178.com.baccaratgame")) {
                                Intent intent = new Intent();
                                ComponentName comp = new ComponentName("gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity");
                                intent.setComponent(comp);
                                PersonalInfo info = mainActivity.getApp().getUser();
                                intent.putExtra("username", info.getLoginName());
                                intent.putExtra("password", info.getPassword());
                                intent.putExtra("language", "en");
                                intent.putExtra("web_id", "-1");
                                intent.putExtra("webUrl", url);
                                intent.putExtra("gameType", 5);
                                intent.putExtra("balance", info.getCredit2());
                                LogIntervalUtils.logTime("请求数据完成开始跳转");
                                baseContext.getBaseActivity().startActivity(intent);
                            } else {
                                downloadGd88();
                            }
                        } else {
                            ToastUtils.showShort("not find agent!");
                        }
                        baseContext.hideLoadingDialog();
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
                        baseContext.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void downloadGd88() {
        String url = "http://www.appgd88.com/api/gd88AndroidVersion.php?Labelid=48";
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                AppVersionBean appVersionBean = gson.fromJson(data, AppVersionBean.class);
                String gd88DownloadUrl = appVersionBean.getData().getUrl();
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(gd88DownloadUrl);
                intent.setData(content_url);
                baseContext.getBaseActivity().startActivity(intent);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    @NonNull
    private String getLanguage() {
        LanguageHelper helper = new LanguageHelper(baseContext.getBaseActivity());
        return helper.getLanguage();
    }

    public void login(final LoginInfo info, final BaseConsumer<String> baseConsumer) {

        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, info.getWfmain("Login", getLanguage()))

                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        String regex = "window.location";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(s);
                        if (m.find()) {
                            return getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN);
                        }
                        Exception exception1 = new Exception("Server Error");
                        throw exception1;

                    }
                }), baseConsumer);
    }

    int errorOddsType = 0;

    public void switchOddsType(final String oddsType) {
        Map<String, String> map = new HashMap<>();

        LoginInfo.LanguageWfBean languageWfBean = new LoginInfo.LanguageWfBean("");
        languageWfBean.setAccType(oddsType);
        map.put("_fm", languageWfBean.getJson());
        Disposable subscription = getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, map)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())

                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        errorOddsType = 0;
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                        if (errorOddsType > 5) {
                            return;
                        }
                        errorOddsType++;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {

                            }
                        }, 1000);
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

     /*   Map<String, String> map = new HashMap<>();
        LoginInfo.LanguageWfBean languageWfBean = new LoginInfo.LanguageWfBean(getLanguage());
        languageWfBean.setAccType(oddsType);
        map.put("_fm", languageWfBean.getJson());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, map).flatMap(new Function<String, Flowable<String>>() {
            @Override
            public Flowable<String> apply(String s) throws Exception {
                return getService(ApiService.class).getData(AppConstant.getInstance().URL_ODDS_TYPE + oddsType);
            }
        }), consumer);*/
    }

    public void refreshMenu(LinkedHashMap<String, String> paramMap) {
        String p = AfbUtils.getJsonParam(paramMap);
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(3);
                    JSONArray jsonArrayData3 = jsonArrayData2.getJSONArray(2);
                    List<String> beanList = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData3.length(); i++) {
                        JSONArray menuWaitDataArray = (JSONArray) jsonArrayData3.get(i);
                        String waitId = menuWaitDataArray.getString(0);
                        if (!waitId.equals("0")) {
                            beanList.add(waitId);
                        }
                    }
                    if (iGetRefreshMenu != null) {
                        iGetRefreshMenu.onGetRefreshMenu(beanList);
                    }
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void loadAllMainData(LoginInfo.LanguageWfBean languageWfBean, final CallBack<String> back) {
        LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        helper.doRetrofitApiOnUiThread(languageWfBean, back);
    }

    public void oddsType() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().URL_ODDS_TYPE + "MY").subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
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
        mCompositeSubscription.add(subscription);

    }

    //
    public void skipPCashio(String Method, String url, final String itemG, LoginInfo.LanguageWfBean wfBean, final String host, String matchs) {


        LoadPCasinoDataHelper<LoginInfo.LanguageWfBean> helper = new LoadPCasinoDataHelper<>(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        if (Method.equals("get")) {
            helper.doRetrofitApiOnUiThreadBackGet(url, wfBean, new CallBackError<String>() {
                @Override
                public void onBack(String data) throws JSONException {
                    goPCasino(data, itemG, host);
                }

                @Override
                public void onError(String data) throws JSONException {
                    ToastUtils.showLong(data);
                }
            }, matchs);
        } else {

            helper.doRetrofitApiOnUiThreadBackPost(url, wfBean, new CallBackError<String>() {
                @Override
                public void onBack(String data) throws JSONException {
                    goPCasino(data, itemG, host);
                }

                @Override
                public void onError(String data) throws JSONException {
                    ToastUtils.showLong(data);
                }
            }, matchs);


        }
    }

    private void goPCasino(String data, String itemG, String host) {
        final SportIdBean sportIdBean = AfbUtils.getSportByG(itemG);
        String url = host + data;
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString(AppConstant.KEY_STRING, baseContext.getBaseActivity().getString(sportIdBean.getTextRes()));
        baseContext.getBaseActivity().skipAct(WebActivity.class, bundle);
    }

    public interface CallBack<T> {
        void onBack(T data) throws JSONException;
    }

    public interface CallBackError<T> {
        void onBack(T data) throws JSONException;

        void onError(String data) throws JSONException;
    }

}
