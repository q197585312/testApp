package com.nanyang.app.main.home.sport.main;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;
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

public abstract class SportBetHelper<B extends SportInfo, V extends BetView> implements IBetHelper<B> {
    protected V baseView;
    private ResultCallBack back;

    private TextView v;


    @Override
    public void setCompositeSubscription(CompositeDisposable compositeSubscription) {
        this.compositeSubscription = compositeSubscription;
    }

    protected CompositeDisposable compositeSubscription;

    public SportBetHelper(V baseView) {
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
        this.back = back;
    }

    //CHG|Odds has changed to 1.69!|1.69|1
    protected void handleOddsUpdate(String allData) {
        String substring = allData.substring(allData.indexOf("!|") + 2);
        String odds = substring.substring(0, substring.indexOf("|"));
        back.callBack(odds);
    }

    protected void updateFirstStake() {

//        Disposable d = Api.getService(ApiService.class).getData(
//                AppConstant.getInstance().URL_STAKE).subscribeOn(Schedulers.io()).observeOn(Schedulers.computation())
//                .map(new Function<String, StakeListBean.DicAllBean>() {
//
//                    @Override
//                    public StakeListBean.DicAllBean apply(String data) throws Exception {
//                        Gson gson = new Gson();
//                        data = Html.fromHtml(data).toString();
//                        String[] data1 = data.split("nyhxkj");
//                        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
//                        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
//                        if (list1 != null)
//                            return list1.get(0);
//                        return new StakeListBean.DicAllBean();
//                    }
//                }).observeOn(AndroidSchedulers.mainThread())
//
//                .subscribe(new Consumer<StakeListBean.DicAllBean>() {
//                    @Override
//                    public void accept(StakeListBean.DicAllBean dicAllBean) throws Exception {
//                        handleDicAllBean(dicAllBean);
//                        baseView.getIBaseContext().hideLoadingDialog();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        baseView.getIBaseContext().hideLoadingDialog();
//                        baseView.onFailed(throwable.getMessage());
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        baseView.getIBaseContext().hideLoadingDialog();
//                    }
//                }, new Consumer<Subscription>() {
//                    @Override
//                    public void accept(Subscription subscription) throws Exception {
//                        baseView.getIBaseContext().showLoadingDialog();
//                        subscription.request(Integer.MAX_VALUE);
//                    }
//                });
//        if (compositeSubscription != null)
//            compositeSubscription.add(d);
    }

//    private void handleDicAllBean(StakeListBean.DicAllBean item) {
//
//        StringBuilder builder = new StringBuilder();
//        builder.append(item.getRefNo() + "(" + item.getTransDate() + ")");
//        builder.append("\n");
//        builder.append(item.getHome() + "  vs  " + item.getAway());
//        builder.append("\n");
//        String typeName = "";
//        if (item.getTransType().startsWith("MM")) {
//            String type = item.getTransType();
//            if (type.equals("MMO")) {
//                if (item.isIsBetHome()) {
//                    typeName = baseView.getIBaseContext().getBaseActivity().getString(R.string.over);
//                } else {
//                    typeName = baseView.getIBaseContext().getBaseActivity().getString(R.string.under);
//                }
//            } else {
//                if (item.isIsBetHome()) {
//                    typeName = item.getHome();
//                } else {
//                    typeName = item.getAway();
//                }
//            }
//            builder.append(typeName + "(" + item.getHdp() + "(" + Integer.parseInt(item.getMMPct()) / 100 + ")" + "@" + item.getRunHomeScore() + " - " + item.getRunAwayScore() + ")");
//        } else {
//            if (item.isIsRun() == 1) {
//                builder.append("(" + item.getRunHomeScore() + " - " + item.getRunAwayScore() + ")");
//            }
//        }
//        String odds = item.getDisplayOdds2();
//        if (item.getTransType().equals("HDP") || item.getTransType().equals("OU") || item.getTransType().equals("OE"))
//            odds = item.getOdds();
//        builder.append(odds);
//        builder.append("\n");
//        builder.append(item.getModuleTitle());
//        if (item.getFullTimeId() > 0) {
//            builder.append("\n");
//            builder.append("(First Half)");
//            builder.append(item.getBetType());
//        }
//
//        String n = "Accepted";
//        if (item.getDangerStatus().equals("D")) {
//            n = "Waiting";
//        } else if (item.getDangerStatus().equals("R")) {
//            n = "Rejected " + item.getR_DateTime();
//        } else if (item.getDangerStatus().equals("RR")) {
//            n = "Rejected (Red Card " + item.getR_DateTime() + ")";
//        } else if (item.getDangerStatus().equals("RP")) {
//            n = "Rejected (Goal Disallowed " + item.getR_DateTime() + ")";
//        } else if (item.getDangerStatus().equals("RA")) {
//            n = "Rejected (Abnormal Bet " + item.getR_DateTime() + ")";
//        } else if (item.getDangerStatus().equals("RG")) {
//            n = "Rejected (Goal " + item.getR_DateTime() + ")";
//        } else if (item.getDangerStatus().equals("0")) {
//            n = "Oddschange";
//        }
//        builder.append("\n");
//        builder.append(n + "     " + item.getAmt());
//        String str = builder.toString();
//        int start = str.indexOf(n);
//        int end = start + n.length();
//        SpannableStringBuilder style = new SpannableStringBuilder(str);
//        if (item.getTransType().startsWith("MM")) {
//            int star1 = str.indexOf("(" + Integer.parseInt(item.getMMPct()) / 100 + ")@");
//            int end1 = str.indexOf(")@") + 1;
//            if (item.getMMPct().startsWith("-")) {
//                style.setSpan(new ForegroundColorSpan(Color.RED), star1, end1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            } else {
//                style.setSpan(new ForegroundColorSpan(Color.BLUE), star1, end1, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
//            }
//        }
//        if (item.getDangerStatus().equals("D")) {
//            style.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("R")) {
//            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("RR")) {
//            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("RP")) {
//            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("RA")) {
//            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("RG")) {
//            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else if (item.getDangerStatus().equals("0")) {
//            style.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        } else {
//            style.setSpan(new BackgroundColorSpan(Color.GREEN), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        }
//        ToastUtils.showShort(style);
//    }

