package com.nanyang.app.main.home.sportInterface;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
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

public class BallCommonBetHelper implements IBetHelper {
    IBaseView baseView;
    private CompositeDisposable compositeSubscription;

    public BallCommonBetHelper(IBaseView baseView) {
        this.baseView = baseView;
    }

    public IBaseView getBaseView() {
        return baseView;
    }

    public void setBaseView(IBaseView baseView) {
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

    @Override
    public Disposable clickOdds(TextView v, String url, final boolean isHf) {

        Disposable subscribe = getService(ApiService.class).getBetData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingPromptBean>() {//onNext
                    @Override
                    public void accept(BettingPromptBean bean) throws Exception {
                        createBetPop(bean, isHf);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getBaseView().onFailed(throwable.getMessage());
                        getBaseView().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        getBaseView().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        getBaseView().showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        if (compositeSubscription != null)
            compositeSubscription.add(subscribe);
        return subscribe;
    }

    private void createBetPop(BettingPromptBean bean, boolean isHf) {
        BetPop pop = new BetPop((Context) baseView, new View((Context) baseView));
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        pop.showPopupDownWindow();
    }

    public void setCompositeSubscription(CompositeDisposable compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }
}
