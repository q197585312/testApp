package com.nanyang.app.main.home.sport.squash;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class SquashFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "31";
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
                switchState(new SquashRunningState(this));
                break;
            case "Today":
                switchState(new SquashTodayState(this));
                break;
            case "Early":
                switchState(new SquashEarlyState(this));
                break;
            default:
                switchState(new SquashTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Squash);
    }

}
