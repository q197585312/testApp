package nanyang.com.dig88.Util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.HashMap;

import nanyang.com.dig88.Activity.AgFishActivity;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.FfylPokerActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.AgFishConfigBean;
import nanyang.com.dig88.Entity.FFYLPokerBalanceBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.quick.QuickRequestBean;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/4/23.
 */

public class PopAgFishTransfer extends BasePopupWindow implements View.OnClickListener {
    String balanceUrl;
    String buyUrl;
    String takeOutUrl;
    UserInfoBean u;
    BaseActivity aty;
    String buyMoney;
    String takeOutMoney;
    HttpClient httpClient;
    private TextView tv_master_balance, tv_poker_balance, tv_lottery_balance_name;
    private Button btn_start, btn_buy, btn_takeout;
    private EditText edt_buy, edt_takeout;
    private int typeBuy = 1;
    private int typeTakeOut = 2;
    private int typeGetBalance = 3;
    private int requestType;
    private Gson gson = new Gson();
    public PopAgFishTransfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_texas_poker_transfer;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        httpClient = new HttpClient("");
        tv_master_balance = view.findViewById(R.id.tv_master_balance);
        tv_poker_balance = view.findViewById(R.id.tv_poker_balance);
        tv_lottery_balance_name = view.findViewById(R.id.tv_lottery_balance_name);
        tv_lottery_balance_name.setText("Ag " + context.getString(R.string.Fishing) + " " + context.getString(R.string.Balance));
        btn_start = view.findViewById(R.id.btn_start);
        edt_buy = view.findViewById(R.id.edt_buy);
        edt_takeout = view.findViewById(R.id.edt_takeout);
        btn_buy = view.findViewById(R.id.btn_buy);
        btn_takeout = view.findViewById(R.id.btn_takeout);
        edt_buy.setKeyListener(new DigitsKeyListener(false, true) {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                return numberChars;
            }
        });
        edt_takeout.setKeyListener(new DigitsKeyListener(false, true) {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                return numberChars;
            }
        });
        btn_buy.setOnClickListener(this);
        btn_takeout.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        aty = (BaseActivity) context;
        u = aty.getUserInfoBean();
        updataAccunt(((BaseActivity) context).getUserInfoBean().getMoneyBalance().getBalance());
        typeBuy = 1;
        typeTakeOut = 2;
        typeGetBalance = 3;
        balanceUrl = "http://agcasino.dig88api.com/fish/index.php";
        buyUrl = "http://agcasino.dig88api.com/fish/transfer.php";
        takeOutUrl = "http://agcasino.dig88api.com/fish/transfer.php";
        requestType = typeGetBalance;
        aty.showLoadingDialog();
        getBalance();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_buy:
                buyMoney = edt_buy.getText().toString().trim();
                if (TextUtils.isEmpty(buyMoney)) {
                    Toast.makeText(context, context.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
                    return;
                }
                requestType = typeBuy;
                transfer();
                break;
            case R.id.btn_takeout:
                takeOutMoney = edt_takeout.getText().toString().trim();
                if (TextUtils.isEmpty(takeOutMoney)) {
                    Toast.makeText(context, context.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
                    return;
                }
                requestType = typeTakeOut;
                transfer();
                break;
            case R.id.btn_start:
                closePopupWindow();
                AppTool.activiyJump(context, AgFishActivity.class);
                break;
        }
    }

    public void transfer() {
        aty.showLoadingDialog();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(context, "loginInfo");
        String requestUrl = "";
        String param = "";
        if (requestType == typeBuy) {
            requestUrl = buyUrl;
            param = "buyin_amount=" + buyMoney + "&buyin=1" + "&member_id=" + aty.getUserInfoBean().getUser_id() + "&web_id=" + WebSiteUrl.WebId +
                    "&username=" + s.getUsername() + "&currency=" + aty.getUserInfoBean().getId_mod_currency() +
                    "&language=" + Dig88Utils.getLanguage(context) + "&token=" + aty.getUserInfoBean().getSession_id() + "&domain=855kg.com";
        } else if (requestType == typeTakeOut) {
            requestUrl = takeOutUrl;
            param = "buyout_amount=" + takeOutMoney + "&buyout=1" + "&member_id=" + aty.getUserInfoBean().getUser_id() + "&web_id=" + WebSiteUrl.WebId +
                    "&username=" + s.getUsername() + "&currency=" + aty.getUserInfoBean().getId_mod_currency() +
                    "&language=" + Dig88Utils.getLanguage(context) + "&token=" + aty.getUserInfoBean().getSession_id() + "&domain=855kg.com";
        }
        HttpUtils.httpPost(requestUrl, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String result) {
                if (result.contains("Succeed") || result.contains("No Error") || result.contains("Success")) {
                    Toast.makeText(context, context.getString(R.string.Success), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, context.getString(R.string.Balance_not_enough), Toast.LENGTH_SHORT).show();
                }
                edt_buy.setText("");
                edt_takeout.setText("");
                getVipInfo();
                requestType = typeGetBalance;
                getBalance();
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    public void getBalance() {
        String requestUrl = balanceUrl;
        String param = "?web_id=" + WebSiteUrl.WebId + "&member_id=" +
                aty.getUserInfoBean().getUser_id() + "&language=" + Dig88Utils.getLanguage(context) + "&token=" +
                aty.getUserInfoBean().getSession_id() + "&domain=855kg.com";
        HttpUtils.httpGet(requestUrl + param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String result) {
                AgFishConfigBean balanceBean = gson.fromJson(result, AgFishConfigBean.class);
                if (balanceBean.getCode() == 0) {
                    tv_poker_balance.setText(balanceBean.getGamebalance() + "");
                } else {
                    tv_poker_balance.setText("0");
                }
                aty.hideLoadingDialog();
            }

            @Override
            public void onRequestFailed(String s) {
                aty.hideLoadingDialog();
            }
        });
    }

    //转账后更新帐号余额
    public void getVipInfo() {
        NyVolleyJsonThreadHandler<VipInfoBean> registThread = new NyVolleyJsonThreadHandler<VipInfoBean>(context) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", u.getUser_id());
                params.put("session_id", u.getSession_id());
                return new QuickRequestBean(WebSiteUrl.MemberInfoSubmitter, params
                        , new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, VipInfoBean data) {
                if (data != null) {
                    AppTool.saveObjectData(context, "vipInfo", data);
                    updataAccunt(data.getBalance());
                }
            }
        };
        registThread.startThread(null);
    }

    private void updataAccunt(String balance) {
        if (TextUtils.isEmpty(balance))
            return;
        try {
            double zhanghuyue = Double.valueOf(balance);
            String result = StringUtils.floatDecimalFormat(zhanghuyue);
            tv_master_balance.setText(result);
            aty.showMoney(result);
        } catch (Exception e) {

        }
    }
}
