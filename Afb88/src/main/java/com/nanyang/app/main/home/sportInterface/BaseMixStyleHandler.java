package com.nanyang.app.main.home.sportInterface;

import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.unkonw.testapp.training.ScrollLayout;

/**
 * Created by Administrator on 2017/4/20.
 */

public class BaseMixStyleHandler implements IMixStyleHandler {
    public BaseMixStyleHandler(BaseToolbarActivity act) {
        this.act = act;
    }

    BaseToolbarActivity act;

    @Override
    public BettingParPromptBean.BetParBean getMixItem(String itemId) {
        if (act.getApp().getBetParList() != null && act.getApp().getBetParList().getBetPar().size() > 0)
            for (BettingParPromptBean.BetParBean betParBean : act.getApp().getBetParList().getBetPar()) {
                String parSocOddsId = betParBean.getSocOddsId() + "";
                if (itemId.equals(parSocOddsId)) {
                    return betParBean;
                }
            }
        return null;
    }

    @Override
    public void setCommonBackground(TextView tv) {
        tv.setBackgroundResource(0);
        tv.setTextColor(act.getResources().getColor(R.color.black_grey));
    }

    @Override
    public void setMixBackground(TextView tv) {
        tv.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_bg);
        tv.setTextColor(act.getResources().getColor(R.color.white));
    }

    public void parseCommonBackground(int i, ScrollLayout sl) {
        TextView overTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_overodds_tv);
        setCommonBackground(overTv);
        TextView underTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_underodds_tv);
        setCommonBackground(underTv);
        TextView awayTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_visit_hdpodds_tv);
        setCommonBackground(awayTv);
        TextView homeTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_home_hdpodds_tv);
        setCommonBackground(homeTv);

        TextView even_tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_even_tv);
        setCommonBackground(even_tv);
        TextView odd_tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_odd_tv);
        setCommonBackground(odd_tv);
    }


    public void parseMixBackground(BettingParPromptBean.BetParBean par, int i, ScrollLayout sl) {
        String transType = par.getTransType();
        boolean isHome = par.isIsBetHome();
        TextView overTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_overodds_tv);
        setCommonBackground(overTv);
        TextView underTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_underodds_tv);
        setCommonBackground(underTv);
        TextView awayTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_visit_hdpodds_tv);
        setCommonBackground(awayTv);
        TextView homeTv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_home_hdpodds_tv);
        setCommonBackground(homeTv);
        TextView even_tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_even_tv);
        setCommonBackground(even_tv);
        TextView odd_tv = (TextView) sl.getChildAt(i).findViewById(R.id.viewpager_match_odd_tv);
        setCommonBackground(odd_tv);
        if (transType.equalsIgnoreCase("HDP")) {
            if (isHome) {
                setMixBackground(homeTv);
            } else {
                setMixBackground(awayTv);
            }
        } else if (transType.equalsIgnoreCase("OU")) {
            if (isHome) {
                setMixBackground(overTv);
            } else {
                setMixBackground(underTv);
            }
        } else if (transType.equalsIgnoreCase("OE")) {
            if (isHome) {
                setMixBackground(odd_tv);
            } else {
                setMixBackground(even_tv);
            }
        }

    }
}
