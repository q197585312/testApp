package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sportInterface.BallAdapterHelper;
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
        scrollChild(sl.getChildAt(1),true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());

        if (((BaseToolbarActivity) context).getApp().getBetParList() != null && ((BaseToolbarActivity) context).getApp().getBetParList().getBetPar().size() > 0)
            for (BettingParPromptBean.BetParBean betParBean : ((BaseToolbarActivity) context).getApp().getBetParList().getBetPar()) {
                String parFullTimeId = betParBean.getParFullTimeId();
                String socOddsId = betParBean.getSocOddsId() + "";
                if (parFullTimeId.equals("0") || parFullTimeId.equals("")) {
                    if (socOddsId.equals(item.getSocOddsId())) {
                        parseMixBackground(betParBean, 0, sl);
                    }

                } else {
                    if (socOddsId.equals(item.getSocOddsId_FH()) && parFullTimeId.equals(item.getSocOddsId())) {
                        parseMixBackground(betParBean, 1, sl);
                    }
                }
            }

    }

    // http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19",
    public void parseMixBackground(BettingParPromptBean.BetParBean par, int i, ScrollLayout sl) {


        String transType = par.getTransType();
        boolean isHome = par.isIsBetHome();
        if (transType.equalsIgnoreCase("HDP")) {
            if (isHome) {
                TextView tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_home_hdpodds_tv);
                setMixBackground(tv, context);
            } else {
                TextView tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_visit_hdpodds_tv);
                setMixBackground(tv, context);

            }
        } else if (transType.equalsIgnoreCase("OU")) {

            if (isHome) {
                TextView tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_overodds_tv);
                setMixBackground(tv, context);
            } else {
                TextView tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_underodds_tv);
                setMixBackground(tv, context);
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
