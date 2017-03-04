package com.nanyang.app.main.home.sport.additional;

import com.nanyang.app.main.home.sport.model.ScaleBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface VsContract {
    interface View extends IBaseView<ScaleBean> {
       void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {


    }
}