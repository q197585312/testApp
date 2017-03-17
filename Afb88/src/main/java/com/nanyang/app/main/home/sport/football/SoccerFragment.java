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


public class SoccerFragment extends BaseSportFragment<SoccerPresenter> {

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
    protected SoccerPresenter onCreatePresenter() {
        return new SoccerPresenter(this);
    }

    @Override
    public String getTitle() {
        return getString(R.string.Soccer);
    }


    @Override
    public void clickAdd(View v, SportInfo item, String type) {
        Bundle b = new Bundle();
        b.putBoolean(AppConstant.KEY_BOOLEAN, type.equals("mix"));
        b.putString(AppConstant.KEY_STRING, presenter.getStateHelper().getStateType().getType());
        b.putSerializable(AppConstant.KEY_DATA, item);
        skipAct(VsActivity.class, b);
    }


}
