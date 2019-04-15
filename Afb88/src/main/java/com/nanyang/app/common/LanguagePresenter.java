package com.nanyang.app.common;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.BaseSwitchPresenter;
import com.nanyang.app.main.MainPresenter;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LanguagePresenter extends BaseSwitchPresenter {
    SwitchLanguage switchLanguage;
    ILanguageView changeLanguageFragment;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public LanguagePresenter(IBaseContext iBaseContext) {
        super(iBaseContext);
        switchLanguage = new SwitchLanguage(iBaseContext);
        changeLanguageFragment = (ILanguageView) iBaseContext;
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

    public void skipGd88(final IBaseView baseView) {
        BaseConsumer<String> baseConsumer = new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String Str) {
                int start = Str.indexOf("TransferBalance");
                int end = Str.indexOf("\" id=\"form1\"");

                String k = "";
                if (start > 0 && end > 0 && end > start) {
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
                    String url = Str.substring(start, end);
                    k = url.substring(url.indexOf("k="));
                    baseView.onGetData(k);
                } else if (Str.contains("Transaction not tally")) {
                    ToastUtils.showShort("Transaction not tally");
                } else if (Str.contains("Session Expired")) {
                    ToastUtils.showShort("Session Expired");
                } else if (Str.contains("Account is LOCKED")) {
                    ToastUtils.showShort("Account is LOCKED! Please contact your agent!");
                } else {
                    ToastUtils.showShort("Failed");
                }

            }
        };
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(AppConstant.getInstance().HOST + "_View/LiveDealerGDC.aspx"), baseConsumer);

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
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN)
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
                }), baseConsumer);

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

    public void loadAllMainData(LoginInfo.LanguageWfBean languageWfBean, final MainPresenter.CallBack<String> back) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    if (jsonArrayData.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
                        JSONObject jsonObject = jsonArrayData.getJSONObject(0);
                        back.onBack(jsonObject.toString());
                    }
                }
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }
}
