package com.nanyang.app.main.home.sport.cycling;

import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.financial.OtherSingleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
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
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherSingleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity()){
            @Override
            public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
                super.onConvert(helper, position, item);
                ScrollLayout sl = helper.getView(R.id.module_center_sl);
                sl.getChildAt(0).setVisibility(View.VISIBLE);
                ViewHolder holder = new ViewHolder(sl.getChildAt(0));
                setUpDownOdds(true,item,false,item.getIsX12New(),item.getHasX12(),"",holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv,holder.viewpagerMatchVisitHdpoddsTv
                        ,item.getX12_1Odds(),item.getX12_2Odds(),"1","2");

                setUpDownOdds(true,item,false,item.getIsHdpNew(),item.getHasHdp(),item.getHdp(),holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv,holder.viewpagerMatchUnderoddsTv
                        ,item.getHOdds(),item.getAOdds(),"home","away");

            }
        };
    }

    public CyclingState(SportContract.View baseView) {
        super(baseView);
    }
    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new CyclingBetHelper(getBaseView());
    }
    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.remove(0);
        lists.get(0).set(1,getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HANDICAP));
        return lists;
    }

}
