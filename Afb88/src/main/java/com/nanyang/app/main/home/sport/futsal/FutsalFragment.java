package com.nanyang.app.main.home.sport.futsal;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class FutsalFragment extends BaseSportFragment {

    @Override
    protected String getBallDbid() {
        return "19";
    }

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Futsal));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new FutsalRunningState(this));
                break;
            case "Today":
                switchState(new FutsalTodayState(this));
                break;
            case "Early":
                switchState(new FutsalEarlyState(this));
                break;
            default:
                switchState(new FutsalTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Futsal);
    }


}
