package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballEarlyMixState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/6/2.
 */

class USFootballEarlyMixState extends BasketballEarlyMixState {

    public USFootballEarlyMixState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new USFootballEarlyState(getBaseView()));
        return super.mix();
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_US_FOOTBALL_EARLY_MIX;
    }
    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1,getBaseView().getContextActivity().getString(R.string.Early),"Early",getBaseView().getContextActivity().getString(R.string.US_Football));
    }
}
