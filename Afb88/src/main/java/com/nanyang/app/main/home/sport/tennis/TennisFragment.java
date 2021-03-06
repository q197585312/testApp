package com.nanyang.app.main.home.sport.tennis;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;


public class TennisFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Tennis));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new TennisRunningState(this));
                break;
            case "Today":
                switchState(new TennisTodayState(this));
                break;
            case "Early":
                switchState(new TennisEarlyState(this));
                break;
            case "OutRight":
                switchState(new TennisOutRightState(this));
                break;
            default:
                switchState(new TennisTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Tennis);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
