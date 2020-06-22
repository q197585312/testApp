package gaming178.com.casinogame.Activity;

import android.content.Context;
import android.util.Log;

import gaming178.com.casinogame.Util.VideoListener;
import gaming178.com.casinogame.Util.VideoPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/**
 * Created by Administrator on 2016/5/18.
 */
public class VideoHelper {
    private VideoPlayer sv;
    Context mContext;

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
        initPlayer();
    }

    String playUrl = "rtmp://gnddragon.fc.llnwd.net/gnddragone/myvideo1";

    public void initPlayer() {
        sv.setVideoListener(new VideoListener() {
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
                doVideoFix();
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
        sv.setPath(playUrl);
    }

    public void onStartPlay() {
        if (sv != null) {
            Log.d("ijk", "onPause");
            sv.start();
        }
    }

    public VideoHelper(Context mContext, VideoPlayer sv) {
        this.mContext = mContext;
        this.sv = sv;

    }

    public void playVideo() {
        sv.start();

    }

    public void pauseVideo() {
        sv.pause();
    }


    public void stopVideo() {
        sv.stop();
    }

    public void destroyVideo() {
        sv.release();
    }

    public void doVideoFix() {
    }
}
