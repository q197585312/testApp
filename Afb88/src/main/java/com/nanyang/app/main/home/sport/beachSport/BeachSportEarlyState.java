package com.nanyang.app.main.home.sport.beachSport;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BeachSportEarlyState extends BeachSportState {
    public BeachSportEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Beach_Soccer));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BEACH_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new BeachSportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new BeachSportRunningState(getBaseView()));
                break;
        }
    }
}
