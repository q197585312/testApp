package com.nanyang.app.main.home.sport.allRunning;

import com.nanyang.app.main.home.sport.europe.BallState;
import com.nanyang.app.main.home.sport.main.SportContract;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class AllCommonState extends BallState {
    public AllCommonState(SportContract.View baseView) {
        super(baseView);
    }

    public void onSwitch() {

    }


}
