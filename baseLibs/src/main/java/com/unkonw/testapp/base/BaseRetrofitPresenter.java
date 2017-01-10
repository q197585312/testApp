package com.unkonw.testapp.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public  class BaseRetrofitPresenter<T,V extends IBaseView<T>> implements IBasePresenter {

    public V baseView;
    @Override
    public void unSubscribe() {
        if(mCompositeSubscription != null){
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
        this.baseView  = view;
        this.mApiWrapper = new ApiWrapper();
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <E>
     * @return
     */
   /* protected <E> Subscriber newMySubscriber(final SimpleMyCallBack onNext) {
        return new Subscriber<E>() {

            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    if (baseView != null) {
                        ToastUtils.showShort(exception.message);
                    }
                } else if (e instanceof HttpException) {
                    if (e instanceof HttpException) {
                        ResponseBody body = ((HttpException) e).response().errorBody();
                        try {
                            String json = body.string();
                            Gson gson = new Gson();
                            HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                            if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.getMessage());
                                onNext.onError(mHttpExceptionBean);
                            }
                        } catch (IOException IOe) {
                            IOe.printStackTrace();
                        }
                    }
                }
//                e.printStackTrace();
                if (baseView != null) {
                    baseView.hideLoading();
                }
            }

            @Override
            public void onComplete() {
                if (baseView != null) {
                    baseView.hideLoading();
                }
                onNext.onCompleted();
            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(E t) {
                if (!mCompositeSubscription.isDisposed()) {
                    onNext.onNext(t);
                }
            }

        };
    }*/
}
