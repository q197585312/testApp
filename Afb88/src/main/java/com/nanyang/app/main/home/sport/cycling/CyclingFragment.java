package com.nanyang.app.main.home.sport.cycling;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class CyclingFragment extends BaseSportFragment {

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
                switchState(new CyclingRunningState(this));
                break;
            case "Today":
                switchState(new CyclingTodayState(this));
                break;
            case "Early":
                switchState(new CyclingEarlyState(this));
                break;
            case "OutRight":
                switchState(new CyclingOutRightState(this));
                break;
            default:
                switchState(new CyclingTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Cycling);
    }

}
