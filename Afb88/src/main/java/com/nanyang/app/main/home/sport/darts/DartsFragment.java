package com.nanyang.app.main.home.sport.darts;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class DartsFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "13";
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
                switchState(new DartsRunningState(this));
                break;
            case "Today":
                switchState(new DartsTodayState(this));
                break;
            case "Early":
                switchState(new DartsEarlyState(this));
                break;
            case "OutRight":
                switchState(new DartsOutRightState(this));
                break;
            default:
                switchState(new DartsTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Darts);
    }

}
