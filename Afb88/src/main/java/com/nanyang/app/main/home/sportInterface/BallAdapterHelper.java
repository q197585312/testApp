package com.nanyang.app.main.home.sportInterface;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/12 0012.
 */

public class BallAdapterHelper<I extends BallInfo> extends SportAdapterHelper<I> {
    final int google_yellow = 0XFFFBBC05;

    final int black_grey = 0XFF333333;
    protected Context context;
    protected List<ScrollLayout> slFollowers = new ArrayList<>();

    private int slIndex=0;


    public BallAdapterHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, int position, I item) {
        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
        View headV = helper.getView(R.id.module_match_head_v);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);
        TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);
        TextView tvRightMark = helper.getView(R.id.module_right_mark_tv);
        final TextView tvCollection = helper.getView(R.id.module_match_collection_tv);
        liveTv.setTextColor(google_yellow);
        dateTv.setTextColor(google_yellow);
        timeTv.setTextColor(context.getResources().getColor(R.color.green_light));
        dateTv.setTextSize(10);
        dateTv.setPadding(0, 0, 0, 0);

        String date = item.getMatchDate().substring(0, 5);
        dateTv.setText(date);
        String time = item.getMatchDate();
        if (time.length() > 6) {
            time = time.substring(time.length() - 7, time.length());
            time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
        }
        timeTv.setText(time);

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
        tvCollection.setVisibility(View.GONE);
        String isHomeGive = item.getIsHomeGive();
        if (isHomeGive.equals("1")) {
            homeTv.setTextColor(google_yellow);
            awayTv.setTextColor(black_grey);
        } else {
            homeTv.setTextColor(black_grey);
            awayTv.setTextColor(google_yellow);
        }

        tvRightMark.setVisibility(View.GONE);
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
        String hasHdp = item.getHasHdp();
        String hdp = item.getHdp();
        String hasOU = item.getHasOU();
        String ou = item.getOU();
        String isHdpNew = item.getIsHdpNew();
        String isOUNew = item.getIsOUNew();
        String underOdds = item.getUnderOdds();
        String overOdds = item.getOverOdds();
        String homeHdpOdds = item.getHomeHdpOdds();
        String awayHdpOdds = item.getAwayHdpOdds();

        View vp = scrollChild(false,item,isHomeGive, hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds);
        sl.removeAllViews();
        sl.addView(vp, SoccerHeaderContent.layoutParams);
        if (!slFollowers.contains(sl))
            slFollowers.add(sl);
        sl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (sl.getFollowScrolls() == null) {
                    sl.setFollowScrolls(slFollowers);
                }
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
        sl.setCurrentIndex(slIndex);
        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);
        awayTv.setText(away);
    }

    public View scrollChild(boolean isFh,I item, String isHomeGive, String hasHdp, String hdp, String hasOU, String ou, String isHdpNew, String isOUNew, String underOdds, String overOdds, String homeHdpOdds, String awayHdpOdds) {
        LayoutInflater from = LayoutInflater.from(context);
        View vp = from.inflate(R.layout.sport_item_table_module_viewpager, null, false);
        ViewHolder holder = new ViewHolder(vp);
        if (hasHdp.equals("0")) {
            holder.viewpagerMatchHomeHdpTv.setText("");
            holder.viewpagerMatchHomeHdpoddsTv.setText("");
            holder.viewpagerMatchVisitHdpTv.setText("");
            holder.viewpagerMatchVisitHdpoddsTv.setText("");
        } else {
            String hdpS = changeValueF(hdp);
            if (isHomeGive.equals("1")) {
                holder.viewpagerMatchVisitHdpTv.setText("");
                holder.viewpagerMatchHomeHdpTv.setText(hdpS);
            } else {
                holder.viewpagerMatchVisitHdpTv.setText(hdpS);
                holder.viewpagerMatchHomeHdpTv.setText("");
            }
            boolean isAnimation = false;
            if (isHdpNew.equals("1"))
                isAnimation = true;
            else
                isAnimation = false;
            setValue(item,holder.viewpagerMatchHomeHdpoddsTv, homeHdpOdds, isAnimation,"home",isFh);
            setValue(item,holder.viewpagerMatchVisitHdpoddsTv, awayHdpOdds, isAnimation,"away",isFh);

        }


        if (hasOU.equals("0")) {
            holder.viewpagerMatchOuTv.setText("");
            holder.viewpagerMatchOu2Tv.setText("");
            holder.viewpagerMatchOveroddsTv.setText("");
            holder.viewpagerMatchUnderoddsTv.setText("");

        } else {
            String ouf = changeValueF(ou);
            holder.viewpagerMatchOuTv.setText(ouf);
            holder.viewpagerMatchOu2Tv.setText("");
            boolean isAnimation = false;
            if (isOUNew.equals("1"))
                isAnimation = true;
            setValue(item,holder.viewpagerMatchUnderoddsTv, underOdds, isAnimation,"under",isFh);
            setValue(item,holder.viewpagerMatchOveroddsTv, overOdds, isAnimation,"over",isFh);
        }
        return vp;
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

    protected void setValue(final I item, TextView textView, final String f, boolean isAnimation, final String type, final boolean isHf) {
        if (f.equals("")) {
            textView.setText(f);
        } else {
            textView.setText(AfbUtils.changeValueS(f));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back.clickOdds((TextView)v,item,type,isHf,f);
                }
            });
        }




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

    @Override
    public int onSetAdapterItemLayout() {
        return R.layout.sport_common_ball_item;
    }

    @Override
    public List<ScrollLayout> getFollowers() {
        return slFollowers;
    }

    public static class ViewHolder {
        @Bind(R.id.viewpager_match_home_hdp_tv)
        TextView viewpagerMatchHomeHdpTv;
        @Bind(R.id.viewpager_match_home_hdpodds_tv)
        TextView viewpagerMatchHomeHdpoddsTv;
        @Bind(R.id.viewpager_match_ou_tv)
        TextView viewpagerMatchOuTv;
        @Bind(R.id.viewpager_match_overodds_tv)
        TextView viewpagerMatchOveroddsTv;
        @Bind(R.id.viewpager_odd_label_tv)
        TextView viewpagerOddLabelTv;
        @Bind(R.id.viewpager_match_odd_tv)
        TextView viewpagerMatchOddTv;
        @Bind(R.id.viewpager_match_visit_hdp_tv)
        TextView viewpagerMatchVisitHdpTv;
        @Bind(R.id.viewpager_match_visit_hdpodds_tv)
        TextView viewpagerMatchVisitHdpoddsTv;
        @Bind(R.id.viewpager_match_ou2_tv)
        TextView viewpagerMatchOu2Tv;
        @Bind(R.id.viewpager_match_underodds_tv)
        TextView viewpagerMatchUnderoddsTv;
        @Bind(R.id.viewpager_even_label_tv)
        TextView viewpagerEvenLabelTv;
        @Bind(R.id.viewpager_match_even_tv)
        TextView viewpagerMatchEvenTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    interface BallItemCallBack<I> extends SportAdapterHelper.ItemCallBack<I>{
        boolean isItemCollection(I item);
        void collectionItem(I item);
    }

}
