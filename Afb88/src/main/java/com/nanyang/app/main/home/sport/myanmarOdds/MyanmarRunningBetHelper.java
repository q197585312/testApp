package com.nanyang.app.main.home.sport.myanmarOdds;

import android.view.View;

import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

import java.util.List;

/**
 * Created by Administrator on 2018/12/7.
 */

public class MyanmarRunningBetHelper extends MyanmarBetHelper {
    public MyanmarRunningBetHelper(BetView baseView) {
        super(baseView);
    }



    @Override
    protected void createBetPop(List<AfbClickBetBean> bean, View v) {

        super.createBetPop(bean,v);
        betPop.setrTMatchInfo(item);

    }
}
