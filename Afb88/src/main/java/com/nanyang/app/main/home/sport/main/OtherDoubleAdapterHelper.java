package com.nanyang.app.main.home.sport.main;

import android.content.Context;

import com.nanyang.app.main.home.sport.model.BallInfo;

/**
 * Created by Administrator on 2017/3/14.
 */

/**
 * {
 * "SocOddsId": 12219288,
 * "Home": "AS摩纳哥",
 * "Away": "曼城",
 * "IsHomeGive": false,
 * "IsBetHome": false,
 * "ParFullTimeId": "12219287",
 * "TransType": "HDP",
 * "IsOddsChange": false,
 * "ParUrl": "http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19",
 * "BetHdp": "0",
 * "BetOu": "0",
 * "BetOdds": "1.90",
 * "Test2": "testing2"
 * }
 */

public class OtherDoubleAdapterHelper extends BallAdapterHelper<BallInfo> {


    public OtherDoubleAdapterHelper(Context context) {
        super(context);

    }

   /* @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        sl.getChildAt(0).setVisibility(View.VISIBLE);
        ViewHolder holder = new ViewHolder(sl.getChildAt(0));
        setUpDownOdds(true, item, false, item.getIsX12New(), item.getHasX12(), "", holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv, holder.viewpagerMatchVisitHdpoddsTv
                , item.getX12_1Odds(), item.getX12_2Odds(), "1", "2");

        setUpDownOdds(true, item, false, item.getIsHdpNew(), item.getHasHdp(), item.getHdp(), holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv
                , item.getHOdds(), item.getAOdds(), "home", "away");

        sl.getChildAt(1).setVisibility(View.VISIBLE);
        ViewHolder holder2 = new ViewHolder(sl.getChildAt(1));
        setUpDownOdds(true, item, false, item.getIsOUNew(), item.getHasOU(), item.getOU(), holder2.viewpagerMatchHomeHdpTv, holder2.viewpagerMatchVisitHdpTv, holder2.viewpagerMatchHomeHdpoddsTv, holder2.viewpagerMatchVisitHdpoddsTv
                , item.getOOdds(), item.getUOdds(), "over", "under");
        setUpDownOdds(true, item, false, item.getIsOENew(), item.getHasOE(), "", holder2.viewpagerMatchOuTv, holder2.viewpagerMatchOu2Tv, holder2.viewpagerMatchOveroddsTv, holder2.viewpagerMatchUnderoddsTv
                , (item.getOEOdds() != null && Double.valueOf(item.getOEOdds()) >= 1 || Double.valueOf(item.getOEOdds()) <= -1) ? item.getOddOdds() : "",
                (item.getOEOdds() != null && Double.valueOf(item.getOEOdds()) >= 1 || Double.valueOf(item.getOEOdds()) <= -1) ? item.getEvenOdds() : "", "odd", "even");
        *//*scrollChild(sl.getChildAt(1), false, item, item.getIsHomeGive(), item.getHasX12(), "", item.getHasOE(), "", item.getIsX12New(), "", "", "", item.getX12_1Odds(), item.getX12_2Odds(), "1", "2", "", ""
                , true, false, true, item.getHasOE(), item.getIsOENew(), item.getOddOdds(), item.getEvenOdds(), "odd", "even");
        getBaseRecyclerAdapter().getItem(position).setIsX12New("0");
        getBaseRecyclerAdapter().getItem(position).setIsOENew("0");*//*
        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        handler.updateAfbMixBackground(item.getSocOddsId(), 0, sl, "1", "2", "home", "away", "NULL", "NULL");
        handler.updateAfbMixBackground(item.getSocOddsId(), 1, sl, "over", "under", "odd", "even", "NULL", "NULL");


    }*/


}
