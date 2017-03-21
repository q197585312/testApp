package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;
import com.nanyang.app.main.home.sportInterface.SoccerEarlyState;
import com.nanyang.app.main.home.sportInterface.SoccerOutRightState;
import com.nanyang.app.main.home.sportInterface.SoccerRunningState;
import com.nanyang.app.main.home.sportInterface.SoccerTodayState;


public class SoccerFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switch (type) {
            case "Running":
                switchState(new SoccerRunningState(this));
                break;
            case "Today":
                switchState(new SoccerTodayState(this));
                break;
            case "Early":
                switchState(new SoccerEarlyState(this));
                break;
            case "OutRight":
                switchState(new SoccerOutRightState(this));
                break;
        }
        setTitle(getString(R.string.football));
    }


    @Override
    public String getTitle() {
        return getString(R.string.Soccer);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
        Bundle b = new Bundle();
        b.putBoolean(AppConstant.KEY_BOOLEAN, type.equals("mix"));

        b.putSerializable(AppConstant.KEY_DATA, item);
        b.putSerializable(AppConstant.KEY_DATA2, presenter.getStateHelper().getStateType());
        skipAct(VsActivity.class, b);
    }


}
