package com.nanyang.app.main.home.sport.e_sport;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ESportEarlyState extends ESportState {
    public ESportEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.E_Sport));
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new ESportOutRightState(getBaseView()));
                break;

        }
    }
}
