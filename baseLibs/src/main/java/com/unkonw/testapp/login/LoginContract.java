package com.unkonw.testapp.login;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.Map;

public interface LoginContract {
    interface View extends IBaseView<String> {
    }

    interface Presenter extends IBasePresenter {
        void login(Map<String,String> info);
        void login(LoginInfo info);

    }
}