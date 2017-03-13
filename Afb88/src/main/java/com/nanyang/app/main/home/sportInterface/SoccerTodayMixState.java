package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerTodayMixState extends SoccerMixState {
    public SoccerTodayMixState(SportContract2.View baseView) {
        super(baseView);
    }

    @Override
    public boolean menu() {
        return false;
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerTodayState(getBaseView()));
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_TODAY_Mix;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if(item.getType().equals("Early")){
            getBaseView().switchState(new SoccerEarlyMixState(getBaseView()));
        }
    }
}
