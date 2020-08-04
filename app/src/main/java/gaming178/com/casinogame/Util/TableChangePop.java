package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.entity.BaccaratTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.DiceBean;
import gaming178.com.casinogame.Activity.entity.DiceContentBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.SicboTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.TableTimerBean;
import gaming178.com.casinogame.Bean.GameMenuItem;
import gaming178.com.casinogame.Bean.TableMaintenanceBean;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.AppModel;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2016/12/1.
 */

public class TableChangePop extends BasePopupWindow {
    private ArrayList<GameMenuItem> tables;
    private BaseRecyclerAdapter<String> adapter;
    private LinearLayout parent;
    private List<TableTimerBean> list;
    private TextView tv_b, tv_r, tv_s, tv_d;
    List<TextView> hereList = new ArrayList<>();
    List<TableMaintenanceBean> tableMaintenanceList = new ArrayList<>();
    private AppModel mAppViewModel;

    public TableChangePop(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    public int getParentCount() {
        if (parent != null) {
            return parent.getChildCount();
        } else {
            return 0;
        }
    }

    public void setClickImp(ItemCLickImp<GameMenuItem> cLickImp) {
        this.cLickImp = cLickImp;
    }

    ItemCLickImp cLickImp;


    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_layout_framelayout_table;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        baccaratTableChangeViewBeenList = new ArrayList<>();
        list = new ArrayList<>();
        parent = (LinearLayout) view.findViewById(R.id.gd__ll_change_table_parent);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tv_b = view.findViewById(R.id.gd__tv_b);
        tv_r = view.findViewById(R.id.gd__tv_r);
        tv_s = view.findViewById(R.id.gd__tv_s);
        tv_d = view.findViewById(R.id.gd__tv_d);
        tv_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < baccaratTableChangeViewBeenList.size(); i++) {
                    baccaratTableChangeViewBeenList.get(i).getView_Parent().setVisibility(View.VISIBLE);
                }
                dragonTigerTableChangeViewBeen.getView_Parent().setVisibility(View.VISIBLE);
                rouletteTableChangeViewBean.getView_Parent().setVisibility(View.VISIBLE);
                sicboTableChangeViewBean.getView_Parent().setVisibility(View.VISIBLE);
            }
        });
        tv_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < baccaratTableChangeViewBeenList.size(); i++) {
                    baccaratTableChangeViewBeenList.get(i).getView_Parent().setVisibility(View.GONE);
                }
                dragonTigerTableChangeViewBeen.getView_Parent().setVisibility(View.GONE);
                rouletteTableChangeViewBean.getView_Parent().setVisibility(View.VISIBLE);
                sicboTableChangeViewBean.getView_Parent().setVisibility(View.GONE);
            }
        });
        tv_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < baccaratTableChangeViewBeenList.size(); i++) {
                    baccaratTableChangeViewBeenList.get(i).getView_Parent().setVisibility(View.GONE);
                }
                dragonTigerTableChangeViewBeen.getView_Parent().setVisibility(View.GONE);
                rouletteTableChangeViewBean.getView_Parent().setVisibility(View.GONE);
                sicboTableChangeViewBean.getView_Parent().setVisibility(View.VISIBLE);
            }
        });
        tv_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < baccaratTableChangeViewBeenList.size(); i++) {
                    baccaratTableChangeViewBeenList.get(i).getView_Parent().setVisibility(View.GONE);
                }
                dragonTigerTableChangeViewBeen.getView_Parent().setVisibility(View.VISIBLE);
                rouletteTableChangeViewBean.getView_Parent().setVisibility(View.GONE);
                sicboTableChangeViewBean.getView_Parent().setVisibility(View.GONE);
            }
        });
    }

    private boolean isNeedRefenshTimer;

    public void setPopTopContent() {
        for (int i = 0; i < hereList.size(); i++) {
            hereList.get(i).setText(context.getString(R.string.your_here));
        }
        refreshTimer((BaseActivity) context);
    }

    private void refreshTimer(final BaseActivity baseActivity) {
        isNeedRefenshTimer = true;
        new Thread() {
            @Override
            public void run() {
                while (isNeedRefenshTimer) {
                    baseActivity.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            if (baccaratTableChangeViewBeenList != null && baccaratTableChangeViewBeenList.size() > 0) {
                                for (int j = 0; j < baccaratTableChangeViewBeenList.size(); j++) {
                                    BaccaratTableChangeViewBean baccaratTableChangeViewBean = baccaratTableChangeViewBeenList.get(j);
                                    mAppViewModel.updateBigRoad(context, mAppViewModel.getBaccarat(baccaratTableChangeViewBean.getTableId()), baccaratTableChangeViewBean.getLayout(),
                                            baccaratTableChangeViewBean.getTv_baccarat_shoe_number(), baccaratTableChangeViewBean.getTv_baccarat_total_number(), baccaratTableChangeViewBean.getTv_baccarat_banker_number(), baccaratTableChangeViewBean.getTv_baccarat_player_number(), baccaratTableChangeViewBean.getTv_baccarat_tie_number()
                                            , baccaratTableChangeViewBean.getTv_baccarat_bp_number(), baccaratTableChangeViewBean.getTv_baccarat_pp_number(), baccaratTableChangeViewBean.getLl_good_road_parent(), baccaratTableChangeViewBean.getTv_good_road_name());
                                }
                            }
                            if (dragonTigerTableChangeViewBeen != null) {
                                mAppViewModel.updateDragenTigerBigRoad(context, mAppViewModel.getDragonTiger01(), dragonTigerTableChangeViewBeen.getLayout(), dragonTigerTableChangeViewBeen.getTv_baccarat_shoe_number(), dragonTigerTableChangeViewBeen.getTv_baccarat_total_number(), dragonTigerTableChangeViewBeen.getTv_baccarat_banker_number(), dragonTigerTableChangeViewBeen.getTv_baccarat_player_number(), dragonTigerTableChangeViewBeen.getTv_baccarat_tie_number()
                                        , dragonTigerTableChangeViewBeen.getTv_baccarat_bp_number(), dragonTigerTableChangeViewBeen.getTv_baccarat_pp_number());
                            }
                            if (rouletteTableChangeViewBean != null && adapter != null) {
                                mAppViewModel.updateRouletteBigRoad(mAppViewModel.getRoulette01(), adapter, rouletteTableChangeViewBean.getTv_game_number01(), rouletteTableChangeViewBean.getTv_roulette_red01(), rouletteTableChangeViewBean.getTv_roulette_black01(), rouletteTableChangeViewBean.getTv_roulette_zero01(), rouletteTableChangeViewBean.getTv_roulette_even01(), rouletteTableChangeViewBean.getTv_roulette_odd01(), rouletteTableChangeViewBean.getTv_roulette_big01(), rouletteTableChangeViewBean.getTv_roulette_small01());
                            }
                            if (sicboTableChangeViewBean != null) {
                                if (mAppViewModel.getSicbo01().getRoad() != null && !mAppViewModel.getSicbo01().getRoad().equals(mAppViewModel.getSicbo01().getRoadOld())) {
                                    mAppViewModel.getSicbo01().setRoadOld(mAppViewModel.getSicbo01().getRoad());
                                    sicboTableChangeViewBean.getLinearlayout().removeAllViewsInLayout();
                                    for (DiceContentBean dice : getSicboResultsData(mAppViewModel)) {
                                        View diceView = LayoutInflater.from(context).inflate(R.layout.gd_item_table_bet_dice_info, null);
                                        ((ImageView) diceView.findViewById(R.id.gd__iv_dice_1)).setImageResource(dice.getList().get(0).getResDrawable());
                                        ((ImageView) diceView.findViewById(R.id.gd__iv_dice_2)).setImageResource(dice.getList().get(1).getResDrawable());
                                        ((ImageView) diceView.findViewById(R.id.gd__iv_dice_3)).setImageResource(dice.getList().get(2).getResDrawable());
                                        sicboTableChangeViewBean.getLinearlayout().addView(diceView);
                                    }
//                                    mAppViewModel.updateGameNumber(mAppViewModel.getSicbo01(), sicboTableChangeViewBean.getTv_sicbo_number01(), sicboTableChangeViewBean.getTv_even01(), sicboTableChangeViewBean.getTv_small01(), sicboTableChangeViewBean.getTv_waidic01(), sicboTableChangeViewBean.getTv_big01(), sicboTableChangeViewBean.getTv_odd01());
                                }
                            }
                        }
                    });
                    String strRes = baseActivity.mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + baseActivity.mAppViewModel.getUser().getName());
                    if (strRes.startsWith("Results=ok")) {
                        String[] split = strRes.split("\\^");
                        //Results=ok#^1#1#18#^2#2#0#^3#5#0#^5#2#0#^21#2#0#^31#1#10#^61#5#0#^62#5#0#^63#1#0#^64#5#0#^65#5#0#^66#5#0#^71#2#0#^
                        for (int i = 0; i < split.length; i++) {
                            if (i > 0) {
                                String s = split[i];
                                String[] split1 = s.split("#");
                                final String type = split1[0];
                                final String timer = split1[2];
                                Log.d("shangpeisheng11111", "type: " + type + "----" + "timer :" + timer);
                                baseActivity.getHandler().post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (list.size() == 10) {
                                            updateTimer(type, timer);
                                        }
                                        updateTableMaintenance();
                                    }
                                });

                            }
                        }
                    }
                    try {
                        Thread.sleep(990);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    protected void onCloose() {
        super.onCloose();
        isNeedRefenshTimer = false;
    }

    private void updateTableMaintenance() {
        for (int i = 0; i < tableMaintenanceList.size(); i++) {
            TableMaintenanceBean tableMaintenanceBean = tableMaintenanceList.get(i);
            int tableId = tableMaintenanceBean.getTableId();
            View view = tableMaintenanceBean.getView();
            if (tableId == 5) {
                if (mAppViewModel.getDragonTiger01().getStatus() != 1) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            } else if (tableId == 21) {
                if (mAppViewModel.getRoulette01().getStatus() != 1) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            } else if (tableId == 31) {
                if (mAppViewModel.getSicbo01().getStatus() != 1) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            } else {
                if (mAppViewModel.getBaccarat(tableId).getStatus() != 1) {
                    view.setVisibility(View.VISIBLE);
                } else {
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    private void updateTimer(String type, String timer) {
        for (int i = 0; i < list.size(); i++) {
            TableTimerBean tableTimerBean = list.get(i);
            String type1 = tableTimerBean.getType();
            final TextView tvTimer = tableTimerBean.getTvTimer();
            if (type1.equals(type)) {
                if (tvTimer != null) {
                    tvTimer.setText(timer);
                }
            }
        }
    }

    public int getMipmap(int res) {
        int resMipmap = 0;
        switch (res) {
            case 1:
                resMipmap = R.mipmap.gd_dice1;
                break;
            case 2:
                resMipmap = R.mipmap.gd_dice2;
                break;
            case 3:
                resMipmap = R.mipmap.gd_dice3;
                break;
            case 4:
                resMipmap = R.mipmap.gd_dice4;
                break;
            case 5:
                resMipmap = R.mipmap.gd_dice5;
                break;
            case 6:
                resMipmap = R.mipmap.gd_dice6;
                break;
        }
        return resMipmap;
    }

    public List<DiceContentBean> getSicboResultsData(AppModel mAppViewModel) {
        List<DiceContentBean> list = new ArrayList<DiceContentBean>();
        //得到最近15局的结果
        try {
            String luziInfo[] = mAppViewModel.getSicbo01().getRoad().split("\\#");
            if (luziInfo.length <= 0 && luziInfo.length > 100) {//数据格式不对
                return null;
            }
            int point = 0;
            for (int i = 99; i > 84; i--) {
                DiceContentBean diceContentBean = new DiceContentBean();
                DiceBean diceBean1 = new DiceBean();
                DiceBean diceBean2 = new DiceBean();
                DiceBean diceBean3 = new DiceBean();
                String luziInfoDetail[] = luziInfo[i].split("\\-");//路子相信信息9-8-7
                point = Integer.parseInt(luziInfoDetail[0]) + Integer.parseInt(luziInfoDetail[1]) + Integer.parseInt(luziInfoDetail[2]);
                diceContentBean.setPoint(point);
                diceBean1.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[0])));

                diceBean2.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[1])));

                diceBean3.setResDrawable(getMipmap(Integer.parseInt(luziInfoDetail[2])));
                diceContentBean.getList().add(diceBean1);
                diceContentBean.getList().add(diceBean2);
                diceContentBean.getList().add(diceBean3);
                list.add(diceContentBean);
            }
        } catch (Exception e) {

        }

        return list;
    }

    private void setValue(RecyclerView gv) {

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(22 * 50,
                ScreenUtil.dip2px(context, 85));
        gv.setLayoutParams(params);
        int count = 22;
        gv.setLayoutManager(new GridLayoutManager(context, (count % 2 == 0) ? count / 2 : count / 2 + 1));
    }

    List<BaccaratTableChangeViewBean> baccaratTableChangeViewBeenList;
    BaccaratTableChangeViewBean dragonTigerTableChangeViewBeen;
    RouletteTableChangeViewBean rouletteTableChangeViewBean;
    SicboTableChangeViewBean sicboTableChangeViewBean;

    private boolean isHaveThisTableId(String tableId) {
        for (int i = 0; i < list.size(); i++) {
            String type = list.get(i).getType();
            if (type.equals(tableId)) {
                return true;
            }
        }
        return false;
    }


    public void setTablesData(AppModel mAppViewModel, ArrayList<GameMenuItem> tables) {
        this.mAppViewModel = mAppViewModel;
        isNeedRefenshTimer = false;
        list.clear();
        hereList.clear();
        tableMaintenanceList.clear();
        this.tables = tables;
        parent.removeAllViews();
        LinearLayout parentLine = null;

        int i = 0;
        for (final GameMenuItem item : tables) {

            TextView textView = null;
            View aB1 = null;

            if (item.getDrawableRes() < 4 || item.getDrawableRes() > 60) {

                aB1 = LayoutInflater.from(context).inflate(R.layout.gd_layout_scrollview_h_table_brccarat, null);

                textView = (TextView) aB1.findViewById(R.id.gd__tv_table_title);
                GridLayout layout = (GridLayout) aB1.findViewById(R.id.gd__baccarat_gridlayout2);

                TextView tv_timer = (TextView) aB1.findViewById(R.id.gd__tv_timer);
                if (!isHaveThisTableId(item.getDrawableRes() + "")) {
                    list.add(new TableTimerBean(tv_timer, item.getDrawableRes() + ""));
                }
                TextView tv_baccarat_shoe_number = (TextView) aB1.findViewById(R.id.gd__text_shoe_game_number);

                TextView tv_baccarat_total_number = (TextView) aB1.findViewById(R.id.gd__text_total);
                TextView tv_total_name = (TextView) aB1.findViewById(R.id.gd__tv_total_name);
                tv_total_name.setVisibility(View.GONE);
                tv_baccarat_total_number.setVisibility(View.GONE);
                TextView tv_baccarat_banker_number = (TextView) aB1.findViewById(R.id.gd__text_banker);
                TextView tv_baccarat_player_number = (TextView) aB1.findViewById(R.id.gd__text_player);
                TextView tv_baccarat_tie_number = (TextView) aB1.findViewById(R.id.gd__text_tie);
                TextView tv_baccarat_bp_number = (TextView) aB1.findViewById(R.id.gd__text_bp);
                TextView tv_baccarat_pp_number = (TextView) aB1.findViewById(R.id.gd__text_pp);
                View ll_good_road_parent = aB1.findViewById(R.id.gd__ll_good_road_parent);
                View view_you_here = aB1.findViewById(R.id.gd__view_you_here);
                TextView tv_here = view_you_here.findViewById(R.id.gd__tv_here);
                hereList.add(tv_here);
                View table_close_bg = aB1.findViewById(R.id.gd_view_table_maintenance);
                tableMaintenanceList.add(new TableMaintenanceBean(item.getDrawableRes(), table_close_bg));
                if (mAppViewModel.getTableId() == item.getDrawableRes()) {
                    view_you_here.setVisibility(View.VISIBLE);
                }
                TextView tv_good_road_name = (TextView) ll_good_road_parent.findViewById(R.id.gd__tv_good_road_name);
                BaccaratTableChangeViewBean bean = new BaccaratTableChangeViewBean(item.getDrawableRes(), layout,
                        tv_baccarat_shoe_number, tv_baccarat_total_number, tv_baccarat_banker_number, tv_baccarat_player_number, tv_baccarat_tie_number
                        , tv_baccarat_bp_number, tv_baccarat_pp_number);
                bean.setLl_good_road_parent(ll_good_road_parent);
                bean.setTv_good_road_name(tv_good_road_name);
                bean.setView_Parent(aB1);
                baccaratTableChangeViewBeenList.add(bean);
            } else if (item.getDrawableRes() == 21) {
                aB1 = LayoutInflater.from(context).inflate(R.layout.gd_layout_scrollview_h_table_roultette, null);
                TextView tv_timer = (TextView) aB1.findViewById(R.id.gd__tv_timer);
                if (!isHaveThisTableId(item.getDrawableRes() + "")) {
                    list.add(new TableTimerBean(tv_timer, item.getDrawableRes() + ""));
                }
                RecyclerView layout = (RecyclerView) aB1.findViewById(R.id.gd__gridView1);
                textView = (TextView) aB1.findViewById(R.id.gd__tv_table_title);
                TextView tv_game_number01 = (TextView) aB1.findViewById(R.id.gd__text_shoe_game_number);
                TextView tv_roulette_red01 = (TextView) aB1.findViewById(R.id.gd__text_red);
                TextView tv_roulette_black01 = (TextView) aB1.findViewById(R.id.gd__text_black);
                TextView tv_roulette_zero01 = (TextView) aB1.findViewById(R.id.gd__text_zero);
                TextView tv_roulette_even01 = (TextView) aB1.findViewById(R.id.gd__text_even);
                TextView tv_roulette_odd01 = (TextView) aB1.findViewById(R.id.gd__text_odd);
                TextView tv_roulette_big01 = (TextView) aB1.findViewById(R.id.gd__text_big);
                TextView tv_roulette_small01 = (TextView) aB1.findViewById(R.id.gd__text_small);
                View view_you_here = aB1.findViewById(R.id.gd__view_you_here);
                TextView tv_here = view_you_here.findViewById(R.id.gd__tv_here);
                hereList.add(tv_here);
                View table_close_bg = aB1.findViewById(R.id.gd_view_table_maintenance);
                tableMaintenanceList.add(new TableMaintenanceBean(item.getDrawableRes(), table_close_bg));
                if (mAppViewModel.getTableId() == item.getDrawableRes()) {
                    view_you_here.setVisibility(View.VISIBLE);
                }
                setValue(layout);

                adapter = new BaseRecyclerAdapter<String>(context, new ArrayList<String>(), R.layout.gd_item_change_table_roulette_content) {
                    @Override
                    public void convert(MyRecyclerViewHolder helper, int position, String item) {
                        switch (item) {
                            case "2":
                            case "4":
                            case "6":
                            case "8":
                            case "10":
                            case "11":
                            case "13":
                            case "15":
                            case "17":
                            case "20":
                            case "22":
                            case "24":
                            case "26":
                            case "28":
                            case "29":
                            case "31":
                            case "33":
                            case "35":
                                helper.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_black_brown_stroke_yellow);
                                break;
                            case "0":
                                helper.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_green_brown_stroke_yellow);
                                break;
                            default:
                                helper.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_red_brown_stroke_yellow);
                                break;
                        }
                        helper.setText(R.id.gd__roulette_title_tv, item);

                    }
                };
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
                    @Override
                    public void onItemClick(View view, String bb, int position) {
                        cLickImp.itemCLick(v, item, 0);
                    }
                });
                layout.setAdapter(adapter);

                rouletteTableChangeViewBean = new RouletteTableChangeViewBean(tv_game_number01, tv_roulette_red01, tv_roulette_black01, tv_roulette_zero01, tv_roulette_even01, tv_roulette_odd01, tv_roulette_big01, tv_roulette_small01);
                rouletteTableChangeViewBean.setView_Parent(aB1);
            } else if (item.getDrawableRes() == 31) {
                aB1 = LayoutInflater.from(context).inflate(R.layout.gd_layout_scrollview_h_table_scibao, null);
                TextView tv_timer = (TextView) aB1.findViewById(R.id.gd__tv_timer);
                if (!isHaveThisTableId(item.getDrawableRes() + "")) {
                    list.add(new TableTimerBean(tv_timer, item.getDrawableRes() + ""));
                }
                textView = (TextView) aB1.findViewById(R.id.gd__tv_table_title);
                TextView tv_sicbo_number01 = (TextView) aB1.findViewById(R.id.gd__text_shoe_game_number);
                TextView tv_even01 = (TextView) aB1.findViewById(R.id.gd__text_even);
                TextView tv_odd01 = (TextView) aB1.findViewById(R.id.gd__text_odd);
                TextView tv_big01 = (TextView) aB1.findViewById(R.id.gd__text_big);
                TextView tv_small01 = (TextView) aB1.findViewById(R.id.gd__text_small);
                TextView tv_waidic01 = (TextView) aB1.findViewById(R.id.gd__text_waidic);
                LinearLayout linearlayout = (LinearLayout) aB1.findViewById(R.id.gd__layout2);
                View view_you_here = aB1.findViewById(R.id.gd__view_you_here);
                TextView tv_here = view_you_here.findViewById(R.id.gd__tv_here);
                hereList.add(tv_here);
                View table_close_bg = aB1.findViewById(R.id.gd_view_table_maintenance);
                tableMaintenanceList.add(new TableMaintenanceBean(item.getDrawableRes(), table_close_bg));
                if (mAppViewModel.getTableId() == item.getDrawableRes()) {
                    view_you_here.setVisibility(View.VISIBLE);
                }

                for (DiceContentBean dice : getSicboResultsData(mAppViewModel)) {
                    View diceView = LayoutInflater.from(context).inflate(R.layout.gd_item_table_bet_dice_info, null);
                    ((ImageView) diceView.findViewById(R.id.gd__iv_dice_1)).setImageResource(dice.getList().get(0).getResDrawable());
                    ((ImageView) diceView.findViewById(R.id.gd__iv_dice_2)).setImageResource(dice.getList().get(1).getResDrawable());
                    ((ImageView) diceView.findViewById(R.id.gd__iv_dice_3)).setImageResource(dice.getList().get(2).getResDrawable());
                    linearlayout.addView(diceView);
                }
                sicboTableChangeViewBean = new SicboTableChangeViewBean(linearlayout, tv_sicbo_number01, tv_even01, tv_small01, tv_waidic01, tv_big01, tv_odd01);
                sicboTableChangeViewBean.setView_Parent(aB1);
            } else if (item.getDrawableRes() == 5) {
                aB1 = LayoutInflater.from(context).inflate(R.layout.gd_layout_scrollview_h_table_brccarat, null);
                TextView tv_timer = (TextView) aB1.findViewById(R.id.gd__tv_timer);
                if (!isHaveThisTableId(item.getDrawableRes() + "")) {
                    list.add(new TableTimerBean(tv_timer, item.getDrawableRes() + ""));
                }
                textView = (TextView) aB1.findViewById(R.id.gd__tv_table_title);
                TextView tv_shoe = (TextView) aB1.findViewById(R.id.gd__text_shoe_game_number);

                TextView tv_total_name = (TextView) aB1.findViewById(R.id.gd__tv_total_name);
                TextView tv_total = (TextView) aB1.findViewById(R.id.gd__text_total);
                tv_total_name.setVisibility(View.GONE);
                tv_total.setVisibility(View.GONE);
                TextView text_banker = (TextView) aB1.findViewById(R.id.gd__text_banker);
                TextView text_player = (TextView) aB1.findViewById(R.id.gd__text_player);
                TextView tv_banker = (TextView) aB1.findViewById(R.id.gd__tv_banker);
                tv_banker.setText(context.getString(R.string.dr));
                TextView tv_player = (TextView) aB1.findViewById(R.id.gd__tv_player);
                tv_player.setText(context.getString(R.string.ti));
                TextView tv_tie = (TextView) aB1.findViewById(R.id.gd__text_tie);
                TextView tv_bp = (TextView) aB1.findViewById(R.id.gd__text_bp);
                TextView tv_pp = (TextView) aB1.findViewById(R.id.gd__text_pp);
                LinearLayout ll_pp = (LinearLayout) aB1.findViewById(R.id.gd__ll_pp);
                ll_pp.setVisibility(View.GONE);
                LinearLayout ll_bp = (LinearLayout) aB1.findViewById(R.id.gd__ll_bp);
                ll_bp.setVisibility(View.GONE);
                View view_you_here = aB1.findViewById(R.id.gd__view_you_here);
                TextView tv_here = view_you_here.findViewById(R.id.gd__tv_here);
                hereList.add(tv_here);
                View table_close_bg = aB1.findViewById(R.id.gd_view_table_maintenance);
                tableMaintenanceList.add(new TableMaintenanceBean(item.getDrawableRes(), table_close_bg));
                if (mAppViewModel.getTableId() == item.getDrawableRes()) {
                    view_you_here.setVisibility(View.VISIBLE);
                }
                GridLayout layout = (GridLayout) aB1.findViewById(R.id.gd__baccarat_gridlayout2);
                dragonTigerTableChangeViewBeen = new BaccaratTableChangeViewBean(item.getDrawableRes(), layout, tv_shoe, tv_total, text_banker, text_player, tv_tie, tv_bp, tv_pp);
                dragonTigerTableChangeViewBeen.setView_Parent(aB1);
            }
            textView.setText(item.getTitle());
            View v = aB1.findViewById(R.id.gd__layout2);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cLickImp.itemCLick(v, item, 0);
                }
            });
            aB1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cLickImp.itemCLick(v, item, 0);
                }
            });
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                if (i % 2 == 0) {
                    parentLine = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.gd_include_linearlayout, null);
                }
                parentLine.addView(aB1, layoutParams);
//                if (i == tables.size() - 1) {
//                    parentLine.addView(new View(context), layoutParams);
//                }
                if (i % 2 == 1 || i == tables.size() - 1) {
                    parent.addView(parentLine);
                }
            } else {
                parentLine = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.gd_include_linearlayout, null);
                parentLine.addView(aB1, layoutParams);
                parent.addView(parentLine);
            }
            i++;
        }
    }
}