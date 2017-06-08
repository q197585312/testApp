package com.nanyang.app.main.home.sport.poll;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;


public class PoolFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switchType(type);
        setTitle(getString(R.string.Pool));
    }

    @Override
    public void switchType(String type) {
        switch (type) {
            case "Running":
                switchState(new PoolRunningState(this));
                break;
            case "Today":
                switchState(new PoolTodayState(this));
                break;
            case "Early":
                switchState(new PoolEarlyState(this));
                break;
            case "OutRight":
                switchState(new PoolOutRightState(this));
                break;
            default:
                switchState(new PoolTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Pool);
    }

}
