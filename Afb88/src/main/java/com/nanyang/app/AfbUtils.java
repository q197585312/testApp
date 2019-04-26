package com.nanyang.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nanyang.app.main.home.sport.USFootball.USFootballFragment;
import com.nanyang.app.main.home.sport.allRunning.AllRunningFragment;
import com.nanyang.app.main.home.sport.badminton.BadmintonFragment;
import com.nanyang.app.main.home.sport.baseball.BaseballFragment;
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.nanyang.app.main.home.sport.boxing.BoxingFragment;
import com.nanyang.app.main.home.sport.cricket.CricketFragment;
import com.nanyang.app.main.home.sport.cycling.CyclingFragment;
import com.nanyang.app.main.home.sport.darts.DartsFragment;
import com.nanyang.app.main.home.sport.e_sport.ESportFragment;
import com.nanyang.app.main.home.sport.europe.EuropeFragment;
import com.nanyang.app.main.home.sport.financial.FinancialFragment;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.formula.FormulaFragment;
import com.nanyang.app.main.home.sport.game4d.Game4dFragment;
import com.nanyang.app.main.home.sport.golf.GolfFragment;
import com.nanyang.app.main.home.sport.handball.HandballFragment;
import com.nanyang.app.main.home.sport.iceHockey.IceHockeyFragment;
import com.nanyang.app.main.home.sport.main.BaseSportFragment;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.muayThai.MuayThaiFragment;
import com.nanyang.app.main.home.sport.myanmarOdds.MyanmarFragment;
import com.nanyang.app.main.home.sport.outRight.OutRightFragment;
import com.nanyang.app.main.home.sport.poll.PoolSnookerFragment;
import com.nanyang.app.main.home.sport.rugby.RugbyFragment;
import com.nanyang.app.main.home.sport.tableTennis.TableTennisFragment;
import com.nanyang.app.main.home.sport.tennis.TennisFragment;
import com.nanyang.app.main.home.sport.volleyball.VolleyballFragment;
import com.nanyang.app.main.home.sport.winterSport.WinterSportFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.CookieManger;
import com.unkonw.testapp.libs.utils.SystemTool;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/2/22.
 */

public class AfbUtils {
    public static String nativePath = Environment.getExternalStorageDirectory().getPath() + "/afb88";
    public static String headImgName = "/head.png";
    /**
     * 定义script的正则表达式
     */
    private static final String REGEX_SCRIPT = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    /**
     * 定义style的正则表达式
     */
    private static final String REGEX_STYLE = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    /**
     * 定义HTML标签的正则表达式
     */
    private static final String REGEX_HTML = "<[^>]+>";
    private static Map<String, SportState> majorStateHashMap = new HashMap<>();

    public static String delHTMLTag(String htmlStr) {
        // 过滤script标签
        Pattern p_script = Pattern.compile(REGEX_SCRIPT, Pattern.CASE_INSENSITIVE);
        Matcher m_script = p_script.matcher(htmlStr);
        htmlStr = m_script.replaceAll("");
        // 过滤style标签
        Pattern p_style = Pattern.compile(REGEX_STYLE, Pattern.CASE_INSENSITIVE);
        Matcher m_style = p_style.matcher(htmlStr);
        htmlStr = m_style.replaceAll("");
        // 过滤html标签
        Pattern p_html = Pattern.compile(REGEX_HTML, Pattern.CASE_INSENSITIVE);
        Matcher m_html = p_html.matcher(htmlStr);
        htmlStr = m_html.replaceAll("");
        htmlStr = htmlStr.replace("&nbsp;", "");

        return htmlStr.trim(); // 返回文本字符串
    }

