package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.CSBean;
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
import java.util.List;
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
//            LogUtil.d("Addition", "--------------repeatRow:" + (repeatRow == null ? "null" : repeatRow.size()));
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
                if (additionData.getFT1x2() != null && additionData.getFT1x2().getOid() > 0) {
                    addAddition1x2(additionData.getFT1x2(), false, parent, item);
                }
                if (additionData.getFH1x2() != null && additionData.getFH1x2().getOid() > 0) {
                    addAddition1x2(additionData.getFH1x2(), true, parent, item);
                }
                if (additionData.getFTCS() != null && additionData.getFTCS().getOid() > 0) {//波胆
                    addAdditionCS(additionData.getFTCS(), false, parent, item, "70");
                }
                if (additionData.getFHCS() != null && additionData.getFHCS().getOid() > 0) {
                    addAdditionCS(additionData.getFHCS(), true, parent, item, "80");
                }

                if (additionData.getFTDC() != null && additionData.getFTDC().getOid() > 0) {
                    addAdditionDC(additionData.getFTDC(), false, parent, item);

                }
                if (additionData.getFHDC() != null && additionData.getFHDC().getOid() > 0) {
                    addAdditionDC(additionData.getFHDC(), true, parent, item);
                }

                if (additionData.getFTOE() != null && additionData.getFTOE().getOid() > 0) {
                    addAdditionOE(additionData.getFTOE(), false, parent, item);
                }
                if (additionData.getFHOE() != null && additionData.getFHOE().getOid() > 0) {
                    addAdditionOE(additionData.getFHOE(), true, parent, item);
                }

                if (additionData.getHTFT() != null && additionData.getHTFT().getOid() > 0) {//半场全场
                    addAdditionHTFT(additionData.getHTFT(), parent, item);
                }

                if (additionData.getFGLG() != null && additionData.getFGLG().getOid() > 0) {//最后得分 最先得分
                    addAdditionFGLG(additionData.getFGLG(), parent, item);
                }
                if (additionData.getTG() != null && additionData.getTG().getOid() > 0) {//zho 最先得分
                    addAdditionTG(additionData.getTG(), parent, item);
                }

                if (additionData.getHOMETEAMTG() != null && additionData.getHOMETEAMTG().getOid() > 0) {//最后得分 最先得分
                    addAdditionTEAMTG(additionData.getHOMETEAMTG(), false, parent, item);
                }
                if (additionData.getAWAYTEAMTG() != null && additionData.getAWAYTEAMTG().getOid() > 0) {//最后得分 最先得分
                    addAdditionTEAMTG(additionData.getAWAYTEAMTG(), true, parent, item);
                }
                /*"(00:00-15:00)"
"(15:01-30:00)"
"(30:01-45:00)"
"(45:01-60:00)"
"(60:01-75:00)"*/
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_0() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_0().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_0(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)", parent, item);
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_15() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_15().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_15(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(15:01-30:00)", parent, item);
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_30_N(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(30:01-45:00)", parent, item);
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_45() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_45().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_45(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(45:01-60:00)", parent, item);
                }
                if (additionData.getFT15MINSHANDICAP_OVER_UNDER_60() != null && additionData.getFT15MINSHANDICAP_OVER_UNDER_60().getOid() > 0) {
                    addAdditionFT15MINSHANDICAP_OVER_UNDER(additionData.getFT15MINSHANDICAP_OVER_UNDER_60(), context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(60:01-75:00)", parent, item);
                }
 /*

*/
//                 rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_0().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_0().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_0().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)", "", "", ""));

            }
        } else {
            parent.setVisibility(View.GONE);

        }
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.INVISIBLE);

        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
        TextView LeagueCollectionTv = helper.getView(R.id.module_League_collection_tv);

        View contentParentLl = helper.getView(R.id.content_parent_ll);
        contentParentLl.setBackgroundResource((position % 2 == 0) ? R.color.white : R.color.grey_white);
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
    //  /("htft", Arrays.asList(new VsCellBean(getString(R.string.hh), result.getHTFT().getHH(), /*"&sc=" +*/ "11"
    // , result.getHTFT().getOid()), new VsCellBean(getString(R.string.hd), result.getHTFT().getHD(), /*"&sc=" +*/ "10"
    // , result.getHTFT().getOid()), new VsCellBean(getString(R.string.ha), result.getHTFT().getHA(), /*"&sc=" +*/ "12"
    // , result.getHTFT().getOid())), true, false, getString(R.string.half_full_time), "", "", ""),
    //new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.dh), result.getHTFT().getDH(), /*"&sc=" +*/ "1",
    // result.getHTFT().getOid()), new VsCellBean(getString(R.string.dd), result.getHTFT().getDD(), /*"&sc=" +*/ "0",
    // result.getHTFT().getOid()), new VsCellBean(getString(R.string.da), result.getHTFT().getDA(), /*"&sc=" +*/ "2", result.getHTFT().getOid()))),
    //      new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.ah), result.getHTFT().getAH(), /*"&sc=" +*/ "21",
    // result.getHTFT().getOid()), new VsCellBean(getString(R.string.ad), result.getHTFT().getAD(), /*"&sc=" +*/ "20", result.getHTFT().getOid()), new VsCellBean(getString(R.string.aa), result.getHTFT().getAA(), /*"&sc=" +*/ "22", result.getHTFT().getOid())), true),*/

    private void addAdditionHTFT(HTFTBean htft, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, context.getString(R.string.half_full_time));

        addAddition(htft.getHH(), htft.getHD(), htft.getHA(), htft.getOid(), false, parent, item,
                context.getString(R.string.hh), context.getString(R.string.hd), context.getString(R.string.ha)
                , "htft", "htft", "htft", "11 ", "10", "12", R.layout.addition_htft_sport_item);
        addAddition(htft.getDH(), htft.getDD(), htft.getDA(), htft.getOid(), false, parent, item,
                context.getString(R.string.dh), context.getString(R.string.dd), context.getString(R.string.da)
                , "htft", "htft", "htft", "1 ", "0", "2", R.layout.addition_htft_sport_item);

        addAddition(htft.getAH(), htft.getAD(), htft.getAA(), htft.getOid(), false, parent, item,
                context.getString(R.string.ah), context.getString(R.string.ad), context.getString(R.string.aa)
                , "htft", "htft", "htft", "21 ", "20", "22", R.layout.addition_htft_sport_item);
    }

    private void addAdditionFT15MINSHANDICAP_OVER_UNDER(AdditionBean.FT15MINSHANDICAPOVERUNDERBean tg, String title, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, title);
        addAddition(tg.getO(), tg.getU(), tg.getOid(), false, parent, item,
                context.getString(R.string.O) + tg.getFT_OU(), context.getString(R.string.U) + tg.getFT_OU(), "over", "under", "", "", R.layout.addition_htft_sport_item);
    }

    private void addAdditionTEAMTG(AdditionBean.TEAMTGBean tg, boolean isAway, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        if (isAway)
            addTitle(parent, inflate, context.getString(R.string.AWAY_TEAM_TOTAL_GOALS));
        else
            addTitle(parent, inflate, context.getString(R.string.HOME_TEAM_TOTAL_GOALS));
/*https://www.afb1188.com/Bet/hBetOdds.ashx?BTMD=S&coupon=0&BETID=s|over|1|739583||&_=1554348545809*/
        addAddition(tg.getFT_O(), tg.getFT_U(), tg.getOid(), false, parent, item,
                "FT.Over " + tg.getFT_OU(), "FT.Under " + tg.getFT_OU(), "over", "under", "", "", R.layout.addition_1x2_sport_item);
        addAddition(tg.getFH_O(), tg.getFH_U(), tg.getOid_FH(), true, parent, item,
                "FT.Over " + tg.getFH_OU(), "FT.Under " + tg.getFH_OU(), "over", "under", "", "", R.layout.addition_1x2_sport_item);
    }

    private void addAdditionTG(TGBean tg, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, context.getString(R.string.total_goals));

        addAddition(tg.getT0_1(), tg.getT2_3(), tg.getOid(), false, parent, item,
                "0~1", "2~3", "tg", "tg", "1", "23", R.layout.addition_1x2_sport_item);
        addAddition(tg.getT4_6(), tg.getT7_OVER(), tg.getOid(), false, parent, item,
                "4~6", "7~Over", "tg", "tg", "46", "70", R.layout.addition_1x2_sport_item);
    }

    private void addAdditionFGLG(FGLGBean fglg, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        addTitle(parent, inflate, context.getString(R.string.first_last_goal));

        addAddition(fglg.getHF(), fglg.getNO_GOAL(), fglg.getAF(), fglg.getOid(), false, parent, item,
                "HFG", "NG", "AFG", "fglg", "fglg", "fglg", "10 ", "0", "20", R.layout.addition_htft_sport_item);
        addAddition(fglg.getHL(), "", fglg.getAL(), fglg.getOid(), false, parent, item,
                "HLG", "", "ALG", "fglg", "", "fglg", "1 ", "", "2", R.layout.addition_htft_sport_item);
    }

    private void addAdditionOE(FTOEBean ftoe, boolean isHalf, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        if (isHalf) {
            addTitle(parent, inflate, context.getString(R.string.half_time) + context.getString(R.string.single_double));
        } else {
            addTitle(parent, inflate, context.getString(R.string.full_time) + context.getString(R.string.single_double));
        }

        addAddition(ftoe.getODD(), ftoe.getEVEN(), ftoe.getOid(), isHalf, parent, item,
                "1X", "12", "dc", "dc", "10", "12", R.layout.addition_1x2_sport_item);
    }


    private void addAdditionDC(DCBean ftdc, boolean isHalf, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        if (isHalf) {
            addTitle(parent, inflate, context.getString(R.string.half_time) + context.getString(R.string.double_chance));
        } else {
            addTitle(parent, inflate, context.getString(R.string.full_time) + context.getString(R.string.double_chance));
        }
        addAddition(ftdc.getF1x(), ftdc.getF12(), ftdc.getFx2(), ftdc.getOid(), isHalf, parent, item,
                "1X", "12", "X2", "dc", "dc", "dc", "10", "12", "2", R.layout.addition_1x2_sport_item);
    }

    private void addAdditionCS(CSBean ftcs, boolean isHalf, LinearLayout parent, I item, String aos) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        if (isHalf) {
            addTitle(parent, inflate, R.string.correct_half);
        } else {
            addTitle(parent, inflate, R.string.correct_full);
        }

        addAddition(ftcs.getC1_0(), ftcs.getC0_0(), ftcs.getC0_1(), ftcs.getOid(), isHalf, parent, item,
                "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC2_0(), ftcs.getC1_1(), ftcs.getC0_2(), ftcs.getOid(), isHalf, parent, item,
                "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC2_1(), ftcs.getC2_2(), ftcs.getC1_2(), ftcs.getOid(), isHalf, parent, item,
                "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC3_0(), ftcs.getC3_3(), ftcs.getC0_3(), ftcs.getOid(), isHalf, parent, item,
                "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC3_1(), ftcs.getC4_4(), ftcs.getC1_3(), ftcs.getOid(), isHalf, parent, item,
                "3:1", "4:4", "1:3", "csr", "csr", "csr", "31", "44", "13", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC3_2(), ftcs.getAOS(), ftcs.getC2_3(), ftcs.getOid(), isHalf, parent, item,
                "3:2", "AOS", "2:3", "csr", "csr", "csr", "32", aos, "23", R.layout.addition_cs_sport_item);

        addAddition(ftcs.getC4_0(), "", ftcs.getC0_4(), ftcs.getOid(), isHalf, parent, item,
                "4:0", "", "0:4", "csr", "csr", "csr", "40", "", "4", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC4_1(), "", ftcs.getC1_4(), ftcs.getOid(), isHalf, parent, item,
                "4:1", "", "1:4", "csr", "csr", "csr", "41", "", "14", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC4_2(), "", ftcs.getC2_4(), ftcs.getOid(), isHalf, parent, item,
                "4:2", "", "2:4", "csr", "csr", "csr", "42", "", "24", R.layout.addition_cs_sport_item);
        addAddition(ftcs.getC4_3(), "", ftcs.getC3_4(), ftcs.getOid(), isHalf, parent, item,
                "4:3", "", "3:4", "csr", "csr", "csr", "43", "", "34", R.layout.addition_cs_sport_item);
    }


    private void addAddition1x2(F1x2Bean ft1x2, boolean isHalf, LinearLayout parent, I item) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
        if (isHalf) {
            addTitle(parent, inflate, R.string.HALF_1X2);
        } else {
            addTitle(parent, inflate, R.string.FULL_1X2);
        }
        addAddition(ft1x2.getF1(), ft1x2.getX(), ft1x2.getF2(), ft1x2.getOid(), false, parent, item,
                "1", "X", "2", "1", "x", "2", "", "", "", R.layout.addition_1x2_sport_item);
    }

    private void addAddition(String f1, String f2, int oid, boolean isHalf, LinearLayout parent, I item, String up1, String up2, String type1, String type2, String sc1, String sc2, int itemRes) {

        addAddition(f1, f2, "", oid, isHalf, parent, item,
                up1, up2, "", type1, type2, "", sc1, sc2, "", R.layout.addition_1x2_sport_item);
        View viewById = parent.getChildAt(parent.getChildCount() - 1).findViewById(R.id.content3_ll);
        viewById.setVisibility(View.GONE);
    }

    private void addAddition(String f1, String x, String f2, int oid, boolean isHalf, LinearLayout parent, I item,
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

    private void addTitle(LinearLayout parent, View inflate, int titleRes) {
        addTitle(parent, inflate, context.getString(titleRes));
    }

    private void addTitle(LinearLayout parent, View inflate, String title) {
        TextView titleTv = inflate.findViewById(R.id.title_tv);
        titleTv.setText(title);
        parent.addView(inflate);
    }

    private void setTextValueClick(final TextView textView, final String content, final String type, final int oid, final I item, final boolean isHalf, final String sc) {
        textView.setText(content);
        if (StringUtils.isNull(content) || StringUtils.isNull(type) || oid < 1) {
            return;
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LogUtil.d("xxxxx", item.toString());
                back.clickOdds(textView, item, type, isHalf, content, oid, sc);
            }
        });

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

        TextView over_tv = inflate.findViewById(R.id.over_tv);
        TextView over_odd_tv = inflate.findViewById(R.id.over_odd_tv);
        TextView under_tv = inflate.findViewById(R.id.under_tv);
        TextView under_odd_tv = inflate.findViewById(R.id.under_odd_tv);

        TextView hf_over_tv = inflate.findViewById(R.id.hf_over_tv);
        TextView hf_over_odd_tv = inflate.findViewById(R.id.hf_over_odd_tv);
        TextView hf_under_tv = inflate.findViewById(R.id.hf_under_tv);
        TextView hf_under_odd_tv = inflate.findViewById(R.id.hf_under_odd_tv);

        setUpDownOdds(true, (I) ballInfo, false, "0", ballInfo.getHasHdp(), ballInfo.getHdp(), home_tv, away_tv, home_odd_tv, away_odd_tv, ballInfo.getHOdds(), ballInfo.getAOdds(), "home", "away");
        setUpDownOdds(true, (I) ballInfo, true, "0", ballInfo.getHasHdp_FH(), ballInfo.getHdp_FH(), hf_home_tv, hf_away_tv, hf_home_odd_tv, hf_away_odd_tv, ballInfo.getHOdds_FH(), ballInfo.getAOdds_FH(), "home", "away");
        //setUpDownOdds(ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv, overOdds, underOdds, overOddsType, underOddsType);
        setUpDownOdds(true, (I) ballInfo, false, "0", ballInfo.getHasOU(), ballInfo.getOU(), over_tv, under_tv, over_odd_tv, under_odd_tv, ballInfo.getOOdds(), ballInfo.getUOdds(), "over", "under");
        setUpDownOdds(true, (I) ballInfo, true, "0", ballInfo.getHasOU_FH(), ballInfo.getOU_FH(), hf_over_tv, hf_under_tv, hf_over_odd_tv, hf_under_odd_tv, ballInfo.getOOdds_FH(), ballInfo.getUOdds_FH(), "over", "under");
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
            switch (downOdds) {
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
                downParent.setVisibility(View.INVISIBLE);
                upParent.setVisibility(View.INVISIBLE);
            } else {
                downParent.setVisibility(View.VISIBLE);
                upParent.setVisibility(View.VISIBLE);
                upTextTv.setText(upStr);
                downTextTv.setText(downStr);
                boolean isAnimation = false;
                if (isNew != null && isNew.equals("1")) {
                    isAnimation = true;
                }
                setValue(item, upOddsTv, upOdds, false, upType, isFh, R.drawable.green_trans_shadow_top, true, 0);
                setValue(item, upTextTv, upOdds, isAnimation, upType, isFh, R.drawable.green_trans_shadow_top, false, resUp);
                setValue(item, downOddsTv, downOdds, false, downType, isFh, R.drawable.green_trans_shadow_bottom, true, 0);
                setValue(item, downTextTv, downOdds, isAnimation, downType, isFh, R.drawable.green_trans_shadow_bottom, false, resDown);
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

    protected String setValue(final I item, final TextView textView, final String f, boolean isAnimation, final String type, final boolean isHf, final int resBg, boolean isShowText, final int resUpdate) {
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
                    Animation animation = new AlphaAnimation(0.8f, 1.0f);
                    //设置动画时间
                    animation.setDuration(2000);
                    animation.setRepeatCount(3);
                    animation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            textView.setCompoundDrawablesWithIntrinsicBounds(resUpdate, 0, 0, 0);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            textView.setCompoundDrawablesWithIntrinsicBounds(resUpdate, 0, 0, 0);
                        }
                    });
                    textView.startAnimation(animation);
                } else {
                    textView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setRunningItemBg(MyRecyclerViewHolder helper, int position) {
        View LlMatchContent = helper.getView(R.id.ll_match_content);
        LlMatchContent.setBackgroundResource((position % 2 == 0) ? R.color.green_content1 : R.color.green_content2);
        View matchTitleLl = helper.getView(R.id.module_match_title_ll);
        View viewLine = helper.getView(R.id.view_line);
        TextView moduleMatchTimeTv = helper.getView(R.id.module_match_time_tv);
        viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.green_line));
        matchTitleLl.setBackgroundColor(ContextCompat.getColor(context, R.color.green_title));
        moduleMatchTimeTv.setTextColor(Color.BLACK);
    }
}
