package com.nanyang.app.main.home.sport.badminton;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BadmintonOutRightState extends OutRightState {
    public BadmintonOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.Badminton));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BADMINTON_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new BadmintonTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new BadmintonEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new BadmintonRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
