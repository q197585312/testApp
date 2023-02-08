package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.AgLoginBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class AgCasinoActivity extends BaseWebGameActivity {
    HttpClient httpClient;
    private Thread CheckOrCreateAccThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl((String) msg.obj);
            } else if (msg.what == 4) {
                Toast.makeText(getApplication(), "login error~", Toast.LENGTH_SHORT).show();
                dismissBlockDialog();
                finish();
            }
        }
    };

    private String getLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
            }
        }
        return "en";
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_web;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        setTitle(getString(R.string.ag_live_entertainment));
    }

    @Override
    public String getType2() {
        return "96";
    }

    @Override
    public void initGameData() {
        super.initGameData();
        setDialog(new BlockDialog(mContext, getString(R.string.zhengjiazai)));
        showBlockDialog();
        CheckOrCreateAcc getSportToken = new CheckOrCreateAcc();
        CheckOrCreateAccThread = new Thread(getSportToken);
        CheckOrCreateAccThread.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            webView.destroy();
            handler = null;
            finish();
            return true;
        }
        return false;
    }

    public class CheckOrCreateAcc implements Runnable {
        @Override
        public void run() {
            String currency = getCurrency();
            LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
            String name = s.getUsername();
            try {
                String loginUrl = "http://agcasino.k-api.com/api/login.php?" + "currency=" + currency + "&web_id=" + WebSiteUrl.WebId +
                        "&language=" + getLanguage() + "&username=" + name + "&token=" + getUserInfoBean().getSession_id()+"&platfrom=mobile";
                String loginResult = httpClient.sendPost(loginUrl, "");
                AgLoginBean agLoginBean = new Gson().fromJson(loginResult, AgLoginBean.class);
                if (agLoginBean.getErrMsg().equals("No Error")) {
                    handler.sendMessage(handler.obtainMessage(1, agLoginBean.getData()));
                } else {
                    handler.sendMessage(handler.obtainMessage(4));
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

        }
    }
}
