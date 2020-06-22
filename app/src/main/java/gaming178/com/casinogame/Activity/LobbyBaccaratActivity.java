package gaming178.com.casinogame.Activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.Baccarat;
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
public class LobbyBaccaratActivity extends BaseActivity {

    @Bind(R.id.baccarat_content_parent_ll)
    LinearLayout baccaratContentParentLl;
    @Bind(R.id.baccarat_gridlayout1)
    GridLayout baccarat_head_road1;
    @Bind(R.id.ll_good_road_parent)
    View ll_good_road_parent1;
    @Bind(R.id.tv_good_road_name)
    TextView tv_good_road_name1;
    //    @Bind(R.id.baccarat_background_gridlayout1)
//    GridBackgroundView baccarat_background_head_road1;
    @Bind(R.id.baccarat_gridlayout2)
    GridLayout baccarat_big_road1;
    @Bind(R.id.baccarat_gridlayout3)
    GridLayout baccarat_bigeyes_road1;
    @Bind(R.id.baccarat_gridlayout4)
    FrameLayout fl4;//baccarat_smalleyes_road1
    @Bind(R.id.baccarat_gridlayout5)
    FrameLayout fl5;//baccarat_roach_road1
    private float density;
    private GridLayout baccarat_head_road2;
    private GridLayout baccarat_big_road2;
    private GridLayout baccarat_bigeyes_road2;
    private GridLayout baccarat_smalleyes_road2;
    private GridLayout baccarat_roach_road2;
    View ll_good_road_parent2;
    TextView tv_good_road_name2;

    private GridLayout baccarat_head_road3;
    private GridLayout baccarat_big_road3;
    private GridLayout baccarat_bigeyes_road3;
    private GridLayout baccarat_smalleyes_road3;
    private GridLayout baccarat_roach_road3;
    View ll_good_road_parent3;
    TextView tv_good_road_name3;

    private GridLayout baccarat_head_road61;
    private GridLayout baccarat_big_road61;
    private GridLayout baccarat_bigeyes_road61;
    private GridLayout baccarat_smalleyes_road61;
    private GridLayout baccarat_roach_road61;
    View ll_good_road_parent61;
    TextView tv_good_road_name61;

    private GridLayout baccarat_head_road62;
    private GridLayout baccarat_big_road62;
    private GridLayout baccarat_bigeyes_road62;
    private GridLayout baccarat_smalleyes_road62;
    private GridLayout baccarat_roach_road62;
    View ll_good_road_parent62;
    TextView tv_good_road_name62;

    private GridLayout baccarat_head_road63;
    private GridLayout baccarat_big_road63;
    private GridLayout baccarat_bigeyes_road63;
    private GridLayout baccarat_smalleyes_road63;
    private GridLayout baccarat_roach_road63;
    View ll_good_road_parent63;
    TextView tv_good_road_name63;

    private GridBackgroundView baccarat_background_head_road1;
    private GridBackgroundView baccarat_background_head_road2;
    private GridBackgroundView baccarat_background_head_road3;
    private GridBackgroundView baccarat_background_head_road61;
    private GridBackgroundView baccarat_background_head_road62;
    private GridBackgroundView baccarat_background_head_road63;

    private GridBackgroundView smallway1;
    private GridBackgroundView smallway2;
    private GridBackgroundView smallway3;
    private GridBackgroundView smallway61;
    private GridBackgroundView smallway62;
    private GridBackgroundView smallway63;

    private TextView tv_baccarat_timer01;
    private TextView tv_baccarat_timer02;
    private TextView tv_baccarat_timer03;
    private TextView tv_baccarat_timer61;
    private TextView tv_baccarat_timer62;
    private TextView tv_baccarat_timer63;

    private TextView tv_baccarat_shoe_number01;
    private TextView tv_baccarat_total_number01;
    private TextView tv_baccarat_banker_number01;
    private TextView tv_baccarat_player_number01;
    private TextView tv_baccarat_tie_number01;

    private TextView tv_baccarat_shoe_number02;
    private TextView tv_baccarat_total_number02;
    private TextView tv_baccarat_banker_number02;
    private TextView tv_baccarat_player_number02;
    private TextView tv_baccarat_tie_number02;

    private TextView tv_baccarat_shoe_number03;
    private TextView tv_baccarat_total_number03;
    private TextView tv_baccarat_banker_number03;
    private TextView tv_baccarat_player_number03;
    private TextView tv_baccarat_tie_number03;

    private TextView tv_baccarat_shoe_number61;
    private TextView tv_baccarat_total_number61;
    private TextView tv_baccarat_banker_number61;
    private TextView tv_baccarat_player_number61;
    private TextView tv_baccarat_tie_number61;

    private TextView tv_baccarat_shoe_number62;
    private TextView tv_baccarat_total_number62;
    private TextView tv_baccarat_banker_number62;
    private TextView tv_baccarat_player_number62;
    private TextView tv_baccarat_tie_number62;

    private TextView tv_baccarat_shoe_number63;
    private TextView tv_baccarat_total_number63;
    private TextView tv_baccarat_banker_number63;
    private TextView tv_baccarat_player_number63;
    private TextView tv_baccarat_tie_number63;

    private TextView tv_baccarat_table_name02;
    private TextView tv_baccarat_table_name03;
    private TextView tv_baccarat_table_name61;
    private TextView tv_baccarat_table_name62;
    private TextView tv_baccarat_table_name63;

    private TextView tv_baccarat_bp_number01;
    private TextView tv_baccarat_pp_number01;
    private TextView tv_baccarat_bp_number02;
    private TextView tv_baccarat_pp_number02;
    private TextView tv_baccarat_bp_number03;
    private TextView tv_baccarat_pp_number03;
    private TextView tv_baccarat_bp_number61;
    private TextView tv_baccarat_pp_number61;
    private TextView tv_baccarat_bp_number62;
    private TextView tv_baccarat_pp_number62;
    private TextView tv_baccarat_bp_number63;
    private TextView tv_baccarat_pp_number63;

    private GridLayout baccarat_smalleyes_road1;
    private GridLayout baccarat_roach_road1;
    private int baccaratTimer01 = 0;
    private int baccaratTimer02 = 0;
    private int baccaratTimer03 = 0;
    private int baccaratTimer61 = 0;
    private int baccaratTimer62 = 0;
    private int baccaratTimer63 = 0;

