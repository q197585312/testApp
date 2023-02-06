package nanyang.com.dig88.Util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.RTGGameActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.RTGbalanceBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Entity.Xe88BalanceBean;
import nanyang.com.dig88.Home.GameWebActivity;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.quick.QuickRequestBean;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/4/23.
 */

public class PopXe88Transfer extends BasePopupWindow implements View.OnClickListener {
    String balanceUrl;
    String buyUrl;
    String takeOutUrl;
    VipInfoBean info;
    UserInfoBean u;
    BaseActivity aty;
    String buyMoney;
    String takeOutMoney;
    private TextView tv_master_balance, tv_poker_balance, tv_lottery_balance_name;
    private Button btn_start, btn_buy, btn_takeout;
    private EditText edt_buy, edt_takeout;
    private int typeBuy = 1;
    private int typeTakeOut = 2;
    private int typeGetBalance = 3;
    private int requestType;
    private Gson gson = new Gson();
    public PopXe88Transfer(Context context, View v, int width, int height) {
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
        btn_start = (Button) view.findViewById(R.id.btn_start);
        edt_buy = (EditText) view.findViewById(R.id.edt_buy);
        edt_takeout = (EditText) view.findViewById(R.id.edt_takeout);
        btn_buy = (Button) view.findViewById(R.id.btn_buy);
        btn_takeout = (Button) view.findViewById(R.id.btn_takeout);
        tv_lottery_balance_name = (TextView) view.findViewById(R.id.tv_lottery_balance_name);
        tv_lottery_balance_name.setText(context.getString(R.string.xe88_game_slots));
        btn_buy.setOnClickListener(this);
        btn_takeout.setOnClickListener(this);
        btn_start.setOnClickListener(this);
        aty = (BaseActivity) context;
        u = aty.getUserInfoBean();
        info = (VipInfoBean) AppTool.getObjectData(context, "vipInfo");
        updataAccunt(info);
        typeBuy = 1;
        typeTakeOut = 2;
        typeGetBalance = 3;
        requestType = typeGetBalance;
        balanceUrl = "http://xe88.k-api.com/balance.php";
        buyUrl = "http://xe88.k-api.com/transfer.php";
        takeOutUrl = "http://xe88.k-api.com/transfer.php";
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
                closePopupWindow();
                Intent intent = new Intent(context, GameWebActivity.class);
                String url = "http://xe88.k-api.com/login.php?" + "web_id=" + WebSiteUrl.WebId + "&language=" + Dig88Utils.getLanguage(context) +
                        "&username=" + info.getUsername() + "&token=" + aty.getUserInfoBean().getSession_id() + "&platform=3g";
                intent.putExtra("url", url);
                intent.putExtra("title", context.getString(R.string.xe88_game_slots));
                context.startActivity(intent);
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
            param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername()
                    + "&from=1&to=25&amount=" + buyMoney + "&language=" + Dig88Utils.getLanguage(context);
        } else if (requestType == typeTakeOut) {
            requestUrl = takeOutUrl;
            param = "?web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername()
                    + "&from=25&to=1&amount=" + takeOutMoney + "&language=" + Dig88Utils.getLanguage(context);
        }
        OkhttpUtils.getRequest(requestUrl + param, new OkhttpUtils.Result() {
            @Override
            public void onSuccess(String result) {
                if (requestType == typeBuy || requestType == typeTakeOut) {
                    edt_buy.setText("");
                    edt_takeout.setText("");
                    getVipInfo();
                    requestType = typeGetBalance;
                    request();
                } else {
                    if (TextUtils.isEmpty(result)) {
                        tv_poker_balance.setText("0.00");
                    } else {
                        Xe88BalanceBean xe88BalanceBean = gson.fromJson(result, Xe88BalanceBean.class);
                        tv_poker_balance.setText(xe88BalanceBean.getBalance() + "");
                    }
                }
                aty.dismissBlockDialog();
            }

            @Override
            public void onFailed(String result) {
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
                    updataAccunt(data);
                }
            }
        };
        registThread.startThread(null);
    }

    private void updataAccunt(VipInfoBean data) {
        if (this == null || data == null)
            return;
        try {
            double zhanghuyue = Double.valueOf(data.getBalance().toString());
            String result = StringUtils.floatDecimalFormat(zhanghuyue);
            tv_master_balance.setText(result);
            aty.showMoney(result);
        } catch (Exception e) {

        }
    }
}
