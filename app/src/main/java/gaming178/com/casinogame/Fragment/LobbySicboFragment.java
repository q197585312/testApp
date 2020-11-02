package gaming178.com.casinogame.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
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
import java.util.List;

import butterknife.BindView;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.Activity.LobbySicboActivity;
import gaming178.com.casinogame.Activity.SicboActivity;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;

public class LobbySicboFragment extends BaseFragment {
    private int sicboTimer01 = 0;
    private float density;
    private TextView tv_sicbo_timer01;
    private TextView tv_sicbo_number01;
    private TextView tv_even01;
    private TextView tv_odd01;
    private TextView tv_small01;
    private TextView tv_big01;
    private TextView tv_waidic01;
    private GridLayout sicbo_bigsmall_road;
    private GridLayout sicbo_evenodd_road;

    /////////////////////////////////
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
                    Thread.sleep(1035);
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
                    InitRoad();
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

    public void initUI() {
        baseActivity.mAppViewModel.getSicbo01().setRoadOld("");
        baseActivity.mAppViewModel.getSicbo01().setTimer(0);
        sicboTimer01 = 0;
        gameNumber = "";

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
        if (sicboTimer01 == 0 && baseActivity.mAppViewModel.getSicbo01().getTimer() > 0) {
            if (!gameNumber.equals(baseActivity.mAppViewModel.getSicbo01().getGameNumber())) {
                gameNumber = baseActivity.mAppViewModel.getSicbo01().getGameNumber();
                sicboTimer01 = baseActivity.mAppViewModel.getSicbo01().getTimer();
            }
        }

    }

    public void updateInterface() {
        if (sicboTimer01 > 0) {
            sicboTimer01--;
            tv_sicbo_timer01.setText("" + sicboTimer01);
            tv_sicbo_timer01.setTextSize(18);
            tv_sicbo_number01.setText("" + baseActivity.mAppViewModel.getSicbo01().getGameNumber());
        } else {
//            if (mAppViewModel.getSicbo01().getGameStatus() == 2) {
//                tv_sicbo_timer01.setText(getString(R.string.START_DEALING));
//                int mCurrentOrientation = getResources().getConfiguration().orientation;
//                if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT)
//                    tv_sicbo_timer01.setTextSize(8);
//                else
//                    tv_sicbo_timer01.setTextSize(12);
//
//            }
        }


    }

    public void InitRoad() {
        //    mAppViewModel.getSicbo01().setRoad("13-445-0#13-256-0#7-223-0#10-136-0#4-112-0#13-166-0#13-346-0#16-466-0#13-256-0#10-235-0#11-146-0#12-444-1#7-124-0#10-244-0#12-246-0#12-246-0#8-134-0#8-134-0#10-145-0#12-156-0#11-146-0#10-136-0#12-156-0#7-124-0#14-446-0#7-133-0#11-146-0#8-224-0#14-356-0#12-336-0#10-226-0#16-466-0#13-355-0#15-456-0#12-156-0#9-234-0#5-122-0#12-156-0#9-126-0#14-455-0#6-222-1#11-245-0#9-135-0#5-113-0#11-236-0#14-455-0#11-245-0#13-346-0#10-136-0#10-235-0#9-144-0#12-345-0#12-156-0#13-256-0#9-333-1#17-566-0#13-346-0#14-455-0#6-123-0#13-166-0#13-346-0#13-166-0#10-145-0#8-125-0#6-123-0#6-114-0#13-256-0#12-336-0#11-335-0#14-455-0#11-344-0#18-666-1#3-111-1#9-135-0#6-123-0#10-235-0#4-112-0#8-125-0#10-145-0#10-136-0#12-156-0#8-125-0#7-223-0#10-235-0#7-124-0#6-123-0#10-145-0#7-223-0#9-126-0#9-234-0#9-234-0#10-244-0#8-116-0#15-456-0#11-344-0#7-223-0#13-445-0#12-345-0#14-455-0#9-333-1#");
        baseActivity.mAppViewModel.updateRoad(baseActivity.mAppViewModel.getSicbo01(), sicbo_bigsmall_road, sicbo_evenodd_road, tv_sicbo_number01, tv_even01, tv_small01, tv_waidic01, tv_big01, tv_odd01, mContext, density);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

        baseActivity.backTv.setVisibility(View.GONE);
        baseActivity.setLayout.setVisibility(View.GONE);
        baseActivity.backTv.setVisibility(View.GONE);
        baseActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        baseActivity.setMoreToolbar(true);
        InitControl();
        baseActivity.setToolbarNameAndBalance();
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "sicbo.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(img_head);
        baseActivity.titleTv.setText(getString(R.string.sicbo).toUpperCase());
        baseActivity.setTitleChangeGame(baseActivity.titleTv);
        baseActivity.initOldBigRoad();
    }

