package com.nanyang.app.load.register;

import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

public interface RegisterContract {
    interface View extends IBaseView<String> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {

        void register(RegisterInfo info);

        void checkUserName(RegisterInfo info);

        void initBank();

        void initCurrency();
    }
}