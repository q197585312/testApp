package com.nanyang.app.main.home.sport.athletics;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class AthleticsFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "29";
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
                switchState(new AthleticsRunningState(this));
                break;
            case "Today":
                switchState(new AthleticsTodayState(this));
                break;
            case "Early":
                switchState(new AthleticsEarlyState(this));
                break;
            default:
                switchState(new AthleticsTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Athletics);
    }

}
