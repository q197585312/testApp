package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class SoccerCommonBetHelper extends BallBetHelper<SoccerCommonInfo, BetView> {


    public SoccerCommonBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "1";
    }

/*
    protected void createBetPop(BettingPromptBean bean, boolean isHf, TextView v,SoccerCommonInfo item) {
        BetPop pop = new BetPop(baseContext.getIBaseContext(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        baseContext.onPopupWindowCreated(pop, Gravity.CENTER);
    }

    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=dc&sc=10&oId=12286344&odds=1.29
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&g=5&b=2&oId=12286343&odds=2.94
//    http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&g=5&b=odd&oId=12286343&odds=9.8
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=csr&sc=2&oId=12286343&odds=18.4
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=csr&sc=33&oId=12286343&odds=27.3
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=csr&sc=80&oId=12286344&odds=34
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=htft&sc=10&oId=12286343&odds=15.5
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=htft&sc=22&oId=12286343&odds=4.7
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=fglg&sc=0&oId=12286343&odds=13
//    /http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=tg&sc=1&oId=12286343&odds=4.6
    //http://main55.afb88.com/_bet/JRecPanel.aspx?gt=s&b=tg&sc=70&oId=12286343&odds=11
    protected String getOddsUrl(SoccerCommonInfo item, String type, boolean isHf, String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("gt=s");
        stringBuilder.append("&b=" + type);
        stringBuilder.append(params);
        String oId = "&oId=" + item.getSocOddsId();
        if (isHf)
            if (type.equalsIgnoreCase("over") || type.equalsIgnoreCase("under") || type.equalsIgnoreCase("home") || type.equalsIgnoreCase("away")) {
                oId += "&isFH=true&oId_fh=" + item.getSocOddsId_FH();
            } else {
                oId = "&oId=" + item.getSocOddsId_FH();
            }
        stringBuilder.append(oId);
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }
    protected String getOddsUrl(String old, String type,String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("gt=s");
        stringBuilder.append("&b=" + type);
        stringBuilder.append(params);
        stringBuilder.append( "&oId=" + old);
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }

    @Override
    public Disposable clickOdds(SoccerCommonInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {
        String url = getOddsUrl(item,  type, isHf, odds, params);
        return getDisposable(v, isHf, url,item);
    }

    @NonNull
    private Disposable getDisposable(final TextView v, final boolean isHf, String url, final SoccerCommonInfo item) {
        Disposable subscribe = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        try {
                            BettingPromptBean bean = new Gson().fromJson(str, BettingPromptBean.class);
                            createBetPop(bean, isHf, v,item);
                        } catch (JsonSyntaxException e) {
                            throw new Exception(str, e);
                        }

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

    @Override
    public Disposable clickOdds(SoccerCommonInfo itemData, int oid, String type, String odds, TextView v, boolean isHf, String params) {
        String url = getOddsUrl(oid + "", type,  odds, params);
        return getDisposable(v, isHf, url,itemData);
    }*/
}
