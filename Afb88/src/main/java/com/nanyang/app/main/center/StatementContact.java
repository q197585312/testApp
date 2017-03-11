package com.nanyang.app.main.center;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementContact {
    interface View extends IBaseView<String> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void getStatementData(String mb, String userName);
        void init(String mb, String userName);
    }
}
