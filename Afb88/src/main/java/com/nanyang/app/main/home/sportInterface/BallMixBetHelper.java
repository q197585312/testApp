package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
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

public class BallMixBetHelper implements IBetHelper {
    SportContract2.View baseView;
    private CompositeDisposable compositeSubscription;

    public BallMixBetHelper(SportContract2.View baseView) {
        this.baseView = baseView;
    }

    public IBaseView getBaseView() {
        return baseView;
    }

    public void setBaseView(SportContract2.View baseView) {
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
    public Disposable clickOdds(final TextView v, String url, final boolean isHf) {

        v.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_bg);
        v.setTextColor(baseView.getContextActivity().getResources().getColor(R.color.white));
        Disposable subscribe = getService(ApiService.class).addMixParlayBet(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean bean) throws Exception {
                        baseView.onUpdateMixSucceed(bean);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        v.setBackgroundResource(0);
                        v.setTextColor(baseView.getContextActivity().getResources().getColor(R.color.black_grey));
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

    public void setCompositeSubscription(CompositeDisposable compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }
}
