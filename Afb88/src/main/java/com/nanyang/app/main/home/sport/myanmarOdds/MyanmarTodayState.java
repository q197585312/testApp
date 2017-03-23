package com.nanyang.app.main.home.sport.myanmarOdds;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/21.
 */

public class MyanmarTodayState extends MyanmarState {
    public MyanmarTodayState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0,getBaseView().getContextActivity().getString(R.string.Today),"Today",getBaseView().getContextActivity().getString(R.string.Myanmar_Odds));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_SOCCER_MYANMAR_TODAY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new MyanmarEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(this);
                break;
            case "Running":
                getBaseView().switchState(new MyanmarRunningState(getBaseView()));
                break;
        }
    }
}
