package com.nanyang.app.main.home.sport.europe;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sportInterface.BetView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/9.
 */

public class EuropeRunningBetHelper extends EuropeBetHelper {
    public EuropeRunningBetHelper(BetView baseView) {
        super(baseView);
    }


    @Override
    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        BetPop pop = new BetPop(baseView.getIBaseContext().getBaseActivity(), v);
        pop.setBetData(bean, this);
        pop.setrTMatchInfo(item);
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }
}
