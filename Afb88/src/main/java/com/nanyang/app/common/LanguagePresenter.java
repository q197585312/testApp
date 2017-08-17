package com.nanyang.app.common;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;
import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LanguagePresenter extends BaseRetrofitPresenter<String, ILanguageView<String>> {
    SwitchLanguage switchLanguage;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public LanguagePresenter(ILanguageView<String> view) {
        super(view);
        switchLanguage = new SwitchLanguage(view, mCompositeSubscription);
    }
    public void switchLanguage(String lang) {
        switchLanguage.switchLanguage(lang);
    }
    public void skipGd88() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().HOST+"_View/LiveDealerGDC.aspx")


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        int start = Str.indexOf("TransferBalance");
                        int end = Str.indexOf("\" id=\"form1\"");

                        String k = "";
                        if (start > 0 && end > 0 && end > start) {
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
                            String url = Str.substring(start, end);
                            k = url.substring(url.indexOf("k="));
                            baseView.onGetData(k);
                        } else if (Str.contains("Transaction not tally")) {
                            ToastUtils.showShort("Transaction not tally");
                        } else if (Str.contains("Session Expired")) {
                            ToastUtils.showShort("Session Expired");
                        } else if (Str.contains("Account is LOCKED")) {
                            ToastUtils.showShort("Account is LOCKED! Please contact your agent!");
                        } else {
                            ToastUtils.showShort("Failed");
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
    public void getTransferMoneyData(final String data) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getTransferMoneyData(AppConstant.getInstance().URL_TRANSFER_MONEY_DATA))
                .subscribe(new Consumer<TransferMoneyBean>() {
                    @Override
                    public void accept(TransferMoneyBean transferMoneyBean) throws Exception {
                        baseView.getMoneyMsg(transferMoneyBean,data);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                        Log.d(TAG, "accept: " + throwable.toString());

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }
    public void gamesGDTransferMonet(String egLimit, final String data) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesGDTransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_GD_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetTransferMoneyData(0,s,data);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onGetTransferMoneyData(-1,throwable.toString(),data);
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

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
