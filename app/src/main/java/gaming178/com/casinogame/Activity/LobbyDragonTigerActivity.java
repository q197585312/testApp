package gaming178.com.casinogame.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Bean.DragonTiger;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.myview.View.GridBackgroundView;

/**
 * Created by Administrator on 2016/3/22.
 */
public class LobbyDragonTigerActivity extends BaseActivity {
    @BindView(R2.id.gd__baccarat_grid_parent_ll)
    LinearLayout baccarat_grid_parent_ll;
    @BindView(R2.id.gd__baccarat_content_parent_ll)
    LinearLayout baccaratContentParentLl;
    @BindView(R2.id.gd__baccarat_gridlayout1)
    GridLayout baccarat_head_road1;
    @BindView(R2.id.gd__baccarat_gridlayout2)
    GridLayout baccarat_big_road1;
    @BindView(R2.id.gd__baccarat_gridlayout3)
    GridLayout baccarat_bigeyes_road1;
    @BindView(R2.id.gd__baccarat_gridlayout4)
    FrameLayout fl4;//baccarat_smalleyes_road1
    @BindView(R2.id.gd__baccarat_gridlayout5)
    FrameLayout fl5;//baccarat_roach_road1
    @BindView(R2.id.gd__baccarat_head_iv)
    ImageView baccarat_head_iv;//baccarat_roach_road1
    private float density;
    private TextView shufflingTv;

    private TextView tv_baccarat_timer01;

    private TextView tv_baccarat_shoe_number01;
    private TextView tv_baccarat_total_number01;
    private TextView tv_baccarat_banker_number01;
    private TextView tv_baccarat_player_number01;
    private TextView tv_baccarat_tie_number01;


    private TextView tv_baccarat_bp_number01;
    private TextView tv_baccarat_pp_number01;
    private TextView tv_baccarat_table_name;


    private int[][] road;
    private GridLayout baccarat_smalleyes_road1;
    private GridLayout baccarat_roach_road1;
    private int dragonTigerTimer01 = 0;

    private String dragenTigerGameNumber01 = "";


    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    private GridBackgroundView baccarat_background_head_road1;
    private GridBackgroundView smallway1;
    private View bigway1;
    private View smallway2;
    private View smallway3;

    private View ll_big_road_parent2;
    private View hsv_small_road_1;
    private View hsv_small_road_2;
    private View hsv_small_road_3;

