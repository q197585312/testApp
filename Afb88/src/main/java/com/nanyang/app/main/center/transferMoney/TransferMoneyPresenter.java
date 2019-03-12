package com.nanyang.app.main.center.transferMoney;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/3/30.
 */

public class TransferMoneyPresenter extends BaseRetrofitPresenter<TransferMoneyFragment> implements TransferMoneyContact.Presenter {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public TransferMoneyPresenter(TransferMoneyFragment view) {
        super(view);
    }

    @Override
    public void getTransferMoneyData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getTransferMoneyData(AppConstant.getInstance().URL_TRANSFER_MONEY_DATA))
                .subscribe(new Consumer<TransferMoneyBean>() {
                    @Override
                    public void accept(TransferMoneyBean transferMoneyBean) throws Exception {
                        baseContext.onGetData(transferMoneyBean);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                        Log.d(TAG, "accept: " + throwable.toString());

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
        mCompositeSubscription.add(d);
    }

    @Override
    public void gamesECashOutMonet(String money) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesECashOutMoney(AppConstant.getInstance().URL_CASHOUT_MONEY_E_GAMES, money))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetCashoutMoneyData(s);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetCashoutMoneyData(throwable.toString());
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

    @Override
    public void gamesETransferMonet(String egLimit) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesETransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_E_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetTransferMoneyData(s);
                        baseContext.refreshEdt(1);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetTransferMoneyData(throwable.toString());
                        baseContext.refreshEdt(1);
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

    @Override
    public void gamesGDCashOutMonet(String money) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesGDCashOutMoney(AppConstant.getInstance().URL_CASHOUT_MONEY_GD_GAMES, money))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetCashoutMoneyData(s);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetCashoutMoneyData(throwable.toString());
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

    @Override
    public void gamesGDTransferMonet(String egLimit) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesGDTransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_GD_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetTransferMoneyData(s);
                        baseContext.refreshEdt(2);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetTransferMoneyData(throwable.toString());
                        baseContext.refreshEdt(2);
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

    @Override
    public void games855CashOutMonet(String money) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).games855CashOutMoney(AppConstant.getInstance().URL_CASHOUT_MONEY_855_GAMES, money))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetCashoutMoneyData(s);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetCashoutMoneyData(throwable.toString());
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

    @Override
    public void games855TransferMonet(String egLimit) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).games855TransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_855_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetTransferMoneyData(s);
                        baseContext.refreshEdt(3);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetTransferMoneyData(throwable.toString());
                        baseContext.refreshEdt(3);
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

    @Override
    public void gamesW88CashOutMonet(String money) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesW88CashOutMoney(AppConstant.getInstance().URL_CASHOUT_MONEY_W88_GAMES, money))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetCashoutMoneyData(s);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetCashoutMoneyData(throwable.toString());
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

    @Override
    public void gamesW88TransferMonet(String egLimit) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesW88TransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_W88_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseContext.onGetTransferMoneyData(s);
                        baseContext.refreshEdt(4);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.onGetTransferMoneyData(throwable.toString());
                        baseContext.refreshEdt(4);
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
