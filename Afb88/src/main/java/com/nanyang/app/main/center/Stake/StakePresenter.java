package com.nanyang.app.main.center.Stake;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakePresenter extends BaseRetrofitPresenter<StakeFragment> implements StakeContact.Presenter {
    public StakePresenter(StakeFragment view) {
        super(view);
    }

    @Override
    public void getStakeListData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_STAKE))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stakeListBeen) throws Exception {
                        baseContext.onGetData(stakeListBeen);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
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
}
