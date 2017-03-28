package com.nanyang.app.main.center.Stake;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.StakeListBean;
import com.nanyang.app.main.center.model.StakeListBean2;
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
        String[] data1 = data.split("nyhxkj");
        StakeListBean stakeListBean = gson.fromJson(data1[0], StakeListBean.class);
        StakeListBean2 stakeListBean2 = gson.fromJson(data1[1], StakeListBean2.class);
        List<StakeListBean.DicAllBean> list1 = stakeListBean.getDicAll();
        list2 = stakeListBean2.getResources();
        initRc(list1);
    }

    List<StakeListBean2.ResourcesBean> list2;

    private void initRc(final List<StakeListBean.DicAllBean> data) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(mLayoutManager);
        BaseRecyclerAdapter<StakeListBean.DicAllBean> baseRecyclerAdapter = new BaseRecyclerAdapter<StakeListBean.DicAllBean>(mContext, data, R.layout.item_stake) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, StakeListBean.DicAllBean item) {
                String transType = item.getTransType();
                TextView refno = holder.getView(R.id.order_item_tv1);
                TextView homeAway = holder.getView(R.id.order_item_tv2);
                TextView moduleTitle = holder.getView(R.id.order_item_tv4);
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
                } else {
                    refno.setVisibility(View.VISIBLE);
                    homeAway.setVisibility(View.VISIBLE);
                    Odds.setVisibility(View.VISIBLE);
                    moduleTitle.setVisibility(View.VISIBLE);
                    Half.setVisibility(View.VISIBLE);
                }
                refno.setText(item.getRefNo() + "(" + item.getTransDate() + ")");
                homeAway.setText(item.getHome() + "  vs  " + item.getAway());
                moduleTitle.setTextColor(0xff333333);
                Odds.setTextColor(0xff333333);
                moduleTitle.setText(item.getModuleTitle());
                String odds = item.getOdds();
                if (item.getBetType() != null) {
                    if (transType.equals("OU") || transType.equals("OE")) {
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

                    } else if (transType.equals("1") || transType.equals("2") || transType.equals("HDP") || transType.equals("X") || transType.equals("PAR")) {
                        odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                        if (transType.equals("1") || transType.equals("X")) {
                            if (transType.equals("X")) {
                                moduleTitle.setText(item.getHome() + " (" + getString(R.string.draw) + ")");
                            } else {
                                moduleTitle.setText(item.getHome() + " (" + getString(R.string.win) + ")");
                            }
                            moduleTitle.setTextColor(Color.BLUE);
                        } else if (transType.equals("2")) {
                            odds = "@" + " " + item.getDisplayOdds2() + " (inet)";
                            moduleTitle.setText(item.getAway() + " (" + getString(R.string.win) + ")");
                            moduleTitle.setTextColor(Color.BLUE);
                        } else if (transType.equals("PAR")) {
                            moduleTitle.setText(getString(R.string.MixParlay));
                            moduleTitle.setTextColor(0xff008000);
                        } else if (transType.equals("HDP")) {
                            odds = item.getDisplayHdp() + " @" + " " + item.getOdds() + " " + item.getOddsType() + " (inet)";
                            if (item.isIsHomeGive()) {
                                moduleTitle.setText(item.getHome());
                                moduleTitle.setTextColor(Color.RED);
                            } else {
                                moduleTitle.setText(item.getHome());
                                moduleTitle.setTextColor(Color.BLUE);
                            }
                        }
                    } else if (transType.equals("3D") || transType.equals("2DT") || transType.equals("1DT")) {
                        moduleTitle.setVisibility(View.GONE);
                        String thaiDate = item.getWorkingDate().substring(0, 5);
                        odds = "@" + " " + item.getHdp() + "(" + transType + "-" + thaiDate + ")" + " (inet)";
                        Odds.setTextColor(Color.BLUE);
                    } else if (transType.equals("FLG") || transType.equals("HFT")) {
                        odds = item.getFGLGScore() + "@" + " " + item.getDisplayOdds2() + " (inet)";
                        if (transType.equals("FLG")) {
                            moduleTitle.setText(getString(R.string.first_last_goal));
                        } else if (transType.equals("HFT")) {
                            moduleTitle.setText(getString(R.string.half_full_time));
                        }
                        moduleTitle.setTextColor(Color.RED);
                    } else if (transType.equals("TG") || transType.equals("CSR")) {
                        odds = item.getCSScore() + "@" + " " + item.getDisplayOdds2() + " (inet)";
                        if (transType.equals("TG")) {
                            moduleTitle.setText(getString(R.string.total_goals));
                            moduleTitle.setTextColor(Color.RED);
                        } else if (transType.equals("CSR")) {
                            moduleTitle.setVisibility(View.GONE);
                        }
                    } else if (transType.equals("DC")) {
                        odds = item.getDCScore() + "@" + " " + item.getDisplayOdds2() + " (inet)";
                        moduleTitle.setText(getString(R.string.double_chance));
                        moduleTitle.setTextColor(Color.RED);
                    }
                }
                Odds.setText(odds);
                String half = "";
                if (item.getFullTimeId() > 0) {
                    half = "(First Half)";
                }
                if (list2.size() != 0) {
                    Half.setText(list2.get(0).getGameType() + half);
                }
                String n = getString(R.string.amount);
                if (data.size() - 1 == position) {
                    n = getString(R.string.total_amount);
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

    @Override
    public void onFailed(String error) {

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_stake;
    }
}
