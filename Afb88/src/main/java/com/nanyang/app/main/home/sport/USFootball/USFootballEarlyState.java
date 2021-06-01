package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballEarlyState;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class USFootballEarlyState extends BasketballEarlyState {
    public USFootballEarlyState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, (R.string.Early), "Early", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.US_Football));
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {

        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new USFootballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new USFootballRunningState(getBaseView()));
                break;
            case "OutRight":
                getBaseView().switchState(new USFootballOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BallBetHelper(getBaseView()) {
            @Override
            public String getBallG() {
                return "12";
            }
        };
    }
}
