package com.nanyang.app.main.home.sport.majorLeagues;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.main.home.sport.football.SoccerCommonAdapterHelper;
import com.nanyang.app.main.home.sport.football.SoccerCommonState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;

import java.util.HashMap;

/**
 * Created by ASUS on 2019/3/26.
 */

public abstract class FiveMajorState extends SoccerCommonState {
    HashMap<String, FiveMajorState> majorStateHashMap = new HashMap<>();


    public FiveMajorState(SportContract.View baseView) {
        super(baseView);
//        majorStateHashMap.put()
    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(new FiveMajorEarlyState(getBaseView()));
                break;
            case "Today":
                getBaseView().switchState(new FiveMajorTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new FiveMajorRunningState(getBaseView()));
                break;
        }
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new SoccerCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }


    @Override
    protected SoccerCommonAdapterHelper onSetCommonAdapterHelper() {
        return new SoccerCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    public boolean mix() {
        return false;
    }
}
