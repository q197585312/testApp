package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class SoccerRunningBetHelper extends SoccerCommonBetHelper {
    public SoccerRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getOddsUrl(SoccerCommonInfo item, String type, boolean isHf, String odds) {
        return super.getOddsUrl(item, type, isHf, odds)+"&isRun=true";
    }
}
