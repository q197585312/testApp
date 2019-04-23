package com.nanyang.app.main.home.sport.outRight;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by ASUS on 2019/4/23.
 */

public class OutRightEarlyState extends OutRightState {
    public OutRightEarlyState(SportContract.View baseView) {
        super(baseView);
        ot="e";
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "OutRight", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight));
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight);
    }

    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_POOL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new OutRightTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new OutRightTodayState(getBaseView()));
                break;

        }
    }
}
