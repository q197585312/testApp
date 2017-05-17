package com.nanyang.app.main.home.sport.europe;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;


public class EuropeFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Europe_View));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new EuropeRunningState(this));
                break;
            case "Today":
                switchState(new EuropeTodayState(this));
                break;
            case "Early":
                switchState(new EuropeEarlyState(this));
                break;
            default:
                switchState(new EuropeTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Europe_View);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
