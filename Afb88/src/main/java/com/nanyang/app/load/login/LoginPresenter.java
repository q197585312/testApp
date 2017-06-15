package com.nanyang.app.load.login;


import android.app.Activity;
import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.common.SwitchLanguage;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

class LoginPresenter extends BaseRetrofitPresenter<String, LoginContract.View> implements LoginContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginContract.View view) {
        super(view);
    }


   /* @Override
    public void login(Map<String,String> info) {


        Call<String> call = mApiWrapper.getData( info);
        call.enqueue(new Callback<String>() {//异步请求
            @Override
            public void onResponse(Call<String> call, final Response<String> response) {
                if (response.isSuccessful() ) {
                    baseView.onGetData(response.body());
                } else {
                    baseView.onGetData("失败");
                }

            }

            @Override
            public void onFailure(Call<String> call, final Throwable t) {
                baseView.onGetData(t.getMessage());
            }
        });
    }*/


    @Override
    public void login(final LoginInfo info) {
        if (checkUserAvailable(info)) {

            Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN)
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
                                    Exception exception = new Exception(((Activity) baseView).getString(R.string.System_maintenance));
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
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {
                            String lag = AfbUtils.getLanguage((Activity) baseView);
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
                            SwitchLanguage switchLanguage = new SwitchLanguage(baseView, mCompositeSubscription);
                            switchLanguage.switchLanguage(lang);

                        }
                    }, new Consumer<Throwable>() {//错误
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            baseView.onFailed(throwable.getMessage());
                            baseView.hideLoadingDialog();
                        }
                    }, new Action() {//完成
                        @Override
                        public void run() throws Exception {
                            baseView.hideLoadingDialog();
                        }
                    }, new Consumer<Subscription>() {//开始绑定
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            baseView.showLoadingDialog();
                            subscription.request(Long.MAX_VALUE);
                        }
                    });
            mCompositeSubscription.add(subscription);
        }
    }

    private boolean checkUserAvailable(LoginInfo info) {
        if (info.getTxtUserName().isEmpty()) {
            baseView.promptMsg(R.string.Account_empty);
            return false;
        }
        if (info.getPassword_password().isEmpty()) {
            baseView.promptMsg(R.string.Password_empty);
            return false;
        }
        return true;
    }


}