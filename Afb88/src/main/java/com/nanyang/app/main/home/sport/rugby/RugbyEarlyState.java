package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class RugbyEarlyState extends RugbyState {
    public RugbyEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Rugby));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_RUGBY_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new RugbyTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new RugbyRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new RugbyOutRightState(getBaseView()));
                break;

        }
    }
}
