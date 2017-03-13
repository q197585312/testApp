package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.main.home.sportInterface.SoccerTodayState;
import com.nanyang.app.main.home.sportInterface.SportContract2;
import com.nanyang.app.main.home.sportInterface.SportPresenter2;

/**
 * Created by Administrator on 2017/3/13.
 */

public class SoccerPresenter extends SportPresenter2 {

    public SoccerPresenter(SportContract2.View view) {
        super(view);
        setStateHelper(new SoccerTodayState(view));
    }

}
