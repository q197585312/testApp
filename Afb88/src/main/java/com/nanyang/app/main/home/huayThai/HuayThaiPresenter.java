package com.nanyang.app.main.home.huayThai;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HuayThaiPresenter extends BaseRetrofitPresenter<HuayDrawDateInfo , HuayThaiContract.View> implements HuayThaiContract.Presenter {

    public HuayThaiPresenter(HuayThaiContract.View view) {
        super(view);
    }

    @Override
    public void refresh(String url) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getHuyaDrawDate(url))
                .subscribe(new Consumer<HuayDrawDateInfo>() {
                    @Override
                    public void accept(HuayDrawDateInfo s) throws Exception {
                        baseView.onGetData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
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
                        subscription.request(Integer.MAX_VALUE);
                        baseView.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(disposable);

    }

    @Override
    public void submitBet(String url,HuayThaiBetSubmitBean info) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).doHuayMap(url,info.getThaiThousandBetSubmitMap()))
                .subscribe(new Consumer<ResultBean>() {
                    @Override
                    public void accept(ResultBean s) throws Exception {
                        baseView.onResultData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
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
                        subscription.request(Integer.MAX_VALUE);
                        baseView.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void unSubscribe() {

    }

}
