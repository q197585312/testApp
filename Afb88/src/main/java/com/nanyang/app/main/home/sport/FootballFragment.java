package com.nanyang.app.main.home.sport;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;

import static cn.finalteam.toolsfinal.DeviceUtils.dip2px;


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

    private String modelType = "Today";
    BaseRecyclerAdapter<MatchBean> baseRecyclerAdapter;
    private ArrayList<ViewPager> vps;
    private int vpPosition = 0;

    @Override
    public void initData() {
        super.initData();
        initAdapter();
        createPresenter(new FootballPresenter(this));
        presenter.refresh(((SportActivity) getActivity()).getType());
    }

    private void initAdapter() {
        vps = new ArrayList<>();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new BaseRecyclerAdapter<MatchBean>(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item) {

            public String oldModuleTitle;
            public String oldHomeGive;
            public String oldAwayName;
            public String oldHomeName;

            @Override
            public void convert(MyRecyclerViewHolder helper, int position, MatchBean item) {
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
                TextView tvCollection = helper.getView(R.id.module_match_collection_tv);

                liveTv.setTextColor(getResources().getColor(R.color.google_yellow));
                dateTv.setTextColor(getResources().getColor(R.color.google_yellow));
                timeTv.setTextColor(getResources().getColor(R.color.green_light));
                dateTv.setTextAppearance(mContext, R.style.text_normal);
                dateTv.setPadding(0, 0, 0, 0);

                if (modelType.equals("Running")) {
                    dateTv.setTextAppearance(mContext, R.style.text_bold);
                    dateTv.setPadding(0, 0, 10, 0);
                    liveTv.setVisibility(View.GONE);
                    if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
                        String shome = item.getRunHomeScore();
                        String sAway = item.getRunAwayScore();
                        dateTv.setText(shome + "-" + sAway);

                    } else {
                        dateTv.setText("");
                    }
                    if (item.getLive().contains("HT")) {
                        timeTv.setText("HT");
                    } else {
                        int min = 0;
                        int start = 0;
                        try {

                            if (item.getStatus().equals("0")) {
                                timeTv.setText(getString(R.string.not_started));
                            } else if (item.getStatus().equals("2")) {
                                min = Integer.valueOf(item.getCurMinute());
                                start = 45;
                                min = min + start;
                                if (min < 130 && min > 0) {
                                    timeTv.setText(min + mContext.getString(R.string.min));
                                } else {
                                    timeTv.setText("");
                                }
                            } else /*if (item.getStatus().equals("1")) */ {
                                min = Integer.valueOf(item.getCurMinute());
                                if (min < 130 && min > 0) {
                                    timeTv.setText(min + mContext.getString(R.string.min));
                                } else {
                                    timeTv.setText("");
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            timeTv.setText("");
                        }
                    }
                    dateTv.setTextColor(mContext.getResources().getColor(R.color.red_title));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.red_title));
                } else {

                    dateTv.setTextSize(10);
                    String date = item.getMatchDate().substring(0, 5);
                    dateTv.setText(date);
                    String time = item.getMatchDate();
                    if (time.length() > 6) {
                        time = time.substring(time.length() - 7, time.length());
                        time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    }
                    timeTv.setText(time);
                    if (/*betType == TableAdapterHelper.ClearanceBet &&*/ modelType.equals("Early")) {
//                        helper.setText(R.id.module_match_date_tv, item.getWorkingDate().substring(0,5));
//                        helper.setText(R.id.module_match_date_tv, item.getMatchDate().substring(0, 5));
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


                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) /*&& betType != TableAdapterHelper.ClearanceBet */ && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    awayTv.setText("");
                    homeTv.setText("");
                   /* if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.INVISIBLE);
                    }*/

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
                    }
                    /* Bundle b = new Bundle();
                        b.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, item);
                        b.putString(AppConfig.ACTION_KEY_INITENT_STRING, modelType);
                        b.putInt(AppConfig.ACTION_KEY_INITENT_INT, betType);
                        AppTool.activiyJump(mContext, VsActivity.class, b);*/
                });

              /*  if (betType == TableAdapterHelper.ClearanceBet) {
                    helper.setVisibility(R.id.module_match_collection_tv, View.GONE);
                }*/
