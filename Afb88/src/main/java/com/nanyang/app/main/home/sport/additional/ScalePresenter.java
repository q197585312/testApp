package com.nanyang.app.main.home.sport.additional;

import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/5 0005.
 */

class ScalePresenter extends BaseRetrofitPresenter<BettingParPromptBean, ScaleContract.View> implements ScaleContract.Presenter {

    public ScalePresenter(ScaleContract.View view) {
        super(view);
    }

    @Override
    public void bet(String s) {

    }
    @NonNull
    private StringBuilder getBetUrl(BettingInfoBean info) {
        //%@/_Bet/JRecPanel.aspx?g=2&b=%@&oId=%@&odds=%f
        StringBuilder builder = new StringBuilder();
        //"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?"
        builder.append(AppConstant.HOST + "_bet/JRecPanel.aspx?");
        if (info.getGt() != null && !info.getGt().equals(""))
            builder.append("gt=" + info.getGt());
        if (info.getB().equals("1") || info.getB().equals("X") || info.getB().equals("2") || info.getB().equals("odd") || info.getB().equals("even"))
            builder.append("&g=5");
        else if (info.getB().equals("X_par") || info.getB().equals("2_par") || info.getB().equals("1_par") || info.getB().equals("under_par") || info.getB().equals("over_par") || info.getB().equals("home_par") ||
                info.getB().equals("away_par") || info.getB().equals("odd_par") || info.getB().equals("even_par"))
            builder.append("&g=2");
        builder.append("&b=" + info.getB());
        if (info.getSc() != null && !info.getSc().equals(""))
            builder.append("&sc=" + info.getSc());
        builder.append("&oId=" + info.getSocOddsId());
        builder.append("&odds=" + info.getOdds());
        if (info.isRunning())
            builder.append("&isRun=true");
        if (info.getIsFH() == 1 && info.getSocOddsId_FH() != null && !info.getSocOddsId_FH().equals(""))
            builder.append("&isFH=true&oId_fh=" + info.getSocOddsId_FH());
        return builder;
    }
    @Override
    public void addMixParlayBet(BettingInfoBean info, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {

        StringBuilder builder = getBetUrl(info);

        //http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=home_par&oId=12036347&odds=17.6
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).updateMixParlayBet(builder.toString()))

                .subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean allData) throws Exception {
                        baseView.onGetData(allData);
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
}
