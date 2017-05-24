package com.nanyang.app.main.home.sport.baseball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BaseballEarlyState extends BaseballState {
    public BaseballEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.Baseball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BASEBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new BaseballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new BaseballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new BaseballOutRightState(getBaseView()));
                break;

        }
    }
}
