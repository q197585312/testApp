package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.football.SoccerRunningGoalManager;
import com.nanyang.app.main.home.sport.live.LiveSelectedHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.OddsClickBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.BaseMixStyleHandler;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class BallAdapterHelper<I extends BallInfo> extends SportAdapterHelper<I> {
    final int red_black = Color.RED;
    final int black_grey = Color.BLACK;
    public final SportActivity act;
    private boolean allowedEurope = true;
    private BaseMixStyleHandler handler;
    private String curCode = "";
    protected Context context;
    private AddMBean additionData;

    private IRTMatchInfo additionBallItem;


    void setSlIndex(int slIndex) {
        this.slIndex = slIndex;
    }

    private int slIndex = 0;
    LiveSelectedHelper liveSelectedHelper;

    synchronized void notifyPositionAdded(AddMBean data, IRTMatchInfo item) {
        this.additionData = data;
        LogUtil.d("additionMap", additionData.toString());
        LogUtil.d("additionMap", "-------------->" + additionMap.get(true));
        this.additionBallItem = item;
        if (!StringUtils.isNull(additionMap.get(true))) {
            getBaseRecyclerAdapter().notifyDataSetChanged();
        }

    }


    public BallAdapterHelper(Context context) {
        this.context = context;
        act = (SportActivity) this.context;
        if (act != null && act.getApp().getSettingAllDataBean() != null)
            curCode = act.getApp().getSettingAllDataBean().getCurCode();
        this.handler = new BaseMixStyleHandler(act);
        liveSelectedHelper = new LiveSelectedHelper();
    }

    Map<String, MenuItemInfo> scrollPositionMap = new HashMap<>();

    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final I item) {


        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
        View LeagueCollectionTv = helper.getView(R.id.module_League_collection_tv);

        View matchTitleLl = helper.getView(R.id.module_match_title_ll);

        View headV = helper.getView(R.id.module_match_head_v);
        LeagueCollectionTv.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        back.clickView(view, item, position);
                    }
                }
        );
        View contentParentLl = helper.getView(R.id.ll_match_content_bg);
        if(contentParentLl==null)
            contentParentLl = helper.getView(R.id.ll_match_content);
        contentParentLl.setBackgroundColor(item.getContentColor());

        setClickOneTeam(helper, item);
        final LinearLayout parent = helper.getView(R.id.common_ball_parent_ll);
        View ll_match_outside = helper.getView(R.id.ll_match_outside);
        View ll_title_list = helper.getView(R.id.ll_title_list);
        if (BuildConfig.FLAVOR.equals("usun")) {
            parent.setBackgroundColor(Color.parseColor("#FDE9DA"));
            ll_title_list.setBackgroundResource(R.drawable.usun_add_title_bg);
        }


        RecyclerView rv_title_list = helper.getView(R.id.rv_title_list);

        if (act.onlyShowOne) {
            ll_match_outside.setVisibility(View.GONE);
        } else {
            if (!StringUtils.isNull(additionMap.get(true)) && !item.outShow && act.hasBet) {
                ll_match_outside.setVisibility(View.GONE);
            } else {
                ll_match_outside.setVisibility(View.VISIBLE);
            }
        }

        if (item.getType() == SportInfo.Type.ITME) {
            matchTitleLl.setVisibility(View.GONE);
            headV.setVisibility(View.GONE);
            LeagueCollectionTv.setBackgroundResource(R.mipmap.sport_game_star_yellow);
        } else {
            LeagueCollectionTv.setBackgroundResource(((BallItemCallBack) back).isLeagueCollection(item) ? R.mipmap.sport_game_star_yellow_open : R.mipmap.sport_game_star_yellow);
            matchTitleLl.setVisibility(View.VISIBLE);
            headV.setVisibility(View.VISIBLE);
            matchTitleTv.setText(item.getModuleTitle());
        }
        boolean contractedMatch = updateContractedMatch(helper, item);
        if (contractedMatch) {
            parent.setVisibility(View.GONE);
            ll_title_list.setVisibility(View.GONE);
            return;
        }
        if (additionBallItem != null && additionMap.get(true) != null && item.getSocOddsId().equals(additionBallItem.getSocOddsId()) && additionMap.get(true).equals(additionBallItem.getSocOddsId())) {

            ll_title_list.setVisibility(View.VISIBLE);
            liveSelectedHelper.iniSelectedHelper(rv_title_list, context, new MainPresenter.CallBack<MenuItemInfo>() {
                @Override
                public void onBack(MenuItemInfo data) throws JSONException {
                    createAddedData(item, parent, additionData, liveSelectedHelper.getLinkMap());
                }
            });


            createAddedData(item, parent, additionData, liveSelectedHelper.getLinkMap());
            final String socOddsId = item.getSocOddsId();
            rv_title_list.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (recyclerView.getLayoutManager() != null) {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                        //获取可视的第一个view
                        View topView = layoutManager.getChildAt(0);
                        LogUtil.d("onScrollStateChanged", topView);
                        //获取与该view的顶部的偏移量
                        int lastOffset = topView.getLeft();
                        //得到该View的数组位置
                        int lastPosition = layoutManager.getPosition(topView);
                        scrollPositionMap.put(socOddsId, new MenuItemInfo(lastPosition, lastOffset));
                        LogUtil.d("onScrollStateChanged", "lastPosition:'" + lastPosition + ",lastOffset:" + lastOffset);
                    }
                }
            });
            MenuItemInfo menuItemInfo = scrollPositionMap.get(socOddsId);
            if (menuItemInfo != null) {
                if (rv_title_list.getLayoutManager() != null && menuItemInfo.getRes() >= 0) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) rv_title_list.getLayoutManager();
                    int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    LogUtil.d("onScrollStateChanged", "menuItemInfo.getRes():'" + menuItemInfo.getRes() + ",menuItemInfo.getText:" + menuItemInfo.getText() + ",lastVisibleItemPosition=" + lastVisibleItemPosition);
                    layoutManager.scrollToPositionWithOffset(menuItemInfo.getRes(), menuItemInfo.getText());
                    if (menuItemInfo.getRes() > 8) {
                        layoutManager.setStackFromEnd(true);
                    } else {
                        layoutManager.setStackFromEnd(false);
                    }

                }
            }

        } else {
            parent.setVisibility(View.GONE);
            ll_title_list.setVisibility(View.GONE);
        }

        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
        onConvertHall(item, ivHall, position);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        ImageView lastGif = helper.getView(R.id.iv_last_call_gif);

        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView hasPTv = helper.getView(R.id.module_match_has_p_tv);

        TextView liveTv = helper.getView(R.id.module_match_live_iv);

        TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);

        TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);

        TextView gameSumTv = helper.getView(R.id.module_right_mark_tv);
        final View tvCollection = helper.getView(R.id.module_match_collection_tv);
        liveTv.setTextColor(red_black);

        dateTv.setTextColor(red_black);

        timeTv.setTextColor(black_grey);

        dateTv.setPadding(0, 0, 0, 0);

        String time = item.getMatchDate();
  /*      if (time.length() > 6) {
            time = time.substring(time.length() - 7, time.length());
            time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
        }*/
        timeTv.setText(time);

        lastGif.setVisibility(View.GONE);
        liveTv.setVisibility(View.VISIBLE);

        timeTv.setVisibility(View.VISIBLE);

        if (hasPTv != null) {
            hasPTv.setVisibility(View.GONE);
            if (!StringUtils.isNull(item.getHasPar()) && item.getHasPar().equals("1")) {
                hasPTv.setVisibility(View.VISIBLE);
            }
        }
        if (item.getLive() != null && !item.getLive().equals("")) {
            dateTv.setVisibility(View.VISIBLE);

            if (item.getLive().contains("LIVE")) {
                dateTv.setText("LIVE");

                liveTv.setVisibility(View.GONE);

            } else {
                String channel = item.getLive();
                channel = Html.fromHtml(channel).toString();
                String[] channels = channel.split("\\n");
                if (channels.length == 1) {
                    liveTv.setVisibility(View.GONE);

                    dateTv.setText(channels[0].trim());

                    if (channels[0].contains("/")) {
                        dateTv.setVisibility(View.GONE);

                    }
                } else if (channels.length == 2) {
                    liveTv.setVisibility(View.GONE);

                    liveTv.setText(channels[0].trim());

                    dateTv.setText(channels[1].trim());

                }
            }
        } else {
            dateTv.setVisibility(View.GONE);

        }
        showLastCall(item, dateTv, lastGif,  timeTv,  liveTv);


        if (((BallItemCallBack) back).isItemCollection(item))
            tvCollection.setBackgroundResource(R.mipmap.sport_game_star_yellow_open);
        else
            tvCollection.setBackgroundResource(R.mipmap.sport_game_star_yellow);
        tvCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item, position);
            }
        });

        String isHomeGive = item.getIsHomeGive();
        String hdp = item.getHdp();
        if (isHomeGive.equals("1")) {
            homeTv.setTextColor(red_black);

            awayTv.setTextColor(black_grey);

        } else {
            homeTv.setTextColor(black_grey);

            awayTv.setTextColor(red_black);

        }
        if (hdp.equals("0")) {
            homeTv.setTextColor(black_grey);

            awayTv.setTextColor(black_grey);

        }
        gameSumTv.setVisibility(View.GONE);
        gameSumTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item, position);
            }
        });
        gameSumTv.setText(item.getGamesSum());

        handleOddsContent(helper, item, position);
        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);

        awayTv.setText(away);

        if (liveTv.getText().toString().trim().isEmpty()) {
            liveTv.setVisibility(View.GONE);
        } else {
            liveTv.setVisibility(View.GONE);
        }
        String oldHomeId = "";
        String oldAwayId = "";
        String oldHomeGive = "";
        String oldModuleId = "";
        if (position > 0) {
            oldHomeId = back.getItem(position - 1).getHomeId();
            oldAwayId = back.getItem(position - 1).getAwayId();
            oldHomeGive = back.getItem(position - 1).getIsHomeGive();
            oldModuleId = back.getItem(position - 1).getModuleId();
        }
        if (item.getModuleId().equals(oldModuleId) && position != 0 && oldHomeId.equals(item.getHomeId()) && oldAwayId.equals(item.getAwayId()) && oldHomeGive.equals(item.getIsHomeGive())) {
            onMatchRepeat(helper, item, position);
        } else {
            onMatchNotRepeat(helper, item, position);
        }

        TextView homeScoreTv = helper.getView(R.id.module_match_home_score_tv);
        TextView awayScoreTv = helper.getView(R.id.module_match_away_score_tv);
        SoccerRunningGoalManager.getInstance().handleGoalStyle(item, homeScoreTv, awayScoreTv);
        if (act != null) {
            if (act.currentFragment.presenter != null && act.currentFragment.isVisible() && act.currentFragment.presenter.getStateHelper().getStateType() != null) {
                String type = act.currentFragment.presenter.getStateHelper().getStateType().getType();
                if (!type.toLowerCase().startsWith("r")) {
                    homeScoreTv.setVisibility(View.GONE);
                    awayScoreTv.setVisibility(View.GONE);
                }
            }
        }
        updateMixNormalBackground(helper, item);

    }


    private void onConvertHall(final I item, ImageView ivHall, final int position) {

        String rtsMatchId = item.getRTSMatchId();
        String tvIBC = item.getTvPathIBC();
        if (rtsMatchId != null && !rtsMatchId.isEmpty() && !rtsMatchId.equals("0") || (!StringUtils.isNull(tvIBC) && !tvIBC.equals("0"))) {
            ivHall.setVisibility(View.VISIBLE);
            ivHall.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back.clickView(v, item, position);
                }
            });
            ivHall.setImageResource(R.mipmap.soccer_hall);
            if ((!StringUtils.isNull(tvIBC) && !tvIBC.equals("0"))) {
                ivHall.setImageResource(R.mipmap.tv_play);
            }
        } else {
            ivHall.setVisibility(View.GONE);
        }
    }

    public void createAddedData(I item, LinearLayout parent, AddMBean additionData, HashMap<Boolean, MenuItemInfo> selectedMap) {
        allowedEurope = allowedEurope();
        LogUtil.d("additionMap", "--------------additionBallItem:" + item);
        parent.removeAllViews();

        int sizeFT = 0;
        int sizeFH = 0;

        if (additionData != null && additionData.getFTodds() != null)
            sizeFT = additionData.getFTodds().size();
        if (additionData != null && additionData.getFHodds() != null)
            sizeFH = additionData.getFHodds().size();
        int size = sizeFT > sizeFH ? sizeFT : sizeFH;
        if (size > 0 || (additionData.getF1X2_CNT() != null && !additionData.getF1X2_CNT().equals("0"))
                || (additionData.getH1X2_CNT() != null && !additionData.getH1X2_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getFCS_CNT()) && !additionData.getFCS_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getHCS_CNT()) && !additionData.getHCS_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getFDB_CNT()) && !additionData.getFDB_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getHDB_CNT()) && !additionData.getHDB_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getFOE_CNT()) && !additionData.getFOE_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getHOE_CNT()) && !additionData.getHOE_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getHTFT_CNT()) && !additionData.getHTFT_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getFGLG_CNT()) && !additionData.getFGLG_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getTG_CNT()) && !additionData.getTG_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getHTTG_CNT()) && !additionData.getHTTG_CNT().equals("0"))
                || (!StringUtils.isNull(additionData.getATTG_CNT()) && !additionData.getATTG_CNT().equals("0"))
                || (additionData.getFTMModds() != null && additionData.getFTMModds().size() > 0)
                || (additionData.getFHMModds() != null && additionData.getFHMModds().size() > 0)
        ) {
            parent.setVisibility(View.VISIBLE);
        } else {
            parent.setVisibility(View.GONE);
        }
        int res = selectedMap.get(true).getRes();
        boolean isAll = (res == R.string.all);

        if (allowedEurope && size > 0 && (isAll || res == R.string.HDP_OU)) {

            for (int i = 0; i < size; i++) {
                if (i == 0) {
                    View titleLL = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_title_item, null);
                    parent.addView(titleLL);
                }
                addMainAddedView(parent, i < sizeFT ? additionData.getFTodds().get(i) : null, i < sizeFH ? additionData.getFHodds().get(i) : null, item, i % 2 == 0 ? R.layout.addition_hdp_ou_sport_item : R.layout.addition_hdp_ou_sport_item_green
                        , i % 2 == 0 ? R.drawable.match_odds_content_bg_mix : R.drawable.match_odds_content_bg_green_mix);
            }
        }


        if (additionData != null) {
            if (additionData.getF1X2_CNT() != null && !additionData.getF1X2_CNT().equals("0") && (isAll || res == R.string.X1X2)) {
                LogUtil.d("additionMap", "--------------getF1X2_CNT:");
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, R.string.FULL_1X2);
                addAddedFor1X2(additionData.getF1(), additionData.getFX(), additionData.getF2(), additionData.getF1X2_SocOddsId(), false, parent, item,
                        context.getString(R.string.h1), context.getString(R.string.dx), context.getString(R.string.a2), "1", "X", "2", "", "", "", R.layout.addition_1x2_sport_item
                        , additionData.getHasPar().equals("True")
                        , additionData.getHasPar().equals("True")
                        , additionData.getHasPar().equals("True")
                        , additionData.getValue1IsInetBet().equals("True")
                        , additionData.getXIsInetBet().equals("True")
                        , additionData.getValue2IsInetBet().equals("True")
                        , additionData.getF1_ishowOdds().equals("True")
                        , additionData.getFX_ishowOdds().equals("True")
                        , additionData.getF2_ishowOdds().equals("True")
                        , item.getIsHomeGive()
                );
            }
            if (additionData.getH1X2_CNT() != null && !additionData.getH1X2_CNT().equals("0") && (isAll || res == R.string.X1X2)) {
                LogUtil.d("additionMap", "--------------getH1X2_CNT:");
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, R.string.HALF_1X2);
                addAddedFor1X2(additionData.getH1(), additionData.getHX(), additionData.getH2(), additionData.getH1X2_SocOddsId(), false, parent, item,
                        context.getString(R.string.h1), context.getString(R.string.dx), context.getString(R.string.a2), "1", "X", "2", "", "", "", R.layout.addition_1x2_sport_item
                        , additionData.getHHasPar().equals("True")
                        , additionData.getHHasPar().equals("True")
                        , additionData.getHHasPar().equals("True")
                        , additionData.getH1IsInetBet().equals("True")
                        , additionData.getHXIsInetBet().equals("True")
                        , additionData.getH2IsInetBet().equals("True")
                        , additionData.getH1_ishowOdds().equals("True")
                        , additionData.getHX_ishowOdds().equals("True")
                        , additionData.getH2_ishowOdds().equals("True")
                        , item.getIsHomeGive_FH()
                );
            }

            if (!StringUtils.isNull(additionData.getFCS_CNT()) && !additionData.getFCS_CNT().equals("0") && (isAll || res == R.string.CS)) {//波胆
//                    addAdditionCS(additionData, false, parent, item, "70");
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, R.string.correct_full);
                addAdded(additionData.getG1(), additionData.getG11(), additionData.getG17(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG1IsInetBet().equals("True")
                        , additionData.getG11IsInetBet().equals("True")
                        , additionData.getG17IsInetBet().equals("True")
                        , additionData.getG1_ishowOdds().equals("True")
                        , additionData.getG11_ishowOdds().equals("True")
                        , additionData.getG17_ishowOdds().equals("True")
                );
                addAdded(additionData.getG2(), additionData.getG12(), additionData.getG18(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG2IsInetBet().equals("True")
                        , additionData.getG12IsInetBet().equals("True")
                        , additionData.getG18IsInetBet().equals("True")
                        , additionData.getG2_ishowOdds().equals("True")
                        , additionData.getG12_ishowOdds().equals("True")
                        , additionData.getG18_ishowOdds().equals("True")
                );
                addAdded(additionData.getG3(), additionData.getG13(), additionData.getG19(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG3IsInetBet().equals("True")
                        , additionData.getG13IsInetBet().equals("True")
                        , additionData.getG19IsInetBet().equals("True")
                        , additionData.getG3_ishowOdds().equals("True")
                        , additionData.getG13_ishowOdds().equals("True")
                        , additionData.getG19_ishowOdds().equals("True")
                );
                addAdded(additionData.getG4(), additionData.getG14(), additionData.getG20(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG4IsInetBet().equals("True")
                        , additionData.getG14IsInetBet().equals("True")
                        , additionData.getG20IsInetBet().equals("True")
                        , additionData.getG4_ishowOdds().equals("True")
                        , additionData.getG14_ishowOdds().equals("True")
                        , additionData.getG20_ishowOdds().equals("True")
                );
                addAdded(additionData.getG5(), additionData.getG15(), additionData.getG21(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "3:1", "4:4", "1:3", "csr", "csr", "csr", "31", "44", "13", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG5IsInetBet().equals("True")
                        , additionData.getG15IsInetBet().equals("True")
                        , additionData.getG21IsInetBet().equals("True")
                        , additionData.getG5_ishowOdds().equals("True")
                        , additionData.getG15_ishowOdds().equals("True")
                        , additionData.getG21_ishowOdds().equals("True")
                );
                addAdded(additionData.getG6(), additionData.getG16(), additionData.getG22(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "3:2", act.getString(R.string.AOS), "2:3", "csr", "csr", "csr", "32", "70", "23", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG6IsInetBet().equals("True")
                        , additionData.getG16IsInetBet().equals("True")
                        , additionData.getG22IsInetBet().equals("True")
                        , additionData.getG6_ishowOdds().equals("True")
                        , !TextUtils.isEmpty(additionData.getG16_ishowOdds()) && additionData.getG16_ishowOdds().equals("True")
                        , additionData.getG22_ishowOdds().equals("True")
                );

                addAdded(additionData.getG7(), "", additionData.getG23(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "4:0", "", "0:4", "csr", "csr", "csr", "40", "", "4", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG7IsInetBet().equals("True")
                        , false
                        , additionData.getG23IsInetBet().equals("True")
                        , additionData.getG7_ishowOdds().equals("True")
                        , false
                        , additionData.getG23_ishowOdds().equals("True")
                );
                addAdded(additionData.getG8(), "", additionData.getG24(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "4:1", "", "1:4", "csr", "csr", "csr", "41", "", "14", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG8IsInetBet().equals("True")
                        , false
                        , additionData.getG24IsInetBet().equals("True")
                        , additionData.getG8_ishowOdds().equals("True")
                        , false
                        , additionData.getG24_ishowOdds().equals("True")
                );
                addAdded(additionData.getG9(), "", additionData.getG25(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "4:2", "", "2:4", "csr", "csr", "csr", "42", "", "24", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG9IsInetBet().equals("True")
                        , false
                        , additionData.getG25IsInetBet().equals("True")
                        , additionData.getG9_ishowOdds().equals("True")
                        , false
                        , additionData.getG25_ishowOdds().equals("True")
                );
                addAdded(additionData.getG10(), "", additionData.getG26(), additionData.getFCS_SocOddsId(), false, parent, item,
                        "4:3", "", "3:4", "csr", "csr", "csr", "43", "", "34", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getG10IsInetBet().equals("True")
                        , false
                        , additionData.getG26IsInetBet().equals("True")
                        , additionData.getG10_ishowOdds().equals("True")
                        , false
                        , additionData.getG26_ishowOdds().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getHCS_CNT()) && !additionData.getHCS_CNT().equals("0") && (isAll || res == R.string.CS)) {
//                    addAdditionCS(additionData.getFHCS(), true, parent, item, "80");
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, R.string.correct_half);
                addAdded(additionData.getHG1(), additionData.getHG11(), additionData.getHG17(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "1:0", "0:0", "0:1", "csr", "csr", "csr", "10", "0", "1", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG1IsInetBet().equals("True")
                        , additionData.getHG11IsInetBet().equals("True")
                        , additionData.getHG17IsInetBet().equals("True")
                        , additionData.getHG1_ishowOdds().equals("True")
                        , additionData.getHG11_ishowOdds().equals("True")
                        , additionData.getHG17_ishowOdds().equals("True")
                );
                addAdded(additionData.getHG2(), additionData.getHG12(), additionData.getHG18(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "2:0", "1:1", "0:2", "csr", "csr", "csr", "20", "11", "2", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG2IsInetBet().equals("True")
                        , additionData.getHG12IsInetBet().equals("True")
                        , additionData.getHG18IsInetBet().equals("True")
                        , additionData.getHG2_ishowOdds().equals("True")
                        , additionData.getHG12_ishowOdds().equals("True")
                        , additionData.getHG18_ishowOdds().equals("True")
                );
                addAdded(additionData.getHG3(), additionData.getHG13(), additionData.getHG19(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "2:1", "2:2", "1:2", "csr", "csr", "csr", "21", "22", "12", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG3IsInetBet().equals("True")
                        , additionData.getHG13IsInetBet().equals("True")
                        , additionData.getHG19IsInetBet().equals("True")
                        , additionData.getHG3_ishowOdds().equals("True")
                        , additionData.getHG13_ishowOdds().equals("True")
                        , additionData.getHG19_ishowOdds().equals("True")
                );
                addAdded(additionData.getHG4(), additionData.getHG14(), additionData.getHG20(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "3:0", "3:3", "0:3", "csr", "csr", "csr", "30", "33", "3", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG4IsInetBet().equals("True")
                        , additionData.getHG14IsInetBet().equals("True")
                        , additionData.getHG20IsInetBet().equals("True")
                        , additionData.getHG4_ishowOdds().equals("True")
                        , additionData.getHG14_ishowOdds().equals("True")
                        , additionData.getHG20_ishowOdds().equals("True")
                );
                addAdded(additionData.getHG5(), additionData.getHG16(), additionData.getHG21(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "3:1", act.getString(R.string.AOS), "1:3", "csr", "csr", "csr", "31", "80", "13", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG5IsInetBet().equals("True")
                        , additionData.getHG16IsInetBet().equals("True")
                        , additionData.getHG21IsInetBet().equals("True")
                        , additionData.getHG5_ishowOdds().equals("True")
                        , (!TextUtils.isEmpty(additionData.getHG16_ishowOdds()) && additionData.getHG16_ishowOdds().equals("True"))
                        , additionData.getHG21_ishowOdds().equals("True")
                );
                addAdded(additionData.getHG6(), "", additionData.getHG22(), additionData.getHCS_SocOddsId(), true, parent, item,
                        "3:2", "", "2:3", "csr", "", "csr", "32", "", "23", R.layout.addition_cs_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHG6IsInetBet().equals("True")
                        , false
                        , additionData.getHG22IsInetBet().equals("True")
                        , additionData.getHG6_ishowOdds().equals("True")
                        , false
                        , additionData.getHG22_ishowOdds().equals("True")
                );
            }

            if (!StringUtils.isNull(additionData.getFDB_CNT()) && !additionData.getFDB_CNT().equals("0") && (isAll || res == R.string.DC)) {

                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.full_time) + context.getString(R.string.double_chance));
                addAdded(additionData.getFHD(), additionData.getFHA(), additionData.getFDA(), additionData.getFDB_SocOddsId(), false, parent, item,
                        "1X", "12", "X2", "dc", "dc", "dc", "10", "12", "2", R.layout.addition_1x2_sport_item
                        , false
                        , false
                        , false
                        , additionData.getFHDIsInetBet().equals("True")
                        , additionData.getFHAIsInetBet().equals("True")
                        , additionData.getFDAIsInetBet().equals("True")
                        , additionData.getFHD_ishowOdds().equals("True")
                        , additionData.getFHA_ishowOdds().equals("True")
                        , additionData.getFDA_ishowOdds().equals("True")
                );

            }
            if (!StringUtils.isNull(additionData.getHDB_CNT()) && !additionData.getHDB_CNT().equals("0") && (isAll || res == R.string.DC)) {
//                    addAdditionDC(additionData.getHTDC(), false, parent, item);
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.half_time) + context.getString(R.string.double_chance));
                addAdded(additionData.getHHD(), additionData.getHHA(), additionData.getHDA(), additionData.getHDB_SocOddsId(), false, parent, item,
                        "1X", "12", "X2", "dc", "dc", "dc", "10", "12", "2", R.layout.addition_1x2_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHHDIsInetBet().equals("True")
                        , additionData.getHHAIsInetBet().equals("True")
                        , additionData.getHDAIsInetBet().equals("True")
                        , additionData.getHHD_ishowOdds().equals("True")
                        , additionData.getHHA_ishowOdds().equals("True")
                        , additionData.getHDA_ishowOdds().equals("True")
                );
            }

            if (!StringUtils.isNull(additionData.getFOE_CNT()) && !additionData.getFOE_CNT().equals("0") && (isAll || res == R.string.OE)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.full_time) + context.getString(R.string.odd_even));
                addAddedByColor(AfbUtils.changeValueS(additionData.getFOdd()), AfbUtils.changeValueS(additionData.getFEven()), additionData.getFOE_SocOddsId(), false, parent, item,
                        context.getString(R.string.ODD), context.getString(R.string.EVEN), "odd", "even", "", "", R.layout.addition_1x2_sport_item, "1"
                        , additionData.getHasPar() != null && additionData.getHasPar().equals("True")
                        , additionData.getHasPar() != null && additionData.getHasPar().equals("True")
                        , additionData.getFOddIsInetBet().equals("True")
                        , additionData.getFEvenIsInetBet().equals("True")
                        , additionData.getFOE_ishowOdds().equals("True")
                        , additionData.getFOE_ishowOdds().equals("True")
                        , ""
                        , ""
                );
            }
            if (!StringUtils.isNull(additionData.getHOE_CNT()) && !additionData.getHOE_CNT().equals("0") && (isAll || res == R.string.OE)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.half_time) + context.getString(R.string.odd_even));

                addAddedByColor(AfbUtils.changeValueS(additionData.getHOdd()), AfbUtils.changeValueS(additionData.getHEven()), additionData.getHOE_SocOddsId(), true, parent, item,
                        context.getString(R.string.ODD), context.getString(R.string.EVEN), "odd", "even", "", "", R.layout.addition_1x2_sport_item, "1"
                        , additionData.getHHasPar() != null && additionData.getHHasPar().equals("True")
                        , additionData.getHHasPar() != null && additionData.getHHasPar().equals("True")
                        , additionData.getHOddIsInetBet() != null && additionData.getHOddIsInetBet().equals("True")
                        , additionData.getHEvenIsInetBet() != null && additionData.getHEvenIsInetBet().equals("True")
                        , additionData.getHOE_ishowOdds() != null && additionData.getHOE_ishowOdds().equals("True")
                        , additionData.getHOE_ishowOdds() != null && additionData.getHOE_ishowOdds().equals("True")
                        , ""
                        , ""
                );
            }

            if (!StringUtils.isNull(additionData.getHTFT_CNT()) && !additionData.getHTFT_CNT().equals("0") && (isAll || res == R.string.HTFT)) {//半场全场
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.half_full_time));

                addAdded(additionData.getHH(), additionData.getHD(), additionData.getHA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                        context.getString(R.string.hh), context.getString(R.string.hd), context.getString(R.string.ha)
                        , "htft", "htft", "htft", "11 ", "10", "12", R.layout.addition_htft_sport_item
                        , false
                        , false
                        , false

                        , additionData.getHHIsInetBet().equals("True")
                        , additionData.getHDIsInetBet().equals("True")
                        , additionData.getHAIsInetBet().equals("True")
                        , additionData.getHH_ishowOdds().equals("True")
                        , additionData.getHD_ishowOdds().equals("True")
                        , additionData.getHA_ishowOdds().equals("True")

                );
                addAdded(additionData.getDH(), additionData.getDD(), additionData.getDA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                        context.getString(R.string.dh), context.getString(R.string.dd), context.getString(R.string.da)
                        , "htft", "htft", "htft", "1 ", "0", "2", R.layout.addition_htft_sport_item
                        , false
                        , false
                        , false
                        , additionData.getDHIsInetBet().equals("True")
                        , additionData.getDDIsInetBet().equals("True")
                        , additionData.getDAIsInetBet().equals("True")
                        , additionData.getDH_ishowOdds().equals("True")
                        , additionData.getDD_ishowOdds().equals("True")
                        , additionData.getDA_ishowOdds().equals("True")
                );

                addAdded(additionData.getAH(), additionData.getAD(), additionData.getAA(), additionData.getHTFT_SocOddsId(), false, parent, item,
                        context.getString(R.string.ah), context.getString(R.string.ad), context.getString(R.string.aa)
                        , "htft", "htft", "htft", "21 ", "20", "22", R.layout.addition_htft_sport_item
                        , false
                        , false
                        , false
                        , additionData.getAHIsInetBet().equals("True")
                        , additionData.getADIsInetBet().equals("True")
                        , additionData.getAAIsInetBet().equals("True")
                        , additionData.getAH_ishowOdds().equals("True")
                        , additionData.getAD_ishowOdds().equals("True")
                        , additionData.getAA_ishowOdds().equals("True")
                );
            }

            if (!StringUtils.isNull(additionData.getFGLG_CNT()) && !additionData.getFGLG_CNT().equals("0") && (isAll || res == R.string.FGLG)) {//最后得分 最先得分
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.first_last_goal));

                addAdded(additionData.getHFG(), additionData.getNG(), additionData.getAFG(), additionData.getFGLG_SocOddsId(), false, parent, item,
                        act.getString(R.string.HFG), act.getString(R.string.NG), act.getString(R.string.AFG), "fglg", "fglg", "fglg", "10 ", "0", "20", R.layout.addition_htft_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHFGIsInetBet().equals("True")
                        , additionData.getNGIsInetBet().equals("True")
                        , additionData.getAFGIsInetBet().equals("True")
                        , additionData.getHFG_ishowOdds().equals("True")
                        , additionData.getNG_ishowOdds().equals("True")
                        , additionData.getAFG_ishowOdds().equals("True")
                );
                addAdded(additionData.getHLG(), "", additionData.getALG(), additionData.getFGLG_SocOddsId(), false, parent, item,
                        act.getString(R.string.HLG), "", act.getString(R.string.ALG), "fglg", "", "fglg", "1 ", "", "2", R.layout.addition_htft_sport_item
                        , false
                        , false
                        , false
                        , additionData.getHLGIsInetBet().equals("True")
                        , false
                        , additionData.getALGIsInetBet().equals("True")
                        , additionData.getHLG_ishowOdds().equals("True")
                        , false
                        , additionData.getALG_ishowOdds().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getTG_CNT()) && !additionData.getTG_CNT().equals("0") && (isAll || res == R.string.TG)) {//zho 最先得分
//                    addAdditionTG(additionData.getTG(), parent, item);
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.total_goals));

                addAddedByColor(additionData.getGoal1(), additionData.getGoal2(), additionData.getTG_SocOddsId(), false, parent, item,
                        "0~1", "2~3", "tg", "tg", "1", "23", R.layout.addition_1x2_sport_item, "2"
                        , false
                        , false
                        , additionData.getGoal1IsInetBet().equals("True")
                        , additionData.getGoal2IsInetBet().equals("True")
                        , additionData.getGoal1_ishowOdds().equals("True")
                        , additionData.getGoal2_ishowOdds().equals("True")
                        , ""
                        , ""

                );
                addAddedByColor(additionData.getGoal3(), additionData.getGoal4(), additionData.getTG_SocOddsId(), false, parent, item,
                        "4~6", act.getString(R.string.Over_7), "tg", "tg", "46", "70", R.layout.addition_1x2_sport_item, "2"
                        , false
                        , false
                        , additionData.getGoal3IsInetBet().equals("True")
                        , additionData.getGoal4IsInetBet().equals("True")
                        , additionData.getGoal3_ishowOdds().equals("True")
                        , additionData.getGoal4_ishowOdds().equals("True")
                        , ""
                        , ""
                );
            }

            if (!StringUtils.isNull(additionData.getHTTG_CNT()) && !additionData.getHTTG_CNT().equals("0") && (isAll || res == R.string.Home_Away_TG)) {//zhudui
//                    addAdditionTEAMTG(additionData.getHOMETEAMTG(), false, parent, item);
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);

                addTitle(parent, inflate, context.getString(R.string.HOME_TEAM_TOTAL_GOALS));

                addAddedByColor(
                        AfbUtils.changeValueS(additionData.getFHOOdds()),
                        AfbUtils.changeValueS(additionData.getFHUOdds()),
                        additionData.getHTTG_SocOddsId(), false, parent, item,
                        act.getString(R.string.FT_O), act.getString(R.string.FT_U), "over", "under", "", "", R.layout.addition_1x2_sport_item, "3"
                        , false
                        , false
                        , additionData.getFHOOddsIsInetBet().equals("True")
                        , additionData.getFHUOddsIsInetBet().equals("True")
                        , additionData.getHTTG_ishowFTOdds().equals("True")
                        , additionData.getHTTG_ishowFTOdds().equals("True")
                        , changeValueF(additionData.getHTTG_OU())
                        , changeValueF(additionData.getHTTG_OU())
                );

                addAddedByColor(AfbUtils.changeValueS(additionData.getHHOOdds()), AfbUtils.changeValueS(additionData.getHHUOdds()), additionData.getHTTG_FHSocOddsId(), true, parent, item,
                        act.getString(R.string.FH_O), act.getString(R.string.FH_U), "over", "under", "", "", R.layout.addition_1x2_sport_item, "3"
                        , false
                        , false
                        , additionData.getHHOOddsIsInetBet().equals("True")
                        , additionData.getHHUOddsIsInetBet().equals("True")
                        , additionData.getHTTG_ishowFHOdds().equals("True")
                        , additionData.getHTTG_ishowFHOdds().equals("True")
                        , changeValueF(additionData.getHTTG_FHOU())
                        , changeValueF(additionData.getHTTG_FHOU())
                );
            }
            if (!StringUtils.isNull(additionData.getATTG_CNT()) && !additionData.getATTG_CNT().equals("0") && (isAll || res == R.string.Home_Away_TG)) {//zhudui
//                    addAdditionTEAMTG(additionData.getHOMETEAMTG(), false, parent, item);
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.AWAY_TEAM_TOTAL_GOALS));

                addAddedByColor(AfbUtils.changeValueS(additionData.getFAOOdds()), AfbUtils.changeValueS(additionData.getFAUOdds()), additionData.getATTG_SocOddsId(), false, parent, item,
                        act.getString(R.string.FT_O), act.getString(R.string.FT_U), "over", "under", "", "", R.layout.addition_1x2_sport_item, "3"
                        , false
                        , false
                        , additionData.getFAOOddsIsInetBet().equals("True")
                        , additionData.getFAUOddsIsInetBet().equals("True")
                        , additionData.getATTG_ishowFTOdds().equals("True")
                        , additionData.getATTG_ishowFTOdds().equals("True")
                        , changeValueF(additionData.getATTG_OU())
                        , changeValueF(additionData.getATTG_OU())
                );
                addAddedByColor(AfbUtils.changeValueS(additionData.getHAOOdds()), AfbUtils.changeValueS(additionData.getHAUOdds()), additionData.getATTG_FHSocOddsId(), true, parent, item,
                        act.getString(R.string.FH_O), act.getString(R.string.FH_U), "over", "under", "", "", R.layout.addition_1x2_sport_item, "3"
                        , false
                        , false
                        , additionData.getHAOOddsIsInetBet().equals("True")
                        , additionData.getHAUOddsIsInetBet().equals("True")
                        , additionData.getATTG_ishowFHOdds().equals("True")
                        , additionData.getATTG_ishowFHOdds().equals("True")
                        , changeValueF(additionData.getATTG_FHOU())
                        , changeValueF(additionData.getATTG_FHOU())
                );
            }
            /*"(00:00-15:00)"
"(15:01-30:00)"
"(30:01-45:00)"
"(45:01-60:00)"
"(60:01-75:00)"*/
            if (!StringUtils.isNull(additionData.getMG15_CNT()) && !additionData.getMG15_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT15InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT15InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT15InfoO()),
                        AfbUtils.changeValueS(additionData.getFT15InfoU()),

                        additionData.getMG15_SocOddsId(), false, parent, item

                        , additionData.getFT15InfoOUIsInetBet().equals("True")
                        , additionData.getFT15InfoUIsInetBet().equals("True")
                        , additionData.getMG15_ishowOdds().equals("True")
                        , additionData.getFT15InfoOU()

                        , additionData.getMG15_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG15_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG15_ishowHDPOdds().equals("True")
                        , additionData.getFT15InfoHDP()
                        , additionData.getMG15_IsHomeGive().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getMG30_CNT()) && !additionData.getMG30_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(15:01-30:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT30InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT30InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT30InfoO()),
                        AfbUtils.changeValueS(additionData.getFT30InfoU()),

                        additionData.getMG30_SocOddsId(), false, parent, item

                        , additionData.getFT30InfoOIsInetBet().equals("True")
                        , additionData.getFT30InfoUIsInetBet().equals("True")
                        , additionData.getMG30_ishowOdds().equals("True")
                        , additionData.getFT30InfoOU()

                        , additionData.getMG30_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG30_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG30_ishowHDPOdds().equals("True")
                        , additionData.getFT30InfoHDP()
                        , additionData.getMG30_IsHomeGive().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getMG45_CNT()) && !additionData.getMG45_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(30:01-45:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT45InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT45InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT45InfoO()),
                        AfbUtils.changeValueS(additionData.getFT45InfoU()),

                        additionData.getMG45_SocOddsId(), false, parent, item

                        , additionData.getFT45InfoOIsInetBet().equals("True")
                        , additionData.getFT45InfoUIsInetBet().equals("True")
                        , additionData.getMG45_ishowOdds().equals("True")
                        , additionData.getFT45InfoOU()

                        , additionData.getMG45_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG45_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG45_ishowHDPOdds().equals("True")
                        , additionData.getFT45InfoHDP()
                        , additionData.getMG45_IsHomeGive().equals("True")
                );
            }

            if (!StringUtils.isNull(additionData.getMG60_CNT()) && !additionData.getMG60_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(45:01-60:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT60InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT60InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT60InfoO()),
                        AfbUtils.changeValueS(additionData.getFT60InfoU()),

                        additionData.getMG60_SocOddsId(), false, parent, item

                        , additionData.getFT60InfoOIsInetBet().equals("True")
                        , additionData.getFT60InfoUIsInetBet().equals("True")
                        , additionData.getMG60_ishowOdds().equals("True")
                        , additionData.getFT60InfoOU()

                        , additionData.getMG60_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG60_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG60_ishowHDPOdds().equals("True")
                        , additionData.getFT60InfoHDP()
                        , additionData.getMG60_IsHomeGive().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getMG75_CNT()) && !additionData.getMG75_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(60:01-75:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT75InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT75InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT75InfoO()),
                        AfbUtils.changeValueS(additionData.getFT75InfoU()),

                        additionData.getMG75_SocOddsId(), false, parent, item

                        , additionData.getFT75InfoOIsInetBet().equals("True")
                        , additionData.getFT75InfoUIsInetBet().equals("True")
                        , additionData.getMG75_ishowOdds().equals("True")
                        , additionData.getFT75InfoOU()

                        , additionData.getMG75_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG75_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG75_ishowHDPOdds().equals("True")
                        , additionData.getFT75InfoHDP()
                        , additionData.getMG75_IsHomeGive().equals("True")
                );
            }
            if (!StringUtils.isNull(additionData.getMG90_CNT()) && !additionData.getMG90_CNT().equals("0") && (isAll || res == R.string.FT15)) {
                View inflate = LayoutInflater.from(context).inflate(R.layout.addition_ft_1x2_title_item, null);
                addTitle(parent, inflate, context.getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(75:01-90:00)");
                addAdditionHdpOu(
                        AfbUtils.changeValueS(additionData.getFT90InfoHome()),
                        AfbUtils.changeValueS(additionData.getFT90InfoAway()),
                        AfbUtils.changeValueS(additionData.getFT90InfoO()),
                        AfbUtils.changeValueS(additionData.getFT90InfoU()),

                        additionData.getMG90_SocOddsId(), false, parent, item

                        , additionData.getFT90InfoOIsInetBet().equals("True")
                        , additionData.getFT90InfoUIsInetBet().equals("True")
                        , additionData.getMG90_ishowOdds().equals("True")
                        , additionData.getFT90InfoOU()

                        , additionData.getMG90_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG90_ishowHDPIsInetBet().equals("True")
                        , additionData.getMG90_ishowHDPOdds().equals("True")
                        , additionData.getFT90InfoHDP()
                        , additionData.getMG90_IsHomeGive().equals("True")
                );
            }
            if (!StringUtils.isNull(curCode) && curCode.toUpperCase().equals("MMK") && additionData.getFTMModds() != null && additionData.getFTMModds().size() > 0 && (isAll || res == R.string.MM_HDP_OU)) {
                for (AddMBean.MModdsBean mModdsBean : additionData.getFTMModds()) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_mm_title_ft_item, null);
                    parent.addView(inflate);
                    addAddedMModds(AfbUtils.changeValueS(mModdsBean.getHomeOdds()), AfbUtils.changeValueS(mModdsBean.getOverOdds()), mModdsBean.getSocOddsId(), false, parent, item,
                            context.getString(R.string.h), context.getString(R.string.o), "mmhome", "mmover", mModdsBean.getHDPH(), mModdsBean.getOU()
                            , mModdsBean.getHasHdp().equals("True") && (!StringUtils.isNull(mModdsBean.getHDPH()) || !StringUtils.isNull(mModdsBean.getHDPA()))
                            , mModdsBean.getHasOU().equals("True") && (!StringUtils.isNull(mModdsBean.getOU()))
                    );
                    addAddedMModds(AfbUtils.changeValueS(mModdsBean.getAwayOdds()), AfbUtils.changeValueS(mModdsBean.getUnderOdds()), mModdsBean.getSocOddsId(), false, parent, item,
                            context.getString(R.string.a), context.getString(R.string.u), "mmaway", "mmunder", mModdsBean.getHDPA(), ""
                            , mModdsBean.getHasHdp().equals("True") && (!StringUtils.isNull(mModdsBean.getHDPH()) || !StringUtils.isNull(mModdsBean.getHDPA()))
                            , mModdsBean.getHasOU().equals("True") && (!StringUtils.isNull(mModdsBean.getOU()))
                    );
                }
            }
            if (additionData.getFHMModds() != null && additionData.getFHMModds().size() > 0 && (isAll || res == R.string.MM_HDP_OU)) {
                for (AddMBean.MModdsBean mModdsBean : additionData.getFHMModds()) {
                    View inflate = LayoutInflater.from(context).inflate(R.layout.addition_mm_title_fh_item, null);
                    parent.addView(inflate);
                    addAddedMModds(AfbUtils.changeValueS(mModdsBean.getHomeOdds()), AfbUtils.changeValueS(mModdsBean.getOverOdds()), mModdsBean.getSocOddsId(), true, parent, item,
                            "Home ", "Over ", "mmhome", "mmover", mModdsBean.getHDPH(), mModdsBean.getOU()
                            , mModdsBean.getHasHdp().equals("True") && (!StringUtils.isNull(mModdsBean.getHDPH()) || !StringUtils.isNull(mModdsBean.getHDPA()))
                            , mModdsBean.getHasOU().equals("True") && (!StringUtils.isNull(mModdsBean.getOU()))
                    );
                    addAddedMModds(AfbUtils.changeValueS(mModdsBean.getAwayOdds()), AfbUtils.changeValueS(mModdsBean.getUnderOdds()), mModdsBean.getSocOddsId(), true, parent, item,
                            "Away ", "Under ", "mmaway", "mmunder", mModdsBean.getHDPA(), ""
                            , mModdsBean.getHasHdp().equals("True") && (!StringUtils.isNull(mModdsBean.getHDPH()) || !StringUtils.isNull(mModdsBean.getHDPA()))
                            , mModdsBean.getHasOU().equals("True") && (!StringUtils.isNull(mModdsBean.getOU()))
                    );
                }
            }
        }
    }

    protected boolean allowedEurope() {
        return true;
    }


    private void addAddedMModds(String oddsLeft,
                                String oddsRight,
                                String socOddsId,
                                boolean isHalf,
                                LinearLayout parent,
                                I item, String hdpShow1, String ouShow2,
                                String type1, String type2,
                                String hdp1, String ou2
            ,
                                boolean hasHdp,
                                boolean hasOu
    ) {

        View inflate1X2 = LayoutInflater.from(context).inflate(R.layout.addition_mmodds_sport_item, null);

        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        subStringsMModds(hdpShow1, hdp1, ouShow2, ou2, inflate1X2);
        down1_tv.setTextColor(getValueColor(oddsLeft));
        down2_tv.setTextColor(getValueColor(oddsRight));
        setMMHdpOuMixStyle(socOddsId, inflate1X2, type1, type2);
        setTextValueClick(down1_tv, oddsLeft, type1, socOddsId, item, isHalf, "", false, hasHdp, false);
        setTextValueClick(down2_tv, oddsRight, type2, socOddsId, item, isHalf, "", false, hasOu, false);
        parent.addView(inflate1X2);

    }

    private void setMMHdpOuMixStyle(String socOddsId, View inflate1X2, String type1, String type2) {
        View ll_content1 = inflate1X2.findViewById(R.id.ll_content1);
        View ll_content2 = inflate1X2.findViewById(R.id.ll_content2);
        HashMap<String, View> map = new HashMap<>();
        map.put(type1, ll_content1);
        map.put(type2, ll_content2);
        setMixBg(R.drawable.match_odds_content_bg_mix, map, socOddsId);
    }

    private void subStringsMModds(String hdpTitle, String hdp, String ouTitle, String ou, View inflate1X2) {
        TextView up1_left_tv = inflate1X2.findViewById(R.id.up1_left_tv);
        TextView up2_left_tv = inflate1X2.findViewById(R.id.up2_left_tv);
        TextView up1_right1_tv = inflate1X2.findViewById(R.id.up1_right1_tv);
        TextView up1_right2_tv = inflate1X2.findViewById(R.id.up1_right2_tv);
        TextView up1_right3_tv = inflate1X2.findViewById(R.id.up1_right3_tv);
        TextView up2_right1_tv = inflate1X2.findViewById(R.id.up2_right1_tv);
        TextView up2_right2_tv = inflate1X2.findViewById(R.id.up2_right2_tv);
        TextView up2_right3_tv = inflate1X2.findViewById(R.id.up2_right3_tv);
        setMModds(hdpTitle, hdp, up1_left_tv, up1_right1_tv, up1_right2_tv, up1_right3_tv);
        setMModds(ouTitle, ou, up2_left_tv, up2_right1_tv, up2_right2_tv, up2_right3_tv);
    }

    private void setMModds(String hdpTitle, String hdp, TextView up1_left_tv, TextView up1_right1_tv, TextView up1_right2_tv, TextView up1_right3_tv) {
        if (!StringUtils.isNull(hdp) && hdp.contains("(")) {
            String hdpOdds = hdp.substring(hdp.indexOf("(") + 1, hdp.indexOf(")"));
            String pre = hdp.substring(0, hdp.indexOf("("));
            String last = hdp.substring(hdp.indexOf(")") + 1);
            up1_right1_tv.setText(pre);
            up1_right3_tv.setText(last);
            up1_right1_tv.setVisibility(View.VISIBLE);
            up1_right2_tv.setVisibility(View.VISIBLE);
            up1_right3_tv.setVisibility(View.VISIBLE);
            if (Float.valueOf(hdpOdds) < 0) {
                up1_right2_tv.setTextColor(ContextCompat.getColor(context, R.color.red_title));

            } else {
                up1_right2_tv.setTextColor(Color.BLACK);
            }

            up1_right2_tv.setText("(" + Integer.valueOf(hdpOdds) / 5 * 5 + ")");
        } else {
            up1_right1_tv.setVisibility(View.GONE);
            up1_right2_tv.setVisibility(View.GONE);
            up1_right3_tv.setVisibility(View.GONE);
        }
        if (hdpTitle.equals(context.getString(R.string.h))) {
            up1_left_tv.setTextColor(Color.RED);
        } else {
            up1_left_tv.setTextColor(Color.BLACK);
        }
        up1_left_tv.setText(hdpTitle);
    }

    public void handleOddsContent(MyRecyclerViewHolder helper, I item, int position) {
        final FrameLayout sl = helper.getView(R.id.module_center_sl);


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

    }


    private void addAddedByColor(String f1, String f2, String oid, boolean isHalf, LinearLayout parent, I item, String up1, String up2, String type1, String type2, String sc1, String sc2, int itemRes, String colorType
            , boolean hasPar1
            , boolean hasPar2
            , boolean hasBet1
            , boolean hasBet2
            , boolean isShowBet1
            , boolean isShowBet2
            , String up11, String up21
    ) {
        addAddedByColor(f1, f2, "", oid, isHalf, parent, item,
                up1, up2, "", type1, type2, "", sc1, sc2, "", colorType
                , hasPar1
                , hasPar2
                , false
                , hasBet1
                , hasBet2
                , false
                , isShowBet1
                , isShowBet2
                , false
                , up11, up21

        );
        View viewById = parent.getChildAt(parent.getChildCount() - 1).findViewById(R.id.content3_ll);
        viewById.setVisibility(View.GONE);
    }

    private void addAddedFor1X2(String f1, String x, String f2, String oid, boolean isHalf, LinearLayout parent, I item,
                                String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3, int itemRes
            , boolean hasPar1
            , boolean hasPar2
            , boolean hasPar3
            , boolean hasBet1
            , boolean hasBet2
            , boolean hasBet3
            , boolean isShowBet1
            , boolean isShowBet2
            , boolean isShowBet3
            , String isHomeGive

    ) {
        LogUtil.d("additionMap", "--------------addAddedFor1X2:");
        View inflate1X2 = LayoutInflater.from(context).inflate(itemRes, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);

        if (isHomeGive.equals("1")) {
            up1_tv.setTextColor(ContextCompat.getColor(context, R.color.red));
            down1_tv.setTextColor(ContextCompat.getColor(context, R.color.red));
            up3_tv.setTextColor(ContextCompat.getColor(context, R.color.black_grey));
            down3_tv.setTextColor(ContextCompat.getColor(context, R.color.black_grey));
        } else {
            up1_tv.setTextColor(ContextCompat.getColor(context, R.color.black_grey));
            down1_tv.setTextColor(ContextCompat.getColor(context, R.color.black_grey));
            up3_tv.setTextColor(ContextCompat.getColor(context, R.color.red));
            down3_tv.setTextColor(ContextCompat.getColor(context, R.color.red));
        }
        set1x2MixStyle(oid, type1, type2, type3, sc1, sc2, sc3, inflate1X2);

        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);
        setTextValueClick(down1_tv, f1, type1, oid, item, isHalf, sc1, hasPar1, hasBet1, isShowBet1);
        setTextValueClick(down2_tv, x, type2, oid, item, isHalf, sc2, hasPar2, hasBet2, isShowBet2);
        setTextValueClick(down3_tv, f2, type3, oid, item, isHalf, sc3, hasPar3, hasBet3, isShowBet3);
        parent.addView(inflate1X2);
    }

    private void addAdded(String f1, String x, String f2, String oid, boolean isHalf, LinearLayout parent, I item,
                          String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3, int itemRes
            , boolean hasPar1
            , boolean hasPar2
            , boolean hasPar3
            , boolean hasBet1
            , boolean hasBet2
            , boolean hasBet3
            , boolean isShowBet1
            , boolean isShowBet2
            , boolean isShowBet3
    ) {

        View inflate1X2 = LayoutInflater.from(context).inflate(itemRes, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);

        set1x2MixStyle(oid, type1, type2, type3, sc1, sc2, sc3, inflate1X2);

        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);
        setTextValueClick(down1_tv, f1, type1, oid, item, isHalf, sc1, hasPar1, hasBet1, isShowBet1);
        setTextValueClick(down2_tv, x, type2, oid, item, isHalf, sc2, hasPar2, hasBet2, isShowBet2);
        setTextValueClick(down3_tv, f2, type3, oid, item, isHalf, sc3, hasPar3, hasBet3, isShowBet3);
        parent.addView(inflate1X2);
    }

    private void set1x2MixStyle(String oid, String type1, String type2, String type3, String sc1, String sc2, String sc3, View inflate1X2) {
        View content1_ll = inflate1X2.findViewById(R.id.content1_ll);
        View content2_ll = inflate1X2.findViewById(R.id.content2_ll);
        View content3_ll = inflate1X2.findViewById(R.id.content3_ll);
        HashMap<String, View> map = new HashMap<>();
        map.put(type1 + sc1, content1_ll);
        map.put(type2 + sc2, content2_ll);
        map.put(type3 + sc3, content3_ll);
        setMixBg(R.drawable.match_odds_content_bg_mix, map, oid);
    }

    private void addAddedByColor(String down1, String down2, String down3, String oid, boolean isHalf, LinearLayout parent, I item,
                                 String up1, String up2, String up3, String type1, String type2, String type3, String sc1, String sc2, String sc3, String colorType

            , boolean hasPar1
            , boolean hasPar2
            , boolean hasPar3
            , boolean hasBet1
            , boolean hasBet2
            , boolean hasBet3
            , boolean isShowBet1
            , boolean isShowBet2
            , boolean isShowBet3
            , String up11, String up21
    ) {


        View inflate1X2 = LayoutInflater.from(context).inflate(R.layout.addition_1x2_sport_item, null);
        TextView up1_tv = inflate1X2.findViewById(R.id.up1_tv);
        TextView up11_tv = inflate1X2.findViewById(R.id.up11_tv);
        TextView up21_tv = inflate1X2.findViewById(R.id.up21_tv);
        TextView down1_tv = inflate1X2.findViewById(R.id.down1_tv);
        TextView up2_tv = inflate1X2.findViewById(R.id.up2_tv);
        TextView down2_tv = inflate1X2.findViewById(R.id.down2_tv);
        TextView up3_tv = inflate1X2.findViewById(R.id.up3_tv);
        TextView down3_tv = inflate1X2.findViewById(R.id.down3_tv);
        if (colorType.equals("1")) {
            up1_tv.setTextColor(Color.BLUE);
            up2_tv.setTextColor(Color.RED);

        } else if (colorType.equals("2")) {
            up1_tv.setTextColor(Color.RED);
            up2_tv.setTextColor(Color.RED);

        } else if (colorType.equals("3")) {
            up1_tv.setTextColor(Color.BLACK);
            up2_tv.setTextColor(Color.BLACK);
        }
        down1_tv.setTextColor(getValueColor(down1));
        down2_tv.setTextColor(getValueColor(down2));
        setTextValue(up1_tv, up1);
        setTextValue(up2_tv, up2);
        setTextValue(up3_tv, up3);

        setTextValue(up11_tv, up11);
        setTextValue(up21_tv, up21);
        set1x2MixStyle(oid, type1, type2, type3, sc1, sc2, sc3, inflate1X2);

        setTextValueClick(down1_tv, down1, type1, oid, item, isHalf, sc1, hasPar1, hasBet1, isShowBet1);
        setTextValueClick(down2_tv, down2, type2, oid, item, isHalf, sc2, hasPar2, hasBet2, isShowBet2);
        setTextValueClick(down3_tv, down3, type3, oid, item, isHalf, sc3, hasPar3, hasBet3, isShowBet3);
        parent.addView(inflate1X2);
    }

    private void addAdditionHdpOu(
            String homeOdds,
            String awayOdds,
            String overOdds,
            String underOdds,
            String oid, boolean isHalf, LinearLayout parent, I item


            , boolean hasBetOver
            , boolean hasBetUnder
            , boolean isShowBetOu
            , String ou

            , boolean hasBetHDPH
            , boolean hasBetHDPA
            , boolean isShowBetHDP
            , String hdp
            , boolean isHomeGive

    ) {


        View inflate1X2 = LayoutInflater.from(context).inflate(R.layout.addition_hdp_ou_15_sport_item, null);

        TextView over_tv = inflate1X2.findViewById(R.id.over_tv);
        TextView under_tv = inflate1X2.findViewById(R.id.under_tv);

        TextView over_odds_tv = inflate1X2.findViewById(R.id.over_odds_tv);
        TextView under_odds_tv = inflate1X2.findViewById(R.id.under_odds_tv);


        TextView home_tv = inflate1X2.findViewById(R.id.home_tv);
        TextView away_tv = inflate1X2.findViewById(R.id.away_tv);

        TextView home_odds_tv = inflate1X2.findViewById(R.id.home_odds_tv);
        TextView away_odds_tv = inflate1X2.findViewById(R.id.away_odds_tv);


        over_odds_tv.setTextColor(getValueColor(overOdds));
        under_odds_tv.setTextColor(getValueColor(underOdds));

        home_odds_tv.setTextColor(getValueColor(homeOdds));
        away_odds_tv.setTextColor(getValueColor(awayOdds));
        setCommonHdpOuMixStyle(oid, inflate1X2);
        setTextValue(over_tv, changeValueF(ou));
        setTextValue(under_tv, "");
        String homeHdp = "";
        String awayHdp = "";
        if (isHomeGive) {
            homeHdp = changeValueF(hdp);
        } else {
            awayHdp = changeValueF(hdp);
        }
        setTextValue(home_tv, homeHdp);
        setTextValue(away_tv, awayHdp);

        setTextValueClick(over_odds_tv, overOdds, "over", oid, item, isHalf, "", false, hasBetOver, isShowBetOu);
        setTextValueClick(under_odds_tv, underOdds, "under", oid, item, isHalf, "", false, hasBetUnder, isShowBetOu);
        setTextValueClick(home_odds_tv, homeOdds, "home", oid, item, isHalf, "", false, hasBetHDPH, isShowBetHDP);
        setTextValueClick(away_odds_tv, awayOdds, "away", oid, item, isHalf, "", false, hasBetHDPA, isShowBetHDP);


        parent.addView(inflate1X2);
    }

    private void setCommonHdpOuMixStyle(String oid, View inflate1X2) {
        View ll_home = inflate1X2.findViewById(R.id.ll_home);
        View ll_away = inflate1X2.findViewById(R.id.ll_away);
        View ll_over = inflate1X2.findViewById(R.id.ll_over);
        View ll_under = inflate1X2.findViewById(R.id.ll_under);
        setHdpOuMixStyle(R.drawable.match_odds_content_bg_mix, ll_home, ll_away, ll_over, ll_under, oid);
    }

    private int getValueColor(String value) {
        if (StringUtils.isNull(value))
            return Color.BLACK;
        try {
            return Float.valueOf(value) < 0 ? Color.RED : Color.BLACK;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Color.BLACK;
    }


    private void addTitle(LinearLayout parent, View inflate, int titleRes) {
        addTitle(parent, inflate, context.getString(titleRes));
    }

    private void addTitle(LinearLayout parent, View inflate, String title) {
        TextView titleTv = inflate.findViewById(R.id.title_tv);
        titleTv.setText(title);
        parent.addView(inflate);
    }

    private void setTextValueClick(final TextView textView, String content, final String type, final String oid, final I item, final boolean isHalf, final String sc, final boolean hasHar, final boolean hasBet, boolean isShowBet) {

        if (StringUtils.isNull(content) || StringUtils.isNull(type) || StringUtils.isNull(oid)) {
            return;
        }
        if (!hasBet) {
            if (isShowBet) {
                content = content.replaceAll(",", ".");
                try {
                    if (Float.valueOf(content) == 0)
                        return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                textView.setText(content);
            } else {
                textView.setText("");
            }
            ((View) textView.getParent()).setOnClickListener(null);

        } else {
            content = content.replaceAll(",", ".");
            try {
                if (Float.valueOf(content) == 0)
                    return;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            textView.setText(content);
            final String finalContent = content;
            ((View) textView.getParent()).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (back != null) {
                        back.clickOdds(textView, item, type, isHalf, finalContent, Integer.valueOf(oid), sc, hasHar);
                        getBaseRecyclerAdapter().notifyDataSetChanged();
                    }
                }
            });
        }
    }

    private void setTextValue(TextView tv, String s) {
        tv.setText(s);
    }

    private void addMainAddedView(LinearLayout parent, AddMBean.OddsBean fTodds, AddMBean.OddsBean fHodds, I item, int res, int mixBg) {
        View inflate = LayoutInflater.from(context).inflate(res, null);
        TextView home_tv = inflate.findViewById(R.id.home_tv);

        TextView home_odd_tv = inflate.findViewById(R.id.home_odd_tv);
        TextView away_tv = inflate.findViewById(R.id.away_tv);
        TextView away_odd_tv = inflate.findViewById(R.id.away_odd_tv);

        TextView hf_home_tv = inflate.findViewById(R.id.hf_home_tv);
        TextView hf_home_odd_tv = inflate.findViewById(R.id.hf_home_odd_tv);
        TextView hf_away_tv = inflate.findViewById(R.id.hf_away_tv);
        TextView hf_away_odd_tv = inflate.findViewById(R.id.hf_away_odd_tv);

        TextView home_title_tv = inflate.findViewById(R.id.home_title_tv);
        TextView away_title_tv = inflate.findViewById(R.id.away_title_tv);
        TextView hf_home_title_tv = inflate.findViewById(R.id.hf_home_title_tv);
        TextView hf_away_title_tv = inflate.findViewById(R.id.hf_away_title_tv);

        TextView over_tv = inflate.findViewById(R.id.over_tv);
        TextView over_odd_tv = inflate.findViewById(R.id.over_odd_tv);
        TextView under_tv = inflate.findViewById(R.id.under_tv);
        TextView under_odd_tv = inflate.findViewById(R.id.under_odd_tv);

        TextView hf_over_tv = inflate.findViewById(R.id.hf_over_tv);
        TextView hf_over_odd_tv = inflate.findViewById(R.id.hf_over_odd_tv);
        TextView hf_under_tv = inflate.findViewById(R.id.hf_under_tv);
        TextView hf_under_odd_tv = inflate.findViewById(R.id.hf_under_odd_tv);

        LinearLayout ll_home_ft = inflate.findViewById(R.id.ll_home_ft);
        LinearLayout ll_away_ft = inflate.findViewById(R.id.ll_away_ft);
        LinearLayout ll_over_ft = inflate.findViewById(R.id.ll_over_ft);
        LinearLayout ll_under_ft = inflate.findViewById(R.id.ll_under_ft);

        LinearLayout ll_home_fh = inflate.findViewById(R.id.ll_home_fh);
        LinearLayout ll_away_fh = inflate.findViewById(R.id.ll_away_fh);
        LinearLayout ll_over_fh = inflate.findViewById(R.id.ll_over_fh);
        LinearLayout ll_under_fh = inflate.findViewById(R.id.ll_under_fh);




         /*       ballInfo.setHome(fTodds.getHome());
        ballInfo.setAway(fTodds.getAway());*/
   /*      ballInfo.setOver(fTodds.getOver());
        ballInfo.setUnder(fTodds.getUnder());*/
        BallInfo ballInfo = new BallInfo();
        ballInfo.setModuleTitle(item.getModuleTitle());
        ballInfo.setModuleId(item.getModuleId());
        ballInfo.setHome(item.getHome());
        ballInfo.setHomeId(item.getHomeId());
        ballInfo.setAway(item.getAway());
        ballInfo.setAwayId(item.getAwayId());
        ballInfo.setRTSMatchId(item.getRTSMatchId());
        ballInfo.setMainAwayId(item.getMainAwayId());
        ballInfo.setMainHomeId(item.getMainHomeId());
        ballInfo.setMainModuleId(item.getMainModuleId());


        if (fTodds != null) {
            String socOddsId = fTodds.getSocOddsId();

            setHdpOuMixStyle(mixBg, ll_home_ft, ll_away_ft, ll_over_ft, ll_under_ft, socOddsId);

            ballInfo.setSocOddsId(socOddsId);
            ballInfo.setHdp(fTodds.getHDP());
            ballInfo.setHOdds(fTodds.getHomeOdds());
            ballInfo.setAOdds(fTodds.getAwayOdds());
            ballInfo.setOU(fTodds.getOU());
            ballInfo.setOOdds(fTodds.getOverOdds());
            ballInfo.setUOdds(fTodds.getUnderOdds());
            ballInfo.setIsHomeGive(fTodds.getIsHomeGive().equals("True") ? "1" : "0");
            home_title_tv.setTextColor(black_grey);
            away_title_tv.setTextColor(black_grey);

            ballInfo.setHasPar(fTodds.getHasPar() != null && fTodds.getHasPar().equals("True") ? "1" : "0");
            ballInfo.setHdpOdds(fTodds.getHdpOdds());
            ballInfo.setOUOdds(fTodds.getOUOdds());
            ballInfo.setOU(fTodds.getOU());
            ballInfo.setRunHomeScore(fTodds.getRunHomeScore());
            ballInfo.setRunAwayScore(fTodds.getRunAwayScore());
            ballInfo.setIsInetBet((fTodds.getHdp_visible().equals("True") || fTodds.getOU_visible().equals("True")) ? "1" : "0");

        } else {
            ballInfo.setSocOddsId("");
            ballInfo.setHdp("");
            ballInfo.setHOdds("");
            ballInfo.setAOdds("");
            ballInfo.setOU("");
            ballInfo.setOOdds("");
            ballInfo.setUOdds("");
            ballInfo.setHasPar("");
            ballInfo.setHdpOdds("");
            ballInfo.setOU("");
            ballInfo.setOUOdds("");
            ballInfo.setRunHomeScore("");
            ballInfo.setRunAwayScore("");
            home_title_tv.setTextColor(black_grey);
            away_title_tv.setTextColor(black_grey);
        }
        if (fHodds != null) {
            String socOddsId = fHodds.getSocOddsId();

            setHdpOuMixStyle(mixBg, ll_home_fh, ll_away_fh, ll_over_fh, ll_under_fh, socOddsId);

            ballInfo.setSocOddsId_FH(fHodds.getSocOddsId());
            ballInfo.setHdp_FH(fHodds.getHDP());
            ballInfo.setHOdds_FH(fHodds.getHomeOdds());
            ballInfo.setAOdds_FH(fHodds.getAwayOdds());
            ballInfo.setOU_FH(fHodds.getOU());
            ballInfo.setOOdds_FH(fHodds.getOverOdds());
            ballInfo.setUOdds_FH(fHodds.getUnderOdds());
            ballInfo.setIsHomeGive_FH(fHodds.getIsHomeGive().equals("True") ? "1" : "0");
            hf_home_title_tv.setTextColor(black_grey);
            hf_away_title_tv.setTextColor(black_grey);


            ballInfo.setHasPar_FH(fHodds.getHasPar() != null && fHodds.getHasPar().equals("True") ? "1" : "0");

            ballInfo.setHdpOdds_FH(fHodds.getHdpOdds());
            ballInfo.setOU_FH(fHodds.getOU());
            ballInfo.setOUOdds_FH(fHodds.getOUOdds());
            ballInfo.setRunHomeScore_FH(fHodds.getRunHomeScore());
            ballInfo.setRunAwayScore_FH(fHodds.getRunAwayScore());
            ballInfo.setIsInetBet_FH((fHodds.getHdp_visible().equals("True") || fHodds.getOU_visible().equals("True")) ? "1" : "0");
        } else {
            ballInfo.setSocOddsId_FH("");
            ballInfo.setHdp_FH("");
            ballInfo.setHOdds_FH("");
            ballInfo.setAOdds_FH("");
            ballInfo.setOU_FH("");
            ballInfo.setOOdds_FH("");
            ballInfo.setUOdds_FH("");
            ballInfo.setHasPar_FH("");
            ballInfo.setHdpOdds_FH("");
            ballInfo.setOU_FH("");
            ballInfo.setRunHomeScore_FH("");
            ballInfo.setRunAwayScore_FH("");
            ballInfo.setOUOdds_FH("");
            hf_home_title_tv.setTextColor(black_grey);
            hf_away_title_tv.setTextColor(black_grey);

        }
        setUpDownOdds(true, true, (I) ballInfo, false, "0", ballInfo.getHasHdp(), ballInfo.getHdp(), home_tv, away_tv, home_odd_tv, away_odd_tv, ballInfo.getHOdds(), ballInfo.getAOdds(), "home", "away", 1, null, null);
        setUpDownOdds(true, true, (I) ballInfo, true, "0", ballInfo.getHasHdp_FH(), ballInfo.getHdp_FH(), hf_home_tv, hf_away_tv, hf_home_odd_tv, hf_away_odd_tv, ballInfo.getHOdds_FH(), ballInfo.getAOdds_FH(), "home", "away", 1, null, null);
        //setUpDownOdds(ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv, overOdds, underOdds, overOddsType, underOddsType);
        setUpDownOdds(true, true, (I) ballInfo, false, "0", ballInfo.getHasOU(), ballInfo.getOU(), over_tv, under_tv, over_odd_tv, under_odd_tv, ballInfo.getOOdds(), ballInfo.getUOdds(), "over", "under", 1, null, null);
        setUpDownOdds(true, true, (I) ballInfo, true, "0", ballInfo.getHasOU_FH(), ballInfo.getOU_FH(), hf_over_tv, hf_under_tv, hf_over_odd_tv, hf_under_odd_tv, ballInfo.getOOdds_FH(), ballInfo.getUOdds_FH(), "over", "under", 1, null, null);
        parent.addView(inflate);
    }

    private void setHdpOuMixStyle(int mixBg, View ll_home_fh, View ll_away_fh, View ll_over_fh, View ll_under_fh, String socOddsId) {
        HashMap<String, View> map = new HashMap<>();
        map.put("home", ll_home_fh);
        map.put("away", ll_away_fh);
        map.put("over", ll_over_fh);
        map.put("under", ll_under_fh);
        setMixBg(mixBg, map, socOddsId);
    }

    private void setMixBg(int mixBg, HashMap<String, View> map, String socOddsId) {
        OddsClickBean mixItem = handler.getMixItem(socOddsId);
        if (mixItem == null)
            return;
        String type = mixItem.getType();
        if (StringUtils.isNull(type))
            return;
        String types = "";
        String sc = mixItem.getSc();
        if (!StringUtils.isNull(sc))
            types = type + sc;
        else
            types = type;
        View view = map.get(types);
        if (view != null) {
            view.setBackgroundResource(mixBg);
        }

    }

    protected void updateMixBackground(BallInfo item, FrameLayout sl, String type01, String type02, String type11, String type12, String type21, String type22) {
        handler.updateMixBackground(item, sl, type01, type02, type11, type12, type21, type22);
    }

    protected void onMatchNotRepeat(MyRecyclerViewHolder helper, final I item, final int position) {
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        tvRightMark.setVisibility(View.VISIBLE);
        View collectionTv = helper.getView(R.id.module_match_collection_tv);
        collectionTv.setVisibility(View.VISIBLE);
    }


    protected void onMatchRepeat(MyRecyclerViewHolder helper, I item, int position) {
      /*  View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        tvRightMark.setVisibility(View.INVISIBLE);*/
        View collectionTv = helper.getView(R.id.module_match_collection_tv);
        collectionTv.setVisibility(View.INVISIBLE);
    }


    protected View scrollChild(View vp, boolean isFh, I item, String isHomeGive, String hasHdp, String hdp, String hasOU, String ou, String isHdpNew, String isOUNew, String underOdds, String overOdds, String homeHdpOdds, String awayHdpOdds) {
        return scrollChild(vp, isFh, item, isHomeGive, hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds, "home", "away", "over", "under", true, true, false, "", "", "", "", "", "");
    }


    protected View scrollChild(View vp, boolean isFh, I item, String isHomeGive, String hasHdp, String hdp, String hasOU, String ou, String isHdpNew, String isOUNew, String underOdds, String overOdds, String homeHdpOdds, String awayHdpOdds, String homeOddsType, String awayOddsType, String overOddsType, String underOddsType
            , boolean hapVisiable, boolean ouVisiable, boolean oeVisiable, String hasOE, String isOENew, String OddOdds, String EvenOdds, String OddOddsType, String EvenOddsType) {
        vp.setVisibility(View.VISIBLE);
        ViewHolder holder = new ViewHolder(vp);
//        Log.d(TAG, "sid: " + item.getSocOddsId() + ",item.isOverBigger:" + item.isOverBigger + ",item.isHomeBigger:" + item.isHomeBigger + ",item.isAwayBigger:" + item.isAwayBigger + ",item.isUnderBigger:" + item.isUnderBigger);
        setUpDownOdds(true, hapVisiable, item, isFh, isHdpNew, hasHdp, hdp, holder.viewpagerMatchHomeHdpTv, holder.viewpagerMatchVisitHdpTv, holder.viewpagerMatchHomeHdpoddsTv, holder.viewpagerMatchVisitHdpoddsTv
                , homeHdpOdds, awayHdpOdds, homeOddsType, awayOddsType, 0, holder.imgUpDownUp1, holder.imgUpDownDown1);
        setUpDownOdds(true, ouVisiable, item, isFh, isOUNew, hasOU, ou, holder.viewpagerMatchOuTv, holder.viewpagerMatchOu2Tv, holder.viewpagerMatchOveroddsTv, holder.viewpagerMatchUnderoddsTv
                , overOdds, underOdds, overOddsType, underOddsType, 0, holder.imgUpDownUp2, holder.imgUpDownDown2);
    /*    setUpDownOdds(oeVisiable, item, isFh, isOENew, hasOE, "", holder.viewpagerOddLabelTv, holder.viewpagerEvenLabelTv, holder.viewpagerMatchOddTv, holder.viewpagerMatchEvenTv
                , OddOdds, EvenOdds, OddOddsType, EvenOddsType);*/
        return vp;
    }

    private void setUpDownOddsForOut(boolean visiableUpDown, I item, boolean isFh, String isNew, String hasUpDown, String upDown, TextView upTextTv, TextView downTextTv, TextView upOddsTv, TextView downOddsTv, String upOdds, String downOdds, String upType, String downType, int visibilityType, ImageView imgUpDown1, ImageView imgUpDown2) {

        if (visiableUpDown) {
            upTextTv.setVisibility(View.VISIBLE);
            upOddsTv.setVisibility(View.VISIBLE);
            downTextTv.setVisibility(View.VISIBLE);
            downOddsTv.setVisibility(View.VISIBLE);
            String upStr = "";
            String downStr = "";
            if (upOdds.trim().isEmpty() || downOdds.trim().isEmpty() || upOdds.equals("0") || downOdds.equals("0") || Math.abs(Float.valueOf(upOdds)) <= 0.3 || Math.abs(Float.valueOf(downOdds)) <= 0.3) {
                hasUpDown = "0";
            }

            int resUp = R.mipmap.arrow_up_update_green;
            int resDown = R.mipmap.arrow_up_update_green;
            String isHomeGive = item.getIsHomeGive();
            if (isFh) {
                isHomeGive = item.getIsHomeGive_FH();
            }
            switch (upType) {
                case "home":
                case "mmhome":
                    if (upDown.isEmpty()) {
                        hasUpDown = "0";
                    }
                    String hdpS = upDown;
                    if (!upDown.equals("0"))
                        hdpS = "-" + upDown;
                    if (isHomeGive.equals("1")) {
                        upStr = hdpS;
                        downStr = "";
                    } else {
                        upStr = "";
                        downStr = hdpS;
                    }
                    upTextTv.setTextColor(Color.RED);
                    resUp = getResUpdate(item.isHomeBigger, isFh);

                    break;
                case "over":
                case "mmover":
                    if (upDown.isEmpty() || upDown.equals("0")) {
                        hasUpDown = "0";
                    }
                    String ouf = upDown;
                    upStr = ouf;
                    downStr = "";
                    upTextTv.setTextColor(Color.BLACK);
                    resUp = getResUpdate(item.isOverBigger, isFh);
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
                    downTextTv.setTextColor(Color.RED);
                    resDown = getResUpdate(item.isAwayBigger, isFh);
                    break;
                case "under":
                case "mmunder":
                    downTextTv.setTextColor(Color.BLACK);
                    resDown = getResUpdate(item.isUnderBigger, isFh);
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
                if (TextUtils.isEmpty(downStr) && downType.contains("under")) {
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

    public boolean getAddHDPVisible(String upOdds, String downOdds, String hdpOdds) {
        boolean b = true;
        if (TextUtils.isEmpty(upOdds)) {
            return false;
        }
        if (TextUtils.isEmpty(downOdds)) {
            return false;
        }
        String type = act.getApp().getOddsType().getType();
        double odds1 = Double.parseDouble(upOdds);
        double odds2 = Double.parseDouble(downOdds);
        if (type.equals("EU")) {
            odds1 = odds1 - 10;
            odds2 = odds2 - 10;
        }
        if (hdpOdds.equals("0")) {
            b = false;
        }
        odds1 = odds1 / 10;
        odds2 = odds2 / 10;
        if (odds1 == 0 || odds2 == 0) {
            b = false;
        }
        if (odds1 > 1 || odds1 < -1) {
            odds1 = -1 / odds1;
        }
        if (odds2 > 1 || odds2 < -1) {
            odds2 = -1 / odds2;
        }
        if (odds1 < 0 && odds2 < 0) {
            b = false;
        }
        if (odds1 < 0 && odds2 > 0) {
            if (Math.abs(odds1) < odds2) {
                b = false;
            }
        } else if (odds2 < 0 && odds1 > 0) {
            if (Math.abs(odds2) < odds1) {
                b = false;
            }
        }
        if (odds1 > 0 && odds1 < 0.03) {
            b = false;
        }
        if (odds2 > 0 && odds2 < 0.03) {
            b = false;
        }
        return b;
    }

    private boolean getAddOUVisible(String ou, String RunHomeScore, String RunAwayScore) {
        if (TextUtils.isEmpty(ou) || TextUtils.isEmpty(RunAwayScore) || TextUtils.isEmpty(RunHomeScore)) {
            return false;
        }
        double dOu = Double.parseDouble(ou);
        if (dOu < 0) {
            return false;
        } else {
            return (dOu - (Double.parseDouble(RunHomeScore) + Double.parseDouble(RunAwayScore))) >= 0.5;
        }
    }


    public void setUpDownOdds(boolean isMainData, boolean visiableUpDown, I item, boolean isFh, String isNew, String hasUpDown, String upDown, TextView upTextTv, TextView downTextTv, TextView upOddsTv, TextView downOddsTv, String upOdds, String downOdds, String upType, String downType, int visibilityType, ImageView imgUpDown1, ImageView imgUpDown2) {

        if (visiableUpDown) {
            upTextTv.setVisibility(View.VISIBLE);
            upOddsTv.setVisibility(View.VISIBLE);
            downTextTv.setVisibility(View.VISIBLE);
            downOddsTv.setVisibility(View.VISIBLE);
            String upStr = "";
            String downStr = "";
            if (upType.equals("home") && downType.equals("away")) {
                String hdpOdds;
                if (isFh) {
                    hdpOdds = item.getHdpOdds_FH();
                } else {
                    hdpOdds = item.getHdpOdds();
                }
                boolean isHDPVisible = getAddHDPVisible(upOdds, downOdds, hdpOdds);
                if (isHDPVisible) {
                    hasUpDown = "1";
                } else {
                    hasUpDown = "0";
                }
            } else if (upType.equals("over") && downType.equals("under")) {
                String hdpOdds;
                String ou;
                String RunHomeScore;
                String RunAwayScore;
                if (isFh) {
                    ou = item.getOU_FH();
                    hdpOdds = item.getOUOdds_FH();
                    RunHomeScore = item.getRunHomeScore_FH();
                    RunAwayScore = item.getRunAwayScore_FH();
                } else {
                    ou = item.getOU();
                    hdpOdds = item.getOUOdds();
                    RunHomeScore = item.getRunHomeScore();
                    RunAwayScore = item.getRunAwayScore();
                }
                boolean isHDPVisible = getAddHDPVisible(upOdds, downOdds, hdpOdds);
                boolean isOUVisible = getAddOUVisible(ou, RunHomeScore, RunAwayScore);
                if (isHDPVisible && isOUVisible) {
                    hasUpDown = "1";
                } else {
                    hasUpDown = "0";
                }
            } else {
                if (upOdds.trim().isEmpty() || downOdds.trim().isEmpty() || upOdds.equals("0") || downOdds.equals("0") || Math.abs(Float.valueOf(upOdds)) <= 0.3 || Math.abs(Float.valueOf(downOdds)) <= 0.3) {
                    hasUpDown = "0";
                }
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
                    if (!upDown.equals("0")) {
                        hdpS = "-" + upDown;
                    } else if (!isMainData) {
                        hdpS = "";
                    }

                    if (!isFh) {
                        if (item.getIsHomeGive().equals("1")) {
                            upStr = hdpS;
                            downStr = "";
                        } else {
                            upStr = "";
                            downStr = hdpS;
                        }
                    } else {

                        if (!StringUtils.isNull(item.getIsHomeGive_FH()) && item.getIsHomeGive_FH().equals("1")) {
                            upStr = hdpS;
                            downStr = "";
                        } else {
                            upStr = "";
                            downStr = hdpS;
                        }
                    }

                    upTextTv.setTextColor(ContextCompat.getColor(context, R.color.red_background));
                    resUp = getResUpdate(item.isHomeBigger, isFh);

                    break;
                case "over":
                case "mmover":
                    if (upDown.isEmpty() || upDown.equals("0")) {
                        hasUpDown = "0";
                    }
                    String ouf = upDown;
                    upStr = ouf;
                    downStr = "";
                    upTextTv.setTextColor(ContextCompat.getColor(context, R.color.grey_hdp_ou));
                    resUp = getResUpdate(item.isOverBigger, isFh);
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
                    downTextTv.setTextColor(ContextCompat.getColor(context, R.color.red_background));
                    resDown = getResUpdate(item.isAwayBigger, isFh);
                    break;
                case "under":
                case "mmunder":
                    downTextTv.setTextColor(ContextCompat.getColor(context, R.color.grey_hdp_ou));
                    resDown = getResUpdate(item.isUnderBigger, isFh);
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
                if (TextUtils.isEmpty(downStr) && downType.contains("under")) {
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

    private int getResUpdate(int bigger, boolean isFh) {
        int resUp;
        if (isFh) {
            resUp = 0;
        } else {
            if (bigger == 1)
                resUp = R.mipmap.arrow_up_update_green;
            else if (bigger == -1)
                resUp = R.mipmap.arrow_down_update_red;
            else {
                resUp = 0;
            }
        }
        return resUp;
    }

    private String changeValueF(String f) {
//        "0-0.5"
        String temp = AfbUtils.delHTMLTag(f);

        if (temp != null && temp.contains("-")) {
            try {
                String[] split = temp.split("-");
                if (split.length > 1) {
                    String s = subZeroAndDot((Float.parseFloat(split[0]) + Float.parseFloat(split[1])) / 2);
                    return s;
                }
            }catch (Exception e){
                e.printStackTrace();
                return temp;
            }
        }
        return temp;


       /* try {
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
        }*/
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
        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        if (StringUtils.isNull(f) || f.equals("0")) {
            textView.setText("");
        } else {

            value = value.replaceAll(",", ".");
            float odds = 0f;
            try {
                odds = Float.parseFloat(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
            odds = odds / 10;
//            float s = Math.round(odds * 1000) / 1000f;

            String s = AfbUtils.scientificCountingToString(odds + "", "0.000");
            String substring = s.substring(s.length() - 1, s.length());
            String showOdds;
            if (substring.equals("0")) {
                showOdds = s.substring(0, s.length() - 1);
            } else {
                showOdds = s;
            }
//            String showOdds = s + "";
            textView.setBackgroundResource(0);
            if (!f.equals("") && !f.equals("0")) {
                if (isAnimation && imgUpDown != null && resUpdate != 0) {
                    imgUpDown.setImageResource(resUpdate);
                    imgUpDown.setVisibility(View.VISIBLE);
                }

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LogUtil.d("xxxxx", item.toString());
                        if (isHf) {
                            if (item.getIsInetBet_FH() == null || !item.getIsInetBet_FH().equals("1"))
                                return;
                        } else {
                            if (item.getIsInetBet() == null || !item.getIsInetBet().equals("1"))
                                return;
                        }
                        back.clickOdds((TextView) v, item, type, isHf, f, Integer.valueOf(isHf ? item.getSocOddsId_FH() : item.getSocOddsId()), "", isHf ? item.getHasPar_FH().equals("1") : item.getHasPar().equals("1"));
                        getBaseRecyclerAdapter().notifyDataSetChanged();

                    }
                });
            } else {
                textView.setOnClickListener(null);
            }
            if (isShowText) {
                try {
                    if (f.startsWith("-")) {
                        textView.setTextColor(red_black);
                    } else {
                        textView.setTextColor(black_grey);
                    }
                    textView.setText(showOdds);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }


        return f;
    }

    @Override
    public int onSetAdapterItemLayout() {
        return R.layout.sport_common_ball_item1;
    }

    public Map<Boolean, String> getAdditionMap() {
        return additionMap;
    }


    public void changeAdded(IRTMatchInfo item, boolean r) {
        String id = additionMap.get(true);
        if (!StringUtils.isNull(id) && id.trim().equals(item.getSocOddsId().trim())) {
            LogUtil.d("additionMap", additionMap.get(true) + ",点击两次关闭");
            additionMap.put(true, "");
        } else {
            liveSelectedHelper.putIndex(0);
            LogUtil.d("additionMap", additionMap.get(true) + ",点击1次打开");
            additionMap.put(true, item.getSocOddsId().trim());
            List<I> is = baseRecyclerAdapter.getmDatas();
            Iterator<I> iterator = is.iterator();
            if (r) {
                boolean outShow = false;
                while (iterator.hasNext()) {
                    I next = iterator.next();
                    next.outShow = outShow;
                    if (next.getSocOddsId().equals(item.getSocOddsId())) {
                        outShow = true;
                    }
                }
            }

        }

        getBaseRecyclerAdapter().notifyDataSetChanged();
    }


    public static class ViewHolder {
        @BindView(R.id.viewpager_match_home_hdp_tv)
        public TextView viewpagerMatchHomeHdpTv;
        @BindView(R.id.viewpager_match_home_hdpodds_tv)
        public TextView viewpagerMatchHomeHdpoddsTv;
        @BindView(R.id.viewpager_match_ou_tv)
        public TextView viewpagerMatchOuTv;
        @BindView(R.id.viewpager_match_overodds_tv)
        public TextView viewpagerMatchOveroddsTv;
        @BindView(R.id.viewpager_odd_label_tv)
        public TextView viewpagerOddLabelTv;
        @BindView(R.id.viewpager_match_odd_tv)
        public TextView viewpagerMatchOddTv;
        @BindView(R.id.viewpager_match_visit_hdp_tv)
        public TextView viewpagerMatchVisitHdpTv;
        @BindView(R.id.viewpager_match_visit_hdpodds_tv)
        public TextView viewpagerMatchVisitHdpoddsTv;
        @BindView(R.id.viewpager_match_ou2_tv)
        public TextView viewpagerMatchOu2Tv;
        @BindView(R.id.viewpager_match_underodds_tv)
        public TextView viewpagerMatchUnderoddsTv;
        @BindView(R.id.viewpager_even_label_tv)
        public TextView viewpagerEvenLabelTv;
        @BindView(R.id.viewpager_match_even_tv)
        public TextView viewpagerMatchEvenTv;
        @BindView(R.id.img_up_down_up1)
        public ImageView imgUpDownUp1;
        @BindView(R.id.img_up_down_up2)
        public ImageView imgUpDownUp2;
        @BindView(R.id.img_up_down_down1)
        public ImageView imgUpDownDown1;
        @BindView(R.id.img_up_down_down2)
        public ImageView imgUpDownDown2;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public void setRunningItemBg(MyRecyclerViewHolder helper, BallInfo item) {
        View matchTitleLl = helper.getView(R.id.module_match_title_bg);
        if(matchTitleLl==null){
            matchTitleLl = helper.getView(R.id.module_match_title_ll);
        }
        View viewLine = helper.getView(R.id.view_line);
        TextView moduleMatchTimeTv = helper.getView(R.id.module_match_time_tv);
        viewLine.setBackgroundColor(ContextCompat.getColor(context, R.color.green_line));
        matchTitleLl.setBackgroundColor(ContextCompat.getColor(context, R.color.green_title));
        moduleMatchTimeTv.setTextColor(Color.BLACK);
    }

    public void updateMixNormalBackground(MyRecyclerViewHolder helper, I item) {

        FrameLayout sl = helper.getView(R.id.module_center_sl);
        updateMixBackground(item, sl, "home", "away", "over", "under", "odd", "even");
    }

    public void showLastCall(I item, TextView dateTv, ImageView lastGif,  TextView timeTv, TextView liveTv) {
        if (item.getIsLastCall() != null && item.getIsLastCall().equals("1")) {
            Glide.with(context).load(R.mipmap.lastcall).into(lastGif);
            dateTv.setVisibility(View.GONE);

            liveTv.setVisibility(View.GONE);

            timeTv.setVisibility(View.INVISIBLE);

            lastGif.setVisibility(View.VISIBLE);

        }
    }


    protected void handleLiveTimeTv(BallInfo item, TextView timeTv) {
        SoccerRunningGoalManager.getInstance().runTimeStyle(timeTv, item.getMExtraTime(), item.getStatus(), item.getCurMinute(), item.getLive());

        if (act != null && act.videoHolder != null && act.fl_top_video.getVisibility() == View.VISIBLE && act.videoHolder.tv_run_time.getVisibility() == View.VISIBLE
                && act.itemBallAdded != null && act.itemBallAdded.getSocOddsId().equals(item.getSocOddsId())
        ) {
            SoccerRunningGoalManager.getInstance().runTimeStyleColor(act.videoHolder.tv_run_time, item.getMExtraTime(), item.getStatus(), item.getCurMinute(), item.getLive(), Color.WHITE);
        }
    }

    @Override
    public boolean clickAllContracted() {
        additionMap.put(true, "");
        return super.clickAllContracted();
    }

    @Override
    public void clickOneTeamContracted(I item) {
        if (additionMap != null && additionMap.get(true) != null && item.getSocOddsId().trim().equals(additionMap.get(true)))
            additionMap.put(true, "");
        super.clickOneTeamContracted(item);
    }

}
