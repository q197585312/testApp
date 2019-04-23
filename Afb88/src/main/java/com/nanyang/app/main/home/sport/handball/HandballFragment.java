package com.nanyang.app.main.home.sport.handball;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class HandballFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "25";
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
                switchState(new HandballRunningState(this));
                break;
            case "Today":
                switchState(new HandballTodayState(this));
                break;
            case "Early":
                switchState(new HandballEarlyState(this));
                break;
            case "OutRight":
                switchState(new HandballOutRightState(this));
                break;
            default:
                switchState(new HandballTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Handball);
    }

}
