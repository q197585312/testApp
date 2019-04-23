package com.nanyang.app.main.home.sport.cricket;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class CricketFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "23";
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
                switchState(new CricketRunningState(this));
                break;
            case "Today":
                switchState(new CricketTodayState(this));
                break;
            case "Early":
                switchState(new CricketEarlyState(this));
                break;
            case "OutRight":
                switchState(new CricketOutRightState(this));
                break;
            default:
                switchState(new CricketTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Cricket);
    }

}
