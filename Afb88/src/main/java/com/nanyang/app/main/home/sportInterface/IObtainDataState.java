package com.nanyang.app.main.home.sportInterface;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/3/10.
 */

public interface IObtainDataState {
    Disposable refresh();
    Disposable startUpdateData();
    void stopUpdateData();
    void showData();
    boolean isCollection();


}
