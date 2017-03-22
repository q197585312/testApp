package com.nanyang.app.main.home.sport.myanmarOdds;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sportInterface.BallAdapterHelper;
import com.nanyang.app.main.home.sportInterface.SoccerHeaderContent;
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
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);
        TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
        TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
        tvCollection.setVisibility(View.GONE);
        tvRightMark.setVisibility(View.VISIBLE);

        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        View hfView = scrollChild(true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), "0", "0", item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());
        sl.addView(hfView, SoccerHeaderContent.layoutParams);


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
        tvRightMark.setVisibility(View.VISIBLE);

        String rcAway = item.getRCAway();
        String rcHome = item.getRCHome();
        checkRedCards(awayRedCardTv, rcAway);
        checkRedCards(homeRedCardTv, rcHome);


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
