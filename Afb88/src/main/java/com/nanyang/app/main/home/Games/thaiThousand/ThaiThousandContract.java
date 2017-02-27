package com.nanyang.app.main.home.Games.thaiThousand;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/27.
 */

public class ThaiThousandContract {
    interface View<T> extends IBaseView<T> {
        void onFailed(String error);
    }

    interface Presenter extends IBasePresenter {
        void refresh(String type);

        void nativeMsg();
    }
}
