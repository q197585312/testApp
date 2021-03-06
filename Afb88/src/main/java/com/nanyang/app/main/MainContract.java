package com.nanyang.app.main;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface MainContract {
    interface View extends IBaseView<String> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        //        void login(Map<String, String> info);

//        void switchLanguage(String lang);

    }
}