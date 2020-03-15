package com.nanyang.app.main.home.sport.europe;

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

    BetPop pop;

    @Override
    protected void createBetPop(List<AfbClickBetBean> bean, View v) {
        super.createBetPop(bean,v);
        pop.setrTMatchInfo(item);

    }
}
