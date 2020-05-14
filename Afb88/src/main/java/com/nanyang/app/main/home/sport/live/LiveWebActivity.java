package com.nanyang.app.main.home.sport.live;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.additional.IAdded;
import com.nanyang.app.main.home.sport.betOrder.IBetOrderView;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.VideoPlayer;

import org.json.JSONException;

import butterknife.Bind;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2020/1/3.
 */

public class LiveWebActivity extends BaseToolbarActivity<AdditionPresenter> implements BetView<BallInfo>, IBetOrderView {
    @Bind(R.id.bet_pop_parent_ll)
    View bet_pop_parent_ll;
    @Bind(R.id.iv_play_status)
    ImageView ivPlayStatus;
    @Bind(R.id.iv_voice)
    ImageView ivVoice;
    @Bind(R.id.iv_full_screen)
    ImageView ivFullScreen;
    @Bind(R.id.sv_bottom_content)
    ScrollView sv_bottom_content;
    @Bind(R.id.fl_top_video)
    FrameLayout fl_top_content;
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.ll_back)
    View ll_back;
    @Bind(R.id.ll_status)
    LinearLayout llStatus;
    @Bind(R.id.common_ball_parent_ll)
    LinearLayout parentLl;
    private BallAdapterHelper adapterHelper;
    private ImageView tv_title_live_stream;
    private ImageView tv_title_live_center;
    public VideoPlayer videoPlayer;
    public boolean isPlay = true;
    private String livePlayUrl;
    private RecyclerView rv_title_list;
    LiveSelectedHelper liveSelectedHelper;

    private BallInfo item;
    private AddMBean additionData;
    private String ballG;
    private String gameUrl;
    private LiveParamsInfo liveParamsInfo;
    private View ll_title_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow_live_web);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    }

    LivePlayHelper helper;

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        Object o = extras.get(AppConstant.KEY_DATA);
        if (extras == null || o == null) {
            finish();
            return;
        }
        this.liveParamsInfo = (LiveParamsInfo) o;
        ballG = liveParamsInfo.getBallG();
        rv_title_list = (RecyclerView) findViewById(R.id.rv_title_list);
        ll_title_list = findViewById(R.id.ll_title_list);
        videoPlayer = (VideoPlayer) findViewById(R.id.video_player_stream);

        tv_title_live_stream = (ImageView) findViewById(R.id.tv_title_live_stream);
        tv_title_live_center = (ImageView) findViewById(R.id.tv_title_live_center);
        final ViewHolder viewHolder = new ViewHolder(fl_top_content);
        helper = new LivePlayHelper(viewHolder, this);
        tv_title_live_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_center, webView, false);
            }
        });
        tv_title_live_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_stream, viewHolder.videoPlayerStream, true);
            }
        });


        ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullChangeScreen();
            }
        });

        liveSelectedHelper = new LiveSelectedHelper();
        liveSelectedHelper.iniSelectedHelper(rv_title_list, this, new MainPresenter.CallBack<MenuItemInfo>() {
            @Override
            public void onBack(MenuItemInfo data) throws JSONException {
                refreshAddedData();
            }
        });
        initWeb(liveParamsInfo.getGameUrl());
        initPlay(liveParamsInfo.getLivePlayUrlId());
        createPresenter(new AdditionPresenter(this));
        presenter.addition(liveParamsInfo, new IAdded() {
            @Override
            public void onAdded(AddMBean addMBean, BallInfo ballInfo) {
                LiveWebActivity.this.item = ballInfo;
                LiveWebActivity.this.additionData = addMBean;
                refreshAddedData();
            }
        });
    }

    private void initWeb(String gameUrl) {
        this.gameUrl = gameUrl;
        if (isNotEnable(tv_title_live_center, gameUrl)) return;
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        AfbUtils.synCookies(mContext, webView, gameUrl);
    }

    @Override
    public void updateBalance() {
//        super.updateBalanceTv(allData);
    }


    private void refreshAddedData() {
        if (adapterHelper == null)
            adapterHelper = new BallAdapterHelper(mContext);


        if (additionData != null)
            adapterHelper.createAddedData(item, parentLl, additionData, liveSelectedHelper.getLinkMap());
    }

    private void fullChangeScreen() {
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {// 切换为竖屏
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AfbUtils.switchLanguage(AfbUtils.getLanguage(this), this);
        //重新获取屏幕宽高
        configurationChanged(newConfig);
    }

    private void configurationChanged(Configuration newConfig) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fl_top_content.getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//切换为横屏
            ivFullScreen.setImageResource(R.mipmap.play_close_full_white);
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            fl_top_content.setLayoutParams(lp);
            otherVisible(View.GONE);

        } else {
            ivFullScreen.setImageResource(R.mipmap.play_full_white);
            lp.height = DeviceUtils.getScreenPix(mContext).widthPixels * 9 / 16;
            fl_top_content.setLayoutParams(lp);
            otherVisible(View.VISIBLE);
        }
    }

    private void otherVisible(int visible) {
        sv_bottom_content.setVisibility(visible);
        ll_title_list.setVisibility(visible);
        ll_back.setVisibility(visible);
    }

    private void initPlay(String livePlayUrlId) {
        this.livePlayUrl = livePlayUrlId;

        if (isNotEnable(tv_title_live_stream, livePlayUrl)) {
            enableView(tv_title_live_center, webView);
            llStatus.setVisibility(View.GONE);
            return;
        } else {
            enableView(tv_title_live_stream, videoPlayer);
            llStatus.setVisibility(View.VISIBLE);
        }

        if (!StringUtils.isNull(livePlayUrlId) && !livePlayUrlId.equals("0")) {
            helper.initPlayer(livePlayUrlId);
            visibleLive(tv_title_live_stream, videoPlayer, true);
        }
    }


    private boolean isNotEnable(ImageView tv_title_live_stream, String livePlayUrl) {

        if (StringUtils.isNull(livePlayUrl)) {
            tv_title_live_stream.setEnabled(false);

            return true;
        } else {
            tv_title_live_stream.setEnabled(true);


        }
        return false;
    }


    private void visibleLive(ImageView liveTv, View liveView, boolean isPlay) {
        this.isPlay = isPlay;
/*        tv_title_live_center.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_trans);
        tv_title_live_stream.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_trans);

        tv_title_live_center.setTextColor(ContextCompat.getColor(mContext, R.color.white));
        tv_title_live_stream.setTextColor(ContextCompat.getColor(mContext, R.color.white));*/
        isNotEnable(tv_title_live_center, gameUrl);
        isNotEnable(tv_title_live_stream, livePlayUrl);
        enableView(liveTv, liveView);
        int marWidth = AfbUtils.getScreenWidth(mContext);

        ViewGroup.LayoutParams params = fl_top_content.getLayoutParams();
        // params.width = mWidth;
        int mHeight;
        if (isPlay) {
            if (StringUtils.isNull(livePlayUrl)) {
                llStatus.setVisibility(View.GONE);
            } else {
                llStatus.setVisibility(View.VISIBLE);
            }
            mHeight = AfbUtils.dp2px(mContext, 200);
            helper.onResumePlay();

        } else {
            llStatus.setVisibility(View.GONE);
            mHeight = AfbUtils.dp2px(mContext, 200);
            helper.onPausePlay();
        }
        params.height = mHeight;
        fl_top_content.setLayoutParams(params);
    }

    private void enableView(ImageView liveTv, View liveView) {
        webView.setVisibility(View.GONE);
        videoPlayer.setVisibility(View.GONE);
        liveView.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startRefreshMenu();
        helper.onResumePlay();
        getApp().setNoShowRts(true);
    }


    @Override
    protected void onPause() {
        super.onPause();
        helper.onPausePlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getApp().setNoShowRts(false);
        helper.onStopPlay();
        stopRefreshMenu();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.onDestroy();
    }

    @Override
    public void onGetData(BallInfo data) {

    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onUpdateMixSucceed(AfbClickResponseBean bean) {
        if (getBaseActivity() == null)
            return;
        LogUtil.d("BetPop", "setBetAfbList:onUpdateMixSucceed:" + bean);
        getApp().setBetAfbList(bean);
    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        onPopupWindowCreatedAndShow(pop, -2);
    }

    BetPop betPop;

    @Override
    public BetPop getBetContent() {
        if (betPop == null) {
            betPop = new BetPop(mContext, bet_pop_parent_ll);
            betPop.getLlSingleMix().setVisibility(View.GONE);
        }
        return betPop;
    }

    public String getBallG() {
        return ballG;
    }

    public String getOtType() {
        String ot = "Running";
        if (isHasAttached()) {
            String type = liveParamsInfo.getOddsType();
            if (!StringUtils.isNull(type))
                ot = type;
        }
        return ot;
    }


}
