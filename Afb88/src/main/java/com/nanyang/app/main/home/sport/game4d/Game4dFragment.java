package com.nanyang.app.main.home.sport.game4d;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseSportFragment;


public class Game4dFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switch (type) {
            case "Running":
                switchState(new Game4dRunningState(this));
                break;
            case "Today":
                switchState(new Game4dTodayState(this));
                break;
            case "Early":
                switchState(new Game4dEarlyState(this));
                break;

            default:
                switchState(new Game4dTodayState(this));
                break;
        }
        setTitle(getString(R.string.Specials_4D));
    }


    @Override
    public String getTitle() {
        return getString(R.string.Specials_4D);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
