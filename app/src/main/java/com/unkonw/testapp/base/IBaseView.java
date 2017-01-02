package com.unkonw.testapp.base;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public interface IBaseView<T> {
    void onGetData(T data);
    void hideLoading();
    void showLoadingDialog();
}
