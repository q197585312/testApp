package com.nanyang.app.main.home.sport.basketball;

import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/15.
 */

public class BasketballCommonBetHelper extends BallBetHelper<BallInfo, BetView> {


    public BasketballCommonBetHelper(BetView baseView) {
        super(baseView);
    }


    @Override
    protected String getBallG() {
        return "9";
    }


    //    http://a0096f.panda88.org/_Bet/JRecPanel.aspx?g=9&b=home&oId=12264737&odds=9.6
    // http://www.afb1188.com/Bet/hBetOdds.ashx?BTMD=S&coupon=0&BETID=s|2|1|438838||&_=1543203326861

}
