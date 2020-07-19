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
        muzicFrontArray = new int[]{0, R.raw.gd_countdown, R.raw.gd_v_bettime, R.raw.gd_nomorebets_en
                , R.raw.gd_banker_win, R.raw.gd_player_win, R.raw.gd_tie_win, R.raw.gd_win, R.raw.gd_selectchip
                , R.raw.gd_chipin, R.raw.gd_ok, R.raw.gd_sfx_dragon_wins, R.raw.gd_sfx_tiger_wins, R.raw.gd_sfx_tie, R.raw.gd_sfx_no_more_bets
                , R.raw.gd_sfx_congratulations_you_win, R.raw.gd_sfx_place_your_bets, R.raw.gd_sfx_num0, R.raw.gd_sfx_num1, R.raw.gd_sfx_num2
                , R.raw.gd_sfx_num3, R.raw.gd_sfx_num4, R.raw.gd_sfx_num5, R.raw.gd_sfx_num6, R.raw.gd_sfx_num7, R.raw.gd_sfx_num8, R.raw.gd_sfx_num9
                , R.raw.gd_sfx_num10, R.raw.gd_sfx_num11, R.raw.gd_sfx_num12, R.raw.gd_sfx_num13, R.raw.gd_sfx_num14, R.raw.gd_sfx_num15, R.raw.gd_sfx_num16
                , R.raw.gd_sfx_num17, R.raw.gd_sfx_num18, R.raw.gd_sfx_num19, R.raw.gd_sfx_num20, R.raw.gd_sfx_num21, R.raw.gd_sfx_num22, R.raw.gd_sfx_num23
                , R.raw.gd_sfx_num24, R.raw.gd_sfx_num25, R.raw.gd_sfx_num25, R.raw.gd_sfx_num27, R.raw.gd_sfx_num28, R.raw.gd_sfx_num29, R.raw.gd_sfx_num30
                , R.raw.gd_sfx_num31, R.raw.gd_sfx_num32, R.raw.gd_sfx_num33, R.raw.gd_sfx_num34, R.raw.gd_sfx_num35, R.raw.gd_sfx_num36};
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
            playBackgroudMuzic(R.raw.gd_fapai);
        } else if (action.equals(PLAY_TIMER)) {
            playBackgroudMuzic(R.raw.gd_m10_count);
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
