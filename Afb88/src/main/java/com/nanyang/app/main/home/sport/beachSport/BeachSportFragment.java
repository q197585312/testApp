package com.nanyang.app.main.home.sport.beachSport;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class BeachSportFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "27";
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
                switchState(new BeachSportRunningState(this));
                break;
            case "Today":
                switchState(new BeachSportTodayState(this));
                break;
            case "Early":
                switchState(new BeachSportEarlyState(this));
                break;
            default:
                switchState(new BeachSportTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Beach_Soccer);
    }

}
