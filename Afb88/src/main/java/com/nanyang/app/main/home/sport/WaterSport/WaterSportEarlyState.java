package com.nanyang.app.main.home.sport.WaterSport;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class WaterSportEarlyState extends WaterSportState {
    public WaterSportEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Water_Polo));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_WATER_SPORT_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new WaterSportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new WaterSportRunningState(getBaseView()));
                break;

        }
    }
}
