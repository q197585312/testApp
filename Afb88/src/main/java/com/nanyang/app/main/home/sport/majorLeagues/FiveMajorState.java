package com.nanyang.app.main.home.sport.majorLeagues;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.main.home.sport.europe.EuropeState;
import com.nanyang.app.main.home.sport.football.SoccerCommonAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.HashMap;

/**
 * Created by ASUS on 2019/3/26.
 */

public abstract class FiveMajorState extends EuropeState {
    HashMap<String, FiveMajorState> majorStateHashMap = new HashMap<>();

    public FiveMajorState(SportContract.View baseView) {
        super(baseView);

    }


    @Override
    protected void onTypeClick(MenuItemInfo item, int position) {
        getBaseView().switchState(majorStateHashMap.get(item.getType()));
    }

    @Override
    public SportAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new SoccerCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    @Override
    protected void onChildConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {

    }



    @Override
    public boolean mix() {
        return false;
    }
}
