package com.nanyang.app.main.home.sport.superCombo;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class SuperComboFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "";
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
                switchState(new SuperComboRunningState(this));
                break;
            case "Today":
                switchState(new SuperComboTodayState(this));
                break;
            case "Early":
                switchState(new SuperComboEarlyState(this));
                break;
            default:
                switchState(new SuperComboTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.SuperCombo);
    }



}
