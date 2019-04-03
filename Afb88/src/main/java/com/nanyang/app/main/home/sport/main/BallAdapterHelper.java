package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.CSBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class BallAdapterHelper<I extends BallInfo> extends SportAdapterHelper<I> {
    final int red_black = 0XFFad0c11;

    final int black_grey = 0XFF333333;
    protected Context context;
    private AdditionBean additionData;
    private int additionPosition;


    public Set<ScrollLayout> getSlFollowers() {
        return slFollowers;
    }

    protected Set<ScrollLayout> slFollowers = new HashSet<>();

    public void setSlIndex(int slIndex) {
        this.slIndex = slIndex;
    }

    private int slIndex = 0;

    public synchronized void notifyPositionAddition(AdditionBean data, int position) {
        this.additionData = data;
        this.additionPosition = position;
        getBaseRecyclerAdapter().notifyItemChanged(position);

    }

    public BallAdapterHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final I item) {
        LinearLayout parent = helper.getView(R.id.common_ball_parent_ll);
        List<BallInfo> repeatRow = item.getRepeatRow();

        if (position == additionPosition && additionMap.get(true) != null && additionMap.get(true) == additionPosition) {
            LogUtil.d("Addition", "--------------repeatRow:" + (repeatRow == null ? "null" : repeatRow.size()));
            parent.setVisibility(View.VISIBLE);
            parent.removeAllViews();
            View titleLL = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_title_item, null);
            parent.addView(titleLL);
            if (repeatRow == null || repeatRow.size() == 0) {
                addAdditionView(parent, item);
            } else {
                for (BallInfo ballInfo : repeatRow) {
                    addAdditionView(parent, ballInfo);
                }
            }
            if (additionData != null) {
                if (additionData.getFT1x2() != null && additionData.getFT1x2().getOid() >= 0) {
                    addAddition1x2(additionData.getFT1x2().getF1(), additionData.getFT1x2().getX(), additionData.getFT1x2().getF2(), additionData.getFT1x2().getOid(), false, parent, item,
                            "1", "X", "2", "1", "x", "2");
                }
                if (additionData.getFH1x2() != null && additionData.getFH1x2().getOid() >= 0) {
                    addAddition1x2(additionData.getFH1x2().getF1(), additionData.getFH1x2().getX(), additionData.getFH1x2().getF2(), additionData.getFH1x2().getOid(), true, parent, item, "1", "X", "2", "1", "x", "2");
                }
                if (additionData.getFTCS() != null && additionData.getFTCS().getOid() >= 0) {//波胆
                    addAdditionCS(additionData.getFTCS(), false, parent, item,"70");
                }
                if (additionData.getFHCS() != null && additionData.getFHCS().getOid() >= 0) {
                    addAdditionCS(additionData.getFHCS(), true, parent, item,"80");
                }
/*
                if (additionData.getFTDC() != null && additionData.getFTDC().getOid() >= 0) {
                    addAdditionFTDC(additionData.getFTDC());
                }
                if (additionData.getFHDC() != null && additionData.getFHDC().getOid() >= 0) {
                    addAdditionFHDC(additionData.getFHDC());
                }
                if (additionData.getFTOE() != null && additionData.getFTOE().getOid() >= 0) {
                    addAdditionFTOE(additionData.getFTOE());
                }
                if (additionData.getFHOE() != null && additionData.getFHOE().getOid() >= 0) {
                    addAdditionFHOE(additionData.getFHOE());
                }

                if (additionData.getHTFT() != null && additionData.getFTOE().getOid() >= 0) {//半场全场
                    addAdditionFTOE(additionData.getFTOE());
                }
                if (additionData.getFGLG() != null && additionData.getFGLG().getOid() >= 0) {//最后得分 最先得分
                    addAdditionFGLG(additionData.getFGLG());
                }
                if (additionData.getTG() != null && additionData.getTG().getOid() >= 0) {//最后得分 最先得分
                    addAdditionTG(additionData.getTG());
                }
                if (additionData.getHOMETEAMTG() != null && additionData.getHOMETEAMTG().getOid() >= 0) {//最后得分 最先得分
                    addAdditionHOMETEAMTG(additionData.getHOMETEAMTG());
                }
                if (additionData.getAWAYTEAMTG() != null && additionData.getAWAYTEAMTG().getOid() >= 0) {//最后得分 最先得分
                    addAdditionAWAYTEAMTG(additionData.getAWAYTEAMTG());
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_0() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_0().getOid() >= 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER_0(additionData.getFT15MINSHANDICAP_OVER_UNDER_0());
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_15() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_15().getOid() >= 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER_15(additionData.getFT15MINSHANDICAP_OVER_UNDER_15());
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N().getOid() >= 0) {
                    addAdditgetFT15MINSHANDICAP_OVER_UNDER_30_N(additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N());
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_45() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_45().getOid() >= 0) {
                    addAdditgetFT15MINSHANDICAP_OVER_UNDER_45(additionData.getFT15MINSHANDICAP_OVER_UNDER_45());
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_60() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_60().getOid() >= 0) {
                    addAdditgetFT15MINSHANDICAP_OVER_UNDER_60(additionData.getFT15MINSHANDICAP_OVER_UNDER_60());
                }*/
//                 rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_0().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_0().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_0().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)", "", "", ""));

            }
        } else {
            parent.setVisibility(View.GONE);

        }
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
        helper.getView(R.id.iv_hall_btn).setVisibility(View.GONE);
        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
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
        if (time.length() > 6) {
            time = time.substring(time.length() - 7, time.length());
            time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
        }
        timeTv.setText(time);
        timeTv1.setText(time);

        if (!item.getLive().equals("")) {
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
            if (item.getMatchDate().length() > 6) {
                String date = item.getMatchDate().substring(0, 5);
                dateTv.setText(date);
                dateTv1.setText(date);
            } else {
                dateTv.setText(item.getMatchDate());
                dateTv1.setText(item.getMatchDate());
            }
        }
        tvCollection.setVisibility(View.GONE);
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
        if (item.getType() == SportInfo.Type.ITME) {
            matchTitleTv.setVisibility(View.GONE);
            headV.setVisibility(View.GONE);

        } else {
            matchTitleTv.setVisibility(View.VISIBLE);
            headV.setVisibility(View.VISIBLE);
            matchTitleTv.setText(item.getModuleTitle());
            if (position == 0) {
                headV.setVisibility(View.GONE);
            }
        }
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
        scrollChild(sl.getChildAt(0), false, item, isHomeGive, hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds);
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew("0");
        if (!slFollowers.contains(sl)) {
            slFollowers.add(sl);
            slFollowers.add(sl1);
        }
        sl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ScrollLayout scrollLayout = back.onSetHeaderFollower();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        slFollowers.add(scrollLayout);
                        break;
                }

                sl.setFollowScrolls(slFollowers);

                return false;
            }
        });
        sl1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ScrollLayout scrollLayout = back.onSetHeaderFollower();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        slFollowers.add(scrollLayout);
                        break;
                }

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

    private void addAdditionCS(CSBean ftcs, boolean isHalf, LinearLayout parent, I item, String aos) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, R.string.correct_full);
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "3:1", "4:4", "1:3", "csr", "csr", "csr", "31", "44", "13");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "3:2", "AOS", "2:3", "csr", "csr", "csr", "32", aos, "23");

        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "4:0", "", "0:4", "csr", "csr", "csr", "40", "", "4");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "4:1", "", "1:4", "csr", "csr", "csr", "41", "", "14");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "4:2", "", "2:4", "csr", "csr", "csr", "42", "", "24");
        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "4:3", "", "3:4", "csr", "csr", "csr", "43", "", "34");
    }


    private void addAddition1x2(String f1, String x, String f2, int oid, boolean isHalf, LinearLayout parent, I item,
                                String up1, String up2, String up3, String type1, String type2, String type3) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, R.string.FULL_1X2);
        View inflate1X2 = LayoutInflater.from(context).inflate(R.layout.addition_1x2_sport_item, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);
        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);
        setTextValueClick(down1_tv, f1, type1, oid, item, isHalf, "");
        setTextValueClick(down2_tv, x, type2, oid, item, isHalf, "");
        setTextValueClick(down3_tv, f2, type3, oid, item, isHalf, "");
        parent.addView(inflate1X2);
    }

    private void addAddition(String f1, String x, String f2, int oid, boolean isHalf, LinearLayout parent, I item,
                             String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3) {


        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, R.string.FULL_1X2);
        View inflate1X2 = LayoutInflater.from(context).inflate(R.layout.addition_1x2_sport_item, null);
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

    private void addTitle(LinearLayout parent, View inflate, int titleRes) {
        TextView titleTv = inflate.findViewById(R.id.title_tv);
        titleTv.setText(titleRes);
        parent.addView(inflate);
    }

    private void setTextValueClick(TextView textView, String content, String type, int oid, I item, boolean isHalf, String sc) {
        textView.setText(content);
        back.clickOdds(textView, item, type, isHalf, content, oid, sc);
    }

    private void setTextValue(TextView tv, String s) {
        tv.setText(s);
    }

    private void addAdditionView(LinearLayout parent, BallInfo ballInfo) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_sport_item, null);
        TextView home_tv = inflate.findViewById(R.id.home_tv);
        TextView home_odd_tv = inflate.findViewById(R.id.home_odd_tv);
        TextView away_tv = inflate.findViewById(R.id.away_tv);
        TextView away_odd_tv = inflate.findViewById(R.id.away_odd_tv);

        TextView hf_home_tv = inflate.findViewById(R.id.hf_home_tv);
        TextView hf_home_odd_tv = inflate.findViewById(R.id.hf_home_odd_tv);
        TextView hf_away_tv = inflate.findViewById(R.id.hf_away_tv);
        TextView hf_away_odd_tv = inflate.findViewById(R.id.hf_away_odd_tv);
        setUpDownOdds(true, (I) ballInfo, false, "0", ballInfo.getHasHdp(), ballInfo.getHdp(), home_tv, away_tv, home_odd_tv, away_odd_tv, ballInfo.getHOdds(), ballInfo.getAOdds(), "home", "away");
        setUpDownOdds(true, (I) ballInfo, true, "0", ballInfo.getHasHdp_FH(), ballInfo.getHdp_FH(), hf_home_tv, hf_away_tv, hf_home_odd_tv, hf_away_odd_tv, ballInfo.getHOdds_FH(), ballInfo.getAOdds_FH(), "home", "away");
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
                , homeHdpOdds, awayHdpOdds, homeOddsType, awayOddsType);
        setUpDownOdds(ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv
                , overOdds, underOdds, overOddsType, underOddsType);
        setUpDownOdds(oeVisiable, item, isFh, isOENew, hasOE, "", holder.viewpagerOddLabelTv, holder.viewpagerEvenLabelTv, holder.viewpagerMatchOddTv, holder.viewpagerMatchEvenTv
                , OddOdds, EvenOdds, OddOddsType, EvenOddsType);
        return vp;
    }

    public void setUpDownOdds(boolean visiableUpDown, I item, boolean isFh, String isNew, String hasUpDown, String upDown, TextView upTextTv, TextView downTextTv, TextView upOddsTv, TextView downOddsTv, String upOdds, String downOdds, String upType, String downType) {

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
            switch (upType) {
                case "home":
                case "mmhome":

                    if (upDown.isEmpty()) {
                        hasUpDown = "0";
                    }

                    String hdpS = changeValueF(upDown);
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
                    break;
                case "over":
                case "mmover":
                    if (upDown.isEmpty() || upDown.equals("0")) {
                        hasUpDown = "0";
                    }
                    String ouf = changeValueF(upDown);
                    upStr = ouf;
                    downStr = "";
                    if (upType.startsWith("mm")) {
                        downTextTv.setTextSize(8);
                        upTextTv.setTextSize(8);
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

            if (hasUpDown.equals("0") || hasUpDown.equals("")) {
                upTextTv.setText("");
                upOddsTv.setText("");
                downTextTv.setText("");
                downOddsTv.setText("");
            } else {
                upTextTv.setText(upStr);
                downTextTv.setText(downStr);
                boolean isAnimation = false;
                if (isNew != null && isNew.equals("1")) {
                    isAnimation = true;
                }
                setValue(item, upOddsTv, upOdds, isAnimation, upType, isFh, R.drawable.green_trans_shadow_top, true);
                setValue(item, upTextTv, upOdds, false, upType, isFh, R.drawable.green_trans_shadow_top, false);
                setValue(item, downOddsTv, downOdds, isAnimation, downType, isFh, R.drawable.green_trans_shadow_bottom, true);
                setValue(item, downTextTv, downOdds, false, downType, isFh, R.drawable.green_trans_shadow_bottom, false);
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

    protected String setValue(final I item, final TextView textView, final String f, boolean isAnimation, final String type, final boolean isHf, final int resBg, boolean isShowText) {
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
                if (isAnimation) {
                    Animation animation = new AlphaAnimation(0.0f, 1.0f);
                    //设置动画时间
                    animation.setDuration(1000);
                    animation.setRepeatCount(3);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            ((BaseActivity) context).dynamicAddView(textView, "background", resBg);
//                            textView.setBackgroundResource(resBg);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            textView.setBackgroundResource(0);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            ((BaseActivity) context).dynamicAddView(textView, "background", resBg);
                        }
                    });
                    textView.startAnimation(animation);
                } else {
                    textView.setBackgroundResource(0);
                }

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}
