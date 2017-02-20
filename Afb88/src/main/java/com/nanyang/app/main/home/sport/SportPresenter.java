package com.nanyang.app.main.home.sport;


import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

abstract class SportPresenter<T, A extends  ApiSport> extends BaseRetrofitPresenter<T, SportContract.View<T>> implements SportContract.Presenter {
    public SportPresenter(SportContract.View<T> view) {
        super(view);
    }
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）


    @Override
    public void refresh(String type) {

    }
    public  void addMixParlayBet(BettingInfoBean info){
        StringBuilder builder=new StringBuilder();
        //"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?"
        builder.append(WebSiteUrl.SporSoccerGameBet);
        if(info.getGt()!=null&&!info.getGt().equals(""))
            builder.append("gt="+info.getGt());
        if(info.getB().equals("1")||info.getB().equals("X")||info.getB().equals("2")||info.getB().equals("odd")||info.getB().equals("even"))
            builder.append("&g=5");
        else if(info.getB().equals("X_par")||info.getB().equals("2_par")||info.getB().equals("1_par")||info.getB().equals("under_par")||info.getB().equals("over_par")||info.getB().equals("home_par")||
                info.getB().equals("away_par")||info.getB().equals("odd_par")||info.getB().equals("even_par"))
            builder.append("&g=2");
        builder.append("&b="+info.getB());
        if(info.getSc()!=null&&!info.getSc().equals(""))
            builder.append("&sc="+info.getSc());
        builder.append("&oId="+info.getSocOddsId());
        builder.append("&odds="+info.getOdds());
        if(info.isRunning())
            builder.append("&isRun=true");
        if(info.getIsFH()==1&&info.getSocOddsId_FH()!=null&& !info.getSocOddsId_FH().equals(""))
            builder.append("&isFH=true&oId_fh="+info.getSocOddsId_FH());
        helper.getData(builder.toString(), "", TableDataHelper.ModelType.Default);
        switch (type) {

        Disposable subscription = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
               /* .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
//                        String regex = ".*timerRun2\\('(.*?)'.*?";
                        String regex = ".*timerToday2\\('(.*?)'.*?";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(s);
                        if (m.find()) {
                            String url = "http://a8197c.a36588.com/_view/" + m.group(1) + "&LID=&_=1486612091203";
                            return Api.getService(ApiService.class).goFootball(url);
                        }
                        return null;
                    }
                })*/
                .map(new Function<String, List<TableModuleBean>>() {

                    @Override
                    public List<TableModuleBean> apply(String s) throws Exception {
                        return parseTableModuleBeen(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TableModuleBean>>() {//onNext
                    @Override
                    public void accept(List<TableModuleBean> allData) throws Exception {
                        initAllData(allData);
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

}