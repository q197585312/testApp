package com.nanyang.app.common;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Been.AppVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.Setting.SettingFragment;
import com.nanyang.app.main.Setting.SettingInfoBean;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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

public class LanguagePresenter extends BaseSwitchPresenter {
    SwitchLanguage switchLanguage;
    ILanguageView changeLanguageFragment;
    IGetRefreshMenu iGetRefreshMenu;
    SettingFragment settingFragment;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public LanguagePresenter(IBaseContext iBaseContext) {
        super(iBaseContext);
        switchLanguage = new SwitchLanguage(iBaseContext);
        changeLanguageFragment = (ILanguageView) iBaseContext;
        if (iBaseContext instanceof IGetRefreshMenu) {
            iGetRefreshMenu = (IGetRefreshMenu) iBaseContext;
        } else if (iBaseContext instanceof SettingFragment) {
            settingFragment = (SettingFragment) iBaseContext;
        } else {
            iGetRefreshMenu = null;
        }
    }

    //    https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"GetTT","lang":"ZH-CN","pgLable":"0.18120996831154568","vsn":"4.0.12","PT":"wfLoginH50"}&_db={}
    public void switchLanguage(String lang) {
        doRetrofitApiOnDefaultThread(switchLanguage.switchLanguage(lang), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                changeLanguageFragment.onLanguageSwitchSucceed(data);
            }
        });

    }

    public void getSkipGd88Data() {
        Disposable subscription = getService(ApiService.class).getResponse(BuildConfig.HOST_AFB + "_View/LiveDealerGDC.aspx").subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response>() {
                    @Override
                    public void accept(Response responseBodyResponse) throws Exception {
                        baseContext.hideLoadingDialog();
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
                                baseContext.getBaseActivity().startActivity(intent);
                            } else {
                                downloadGd88();
                            }
                        } else {
                            ToastUtils.showShort("not find agent!");
                        }
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
        if (BuildConfig.FLAVOR.equals("afb1188")) {
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
            return;
        }
        /*doRetrofitApiOnUiThread(getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN)
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        String substring1 = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"") + 24);
                        String __VIEWSTATE = substring1.substring(0, substring1.indexOf("\""));
                        String substring2 = s.substring(s.indexOf("id=\"__EVENTVALIDATION\" value=\"") + 30);
                        String __EVENTVALIDATION = substring2.substring(0, substring2.indexOf("\""));
                        info.set__VIEWSTATE(__VIEWSTATE);
                        info.set__EVENTVALIDATION(__EVENTVALIDATION);

                        return getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, info.getMap());

                    }
                })
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        String regex = ".*<script language='javascript'>window.open\\('(.*?)'.*?";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(s);
                        if (m.find()) {

                            String url = m.group(1);
                            if (url.contains("Maintenance")) {
                                Exception exception = new Exception((baseContext.getBaseActivity()).getString(R.string.System_maintenance));
                                throw exception;
                            } else {
//                                http://a0096f.panda88.org/Public/validate.aspx?us=demoafbai5&k=1a56b037cee84f08acd00cce8be54ca1&r=841903858&lang=EN-US
                                String host = url.substring(0, url.indexOf("/", 9));
                                AppConstant.getInstance().setHost(host + "/");
                                Log.d("OKHttp", url);
                                return getService(ApiService.class).getData(url);
                            }
                        }
                        Exception exception1 = new Exception("Server Error");
                        throw exception1;

                    }
                })
                //http://main55.afb88.com/_bet/panel.aspx
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String str) throws Exception {
                        if (str.contains("window.open(")) {
                            return getService(ApiService.class).getData(AppConstant.getInstance().URL_PANEL);
                        }
                        Exception exception1 = new Exception("Login Failed");
                        throw exception1;


                    }
                }).flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String str) throws Exception {
                        LanguageHelper helper = new LanguageHelper(baseContext.getBaseActivity());
                        SwitchLanguage switchLanguage = new SwitchLanguage<IBaseContext>(baseContext.getBaseActivity());
                        return switchLanguage.switchLanguage(helper.getLanguage());
                    }
                }), baseConsumer)*/;

    }

    public void switchOddsType(final String oddsType, BaseConsumer<String> consumer) {


        Map<String, String> map = new HashMap<>();

        LoginInfo.LanguageWfBean languageWfBean = new LoginInfo.LanguageWfBean(getLanguage());
        languageWfBean.setAccType(oddsType);
        map.put("_fm", languageWfBean.getJson());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, map).flatMap(new Function<String, Flowable<String>>() {
            @Override
            public Flowable<String> apply(String s) throws Exception {
                return getService(ApiService.class).getData(AppConstant.getInstance().URL_ODDS_TYPE + oddsType);
            }
        }), consumer);
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

    public void getSettingContentData() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("ACT", "GetTT");
        map.put("PT", "wfMainH50");
        map.put("lang", AfbUtils.getLangParamStr(settingFragment.getContext()));
        map.put("accType", "");
        map.put("pgLable", "");
        map.put("vsn", "");
        String p = AfbUtils.getJsonParam(map);
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONObject jsonObject = jsonArrayData1.getJSONObject(0);
                    SettingAllDataBean settingAllDataBean = gson.fromJson(jsonObject.toString(), SettingAllDataBean.class);
                    settingFragment.onGetSettingContentData(handleSettingData(settingAllDataBean));
                }
            }
        });
    }

    private List<SettingInfoBean> handleSettingData(SettingAllDataBean settingAllDataBean) {
        List<SettingInfoBean> beanList = new ArrayList<>();
        SettingInfoBean infoBean1 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.login_name), settingAllDataBean.getLoginName(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean2 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.Password), "**********", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean3 = new SettingInfoBean("1", baseContext.getBaseActivity().getBaseActivity().getString(R.string.choose_language), baseContext.getBaseActivity().getString(R.string.language_switch), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean4 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.Odds_Type), settingAllDataBean.getAccType2(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean5 = new SettingInfoBean("2", baseContext.getBaseActivity().getString(R.string.better_odds), settingAllDataBean.getBetterOdds() + "", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean6 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.quick_bet_amount), baseContext.getBaseActivity().getString(R.string.customise), 0, 0, 0, 0, 0, 0);
        infoBean6.setCustomizeAmount(settingAllDataBean.getAccamount() + "");
        SettingInfoBean infoBean7 = new SettingInfoBean("2", baseContext.getBaseActivity().getString(R.string.auto_refresh), "1", 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean8 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.default_sort), settingAllDataBean.getAccDefaultSorting(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean9 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.market_type), settingAllDataBean.getAccMarketType(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean10 = new SettingInfoBean("1", baseContext.getBaseActivity().getString(R.string.score_sound), settingAllDataBean.getAccScoreSound(), 0, 0, 0, 0, 0, 0);
        SettingInfoBean infoBean11 = new SettingInfoBean("3", baseContext.getBaseActivity().getString(R.string.chip_set), "", 0, R.mipmap.chip5000, R.mipmap.chip10000, R.mipmap.chip30000, R.mipmap.chip50000, R.mipmap.chip100000);
        infoBean11.setChipSize2(5000);
        infoBean11.setChipSize3(10000);
        infoBean11.setChipSize4(30000);
        infoBean11.setChipSize5(50000);
        infoBean11.setChipSize6(100000);
        SettingInfoBean infoBean12 = new SettingInfoBean("3", "", "", R.mipmap.chip1, R.mipmap.chip10, R.mipmap.chip50, R.mipmap.chip100, R.mipmap.chip500, R.mipmap.chip1000);
        infoBean12.setChipSize1(1);
        infoBean12.setChipSize2(10);
        infoBean12.setChipSize3(50);
        infoBean12.setChipSize4(100);
        infoBean12.setChipSize5(500);
        infoBean12.setChipSize6(1000);
        beanList.add(infoBean1);
        beanList.add(infoBean2);
        beanList.add(infoBean3);
        beanList.add(infoBean4);
        beanList.add(infoBean5);
        beanList.add(infoBean6);
        beanList.add(infoBean7);
        beanList.add(infoBean8);
        beanList.add(infoBean9);
        beanList.add(infoBean10);
        beanList.add(infoBean11);
        beanList.add(infoBean12);
        return beanList;
    }

    public interface CallBack<T> {
        void onBack(T data);
    }
}
