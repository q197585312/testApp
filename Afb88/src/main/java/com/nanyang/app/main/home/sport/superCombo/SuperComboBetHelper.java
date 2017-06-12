package com.nanyang.app.main.home.sport.superCombo;

import android.view.Gravity;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.football.SoccerMixBetHelper;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
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

public class SuperComboBetHelper extends SoccerMixBetHelper {

    public SuperComboBetHelper(BetView baseView) {
        super(baseView);
    }


//http://main55.afb88.com/_bet/JRecPanel.aspx?g=16&b=home&oId=12556772&odds=12.6
    @Override
    protected String getOddsUrl(SoccerMixInfo item, String type, boolean isHf, String odds, String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append("g=99");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + item.getSocOddsId());
        stringBuilder.append("&odds=" + odds);
        return stringBuilder.toString();
    }

    @Override
    public Disposable clickOdds(SoccerMixInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {
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
    private void createBetPop(BettingPromptBean bean, boolean isHf, TextView v) {
        BetPop pop = new BetPop(baseView.getContextActivity(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }

}
