package gaming178.com.casinogame.Util;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import gaming178.com.baccaratgame.R;

public class BackgroudMuzicService extends Service {
    public static final String PLAY_SONG1 = "PLAY_SONG1";
    public static final String PLAY_SONG2 = "PLAY_SONG2";
    public static final String PLAY_SONG3 = "PLAY_SONG3";
    public static final String PLAY_SONG4 = "PLAY_SONG4";
    public static final String PLAY_SONG5 = "PLAY_SONG5";
    public static final String PLAY_SONG6 = "PLAY_SONG6";
    public static final String PLAY_STOP = "PLAY_STOP";
    public static final String PLAY_CHANGE_VOLUME = "PLAY_CHANGE_VOLUME";
    private MediaPlayer mMediaPlayer = null;

    private int[] muzicBackgroudArray = {0, R.raw.gd_bgm1, R.raw.gd_bgm2, R.raw.gd_bgm3, R.raw.gd_bgm4, R.raw.gd_bgm5, R.raw.gd_bgm6};

    private int playIndex = 0;
    private int volume = -1;

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate() {
        //	Log.i(WebSiteUrl.Tag, "onCreate Service");
        super.onCreate();
        //mMediaPlayer = new MediaPlayer();

    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
        }
        super.onDestroy();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        try {
            if (intent == null)
                return;
            String action = intent.getAction();// intent.get

            int index = 0;
            if (action.equals(PLAY_SONG1))
                index = 1;
            else if (action.equals(PLAY_SONG2)) {
                //	int value = intent.getIntExtra("song2", 0);
                //	Log.i(WebSiteUrl.Tag, "PLAY_SONG2="+value);
                index = 2;
            } else if (action.equals(PLAY_SONG3))
                index = 3;
            else if (action.equals(PLAY_SONG4)) {
                index = 4;
            } else if (action.equals(PLAY_SONG5)) {
                index = 5;
            } else if (action.equals(PLAY_SONG6)) {
                index = 6;
            } else if (action.equals(PLAY_CHANGE_VOLUME)) {

            }
            if (index != playIndex && index != 0) {
                playIndex = index;
//			Log.i(WebSiteUrl.Tag, "playIndex="+playIndex);

                playBackgroudMuzic(playIndex);
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void playBackgroudMuzic(int index) {
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.stop();
            mMediaPlayer.reset();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
        try {
            mMediaPlayer = MediaPlayer.create(getApplicationContext(), muzicBackgroudArray[index]);
            mMediaPlayer.start();
            mMediaPlayer.setLooping(true);
            float fVolume = (float) volume / (float) 100;
            mMediaPlayer.setVolume(fVolume, fVolume);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //mMediaPlayer.setVolume(leftVolume, rightVolume)
    }


}
