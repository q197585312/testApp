package com.nanyang.app.main.home;

import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

public interface HomeContract {
    interface View extends IBaseView<String> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void getBallURl();

    }
}