package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerTodayState extends SoccerCommonState {
    public SoccerTodayState(SportContract2.View baseView) {
        super(baseView);
    }

    @Override
    public boolean menu() {
        return false;
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerTodayMixState(getBaseView()));
        return true;
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if(item.getType().equals("Running")){
            getBaseView().switchState(new SoccerRunningState(getBaseView()));
        }
        else if(item.getType().equals("Early")){
            getBaseView().switchState(new SoccerEarlyState(getBaseView()));
        }
    }
}
