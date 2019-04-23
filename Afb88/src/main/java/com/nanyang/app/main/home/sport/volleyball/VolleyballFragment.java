package com.nanyang.app.main.home.sport.volleyball;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class VolleyballFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "24";
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
                switchState(new VolleyballRunningState(this));
                break;
            case "Today":
                switchState(new VolleyballTodayState(this));
                break;
            case "Early":
                switchState(new VolleyballEarlyState(this));
                break;
            case "OutRight":
                switchState(new VolleyballOutRightState(this));
                break;
            default:
                switchState(new VolleyballTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Volleyball);
    }

}
