package com.nanyang.app.main.home.sport.handball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class HandballEarlyState extends HandballState {
    public HandballEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.Handball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_HANDBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new HandballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new HandballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new HandballOutRightState(getBaseView()));
                break;

        }
    }
}
