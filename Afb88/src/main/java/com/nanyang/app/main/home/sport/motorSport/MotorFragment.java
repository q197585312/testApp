package com.nanyang.app.main.home.sport.motorSport;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class MotorFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "16";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Motor_Sports));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new MotorRunningState(this));
                break;
            case "Today":
                switchState(new MotorTodayState(this));
                break;
            case "Early":
                switchState(new MotorEarlyState(this));
                break;
            default:
                switchState(new MotorTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Motor_Sports);
    }


}
