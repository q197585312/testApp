package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyState extends SoccerCommonState {
    public SoccerEarlyState(SportContract2.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerEarlyMixState(getBaseView()));
        return true;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SoccerRunningState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new SoccerOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo(0,getBaseView().getContextActivity().getString(R.string.Early),"Early");
    }

    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerEarlyAdapterHelper(getBaseView().getContextActivity());
    }
}
