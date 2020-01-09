package com.nanyang.app.main.home.sport.live;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.additional.IAdded;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.VideoPlayer;
import com.unkonw.testapp.libs.widget.listener.VideoListener;

import java.io.IOException;

import butterknife.Bind;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2020/1/3.
 */

public class LiveWebActivity extends BaseToolbarActivity<AdditionPresenter> implements BetView<BallInfo> {
    @Bind(R.id.iv_play_status)
    ImageView ivPlayStatus;
    @Bind(R.id.iv_voice)
    ImageView ivVoice;
    @Bind(R.id.iv_full_screen)
    ImageView ivFullScreen;
    @Bind(R.id.sv_bottom_content)
    ScrollView sv_bottom_content;
    @Bind(R.id.fl_top_content)
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
    private TextView tv_title_live_stream;
    private TextView tv_title_live_center;
    public VideoPlayer videoPlayer;
    public boolean isPlay = true;
    private String livePlayUrl;
    private RecyclerView rv_title_list;
    LiveSelectedHelper liveSelectedHelper;

    boolean playing = true;
    boolean voiceOpen = true;
    private BallInfo item;
    private AddMBean additionData;
    private String ballG;
    private String gameUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popwindow_live_web);

    }

    @Override
    public void initData() {
        super.initData();
        Bundle extras = getIntent().getExtras();
        Object o = extras.get(AppConstant.KEY_DATA);
        if (extras == null || o == null) {
            finish();
            return;
        }
        LiveParamsInfo o1 = (LiveParamsInfo) o;
        ballG = o1.getBallG();
        rv_title_list = (RecyclerView) findViewById(R.id.rv_title_list);
        videoPlayer = (VideoPlayer) findViewById(R.id.video_player_stream);

        tv_title_live_stream = (TextView) findViewById(R.id.tv_title_live_stream);
        tv_title_live_center = (TextView) findViewById(R.id.tv_title_live_center);
        tv_title_live_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_center, webView, false);
            }
        });
        tv_title_live_stream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                visibleLive(tv_title_live_stream, videoPlayer, true);
            }
        });
        ivVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnVoice();
            }
        });

        ivPlayStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                turnPause();
            }
        });
        ivFullScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullChangeScreen();
            }
        });

        initRv();
        initWeb(o1.getGameUrl());
        initPlay(o1.getLivePlayUrlId());
        createPresenter(new AdditionPresenter(this));
        presenter.addition(o1, new IAdded() {
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
        AfbUtils.synCookies(mContext, webView, gameUrl);
    }

    @Override
    public void updateBalance() {
//        super.updateBalanceTv(allData);
    }

    public void setLivePlayUrlId(String BID) {

        Log.d("ijk", "BID:" + BID);
        String path;
        if (StringUtils.isNull(BID) || BID.equals("0")) {
            return;
        } else {
            path = "rtmp://pull.afb1188.com/live/" + BID;
            if (BID.startsWith("1")) {
                path = "rtmp://27.124.35.15:8080/live/" + BID;
            } else if (BID.startsWith("9")) {
                path = "rtmp://27.124.13.234:8080/live/" + BID;
            }
            visibleLive(tv_title_live_stream, videoPlayer, true);
        }
        videoPlayer.setPath(path);
    }

    private void initRv() {
        liveSelectedHelper = new LiveSelectedHelper();
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv_title_list.setLayoutManager(layoutManager);
        final BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, liveSelectedHelper.getList(), R.layout.text_wrap_wrap) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView textView = holder.getTextView(R.id.item_text_tv);
                textView.setTextColor(ContextCompat.getColor(mContext, R.color.black_grey));
                textView.setText(item.getRes());
                if (liveSelectedHelper.isPositionSelected(position)) {
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.yellow1));
                }
            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                liveSelectedHelper.putIndex(position);
                baseRecyclerAdapter.notifyDataSetChanged();
                refreshAddedData();
            }
        });
        rv_title_list.setAdapter(baseRecyclerAdapter);

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
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fl_top_content.getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//切换为横屏
            ivFullScreen.setImageResource(R.mipmap.play_close_full_white);
            lp.height = LinearLayout.LayoutParams.MATCH_PARENT;
            fl_top_content.setLayoutParams(lp);
            otherVisible(View.GONE);

        } else {
            ivFullScreen.setImageResource(R.mipmap.play_full_white);
            lp.height = AfbUtils.getScreenWidth(this) * 9 / 16;
            fl_top_content.setLayoutParams(lp);
            otherVisible(View.VISIBLE);
        }
    }

    private void otherVisible(int visible) {
        sv_bottom_content.setVisibility(visible);
        rv_title_list.setVisibility(visible);
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
        videoPlayer.setVideoListener(new VideoListener() {
            @Override
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                Log.d("ijk", "onBufferingUpdate");
            }

            @Override
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                Log.d("ijk", "onCompletion");
            }

            @Override
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk", "onError");
                return false;

            }

            @Override
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i1) {
                Log.d("ijk", "onInfo");
                return false;
            }

            @Override
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                onStartPlay();
                Log.d("ijk", "onPrepared");
            }

            @Override
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                Log.d("ijk", "onSeekComplete");
            }

            @Override
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
                Log.d("ijk", "onVideoSizeChanged");
            }
        });
        setLivePlayUrlId(livePlayUrlId);
    }

    private boolean isNotEnable(TextView tv_title_live_stream, String livePlayUrl) {
        tv_title_live_stream.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_trans);
        if (StringUtils.isNull(livePlayUrl)) {
            tv_title_live_stream.setEnabled(false);
            tv_title_live_stream.setTextColor(Color.GRAY);
            return true;
        } else {
            tv_title_live_stream.setEnabled(true);
            tv_title_live_stream.setTextColor(ContextCompat.getColor(mContext, R.color.white));

        }
        return false;
    }


    private void visibleLive(TextView liveTv, View liveView, boolean isPlay) {
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
            mHeight = (marWidth * 9 / 16);
            playVideo();
        } else {
            llStatus.setVisibility(View.GONE);
            mHeight = AfbUtils.dp2px(mContext, 200);
            onPausePlay();
        }
        params.height = mHeight;
        fl_top_content.setLayoutParams(params);
    }

    private void enableView(TextView liveTv, View liveView) {
        webView.setVisibility(View.GONE);
        videoPlayer.setVisibility(View.GONE);
        liveView.setVisibility(View.VISIBLE);
        liveTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_yellow);
        liveTv.setTextColor(ContextCompat.getColor(mContext, R.color.yellow_center_user));
    }

    @Override
    protected void onResume() {
        super.onResume();
        playVideo();
        getApp().setNoShowRts(true);
    }

    public void onStartPlay() {
        if (videoPlayer != null) {
            Log.d("ijk", "onPause");
            videoPlayer.start();
            playing = true;
            ivPlayStatus.setImageResource(R.mipmap.play_pause_white);
        }
    }

    public void onPausePlay() {
        if (videoPlayer != null) {
            Log.d("ijk", "onPause");
            videoPlayer.pause();
            playing = false;
            ivPlayStatus.setImageResource(R.mipmap.play_start_white);
        }
    }

    private void playVideo() {
        try {
            if (videoPlayer != null && videoPlayer.getmPath() != null)
                videoPlayer.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnVoice() {
        voiceOpen = !voiceOpen;
        if (voiceOpen) {
            videoPlayer.getmMediaPlayer().setVolume(1.0f, 1.0f);
            ivVoice.setImageResource(R.mipmap.play_mute_white);
        } else {
            videoPlayer.getmMediaPlayer().setVolume(0.0f, 0.0f);
            ivVoice.setImageResource(R.mipmap.play_voice_white);
        }
    }

    public void turnPause() {
        playing = !playing;
        if (playing) {
            onStart();
        } else {
            onPausePlay();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        onPausePlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getApp().setNoShowRts(false);
        if (videoPlayer != null) {
            Log.d("ijk", "stop");
            videoPlayer.stop();
            playing = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoPlayer != null) {
            Log.d("ijk", "onDestroy");
            videoPlayer.release();
        }

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
        pop.setV(fl_top_content);
        pop.setWidth(AfbUtils.getScreenWidth(this));
        onPopupWindowCreatedAndShow(pop, -2);
    }

    public String getBallG() {
        return ballG;
    }
}
