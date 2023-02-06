package nanyang.com.dig88.Util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/9/25.
 */

public class SoundPlayUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundPlayUtils soundPlayUtils;
    public static int BET_PLEASE;
    public static int BET_TOUCH;
    public static int COUNT_DOWN;
    public static int DRAW_STOP;
    public static int GOOD_LUCK;
    // 上下文
    static Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }
        // 初始化声音
        mContext = context;
        BET_PLEASE = mSoundPlayer.load(mContext, R.raw.bet_please, 1);// 1
        BET_TOUCH = mSoundPlayer.load(mContext, R.raw.bet_touch, 1);// 2
        COUNT_DOWN = mSoundPlayer.load(mContext, R.raw.count_down, 1);// 3
        DRAW_STOP = mSoundPlayer.load(mContext, R.raw.draw_stop, 1);// 4
        GOOD_LUCK = mSoundPlayer.load(mContext, R.raw.goodluck, 1);// 5
        return soundPlayUtils;
    }

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }
}
