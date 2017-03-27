package com.nanyang.app.main.home.sport.football;

import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
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

public class SoccerCommonBetHelper extends BallBetHelper<SoccerCommonInfo,BetView> {


    public SoccerCommonBetHelper(BetView baseView) {
        super(baseView);
    }



    private void createBetPop(BettingPromptBean bean, boolean isHf, TextView v) {
        BetPop pop = new BetPop(baseView.getContextActivity(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        pop.showPopupCenterWindow();
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
    protected String getOddsUrl(SoccerCommonInfo item, String type, boolean isHf, String odds,String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.URL_ODDS);
        stringBuilder.append("gt=s");
        stringBuilder.append("&b=" + type);
        stringBuilder.append(params);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        if (isHf)
            stringBuilder.append("&isFH=true&oId_fh=" + item.getSocOddsId_FH());
        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }

    @Override
    public Disposable clickOdds(SoccerCommonInfo item, String type, String odds, final TextView v, final boolean isHf,String params) {
        String url = getOddsUrl(item, type, isHf, odds,params);
        Disposable subscribe = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept( String str) throws Exception {
                        BettingPromptBean bean= new Gson().fromJson(str,BettingPromptBean.class);
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
}
