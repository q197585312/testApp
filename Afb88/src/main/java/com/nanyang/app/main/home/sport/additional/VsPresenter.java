package com.nanyang.app.main.home.sport.additional;


import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.ScaleBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.concurrent.TimeUnit;

import cn.finalteam.toolsfinal.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

class VsPresenter extends BaseRetrofitPresenter<ScaleBean, BetView<ScaleBean>> implements IBasePresenter {
    private String type;
    private BallInfo bean;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    VsPresenter(BetView view) {
        super(view);
    }


    public void scale(BallInfo item, String matchType) {
        this.bean = item;
        this.type = matchType;
        String url = getUrl();
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).scale(url)).subscribe(new Consumer<ScaleBean>() {//onNext
            @Override
            public void accept(ScaleBean Str) throws Exception {
                baseView.onGetData(Str);
                startUpdate();
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

    @NonNull
    private String getUrl() {
        boolean isRunning = false;
        if (type.equals("Running"))
            isRunning = true;

        String url = "http://main55.afb88.com/_view/MoreBet_App.aspx?oId=" + bean.getSocOddsId() + "&home=" + StringUtils.URLEncode(bean.getHome()) + "&away=" + StringUtils.URLEncode(bean.getAway()) + "&moduleTitle=" + StringUtils.URLEncode(bean.getModuleTitle()) + "&date=" + StringUtils.URLEncode(bean.getMatchDate()) + "&isRun=" + isRunning;
        url = url + "&t=" + System.currentTimeMillis();
        return url;
    }


    private Disposable updateSubscription;

    void stopUpdate() {
        if (updateSubscription != null) {
            updateSubscription.dispose();
            Logger.getDefaultLogger().d(getClass().getSimpleName(), "stopUpdate---->");
            updateSubscription = null;
        }
    }

    public void startUpdate() {
        Flowable<ScaleBean> updateFlowable = Flowable.interval(20, 20, TimeUnit.SECONDS).flatMap(new Function<Long, Publisher<ScaleBean>>() {
            @Override
            public Publisher<ScaleBean> apply(Long aLong) throws Exception {
                return getService(ApiService.class).scale(getUrl());
            }
        });
        if (updateSubscription != null) {
            updateSubscription.dispose();
            updateSubscription = null;
        }
        updateSubscription = updateFlowable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<ScaleBean>() {//onNext
                    @Override
                    public void accept(ScaleBean allData) throws Exception {
                        if (allData != null) {
                            baseView.onGetData(allData);
                        }
                    }
                },new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                    }
                });
        Logger.getDefaultLogger().d(getClass().getSimpleName(), "startUpdate---->");
        mCompositeSubscription.add(updateSubscription);
    }
}