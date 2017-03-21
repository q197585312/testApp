package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;

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

public class SoccerMixBetHelper extends BallBetHelper<SoccerMixInfo, BetView> {


    public SoccerMixBetHelper(BetView baseView) {
        super(baseView);
    }
//http://a8197c.a36588.com/_Bet/JRecPanel.aspx?g=2&b=home_par&oId=12147539&odds=18

    @Override
    public Disposable clickOdds(SoccerMixInfo item, String type, String odds, final TextView v, boolean isHf) {
        SoccerMixAdapterHelper.setMixBackground(v, baseView.getContextActivity());
        Disposable subscribe = getService(ApiService.class).updateMixParlayBet(getOddsUrl(item, type, isHf, odds)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean bean) throws Exception {
                        baseView.onUpdateMixSucceed(bean);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        SoccerMixAdapterHelper.setCommonBackground(v, baseView.getContextActivity());
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
//    http://a8197c.a36588.com/_Bet/JRecPanel.aspx?g=2&b=1_par&oId=12265358&odds=1.66
    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?g=2&b=home_par&oId=12152396&odds=19.9
    protected String getOddsUrl(SoccerMixInfo item, String type, boolean isHf, String odds) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.URL_ODDS);
        stringBuilder.append("g=2");
        if (!type.endsWith("_par"))
            stringBuilder.append("&b=" + type + "_par");
        else{
            stringBuilder.append("&b=" + type);
        }
        stringBuilder.append("&oId=");
        if (isHf)
            stringBuilder.append(item.getSocOddsId_FH());
        else
            stringBuilder.append(item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }
}
