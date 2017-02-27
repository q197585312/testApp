package com.nanyang.app.main.home.sport;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.additional.VsActivity;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;

import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 2017/2/24.
 */

public class ApBallAdapter extends BaseRecyclerAdapter<MatchBean> {
    public ApBallAdapter(Context context, List<MatchBean> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    @Override
    public void convert(MyRecyclerViewHolder holder, int position, MatchBean item) {


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
    }
}