    public class UpdateStatus implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetStatus) {
                try {

                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!isAttached) {
                return;
            }
            switch (msg.what) {
                case HandlerCode.UPDATE_STATUS:
                    //  InitRoad();
                    updateTimer();
                    updateInterface();
                    InitRoad();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismissBlockDialog();
                        }
                    }, 500);
                    break;

            }
            //

        }
    };

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showBlockDialog();
    }

    private void setLayoutLayoutParams(View view) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = ScreenUtil.dip2px(mContext, 333);
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(params);
    }

    @BindView(R2.id.gd__layout1)
    HorizontalScrollView scrollView1;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Configuration mConfiguration = getResources().getConfiguration();
        if (AppTool.isPad(mContext) && mConfiguration.orientation == mConfiguration.ORIENTATION_LANDSCAPE) {
            setLayoutLayoutParams(scrollView1);
        }
    }

    public void initUI() {
        mAppViewModel.getDragonTiger01().setTimer(0);
        mAppViewModel.getDragonTiger01().setBigRoadOld("");
        dragonTigerTimer01 = 0;
        tv_baccarat_timer01.setText("0");
        dragenTigerGameNumber01 = "";
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(WebSiteUrl.Tag, "LobbyDragonTiger onResume()");
        initUI();
        startUpdateStatusThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(WebSiteUrl.Tag, "LobbyDragonTiger onPause()");

        stopUpdateStatusThread();

    }

    @Override
    protected void leftClick() {
        mAppViewModel.getDragonTiger01().setBigRoadOld("");
        if (!WebSiteUrl.isDomain)
            skipAct(LobbyActivity.class);
        finish();
    }

    public void startUpdateStatusThread() {
        //   Log.i(WebSiteUrl.Tag, "startUpdateStatus() ");
        if (updateStatus == null) {
            //      Log.i(WebSiteUrl.Tag, "StartUpdateGameStatus() Start");
            bGetStatus = true;

            updateStatus = new UpdateStatus();
            threadStatus = new Thread(updateStatus);
            threadStatus.start();


        }
    }

    public void stopUpdateStatusThread() {
        //   Log.i(WebSiteUrl.Tag, "StopUpdateGameStatus() ");
        if (updateStatus != null) {
            bGetStatus = false;

            updateStatus = null;
            threadStatus = null;

        }


    }

    public void updateTimer() {
        if (mAppViewModel.getDragonTiger01().getGameStatus() == 8) {
            shufflingTv.setVisibility(View.VISIBLE);
        } else {
            shufflingTv.setVisibility(View.GONE);
        }
        if (dragonTigerTimer01 == 0 && mAppViewModel.getDragonTiger01().getTimer() > 0) {
            if (!dragenTigerGameNumber01.equals(mAppViewModel.getDragonTiger01().getShoeNumber() + mAppViewModel.getDragonTiger01().getGameNumber())) {
                dragenTigerGameNumber01 = mAppViewModel.getDragonTiger01().getShoeNumber() + mAppViewModel.getDragonTiger01().getGameNumber();
                dragonTigerTimer01 = mAppViewModel.getDragonTiger01().getTimer();
            }


        }

    }

    public void updateInterface() {
        if (dragonTigerTimer01 > 0) {
            dragonTigerTimer01--;
            tv_baccarat_timer01.setText("" + dragonTigerTimer01);
            tv_baccarat_timer01.setTextSize(18);
            tv_baccarat_shoe_number01.setText("" + mAppViewModel.getDragonTiger01().getShoeNumber() + " - " + mAppViewModel.getDragonTiger01().getGameNumber());
        } else {
//            if (mAppViewModel.getDragonTiger01().getGameStatus() == 2) {
//                tv_baccarat_timer01.setText(getString(R.string.START_DEALING));
//                tv_baccarat_timer01.setTextSize(12);
//            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "dragontiger.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv);
        setMoreToolbar(true);
        backTv.setVisibility(View.GONE);
        setLayout.setVisibility(View.GONE);
        shufflingTv = (TextView) findViewById(R.id.gd__tv_shuffling);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        InitControl();
        mAppViewModel.getDragonTiger01().setShoeNumberOld(mAppViewModel.getDragonTiger01().getShoeNumber());
        titleTv.setText(getString(R.string.dragon_tiger).toUpperCase());
        setTitleChangeGame(titleTv);
    }

    public void InitRoad() {
//        road = mAppViewModel.getDragonTiger02().ShowDragonTigerBigRoad("5#5#7#1#5#1#1#1#1#5#5#1#5#1#1#2#1#6#9#7#1#1#5#1#1#5#9#9#1#1#1#1#3#1#1#1#1#5#3#5#5#5#1#5#1#1#7#1#",
//                mContext,baccarat_big_road2,6,density,1);
        mAppViewModel.updateDragenTigerRoad(mContext, density, mAppViewModel.getDragonTiger01(), baccarat_head_road1, baccarat_big_road1, baccarat_bigeyes_road1, baccarat_smalleyes_road1, baccarat_roach_road1
                , tv_baccarat_shoe_number01, tv_baccarat_total_number01, tv_baccarat_banker_number01, tv_baccarat_player_number01, tv_baccarat_tie_number01, tv_baccarat_bp_number01, tv_baccarat_pp_number01,
                false, ll_big_road_parent2, hsv_small_road_1, hsv_small_road_2, hsv_small_road_3);
    }

    public void InitControl() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        density = ScreenUtil.getDisplayMetrics(mContext).density;


        baccarat_smalleyes_road1 = (GridLayout) fl4.findViewById(R.id.gd__baccarat_gridlayout3);
        baccarat_roach_road1 = (GridLayout) fl5.findViewById(R.id.gd__baccarat_gridlayout3);


        tv_baccarat_timer01 = (TextView) findViewById(R.id.gd__baccarat_status_tv);
        tv_baccarat_table_name = (TextView) findViewById(R.id.gd__tv_baccarat_table_name);

        tv_baccarat_shoe_number01 = (TextView) findViewById(R.id.gd__text_shoe_game_number);


        tv_baccarat_total_number01 = (TextView) findViewById(R.id.gd__text_total);


        tv_baccarat_banker_number01 = (TextView) findViewById(R.id.gd__text_banker);


        tv_baccarat_player_number01 = (TextView) findViewById(R.id.gd__text_player);

        tv_baccarat_tie_number01 = (TextView) findViewById(R.id.gd__text_tie);


        tv_baccarat_bp_number01 = (TextView) findViewById(R.id.gd__text_bp);

        tv_baccarat_pp_number01 = (TextView) findViewById(R.id.gd__text_pp);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_big_road_parent2 = findViewById(R.id.gd__ll_big_road_parent2);
            hsv_small_road_1 = findViewById(R.id.gd__hsv_small_road_1);
            hsv_small_road_2 = findViewById(R.id.gd__hsv_small_road_2);
            hsv_small_road_3 = findViewById(R.id.gd__hsv_small_road_3);
        } else {
            ll_big_road_parent2 = findViewById(R.id.gd__ll_big_road_parent2_landscape);
            hsv_small_road_1 = findViewById(R.id.gd__hsv_small_road_1_landscape);
            hsv_small_road_2 = findViewById(R.id.gd__hsv_small_road_2_landscape);
            hsv_small_road_3 = findViewById(R.id.gd__hsv_small_road_3_landscape);
        }

        setToolbarNameAndBalance();
        baccarat_background_head_road1 = (GridBackgroundView) findViewById(R.id.gd__baccarat_background_gridlayout1);
        smallway1 = (GridBackgroundView) findViewById(R.id.gd__smallway_item);
        smallway2 = findViewById(R.id.gd__baccarat_gridlayout4);
        smallway3 = findViewById(R.id.gd__baccarat_gridlayout5);
        bigway1 = findViewById(R.id.gd__big_way);
        findViewById(R.id.gd__ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        baccarat_background_head_road1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        smallway1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        smallway3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        smallway2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        bigway1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 5);
            }
        });
        tv_baccarat_table_name.setText("DT1");
        ((TextView) findViewById(R.id.gd__tv_banker)).setText(getString(R.string.dr));
        ((TextView) findViewById(R.id.gd__tv_player)).setText(getString(R.string.ti));
        ((TextView) findViewById(R.id.gd__tv_tie)).setText(getString(R.string.tie));
        ((LinearLayout) findViewById(R.id.gd__ll_bp)).setVisibility(View.GONE);
        ((LinearLayout) findViewById(R.id.gd__ll_pp)).setVisibility(View.GONE);
 /*       ((TextView) findViewById(R.id.gd__tv_banker_road)).setText("D");
        ((TextView) findViewById(R.id.gd__tv_player_road)).setText("T");*/
    }

    public void clickGrid(View v, final int tableId) {
        initLimitPop(v, tableId, findViewById(R.id.gd__ll_parent_limit));
    }

    public void initLimitPop(View view, final int tableId, final View top_v) {
        String tableNumber = "DT1";
        RecyclerView recyclerView = (RecyclerView) top_v.findViewById(R.id.gd__base_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mContext, getLimitData(tableId), R.layout.gd_item_popupwindow_text_select) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.gd__pop_text_tv, item);
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String s, int position) {
                if ("0 - 0".endsWith(s))
                    return;
                mAppViewModel.getDragonTiger01().setLimitIndex(position + 1);
                Bundle bundle = new Bundle();
                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
                //   AppTool.activiyJump(AutoNumberActivity.this, NumberGameSingleTableActivity.class, bundle);
                mAppViewModel.setTableId(5);
                mAppViewModel.setbLobby(false);
                skipAct(DragonTigerActivity.class, bundle);

            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);
        top_v.findViewById(R.id.gd__pop_close_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top_v.setVisibility(View.GONE);
            }
        });
        ((TextView) top_v.findViewById(R.id.gd__tv_table_game_number)).setText(tableNumber);
        top_v.setVisibility(View.VISIBLE);

    }

    private List<String> getLimitData(int tableId) {
        String limit1 = "0 - 0";
        String limit2 = "0 - 0";
        String limit3 = "0 - 0";
        String limit4 = "0 - 0";
        DragonTiger dragonTiger = mAppViewModel.getDragonTiger01();
        if (dragonTiger != null) {
            limit1 = "" + (int) dragonTiger.getDragonTigerLimit1().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit1().getMaxTotalBet();
            limit2 = "" + (int) dragonTiger.getDragonTigerLimit2().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit2().getMaxTotalBet();
            limit3 = "" + (int) dragonTiger.getDragonTigerLimit3().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit3().getMaxTotalBet();
            limit4 = "" + (int) dragonTiger.getDragonTigerLimit4().getMinTotalBet() + " - " + (int) dragonTiger.getDragonTigerLimit4().getMaxTotalBet();
        }
        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_baccarat_game;
    }


}

