package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.football.SoccerEarlyMixState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BasketballTodayMixState extends BasketballMixState {
    public BasketballTodayMixState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    public boolean mix() {
        getBaseView().switchState(new BasketballTodayState(getBaseView()));
        return super.mix();
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_BASKETBALL_TODAY_Mix;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        if (item.getType().equals("Early")) {
            getBaseView().switchState(new SoccerEarlyMixState(getBaseView()));
        } else if (item.getType().equals("Today")) {
            getBaseView().switchState(this);
        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(1, (R.string.Today), "Today", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Basketball));
    }
}
