package com.nanyang.app.main.home.sport.football;

import android.view.View;

import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

import java.util.List;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class SoccerRunningBetHelper extends SoccerCommonBetHelper {
    public SoccerRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    public String getBallG() {
        return "1";
    }


    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        super.createBetPop(bean, v);
        betPop.setrTMatchInfo(item);

    }
}
