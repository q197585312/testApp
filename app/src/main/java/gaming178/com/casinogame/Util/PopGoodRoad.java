package gaming178.com.casinogame.Util;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.BaccaratActivity;
import gaming178.com.casinogame.Activity.entity.GoodRoadDataBean;
import gaming178.com.casinogame.Bean.Baccarat;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/6/11.
 */

public class PopGoodRoad extends BasePopupWindow {

    BaseActivity baseActivity;

    public PopGoodRoad(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_good_road;
    }

    @BindView(R2.id.gd__ll_good_parent)
    LinearLayout ll_good_parent;
    @BindView(R2.id.gd__tv_wait)
    TextView tv_wait;
    View viewGoodRoad1;
    View viewGoodRoad2;
    View viewGoodRoad3;
    View viewGoodRoad61;
    View viewGoodRoad62;
    View viewGoodRoad63;
    View viewGoodRoad71;
    TextView tv_timer1;
    TextView tv_timer2;
    TextView tv_timer3;
    TextView tv_timer61;
    TextView tv_timer62;
    TextView tv_timer63;
    TextView tv_timer71;
    TextView tv_table_name1;
    TextView tv_table_name2;
    TextView tv_table_name3;
    TextView tv_table_name61;
    TextView tv_table_name62;
    TextView tv_table_name63;
    TextView tv_table_name71;
    ImageView img_good_road1;
    ImageView img_good_road2;
    ImageView img_good_road3;
    ImageView img_good_road61;
    ImageView img_good_road62;
    ImageView img_good_road63;
    ImageView img_good_road71;
    CountDownView countdown_view1;
    CountDownView countdown_view2;
    CountDownView countdown_view3;
    CountDownView countdown_view61;
    CountDownView countdown_view62;
    CountDownView countdown_view63;
    CountDownView countdown_view71;

