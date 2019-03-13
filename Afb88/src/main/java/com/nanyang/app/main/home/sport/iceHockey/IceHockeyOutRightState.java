package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class IceHockeyOutRightState extends OutRightState {
    public IceHockeyOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight),"OutRight",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.IceHockey));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_ICE_HOCKEY_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new IceHockeyTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new IceHockeyEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new IceHockeyRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
