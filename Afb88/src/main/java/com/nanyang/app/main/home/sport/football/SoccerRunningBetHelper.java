package com.nanyang.app.main.home.sport.football;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.main.home.sport.dialog.BetPop;
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
    protected String getBallG() {
        return "1";
    }

    BetPop pop;

    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        if (pop == null) {
            pop = new BetPop(baseView.getIBaseContext().getBaseActivity(), v);
        }
        pop.setBetData(bean, this);
        pop.setIsRunning(true);
        pop.setrTMatchInfo(item);
        if (!pop.isShowing()) {
            baseView.onPopupWindowCreated(pop, Gravity.CENTER);
        }

    }
}
