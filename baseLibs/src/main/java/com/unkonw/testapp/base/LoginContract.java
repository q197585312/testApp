package com.unkonw.testapp.base;

import com.unkonw.testapp.LoginInfo;

public interface LoginContract {
    interface View extends IBaseView<String> {
    }

    interface Presenter extends IBasePresenter {
        void login(LoginInfo info);
    }
}