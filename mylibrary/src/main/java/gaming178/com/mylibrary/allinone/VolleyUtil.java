package gaming178.com.mylibrary.allinone;

import android.content.Context;

import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.volley.RequestQueue;
import gaming178.com.mylibrary.allinone.volley.toolbox.ImageLoader;
import gaming178.com.mylibrary.allinone.volley.toolbox.Volley;

public class VolleyUtil {
    private volatile static RequestQueue requestQueue;
    private volatile static ImageLoader imageLoader;

    /**
     * 返回RequestQueue单例
     **/
    public static RequestQueue getQueue(Context context) {
        synchronized (VolleyUtil.class) {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context
                        .getApplicationContext());
            }
        }
        return requestQueue;
    }

    /**
     * 返回ImageLoader单例
     **/
    public static ImageLoader getImageLoader(Context context) {
        synchronized (VolleyUtil.class) {
            if (imageLoader == null) {
                imageLoader = new ImageLoader(VolleyUtil.getQueue(context),
                        new BitmapCache());
            }
        }
        return imageLoader;
    }
    /**
     * 保存cookie
     */
    public static void saveCookie(Context context,String cookies){
        AppTool.saveObjectData(context,"cookie",cookies);
    }
    /**
     * 得到cookie
     */
    public static String getCookie(Context context){
       return (String) AppTool.getObjectData(context,"cookie");
    }
}
