package com.nanyang.app.main.home.sport.main;

import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.center.model.StakeListBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.IBaseView;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/15.
 */

public abstract class BallBetHelper<B extends BallInfo, V extends BetView> implements IBetHelper<B> {
    protected V baseView;
    private ResultCallBack back;


    public CompositeDisposable getCompositeSubscription() {
        return compositeSubscription;
    }

    @Override
    public void setCompositeSubscription(CompositeDisposable compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }

    protected CompositeDisposable compositeSubscription;

    public BallBetHelper(V baseView) {
        this.baseView = baseView;
    }

    public IBaseView getBaseView() {
        return baseView;
    }

    public void setBaseView(V baseView) {
        this.baseView = baseView;
    }

    @Override
    public void setResultCallBack(ResultCallBack back) {
        this.back=back;
    }
    @Override
    public Disposable bet(String url) {

        Disposable subscription = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        String[] split = allData.split("\\|");
                        if (split.length == 5) {
                            baseView.onBetSuccess(allData);
                            updateFirstStake();
                        }
                        else {
                            baseView.onFailed(allData);
                            if(back!=null&&allData.startsWith("CHG")){
                                handleOddsUpdate(allData);
                            }
                        }
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
        if (compositeSubscription != null)
            compositeSubscription.add(subscription);
        return subscription;

    }
//CHG|Odds has changed to 1.69!|1.69|1
    private void handleOddsUpdate(String allData) {
        String substring = allData.substring(allData.indexOf("!|")+2);
        String odds = substring.substring(0, substring.indexOf("|"));
        back.callBack(odds);


    }

    private void updateFirstStake() {
        Disposable d = Api.getService(ApiService.class).getStakeData(AppConstant.URL_STAKE).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
                .map(new Function<String, StakeListBean.DicAllBean>() {

                    @Override
                    public StakeListBean.DicAllBean apply(String data) throws Exception {
                        Gson gson = new Gson();
                        data= Html.fromHtml(data).toString();
                        String[] data1 = data.split("nyhxkj");
                        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
                        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
                        if (list1 != null)
                            return list1.get(0);
                        return new StakeListBean.DicAllBean();
                    }
                }).observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<StakeListBean.DicAllBean>() {
                    @Override
                    public void accept(StakeListBean.DicAllBean dicAllBean) throws Exception {
                        handleDicAllBean(dicAllBean);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        if (compositeSubscription != null)
            compositeSubscription.add(d);
    }

    private void handleDicAllBean(StakeListBean.DicAllBean item) {

        StringBuilder builder = new StringBuilder();
        builder.append(item.getRefNo() + "(" + item.getTransDate() + ")");
        builder.append("\n");
        builder.append(item.getHome() + "  vs  " + item.getAway());
        builder.append("\n");
        if(item.isIsRun()){
            builder.append("("+item.getRunHomeScore()+" - "+item.getRunAwayScore()+")");
        }

        builder.append(item.getOdds());
        builder.append("\n");
        builder.append(item.getModuleTitle());
        if (item.getFullTimeId() > 0) {
            builder.append("\n");
            builder.append("(First Half)");
            builder.append(item.getBetType());
        }

        String n = "Accepted";
        if (item.getDangerStatus().equals("D")) {
            n = "Waiting";
        } else if (item.getDangerStatus().equals("R")) {
            n = "Rejected " + item.getR_DateTime();
        } else if (item.getDangerStatus().equals("RR")) {
            n = "Rejected (Red Card " + item.getR_DateTime() + ")";
        } else if (item.getDangerStatus().equals("RP")) {
            n = "Rejected (Goal Disallowed " + item.getR_DateTime() + ")";
        } else if (item.getDangerStatus().equals("RA")) {
            n = "Rejected (Abnormal Bet " + item.getR_DateTime() + ")";
        } else if (item.getDangerStatus().equals("RG")) {
            n = "Rejected (Goal " + item.getR_DateTime() + ")";
        } else if (item.getDangerStatus().equals("0")) {
            n = "Oddschange";
        }
        builder.append("\n");
        builder.append(n + "     " + item.getAmt());
        String str=builder.toString();
        int start=str.indexOf(n);
        int end=start+n.length();
        SpannableStringBuilder style=new SpannableStringBuilder(str);
        if (item.getDangerStatus().equals("D")) {
            style.setSpan(new BackgroundColorSpan(Color.YELLOW),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("R")) {
            style.setSpan(new BackgroundColorSpan(Color.RED),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RR")) {
            style.setSpan(new BackgroundColorSpan(Color.RED),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RP")) {
            style.setSpan(new BackgroundColorSpan(Color.RED),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RA")) {
            style.setSpan(new BackgroundColorSpan(Color.RED),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RG")) {
            style.setSpan(new BackgroundColorSpan(Color.RED),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("0")) {
            style.setSpan(new BackgroundColorSpan(Color.YELLOW),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        else{
            style.setSpan(new BackgroundColorSpan(Color.GREEN),start,end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        ToastUtils.showShort(style);
    }


}
