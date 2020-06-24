package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2020/1/10.
 */

public class Gd88Utils {
    public static boolean isFirstIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("first_pref", Context.MODE_PRIVATE);
        boolean isFirstIn = preferences.getBoolean("isFirstIn", true);
        if (isFirstIn) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isFirstIn", false);
            editor.commit();
            return true;
        } else {
            return false;
        }
    }

    public static String getChipContent(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("chip_Content_Save", Context.MODE_PRIVATE);
        String chipContent = preferences.getString("chipContent", "1-10-50-100-500-1000-");
        return chipContent;
    }

    public static void saveChipContent(Context context, String chipContent) {
        SharedPreferences preferences = context.getSharedPreferences("chip_Content_Save", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("chipContent", chipContent);
        editor.commit();
    }

}
