package com.nanyang.app.main;


import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

class MainPresenter extends BaseRetrofitPresenter<String, MainContract.View> implements MainContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    MainPresenter(MainContract.View view) {
        super(view);
    }


    public void oddsType() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.URL_ODDS_TYPE + "MY").subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

    }
}