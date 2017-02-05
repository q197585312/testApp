package com.nanyang.app.load.login;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface LoginContract {
    interface View extends IBaseView<String> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
//        void login(Map<String, String> info);
        void login(LoginInfo info);

    }
}