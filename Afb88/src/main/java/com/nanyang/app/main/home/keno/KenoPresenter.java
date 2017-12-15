package com.nanyang.app.main.home.keno;

import android.graphics.Color;
import android.os.Handler;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;

import com.google.gson.Gson;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.center.model.StakeListBean;
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/11/16.
 */

public class KenoPresenter extends BaseRetrofitPresenter<KenoDataBean, KenoContract.View> implements KenoContract.Presenter {
    private Handler handler;
    private long countdownTime = 5000;
    private RefreshDataRunable refreshDataRunable;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public KenoPresenter(KenoContract.View view) {
        super(view);
        handler = new Handler();
        refreshDataRunable = new RefreshDataRunable();
    }

    class RefreshDataRunable implements Runnable {
        @Override
        public void run() {
            Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getKenoData(AppConstant.getInstance().URL_KENO_DATA))
                    .subscribe(new Consumer<KenoDataBean>() {
                        @Override
                        public void accept(KenoDataBean kenoDataBean) throws Exception {
                            baseView.onGetData(kenoDataBean);
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            ToastUtils.showShort(throwable.toString());
                            baseView.hideLoadingDialog();
                        }
                    }, new Action() {
                        @Override
                        public void run() throws Exception {

                        }
                    }, new Consumer<Subscription>() {
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            subscription.request(Integer.MAX_VALUE);
                        }
                    });
            mCompositeSubscription.add(d);
            handler.postDelayed(this, countdownTime);
        }
    }

    public void getBetStatu(final String p) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getKenoBetStatuData(AppConstant.getInstance().URL_KENO_STATU_DATA + p))
                .subscribe(new Consumer<KenoBetLimitBean>() {
                    @Override
                    public void accept(KenoBetLimitBean bean) throws Exception {
                        baseView.hideLoadingDialog();
                        baseView.onGetBetLimit(bean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                        ToastUtils.showShort("bet failed");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }

    @Override
    public void KenoBet(String params) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getKenoBetUrl(AppConstant.getInstance().URL_KENO_BET + params))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String str) throws Exception {
                        baseView.onGetBetReturn(str);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                        baseView.onGetBetReturn(throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }

    @Override
    public void KenoBetSuccessMsg() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_STAKE))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String stakeListBeen) throws Exception {
                        handleDicAllBean(stakeListBeen);
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
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }

    @Override
    public void getKenoData() {
        if (handler == null) {
            handler = new Handler();
        }
        if (refreshDataRunable == null) {
            refreshDataRunable = new RefreshDataRunable();
        }
        handler.post(refreshDataRunable);
    }

    @Override
    public void stopRefreshData() {
        if (handler != null && refreshDataRunable != null) {
            handler.removeCallbacks(refreshDataRunable);
            refreshDataRunable = null;
            handler = null;
        }
    }

    private void handleDicAllBean(String stakeListBeen) {
        baseView.hideLoadingDialog();
        Gson gson = new Gson();
        stakeListBeen = Html.fromHtml(stakeListBeen).toString();
        String[] data1 = stakeListBeen.split("nyhxkj");
        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
        if (list1 == null) {
            ToastUtils.showShort("Success");
            return;
        }
        StringBuilder builder = new StringBuilder();
        StakeListBean.DicAllBean item = list1.get(0);
        builder.append(item.getRefNo() + "(" + item.getTransDate() + ")");
        builder.append("\n");
        builder.append(item.getHome() + "  X  " + item.getAway());
        builder.append("\n");
        builder.append(item.getKenoType() + "       " + item.getDisplayOdds2());
        builder.append("\n");
        builder.append(item.getRes2());
        builder.append("\n");
        builder.append(item.getBetType());
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
        String str = builder.toString();
        int start = str.indexOf(n);
        int end = start + n.length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        String oddsType = item.getKenoType();
        style.setSpan(new ForegroundColorSpan(0xffad0c11), str.indexOf(oddsType),str.indexOf(oddsType)+oddsType.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        if (item.getDangerStatus().equals("D")) {
            style.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("R")) {
            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RR")) {
            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RP")) {
            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RA")) {
            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("RG")) {
            style.setSpan(new BackgroundColorSpan(Color.RED), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (item.getDangerStatus().equals("0")) {
            style.setSpan(new BackgroundColorSpan(Color.YELLOW), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            style.setSpan(new BackgroundColorSpan(Color.GREEN), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        ToastUtils.showShort(style);
    }
}
