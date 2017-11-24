package com.nanyang.app.main.home.keno;

import android.os.Handler;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

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
                        baseView.hideLoadingDialog();
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
}
