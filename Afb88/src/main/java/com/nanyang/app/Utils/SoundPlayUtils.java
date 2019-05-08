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
    private static int soundIndex;

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
        sound1 = mSoundPlayer.load(mContext, R.raw.sound1, 1);// 1
        sound2 = mSoundPlayer.load(mContext, R.raw.sound2, 1);// 1
        sound3 = mSoundPlayer.load(mContext, R.raw.sound3, 1);// 1
        sound4 = mSoundPlayer.load(mContext, R.raw.sound4, 1);// 1
        sound5 = mSoundPlayer.load(mContext, R.raw.sound5, 1);// 1
        sound6 = mSoundPlayer.load(mContext, R.raw.sound6, 1);// 1
        sound7 = mSoundPlayer.load(mContext, R.raw.sound7, 1);// 1
        soundIndex = sound1;
        return soundPlayUtils;
    }

    public static int sound1;
    public static int sound2;
    public static int sound3;
    public static int sound4;
    public static int sound5;
    public static int sound6;
    public static int sound7;

    public static void setSound(int sound) {
        soundIndex = sound;
    }

    public static void play() {
        mSoundPlayer.play(soundIndex, 1, 1, 0, 0, 1);
    }
}
