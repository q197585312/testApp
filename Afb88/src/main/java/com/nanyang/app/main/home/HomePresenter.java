package com.nanyang.app.main.home;


import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static com.unkonw.testapp.libs.api.Api.getService;

class HomePresenter extends BaseRetrofitPresenter<String, HomeContract.View> implements HomeContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    HomePresenter(HomeContract.View view) {
        super(view);
    }

    @Override
    public ApiHome createRetrofitApi() {
        return new ApiHome();
    }



    @Override
    public void getBallURl() {
        Disposable subscription =
                mApiWrapper.applySchedulers(getService(ApiService.class).main())
//                mApiWrapper.getBallUrl()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
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
}