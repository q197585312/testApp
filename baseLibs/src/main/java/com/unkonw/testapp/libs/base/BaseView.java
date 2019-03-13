package com.unkonw.testapp.libs.base;

/**
 * Created by ASUS on 2019/3/13.
 */

public abstract class BaseView<V extends IBaseContext,T> implements IBaseView<T> {
    public BaseView(V baseContext) {
        this.baseContext = baseContext;
    }
    V baseContext;

    @Override
    public V getIBaseContext() {
        return baseContext;
    }

    @Override
    public void onFailed(String error) {
        baseContext.hideLoadingDialog();
    }
}
