package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyState extends SoccerCommonState {
    public SoccerEarlyState(SportContract2.View baseView) {
        super(baseView);
    }

    @Override
    public boolean menu() {
        return false;
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
        if(item.getType().equals("Running")){
            getBaseView().switchState(new SoccerRunningState(getBaseView()));
        }
        else if(item.getType().equals("Today")){
            getBaseView().switchState(new SoccerRunningState(getBaseView()));
        }
    }

}
