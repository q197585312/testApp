package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

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
public class SoccerMixAdapterHelper extends BallAdapterHelper<SoccerMixInfo> {


    public SoccerMixAdapterHelper(Context context) {
        super(context);

    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final SoccerMixInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);

        markAdd.setVisibility(View.VISIBLE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());
        String itemFullSocOddsId = item.getSocOddsId();
        String itemHfSocOddsId = item.getSocOddsId_FH();
        if(!checkBackground(sl, itemFullSocOddsId, itemHfSocOddsId)){
            parseCommonBackground(0,sl);
            parseCommonBackground(1,sl);
        }

    }

    private boolean checkBackground(ScrollLayout sl, String itemFullSocOddsId, String itemHfSocOddsId) {
        if (((BaseToolbarActivity) context).getApp().getBetParList() != null && ((BaseToolbarActivity) context).getApp().getBetParList().getBetPar().size() > 0)
            for (BettingParPromptBean.BetParBean betParBean : ((BaseToolbarActivity) context).getApp().getBetParList().getBetPar()) {
                String parFullTimeId = betParBean.getParFullTimeId();
                String parSocOddsId = betParBean.getSocOddsId() + "";
                if (itemFullSocOddsId.equals(parSocOddsId)) {
                    parseMixBackground(betParBean, 0, sl);
                    return true;
                } else if (parSocOddsId.equals(itemHfSocOddsId) && parFullTimeId.equals(itemFullSocOddsId)) {
                    parseMixBackground(betParBean, 1, sl);
                    return true;
                }
            }
        return false;
    }

    private void parseCommonBackground(int i, ScrollLayout sl) {
        TextView overTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_overodds_tv);
        setCommonBackground(overTv, context);
        TextView underTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_underodds_tv);
        setCommonBackground(underTv, context);
        TextView awayTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_visit_hdpodds_tv);
        setCommonBackground(awayTv, context);
        TextView homeTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_home_hdpodds_tv);
        setCommonBackground(homeTv, context);
    }

    // http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19",
    public void parseMixBackground(BettingParPromptBean.BetParBean par, int i, ScrollLayout sl) {
        String transType = par.getTransType();
        boolean isHome = par.isIsBetHome();
        TextView overTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_overodds_tv);
        setCommonBackground(overTv, context);
        TextView underTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_underodds_tv);
        setCommonBackground(underTv, context);
        TextView awayTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_visit_hdpodds_tv);
        setCommonBackground(awayTv, context);
        TextView homeTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_home_hdpodds_tv);
        setCommonBackground(homeTv, context);
        if (transType.equalsIgnoreCase("HDP")) {
            if (isHome) {
                setMixBackground(homeTv, context);
            } else {
                setMixBackground(awayTv, context);
            }
        } else if (transType.equalsIgnoreCase("OU")) {
            if (isHome) {
                setMixBackground(overTv, context);
            } else {
                setMixBackground(underTv, context);
            }
        }

    }

    public static void setMixBackground(TextView tv, Context context) {
        tv.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_bg);
        tv.setTextColor(context.getResources().getColor(R.color.white));
    }

    public static void setCommonBackground(TextView tv, Context context) {
        tv.setBackgroundResource(0);
        tv.setTextColor(context.getResources().getColor(R.color.black_grey));
    }

}
