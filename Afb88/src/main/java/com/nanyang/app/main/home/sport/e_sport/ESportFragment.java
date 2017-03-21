package com.nanyang.app.main.home.sport.e_sport;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;


public class ESportFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
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
        setTitle(getString(R.string.E_Sport));
    }


    @Override
    public String getTitle() {
        return getString(R.string.E_Sport);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
