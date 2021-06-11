package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.DragonTigerActivity;
import gaming178.com.casinogame.Activity.RouletteActivity;
import gaming178.com.casinogame.Activity.SicboActivity;
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetBean;
import gaming178.com.casinogame.Activity.entity.BaccaratTableBetContentBean;
import gaming178.com.casinogame.Activity.entity.BaccaratTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.DiceBean;
import gaming178.com.casinogame.Activity.entity.DiceContentBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableBetBean;
import gaming178.com.casinogame.Activity.entity.DragonTigerTableContentBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableBetBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.RouletteTableContentBean;
import gaming178.com.casinogame.Activity.entity.SicboTableBetBean;
import gaming178.com.casinogame.Activity.entity.SicboTableChangeViewBean;
import gaming178.com.casinogame.Activity.entity.SicboTableContentBean;
import gaming178.com.casinogame.Activity.entity.TableTimerBean;
import gaming178.com.casinogame.Bean.ChipBean;
import gaming178.com.casinogame.Bean.GameMenuItem;
import gaming178.com.casinogame.Bean.TableMaintenanceBean;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.AppModel;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.BitmapTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.ItemCLickImp;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
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
    private AdapterView lvChips;
    List<TextView> hereList = new ArrayList<>();
    List<TableMaintenanceBean> tableMaintenanceList = new ArrayList<>();
    private AppModel mAppViewModel;
    private int chooseChip;
    Map<Boolean, Integer> selectedMap = new HashMap<>();

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
        baccaratBetContentList = new ArrayList<>();
        baccaratTableBetBeanList = new ArrayList<>();
        dragonTigerTableBetBean = new DragonTigerTableBetBean();
        dragonTigerTableContentBean = new DragonTigerTableContentBean();
        sicboTableBetBean = new SicboTableBetBean();
        sicboTableContentBean = new SicboTableContentBean();
        rouletteTableBetBean = new RouletteTableBetBean();
        rouletteTableContentBean = new RouletteTableContentBean();
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
        lvChips = view.findViewById(R.id.gd_lv_chips);
        setChip();
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

    public void setPopTopContent(int chooseChip) {
//        this.chooseChip = chooseChip;
        for (int i = 0; i < hereList.size(); i++) {
            hereList.get(i).setText(context.getString(R.string.your_here));
        }
        refreshTimer((BaseActivity) context);
        initAllGame();
    }

    private void refreshTimer(final BaseActivity baseActivity) {
        isNeedRefenshTimer = true;
        new Thread() {
            @Override
            public void run() {
                while (isNeedRefenshTimer) {
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
                    baseActivity.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            updateGame();
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
                    try {
                        Thread.sleep(900);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void initAllGame() {
        for (int i = 0; i < baccaratBetContentList.size(); i++) {
            BaccaratTableBetContentBean contentBean = baccaratBetContentList.get(i);
            initBaccaratGame(contentBean);
        }
        initDragonTigerGame();
        initSicboGame();
        initRouletteGame();
    }

    private void initBaccaratGame(BaccaratTableBetContentBean contentBean) {
        contentBean.setCanBet(false);
        clearBaccaratResultView(contentBean.getTableId());
        contentBean.getContentView().setVisibility(View.GONE);
        contentBean.getFlTablePlayer().removeAllViews();
        contentBean.getFlTableBanker().removeAllViews();
        contentBean.getFlTableTie().removeAllViews();
        contentBean.getFlTablePP().removeAllViews();
        contentBean.getFlTableBP().removeAllViews();
        contentBean.setBaccaratGameNumber("");
        contentBean.getTvTableNumber().setText("");
        contentBean.setBaccaratOpenPoker(true);
        contentBean.setBaccaratGetResult(true);
        for (int i = 0; i < baccaratTableBetBeanList.size(); i++) {
            int tableId = baccaratTableBetBeanList.get(i).getTableId();
            if (tableId == contentBean.getTableId()) {
                BaccaratTableBetBean bean = baccaratTableBetBeanList.get(i);
                bean.setPlayerCurrentBet(0);
                bean.setPlayerAlreadyBet(0);
                bean.setPlayerRepeatBet(0);
                bean.setBankerCurrentBet(0);
                bean.setBankerAlreadyBet(0);
                bean.setBankerRepeatBet(0);
                bean.setTieCurrentBet(0);
                bean.setTieAlreadyBet(0);
                bean.setTieRepeatBet(0);
                bean.setPpCurrentBet(0);
                bean.setPpAlreadyBet(0);
                bean.setPpRepeatBet(0);
                bean.setBpCurrentBet(0);
                bean.setBpAlreadyBet(0);
                bean.setBpRepeatBet(0);
                break;
            }
        }
    }

    private void initDragonTigerGame() {
        dragonTigerTableContentBean.getTvTableNumber().setText("");
        dragonTigerTableContentBean.setCanBet(false);
        clearDragonTigerResultView();
        dragonTigerTableContentBean.getContentView().setVisibility(View.GONE);
        dragonTigerTableContentBean.getFlTableDragon().removeAllViews();
        dragonTigerTableContentBean.getFlTableTiger().removeAllViews();
        dragonTigerTableContentBean.getFlTableTie().removeAllViews();
        dragonTigerTableContentBean.setDragonTigerGameNumber("");
        dragonTigerTableContentBean.setDragonTigerOpenPoker(true);
        dragonTigerTableContentBean.setDragonTigerGetResult(true);
    }

    private void initSicboGame() {
        sicboTableContentBean.getTvTableNumber().setText("");
        sicboTableContentBean.setCanBet(false);
        sicboTableContentBean.getFlResult().setVisibility(View.GONE);
        sicboTableContentBean.getContentView().setVisibility(View.INVISIBLE);
        sicboTableContentBean.getFlBig().removeAllViews();
        sicboTableContentBean.getFlAny().removeAllViews();
        sicboTableContentBean.getFlSmall().removeAllViews();
        sicboTableContentBean.getFlSingle1().removeAllViews();
        sicboTableContentBean.getFlSingle2().removeAllViews();
        sicboTableContentBean.getFlSingle3().removeAllViews();
        sicboTableContentBean.getFlSingle4().removeAllViews();
        sicboTableContentBean.getFlSingle5().removeAllViews();
        sicboTableContentBean.getFlSingle6().removeAllViews();
        sicboTableContentBean.getFlBetButton().removeAllViews();
        sicboTableContentBean.setSicboGameNumber("");
        sicboTableContentBean.setSicboOpenResult(true);
        sicboTableContentBean.setSicboGetResult(true);
        for (int i = 0; i < sicboTableContentBean.getAnimationList().size(); i++) {
            sicboTableContentBean.getAnimationList().get(i).stop();
            sicboTableContentBean.getAnimationList().get(i).selectDrawable(0);
        }
    }

    private void initRouletteGame() {
        rouletteTableContentBean.getTvTableNumber().setText("");
        rouletteTableContentBean.setCanBet(false);
        rouletteTableContentBean.getFlResult().setVisibility(View.GONE);
        rouletteTableContentBean.getContentView().setVisibility(View.GONE);
        rouletteTableContentBean.getFlEven().removeAllViews();
        rouletteTableContentBean.getFlZero().removeAllViews();
        rouletteTableContentBean.getFlOdd().removeAllViews();
        rouletteTableContentBean.getFlSingle1_12().removeAllViews();
        rouletteTableContentBean.getFlSingle13_24().removeAllViews();
        rouletteTableContentBean.getFlSingle25_36().removeAllViews();
        rouletteTableContentBean.getFlSingle1_18().removeAllViews();
        rouletteTableContentBean.getFlSingle19_36().removeAllViews();
        rouletteTableContentBean.getFlRed().removeAllViews();
        rouletteTableContentBean.getFlBlack().removeAllViews();
        rouletteTableContentBean.getFlBetButton().removeAllViews();
        rouletteTableContentBean.setRouletteGameNumber("");
        rouletteTableContentBean.setRouletteOpenResult(true);
        rouletteTableContentBean.setRouletteGetResult(true);
        for (int i = 0; i < rouletteTableContentBean.getAnimationList().size(); i++) {
            rouletteTableContentBean.getAnimationList().get(i).stop();
            rouletteTableContentBean.getAnimationList().get(i).selectDrawable(0);
        }
    }

    @Override
    protected void onCloose() {
        super.onCloose();
        isNeedRefenshTimer = false;
        mAppViewModel.setOpenChangeTable(false);
        mAppViewModel.setClickBaccarat1(false);
        mAppViewModel.setClickBaccarat2(false);
        mAppViewModel.setClickBaccarat3(false);
        mAppViewModel.setClickBaccarat5(false);
        mAppViewModel.setClickBaccarat6(false);
        mAppViewModel.setClickBaccarat7(false);
        mAppViewModel.setClickBaccaratMi(false);
        mAppViewModel.setClickDragonTiger(false);
        mAppViewModel.setClickRoulette(false);
        mAppViewModel.setClickSicbo(false);
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
                initBaccaratContent(aB1, item.getDrawableRes());
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
                initRouletteContent(aB1, item.getDrawableRes());
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
                initSicboGameContent(aB1, item.getDrawableRes());
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
                initDragonTigerContent(aB1, item.getDrawableRes());
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

    private void initRouletteContent(View view, int tableId) {
        rouletteTableBetBean.setTableId(tableId);
        rouletteTableContentBean.setTableId(tableId);
        View betContent = view.findViewById(R.id.gd_roulette_bet_table_change);
        rouletteTableContentBean.setContentView(betContent);
        rouletteTableContentBean.setTvTableNumber(view.findViewById(R.id.tv_table_number));
        rouletteTableContentBean.setTvBetHint(view.findViewById(R.id.tv_table_bet_hint));
        FrameLayout flEven = view.findViewById(R.id.fl_even);
        FrameLayout flZero = view.findViewById(R.id.fl_zero);
        FrameLayout flOdd = view.findViewById(R.id.fl_odd);
        ImageView imgEven = view.findViewById(R.id.img_even);
        ImageView imgZero = view.findViewById(R.id.img_even);
        ImageView imgOdd = view.findViewById(R.id.img_even);
        ImageView imgSingle1_12 = view.findViewById(R.id.img_1_12);
        ImageView imgSingle13_24 = view.findViewById(R.id.img_13_24);
        ImageView imgSingle25_36 = view.findViewById(R.id.img_25_36);
        ImageView imgSingle1_18 = view.findViewById(R.id.img_1_18);
        ImageView imgSingle19_36 = view.findViewById(R.id.img_19_36);
        ImageView imgSingleRed = view.findViewById(R.id.img_red);
        ImageView imgSingleBlack = view.findViewById(R.id.img_black);
        AnimationDrawable animationEven = (AnimationDrawable) imgEven.getBackground();
        AnimationDrawable animationZero = (AnimationDrawable) imgZero.getBackground();
        AnimationDrawable animationOdd = (AnimationDrawable) imgOdd.getBackground();
        AnimationDrawable animation1_12 = (AnimationDrawable) imgSingle1_12.getBackground();
        AnimationDrawable animation13_24 = (AnimationDrawable) imgSingle13_24.getBackground();
        AnimationDrawable animation25_36 = (AnimationDrawable) imgSingle25_36.getBackground();
        AnimationDrawable animation1_18 = (AnimationDrawable) imgSingle1_18.getBackground();
        AnimationDrawable animation19_36 = (AnimationDrawable) imgSingle19_36.getBackground();
        AnimationDrawable animationRed = (AnimationDrawable) imgSingleRed.getBackground();
        AnimationDrawable animationBlack = (AnimationDrawable) imgSingleBlack.getBackground();
        rouletteTableContentBean.setAnimationEven(animationEven);
        rouletteTableContentBean.setAnimationZero(animationZero);
        rouletteTableContentBean.setAnimationOdd(animationOdd);
        rouletteTableContentBean.setAnimation1_12(animation1_12);
        rouletteTableContentBean.setAnimation13_24(animation13_24);
        rouletteTableContentBean.setAnimation25_36(animation25_36);
        rouletteTableContentBean.setAnimation1_18(animation1_18);
        rouletteTableContentBean.setAnimation19_36(animation19_36);
        rouletteTableContentBean.setAnimationRed(animationRed);
        rouletteTableContentBean.setAnimationBlack(animationBlack);
        rouletteTableContentBean.getAnimationList().add(animationEven);
        rouletteTableContentBean.getAnimationList().add(animationZero);
        rouletteTableContentBean.getAnimationList().add(animationOdd);
        rouletteTableContentBean.getAnimationList().add(animation1_12);
        rouletteTableContentBean.getAnimationList().add(animation13_24);
        rouletteTableContentBean.getAnimationList().add(animation25_36);
        rouletteTableContentBean.getAnimationList().add(animation1_18);
        rouletteTableContentBean.getAnimationList().add(animation19_36);
        rouletteTableContentBean.getAnimationList().add(animationRed);
        rouletteTableContentBean.getAnimationList().add(animationBlack);
        flEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getOddCurrentBet() > 0 || rouletteTableBetBean.getOddAlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_odd_even), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "Even", false);
                }
            }
        });
        flZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "0", false);
                }
            }
        });
        flOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getEvenCurrentBet() > 0 || rouletteTableBetBean.getEvenAlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_odd_even), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "Odd", false);
                }
            }
        });
        imgSingle1_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((rouletteTableBetBean.getSingle13_24CurrentBet() > 0 || rouletteTableBetBean.getSingle13_24AlreadyBet() > 0) && (rouletteTableBetBean.getSingle25_36CurrentBet() > 0 || rouletteTableBetBean.getSingle25_36AlreadyBet() > 0)) {
                    Toast.makeText(context, context.getString(R.string.show_limit_column), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "1_12", false);
                }
            }
        });
        imgSingle13_24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((rouletteTableBetBean.getSingle1_12CurrentBet() > 0 || rouletteTableBetBean.getSingle1_12AlreadyBet() > 0) && (rouletteTableBetBean.getSingle25_36CurrentBet() > 0 || rouletteTableBetBean.getSingle25_36AlreadyBet() > 0)) {
                    Toast.makeText(context, context.getString(R.string.show_limit_column), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "13_24", false);
                }
            }
        });
        imgSingle25_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if ((rouletteTableBetBean.getSingle1_12CurrentBet() > 0 || rouletteTableBetBean.getSingle1_12AlreadyBet() > 0) && (rouletteTableBetBean.getSingle13_24CurrentBet() > 0 || rouletteTableBetBean.getSingle13_24AlreadyBet() > 0)) {
                    Toast.makeText(context, context.getString(R.string.show_limit_column), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "25_36", false);
                }
            }
        });
        imgSingle1_18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getSingle19_36CurrentBet() > 0 || rouletteTableBetBean.getSingle19_36AlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_big_small), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "1_18", false);
                }
            }
        });
        imgSingle19_36.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getSingle1_18CurrentBet() > 0 || rouletteTableBetBean.getSingle1_18AlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_big_small), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "19_36", false);
                }
            }
        });
        imgSingleRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getBlackCurrentBet() > 0 || rouletteTableBetBean.getBlackAlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_red_black), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "Red", false);
                }
            }
        });
        imgSingleBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableBetBean.getRedCurrentBet() > 0 || rouletteTableBetBean.getRedAlreadyBet() > 0) {
                    Toast.makeText(context, context.getString(R.string.show_limit_red_black), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (rouletteTableContentBean.isCanBet() && mAppViewModel.getRoulette01().getGameStatus() == 1) {
                    TableBetUtils.RouletteBet(tableId, rouletteTableBetBean, rouletteTableContentBean, mAppViewModel, context, chooseChip, "Black", false);
                }
            }
        });
        rouletteTableContentBean.setFlSingle1_12(view.findViewById(R.id.fl_1_12));
        rouletteTableContentBean.setFlSingle13_24(view.findViewById(R.id.fl_13_24));
        rouletteTableContentBean.setFlSingle25_36(view.findViewById(R.id.fl_25_36));
        rouletteTableContentBean.setFlSingle1_18(view.findViewById(R.id.fl_1_18));
        rouletteTableContentBean.setFlSingle19_36(view.findViewById(R.id.fl_19_36));
        rouletteTableContentBean.setFlRed(view.findViewById(R.id.fl_red));
        rouletteTableContentBean.setFlBlack(view.findViewById(R.id.fl_black));
        rouletteTableContentBean.setFlEven(flEven);
        rouletteTableContentBean.setFlZero(flZero);
        rouletteTableContentBean.setFlOdd(flOdd);
        rouletteTableContentBean.setImgSingle1_12(imgSingle1_12);
        rouletteTableContentBean.setImgSingle13_24(imgSingle13_24);
        rouletteTableContentBean.setImgSingle25_36(imgSingle25_36);
        rouletteTableContentBean.setImgSingle1_18(imgSingle1_18);
        rouletteTableContentBean.setImgSingle19_36(imgSingle19_36);
        rouletteTableContentBean.setImgSingleRed(imgSingleRed);
        rouletteTableContentBean.setImgSingleBlack(imgSingleBlack);
        rouletteTableContentBean.setFlBetButton(view.findViewById(R.id.fl_bet_button));
        rouletteTableContentBean.setFlResult(view.findViewById(R.id.fl_result));
        rouletteTableContentBean.setTvNumber(view.findViewById(R.id.gd__tv_pop_number));
        rouletteTableContentBean.setTvRedBlack(view.findViewById(R.id.gd__tv_red_black));
        rouletteTableContentBean.setTvOddEven(view.findViewById(R.id.gd__tv_odd_even));
        ImageView imgCloseBet = view.findViewById(R.id.gd_img_close_bet);
        imgCloseBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initRouletteGame();
                mAppViewModel.setClickRoulette(false);
            }
        });
        ImageView imgTable = view.findViewById(R.id.img_table);
        imgTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherGame(tableId);
            }
        });
    }

    private void initSicboGameContent(View view, int tableId) {
        sicboTableBetBean.setTableId(tableId);
        sicboTableContentBean.setTableId(tableId);
        View betContent = view.findViewById(R.id.gd_sicbo_bet_table_change);
        sicboTableContentBean.setContentView(betContent);
        sicboTableContentBean.setTvTableNumber(view.findViewById(R.id.tv_table_number));
        sicboTableContentBean.setTvBetHint(view.findViewById(R.id.tv_table_bet_hint));
        FrameLayout flBig = view.findViewById(R.id.fl_big);
        FrameLayout flAny = view.findViewById(R.id.fl_any);
        FrameLayout flSmall = view.findViewById(R.id.fl_small);
        ImageView imgBig = view.findViewById(R.id.img_big);
        ImageView imgAny = view.findViewById(R.id.img_any);
        ImageView imgSmall = view.findViewById(R.id.img_small);
        ImageView imgSingle6 = view.findViewById(R.id.img_single_6);
        ImageView imgSingle5 = view.findViewById(R.id.img_single_5);
        ImageView imgSingle4 = view.findViewById(R.id.img_single_4);
        ImageView imgSingle3 = view.findViewById(R.id.img_single_3);
        ImageView imgSingle2 = view.findViewById(R.id.img_single_2);
        ImageView imgSingle1 = view.findViewById(R.id.img_single_1);
        sicboTableContentBean.setImgBig(imgBig);
        sicboTableContentBean.setImgAny(imgAny);
        sicboTableContentBean.setImgSmall(imgSmall);
        AnimationDrawable AnimationBig = (AnimationDrawable) imgBig.getBackground();
        AnimationDrawable AnimationAny = (AnimationDrawable) imgAny.getBackground();
        AnimationDrawable AnimationSmall = (AnimationDrawable) imgSmall.getBackground();
        AnimationDrawable Animation1 = (AnimationDrawable) imgSingle1.getBackground();
        AnimationDrawable Animation2 = (AnimationDrawable) imgSingle2.getBackground();
        AnimationDrawable Animation3 = (AnimationDrawable) imgSingle3.getBackground();
        AnimationDrawable Animation4 = (AnimationDrawable) imgSingle4.getBackground();
        AnimationDrawable Animation5 = (AnimationDrawable) imgSingle5.getBackground();
        AnimationDrawable Animation6 = (AnimationDrawable) imgSingle6.getBackground();
        sicboTableContentBean.setAnimationBig(AnimationBig);
        sicboTableContentBean.setAnimationAny(AnimationAny);
        sicboTableContentBean.setAnimationSmall(AnimationSmall);
        sicboTableContentBean.setAnimation1(Animation1);
        sicboTableContentBean.setAnimation2(Animation2);
        sicboTableContentBean.setAnimation3(Animation3);
        sicboTableContentBean.setAnimation4(Animation4);
        sicboTableContentBean.setAnimation5(Animation5);
        sicboTableContentBean.setAnimation6(Animation6);
        sicboTableContentBean.getAnimationList().add(AnimationBig);
        sicboTableContentBean.getAnimationList().add(AnimationAny);
        sicboTableContentBean.getAnimationList().add(AnimationSmall);
        sicboTableContentBean.getAnimationList().add(Animation1);
        sicboTableContentBean.getAnimationList().add(Animation2);
        sicboTableContentBean.getAnimationList().add(Animation3);
        sicboTableContentBean.getAnimationList().add(Animation4);
        sicboTableContentBean.getAnimationList().add(Animation5);
        sicboTableContentBean.getAnimationList().add(Animation6);
        flBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableBetBean.getSmallCurrentBet() > 0 || sicboTableBetBean.getSmallAlreadyBet() > 0) {
                    Toast.makeText(context, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "B", false);
                }
            }
        });
        flAny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "A", false);
                }
            }
        });
        flSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableBetBean.getBigCurrentBet() > 0 || sicboTableBetBean.getBigAlreadyBet() > 0) {
                    Toast.makeText(context, R.string.show_limit_big_small, Toast.LENGTH_LONG).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "S", false);
                }
            }
        });
        imgSingle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "6", false);
                }
            }
        });
        imgSingle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "5", false);
                }
            }
        });
        imgSingle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "4", false);
                }
            }
        });
        imgSingle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "3", false);
                }
            }
        });
        imgSingle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "2", false);
                }
            }
        });
        imgSingle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chooseChip < 1) {
                    Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sicboTableContentBean.isCanBet() && mAppViewModel.getSicbo01().getGameStatus() == 1) {
                    TableBetUtils.sicboBet(tableId, sicboTableBetBean, sicboTableContentBean, mAppViewModel, context, chooseChip, "1", false);
                }
            }
        });
        FrameLayout flSingle6 = view.findViewById(R.id.fl_single_6);
        flSingle6.setTag("Top");
        FrameLayout flSingle5 = view.findViewById(R.id.fl_single_5);
        flSingle5.setTag("Top");
        FrameLayout flSingle4 = view.findViewById(R.id.fl_single_4);
        flSingle4.setTag("Top");
        FrameLayout flSingle3 = view.findViewById(R.id.fl_single_3);
        flSingle3.setTag("Top");
        FrameLayout flSingle2 = view.findViewById(R.id.fl_single_2);
        flSingle2.setTag("Top");
        FrameLayout flSingle1 = view.findViewById(R.id.fl_single_1);
        flSingle1.setTag("Top");
        sicboTableContentBean.setFlSingle6(flSingle6);
        sicboTableContentBean.setFlSingle5(flSingle5);
        sicboTableContentBean.setFlSingle4(flSingle4);
        sicboTableContentBean.setFlSingle3(flSingle3);
        sicboTableContentBean.setFlSingle2(flSingle2);
        sicboTableContentBean.setFlSingle1(flSingle1);
        sicboTableContentBean.setFlBig(flBig);
        sicboTableContentBean.setFlAny(flAny);
        sicboTableContentBean.setFlSmall(flSmall);
        sicboTableContentBean.setImgSingle6(imgSingle6);
        sicboTableContentBean.setImgSingle5(imgSingle5);
        sicboTableContentBean.setImgSingle4(imgSingle4);
        sicboTableContentBean.setImgSingle3(imgSingle3);
        sicboTableContentBean.setImgSingle2(imgSingle2);
        sicboTableContentBean.setImgSingle1(imgSingle1);
        sicboTableContentBean.setFlBetButton(view.findViewById(R.id.fl_bet_button));
        sicboTableContentBean.setFlResult(view.findViewById(R.id.fl_result));
        sicboTableContentBean.setImgResult1(view.findViewById(R.id.gd__iv_dice_1));
        sicboTableContentBean.setImgResult2(view.findViewById(R.id.gd__iv_dice_2));
        sicboTableContentBean.setImgResult3(view.findViewById(R.id.gd__iv_dice_3));
        sicboTableContentBean.setTvResultPoint(view.findViewById(R.id.gd__iv_dice_tv));
        sicboTableContentBean.setTvResultBigSmall(view.findViewById(R.id.gd__tv_big_small));
        sicboTableContentBean.setTvResultOddEven(view.findViewById(R.id.gd__tv_odd_even));
        ImageView imgCloseBet = view.findViewById(R.id.gd_img_close_bet);
        imgCloseBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initSicboGame();
                mAppViewModel.setClickSicbo(false);
            }
        });
        ImageView imgTable = view.findViewById(R.id.img_table);
        imgTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherGame(tableId);
            }
        });
    }

    private void initDragonTigerContent(View view, int tableId) {
        dragonTigerTableBetBean.setTableId(tableId);
        dragonTigerTableContentBean.setTableId(tableId);
        View betContent = view.findViewById(R.id.gd_dragon_tiger_bet_table_change);
        dragonTigerTableContentBean.setContentView(betContent);
        dragonTigerTableContentBean.setTvTableNumber(view.findViewById(R.id.tv_table_number));
        dragonTigerTableContentBean.setTvBetHint(view.findViewById(R.id.tv_table_bet_hint));
        dragonTigerTableContentBean.setTvDragonPoint(view.findViewById(R.id.tv_dragon));
        dragonTigerTableContentBean.setTvTigerPoint(view.findViewById(R.id.tv_tiger));
        ImageView imgCloseBet = view.findViewById(R.id.gd_img_close_bet);
        dragonTigerTableContentBean.setLlResult(view.findViewById(R.id.ll_dragon_tiger_result));
        dragonTigerTableContentBean.setImgDragon(view.findViewById(R.id.img_tiger_poker_1));
        dragonTigerTableContentBean.setImgTiger(view.findViewById(R.id.img_dragon_poker_1));
        dragonTigerTableContentBean.setLlDragonParent(view.findViewById(R.id.ll_dragon_parent));
        dragonTigerTableContentBean.setLlTigerParent(view.findViewById(R.id.ll_tiger_parent));
        FrameLayout flTableDragon = view.findViewById(R.id.fl_dragon);
        flTableDragon.setTag("DT");
        dragonTigerTableContentBean.setFlTableDragon(flTableDragon);
        flTableDragon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (dragonTigerTableBetBean.getTigerCurrentBet() > 0 || dragonTigerTableBetBean.getTigerAlreadyBet() > 0) {
                        Toast.makeText(context, context.getString(R.string.show_limit_dragon_tiger), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (dragonTigerTableContentBean.isCanBet() && mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                        TableBetUtils.dragonTigerBet(tableId, dragonTigerTableBetBean, dragonTigerTableContentBean, mAppViewModel, context, chooseChip, "D", false);
                    }
                }
            }
        });
        FrameLayout flTableTie = view.findViewById(R.id.fl_dragon_tiger_tie);
        flTableTie.setTag("DT");
        dragonTigerTableContentBean.setFlTableTie(flTableTie);
        flTableTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (dragonTigerTableContentBean.isCanBet() && mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                        TableBetUtils.dragonTigerBet(tableId, dragonTigerTableBetBean, dragonTigerTableContentBean, mAppViewModel, context, chooseChip, "Tie", false);
                    }
                }
            }
        });
        FrameLayout flTableTiger = view.findViewById(R.id.fl_tiger);
        flTableTiger.setTag("DT");
        dragonTigerTableContentBean.setFlTableTiger(flTableTiger);
        flTableTiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (dragonTigerTableBetBean.getDragonCurrentBet() > 0 || dragonTigerTableBetBean.getDragonAlreadyBet() > 0) {
                        Toast.makeText(context, context.getString(R.string.show_limit_dragon_tiger), Toast.LENGTH_LONG).show();
                        return;
                    }
                    if (dragonTigerTableContentBean.isCanBet() && mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
                        TableBetUtils.dragonTigerBet(tableId, dragonTigerTableBetBean, dragonTigerTableContentBean, mAppViewModel, context, chooseChip, "T", false);
                    }
                }
            }
        });

        imgCloseBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDragonTigerGame();
                mAppViewModel.setClickDragonTiger(false);
            }
        });
        ImageView imgTable = view.findViewById(R.id.img_table);
        imgTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherGame(tableId);
            }
        });
    }

    private List<BaccaratTableBetContentBean> baccaratBetContentList;
    private List<BaccaratTableBetBean> baccaratTableBetBeanList;
    private DragonTigerTableBetBean dragonTigerTableBetBean;
    private DragonTigerTableContentBean dragonTigerTableContentBean;
    private SicboTableBetBean sicboTableBetBean;
    private SicboTableContentBean sicboTableContentBean;
    private RouletteTableBetBean rouletteTableBetBean;
    private RouletteTableContentBean rouletteTableContentBean;

    public BaccaratTableBetContentBean getBaccaratBetContentBean(int tableId) {
        BaccaratTableBetContentBean contentBean = null;
        for (int i = 0; i < baccaratBetContentList.size(); i++) {
            BaccaratTableBetContentBean bean = baccaratBetContentList.get(i);
            int id = bean.getTableId();
            if (tableId == id) {
                contentBean = bean;
                break;
            }
        }
        return contentBean;
    }

    public DragonTigerTableContentBean getDragonTigerContentBean() {
        return dragonTigerTableContentBean;
    }

    public SicboTableContentBean getSicboContentBean() {
        return sicboTableContentBean;
    }

    public RouletteTableContentBean getRouletteContentBean() {
        return rouletteTableContentBean;
    }

    public BaccaratTableBetBean getBaccaratBetBean(int tableId) {
        BaccaratTableBetBean contentBean = null;
        for (int i = 0; i < baccaratTableBetBeanList.size(); i++) {
            BaccaratTableBetBean bean = baccaratTableBetBeanList.get(i);
            int id = bean.getTableId();
            if (tableId == id) {
                contentBean = bean;
                break;
            }
        }
        return contentBean;
    }

    private void initBaccaratContent(View view, int tableId) {
        BaccaratTableBetBean baccaratTableBetBean = new BaccaratTableBetBean();
        baccaratTableBetBean.setTableId(tableId);
        baccaratTableBetBeanList.add(baccaratTableBetBean);
        BaccaratTableBetContentBean contentBean = new BaccaratTableBetContentBean();
        contentBean.setTableId(tableId);
        View betContent = view.findViewById(R.id.gd_baccarat_bet_table_change);
        contentBean.setTvTableNumber(view.findViewById(R.id.tv_table_number));
        contentBean.setTvBetHint(view.findViewById(R.id.tv_table_bet_hint));
        contentBean.setContentView(betContent);
        contentBean.setTvPlayerPoint(view.findViewById(R.id.tv_player));
        contentBean.setTvBankerPoint(view.findViewById(R.id.tv_banker));
        ImageView imgCloseBet = view.findViewById(R.id.gd_img_close_bet);
        contentBean.setLlResult(view.findViewById(R.id.ll_result));
        contentBean.setImgPlayer1(view.findViewById(R.id.img_player_1));
        contentBean.setImgPlayer2(view.findViewById(R.id.img_player_2));
        contentBean.setImgPlayer3(view.findViewById(R.id.img_player_3));
        contentBean.setImgBanker1(view.findViewById(R.id.img_banker_1));
        contentBean.setImgBanker2(view.findViewById(R.id.img_banker_2));
        contentBean.setImgBanker3(view.findViewById(R.id.img_banker_3));
        contentBean.setLlPlayerParent(view.findViewById(R.id.ll_player_parent));
        contentBean.setLlBankerParent(view.findViewById(R.id.ll_banker_parent));
        FrameLayout flTablePlayer = view.findViewById(R.id.fl_table_player_parent);
        contentBean.setFlTablePlayer(flTablePlayer);
        flTablePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentBean.isCanBet() && mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TableBetUtils.baccaratBet(tableId, baccaratTableBetBean, getBaccaratBetContentBean(tableId), mAppViewModel, context, chooseChip, "P", false);
                }

            }
        });

        FrameLayout flTableBanker = view.findViewById(R.id.fl_table_banker_parent);
        contentBean.setFlTableBanker(flTableBanker);
        flTableBanker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentBean.isCanBet() && mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TableBetUtils.baccaratBet(tableId, baccaratTableBetBean, getBaccaratBetContentBean(tableId), mAppViewModel, context, chooseChip, "B", false);
                }
            }
        });

        FrameLayout flTableTie = view.findViewById(R.id.fl_table_tie_parent);
        contentBean.setFlTableTie(flTableTie);
        flTableTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentBean.isCanBet() && mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TableBetUtils.baccaratBet(tableId, baccaratTableBetBean, getBaccaratBetContentBean(tableId), mAppViewModel, context, chooseChip, "T", false);
                }

            }
        });

        FrameLayout flTablePP = view.findViewById(R.id.fl_table_pp_parent);
        contentBean.setFlTablePP(flTablePP);
        flTablePP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentBean.isCanBet() && mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TableBetUtils.baccaratBet(tableId, baccaratTableBetBean, getBaccaratBetContentBean(tableId), mAppViewModel, context, chooseChip, "PP", false);
                }

            }
        });

        FrameLayout flTableBP = view.findViewById(R.id.fl_table_bp_parent);
        contentBean.setFlTableBP(flTableBP);
        flTableBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (contentBean.isCanBet() && mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
                    if (chooseChip < 1) {
                        Toast.makeText(context, context.getString(R.string.please_select_chips), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    TableBetUtils.baccaratBet(tableId, baccaratTableBetBean, getBaccaratBetContentBean(tableId), mAppViewModel, context, chooseChip, "BP", false);
                }

            }
        });


        imgCloseBet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBaccaratGame(getBaccaratBetContentBean(tableId));
                switch (tableId) {
                    case 1:
                        mAppViewModel.setClickBaccarat1(false);
                        break;
                    case 2:
                        mAppViewModel.setClickBaccarat2(false);
                        break;
                    case 3:
                        mAppViewModel.setClickBaccarat3(false);
                        break;
                    case 61:
                        mAppViewModel.setClickBaccarat5(false);
                        break;
                    case 62:
                        mAppViewModel.setClickBaccarat6(false);
                        break;
                    case 63:
                        mAppViewModel.setClickBaccarat7(false);
                        break;
                    case 71:
                        mAppViewModel.setClickBaccaratMi(false);
                        break;
                }
            }
        });
        baccaratBetContentList.add(contentBean);
        ImageView imgTable = view.findViewById(R.id.img_table);
        imgTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goOtherGame(tableId);
            }
        });
    }

    private void clearBaccaratResultView(int tableId) {
        getBaccaratBetContentBean(tableId).getLlResult().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgBanker1().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgBanker2().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgBanker3().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgPlayer1().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgPlayer2().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getImgPlayer3().setVisibility(View.GONE);
        getBaccaratBetContentBean(tableId).getTvBankerPoint().setText("");
        getBaccaratBetContentBean(tableId).getTvPlayerPoint().setText("");
    }

    private void clearDragonTigerResultView() {
        dragonTigerTableContentBean.getLlResult().setVisibility(View.GONE);
        dragonTigerTableContentBean.getImgDragon().setVisibility(View.GONE);
        dragonTigerTableContentBean.getImgTiger().setVisibility(View.GONE);
        dragonTigerTableContentBean.getTvDragonPoint().setText("");
        dragonTigerTableContentBean.getTvTigerPoint().setText("");
    }

    private void updateBaccarat(int tableId) {
        if (mAppViewModel.getBaccarat(tableId).getGameStatus() == 1) {
            if (getBaccaratBetContentBean(tableId).getResultWinView() != null && getBaccaratBetContentBean(tableId).getResultWinView().getBackground() != null) {
                getBaccaratBetContentBean(tableId).getResultWinView().setBackgroundResource(0);
            }
            clearBaccaratResultView(tableId);
            if (!getBaccaratBetContentBean(tableId).getBaccaratGameNumber().equals(mAppViewModel.getBaccarat(tableId).getGameNumber())) {
                String gameIdNumber = mAppViewModel.getBaccarat(tableId).getShoeNumber() + " - " + mAppViewModel.getBaccarat(tableId).getGameNumber();
                getBaccaratBetContentBean(tableId).getTvTableNumber().setText(gameIdNumber);
                getBaccaratBetContentBean(tableId).setBaccaratGameNumber(mAppViewModel.getBaccarat(tableId).getGameNumber());
                getBaccaratBetContentBean(tableId).setBaccaratOpenPoker(true);
                getBaccaratBetContentBean(tableId).setBaccaratGetResult(true);
                BaccaratTableBetBean baccaratBetBean = getBaccaratBetBean(tableId);
                baccaratBetBean.setPlayerAlreadyBet(0);
                baccaratBetBean.setBankerAlreadyBet(0);
                baccaratBetBean.setTieAlreadyBet(0);
                baccaratBetBean.setPpAlreadyBet(0);
                baccaratBetBean.setBpAlreadyBet(0);
            }
        } else if (mAppViewModel.getBaccarat(tableId).getGameStatus() == 2) {
            getBaccaratBetContentBean(tableId).getLlResult().setVisibility(View.VISIBLE);
            showBaccaratPoint(tableId);
            if (getBaccaratBetContentBean(tableId).isBaccaratOpenPoker()) {
                String gameIdNumber = mAppViewModel.getBaccarat(tableId).getShoeNumber() + " - " + mAppViewModel.getBaccarat(tableId).getGameNumber();
                getBaccaratBetContentBean(tableId).getTvTableNumber().setText(gameIdNumber);
                getBaccaratBetContentBean(tableId).setBaccaratOpenPoker(false);
                TableBetUtils.clearNoBetChip(getBaccaratBetBean(tableId), getBaccaratBetContentBean(tableId), context);
            }
        } else if (mAppViewModel.getBaccarat(tableId).getGameStatus() == 5) {
            showBaccaratPoint(tableId);
            showBaccaratResult(tableId);
            if (getBaccaratBetContentBean(tableId).isBaccaratGetResult()) {
                String gameIdNumber = mAppViewModel.getBaccarat(tableId).getShoeNumber() + " - " + mAppViewModel.getBaccarat(tableId).getGameNumber();
                getBaccaratBetContentBean(tableId).getTvTableNumber().setText(gameIdNumber);
                getBaccaratBetContentBean(tableId).setBaccaratGetResult(false);
                TableBetUtils.clearAllChip(getBaccaratBetBean(tableId), getBaccaratBetContentBean(tableId));
            }

        }
    }

    private void updateSicbo(int tableId) {
        if (mAppViewModel.getSicbo01().getGameStatus() == 1) {
            if (!sicboTableContentBean.getSicboGameNumber().equals(mAppViewModel.getSicbo01().getGameNumber())) {
                sicboTableContentBean.getTvTableNumber().setText(mAppViewModel.getSicbo01().getGameNumber());
                mAppViewModel.getSicbo01().setResult("");
                for (int i = 0; i < sicboTableContentBean.getAnimationList().size(); i++) {
                    sicboTableContentBean.getAnimationList().get(i).stop();
                    sicboTableContentBean.getAnimationList().get(i).selectDrawable(0);
                }
                sicboTableContentBean.getFlResult().setVisibility(View.GONE);
                sicboTableContentBean.setSicboGameNumber(mAppViewModel.getSicbo01().getGameNumber());
                sicboTableContentBean.setSicboOpenResult(true);
                sicboTableContentBean.setSicboGetResult(true);
            }
        } else if (mAppViewModel.getSicbo01().getGameStatus() == 2) {
            if (sicboTableContentBean.isSicboOpenResult()) {
                sicboTableContentBean.getTvTableNumber().setText(mAppViewModel.getSicbo01().getGameNumber());
                sicboTableContentBean.setSicboOpenResult(false);
                TableBetUtils.clearSicboNoBetChip(sicboTableBetBean, sicboTableContentBean, context);
            }
        } else if (mAppViewModel.getSicbo01().getGameStatus() == 5) {
            if (sicboTableContentBean.isSicboGetResult()) {
                sicboTableContentBean.getTvTableNumber().setText(mAppViewModel.getSicbo01().getGameNumber());
                showSicboResult();
                sicboTableContentBean.setSicboGetResult(false);
                TableBetUtils.clearSicboAllChip(sicboTableBetBean, sicboTableContentBean);
            }
        }
    }

    private void updateRoulette(int tableId) {
        if (mAppViewModel.getRoulette01().getGameStatus() == 1) {
            if (!rouletteTableContentBean.getRouletteGameNumber().equals(mAppViewModel.getRoulette01().getGameNumber())) {
                rouletteTableContentBean.getTvTableNumber().setText(mAppViewModel.getRoulette01().getGameNumber());
                mAppViewModel.getRoulette01().setResult("");
                for (int i = 0; i < rouletteTableContentBean.getAnimationList().size(); i++) {
                    rouletteTableContentBean.getAnimationList().get(i).stop();
                    rouletteTableContentBean.getAnimationList().get(i).selectDrawable(0);
                }
                rouletteTableContentBean.getFlResult().setVisibility(View.GONE);
                rouletteTableContentBean.setRouletteGameNumber(mAppViewModel.getRoulette01().getGameNumber());
                rouletteTableContentBean.setRouletteOpenResult(true);
                rouletteTableContentBean.setRouletteGetResult(true);
            }
        } else if (mAppViewModel.getRoulette01().getGameStatus() == 2) {
            if (rouletteTableContentBean.isRouletteOpenResult()) {
                rouletteTableContentBean.getTvTableNumber().setText(mAppViewModel.getRoulette01().getGameNumber());
                rouletteTableContentBean.setRouletteOpenResult(false);
                TableBetUtils.clearRouletteNoBetChip(rouletteTableBetBean, rouletteTableContentBean, context);
            }
        } else if (mAppViewModel.getRoulette01().getGameStatus() == 5) {
            if (rouletteTableContentBean.isRouletteGetResult()) {
                rouletteTableContentBean.getTvTableNumber().setText(mAppViewModel.getRoulette01().getGameNumber());
                showRouletteResult();
                rouletteTableContentBean.setRouletteGetResult(false);
                TableBetUtils.clearRouletteAllChip(rouletteTableBetBean, rouletteTableContentBean);
            }
        }
    }

    private void updateDragonTiger(int tableId) {
        if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 1) {
            if (dragonTigerTableContentBean.getResultWinView() != null && dragonTigerTableContentBean.getResultWinView().getBackground() != null) {
                dragonTigerTableContentBean.getResultWinView().setBackgroundResource(0);
            }
            clearDragonTigerResultView();
            if (!dragonTigerTableContentBean.getDragonTigerGameNumber().equals(mAppViewModel.getDragonTiger(tableId).getGameNumber())) {
                dragonTigerTableContentBean.getTvTableNumber().setText(mAppViewModel.getDragonTiger(tableId).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(tableId).getGameNumber());
                mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().setDragon(0);
                mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().setTiger(0);
                dragonTigerTableContentBean.setDragonTigerGameNumber(mAppViewModel.getDragonTiger(tableId).getGameNumber());
                dragonTigerTableContentBean.setDragonTigerOpenPoker(true);
                dragonTigerTableContentBean.setDragonTigerOpenPoker(true);
                dragonTigerTableBetBean.setDragonAlreadyBet(0);
                dragonTigerTableBetBean.setTigerAlreadyBet(0);
                dragonTigerTableBetBean.setTieAlreadyBet(0);
            }
        } else if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 2) {
            dragonTigerTableContentBean.getLlResult().setVisibility(View.VISIBLE);
            showDragonTigerPoint(tableId);
            if (dragonTigerTableContentBean.isDragonTigerOpenPoker()) {
                dragonTigerTableContentBean.getTvTableNumber().setText(mAppViewModel.getDragonTiger(tableId).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(tableId).getGameNumber());
                dragonTigerTableContentBean.setDragonTigerOpenPoker(false);
                TableBetUtils.clearDragonTigerNoBetChip(dragonTigerTableBetBean, dragonTigerTableContentBean, context);
            }
        } else if (mAppViewModel.getDragonTiger(tableId).getGameStatus() == 5) {
            showDragonTigerPoint(tableId);
            showDragonTigerResult(tableId);
            if (dragonTigerTableContentBean.isDragonTigerGetResult()) {
                dragonTigerTableContentBean.getTvTableNumber().setText(mAppViewModel.getDragonTiger(tableId).getShoeNumber() + " - " + mAppViewModel.getDragonTiger(tableId).getGameNumber());
                dragonTigerTableContentBean.setDragonTigerGetResult(false);
                TableBetUtils.clearDragonTigerAllChip(dragonTigerTableBetBean, dragonTigerTableContentBean);
            }
        }
    }

    private int getBanker3(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getBanker3();
    }

    private int getBanker2(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getBanker2();
    }

    private int getBanker1(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getBanker1();
    }

    private int getPlayer3(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getPlayer3();
    }

    private int getPlayer2(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getPlayer2();
    }

    private int getPlayer1(int tableId) {
        return mAppViewModel.getBaccarat(tableId).getBaccaratPoker().getPlayer1();

    }

    private void showBaccaratPoint(int tableId) {
        mAppViewModel.showPoint(getPlayer1(tableId),
                getPlayer2(tableId),
                getPlayer3(tableId),
                getBanker1(tableId),
                getBanker2(tableId),
                getBanker3(tableId),
                /*tv_point_banker, tv_point_player*/getBaccaratBetContentBean(tableId).getTvBankerPoint(), getBaccaratBetContentBean(tableId).getTvPlayerPoint(), "", "");

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        if (getBanker1(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgBanker1().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getBanker1(tableId)));
                getBaccaratBetContentBean(tableId).getImgBanker1().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgBanker1().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));

            }
        }
        if (getBanker2(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgBanker2().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getBanker2(tableId)));
                getBaccaratBetContentBean(tableId).getImgBanker2().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgBanker2().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));

            }
        }
        if (getBanker3(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgBanker3().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getBanker3(tableId)));
                getBaccaratBetContentBean(tableId).getImgBanker3().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgBanker3().setImageBitmap(BitmapTool.skewBitmap(BitmapTool.toturn(bitmap, 90), 0, 0f));
            }
        }
        if (getPlayer1(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgPlayer1().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getPlayer1(tableId)));
                getBaccaratBetContentBean(tableId).getImgPlayer1().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgPlayer1().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));
            }
        }
        if (getPlayer2(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgPlayer2().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getPlayer2(tableId)));
                getBaccaratBetContentBean(tableId).getImgPlayer2().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgPlayer2().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));
            }
        }
        if (getPlayer3(tableId) > 0) {
            if (getBaccaratBetContentBean(tableId).getImgPlayer3().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(getPlayer3(tableId)));
                getBaccaratBetContentBean(tableId).getImgPlayer3().setVisibility(View.VISIBLE);
                getBaccaratBetContentBean(tableId).getImgPlayer3().setImageBitmap(BitmapTool.skewBitmap(BitmapTool.toturn(bitmap, 90), 0, 0f));
            }
        }
    }

    private void showDragonTigerPoint(int tableId) {
        mAppViewModel.showPoint(mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getDragon(),
                mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getTiger(),
                dragonTigerTableContentBean.getTvDragonPoint(), dragonTigerTableContentBean.getTvTigerPoint(), "", "");

        if (mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getDragon() > 0) {
            if (dragonTigerTableContentBean.getImgDragon().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getDragon()));
                dragonTigerTableContentBean.getImgDragon().setVisibility(View.VISIBLE);
                dragonTigerTableContentBean.getImgDragon().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));
            }
        }

        if (mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getTiger() > 0) {
            if (dragonTigerTableContentBean.getImgTiger().getVisibility() == View.GONE) {
                Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), getPokerResource(mAppViewModel.getDragonTiger(tableId).getDragonTigerPoker().getTiger()));
                dragonTigerTableContentBean.getImgTiger().setVisibility(View.VISIBLE);
                dragonTigerTableContentBean.getImgTiger().setImageBitmap(BitmapTool.skewBitmap(bitmap, 0, 0f));
            }
        }
    }


    private void showBaccaratResult(int tableId) {
        View winView = null;
        if (mAppViewModel.getBaccarat(tableId).getBaccaratResults().getBanker_palyer_tie() == 1) {
            winView = getBaccaratBetContentBean(tableId).getLlBankerParent();
        } else if (mAppViewModel.getBaccarat(tableId).getBaccaratResults().getBanker_palyer_tie() == 2) {
            winView = getBaccaratBetContentBean(tableId).getLlPlayerParent();
        }
        getBaccaratBetContentBean(tableId).setResultWinView(winView);
        if (winView != null) {
            if (winView.getBackground() == null) {
                winView.setBackgroundResource(R.drawable.shape_table_win_b_dt);
            } else {
                winView.setBackgroundResource(0);
            }

        }
    }

    private void showDragonTigerResult(int tableId) {
        View winView = null;
        if (mAppViewModel.getDragonTiger(tableId).getDragonTigerResults().getDragon_tiger_tie() == 1) {
            winView = dragonTigerTableContentBean.getLlDragonParent();
        } else if (mAppViewModel.getDragonTiger(tableId).getDragonTigerResults().getDragon_tiger_tie() == 2) {
            winView = dragonTigerTableContentBean.getLlTigerParent();
        }
        dragonTigerTableContentBean.setResultWinView(winView);
        if (winView != null) {
            if (winView.getBackground() == null) {
                winView.setBackgroundResource(R.drawable.shape_table_win_b_dt);
            } else {
                winView.setBackgroundResource(0);
            }

        }
    }

    private void showSicboResult() {
        FrameLayout flResult = sicboTableContentBean.getFlResult();
        ImageView iv1 = flResult.findViewById(R.id.gd__iv_dice_1);
        ImageView iv2 = flResult.findViewById(R.id.gd__iv_dice_2);
        ImageView iv3 = flResult.findViewById(R.id.gd__iv_dice_3);
        TextView tv = flResult.findViewById(R.id.gd__iv_dice_tv);
        TextView tv_big_small = flResult.findViewById(R.id.gd__tv_big_small);
        TextView tv_odd_even = flResult.findViewById(R.id.gd__tv_odd_even);
        if (mAppViewModel.getSicbo01().getResult() != null && !"".equals(mAppViewModel.getSicbo01().getResult()) && !"0".equals(mAppViewModel.getSicbo01().getResult())
                && mAppViewModel.getSicbo01().getResult().length() == 3) {
            int dices1 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(0, 1));
            int dices2 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(1, 2));
            int dices3 = Integer.parseInt(mAppViewModel.getSicbo01().getResult().substring(2, 3));
            int point = dices1 + dices2 + dices3;
            iv1.setImageResource(getMipmap(dices1));
            iv2.setImageResource(getMipmap(dices2));
            iv3.setImageResource(getMipmap(dices3));
            tv.setText("" + point);
            if (point > 10) {
                tv_big_small.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                tv_big_small.setText(context.getString(R.string.gd_B));
            } else {
                tv_big_small.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                tv_big_small.setText(context.getString(R.string.gd_S));
            }
            if (point % 2 == 0) {
                tv_odd_even.setBackgroundResource(R.drawable.rectangle_sicbo_red);
                tv_odd_even.setText(context.getString(R.string.gd_E));
            } else {
                tv_odd_even.setBackgroundResource(R.drawable.rectangle_sicbo_blue);
                tv_odd_even.setText(context.getString(R.string.gd_O));
            }
            flResult.setVisibility(View.VISIBLE);

            int allDices = 0;
            if ((dices1 == dices2) && (dices1 == dices3))
                allDices = 1;
            int bigSmall = 0;
            if (point > 10) {
                bigSmall = 1;//big
            } else
                bigSmall = 0;
            if (bigSmall == 1) {
                if (sicboTableContentBean.getAnimationBig().isRunning())
                    sicboTableContentBean.getAnimationBig().stop();
                sicboTableContentBean.getAnimationBig().start();
            } else {
                if (sicboTableContentBean.getAnimationSmall().isRunning())
                    sicboTableContentBean.getAnimationSmall().stop();
                sicboTableContentBean.getAnimationSmall().start();
            }
            if (allDices == 1) {
                if (sicboTableContentBean.getAnimationAny().isRunning())
                    sicboTableContentBean.getAnimationAny().stop();
                sicboTableContentBean.getAnimationAny().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("1")) {
                if (sicboTableContentBean.getAnimation1().isRunning())
                    sicboTableContentBean.getAnimation1().stop();
                sicboTableContentBean.getAnimation1().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("2")) {
                if (sicboTableContentBean.getAnimation2().isRunning())
                    sicboTableContentBean.getAnimation2().stop();
                sicboTableContentBean.getAnimation2().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("3")) {
                if (sicboTableContentBean.getAnimation3().isRunning())
                    sicboTableContentBean.getAnimation3().stop();
                sicboTableContentBean.getAnimation3().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("4")) {
                if (sicboTableContentBean.getAnimation4().isRunning())
                    sicboTableContentBean.getAnimation4().stop();
                sicboTableContentBean.getAnimation4().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("5")) {
                if (sicboTableContentBean.getAnimation5().isRunning())
                    sicboTableContentBean.getAnimation5().stop();
                sicboTableContentBean.getAnimation5().start();
            }
            if (mAppViewModel.getSicbo01().getResult().contains("6")) {
                if (sicboTableContentBean.getAnimation6().isRunning())
                    sicboTableContentBean.getAnimation6().stop();
                sicboTableContentBean.getAnimation6().start();
            }
        } else {
            flResult.setVisibility(View.GONE);
        }
    }

    private void showRouletteResult() {
        FrameLayout flResult = rouletteTableContentBean.getFlResult();
        TextView tv_pop_number = flResult.findViewById(R.id.gd__tv_pop_number);
        TextView tv_red_black = flResult.findViewById(R.id.gd__tv_red_black);
        TextView tv_odd_even = flResult.findViewById(R.id.gd__tv_odd_even);
        if (mAppViewModel.getRoulette01().getResult() != null && !"".equals(mAppViewModel.getRoulette01().getResult())) {
            switch (mAppViewModel.getRoulette01().getResult()) {
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
                    tv_red_black.setBackgroundResource(R.drawable.gd_shape_roulette_black_bg);
                    tv_red_black.setText(context.getString(R.string.black_acronym));
                    break;
                case "0":
                    tv_red_black.setBackgroundResource(R.drawable.gd_shape_roulette_zero_bg);
                    tv_red_black.setText(context.getString(R.string.zero_acronym));
                    break;
                default:
                    tv_red_black.setBackgroundResource(R.drawable.gd_shape_roulette_red_bg);
                    tv_red_black.setText(context.getString(R.string.red_acronym));
                    break;
            }
            String resultStr = mAppViewModel.getRoulette01().getResult();
            tv_pop_number.setText(resultStr);
            int result = Integer.parseInt(resultStr);
            if (result % 2 == 0) {
                tv_odd_even.setBackgroundResource(R.drawable.gd_shape_roulette_red_bg);
                tv_odd_even.setText(context.getString(R.string.gd_E));
            } else {
                tv_odd_even.setBackgroundResource(R.drawable.gd_shape_roulette_black_bg);
                tv_odd_even.setText(context.getString(R.string.gd_O));
            }
            flResult.setVisibility(View.VISIBLE);

            if (mAppViewModel.getRoulette01().getResult().equals("0")) {
                if (rouletteTableContentBean.getAnimationZero().isRunning()) {
                    rouletteTableContentBean.getAnimationZero().stop();
                }
                rouletteTableContentBean.getAnimationZero().start();
            }
            if (!"0".equals(mAppViewModel.getRoulette01().getResult())) {
                switch (mAppViewModel.getRoulette01().getResult()) {
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
                        if (rouletteTableContentBean.getAnimationBlack().isRunning()) {
                            rouletteTableContentBean.getAnimationBlack().stop();
                        }
                        rouletteTableContentBean.getAnimationBlack().start();
                        break;
                    default:
                        if (rouletteTableContentBean.getAnimationRed().isRunning()) {
                            rouletteTableContentBean.getAnimationRed().stop();
                        }
                        rouletteTableContentBean.getAnimationRed().start();
                        break;
                }
                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) % 2 == 0) {
                    if (rouletteTableContentBean.getAnimationEven().isRunning()) {
                        rouletteTableContentBean.getAnimationEven().stop();
                    }
                    rouletteTableContentBean.getAnimationEven().start();
                } else {
                    if (rouletteTableContentBean.getAnimationOdd().isRunning()) {
                        rouletteTableContentBean.getAnimationOdd().stop();
                    }
                    rouletteTableContentBean.getAnimationOdd().start();
                }
                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) > 18) {
                    if (rouletteTableContentBean.getAnimation19_36().isRunning()) {
                        rouletteTableContentBean.getAnimation19_36().stop();
                    }
                    rouletteTableContentBean.getAnimation19_36().start();
                } else {
                    if (rouletteTableContentBean.getAnimation1_18().isRunning()) {
                        rouletteTableContentBean.getAnimation1_18().stop();
                    }
                    rouletteTableContentBean.getAnimation1_18().start();
                }
                if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) > 0 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 12) {
                    if (rouletteTableContentBean.getAnimation1_12().isRunning()) {
                        rouletteTableContentBean.getAnimation1_12().stop();
                    }
                    rouletteTableContentBean.getAnimation1_12().start();
                } else if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) >= 13 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 24) {
                    if (rouletteTableContentBean.getAnimation13_24().isRunning()) {
                        rouletteTableContentBean.getAnimation13_24().stop();
                    }
                    rouletteTableContentBean.getAnimation13_24().start();
                } else if (Integer.parseInt(mAppViewModel.getRoulette01().getResult()) >= 25 && Integer.parseInt(mAppViewModel.getRoulette01().getResult()) <= 36) {
                    if (rouletteTableContentBean.getAnimation25_36().isRunning()) {
                        rouletteTableContentBean.getAnimation25_36().stop();
                    }
                    rouletteTableContentBean.getAnimation25_36().start();
                }
            }
        } else {
            flResult.setVisibility(View.GONE);
        }
    }

    public void updateBaccaratBetMoney(int tableId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Xhid=" + mAppViewModel.getBaccarat(tableId).getShoeNumber() + "&Blid=" + mAppViewModel.getBaccarat(tableId).getGameNumber() +
                            "&Xh=" + mAppViewModel.getBaccarat(tableId).getBaccaratLimit(mAppViewModel.getBaccarat(tableId).getLimitIndex()).getMaxTotalBet();
                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.BJL_BET_MONEY_URL, params);
                    String strInfo[] = strRes.split("#");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            if (!TextUtils.isEmpty(strInfo[4])) {
                                getBaccaratBetBean(tableId).setBankerAlreadyBet((int) Double.parseDouble(strInfo[4]));
                            }
                            if (!TextUtils.isEmpty(strInfo[3])) {
                                getBaccaratBetBean(tableId).setPlayerAlreadyBet((int) Double.parseDouble(strInfo[3]));
                            }
                            if (!TextUtils.isEmpty(strInfo[5])) {
                                getBaccaratBetBean(tableId).setTieAlreadyBet((int) Double.parseDouble(strInfo[5]));
                            }
                            if (!TextUtils.isEmpty(strInfo[6])) {
                                getBaccaratBetBean(tableId).setBpAlreadyBet((int) Double.parseDouble(strInfo[6]));
                            }
                            if (!TextUtils.isEmpty(strInfo[7])) {
                                getBaccaratBetBean(tableId).setPpAlreadyBet((int) Double.parseDouble(strInfo[7]));
                            }
                            BaseActivity baseActivity = (BaseActivity) context;
                            baseActivity.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    BaccaratTableBetBean baccaratBetBean = getBaccaratBetBean(tableId);
                                    int playerAlreadyBet = baccaratBetBean.getPlayerAlreadyBet();
                                    int bankerAlreadyBet = baccaratBetBean.getBankerAlreadyBet();
                                    int tieAlreadyBet = baccaratBetBean.getTieAlreadyBet();
                                    int ppAlreadyBet = baccaratBetBean.getPpAlreadyBet();
                                    int bpAlreadyBet = baccaratBetBean.getBpAlreadyBet();
                                    if (playerAlreadyBet > 0) {
                                        TableBetUtils.addChip(getBaccaratBetContentBean(tableId).getFlTablePlayer(), playerAlreadyBet, playerAlreadyBet, context);
                                    }
                                    if (bankerAlreadyBet > 0) {
                                        TableBetUtils.addChip(getBaccaratBetContentBean(tableId).getFlTableBanker(), bankerAlreadyBet, bankerAlreadyBet, context);
                                    }
                                    if (tieAlreadyBet > 0) {
                                        TableBetUtils.addChip(getBaccaratBetContentBean(tableId).getFlTableTie(), tieAlreadyBet, tieAlreadyBet, context);
                                    }
                                    if (ppAlreadyBet > 0) {
                                        TableBetUtils.addChip(getBaccaratBetContentBean(tableId).getFlTablePP(), ppAlreadyBet, ppAlreadyBet, context);
                                    }
                                    if (bpAlreadyBet > 0) {
                                        TableBetUtils.addChip(getBaccaratBetContentBean(tableId).getFlTableBP(), bpAlreadyBet, bpAlreadyBet, context);
                                    }
                                    getBaccaratBetContentBean(tableId).setCanBet(true);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void updateDragonTigerBetMoney(int tableId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Xhid=" + mAppViewModel.getDragonTiger(tableId).getShoeNumber() + "&Blid=" + mAppViewModel.getDragonTiger(tableId).getGameNumber() +
                            "&Xh=" + mAppViewModel.getDragonTiger(tableId).getDragonTigerLimit(mAppViewModel.getDragonTiger(tableId).getLimitIndex()).getMaxTotalBet();
                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LH_BET_MONEY_URL, params);
                    String strInfo[] = strRes.split("#");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            if (!TextUtils.isEmpty(strInfo[4])) {
                                dragonTigerTableBetBean.setDragonAlreadyBet((int) Double.parseDouble(strInfo[4]));
                            }
                            if (!TextUtils.isEmpty(strInfo[3])) {
                                dragonTigerTableBetBean.setTigerAlreadyBet((int) Double.parseDouble(strInfo[3]));
                            }
                            if (!TextUtils.isEmpty(strInfo[5])) {
                                dragonTigerTableBetBean.setTieAlreadyBet((int) Double.parseDouble(strInfo[5]));
                            }
                            BaseActivity baseActivity = (BaseActivity) context;
                            baseActivity.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    int dragonAlreadyBet = dragonTigerTableBetBean.getDragonAlreadyBet();
                                    int tigerAlreadyBet = dragonTigerTableBetBean.getTigerAlreadyBet();
                                    int tieAlreadyBet = dragonTigerTableBetBean.getTieAlreadyBet();
                                    if (dragonAlreadyBet > 0) {
                                        TableBetUtils.addChip(dragonTigerTableContentBean.getFlTableDragon(), dragonAlreadyBet, dragonAlreadyBet, context);
                                    }
                                    if (tigerAlreadyBet > 0) {
                                        TableBetUtils.addChip(dragonTigerTableContentBean.getFlTableTiger(), tigerAlreadyBet, tigerAlreadyBet, context);
                                    }
                                    if (tieAlreadyBet > 0) {
                                        TableBetUtils.addChip(dragonTigerTableContentBean.getFlTableTie(), tieAlreadyBet, tieAlreadyBet, context);
                                    }
                                    dragonTigerTableContentBean.setCanBet(true);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void updateSicboBetMoney(int tableId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Bl=" + mAppViewModel.getSicbo01().getGameNumber() +
                            "&Xh=" + mAppViewModel.getSicbo01().getSicboLimit(mAppViewModel.getSicbo01().getLimitIndex()).getMaxTotalBet();
                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.SICBO_BET_MONEY_URL, params);
                    String strInfo[] = strRes.split("\\^");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            sicboTableBetBean.setBigAlreadyBet((int) Double.parseDouble(strInfo[3]));
                            sicboTableBetBean.setSmallAlreadyBet((int) Double.parseDouble(strInfo[4]));
                            sicboTableBetBean.setAnyAlreadyBet((int) Double.parseDouble(strInfo[7]));
                            if (!"0".equals(strInfo[8])) {//1#2|2#2|6#2|
                                String strThree[] = strInfo[8].split("\\|");
                                for (int i = 0; i < strThree.length; i++) {
                                    String strThreeDetail[] = strThree[i].split("#");
                                    if (strThreeDetail != null && strThreeDetail.length == 2) {
                                        String point = strThreeDetail[0];
                                        int betMoney = (int) Double.parseDouble(strThreeDetail[1]);
                                        if (point.equals("1")) {
                                            sicboTableBetBean.setSingle1AlreadyBet(betMoney);
                                        } else if (point.equals("2")) {
                                            sicboTableBetBean.setSingle2AlreadyBet(betMoney);
                                        } else if (point.equals("3")) {
                                            sicboTableBetBean.setSingle3AlreadyBet(betMoney);
                                        } else if (point.equals("4")) {
                                            sicboTableBetBean.setSingle4AlreadyBet(betMoney);
                                        } else if (point.equals("5")) {
                                            sicboTableBetBean.setSingle5AlreadyBet(betMoney);
                                        } else if (point.equals("6")) {
                                            sicboTableBetBean.setSingle6AlreadyBet(betMoney);
                                        }
                                    }

                                }
                            }
                            BaseActivity baseActivity = (BaseActivity) context;
                            baseActivity.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    int bigAlreadyBet = sicboTableBetBean.getBigAlreadyBet();
                                    int anyAlreadyBet = sicboTableBetBean.getAnyAlreadyBet();
                                    int smallAlreadyBet = sicboTableBetBean.getSmallAlreadyBet();
                                    int single1AlreadyBet = sicboTableBetBean.getSingle1AlreadyBet();
                                    int single2AlreadyBet = sicboTableBetBean.getSingle2AlreadyBet();
                                    int single3AlreadyBet = sicboTableBetBean.getSingle3AlreadyBet();
                                    int single4AlreadyBet = sicboTableBetBean.getSingle4AlreadyBet();
                                    int single5AlreadyBet = sicboTableBetBean.getSingle5AlreadyBet();
                                    int single6AlreadyBet = sicboTableBetBean.getSingle6AlreadyBet();
                                    if (bigAlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlBig(), bigAlreadyBet, bigAlreadyBet, context);
                                    }
                                    if (anyAlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlAny(), anyAlreadyBet, anyAlreadyBet, context);
                                    }
                                    if (smallAlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSmall(), smallAlreadyBet, smallAlreadyBet, context);
                                    }
                                    if (single1AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle1(), single1AlreadyBet, single1AlreadyBet, context);
                                    }
                                    if (single2AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle2(), single2AlreadyBet, single2AlreadyBet, context);
                                    }
                                    if (single3AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle3(), single3AlreadyBet, single3AlreadyBet, context);
                                    }
                                    if (single4AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle4(), single4AlreadyBet, single4AlreadyBet, context);
                                    }
                                    if (single5AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle5(), single5AlreadyBet, single5AlreadyBet, context);
                                    }
                                    if (single6AlreadyBet > 0) {
                                        TableBetUtils.addChip(sicboTableContentBean.getFlSingle6(), single6AlreadyBet, single6AlreadyBet, context);
                                    }
                                    sicboTableContentBean.setCanBet(true);
                                }
                            });

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void updateRouletteBetMoney(int tableId) {
        new Thread() {
            @Override
            public void run() {
                try {
                    String params = "GameType=11&Tbid=" + tableId + "&Usid=" + mAppViewModel.getUser().getName()
                            + "&Blid=" + mAppViewModel.getRoulette01().getGameNumber() +
                            "&Xh=" + mAppViewModel.getRoulette01().getRouletteLimit(mAppViewModel.getRoulette01().getLimitIndex()).getMaxTotalBet();
                    String strRes = mAppViewModel.getHttpClient().sendPost(WebSiteUrl.LP_BET_MONEY_URL, params);
                    String strInfo[] = strRes.split("\\^");
                    if (strRes.startsWith("Results=ok")) {
                        if (strInfo.length >= 10) {
                            if (!"0".equals(strInfo[3])) {//09#1|06#1|00#1|
                                String direct[] = strInfo[3].split("\\|");
                                for (int i = 0; i < direct.length; i++) {
                                    String strDirectDetail[] = direct[i].split("#");
                                    if (strDirectDetail != null && strDirectDetail.length == 2) {
                                        String point = strDirectDetail[0];
                                        int betMoney = (int) Double.parseDouble(strDirectDetail[1]);
                                        if (point.equals("00")) {
                                            rouletteTableBetBean.setZeroAlreadyBet(betMoney);
                                            break;
                                        }
                                    }

                                }
                            }
                            rouletteTableBetBean.setSingle1_12AlreadyBet(Integer.parseInt(strInfo[13]));
                            rouletteTableBetBean.setSingle13_24AlreadyBet(Integer.parseInt(strInfo[14]));
                            rouletteTableBetBean.setSingle25_36AlreadyBet(Integer.parseInt(strInfo[15]));
                            rouletteTableBetBean.setRedAlreadyBet(Integer.parseInt(strInfo[16]));
                            rouletteTableBetBean.setBlackAlreadyBet(Integer.parseInt(strInfo[17]));
                            rouletteTableBetBean.setOddAlreadyBet(Integer.parseInt(strInfo[18]));
                            rouletteTableBetBean.setEvenAlreadyBet(Integer.parseInt(strInfo[19]));
                            rouletteTableBetBean.setSingle1_18AlreadyBet(Integer.parseInt(strInfo[20]));
                            rouletteTableBetBean.setSingle19_36AlreadyBet(Integer.parseInt(strInfo[21]));
                            BaseActivity baseActivity = (BaseActivity) context;
                            baseActivity.getHandler().post(new Runnable() {
                                @Override
                                public void run() {
                                    int evenAlreadyBet = rouletteTableBetBean.getEvenAlreadyBet();
                                    int zeroAlreadyBet = rouletteTableBetBean.getZeroAlreadyBet();
                                    int oddAlreadyBet = rouletteTableBetBean.getOddAlreadyBet();
                                    int single1_12AlreadyBet = rouletteTableBetBean.getSingle1_12AlreadyBet();
                                    int single13_24AlreadyBet = rouletteTableBetBean.getSingle13_24AlreadyBet();
                                    int single25_36AlreadyBet = rouletteTableBetBean.getSingle25_36AlreadyBet();
                                    int single1_18AlreadyBet = rouletteTableBetBean.getSingle1_18AlreadyBet();
                                    int single19_36AlreadyBet = rouletteTableBetBean.getSingle19_36AlreadyBet();
                                    int redAlreadyBet = rouletteTableBetBean.getRedAlreadyBet();
                                    int blackAlreadyBet = rouletteTableBetBean.getBlackAlreadyBet();
                                    if (evenAlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlEven(), evenAlreadyBet, evenAlreadyBet, context);
                                    }
                                    if (zeroAlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlZero(), zeroAlreadyBet, zeroAlreadyBet, context);
                                    }
                                    if (oddAlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlOdd(), oddAlreadyBet, oddAlreadyBet, context);
                                    }
                                    if (single1_12AlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlSingle1_12(), single1_12AlreadyBet, single1_12AlreadyBet, context);
                                    }
                                    if (single13_24AlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlSingle13_24(), single13_24AlreadyBet, single13_24AlreadyBet, context);
                                    }
                                    if (single25_36AlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlSingle25_36(), single25_36AlreadyBet, single25_36AlreadyBet, context);
                                    }
                                    if (single1_18AlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlSingle1_18(), single1_18AlreadyBet, single1_18AlreadyBet, context);
                                    }
                                    if (single19_36AlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlSingle19_36(), single19_36AlreadyBet, single19_36AlreadyBet, context);
                                    }
                                    if (redAlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlRed(), redAlreadyBet, redAlreadyBet, context);
                                    }
                                    if (blackAlreadyBet > 0) {
                                        TableBetUtils.addChip(rouletteTableContentBean.getFlBlack(), blackAlreadyBet, blackAlreadyBet, context);
                                    }
                                    rouletteTableContentBean.setCanBet(true);
                                }
                            });
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void updateGame() {
        if (mAppViewModel.isClickBaccarat1()) {
            updateBaccarat(1);
        }
        if (mAppViewModel.isClickBaccarat2()) {
            updateBaccarat(2);
        }
        if (mAppViewModel.isClickBaccarat3()) {
            updateBaccarat(3);
        }
        if (mAppViewModel.isClickBaccarat5()) {
            updateBaccarat(61);
        }
        if (mAppViewModel.isClickBaccarat6()) {
            updateBaccarat(62);
        }
        if (mAppViewModel.isClickBaccarat7()) {
            updateBaccarat(63);
        }
        if (mAppViewModel.isClickBaccaratMi()) {
            updateBaccarat(71);
        }
        if (mAppViewModel.isClickDragonTiger()) {
            updateDragonTiger(5);
        }
        if (mAppViewModel.isClickSicbo()) {
            updateSicbo(31);
        }
        if (mAppViewModel.isClickRoulette()) {
            updateRoulette(21);
        }
    }

    private void goOtherGame(int tableId) {
        String menuStr = "";
        switch (tableId) {
            case 1:
                menuStr = "LB1";
                break;
            case 2:
                menuStr = "LB2";
                break;
            case 3:
                menuStr = "LB3";
                break;
            case 61:
                menuStr = "LB5";
                break;
            case 62:
                menuStr = "LB6";
                break;
            case 63:
                menuStr = "LB7";
                break;
            case 71:
                menuStr = "BM1";
                break;
            case 5:
                menuStr = "DT1";
                break;
            case 21:
                menuStr = "RL1";
                break;
            case 31:
                menuStr = "SB1";
                break;
        }
        BaseActivity baseActivity = (BaseActivity) context;
        if (mAppViewModel.getTableId() == tableId) {
            Toast.makeText(context, context.getString(R.string.your_here), Toast.LENGTH_SHORT).show();
            return;
        }
        closePopupWindow();
        if (mAppViewModel.getTableId() == 1 || mAppViewModel.getTableId() == 2 || mAppViewModel.getTableId() == 3 ||
                mAppViewModel.getTableId() == 71 || mAppViewModel.getTableId() == 61 || mAppViewModel.getTableId() == 62 || mAppViewModel.getTableId() == 63) {
            if (tableId == 1 || tableId == 2 || tableId == 3 || tableId == 71 || tableId == 61 || tableId == 62 || tableId == 63) {
                if (mAppViewModel.getBaccarat(tableId).getStatus() != 1) {
                    Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                } else {
                    mAppViewModel.setTableId(tableId);
                    baseActivity.tableId = tableId;
                    baseActivity.initBaccarat();
                }
                return;
            }
        }
        if (menuStr.equals("LB1")) {
            if (mAppViewModel.getBaccarat(1).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            mAppViewModel.setTableId(1);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat01().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat01().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.tableId = 1;
            baseActivity.goBaccarat(1);
        } else if (menuStr.equals("LB2")) {
            if (mAppViewModel.getBaccarat(2).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 2;
            mAppViewModel.setTableId(2);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat02().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat02().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(2);
        } else if (menuStr.equals("LB3")) {
            if (mAppViewModel.getBaccarat(3).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 3;
            mAppViewModel.setTableId(3);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat03().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat03().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(3);
        } else if (menuStr.equals("BM1")) {
            if (mAppViewModel.getBaccarat(71).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 71;
            mAppViewModel.setTableId(71);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat71().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat71().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(71);
        } else if (menuStr.equals("RL1")) {
            if (mAppViewModel.getRoulette01().getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 21;
            Bundle bundle = new Bundle();
            bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "21");
            mAppViewModel.setTableId(21);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getRoulette01().getRouletteLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getRoulette01().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.skipAct(RouletteActivity.class, bundle);
            baseActivity.finish();
        } else if (menuStr.equals("SB1")) {
            if (mAppViewModel.getSicbo01().getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 31;
            Bundle bundle = new Bundle();
            bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "31");
            mAppViewModel.setTableId(31);
            String s = "";
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getSicbo01().getSicboLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getSicbo01().setLimitIndex(i);
                    if (i == 1) {
                        s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit1().getMaxTotalBet();
                    } else if (i == 2) {
                        s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit2().getMaxTotalBet();
                    } else if (i == 3) {
                        s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit3().getMaxTotalBet();
                    } else {
                        s = "" + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) mAppViewModel.getSicbo01().getSicboLimit4().getMaxTotalBet();
                    }
                    break;
                }
            }
            bundle.putString("limit", s);
            baseActivity.skipAct(SicboActivity.class, bundle);
            baseActivity.finish();
        } else if (menuStr.equals("DT1")) {
            if (mAppViewModel.getDragonTiger01().getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 5;
            Bundle bundle = new Bundle();
            bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "5");
            mAppViewModel.setTableId(5);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getDragonTiger01().getDragonTigerLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getDragonTiger01().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.skipAct(DragonTigerActivity.class, bundle);
            baseActivity.finish();
        } else if (menuStr.equals("LB5")) {
            if (mAppViewModel.getBaccarat(61).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 61;
            mAppViewModel.setTableId(61);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat61().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat61().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(61);
        } else if (menuStr.equals("LB6")) {
            if (mAppViewModel.getBaccarat(62).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 62;
            mAppViewModel.setTableId(62);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat62().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat62().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(62);
        } else if (menuStr.equals("LB7")) {
            if (mAppViewModel.getBaccarat(63).getStatus() != 1) {
                Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
                return;
            }
            baseActivity.tableId = 63;
            mAppViewModel.setTableId(63);
            for (int i = 1; i <= 4; i++) {
                if (mAppViewModel.getBaccarat63().getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    mAppViewModel.getBaccarat63().setLimitIndex(i);
                    break;
                }
            }
            baseActivity.goBaccarat(63);
        }
    }

    public int getPokerResource(int poker) {
        int poker_res = 0;
        switch (poker) {
            case 1:
                poker_res = R.mipmap.gd_pk_1;
                break;
            case 2:
                poker_res = R.mipmap.gd_pk_2;
                break;
            case 3:
                poker_res = R.mipmap.gd_pk_3;
                break;
            case 4:
                poker_res = R.mipmap.gd_pk_4;
                break;
            case 5:
                poker_res = R.mipmap.gd_pk_5;
                break;
            case 6:
                poker_res = R.mipmap.gd_pk_6;
                break;
            case 7:
                poker_res = R.mipmap.gd_pk_7;
                break;
            case 8:
                poker_res = R.mipmap.gd_pk_8;
                break;
            case 9:
                poker_res = R.mipmap.gd_pk_9;
                break;
            case 10:
                poker_res = R.mipmap.gd_pk_10;
                break;
            case 11:
                poker_res = R.mipmap.gd_pk_11;
                break;
            case 12:
                poker_res = R.mipmap.gd_pk_12;
                break;
            case 13:
                poker_res = R.mipmap.gd_pk_13;
                break;
            case 14:
                poker_res = R.mipmap.gd_pk_14;
                break;
            case 15:
                poker_res = R.mipmap.gd_pk_15;
                break;
            case 16:
                poker_res = R.mipmap.gd_pk_16;
                break;
            case 17:
                poker_res = R.mipmap.gd_pk_17;
                break;
            case 18:
                poker_res = R.mipmap.gd_pk_18;
                break;
            case 19:
                poker_res = R.mipmap.gd_pk_19;
                break;
            case 20:
                poker_res = R.mipmap.gd_pk_20;
                break;
            case 21:
                poker_res = R.mipmap.gd_pk_21;
                break;
            case 22:
                poker_res = R.mipmap.gd_pk_22;
                break;
            case 23:
                poker_res = R.mipmap.gd_pk_23;
                break;
            case 24:
                poker_res = R.mipmap.gd_pk_24;
                break;
            case 25:
                poker_res = R.mipmap.gd_pk_25;
                break;
            case 26:
                poker_res = R.mipmap.gd_pk_26;
                break;
            case 27:
                poker_res = R.mipmap.gd_pk_27;
                break;
            case 28:
                poker_res = R.mipmap.gd_pk_28;
                break;
            case 29:
                poker_res = R.mipmap.gd_pk_29;
                break;
            case 30:
                poker_res = R.mipmap.gd_pk_30;
                break;
            case 31:
                poker_res = R.mipmap.gd_pk_31;
                break;
            case 32:
                poker_res = R.mipmap.gd_pk_32;
                break;
            case 33:
                poker_res = R.mipmap.gd_pk_33;
                break;
            case 34:
                poker_res = R.mipmap.gd_pk_34;
                break;
            case 35:
                poker_res = R.mipmap.gd_pk_35;
                break;
            case 36:
                poker_res = R.mipmap.gd_pk_36;
                break;
            case 37:
                poker_res = R.mipmap.gd_pk_37;
                break;
            case 38:
                poker_res = R.mipmap.gd_pk_38;
                break;
            case 39:
                poker_res = R.mipmap.gd_pk_39;
                break;
            case 40:
                poker_res = R.mipmap.gd_pk_40;
                break;
            case 41:
                poker_res = R.mipmap.gd_pk_41;
                break;
            case 42:
                poker_res = R.mipmap.gd_pk_42;
                break;
            case 43:
                poker_res = R.mipmap.gd_pk_43;
                break;
            case 44:
                poker_res = R.mipmap.gd_pk_44;
                break;
            case 45:
                poker_res = R.mipmap.gd_pk_45;
                break;
            case 46:
                poker_res = R.mipmap.gd_pk_46;
                break;
            case 47:
                poker_res = R.mipmap.gd_pk_47;
                break;
            case 48:
                poker_res = R.mipmap.gd_pk_48;
                break;
            case 49:
                poker_res = R.mipmap.gd_pk_49;
                break;
            case 50:
                poker_res = R.mipmap.gd_pk_50;
                break;
            case 51:
                poker_res = R.mipmap.gd_pk_51;
                break;
            case 52:
                poker_res = R.mipmap.gd_pk_52;
                break;
        }
        return poker_res;
    }


    private void setChip() {
        AdapterViewContent<ChipBean> chips = new AdapterViewContent<>(context, lvChips);
        chips.setBaseAdapter(new QuickAdapterImp<ChipBean>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.gd_item_image_chip;
            }

            @Override
            public void convert(final ViewHolder helper, ChipBean item, final int position) {
                final LinearLayout llParent = helper.retrieveView(R.id.gd__ll_chip_parent);
                ImageView imgChip = helper.retrieveView(R.id.gd__iv_chip_pic);
                int w = 43;
                int h = 43;
                int m = 0;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) llParent.getLayoutParams();
                layoutParams.width = ScreenUtil.dip2px(context, w);
                layoutParams.height = ScreenUtil.dip2px(context, h);
                layoutParams.bottomMargin = ScreenUtil.dip2px(context, 6);
                layoutParams.leftMargin = ScreenUtil.dip2px(context, m);
                layoutParams.rightMargin = ScreenUtil.dip2px(context, m);
                llParent.setLayoutParams(layoutParams);
                LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                layoutParams1.width = ScreenUtil.dip2px(context, w);
                layoutParams1.height = ScreenUtil.dip2px(context, h);
                imgChip.setLayoutParams(layoutParams1);

                LinearLayout.LayoutParams layoutParams2;
                if (selectedMap.get(true) != null && position == selectedMap.get(true).intValue()) {
                    layoutParams2 = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams2.width = (int) (layoutParams2.width * 1.2);
                    layoutParams2.height = (int) (layoutParams2.height * 1.2);
                    imgChip.setLayoutParams(layoutParams2);
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, R.drawable.gd_rectangle_trans_stroke_yellow);
                } else {
                    layoutParams2 = (LinearLayout.LayoutParams) imgChip.getLayoutParams();
                    layoutParams2.width = ScreenUtil.dip2px(context, w);
                    layoutParams2.height = ScreenUtil.dip2px(context, h);
                    imgChip.setLayoutParams(layoutParams2);
                    helper.setBackgroundRes(R.id.gd__ll_chip_parent, 0);
                }
                imgChip.setBackgroundResource(item.getDrawableRes());
                helper.setText(R.id.gd__tv_chip_amount, item.getName());
            }
        });
        chips.setItemClick(new ItemCLickImp<ChipBean>() {
            @Override
            public void itemCLick(View view, ChipBean chipBean, int position) {
                if (chipBean.getValue() > 0) {
                    selectedMap.put(true, position);
                    chips.notifyDataSetChanged();
                    chooseChip = chipBean.getValue();
                }
            }
        });
        BaseActivity baseActivity = (BaseActivity) context;
        List<ChipBean> currentChip = baseActivity.getCurrentChip(false);
        List<ChipBean> chip = new ArrayList<>();
        for (int i = 0; i < currentChip.size(); i++) {
            if (currentChip.get(i).getValue() != -101) {
                chip.add(currentChip.get(i));
            }
        }
        chips.setData(chip);
    }

    public void showChooseChip(View v) {
        PopChooseChip popChooseChip = new PopChooseChip(context, v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {
            @Override
            public void onChooseChipFinish() {

            }
        };
        popChooseChip.showPopupCenterWindow();
    }

}