    private String baccaratGameNumber01 = "";
    private String baccaratGameNumber02 = "";
    private String baccaratGameNumber03 = "";
    private String baccaratGameNumber61 = "";
    private String baccaratGameNumber62 = "";
    private String baccaratGameNumber63 = "";

    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;
    private boolean bGetStatus = true;
    private ImageView baccarat_head_iv2;
    private ImageView baccarat_head_iv3;
    private ImageView baccarat_head_iv61;
    private ImageView baccarat_head_iv62;
    private ImageView baccarat_head_iv63;
    private ImageView baccarat_head_iv1;
    private ImageView baccarat_head_iv71;
    private TextView tvBaccaratDealerName1;
    private TextView tvBaccaratDealerName2;
    private TextView tvBaccaratDealerName3;
    private TextView tvBaccaratDealerName61;
    private TextView tvBaccaratDealerName62;
    private TextView tvBaccaratDealerName63;
    private TextView tv_baccarat_shoe_number71;
    private TextView tv_baccarat_timer71;
    private TextView tv_baccarat_total_number71;
    private TextView tv_baccarat_banker_number71;
    private TextView tv_baccarat_player_number71;
    private TextView tv_baccarat_tie_number71;
    private TextView tv_baccarat_table_name71;
    private TextView tv_baccarat_bp_number71;
    private TextView tv_baccarat_pp_number71;
    private int baccaratTimer71;
    private String baccaratGameNumber71;
    private GridLayout baccarat_head_road71;
    private GridLayout baccarat_big_road71;
    private GridLayout baccarat_bigeyes_road71;
    private GridLayout baccarat_smalleyes_road71;
    private GridLayout baccarat_roach_road71;
    View ll_good_road_parent71;
    TextView tv_good_road_name71;
    private View layout71;
    private View top_v;
    private View layout2;
    private View layout3;
    private View layout61;
    private View layout62;
    private View layout63;

    private View ll_big_road_parent2_1;
    private View ll_big_road_parent2_2;
    private View ll_big_road_parent2_3;
    private View ll_big_road_parent2_61;
    private View ll_big_road_parent2_62;
    private View ll_big_road_parent2_63;
    private View ll_big_road_parent2_71;

    private View hsv_small_road_1_1;
    private View hsv_small_road_1_2;
    private View hsv_small_road_1_3;
    private View hsv_small_road_1_61;
    private View hsv_small_road_1_62;
    private View hsv_small_road_1_63;
    private View hsv_small_road_1_71;

    private View hsv_small_road_2_1;
    private View hsv_small_road_2_2;
    private View hsv_small_road_2_3;
    private View hsv_small_road_2_61;
    private View hsv_small_road_2_62;
    private View hsv_small_road_2_63;
    private View hsv_small_road_2_71;

    private View hsv_small_road_3_1;
    private View hsv_small_road_3_2;
    private View hsv_small_road_3_3;
    private View hsv_small_road_3_61;
    private View hsv_small_road_3_62;
    private View hsv_small_road_3_63;
    private View hsv_small_road_3_71;

    public class UpdateStatus implements Runnable {

