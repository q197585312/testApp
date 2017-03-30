package com.nanyang.app.main.home.poker;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/2/15.
 */

class PorkerPresenter extends BaseRetrofitPresenter<String, PorkerContract.View<String>> implements PorkerContract.Presenter {


    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public PorkerPresenter(PorkerContract.View<String> view) {
        super(view);
    }

    // action="TransferBalance.aspx?us=demoafbai1&amp;k=a8f26ff623f44f5f8058b5bf45774df0" id="form1">
    public void skipGd88() {
        Disposable subscription = getService(ApiService.class).getData("http://main55.afb88.com/_View/LiveDealerGDC.aspx")


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        int start = Str.indexOf("TransferBalance");
                        int end = Str.indexOf("\" id=\"form1\"");

                        String k = "";
                        if (start > 0 && end > 0 && end > start) {
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
                            String url = Str.substring(start, end);
                            k = url.substring(url.indexOf("k="));
                            baseView.onGetData(k);
                        }
                        else if(Str.contains("Transaction not tally")){
                            ToastUtils.showShort("Transaction not tally");
                        }else if(Str.contains("Session Expired")){
                            ToastUtils.showShort("Session Expired");
                        }
                        else{
                            ToastUtils.showShort("Failed");
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
        mCompositeSubscription.add(subscription);

         /*   Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).getData(AppConstant.URL_GD88_1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String regex=".*<script language='javascript'>window.open\\('(.*?)'.*?";
                            Pattern p= Pattern.compile(regex);
                            Matcher m=p.matcher(s);
                            if(m.find()){
                                String url=m.group(1);
//                                http://a0096f.panda88.org/Public/validate.aspx?us=demoafbai5&k=1a56b037cee84f08acd00cce8be54ca1&r=841903858&lang=EN-US
                                String host=url.substring(0,url.indexOf("/",9));
                                AppConstant.HOST=host+"/";
                                return getService(ApiService.class).timerRun2(url);
                            }
                            return null;
                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())

                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {
                            if(str.contains("window.open(")) {
                                baseView.onGetData("Login Success");
                            }
                            else{
                                baseView.onFailed("Login Failed");
                            }
                        }
                    }, new Consumer<Throwable>() {//错误
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            baseView.onFailed(throwable.getLocalizedMessage());
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
            mCompositeSubscription.add(subscription);*/
    }
}
