package com.unkonw.testapp.libs.base;

/**
 * Created by Administrator on 2019/1/21.
 */

public interface IBaseContext {
    void hideLoadingDialog();
    void showLoadingDialog();
    BaseActivity getBaseActivity();
}
