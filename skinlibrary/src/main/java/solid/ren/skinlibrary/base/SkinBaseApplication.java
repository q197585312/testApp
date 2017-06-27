package solid.ren.skinlibrary.base;

import android.app.Application;


/**
 * Created by _SOLID
 * Date:2016/4/14
 * Time:10:54
 * Desc:
 */
public class SkinBaseApplication extends Application {

    public void onCreate() {
        super.onCreate();
        SkinAppManager.getInstance().initSkinLoader(this);
    }
}
