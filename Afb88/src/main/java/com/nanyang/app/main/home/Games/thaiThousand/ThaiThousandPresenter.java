package com.nanyang.app.main.home.Games.thaiThousand;

import android.util.Log;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.Games.thaiThousand.model.ThaiThousandBetSubmitBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ThaiThousandPresenter extends BaseRetrofitPresenter<String, ThaiThousandContract.View> implements ThaiThousandContract.Presenter {

    public ThaiThousandPresenter(ThaiThousandContract.View view) {
        super(view);
    }

    @Override
    public void refresh(String type) {
        if (type.equals("Game1")) {

        } else if (type.equals("Game2")) {

        } else if (type.equals("Game3")) {

        }

    }

    @Override
    public void submitBet(ThaiThousandBetSubmitBean info) {
        Disposable disposable = mApiWrapper.applySchedulers(Api.getService(ApiService.class).ThaiThsandBetSubmit(info.getThaiThousandBetSubmitMap()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("Throwable", "accept: " + throwable.toString());
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
        mCompositeSubscription.add(disposable);
    }

    @Override
    public void unSubscribe() {

    }

}
