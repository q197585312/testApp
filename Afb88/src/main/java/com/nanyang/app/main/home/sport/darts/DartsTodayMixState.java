package com.nanyang.app.main.home.sport.darts;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballTodayMixState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/6/2.
 */

class DartsTodayMixState extends BasketballTodayMixState {

    public DartsTodayMixState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new DartsTodayState(getBaseView()));
        return super.mix();
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_DARTS_TODAY_MIX;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Darts));
    }
}
