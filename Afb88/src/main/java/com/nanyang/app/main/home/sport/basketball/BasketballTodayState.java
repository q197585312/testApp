package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballTodayState extends BasketballCommonState {
    public BasketballTodayState(SportContract.View baseView) {
        super(baseView);
    }




    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BASKETBALL_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {

            case "Early":
                getBaseView().switchState(new BasketballEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new BasketballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new BasketballOutRightState(getBaseView()));
                break;
        }

    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Basketball));
    }

    @Override
    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return new BasketballCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
}
