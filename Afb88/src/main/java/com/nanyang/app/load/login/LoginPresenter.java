package com.nanyang.app.load.login;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.SwitchLanguage;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

import static com.unkonw.testapp.libs.api.Api.getService;

class LoginPresenter extends BaseRetrofitPresenter<LoginActivity> {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginActivity view) {
        super(view);
    }


   /* @Override
    public void login(Map<String,String> info) {


        Call<String> call = mApiWrapper.getData( info);
        call.enqueue(new Callback<String>() {//异步请求
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                if (response.isSuccessful() ) {
                    baseContext.onBaseGetData(response.body());
                } else {
                    baseContext.onBaseGetData("失败");
                }

            }

            @Override
            public void onFailure(Call<String> call, final Throwable t) {
                baseContext.onBaseGetData(t.getMessage());
            }
        });
    }*/

    @NonNull
    private String getLanguage() {
        String lag = AfbUtils.getLanguage((Activity) baseContext);
        String lang;
        switch (lag) {
            case "zh":
                lang = "ZH-CN";
                break;
            case "en":
                lang = "EN-US";
                break;
            case "th":
                lang = "TH-TH";
                break;
            case "ko":
                lang = "EN-TT";
                break;
            case "vi":
                lang = "EN-IE";
                break;
            case "tr":
                lang = "UR-PK";
                break;

            default:
                lang = "EN-US";
                break;
        }
        return lang;
    }


    public void login(final LoginInfo info, BaseConsumer<String> baseConsumer) {
        if (checkUserAvailable(info)) {
            //http://www.afb1188.com/W0/Pub/pcode.axd
            final String url_login = AppConstant.getInstance().URL_LOGIN;
            Map<String, String> infoWfmain = info.getWfmain("Login", getLanguage());
            if (BuildConfig.FLAVOR.equals("afb1188")) {
                doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url_login, infoWfmain)

                        .flatMap(new Function<String, Flowable<String>>() {
                            @Override
                            public Flowable<String> apply(String s) throws Exception {
                                String regex = "window.location";
                                Pattern p = Pattern.compile(regex);
                                Matcher m = p.matcher(s);
                                if (m.find()) {
                                    return getService(ApiService.class).getData(url_login);
                                }
                                Exception exception1 = new Exception("Server Error");
                                throw exception1;

                            }
                        }).flatMap(new Function<String, Flowable<String>>() {
                            @Override
                            public Flowable<String> apply(String s) throws Exception {
                                SwitchLanguage switchLanguage = new SwitchLanguage<IBaseContext>(baseContext);
                                return switchLanguage.switchLanguage(getLanguage(), "MY");
                            }

                        }), baseConsumer);
                return;
            }
            doRetrofitApiOnUiThread(getService(ApiService.class).getData(url_login)
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String substring1 = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"") + 24);
                            String __VIEWSTATE = substring1.substring(0, substring1.indexOf("\""));
                            String substring2 = s.substring(s.indexOf("id=\"__EVENTVALIDATION\" value=\"") + 30);
                            String __EVENTVALIDATION = substring2.substring(0, substring2.indexOf("\""));

                            info.set__VIEWSTATE(__VIEWSTATE);
                            info.set__EVENTVALIDATION(__EVENTVALIDATION);

                            return getService(ApiService.class).doPostMap(url_login, info.getMap());

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
                                    Exception exception = new Exception(((Activity) baseContext).getString(R.string.System_maintenance));
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
                            String lang = getLanguage();
                            SwitchLanguage switchLanguage = new SwitchLanguage<IBaseContext>(baseContext);
                            return switchLanguage.switchLanguage(lang);

                        }
                    }), baseConsumer);
        }
    }


    private boolean checkUserAvailable(LoginInfo info) {
        if (info.getTxtUserName().isEmpty()) {
            baseContext.promptMsg(R.string.Account_empty);
            return false;
        }
        if (info.getPassword_password().isEmpty()) {
            baseContext.promptMsg(R.string.Password_empty);
            return false;
        }
        return true;
    }
    public void loadAllImages() {
//        http://www.appgd88.com/api/afb1188.php?app=afb88&lang=EN-CA
        doRetrofitApiOnDefaultThread(getService(ApiService.class).getAllImagesData("http://www.appgd88.com/api/afb1188.php?app=" + BuildConfig.FLAVOR + "&lang=" +  new LanguageHelper(baseContext.getBaseActivity()).getLanguage()), new BaseConsumer<AllBannerImagesBean>(baseContext) {
            @Override
            protected void onBaseGetData(AllBannerImagesBean data) {
//                @Subscribe(threadMode = ThreadMode.MainThread)
                    LoginPresenter.this.baseContext.sendImageEvent(data);
            }
        });
    }
}