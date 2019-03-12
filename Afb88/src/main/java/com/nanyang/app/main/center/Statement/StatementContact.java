package com.nanyang.app.main.center.Statement;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.base.IBaseView;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementContact {
    interface View extends IBaseView<String> {
        void onFailed(String error);

        void onGetConfirmBlanceData(String data);
    }

    interface Presenter extends IBasePresenter {
        void getStatementData(String userName);

        void confirmBlance(String url,String userName);
    }
}
