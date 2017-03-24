package com.nanyang.app.main.home.sport.basketball;

import android.os.Bundle;
import android.view.View;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;


public class BasketballFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switch (type) {
            case "Running":
                switchState(new BasketballRunningState(this));
                break;
            case "Today":
                switchState(new BasketballTodayState(this));
                break;
            case "Early":
                switchState(new BasketballEarlyState(this));
                break;
            case "OutRight":
                switchState(new BasketballOutRightState(this));
                break;
            default:
                switchState(new BasketballTodayState(this));
                break;
        }
        setTitle(getString(R.string.Basketball));
    }




    @Override
    public String getTitle() {
        return getString(R.string.Basketball);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, item);
        b.putSerializable(AppConstant.KEY_DATA2, presenter.getStateHelper().getStateType());
        skipAct(VsActivity.class, b);
    }


}
