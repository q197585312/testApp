package solid.ren.skinlibrary.base;

import android.content.Context;

import java.io.File;
import java.io.IOException;

import solid.ren.skinlibrary.SkinConfig;
import solid.ren.skinlibrary.loader.SkinManager;
import solid.ren.skinlibrary.utils.SkinFileUtils;

/**
 * Created by Administrator on 2017/6/26.
 */

public class SkinAppManager {
    static SkinAppManager instance;

    public static SkinAppManager getInstance() {
        if (instance == null)
            instance = new SkinAppManager();
        return instance;
    }

    /**
     * Must call init first
     */
    public void initSkinLoader(Context context) {
        setUpSkinFile(context.getApplicationContext());
        SkinManager.getInstance().init(context.getApplicationContext());
        SkinManager.getInstance().loadSkin();
    }

    private void setUpSkinFile(Context context) {
        try {
            String[] skinFiles = context.getAssets().list(SkinConfig.SKIN_DIR_NAME);
            for (String fileName : skinFiles) {
                File file = new File(SkinFileUtils.getSkinDir(context), fileName);
                boolean b = !file.exists();
//                if (b)
                    SkinFileUtils.copySkinAssetsToDir(context, fileName, SkinFileUtils.getSkinDir(context));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
