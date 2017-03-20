package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;

import com.nanyang.app.main.home.sportInterface.SportContract2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballEarlyMixState extends BasketballMixState {
    public BasketballEarlyMixState(SportContract2.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new BasketballEarlyState(getBaseView()));
        return super.mix();
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, getBaseView().getContextActivity().getString(R.string.Early), "Early",getBaseView().getContextActivity().getString(R.string.Basketball));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_BASKETBALL_EARLY_Mix;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        if (item.getType().equals("Today")) {
            getBaseView().switchState(new BasketballTodayMixState(getBaseView()));
        } else if (item.getType().equals("Early")) {
            getBaseView().switchState(this);
        }

    }
}
