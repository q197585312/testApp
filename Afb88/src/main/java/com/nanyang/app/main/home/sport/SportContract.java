package com.nanyang.app.main.home.sport;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface SportContract {
    interface View<T> extends IBaseView<T> {
       void onFailed(String error);
        void onPageData(int page, T pageData);
    }

    interface Presenter extends IBasePresenter {
        void refresh();
        void collection();
        void menu();
        void mix();
    }
}