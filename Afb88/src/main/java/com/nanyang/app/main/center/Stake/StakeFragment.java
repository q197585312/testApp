package com.nanyang.app.main.center.Stake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebSettings;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StakeListBean;
import com.nanyang.app.main.center.model.StakeListBean2;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.List;

import butterknife.Bind;

/**
 * Created by Administrator on 2017/3/23.
 */

public class StakeFragment extends BaseFragment<StakePresenter> implements StakeContact.View {
    @Bind(R.id.stake_rc)
    RecyclerView rc;
    @Bind(R.id.swiprefreshlayout)
    SwipeRefreshLayout swipeToLoadLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createPresenter(new StakePresenter(this));
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getStakeListData();
        swipeToLoadLayout.setColorSchemeResources(android.R.color.holo_green_dark);
        swipeToLoadLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getStakeListData();
                swipeToLoadLayout.setRefreshing(false);
            }
        });
    }


    @Override
    public void onGetData(String data) {
        Gson gson = new Gson();
        data = Html.fromHtml(data).toString();
        String[] data1 = data.split("nyhxkj");
        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
        StakeListBean2 stakeListBean2 = gson.fromJson(data1[1], StakeListBean2.class);
        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
        list2 = stakeListBean2.getResources();
        float total = 0.0f;
        for (int i = 0; i < list1.size(); i++) {
            StakeListBean.DicAllBean dicAllBean = list1.get(i);
            String amt = dicAllBean.getAmt();
            if (amt != null && !amt.isEmpty())
                total += Float.valueOf(amt);
        }
        StakeListBean.DicAllBean temp = new StakeListBean.DicAllBean();
        temp.setAmt(AfbUtils.decimalValue(total, "0.0"));
        list1.add(temp);
        initRc(list1);
    }

    List<StakeListBean2.ResourcesBean> list2;

    private void initRc(final List<StakeListBean.DicAllBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StakeListBean.DicAllBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StakeListBean.DicAllBean>(mContext, data, R.layout.item_stake) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final StakeListBean.DicAllBean item) {
                View view = holder.getView(R.id.ll_state_parent);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickItem(v, item);
                    }
                });
                String transType = item.getTransType();
                TextView refno = holder.getView(R.id.order_item_tv1);
                TextView homeAway = holder.getView(R.id.order_item_tv2);
                TextView combTv = holder.getView(R.id.order_item_tv21);
                TextView moduleTitle = holder.getView(R.id.order_item_tv4);
                TextView leagueTitle = holder.getView(R.id.order_item_league);
                TextView Odds = holder.getView(R.id.order_item_tv3);
                TextView Half = holder.getView(R.id.order_item_tv5);
                TextView dangerStatus = holder.getView(R.id.order_item_tv51);
                TextView amt = holder.getView(R.id.order_item_tv52);

                if (position == data.size() - 1) {
                    refno.setVisibility(View.GONE);
                    homeAway.setVisibility(View.GONE);
                    Odds.setVisibility(View.GONE);
                    moduleTitle.setVisibility(View.GONE);
                    Half.setVisibility(View.GONE);
                    combTv.setVisibility(View.GONE);
                    amt.setText(item.getAmt());
                    dangerStatus.setText("Total:");

                    return;
                } else {
                    combTv.setVisibility(View.VISIBLE);
                    refno.setVisibility(View.VISIBLE);
                    homeAway.setVisibility(View.VISIBLE);
                    Odds.setVisibility(View.VISIBLE);
                    moduleTitle.setVisibility(View.VISIBLE);
                    Half.setVisibility(View.VISIBLE);
                }
                if (item.getCombInfo().trim().isEmpty()) {
                    combTv.setVisibility(View.GONE);
                } else {
                    combTv.setVisibility(View.VISIBLE);
                    combTv.setText(item.getCombInfo());
                }
                refno.setText(item.getRefNo() + "(" + item.getTransDate() + ")");
                homeAway.setText(item.getHome() + "  vs  " + item.getAway());
                moduleTitle.setTextColor(0xff333333);
                Odds.setTextColor(0xff333333);
                moduleTitle.setText(item.getModuleTitle());
                String odds = item.getOdds();
                String od = "";
                if (item.getBetType() != null) {
                    if (transType.equals("OU") || transType.equals("OE")) {
                        od = item.getOdds();
                        if (transType.equals("OU")) {
                            odds = item.getHdp() + "@" + " " + item.getOdds() + " " + item.getOddsType() + " (inet)";
                            if (item.isIsBetHome()) {
                                moduleTitle.setText(getString(R.string.over));
                                moduleTitle.setTextColor(Color.RED);
                            } else {
                                moduleTitle.setText(getString(R.string.under));
                                moduleTitle.setTextColor(Color.BLUE);
                            }
                        } else if (transType.equals("OE")) {
                            odds = item.getHdp() + "@" + " " + item.getOdds() + " " + item.getOddsType() + " (inet)";
                            if (item.isIsBetHome()) {
                                moduleTitle.setText(getString(R.string.odd));
                                moduleTitle.setTextColor(Color.RED);
                            } else {
                                moduleTitle.setText(getString(R.string.even));
                                moduleTitle.setTextColor(Color.BLUE);
                            }
                        }

                    } else if (transType.equals("1") || transType.equals("2") || transType.equals("HDP") || transType.equals("X") || transType.startsWith("PA")) {
                        od = item.getDisplayOdds2();
                        odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                        moduleTitle.setTextColor(Color.BLUE);
                        if (transType.equalsIgnoreCase("X")) {
                            moduleTitle.setText(item.getHome() + " (" + getString(R.string.draw) + ")");
                        } else if (transType.equals("1")) {
                            moduleTitle.setText(item.getHome() + " (" + getString(R.string.win) + ")");
                            if(item.isIsHomeGive()){
                                moduleTitle.setTextColor(getResources().getColor(R.color.red_title));
                            }
                        }

                         else if (transType.equals("2")) {
                            odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                            moduleTitle.setText(item.getAway() + " (" + getString(R.string.win) + ")");
                            if(!item.isIsHomeGive()){
                                moduleTitle.setTextColor(getResources().getColor(R.color.red_title));
                            }

                        } else if (transType.startsWith("PA")) {
                            moduleTitle.setText(getString(R.string.MixParlay));
                        } else if (transType.equals("HDP")) {
                            od = item.getOdds();
                            odds = item.getDisplayHdp() + " @" + " " + item.getOdds() + " " + item.getOddsType() + " (inet)";
                            if (item.isIsBetHome()) {
                                moduleTitle.setText(item.getHome());
                                if (item.isIsHomeGive()) {
                                    moduleTitle.setTextColor(getResources().getColor(R.color.red_title));
                                } else {
                                    moduleTitle.setTextColor(Color.BLUE);
                                }
                            } else {
                                moduleTitle.setText(item.getAway());
                                if (item.isIsHomeGive()) {
                                    moduleTitle.setTextColor(Color.BLUE);
                                } else {
                                    moduleTitle.setTextColor(getResources().getColor(R.color.red_title));
                                }
                            }

                        }
                    } else if (transType.equals("3D") || transType.equals("2DT") || transType.equals("1DT")) {
                        moduleTitle.setVisibility(View.GONE);
                        String thaiDate = item.getWorkingDate().substring(0, 5);
                        od = item.getHdp();
                        odds = "@" + " " + item.getHdp() + "(" + transType + "-" + thaiDate + ")" + " (inet)";
                        Odds.setTextColor(Color.BLUE);
                    } else if (transType.equals("FLG") || transType.equals("HFT")) {
                        od = item.getDisplayOdds2();
                        odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                        if (transType.equals("FLG")) {
                            moduleTitle.setText(item.getFGLGScore() );
                        } else if (transType.equals("HFT")) {
                            moduleTitle.setText(item.getHTFTScore() );
                        }
                        moduleTitle.setTextColor(Color.RED);
                    } else if (transType.equals("TG") || transType.equals("CSR")) {
                        od = item.getDisplayOdds2();
                        odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                        if (transType.equals("TG")) {
                            moduleTitle.setText(item.getTGScore() );
                            moduleTitle.setTextColor(Color.RED);
                        } else if (transType.equals("CSR")) {
                            moduleTitle.setText(item.getCSRScore() );
                            moduleTitle.setVisibility(View.GONE);
                        }
                    } else if (transType.equals("DC")) {
                        od = item.getDisplayOdds2();
                        odds ="@" + " " + item.getDisplayOdds2() + " (inet)";
                        moduleTitle.setText(item.getDCScore() );
                        moduleTitle.setTextColor(Color.RED);
                    }
                }
                if (item.isIsRun()) {
                    odds = "(" + item.getRunHomeScore() + " - " + item.getRunAwayScore() + ")" + odds;
                }
                int start = odds.indexOf(od);
                int end = start + od.length();
                SpannableStringBuilder style = new SpannableStringBuilder(odds);
                if (!od.isEmpty() && Float.valueOf(od) < 0) {
                    style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.red_title)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                } else {
                    style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.black_grey)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                }


                Odds.setText(style);
                String half = "";
                if (item.getFullTimeId() > 0) {
                    half = "(First Half)";
                }


                String tType = getString(R.string.HANDICAP);
                switch (transType) {
                    case "OU":
                        tType = getString(R.string.OVER_UNDER);
                        break;
                    case "OE":
                        tType = getString(R.string.ODD_EVEN);
                        break;
                    case "HDP":
                        tType = getString(R.string.HANDICAP);
                        break;
                    case "1":
                    case "2":
                    case "x":
                    case "X":
                        tType = getString(R.string.X1X2);
                        break;
                    case "DC":
                        tType = getString(R.string.double_chance);
                        break;
                    case "CSR":
                        tType = getString(R.string.correct_score);
                        break;
                    case "HFT":
                        tType = getString(R.string.half_full_time);
                        break;
                    case "FLG":
                        tType = getString(R.string.first_last_goal);
                        break;
                    case "TG":
                        tType = getString(R.string.total_goals);
                        break;

                }
                if (transType.startsWith("PA"))
                    tType = getString(R.string.PARLAY);
                if (transType.equals("1") && item.getGameType3().equals("O")) {
                    tType = getString(R.string.OutRight);
                    homeAway.setVisibility(View.GONE);
                }
                else{
                    homeAway.setVisibility(View.VISIBLE);
                }
                Half.setText(tType + half);
                leagueTitle.setText(item.getModuleTitle());
                String n = "Accepted";
                if (item.getDangerStatus().equals("D")) {
                    n = "Waiting";
                    dangerStatus.setBackgroundResource(R.color.yellow_button);
                } else if (item.getDangerStatus().equals("R")) {
                    n = "Rejected " + item.getR_DateTime();
                    dangerStatus.setBackgroundResource(R.drawable.rectangle_red);
                } else if (item.getDangerStatus().equals("RR")) {
                    n = "Rejected (Red Card " + item.getR_DateTime() + ")";
                    dangerStatus.setBackgroundResource(R.drawable.rectangle_red);
                } else if (item.getDangerStatus().equals("RP")) {
                    n = "Rejected (Goal Disallowed " + item.getR_DateTime() + ")";
                    dangerStatus.setBackgroundResource(R.drawable.rectangle_red);
                } else if (item.getDangerStatus().equals("RA")) {
                    n = "Rejected (Abnormal Bet " + item.getR_DateTime() + ")";
                    dangerStatus.setBackgroundResource(R.drawable.rectangle_red);
                } else if (item.getDangerStatus().equals("RG")) {
                    n = "Rejected (Goal " + item.getR_DateTime() + ")";
                    dangerStatus.setBackgroundResource(R.drawable.rectangle_red);
                } else if (item.getDangerStatus().equals("0")) {
                    n = "Oddschange";
                    dangerStatus.setBackgroundResource(R.color.yellow_button);
                } else {
                    dangerStatus.setBackgroundResource(R.color.green500);
                }
                if (position == data.size() - 1) {
                    n = "Total " + n;
                }
                dangerStatus.setText(n);

                amt.setText(item.getAmt());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<StakeListBean.DicAllBean>() {
            @Override
            public void onItemClick(View view, StakeListBean.DicAllBean item, int position) {

            }
        });
        rc.setAdapter(baseRecyclerAdapter);
    }

    private void clickItem(View v, StakeListBean.DicAllBean item) {
        if (item.getTransType().startsWith("PA")) {
//            http://main55.afb88.com/_norm/PamTrans.aspx?userName=demoafbAi1&id=138661496
            WebPop pop = new WebPop(mContext, v);
            WebSettings webSettings = pop.getWebView().getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setAllowContentAccess(true);
            webSettings.setAppCacheEnabled(false);
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
            String url = AppConstant.HOST + "_norm/PamTrans.aspx?userName=" + ((BaseToolbarActivity) getActivity()).getApp().getUser().getUserName() + "&id=" + item.getSocTransId();
            pop.setUrl(url);
            pop.showPopupCenterWindow();
        }
    }


    @Override
    public void onFailed(String error) {

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_stake;
    }
}
