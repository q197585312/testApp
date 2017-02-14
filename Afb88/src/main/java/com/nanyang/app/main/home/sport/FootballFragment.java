package com.nanyang.app.main.home.sport;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/2/12 0012.
 */

public class FootballFragment extends BaseSportFragment<FootballPresenter> implements SportContract.View<List<MatchBean>> {
    @Bind(R.id.tvRefresh)
    TextView tvRefresh;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.swipeToLoadLayout)
    SwipeToLoadLayout swipeToLoadLayout;
    private String modleType;

    @Override
    public void initData() {
        super.initData();
        createPresenter(new FootballPresenter(this));
        presenter.refresh();
        initAdapter();
    }

    private void initAdapter() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(mLayoutManager);


        BaseRecyclerAdapter<MatchBean> baseRecyclerAdapter = new BaseRecyclerAdapter<MatchBean>(mContext,  new ArrayList<>(), R.layout.sport_match_item) {
            @Override
            public void convert(MyRecyclerViewHolder helper, int position, MatchBean item) {
                TextView dateTv = helper.getView(R.id.module_match_date_tv);
                TextView timeTv = helper.getView(R.id.module_match_time_tv);
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
                    TextView tvLive=helper.getView(R.id.module_match_live_iv);
                            tvLive.setText("LIVE");
//                    helper.setBackgroundRes(R.id.module_match_live_iv, R.mipmap.live_green_left_arrow_white);
                    helper.setText(R.id.module_match_live_iv, "");
                    dateTv.setTextColor(mContext.getResources().getColor(R.color.grey_light));
                    timeTv.setTextColor(mContext.getResources().getColor(R.color.grey_light));
//                    helper.setText(R.id.module_match_date_tv, item.getMatchDate());

                   TextView tvDate= helper.getView(R.id.module_match_date_tv);
                    tvDate.setTextSize(10);
                    String date = item.getMatchDate().substring(0, 5);
                    helper.setText(R.id.module_match_date_tv, date);

                    String time = item.getMatchDate();
                    if (time.length() >6) {
                        time = time.substring(time.length() - 7, time.length());
//                        time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    }
                    helper.setText(R.id.module_match_time_tv, time);
                    if (betType == TableAdapterHelper.ClearanceBet && modleType.equals("Early")) {
//                        helper.setText(R.id.module_match_date_tv, item.getWorkingDate().substring(0,5));
                        helper.setText(R.id.module_match_date_tv, item.getMatchDate().substring(0, 5));
                    }
                    if (!item.getLive().equals("")) {
                        helper.setBackgroundRes(R.id.module_match_live_iv, 0);
                        helper.setTextColorRes(R.id.module_match_live_iv, R.color.red_title);
                        helper.setTextColorRes(R.id.module_match_date_tv, R.color.red_title);
                        helper.setText(R.id.module_match_live_iv, "");
                        helper.setText(R.id.module_match_date_tv, "");
                        if (!item.getLive().equals("")) {
                            String channel = item.getLive();
                            String[] channels = channel.split("%");
                            if (channels != null && channels.length == 1) {
                                helper.setVisibility(R.id.module_match_live_iv, View.GONE);
                                if(channel.trim().length()>6)
                                    helper.setTextSize(R.id.module_match_date_tv,8);
                                helper.setText(R.id.module_match_date_tv, channel.trim());
                            } else if (channels != null && channels.length == 2) {
                                helper.setTextSize(R.id.module_match_live_iv, 7);
                                if(channels[1].trim().length()>=6)
                                    helper.setTextSize(R.id.module_match_date_tv, 8);
                                else{
                                    helper.setTextSize(R.id.module_match_date_tv, 9);
                                }
                                helper.setVisibility(R.id.module_match_live_iv, View.VISIBLE);
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

                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) && betType != TableAdapterHelper.ClearanceBet && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    helper.setText(R.id.module_match_home_team_tv, "");
                    helper.setText(R.id.module_match_visiting_team_tv, "");
                    if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.INVISIBLE);
                    }
                    helper.setVisibility(R.id.module_right_mark_tv, View.INVISIBLE);
                    helper.setVisibility(R.id.module_match_left1_ll, View.INVISIBLE);
                    helper.setVisibility(R.id.module_match_left2_ll, View.INVISIBLE);
                } else {
                    if (item.getHandicapBeans().get(0).getIsHomeGive().equals("true")) {
                        helper.setTextColorRes(R.id.module_match_home_team_tv, R.color.red_title);
                        helper.setTextColorRes(R.id.module_match_visiting_team_tv, R.color.black_grey);
                    } else {
                        helper.setTextColorRes(R.id.module_match_home_team_tv, R.color.black_grey);
                        helper.setTextColorRes(R.id.module_match_visiting_team_tv, R.color.red_title);
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
                    helper.setVisibility(R.id.module_right_mark_tv, View.VISIBLE);
                    if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.VISIBLE);
                    }
                    helper.setVisibility(R.id.module_match_left1_ll, View.VISIBLE);
                    helper.setVisibility(R.id.module_match_left2_ll, View.VISIBLE);
                }
                helper.setClickLisenter(R.id.module_right_mark_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, item);
                        b.putString(AppConfig.ACTION_KEY_INITENT_STRING, modleType);
                        b.putInt(AppConfig.ACTION_KEY_INITENT_INT, betType);
                        AppTool.activiyJump(mContext, VsActivity.class, b);
                    }
                });
                if (betType == TableAdapterHelper.ClearanceBet) {
                    helper.setVisibility(R.id.module_match_collection_tv, View.GONE);
                }
