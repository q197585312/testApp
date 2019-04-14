package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerCommonAdapterHelper extends BallAdapterHelper<BallInfo> {
    public Map<Integer, Boolean> getRepMap() {
        return repMap;
    }

    Map<Integer, Boolean> repMap = new HashMap<>();

    public SoccerCommonAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final BallInfo item) {
        super.onConvert(helper, position, item);
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUOdds_FH(), item.getOOdds_FH(), item.getHOdds_FH(), item.getAOdds_FH());
//        getBaseRecyclerAdapter().getItem(position).setIsHdpNew_FH("0");
//        getBaseRecyclerAdapter().getItem(position).setIsOUNew_FH("0");
        updateMixBackground(item, sl, "home", "away", "over", "under", "odd", "even");
    }


    public int getNextNotRepeat(int position) {
        if (position < getBaseRecyclerAdapter().getItemCount()) {
            if (repMap.get(position + 1) == null || !repMap.get(position + 1)) {
                return position;
            } else {
                return getNextNotRepeat(position + 1);
            }
        } else
            return 0;

    }

    @Override
    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, final BallInfo item, final int position) {//
        super.onMatchNotRepeat(helper, item, position);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
        TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
        View llLeft1 = helper.getView(R.id.module_match_left1_ll);
        View llLeft2 = helper.getView(R.id.module_match_left2_ll);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        String rtsMatchId = item.getRTSMatchId();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0")) {
            ivHall.setVisibility(View.VISIBLE);
            ivHall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back.clickView(v, item, position);
                }
            });
        } else {
            ivHall.setVisibility(View.INVISIBLE);
        }
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


//        llLeft1.setVisibility(View.VISIBLE);
        llLeft2.setVisibility(View.VISIBLE);
        String rcAway = item.getRCAway();
        String rcHome = item.getRCHome();
        checkRedCards(awayRedCardTv, rcAway);
        checkRedCards(homeRedCardTv, rcHome);
        repMap.put(position, false);
    }

    /**
     * //重复上一个
     */
    @Override
    protected void onMatchRepeat(MyRecyclerViewHolder helper, BallInfo item, int position) {
        super.onMatchRepeat(helper, item, position);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        View llLeft1 = helper.getView(R.id.module_match_left1_ll);
        View llLeft2 = helper.getView(R.id.module_match_left2_ll);
        awayTv.setText("");
        homeTv.setText("");

//        llLeft1.setVisibility(View.INVISIBLE);
        llLeft2.setVisibility(View.INVISIBLE);
//        item.setHasOE("0");
        repMap.put(position, true);
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


}
