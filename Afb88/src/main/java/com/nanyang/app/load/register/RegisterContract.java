package com.nanyang.app.load.register;

import com.nanyang.app.load.UserInfo;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface RegisterContract {
    interface View extends IBaseView<String> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void register(UserInfo info);
    }
}