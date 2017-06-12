package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SuperComboEarlyState extends SuperComboState {
    public SuperComboEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.SuperCombo));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SUPER_COMBO_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new SuperComboTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new SuperComboRunningState(getBaseView()));
                break;

        }
    }
}
