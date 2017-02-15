package com.nanyang.app.main.home.sport;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;


public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<List<MatchBean>> {

    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    private String modleType = "Today";
    BaseRecyclerAdapter<MatchBean> baseRecyclerAdapter;

    @Override
    public void initData() {
        super.initData();
        initAdapter();
    }
    private void initAdapter() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(mLayoutManager);
        baseRecyclerAdapter = new BaseRecyclerAdapter<MatchBean>(mContext, new ArrayList<MatchBean>(), R.layout.sport_match_item) {
            public String oldModuleTitle;
            public String oldHomeGive;
            public String oldAwayName;
            public String oldHomeName;

            @Override
            public void convert(MyRecyclerViewHolder helper, int position, MatchBean item) {
                TextView dateTv = helper.getView(R.id.module_match_date_tv);
                TextView timeTv = helper.getView(R.id.module_match_time_tv);
                TextView liveTv = helper.getView(R.id.module_match_live_iv);
                dateTv.setTextAppearance(mContext, R.style.text_normal);
                dateTv.setPadding(0, 0, 0, 0);

                if (modleType.equals("Running")) {
                    dateTv.setTextAppearance(mContext, R.style.text_bold);
                    dateTv.setPadding(0, 0, 10, 0);
                    helper.getView(R.id.module_match_live_iv).setVisibility(View.GONE);
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

                    liveTv.setText("LIVE");
//                    helper.getView(R.id.module_match_live_iv, R.mipmap.live_green_left_arrow_white)).set
                    helper.setText(R.id.module_match_live_iv, "");
                    dateTv.setTextColor(mContext.getResources().getColor(R.color.grey_light));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.grey_light));
//                    helper.setText(R.id.module_match_date_tv, item.getMatchDate());
                    dateTv.setTextSize(10);
                    String date = item.getMatchDate().substring(0, 5);
                    helper.setText(R.id.module_match_date_tv, date);

                    String time = item.getMatchDate();
                    if (time.length() > 6) {
                        time = time.substring(time.length() - 7, time.length());
//                        time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    }
                    helper.setText(R.id.module_match_time_tv, time);
                    if (/*betType == TableAdapterHelper.ClearanceBet &&*/ modleType.equals("Early")) {
//                        helper.setText(R.id.module_match_date_tv, item.getWorkingDate().substring(0,5));
                        helper.setText(R.id.module_match_date_tv, item.getMatchDate().substring(0, 5));
                    }
                    if (!item.getLive().equals("")) {
                        helper.getView(R.id.module_match_live_iv).setBackgroundResource(0);
                        liveTv = helper.getView(R.id.module_match_live_iv);
                        liveTv.setTextColor(getResources().getColor(R.color.red_title));
                        dateTv.setTextColor(getResources().getColor(R.color.red_title));
                        helper.setText(R.id.module_match_live_iv, "");
                        helper.setText(R.id.module_match_date_tv, "");
                        if (!item.getLive().equals("")) {
                            String channel = item.getLive();
                            String[] channels = channel.split("%");
                            if (channels != null && channels.length == 1) {
                                liveTv.setVisibility(View.GONE);
                                if (channel.trim().length() > 6)
                                    dateTv.setTextSize(8);
                                helper.setText(R.id.module_match_date_tv, channel.trim());
                            } else if (channels != null && channels.length == 2) {
                                liveTv.setTextSize(7);
                                if (channels[1].trim().length() >= 6)
                                    dateTv.setTextSize(8);
                                else {
                                    dateTv.setTextSize(9);
                                }
                                liveTv.setVisibility(View.VISIBLE);
                                helper.setText(R.id.module_match_live_iv, channels[0].trim());
                                helper.setText(R.id.module_match_date_tv, channels[1].trim());
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
                TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);
                View llLeft1 = helper.getView(R.id.module_match_left1_ll);
                View llLeft2 = helper.getView(R.id.module_match_left2_ll);
                TextView tvCollection = helper.getView(R.id.module_match_collection_tv);
                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) /*&& betType != TableAdapterHelper.ClearanceBet */ && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    helper.setText(R.id.module_match_home_team_tv, "");
                    helper.setText(R.id.module_match_visiting_team_tv, "");
                   /* if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.INVISIBLE);
                    }*/

                    tvRightMark.setVisibility(View.INVISIBLE);
                    llLeft1.setVisibility(View.INVISIBLE);
                    llLeft2.setVisibility(View.INVISIBLE);

                } else {
                    TextView tvHomeTeam = helper.getView(R.id.module_match_home_team_tv);
                    TextView tvVisiting = helper.getView(R.id.module_match_visiting_team_tv);
                    if (item.getHandicapBeans().get(0).getIsHomeGive().equals("true")) {
                        tvHomeTeam.setTextColor(getResources().getColor(R.color.red_title));
                        tvVisiting.setTextColor(getResources().getColor(R.color.black_grey));
                    } else {
                        tvHomeTeam.setTextColor(getResources().getColor(R.color.black_grey));
                        tvVisiting.setTextColor(getResources().getColor(R.color.red_title));
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
                    helper.setText(R.id.module_match_home_team_tv, home);
                    helper.setText(R.id.module_match_visiting_team_tv, away);
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
                        b.putString(AppConfig.ACTION_KEY_INITENT_STRING, modleType);
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
                if (localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway())) {
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

               /* ViewPager vp = helper.getView(R.id.module_center_vp);
                handleViewPager(vp, item, position);
                vps.add(headerPager);
                if (!vps.contains(vp)) {
                    vps.add(vp);
                }*/
            /*    if (item.getType() == MatchBean.Type.ITME) {

                    helper.getView(R.id.module_match_title_tv, false);
                    helper.getView(R.id.module_match_head_v, false);

                } else {
                    helper.getView(R.id.module_match_title_tv).setVisibility(View.VISIBLE);
                    helper.getView(R.id.module_match_head_v).setVisibility(View.VISIBLE);
                    helper.setText(android.R.id.text1, item.getLeagueBean().getModuleTitle());
                    if (position == 0) {
                        helper.getView(R.id.module_match_head_v, false);
                    }
                }*/

            }
        };
        rvContent.setAdapter(baseRecyclerAdapter);


    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData) {
        baseRecyclerAdapter.addAllAndClear(pageData);
    }


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_football;
    }


    @Override
    public void onGetData(List<MatchBean> data) {
        ToastUtils.showShort(data.toString());
    }

    @Override
    public String getTitle() {
        return getString(R.string.Football);
    }


}
