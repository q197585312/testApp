package com.nanyang.app.main.home.sport.handball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class HandballOutRightState extends OutRightState {
    public HandballOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getBaseActivity().getString(R.string.OutRight),"OutRight",getBaseView().getBaseActivity().getString(R.string.Handball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_HANDBALL_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new HandballTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new HandballEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new HandballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
