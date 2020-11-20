package com.nanyang.app.main.home.sport.outRight;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by ASUS on 2019/4/23.
 */

class OutRightTodayState extends OutRightState {
    public OutRightTodayState(SportContract.View baseView) {
        super(baseView);

        ot = "t";
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_POOL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new OutRightEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new OutRightRunningState(getBaseView()));
                break;
        }
        fragment.addSportHeadAndFoot(fragment.currentIdBean);
    }
}
