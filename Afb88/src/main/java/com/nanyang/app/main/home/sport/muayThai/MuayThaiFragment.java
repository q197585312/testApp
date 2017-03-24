package com.nanyang.app.main.home.sport.muayThai;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;


public class MuayThaiFragment extends BaseSportFragment {

    @Override
    public void initData() {
        super.initData();
        String type = ((SportActivity) getActivity()).getType();
        switch (type) {
            case "Running":
                switchState(new MuayThaiRunningState(this));
                break;
            case "Today":
                switchState(new MuayThaiTodayState(this));
                break;
            case "Early":
                switchState(new MuayThaiEarlyState(this));
                break;

            default:
                switchState(new MuayThaiTodayState(this));
                break;
        }
    }


    @Override
    public String getTitle() {
        return getString(R.string.Muay_Thai);
    }


    @Override
    public void clickItemAdd(View v, SportInfo item, String type) {
    }


}