    public void setContent(){
        tv_wait.setText(context.getString(R.string.wait_good_road));
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        viewGoodRoad1 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad2 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad3 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad61 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad62 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad63 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        viewGoodRoad71 = LayoutInflater.from(context).inflate(R.layout.gd_item_good_road, null);
        tv_timer1 = viewGoodRoad1.findViewById(R.id.gd__tv_timer);
        tv_timer2 = viewGoodRoad2.findViewById(R.id.gd__tv_timer);
        tv_timer3 = viewGoodRoad3.findViewById(R.id.gd__tv_timer);
        tv_timer61 = viewGoodRoad61.findViewById(R.id.gd__tv_timer);
        tv_timer62 = viewGoodRoad62.findViewById(R.id.gd__tv_timer);
        tv_timer63 = viewGoodRoad63.findViewById(R.id.gd__tv_timer);
        tv_timer71 = viewGoodRoad71.findViewById(R.id.gd__tv_timer);
        tv_table_name1 = viewGoodRoad1.findViewById(R.id.gd__tv_table_name);
        tv_table_name2 = viewGoodRoad2.findViewById(R.id.gd__tv_table_name);
        tv_table_name3 = viewGoodRoad3.findViewById(R.id.gd__tv_table_name);
        tv_table_name61 = viewGoodRoad61.findViewById(R.id.gd__tv_table_name);
        tv_table_name62 = viewGoodRoad62.findViewById(R.id.gd__tv_table_name);
        tv_table_name63 = viewGoodRoad63.findViewById(R.id.gd__tv_table_name);
        tv_table_name71 = viewGoodRoad71.findViewById(R.id.gd__tv_table_name);
        img_good_road1 = viewGoodRoad1.findViewById(R.id.gd__img_good_road);
        img_good_road2 = viewGoodRoad2.findViewById(R.id.gd__img_good_road);
        img_good_road3 = viewGoodRoad3.findViewById(R.id.gd__img_good_road);
        img_good_road61 = viewGoodRoad61.findViewById(R.id.gd__img_good_road);
        img_good_road62 = viewGoodRoad62.findViewById(R.id.gd__img_good_road);
        img_good_road63 = viewGoodRoad63.findViewById(R.id.gd__img_good_road);
        img_good_road71 = viewGoodRoad71.findViewById(R.id.gd__img_good_road);
        countdown_view1 = viewGoodRoad1.findViewById(R.id.gd__countdown_view);
        countdown_view2 = viewGoodRoad2.findViewById(R.id.gd__countdown_view);
        countdown_view3 = viewGoodRoad3.findViewById(R.id.gd__countdown_view);
        countdown_view61 = viewGoodRoad61.findViewById(R.id.gd__countdown_view);
        countdown_view62 = viewGoodRoad62.findViewById(R.id.gd__countdown_view);
        countdown_view63 = viewGoodRoad63.findViewById(R.id.gd__countdown_view);
        countdown_view71 = viewGoodRoad71.findViewById(R.id.gd__countdown_view);
        ll_good_parent.addView(viewGoodRoad1);
        ll_good_parent.addView(viewGoodRoad2);
        ll_good_parent.addView(viewGoodRoad3);
        ll_good_parent.addView(viewGoodRoad61);
        ll_good_parent.addView(viewGoodRoad62);
        ll_good_parent.addView(viewGoodRoad63);
        ll_good_parent.addView(viewGoodRoad71);
        viewGoodRoad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goBaccarat(1, baseActivity.mAppViewModel.getBaccarat01());
            }
        });
        viewGoodRoad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(2, baseActivity.mAppViewModel.getBaccarat02());
            }
        });
        viewGoodRoad3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(3, baseActivity.mAppViewModel.getBaccarat03());
            }
        });
        viewGoodRoad61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(61, baseActivity.mAppViewModel.getBaccarat61());
            }
        });
        viewGoodRoad62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(62, baseActivity.mAppViewModel.getBaccarat62());
            }
        });
        viewGoodRoad63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(63, baseActivity.mAppViewModel.getBaccarat63());
            }
        });
        viewGoodRoad71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBaccarat(71, baseActivity.mAppViewModel.getBaccarat71());
            }
        });
        for (int i = 0; i < ll_good_parent.getChildCount(); i++) {
            View childAt = ll_good_parent.getChildAt(i);
            childAt.setVisibility(View.GONE);
        }
    }

    private void goBaccarat(int tableId, Baccarat baccarat) {
        if (baseActivity.mAppViewModel.getBaccarat(tableId).getStatus() != 1) {
            Toast.makeText(context, context.getString(R.string.game_close), Toast.LENGTH_SHORT).show();
            return;
        }
        baseActivity.mAppViewModel.setTableId(tableId);
        if (baseActivity instanceof BaccaratActivity){
            BaccaratActivity baccaratActivity = (BaccaratActivity) baseActivity;
            baccaratActivity.initBaccarat();
            closePopupWindow();
        }else {
            for (int i = 1; i <= 4; i++) {
                if (baccarat.getBaccaratLimit(i).getMaxTotalBet() > 0) {
                    baccarat.setLimitIndex(i);
                    break;
                }
            }
            Bundle bundle = new Bundle();
            bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
            bundle.putBoolean("baccaratA", true);
            baseActivity.mAppViewModel.setbLobby(false);
            AppTool.activiyJump(context, BaccaratActivity.class, bundle);
            baseActivity.finish();
        }
    }

    public void initUi(final List<GoodRoadDataBean> goodRoadDataBeenList) {
        new Thread() {
            @Override
            public void run() {
                String strRes = baseActivity.mAppViewModel.getHttpClient().sendPost(WebSiteUrl.COUNTDOWN_URL_A, "GameType=11&Tbid=0&Usid=" + baseActivity.mAppViewModel.getUser().getName());
                if (strRes.startsWith("Results=ok")) {
                    final String[] split = strRes.split("\\^");
                    //Results=ok#^1#1#21#^2#2#0#^3#1#25#^5#1#10#^21#1#37#^31#1#20#^71#2#0#^61#2#0#^62#1#12#^63#1#24#^
                    baseActivity.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            hideAllView();
                            for (int i = 0; i < goodRoadDataBeenList.size(); i++) {
                                GoodRoadDataBean goodRoadDataBean = goodRoadDataBeenList.get(i);
                                String tableId = goodRoadDataBean.getTableId();
                                if (tableId.equals("1")) {
                                    int timer = Integer.parseInt(split[1].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer1.setTextColor(Color.RED);
                                    } else {
                                        tv_timer1.setTextColor(Color.WHITE);
                                    }
                                    tv_timer1.setText(timer + "");
                                    if (!countdown_view1.viewIsRunning() && timer > 0) {
                                        countdown_view1.setCountdownTime(timer);
                                        countdown_view1.startCountDown();
                                    }
                                    tv_table_name1.setText(goodRoadDataBean.getTableName());
                                    img_good_road1.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad1.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("2")) {
                                    int timer = Integer.parseInt(split[2].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer2.setTextColor(Color.RED);
                                    } else {
                                        tv_timer2.setTextColor(Color.WHITE);
                                    }
                                    tv_timer2.setText(timer + "");
                                    if (!countdown_view2.viewIsRunning() && timer > 0) {
                                        countdown_view2.setCountdownTime(timer);
                                        countdown_view2.startCountDown();
                                    }
                                    tv_table_name2.setText(goodRoadDataBean.getTableName());
                                    img_good_road2.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad2.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("3")) {
                                    int timer = Integer.parseInt(split[3].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer3.setTextColor(Color.RED);
                                    } else {
                                        tv_timer3.setTextColor(Color.WHITE);
                                    }
                                    tv_timer3.setText(timer + "");
                                    if (!countdown_view3.viewIsRunning() && timer > 0) {
                                        countdown_view3.setCountdownTime(timer);
                                        countdown_view3.startCountDown();
                                    }
                                    tv_table_name3.setText(goodRoadDataBean.getTableName());
                                    img_good_road3.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad3.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("61")) {
                                    int timer = Integer.parseInt(split[8].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer61.setTextColor(Color.RED);
                                    } else {
                                        tv_timer61.setTextColor(Color.WHITE);
                                    }
                                    tv_timer61.setText(timer + "");
                                    if (!countdown_view61.viewIsRunning() && timer > 0) {
                                        countdown_view61.setCountdownTime(timer);
                                        countdown_view61.startCountDown();
                                    }
                                    tv_table_name61.setText(goodRoadDataBean.getTableName());
                                    img_good_road61.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad61.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("62")) {
                                    int timer = Integer.parseInt(split[9].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer62.setTextColor(Color.RED);
                                    } else {
                                        tv_timer62.setTextColor(Color.WHITE);
                                    }
                                    tv_timer62.setText(timer + "");
                                    if (!countdown_view62.viewIsRunning() && timer > 0) {
                                        countdown_view62.setCountdownTime(timer);
                                        countdown_view62.startCountDown();
                                    }
                                    tv_table_name62.setText(goodRoadDataBean.getTableName());
                                    img_good_road62.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad62.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("63")) {
                                    int timer = Integer.parseInt(split[10].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer63.setTextColor(Color.RED);
                                    } else {
                                        tv_timer63.setTextColor(Color.WHITE);
                                    }
                                    tv_timer63.setText(timer + "");
                                    if (!countdown_view63.viewIsRunning() && timer > 0) {
                                        countdown_view63.setCountdownTime(timer);
                                        countdown_view63.startCountDown();
                                    }
                                    tv_table_name63.setText(goodRoadDataBean.getTableName());
                                    img_good_road63.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad63.setVisibility(View.VISIBLE);
                                } else if (tableId.equals("71")) {
                                    int timer = Integer.parseInt(split[7].split("#")[2]);
                                    if (timer < 6) {
                                        tv_timer71.setTextColor(Color.RED);
                                    } else {
                                        tv_timer71.setTextColor(Color.WHITE);
                                    }
                                    tv_timer71.setText(timer + "");
                                    if (!countdown_view71.viewIsRunning() && timer > 0) {
                                        countdown_view71.setCountdownTime(timer);
                                        countdown_view71.startCountDown();
                                    }
                                    tv_table_name71.setText(goodRoadDataBean.getTableName());
                                    img_good_road71.setImageResource(goodRoadDataBean.getPic());
                                    viewGoodRoad71.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    });
                }
            }
        }.start();
    }

    public void hideAllView() {
        for (int i = 0; i < ll_good_parent.getChildCount(); i++) {
            View childAt = ll_good_parent.getChildAt(i);
            childAt.setVisibility(View.GONE);
        }
    }

    public void showHideWait(boolean b) {
        if (b) {
            tv_wait.setVisibility(View.VISIBLE);
        } else {
            tv_wait.setVisibility(View.GONE);
        }
    }
}
