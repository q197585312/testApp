package com.nanyang.app.main.home.sport.financial;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.SportContract2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class FinancialRunningState extends FinancialState {
    public FinancialRunningState(SportContract2.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FINANCIAL_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new FinancialEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new FinancialTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Running),"Running",getBaseView().getContextActivity().getString(R.string.Financial));
    }

}
