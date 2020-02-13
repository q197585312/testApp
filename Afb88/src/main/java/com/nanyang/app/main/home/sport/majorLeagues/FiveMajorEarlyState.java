package com.nanyang.app.main.home.sport.majorLeagues;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by ASUS on 2019/3/26.
 */

public class FiveMajorEarlyState extends FiveMajorState {
    public FiveMajorEarlyState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FIVE_MAJOR_EARLY;
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Five_Major_Match));
    }
}
