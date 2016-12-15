package com.unkonw.testapp.base;

import com.unkonw.testapp.UserDate;

public interface LoginContract {
    interface View extends IBaseView<UserDate> {
        void loginSuccess(UserDate data);
    }

    interface Presenter extends IBasePresenter {
        void login();
    }
}