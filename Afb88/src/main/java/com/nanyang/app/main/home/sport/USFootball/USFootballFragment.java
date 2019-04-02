package com.nanyang.app.main.home.sport.USFootball;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class USFootballFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.US_Football));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new USFootballRunningState(this));
                break;
            case "Today":
                switchState(new USFootballTodayState(this));
                break;
            case "Early":
                switchState(new USFootballEarlyState(this));
                break;
            case "OutRight":
                switchState(new USFootballOutRightState(this));
                break;
            default:
                switchState(new USFootballTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.US_Football);
    }



}
