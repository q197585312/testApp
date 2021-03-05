package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, String> getCurCodeMap() {
        Map<String, String> curMap = new HashMap<>();
        curMap.put("MYR", "Z32AFB031");
        curMap.put("SGD", "Z31AFB021");
        curMap.put("USD", "Z30AFB011");
        curMap.put("VND", "Z37AFB081");
        curMap.put("THB", "Z33AFB041");
        curMap.put("IDR", "Z38AFB091");
        curMap.put("HKD", "Z35AFB061");
        curMap.put("GBP", "Z45AFB161");
        curMap.put("JPY", "Z41AFB121");
        curMap.put("KRW", "Z42AFB131");
        curMap.put("EUR", "Z39AFB101");
        curMap.put("ILS", "Z40AFB111");
        curMap.put("PHP", "Z44AFB151");
        curMap.put("MMK", "Z43AFB141");
        curMap.put("CNY", "Z34AFB051");
        curMap.put("CAD", "Z49AFB201");
        curMap.put("AUD", "Z48AFB191");
        curMap.put("BDT", "Z46AFB171");
        curMap.put("NOK", "Z50AFB211");
        curMap.put("SEK", "Z51AFB221");
        curMap.put("TRY", "Z47AFB181");
        curMap.put("TWD", "Z36AFB071");
        curMap.put("ZAR", "Z52AFB231");
        curMap.put("BND", "Z53AFB233");
        curMap.put("PTS", "Z54AFB234");
        return curMap;


    }

    public static void goBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

}
