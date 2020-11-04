package com.nanyang.app;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.home.sport.main.SportState;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.CookieManger;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.SystemTool;

import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Cookie;

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
    private static List<MenuItemInfo> oddsTypeList;
    private static HashMap<String, Boolean> chipStatusMap;

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
            if (Math.abs(Float.valueOf(v)) <= 0.3) {
                return "";
            }
            p = scientificCountingToString(Float.valueOf(v) / 10f + "", "0.00");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return p;
    }

    public static String decimalValue(float v, String format) {
        return scientificCountingToString(v + "", format);
     /*   DecimalFormat decimalFormat = new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        decimalFormat.setDecimalFormatSymbols(dfs);
        return decimalFormat.format(v);//format 返回的是字符串*/
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

    public static SpannableStringBuilder handleStringColor(String str, String splitStr, int firColor, int sedColor) {
        int bstart = str.indexOf(splitStr);
        int bend = bstart + splitStr.length();
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
        LogUtil.d("getLanguage", "switchLanguage:" + lag);
        if (!StringUtils.isNull(lag))
            SystemTool.switchLanguage(lag, context);

    }

    public static String getLanguage(Context context) {
        String language = SystemTool.getLanguage(context);
        LogUtil.d("getLanguage", language);
        return language;
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

    public static SpannableStringBuilder setColorStyle(String str, int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        String[] split = str.split("\n");
        String a = "";
        for (int i = 0; i < split.length; i++) {
            a += split[i];
            if (i != split.length - 1) {
                a += "i" + i;
            }
        }
        int startIndex = a.indexOf("i0");
        int mindIndex = a.indexOf("i1");
        int endIndex = a.indexOf("i2");
        style.setSpan(new ForegroundColorSpan(color), 0, startIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(color), mindIndex, endIndex - 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    public static void synCookies(Context context, WebView webView, String url, boolean isZoom) {
        synCookies(context, webView, url, isZoom, null);
    }

    public static void synCookies(Context context, WebView webView, String url, boolean isZoom, WebViewClient webViewClient) {
        WebSettings webSettings = webView.getSettings();

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(isZoom); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(isZoom); // 缩放至屏幕的大小
//设置可以支持缩放
        webSettings.setSupportZoom(isZoom);
//设置出现缩放工具
        webSettings.setBuiltInZoomControls(false);
        //设定缩放控件隐藏
        if (isZoom) {
            webView.setInitialScale(100);
        }

// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        webSettings.setJavaScriptEnabled(true);//是否允许JavaScript脚本运行，默认为false
//支持插件


//其他细节操作
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webSettings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webSettings.setAppCacheEnabled(true);//是否使用缓存
        webSettings.setDomStorageEnabled(true);//开启本地DOM存储
        webSettings.setLoadsImagesAutomatically(true); // 加载图片
        webSettings.setMediaPlaybackRequiresUserGesture(false);

        webSettings.setDomStorageEnabled(true);//开启本地DOM存储
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (webViewClient == null) {
            webView.setWebViewClient(new WebViewClient() {

                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        view.loadUrl(request.getUrl().toString());
                        LogUtil.d("url", request.getUrl().toString());
                    } else {
                        view.loadUrl(request.toString());
                        LogUtil.d("url", request.toString());
                    }
                    return true;
                }


            });
        } else {
            webView.setWebViewClient(webViewClient);
        }
        syncCookie(context, url);
        webView.loadUrl(url);
    }

    /**
     * 给WebView同步Cookie
     *
     * @param context 上下文
     * @param url     可以使用[domain][host]
     */

    private static void syncCookie(Context context, String url) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.createInstance(context);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);// 允许接受 Cookie
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeSessionCookie();// 移除
        } else {
            cookieManager.removeSessionCookies(null);// 移除
        }
        List<Cookie> cookies = CookieManger.getInstance().getCookieStore().getCookies();//;
        for (int i = 0; i < cookies.size(); i++) {
            Cookie cookie = cookies.get(i);
            String value = cookie.name() + "=" + cookie.value();
            LogUtil.d("url", "cookie:" + value);
            cookieManager.setCookie(BuildConfig.Domain, value);
        }
        cookieManager.setCookie(BuildConfig.Domain, "Domain=" + BuildConfig.Domain);
        cookieManager.setCookie(BuildConfig.Domain, "Path=/");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync();
        } else {
            CookieSyncManager.getInstance().sync();
            cookieManager.flush();

        }
    }

    public static void synCookies(Context context, String url, String cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        cookieManager.setCookie(url, cookies);//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }


    @NonNull
    public static BaseRecyclerAdapter getGamesAdapter(Context mContext, RecyclerView rvContent) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, (R.string.SportBook), "SportBook"));
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, (R.string.Europe_View), "Europe"));
        dataList.add(new MenuItemInfo(R.mipmap.home_keno2, (R.string.Myanmar_Odds), "Myanmar_Odds"));
        dataList.add(new MenuItemInfo(R.mipmap.home_financials, (R.string.Financial), "Financial"));

        dataList.add(new MenuItemInfo(R.mipmap.home_games, (R.string.E_Sport), "E_Sport"));
        dataList.add(new MenuItemInfo(R.mipmap.home_live, (R.string.Live_Casino), "Live_Casino"));
        dataList.add(new MenuItemInfo(R.mipmap.home_specals4d, (R.string.Specials_4D), "Specials_4D"));
        dataList.add(new MenuItemInfo(R.mipmap.home_huay_thai, (R.string.Huay_Thai), "Huay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.home_muay_thai, (R.string.Muay_Thai), "Muay_Thai"));

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

        Glide.with(context).load(res).into(img);
    }

    public static String touzi_ed_values22 = "";

    /**
     * 在数字型字符串千分位加逗号,删除小数
     *
     * @param str
     * @param edt
     * @return sb.toString()
     */
    public static String addComma(String str, TextView edt) {
        if (StringUtils.isNull(str))
            return "";
        touzi_ed_values22 = edt.getText().toString().trim().replaceAll(",", "");
        str = str.trim().replaceAll(",", "");

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
        if (AppConstant.IS_AGENT)
            if (tail != null) {
                sb.append(tail);
            }
        return sb.toString();
    }

    /**
     * 在数字型字符串千分位加逗号,删除小数
     *
     * @param str
     * @return sb.toString()
     */
    public static String addComma(String str, boolean showTail) {



       /* touzi_ed_values22 = edt.getText().toString().trim().replaceAll(",", "");
        str = str.trim().replaceAll(",", "");

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
        }*/
        String f = "";
        if (showTail) {
            f = "#,###.00";
        } else {
            f = "#,###";
        }
        String s = scientificCountingToStringNoT(str, f);

        return s;
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
        String ff = scientificCounting.toString().replace(",", "");
        if (StringUtils.isNull(ff) || Float.valueOf(ff) == 0) {
            return "0.00";
        }
        String f = "#,###.00";
        return scientificCountingToString(scientificCounting, f);
    }

    public static String scientificCountingToString(String scientificCounting, String format) {


        scientificCounting = scientificCounting.toString().replace(",", "");
        BigDecimal bd = new BigDecimal(scientificCounting);
        String s = bd.toPlainString();
        Locale enlocale = Locale.US;
        DecimalFormat df = (DecimalFormat)
                NumberFormat.getNumberInstance(enlocale);
        df.applyPattern(format);

        String format1 = df.format(Double.parseDouble(s));
        return format1;
    }


    public static String scientificCountingToStringNoT(String scientificCounting, String format) {
        scientificCounting = scientificCounting.toString().replace(",", "");
        return scientificCountingToString(scientificCounting, format);
    }

    public static String getJsonParam(LinkedHashMap<String, String> map) {
        JSONObject jsonObject = new JSONObject(map);
        return jsonObject.toString();
    }


    public static String getLangWeek(Context context, int day) {
        String language = getLanguage(context);
        switch (day) {
            case 1:
                if (language.equals("zh")) {
                    return "一.";
                } else {
                    return "MON.";
                }
            case 2:
                if (language.equals("zh")) {
                    return "二.";
                } else {
                    return "TUE.";
                }
            case 3:
                if (language.equals("zh")) {
                    return "三.";
                } else {
                    return "WED.";
                }
            case 4:
                if (language.equals("zh")) {
                    return "四.";
                } else {
                    return "THU.";
                }
            case 5:
                if (language.equals("zh")) {
                    return "五.";
                } else {
                    return "FRI.";
                }
            case 6:
                if (language.equals("zh")) {
                    return "六.";
                } else {
                    return "SAT.";
                }
            case 7:
                if (language.equals("zh")) {
                    return "七.";
                } else {
                    return "SUN.";
                }
            default:
                return day + ".";
        }
    }

    public static int getLangMonth(String month) {
        if (month.startsWith("0")) {
            month = month.substring(1);
        }
        switch (month) {
            case "1":
                return (R.string.Jan);
            case "2":
                return (R.string.Feb);
            case "3":
                return (R.string.Mar);
            case "4":
                return (R.string.Apr);
            case "5":
                return (R.string.May);
            case "6":
                return (R.string.Jun);
            case "7":
                return (R.string.Jul);
            case "8":
                return (R.string.Aug);
            case "9":
                return (R.string.Sep);
            case "10":
                return (R.string.Oct);
            case "11":
                return (R.string.Nov);
            case "12":
                return (R.string.Dec);
            default:
                return (R.string.Jan);

        }
    }

    public static void setLayoutParams(View view, int width, int height) {
        ViewGroup.LayoutParams linearParams = view.getLayoutParams();
        linearParams.width = width;
        linearParams.height = height;
        view.setLayoutParams(linearParams);
    }


    public static List<MenuItemInfo<String>> getMarketsList(Context context) {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(context), context);
        List<MenuItemInfo<String>> list = new ArrayList<>();
        list.add(new MenuItemInfo<>(0, (R.string.All_Markets), "0", context.getString(R.string.All_Market)));//accType=
        list.add(new MenuItemInfo<>(0, (R.string.Main_Markets), "1", context.getString(R.string.Main_Markets)));
        list.add(new MenuItemInfo<>(0, (R.string.Other_Bet_Markets), "2", context.getString(R.string.Other_Bet_Markets)));
        return list;
    }

    public static MenuItemInfo<String> getMarketByType(Context context, String type) {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(context), context);
        List<MenuItemInfo<String>> list = getMarketsList(context);
        for (MenuItemInfo<String> menuItemInfo : list) {
            if (menuItemInfo.getType().equals(type))
                return menuItemInfo;
        }
        return new MenuItemInfo<>(0, (R.string.All_Markets), "0", context.getString(R.string.All_Market));
    }

    public static List<MenuItemInfo> getOddsTypeList(Context context, String CurCode) {

        List<MenuItemInfo> list = new ArrayList<>();
        list.add(new MenuItemInfo(0, (R.string.HK_ODDS), "HK"));//accType=
        list.add(new MenuItemInfo(0, (R.string.MY_ODDS), "MY"));
        if (CurCode.equals("IDR") || AppConstant.IS_AGENT)
            list.add(new MenuItemInfo(0, (R.string.ID_ODDS), "ID"));
        list.add(new MenuItemInfo(0, (R.string.EU_ODDS), "EU"));
        return list;
    }

    public static MenuItemInfo getOddsTypeByType(Context context, String type, String CurCode) {
        List<MenuItemInfo> list = getOddsTypeList(context, CurCode);
        for (MenuItemInfo menuItemInfo : list) {
            if (menuItemInfo.getType().equals(type))
                return menuItemInfo;
        }
        return new MenuItemInfo(0, (R.string.MY_ODDS), "MY");
    }

    public static void setChipStatusMap(String chips) {
        chipStatusMap = new HashMap<>();
        chipStatusMap.put("1", false);
        chipStatusMap.put("10", false);
        chipStatusMap.put("50", false);
        chipStatusMap.put("100", false);
        chipStatusMap.put("500", false);
        chipStatusMap.put("1000", false);
        chipStatusMap.put("5000", false);
        chipStatusMap.put("10000", false);
        chipStatusMap.put("30000", false);
        chipStatusMap.put("50000", false);
        chipStatusMap.put("100000", false);
        chipStatusMap.put("max", false);
        chipStatusMap.put("min", false);
        if (!StringUtils.isNull(chips)) {
            String[] split = chips.split(",");
            for (String s : split) {
                chipStatusMap.put(s, true);
            }
        }
    }

    public static HashMap<String, Boolean> getChipStatusMap() {
        return chipStatusMap;
    }


    public static void startAnimator(ValueAnimator animator) {
        /*
         *参数解释：
         *target：设置属性动画的目标类，此处是当前自定义view所以使用this
         *propertyName:属性名称。（要对View的那个属性执行动画操作）
         *values数组：根据时间的推移动画将根据数组的内容进行改变
         */
        ;
        /*
         * ArgbEvaluator：这种评估者可以用来执行类型之间的插值整数值代表ARGB颜色。
         * FloatEvaluator：这种评估者可以用来执行浮点值之间的插值。
         * IntEvaluator：这种评估者可以用来执行类型int值之间的插值。
         * RectEvaluator：这种评估者可以用来执行类型之间的插值矩形值。
         *
         * 由于本例是改变View的backgroundColor属性的背景颜色所以此处使用ArgbEvaluator
         */
        animator.setEvaluator(new ArgbEvaluator());
        //设置动画重复次数，此处设置无限重复
        animator.setRepeatCount(ValueAnimator.INFINITE);
        //设置重复模式
        animator.setRepeatMode(ValueAnimator.REVERSE);
        //开启动画
        animator.start();
    }

    public static String getPayout(String index3, String index9, String end17) {
        int nn = 1;
        if (!StringUtils.isNull(end17) && !end17.endsWith("0")) {
            String c = String.valueOf(end17.charAt(end17.length() - 1));
            try {
                nn = Integer.valueOf(c);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

        }
        Log.d("end17", "end17:" + end17 + "," + nn);
        return AfbUtils.decimalValue(Float.parseFloat(index3) * Float.parseFloat(index9) / nn, "0.00");
    }
}
