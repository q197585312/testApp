package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/15.
 */

public interface IBetHelper<B> {
    Disposable bet(String url);
    Disposable clickOdds(B item,String type,String odds,TextView v, final boolean isHf);
    void setCompositeSubscription(CompositeDisposable compositeSubscription);
}
