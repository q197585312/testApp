package com.nanyang.app.main.home.poker;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/15.
 */

public interface PorkerContract {
    interface View<T> extends IBaseView<T> {
        void initUi();
    }

    interface Presenter extends IBasePresenter {

    }
}
