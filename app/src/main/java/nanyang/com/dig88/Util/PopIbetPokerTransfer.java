package nanyang.com.dig88.Util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.IbetPokerBalanceBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/8/30.
 */

public abstract class PopIbetPokerTransfer extends BasePopupWindow {
    @BindView(R.id.tv_master_balance)
    TextView tvMasterBalance;
    @BindView(R.id.tv_poker_name)
    TextView tvPokerName;
    @BindView(R.id.tv_poker_balance)
    TextView tvPokerBalance;
    @BindView(R.id.tv_poker_title)
    TextView tvPokerTitle;
    @BindView(R.id.edt_buy)
    EditText edtBuy;
    @BindView(R.id.edt_takeout)
    EditText edtTakeout;
    @BindView(R.id.tv_hint)
    TextView tvHint;
    String amount;
    UserInfoBean u;
    BaseActivity aty;
    Gson gson = new Gson();
    boolean isGetMainBalance;
    boolean isGetPokerBalance;
    private LoginInfoBean s;
    private int fromId, toId;

    public PopIbetPokerTransfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_ibet_poker_transfer;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        aty = (BaseActivity) context;
        u = aty.getUserInfoBean();
        s = (LoginInfoBean) AppTool.getObjectData(context, "loginInfo");
        aty.showBlockDialog();
        updateMainBalance();
        getPokerBalance();
    }

    public void setPokerName(String name, String lessBalance) {
        tvPokerTitle.setText(name);
        tvPokerName.setText(name + " " + context.getString(R.string.Balance));
        tvHint.setText("(" + name + " " + context.getString(R.string.balance_must_more_than) + " " + lessBalance + "$)");
    }

    private void transfer() {
        if (TextUtils.isEmpty(amount)) {
            com.unkonw.testapp.libs.utils.ToastUtils.showShort(context.getString(R.string.shurujine));
            return;
        }
        aty.showBlockDialog();
        String url = WebSiteUrl.Ibet567PokerTransferUrl;
        url += "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&from=" + fromId + "&to=" + toId + "&amount=" + amount;
        HttpUtils.httpGet(url, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                //{"code":1,"errMsg":"No Error"}
                if (s.contains("No Error")) {
                    getPokerBalance();
                    updateMainBalance();
                    com.unkonw.testapp.libs.utils.ToastUtils.showShort(context.getString(R.string.Success));
                } else {
                    com.unkonw.testapp.libs.utils.ToastUtils.showShort(context.getString(R.string.Failed));
                }
                aty.dismissBlockDialog();
            }

            @Override
            public void onRequestFailed(String s) {
                aty.dismissBlockDialog();
            }
        });
    }

    private void getPokerBalance() {
        String url = WebSiteUrl.Ibet567PokerBalanceUrl + "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername();
        HttpUtils.httpGet(url, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                String balanceStr;
                if (!TextUtils.isEmpty(s)) {
                    IbetPokerBalanceBean ibetPokerBalanceBean = gson.fromJson(s, IbetPokerBalanceBean.class);
                    balanceStr = String.format("%.2f", Double.valueOf(ibetPokerBalanceBean.getBalance()));
                } else {
                    balanceStr = "0.00";
                }
                tvPokerBalance.setText(balanceStr);
                isGetPokerBalance = true;
                if (isGetMainBalance && isGetPokerBalance) {
                    aty.dismissBlockDialog();
                }
            }

            @Override
            public void onRequestFailed(String s) {
                tvPokerBalance.setText("0.00");
            }
        });
    }

    public void updateMainBalance() {
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
                    String balance = data.getBalance();
                    if (TextUtils.isEmpty(balance)) {
                        tvMasterBalance.setText("0.00");
                    } else {
                        double doubleBalance = Double.valueOf(balance);
                        String balanceStr = String.format("%.2f", doubleBalance);
                        tvMasterBalance.setText(balanceStr);
                    }
                    isGetMainBalance = true;
                    if (isGetMainBalance && isGetPokerBalance) {
                        aty.dismissBlockDialog();
                    }
                }
            }
        };
        registThread.startThread(null);
    }

    @OnClick({R.id.btn_buy, R.id.btn_takeout, R.id.btn_start, R.id.tv_exit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_buy:
                fromId = 1;
                toId = 24;
                amount = edtBuy.getText().toString();
                transfer();
                break;
            case R.id.btn_takeout:
                fromId = 24;
                toId = 1;
                amount = edtTakeout.getText().toString();
                transfer();
                break;
            case R.id.btn_start:
                enterGame();
                break;
            case R.id.tv_exit:
                closePopupWindow();
                break;
        }
    }

    public abstract void enterGame();
}
