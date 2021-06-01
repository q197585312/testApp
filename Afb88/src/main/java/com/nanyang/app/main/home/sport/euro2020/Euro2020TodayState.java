package com.nanyang.app.main.home.sport.euro2020;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.majorLeagues.FiveMajorState;

/**
 * Created by ASUS on 2019/3/26.
 */

public class Euro2020TodayState extends FiveMajorState {
    public Euro2020TodayState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public String getDbId() {
        return "1";
    }
    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Euro_2020));
    }
}
