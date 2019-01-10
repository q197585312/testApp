package com.nanyang.app.load.welcome;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.common.SwitchLanguage;
import com.nanyang.app.load.login.LoginActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.SystemTool;

import org.reactivestreams.Subscription;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import cn.finalteam.toolsfinal.StringUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

import static com.unkonw.testapp.libs.api.Api.getService;

class WelcomePresenter extends BaseRetrofitPresenter<String, WelcomeContract.View> implements WelcomeContract.Presenter {
    private File file;

    //构造 （activity implements v, 然后WelcomePresenter(this)构造出来）
    WelcomePresenter(WelcomeContract.View view) {
        super(view);
    }


    @Override
    public void checkVersion(final String versionName) {
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).checkVersion())
//                mApiWrapper.checkVersion()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String response) throws Exception {
                        baseView.onGetData(response);
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


    @Override
    public void updateVersion(final String version) {
        String path = Environment.getExternalStorageDirectory().getPath();

        file = new File(path, "afb88.apk");
        file.deleteOnExit();
        Disposable subscription = getService(ApiService.class).updateVersion().observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
//                mApiWrapper.updateVersion()
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
                            fos.write(buffer, 0, len);
                            baseView.onLoadingApk(len, contentLength);
                        }
                        fos.flush();
                        fos.close();
                        bis.close();
                        is.close();

                        if (file.exists() && file.length() > 0)
                            baseView.onLoadEnd(file);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onLoadError(throwable.getMessage());

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
        if (intent == null || extras == null) {
            ((BaseActivity) baseView.getContextActivity()).skipAct(LoginActivity.class);
            baseView.getContextActivity().finish();

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
        Disposable subscription = getService(ApiService.class).doPostJson(ckAccUrl, body)

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
                })

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        if (!StringUtils.isEmpty(str) && str.contains("wfMain")) {
                            SwitchLanguage switchLanguage = new SwitchLanguage(baseView, mCompositeSubscription);
                            switchLanguage.switchLanguage(getSkipLanguage(language), "MY");
                            AfbApplication app = (AfbApplication) baseView.getContextActivity().getApplication();
                            app.getUser().setUserName(us);
                            app.getUser().setPassword("");
                        }
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
        return;

    }

    private String getSkipLanguage(String language) {
        String lang;
        switch (language) {
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


}