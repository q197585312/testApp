package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class IceHockeyEarlyState extends IceHockeyState {
    public IceHockeyEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getBaseActivity().getString(R.string.IceHockey));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_ICE_HOCKEY_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new IceHockeyTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new IceHockeyRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new IceHockeyOutRightState(getBaseView()));
                break;

        }
    }
}
