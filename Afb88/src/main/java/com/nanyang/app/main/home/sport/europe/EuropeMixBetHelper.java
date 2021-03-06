package com.nanyang.app.main.home.sport.europe;

import android.view.Gravity;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.nanyang.app.main.home.sportInterface.BetView;

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

public class EuropeMixBetHelper extends BallBetHelper<EuropeMixInfo, BetView> {

    public EuropeMixBetHelper(BetView baseView) {
        super(baseView);
    }


    //http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=10&b=odd&oId=12264769&odds=9.4
//    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=10&b=away&oId=12264970&odds=9
    //http://a8206d.a36588.com/_bet/JRecPanel.aspx?g=10&b=home_par&oId=12510519&odds=8.3
    @Override
    public Disposable clickOdds(EuropeMixInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {

        String url = getOddsUrl(item, type, isHf, odds, params);
       /* Disposable subscribe = getService(ApiService.class).getBetData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingPromptBean>() {//onNext
                    @Override
                    public void accept(BettingPromptBean bean) throws Exception {
                        createBetPop(bean, isHf, v);
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
            compositeSubscription.add(subscribe);*/
        new BaseMixStyleHandler((BaseToolbarActivity) baseView.getContextActivity()).setMixBackground(v);
        Disposable subscribe = getService(ApiService.class).updateMixParlayBet(getOddsUrl(item, type, isHf, odds, params)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean bean) throws Exception {
                        baseView.onUpdateMixSucceed(bean);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        new BaseMixStyleHandler((BaseToolbarActivity) baseView.getContextActivity()).setCommonBackground(v);
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


    private void createBetPop(BettingPromptBean bean, boolean isHf, TextView v) {
        BetPop pop = new BetPop(baseView.getContextActivity(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }


    //http://main55.afb88.net/_bet/JRecPanel.aspx?g=2&b=X_par&oId=13248116&odds=3.4
    protected String getOddsUrl(EuropeMixInfo item, String type, boolean isHf, String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=2");
        stringBuilder.append("&b=" + type + "_par");
        stringBuilder.append("&oId=");
        if (isHf) {
            stringBuilder.append(item.getSocOddsId_FH());
        } else {
            stringBuilder.append(item.getSocOddsId());
        }
        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }


}
