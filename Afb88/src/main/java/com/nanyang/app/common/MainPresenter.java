package com.nanyang.app.common;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Been.AppVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.Setting.SettingFragment;
import com.nanyang.app.main.home.LoadPCasinoDataHelper;
import com.nanyang.app.main.home.SaCasinoWfBean;
import com.nanyang.app.main.home.keno.WebActivity;
import com.nanyang.app.main.home.sport.model.RunMatchInfo;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.utils.LogUtil;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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

    public void clickGdGameItem(String g) {

        if (g.equals("Casino")) {
            ((BaseToolbarActivity) baseContext.getBaseActivity()).getSkipGd88Data();
        } else if (g.equals("PRAGMATIC CASINO")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("GetTT", "", "wfPragmatic"), AppConstant.getInstance().HOST, "^.*window.open\\(\\'\\.\\./\\.\\./([^\\']+)\\'.*$");
        } else if (g.equals("PG CASINO")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("GetTT", "", "wfPGHome"), "", "^.*(http[^\"]+)\"\\}.*$");
        } else if (g.equals("PS GAMING")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("GetTT", "", "wfPSLogin"), AppConstant.getInstance().HOST, "^.*window.open\\(\\'\\.\\./\\.\\./([^\\']+)\\'.*$");
        } else if (g.equals("SEXY CASINO")) {
            skipPCashio("post", AppConstant.getInstance().HOST + "api/SGCheckonline", g, new SaCasinoWfBean("", "", "SGCheckonline"), "", "^.*\"(http[^\"]+)\",.*$");
//                    skipPCashio(item.getG(),"GetTT",  "wfPGHome","","^.*(http[^\"]+)\"\\}.*$");
        } else if (g.equals("SA CASINO")) {
            skipPCashio("post", AppConstant.getInstance().HOST + "api/SACheckonline", g, new SaCasinoWfBean("", "", "SACheckonline"), "", "^.*\"(http[^\"]+)\",.*$");
        } else if (g.equals("EVOPLAY")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("GetTT", "", "wfEVLogin"), AppConstant.getInstance().HOST, "^.*window.open\\(\\'\\.\\./\\.\\./([^\\']+)\\'.*$");
        } else if (g.equals("DG CASINO")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("OpenDGGamee", "", "wfDGLogin"), "", "^.*\"(http[^\"]+)\",.*$");
        } else if (g.equals("WM CASINO")) {
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("OpenWMGamee", "", "wfWMLogin"), "", "^.*\"(http[^\"]+)\",.*$");
        } else if (g.equals("NL CASINO")) {
            //{"ACT":"OpenNLGamee","PT":"wfNLLogin"
            skipPCashio("get", "", g, new LoginInfo.LanguageWfBean("OpenNLGamee", "", "wfNLLogin"), "", "^.*\"(http[^\"]+)\",.*$");
        } else if (g.equals("LG CASINO")) {
            skipPCashio("post", AppConstant.getInstance().HOST + "api/LGLogin", g, new SaCasinoWfBean("", "", "LGLogin"), "", "^.*\"(http[^\"]+)\",.*$");
//
        }
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

                    if (data.contains("_delay=20")) {
                        ((AfbApplication) baseContext.getBaseActivity().getApplication()).setDelayBet(5000);
                    } else if (data.contains("_delay=30")) {
                        ((AfbApplication) baseContext.getBaseActivity().getApplication()).setDelayBet(7000);
                    } else {
                        ((AfbApplication) baseContext.getBaseActivity().getApplication()).setDelayBet(0);
                    }
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

    public void loadAllImages(final CallBack<AllBannerImagesBean> back) {
//        http://www.appgd88.com/api/afb1188.php?app=afb88&lang=EN-CA
        doRetrofitApiOnUiThread(getService(ApiService.class).getAllImagesData(BuildConfig.ImgConfig_URL), new BaseConsumer<AllBannerImagesBean>(baseContext) {
            @Override
            protected void onBaseGetData(AllBannerImagesBean data) throws JSONException {
//                @Subscribe(threadMode = ThreadMode.MainThread)
                LogUtil.d(getClass().getSimpleName(), "sendEvent--------------->" + data.toString());
                ((BaseToolbarActivity) baseContext.getBaseActivity()).getApp().setListMainPictures(data.getMain());
                ((BaseToolbarActivity) baseContext.getBaseActivity()).getApp().setListMainBanners(data.getMainBanners());
                if (back != null)
                    back.onBack(data);

            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });
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
    public void skipPCashio(String Method, String url, final String itemG, LoginInfo.LanguageWfBean wfBean, final String host, String matches) {


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
            }, matches);
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
            }, matches);


        }
    }

    private void goPCasino(String data, String itemG, String host) {
        final SportIdBean sportIdBean = AfbUtils.getSportByG(itemG);
        String url = host + data;
        String string = baseContext.getBaseActivity().getString(sportIdBean.getTextRes());
        goWebActivity(url, string, false);
    }

    public void goWebPc(String url) {
        String string = baseContext.getBaseActivity().getString(R.string.app_name);
        goWebActivity(url, string, true);
    }

    private void goWebActivity(String url, String string, boolean canFinish) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString(AppConstant.KEY_STRING, string);
        bundle.putBoolean(AppConstant.KEY_BOOLEAN, canFinish);
        baseContext.getBaseActivity().skipAct(WebActivity.class, bundle);
    }

    Runnable runnable;

    public void loadAllRunMatches(final View fl_top_video, final Handler handler, final CallBack<List<RunMatchInfo>> back) {
//        http://www.appgd88.com/api/afb1188.php?app=afb88&lang=EN-CA
        runnable = new Runnable() {
            @Override
            public void run() {
                loadAllRunMatches(fl_top_video, handler, back);
            }
        };
        LoadPCasinoDataHelper<LoginInfo.LanguageWfBean> helper = new LoadPCasinoDataHelper<>(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
        helper.doRetrofitApiOnUiThreadBackPostJson(AppConstant.getInstance().HOST + "api/pgGetTVID", new SaCasinoWfBean("GETTV", "", "pgGetTVID"), new CallBackError<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                LogUtil.d("RunMatchInfo", data);
                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = jsonObject.optJSONArray("db");
                ArrayList<RunMatchInfo> runMatchInfoList = new ArrayList<>();
                if (jsonArray != null && jsonArray.length() > 0) {
                    JSONArray jsonArray1 = jsonArray.optJSONObject(0).optJSONArray("data");
                    for (int i = 0; i < jsonArray1.length(); i++) {
                        JSONArray jsonArray2 = jsonArray1.optJSONArray(i);
                        if (jsonArray2 != null && jsonArray2.length() > 7) {
                            runMatchInfoList.add(new RunMatchInfo(
                                    jsonArray2.optString(0),
                                    jsonArray2.optString(1),
                                    jsonArray2.optString(2),
                                    jsonArray2.optString(3),
                                    jsonArray2.optString(4),
                                    jsonArray2.optString(5),
                                    jsonArray2.optString(6),
                                    jsonArray2.optString(7),
                                    jsonArray2.length() > 8 ? jsonArray2.optString(8) : jsonArray2.optString(5),
                                    jsonArray2.length() > 10 ? jsonArray2.optString(9) : "0",
                                    jsonArray2.length() > 10 ? jsonArray2.optString(10) : "0"
                            ));
                        }
                    }
                }
                back.onBack(runMatchInfoList);
                if (fl_top_video != null && fl_top_video.getVisibility() == View.VISIBLE) {
                    handler.postDelayed(runnable, 3000);
                } else {
                    handler.removeCallbacks(runnable);
                }
            }

            @Override
            public void onError(String data) throws JSONException {
                ToastUtils.showLong(data);
            }
        }, "");
    }

    public boolean isTouchInView(MotionEvent ev, View view) {
        if (view == null) {
            return false;
        }
        int x = (int) ev.getRawX();
        int y = (int) ev.getRawY();
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];
        int right = left + view.getMeasuredWidth();
        int bottom = top + view.getMeasuredHeight();
        //view.isClickable() &&
        if (y >= top && y <= bottom && x >= left
                && x <= right) {
            return true;
        }
        return false;

    }

    public void initMatchTypePop() {

    }


    public interface CallBack<T> {
        void onBack(T data) throws JSONException;
    }

    public interface CallBackError<T> {
        void onBack(T data) throws JSONException;

        void onError(String data) throws JSONException;
    }

}
