package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BasketballMixBetHelper extends BallBetHelper<BasketballMixInfo, BetView> {


    public BasketballMixBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "9";
    }

    /*//http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=10&b=odd&oId=12264769&odds=9.4
//    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=10&b=away&oId=12264970&odds=9
    //http://a8206d.a36588.com/_bet/JRecPanel.aspx?g=10&b=home_par&oId=12510519&odds=8.3
    @Override
    public Disposable clickOdds(BasketballMixInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {

        String url = getOddsUrl(item, type, isHf, odds, params);
       *//* Disposable subscribe = getService(ApiService.class).getBetData(url).subscribeOn(Schedulers.io())
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
            compositeSubscription.add(subscribe);*//*
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

    //http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=10&b=odd&oId=12264769&odds=9.4
//    http://a8206d.a36588.com/_bet/JRecPanel.aspx?g=10&b=home_par&oId=12510519&odds=8.3
    //http://a8206d.a36588.com/_bet/JRecPanel.aspx?g=10&b=odd_par&oId=12510520&odds=9.4
    protected String getOddsUrl(BasketballMixInfo item, String type, boolean isHf, String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=10");
        stringBuilder.append("&b=" + type + "_par");
        stringBuilder.append("&oId=");
        stringBuilder.append(item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }*/
}
