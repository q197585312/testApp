package com.nanyang.app.main.home.sport.winterSport;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class WinterSportOutRightState extends OutRightState {
    public WinterSportOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.WinterSport));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_WINTER_SPORT_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new WinterSportTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new WinterSportEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new WinterSportRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
