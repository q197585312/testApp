package com.unkonw.testapp;


import com.unkonw.testapp.base.BaseRetrofitPresenter;
import com.unkonw.testapp.base.LoginContract;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BaseRetrofitPresenter<String,LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public void login(LoginInfo info) {


        Disposable subscription = mApiWrapper.getUerInfo(info)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String Str) throws Exception {

                        baseView.onGetData(Str);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onGetData(throwable.getMessage());
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.dispose();
    }
}