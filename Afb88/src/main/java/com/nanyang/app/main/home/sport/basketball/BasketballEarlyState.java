package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

import com.nanyang.app.main.home.sportInterface.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballEarlyState extends BasketballCommonState {
    public BasketballEarlyState(SportContract.View baseView) {
        super(baseView);

    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new BasketballEarlyMixState(getBaseView()));
        return true;
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_BASKETBALL_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Running":
                getBaseView().switchState(new BasketballRunningState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new BasketballRunningState(getBaseView()));
                break;
            case "Early":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new BasketballOutRightState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Early),"Early",getBaseView().getContextActivity().getString(R.string.Basketball));
    }

    @Override
    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return new BasketballCommonAdapterHelper(getBaseView().getContextActivity());
    }
}
