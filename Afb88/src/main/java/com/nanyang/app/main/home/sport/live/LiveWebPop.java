package com.nanyang.app.main.home.sport.live;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.additional.AddMBean;
import com.nanyang.app.main.home.sport.dialog.WebPop;
import com.nanyang.app.main.home.sport.main.BallAdapterHelper;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.widget.VideoPlayer;
import com.unkonw.testapp.libs.widget.listener.VideoListener;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2019/11/28.
 */

public class LiveWebPop extends WebPop {


    private LinearLayout parentLl;
    private BallAdapterHelper adapterHelper;
    private TextView tv_title_live_stream;
    private TextView tv_title_live_center;
    public VideoPlayer videoPlayer;
    public boolean isPlay = true;
    private String livePlayUrl;
    private RecyclerView rv_title_list;
    LiveSelectedHelper liveSelectedHelper;
    private AddMBean additionData;
    private BallInfo item;

    public LiveWebPop(Context context, View v, int width, int height) {
        super(context, v, width, height);
        initData();
    }

    private void initData() {
        liveSelectedHelper = new LiveSelectedHelper();
        initRv();
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
                videoPlayer.start();
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

    }

    //  setContentView(R.layout.activity_live_web);
    @Override
    protected int onSetLayoutRes() {
        return R.layout.activity_live_web;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        rv_title_list = view.findViewById(R.id.rv_title_list);
        videoPlayer = view.findViewById(R.id.video_player_stream);
        parentLl = view.findViewById(R.id.common_ball_parent_ll);
        tv_title_live_stream = view.findViewById(R.id.tv_title_live_stream);
        tv_title_live_center = view.findViewById(R.id.tv_title_live_center);
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

    }


    private void initRv() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        rv_title_list.setLayoutManager(layoutManager);
        final BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(context, liveSelectedHelper.getList(), R.layout.text_wrap_wrap) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView textView = holder.getTextView(R.id.item_text_tv);
                textView.setTextColor(ContextCompat.getColor(context, R.color.black_grey));
                textView.setText(item.getRes());
                if (liveSelectedHelper.isPositionSelected(position)) {
                    textView.setTextColor(ContextCompat.getColor(context, R.color.yellow_gold));
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

    private void visibleLive(TextView liveTv, View liveView, boolean isPlay) {
        this.isPlay = isPlay;
        tv_title_live_center.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_trans);
        tv_title_live_stream.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_trans);
        tv_title_live_center.setTextColor(ContextCompat.getColor(context,R.color.white));
        tv_title_live_stream.setTextColor(ContextCompat.getColor(context,R.color.white));
        webView.setVisibility(View.GONE);
        videoPlayer.setVisibility(View.GONE);
        liveView.setVisibility(View.VISIBLE);
        liveTv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_yellow);
        liveTv.setTextColor(ContextCompat.getColor(context,R.color.yellow1));
        int marWidth = AfbUtils.getScreenWidth((Activity) context);

        ViewGroup.LayoutParams params = parentLl.getLayoutParams();
        // params.width = mWidth;
        int mHeight;
        if (isPlay) {
            mHeight = ((marWidth * 19) / 32);
            onResume();
        } else {
            mHeight = AfbUtils.dp2px(context, 180);
            onPause();
        }
        params.height = mHeight;
        parentLl.setLayoutParams(params);
        parentLl.invalidate();
    }

    public void onStop() {
        if (videoPlayer != null) {
            Log.d("ijk", "stop");
            videoPlayer.stop();
        }
    }

    public void onResume() {

        if (videoPlayer != null) {
            Log.d("ijk", "onResume");
            if (isPlay)
                videoPlayer.start();
        }
    }

    public void onPause() {
        if (videoPlayer != null) {
            Log.d("ijk", "onPause");
            videoPlayer.pause();
        }
    }


    public void setAdditionData(AddMBean additionData, BallAdapterHelper adapterHelper, BallInfo item) {
        this.adapterHelper = adapterHelper;
        this.additionData = additionData;
        this.item = item;
        refreshAddedData();

    }

    private void refreshAddedData() {
        if (adapterHelper != null && item != null && parentLl != null && additionData != null)
            adapterHelper.createAddedData(item, parentLl, additionData, liveSelectedHelper.getLinkMap());
    }

    @Override
    protected void onClose() {
        super.onClose();
        adapterHelper.setIsLiveOpen(false);
        onStop();
    }

    public void setUrl(String url) {
        super.setUrl(url);
        visibleLive(tv_title_live_center, webView, false);
    }

    public void setLivePlayUrl(String livePlayUrl) {
        this.livePlayUrl = livePlayUrl;
        Log.d("ijk", "livePlayUrl:" + livePlayUrl);
        String path = "rtmp://58.200.131.2:1935/livetv/hunantv";
        if (StringUtils.isNull(livePlayUrl) || livePlayUrl.equals("0")) {
            return;
        } else {
            path = "rtmp://pull.afb1188.com/live/" + livePlayUrl;
            if(livePlayUrl.startsWith("1")){
                path = "rtmp://27.124.35.15:8080/live/" + livePlayUrl;
            }else if(livePlayUrl.startsWith("9")){
                path = "rtmp://27.124.13.234:8080/live/" + livePlayUrl;
            }

            visibleLive(tv_title_live_stream, videoPlayer, true);
        }
        videoPlayer.setPath(path);
        try {
            videoPlayer.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
