package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyMixState extends SoccerMixState {
    public SoccerEarlyMixState(SportContract2.View baseView) {
        super(baseView);
    }




    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerEarlyState(getBaseView()));
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo(1,getBaseView().getContextActivity().getString(R.string.Early),"Early");
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_EARLY_Mix;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new SoccerTodayMixState(getBaseView()));
        }else if(item.getType().equals("Early")) {
            getBaseView().switchState(this);
        }

    }
}
