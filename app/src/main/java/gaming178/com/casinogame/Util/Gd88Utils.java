package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.entity.HallGameItemBean;

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

    public static String formatTosepara(double data) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(data);
    }

    public static void goBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    public static List<HallGameItemBean> getLobbyGameList(Context context) {
        List<HallGameItemBean> hallGameItemBeenS = new ArrayList<>();
        if (WebSiteUrl.GameType != 3) {
            if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
                hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_we1poker, context.getString(R.string.we1poker), AppConfig.we1poker));
            }
        }
        if (WebSiteUrl.GameType != 3 && !BuildConfig.FLAVOR.equals("liga365")) {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_sport_afb1188, context.getString(R.string.afb1188), AppConfig.afb1188));
        }
        int slot = R.mipmap.gd_slots;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            slot = R.mipmap.gd_slots_v;
        }
        hallGameItemBeenS.add(new HallGameItemBean(slot, context.getString(R.string.slots), AppConfig.slots));
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.lg88_casino, context.getString(R.string.lg88_casino), AppConfig.lg88));
        }
        hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_cq_slots, context.getString(R.string.cq), AppConfig.cq9));
        if (!BuildConfig.FLAVOR.equals("ahlicasino")) {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_cock_fighting, context.getString(R.string.cock_fighting), AppConfig.cockfighting));
        }
        if (!BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.pra, context.getString(R.string.pragmatic), AppConfig.pragmatic));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.king_kong, context.getString(R.string.king_kong), AppConfig.kingKong));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_haba, context.getString(R.string.haba), AppConfig.haba));
            hallGameItemBeenS.add(new HallGameItemBean(R.mipmap.gd_pg, context.getString(R.string.gd_pg), AppConfig.pg));
        }
        return hallGameItemBeenS;
    }

    public static List<HallGameItemBean> getTableGameList(Context context) {
        List<HallGameItemBean> allGameList = new ArrayList<>();
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_we1poker, context.getString(R.string.we1poker), AppConfig.we1poker));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_afb1188, context.getString(R.string.afb1188), AppConfig.afb1188));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_slot, context.getString(R.string.slots), AppConfig.slots));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_lg, context.getString(R.string.lg88_casino), AppConfig.lg88));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_cock_fighting, context.getString(R.string.cock_fighting), AppConfig.cockfighting));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_pra, context.getString(R.string.pragmatic), AppConfig.pragmatic));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_cq9, context.getString(R.string.cq), AppConfig.cq9));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_king_kong, context.getString(R.string.king_kong), AppConfig.kingKong));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_haba, context.getString(R.string.haba), AppConfig.haba));
        allGameList.add(new HallGameItemBean(R.mipmap.gd_table_pg, context.getString(R.string.gd_pg), AppConfig.pg));
        List<HallGameItemBean> tableGameList = new ArrayList<>();
        for (int i = 0; i < allGameList.size(); i++) {
            HallGameItemBean hallGameItemBean = allGameList.get(i);
            int gameType = hallGameItemBean.getGameType();
            HallGameItemBean game = getGame(gameType, context);
            if (game != null) {
                game.setImageRes(hallGameItemBean.getImageRes());
                tableGameList.add(game);
            }
        }
        return tableGameList;
    }

    private static HallGameItemBean getGame(int gameType, Context context) {
        List<HallGameItemBean> lobbyGameList = getLobbyGameList(context);
        for (int i = 0; i < lobbyGameList.size(); i++) {
            HallGameItemBean hallGameItemBean = lobbyGameList.get(i);
            int type = hallGameItemBean.getGameType();
            if (type == gameType) {
                return hallGameItemBean;
            }
        }
        return null;
    }
}
