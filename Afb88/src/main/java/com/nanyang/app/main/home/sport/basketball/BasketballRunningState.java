package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballRunningState extends BasketballCommonState {
    public BasketballRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    public boolean mix() {
        return false;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BASKETBALL_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new BasketballEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new BasketballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new BasketballOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Basketball));
    }


    @Override
    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return  new BasketballCommonAdapterHelper(getBaseView().getContextActivity());
    }
    @Override
    protected IBetHelper onSetBetHelper() {
        return new BasketballCommonBetHelper(getBaseView());
    }
}
