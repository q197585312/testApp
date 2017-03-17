package com.nanyang.app.main.center;

import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.center.model.StatementListBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementPresenter extends BaseRetrofitPresenter<String, StatementContact.View> implements StatementContact.Presenter {
    public StatementPresenter(StatementContact.View view) {
        super(view);
    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void getStatementData(String mb, String userName) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).statementData())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

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
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void getThisBetHistory(String mb, String userName) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).thisWeekBetList(mb, userName))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.d("Consumer", "accept: "+s);
                        String result = Html.fromHtml(s).toString();
                        StringBuilder sb = new StringBuilder();
                        int size = result.length();
                        for (int i = 0; i < size; i++) {
                            String index = result.charAt(i) + "";
                            if (!index.equals("\t") && !TextUtils.isEmpty(index) && index != " ") {
                                sb.append(index);
                            }
                        }
                        String data1[] = sb.toString().split(" ");
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < data1.length; i++) {
                            if (!TextUtils.isEmpty(data1[i])) {
                                list.add(data1[i]);
                            }
                        }
                        Log.d("Consumer", "accept: "+list.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Consumer", "accept: "+throwable.toString());
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
    }

    @Override
    public List<StatementListBean> parseData(String data) {
        String result = Html.fromHtml(data).toString();
        StringBuilder sb = new StringBuilder();
        int size = result.length();
        for (int i = 0; i < size; i++) {
            String index = result.charAt(i) + "";
            if (!index.equals("\t") && !TextUtils.isEmpty(index) && index != " ") {
                sb.append(index);
            }
        }
        String data1[] = sb.toString().split(" ");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < data1.length; i++) {
            if (!TextUtils.isEmpty(data1[i])) {
                list.add(data1[i]);
            }
        }
        StatementListBean listBean1 = new StatementListBean();
        listBean1.setDate(list.get(7));
        listBean1.setStake(list.get(8));
        listBean1.setValid_amount(list.get(9));
        listBean1.setWl(list.get(10));
        listBean1.setCom(list.get(11));
        listBean1.setSettled(list.get(12));
        listBean1.setBalance(list.get(13));
        StatementListBean listBean2 = new StatementListBean();
        listBean2.setDate(list.get(14) + list.get(15));
        listBean2.setStake(list.get(16));
        listBean2.setValid_amount(list.get(17));
        listBean2.setWl(list.get(18));
        listBean2.setCom(list.get(19));
        listBean2.setSettled(list.get(20));
        listBean2.setBalance(list.get(21));
        StatementListBean listBean3 = new StatementListBean();
        listBean3.setDate(list.get(22) + list.get(23));
        listBean3.setStake(list.get(24));
        listBean3.setValid_amount(list.get(25));
        listBean3.setWl(list.get(26));
        listBean3.setCom(list.get(27));
        listBean3.setSettled(list.get(28));
        listBean3.setBalance(list.get(29));
        StatementListBean listBean4 = new StatementListBean();
        listBean4.setDate(list.get(30) + list.get(31));
        listBean4.setStake(list.get(32));
        listBean4.setValid_amount(list.get(33));
        listBean4.setWl(list.get(34));
        listBean4.setCom(list.get(35));
        listBean4.setSettled(list.get(36));
        listBean4.setBalance(list.get(37));
        StatementListBean listBean5 = new StatementListBean();
        listBean5.setDate(list.get(38) + list.get(39));
        listBean5.setStake(list.get(40));
        listBean5.setValid_amount(list.get(41));
        listBean5.setWl(list.get(42));
        listBean5.setCom(list.get(43));
        listBean5.setSettled(list.get(44));
        listBean5.setBalance(list.get(45));
        StatementListBean listBean6 = new StatementListBean();
        listBean6.setDate(list.get(46) + list.get(47));
        listBean6.setStake(list.get(48));
        listBean6.setValid_amount(list.get(49));
        listBean6.setWl(list.get(50));
        listBean6.setCom(list.get(51));
        listBean6.setSettled(list.get(52));
        listBean6.setBalance(list.get(53));
        StatementListBean listBean7 = new StatementListBean();
        listBean7.setDate(list.get(54) + list.get(55));
        listBean7.setStake(list.get(56));
        listBean7.setValid_amount(list.get(57));
        listBean7.setWl(list.get(58));
        listBean7.setCom(list.get(59));
        listBean7.setSettled(list.get(60));
        listBean7.setBalance(list.get(61));
        StatementListBean listBean8 = new StatementListBean();
        listBean8.setDate(list.get(62) + list.get(63));
        listBean8.setStake(list.get(64));
        listBean8.setValid_amount(list.get(65));
        listBean8.setWl(list.get(66));
        listBean8.setCom(list.get(67));
        listBean8.setSettled(list.get(68));
        listBean8.setBalance(list.get(69));
        StatementListBean listBean9 = new StatementListBean();
        listBean9.setDate(list.get(70));
        listBean9.setStake(list.get(71));
        listBean9.setValid_amount(list.get(72));
        listBean9.setWl(list.get(73));
        listBean9.setCom(list.get(74));
        listBean9.setSettled(list.get(75));
        listBean9.setBalance("");
        List<StatementListBean> dataList = new ArrayList<>();
        dataList.add(listBean1);
        dataList.add(listBean2);
        dataList.add(listBean3);
        dataList.add(listBean4);
        dataList.add(listBean5);
        dataList.add(listBean6);
        dataList.add(listBean7);
        dataList.add(listBean8);
        dataList.add(listBean9);
        baseView.hideLoadingDialog();
        return dataList;
    }
}
