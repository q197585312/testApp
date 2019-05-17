package com.nanyang.app.main.home.sport.winterSport;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class WinterSportFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "30";
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
                switchState(new WinterSportRunningState(this));
                break;
            case "Today":
                switchState(new WinterSportTodayState(this));
                break;
            case "Early":
                switchState(new WinterSportEarlyState(this));
                break;
            default:
                switchState(new WinterSportTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.WinterSport);
    }

}
