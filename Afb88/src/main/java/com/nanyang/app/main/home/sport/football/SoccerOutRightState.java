package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerOutRightState extends OutRightState {
    public SoccerOutRightState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.OutRight),"OutRight",getBaseView().getContextActivity().getString(R.string.football));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FOOTBALL_OUT_RIGHT+"&ot=e";
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new SoccerTodayState(getBaseView()));
        }else if(item.getType().equals("Early")) {
            getBaseView().switchState(new SoccerEarlyState(getBaseView()));
        }else if(item.getType().equals("Running")) {
            getBaseView().switchState(new SoccerRunningState(getBaseView()));
        }else if(item.getType().equals("OutRight")) {
            getBaseView().switchState(this);
        }

    }
}
