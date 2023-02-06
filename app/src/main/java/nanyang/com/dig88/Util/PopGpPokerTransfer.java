package nanyang.com.dig88.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.GpPokerBalanceBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.quick.QuickRequestBean;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/4/23.
 */

public class PopGpPokerTransfer extends BasePopupWindow implements View.OnClickListener {
    String balanceUrl = "http://gppoker.dig88api.com/api/balance.php";
    String buyUrl = "http://gppoker.dig88api.com/api/deposit.php";
    String takeOutUrl = "http://gppoker.dig88api.com/api/withdraw.php";
    UserInfoBean u;
    BaseActivity aty;
    String buyMoney;
    String takeOutMoney;
    private TextView tv_master_balance, tv_poker_balance, tv_poker_balance_name;
    private Button btn_start, btn_buy, btn_takeout;
    private EditText edt_buy, edt_takeout;
    private int typeBuy = 1;
    private int typeTakeOut = 2;
    private int typeGetBalance = 3;
    private int requestType;
    private Gson gson = new Gson();
    public PopGpPokerTransfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_texas_poker_transfer;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tv_master_balance = (TextView) view.findViewById(R.id.tv_master_balance);
        tv_poker_balance = (TextView) view.findViewById(R.id.tv_poker_balance);
        tv_poker_balance_name = (TextView) view.findViewById(R.id.tv_lottery_balance_name);
        tv_poker_balance_name.setText(context.getString(R.string.texas_holdem));
        btn_start = (Button) view.findViewById(R.id.btn_start);
        edt_buy = (EditText) view.findViewById(R.id.edt_buy);
        edt_takeout = (EditText) view.findViewById(R.id.edt_takeout);
        btn_buy = (Button) view.findViewById(R.id.btn_buy);
        btn_takeout = (Button) view.findViewById(R.id.btn_takeout);
        btn_buy.setOnClickListener(this);
        btn_takeout.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        aty = (BaseActivity) context;
        u = aty.getUserInfoBean();
        updataAccunt(((BaseActivity) context).getUserInfoBean().getMoneyBalance().getBalance());
        typeBuy = 1;
        typeTakeOut = 2;
        typeGetBalance = 3;
        balanceUrl = "http://gppoker.dig88api.com/api/balance.php";
        buyUrl = "http://gppoker.dig88api.com/api/deposit.php";
        takeOutUrl = "http://gppoker.dig88api.com/api/withdraw.php";
        requestType = typeGetBalance;
        request();
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
                request();
                break;
            case R.id.btn_takeout:
                takeOutMoney = edt_takeout.getText().toString().trim();
                if (TextUtils.isEmpty(takeOutMoney)) {
                    Toast.makeText(context, context.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
                    return;
                }
                requestType = typeTakeOut;
                request();
                break;
            case R.id.btn_start:
                String lg = AppTool.getAppLanguage(context);
                String language = "en";
                if (!TextUtils.isEmpty(lg)) {
                    switch (lg) {
                        case "zh":
                            language = "zh-cn";
                            break;
                        case "vn":
                            language = "vi";
                            break;
                        case "in":
                            language = "id";
                            break;
                        case "th":
                            language = "th";
                            break;
                        case "en":
                            language = "en";
                            break;
                    }
                }
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                String pokerWebUrl = "https://contents.good-game-network.com/mobile/ggpokersite/download/" + language + "?btag1=khmergaming&btag2=" + WebSiteUrl.WebId + "s";
                Uri content_url = Uri.parse(pokerWebUrl);
                intent.setData(content_url);
                context.startActivity(intent);
                closePopupWindow();
                break;
        }
    }

    private void request() {
        aty.showBlockDialog();
        LoginInfoBean s = (LoginInfoBean) AppTool.getObjectData(context, "loginInfo");
        String requestUrl = balanceUrl;
        String param = "?web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername();
        if (requestType == typeBuy) {
            requestUrl = buyUrl;
            param += "&token=" + u.getSession_id() + "&amount=" + buyMoney;
        } else if (requestType == typeTakeOut) {
            requestUrl = takeOutUrl;
            param += "&token=" + u.getSession_id() + "&amount=" + takeOutMoney;
        }
        OkhttpUtils.getRequest(requestUrl + param, new OkhttpUtils.Result() {
            @Override
            public void onSuccess(String result) {
                if (requestType == typeBuy || requestType == typeTakeOut) {
                    if (result.contains("Succeed") || result.contains("No Error")) {
                        Toast.makeText(context, context.getString(R.string.Success), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, context.getString(R.string.Balance_not_enough), Toast.LENGTH_SHORT).show();
                    }
                    edt_buy.setText("");
                    edt_takeout.setText("");
                    getVipInfo();
                    requestType = typeGetBalance;
                    request();
                } else {
                    GpPokerBalanceBean gpPokerBalanceBean = gson.fromJson(result, GpPokerBalanceBean.class);
                    if (gpPokerBalanceBean.getErrorMsg().equals("No Error")) {
                        tv_poker_balance.setText(gpPokerBalanceBean.getBalance() + "");
                    } else {
                        tv_poker_balance.setText("0");
                    }
                }
                aty.dismissBlockDialog();
            }

            @Override
            public void onFailed(String result) {
                Toast.makeText(context, context.getString(R.string.Failed), Toast.LENGTH_SHORT).show();
                aty.dismissBlockDialog();
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
            String result = String.format("%.2f", zhanghuyue);
            tv_master_balance.setText(result);
        } catch (Exception e) {

        }
    }
}
