package com.nanyang.app.main.home.sport.baseball.USFootball;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;


public class BaseballFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Baseball));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new BaseballRunningState(this));
                break;
            case "Today":
                switchState(new BaseballTodayState(this));
                break;
            case "Early":
                switchState(new BaseballEarlyState(this));
                break;
            case "OutRight":
                switchState(new BaseballOutRightState(this));
                break;
            default:
                switchState(new BaseballTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Baseball);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {

    }
}
