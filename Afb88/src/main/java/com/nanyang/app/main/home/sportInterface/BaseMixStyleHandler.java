package com.nanyang.app.main.home.sportInterface;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
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
    public OddsClickBean getMixItem(String itemId) {
        if (act.getApp().getMixBetList() != null && act.getApp().getMixBetList().size() > 0)
            for (OddsClickBean betParBean : act.getApp().getMixBetList()) {
                if (itemId.equals(betParBean.getOid())) {
                    return betParBean;
                }
            }
        return null;
    }

    @Override
    public void setCommonBackground(TextView tv) {

        View parent = getParentRelativeLayout(tv);
        if (parent == null)
            return;
        parent.setBackgroundResource(R.drawable.match_odds_content_bg);
//        tv.setTextColor(act.getResources().getColor(R.color.black_grey));
    }

    @Nullable
    private View getParentFragment(TextView tv) {
        View parent = (View) tv.getParent();
        while (parent != null && !(parent instanceof FrameLayout)) {
            parent = (View) parent.getParent();
        }
        return parent;
    }

    @Nullable
    private View getParentRelativeLayout(TextView tv) {
        View parent = (View) tv.getParent();
        while (parent != null && !(parent instanceof RelativeLayout)) {
            parent = (View) parent.getParent();
        }
        return parent;
    }


    @Override
    public void setMixBackground(TextView tv) {
        View parent = getParentRelativeLayout(tv);
        if (parent == null)
            return;
        parent.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_content_bg);

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


    public void parseMixBackground(OddsClickBean par, int i, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        String transType = par.getType();


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
        if (transType.startsWith(type01)) {
            setMixBackground(homeTv);
        } else if (transType.startsWith(type02)) {
            setMixBackground(awayTv);
        } else if (transType.startsWith(type11)) {
            setMixBackground(overTv);
        } else if (transType.startsWith(type12)) {
            setMixBackground(underTv);
        } else if (transType.startsWith(type21)) {
            setMixBackground(odd_tv);
        } else if (transType.startsWith(type22)) {
            setMixBackground(even_tv);
        }

    }

    public void updateMixBackground(BallInfo item, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        String itemFullSocOddsId = item.getSocOddsId();

        OddsClickBean mixItem = getMixItem(itemFullSocOddsId);
        int index = 0;

        if (mixItem != null) {
            parseMixBackground(mixItem, index, sl, type01, type02, type11, type12, type21, type22);
        } else {
            parseCommonBackground(0, sl);
            parseCommonBackground(1, sl);
        }
    }
}
