package com.nanyang.app.main.home.sport.myanmarOdds;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2018/12/7.
 */

public class MyanmarRunningBetHelper extends MyanmarBetHelper {
    public MyanmarRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected void createBetPop(AfbClickBetBean bean, View v) {
        BetPop pop = new BetPop(baseView.getIBaseContext().getBaseActivity(), v);
        pop.setBetData(bean, this);
        pop.setrTMatchInfo(item);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }
}
