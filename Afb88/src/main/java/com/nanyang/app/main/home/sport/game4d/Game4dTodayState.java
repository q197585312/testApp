package com.nanyang.app.main.home.sport.game4d;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class Game4dTodayState extends Game4dState {
    public Game4dTodayState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_4D_SPECIAL_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new Game4dEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new Game4dRunningState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Specials_4D));
    }


}
