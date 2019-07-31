package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TennisEarlyState extends TennisState {
    public TennisEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Tennis));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_TENNIS_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new TennisTodayState(getBaseView()));
        } else if (item.getType().equals("Early")) {
            getBaseView().switchState(this);
        }
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new TennisTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new TennisRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new TennisOutRightState(getBaseView()));
                break;
        }
    }
}
