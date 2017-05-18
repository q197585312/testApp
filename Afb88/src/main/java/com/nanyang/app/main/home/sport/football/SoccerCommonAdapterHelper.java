package com.nanyang.app.main.home.sport.football;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/14.
 */

public class SoccerCommonAdapterHelper extends BallAdapterHelper<SoccerCommonInfo> {
    public Map<Integer, Boolean> getRepMap() {
        return repMap;
    }

    Map<Integer, Boolean> repMap = new HashMap<>();

    public SoccerCommonAdapterHelper(Context context) {
        super(context);
    }


    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final SoccerCommonInfo item) {
        super.onConvert(helper, position, item);
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
        View tvCollection = helper.getView(R.id.module_match_collection_tv);
        View flCollection = helper.getView(R.id.module_match_collection_fl);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
        TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
        View llLeft1 = helper.getView(R.id.module_match_left1_ll);
        View llLeft2 = helper.getView(R.id.module_match_left2_ll);
        tvCollection.setVisibility(View.VISIBLE);
        tvRightMark.setVisibility(View.VISIBLE);

        if (((BallItemCallBack) back).isItemCollection(item))
            tvCollection.setBackgroundResource(R.mipmap.star_red_solid);
        else
            tvCollection.setBackgroundResource(R.mipmap.star_red_not_solid);
        flCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item,position);
            }
        });
        ScrollLayout sl = helper.getView(R.id.module_center_sl);
        scrollChild(sl.getChildAt(1), true, item, item.getIsHomeGive_FH(), item.getHasHdp_FH(), item.getHdp_FH(), item.getHasOU_FH(), item.getOU_FH(), item.getIsHdpNew_FH(), item.getIsOUNew_FH(), item.getUnderOdds_FH(), item.getOverOdds_FH(), item.getHomeHdpOdds_FH(), item.getAwayHdpOdds_FH());
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew_FH("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew_FH("0");
        String oldHomeName = "";
        String oldAwayName = "";
        String oldHomeGive = "";
        String oldModuleTitle = "";
        if (position > 0) {
            oldHomeName = back.getItem(position - 1).getHome();
            oldAwayName = back.getItem(position - 1).getAway();
            oldHomeGive = back.getItem(position - 1).getIsHomeGive();
            oldModuleTitle = back.getItem(position - 1).getModuleTitle().toString();

        }


        if (item.getModuleTitle().equals(oldModuleTitle) && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getIsHomeGive())) {
            awayTv.setText("");
            homeTv.setText("");
            tvRightMark.setVisibility(View.INVISIBLE);
            llLeft1.setVisibility(View.INVISIBLE);
            llLeft2.setVisibility(View.INVISIBLE);
            tvCollection.setVisibility(View.INVISIBLE);
            item.setHasOE("0");
            onMatchRepeat(helper, item, position);
        } else {

            onMatchNotRepeat(helper, item, position);
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
            tvCollection.setVisibility(View.VISIBLE);
            llLeft1.setVisibility(View.VISIBLE);
            llLeft2.setVisibility(View.VISIBLE);
            String rcAway = item.getRCAway();
            String rcHome = item.getRCHome();
            checkRedCards(awayRedCardTv, rcAway);
            checkRedCards(homeRedCardTv, rcHome);
        }

    }

    public int getNextNotRepeat(int position) {
        if(position<getBaseRecyclerAdapter().getItemCount()) {
            if (repMap.get(position + 1)==null||!repMap.get(position + 1)) {
                return position;
            } else {
                return getNextNotRepeat(position + 1);
            }
        }
        else
            return 0;

    }

    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, SoccerCommonInfo item, int postion) {//
        repMap.put(postion, false);
    }

    /**
     * //重复上一个
     *
     * @param helper
     * @param item
     * @param postion
     */
    protected void onMatchRepeat(MyRecyclerViewHolder helper, SoccerCommonInfo item, int postion) {
        repMap.put(postion, true);
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
