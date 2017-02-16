package com.unkonw.testapp.libs.presenter;

import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public abstract class BaseRetrofitPresenter<T, V extends IBaseView<T>> implements IBasePresenter {


    public V baseView;

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }


    /**
     * Api类的包装 对象
     */

    public Api mApiWrapper;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeDisposable mCompositeSubscription;


    public  BaseRetrofitPresenter(V view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeDisposable();
        this.baseView = view;
        this.mApiWrapper = createRetrofitApi();
    }

    public  Api createRetrofitApi() {
        return  new Api();
    }

}
