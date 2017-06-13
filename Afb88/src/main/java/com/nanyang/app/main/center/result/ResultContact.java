package com.nanyang.app.main.center.result;

import android.app.Activity;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

/**
 * Created by Administrator on 2017/3/23.
 */

public class ResultContact {
    interface View extends IBaseView<String> {
        Activity getContextActivity();
        void onPopupWindowCreated(BasePopupWindow pop, int center);

    }

    interface Presenter extends IBasePresenter {
        void getResultData();
    }
}
