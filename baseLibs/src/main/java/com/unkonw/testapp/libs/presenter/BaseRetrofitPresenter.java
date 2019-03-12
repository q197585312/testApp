package com.unkonw.testapp.libs.presenter;

import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public abstract class BaseRetrofitPresenter<V extends IBaseContext> implements IBasePresenter {


    public V baseContext;
    protected CompositeDisposable mCompositeSubscription;

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

    public BaseRetrofitPresenter(V iBaseContext) {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeDisposable();
        this.baseContext = iBaseContext;
        this.mApiWrapper = createRetrofitApi();
    }

    public Api createRetrofitApi() {
        return new Api();
    }

    public <T> void doRetrofitApiOnUiThread(Flowable<T> flowable, BaseConsumer<T> baseConsumer) {
        Disposable disposable = mApiWrapper.applyDisposable(flowable, baseConsumer);
        mCompositeSubscription.add(disposable);
    }

    public <T> void doRetrofitApiOnDefaultThread(Flowable<T> flowable, BaseConsumer<T> baseConsumer) {
        flowable.subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(baseConsumer.onNext, baseConsumer.onError, baseConsumer.onCompleted, baseConsumer.onStart);
    }
}
