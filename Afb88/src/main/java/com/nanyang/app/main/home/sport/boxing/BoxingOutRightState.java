package com.nanyang.app.main.home.sport.boxing;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OutRightState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/13.
 */

public class BoxingOutRightState extends OutRightState {
    public BoxingOutRightState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    protected String getSportName() {
        return getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Boxing);
    }




}
