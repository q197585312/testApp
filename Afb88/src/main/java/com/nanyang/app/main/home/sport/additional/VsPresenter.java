package com.nanyang.app.main.home.sport.additional;


import android.os.Handler;
import android.support.annotation.NonNull;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

class VsPresenter extends BaseRetrofitPresenter<VsActivity> implements IBasePresenter {
    private String type;
    private BallInfo bean;
    private String paramT;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    VsPresenter(VsActivity view) {
        super(view);
    }


    public void scale(String paramT, BallInfo item, String matchType) {
        this.bean = item;
        this.type = matchType;
        this.paramT = paramT;
        String url = getUrl();
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).getData(url)).subscribe(new Consumer<String>() {//onNext
            @Override
            public void accept(String Str) throws Exception {
                baseContext.onGetData(Str);
                startUpdate();
                baseContext.hideLoadingDialog();
            }
        }, new Consumer<Throwable>() {//错误
            @Override
            public void accept(Throwable throwable) throws Exception {
                baseContext.onFailed(throwable.getMessage());
                baseContext.hideLoadingDialog();
            }
        }, new Action() {//完成
            @Override
            public void run() throws Exception {
                baseContext.hideLoadingDialog();
            }
        }, new Consumer<Subscription>() {//开始绑定
            @Override
            public void accept(Subscription subscription) throws Exception {
                baseContext.showLoadingDialog();
                subscription.request(Long.MAX_VALUE);
            }
        });
        mCompositeSubscription.add(subscription);
    }

    //http://a8206d.a36588.com/_view/pgajaxS.axd?T=MB2&oId=12270813&home=Rochdale&away=Millwall&moduleTitle=ENGLISH%20LEAGUE%20ONE&date=03:45AM&lang=EN-US&isRun=false&_=1490092254432
    @NonNull
    private String getUrl() {
        boolean isRunning = false;
        if (type.equals("Running"))
            isRunning = true;

        String url = AppConstant.getInstance().HOST + "_view/MoreBet_App.aspx?oId=" + bean.getSocOddsId() /*+ "&home=" + StringUtils.URLEncode(bean.getHome()) + "&away=" + StringUtils.URLEncode(bean.getAway()) + "&moduleTitle=" + StringUtils.URLEncode(bean.getModuleTitle().toString()) + "&date=" + StringUtils.URLEncode(bean.getMatchDate()) + "&isRun=" + isRunning*/
       + "&T=MB2&mt=0";
        url = url + "&t=" + System.currentTimeMillis();
        return url;
    }


    Handler updateHandler = new Handler();

    void stopUpdate() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }

    void startUpdate() {
        stopUpdate();
        updateHandler.postDelayed(dataUpdateRunnable, 20000);// 打开定时器，执行操作
    }

    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Disposable subscribe = getService(ApiService.class).getData(getUrl())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {//onNext
                                   @Override
                                   public void accept(String allData) throws Exception {
                                       baseContext.onGetData(allData);

                                   }
                               }, new Consumer<Throwable>() {
                                   @Override
                                   public void accept(Throwable throwable) {
                                       ToastUtils.showShort(throwable.getMessage());
                                   }
                               }, new Action() {//完成
                                   @Override
                                   public void run() throws Exception {
                                   }
                               }, new Consumer<Subscription>() {//开始绑定
                                   @Override
                                   public void accept(Subscription subscription1) throws Exception {
                                       subscription1.request(Long.MAX_VALUE);
                                   }
                               }

                    );
            mCompositeSubscription.add(subscribe);
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };
}