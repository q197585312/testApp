package com.nanyang.app.main.home.sport.volleyball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class VolleyballEarlyState extends VolleyballState {
    public VolleyballEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Volleyball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_VOLLEYBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new VolleyballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new VolleyballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new VolleyballOutRightState(getBaseView()));
                break;

        }
    }
}
