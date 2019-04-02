package com.nanyang.app.main.center.Stake;

import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeContact {
    interface View extends IBaseView<String> {

    }

    interface Presenter extends IBasePresenter {
        void getStakeListData();
    }
}
