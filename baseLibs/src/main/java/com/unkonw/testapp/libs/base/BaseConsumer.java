package com.unkonw.testapp.libs.base;

import android.util.Log;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

import static android.content.ContentValues.TAG;

/**
 * Created by ASUS on 2019/3/10.
 */

public abstract class BaseConsumer<T> {
    public Consumer<Subscription> onStart;
    public Action onCompleted;
    public Consumer<Throwable> onError;
    public Consumer<T> onNext;
    public IBaseContext baseContext;

    public BaseConsumer(IBaseContext iBaseActivity) {
        this.baseContext = iBaseActivity;
        onNext = new Consumer<T>() {
            @Override
            public void accept(T data) throws Exception {
                onBaseGetData(data);
                baseContext.hideLoadingDialog();
            }
        };
        onError = new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                onError(throwable);
            }
        };
        onCompleted = new Action() {
            @Override
            public void run() throws Exception {

            }
        };
        onStart = new Consumer<Subscription>() {
            @Override
            public void accept(Subscription subscription) throws Exception {
                onAccept();
                subscription.request(Integer.MAX_VALUE);
            }
        };

    }

    protected void onError(Throwable throwable) {
        baseContext.hideLoadingDialog();
        Log.d(TAG, "accept: " + throwable.toString());
    }

    protected void onAccept() {
        baseContext.showLoadingDialog();
    }

    protected abstract void onBaseGetData(T data) throws JSONException;

}
