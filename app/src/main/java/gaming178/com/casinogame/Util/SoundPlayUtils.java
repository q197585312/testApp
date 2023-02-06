package gaming178.com.casinogame.Util;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;


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

    public static SoundPlayUtils init(Context context) {
        if (soundPlayUtils == null) {
            soundPlayUtils = new SoundPlayUtils();
        }
        // 初始化声音
        mContext = context;
        return soundPlayUtils;
    }
    public void play(int raw) {
        mSoundPlayer.play(mSoundPlayer.load(mContext, raw, 1), 1, 1, 0, 0, 1);
    }

}
