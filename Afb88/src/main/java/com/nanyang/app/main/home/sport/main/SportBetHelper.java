package com.nanyang.app.main.home.sport.main;

import android.support.annotation.NonNull;
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
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.base.IBaseView;

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

import static android.content.ContentValues.TAG;
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


    @Override
    public abstract Disposable clickOdds(B itemData, int oid, String type, String value, TextView v, boolean isHf, String params, boolean hasPar); /*{
        return clickOdds(itemData, type, value, v, isHf, params);
    }*/

 /*   @Override
    public abstract Disposable clickOdds(B item, String type, String odds, TextView v, boolean isHf, String sc);*/

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
                        }
                        if (back != null /*&& allData.startsWith("CHG")*/) {
                            back.callBack(allData);
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
        pop.setIsRunning(false);
        boolean showBet = ((BaseToolbarActivity) getBaseView().getIBaseContext().getBaseActivity()).getApp().isShowBet();
        if (showBet) {
            if (!pop.isShowing()) {
                baseView.onPopupWindowCreated(pop, Gravity.CENTER);
            }
        }
    }

    @NonNull
    protected Disposable getDisposable(final TextView v, final boolean isHf, String betOddsUrl) {
        String url = AppConstant.getInstance().URL_ODDS + betOddsUrl;
        this.v = v;
        baseView.getIBaseContext().showLoadingDialog();
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
                            Log.d("betUrl", "getRefreshResult:" + s);
                            s = AfbUtils.delHTMLTag(s);
                            Log.d("betUrl", "delHTMLTag:" + s);
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
                            if (list != null && list.size() > 0 && list.get(0).getId() != null) {
                                JSONArray dataListArray1 = jsonArray.getJSONArray(1);

                                bean = new AfbClickResponseBean(list, dataListArray1);
                                bean = initHasPar(bean);
                                ((AfbApplication) AfbApplication.getInstance()).setBetAfbList(bean);
                            }
                        }

                        return bean;


                    }
                }).subscribe(new Consumer<AfbClickResponseBean>() {//onNext
                    @Override
                    public void accept(AfbClickResponseBean bean) throws Exception {
                        Log.d(TAG, "accept: " + bean);
                        if (bean == null || bean.getList() == null || bean.getList().size() == 0) {
                        } else if (bean.getList().size() >= 1) {
                            createBetPop(bean.getList(), v == null ? new View(getBaseView().getIBaseContext().getBaseActivity()) : v);
                        }
                        baseView.onUpdateMixSucceed(bean);

                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d(TAG, "throwable:" + throwable.getCause());
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
//                        getBaseView().getIBaseContext().showLoadingDialog();
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
