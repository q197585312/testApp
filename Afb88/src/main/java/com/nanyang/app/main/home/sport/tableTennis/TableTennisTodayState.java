package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TableTennisTodayState extends TableTennisState {
    public TableTennisTodayState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_TABLE_TENNIS_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new TableTennisEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new TableTennisRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new TableTennisOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Today), "Today", getBaseView().getContextActivity().getString(R.string.Table_Tennis));
    }


}
