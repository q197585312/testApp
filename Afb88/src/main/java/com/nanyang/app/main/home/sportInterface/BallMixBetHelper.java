package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BallMixBetHelper extends BallBetHelper<SportContract2.View> {


    public BallMixBetHelper(SportContract2.View baseView) {
        super(baseView);
    }
//http://a8197c.a36588.com/_Bet/JRecPanel.aspx?g=2&b=home_par&oId=12147539&odds=18
    @Override
    public Disposable clickOdds(final TextView v, String url, final boolean isHf) {
        SoccerMixAdapterHelper.setMixBackground(v,baseView.getContextActivity());
        Disposable subscribe = getService(ApiService.class).updateMixParlayBet(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean bean) throws Exception {
                        baseView.onUpdateMixSucceed(bean);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SoccerMixAdapterHelper.setCommonBackground(v,baseView.getContextActivity());
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
}
