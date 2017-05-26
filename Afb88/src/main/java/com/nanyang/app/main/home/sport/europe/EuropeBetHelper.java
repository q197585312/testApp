package com.nanyang.app.main.home.sport.europe;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
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

public class EuropeBetHelper extends BallBetHelper<EuropeInfo, BetView> {

    public EuropeBetHelper(BetView baseView) {
        super(baseView);
    }

    //http://main55.afb88.com/_bet/JRecPanel.aspx?g=5&b=1&oId=12794616&odds=11.8
    //http://main55.afb88.com/_Bet/JRecPanel.aspx?g=5&b=1&oId=12683157&odds=2.9
    protected String getOddsUrl(String SocOddsId, String type, boolean isHf, String odds, @NonNull String params) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AppConstant.getInstance().URL_ODDS);
        stringBuilder.append(params);
        stringBuilder.append("&gt=s");
        stringBuilder.append("&b=" + type);
        stringBuilder.append("&oId=" + SocOddsId);
        stringBuilder.append("&odds=" + odds);

        return stringBuilder.toString();
    }

    //    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=home&oId=12264737&odds=9.6
    //http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=even&oId=12264963&oId_fh=12264964&odds=9.4&isFH=true
    @Override
    public Disposable clickOdds(EuropeInfo item, String type, String odds, final TextView v, final boolean isHf, String params) {
        String socOddsId = item.getSocOddsId();
        if (isHf) {
            socOddsId = item.getSocOddsId_FH();
        }
        String url = getOddsUrl(socOddsId, type, isHf, odds, params);
        return getDisposable(v, isHf, url);
    }

    @NonNull
    private Disposable getDisposable(final TextView v, final boolean isHf, String url) {
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

    @Override
    public Disposable clickOdds(EuropeInfo itemData, int oid, String type, String odds, TextView v, boolean isHf, String params) {
        String url = getOddsUrl(oid + "", type, false, odds, params);
        return getDisposable(v, isHf, url);
    }


}
