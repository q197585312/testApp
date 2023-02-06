package nanyang.com.dig88.Table.Thread;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.AfbH5HostBean;
import nanyang.com.dig88.Entity.AfbLimitBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.ICookie;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.MyLog;
import xs.com.mylibrary.allinone.util.ThreadPoolUtils;
import xs.com.mylibrary.allinone.util.WidgetUtil;

/**
 * Created by Administrator on 2015/11/9.
 */
public class TableHttpHelper<T> implements ICookie {
    public static HttpClient client;
    String username;
    String urlHead = "http://api.dig88.com";
    private MyHandler handler;
    private ThreadEndT<T> iEnd;
    private View dataView;
    private Context context;
    private View loadingV;
    private boolean showAble = true;

    public TableHttpHelper(Context context, View dataView, ThreadEndT<T> iEnd) {
        if (client == null)
            client = new HttpClient(this);
        this.dataView = dataView;
        this.context = context;
        handler = new MyHandler(context);
        this.iEnd = iEnd;
    }

    public View getDataView() {
        return dataView;
    }

    public void setDataView(View dataView) {
        this.dataView = dataView;
    }

    public void login(final LoginInfoBean infoBean, final String currency) {
        Runnable run = new Runnable() {
            @Override
            public void run() {
                String strRes = "";
                try {
                    //请求限制数据
                    LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(context, "loginInfo");
                    BaseActivity aty = (BaseActivity) context;
                    String limitResule = client.sendPost("http://app.info.dig88api.com/index.php?page=bet_limit_sport", "web_id=" + WebSiteUrl.WebId + "&member_id=" + aty.getUserInfoBean().getUser_id());
                    Gson gson = new Gson();
                    String afbH5HostResult = client.sendPost("http://app.info.dig88api.com/index.php?page=sports_afb_submitter&web_id=" + WebSiteUrl.WebId + "&member_id=" + aty.getUserInfoBean().getUser_id() + "&username=" + s.getUsername() + "&currency=" + aty.getCurrency(), "");
                    AfbH5HostBean afbH5HostBean = gson.fromJson(afbH5HostResult, AfbH5HostBean.class);
                    urlHead = afbH5HostBean.getURL();
                    AfbLimitBean afbLimitBean = gson.fromJson(limitResule, AfbLimitBean.class);
                    String updatePamars = "";
                    String agent = WebSiteUrl.AfbAgent;
                    username = WebSiteUrl.WebId + "s" + s.getUsername();
                    A:
                    for (int i = 0; i < afbLimitBean.getData().size(); i++) {
                        AfbLimitBean.DataBean dataBean = afbLimitBean.getData().get(i);
                        if (dataBean.getType2().equals("52")) {
                            String limit = afbLimitBean.getData().get(i).getMin_max();
                            String[] limitArr = limit.split("\\|");
                            B:
                            for (int j = 0; j < limitArr.length; j++) {
                                if (limitArr[j].startsWith(aty.getCurrency())) {
                                    String[] minMaxArr = limitArr[j].split("\\^");
                                    if (minMaxArr.length >= 3) {
                                        String max1 = minMaxArr[1];
                                        if (!TextUtils.isEmpty(dataBean.getMember_max()) && !dataBean.getMember_max().equals("0")) {
                                            max1 = dataBean.getMember_max();
                                        }
                                        String min1 = minMaxArr[2];
                                        if (!TextUtils.isEmpty(dataBean.getMember_min()) && !dataBean.getMember_min().equals("0")) {
                                            min1 = dataBean.getMember_min();
                                        }
                                        String lim2 = "";
                                        if (minMaxArr.length >= 4) {
                                            lim2 = minMaxArr[3];
                                        } else {
                                            lim2 = max1 + "0";
                                        }
                                        if (!TextUtils.isEmpty(dataBean.getMatch_max()) && !dataBean.getMatch_max().equals("0")) {
                                            lim2 = dataBean.getMatch_max();
                                        }
                                        updatePamars = "secret=dig888&agent=" + agent + "&username=" + username + "&action=update"
                                                + "&max1=" + max1 + "&max2=0&max3=0&max4=0&min1=" + min1 + "&lim1=0&lim2=" + lim2 +
                                                "&lim3=0&com1=0&com2=0&com3=0&comtype=A&suspend=0";
                                        String url = urlHead + updatePamars;
                                        String limitResult = client.sendPost(url, "");
                                        Log.d("limitResult", "run: ");
                                    } else {
                                        String max1 = "";
                                        if (!TextUtils.isEmpty(dataBean.getMember_max())) {
                                            max1 = dataBean.getMember_max();
                                        }
                                        String min1 = "";
                                        if (!TextUtils.isEmpty(dataBean.getMember_min())) {
                                            min1 = dataBean.getMember_min();
                                        }
                                        String lim2 = dataBean.getMember_max() + "0";
                                        if (!TextUtils.isEmpty(dataBean.getMatch_max()) && !dataBean.getMatch_max().equals("0")) {
                                            lim2 = dataBean.getMatch_max();
                                        }
                                        updatePamars = "secret=dig888&agent=" + agent + "&username=" + username + "&action=update"
                                                + "&max1=" + max1 + "&max2=0&max3=0&max4=0&min1=" + min1 + "&lim1=0&lim2=" + lim2 +
                                                "&lim3=0&com1=0&com2=0&com3=0&comtype=A&suspend=0";
                                        if (!TextUtils.isEmpty(max1) && !TextUtils.isEmpty(min1) && !max1.equals("0") && !min1.equals("0")) {
                                            String limitResult = client.sendPost(urlHead + updatePamars, "");
                                            Log.d("limitResult", "run: ");
                                        }
                                    }
                                    break B;
                                }
                            }
                        }
                        break A;
                    }
                    String url = "";

                    if (infoBean == null || infoBean.getUsername() == null || infoBean.getUsername().equals("")) {
                        handler.obtainMessage(0, "username can not be null").sendToTarget();
                        return;
                    } else {
//                        url = WebSiteUrl.SportDig88Login + infoBean.getUsername();
                        url = WebSiteUrl.RegisterAfbSports + "secret=" + WebSiteUrl.WebSiteName + "&agent=" + WebSiteUrl.AfbAgent + "&action=login" + "&host=" + WebSiteUrl.SportHost + "&username=" + aty.getUserInfoBean().getUser_id();
                    }
                    String l = AppTool.getAppLanguage(context);
                    String lang = "EN-US";
                    if (l != null) {
                        if (l.equals("zh")) {
                            lang = "ZH-CN";
                        } else if (l.equals("kr")) {
                            lang = "EN-TT";
                        } else if (l.equals("vn")) {
                            lang = "EN-IE";
                        } else if (l.equals("th")) {
                            lang = "TH-TH";
                        } else {
                            lang = "EN-US";
                        }
                    }
                    url = url + "&lang=" + lang;
                    Log.w("HttpVolley", url);
                    /*  params.put("secret", WebSiteUrl.WebSiteName);
                    params.put("agent", WebSiteUrl.AfbAgent);
                    params.put("username", WebSiteUrl.AfbUsername+stregistername);
                    params.put("action", "create");
                    params.put("currency", Currency);
                    //"http://api.dig88.com/api.aspx?"
                    return new QuickRequestBean(RequestBean.Method.GET,WebSiteUrl.RegisterAfbSports, params
                            , new TypeToken<Object>() {}.getType());*/
                    try {
                        String registerUrl = client.setHttpClient(WebSiteUrl.RegisterAfbSports + "secret=" + WebSiteUrl.WebSiteName +
                                "&agent=" + WebSiteUrl.AfbAgent +
                                "&username=" + aty.getUserInfoBean().getUser_id() +
                                "&action=" + "create" +
                                "&currency=" + currency);
                        String urlDeposit = WebSiteUrl.RegisterAfbSports + "secret=dig888&agent=" + WebSiteUrl.AfbAgent + "&username=" + username + "&action=deposit&serial=" + System.currentTimeMillis() + "&amount=10000000000";
                        String urlDepositResult = client.sendPost(urlDeposit, "");
                        Log.d("urlDepositResult", "run: ");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    strRes = client.setHttpClient(url);
                    Log.w("HttpVolley", strRes);
                    String[] sourceStrArray = strRes.split("<result>");
                    //	System.out.println(sourceStrArray[1]);
                    sourceStrArray = sourceStrArray[1].split("</result>");
                    // 	System.out.println(sourceStrArray[0]);
                    Log.w("HttpVolley", sourceStrArray[0]);
                    strRes = client.setHttpClient(sourceStrArray[0]);
                    Log.w("HttpVolley", strRes);
                    strRes = strRes.substring(strRes.indexOf("\"") + 1, strRes.lastIndexOf("\""));
                    if (strRes != null && strRes.startsWith("http:")) {
                        strRes = strRes.replace("&amp;", "&");
                        strRes = client.setHttpClient(strRes);
                        Log.w("HttpVolley", strRes);
                    }
                    handler.obtainMessage(1, strRes).sendToTarget();
                    ((BaseActivity) context).getApp().setBetParList(null);
                    ((BaseActivity) context).getApp().setBetDetail(null);
                } catch (Exception e) {
//                    if (strRes.contains("System Maintenance")) {
//                        handler.obtainMessage(0, "System Maintenance").sendToTarget();
//                    } else
//                        handler.obtainMessage(0, e.getMessage()).sendToTarget();
                    handler.obtainMessage(0, "System Maintenance").sendToTarget();
                }
            }
        };
        if (dataView != null) {
            loadingV = LayoutInflater.from(context).inflate(
                    xs.com.mylibrary.R.layout.green_center_loading, null);
            loadingV.setVisibility(View.VISIBLE);
            WidgetUtil.coverView(dataView, loadingV);
        }
        ThreadPoolUtils.execute(run);
    }

    public void getData(final String url, final String params, final int model) {
        showAble = true;
        Runnable run = new Runnable() {
            @Override
            public void run() {

                Log.w("HttpVolley", url + "&" + params);
                String strRes = "";
                try {
                    strRes = client.sendPost(url, params);
                    Log.w("HttpVolley", strRes);
                    strRes = getFormatString(strRes);
                    T t = null;
                    if (iEnd.getT() != null) {
                        t = iEnd.gsonAnalysis(strRes);
                        handler.obtainMessage(1, model, 0, t).sendToTarget();
                        handler.obtainMessage(7, model, 0, t).sendToTarget();
                    } else {
                        handler.obtainMessage(1, model, 0, strRes).sendToTarget();
                        handler.obtainMessage(7, model, 0, strRes).sendToTarget();
                    }
                } catch (Exception e) {
                    handler.obtainMessage(0, model, 0, strRes).sendToTarget();
                }

            }
        };
        startThread(run, showAble);

    }

    @NonNull
    protected String getFormatString(String strRes) {
        if (strRes != null && !strRes.equals("")) {
            strRes.replaceAll("<br>", " ");
//            strRes = Html.fromHtml(strRes).toString();
            strRes.replaceAll("\\n", " ");
            strRes = strRes.replaceAll(" +", " ");
            Log.w("HttpVolley", strRes);
        }
        return strRes;
    }
//
//    public void getData(final String url, final String params, String Model) {
//        showAble = true;
//        if (Model.equals("POST")) {
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                        Log.w("HttpVolley", url + "&" + params);
//                        String strRes = "";
//                        try {
//                            strRes = client.sendPost(url, params);
//                            strRes = getFormatString(strRes);
//                            T t = iEnd.gsonAnalysis(strRes);
//                            handler.obtainMessage(1, t).sendToTarget();
//                        } catch (Exception e) {
//                            handler.obtainMessage(0, strRes).sendToTarget();
//                        }
//                }
//            };
//
//            startThread(run, showAble);
//        } else {
//            Runnable run = new Runnable() {
//                @Override
//                public void run() {
//                    String strRes = "";
//                    try {
//                        Log.w("HttpVolley", url + "&" + params);
//                        strRes = client.setHttpClient(url);
//                        strRes = getFormatString(strRes);
//                        T t = iEnd.gsonAnalysis(strRes);
//                        handler.obtainMessage(1, t).sendToTarget();
//                    } catch (Exception e) {
//                        handler.obtainMessage(0, strRes).sendToTarget();
//                    }
//                }
//            };
//
//            startThread(run, showAble);
//        }
//
//    }

    public void updateData(final String url, final String params, boolean showAble, int model) {
        updateData(url, params, showAble, true, model);
    }

    public void updateData(final String url, final String params, boolean showAble, final boolean format, final int model) {
        this.showAble = showAble;
        Runnable run = new Runnable() {
            @Override
            public void run() {
                Log.w("HttpVolley", url + "&" + params);
                String strRes = "";
                try {
                    strRes = client.sendPost(url, params);
                    Log.w("HttpVolley", strRes);
                    if (format)
                        strRes = getFormatString(strRes);
                    handler.obtainMessage(2, model, 0, strRes).sendToTarget();
                } catch (IOException e) {
                    handler.obtainMessage(0, model, 0, strRes).sendToTarget();
                    MyLog.w("Error", "netError:" + e.getMessage() + "  url:" + url);
                }
            }
        };
        startThread(run, showAble);
    }

    public void updateData(String url, String params, int model) {
        updateData(url, params, false, model);
    }

    private void startThread(Runnable run, boolean showAble) {
        if (dataView != null && showAble) {
            loadingV = LayoutInflater.from(context).inflate(
                    xs.com.mylibrary.R.layout.green_center_loading, null);
            loadingV.setVisibility(View.VISIBLE);
            WidgetUtil.coverView(dataView, loadingV);
        }
        ThreadPoolUtils.execute(run);
    }

    public void handleLoginout(T result, int model) {
    }

    @Override
    public void saveCookie(String cookie) {
        ((BaseActivity) context).getApp().setCookie(cookie);
    }

    @Override
    public String getCookie() {
        return ((BaseActivity) context).getApp().getCookie();
    }

    private class MyHandler extends Handler {
        private final WeakReference mActivity;

        public MyHandler(Context activity) {
            mActivity = new WeakReference(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                iEnd.endT((T) msg.obj, msg.arg1);
            } else if (msg.what == 0) {
                String error = "";
                if (msg.obj != null)
                    error = msg.obj.toString();
                iEnd.endError(error);
            } else if (msg.what == 2) {
                iEnd.endString(msg.obj.toString(), msg.arg1);
            } else if (msg.what == 7) {
                handleLoginout((T) msg.obj, msg.arg1);
            }
            if (loadingV != null) {
                loadingV.setVisibility(View.GONE);
                dataView.setVisibility(View.VISIBLE);
            }

        }
    }

}
