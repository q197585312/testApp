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
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/11/16.
 */

public class KenoPresenter extends BaseRetrofitPresenter<KenoActivity> {
    private Handler handler;
    private long countdownTime = 5000;
    private RefreshDataRunnable refreshDataRunable;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public KenoPresenter(KenoActivity view) {
        super(view);
        handler = new Handler();
    }

    class RefreshDataRunnable implements Runnable {
        BaseConsumer<KenoDataBean> consumer;

        public RefreshDataRunnable(BaseConsumer<KenoDataBean> consumer) {
            this.consumer = consumer;
        }

        @Override
        public void run() {
            doRetrofitApiOnUiThread(Api.getService(ApiService.class).getKenoData(AppConstant.getInstance().URL_KENO_DATA), consumer);
            handler.postDelayed(this, countdownTime);
        }
    }

    public void getBetStatus(final String p, BaseConsumer<KenoBetLimitBean> consumer) {
        doRetrofitApiOnUiThread(Api.getService(ApiService.class).getKenoBetStatusData(AppConstant.getInstance().URL_KENO_STATU_DATA + p), consumer);
    }

    public void kenoBet(String params, BaseConsumer<String> consumer) {
        doRetrofitApiOnUiThread(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_KENO_BET + params), consumer);
    }

    public void KenoBetSuccessMsg(BaseConsumer<String> consumer) {
        doRetrofitApiOnUiThread(Api.getService(ApiService.class).getData(AppConstant.getInstance().URL_STAKE), consumer);
    }


    public void getKenoData(BaseConsumer<KenoDataBean> baseConsumer) {
        if (handler == null) {
            handler = new Handler();
        }
        if (refreshDataRunable == null) {
            refreshDataRunable = new RefreshDataRunnable(baseConsumer);
        }
        handler.post(refreshDataRunable);
    }


    public void stopRefreshData() {
        if (handler != null && refreshDataRunable != null) {
            handler.removeCallbacks(refreshDataRunable);
            refreshDataRunable = null;
            handler = null;
        }
    }

//    protected void handleDicAllBean(String stakeListBeen) {
//        baseContext.hideLoadingDialog();
//        Gson gson = new Gson();
//        stakeListBeen = Html.fromHtml(stakeListBeen).toString();
//        String[] data1 = stakeListBeen.split("nyhxkj");
//        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
//        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
//        if (list1 == null) {
//            ToastUtils.showShort("Success");
//            return;
//        }
//        StringBuilder builder = new StringBuilder();
//        StakeListBean.DicAllBean item = list1.get(0);
//        builder.append(item.getRefNo() + "(" + item.getTransDate() + ")");
//        builder.append("\n");
//        builder.append(item.getHome() + "  X  " + item.getAway());
//        builder.append("\n");
//        builder.append(item.getKenoType() + "       " + item.getDisplayOdds2());
//        builder.append("\n");
//        builder.append(item.getRes2());
//        builder.append("\n");
//        builder.append(item.getBetType());
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
//        String oddsType = item.getKenoType();
//        style.setSpan(new ForegroundColorSpan(0xffad0c11), str.indexOf(oddsType), str.indexOf(oddsType) + oddsType.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
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
}
