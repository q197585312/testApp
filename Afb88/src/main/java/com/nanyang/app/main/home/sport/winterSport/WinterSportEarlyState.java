package com.nanyang.app.main.home.sport.winterSport;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class WinterSportEarlyState extends WinterSportState {
    public WinterSportEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.WinterSport));
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new WinterSportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new WinterSportRunningState(getBaseView()));
                break;

        }
    }
}
