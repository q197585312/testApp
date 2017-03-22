package com.nanyang.app.main.center;

import com.nanyang.app.main.center.model.StatementListBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class StatementContact {
    interface View extends IBaseView<String> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void getStatementData(String userName);

        void getThisBetHistory(String mb, String userName);

        List<StatementListBean> parseData(String data);
    }
}