        public void run() {
            while (bGetStatus) {
                try {
                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1200);
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
                    updateTimer();
                    updateInterface();
                    InitRoad();
                    updateShuffling();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismissBlockDialog();
                        }
                    }, 500);
                    break;
            }
        }
    };

    private void updateShuffling() {
        if (afbApp.getBaccarat(1).getGameStatus() == 8) {
            shuffling1.setVisibility(View.VISIBLE);
        } else {
            shuffling1.setVisibility(View.GONE);
        }
        if (afbApp.getBaccarat(2).getGameStatus() == 8) {
            shuffling2.setVisibility(View.VISIBLE);
        } else {
            shuffling2.setVisibility(View.GONE);
        }
        if (afbApp.getBaccarat(3).getGameStatus() == 8) {
            shuffling3.setVisibility(View.VISIBLE);
        } else {
            shuffling3.setVisibility(View.GONE);
        }
        if (afbApp.getBaccarat(61).getGameStatus() == 8) {
            shuffling61.setVisibility(View.VISIBLE);
        } else {
            shuffling61.setVisibility(View.GONE);
        }
        if (afbApp.getBaccarat(62).getGameStatus() == 8) {
            shuffling62.setVisibility(View.VISIBLE);
        } else {
            shuffling62.setVisibility(View.GONE);
        }
        if (afbApp.getBaccarat(63).getGameStatus() == 8) {
            shuffling63.setVisibility(View.VISIBLE);
        } else {
            shuffling63.setVisibility(View.GONE);
        }
        if (shuffling7 != null) {
            if (afbApp.getBaccarat(71).getGameStatus() == 8) {
                shuffling7.setVisibility(View.VISIBLE);
            } else {
                shuffling7.setVisibility(View.GONE);
            }
        }
    }

    public void initUI() {
        for (int i = 1; i <= 3; i++) {
            afbApp.getBaccarat(i).setTimer(0);
            afbApp.getBaccarat(i).setBigRoadOld("");
        }
        for (int i = 61; i <= 63; i++) {
            afbApp.getBaccarat(i).setTimer(0);
            afbApp.getBaccarat(i).setBigRoadOld("");
        }
        initUI71();

        baccaratTimer01 = 0;
        baccaratTimer02 = 0;
        baccaratTimer03 = 0;
        baccaratTimer61 = 0;
        baccaratTimer62 = 0;
        baccaratTimer63 = 0;
        baccaratGameNumber01 = "";
        baccaratGameNumber02 = "";
        baccaratGameNumber03 = "";
        baccaratGameNumber61 = "";
        baccaratGameNumber62 = "";
        baccaratGameNumber63 = "";
        tv_baccarat_timer01.setText("0");
        tv_baccarat_timer02.setText("0");
        tv_baccarat_timer03.setText("0");
        tv_baccarat_timer61.setText("0");
        tv_baccarat_timer62.setText("0");
        tv_baccarat_timer63.setText("0");

    }

    private void initUI71() {
        afbApp.getBaccarat71().setTimer(0);
        afbApp.getBaccarat71().setBigRoadOld("");
        baccaratTimer71 = 0;
        baccaratGameNumber71 = "";
        tv_baccarat_timer71.setText("0");
    }

    public int getBaccaratTimer(int tableId) {
        switch (tableId) {
            case 1:
                return baccaratTimer01;
            case 2:
                return baccaratTimer02;
            case 3:
                return baccaratTimer03;
            case 61:
                return baccaratTimer61;
            case 62:
                return baccaratTimer62;
            case 63:
                return baccaratTimer63;
            default:
                return baccaratTimer01;
        }
    }

    public void setBaccaratTimer(int tableId, int timer) {
        switch (tableId) {
            case 1:
                baccaratTimer01 = timer;
                break;
            case 2:
                baccaratTimer02 = timer;
                break;
            case 3:
                baccaratTimer03 = timer;
                break;
            case 61:
                baccaratTimer61 = timer;
                break;
            case 62:
                baccaratTimer62 = timer;
                break;
            case 63:
                baccaratTimer63 = timer;
                break;


        }
    }

    public String getBaccaratGameNumber(int tableId) {
        switch (tableId) {
            case 1:
                return baccaratGameNumber01;
            case 2:
                return baccaratGameNumber02;
            case 3:
                return baccaratGameNumber03;
            case 61:
                return baccaratGameNumber61;
            case 62:
                return baccaratGameNumber62;
            case 63:
                return baccaratGameNumber63;
            default:
                return baccaratGameNumber01;

        }
    }

    public void setBaccaratGameNumber(int tableId, String number) {
        switch (tableId) {
            case 1:
                baccaratGameNumber01 = number;
                break;
            case 2:
                baccaratGameNumber02 = number;
                break;
            case 3:
                baccaratGameNumber03 = number;
                break;
            case 61:
                baccaratGameNumber61 = number;
                break;
            case 62:
                baccaratGameNumber62 = number;
                break;
            case 63:
                baccaratGameNumber63 = number;
                break;
            default:
                baccaratGameNumber01 = number;

        }
    }

    private TextView getBaccaratTimerTextView(int tableId) {
        switch (tableId) {
            case 1:
                return tv_baccarat_timer01;
            case 2:
                return tv_baccarat_timer02;
            case 3:
                return tv_baccarat_timer03;
            case 61:
                return tv_baccarat_timer61;
            case 62:
                return tv_baccarat_timer62;
            case 63:
                return tv_baccarat_timer63;
            default:
                return tv_baccarat_timer01;

        }
    }

    private TextView getBaccaratShoeGameNumberTextView(int tableId) {
        switch (tableId) {
            case 1:
                return tv_baccarat_shoe_number01;
            case 2:
                return tv_baccarat_shoe_number02;
            case 3:
                return tv_baccarat_shoe_number03;
            case 61:
                return tv_baccarat_shoe_number61;
            case 62:
                return tv_baccarat_shoe_number62;
            case 63:
                return tv_baccarat_shoe_number63;
            default:
                return tv_baccarat_shoe_number01;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initUI();

        startUpdateStatusThread();
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopUpdateStatusThread();
    }

    @Override
    protected void leftClick() {
        for (int i = 1; i <= 3; i++) {
            afbApp.getBaccarat(i).setBigRoadOld("");
        }
        for (int i = 61; i <= 63; i++) {
            afbApp.getBaccarat(i).setBigRoadOld("");
        }
        if (!(WebSiteUrl.isDomain && WebSiteUrl.GameType == 1))
            AppTool.activiyJump(mContext, LobbyActivity.class);
        else {
            afbApp.setbLogin(false);
        }
        finish();
    }

    public void startUpdateStatusThread() {
        if (updateStatus == null) {
            bGetStatus = true;
            updateStatus = new UpdateStatus();
            threadStatus = new Thread(updateStatus);
            threadStatus.start();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showBlockDialog();
    }

    public void stopUpdateStatusThread() {
        if (updateStatus != null) {
            bGetStatus = false;
            updateStatus = null;
            threadStatus = null;
        }
    }

    public void updateTimer(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (getBaccaratTimer(i) == 0 && afbApp.getBaccarat(i).getTimer() > 0) {
                if (!getBaccaratGameNumber(i).equals(afbApp.getBaccarat(i).getShoeNumber() + afbApp.getBaccarat(i).getGameNumber())) {
                    setBaccaratGameNumber(i, afbApp.getBaccarat(i).getShoeNumber() + afbApp.getBaccarat(i).getGameNumber());
                    setBaccaratTimer(i, afbApp.getBaccarat(i).getTimer());
                }
            }
        }
        if (end == 3) {
            updateTimer71();
        }
    }

    public void updateTimer() {
        updateTimer(1, 3);
        updateTimer(61, 63);
    }

    public void updateInterface(int start, int end) {
        for (int i = start; i <= end; i++) {
            if (getBaccaratTimer(i) > 0) {
                setBaccaratTimer(i, getBaccaratTimer(i) - 1);
                getBaccaratTimerTextView(i).setText("" + getBaccaratTimer(i));
                getBaccaratTimerTextView(i).setTextSize(18);
                getBaccaratShoeGameNumberTextView(i).setText("" + afbApp.getBaccarat(i).getShoeNumber() + " - " + afbApp.getBaccarat(i).getGameNumber());
            } else {
//                if (afbApp.getBaccarat(i).getGameStatus() == 2) {
//                    getBaccaratTimerTextView(i).setText(getString(R.string.START_DEALING));
//                    getBaccaratTimerTextView(i).setTextSize(12);
//                }
            }
        }
        if (end == 3) {
            updateInterface71();
        }

    }

    public void updateInterface71() {
        Log.d("Afb88", "updateInterface71--->" + baccaratTimer71);
        if (baccaratTimer71 > 0) {
            baccaratTimer71--;
            tv_baccarat_timer71.setText("" + baccaratTimer71);
            tv_baccarat_timer71.setTextSize(18);
            tv_baccarat_shoe_number71.setText("" + afbApp.getBaccarat71().getShoeNumber() + " - " + afbApp.getBaccarat71().getGameNumber());
        } else {
//            if (afbApp.getBaccarat71().getGameStatus() == 2) {
//                tv_baccarat_timer71.setText(getString(R.string.START_DEALING));
//                tv_baccarat_timer71.setTextSize(12);
//            }
        }
    }

    public void updateTimer71() {
        if (baccaratTimer71 == 0 && afbApp.getBaccarat71().getTimer() > 0) {
            if (!baccaratGameNumber71.equals(afbApp.getBaccarat71().getShoeNumber() + afbApp.getBaccarat71().getGameNumber())) {
                baccaratGameNumber71 = afbApp.getBaccarat71().getShoeNumber() + afbApp.getBaccarat71().getGameNumber();
                baccaratTimer71 = afbApp.getBaccarat71().getTimer();
                Log.d("Afb88", "updateTimer71--->" + baccaratTimer71);
            }
        }
    }

    public void updateInterface() {
        updateInterface(1, 3);
        updateInterface(61, 63);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        setMoreToolbar(true);
        initOldBigRoad();
        backTv.setVisibility(View.GONE);
        setLayout.setVisibility(View.GONE);
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText(getString(R.string.baccarat).toUpperCase());

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        InitControl();
        for (int i = 1; i <= 3; i++) {
            afbApp.getBaccarat(i).setShoeNumberOld(afbApp.getBaccarat(i).getShoeNumber());
        }
        for (int i = 61; i <= 63; i++) {
            afbApp.getBaccarat(i).setShoeNumberOld(afbApp.getBaccarat(i).getShoeNumber());
        }
        afbApp.getBaccarat71().setShoeNumberOld(afbApp.getBaccarat71().getShoeNumber());

    }

    public void InitRoad() {
//        road = getApp().getBaccarat02().ShowBaccaratBigRoad("5#5#7#1#5#1#1#1#1#5#5#1#5#1#1#2#1#6#9#7#1#1#5#1#1#5#9#9#1#1#1#1#3#1#1#1#1#5#3#5#5#5#1#5#1#1#7#1#",
//                mContext,baccarat_big_road2,6,density,1);
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(1), baccarat_head_road1, baccarat_big_road1, baccarat_bigeyes_road1, baccarat_smalleyes_road1, baccarat_roach_road1
                , tv_baccarat_shoe_number01, tv_baccarat_total_number01, tv_baccarat_banker_number01, tv_baccarat_player_number01, tv_baccarat_tie_number01, tv_baccarat_bp_number01,
                tv_baccarat_pp_number01, ll_good_road_parent1, tv_good_road_name1, false, ll_big_road_parent2_1, hsv_small_road_1_1, hsv_small_road_2_1, hsv_small_road_3_1);
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(2), baccarat_head_road2, baccarat_big_road2, baccarat_bigeyes_road2, baccarat_smalleyes_road2, baccarat_roach_road2
                , tv_baccarat_shoe_number02, tv_baccarat_total_number02, tv_baccarat_banker_number02, tv_baccarat_player_number02, tv_baccarat_tie_number02, tv_baccarat_bp_number02,
                tv_baccarat_pp_number02, ll_good_road_parent2, tv_good_road_name2, false, ll_big_road_parent2_2, hsv_small_road_1_2, hsv_small_road_2_2, hsv_small_road_3_2);
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(3), baccarat_head_road3, baccarat_big_road3, baccarat_bigeyes_road3, baccarat_smalleyes_road3, baccarat_roach_road3
                , tv_baccarat_shoe_number03, tv_baccarat_total_number03, tv_baccarat_banker_number03, tv_baccarat_player_number03, tv_baccarat_tie_number03, tv_baccarat_bp_number03,
                tv_baccarat_pp_number03, ll_good_road_parent3, tv_good_road_name3, false, ll_big_road_parent2_3, hsv_small_road_1_3, hsv_small_road_2_3, hsv_small_road_3_3);

        afbApp.updateRoad(mContext, density, getApp().getBaccarat(61), baccarat_head_road61, baccarat_big_road61, baccarat_bigeyes_road61, baccarat_smalleyes_road61, baccarat_roach_road61
                , tv_baccarat_shoe_number61, tv_baccarat_total_number61, tv_baccarat_banker_number61, tv_baccarat_player_number61, tv_baccarat_tie_number61, tv_baccarat_bp_number61,
                tv_baccarat_pp_number61, ll_good_road_parent61, tv_good_road_name61, false, ll_big_road_parent2_61, hsv_small_road_1_61, hsv_small_road_2_61, hsv_small_road_3_61);
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(62), baccarat_head_road62, baccarat_big_road62, baccarat_bigeyes_road62, baccarat_smalleyes_road62, baccarat_roach_road62
                , tv_baccarat_shoe_number62, tv_baccarat_total_number62, tv_baccarat_banker_number62, tv_baccarat_player_number62, tv_baccarat_tie_number62, tv_baccarat_bp_number62,
                tv_baccarat_pp_number62, ll_good_road_parent62, tv_good_road_name62, false, ll_big_road_parent2_62, hsv_small_road_1_62, hsv_small_road_2_62, hsv_small_road_3_62);
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(63), baccarat_head_road63, baccarat_big_road63, baccarat_bigeyes_road63, baccarat_smalleyes_road63, baccarat_roach_road63
                , tv_baccarat_shoe_number63, tv_baccarat_total_number63, tv_baccarat_banker_number63, tv_baccarat_player_number63, tv_baccarat_tie_number63, tv_baccarat_bp_number63,
                tv_baccarat_pp_number63, ll_good_road_parent63, tv_good_road_name63, false, ll_big_road_parent2_63, hsv_small_road_1_63, hsv_small_road_2_63, hsv_small_road_3_63);
        initRoad71();
    }

    private void initRoad71() {
        afbApp.updateRoad(mContext, density, getApp().getBaccarat(71), baccarat_head_road71, baccarat_big_road71, baccarat_bigeyes_road71, baccarat_smalleyes_road71, baccarat_roach_road71
                , tv_baccarat_shoe_number71, tv_baccarat_total_number71, tv_baccarat_banker_number71, tv_baccarat_player_number71, tv_baccarat_tie_number71, tv_baccarat_bp_number71,
                tv_baccarat_pp_number71, ll_good_road_parent71, tv_good_road_name71, false, ll_big_road_parent2_71, hsv_small_road_1_71, hsv_small_road_2_71, hsv_small_road_3_71);
    }

    private void addLayout4(LinearLayout baccaratContentParentLl) {
        layout71 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);
        shuffling7 = (TextView) layout71.findViewById(R.id.tv_shuffling);
        scrollView7 = (HorizontalScrollView) layout71.findViewById(R.id.layout1);
        baccaratContentParentLl.addView(layout71);
        baccarat_head_iv71 = (ImageView) layout71.findViewById(R.id.baccarat_head_iv);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl4.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv71);
        ll_good_road_parent71 = layout71.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name71 = layout71.findViewById(R.id.tv_good_road_name);
        baccarat_head_road71 = (GridLayout) layout71.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road71 = (GridLayout) layout71.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road71 = (GridLayout) layout71.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road71 = (GridLayout) layout71.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road71 = (GridLayout) layout71.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);
        tv_baccarat_shoe_number71 = (TextView) layout71.findViewById(R.id.text_shoe_game_number);
        tv_baccarat_timer71 = (TextView) layout71.findViewById(R.id.baccarat_status_tv);
        tv_baccarat_total_number71 = (TextView) layout71.findViewById(R.id.text_total);
        tv_baccarat_banker_number71 = (TextView) layout71.findViewById(R.id.text_banker);

        tv_baccarat_player_number71 = (TextView) layout71.findViewById(R.id.text_player);
        tv_baccarat_tie_number71 = (TextView) layout71.findViewById(R.id.text_tie);

        tv_baccarat_table_name71 = (TextView) layout71.findViewById(R.id.tv_baccarat_table_name);
        tv_baccarat_bp_number71 = (TextView) layout71.findViewById(R.id.text_bp);
        tv_baccarat_pp_number71 = (TextView) layout71.findViewById(R.id.text_pp);
        tv_baccarat_table_name71.setText("BM1");
        layout71.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
    }


    @Bind(R.id.layout1)
    HorizontalScrollView scrollView1;
    HorizontalScrollView scrollView2;
    HorizontalScrollView scrollView3;
    HorizontalScrollView scrollView61;
    HorizontalScrollView scrollView62;
    HorizontalScrollView scrollView63;
    HorizontalScrollView scrollView7;

    private void setLayoutLayoutParams(View view) {
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams();
        params.width = ScreenUtil.dip2px(mContext, 333);
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        view.setLayoutParams(params);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Configuration mConfiguration = getResources().getConfiguration();
        if (AppTool.isPad(mContext) && mConfiguration.orientation == mConfiguration.ORIENTATION_LANDSCAPE) {
            setLayoutLayoutParams(scrollView1);
            setLayoutLayoutParams(scrollView2);
            setLayoutLayoutParams(scrollView3);
            setLayoutLayoutParams(scrollView61);
            setLayoutLayoutParams(scrollView62);
            setLayoutLayoutParams(scrollView63);
            setLayoutLayoutParams(scrollView7);
        }
    }

    private TextView shuffling1;
    private TextView shuffling2;
    private TextView shuffling3;
    private TextView shuffling61;
    private TextView shuffling62;
    private TextView shuffling63;
    private TextView shuffling7;

    public void InitControl() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        density = ScreenUtil.getDisplayMetrics(mContext).density;
        shuffling1 = (TextView) findViewById(R.id.tv_shuffling);
        layout2 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);
        layout3 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);
        layout61 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);
        layout62 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);
        layout63 = LayoutInflater.from(mContext).inflate(R.layout.item_baccarat_content1, null);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_big_road_parent2_1 = findViewById(R.id.ll_big_road_parent2);
            ll_big_road_parent2_2 = layout2.findViewById(R.id.ll_big_road_parent2);
            ll_big_road_parent2_3 = layout3.findViewById(R.id.ll_big_road_parent2);
            ll_big_road_parent2_61 = layout61.findViewById(R.id.ll_big_road_parent2);
            ll_big_road_parent2_62 = layout62.findViewById(R.id.ll_big_road_parent2);
            ll_big_road_parent2_63 = layout63.findViewById(R.id.ll_big_road_parent2);

            hsv_small_road_1_1 = findViewById(R.id.hsv_small_road_1);
            hsv_small_road_1_2 = layout2.findViewById(R.id.hsv_small_road_1);
            hsv_small_road_1_3 = layout3.findViewById(R.id.hsv_small_road_1);
            hsv_small_road_1_61 = layout61.findViewById(R.id.hsv_small_road_1);
            hsv_small_road_1_62 = layout62.findViewById(R.id.hsv_small_road_1);
            hsv_small_road_1_63 = layout63.findViewById(R.id.hsv_small_road_1);

            hsv_small_road_2_1 = findViewById(R.id.hsv_small_road_2);
            hsv_small_road_2_2 = layout2.findViewById(R.id.hsv_small_road_2);
            hsv_small_road_2_3 = layout3.findViewById(R.id.hsv_small_road_2);
            hsv_small_road_2_61 = layout61.findViewById(R.id.hsv_small_road_2);
            hsv_small_road_2_62 = layout62.findViewById(R.id.hsv_small_road_2);
            hsv_small_road_2_63 = layout63.findViewById(R.id.hsv_small_road_2);

            hsv_small_road_3_1 = findViewById(R.id.hsv_small_road_3);
            hsv_small_road_3_2 = layout2.findViewById(R.id.hsv_small_road_3);
            hsv_small_road_3_3 = layout3.findViewById(R.id.hsv_small_road_3);
            hsv_small_road_3_61 = layout61.findViewById(R.id.hsv_small_road_3);
            hsv_small_road_3_62 = layout62.findViewById(R.id.hsv_small_road_3);
            hsv_small_road_3_63 = layout63.findViewById(R.id.hsv_small_road_3);
        } else {
            ll_big_road_parent2_1 = findViewById(R.id.ll_big_road_parent2_landscape);
            ll_big_road_parent2_2 = layout2.findViewById(R.id.ll_big_road_parent2_landscape);
            ll_big_road_parent2_3 = layout3.findViewById(R.id.ll_big_road_parent2_landscape);
            ll_big_road_parent2_61 = layout61.findViewById(R.id.ll_big_road_parent2_landscape);
            ll_big_road_parent2_62 = layout62.findViewById(R.id.ll_big_road_parent2_landscape);
            ll_big_road_parent2_63 = layout63.findViewById(R.id.ll_big_road_parent2_landscape);

            hsv_small_road_1_1 = findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_1_2 = layout2.findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_1_3 = layout3.findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_1_61 = layout61.findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_1_62 = layout62.findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_1_63 = layout63.findViewById(R.id.hsv_small_road_1_landscape);

            hsv_small_road_2_1 = findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_2_2 = layout2.findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_2_3 = layout3.findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_2_61 = layout61.findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_2_62 = layout62.findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_2_63 = layout63.findViewById(R.id.hsv_small_road_2_landscape);

            hsv_small_road_3_1 = findViewById(R.id.hsv_small_road_3_landscape);
            hsv_small_road_3_2 = layout2.findViewById(R.id.hsv_small_road_3_landscape);
            hsv_small_road_3_3 = layout3.findViewById(R.id.hsv_small_road_3_landscape);
            hsv_small_road_3_61 = layout61.findViewById(R.id.hsv_small_road_3_landscape);
            hsv_small_road_3_62 = layout62.findViewById(R.id.hsv_small_road_3_landscape);
            hsv_small_road_3_63 = layout63.findViewById(R.id.hsv_small_road_3_landscape);
        }
        shuffling2 = (TextView) layout2.findViewById(R.id.tv_shuffling);
        shuffling3 = (TextView) layout3.findViewById(R.id.tv_shuffling);
        shuffling61 = (TextView) layout61.findViewById(R.id.tv_shuffling);
        shuffling62 = (TextView) layout62.findViewById(R.id.tv_shuffling);
        shuffling63 = (TextView) layout63.findViewById(R.id.tv_shuffling);
        scrollView2 = (HorizontalScrollView) layout2.findViewById(R.id.layout1);
        scrollView3 = (HorizontalScrollView) layout3.findViewById(R.id.layout1);
        scrollView61 = (HorizontalScrollView) layout61.findViewById(R.id.layout1);
        scrollView62 = (HorizontalScrollView) layout62.findViewById(R.id.layout1);
        scrollView63 = (HorizontalScrollView) layout63.findViewById(R.id.layout1);
        baccaratContentParentLl.addView(layout2);
        baccaratContentParentLl.addView(layout3);
        baccaratContentParentLl.addView(layout61);
        baccaratContentParentLl.addView(layout62);
        baccaratContentParentLl.addView(layout63);

        baccarat_smalleyes_road1 = (GridLayout) fl4.findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road1 = (GridLayout) fl5.findViewById(R.id.baccarat_gridlayout3);

        ll_good_road_parent2 = layout2.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name2 = layout2.findViewById(R.id.tv_good_road_name);
        baccarat_head_road2 = (GridLayout) layout2.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road2 = (GridLayout) layout2.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road2 = (GridLayout) layout2.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road2 = (GridLayout) layout2.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road2 = (GridLayout) layout2.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);

        ll_good_road_parent3 = layout3.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name3 = layout3.findViewById(R.id.tv_good_road_name);
        baccarat_head_road3 = (GridLayout) layout3.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road3 = (GridLayout) layout3.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road3 = (GridLayout) layout3.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road3 = (GridLayout) layout3.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road3 = (GridLayout) layout3.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);

        ll_good_road_parent61 = layout61.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name61 = layout61.findViewById(R.id.tv_good_road_name);
        baccarat_head_road61 = (GridLayout) layout61.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road61 = (GridLayout) layout61.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road61 = (GridLayout) layout61.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road61 = (GridLayout) layout61.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road61 = (GridLayout) layout61.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);

        ll_good_road_parent62 = layout62.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name62 = layout62.findViewById(R.id.tv_good_road_name);
        baccarat_head_road62 = (GridLayout) layout62.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road62 = (GridLayout) layout62.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road62 = (GridLayout) layout62.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road62 = (GridLayout) layout62.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road62 = (GridLayout) layout62.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);

        ll_good_road_parent63 = layout63.findViewById(R.id.ll_good_road_parent);
        tv_good_road_name63 = layout63.findViewById(R.id.tv_good_road_name);
        baccarat_head_road63 = (GridLayout) layout63.findViewById(R.id.baccarat_gridlayout1);
        baccarat_big_road63 = (GridLayout) layout63.findViewById(R.id.baccarat_gridlayout2);
        baccarat_bigeyes_road63 = (GridLayout) layout63.findViewById(R.id.baccarat_gridlayout3);
        baccarat_smalleyes_road63 = (GridLayout) layout63.findViewById(R.id.baccarat_gridlayout4).findViewById(R.id.baccarat_gridlayout3);
        baccarat_roach_road63 = (GridLayout) layout63.findViewById(R.id.baccarat_gridlayout5).findViewById(R.id.baccarat_gridlayout3);

        tv_baccarat_timer01 = (TextView) findViewById(R.id.baccarat_status_tv);
        tv_baccarat_timer02 = (TextView) layout2.findViewById(R.id.baccarat_status_tv);
        tv_baccarat_timer03 = (TextView) layout3.findViewById(R.id.baccarat_status_tv);
        tv_baccarat_timer61 = (TextView) layout61.findViewById(R.id.baccarat_status_tv);
        tv_baccarat_timer62 = (TextView) layout62.findViewById(R.id.baccarat_status_tv);
        tv_baccarat_timer63 = (TextView) layout63.findViewById(R.id.baccarat_status_tv);

        tv_baccarat_shoe_number01 = (TextView) findViewById(R.id.text_shoe_game_number);
        tv_baccarat_shoe_number02 = (TextView) layout2.findViewById(R.id.text_shoe_game_number);
        tv_baccarat_shoe_number03 = (TextView) layout3.findViewById(R.id.text_shoe_game_number);
        tv_baccarat_shoe_number61 = (TextView) layout61.findViewById(R.id.text_shoe_game_number);
        tv_baccarat_shoe_number62 = (TextView) layout62.findViewById(R.id.text_shoe_game_number);
        tv_baccarat_shoe_number63 = (TextView) layout63.findViewById(R.id.text_shoe_game_number);

        tv_baccarat_total_number01 = (TextView) findViewById(R.id.text_total);
        tv_baccarat_total_number02 = (TextView) layout2.findViewById(R.id.text_total);
        tv_baccarat_total_number03 = (TextView) layout3.findViewById(R.id.text_total);
        tv_baccarat_total_number61 = (TextView) layout61.findViewById(R.id.text_total);
        tv_baccarat_total_number62 = (TextView) layout62.findViewById(R.id.text_total);
        tv_baccarat_total_number63 = (TextView) layout63.findViewById(R.id.text_total);

        tv_baccarat_banker_number01 = (TextView) findViewById(R.id.text_banker);
        tv_baccarat_banker_number02 = (TextView) layout2.findViewById(R.id.text_banker);
        tv_baccarat_banker_number03 = (TextView) layout3.findViewById(R.id.text_banker);
        tv_baccarat_banker_number61 = (TextView) layout61.findViewById(R.id.text_banker);
        tv_baccarat_banker_number62 = (TextView) layout62.findViewById(R.id.text_banker);
        tv_baccarat_banker_number63 = (TextView) layout63.findViewById(R.id.text_banker);

        tv_baccarat_player_number01 = (TextView) findViewById(R.id.text_player);
        tv_baccarat_player_number02 = (TextView) layout2.findViewById(R.id.text_player);
        tv_baccarat_player_number03 = (TextView) layout3.findViewById(R.id.text_player);
        tv_baccarat_player_number61 = (TextView) layout61.findViewById(R.id.text_player);
        tv_baccarat_player_number62 = (TextView) layout62.findViewById(R.id.text_player);
        tv_baccarat_player_number63 = (TextView) layout63.findViewById(R.id.text_player);

        tv_baccarat_tie_number01 = (TextView) findViewById(R.id.text_tie);
        tv_baccarat_tie_number02 = (TextView) layout2.findViewById(R.id.text_tie);
        tv_baccarat_tie_number03 = (TextView) layout3.findViewById(R.id.text_tie);
        tv_baccarat_tie_number61 = (TextView) layout61.findViewById(R.id.text_tie);
        tv_baccarat_tie_number62 = (TextView) layout62.findViewById(R.id.text_tie);
        tv_baccarat_tie_number63 = (TextView) layout63.findViewById(R.id.text_tie);

        tv_baccarat_table_name02 = (TextView) layout2.findViewById(R.id.tv_baccarat_table_name);
        tv_baccarat_table_name03 = (TextView) layout3.findViewById(R.id.tv_baccarat_table_name);
        tv_baccarat_table_name61 = (TextView) layout61.findViewById(R.id.tv_baccarat_table_name);
        tv_baccarat_table_name62 = (TextView) layout62.findViewById(R.id.tv_baccarat_table_name);
        tv_baccarat_table_name63 = (TextView) layout63.findViewById(R.id.tv_baccarat_table_name);

        tv_baccarat_bp_number01 = (TextView) findViewById(R.id.text_bp);
        tv_baccarat_bp_number02 = (TextView) layout2.findViewById(R.id.text_bp);
        tv_baccarat_bp_number03 = (TextView) layout3.findViewById(R.id.text_bp);
        tv_baccarat_bp_number61 = (TextView) layout61.findViewById(R.id.text_bp);
        tv_baccarat_bp_number62 = (TextView) layout62.findViewById(R.id.text_bp);
        tv_baccarat_bp_number63 = (TextView) layout63.findViewById(R.id.text_bp);

        tv_baccarat_pp_number01 = (TextView) findViewById(R.id.text_pp);
        tv_baccarat_pp_number02 = (TextView) layout2.findViewById(R.id.text_pp);
        tv_baccarat_pp_number03 = (TextView) layout3.findViewById(R.id.text_pp);
        tv_baccarat_pp_number61 = (TextView) layout61.findViewById(R.id.text_pp);
        tv_baccarat_pp_number62 = (TextView) layout62.findViewById(R.id.text_pp);
        tv_baccarat_pp_number63 = (TextView) layout63.findViewById(R.id.text_pp);


        setToolbarNameAndBalance();
        baccarat_head_iv1 = (ImageView) findViewById(R.id.baccarat_head_iv);
        baccarat_head_iv2 = (ImageView) layout2.findViewById(R.id.baccarat_head_iv);
        baccarat_head_iv3 = (ImageView) layout3.findViewById(R.id.baccarat_head_iv);
        baccarat_head_iv61 = (ImageView) layout61.findViewById(R.id.baccarat_head_iv);
        baccarat_head_iv62 = (ImageView) layout62.findViewById(R.id.baccarat_head_iv);
        baccarat_head_iv63 = (ImageView) layout63.findViewById(R.id.baccarat_head_iv);

        tvBaccaratDealerName1 = (TextView) findViewById(R.id.tv_baccarat_dealer_name);
        tvBaccaratDealerName2 = (TextView) layout2.findViewById(R.id.tv_baccarat_dealer_name);
        tvBaccaratDealerName3 = (TextView) layout3.findViewById(R.id.tv_baccarat_dealer_name);
        tvBaccaratDealerName61 = (TextView) layout61.findViewById(R.id.tv_baccarat_dealer_name);
        tvBaccaratDealerName62 = (TextView) layout62.findViewById(R.id.tv_baccarat_dealer_name);
        tvBaccaratDealerName63 = (TextView) layout63.findViewById(R.id.tv_baccarat_dealer_name);

        tvBaccaratDealerName1.setTextColor(getResources().getColor(R.color.orange_dealer_name));
        tvBaccaratDealerName2.setTextColor(getResources().getColor(R.color.orange_dealer_name));
        tvBaccaratDealerName3.setTextColor(getResources().getColor(R.color.orange_dealer_name));
        tvBaccaratDealerName61.setTextColor(getResources().getColor(R.color.orange_dealer_name));
        tvBaccaratDealerName62.setTextColor(getResources().getColor(R.color.orange_dealer_name));
        tvBaccaratDealerName63.setTextColor(getResources().getColor(R.color.orange_dealer_name));

        baccarat_background_head_road1 = (GridBackgroundView) findViewById(R.id.baccarat_background_gridlayout1);
        baccarat_background_head_road2 = (GridBackgroundView) layout2.findViewById(R.id.baccarat_background_gridlayout1);
        baccarat_background_head_road3 = (GridBackgroundView) layout3.findViewById(R.id.baccarat_background_gridlayout1);
        baccarat_background_head_road61 = (GridBackgroundView) layout61.findViewById(R.id.baccarat_background_gridlayout1);
        baccarat_background_head_road62 = (GridBackgroundView) layout62.findViewById(R.id.baccarat_background_gridlayout1);
        baccarat_background_head_road63 = (GridBackgroundView) layout63.findViewById(R.id.baccarat_background_gridlayout1);
        smallway1 = (GridBackgroundView) findViewById(R.id.smallway_item);
        smallway2 = (GridBackgroundView) layout2.findViewById(R.id.smallway_item);
        smallway3 = (GridBackgroundView) layout3.findViewById(R.id.smallway_item);
        smallway61 = (GridBackgroundView) layout61.findViewById(R.id.smallway_item);
        smallway62 = (GridBackgroundView) layout62.findViewById(R.id.smallway_item);
        smallway63 = (GridBackgroundView) layout63.findViewById(R.id.smallway_item);
        View smallway21 = findViewById(R.id.baccarat_gridlayout4);
        View smallway22 = layout2.findViewById(R.id.baccarat_gridlayout4);
        View smallway23 = layout3.findViewById(R.id.baccarat_gridlayout4);
        View smallway261 = layout61.findViewById(R.id.baccarat_gridlayout4);
        View smallway262 = layout62.findViewById(R.id.baccarat_gridlayout4);
        View smallway263 = layout63.findViewById(R.id.baccarat_gridlayout4);
        View smallway31 = findViewById(R.id.baccarat_gridlayout5);
        View smallway32 = layout2.findViewById(R.id.baccarat_gridlayout5);
        View smallway33 = layout3.findViewById(R.id.baccarat_gridlayout5);
        View smallway361 = layout61.findViewById(R.id.baccarat_gridlayout5);
        View smallway362 = layout62.findViewById(R.id.baccarat_gridlayout5);
        View smallway363 = layout63.findViewById(R.id.baccarat_gridlayout5);
        View bigway1 = findViewById(R.id.big_way);
        View bigway2 = layout2.findViewById(R.id.big_way);
        View bigway3 = layout3.findViewById(R.id.big_way);
        View bigway61 = layout61.findViewById(R.id.big_way);
        View bigway62 = layout62.findViewById(R.id.big_way);
        View bigway63 = layout63.findViewById(R.id.big_way);
        ((TextView) findViewById(R.id.tv_baccarat_table_name)).setText("LB1");
        tv_baccarat_table_name02.setText("LB2");
        tv_baccarat_table_name03.setText("LB3");
        tv_baccarat_table_name61.setText("LB5");
        tv_baccarat_table_name62.setText("LB6");
        tv_baccarat_table_name63.setText("LB7");

        findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        layout2.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        layout3.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        layout61.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        layout62.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        layout63.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        addLayout4(baccaratContentParentLl);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            ll_big_road_parent2_71 = layout71.findViewById(R.id.ll_big_road_parent2);
            hsv_small_road_1_71 = layout71.findViewById(R.id.hsv_small_road_1);
            hsv_small_road_2_71 = layout71.findViewById(R.id.hsv_small_road_2);
            hsv_small_road_3_71 = layout71.findViewById(R.id.hsv_small_road_3);
        } else {
            ll_big_road_parent2_71 = layout71.findViewById(R.id.ll_big_road_parent2_landscape);
            hsv_small_road_1_71 = layout71.findViewById(R.id.hsv_small_road_1_landscape);
            hsv_small_road_2_71 = layout71.findViewById(R.id.hsv_small_road_2_landscape);
            hsv_small_road_3_71 = layout71.findViewById(R.id.hsv_small_road_3_landscape);
        }

        baccarat_head_road1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        smallway1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        smallway21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        smallway31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });
        bigway1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 1, "LB1", findViewById(R.id.ll_parent_limit));
            }
        });

        baccarat_head_road2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        bigway2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 2, "LB2", layout2.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_head_road3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });
        bigway3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 3, "LB3", layout3.findViewById(R.id.ll_parent_limit));
            }
        });

        baccarat_head_road61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway261.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway361.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });
        bigway61.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 61, "LB5", layout61.findViewById(R.id.ll_parent_limit));
            }
        });

        baccarat_head_road62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway262.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway362.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });
        bigway62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 62, "LB6", layout62.findViewById(R.id.ll_parent_limit));
            }
        });

        baccarat_head_road63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        baccarat_background_head_road63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway263.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        smallway363.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });
        bigway63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 63, "LB7", layout63.findViewById(R.id.ll_parent_limit));
            }
        });

        layout71.findViewById(R.id.baccarat_gridlayout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.baccarat_background_gridlayout1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.ll_baccarat_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.big_way).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.smallway_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.baccarat_gridlayout4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });
        layout71.findViewById(R.id.baccarat_gridlayout5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 71, "BM1", layout71.findViewById(R.id.ll_parent_limit));
            }
        });

        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl1.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv1);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl2.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv2);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl3.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv3);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl61.jpg").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv61);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl62.jpg").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv62);
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "baccarat_girl63.jpg").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(baccarat_head_iv63);
    }

    public void clickGrid(View v, final int tableId, final String tableNumber, View top_v) {
        if (afbApp.getBaccarat(tableId).getStatus() != 1) {
            Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
            return;
        }
        initLimitPop(v, tableId, tableNumber, top_v);
    }

