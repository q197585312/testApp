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
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.Iterator;
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
                            if (betPop != null)
                                betPop.clearSingleHashMap();
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

    BetPop betPop;

    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        BaseActivity baseActivity = baseView.getIBaseContext().getBaseActivity();
        if (betPop == null) {
            betPop = new BetPop(baseActivity, v);
        }
        betPop.setBetData(bean, this);
        if (!betPop.isShowing()) {
//            betPop.showPopupCenterWindow();
            baseView.onPopupWindowCreated(betPop, Gravity.CENTER);
        }
    }


    @NonNull
    protected Disposable getDisposable(final TextView v, final boolean isHf, String betOddsUrl) {

        this.v = v;
        baseView.getIBaseContext().showLoadingDialog();
        Disposable subscribe = getRefreshOdds(betOddsUrl);
        return subscribe;
    }

    @NonNull
    @Override
    public Disposable getRefreshOdds(final String urlBet) {
        final String url = urlBet + "&_=" + System.currentTimeMillis();
        Log.d("updateMixListText", "betUrl:" + url);

        Disposable subscribe = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new Function<String, AfbClickResponseBean>() {
                    @Override
                    public AfbClickResponseBean apply(String s) throws Exception {
                        AfbClickResponseBean bean = null;
                        JSONArray jsonArray = null;
                        try {
                            Log.d("updateMixListText", "getRefreshResult:" + s);
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

                            if (list != null && list.size() > 0 && list.get(0).getId() != null) {
                                JSONArray dataListArray1 = jsonArray.getJSONArray(1);
                                bean = new AfbClickResponseBean(list, dataListArray1);
                                LogUtil.d("updateMixListText", "setBetAfbList:getRefreshOdds:" + bean);
                                ((AfbApplication) AfbApplication.getInstance()).setBetAfbList(bean);
                            }
                        }
                        return bean;


                    }
                }).subscribe(new Consumer<AfbClickResponseBean>() {//onNext
                    @Override
                    public void accept(AfbClickResponseBean bean) throws Exception {
                        if (bean.getList() != null)
                            Log.d("updateMixListText", "accept: " + bean.getList().size());
                        else
                            Log.d("updateMixListText", "accept:getList: 为空");
                        if (bean == null || bean.getList() == null || bean.getList().size() == 0) {

                        } else if (bean.getList().size() >= 1) {
                            createBetPop(bean.getList(), v == null ? new View(getBaseView().getIBaseContext().getBaseActivity()) : v);
                        }
                        updateMixList(url);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("updateMixListText", "throwable:" + throwable.getCause());
                        getBaseView().onFailed(throwable.getMessage());
                        getBaseView().getIBaseContext().hideLoadingDialog();
                        LogUtil.d("BetPop", "setBetAfbList:getRefreshOdds错误:" + null);
                        ((AfbApplication) AfbApplication.getInstance()).setBetAfbList(null);
                        updateMixList(url);
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

    public void updateMixList(String url) {
        LogUtil.d("updateMixListText", "url:" + url);
        if (!StringUtils.isNull(url) && url.contains("_par")) {
            AfbClickResponseBean betAfbList = ((AfbApplication) AfbApplication.getInstance()).getBetAfbList();
            List<OddsClickBean> mixBetList = ((AfbApplication) AfbApplication.getInstance()).getMixBetList();
            if (betAfbList == null || betAfbList.getList() == null || betAfbList.getList().size() < 1) {
                ((AfbApplication) AfbApplication.getInstance()).clearMixBetList();
                updateMixListText();
            } else if (betAfbList.getList().size() < mixBetList.size()) {
                LogUtil.d("updateMixListText", betAfbList.getList().size() + "---" + mixBetList.size());
                Iterator<OddsClickBean> iterator = mixBetList.iterator();
                boolean deleted = false;
                while (iterator.hasNext()) {
                    OddsClickBean next = iterator.next();
                    boolean hasFound = findInBetList(next, betAfbList.getList());
                    LogUtil.d("updateMixListText", "hasFound:" + hasFound + ",item:" + next.getItem().getModuleTitle() + "---" + next.getItem().getHome() + "-----" + next.getItem().getAway());
                    if (!hasFound)
                        iterator.remove();
                    deleted = deleted || !hasFound;
                }
                if (deleted)
                    updateMixListText();
            }
        }
    }

    public void updateMixListText() {
        if ((getBaseView().getIBaseContext().getBaseActivity()) != null) {
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).updateMixOrder();
        }
    }

    private boolean findInBetList(OddsClickBean next, List<AfbClickBetBean> betAfbList) {

        for (int i = 0; i < betAfbList.size(); i++) {
            String socOddsId = betAfbList.get(i).getSocOddsId();
            String parId = betAfbList.get(i).getId();
            OddsClickBean oddsClickBean = next;
            if (oddsClickBean.getBETID().equals(parId)
                    || oddsClickBean.getBETID_PAR().equals(parId)
                    || socOddsId.equals(oddsClickBean.getOid())
                    || socOddsId.equals(oddsClickBean.getOid_fh())) {
                if (betAfbList.get(i).getIsRun() == 1) {
                    boolean isSame = isScoreSame(next.getItem(), betAfbList.get(i));
                    return isSame;
                }
                return true;
            }
        }
        return false;
    }

    private boolean isScoreSame(BallInfo item, AfbClickBetBean afbClickBetBean) {
        if (!afbClickBetBean.getScore().contains("-"))
            return false;
        if (afbClickBetBean.getBeturl().contains("isFH=True")) {
            String runAwayScore_fh = item.getRunAwayScore_FH();
            String runHomeScore_fh = item.getRunHomeScore_FH();
            return checkSameScore(afbClickBetBean, runAwayScore_fh, runHomeScore_fh);
        } else {
            String runAwayScore_fh = item.getRunAwayScore();
            String runHomeScore_fh = item.getRunHomeScore();
            return checkSameScore(afbClickBetBean, runAwayScore_fh, runHomeScore_fh);
        }
    }

    private boolean checkSameScore(AfbClickBetBean afbClickBetBean, String runAwayScore_fh, String runHomeScore_fh) {
        String[] split = afbClickBetBean.getScore().split("-");
        if (split.length < 2)
            return true;
        if (split[0].trim().equals(runHomeScore_fh) && split[1].trim().equals(runAwayScore_fh)
                || split[0].trim().equals(runAwayScore_fh) && split[1].trim().equals(runHomeScore_fh)) {
            return true;
        }
        return false;
    }


}
