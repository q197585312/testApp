package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.BaseSportFragment;
import com.nanyang.app.main.home.sport.SportActivity;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.utils.ViewHolder;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnLoadMoreListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.OnRefreshListener;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;

import static cn.finalteam.toolsfinal.DeviceUtils.dip2px;

//import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;


public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<List<MatchBean>> {

    @Bind(R.id.swipe_target)
    RecyclerView rvContent;
    @Bind(R.id.tv_total_match)
    TextView tvTotalMatch;
    @Bind(R.id.tv_odds_type)
    TextView tvOddsType;
    @Bind(R.id.vp_header)
    ViewPager vpHeader;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;


    BaseRecyclerAdapter<MatchBean> baseRecyclerAdapter;
    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;
    private ArrayList<ViewPager> vps;
    private int vpPosition = 0;
    private BetBasePop betPop;

    @Override
    public void initData() {
        super.initData();
        initAdapter();
        createPresenter(new FootballPresenter(this));
        presenter.setType(((SportActivity) getActivity()).getType());
        presenter.refresh(((SportActivity) getActivity()).getType());

    }

    @Override
    public void toolbarRightClick(View v) {

        createPopupWindow(
                new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, 300) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_ball_type;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setChooseTypeAdapter(rv_list);
                    }
                });
        popWindow.showPopupDownWindow();
    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getString(R.string.Today), "Today"));
        if (!presenter.isMixParlay()) {
            types.add(new MenuItemInfo(0, getString(R.string.Running), "Running"));
        }
        types.add(new MenuItemInfo(0, getString(R.string.Early), "Early"));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                presenter.refresh(item.getType());
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    private void initAdapter() {
        vps = new ArrayList<>();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new BaseRecyclerAdapter<MatchBean>(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item) {

            String oldModuleTitle;
            String oldHomeGive;
            String oldAwayName;
            String oldHomeName;

            @Override
            public void convert(MyRecyclerViewHolder helper, final int position, final MatchBean item) {
                TextView homeRedCardTv = helper.getView(R.id.module_match_home_red_card_tv);
                TextView awayRedCardTv = helper.getView(R.id.module_match_away_red_card_tv);
                TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
                View headV = helper.getView(R.id.module_match_head_v);
                TextView dateTv = helper.getView(R.id.module_match_date_tv);
                TextView timeTv = helper.getView(R.id.module_match_time_tv);
                TextView liveTv = helper.getView(R.id.module_match_live_iv);

                TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);
                TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);

                TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);
                View llLeft1 = helper.getView(R.id.module_match_left1_ll);
                View llLeft2 = helper.getView(R.id.module_match_left2_ll);
                final TextView tvCollection = helper.getView(R.id.module_match_collection_tv);

                liveTv.setTextColor(getResources().getColor(R.color.google_yellow));
                dateTv.setTextColor(getResources().getColor(R.color.google_yellow));
                timeTv.setTextColor(getResources().getColor(R.color.green_light));
                dateTv.setTextAppearance(mContext, R.style.text_normal);
                dateTv.setPadding(0, 0, 0, 0);
                tvCollection.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.collectionItem(getItem(position));
                        notifyDataSetChanged();
                    }
                });

                if (presenter.getType().equals("Running")) {
                    dateTv.setTextAppearance(mContext, R.style.text_bold);
                    dateTv.setPadding(0, 0, 10, 0);
                    liveTv.setVisibility(View.GONE);
                    if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
                        String sHome = item.getRunHomeScore();
                        String sAway = item.getRunAwayScore();
                        dateTv.setText(sHome + "-" + sAway);

                    } else {
                        dateTv.setText("");
                    }
                    if (item.getLive().contains("HT")) {
                        timeTv.setText("HT");
                    } else {
                        int min;
                        int start;
                        try {

                            switch (item.getStatus()) {
                                case "0":
                                    timeTv.setText(getString(R.string.not_started));
                                    break;
                                case "2":
                                    min = Integer.valueOf(item.getCurMinute());
                                    start = 45;
                                    min = min + start;
                                    if (min < 130 && min > 0) {
                                        timeTv.setText(min + mContext.getString(R.string.min));
                                    } else {
                                        timeTv.setText("");
                                    }
                                    break;
                                default:
                                    min = Integer.valueOf(item.getCurMinute());
                                    if (min < 130 && min > 0) {
                                        timeTv.setText(min + mContext.getString(R.string.min));
                                    } else {
                                        timeTv.setText("");
                                    }
                                    break;
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            timeTv.setText("");
                        }
                    }
                    dateTv.setTextColor(mContext.getResources().getColor(R.color.red_title));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.red_title));
                } else {
                    if (presenter.isMixParlay()) {
                        tvCollection.setVisibility(View.GONE);
                    }
                    dateTv.setTextSize(10);
                    String date = item.getMatchDate().substring(0, 5);
                    dateTv.setText(date);
                    String time = item.getMatchDate();
                    if (time.length() > 6) {
                        time = time.substring(time.length() - 7, time.length());
                        time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    }
                    timeTv.setText(time);
                    if (presenter.isMixParlay() && presenter.getType().equals("Early")) {
                        dateTv.setText(item.getMatchDate().substring(0, 5));
                    }
                    if (!item.getLive().equals("")) {
                        if (item.getLive().contains("LIVE")) {
                            dateTv.setText("LIVE");
                            liveTv.setVisibility(View.GONE);
                        } else {
                            String channel = item.getLive();
                            channel = Html.fromHtml(channel).toString();
                            String[] channels = channel.split("\n");
                            if (channels.length == 1) {
                                liveTv.setVisibility(View.GONE);
                                if (channel.trim().length() > 6)
                                    dateTv.setTextSize(8);
                                dateTv.setText(channel.trim());
                            } else if (channels.length == 2) {
                                liveTv.setTextSize(7);
                                if (channels[1].trim().length() >= 6)
                                    dateTv.setTextSize(8);
                                else {
                                    dateTv.setTextSize(9);
                                }
                                liveTv.setVisibility(View.VISIBLE);
                                liveTv.setText(channels[0].trim());
                                dateTv.setText(channels[1].trim());
                            }
                        }
                    }

                }

                if (position > 0) {
                    oldHomeName = getItem(position - 1).getHome();
                    oldAwayName = getItem(position - 1).getAway();
                    oldHomeGive = getItem(position - 1).getHandicapBeans().get(0).getIsHomeGive();
                    oldModuleTitle = getItem(position - 1).getLeagueBean().getModuleTitle();
                }


                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) && !presenter.isMixParlay() && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    awayTv.setText("");
                    homeTv.setText("");
                    tvRightMark.setVisibility(View.INVISIBLE);
                    llLeft1.setVisibility(View.INVISIBLE);
                    llLeft2.setVisibility(View.INVISIBLE);

                } else {

                    if (item.getHandicapBeans().get(0).getIsHomeGive().equals("1")) {
                        homeTv.setTextColor(getResources().getColor(R.color.google_yellow));
                        awayTv.setTextColor(getResources().getColor(R.color.black_grey));
                    } else {
                        homeTv.setTextColor(getResources().getColor(R.color.black_grey));
                        awayTv.setTextColor(getResources().getColor(R.color.google_yellow));
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
                    tvRightMark.setVisibility(View.VISIBLE);
                    tvCollection.setVisibility(View.VISIBLE);
                    llLeft1.setVisibility(View.VISIBLE);
                    llLeft2.setVisibility(View.VISIBLE);

                }
                tvRightMark.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putSerializable(AppConstant.KEY_DATA, item);
                        b.putString(AppConstant.KEY_STRING, presenter.getType());
                        b.putBoolean(AppConstant.KEY_BOOLEAN, presenter.isMixParlay());
                        skipAct(VsActivity.class, b);
                    }

                });
                if (presenter.isItemCollection(item))
                    tvCollection.setBackgroundResource(R.mipmap.collection_star_yellow_soild);
                else
                    tvCollection.setBackgroundResource(R.mipmap.collection_star_yellow_not_soild);

                if (item.getRCHome() == null || item.getRCHome().equals("0") || item.getRCHome().equals("")) {
                    homeRedCardTv.setVisibility(View.GONE);
                } else {
                    homeRedCardTv.setVisibility(View.VISIBLE);
                    switch (item.getRCHome()) {
                        case "1":
                            homeRedCardTv.setBackgroundResource(R.mipmap.red_card1);
                            break;
                        case "2":
                            homeRedCardTv.setBackgroundResource(R.mipmap.red_card2);
                            break;
                        default:
                            homeRedCardTv.setBackgroundResource(R.mipmap.red_card3);
                            break;
                    }
                }
                if (item.getRCAway() == null || item.getRCAway().equals("0") || item.getRCAway().equals("")) {
                    awayRedCardTv.setVisibility(View.GONE);
                } else {
                    awayRedCardTv.setVisibility(View.VISIBLE);
                    switch (item.getRCAway()) {
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
                ViewPager vp = helper.getView(R.id.module_center_vp);
                handleViewPager(vp, item, position);
                vps.add(vpHeader);
                if (!vps.contains(vp)) {
                    vps.add(vp);
                }
                if (item.getType() == MatchBean.Type.ITME) {
                    matchTitleTv.setVisibility(View.GONE);
                    headV.setVisibility(View.GONE);

                } else {
                    matchTitleTv.setVisibility(View.VISIBLE);
                    headV.setVisibility(View.VISIBLE);
                    matchTitleTv.setText(item.getLeagueBean().getModuleTitle());
                    if (position == 0) {
                        headV.setVisibility(View.GONE);
                    }
                }

            }
        };
        rvContent.setAdapter(baseRecyclerAdapter);

        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.onPrevious(swipeToLoadLayout);
            }
        });
        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.onNext(swipeToLoadLayout);
            }
        });

        rvContent.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)) {
                        swipeToLoadLayout.setLoadingMore(true);
                    }
                }
            }
        });
    }

    protected void handleViewPager(final ViewPager centerVp, MatchBean datas, int position) {
        centerVp.setOnTouchListener(new View.OnTouchListener() {
            private float mDX, mDY, mLX, mLY;

            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                switch (ev.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        mDX = mDY = 0f;
                        mLX = ev.getX();
                        mLY = ev.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        final float curX = ev.getX();
                        final float curY = ev.getY();
                        mDX += Math.abs(curX - mLX);
                        mDY += Math.abs(curY - mLY);
                        mLX = curX;
                        mLY = curY;

                        if (mDX > mDY) {
                            if (centerVp.getParent() != null) {
                                centerVp.getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            return false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        centerVp.getParent().requestDisallowInterceptTouchEvent(false);

                        break;
                }
                return false;
            }

        });

        MyPagerAdapter<HandicapBean> contentPageAdapter = new MyPagerAdapter<HandicapBean>(mContext) {

            @Override
            protected void convert(ViewHolder helper, final HandicapBean handicapBean, final int position) {
                final MatchBean bean = getExtraData();
                if (handicapBean.getHasHdp().equals("0")) {
                    helper.setText(R.id.viewpager_match_visit_hdp_tv, "");
                    helper.setText(R.id.viewpager_match_home_hdp_tv, "");
                    helper.setText(R.id.viewpager_match_home_hdpodds_tv, "");
                    helper.setText(R.id.viewpager_match_visit_hdpodds_tv, "");
                } else {
                    String hdpS = handicapBean.getHdp();
                    hdpS = changeValueF(hdpS);
                    if (handicapBean.getIsHomeGive().equals("1")) {
                        helper.setText(R.id.viewpager_match_visit_hdp_tv, "");
                        helper.setText(R.id.viewpager_match_home_hdp_tv, hdpS);

                    } else {
                        helper.setText(R.id.viewpager_match_home_hdp_tv, "");
                        helper.setText(R.id.viewpager_match_visit_hdp_tv, hdpS);
                    }
                    boolean isAnmiation = false;
                    if (handicapBean.getIsHdpNew().equals("1"))
                        isAnmiation = true;
                    setValue(helper, R.id.viewpager_match_home_hdpodds_tv, handicapBean.getHomeHdpOdds(), isAnmiation);
                    TextView home_hdpodds_tv = helper.retrieveView(R.id.viewpager_match_home_hdpodds_tv);
                    clickBet(home_hdpodds_tv, handicapBean, bean, position, handicapBean.getHomeHdpOdds(), "home", handicapBean.getHdp());
                    if (handicapBean.getIsHdpNew().equals("1"))
                        isAnmiation = true;
                    setValue(helper, R.id.viewpager_match_visit_hdpodds_tv, handicapBean.getAwayHdpOdds(), isAnmiation);

                    TextView awayHdpodds_tv = helper.retrieveView(R.id.viewpager_match_visit_hdpodds_tv);
                    clickBet(awayHdpodds_tv, handicapBean, bean, position, handicapBean.getAwayHdpOdds(), "away", handicapBean.getHdp());
                }

                baseRecyclerAdapter.getItem(getParentPosition()).getHandicapBeans().get(position).setIsHdpNew("0");

                if (handicapBean.getHasOu().equals("0")) {
                    helper.setText(R.id.viewpager_match_ou_tv, "");
                    helper.setText(R.id.viewpager_match_ou2_tv, "");
                    helper.setText(R.id.viewpager_match_underodds_tv, "");
                    helper.setText(R.id.viewpager_match_overodds_tv, "");
                } else {
                    String ou = handicapBean.getOU();
                    ou = changeValueF(ou);
                    helper.setText(R.id.viewpager_match_ou_tv, ou);
                    helper.setText(R.id.viewpager_match_ou2_tv, "");
                    boolean isAnmiation = false;
                    if (handicapBean.getIsOUNew().equals("1"))
                        isAnmiation = true;
                    setValue(helper, R.id.viewpager_match_underodds_tv, handicapBean.getUnderOdds(), isAnmiation);
                    TextView underodds_tv = helper.retrieveView(R.id.viewpager_match_underodds_tv);
                    clickBet(underodds_tv, handicapBean, bean, position, handicapBean.getUnderOdds(), "under", handicapBean.getOU());

                    if (handicapBean.getIsOUNew().equals("0"))
                        isAnmiation = true;
                    setValue(helper, R.id.viewpager_match_overodds_tv, handicapBean.getOverOdds(), isAnmiation);
                    TextView overoddsTv = helper.retrieveView(R.id.viewpager_match_overodds_tv);
                    clickBet(overoddsTv, handicapBean, bean, position, handicapBean.getOverOdds(), "over", handicapBean.getOU());

                }

                baseRecyclerAdapter.getItem(getParentPosition()).getHandicapBeans().get(position).setIsOUNew("1");
                notifyClearanceBet(baseRecyclerAdapter.getItem(getParentPosition()), position, helper);
            }


            private String changeValueF(String f) {
                if (f.equals("") || f.startsWith("-"))
                    return "";
                if (!presenter.isMixParlay()) {
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

            void clickBet(final TextView tv, final HandicapBean handicapBean, final MatchBean bean, final int position, final String overOdds, final String over, final String ou) {
                tv.setOnTouchListener(new View.OnTouchListener() {
                    private float mDX, mDY, mLX, mLY;
                    boolean isClick = true;

                    @Override
                    public boolean onTouch(View v, MotionEvent ev) {
                        switch (ev.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                isClick = true;
                                mDX = mDY = 0f;
                                mLX = ev.getX();
                                mLY = ev.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                final float curX = ev.getX();
                                final float curY = ev.getY();
                                mDX += Math.abs(curX - mLX);
                                mDY += Math.abs(curY - mLY);
                                mLX = curX;
                                mLY = curY;
                                int dis = dip2px(mContext, 2);
                                if (DeviceUtils.getScreenPix(mContext).widthPixels >= 1920) {
                                    dis = dis + 5;
                                }
                                if (mDX > dis || mDY > dis) {
                                    isClick = false;
                                    tv.setEnabled(false);
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                tv.setEnabled(true);
                                if (isClick) {
                                    tv.getParent().requestDisallowInterceptTouchEvent(true);
                                    if ("0".equals(handicapBean.getIsInetBet())) {
                                        Toast.makeText(mContext, R.string.not_allowed_to_bet, Toast.LENGTH_LONG).show();
                                    } else {
                                        if (presenter.isMixParlay()) {
                                            addMixParlayBet(v, baseRecyclerAdapter.getItem(getParentPosition()), position, over, ou, overOdds, handicapBean.getIsHomeGive());
                                        } else
                                            showBetPop(v, bean, position, overOdds, over, ou);
                                    }
                                }
                                tv.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }

                        return isClick;
                    }
                });

            }

            private void addMixParlayBet(View v, final MatchBean item, final int position, String model, final String hdp, final String odds, final String isHomeGive) {

                if (item != null) {
                    final String recordModel = model;
                    BettingInfoBean modlemap = new BettingInfoBean("s", recordModel, "", hdp, odds, item.getHome(), item.getAway(), item.getLeagueBean().getModuleTitle(),
                            item.getHandicapBeans().get(0).getSocOddsId(), item.getHandicapBeans().get(1).getSocOddsId(), position, model.equals("Running"), isHomeGive.equals("1"));
                    Map<Integer, BettingInfoBean> positionMap = new HashMap<>();
                    positionMap.put(position, modlemap);
                    Map<String, Map<Integer, BettingInfoBean>> keyMap = new HashMap<>();
                    keyMap.put(item.getKey(), positionMap);

                    model = model + "_par";

                    final String SocOddsId = item.getHandicapBeans().get(0).getSocOddsId();
                    String SocOddsId_FH = "";
                    if (position == 1)
                        SocOddsId_FH = item.getHandicapBeans().get(1).getSocOddsId();
                    BettingInfoBean m1 = new BettingInfoBean("", model, "", hdp, odds, item.getHome(), item.getAway(), item.getLeagueBean().getModuleTitle(),
                            SocOddsId, SocOddsId_FH, position, model.equals("Running"), isHomeGive.equals("1"));
                    presenter.addMixParlayBet(m1, keyMap, item);

                    v.setBackgroundResource(R.drawable.sport_mix_parlay_bet_green_bg);
                    ((TextView) v).setTextColor(getResources().getColor(R.color.white));

                } else {
                    getApp().setBetDetail(null);
                    countBet();
                    baseRecyclerAdapter.notifyDataSetChanged();
                }

            }

            private void setValue(final ViewHolder helper, final int id, String f, boolean isAnimation) {
                if (f.equals("")) {
                    helper.setText(id, "");
                    return;
                }
                helper.setText(id, AfbUtils.changeValueS(f));
                helper.setTextColor(id, mContext.getResources().getColor(R.color.black_grey));


              /*  if (isAnimation && updateType != 1) {

                    helper.setAnimation(id, getAnimation(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            helper.setBackgroundColorRes(id, R.color.dig_game_bg);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            helper.setBackgroundColorRes(id, R.color.white);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            helper.setBackgroundColorRes(id, R.color.dig_game_bg);
                        }
                    }));

                }*/
            }

            private void showBetPop(View v, MatchBean bean, int position, String odds, String type, String ou) {

                betPop = new BetBasePop(mContext, v, 700, LinearLayout.LayoutParams.WRAP_CONTENT);
                //String b,String sc, String oId, String odds, boolean isRunning, String oId_fh
                BettingInfoBean info;
                if (position == 0) {
                    info = new BettingInfoBean("s", type, "", ou, odds,
                            bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", "", 0, type.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("1"));


                } else {
                    info = new BettingInfoBean("s", type, "", ou, odds,
                            bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", bean.getHandicapBeans().get(1).getSocOddsId(), 1, type.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("1"));

                    betPop.setBetSelectionType(mContext.getString(R.string.half_time));
                }
                info = betPop.initData(info);
                presenter.getBetPopupData(info);
                createPopupWindow(betPop);
            }


            @Override
            protected int getPageLayoutRes() {
                return R.layout.sport_item_table_module_viewpager;
            }
        };
        contentPageAdapter.setExtraData(datas);
        contentPageAdapter.setParentPosition(position);
        contentPageAdapter.setDatas(datas.getHandicapBeans());
        centerVp.setAdapter(contentPageAdapter);
//                }
        centerVp.setFollowViewPagers(vps);
        centerVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if (vpPosition != position) {
                    vpPosition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        centerVp.setCurrentItem(vpPosition);
    }

    private void notifyClearanceBet(MatchBean item, int position, ViewHolder helper) {
        if (getApp().getBetDetail() == null || item == null)
            return;
        Map<String, Map<Integer, BettingInfoBean>> keyMap = getApp().getBetDetail().get(item.getHome() + "+" + item.getAway());
        if (keyMap == null)
            return;
        Map<Integer, BettingInfoBean> positionMap = keyMap.get(item.getKey());
        if (positionMap == null)
            return;
        BettingInfoBean modelInfo = positionMap.get(position);
        if (modelInfo == null)
            return;
        String model = modelInfo.getB();

        if (model.equals("away")) {
            helper.setBackgroundRes(R.id.viewpager_match_visit_hdpodds_tv, R.drawable.sport_mix_parlay_bet_green_bg);
            helper.setTextColorRes(R.id.viewpager_match_visit_hdpodds_tv, R.color.white);

        } else if (model.equals("home")) {
            helper.setBackgroundRes(R.id.viewpager_match_home_hdpodds_tv, R.drawable.sport_mix_parlay_bet_green_bg);
            helper.setTextColorRes(R.id.viewpager_match_home_hdpodds_tv, R.color.white);
        } else if (model.equals("over")) {
            helper.setBackgroundRes(R.id.viewpager_match_overodds_tv, R.drawable.sport_mix_parlay_bet_green_bg);
            helper.setTextColorRes(R.id.viewpager_match_overodds_tv, R.color.white);
        } else if (model.equals("under")) {
            helper.setBackgroundRes(R.id.viewpager_match_underodds_tv, R.drawable.sport_mix_parlay_bet_green_bg);
            helper.setTextColorRes(R.id.viewpager_match_underodds_tv, R.color.white);
        }
    }

    public void countBet() {
        llMixParlayOrder.setVisibility(View.VISIBLE);
        Map<String, Map<String, Map<Integer, BettingInfoBean>>> result = getApp().getBetDetail();
        if (result != null) {
            if (result.size() == 0) {
                llMixParlayOrder.setVisibility(View.GONE);
            } else {
                tvMixParlayOrder.setText(result.size() + "");
                llMixParlayOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        skipAct(MixOrderListActivity.class);
                    }
                });
            }
        } else
            llMixParlayOrder.setVisibility(View.GONE);
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String modelType) {
        baseRecyclerAdapter.addAllAndClear(pageData);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(modelType);
        if (presenter.isMixParlay()) {
            llMixParlayOrder.setVisibility(View.VISIBLE);
        } else {
            llMixParlayOrder.setVisibility(View.GONE);
        }
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean result, Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (result != null)
            getApp().setBetParList(result);
        saveBetMap(keyMap, item);
        countBet();
    }

    @Override
    public void onAddMixFailed(String message) {
        countBet();
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetBetSucceed(BettingPromptBean allData) {
        betPop.setBetData(allData, presenter);
        betPop.showPopupCenterWindow();
    }

    @Override
    public void onBetSucceed(String allData) {
        ToastUtils.showShort(allData);
        betPop.closePopupWindow();
    }


    private void saveBetMap(Map<String, Map<Integer, BettingInfoBean>> keyMap, MatchBean item) {
        if (item == null) {
            getApp().setBetDetail(null);
        } else {
            getApp().getBetDetail().put(item.getHome() + "+" + item.getAway(), keyMap);
            baseRecyclerAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean mixParlayCLick(TextView tvMix) {
        presenter.mixParlay();
        if (presenter.isMixParlay())
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_green, 0, 0);
        else
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_oval_u_black, 0, 0);
        return presenter.isMixParlay();
    }

    @Override
    public boolean collectionClick(TextView tvCollection) {
        presenter.collection();
        return presenter.isCollection();
    }

    @Override
    public void onGetData(List<MatchBean> data) {
        ToastUtils.showShort(data.toString());
    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
