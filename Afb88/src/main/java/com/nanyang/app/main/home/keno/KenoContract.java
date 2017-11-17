package com.nanyang.app.main.home.keno;

import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class KenoContract {
    interface View extends IBaseView<KenoDataBean> {

    }

    interface Presenter extends IBasePresenter {
        void getKenoData();
    }
}
