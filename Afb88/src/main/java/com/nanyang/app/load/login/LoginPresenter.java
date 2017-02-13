package com.nanyang.app.load.login;


import com.nanyang.app.ApiService;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.api.Api;
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

class LoginPresenter extends BaseRetrofitPresenter<String, LoginContract.View, ApiLogin> implements LoginContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginContract.View view) {
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
        if(checkUserAvailable(info)) {
            Disposable subscription = mApiWrapper.doLogin(info)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String regex=".*<script language='javascript'>window.open\\('(.*?)'.*?";
                            Pattern p= Pattern.compile(regex);
                            Matcher m=p.matcher(s);
                            if(m.find()){
                                String url=m.group(1);
                                return Api.getService(ApiService.class).timerRun2(url);
                            }
                            return null;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {
                                if(str.contains("window.open(")) {
                                    baseView.onGetData("Login Success");
                                }
                            else{
                                    baseView.onFailed("Login Failed");
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
        }
    }

    private boolean checkUserAvailable(LoginInfo info) {
        if(info.getTxtUserName().isEmpty()){
            baseView.promptMsg(R.string.Account_empty);
            return false;
        }
        if(info.getPassword_password().isEmpty()){
            baseView.promptMsg(R.string.Password_empty);
            return false;
        }
        return true;
    }


}