    @Override
    public Disposable clickOdds(B itemData, int oid, String type, String value, TextView v, boolean isHf, String params, boolean hasPar) {
        return clickOdds(itemData, type, value, v, isHf, params);
    }

    @Override
    public abstract Disposable clickOdds(B item, String type, String odds, TextView v, boolean isHf, String sc);

    protected OddsClickBean getOddsUrl(B item, String type, boolean isHf, String odds, String sc) {
        return null;
    }


    @Override
    public Disposable bet(String url) {

        url = url + "&_=" + System.currentTimeMillis();
        Log.d("betUrl", url);
        Disposable subscription = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        allData = AfbUtils.delHTMLTag(allData);
                        Log.d("betUrl", "betResult:" + allData);
                        String[] split = allData.split("\\|");
                        if (split.length >= 5) {
                            baseView.onBetSuccess(allData);
                            updateFirstStake();
                        } else {
                            baseView.onFailed(allData);
                            if (back != null && allData.startsWith("CHG")) {
                                handleOddsUpdate(allData);
                            }
                        }
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.getIBaseContext().showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        if (compositeSubscription != null)
            compositeSubscription.add(subscription);
        return subscription;
    }

    BetPop pop;

    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        if (pop == null) {
            pop = new BetPop(baseView.getIBaseContext().getBaseActivity(), v);
        }
        pop.setBetData(bean, this);
        if (!pop.isShowing()) {
            baseView.onPopupWindowCreated(pop, Gravity.CENTER);
        }

    }

    @NonNull
    protected Disposable getDisposable(final TextView v, final boolean isHf, String betOddsUrl) {
        String url = AppConstant.getInstance().URL_ODDS + betOddsUrl;
        this.v = v;
        Disposable subscribe = getRefreshOdds(url);
        return subscribe;
    }

    @NonNull
    @Override
    public Disposable getRefreshOdds(String url) {
        url = url + "&_=" + System.currentTimeMillis();
        Log.d("betUrl", "getRefreshOdds:" + url);
        Disposable subscribe = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<String, AfbClickResponseBean>() {


                    @Override
                    public AfbClickResponseBean apply(String s) throws Exception {
                        AfbClickResponseBean bean = null;
                        JSONArray jsonArray = null;
                        try {
                            s = AfbUtils.delHTMLTag(s);
                            Log.d("betUrl", "getRefreshResult:" + s);
                            jsonArray = new JSONArray(s);
                        } catch (JSONException e) {
                            getBaseView().onFailed(s);
                            getBaseView().getIBaseContext().hideLoadingDialog();
                            return null;
                        }
                        if (jsonArray.length() > 1) {
                            JSONArray dataListArray = jsonArray.getJSONArray(0);
                            List<AfbClickBetBean> list = new Gson().fromJson(dataListArray.toString(), new TypeToken<List<AfbClickBetBean>>() {
                            }.getType());
//[10000, 3, 4.654056, 10000, 'hBetSub.ashx?BTMD=P&odds=4.654056', 1, 0]
                            JSONArray dataListArray1 = jsonArray.getJSONArray(1);

                            bean = new AfbClickResponseBean(list, dataListArray1);
                            bean = initHasPar(bean);
                            ((AfbApplication) AfbApplication.getInstance()).setBetAfbList(bean);
                        }

                        return bean;


                    }
                }).subscribe(new Consumer<AfbClickResponseBean>() {//onNext
                    @Override
                    public void accept(AfbClickResponseBean bean) throws Exception {
                        if (bean == null || bean.getList() == null || bean.getList().size() == 0) {
                        } else if (bean.getList().size() >= 1) {
                            createBetPop(bean.getList(), v == null ? new View(getBaseView().getIBaseContext().getBaseActivity()) : v);
                        }
                        baseView.onUpdateMixSucceed(bean);

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getBaseView().onFailed(throwable.getMessage());
                        getBaseView().getIBaseContext().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        getBaseView().getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        getBaseView().getIBaseContext().showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        if (compositeSubscription != null)
            compositeSubscription.add(subscribe);
        return subscribe;
    }

    protected AfbClickResponseBean initHasPar(AfbClickResponseBean bean) {
        return bean;
    }
}
