package com.nanyang.app.main.home.sport.USFootball;

import android.support.v4.content.ContextCompat;

import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballRunningState;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/13.
 */

public class USFootballRunningState extends BasketballRunningState {
    public USFootballRunningState(SportContract.View baseView) {
        super(baseView);
    }


    @Override
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_US_FOOTBALL_RUNNING;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new USFootballEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new USFootballTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(this);
                break;
            case "OutRight":
                getBaseView().switchState(new USFootballOutRightState(getBaseView()));
                break;

        }
    }

    @Override
    public MenuItemInfo getStateType() {
        return new MenuItemInfo<String>(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", getBaseView().getIBaseContext().getBaseActivity().getString(R.string.US_Football));
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new BallBetHelper(getBaseView()) {
            @Override
            protected String getBallG() {
                return "12";
            }
        };
    }

    @Override
    public int getTitleContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content1);
    }

    @Override
    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.green_content2);
    }

}
