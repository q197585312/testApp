package com.nanyang.app.main.home.sport.tableTennis;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class TableTennisRunningState extends TableTennisState {
    public TableTennisRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_TABLE_TENNIS_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new TableTennisEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new TableTennisTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new TableTennisOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Table_Tennis));
    }

}
