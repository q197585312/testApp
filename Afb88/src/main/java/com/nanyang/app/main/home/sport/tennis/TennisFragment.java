package com.nanyang.app.main.home.sport.tennis;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;



public class TennisFragment extends BaseSportFragment<TennisPresenter> {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
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
        }
        setTitle(getString(R.string.football));
    }


    @Override
    protected TennisPresenter onCreatePresenter() {
        return new TennisPresenter(this);
    }

    @Override
    public String getTitle() {
        return getString(R.string.Tennis);
    }


    @Override
    public void clickAdd(View v, SportInfo item, String type) {
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, item);
        b.putSerializable(AppConstant.KEY_DATA2, presenter.getStateHelper().getStateType());
        skipAct(VsActivity.class, b);
    }


}
