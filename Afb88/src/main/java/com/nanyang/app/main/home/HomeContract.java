package com.nanyang.app.main.home;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface HomeContract {
    interface View extends IBaseView<String> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void getBallURl();

    }
}