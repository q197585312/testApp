package com.nanyang.app.main.home.sport.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.football.FootballPresenter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.nanyang.app.myView.LinkedViewPager.ViewPager;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.utils.ViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import cn.finalteam.toolsfinal.DeviceUtils;

import static cn.finalteam.toolsfinal.DeviceUtils.dip2px;

/**
 * Created by Administrator on 2017/2/24.
 */

public class VpBallAdapter extends BaseRecyclerAdapter<MatchBean> {
    private SportPresenter presenter;
    private BetBasePop betPop;
    private ArrayList<ViewPager> vps = new ArrayList<>();
    private int vpPosition = 0;

    public void setVpHeader(ViewPager vpHeader) {
        this.vpHeader = vpHeader;
    }

    private ViewPager vpHeader;

    public VpBallAdapter(Context context, List<MatchBean> mDatas, int layoutId) {
        super(context, mDatas, layoutId);
    }

    public void setPresenter(SportPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void convert(MyRecyclerViewHolder helper, final int position, final MatchBean item) {


        String oldModuleTitle = null;
        String oldHomeGive = null;
        ;
        String oldAwayName = null;
        ;
        String oldHomeName = null;
        ;


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

        liveTv.setTextColor(mContext.getResources().getColor(R.color.google_yellow));
        dateTv.setTextColor(mContext.getResources().getColor(R.color.google_yellow));
        timeTv.setTextColor(mContext.getResources().getColor(R.color.green_light));
        dateTv.setTextAppearance(mContext, R.style.text_normal);
        dateTv.setPadding(0, 0, 0, 0);


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
                            timeTv.setText(mContext.getString(R.string.not_started));
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
            tvCollection.setVisibility(View.INVISIBLE);
            item.getOtherDataBean().setHasOE("0");
            item.getOtherDataBean().setHasOEFH("0");

        } else {

            if (item.getHandicapBeans().get(0).getIsHomeGive().equals("1")) {
                homeTv.setTextColor(mContext.getResources().getColor(R.color.google_yellow));
                awayTv.setTextColor(mContext.getResources().getColor(R.color.black_grey));
            } else {
                homeTv.setTextColor(mContext.getResources().getColor(R.color.black_grey));
                awayTv.setTextColor(mContext.getResources().getColor(R.color.google_yellow));
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
                presenter.onRightMarkClick(b);

            }

        });
        if ((presenter instanceof FootballPresenter) && ((FootballPresenter) presenter).isItemCollection(item))
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
        if (presenter instanceof FootballPresenter)
            vp.getLayoutParams().width = DeviceUtils.dip2px(mContext, 140);
        else {
            vp.getLayoutParams().width = DeviceUtils.dip2px(mContext, 210);
        }
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
        if (presenter instanceof FootballPresenter) {
            tvCollection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ((FootballPresenter) presenter).collectionItem(getItem(position));
                    notifyDataSetChanged();
                }
            });
        } else {
            tvCollection.setVisibility(View.GONE);
            tvRightMark.setVisibility(View.GONE);
        }
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

                getItem(getParentPosition()).getHandicapBeans().get(position).setIsHdpNew("0");

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
                TextView tvOddLabel = helper.getView(R.id.viewpager_odd_label_tv);
                TextView tvEvenLabel = helper.getView(R.id.viewpager_even_label_tv);
                TextView tvOdd = helper.getView(R.id.viewpager_match_odd_tv);
                TextView tvEven = helper.getView(R.id.viewpager_match_even_tv);
                if (presenter instanceof FootballPresenter) {
                    tvOddLabel.setVisibility(View.GONE);
                    tvEvenLabel.setVisibility(View.GONE);
                    tvOdd.setVisibility(View.GONE);
                    tvEven.setVisibility(View.GONE);

                } else {

                    tvOddLabel.setVisibility(View.VISIBLE);
                    tvEvenLabel.setVisibility(View.VISIBLE);
                    tvOdd.setVisibility(View.VISIBLE);
                    tvEven.setVisibility(View.VISIBLE);
                    if(position==0) {
                        if (bean.getOtherDataBean().getHasOE().equals("1")) {

                            tvOdd.setText(AfbUtils.changeValueS(bean.getOtherDataBean().getOddOdds()));
                            tvEven.setText(AfbUtils.changeValueS(bean.getOtherDataBean().getEvenOdds()));
                        } else {
                            tvOdd.setText("");
                            tvEven.setText("");
                            tvOddLabel.setText("");
                            tvEvenLabel.setText("");
                        }
                    }
                    else if(position==1) {
                        if (bean.getOtherDataBean().getHasOEFH().equals("1")) {
                            tvOdd.setText(AfbUtils.changeValueS(bean.getOtherDataBean().getOddOddsFH()));
                            tvEven.setText(AfbUtils.changeValueS(bean.getOtherDataBean().getEvenOddsFH()));
                        } else {
                            tvOdd.setText("");
                            tvEven.setText("");
                            tvOddLabel.setText("");
                            tvEvenLabel.setText("");
                        }
                    }
                }

                VpBallAdapter.this.getItem(getParentPosition()).getHandicapBeans().get(position).setIsOUNew("1");
                notifyClearanceBet(VpBallAdapter.this.getItem(getParentPosition()), position, helper);
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
                                if (DeviceUtils.getScreenPix((Activity) mContext).widthPixels >= 1920) {
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
                                            addMixParlayBet(v, VpBallAdapter.this.getItem(getParentPosition()), position, over, ou, overOdds, handicapBean.getIsHomeGive());
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
                    ((TextView) v).setTextColor(mContext.getResources().getColor(R.color.white));

                } else {
                    ((BaseToolbarActivity) mContext).getApp().setBetDetail(null);
                    countBet();
                    VpBallAdapter.this.notifyDataSetChanged();
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

    private void createPopupWindow(BetBasePop betPop) {
        presenter.createPopupWindow(betPop);
    }

    private void countBet() {
        presenter.countBet();
    }

    private void notifyClearanceBet(MatchBean item, int position, ViewHolder helper) {
        if (((BaseToolbarActivity) mContext).getApp().getBetDetail() == null || item == null)
            return;
        Map<String, Map<Integer, BettingInfoBean>> keyMap = ((BaseToolbarActivity) mContext).getApp().getBetDetail().get(item.getHome() + "+" + item.getAway());
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
}
