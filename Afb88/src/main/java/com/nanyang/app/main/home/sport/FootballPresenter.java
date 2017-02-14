package com.nanyang.app.main.home.sport;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.api.Api;

import org.json.JSONArray;
import org.reactivestreams.Subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class
FootballPresenter extends SportPresenter<Object,ApiSport> {
    public FootballPresenter(SportContract.View<Object> view) {
        super(view);
    }

    @Override
    public ApiSport createRetrofitApi() {
        return new ApiSport() {
            @Override
            Flowable<String> refresh() {
                return applySchedulers(getService(ApiService.class).goRefresh());
            }
        };
    }

    //  String regex=".*timerRun2\\('(.*?)'.*timerToday2\\('(.*?)'.*?";
    @Override
    public void refresh() {
        Disposable subscription = mApiWrapper.refresh()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        String regex=".*timerRun2\\('(.*?)'.*?";
                        Pattern p= Pattern.compile(regex);
                        Matcher m=p.matcher(s);
                        if(m.find()){
                            String url="http://a8197c.a36588.com/_view/"+m.group(1)+"&LID=&_=1486612091203";
                            return Api.getService(ApiService.class).timerRun2(url);
                        }
                        return null;
                    }
                })
                .map(new Function<String,String>() {

                    @Override
                    public String apply(String s) throws Exception {
                        JSONArray jsonArray =new  JSONArray(s);
                        if(jsonArray.length()>4){
                            JSONArray jsonArray2= jsonArray.getJSONArray(3);
                            if(jsonArray2.length()>0){
                                
                                for (int i = 0; i < jsonArray2.length(); i++) {
                                    JSONArray jsonArray3= jsonArray.getJSONArray(i);
                                }
                            }
                        }
                        return jsonArray.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {//onNext
                    @Override
                    public void accept(Object Str) throws Exception {
                        baseView.onGetData(Str);
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
        mCompositeSubscription.add(subscription);

    }
    @Override
    public void collection() {
    }
    @Override
    public void menu() {
    }
    @Override
    public void mix() {
    }
    @NonNull
    protected String getFormatString(String strRes) {
        if (strRes != null && !strRes.equals("")) {
            strRes.replaceAll("<br>", " ");
//            strRes = Html.fromHtml(strRes).toString();
            strRes.replaceAll("\\n", " ");
            strRes = strRes.replaceAll(" +", " ");
            Log.w("HttpVolley", strRes);
        }
        return strRes;
    }
}
