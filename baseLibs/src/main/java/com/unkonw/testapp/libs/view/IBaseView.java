package com.unkonw.testapp.libs.view;

import android.app.Activity;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public interface IBaseView<T> {
    void onGetData(T data);
    void onFailed(String error);
    void hideLoadingDialog();
    void showLoadingDialog();
    Activity getContextActivity();
}
