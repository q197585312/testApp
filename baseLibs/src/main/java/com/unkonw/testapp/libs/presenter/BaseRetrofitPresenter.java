package com.unkonw.testapp.libs.presenter;

import com.unkonw.testapp.libs.api.ApiWrapper;
import com.unkonw.testapp.libs.view.IBaseView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public class BaseRetrofitPresenter<T, V extends IBaseView<T>> implements IBasePresenter {

    public V baseView;

    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.dispose();
        }
    }


    /**
     * Api类的包装 对象
     */
    protected ApiWrapper mApiWrapper;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeDisposable mCompositeSubscription;


    public BaseRetrofitPresenter(V view) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeDisposable();
        // 构建 ApiWrapper 对象
        this.baseView = view;
        this.mApiWrapper = new ApiWrapper();
    }

}
