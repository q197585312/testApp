package com.nanyang.app.main.home.keno;

import com.nanyang.app.main.home.keno.bean.KenoBetLimitBean;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/11/16.
 */

public class KenoContract {
    interface View extends IBaseView<KenoDataBean> {
        void onGetBetLimit(KenoBetLimitBean bean);

        void onGetBetReturn(String str);
    }

    interface Presenter extends IBasePresenter {
        void getKenoData();

        void stopRefreshData();

        void getBetStatu(String s);

        void KenoBet(String params);

        void KenoBetSuccessMsg();
    }
}
