package com.nanyang.app.main.home.sport.speedWay;

import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class SpeedWayFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "28";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new SpeedWayRunningState(this));
                break;
            case "Today":
                switchState(new SpeedWayTodayState(this));
                break;
            case "Early":
                switchState(new SpeedWayEarlyState(this));
                break;
            default:
                switchState(new SpeedWayTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return "SPEEDWAY";
    }

}
