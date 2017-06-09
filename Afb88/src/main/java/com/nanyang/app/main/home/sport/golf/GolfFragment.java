package com.nanyang.app.main.home.sport.golf;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class GolfFragment extends BaseSportFragment {

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
                switchState(new GolfRunningState(this));
                break;
            case "Today":
                switchState(new GolfTodayState(this));
                break;
            case "Early":
                switchState(new GolfEarlyState(this));
                break;

            default:
                switchState(new GolfTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Golf);
    }


}
