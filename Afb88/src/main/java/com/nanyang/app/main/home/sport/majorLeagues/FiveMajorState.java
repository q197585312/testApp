package com.nanyang.app.main.home.sport.majorLeagues;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.main.home.sport.e_sport.ESportRunningState;
import com.nanyang.app.main.home.sport.e_sport.ESportTodayState;
import com.nanyang.app.main.home.sport.europe.EuropeState;
import com.nanyang.app.main.home.sport.football.SoccerCommonAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by ASUS on 2019/3/26.
 */

public class FiveMajorState extends EuropeState {

    public FiveMajorState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getRefreshUrl() {
        return null;
    }

    @Override
    protected void onTypeClick(MenuItemInfo item) {
        switch (item.getType()) {
            case "Early":
                getBaseView().switchState(this);
                break;
            case "Today":
                getBaseView().switchState(new ESportTodayState(getBaseView()));
                break;
            case "Running":
                getBaseView().switchState(new ESportRunningState(getBaseView()));
                break;

        }
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new SoccerCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity())
    }

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {

    }

    @Override
    public MenuItemInfo getStateType() {
        return null;
    }

    @Override
    public boolean mix() {
        return false;
    }
}
