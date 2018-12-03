package com.nanyang.app.main.home.sport.basketball;

import android.content.Context;

import com.nanyang.app.main.home.sport.main.BallAdapterHelper;

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

public class BasketballAdapterHelper extends BallAdapterHelper<BasketballMixInfo> {


    public BasketballAdapterHelper(Context context) {
        super(context);

    }
/*
    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final BasketballMixInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        markAdd.setVisibility(View.GONE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(1), false, item, item.getIsHomeGive(), item.getHasX12(), "", item.getHasOE(), "", item.getIsX12New(), "", "", "", item.getX12_1Odds(), item.getX12_2Odds(), "1", "2", "", ""
                , true, false, true, item.getHasOE(), item.getIsOENew(), (item.getOEOdds()!=null&&Float.valueOf(item.getOEOdds())>=1||Float.valueOf(item.getOEOdds())<=-1)?item.getOddOdds():"",
                (item.getOEOdds()!=null&&Float.valueOf(item.getOEOdds())>=1||Float.valueOf(item.getOEOdds())<=-1)?item.getEvenOdds():"", "odd", "even");
        getBaseRecyclerAdapter().getItem(position).setIsX12New("0");
        getBaseRecyclerAdapter().getItem(position).setIsOENew("0");
        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        String itemFullSocOddsId = item.getSocOddsId();
        AfbClickBetBean mixItem = handler.getMixItem(itemFullSocOddsId);
        if (mixItem != null) {
            if (mixItem.getTransType().equalsIgnoreCase("OE"))
                handler.parseMixBackground(mixItem, 1, sl);
            else {
                handler.parseMixBackground(mixItem, 0, sl);
            }
        } else {
            handler.parseCommonBackground(0, sl);
            handler.parseCommonBackground(1, sl);
        }
    }*/


}
