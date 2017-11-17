package com.nanyang.app.main.home.keno;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
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

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public KenoPresenter(KenoContract.View view) {
        super(view);
    }

    @Override
    public void getKenoData() {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getKenoData(AppConstant.getInstance().URL_KENO_DATA))
                .subscribe(new Consumer<KenoDataBean>() {
                    @Override
                    public void accept(KenoDataBean kenoDataBean) throws Exception {
                        baseView.hideLoadingDialog();
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
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }
}
