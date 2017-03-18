package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerTodayMixState extends SoccerMixState {
    public SoccerTodayMixState(SportContract2.View baseView) {
        super(baseView);
    }





    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerTodayState(getBaseView()));
        return super.mix();
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
        else if(item.getType().equals("Today")){
            getBaseView().switchState(this);
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1,getBaseView().getContextActivity().getString(R.string.Today),"Today",getBaseView().getContextActivity().getString(R.string.football));
    }


}
