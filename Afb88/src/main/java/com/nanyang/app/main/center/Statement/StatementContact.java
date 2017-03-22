package com.nanyang.app.main.center.Statement;

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

        void onGetStatementListData(List<StatementListBean> list);
    }

    interface Presenter extends IBasePresenter {
        void getStatementData(String userName);

    }
}