//    PopChoiceLanguage<String> centerPop;

    public void initLimitPop(View view, final int tableId, final String tableNumber, final View top_v) {
        findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout2 != null)
            layout2.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout3 != null)
            layout3.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout61 != null)
            layout61.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout62 != null)
            layout62.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout63 != null)
            layout63.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        if (layout71 != null)
            layout71.findViewById(R.id.ll_parent_limit).setVisibility(View.GONE);
        RecyclerView recyclerView = (RecyclerView) top_v.findViewById(R.id.base_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mContext, getLimitData(tableId), R.layout.item_popupwindow_text_select) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.pop_text_tv, item);
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String s, int position) {
                if ("0 - 0".endsWith(s))
                    return;
                getApp().getBaccarat(tableId).setLimitIndex(position + 1);
                if (WebSiteUrl.isDomain) {
                    afbApp.setTableId(tableId);
                    afbApp.setSerialId(0);
                    afbApp.setAreaId(0);
                    afbApp.setbLobby(false);
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
//                    if (tableId <= 3 || tableId == 71) {
//                        bundle.putBoolean("baccaratA", true);
//                    } else {
//                        bundle.putBoolean("baccaratA", false);
//                    }
                    bundle.putBoolean("baccaratA", true);
                    AppTool.activiyJump(mContext, BaccaratActivity.class, bundle);

                } else {
                    /*
                     * */
                    Bundle bundle = new Bundle();
                    bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + tableId);
                    afbApp.setTableId(tableId);
                    bundle.putBoolean("baccaratA", true);
//                afbApp.setSerialId(serialId);
//                afbApp.setAreaId(areaId);
                    afbApp.setbLobby(false);
                    AppTool.activiyJump(mContext, BaccaratActivity.class, bundle);

                }

            }
        });
        recyclerView.setAdapter(baseRecyclerAdapter);
        top_v.findViewById(R.id.pop_close_iv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                top_v.setVisibility(View.GONE);
            }
        });
        ((TextView) top_v.findViewById(R.id.tv_table_game_number)).setText(tableNumber);
        top_v.setVisibility(View.VISIBLE);

    }

    private ArrayList<String> getLimitData(int tableId) {
        Baccarat baccarat;
        String limit1 = "0 - 0";
        String limit2 = "0 - 0";
        String limit3 = "0 - 0";
        String limit4 = "0 - 0";
        baccarat = getApp().getBaccarat(tableId);

        if (baccarat != null) {
            limit1 = "" + (int) baccarat.getBaccaratLimit1().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit1().getMaxTotalBet();
            limit2 = "" + (int) baccarat.getBaccaratLimit2().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit2().getMaxTotalBet();
            limit3 = "" + (int) baccarat.getBaccaratLimit3().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit3().getMaxTotalBet();
            limit4 = "" + (int) baccarat.getBaccaratLimit4().getMinTotalBet() + " - " + (int) baccarat.getBaccaratLimit4().getMaxTotalBet();
        }
        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));
//        centerPop.setData());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_baccarat_game;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initOrientation();
    }


}