//                if (item.getIsInFavourite().equals("true")) {
//                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
//                } else {
//                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
//
//                }
                if (localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway())) {
                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
                } else {
                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
                }
                if (item.getRCHome() == null || item.getRCHome().equals("0") || item.getRCHome().equals("0")) {
                    helper.getView(R.id.module_match_home_rea_card_tv, false);
                } else {
                    helper.getView(R.id.module_match_home_rea_card_tv).
                    if (item.getRCHome().equals("1"))
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card1);
                    else if (item.getRCHome().equals("2"))
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card2);
                    else
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card3);
                }
                if (item.getRCAway() == null || item.getRCAway().equals("0") || item.getRCAway().equals("")) {
                    helper.getView(R.id.module_match_away_rea_card_tv, false);
                } else {
                    helper.getView(R.id.module_match_away_rea_card_tv).setVisibility(View.VISIBLE);
                    if (item.getRCAway().equals("1"))
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card1);
                    else if (item.getRCAway().equals("2"))
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card2);
                    else
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card3);
                }
                final TextView ct = helper.getView(R.id.module_match_collection_tv);
                helper.setClickLisenter(R.id.module_match_collection_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //收藏 取消收藏
                        /*添加我的最爱:
                        添加我的最爱:
                        http://mobilesport.dig88api.com/_View/Favourite.aspx?id=29278,139575,55712&IsAdd=True&ot=t
                        消除我的最爱:
                        http://mobilesport.dig88api.com/_View/Favourite.aspx?id=29278,139575,55712&IsAdd=False&ot=t
                        Id组成	联赛id,主队id,客队id                        Ot	t (today)	 e(Early)	r(Running)
                            */
                        clickLocalCollection(getItem(position));
//                        TableHttpHelper<String> helper1 = new TableHttpHelper<String>(mContext, ct, new ThreadEndT<String>(new TypeToken<String>() {
//                        }.getType()) {
//                            @Override
//                            public void endT(String result) {
//                                helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
//                                getItem(position).setIsInFavourite("true");
//                            }
//
//                            @Override
//                            public void endString(String result) {
//                                helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
//                                getItem(position).setIsInFavourite("false");
//
//                            }
//
//                            @Override
//                            public void endError(String error) {
//
//                            }
//                        });
//                        if (item.getIsInFavourite().equals("true")) {
//                            helper1.updateData(WebSiteUrl.SportUrl + "_View/Favourite.aspx?id=" + item.getLeagueBean().getModuleId() + "," + item.getHomeId() + "," + item.getAwayId() + "&IsAdd=False&ot=" +
//                                    modleType.substring(0, 1).toLowerCase(), "");
//                        } else {
//                            helper1.getData(WebSiteUrl.SportUrl + "_View/Favourite.aspx?id=" + item.getLeagueBean().getModuleId() + "," + item.getHomeId() + "," + item.getAwayId() + "&IsAdd=True&ot=" +
//                                    modleType.substring(0, 1).toLowerCase(), "");
//                        }
                    }

                });
                ViewPager vp = helper.getView(R.id.module_center_vp);
                handleViewPager(vp, item, position);
                vps.add(headerPager);
                if (!vps.contains(vp)) {
                    vps.add(vp);
                }
                if (item.getType() == MatchBean.Type.ITME) {
                    helper.getView(R.id.module_match_title_tv, false);
                    helper.getView(R.id.module_match_head_v, false);

                } else {
                    helper.getView(R.id.module_match_title_tv).setVisibility(View.VISIBLE);
                    helper.getView(R.id.module_match_head_v).setVisibility(View.VISIBLE);
                    helper.setText(android.R.id.text1, item.getLeagueBean().getModuleTitle());
                    if (position == 0) {
                        helper.getView(R.id.module_match_head_v, false);
                    }
                }

            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MatchBean>() {
            @Override
            public void onItemClick(View view, MatchBean item, int position) {
                if(item.getText().equals(getString(R.string.Cancel))){
                    closePopupWindow();
                }
                else{
                    Bundle b=new Bundle();
                    b.putString(AppConstant.KEY_STRING,item.getText());
                    skipAct(SportActivity.class,b);
                }
            }
        });
        rvContent.setAdapter(baseRecyclerAdapter);


    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onPageData(int page, List<MatchBean> pageData) {


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
