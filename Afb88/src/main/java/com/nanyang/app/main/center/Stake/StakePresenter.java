package com.nanyang.app.main.center.Stake;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.center.model.StakeListBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakePresenter extends BaseRetrofitPresenter<StakeListBean, StakeContact.View> implements StakeContact.Presenter {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public StakePresenter(StakeContact.View view) {
        super(view);
    }

    @Override
    public void getStakeListData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getStakeData(""/*AppConstant.URL_STAKE*/))
                .subscribe(new Consumer<StakeListBean>() {
                    @Override
                    public void accept(StakeListBean stakeListBeen) throws Exception {
                        Log.d(TAG, "accept: " + stakeListBeen);
                        baseView.onGetData(stakeListBeen);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
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
}
