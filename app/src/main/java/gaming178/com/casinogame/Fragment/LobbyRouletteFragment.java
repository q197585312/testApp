package gaming178.com.casinogame.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
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
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Activity.LobbyRouletteActivity;
import gaming178.com.casinogame.Activity.RouletteActivity;
import gaming178.com.casinogame.Bean.Roulette;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;

public class LobbyRouletteFragment extends BaseFragment {
    @BindView(R2.id.gd__base_rv)
    RecyclerView gridView;


    private TextView tv_roulette_timer01;
    private TextView tv_game_number01;
    private TextView tv_roulette_red01;
    private TextView tv_roulette_black01;
    private TextView tv_roulette_zero01;
    private TextView tv_roulette_even01;
    private TextView tv_roulette_odd01;
    private TextView tv_roulette_big01;
    private TextView tv_roulette_small01;
    private BaseRecyclerAdapter<String> adapter;
    private LinearLayout ll_roulete_road;

    private int rouletteTimer01 = 0;
    private UpdateStatus updateStatus = null;
    private Thread threadStatus = null;


    private boolean bGetStatus = true;
    private String gameNumber = "";

    public class UpdateStatus implements Runnable {
        int iError = 0;

        public void run() {
            while (bGetStatus) {
                try {

                    handler.sendEmptyMessage(HandlerCode.UPDATE_STATUS);
                    Thread.sleep(1020);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {

            if (!baseActivity.isAttached) {
                return;
            }
            switch (msg.what) {
                case HandlerCode.UPDATE_STATUS:
                    //  InitRoad();
                    updateTimer();
                    updateInterface();
                    initRoad();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            baseActivity.dismissBlockDialog();
                        }
                    }, 500);
                    break;

            }
            //

        }
    };

    public void initRoad() {
        updateRoad(baseActivity.mAppViewModel.getRoulette01(), tv_game_number01, tv_roulette_red01, tv_roulette_black01, tv_roulette_zero01, tv_roulette_even01, tv_roulette_odd01, tv_roulette_big01, tv_roulette_small01);

    }


    @BindView(R2.id.gd__img_head)
    ImageView img_head;

