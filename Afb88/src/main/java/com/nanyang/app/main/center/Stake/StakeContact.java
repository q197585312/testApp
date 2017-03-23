package com.nanyang.app.main.center.Stake;

import com.nanyang.app.main.center.model.StakeListBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeContact {
    interface View extends IBaseView<StakeListBean> {

    }

    interface Presenter extends IBasePresenter {
        void getStakeListData();
    }
}
