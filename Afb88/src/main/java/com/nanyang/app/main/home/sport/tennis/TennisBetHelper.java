package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class TennisBetHelper extends BallBetHelper<BallInfo, BetView> {

    public TennisBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getBallG() {
        return "21";
    }


    /*protected String getOddsUrl(BallInfo item, String type, boolean isHf, String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=21");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());

        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }*/

    //    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=home&oId=12264737&odds=9.6
    //http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=even&oId=12264963&oId_fh=12264964&odds=9.4&isFH=true
  /*  @Override
    public Disposable clickOdds(final BallInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {

        String url = getOddsUrl(item, type, isHf, odds, params);
        Disposable subscribe = getService(ApiService.class).getBetData(url).subscribeOn(Schedulers.io())
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
            compositeSubscription.add(subscribe);
        return subscribe;
    }

    private void createBetPop(AfbClickBetBean bean, boolean isHf, TextView v) {
        BetPop pop = new BetPop(baseContext.getIBaseContext(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        baseContext.onPopupWindowCreated(pop, Gravity.CENTER);
    }*/


}