//                if (item.getIsInFavourite().equals("true")) {
//                    helper.getView(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow)).set
//                } else {
//                    helper.getView(R.id.module_match_collection_tv, R.mipmap.star_outline_grey)).set
//
//                }
                /*
                if (localCollectionMap.get(modelType + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(modelType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(modelType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway())) {
                    helper.getView(R.id.module_match_collection_tv).set R.mipmap.star_outline_grey);
                } else {
                    helper.getView(R.id.module_match_collection_tv).set R.mipmap.star_solid_yellow);
                }
                if (item.getRCHome() == null || item.getRCHome().equals("0") || item.getRCHome().equals("0")) {
                    helper.getView(R.id.module_match_home_rea_card_tv, false);
                } else {
                    helper.getView(R.id.module_match_home_rea_card_tv).
                    if (item.getRCHome().equals("1"))
                        helper.getView(R.id.module_match_home_rea_card_tv).set R.drawable.rectangle_red_card1);
                    else if (item.getRCHome().equals("2"))
                        helper.getView(R.id.module_match_home_rea_card_tv).set R.drawable.rectangle_red_card2);
                    else
                        helper.getView(R.id.module_match_home_rea_card_tv).set R.drawable.rectangle_red_card3);
                }
                if (item.getRCAway() == null || item.getRCAway().equals("0") || item.getRCAway().equals("")) {
                    helper.getView(R.id.module_match_away_rea_card_tv, false);
                } else {
                    helper.getView(R.id.module_match_away_rea_card_tv).setVisibility(View.VISIBLE);
                    if (item.getRCAway().equals("1"))
                        helper.getView(R.id.module_match_away_rea_card_tv).set R.drawable.rectangle_red_card1);
                    else if (item.getRCAway().equals("2"))
                        helper.getView(R.id.module_match_away_rea_card_tv).set R.drawable.rectangle_red_card2);
                    else
                        helper.getView(R.id.module_match_away_rea_card_tv).set R.drawable.rectangle_red_card3);
                }
                final TextView ct = helper.getView(R.id.module_match_collection_tv);*/

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
                if (newState == RecyclerView.SCROLL_STATE_IDLE ){
                    if (!ViewCompat.canScrollVertically(recyclerView, 1)){
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
                       /*     contentSv.setCanPullDown(false);
                            contentSv.setCanPullUp(false);
                            contentSv.setEnabled(false);*/
                            if (centerVp.getParent() != null) {
                                centerVp.getParent().requestDisallowInterceptTouchEvent(true);
                            }
                            return false;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        centerVp.getParent().requestDisallowInterceptTouchEvent(false);
                      /*  contentSv.setCanPullDown(true);
                        contentSv.setCanPullUp(true);
                        contentSv.setEnabled(true);*/
                        break;
                }
                return false;
            }

        });
//                if(centerVp.getAdapter()!=null){
//                    MyPagerAdapter<HandicapBean> recycledAdapter= (MyPagerAdapter<HandicapBean>) centerVp.getAdapter();
//                    recycledAdapter.setExtraData(datas);
//                    recycledAdapter.setParentPosition(position);
//                    recycledAdapter.setDatas(datas.getHandicapBeans());
//                    recycledAdapter.notifyDataSetChanged();
//                }else{
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
//                notifyClearanceBet(baseRecyclerAdapter.getItem(getParentPosition()), position, helper);
            }

            private String changeValueS(String v) {
                if (v == null || v.equals(""))
                    return "";
                DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                String p = "";
                try {
                    if (Float.valueOf(v) == 0) {
                        return "";
                    }
                    if (!modelType.equals("Mix"))
                        p = decimalFormat.format(Float.valueOf(v) / 10);//format 返回的是字符串
                    else
                        p = decimalFormat.format(Float.valueOf(v));//format 返回的是字符串收藏不除以10
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return p;
            }

            private String changeValueF(String f) {
                if (f.equals("") || f.startsWith("-"))
                    return "";
                if (!modelType.equals("Mix")) {
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

            public String subZeroAndDot(float s) {
                String ss = s + "";
                if (ss.indexOf(".") > 0) {
                    ss = ss.replaceAll("0+?$", "");//去掉多余的0
                    ss = ss.replaceAll("[.]$", "");//如最后一位是.则去掉
                }
                return ss;
            }

            protected void clickBet(final TextView tv, final HandicapBean handicapBean, final MatchBean bean, final int position, final String overOdds, final String over, final String ou) {
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
//                                        if (betType == 0)
                                        showBetPop(v, bean, position, overOdds, over, ou);
//                                        else if (betType == ClearanceBet)
//                                            handleClearanceBet(v, baseRecyclerAdapter.getItem(getParentPosition()), position, over, ou, overOdds, handicapBean.getIsHomeGive());

                                    }
                                }
                                tv.getParent().requestDisallowInterceptTouchEvent(false);
                                break;
                        }

                        return isClick;
                    }
                });

            }

            private void setValue(final ViewHolder helper, final int id, String f, boolean isAnimation) {
                if (f.equals("")) {
                    helper.setText(id, "");
                    return;
                }
                helper.setText(id, changeValueS(f));
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

               /* betPop = new BetBasePop(mContext, v, 700, LinearLayout.LayoutParams.WRAP_CONTENT);
                //String b,String sc, String oId, String odds, boolean isRunning, String oId_fh
                if (position == 0) {
                    BettingInfoBean info = new BettingInfoBean("s", type, "", ou, odds,
                            bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", "", 0, modleType.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("true"));
                    betPop.getData(info);
                } else {
                    BettingInfoBean info = new BettingInfoBean("s", type, "", ou, odds,
                            bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", bean.getHandicapBeans().get(1).getSocOddsId(), 1, modleType.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("true"));
                    betPop.getData(info);
                    betPop.setBetSelectionType(mContext.getString(R.string.half_time));
                }
                betPop.showPopupCenterWindow();*/
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

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData, String modelType) {
        this.modelType = modelType;
        baseRecyclerAdapter.addAllAndClear(pageData);
        String size = pageData.size() + "";
        tvTotalMatch.setText(size);
        ((BaseToolbarActivity) getActivity()).getTvToolbarTitle().setText(modelType);
    }

    @Override
    public String getType() {
        return modelType;
    }


    @Override
    public void onGetData(List<MatchBean> data) {
        ToastUtils.showShort(data.toString());
    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }

    /*  PullToRefreshLayout.OnRefreshListener onRefreshListener = new PullToRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
  //            presenter.onPrevious(ballgameTableRefreshView);

          }

          @Override
          public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
  //            presenter.onNext(ballgameTableRefreshView);
          }
      };
  */
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
