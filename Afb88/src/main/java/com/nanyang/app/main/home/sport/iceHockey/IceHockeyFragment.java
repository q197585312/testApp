package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class IceHockeyFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "10";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.IceHockey));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new IceHockeyRunningState(this));
                break;
            case "Today":
                switchState(new IceHockeyTodayState(this));
                break;
            case "Early":
                switchState(new IceHockeyEarlyState(this));
                break;
            case "OutRight":
                switchState(new IceHockeyOutRightState(this));
                break;
            default:
                switchState(new IceHockeyTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.IceHockey);
    }



}
