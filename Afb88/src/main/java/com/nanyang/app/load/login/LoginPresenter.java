package com.nanyang.app.load.login;


import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class LoginPresenter extends BaseRetrofitPresenter<String,LoginContract.View,ApiLogin> implements LoginContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public LoginPresenter(LoginContract.View view) {
        super(view);
    }

    @Override
    public ApiLogin createRetrofitApi() {
        return new ApiLogin();
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
    public void login(LoginInfo info) {
        Disposable subscription = mApiWrapper.doLogin(info)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        baseView.onGetData(Str);
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