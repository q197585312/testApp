package com.nanyang.app.main.home.sport.e_sport;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class ESportOutRightState extends OutRightState {
    public ESportOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getBaseActivity().getString(R.string.OutRight),"OutRight",getBaseView().getBaseActivity().getString(R.string.E_Sport));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_E_SPORT_OUTRIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(new ESportEarlyState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(this);
                break;
        }

    }
}
