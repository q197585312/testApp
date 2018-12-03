package com.nanyang.app.main.home.sport.mixparlayList;

import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import java.util.List;

public interface MixOrderListContract {
    interface View<T> extends BetView<T> {
        void obtainListData(AfbClickResponseBean betInfo);
        void obtainBottomData(List<ClearanceBetAmountBean> clearanceBetAmountBeen);
    }

    interface Presenter extends IBasePresenter {

    }
}