package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.OutRightState;
import com.nanyang.app.main.home.sportInterface.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballOutRightState extends OutRightState {
    public BasketballOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.Basketball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_BASKETBALL_OUT_RIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new BasketballTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new BasketballEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new BasketballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
