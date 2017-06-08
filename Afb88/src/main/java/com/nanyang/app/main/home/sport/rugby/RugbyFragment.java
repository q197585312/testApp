package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class RugbyFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Rugby));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new RugbyRunningState(this));
                break;
            case "Today":
                switchState(new RugbyTodayState(this));
                break;
            case "Early":
                switchState(new RugbyEarlyState(this));
                break;
            case "OutRight":
                switchState(new RugbyOutRightState(this));
                break;
            default:
                switchState(new RugbyTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Rugby);
    }



}
