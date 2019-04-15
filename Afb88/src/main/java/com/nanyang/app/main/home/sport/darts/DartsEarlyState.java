package com.nanyang.app.main.home.sport.darts;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballEarlyState;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class DartsEarlyState extends BasketballEarlyState {
    public DartsEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early",getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Darts));
    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_DARTS_EARLY;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new DartsTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new DartsRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new DartsOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public boolean mix() {
        getBaseView().switchState(new DartsEarlyMixState(getBaseView()));
        return true;
    }
    @Override
    public IBetHelper onSetBetHelper() {
        return new DartsBetHelper(getBaseView());
    }
}
