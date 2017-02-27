package com.nanyang.app.main.home.sport;


import android.os.Bundle;
import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.unkonw.testapp.libs.api.Api.getService;

public abstract class SportPresenter<T, V extends SportContract.View<T>> extends BaseRetrofitPresenter<T, V> implements SportContract.Presenter {
    public SportPresenter(V view) {
        super(view);
    }
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）


    @Override
    public void refresh(String type) {

    }

    public void addMixParlayBet(BettingInfoBean info, final Map<String, Map<Integer, BettingInfoBean>> keyMap, final MatchBean item) {
        StringBuilder builder = getBetUrl(info);

        //http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=home_par&oId=12036347&odds=17.6
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).addMixParlayBet(builder.toString()))

                .subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean allData) throws Exception {
                        baseView.onUpdateMixSucceed(allData, keyMap, item);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onAddMixFailed(throwable.getMessage());
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

    public void getBetPopupData(BettingInfoBean info) {
        StringBuilder betUrl = getBetUrl(info);
        Flowable<BettingPromptBean> flowable = getService(ApiService.class).getBetData(betUrl.toString());
        Disposable subscription = mApiWrapper.applySchedulers(flowable)
                .subscribe(new Consumer<BettingPromptBean>() {//onNext
                    @Override
                    public void accept(BettingPromptBean allData) throws Exception {
                        baseView.onGetBetSucceed(allData);
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

    @Override
    public void bet(String s) {
        Flowable<String> flowable = getService(ApiService.class).getData(s);
        Disposable subscription = mApiWrapper.applySchedulers(flowable)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        baseView.onBetSucceed(allData);
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

    public BettingParPromptBean removeBetItem(final BettingInfoBean item) {

        String ParUrl = "";

        for (BettingParPromptBean.BetParBean aitem : ((BaseToolbarActivity) baseView).getApp().getBetParList().getBetPar()) {
            if (item.getHome().equals(aitem.getHome()) && item.getAway().equals(aitem.getAway())) {
                ParUrl = aitem.getParUrl();
                break;
            }
        }
        String url;
        if (!ParUrl.equals("")) {
            if (item.getIsFH() == 0)
                url = ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId();
            else {
                url = ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId_FH();
            }
            try {
                return getService(ApiService.class).removeMixOrder(url).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }
    protected String type;
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected boolean isMixParlay = false;
    public boolean isMixParlay() {
        return isMixParlay;
    }

    public void setMixParlay(boolean mixParlay) {
        isMixParlay = mixParlay;
    }

    public void onRightMarkClick(Bundle b) {
    }

    public void countBet() {
        baseView.onCountBet();
    }

    public void createPopupWindow(BetBasePop betPop) {
        baseView.onCreatePopupWindow(betPop);
    }
}