package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.training.ScrollLayout;

import java.util.HashSet;
import java.util.Locale;
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

    public Set<ScrollLayout> getSlFollowers() {
        return slFollowers;
    }

    protected Set<ScrollLayout> slFollowers = new HashSet<>();

    private int slIndex = 0;


    public BallAdapterHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onConvert(MyRecyclerViewHolder helper, final int position, final I item) {
        ImageView ivHall = helper.getView(R.id.iv_hall_btn);
        ivHall.setVisibility(View.GONE);
        helper.getView(R.id.iv_hall_btn).setVisibility(View.GONE);
        TextView matchTitleTv = helper.getView(R.id.module_match_title_tv);
        View headV = helper.getView(R.id.module_match_head_v);
        TextView dateTv = helper.getView(R.id.module_match_date_tv);
        TextView timeTv = helper.getView(R.id.module_match_time_tv);
        TextView liveTv = helper.getView(R.id.module_match_live_iv);
        TextView homeTv = helper.getTextView(R.id.module_match_home_team_tv);
        TextView awayTv = helper.getTextView(R.id.module_match_away_team_tv);
        View tvRightMark = helper.getView(R.id.module_right_mark_tv);
        final View tvCollection = helper.getView(R.id.module_match_collection_tv);
        liveTv.setTextColor(red_black);
        dateTv.setTextColor(red_black);
        timeTv.setTextColor(black_grey);
        dateTv.setTextSize(10);
        dateTv.setPadding(0, 0, 0, 0);

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
        } else {
            if (item.getMatchDate().length() > 6) {
                String date = item.getMatchDate().substring(0, 5);
                dateTv.setText(date);
            } else {
                dateTv.setText(item.getMatchDate());
            }
        }
        tvCollection.setVisibility(View.GONE);
        String isHomeGive = item.getIsHomeGive();
        if (isHomeGive.equals("1")) {
            homeTv.setTextColor(red_black);
            awayTv.setTextColor(black_grey);
        } else {
            homeTv.setTextColor(black_grey);
            awayTv.setTextColor(red_black);
        }

        tvRightMark.setVisibility(View.GONE);
        tvRightMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.clickView(v, item, position);
            }
        });
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
        scrollChild(sl.getChildAt(0), false, item, isHomeGive, hasHdp, hdp, hasOU, ou, isHdpNew, isOUNew, underOdds, overOdds, homeHdpOdds, awayHdpOdds);
        getBaseRecyclerAdapter().getItem(position).setIsHdpNew("0");
        getBaseRecyclerAdapter().getItem(position).setIsOUNew("0");
        if (!slFollowers.contains(sl))
            slFollowers.add(sl);
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
        String away = item.getAway();
        String home = item.getHome();
        homeTv.setText(home);
        awayTv.setText(away);
        if (liveTv.getText().toString().trim().isEmpty()) {
            liveTv.setVisibility(View.GONE);
        } else {
            liveTv.setVisibility(View.VISIBLE);
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
            if (upOdds.trim().isEmpty() || downOdds.trim().isEmpty()||upOdds.equals("0") || downOdds.equals("0")) {
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
                upOddsTv.setText(upOdds);
                downOddsTv.setText(downOdds);
                boolean isAnimation = false;
                if (isNew != null && isNew.equals("1")) {
                    isAnimation = true;
                }
                setValue(item, upOddsTv, upOdds, isAnimation, upType, isFh, R.drawable.green_trans_shadow_top,true);
                setValue(item, upTextTv, upOdds, false, upType, isFh, R.drawable.green_trans_shadow_top,false);
                setValue(item, downOddsTv, downOdds, isAnimation, downType, isFh, R.drawable.green_trans_shadow_bottom,true);
                setValue(item, downTextTv, downOdds, false, downType, isFh, R.drawable.green_trans_shadow_bottom,false);
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

    protected String setValue(final I item, final TextView textView, final String f, boolean isAnimation, final String type, final boolean isHf, final int resBg,boolean isShowText) {
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
                            ((BaseActivity)context).dynamicAddView(textView, "background", resBg);
//                            textView.setBackgroundResource(resBg);
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            textView.setBackgroundResource(0);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {
                            ((BaseActivity)context).dynamicAddView(textView, "background", resBg);
                        }
                    });
                    textView.startAnimation(animation);
                } else {
                    textView.setBackgroundResource(0);
                }

                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        back.clickOdds((TextView) v, item, type, isHf, f);
                    }
                });
            } else {
                textView.setOnClickListener(null);
            }
            if (isShowText){
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


    public static class ViewHolder {
        @Bind(R.id.viewpager_match_home_hdp_tv)
        public
        TextView viewpagerMatchHomeHdpTv;
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
