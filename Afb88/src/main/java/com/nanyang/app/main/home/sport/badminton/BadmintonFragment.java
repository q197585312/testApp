package com.nanyang.app.main.home.sport.badminton;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class BadmintonFragment extends BaseSportFragment {

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
                switchState(new BadmintonRunningState(this));
                break;
            case "Today":
                switchState(new BadmintonTodayState(this));
                break;
            case "Early":
                switchState(new BadmintonEarlyState(this));
                break;
            case "OutRight":
                switchState(new BadmintonOutRightState(this));
                break;
            default:
                switchState(new BadmintonTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Badminton);
    }

}
