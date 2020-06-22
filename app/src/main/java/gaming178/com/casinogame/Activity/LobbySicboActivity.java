package gaming178.com.casinogame.Activity;

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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.AppConfig;
import gaming178.com.casinogame.Util.HandlerCode;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;

/**
 * Created by Administrator on 2016/4/1.
 */
public class LobbySicboActivity extends BaseActivity implements View.OnClickListener {

    /////////////////////////////////
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_layout_girl) {
            clickGrid(v, 31);
        }
    }

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

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        showBlockDialog();
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

    public void initUI() {
        afbApp.getSicbo01().setRoadOld("");
        afbApp.getSicbo01().setTimer(0);
        sicboTimer01 = 0;
        gameNumber = "";

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
        if (sicboTimer01 == 0 && afbApp.getSicbo01().getTimer() > 0) {
            if (!gameNumber.equals(afbApp.getSicbo01().getGameNumber())) {
                gameNumber = afbApp.getSicbo01().getGameNumber();
                sicboTimer01 = afbApp.getSicbo01().getTimer();
            }
        }

    }

    public void updateInterface() {
        if (sicboTimer01 > 0) {
            sicboTimer01--;
            tv_sicbo_timer01.setText("" + sicboTimer01);
            tv_sicbo_timer01.setTextSize(18);
            tv_sicbo_number01.setText("" + afbApp.getSicbo01().getGameNumber());
        } else {
//            if (afbApp.getSicbo01().getGameStatus() == 2) {
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

    @Override
    protected void leftClick() {
        afbApp.getSicbo01().setRoadOld("");
        AppTool.activiyJump(mContext, LobbyActivity.class);
        finish();

    }

    public void InitRoad() {
        //    afbApp.getSicbo01().setRoad("13-445-0#13-256-0#7-223-0#10-136-0#4-112-0#13-166-0#13-346-0#16-466-0#13-256-0#10-235-0#11-146-0#12-444-1#7-124-0#10-244-0#12-246-0#12-246-0#8-134-0#8-134-0#10-145-0#12-156-0#11-146-0#10-136-0#12-156-0#7-124-0#14-446-0#7-133-0#11-146-0#8-224-0#14-356-0#12-336-0#10-226-0#16-466-0#13-355-0#15-456-0#12-156-0#9-234-0#5-122-0#12-156-0#9-126-0#14-455-0#6-222-1#11-245-0#9-135-0#5-113-0#11-236-0#14-455-0#11-245-0#13-346-0#10-136-0#10-235-0#9-144-0#12-345-0#12-156-0#13-256-0#9-333-1#17-566-0#13-346-0#14-455-0#6-123-0#13-166-0#13-346-0#13-166-0#10-145-0#8-125-0#6-123-0#6-114-0#13-256-0#12-336-0#11-335-0#14-455-0#11-344-0#18-666-1#3-111-1#9-135-0#6-123-0#10-235-0#4-112-0#8-125-0#10-145-0#10-136-0#12-156-0#8-125-0#7-223-0#10-235-0#7-124-0#6-123-0#10-145-0#7-223-0#9-126-0#9-234-0#9-234-0#10-244-0#8-116-0#15-456-0#11-344-0#7-223-0#13-445-0#12-345-0#14-455-0#9-333-1#");
        afbApp.updateRoad(afbApp.getSicbo01(), sicbo_bigsmall_road, sicbo_evenodd_road, tv_sicbo_number01, tv_even01, tv_small01, tv_waidic01, tv_big01, tv_odd01, mContext, density);
    }


    @Override
    protected void initData(Bundle savedInstanceState) {

        backTv.setVisibility(View.GONE);
        setLayout.setVisibility(View.GONE);
        backTv.setVisibility(View.GONE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setMoreToolbar(true);
        InitControl();
        setToolbarNameAndBalance();
        Glide.with(mContext).load(WebSiteUrl.DownLoadPicture + "sicbo.png").skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE).into(img_head);
        titleTv.setText(getString(R.string.sicbo).toUpperCase());
        initOldBigRoad();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sicbo_game;
    }

    ImageView img_head;

    public void InitControl() {
        density = ScreenUtil.getDisplayMetrics(mContext).density;
        girlLayout = (LinearLayout) findViewById(R.id.ll_layout_girl);
        ll_more_info = (LinearLayout) findViewById(R.id.ll_more_info);
        img_head = (ImageView) findViewById(R.id.img_head);
        girlLayout.setOnClickListener(this);
        tv_sicbo_timer01 = (TextView) findViewById(R.id.sicbo_status_tv);
        tv_sicbo_number01 = (TextView) findViewById(R.id.text_shoe_game_number);
        tv_even01 = (TextView) findViewById(R.id.text_even);
        tv_odd01 = (TextView) findViewById(R.id.text_odd);
        tv_big01 = (TextView) findViewById(R.id.text_big);
        tv_small01 = (TextView) findViewById(R.id.text_small);
        tv_waidic01 = (TextView) findViewById(R.id.text_waidic);
        HorizontalScrollView hv_bigsmall = (HorizontalScrollView) findViewById(R.id.hv_bigsmall);
        HorizontalScrollView hv_evenodd = (HorizontalScrollView) findViewById(R.id.hv_evenodd);

        sicbo_bigsmall_road = (GridLayout) hv_bigsmall.findViewById(R.id.sicbo_gridlayout1);
        sicbo_evenodd_road = (GridLayout) hv_evenodd.findViewById(R.id.sicbo_gridlayout1);

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
        ((TextView) findViewById(R.id.tv_sicbo_table_name)).setText("SB1");

    }


    public void clickGrid(View v, final int tableId) {
        initLimitPop(v, tableId, findViewById(R.id.ll_parent_limit));

    }

    public void initLimitPop(View view, final int tableId, final View top_v) {
        String tableNumber = "SB1";
        RecyclerView recyclerView = (RecyclerView) top_v.findViewById(R.id.base_rv);
        recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
        BaseRecyclerAdapter<String> baseRecyclerAdapter = new BaseRecyclerAdapter<String>(mContext, getLimitData(tableId), R.layout.item_popupwindow_text_select_sicbo) {
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
                getApp().getSicbo01().setLimitIndex(position + 1);
                Bundle bundle = new Bundle();
                bundle.putString("limit",s);
                bundle.putString(AppConfig.ACTION_KEY_INITENT_DATA, "" + 31);
                //   AppTool.activiyJump(AutoNumberActivity.this, NumberGameSingleTableActivity.class, bundle);
                afbApp.setTableId(31);
                afbApp.setbLobby(false);
                AppTool.activiyJump(mContext, SicboActivity.class, bundle);

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

    private List<String> getLimitData(int tableId) {
        String limit1 = "0 - 0";
        String limit2 = "0 - 0";
        String limit3 = "0 - 0";
        String limit4 = "0 - 0";


        if (getApp().getSicbo01() != null) {
            limit1 = "" + (int) getApp().getSicbo01().getSicboLimit1().getMinTotalBet() + " - " + (int) getApp().getSicbo01().getSicboLimit1().getMaxTotalBet();
            limit2 = "" + (int) getApp().getSicbo01().getSicboLimit2().getMinTotalBet() + " - " + (int) getApp().getSicbo01().getSicboLimit2().getMaxTotalBet();
            limit3 = "" + (int) getApp().getSicbo01().getSicboLimit3().getMinTotalBet() + " - " + (int) getApp().getSicbo01().getSicboLimit3().getMaxTotalBet();
            limit4 = "" + (int) getApp().getSicbo01().getSicboLimit4().getMinTotalBet() + " - " + (int) getApp().getSicbo01().getSicboLimit4().getMaxTotalBet();
        }

        return new ArrayList<>(Arrays.asList(limit1, limit2, limit3, limit4));
    }
}
