package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SuperComboRunningState extends SuperComboState {
    public SuperComboRunningState(SportContract.View baseView) {
        super(baseView);
    }



    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SUPER_COMBO_RUNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new SuperComboEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new SuperComboTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;


        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Running),"Running",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.SuperCombo));
    }

}
