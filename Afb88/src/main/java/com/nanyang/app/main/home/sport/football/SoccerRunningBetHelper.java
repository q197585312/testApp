package com.nanyang.app.main.home.sport.football;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

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

    protected void createBetPop(AfbClickBetBean bean, View v) {
        BetPop pop = new BetPop(baseView.getContextActivity(), v);
        pop.setBetData(bean, this);
        pop.setrTMatchInfo(item);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }
}
