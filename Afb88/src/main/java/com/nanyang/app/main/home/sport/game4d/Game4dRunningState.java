package com.nanyang.app.main.home.sport.game4d;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Game4dRunningState extends Game4dState {
    public Game4dRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_4D_SPECIAL_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new Game4dEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new Game4dTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Specials_4D));
    }

}
