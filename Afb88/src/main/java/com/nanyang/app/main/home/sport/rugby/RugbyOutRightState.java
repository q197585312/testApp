package com.nanyang.app.main.home.sport.rugby;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class RugbyOutRightState extends OutRightState {
    public RugbyOutRightState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Rugby);
    }

}
