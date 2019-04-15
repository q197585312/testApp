package com.nanyang.app.main.home.sport.cricket;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class CricketEarlyState extends CricketState {
    public CricketEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Cricket));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_CRICKET_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new CricketTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new CricketRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new CricketOutRightState(getBaseView()));
                break;

        }
    }
}