    @Override
    protected void initData(Bundle savedInstanceState) {
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "roulette.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(img_head);
        baseActivity.setMoreToolbar(true);
        baseActivity.backTv.setVisibility(View.GONE);
        baseActivity.setLayout.setVisibility(View.GONE);

        baseActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initControl();

        baseActivity.setToolbarNameAndBalance();
        baseActivity.girlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLimit(v, 21);
            }
        });
        baseActivity.titleTv.setText(getString(R.string.roulette).toUpperCase());
        baseActivity.setTitleChangeGame(baseActivity.titleTv);
        baseActivity.initOldBigRoad();
    }

    public void initUI() {
        baseActivity.mAppViewModel.getRoulette01().setRoadOld("");
        baseActivity.mAppViewModel.getRoulette01().setTimer(0);
        rouletteTimer01 = 0;
        gameNumber = "";
    }

    @Override
    public int getFragmentLayoutRes() {
        return R.layout.gd_activity_list_content_base;
    }

    @Override
    public void onResume() {
        super.onResume();
        initUI();
        startUpdateStatusThread();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            stopUpdateStatusThread();
        } else {
            initUI();
            startUpdateStatusThread();
        }
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
        if (rouletteTimer01 == 0 && baseActivity.mAppViewModel.getRoulette01().getTimer() > 0) {
            //   Log.i(WebSiteUrl.Tag, "updateTimer="+mAppViewModel.getRoulette01().getTimer());
            if (!gameNumber.equals(baseActivity.mAppViewModel.getRoulette01().getGameNumber())) {
                gameNumber = baseActivity.mAppViewModel.getRoulette01().getGameNumber();
                rouletteTimer01 = baseActivity.mAppViewModel.getRoulette01().getTimer();
            }
        }

    }

    public void updateInterface() {
        if (rouletteTimer01 > 0) {
            rouletteTimer01--;
            if (tv_roulette_timer01 != null) {
                tv_roulette_timer01.setText("" + rouletteTimer01);
                tv_roulette_timer01.setTextSize(18);
                tv_game_number01.setText("" + baseActivity.mAppViewModel.getRoulette01().getGameNumber());
            }

        } else {
//            if (mAppViewModel.getRoulette01().getGameStatus() == 2) {
//                if (tv_roulette_timer01 != null) {
//                    tv_roulette_timer01.setText(getString(R.string.START_DEALING));
//                    tv_roulette_timer01.setTextSize(8);
//                }
//            }
        }

    }

    public void initControl() {
        tv_roulette_timer01 = (TextView) rootView.findViewById(R.id.gd__roulette_status_tv);
        tv_game_number01 = (TextView) rootView.findViewById(R.id.gd__text_shoe_game_number);
        tv_roulette_red01 = (TextView) rootView.findViewById(R.id.gd__text_red);
        tv_roulette_black01 = (TextView) rootView.findViewById(R.id.gd__text_black);
        tv_roulette_zero01 = (TextView) rootView.findViewById(R.id.gd__text_zero);
        tv_roulette_even01 = (TextView) rootView.findViewById(R.id.gd__text_even);
        tv_roulette_odd01 = (TextView) rootView.findViewById(R.id.gd__text_odd);
        tv_roulette_big01 = (TextView) rootView.findViewById(R.id.gd__text_big);
        tv_roulette_small01 = (TextView) rootView.findViewById(R.id.gd__text_small);
        baseActivity.girlLayout = (LinearLayout) rootView.findViewById(R.id.gd__ll_layout_girl);
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            baseActivity.girlLayout.setVisibility(View.VISIBLE);
        }else {
            baseActivity.girlLayout.setVisibility(View.GONE);
        }
        baseActivity.ll_more_info = (LinearLayout) rootView.findViewById(R.id.gd__ll_more_info);

        ((TextView) rootView.findViewById(R.id.gd__tv_roulette_table_name)).setText("RL1");
        //    getString(R.string.baccarat).toUpperCase()+"01"
        gridView.setLayoutManager(new GridLayoutManager(mContext, 11));


        adapter = new BaseRecyclerAdapter<String>(mContext, new ArrayList<>(Collections.nCopies(22, "10")), R.layout.gd_item_roulette_content) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
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
                        holder.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_black_brown_stroke_yellow);
                        break;
                    case "0":
                        holder.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_green_brown_stroke_yellow);
                        break;
                    default:
                        holder.setBackgroundRes(R.id.gd__roulette_title_tv, R.drawable.gd_rectangle_red_brown_stroke_yellow);
                        break;
                }
                holder.setText(R.id.gd__roulette_title_tv, item);

            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                showLimit(view, 21);
            }
        });
        gridView.setAdapter(adapter);
    }

    public void updateRoad(Roulette roulette
            , TextView tv_roulette_number, TextView tv_red, TextView tv_black, TextView tv_zero, TextView tv_even, TextView tv_odd, TextView tv_big, TextView tv_small) {
        if (roulette.getStatus() == 0)//清除路子信息
        {


            tv_roulette_number.setText("0");
            tv_red.setText("0");
            tv_black.setText("0");
            tv_zero.setText("0");

            //需要显示关桌
        } else {//显示路子
            if (roulette.getRoad() != null && !roulette.getRoad().equals(roulette.getRoadOld())) {
                roulette.setRoadOld(roulette.getRoad());
                String roadDetail[] = roulette.getRoad().split("\\#");
                if (roadDetail != null) {
                    List<String> listItem = new ArrayList<String>();
                    int start = 99;
                    int end = 78;
                    if (roadDetail.length == 99) {
                        start = 98;
                        end = 77;
                    }
                    for (int i = start; i >= end; i--) {
                        listItem.add(roadDetail[i]);
                    }
                    adapter.addAllAndClear(listItem);
                }
                //      Log.i(WebSiteUrl.Tag,"updateRoad(),TableID="+roulette.getTableName()+",Luzi roads="+roulette.getRoadOld()+ ",Road="+roulette.getRoad()+",roadDetail="+roadDetail.length);
                //更新局数
                updateGameNumber(baseActivity.mAppViewModel.getRoulette01(), tv_game_number01, tv_roulette_red01, tv_roulette_black01, tv_roulette_zero01, tv_roulette_even01, tv_roulette_odd01, tv_roulette_big01, tv_roulette_small01);

            }

        }
    }

    public void updateGameNumber(Roulette roulette, TextView tv_roulette_number, TextView tv_red, TextView tv_black, TextView tv_zero, TextView tv_even, TextView tv_odd, TextView tv_big, TextView tv_small) {
        if (roulette.getStatus() == 0) {
            tv_roulette_number.setText("0");
            tv_red.setText("0");
            tv_black.setText("0");
            tv_zero.setText("0");
            tv_even.setText("0");
            tv_odd.setText("0");
            tv_big.setText("0");
            tv_small.setText("0");
        } else {
            roulette.getTotal(roulette.getRoad());
            tv_roulette_number.setText("" + roulette.getGameNumber().trim());
            tv_red.setText("" + roulette.getRed());
            tv_black.setText("" + roulette.getBlack());
            tv_zero.setText("" + roulette.getZero());
            tv_even.setText("" + roulette.getEven());
            tv_odd.setText("" + roulette.getOdd());
            tv_big.setText("" + roulette.getBig());
            tv_small.setText("" + roulette.getSmall());
        }

    }

    public void showLimit(View v, final int tableId) {
        if (baseActivity.mAppViewModel.getRoulette01().getStatus() != 1) {
            Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
            return;
        }
        initLimitPop(v, tableId, rootView.findViewById(R.id.gd__ll_parent_limit));
    }

    public void initLimitPop(View view, final int tableId, final View top_v) {
        String tableNumber = "RL1";
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
                baseActivity.mAppViewModel.getRoulette01().setLimitIndex(position + 1);
                Bundle bundle = new Bundle();
                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 21);
                //   AppTool.activiyJump(AutoNumberActivity.this, NumberGameSingleTableActivity.class, bundle);
                baseActivity.mAppViewModel.setTableId(21);
                baseActivity.mAppViewModel.setbLobby(false);
                baseActivity.skipAct(RouletteActivity.class, bundle);
                baseActivity.finish();
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


        if (baseActivity.mAppViewModel.getRoulette01() != null) {
            limit1 = "" + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit1().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit1().getMaxTotalBet();
            limit2 = "" + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit2().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit2().getMaxTotalBet();
            limit3 = "" + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit3().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit3().getMaxTotalBet();
            limit4 = "" + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit4().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getRoulette01().getRouletteLimit4().getMaxTotalBet();
        }
        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));
    }
}
