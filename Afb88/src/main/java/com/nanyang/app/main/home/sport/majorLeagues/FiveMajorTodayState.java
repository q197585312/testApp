package com.nanyang.app.main.home.sport.majorLeagues;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by ASUS on 2019/3/26.
 */

public class FiveMajorTodayState extends FiveMajorState {
    public FiveMajorTodayState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FIVE_MAJOR_TODAY;
    }
    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Five_Major_Match));
    }
}
