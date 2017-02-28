package com.nanyang.app.main.home.Games.thaiThousand;

import com.nanyang.app.main.home.Games.thaiThousand.model.ThaiThousandBetSubmitBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

/**
 * Created by Administrator on 2017/2/27.
 */

public class ThaiThousandContract {
    interface View extends IBaseView<String> {
        void onFailed(String error);

        void reset(String type);
    }

    interface Presenter extends IBasePresenter {
        void refresh(String type);
        void submitBet(ThaiThousandBetSubmitBean info);
    }
}
