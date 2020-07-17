package com.nanyang.app.main.home.sport.myanmarOdds;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/3/14.
 */

public class MyanmarAdapterHelper extends BallAdapterHelper<MyanmarInfo> {
    public MyanmarAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, final MyanmarInfo item) {
        super.onConvert(helper, position, item);
        LinearLayout ll_data1 = helper.getView(R.id.ll_data1);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);

        TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
        TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
        tvCollection.setVisibility(View.GONE);

        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), "0", "0", item.getUOdds_FH(), item.getOOdds_FH(), item.getHOdds_FH(), item.getAOdds_FH());

        String hasMMHdp = "0";
        if (!item.MMHdpOdds.equals("0") && Integer.valueOf(item.MMHdpPct) != -1) {
            hasMMHdp = "1";
        }
        String hasMMOu = "0";
        if (!item.MMOUOdds.equals("0")) {
            hasMMOu = "1";
        }
        String hdpMM = item.getMMHdp() + "(" + Integer.valueOf(item.getMMHdpPct()) / 100 + ")";


        String ouMM = item.getMMOU() + "(" + Integer.valueOf(item.getMMOUPct()) / 100 + ")";
//        scrollChild(sl.getChildAt(2), false, item, item.getMMIsHomeGive(), hasMMHdp, hdpMM, hasMMOu, ouMM, "0", "0", item.getMMOUOdds(), item.getMMOUOdds(), item.getMMHdpOdds(), item.getMMHdpOdds(),
//                "mmhome", "mmaway", "mmover", "mmunder",
//                true, true, false, "", "", "", "", "", ""
//        );
        String homeRank = item.getHomeRank();
        String awayRank = item.getAwayRank();
        String away = item.getAway();
        if (awayRank != null && !awayRank.equals("")) {
            away = "[" + awayRank + "]" + away;
        }
        String home = item.getHome();
        if (homeRank != null && !homeRank.equals("")) {
            home = "[" + homeRank + "]" + home;
        }
        homeTv.setText(home);
        awayTv.setText(away);
        String rcAway = item.getRCAway();
        String rcHome = item.getRCHome();
        checkRedCards(awayRedCardTv, rcAway);
        checkRedCards(homeRedCardTv, rcHome);
        if ((!item.getMMHdpOdds().equals("0") && !item.getMMHdpPct().equals("-1")) || (!item.getMMOUOdds().equals("0") && !item.getMMOUPct().equals("-1"))) {
            ll_data1.setVisibility(View.VISIBLE);
            TextView awayTv1 = helper.getView(R.id.module_match_away_team_tv1);
            TextView homeTv1 = helper.getView(R.id.module_match_home_team_tv1);
            TextView awayRedCardTv1 = helper.getView(R.id.module_match_away_red_card_tv1);
            TextView homeRedCardTv1 = helper.getView(R.id.module_match_home_red_card_tv1);
            homeTv1.setText(home);
            awayTv1.setText(away);
            checkRedCards(awayRedCardTv1, rcAway);
            checkRedCards(homeRedCardTv1, rcHome);
            ScrollLayout sl1 = helper.getView(R.id.module_center_sl1);
            scrollChild(sl1.getChildAt(0), false, item, item.getMMIsHomeGive(), hasMMHdp, hdpMM, hasMMOu, ouMM, "0", "0", item.getMMOUOdds(), item.getMMOUOdds(), item.getMMHdpOdds(), item.getMMHdpOdds(),
                    "mmhome", "mmaway", "mmover", "mmunder",
                    true, true, false, "", "", "", "", "", ""
            );
        } else {
            ll_data1.setVisibility(View.GONE);
        }
        updateMixBackground(item, sl, "home", "away", "over", "under", "odd", "even");
    }

    private void checkRedCards(TextView awayRedCardTv, String rcAway) {
        if (rcAway == null || rcAway.equals("0") || rcAway.equals("")) {
            awayRedCardTv.setVisibility(View.GONE);
        } else {
            awayRedCardTv.setVisibility(View.VISIBLE);
            switch (rcAway) {
                case "1":
                    awayRedCardTv.setBackgroundResource(R.mipmap.red_card1);
                    break;
                case "2":
                    awayRedCardTv.setBackgroundResource(R.mipmap.red_card2);
                    break;
                default:
                    awayRedCardTv.setBackgroundResource(R.mipmap.red_card3);
                    break;
            }
        }
    }
/**
 if ([obj.MMHdpOdds floatValue]!=0.0&&[obj.MMHdpPct floatValue]!=-1.0) {
 _btn00.hidden=_btn10.hidden=_lab00.hidden=_lab10.hidden=NO;
 if ([obj.IsHomeGive boolValue]) {
 _lab10.hidden=YES;
 _lab00.text=[NSString stringWithFormat:@"%@(%0.0f)H",[CM formateScore:[obj.MMHdp floatValue]],[obj.MMHdpPct floatValue]/100];
 }else{
 _lab00.hidden=YES;
 _lab10.text=[NSString stringWithFormat:@"%@(%0.0f)A",[CM formateScore:[obj.MMHdp floatValue]],[obj.MMHdpPct floatValue]/100];
 }
 }
 if ([obj.MMOUOdds floatValue]!=0.0) {
 _btn01.hidden=_btn11.hidden=_lab01.hidden=_lab11.hidden=NO;
 _lab11.text=@"u";
 _lab01.text=[NSString stringWithFormat:@"%@(%0.0f)",[CM formateScore:[obj.MMOU floatValue]],[obj.MMOUPct floatValue]/100];
 }*/

}
