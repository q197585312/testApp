package com.nanyang.app.main.center.Statement;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementPresenter extends BaseRetrofitPresenter<StatementFragment> implements StatementContact.Presenter {
    public StatementPresenter(StatementFragment view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    //
    @Override
    public void getStatementData(String userName) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_STAEMENT))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetData(s);
                        baseContext.hideLoadingDialog();
                    }

                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.toString());
                        baseContext.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void confirmBlance(String url,String userName) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(url+userName))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetConfirmBlanceData(s);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "accept: " + throwable.toString());
                        baseContext.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                        baseContext.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(d);
    }

}
