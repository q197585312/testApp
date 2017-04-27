package com.nanyang.app.main.home.sport.USFootball;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.e_sport.ESportEarlyState;
import com.nanyang.app.main.home.sport.e_sport.ESportOutRightState;
import com.nanyang.app.main.home.sport.e_sport.ESportRunningState;
import com.nanyang.app.main.home.sport.e_sport.ESportTodayState;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;


public class USFootballFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.US_Football));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new ESportRunningState(this));
                break;
            case "Today":
                switchState(new ESportTodayState(this));
                break;
            case "Early":
                switchState(new ESportEarlyState(this));
                break;
            case "OutRight":
                switchState(new ESportOutRightState(this));
                break;
            default:
                switchState(new ESportTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.E_Sport);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
