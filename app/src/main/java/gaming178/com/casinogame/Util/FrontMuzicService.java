package gaming178.com.casinogame.Util;


import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import gaming178.com.baccaratgame.R;
import gaming178.com.mylibrary.allinone.util.AppTool;

public class FrontMuzicService extends Service {
    public static final String PLAY_MUZIC_INDEX = "PLAY_MUZIC_INDEX";
    public static final String PLAY_TIMER = "PLAY_TIMER";
    public static final String PLAY_START_BETTING = "PLAY_START_BETTING";
    public static final String PLAY_NOMOREBETS = "PLAY_NOMOREBETS";
    public static final String PLAY_RESULTS = "PLAY_RESULTS";
    public static final String PLAY_CHIP = "PLAY_CHIP";

    public static final String PLAY_CHANGE_VOLUME = "PLAY_CHANGE_VOLUME";
    public static final String GET_POKER = "GET_POKER";


    private MediaPlayer mMediaPlayer = null;
    //11
    private int[] muzicFrontArray;

    private int playIndex = 0;
    private int volume = -1;

    public void initSound() {
        AppTool.setAppLanguage(getApplicationContext(), AppTool.getAppLanguage(getApplicationContext()));
        muzicFrontArray = new int[]{0, R.raw.countdown, R.raw.v_bettime, R.raw.nomorebets_en
                , R.raw.banker_win, R.raw.player_win, R.raw.tie_win, R.raw.win, R.raw.selectchip
                , R.raw.chipin, R.raw.ok, R.raw.sfx_dragon_wins, R.raw.sfx_tiger_wins, R.raw.sfx_tie, R.raw.sfx_no_more_bets
                , R.raw.sfx_congratulations_you_win, R.raw.sfx_place_your_bets, R.raw.sfx_num0, R.raw.sfx_num1, R.raw.sfx_num2
                , R.raw.sfx_num3, R.raw.sfx_num4, R.raw.sfx_num5, R.raw.sfx_num6, R.raw.sfx_num7, R.raw.sfx_num8, R.raw.sfx_num9
                , R.raw.sfx_num10, R.raw.sfx_num11, R.raw.sfx_num12, R.raw.sfx_num13, R.raw.sfx_num14, R.raw.sfx_num15, R.raw.sfx_num16
                , R.raw.sfx_num17, R.raw.sfx_num18, R.raw.sfx_num19, R.raw.sfx_num20, R.raw.sfx_num21, R.raw.sfx_num22, R.raw.sfx_num23
                , R.raw.sfx_num24, R.raw.sfx_num25, R.raw.sfx_num25, R.raw.sfx_num27, R.raw.sfx_num28, R.raw.sfx_num29, R.raw.sfx_num30
                , R.raw.sfx_num31, R.raw.sfx_num32, R.raw.sfx_num33, R.raw.sfx_num34, R.raw.sfx_num35, R.raw.sfx_num36};
    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        //	Log.i(WebSiteUrl.Tag, "onCreate FrontMuzicService Service");
        super.onCreate();
        //mMediaPlayer = new MediaPlayer();

    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        if (intent == null)
            return;
        String action = intent.getAction();// intent.get
        //	Log.i(WebSiteUrl.Tag, "onStart="+action);
        initSound();
        if (action.equals(PLAY_CHANGE_VOLUME))//�ı�����
        {

        } else if (action.equals(GET_POKER)) {
            playBackgroudMuzic(R.raw.fapai);
        } else if (action.equals(PLAY_TIMER)) {
            playBackgroudMuzic(R.raw.m10_count);
        } else {
            int muzic_index = intent.getIntExtra("muzic_index", 0);
            //	Log.i(WebSiteUrl.Tag, "front muzic index="+muzic_index);

            if (muzic_index > 0 && muzic_index < muzicFrontArray.length)
                playBackgroudMuzic(muzicFrontArray[muzic_index]);

        }

        int value = intent.getIntExtra("volume", 0);
        if (value != volume) {
            volume = value;
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {

                float fVolume = (float) value / (float) 100;
//				Log.i(WebSiteUrl.Tag, "volume="+value);
//				Log.i(WebSiteUrl.Tag, "volume="+fVolume);
                mMediaPlayer.setVolume(fVolume, fVolume);
            }
        }

    }

    public synchronized void playBackgroudMuzic(int id) {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        try {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), id);
            mMediaPlayer.start();
            float fVolume = (float) volume/*/(float)100*/;
            mMediaPlayer.setVolume(fVolume, fVolume);

        } catch (IllegalArgumentException e1) {
            e1.printStackTrace();
        } catch (IllegalStateException e1) {
            e1.printStackTrace();
        } finally {

        }
    }

    static MediaPlayer getMediaPlayer(Context context) {
        MediaPlayer mediaplayer = new MediaPlayer();
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
            return mediaplayer;
        }

        try {
            Class<?> cMediaTimeProvider = Class.forName("android.media.MediaTimeProvider");
            Class<?> cSubtitleController = Class.forName("android.media.SubtitleController");
            Class<?> iSubtitleControllerAnchor = Class.forName("android.media.SubtitleController$Anchor");
            Class<?> iSubtitleControllerListener = Class.forName("android.media.SubtitleController$Listener");

            Constructor constructor = cSubtitleController.getConstructor(new Class[]{Context.class, cMediaTimeProvider, iSubtitleControllerListener});

            Object subtitleInstance = constructor.newInstance(context, null, null);

            Field f = cSubtitleController.getDeclaredField("mHandler");

            f.setAccessible(true);
            try {
                f.set(subtitleInstance, new Handler());
            } catch (IllegalAccessException e) {
                return mediaplayer;
            } finally {
                f.setAccessible(false);
            }

            Method setsubtitleanchor = mediaplayer.getClass().getMethod("setSubtitleAnchor", cSubtitleController, iSubtitleControllerAnchor);
            setsubtitleanchor.invoke(mediaplayer, subtitleInstance, null);
            //Log.e("", "subtitle is setted :p");
        } catch (Exception e) {
        }

        return mediaplayer;
    }
}
