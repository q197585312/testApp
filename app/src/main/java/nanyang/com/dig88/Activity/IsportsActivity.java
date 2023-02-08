package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


import java.io.IOException;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.IbcUrlBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class IsportsActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    String token;
    private Thread GetMaxMinBetThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                dismissBlockDialog();
                Toast.makeText(getApplication(), "正在维护,请稍后", Toast.LENGTH_SHORT).show();
                finish();
            } else if (msg.what == 3) {
                String url = (String) msg.obj;
                webView.loadUrl(url);
            }
        }
    };

    @Override
    protected void initData(Bundle savedInstanceState) {
        //设置窗口风格为进度条
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.i_sport_betting));
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        GetMaxMinBet getMaxMinBet = new GetMaxMinBet();
        GetMaxMinBetThread = new Thread(getMaxMinBet);
        GetMaxMinBetThread.start();
    }

    private String getGameUrl() {
        //http://sbsports.k-api.com/api/login.php?web_id=$web_id&username=$username&token=$token&platform=$platform&language=$language
        String lg = AppTool.getAppLanguage(mContext);
        AppTool.setAppLanguage(mContext, lg);
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        String lang = "en";
        if (lg.equals("zh")) {
            lang = "cn";
        } else if (lg.equals("kr")) {
            lang = "ko";
        } else if (lg.equals("in")) {
            lang = "id";
        }

        String url = "http://sbsports.k-api.com/api/login.php?" + "web_id=" + WebSiteUrl.WebId +
                "&username=" + s.getUsername() + "&token=" + getUserInfoBean().getSession_id() + "&platform=mobile" +
                "&language=" + lang;
        return url;
    }

    @Override
    public String getType2() {
        return "98";
    }

//    private void isGoBack() {
//        if (webView.canGoBack()) {
//            webView.goBack();
//        } else {
//            ViewGroup view = (ViewGroup) getWindow().getDecorView();
//            view.removeAllViews();
//            super.leftClick();
//        }
//    }

//    @Override
//    protected void leftClick() {
//        isGoBack();
//    }
//
//    @Override
//    //设置回退
//    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            isGoBack();
//            return true;
//        }
//        return false;
//    }

    public class GetMaxMinBet implements Runnable {
        @Override
        public void run() {
            try {
                String url = getGameUrl();
                String requestStr = httpClient.sendPost(url, "");
                if (requestStr.contains("No Error")) {
                    IbcUrlBean ibcUrlBean = gson.fromJson(requestStr, IbcUrlBean.class);
                    handler.sendMessage(handler.obtainMessage(3, ibcUrlBean.getLoginURL()));
                } else {
                    handler.sendEmptyMessage(1);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
