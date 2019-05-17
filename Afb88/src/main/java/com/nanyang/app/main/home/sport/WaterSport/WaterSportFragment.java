package com.nanyang.app.main.home.sport.WaterSport;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class WaterSportFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "21";
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
                switchState(new WaterSportRunningState(this));
                break;
            case "Today":
                switchState(new WaterSportTodayState(this));
                break;
            case "Early":
                switchState(new WaterSportEarlyState(this));
                break;
            default:
                switchState(new WaterSportTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Water_Polo);
    }

}
