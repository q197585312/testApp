package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface IBetHelper {
    Disposable bet(String url);
    Disposable clickOdds(TextView v, String url, final boolean isHf);
    void setCompositeSubscription(CompositeDisposable compositeSubscription);
}
