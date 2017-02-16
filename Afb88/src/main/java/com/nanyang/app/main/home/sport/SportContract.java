package com.nanyang.app.main.home.sport;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

public interface SportContract {
    interface View<T> extends IBaseView<T> {
       void onFailed(String error);
        void onPageData(int page, T pageData,String type);
    }

    interface Presenter extends IBasePresenter {
        void refresh(String type);
        void collection();
        void menu();
        void mix();
    }
}