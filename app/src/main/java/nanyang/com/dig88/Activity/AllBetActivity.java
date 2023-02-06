package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import butterknife.Bind;
import nanyang.com.dig88.Entity.AllBetBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2016/10/12.
 */
public class AllBetActivity extends BaseWebGameActivity {
    @Bind(R.id.web_left_back)
    ImageView exitImgl;
    HttpClient httpClient;
    String requestUrl = "http://obcasino.dig88api.com/api/login.php?";
    private Thread GetSportTokenThread = null;
    private Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            super.dispatchMessage(msg);
            if (msg.what == 1) {
                webView.loadUrl((String) msg.obj);
            } else if (msg.what == 2) {
                dismissBlockDialog();
                Toast.makeText(mContext, "login error", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public String getType2() {
        return "129";
    }

    private String getLoginLanguage() {
        String lg = AppTool.getAppLanguage(mContext);
        if (TextUtils.isEmpty(lg)) {
            return "en";
        }
        switch (lg) {
            case "en":
                return "en";
            case "zh":
                return "cn";
            case "kr":
                return "kr";
            case "in":
                return "id";
            case "th":
                return "th";
            case "vn":
                return "vn";
            case "ms":
                return "ma";
            case "kh":
                return "kh";
            default:
                return "en";
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        AppTool.setAppLanguage(mContext, "");
        setBackMargin();
        exitImgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGoBack();
            }
        });
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

    private void setBackMargin() {
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) exitImgl.getLayoutParams();
        params.leftMargin = UIUtil.dip2px(mContext, 50);
        exitImgl.setLayoutParams(params);
    }

    private void isGoBack() {
        webView.destroy();
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.leftClick();
    }

    @Override
    protected void leftClick() {
        isGoBack();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            isGoBack();
            return true;
        }
        return false;
    }

    @Override
    public void onLoadFinish() {
        super.onLoadFinish();
        if (exitImgl != null) {
            exitImgl.setVisibility(View.VISIBLE);
        }
    }

    public class GetSportToken implements Runnable {
        @Override
        public void run() {
            try {
                LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
                String params = "web_id=" + WebSiteUrl.WebId;
                params += "&token=" + getUserInfoBean().getSession_id();
                params += "&isfree=0&platform=mobile";
                params += "&language=" + getLoginLanguage();
                params += "&username=" + s.getUsername();
                String resultStr = httpClient.sendPost(requestUrl + params, "");
                AllBetBean allBetBean = new Gson().fromJson(resultStr, AllBetBean.class);
                handler.sendMessage(handler.obtainMessage(1, allBetBean.getLoginURL()));
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(2);
            }

        }
    }
}
