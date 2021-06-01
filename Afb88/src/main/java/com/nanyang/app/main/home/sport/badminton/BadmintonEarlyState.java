package com.nanyang.app.main.home.sport.badminton;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BadmintonEarlyState extends BadmintonState {
    public BadmintonEarlyState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Badminton));
    }



    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new BadmintonTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new BadmintonRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new BadmintonOutRightState(getBaseView()));
                break;

        }
    }
}
