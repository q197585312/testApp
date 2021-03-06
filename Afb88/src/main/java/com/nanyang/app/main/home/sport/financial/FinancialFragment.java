package com.nanyang.app.main.home.sport.financial;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;


public class FinancialFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);

        setTitle(getString(R.string.Financial));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new FinancialRunningState(this));
                break;
            case "Today":
                switchState(new FinancialTodayState(this));
                break;
            case "Early":
                switchState(new FinancialEarlyState(this));
                break;

            default:
                switchState(new FinancialTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Financial);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
