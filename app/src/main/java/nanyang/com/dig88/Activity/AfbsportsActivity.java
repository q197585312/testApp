package nanyang.com.dig88.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.AfbH5HostBean;
import nanyang.com.dig88.Entity.AfbLimitBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BlockDialog;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2016/10/12.
 */
public class AfbsportsActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    String username;
    int firstIndex;
    int lastIndex;
    //注册接口
    //http://api.dig88.com/api.aspx?secret=dig888&agent=t@dc&username=2sliangshui&action=create&currency=IDR
    String registUrl;
    String loginUrl;
    String urlHead;
    String host;
    private Thread GetSportTokenThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl((String) msg.obj);
            } else if (msg.what == 2) {
                dismissBlockDialog();
            } else if (msg.what == 3) {
                dismissBlockDialog();
                Toast.makeText(mContext, "login error", Toast.LENGTH_SHORT).show();
                finish();
            } else if (msg.what == 4) {
                setConnection();
            }
        }
    };

    //判断是手机还是平板
    private boolean isPad(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        setTitle(getString(R.string.afb88_sports_h5));
        httpClient = new HttpClient("");
        AppTool.setAppLanguage(mContext, "");
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        GetSportToken getSportToken = new GetSportToken();
        GetSportTokenThread = new Thread(getSportToken);
        GetSportTokenThread.start();
    }

    private void isGoBack() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            super.leftClick();
        }

    }

    @Override
    protected void leftClick() {
        isGoBack();
    }

    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isGoBack();
            return true;
        }
        return false;
    }

    private void setConnection() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    String connectionResult = httpClient.sendPost(getConnectionUrl(), "");
                    if (connectionResult.contains("<errcode>0</errcode>")) {
                        handler.sendEmptyMessage(2);
                    } else if (connectionResult.contains("<errcode>2</errcode>")) {
                        handler.sendEmptyMessage(3);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private String getConnectionUrl() {
        String agent = WebSiteUrl.AfbAgent;
        String username = getUserInfoBean().getUser_id();
        String url = WebSiteUrl.RegisterAfbSports + "secret=dig888&agent=" + agent + "&username=" + username + "&action=deposit&serial=" + System.currentTimeMillis() + "&amount=10000000000";
        return url;
    }

    @Override
    public void onLoadFinish() {
        super.onLoadFinish();
        handler.sendEmptyMessage(4);
    }

    @Override
    public String getType2() {
        return "52";
    }

    public class GetSportToken implements Runnable {
        @Override
        public void run() {
            username = getUserInfoBean().getUser_id();
            String agent = WebSiteUrl.AfbAgent;
            String currency = getCurrency();
            if (currency.equals("GBP")) {
                currency = "GBT";
            }
            try {
                Gson gson = new Gson();
                LoginInfoBean logininfobean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
                String s = httpClient.sendPost("http://app.info.dig88api.com/index.php?page=sports_afb_submitter&web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfoBean().getUser_id() + "&username=" + logininfobean.getUsername() + "&currency=" + getCurrency(), "");
                AfbH5HostBean afbH5HostBean = gson.fromJson(s, AfbH5HostBean.class);
                Intent intent = getIntent();
                String sportType = intent.getStringExtra("sportType");
                urlHead = afbH5HostBean.getURL();
                if (sportType.equals("3G")) {
                    host = afbH5HostBean.getHost();
                } else {
                    host = afbH5HostBean.getHostDesk();
                }
                registUrl = urlHead + "secret=dig888&agent=" + agent + "&username=" + username + "&action=create&currency=" + currency;
                //请求限制数据
                String limitResule = httpClient.sendPost("http://app.info.dig88api.com/index.php?page=bet_limit_sport", "web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfoBean().getUser_id());
                AfbLimitBean afbLimitBean = gson.fromJson(limitResule, AfbLimitBean.class);
                String registResult = httpClient.sendPost(registUrl, "");
                if (registResult.contains("<errcode>1</errcode>") || registResult.contains("<errcode>0</errcode>")) {
//http://api.dig88.com/api.aspx?secret=".$secret."&agent=".$agent."&username=".$username."&action=update&max1=$max&max2=0&max3=0&max4=0&min1=$min&lim1=0&lim2=$max2&lim3=0&com1=0&com2=0&com3=0&comtype=A&suspend=0
                    //注册成功后设置限制
                    String updatePamars = "";
                    A:
                    for (int i = 0; i < afbLimitBean.getData().size(); i++) {
                        AfbLimitBean.DataBean dataBean = afbLimitBean.getData().get(i);
                        if (dataBean.getType2().equals("52")) {
                            String limit = afbLimitBean.getData().get(i).getMin_max();
                            String[] limitArr = limit.split("\\|");
                            B:
                            for (int j = 0; j < limitArr.length; j++) {
                                if (limitArr[j].startsWith(getCurrency())) {
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
                                        String limitResult = httpClient.sendPost(url, "");
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
                                            String limitResult = httpClient.sendPost(urlHead + updatePamars, "");
                                            Log.d("limitResult", "run: ");
                                        }
                                    }
                                    break B;
                                }
                            }
                        }
                        break A;
                    }
                    //限制设置后登录，登录接口
                    //http://api.dig88.com/api.aspx?secret=dig888&agent=t@dc&username=2sliangshui&action=login&host=wapsport.dig88api.com&lang=EN-US
                    String lang = AppTool.getAppLanguage(mContext);
                    String lg = "EN-US";
                    if (lang.equals("zh")) {
                        lg = "ZH-CN";
                    } else if (lang.equals("kr")) {
                        lg = "EN-TT";
                    } else if (lang.equals("zh_TW")) {
                        lg = "ZH-TW";
                    } else {
                        lg = "EN-US";
                    }
                    loginUrl = urlHead + "secret=dig888&agent=" + agent + "&username=" + username + "&action=login&host=" + host + "&lang=" + lg;
                    String loginResult = httpClient.sendPost(loginUrl, "");
                    if (loginResult.contains("<result>http://")) {
                        firstIndex = loginResult.indexOf("<result>") + "<result>".length();
                        lastIndex = loginResult.indexOf("</result>");
                    }
                    String trueUrl = loginResult.substring(firstIndex, lastIndex);
                    handler.sendMessage(handler.obtainMessage(1, trueUrl));
                } else {
                    handler.sendEmptyMessage(3);
                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(3);
            }

        }
    }
}
