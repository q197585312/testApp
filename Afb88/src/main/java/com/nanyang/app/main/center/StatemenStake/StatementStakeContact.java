package com.nanyang.app.main.center.StatemenStake;

import com.nanyang.app.main.center.model.StatementStakeListBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */

public class StatementStakeContact {
    interface View extends IBaseView<List<StatementStakeListBean>> {

    }

    interface Presenter extends IBasePresenter {
        void getThisBet(String url);
    }
}
