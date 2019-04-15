package com.nanyang.app.main.home.sport.game4d;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Game4dEarlyState extends Game4dState {
    public Game4dEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Specials_4D));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_4D_SPECIAL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new Game4dTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new Game4dRunningState(getBaseView()));
                break;

        }
    }
}
