package com.nanyang.app.common;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.unkonw.testapp.libs.api.Api;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

    @Override
    public void switchLanguage(String lang) {
//     http://main55.afb88.com/Main.aspx?lang=EN-US
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
