package com.nanyang.app.load.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.SwitchLanguage;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.MainPresenter;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static com.unkonw.testapp.libs.api.Api.getService;

class WelcomePresenter extends BaseRetrofitPresenter<WelcomeActivity> {
    private File file;

    //构造 （activity implements v, 然后WelcomePresenter(this)构造出来）
    WelcomePresenter(WelcomeActivity view) {
        super(view);
    }


    public void checkVersion(final String versionName, BaseConsumer<String> baseConsumer) {
        doRetrofitApiOnUiThread(getService(ApiService.class).checkVersion(), baseConsumer);
    }


    public void updateVersion(final String version) {
        String path = Environment.getExternalStorageDirectory().getPath();
        file = new File(path, "afb88.apk");
        file.deleteOnExit();
        Disposable subscription = getService(ApiService.class).updateVersion().observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
                .subscribe(new Consumer<ResponseBody>() {//onNext
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        long contentLength;
                        InputStream is = response.byteStream();
                        contentLength = response.contentLength();
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = bis.read(buffer)) != -1) {
                            baseContext.onLoadingApk(len, contentLength);
                            fos.write(buffer, 0, len);
                        }
                        fos.flush();
                        fos.close();
                        bis.close();
                        is.close();
                        Thread.sleep(1500);
                        if (file.exists() && file.length() > 0)
                            baseContext.onLoadEnd(file);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onLoadError(throwable.getMessage());

                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        Log.d("LOAD", "wancheng");
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

    }

    public void checkInitCheck(Intent intent) {
        Bundle extras = intent.getExtras();
        if (intent == null || extras == null || extras.getString("companyKey") == null) {
            ((BaseActivity) baseContext.getBaseActivity()).skipAct(LoginActivity.class);
            baseContext.getBaseActivity().finish();

        } else {
            String companyKey = extras.getString("companyKey");
            String userName = extras.getString("userName");
            String us = extras.getString("us");
            String language = extras.getString("lang");
            String webId = extras.getString("webId");
            String currencyName = extras.getString("currencyName");
            skipLogin(companyKey, userName, us, language, webId, currencyName);
        }
    }

    public void skipLogin(String companyKey, String userName, final String us, final String language, final String webId, final String currencyName) {

        String ckAccUrl = BuildConfig.HOST_AFB + "Public/ckAcc.ashx";
        Map<String, String> map = new HashMap<>();
        CompanyKeyBean info = new CompanyKeyBean(companyKey, userName);
        Gson gson = new Gson();
        String obj = gson.toJson(info);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostJson(ckAccUrl, body)

                        .flatMap(new Function<String, Flowable<String>>() {
                            @Override
                            public Flowable<String> apply(String s) throws Exception {
                                CkAccResponseBean bean = new Gson().fromJson(s, new TypeToken<CkAccResponseBean>() {
                                }.getType());
                                if (bean != null && !bean.getError().equals("1")) {
                                    final String url_login = BuildConfig.HOST_AFB + "Public/validate.aspx?us=" + webId + "s" + us + "&k=" + bean.getToken() + "&device=m&oddsstyle=MY&oddsmode=Double&lang=" + language + "&currencyName=" + currencyName;
                                    return getService(ApiService.class).getData(url_login);
                                }
                                return null;
                            }
                        }).flatMap(new Function<String, Flowable<String>>() {
                            @Override
                            public Flowable<String> apply(String str) throws Exception {
                                if (!StringUtils.isEmpty(str) && str.contains("wfMain")) {
                                    SwitchLanguage switchLanguage = new SwitchLanguage(baseContext);
                                    AfbApplication app = (AfbApplication) baseContext.getBaseActivity().getApplication();
                                    app.getUser().setLoginName(us);
                                    app.getUser().setPassword("");
                                    return switchLanguage.switchLanguage(new LanguageHelper(baseContext.getBaseActivity()).getLanguage(), "MY");
                                }
                                return null;
                            }
                        })
                , new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(String data) {
                        String language = new LanguageHelper(baseContext.getBaseActivity()).getLanguage();
                        LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
                        helper.doRetrofitApiOnUiThread(new LoginInfo.LanguageWfBean("AppGetDate", language, "wfMainH50"), new MainPresenter.CallBack<String>() {
                            @Override
                            public void onBack(String data) {
                                PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                                personalInfo.setPassword(((AfbApplication) baseContext.getBaseActivity().getApplication()).getUser().getPassword());
                                ((AfbApplication) baseContext.getBaseActivity().getApplication()).setUser(personalInfo);
                                WelcomePresenter.this.baseContext.onLanguageSwitchSucceed(data);
                            }
                        });

                    }
                });
    }
}