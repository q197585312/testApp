package com.nanyang.app.load.login;

import com.nanyang.app.common.ILanguageView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

public interface LoginContract {
    interface View extends ILanguageView<String> {
       void onFailed(String error);
        void promptMsg(int msgRes);
    }

    interface Presenter extends IBasePresenter {
//        void login(Map<String, String> info);
        void login(LoginInfo info);

    }
}