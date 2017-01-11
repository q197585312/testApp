package com.unkonw.testapp.login;


import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BaseRetrofitPresenter<String,LoginContract.View> implements LoginContract.Presenter {

    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
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
    }

    @Override
    public void login(LoginInfo info) {
        Disposable subscription = mApiWrapper.getPersonalInfo(info)
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String Str) throws Exception {
                        baseView.onGetData(Str);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onGetData(throwable.getMessage());
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
                        baseView.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void unSubscribe() {
        mCompositeSubscription.clear();
    }
}