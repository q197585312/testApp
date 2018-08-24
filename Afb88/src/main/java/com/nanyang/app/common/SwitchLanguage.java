package com.nanyang.app.common;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.load.login.LoginInfo;
import com.unkonw.testapp.libs.api.Api;

import org.reactivestreams.Subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class SwitchLanguage implements ILanguageSwitch {
    ILanguageView<String> baseView;
    CompositeDisposable mCompositeSubscription;

    public SwitchLanguage(ILanguageView<String> baseView, CompositeDisposable mCompositeSubscription) {
        this.baseView = baseView;
        this.mCompositeSubscription = mCompositeSubscription;
    }
    @NonNull
    private String getLanguage() {
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
        return lang;
    }
    @Override
    public void switchLanguage(String lang) {
//     http://main55.afb88.com/Main.aspx?lang=EN-US
//         {"ACT":"GetTT","lang":"EN-US","accType":"","pgLable":"0.8736397885598416","vsn":"4.0.121","PT":"wfMain0"}
        if(BuildConfig.FLAVOR.equals("wfmain")){
            Disposable subscription = getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, new LoginInfo().getWfLanguage(getLanguage()))

                   /* .flatMap(new Function<String, Flowable<String>>() {
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
                    })*/

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {

                            //#	Result	Protocol	Host	URL	Body	Caching	Content-Type	Process	Comments	Custom

                            /*    SwitchLanguage switchLanguage = new SwitchLanguage(baseView, mCompositeSubscription);
                                switchLanguage.switchLanguage(lang);*/
                            baseView.onLanguageSwitchSucceed("");
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

        Disposable d = Api.getService(ApiService.class).switchLanguage(AppConstant.getInstance().URL_CHANGE_LANGUAGE, lang).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.hideLoadingDialog();
                        baseView.onLanguageSwitchSucceed(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                        baseView.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(d);
    }

}
