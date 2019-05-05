package com.nanyang.app.main.home.sport.europe;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

/**
 * Created by ASUS on 2019/4/13.
 */

public class EuropeCommonAdapter extends BallAdapterHelper<BallInfo> {
    public EuropeCommonAdapter(Context context) {
        super(context);
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, BallInfo item) {
        super.onConvert(helper, position, item);
        TextView awayTv = helper.getView(R.id.module_match_away_team_tv);
        TextView homeTv = helper.getView(R.id.module_match_home_team_tv);
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
        TextView LeagueCollectionTv = helper.getView(R.id.module_League_collection_tv);
        TextView moduleMatchCollectionTv = helper.getView(R.id.module_match_collection_tv);
        LeagueCollectionTv.setVisibility(View.INVISIBLE);
        moduleMatchCollectionTv.setVisibility(View.INVISIBLE);
        homeTv.setText(home);
        awayTv.setText(away);
        updateMixBackground(helper, item);
    }

    @Override
    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, final BallInfo item, final int position) {//
        super.onMatchNotRepeat(helper, item, position);
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
    }

    @Override
    public void handleOddsContent(MyRecyclerViewHolder helper, BallInfo item, int position) {
        TextView full1 = helper.getTextView(R.id.europe_1_full_time_odds_tv);
        TextView full2 = helper.getTextView(R.id.europe_2_full_time_odds_tv);
        TextView fullx = helper.getTextView(R.id.europe_x_full_time_odds_tv);
        ImageView imgUpDownFt1 = helper.getImageView(R.id.img_up_down_ft_1);
        ImageView imgUpDownFtX = helper.getImageView(R.id.img_up_down_ft_x);
        ImageView imgUpDownFt2 = helper.getImageView(R.id.img_up_down_ft_2);
        imgUpDownFt1.setVisibility(View.INVISIBLE);
        imgUpDownFtX.setVisibility(View.INVISIBLE);
        imgUpDownFt2.setVisibility(View.INVISIBLE);
        View parent1 = (View) full1.getParent();
        View parent2 = (View) full2.getParent();
        View parent3 = (View) fullx.getParent();
        parent1.setVisibility(View.VISIBLE);
        parent2.setVisibility(View.VISIBLE);
        parent3.setVisibility(View.VISIBLE);
        if (item.getHasX12().equals("1")) {
            String full1Str = AfbUtils.decimalValue(Float.parseFloat(item.getX12_1Odds()), "0.00");
            if (!full1Str.equals("0.00")) {
                full1.setText(full1Str);
            } else {
                parent1.setVisibility(View.GONE);
            }
            String fullxStr = AfbUtils.decimalValue(Float.parseFloat(item.getX12_XOdds()), "0.00");
            if (!fullxStr.equals("0.00")) {
                fullx.setText(fullxStr);
            } else {
                parent2.setVisibility(View.GONE);
            }
            String full2Str = AfbUtils.decimalValue(Float.parseFloat(item.getX12_2Odds()), "0.00");
            if (!full2Str.equals("0.00")) {
                full2.setText(full2Str);
            } else {
                parent3.setVisibility(View.GONE);
            }
            full1.setOnClickListener(new ItemClick(back, position, item, item.getX12_1Odds(), "1", false));
            fullx.setOnClickListener(new ItemClick(back, position, item, item.getX12_XOdds(), "X", false));
            full2.setOnClickListener(new ItemClick(back, position, item, item.getX12_2Odds(), "2", false));
            setUpDown(item.isX12New, item.isX12_1New, imgUpDownFt1, item.isFt1Bigger());
            setUpDown(item.isX12New, item.isX12_XNew, imgUpDownFtX, item.isFtXBigger());
            setUpDown(item.isX12New, item.isX12_2New, imgUpDownFt2, item.isFt2Bigger());

        } else {
            full1.setText("");
            full2.setText("");
            fullx.setText("");
            parent1.setVisibility(View.GONE);
            parent2.setVisibility(View.GONE);
            parent3.setVisibility(View.GONE);
        }
        TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
        TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
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

    private void setUpDown(String isX12New, int isX12_1New, ImageView imgUpDownFt1, int ft1Bigger) {
        if (isX12New != null && isX12New.equals("1") && (isX12_1New == 1)) {
            if (ft1Bigger == 1) {
                imgUpDownFt1.setImageResource(R.mipmap.arrow_up_update_green);
                imgUpDownFt1.setVisibility(View.VISIBLE);
            } else if (ft1Bigger == -1) {
                imgUpDownFt1.setImageResource(R.mipmap.arrow_down_update_red);
                imgUpDownFt1.setVisibility(View.VISIBLE);
            }

        } else {
            imgUpDownFt1.setVisibility(View.GONE);
        }
    }

    public int onSetAdapterItemLayout() {
        return R.layout.sport_common_europe_ball_item;
    }

    private void updateMixBackground(MyRecyclerViewHolder helper, BallInfo item) {
        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        String itemFullSocOddsId = item.getSocOddsId();

        AfbClickBetBean mixItem = handler.getMixItem(itemFullSocOddsId);


        TextView fullTv1 = (TextView) helper.getView(R.id.europe_1_full_time_odds_tv);
        handler.setCommonBackground(fullTv1);
        TextView fullTvX = (TextView) helper.getView(R.id.europe_x_full_time_odds_tv);
        handler.setCommonBackground(fullTvX);
        TextView fullTv2 = (TextView) helper.getView(R.id.europe_2_full_time_odds_tv);
        handler.setCommonBackground(fullTv2);
        if (mixItem != null) {
            String transType = new AfbParseHelper().getBetTypeFromId(mixItem.getId());
            if (transType.startsWith("1")) {
                setMixBackground(handler, fullTv1);
            } else if (transType.startsWith("X")) {
                setMixBackground(handler, fullTvX);
            } else if (transType.startsWith("2")) {
                setMixBackground(handler, fullTv2);
            }


        }
    }

    private void setMixBackground(BaseMixStyleHandler handler, TextView fullTv) {
        handler.setMixBackground(fullTv);
    }

}

class ItemClick implements View.OnClickListener {
    SportAdapterHelper.ItemCallBack<BallInfo> back;
    int position;
    BallInfo item;
    String odds;
    String type;
    boolean isHf;

    public ItemClick(SportAdapterHelper.ItemCallBack<BallInfo> back, int position, BallInfo item, String odds, String type, boolean isHf) {
        this.position = position;
        this.item = item;
        this.odds = odds;
        this.back = back;
        this.type = type;
        this.isHf = isHf;
    }

    @Override
    public void onClick(View v) {
        if (!odds.equals("") && Float.valueOf(odds) != 0)
            back.clickOdds((TextView) v, item, type, isHf, odds, Integer.valueOf(isHf ? item.getSocOddsId_FH() : item.getSocOddsId()), "", isHf ? (item.getHasPar_FH() != null && item.getHasPar_FH().equals("1")) : (item.getHasPar() != null && item.getHasPar().equals("1")));
    }
}


