package com.unkonw.testapp.libs.base;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public interface IBaseView<T> {
    void onGetData(T data);
    void onFailed(String error);
    IBaseContext getIBaseContext();
}
