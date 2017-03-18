package com.nanyang.app.main.home.sport.mixparlayList;

import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.view.IBaseView;

import java.util.List;

public interface MixOrderListContract {
    interface View<T> extends IBaseView<T> {
        void obtainListData(BettingParPromptBean betInfo);
        void obtainBottomData(List<ClearanceBetAmountBean> clearanceBetAmountBeen);
    }

    interface Presenter extends IBasePresenter {

    }
}