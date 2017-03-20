package com.nanyang.app.main.home.sport.tennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.OutRightState;
import com.nanyang.app.main.home.sportInterface.SportContract2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TennisOutRightState extends OutRightState {
    public TennisOutRightState(SportContract2.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.Basketball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_TENNIS_CHAMPION_EARLY+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new TennisTodayState(getBaseView()));
        }else if(item.getType().equals("Early")) {
            getBaseView().switchState(new TennisEarlyState(getBaseView()));
        }else if(item.getType().equals("Running")) {
            getBaseView().switchState(new TennisRunningState(getBaseView()));
        }else if(item.getType().equals("OutRight")) {
            getBaseView().switchState(this);
        }

    }
}
