package com.nanyang.app.main.home.sport.baseball;

import android.content.Context;
import android.view.View;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.basketball.BasketballCommonInfo;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/6/6.
 */

public class BaseballAdapter extends BallAdapterHelper<BasketballCommonInfo> {
    public BaseballAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BasketballCommonInfo item) {
        super.onConvert(helper, position, item);

        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
        View vp = helper.getView(R.id.module_center_sl);
        vp.getLayoutParams().width = DeviceUtils.dip2px(context, 210);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
     /*   scrollChild(sl.getChildAt(0), false, item, item.getIsHomeGive(), item.getHasHdp(), item.getHdp(), item.getHasOU(), item.getOU(), item.getIsHdpNew(), item.getIsOUNew(), item.getUnderOdds(), item.getOverOdds(), item.getHomeHdpOdds(), item.getAwayHdpOdds(), "home", "away", "over", "under",
                true, true, true,    item.getHasX12(), item.getIsX12New(), item.getX12_1Odds(), item.getX12_2Odds()
                , "1", "2"
        );
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH()
                , "home", "away", "over", "under",
                true, true, true,
                item.getHasOE(), item.getIsOENew(), item.getOddOdds(), item.getEvenOdds(), "odd", "even"
        );*/
        sl.getChildAt(0).setVisibility(View.VISIBLE);
        ViewHolder holder = new ViewHolder(sl.getChildAt(0));
        setUpDownOdds(true,item,false,item.getIsX12New(),item.getHasX12(),"",holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv,holder.viewpagerMatchVisitHdpoddsTv
                ,item.getX12_1Odds(),item.getX12_2Odds(),"1","2");

        setUpDownOdds(true,item,false,item.getIsHdpNew(),item.getHasHdp(),item.getHdp(),holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv,holder.viewpagerMatchUnderoddsTv
                ,item.getHomeHdpOdds(),item.getAwayHdpOdds(),"home","away");
        setUpDownOdds(true,item,false,item.getIsOUNew(),item.getHasOU(),item.getOU(),holder.viewpagerOddLabelTv, holder.viewpagerEvenLabelTv, holder.viewpagerMatchOddTv,holder.viewpagerMatchEvenTv
                ,item.getOverOdds(),item.getUnderOdds(),"over","under");

        sl.getChildAt(1).setVisibility(View.VISIBLE);
        ViewHolder ho = new ViewHolder(sl.getChildAt(1));

        setUpDownOdds(true,item,false,item.getIsOENew(),item.getHasOE(),"",ho.viewpagerMatchHomeHdpTv, ho.viewpagerMatchVisitHdpTv, ho.viewpagerMatchHomeHdpoddsTv,ho.viewpagerMatchVisitHdpoddsTv
                ,item.getOddOdds(),item.getEvenOdds(),"odd","even");

        setUpDownOdds(true,item,true,item.getIsHdpNew_FH(),item.getHasHdp_FH(),item.getHdp_FH(),ho.viewpagerMatchOuTv, ho.viewpagerMatchOu2Tv, ho.viewpagerMatchOveroddsTv,ho.viewpagerMatchUnderoddsTv
                ,item.getHomeHdpOdds_FH(),item.getAwayHdpOdds_FH(),"home","away");
        setUpDownOdds(true,item,true,item.getIsOUNew_FH(),item.getHasOU_FH(),item.getOU_FH(),ho.viewpagerOddLabelTv, ho.viewpagerEvenLabelTv, ho.viewpagerMatchOddTv,ho.viewpagerMatchEvenTv
                ,item.getOverOdds_FH(),item.getUnderOdds_FH(),"over","under");
    }
}
