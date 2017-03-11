package com.nanyang.app.main.center;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementPresenter extends BaseRetrofitPresenter<String, StatementContact.View> implements StatementContact.Presenter {
    public StatementPresenter(StatementContact.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void getStatementData(String mb, String userName) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).statementData(mb, userName))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void init(final String mb, final String userName) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).init())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        getStatementData(mb, userName);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(disposable);
    }
}
