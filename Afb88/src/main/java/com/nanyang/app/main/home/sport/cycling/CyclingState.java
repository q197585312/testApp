package com.nanyang.app.main.home.sport.cycling;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballMixInfo;
import com.nanyang.app.main.home.sport.financial.OtherSingleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class CyclingState extends TennisState{
    @Override
    public IAdapterHelper<BasketballMixInfo> onSetAdapterHelper() {
        return new OtherSingleAdapterHelper(getBaseView().getContextActivity()){
            @Override
            public void onConvert(MyRecyclerViewHolder helper, int position, BasketballMixInfo item) {
                super.onConvert(helper, position, item);
                ScrollLayout sl = helper.getView(R.id.module_center_sl);
                scrollChild(sl.getChildAt(0), false, item, item.getIsHomeGive(), item.getHasX12(), "", item.getHasOE(), "", item.getIsX12New(), "", "", "", item.getX12_1Odds(), item.getX12_2Odds(), "1", "2", "", ""
                        , true, false, true, item.getHasOE(), item.getIsOENew(), item.getOddOdds(), item.getEvenOdds(), "odd", "even");
                getBaseRecyclerAdapter().getItem(position).setIsX12New("0");
                getBaseRecyclerAdapter().getItem(position).setIsOENew("0");
            }
        };
    }

    public CyclingState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new CyclingBetHelper(getBaseView());
    }
    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.remove(0);
        return lists;
    }

}
