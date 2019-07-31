package com.nanyang.app.main.home.sport.cycling;

import com.nanyang.app.main.home.sport.main.OtherDoubleAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.tennis.TennisState;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class CyclingState extends TennisState {
    /* @Override
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
 */
    @Override
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        return new OtherDoubleAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }

    public CyclingState(SportContract.View baseView) {
        super(baseView);
    }

    @Override
    public IBetHelper<BallInfo> onSetBetHelper() {
        return new CyclingBetHelper(getBaseView());
    }


}
