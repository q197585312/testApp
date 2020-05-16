package com.nanyang.app.main.home.sport.athletics;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class AthleticsEarlyState extends AthleticsState {
    public AthleticsEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Athletics));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_ATHLETICS_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new AthleticsTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new AthleticsRunningState(getBaseView()));
                break;

        }
    }
}