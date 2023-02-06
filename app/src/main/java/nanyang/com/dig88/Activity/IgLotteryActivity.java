package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.GameMaintenanceBean;
import nanyang.com.dig88.Entity.IgLotteryBalanceBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.OkhttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by Administrator on 2018/3/26.
 */

public class IgLotteryActivity extends BaseActivity {
    @Bind(R.id.tv_master_balance)
    TextView tv_master_balance;
    @Bind(R.id.tv_lottery_balance)
    TextView tv_lottery_balance;
    @Bind(R.id.tv_master_balance_name)
    TextView tv_master_balance_name;
    @Bind(R.id.tv_lottery_balance_name)
    TextView tv_lottery_balance_name;
    @Bind(R.id.edt_buy)
    EditText edt_buy;
    @Bind(R.id.edt_takeout)
    EditText edt_takeout;
    @Bind(R.id.ll_transfer)
    LinearLayout ll_transfer;
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.rl_game_content)
    RelativeLayout rl_game_content;
    @Bind(R.id.img_maintenance)
    ImageView img_maintenance;
    HttpClient httpClient;
    LoginInfoBean s;
    Gson gson = new Gson();
    String amount;
    private int buy = 7;
    private int takeout = 8;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ig_lottery_transfer_money;
    }

    @Override
    public void initView() {
        super.initView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.ig_lottery));
        initGameStatus();
    }

    private void initGameContent() {
        tv_master_balance_name.setText(getString(R.string.master_balance) + "(" + getCurrency() + "):");
        tv_lottery_balance_name.setText("IG "+getString(R.string.Balance) + "(" + getCurrency() + "):");
        httpClient = new HttpClient("");
        initWeb();
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        updataAccunt(getUserInfoBean().getMoneyBalance().getBalance());
    }

    private void initGameStatus() {
        HttpUtils.httpPost(WebSiteUrl.GameMaintenanceUrl, "type2=" + "251", new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                GameMaintenanceBean gameMaintenanceBean = gson.fromJson(s, GameMaintenanceBean.class);
                String status = gameMaintenanceBean.getData().getStatus();
                if (status.equals("1")) {
                    rl_game_content.setVisibility(View.GONE);
                    img_maintenance.setVisibility(View.VISIBLE);
                } else {
                    initGameContent();
                }
            }

            @Override
            public void onRequestFailed(String s) {
                initGameContent();
            }
        });
    }

    private void back() {
        if (webView.getVisibility() == View.VISIBLE) {
            webView.setVisibility(View.INVISIBLE);
            ll_transfer.setVisibility(View.VISIBLE);
        } else {
            ViewGroup view = (ViewGroup) getWindow().getDecorView();
            view.removeAllViews();
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            back();
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void leftClick() {
        back();
    }

    @OnClick({R.id.btn_start_ssc, R.id.btn_start_lhc, R.id.btn_buy, R.id.btn_takeout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_ssc:
                ll_transfer.setVisibility(View.GONE);
                loadWeb("0");
                break;
            case R.id.btn_start_lhc:
                ll_transfer.setVisibility(View.GONE);
                loadWeb("1");
                break;
            case R.id.btn_buy:
                transferMoney(buy);
                break;
            case R.id.btn_takeout:
                transferMoney(takeout);
                break;
        }
    }

    private void transferMoney(int type) {
        String url;
        if (type == buy) {
            url = WebSiteUrl.IgLotteryDeposit;
            amount = edt_buy.getText().toString().toString().toString().trim();
        } else {
            url = WebSiteUrl.IgLotteryWithdraw;
            amount = edt_takeout.getText().toString().toString().toString().trim();
        }
        url += "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&amount=" + amount;
        if (TextUtils.isEmpty(amount)) {
            Toast.makeText(mContext, getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
            return;
        }
        showBlockDialog();

        OkhttpUtils.getRequest(url, new OkhttpUtils.Result() {
            @Override
            public void onSuccess(String result) {
                if (result.contains("1") && result.contains("No Error")) {
                    Toast.makeText(mContext, getString(R.string.Success), Toast.LENGTH_SHORT).show();
                    getVipInfo();
                } else {
                    dismissBlockDialog();
                    Toast.makeText(mContext, getString(R.string.Balance_not_enough), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailed(String result) {
                dismissBlockDialog();
                Toast.makeText(mContext, getString(R.string.Failed), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initWeb() {
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        webView.getSettings().setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        webView.getSettings().setSupportZoom(true);//是否可以缩放，默认true
        webView.getSettings().setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        webView.getSettings().setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        webView.getSettings().setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        webView.getSettings().setAppCacheEnabled(false);//是否使用缓存
        webView.getSettings().setDomStorageEnabled(true);//DOM Storage
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                if (webView != null) {
                    webView.setVisibility(View.VISIBLE);
                }
                dismissBlockDialog();
            }
        });
    }

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
                case "my":
                    return "ma";
                case "kh":
                    return "ca";
            }
        }
        return "en";
    }

    private void loadWeb(String type) {
        showBlockDialog();
        String loginUrl = WebSiteUrl.IgLotteryUrl += "token=" + getUserInfoBean().getSession_id() + "&from=android&language=" + getLanguage() + "&type=" + type;
        webView.loadUrl(loginUrl);

    }

    //转账后更新帐号余额
    public void getVipInfo() {
        NyVolleyJsonThreadHandler<VipInfoBean> registThread = new NyVolleyJsonThreadHandler<VipInfoBean>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfoBean().getUser_id());
                params.put("session_id", getUserInfoBean().getSession_id());
                return new QuickRequestBean(WebSiteUrl.MemberInfoSubmitter, params
                        , new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, VipInfoBean data) {
                if (data != null) {
                    AppTool.saveObjectData(mContext, "vipInfo", data);
                    updataAccunt(data.getBalance());
                }
            }
        };
        registThread.startThread(null);
    }

    private void updateLotteryBalance() {
        showBlockDialog();
        String balanceUrl = WebSiteUrl.IgLotteryBalance += "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername();
        OkhttpUtils.getRequest(balanceUrl, new OkhttpUtils.Result() {
            @Override
            public void onSuccess(String result) {
                IgLotteryBalanceBean balanceBean = gson.fromJson(result, IgLotteryBalanceBean.class);
                if (balanceBean.getErrorCode() == 0) {
                    tv_lottery_balance.setText(balanceBean.getParams().getBalance() + "");
                    edt_buy.setText("");
                    edt_takeout.setText("");
                }
                try {
                    Thread.sleep(3000);
                    dismissBlockDialog();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailed(String result) {
                tv_lottery_balance.setText("0.00");
                dismissBlockDialog();
            }
        });
    }

    private void updataAccunt(String balance) {
        if (TextUtils.isEmpty(balance))
            return;
        try {
            double zhanghuyue = Double.valueOf(balance);
            String result = String.format("%.2f", zhanghuyue);
            tv_master_balance.setText(result);
            updateLotteryBalance();
        } catch (Exception e) {

        }
    }

}
