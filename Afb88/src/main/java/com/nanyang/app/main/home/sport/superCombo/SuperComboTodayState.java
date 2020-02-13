package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SuperComboTodayState extends SuperComboState {
    public SuperComboTodayState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_SUPER_COMBO_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new SuperComboEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new SuperComboRunningState(getBaseView()));
                break;
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.SuperCombo));
    }


}
