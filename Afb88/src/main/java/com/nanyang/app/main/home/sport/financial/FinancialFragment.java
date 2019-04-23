package com.nanyang.app.main.home.sport.financial;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class FinancialFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "4";
    }

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





}
