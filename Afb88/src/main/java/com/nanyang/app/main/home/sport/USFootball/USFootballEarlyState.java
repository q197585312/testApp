package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.AppConstant;
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
    protected String getRefreshUrl() {
        return AppConstant.getInstance().URL_US_FOOTBALL_EARLY;
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
//http://ws.afb1188.com:8888/fnOddsGen?wst=wsSocAllGen&g=12&ot=r&wd=7&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=1&tfDate=2018-11-30&LangCol=&accType=HK&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&tp=0&fh=1&um=1|1670|18942|18941|18944&LID=&ov=0&mt=0&FAV=&SL=


    @Override
    public boolean mix() {
        getBaseView().switchState(new USFootballEarlyMixState(getBaseView()));
        return true;
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
