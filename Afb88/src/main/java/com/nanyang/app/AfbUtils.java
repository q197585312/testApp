package com.nanyang.app;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.CookieManger;
import com.unkonw.testapp.libs.utils.SystemTool;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public static String decimalValue(String v, String format) {
        DecimalFormat decimalFormat = new DecimalFormat(format);//构造方法的字符格式这里如果小数不足2位,会以0补足.
        Long aLong = Long.valueOf(v);
        return decimalFormat.format(aLong);//format 返回的是字符串
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

    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }
        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right, (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top, (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
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

    /*
    图片保存文件
     */
    public static void writeBitmapToFile(String filePath, Bitmap b, int quality) {
        try {
            File desFile = new File(filePath);
            if (!desFile.exists()) {
                desFile.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(desFile + headImgName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            b.compress(Bitmap.CompressFormat.PNG, quality, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取手机图片
    public static List<String> getPhoneImg(Context context) {
        List<String> list = new ArrayList<>();
        String str[] = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, str,
                null, null, null);
        while (cursor.moveToNext()) {
            list.add(cursor.getString(0)); // 图片绝对路径
        }
        return list;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    public static Bitmap compressBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    public static Bitmap compressBitmapFromFile(String file, int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(file, options);
    }

    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) {    // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    public static SpannableStringBuilder handleStringColor(String str, int color) {
        int bstart = str.indexOf("VS");
        int bend = bstart + "VS".length();
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), 0, bstart, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        style.setSpan(new ForegroundColorSpan(color), bend, str.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        return style;
    }
    public static SpannableStringBuilder handleStringTextColor(String str,int startIndex,int endIndex ,int color) {
        SpannableStringBuilder style = new SpannableStringBuilder(str);
        style.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
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

    @NonNull
    public static BaseRecyclerAdapter getGamesAdapter(Context mContext, RecyclerView rvContent) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, mContext.getString(R.string.SportBook), "SportBook"));
        dataList.add(new MenuItemInfo(R.mipmap.home_live, mContext.getString(R.string.Live_Casino), "Live_Casino"));
        dataList.add(new MenuItemInfo(R.mipmap.home_financials, mContext.getString(R.string.Financial), "Financial"));
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, mContext.getString(R.string.Europe_View), "Europe"));
        dataList.add(new MenuItemInfo(R.mipmap.home_specals4d, mContext.getString(R.string.Specials_4D), "Specials_4D"));
        dataList.add(new MenuItemInfo(R.mipmap.home_huay_thai, mContext.getString(R.string.Huay_Thai), "Huay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.home_muay_thai, mContext.getString(R.string.Muay_Thai), "Muay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.home_games, mContext.getString(R.string.E_Sport), "E_Sport"));
        dataList.add(new MenuItemInfo(R.mipmap.home_keno2, mContext.getString(R.string.Myanmar_Odds), "Myanmar_Odds"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_keno, mContext.getString(R.string.Keno), "Keno"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_poker, mContext.getString(R.string.Poker), "Poker"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_lottery, mContext.getString(R.string.Lottery), "Lottery"));

//        dataList.add(new MenuItemInfo(R.mipmap.home_roulette, getString(R.string.Roulette), "Roulette"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_casino, getString(R.string.Casino), "Casino"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_discount, getString(R.string.Discount), "Discount"));

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
     * @param str
     * @param edtext
     * @return sb.toString()
     */
    public static String addComma(String str,TextView edtext){

        touzi_ed_values22 = edtext.getText().toString().trim().replaceAll(",","");

        boolean neg = false;
        if (str.startsWith("-")){  //处理负数
            str = str.substring(1);
            neg = true;
        }
        String tail = null;
        if (str.indexOf('.') != -1){ //处理小数点
            tail = str.substring(str.indexOf('.'));
            str = str.substring(0, str.indexOf('.'));
        }
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        for (int i = 3; i < sb.length(); i += 4){
            sb.insert(i, ',');
        }
        sb.reverse();
        if (neg){
            sb.insert(0, '-');
        }
        if (tail != null){
            sb.append(tail);
        }
        return sb.toString();
    }

}
