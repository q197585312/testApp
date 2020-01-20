package com.nanyang.app.main.home.sport.myanmarOdds;

import android.view.Gravity;
import android.view.View;

import com.nanyang.app.main.home.sport.dialog.BetPop;
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

    BetPop pop;

    @Override
    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        if (pop == null) {
            pop = new BetPop(baseView.getIBaseContext().getBaseActivity(), v);
        }
        pop.setBetData(bean, this);
        pop.setrTMatchInfo(item);
        if (!pop.isShowing()) {
//            pop.showPopupCenterWindow();
            baseView.onPopupWindowCreated(pop, Gravity.CENTER);
        }

    }
}
