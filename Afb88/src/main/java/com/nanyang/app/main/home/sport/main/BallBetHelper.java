package com.nanyang.app.main.home.sport.main;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.IBaseView;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BallBetHelper<B extends BallInfo,V extends IBaseView> implements IBetHelper<B> {
    protected V baseView;



    public CompositeDisposable getCompositeSubscription() {
        return compositeSubscription;
    }

    @Override
    public void setCompositeSubscription(CompositeDisposable compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }

    protected CompositeDisposable compositeSubscription;

    public BallBetHelper(V baseView) {
        this.baseView = baseView;
    }

    public IBaseView getBaseView() {
        return baseView;
    }

    public void setBaseView(V baseView) {
        this.baseView = baseView;
    }


    @Override
    public Disposable bet(String url) {

        Disposable subscription = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        ToastUtils.showShort(allData);
                        baseView.onFailed(allData);
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
        if (compositeSubscription != null)
            compositeSubscription.add(subscription);
        return subscription;

    }


}
