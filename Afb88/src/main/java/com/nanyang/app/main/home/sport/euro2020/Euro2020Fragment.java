package com.nanyang.app.main.home.sport.euro2020;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class Euro2020Fragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "1";
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
                switchState(new Euro2020RunningState(this));
                break;
            case "Today":
                switchState(new Euro2020TodayState(this));
                break;
            case "Early":
                switchState(new Euro2020EarlyState(this));
                break;
            default:
                switchState(new Euro2020TodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Euro_2020);
    }


}
