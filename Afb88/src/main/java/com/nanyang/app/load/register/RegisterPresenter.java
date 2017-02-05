package com.nanyang.app.load.register;


import com.nanyang.app.load.login.ApiLogin;
import com.nanyang.app.load.UserInfo;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class RegisterPresenter extends BaseRetrofitPresenter<String,RegisterContract.View,ApiRegister> implements RegisterContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    public RegisterPresenter(RegisterContract.View view) {
        super(view);
    }

    @Override
    public ApiRegister createRetrofitApi() {
        return new ApiRegister();
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
    public void register(UserInfo info) {
        Disposable subscription = mApiWrapper.getPersonalInfo(info)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        baseView.onGetData(Str);
                        baseView.hideLoadingDialog();
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