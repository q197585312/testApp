package com.nanyang.app.main.home.sportInterface;

import android.content.Context;
import android.view.View;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/14.
 */

/**
 {
 "SocOddsId": 12219288,
 "Home": "AS摩纳哥",
 "Away": "曼城",
 "IsHomeGive": false,
 "IsBetHome": false,
 "ParFullTimeId": "12219287",
 "TransType": "HDP",
 "IsOddsChange": false,
 "ParUrl": "http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=away_par&oId=12219288&odds=19",
 "BetHdp": "0",
 "BetOu": "0",
 "BetOdds": "1.90",
 "Test2": "testing2"
 }
 */
public class SoccerMixAdapterHelper extends BallAdapterHelper<SoccerMixInfo> {
    BettingParPromptBean betParList;

    public SoccerMixAdapterHelper(Context context) {
        super(context);
        betParList = ((BaseToolbarActivity) context).getApp().getBetParList();
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final SoccerMixInfo item) {
        super.onConvert(helper, position, item);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View markAdd = helper.getView(R.id.module_right_mark_tv);
        helper.getView(R.id.);
        markAdd.setVisibility(View.VISIBLE);
        tvCollection.setVisibility(View.GONE);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        View hfView = scrollChild(true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());
        sl.addView(hfView, SoccerHeaderContent.layoutParams);
        for (BettingParPromptBean.BetParBean betParBean : betParList.getBetPar()) {
            String parFullTimeId = betParBean.getParFullTimeId();
            String socOddsId = betParBean.getSocOddsId() + "";
            if(parFullTimeId.equals("0")|| parFullTimeId.equals("")){
                if(socOddsId.equals(item.getSocOddsId())){
                    setMixBackground(betParBean.getParUrl(),);
                }else{

                }

            }else{

            }
        }

    }


}
