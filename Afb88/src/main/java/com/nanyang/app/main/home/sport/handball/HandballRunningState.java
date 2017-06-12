package com.nanyang.app.main.home.sport.handball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class HandballRunningState extends HandballState {
    public HandballRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_HANDBALL_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new HandballEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new HandballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new HandballOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Handball));
    }

}