    public static String changeValueS(String v) {
        if (v == null || v.equals(""))
            return "";
        String p = "";
        try {
            if (Float.valueOf(v) == 0) {
                return "";
            }
            p = decimalValue(Float.valueOf(v) / 10, "0.00");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static String decimalValue(float v, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
        return decimalFormat.format(v);//format 返回的是字符串
    }

    public static void appJump(Context context, String packageName, String cls, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        ComponentName comp = new ComponentName(packageName,
                cls);
        intent.setComponent(comp);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startActivity(intent);
    }

    /**
     * 保存对象到文件
     */
    public static boolean saveObjectData(Context context, String name, Object obj) {
        boolean result = false;
        try {
            FileOutputStream stream = context.openFileOutput(name,
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(obj);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 从文件中读取对象
     */
    public static Object getObjectData(Context context, String name) {
        Object obj = null;
        try {
            FileInputStream stream = context.openFileInput(name);
            ObjectInputStream ois = new ObjectInputStream(stream);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static SpannableStringBuilder handleStringColor(String str, int firColor, int sedColor) {
        int bstart = str.indexOf("/");
        int bend = bstart + "/".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new BackgroundColorSpan(firColor), 0, bstart, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new BackgroundColorSpan(sedColor), bend, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static SpannableStringBuilder handleStringTextColor(String str, int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), 0, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }

    public static void switchLanguage(String lag, Context context) {
        SystemTool.switchLanguage(lag, context);

    }

    public static String getLanguage(Context context) {
        return SystemTool.getLanguage(context);
    }

    public static void synCookies(Context context, String url, String cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        cookieManager.setCookie(url, cookies);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }

    public static SpannableStringBuilder setColorStyle(String str, int[] color, String[] strColors) {

        SpannableStringBuilder style = new SpannableStringBuilder(str);
        for (int i = 0; i < color.length; i++) {
            int start = str.indexOf(strColors[i]);
            int end = start + strColors[i].length();
            style.setSpan(new ForegroundColorSpan(color[i]), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return style;
    }

    public static void synCookies(Context context, WebView webView, String url) {
        WebSettings webSettings = webView.getSettings();
        //开启javascript
        webSettings.setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setAllowFileAccess(true);// 设置允许访问文件数据
        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        String cookie = "";
        if (CookieManger.getCookieStore().get(url) != null && CookieManger.getCookieStore().get(url).size() > 0) {
            cookie = CookieManger.getCookieStore().get(url).get(0).toString();
            AfbUtils.synCookies(context, url, cookie);
        }
        webView.loadUrl(url);
    }

    /*	"id": "25",
        "img": "https:\/\/www.afb1188.com\/H50\/Img\/handball.jpg"
	}, {
		"id": "10",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/ice_hockey.jpg"
	}, {
		"id": "16",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/motor.jpg"
	}, {
		"id": "12",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/rugby.jpg"
	}, {
		"id": "11",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/snooker.jpg"
	}, {
		"id": "22",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/tabletennis.jpg"
	}, {
		"id": "8",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/usfootball.jpg"
	}, {
		"id": "24",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/volleyball.jpg"
	}, {
		"id": "21",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/waterpolo.jpg"
	}, {
		"id": "0",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/running.jpg"
	}, {
		"id": "999",
		"img": "https:\/\/www.afb1188.com\/H50\/Img\/Outright.jpg"
	}],*/
    public static LinkedHashMap<String, SportIdBean> beanHashMap = new LinkedHashMap<>();
    public static LinkedHashMap<String, SportIdBean> sportMap = new LinkedHashMap<>();
    public static LinkedHashMap<String, SportIdBean> othersMap = new LinkedHashMap<>();

    public static void initAllSprotMap() {
        BaseSportFragment soccerFragment = new SoccerFragment();
        BaseSportFragment basketballFragment = new BasketballFragment();
        BaseSportFragment tennisFragment = new TennisFragment();
        BaseSportFragment financialFragment = new FinancialFragment();
        BaseSportFragment game4dFragment = new Game4dFragment();
        BaseSportFragment eSportFragment = new ESportFragment();
        BaseSportFragment muayThaiFragment = new MuayThaiFragment();

        BaseSportFragment myanmarFragment = new MyanmarFragment();
        BaseSportFragment europeFragment = new EuropeFragment();
        BaseSportFragment usFootballFragment = new USFootballFragment();
        BaseSportFragment baseballFragment = new BaseballFragment();
        BaseSportFragment iceHockeyFragment = new IceHockeyFragment();
        BaseSportFragment poolSnookerFragment = new PoolSnookerFragment();
        BaseSportFragment rugbyFragment = new RugbyFragment();
        BaseSportFragment dartsFragment = new DartsFragment();
        BaseSportFragment boxingFragment = new BoxingFragment();
        BaseSportFragment golfFragment = new GolfFragment();
        BaseSportFragment badmintonFragment = new BadmintonFragment();
        BaseSportFragment volleyballFragment = new VolleyballFragment();
        BaseSportFragment cricketFragment = new CricketFragment();

        BaseSportFragment handballFragment = new HandballFragment();
        BaseSportFragment cyclingFragment = new CyclingFragment();
        BaseSportFragment winterSportFragment = new WinterSportFragment();
        //    BaseSportFragment superComboFragment = new SuperComboFragment();
        BaseSportFragment tableTennisFragment = new TableTennisFragment();
        BaseSportFragment formulaFragment = new FormulaFragment();
        BaseSportFragment outRightFragment = new OutRightFragment();
        BaseSportFragment allRunningFragment = new AllRunningFragment();

        beanHashMap.put("1", new SportIdBean("1", "1", R.string.Soccer, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football));

        beanHashMap.put("Cashio", new SportIdBean("Cashio", "", R.string.gd88_casino, "Casino", SportActivity.class, soccerFragment, Color.BLACK));
        beanHashMap.put("9", new SportIdBean("9", "2", R.string.Basketball, "Basketball", SportActivity.class, basketballFragment, Color.BLACK, R.mipmap.basketball));
        beanHashMap.put("21", new SportIdBean("21", "3", R.string.Tennis, "Tennis", SportActivity.class, tennisFragment, Color.BLACK, R.mipmap.tennis));
        beanHashMap.put("29", new SportIdBean("29", "9", R.string.Baseball, "Baseball", SportActivity.class, baseballFragment, Color.BLACK, R.mipmap.baseball));
        beanHashMap.put("51", new SportIdBean("51", "20", R.string.Badminton, "Badminton", SportActivity.class, badmintonFragment, Color.BLACK, R.mipmap.badminton));
        beanHashMap.put("106", new SportIdBean("106", "34", R.string.E_Sport, "E_Sport", SportActivity.class, eSportFragment, Color.RED, R.mipmap.e_sport));
        beanHashMap.put("16", new SportIdBean("16", "14", R.string.Boxing, "Boxing", SportActivity.class, boxingFragment, Color.BLACK, R.mipmap.boxing));
        beanHashMap.put("44", new SportIdBean("44", "23", R.string.cricket, "Cricket", SportActivity.class, cricketFragment, Color.BLACK, R.mipmap.cricket));
// '26', g = '65', img = 'https://www.afb1188.com/H50/Img/cycling.jpg'

        beanHashMap.put("65", new SportIdBean("65", "26", R.string.Cycling, "Cycling", SportActivity.class, cyclingFragment, Color.BLACK, R.mipmap.cycling));

        beanHashMap.put("19", new SportIdBean("19", "13", R.string.Darts, "Darts", SportActivity.class, dartsFragment, Color.BLACK, R.mipmap.darts));

        beanHashMap.put("25", new SportIdBean("25", "15", R.string.Formula1, "Formula1", SportActivity.class, formulaFragment, Color.BLACK, R.mipmap.motor_sports));
        beanHashMap.put("28", new SportIdBean("28", "19", R.string.Futsal, "Futsal", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football));
        beanHashMap.put("182", new SportIdBean("182", "36", R.string.Europe_View, "Europe", SportActivity.class, europeFragment, Color.RED, R.mipmap.football));
        beanHashMap.put("22", new SportIdBean("22", "17", R.string.Golf, "Golf", SportActivity.class, golfFragment, Color.BLACK, R.mipmap.ice_hockey));
        beanHashMap.put("41", new SportIdBean("41", "25", R.string.Handball, "Handball", SportActivity.class, handballFragment, Color.BLACK, R.mipmap.football));
        beanHashMap.put("14", new SportIdBean("14", "10", R.string.IceHockey, "IceHockey", SportActivity.class, iceHockeyFragment, Color.BLACK, R.mipmap.ice_hockey));
        beanHashMap.put("49", new SportIdBean("49", "16", R.string.Motor_Sports, "Motor_Sports", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.motor_sports));
        beanHashMap.put("7", new SportIdBean("7", "4", R.string.Financial, "Financial", SportActivity.class, financialFragment, Color.BLACK, R.mipmap.financial));

        beanHashMap.put("17", new SportIdBean("17", "12", R.string.Rugby, "Rugby", SportActivity.class, rugbyFragment, Color.BLACK, R.mipmap.rugby));
        beanHashMap.put("11", new SportIdBean("11", "11", R.string.Snooker, "Snooker", SportActivity.class, poolSnookerFragment, Color.BLACK, R.mipmap.cricket));
        beanHashMap.put("57", new SportIdBean("57", "22", R.string.Table_Tennis, "Table_Tennis", SportActivity.class, tableTennisFragment, Color.BLACK, R.mipmap.table_tennis));
        beanHashMap.put("12", new SportIdBean("12", "8", R.string.US_Football, "US_Football", SportActivity.class, usFootballFragment, Color.BLACK, R.mipmap.volleyball));
        beanHashMap.put("23", new SportIdBean("23", "24", R.string.Volleyball, "Volleyball", SportActivity.class, volleyballFragment, Color.BLACK, R.mipmap.volleyball));
        beanHashMap.put("53", new SportIdBean("53", "21", R.string.Water_Polo, "Water_Polo", SportActivity.class, winterSportFragment, Color.BLACK, R.mipmap.water_polo));
        beanHashMap.put("1,9,21,29,51,182", new SportIdBean("1,9,21,29,51,182", "0", R.string.all_running, "AllRunning", SportActivity.class, allRunningFragment, Color.BLACK, R.mipmap.all_running));
        beanHashMap.put("43,104,61,58,64,54,91,69,37,91,61,63,102", new SportIdBean("43,104,61,58,64,54,91,69,37,91,61,63,102", "999", R.string.OutRight, "OutRight", SportActivity.class, outRightFragment, Color.BLACK, R.mipmap.outright));

        sportMap.put("1,9,21,29,51,182", new SportIdBean("1,9,21,29,51,182", "0", R.string.all_running, "AllRunning", SportActivity.class, allRunningFragment, Color.BLACK, R.mipmap.all_running));
        sportMap.put("182", new SportIdBean("182", "36", R.string.Europe_View, "Europe", SportActivity.class, europeFragment, Color.RED, R.mipmap.football));
        sportMap.put("1", new SportIdBean("1", "1", R.string.Soccer, "SportBook", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football));
        sportMap.put("9", new SportIdBean("9", "2", R.string.Basketball, "Basketball", SportActivity.class, basketballFragment, Color.BLACK, R.mipmap.basketball));
        sportMap.put("21", new SportIdBean("21", "3", R.string.Tennis, "Tennis", SportActivity.class, tennisFragment, Color.BLACK, R.mipmap.tennis));
        sportMap.put("7", new SportIdBean("7", "4", R.string.Financial, "Financial", SportActivity.class, financialFragment, Color.BLACK, R.mipmap.financial));
        sportMap.put("106", new SportIdBean("106", "34", R.string.E_Sport, "E_Sport", SportActivity.class, eSportFragment, Color.RED, R.mipmap.e_sport));
        sportMap.put("6", new SportIdBean("6", "6", R.string.Specials_4D, "Specials_4D", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.baseball));
        sportMap.put("43,104,61,58,64,54,91,69,37,91,61,63,102", new SportIdBean("43,104,61,58,64,54,91,69,37,91,61,63,102", "999", R.string.OutRight, "OutRight", SportActivity.class, outRightFragment, Color.BLACK, R.mipmap.outright));


        othersMap.put("12", new SportIdBean("12", "8", R.string.US_Football, "US_Football", SportActivity.class, usFootballFragment, Color.BLACK, R.mipmap.volleyball));
        othersMap.put("29", new SportIdBean("29", "9", R.string.Baseball, "Baseball", SportActivity.class, baseballFragment, Color.BLACK, R.mipmap.baseball));
        othersMap.put("14", new SportIdBean("14", "10", R.string.IceHockey, "IceHockey", SportActivity.class, iceHockeyFragment, Color.BLACK, R.mipmap.ice_hockey));
        othersMap.put("17", new SportIdBean("17", "12", R.string.Rugby, "Rugby", SportActivity.class, rugbyFragment, Color.BLACK, R.mipmap.rugby));
        othersMap.put("19", new SportIdBean("19", "13", R.string.Darts, "Darts", SportActivity.class, dartsFragment, Color.BLACK, R.mipmap.darts));
        othersMap.put("16", new SportIdBean("16", "14", R.string.Boxing, "Boxing", SportActivity.class, boxingFragment, Color.BLACK, R.mipmap.boxing));
        othersMap.put("49", new SportIdBean("49", "16", R.string.Motor_Sports, "Motor_Sports", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.motor_sports));
        othersMap.put("22", new SportIdBean("22", "17", R.string.Golf, "Golf", SportActivity.class, golfFragment, Color.BLACK, R.mipmap.ice_hockey));
        othersMap.put("28", new SportIdBean("28", "19", R.string.Futsal, "Futsal", SportActivity.class, soccerFragment, Color.BLACK, R.mipmap.football));
        othersMap.put("51", new SportIdBean("51", "20", R.string.Badminton, "Badminton", SportActivity.class, badmintonFragment, Color.BLACK, R.mipmap.badminton));
        othersMap.put("53", new SportIdBean("53", "21", R.string.Water_Polo, "Water_Polo", SportActivity.class, winterSportFragment, Color.BLACK, R.mipmap.water_polo));
        othersMap.put("57", new SportIdBean("57", "22", R.string.Table_Tennis, "Table_Tennis", SportActivity.class, tableTennisFragment, Color.BLACK, R.mipmap.table_tennis));
        othersMap.put("44", new SportIdBean("44", "23", R.string.cricket, "Cricket", SportActivity.class, cricketFragment, Color.BLACK, R.mipmap.cricket));
        othersMap.put("23", new SportIdBean("23", "24", R.string.Volleyball, "Volleyball", SportActivity.class, volleyballFragment, Color.BLACK, R.mipmap.volleyball));
        othersMap.put("41", new SportIdBean("41", "25", R.string.Handball, "Handball", SportActivity.class, handballFragment, Color.BLACK, R.mipmap.football));
        othersMap.put("65", new SportIdBean("65", "26", R.string.Cycling, "Cycling", SportActivity.class, cyclingFragment, Color.BLACK, R.mipmap.cycling));
        othersMap.put("108", new SportIdBean("108", "108", R.string.Muay_Thai, "Muay_Thai", SportActivity.class, muayThaiFragment, Color.RED, R.mipmap.financial));


    }

    //("1", "9", "21", "29", "14", "5");
    public static SportIdBean getSportFromOtherAndSportByG(String id) {
        return sportMap.get(id) != null ? sportMap.get(id) : othersMap.get(id);
    }

    public static SportIdBean getSportByG(String id) {
        return beanHashMap.get(id);
    }

    public static SportIdBean getSportByDbid(String dbid) {
        Iterator<Map.Entry<String, SportIdBean>> iterator = beanHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next().getValue();
            if (next.getDbid().equals(dbid))
                return next;
        }
        return null;

    }

    public static SportIdBean getSportByType(String type) {
        Iterator<Map.Entry<String, SportIdBean>> iterator = beanHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next().getValue();
            if (next.getType().equals(type))
                return next;
        }
        return null;
    }

    public static String getOutRightGByDbid(SportIdBean sportIdBean) {
        String dbid = sportIdBean.getDbid();
        String outRightG = "";
        switch (dbid) {
            case "1":
                outRightG = "2";
                break;
            case "2":
                outRightG = "31";
                break;
            case "3":
                outRightG = "36";
                break;

            case "34":
                outRightG = "107";
                break;
            case "8":
                outRightG = "30";
            case "9":
                outRightG = "39";
                break;
            case "10":
                outRightG = "33";
                break;

            case "11":
                outRightG = "32";
                break;
            case "12":
                outRightG = "34";
            case "13":
                outRightG = "35";
                break;
            case "14":
                outRightG = "92";
                break;
            case "16":
                outRightG = "46";
                break;


            case "17":
                outRightG = "37";
                break;
            case "19":
                outRightG = "63";
                break;
            case "20":
                outRightG = "52";
                break;
            case "21":
                outRightG = "54";
                break;

            case "22":
                outRightG = "58";
                break;

            case "23":
                outRightG = "61";
                break;

            case "24":
                outRightG = "62";
                break;

            case "25":
                outRightG = "43";
                break;
            case "26":
                outRightG = "64";
                break;
            case "27":
                outRightG = "69";
                break;
            case "28":
                outRightG = "91";
                break;
            case "29":
                outRightG = "102";
                break;
            case "30":
                outRightG = "104";

        }
        return outRightG;

    }

    @NonNull
    public static BaseRecyclerAdapter getGamesAdapter(Context mContext, RecyclerView rvContent) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, mContext.getString(R.string.SportBook), "SportBook"));
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, mContext.getString(R.string.Europe_View), "Europe"));
        dataList.add(new MenuItemInfo(R.mipmap.home_keno2, mContext.getString(R.string.Myanmar_Odds), "Myanmar_Odds"));
        dataList.add(new MenuItemInfo(R.mipmap.home_financials, mContext.getString(R.string.Financial), "Financial"));

        dataList.add(new MenuItemInfo(R.mipmap.home_games, mContext.getString(R.string.E_Sport), "E_Sport"));
        dataList.add(new MenuItemInfo(R.mipmap.home_live, mContext.getString(R.string.Live_Casino), "Live_Casino"));
        dataList.add(new MenuItemInfo(R.mipmap.home_specals4d, mContext.getString(R.string.Specials_4D), "Specials_4D"));
        dataList.add(new MenuItemInfo(R.mipmap.home_huay_thai, mContext.getString(R.string.Huay_Thai), "Huay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.home_muay_thai, mContext.getString(R.string.Muay_Thai), "Muay_Thai"));

        BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, dataList, R.layout.home_item_image_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                ImageView iv = holder.getView(R.id.iv_pic);
                TextView tv = holder.getView(R.id.tv_text);
                iv.setImageResource(item.getRes());
                tv.setText(item.getText());
            }
        };
        rvContent.setAdapter(adapter);
        return adapter;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static long diffTime(String open) {
        String format = "yyyy-MM-dd|HH:mm:ss";
        DateFormat df = new SimpleDateFormat(format);
        DateFormat simpleDateFormat = new SimpleDateFormat(format);
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Hong_Kong");
        simpleDateFormat.setTimeZone(timeZone);
        String other = simpleDateFormat.format(new Date());
        long diff = 0;
        try {
            Date d1 = df.parse(open);
            Date d2 = df.parse(other);
            long openTime = d1.getTime();
            long nowTime = d2.getTime();
            diff = openTime - nowTime;
            return diff;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diff;
    }

    public static String getCurrentDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        String date = df.format(new Date());
        return date;
    }

    public static void GildLoadResForImg(Context context, ImageView img, int res) {
        Glide.with(context).load(res).asBitmap().into(img);
    }

    public static String touzi_ed_values22 = "";

    /**
     * 在数字型字符串千分位加逗号
     *
     * @param str
     * @param edtext
     * @return sb.toString()
     */
    public static String addComma(String str, TextView edtext) {

        touzi_ed_values22 = edtext.getText().toString().trim().replaceAll(",", "");

        boolean neg = false;
        if (str.startsWith("-")) {  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1) { //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4) {
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg) {
            sb.insert(0, '-');
        }
        if (tail != null) {
            sb.append(tail);
        }
        return sb.toString();
    }

    private static DisplayMetrics metric;

    private static void initDM(Activity activity) {
        if (metric == null) {
            metric = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        }
    }

    public static int getScreenHeight(Activity activity) {
        initDM(activity);
        int height = metric.heightPixels;
        return height;
    }

    public static int getScreenWidth(Activity activity) {
        initDM(activity);
        int width = metric.widthPixels;
        return width;
    }

    public static String scientificCountingToString(String scientificCounting) {
        BigDecimal bd = new BigDecimal(scientificCounting);
        String s = bd.toPlainString();
        DecimalFormat df = new DecimalFormat("#,###.00");
        String format = df.format(Double.parseDouble(s));
        return format;
    }

    public static String getJsonParam(LinkedHashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }

    public static String getLangParamStr(Context context) {
        String language = getLanguage(context);
        if (TextUtils.isEmpty(language)) {
            return "EN-US";
        } else {
            switch (language) {
                case "zh":
                    return "ZH-CN";
                case "en":
                    return "EN-US";
                case "th":
                    return "TH-TH";
                case "ko":
                    return "EN-TT";
                case "vi":
                    return "EN-IE";
                case "tr":
                    return "UR-PK";
                default:
                    return "EN-US";
            }
        }
    }

    public static String getLangMonth(Context context, String month) {
        String language = getLanguage(context);
        if (month.startsWith("0")) {
            month = month.substring(1);
        }
        if (language.equals("zh")) {
            return month + "月";
        } else {
            switch (month) {
                case "1":
                    return "Jan";
                case "2":
                    return "Feb";
                case "3":
                    return "Mar";
                case "4":
                    return "Apr";
                case "5":
                    return "May";
                case "6":
                    return "Jun";
                case "7":
                    return "Jul";
                case "8":
                    return "Aug";
                case "9":
                    return "Sep";
                case "10":
                    return "Oct";
                case "11":
                    return "Nov";
                case "12":
                    return "Dec";
                default:
                    return month;
            }
        }
    }

    public static void setLayoutParams(View view, int width, int height) {
        ViewGroup.LayoutParams linearParams = view.getLayoutParams();
        linearParams.width = width;
        linearParams.height = height;
        view.setLayoutParams(linearParams);
    }

}
