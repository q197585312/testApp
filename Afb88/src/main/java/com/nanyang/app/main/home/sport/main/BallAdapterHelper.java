package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.DCBean;
import com.nanyang.app.main.home.sport.model.F1x2Bean;
import com.nanyang.app.main.home.sport.model.FGLGBean;
import com.nanyang.app.main.home.sport.model.FTOEBean;
import com.nanyang.app.main.home.sport.model.HTFTBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TGBean;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class BallAdapterHelper<I extends BallInfo> extends SportAdapterHelper<I> {
    final int red_black = Color.RED;
    final int black_grey = Color.BLACK;
    protected Context context;
    private AddMBean additionData;
    private int additionPosition;

    public Set<ScrollLayout> getSlFollowers() {
        return slFollowers;
    }

    protected Set<ScrollLayout> slFollowers = new HashSet<>();

    public void setSlIndex(int slIndex) {
        this.slIndex = slIndex;
    }

    private int slIndex = 0;

    public synchronized void notifyPositionAddition(AddMBean data, int position) {
        this.additionData = data;
        int oldAdditionPosition = additionPosition;

        this.additionPosition = position;
        getBaseRecyclerAdapter().notifyItemChanged(position);
        if (oldAdditionPosition >= 0)
            getBaseRecyclerAdapter().notifyItemChanged(oldAdditionPosition);

    }

    public BallAdapterHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final I item) {
        LinearLayout parent = helper.getView(R.id.common_ball_parent_ll);
//        List<BallInfo> repeatRow = item.getRepeatRow();
        if (position == additionPosition && additionMap.get(true) != null && additionMap.get(true) == additionPosition) {
//            LogUtil.d("Addition", "--------------repeatRow:" + (repeatRow == null ? "null" : repeatRow.size()));
            parent.setVisibility(View.VISIBLE);
            parent.removeAllViews();
            View titleLL = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_title_item, null);
            parent.addView(titleLL);
            int sizeFT = 0;
            int sizeFH = 0;

            if (additionData != null && additionData.getFTodds() != null)
                sizeFT = additionData.getFTodds().size();
            if (additionData != null && additionData.getFHodds() != null)
                sizeFH = additionData.getFHodds().size();
            int size = sizeFT > sizeFH ? sizeFT : sizeFH;
            for (int i = 0; i < size; i++) {
                addAdditionView(parent, i < sizeFT ? additionData.getFTodds().get(i) : null, i < sizeFH ? additionData.getFHodds().get(i) : null, item);
            }


            if (additionData != null) {
                if (additionData.getF1X2_CNT() != null && !additionData.getF1X2_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, R.string.FULL_1X2);
                    addAddition(additionData.getF1(), additionData.getFX(), additionData.getF2(), additionData.getF1X2_SocOddsId(), false, parent, item,
                            "1", "X", "2", "1", "x", "2", "", "", "", R.layout.addition_1x2_sport_item);
                }
                if (additionData.getH1X2_CNT() != null && !additionData.getH1X2_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, R.string.HALF_1X2);
                    addAddition(additionData.getH1(), additionData.getHX(), additionData.getH2(), additionData.getH1X2_SocOddsId(), false, parent, item,
                            "1", "X", "2", "1", "x", "2", "", "", "", R.layout.addition_1x2_sport_item);
                }

                if (!StringUtils.isNull(additionData.getFCS_CNT()) && !additionData.getFCS_CNT().equals("0")) {//波胆
//                    addAdditionCS(additionData, false, parent, item, "70");
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, R.string.correct_full);
                    addAddition(additionData.getG1(), additionData.getG11(), additionData.getG17(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG2(), additionData.getG12(), additionData.getG18(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG3(), additionData.getG13(), additionData.getG19(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG4(), additionData.getG14(), additionData.getG20(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG5(), additionData.getG15(), additionData.getG21(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "3:1", "4:4", "1:3", "csr", "csr", "csr", "31", "44", "13", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG6(), additionData.getG16(), additionData.getG22(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "3:2", "AOS", "2:3", "csr", "csr", "csr", "32", "70", "23", R.layout.addition_cs_sport_item);

                    addAddition(additionData.getG7(), "", additionData.getG23(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "4:0", "", "0:4", "csr", "csr", "csr", "40", "", "4", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG8(), "", additionData.getG24(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "4:1", "", "1:4", "csr", "csr", "csr", "41", "", "14", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG9(), "", additionData.getG25(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "4:2", "", "2:4", "csr", "csr", "csr", "42", "", "24", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getG10(), "", additionData.getG26(), additionData.getFCS_SocOddsId(), false, parent, item,
                            "4:3", "", "3:4", "csr", "csr", "csr", "43", "", "34", R.layout.addition_cs_sport_item);
                }
                if (!StringUtils.isNull(additionData.getHCS_CNT()) && !additionData.getHCS_CNT().equals("0")) {
//                    addAdditionCS(additionData.getFHCS(), true, parent, item, "80");
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, R.string.correct_half);
                    addAddition(additionData.getHG1(), additionData.getHG11(), additionData.getHG17(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getHG2(), additionData.getHG12(), additionData.getHG18(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getHG3(), additionData.getHG13(), additionData.getHG19(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getHG4(), additionData.getHG14(), additionData.getHG20(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getHG5(), additionData.getHG16(), additionData.getHG21(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "3:1", "AOS", "1:3", "csr", "csr", "csr", "31", "80", "13", R.layout.addition_cs_sport_item);
                    addAddition(additionData.getHG6(), "", additionData.getHG22(), additionData.getHCS_SocOddsId(), true, parent, item,
                            "3:2", "", "2:3", "csr", "", "csr", "32", "", "23", R.layout.addition_cs_sport_item);


                }

                if (!StringUtils.isNull(additionData.getFDB_CNT()) && !additionData.getFDB_CNT().equals("0")) {
//                    addAdditionDC(additionData.getFTDC(), false, parent, item);

                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.full_time) + " " + context.getString(R.string.double_chance));
                    addAddition(additionData.getFHD(), additionData.getFHA(), additionData.getFDA(), additionData.getFDB_SocOddsId(), false, parent, item,
                            "1X", "12", "X2", "dc", "dc", "dc", "10", "12", "2", R.layout.addition_1x2_sport_item);

                }
                if (!StringUtils.isNull(additionData.getHDB_CNT()) && !additionData.getHDB_CNT().equals("0")) {
//                    addAdditionDC(additionData.getHTDC(), false, parent, item);

                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.half_time) + " " + context.getString(R.string.double_chance));
                    addAddition(additionData.getHHD(), additionData.getHHA(), additionData.getHDA(), additionData.getHDB_SocOddsId(), false, parent, item,
                            "1X", "12", "X2", "dc", "dc", "dc", "10", "12", "2", R.layout.addition_1x2_sport_item);

                }

                if (!StringUtils.isNull(additionData.getFOE_CNT()) && !additionData.getFOE_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.full_time) + context.getString(R.string.odd_even));
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFOdd()), AfbUtils.changeValueS(additionData.getFEven()), additionData.getFOE_SocOddsId(), false, parent, item,
                            "ODD", "EVEN", "dc", "dc", "10", "12", R.layout.addition_1x2_sport_item,"1");
                }
                if (!StringUtils.isNull(additionData.getHOE_CNT()) && !additionData.getHOE_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.half_time) + context.getString(R.string.odd_even));
                    addAddition(AfbUtils.changeValueS(additionData.getHOdd()), AfbUtils.changeValueS(additionData.getHEven()), additionData.getHOE_SocOddsId(), true, parent, item,
                            "1X", "12", "dc", "dc", "10", "12", R.layout.addition_1x2_sport_item);
                }

                if (!StringUtils.isNull(additionData.getHTFT_CNT()) && !additionData.getHTFT_CNT().equals("0")) {//半场全场
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.half_full_time));

                    addAddition(additionData.getHH(), additionData.getHD(), additionData.getHA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                            context.getString(R.string.hh), context.getString(R.string.hd), context.getString(R.string.ha)
                            , "additionData", "additionData", "additionData", "11 ", "10", "12", R.layout.addition_htft_sport_item);
                    addAddition(additionData.getDH(), additionData.getDD(), additionData.getDA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                            context.getString(R.string.dh), context.getString(R.string.dd), context.getString(R.string.da)
                            , "additionData", "additionData", "additionData", "1 ", "0", "2", R.layout.addition_htft_sport_item);

                    addAddition(additionData.getAH(), additionData.getAD(), additionData.getAA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                            context.getString(R.string.ah), context.getString(R.string.ad), context.getString(R.string.aa)
                            , "htft", "htft", "htft", "21 ", "20", "22", R.layout.addition_htft_sport_item);
                }

                if (!StringUtils.isNull(additionData.getFGLG_CNT()) && !additionData.getFGLG_CNT().equals("0")) {//最后得分 最先得分
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.first_last_goal));

                    addAddition(additionData.getHFG(), additionData.getNG(), additionData.getAFG(), additionData.getFGLG_SocOddsId(), false, parent, item,
                            "HFG", "NG", "AFG", "fglg", "fglg", "fglg", "10 ", "0", "20", R.layout.addition_htft_sport_item);
                    addAddition(additionData.getHLG(), "", additionData.getALG(), additionData.getFGLG_SocOddsId(), false, parent, item,
                            "HLG", "", "ALG", "fglg", "", "fglg", "1 ", "", "2", R.layout.addition_htft_sport_item);
                }
                if (!StringUtils.isNull(additionData.getTG_CNT()) && !additionData.getTG_CNT().equals("0")) {//zho 最先得分
//                    addAdditionTG(additionData.getTG(), parent, item);
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.total_goals));

                    addAdditionByColor(additionData.getGoal1(), additionData.getGoal2(), additionData.getTG_SocOddsId(), false, parent, item,
                            "0~1", "2~3", "tg", "tg", "1", "23", R.layout.addition_1x2_sport_item,"2");
                    addAdditionByColor(additionData.getGoal3(), additionData.getGoal4(), additionData.getTG_SocOddsId(), false, parent, item,
                            "4~6", "7~Over", "tg", "tg", "46", "70", R.layout.addition_1x2_sport_item,"2");
                }

                if (!StringUtils.isNull(additionData.getHTTG_CNT()) && !additionData.getHTTG_CNT().equals("0")) {//zhudui
//                    addAdditionTEAMTG(additionData.getHOMETEAMTG(), false, parent, item);
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);

                    addTitle(parent, inflate, context.getString(R.string.HOME_TEAM_TOTAL_GOALS));
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFHOOdds()), AfbUtils.changeValueS(additionData.getFHUOdds()), additionData.getHTTG_SocOddsId(), false, parent, item,
                            "FT.Over " + additionData.getHTTG_OU(), "FT.Under " + additionData.getHTTG_OU(), "over", "under", "", "", R.layout.addition_1x2_sport_item,"3");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getHHOOdds()), AfbUtils.changeValueS(additionData.getHHUOdds()), additionData.getHTTG_FHSocOddsId(), true, parent, item,
                            "FT.Over " + additionData.getHTTG_FHOU(), "FT.Under " + additionData.getHTTG_FHOU(), "over", "under", "", "", R.layout.addition_1x2_sport_item,"3");
                }
                if (!StringUtils.isNull(additionData.getATTG_CNT()) && !additionData.getATTG_CNT().equals("0")) {//zhudui
//                    addAdditionTEAMTG(additionData.getHOMETEAMTG(), false, parent, item);
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.AWAY_TEAM_TOTAL_GOALS));

                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFAOOdds()), AfbUtils.changeValueS(additionData.getFAUOdds()), additionData.getATTG_SocOddsId(), false, parent, item,
                            "FT.Over " + additionData.getATTG_OU(), "FT.Under " + additionData.getATTG_OU(), "over", "under", "", "", R.layout.addition_1x2_sport_item,"3");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getHAOOdds()), AfbUtils.changeValueS(additionData.getHAUOdds()), additionData.getATTG_FHSocOddsId(), true, parent, item,
                            "FT.Over " + additionData.getATTG_FHOU(), "FT.Under " + additionData.getATTG_FHOU(), "over", "under", "", "", R.layout.addition_1x2_sport_item,"3");
                }
                /*"(00:00-15:00)"
"(15:01-30:00)"
"(30:01-45:00)"
"(45:01-60:00)"
"(60:01-75:00)"*/
                if (!StringUtils.isNull(additionData.getMG15_CNT()) && !additionData.getMG15_CNT().equals("0")) {
//                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_0(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)", parent, item);
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFT15InfoO()), AfbUtils.changeValueS(additionData.getFT15InfoU()), additionData.getMG15_SocOddsId(), false, parent, item,
                            context.getString(R.string.O) + additionData.getFT15InfoOU(), context.getString(R.string.U) + additionData.getFT15InfoOU(), "over", "under", "", "", R.layout.addition_htft_sport_item,"3");
                }
                if (!StringUtils.isNull(additionData.getMG30_CNT()) && !additionData.getMG30_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(15:01-30:00)");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFT30InfoO()), AfbUtils.changeValueS(additionData.getFT30InfoU()), additionData.getMG30_SocOddsId(), false, parent, item,
                            context.getString(R.string.O) + additionData.getFT30InfoOU(), context.getString(R.string.U) + additionData.getFT30InfoOU(), "over", "under", "", "", R.layout.addition_htft_sport_item,"3");
                }
                if (!StringUtils.isNull(additionData.getMG45_CNT()) && !additionData.getMG45_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(30:01-45:00)");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFT45InfoO()), AfbUtils.changeValueS(additionData.getFT45InfoU()), additionData.getMG45_SocOddsId(), false, parent, item,
                            context.getString(R.string.O) + additionData.getFT45InfoOU(), context.getString(R.string.U) + additionData.getFT45InfoOU(), "over", "under", "", "", R.layout.addition_htft_sport_item,"3");
                }

                if (!StringUtils.isNull(additionData.getMG60_CNT()) && !additionData.getMG60_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(45:01-60:00)");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFT60InfoO()), AfbUtils.changeValueS(additionData.getFT60InfoU()), additionData.getMG60_SocOddsId(), false, parent, item,
                            context.getString(R.string.O) + additionData.getFT60InfoOU(), context.getString(R.string.U) + additionData.getFT60InfoOU(), "over", "under", "", "", R.layout.addition_htft_sport_item,"3");
                }
             /*   if (additionData.getFT15MINSHANDICAP_OVER_UNDER_60() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_60().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_60(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(60:01-75:00)", parent, item);
                }*/
                if (!StringUtils.isNull(additionData.getMG75_CNT()) && !additionData.getMG75_CNT().equals("0")) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                    addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(60:01-75:00)");
                    addAdditionByColor(AfbUtils.changeValueS(additionData.getFT75InfoO()), AfbUtils.changeValueS(additionData.getFT75InfoU()), additionData.getMG75_SocOddsId(), false, parent, item,
                            context.getString(R.string.O) + additionData.getFT75InfoOU(), context.getString(R.string.U) + additionData.getFT75InfoOU(), "over", "under", "", "", R.layout.addition_htft_sport_item,"3");
                }
                if (additionData.getFTMModds() != null && additionData.getFTMModds().size() > 0) {
                    for (AddMBean.MModdsBean mModdsBean : additionData.getFTMModds()) {
                        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_mm_title_ft_item, null);
                        parent.addView(inflate);
                        addAdditionByColor(AfbUtils.changeValueS(mModdsBean.getHomeOdds()), AfbUtils.changeValueS(mModdsBean.getOverOdds()), mModdsBean.getSocOddsId(), false, parent, item,
                                "Home " + mModdsBean.getHDPH(), "Over " + mModdsBean.getOU(), "mmhome", "mmover", "", "", R.layout.addition_1x2_sport_item,"3");
                        addAdditionByColor(AfbUtils.changeValueS(mModdsBean.getAwayOdds()), AfbUtils.changeValueS(mModdsBean.getUnderOdds()), mModdsBean.getSocOddsId(), false, parent, item,
                                "Away " + mModdsBean.getHDPA(), "Under " + mModdsBean.getOU(), "mmaway", "mmunder", "", "", R.layout.addition_1x2_sport_item,"3");
                    }
                }
                if (additionData.getFHMModds() != null && additionData.getFHMModds().size() > 0) {
                    for (AddMBean.MModdsBean mModdsBean : additionData.getFHMModds()) {
                        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_mm_title_fh_item, null);
                        parent.addView(inflate);
                        addAdditionByColor(AfbUtils.changeValueS(mModdsBean.getHomeOdds()), AfbUtils.changeValueS(mModdsBean.getOverOdds()), mModdsBean.getSocOddsId(), true, parent, item,
                                "Home " + mModdsBean.getHDPH(), "Over " + mModdsBean.getOU(), "mmhome", "mmover", "", "", R.layout.addition_1x2_sport_item,"3");
                        addAdditionByColor(AfbUtils.changeValueS(mModdsBean.getAwayOdds()), AfbUtils.changeValueS(mModdsBean.getUnderOdds()), mModdsBean.getSocOddsId(), true, parent, item,
                                "Away " + mModdsBean.getHDPA(), "Under " + mModdsBean.getOU(), "mmaway", "mmunder", "", "", R.layout.addition_1x2_sport_item,"3");
                    }
                }
            }
        } else {
            parent.setVisibility(View.GONE);

        }
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.INVISIBLE);

        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
        TextView LeagueCollectionTv = helper.getView(R.id.module_League_collection_tv);

        View matchTitleLl = helper.getView(R.id.module_match_title_ll);

        View headV = helper.getView(R.id.module_match_head_v);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView dateTv1 = helper.getView(R.id.module_match_date_tv1);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView timeTv1 = helper.getView(R.id.module_match_time_tv1);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView liveTv1 = helper.getView(R.id.module_match_live_iv1);
        TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);
        TextView homeTv1 = helper.getTextView(R.id.module_match_home_team_tv1);
        TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);
        TextView awayTv1 = helper.getTextView(R.id.module_match_away_team_tv1);
        TextView gameSumTv = helper.getView(R.id.module_right_mark_tv);
        final View tvCollection = helper.getView(R.id.module_match_collection_tv);
        liveTv.setTextColor(red_black);
        liveTv1.setTextColor(red_black);
        dateTv.setTextColor(red_black);
        dateTv1.setTextColor(red_black);
        timeTv.setTextColor(black_grey);
        timeTv1.setTextColor(black_grey);
        dateTv.setTextSize(10);
        dateTv1.setTextSize(10);
        dateTv.setPadding(0, 0, 0, 0);
        dateTv1.setPadding(0, 0, 0, 0);
        String time = item.getMatchDate();
  /*      if (time.length() > 6) {
            time = time.substring(time.length() - 7, time.length());
            time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
        }*/
        timeTv.setText(time);
        timeTv1.setText(time);

        if (!item.getLive().equals("")) {
            dateTv.setVisibility(View.VISIBLE);
            if (item.getLive().contains("LIVE")) {
                dateTv.setText("LIVE");
                dateTv1.setText("LIVE");
                liveTv.setVisibility(View.GONE);
                liveTv1.setVisibility(View.GONE);
            } else {
                String channel = item.getLive();
                channel = Html.fromHtml(channel).toString();
                String[] channels = channel.split("\n");
                if (channels.length == 1) {
                    liveTv.setVisibility(View.GONE);
                    liveTv1.setVisibility(View.GONE);
                    if (channel.trim().length() > 6) {
                        dateTv.setTextSize(8);
                        dateTv1.setTextSize(8);
                    }
                    dateTv.setText(channel.trim());
                    dateTv1.setText(channel.trim());
                } else if (channels.length == 2) {
                    liveTv.setTextSize(7);
                    liveTv1.setTextSize(7);
                    if (channels[1].trim().length() >= 6) {
                        dateTv.setTextSize(8);
                        dateTv1.setTextSize(8);
                    } else {
                        dateTv.setTextSize(9);
                        dateTv1.setTextSize(9);
                    }
                    liveTv.setVisibility(View.GONE);
                    liveTv1.setVisibility(View.VISIBLE);
                    liveTv.setText(channels[0].trim());
                    liveTv1.setText(channels[0].trim());
                    dateTv.setText(channels[1].trim());
                    dateTv1.setText(channels[1].trim());
                }
            }
        } else {
            dateTv.setVisibility(View.GONE);
//            if (item.getMatchDate().length() > 6) {
//                String date = item.getMatchDate().substring(0, 5);
//                dateTv.setText(date);
//                dateTv1.setText(date);
//            } else {
//                dateTv.setText(item.getMatchDate());
//                dateTv1.setText(item.getMatchDate());
//            }
        }

        if (((BallItemCallBack) back).isItemCollection(item))
            tvCollection.setBackgroundResource(R.mipmap.star_red_solid);
        else
            tvCollection.setBackgroundResource(R.mipmap.star_red_not_solid);
        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item, position);
            }
        });
        LeagueCollectionTv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        back.clickView(view, item, position);
                    }
                }
        );
        String isHomeGive = item.getIsHomeGive();
        if (isHomeGive.equals("1")) {
            homeTv.setTextColor(red_black);
            homeTv1.setTextColor(red_black);
            awayTv.setTextColor(black_grey);
            awayTv1.setTextColor(black_grey);
        } else {
            homeTv.setTextColor(black_grey);
            homeTv1.setTextColor(black_grey);
            awayTv.setTextColor(red_black);
            awayTv1.setTextColor(red_black);
        }

        gameSumTv.setVisibility(View.GONE);
        gameSumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item, position);
            }
        });
        gameSumTv.setText("+ " + item.getGamesSum());
        View contentParentLl = helper.getView(R.id.ll_match_content);
        contentParentLl.setBackgroundColor(item.getContentColor());
        if (item.getType() == SportInfo.Type.ITME) {
            matchTitleLl.setVisibility(View.GONE);
            headV.setVisibility(View.GONE);
            LeagueCollectionTv.setBackgroundResource(R.mipmap.star_red_not_solid);
        } else {
            LeagueCollectionTv.setBackgroundResource(((BallItemCallBack) back).isLeagueCollection(item) ? R.mipmap.star_red_solid : R.mipmap.star_red_not_solid);
            matchTitleLl.setVisibility(View.VISIBLE);
            headV.setVisibility(View.VISIBLE);
            matchTitleTv.setText(item.getModuleTitle());
//            if (position == 0) {
//                headV.setVisibility(View.GONE);
//            }
        }
        handleOddsContent(helper, item, position);

        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);
        homeTv1.setText(home);
        awayTv.setText(away);
        awayTv1.setText(away);
        if (liveTv.getText().toString().trim().isEmpty()) {
            liveTv.setVisibility(View.GONE);
            liveTv1.setVisibility(View.GONE);
        } else {
            liveTv.setVisibility(View.GONE);
            liveTv1.setVisibility(View.VISIBLE);
        }
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
            onMatchRepeat(helper, item, position);
        } else {
            onMatchNotRepeat(helper, item, position);
        }

    }

    public void handleOddsContent(MyRecyclerViewHolder helper, I item, int position) {
        final ScrollLayout sl = helper.getView(R.id.module_center_sl);
        final ScrollLayout sl1 = helper.getView(R.id.module_center_sl1);
        sl1.getChildAt(0).setVisibility(View.VISIBLE);
        sl1.getChildAt(1).setVisibility(View.VISIBLE);

        String hasHdp = item.getHasHdp();
        String hdp = item.getHdp();
        String hasOU = item.getHasOU();
        String ou = item.getOU();
        String isHdpNew = item.getIsHdpNew();
        String isOUNew = item.getIsOUNew();
        String underOdds = item.getUOdds();
        String overOdds = item.getOOdds();
        String homeHdpOdds = item.getHOdds();
        String awayHdpOdds = item.getAOdds();
        scrollChild(sl.getChildAt(0), false, item, item.getIsHomeGive(), hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds);
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew("0");

        sl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                sl.setFollowScrolls(slFollowers);
                return false;
            }
        });
        sl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                sl1.setFollowScrolls(slFollowers);

                return false;
            }
        });
        sl.setIndexChangeListener(new ScrollLayout.IndexChangeCallBack() {
            @Override
            public void changePosition(int index) {
                if (slIndex != index) {
                    slIndex = index;

                }
            }
        });

        if (sl.getTargetIndex() != slIndex)
            sl.setCurrentIndex(slIndex);
        if (sl1.getTargetIndex() != slIndex)
            sl1.setCurrentIndex(slIndex);
    }

    private void addAdditionHTFT(HTFTBean htft, LinearLayout parent, I item) {

    }

    private void addAdditionFT15MINSHANDICAP_OVER_UNDER(AdditionBean.FT15MINSHANDICAPOVERUNDERBean tg, String title, LinearLayout parent, I item) {

    }

    private void addAdditionTEAMTG(AdditionBean.TEAMTGBean tg, boolean isAway, LinearLayout parent, I item) {

    }

    private void addAdditionTG(TGBean tg, LinearLayout parent, I item) {

    }

    private void addAdditionFGLG(FGLGBean fglg, LinearLayout parent, I item) {

    }

    private void addAdditionOE(FTOEBean ftoe, boolean isHalf, LinearLayout parent, I item) {

    }


    private void addAdditionDC(DCBean ftdc, boolean isHalf, LinearLayout parent, I item) {

    }


    private void addAddition1x2(F1x2Bean ft1x2, boolean isHalf, LinearLayout parent, I item) {

    }

    private void addAddition(String f1, String f2, String oid, boolean isHalf, LinearLayout parent, I item, String up1, String up2, String type1, String type2, String sc1, String sc2, int itemRes) {
        addAddition(f1, f2, "", oid, isHalf, parent, item,
                up1, up2, "", type1, type2, "", sc1, sc2, "", R.layout.addition_1x2_sport_item);
        View viewById = parent.getChildAt(parent.getChildCount() - 1).findViewById(R.id.content3_ll);
        viewById.setVisibility(View.GONE);
    }

    private void addAdditionByColor(String f1, String f2, String oid, boolean isHalf, LinearLayout parent, I item, String up1, String up2, String type1, String type2, String sc1, String sc2, int itemRes,String colorType) {
        addAdditionByColor(f1, f2, "", oid, isHalf, parent, item,
                up1, up2, "", type1, type2, "", sc1, sc2, "", R.layout.addition_1x2_sport_item,colorType);
        View viewById = parent.getChildAt(parent.getChildCount() - 1).findViewById(R.id.content3_ll);
        viewById.setVisibility(View.GONE);
    }

    private void addAddition(String f1, String x, String f2, String oid, boolean isHalf, LinearLayout parent, I item,
                             String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3, int itemRes) {

        View inflate1X2 = LayoutInflater.from(context).inflate(itemRes, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);
        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);
        setTextValueClick(down1_tv, f1, type1, oid, item, isHalf, sc1);
        setTextValueClick(down2_tv, x, type2, oid, item, isHalf, sc2);
        setTextValueClick(down3_tv, f2, type3, oid, item, isHalf, sc3);
        parent.addView(inflate1X2);
    }

    private void addAdditionByColor(String f1, String x, String f2, String oid, boolean isHalf, LinearLayout parent, I item,
                             String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3, int itemRes,String colorType) {

        View inflate1X2 = LayoutInflater.from(context).inflate(itemRes, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);
        if(colorType.equals("1")){
            up1_tv.setTextColor(Color.BLUE);
            down1_tv.setTextColor(Color.BLACK);
            up2_tv.setTextColor(Color.RED);
            down2_tv.setTextColor(Color.BLACK);
        }else if(colorType.equals("2")){
            up1_tv.setTextColor(Color.RED);
            down1_tv.setTextColor(Color.BLACK);
            up2_tv.setTextColor(Color.RED);
            down2_tv.setTextColor(Color.BLACK);
        }else if(colorType.equals("3")){
            up1_tv.setTextColor(Color.GRAY);
            down1_tv.setTextColor(Color.RED);
            up2_tv.setTextColor(Color.GRAY);
            down2_tv.setTextColor(Color.BLACK);
        }
        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);
        setTextValueClick(down1_tv, f1, type1, oid, item, isHalf, sc1);
        setTextValueClick(down2_tv, x, type2, oid, item, isHalf, sc2);
        setTextValueClick(down3_tv, f2, type3, oid, item, isHalf, sc3);
        parent.addView(inflate1X2);
    }



    private void addTitle(LinearLayout parent, View inflate, int titleRes) {
        addTitle(parent, inflate, context.getString(titleRes));
    }

    private void addTitle(LinearLayout parent, View inflate, String title) {
        TextView titleTv = inflate.findViewById(R.id.title_tv);
        titleTv.setText(title);
        parent.addView(inflate);
    }

    private void setTextValueClick(final TextView textView, final String content, final String type, final String oid, final I item, final boolean isHalf, final String sc) {
        textView.setText(content);
        if (StringUtils.isNull(content) || StringUtils.isNull(type) || StringUtils.isNull(oid)) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d("xxxxx", item.toString());
                back.clickOdds(textView, item, type, isHalf, content, Integer.valueOf(oid), sc);
            }
        });

    }

    private void setTextValue(TextView tv, String s) {
        tv.setText(s);
    }

    private void addAdditionView(LinearLayout parent, AddMBean.OddsBean fTodds, AddMBean.OddsBean fHodds, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_sport_item, null);
        TextView home_tv = inflate.findViewById(R.id.home_tv);
        TextView home_odd_tv = inflate.findViewById(R.id.home_odd_tv);
        TextView away_tv = inflate.findViewById(R.id.away_tv);
        TextView away_odd_tv = inflate.findViewById(R.id.away_odd_tv);

        TextView hf_home_tv = inflate.findViewById(R.id.hf_home_tv);
        TextView hf_home_odd_tv = inflate.findViewById(R.id.hf_home_odd_tv);
        TextView hf_away_tv = inflate.findViewById(R.id.hf_away_tv);
        TextView hf_away_odd_tv = inflate.findViewById(R.id.hf_away_odd_tv);

        TextView over_tv = inflate.findViewById(R.id.over_tv);
        TextView over_odd_tv = inflate.findViewById(R.id.over_odd_tv);
        TextView under_tv = inflate.findViewById(R.id.under_tv);
        TextView under_odd_tv = inflate.findViewById(R.id.under_odd_tv);

        TextView hf_over_tv = inflate.findViewById(R.id.hf_over_tv);
        TextView hf_over_odd_tv = inflate.findViewById(R.id.hf_over_odd_tv);
        TextView hf_under_tv = inflate.findViewById(R.id.hf_under_tv);
        TextView hf_under_odd_tv = inflate.findViewById(R.id.hf_under_odd_tv);

         /*       ballInfo.setHome(fTodds.getHome());
        ballInfo.setAway(fTodds.getAway());*/
   /*      ballInfo.setOver(fTodds.getOver());
        ballInfo.setUnder(fTodds.getUnder());*/
        BallInfo ballInfo = new BallInfo();
        ballInfo.setModuleTitle(item.getModuleTitle());
        ballInfo.setModuleId(item.getModuleId());
        ballInfo.setHome(item.getHome());
        ballInfo.setHomeId(item.getHomeId());
        ballInfo.setAway(item.getAwayId());
        if (fTodds != null) {
            ballInfo.setSocOddsId(fTodds.getSocOddsId());
            ballInfo.setHdp(fTodds.getHDP());
            ballInfo.setHOdds(fTodds.getHomeOdds());
            ballInfo.setAOdds(fTodds.getAwayOdds());
            ballInfo.setOU(fTodds.getOU());
            ballInfo.setOOdds(fTodds.getOverOdds());
            ballInfo.setUOdds(fTodds.getUnderOdds());
            ballInfo.setIsHomeGive(fTodds.getIsHomeGive());
            ballInfo.setHasPar(fTodds.getHasPar());

        } else {
            ballInfo.setSocOddsId("");
            ballInfo.setHdp("");
            ballInfo.setHOdds("");
            ballInfo.setAOdds("");
            ballInfo.setOU("");
            ballInfo.setOOdds("");
            ballInfo.setUOdds("");
            ballInfo.setIsHomeGive("");
            ballInfo.setHasPar("");
        }
        if (fHodds != null) {
            ballInfo.setSocOddsId_FH(fHodds.getSocOddsId());
            ballInfo.setHdp_FH(fHodds.getHDP());
            ballInfo.setHOdds_FH(fHodds.getHomeOdds());
            ballInfo.setAOdds_FH(fHodds.getAwayOdds());
            ballInfo.setOU_FH(fHodds.getOU());
            ballInfo.setOOdds_FH(fHodds.getOverOdds());
            ballInfo.setUOdds_FH(fHodds.getUnderOdds());
            ballInfo.setIsHomeGive_FH(fHodds.getIsHomeGive());
            ballInfo.setHasPar_FH(fHodds.getHasPar());

        } else {
            ballInfo.setSocOddsId_FH("");
            ballInfo.setHdp_FH("");
            ballInfo.setHOdds_FH("");
            ballInfo.setAOdds_FH("");
            ballInfo.setOU_FH("");
            ballInfo.setOOdds_FH("");
            ballInfo.setUOdds_FH("");
            ballInfo.setIsHomeGive_FH("");
            ballInfo.setHasPar_FH("");
        }
        setUpDownOdds(true, (I) ballInfo, false, "0", ballInfo.getHasHdp(), ballInfo.getHdp(), home_tv, away_tv, home_odd_tv, away_odd_tv, ballInfo.getHOdds(), ballInfo.getAOdds(), "home", "away", 1, null, null);
        setUpDownOdds(true, (I) ballInfo, true, "0", ballInfo.getHasHdp_FH(), ballInfo.getHdp_FH(), hf_home_tv, hf_away_tv, hf_home_odd_tv, hf_away_odd_tv, ballInfo.getHOdds_FH(), ballInfo.getAOdds_FH(), "home", "away", 1, null, null);
        //setUpDownOdds(ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv, overOdds, underOdds, overOddsType, underOddsType);
        setUpDownOdds(true, (I) ballInfo, false, "0", ballInfo.getHasOU(), ballInfo.getOU(), over_tv, under_tv, over_odd_tv, under_odd_tv, ballInfo.getOOdds(), ballInfo.getUOdds(), "over", "under", 1, null, null);
        setUpDownOdds(true, (I) ballInfo, true, "0", ballInfo.getHasOU_FH(), ballInfo.getOU_FH(), hf_over_tv, hf_under_tv, hf_over_odd_tv, hf_under_odd_tv, ballInfo.getOOdds_FH(), ballInfo.getUOdds_FH(), "over", "under", 1, null, null);
        parent.addView(inflate);
    }

    protected void updateMixBackground(BallInfo item, ScrollLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        BaseMixStyleHandler handler = new BaseMixStyleHandler((BaseToolbarActivity) context);
        handler.updateMixBackground(item, sl, type01, type02, type11, type12, type21, type22);
    }

    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, I item, int position) {
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        tvRightMark.setVisibility(View.VISIBLE);
        View collectionTv = helper.getView(R.id.module_match_collection_tv);
        collectionTv.setVisibility(View.VISIBLE);
    }

    protected void onMatchRepeat(MyRecyclerViewHolder helper, I item, int position) {
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        tvRightMark.setVisibility(View.INVISIBLE);
        View collectionTv = helper.getView(R.id.module_match_collection_tv);
        collectionTv.setVisibility(View.INVISIBLE);
    }


    public View scrollChild(View vp, boolean isFh, I item, String isHomeGive, String hasHdp, String hdp, String hasOU, String ou, String isHdpNew, String isOUNew, String underOdds, String overOdds, String homeHdpOdds, String awayHdpOdds) {
        return scrollChild(vp, isFh, item, isHomeGive, hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds, "home", "away", "over", "under", true, true, false, "", "", "", "", "", "");
    }


    public View scrollChild(View vp, boolean isFh, I item, String isHomeGive, String hasHdp, String hdp, String hasOU, String ou, String isHdpNew, String isOUNew, String underOdds, String overOdds, String homeHdpOdds, String awayHdpOdds, String homeOddsType, String awayOddsType, String overOddsType, String underOddsType
            , boolean hapVisiable, boolean ouVisiable, boolean oeVisiable, String hasOE, String isOENew, String OddOdds, String EvenOdds, String OddOddsType, String EvenOddsType) {
        vp.setVisibility(View.VISIBLE);
        ViewHolder holder = new ViewHolder(vp);
        setUpDownOdds(hapVisiable, item, isFh, isHdpNew, hasHdp, hdp, holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv, holder.viewpagerMatchVisitHdpoddsTv
                , homeHdpOdds, awayHdpOdds, homeOddsType, awayOddsType, 0, holder.imgUpDownUp1, holder.imgUpDownDown1);
        setUpDownOdds(ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv
                , overOdds, underOdds, overOddsType, underOddsType, 0, holder.imgUpDownUp2, holder.imgUpDownDown2);
    /*    setUpDownOdds(oeVisiable, item, isFh, isOENew, hasOE, "", holder.viewpagerOddLabelTv, holder.viewpagerEvenLabelTv, holder.viewpagerMatchOddTv, holder.viewpagerMatchEvenTv
                , OddOdds, EvenOdds, OddOddsType, EvenOddsType);*/
        return vp;
    }

    public void setUpDownOdds(boolean visiableUpDown, I item, boolean isFh, String isNew, String hasUpDown, String upDown, TextView upTextTv, TextView downTextTv, TextView upOddsTv, TextView downOddsTv, String upOdds, String downOdds, String upType, String downType, int visibilityType, ImageView imgUpDown1, ImageView imgUpDown2) {

        if (visiableUpDown) {
            upTextTv.setVisibility(View.VISIBLE);
            upOddsTv.setVisibility(View.VISIBLE);
            downTextTv.setVisibility(View.VISIBLE);
            downOddsTv.setVisibility(View.VISIBLE);
            String upStr = "";
            String downStr = "";
            downTextTv.setTextSize(12);
            upTextTv.setTextSize(12);
            if (upOdds.trim().isEmpty() || downOdds.trim().isEmpty() || upOdds.equals("0") || downOdds.equals("0") || Math.abs(Float.valueOf(upOdds)) < 0.5 || Math.abs(Float.valueOf(downOdds)) < 0.5) {
                hasUpDown = "0";
            }
            int resUp = R.mipmap.arrow_up_update_green;
            int resDown = R.mipmap.arrow_up_update_green;
            switch (upType) {
                case "home":
                case "mmhome":

                    if (upDown.isEmpty()) {
                        hasUpDown = "0";
                    }
                    String hdpS = upDown;
                    if (!upDown.equals("0"))
                        hdpS = "-" + upDown;
                    if (item.getIsHomeGive().equals("1")) {
                        upStr = hdpS;
                        downStr = "";
                    } else {
                        upStr = "";
                        downStr = hdpS;
                    }
                    if (upType.startsWith("mm")) {
                        downTextTv.setTextSize(8);
                        upTextTv.setTextSize(8);
                    }
                    upTextTv.setTextColor(Color.RED);
                    downTextTv.setTextColor(Color.RED);

                    if (isFh) {
                        if (item.isHomeBigger_FH)
                            resUp = R.mipmap.arrow_up_update_green;
                        else
                            resUp = R.mipmap.arrow_down_update_red;
                    } else {
                        if (item.isHomeBigger)
                            resUp = R.mipmap.arrow_up_update_green;
                        else
                            resUp = R.mipmap.arrow_down_update_red;
                    }

                    break;
                case "over":
                case "mmover":
                    if (upDown.isEmpty() || upDown.equals("0")) {
                        hasUpDown = "0";
                    }
                    String ouf = upDown;
                    upStr = ouf;
                    downStr = "";
                    if (upType.startsWith("mm")) {
                        downTextTv.setTextSize(8);
                        upTextTv.setTextSize(8);
                    }
                    if (isFh) {
                        if (item.isOverBigger_FH)
                            resUp = R.mipmap.arrow_up_update_green;
                        else
                            resUp = R.mipmap.arrow_down_update_red;
                    } else {
                        if (item.isOverBigger)
                            resUp = R.mipmap.arrow_up_update_green;
                        else
                            resUp = R.mipmap.arrow_down_update_red;
                    }
                    break;

                case "1":
                    upStr = "1";
                    downStr = "2";
                    break;
                case "odd":
                    upStr = context.getString(R.string.ODD);
                    downStr = context.getString(R.string.EVEN);
                    break;

            }
            switch (downType) {
                case "away":
                case "mmaway":
                    if (isFh) {
                        if (item.isAwayBigger_FH)
                            resDown = R.mipmap.arrow_up_update_green;
                        else
                            resDown = R.mipmap.arrow_down_update_red;
                    } else {
                        if (item.isAwayBigger)
                            resDown = R.mipmap.arrow_up_update_green;
                        else
                            resDown = R.mipmap.arrow_down_update_red;
                    }
                    break;
                case "under":
                case "mmunder":
                    if (isFh) {
                        if (item.isUnderBigger_FH)
                            resDown = R.mipmap.arrow_up_update_green;
                        else
                            resDown = R.mipmap.arrow_down_update_red;
                    } else {
                        if (item.isUnderBigger)
                            resDown = R.mipmap.arrow_up_update_green;
                        else
                            resDown = R.mipmap.arrow_down_update_red;
                    }
                    break;
            }
            View downParent = (View) downOddsTv.getParent();
            View upParent = (View) upOddsTv.getParent();
            if (hasUpDown.equals("0") || hasUpDown.equals("")) {
                upTextTv.setText("");
                upOddsTv.setText("");
                downTextTv.setText("");
                downOddsTv.setText("");
                if (visibilityType == 0) {
                    downParent.setVisibility(View.INVISIBLE);
                    upParent.setVisibility(View.INVISIBLE);
                }
            } else {
                if (visibilityType == 0) {
                    downParent.setVisibility(View.VISIBLE);
                    upParent.setVisibility(View.VISIBLE);
                }
                upTextTv.setText(upStr);
                if (TextUtils.isEmpty(downStr) && downTextTv.getId() == R.id.viewpager_match_ou2_tv) {
                    downTextTv.setText(upStr);
                } else {
                    downTextTv.setText(downStr);
                }
                boolean isAnimation = false;
                if (isNew != null && isNew.equals("1")) {
                    isAnimation = true;
                }
                setValue(item, upOddsTv, upOdds, false, upType, isFh, R.drawable.green_trans_shadow_top, true, 0, imgUpDown1);
                setValue(item, upTextTv, upOdds, isAnimation, upType, isFh, R.drawable.green_trans_shadow_top, false, resUp, imgUpDown1);
                setValue(item, downOddsTv, downOdds, false, downType, isFh, R.drawable.green_trans_shadow_bottom, true, 0, imgUpDown2);
                setValue(item, downTextTv, downOdds, isAnimation, downType, isFh, R.drawable.green_trans_shadow_bottom, false, resDown, imgUpDown2);
            }
        } else {
            upTextTv.setVisibility(View.GONE);
            upOddsTv.setVisibility(View.GONE);
            downTextTv.setVisibility(View.GONE);
            downOddsTv.setVisibility(View.GONE);
        }
    }


    private String changeValueF(String f) {
        if (f.equals("") || f.startsWith("-"))
            return "";

        try {
            float v = Float.valueOf(f);
            int i;
            i = (int) (v / 0.5);   //先取商
            if (v == 0.5 * i) {
                return subZeroAndDot(v);
            } else {
                return subZeroAndDot((float) (v - 0.25)) + "-" + subZeroAndDot((float) (v + 0.25));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    String subZeroAndDot(float s) {
        String ss = s + "";
        if (ss.indexOf(".") > 0) {
            ss = ss.replaceAll("0+?$", "");//去掉多余的0
            ss = ss.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return ss;
    }

    protected String setValue(final I item, final TextView textView, final String f, boolean isAnimation, final String type, final boolean isHf, final int resBg, boolean isShowText, final int resUpdate, final ImageView imgUpDown) {
        String value = f;
        if (f.equals("0")) {
            textView.setText("");
        } else {
            textView.setBackgroundResource(0);
            if (!f.equals("") && !f.equals("0")) {
                if (!type.equals("1") && !type.equals("2") && !type.equalsIgnoreCase("x"))
                    value = AfbUtils.changeValueS(f);

                else {
                    value = AfbUtils.decimalValue(Float.valueOf(f), "0.00");
                }
                if (isAnimation && imgUpDown != null) {
                    imgUpDown.setImageResource(resUpdate);
                    imgUpDown.setVisibility(View.VISIBLE);
                }
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d("xxxxx", item.toString());
                        back.clickOdds((TextView) v, item, type, isHf, f, Integer.valueOf(isHf ? item.getSocOddsId_FH() : item.getSocOddsId()), "");
                    }
                });
            } else {
                textView.setOnClickListener(null);
            }
            if (isShowText) {
                if (Float.valueOf(value) < 0) {
                    textView.setTextColor(red_black);
                } else {
                    textView.setTextColor(black_grey);
                }
                textView.setText(value);
            }
        }


        return f;
    }

    @Override
    public int onSetAdapterItemLayout() {
        return R.layout.sport_common_ball_item;
    }

    Map<Boolean, Integer> additionMap = new HashMap<>();

    public void changeAddition(int position) {
        Integer p = additionMap.get(true);
        if (p != null && p == position) {
            additionMap.put(true, -1);
        } else {
            additionMap.put(true, position);
        }
    }


    public static class ViewHolder {
        @Bind(R.id.viewpager_match_home_hdp_tv)
        public TextView viewpagerMatchHomeHdpTv;
        @Bind(R.id.viewpager_match_home_hdpodds_tv)
        public TextView viewpagerMatchHomeHdpoddsTv;
        @Bind(R.id.viewpager_match_ou_tv)
        public TextView viewpagerMatchOuTv;
        @Bind(R.id.viewpager_match_overodds_tv)
        public TextView viewpagerMatchOveroddsTv;
        @Bind(R.id.viewpager_odd_label_tv)
        public TextView viewpagerOddLabelTv;
        @Bind(R.id.viewpager_match_odd_tv)
        public TextView viewpagerMatchOddTv;
        @Bind(R.id.viewpager_match_visit_hdp_tv)
        public TextView viewpagerMatchVisitHdpTv;
        @Bind(R.id.viewpager_match_visit_hdpodds_tv)
        public TextView viewpagerMatchVisitHdpoddsTv;
        @Bind(R.id.viewpager_match_ou2_tv)
        public TextView viewpagerMatchOu2Tv;
        @Bind(R.id.viewpager_match_underodds_tv)
        public TextView viewpagerMatchUnderoddsTv;
        @Bind(R.id.viewpager_even_label_tv)
        public TextView viewpagerEvenLabelTv;
        @Bind(R.id.viewpager_match_even_tv)
        public TextView viewpagerMatchEvenTv;
        @Bind(R.id.img_up_down_up1)
        public ImageView imgUpDownUp1;
        @Bind(R.id.img_up_down_up2)
        public ImageView imgUpDownUp2;
        @Bind(R.id.img_up_down_down1)
        public ImageView imgUpDownDown1;
        @Bind(R.id.img_up_down_down2)
        public ImageView imgUpDownDown2;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setRunningItemBg(MyRecyclerViewHolder helper, BallInfo item) {
        View matchTitleLl = helper.getView(R.id.module_match_title_ll);
        View viewLine = helper.getView(R.id.view_line);
        TextView moduleMatchTimeTv = helper.getView(R.id.module_match_time_tv);
        viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.green_line));
        matchTitleLl.setBackgroundColor(ContextCompat.getColor(context, R.color.green_title));
        moduleMatchTimeTv.setTextColor(Color.BLACK);
    }
}
