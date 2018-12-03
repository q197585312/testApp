package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/6/9.
 */

public class EuropeRunningBetHelper extends EuropeBetHelper {
    public EuropeRunningBetHelper(BetView baseView) {
        super(baseView);
    }

/*    @Override
    protected void createBetPop(BettingPromptBean bean, boolean isHf, TextView v, BallInfo item) {
        BetPop pop = new BetPop(baseView.getContextActivity(), v);
        pop.setBetData(bean, this);
        pop.setIsHf(isHf);
        pop.setrTMatchInfo(item);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }*/
}
