package com.nanyang.app.main.home.sport.mixparlayList;

import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.ClearanceBetAmountBean;
import com.unkonw.testapp.libs.presenter.IBasePresenter;

import java.util.ArrayList;
import java.util.List;

public interface MixOrderListContract {
    interface View<T> extends SportContract.View<T> {
        void obtainListData(ArrayList<BettingInfoBean> betInfo);

        void obtainBottomData(List<ClearanceBetAmountBean> clearanceBetAmountBeen);
    }

    interface Presenter extends IBasePresenter {
        void main(String msg);

    }
}