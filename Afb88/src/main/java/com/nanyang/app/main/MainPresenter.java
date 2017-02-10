package com.nanyang.app.main;


import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

class MainPresenter extends BaseRetrofitPresenter<String, MainContract.View, ApiMain> implements MainContract.Presenter {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    MainPresenter(MainContract.View view) {
        super(view);
    }

    @Override
    public ApiMain createRetrofitApi() {
        return new ApiMain();
    }



    @Override
    public void main(String str) {

            Disposable subscription = mApiWrapper.goMain()
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