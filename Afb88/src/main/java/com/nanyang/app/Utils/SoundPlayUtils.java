package com.nanyang.app.Utils;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.nanyang.app.R;


/**
 * Created by Administrator on 2018/9/25.
 */

public class SoundPlayUtils {
    // SoundPool对象
    public static SoundPool mSoundPlayer = new SoundPool(10,
            AudioManager.STREAM_SYSTEM, 5);
    public static SoundPlayUtils soundPlayUtils;
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
        GOAL = mSoundPlayer.load(mContext, R.raw.goal, 1);// 1
        return soundPlayUtils;
    }

    public static int GOAL;

    /**
     * 播放声音
     *
     * @param soundID
     */
    public static void play(int soundID) {
        mSoundPlayer.play(soundID, 1, 1, 0, 0, 1);
    }
}
