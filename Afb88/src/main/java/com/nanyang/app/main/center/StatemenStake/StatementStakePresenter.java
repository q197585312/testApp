package com.nanyang.app.main.center.StatemenStake;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.center.model.StatementStakeListBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakePresenter extends BaseRetrofitPresenter<StatementStakeActivity> implements StatementStakeContact.Presenter {

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public StatementStakePresenter(StatementStakeActivity view) {
        super(view);
    }

    @Override
    public void getThisBet(String url) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).statementStake(url))
                .subscribe(new Consumer<List<StatementStakeListBean>>() {
                    @Override
                    public void accept(List<StatementStakeListBean> statementStakeListBeen) throws Exception {
                        baseContext.onGetData(statementStakeListBeen);
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Consumer", "accept: " + throwable.toString());
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
        mCompositeSubscription.add(d);
    }
}
