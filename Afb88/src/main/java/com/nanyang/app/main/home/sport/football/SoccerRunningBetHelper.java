package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.BetView;

/**
 * Created by Administrator on 2017/3/19 0019.
 */

public class SoccerRunningBetHelper extends SoccerCommonBetHelper {
    public SoccerRunningBetHelper(BetView baseView) {
        super(baseView);
    }

    @Override
    protected String getOddsUrl(SoccerCommonInfo item, String oid,String oidFh,String type, boolean isHf, String odds,String params) {
        return super.getOddsUrl(item,item.getSocOddsId(),item.getSocOddsId_FH(), type, isHf, odds, params)+"&isRun=true";
    }
}
