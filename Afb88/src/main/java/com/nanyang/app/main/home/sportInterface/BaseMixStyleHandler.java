package com.nanyang.app.main.home.sportInterface;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.model.AfbClickBetBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.utils.LogUtil;
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
    public AfbClickBetBean getMixItem(String itemId) {
        if (act.getApp().getBetParList() != null && act.getApp().getBetParList().getList().size() > 0)
            for (AfbClickBetBean betParBean : act.getApp().getBetParList().getList()) {

                String parSocOddsId = new AfbParseHelper<>().getSocOddsIdFromId(betParBean.getId());
                if (itemId.equals(parSocOddsId)) {
                    return betParBean;
                }
            }
        return null;
    }

    @Override
    public void setCommonBackground(TextView tv) {

        View parent = getParentFragment(tv);
        if (parent == null)
            return;
        parent.setBackgroundResource(0);
//        tv.setTextColor(act.getResources().getColor(R.color.black_grey));
    }

    @Nullable
    private View getParentFragment(TextView tv) {
        View parent = (View) tv.getParent();
        while (parent != null && !(parent instanceof FrameLayout)) {
            LogUtil.d("MixStyle", "parentId;" + parent.getId());
            parent = (View) parent.getParent();
        }
        return parent;
    }

    @Override
    public void setMixBackground(TextView tv) {
        View parent = getParentFragment(tv);
        if (parent == null)
            return;
        parent.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_bg);

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


    public void parseMixBackground(AfbClickBetBean par, int i, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        String transType = new AfbParseHelper().getBetTypeFromId(par.getId());


        boolean isHome = par.getBTeam().equalsIgnoreCase("Home");
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
        if (transType.startsWith(type01) || transType.startsWith(type02)) {
            if (isHome) {
                setMixBackground(homeTv);
            } else {
                setMixBackground(awayTv);
            }
        } else if (transType.startsWith(type11) || transType.startsWith(type12)) {
            if (isHome) {
                setMixBackground(overTv);
            } else {
                setMixBackground(underTv);
            }
        } else if (transType.startsWith(type21) || transType.startsWith(type22)) {
            if (isHome) {
                setMixBackground(odd_tv);
            } else {
                setMixBackground(even_tv);
            }
        }

    }

    public void updateMixBackground(BallInfo item, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        String itemFullSocOddsId = item.getSocOddsId();
        String itemHfSocOddsId = item.getSocOddsId_FH();
        AfbClickBetBean mixItem = getMixItem(itemFullSocOddsId);
        int index = 0;
        if (mixItem == null) {
            mixItem = getMixItem(itemHfSocOddsId);
            index = 1;
        }
        if (mixItem != null) {
            parseMixBackground(mixItem, index, sl, type01, type02, type11, type12, type21, type22);
        } else {
            parseCommonBackground(0, sl);
            parseCommonBackground(1, sl);
        }
    }

    public void updateAfbMixBackground(String OddsId, int index, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        AfbClickBetBean mixItem = getMixItem(OddsId);
        if (mixItem != null) {
            parseMixBackground(mixItem, index, sl, type01, type02, type11, type12, type21, type22);
        } else {
            parseCommonBackground(index, sl);
        }
    }

}
