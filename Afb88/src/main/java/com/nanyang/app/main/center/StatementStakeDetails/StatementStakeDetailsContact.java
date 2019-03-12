package com.nanyang.app.main.center.StatementStakeDetails;

import com.nanyang.app.main.center.model.StatementStakeDetailsListBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.base.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakeDetailsContact {
    interface View extends IBaseView<List<StatementStakeDetailsListBean>> {

    }

    interface Presenter extends IBasePresenter {
        void getStatementStakeDetailsData(String url);
    }
}