    @Override
    public int getFragmentLayoutRes() {
        return R.layout.gd_activity_sicbo_game;
    }

    ImageView img_head;

    public void InitControl() {
        density = ScreenUtil.getDisplayMetrics(mContext).density;
        baseActivity.girlLayout = (LinearLayout) rootView.findViewById(R.id.gd__ll_layout_girl);
        if (baseActivity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            baseActivity.girlLayout.setVisibility(View.GONE);
        } else {
            baseActivity.girlLayout.setVisibility(View.VISIBLE);
        }
        baseActivity.ll_more_info = (LinearLayout) rootView.findViewById(R.id.gd__ll_more_info);
        img_head = (ImageView) rootView.findViewById(R.id.gd__img_head);
        baseActivity.girlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 31);
            }
        });
        tv_sicbo_timer01 = (TextView) rootView.findViewById(R.id.gd__sicbo_status_tv);
        tv_sicbo_number01 = (TextView) rootView.findViewById(R.id.gd__text_shoe_game_number);
        tv_even01 = (TextView) rootView.findViewById(R.id.gd__text_even);
        tv_odd01 = (TextView) rootView.findViewById(R.id.gd__text_odd);
        tv_big01 = (TextView) rootView.findViewById(R.id.gd__text_big);
        tv_small01 = (TextView) rootView.findViewById(R.id.gd__text_small);
        tv_waidic01 = (TextView) rootView.findViewById(R.id.gd__text_waidic);
        HorizontalScrollView hv_bigsmall = (HorizontalScrollView) rootView.findViewById(R.id.gd__hv_bigsmall);
        HorizontalScrollView hv_evenodd = (HorizontalScrollView) rootView.findViewById(R.id.gd__hv_evenodd);

        sicbo_bigsmall_road = (GridLayout) hv_bigsmall.findViewById(R.id.gd__sicbo_gridlayout1);
        sicbo_evenodd_road = (GridLayout) hv_evenodd.findViewById(R.id.gd__sicbo_gridlayout1);

        sicbo_bigsmall_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 31);
            }
        });
        sicbo_evenodd_road.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGrid(v, 31);
            }
        });
        ((TextView) rootView.findViewById(R.id.gd__tv_sicbo_table_name)).setText("SB1");

    }


    public void clickGrid(View v, final int tableId) {
        if (baseActivity.mAppViewModel.getSicbo01().getStatus() != 1) {
            Toast.makeText(mContext, getString(R.string.game_close), Toast.LENGTH_SHORT).show();
            return;
        }
        initLimitPop(v, tableId, rootView.findViewById(R.id.gd__ll_parent_limit));

    }

    public void initLimitPop(View view, final int tableId, final View top_v) {
        String tableNumber = "SB1";
        RecyclerView recyclerView = (RecyclerView) top_v.findViewById(R.id.gd__base_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mContext, getLimitData(tableId), R.layout.gd_item_popupwindow_text_select_sicbo) {
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
                baseActivity.mAppViewModel.getSicbo01().setLimitIndex(position + 1);
                Bundle bundle = new Bundle();
                bundle.putString("limit", s);
                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 31);
                //   AppTool.activiyJump(AutoNumberActivity.this, NumberGameSingleTableActivity.class, bundle);
                baseActivity.mAppViewModel.setTableId(31);
                baseActivity.mAppViewModel.setbLobby(false);
                baseActivity.skipAct(SicboActivity.class, bundle);

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


        if (baseActivity.mAppViewModel.getSicbo01() != null) {
            limit1 = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit1().getMaxTotalBet();
            limit2 = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit2().getMaxTotalBet();
            limit3 = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit3().getMaxTotalBet();
            limit4 = "" + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) baseActivity.mAppViewModel.getSicbo01().getSicboLimit4().getMaxTotalBet();
        }

        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));
    }
}
