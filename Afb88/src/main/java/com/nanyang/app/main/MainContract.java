package com.nanyang.app.main;

import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

public interface MainContract {
    interface View extends IBaseView<String> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        //        void login(Map<String, String> info);

//        void switchLanguage(String lang);

    }
}