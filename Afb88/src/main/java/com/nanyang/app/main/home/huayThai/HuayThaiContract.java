package com.nanyang.app.main.home.huayThai;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/27.
 */

public class HuayThaiContract {
    interface View extends IBaseView<HuayDrawDateInfo> {
        void onFailed(String error);
        void onResultData(ResultBean s);
    }

    interface Presenter extends IBasePresenter {
        void refresh(String url);
        void submitBet(String url,HuayThaiBetSubmitBean info);
    }
}
