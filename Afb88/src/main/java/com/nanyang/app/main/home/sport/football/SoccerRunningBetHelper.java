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

    /*  @Override
    protected String getOddsUrl(SoccerCommonInfo item, String type, boolean isHf, String odds, String params) {
        return super.getOddsUrl(item, type, isHf, odds, params) + "&isRun=true";
    }

    @Override
    protected String getOddsUrl(String old, String type, String odds, String params) {
        return super.getOddsUrl(old, type, odds, params) + "&isRun=true";
    }
    */
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
