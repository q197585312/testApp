package com.nanyang.app.main.center.result;

import android.app.Activity;

import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ResultContact {
    interface View extends IBaseView<String> {
        Activity getBaseActivity();
        void onPopupWindowCreated(BasePopupWindow pop, int center);
        void onModuleList(String item, List<ResultInfo> resultInfos);
    }

    interface Presenter extends IBasePresenter {
        void getResultData();
    }
}
