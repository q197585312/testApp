package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballEarlyState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class USFootballEarlyState extends BasketballEarlyState {
    public USFootballEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.US_Football));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_US_FOOTBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new USFootballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new USFootballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new USFootballOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new USFootballEarlyMixState(getBaseView()));
        return true;
    }
}
