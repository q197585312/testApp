package com.nanyang.app.main.home.sport.iceHockey;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class IceHockeyState extends TennisState{

    public IceHockeyState(SportContract.View baseView) {
        super(baseView);
    }
   @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new IceHockeyBetHelper(getBaseView());
    }

    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }
    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).set(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.TO_WIN));
        lists.get(0).set(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HANDICAP));
        lists.get(1).set(0,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.O_U));
        lists.get(1).set(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.ODD_EVEN));
        return lists;
    }
}
