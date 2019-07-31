package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerEarlyMixState extends SoccerMixState {
    public SoccerEarlyMixState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new SoccerEarlyState(getBaseView()));
        return super.mix();
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.football));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_FOOTBALL_EARLY_Mix;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new SoccerTodayMixState(getBaseView()));
        } else if (item.getType().equals("Early")) {
            getBaseView().switchState(this);
        }

    }
}
