package nanyang.com.dig88.Util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.FishScoreBean;
import nanyang.com.dig88.Entity.FishUserBalanceBean;
import nanyang.com.dig88.Entity.FishUserBalanceTransferBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;
import xs.com.mylibrary.allinone.util.MD5;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/3/14.
 */

public class PopFishTransfer extends BasePopupWindow implements View.OnClickListener {
    BaseActivity activity;
    BlockDialog dialog;
    Context mContext;
    LoginInfoBean s;
    String userBalanceUrl = "http://app.info.dig88api.com/index.php?page=verifi_submitter";
    String fishBalanceUrl = "http://13.112.35.58:8081/GetAccountsGold.aspx?";
    String userBalanceTransfer = "http://app.info.dig88api.com/index.php?page=pangge_points_submitter";
    String fishBalanceTransfer = "http://13.112.35.58:8081/AccountsBuyIn.aspx?";
    EditText edt_deposit, edt_withdraw;
    TextView tvBalance, tvCurrency, tv_fish_balance, tv_fish_rate, tv_deposit, tv_withdraw_currency, tv_withdraw, tv_cancel, tv_start;
    String depositMoney;
    String withdrawMoney;
    private HttpClient httpClient;
    private Gson gson;
    private FishUserBalanceBean fishUserBalanceBean;
    private FishScoreBean fishScoreBean;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                dialog.dismiss();
                tv_withdraw_currency.setText("(" + activity.getCurrency() + ")");
                tvCurrency.setText("(" + activity.getCurrency() + ")");
                if (fishUserBalanceBean == null) {
                    return;
                }
                tvBalance.setText(String.format("%.2f", Double.parseDouble(fishUserBalanceBean.getData().getBalance())));
                setFishScore();
            } else if (msg.what == 2) {
                edt_deposit.setText("");
                refreshBalance();
            } else if (msg.what == 3) {
                edt_withdraw.setText("");
                refreshBalance();
            }
        }
    };

    public PopFishTransfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
        mContext = context;
        activity = (BaseActivity) mContext;
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_fish_transfer;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        gson = new Gson();
        httpClient = new HttpClient("");
        tvBalance = (TextView) view.findViewById(R.id.tv_balance);
        tvCurrency = (TextView) view.findViewById(R.id.tv_currency);
        tv_fish_balance = (TextView) view.findViewById(R.id.tv_fish_balance);
        tv_fish_rate = (TextView) view.findViewById(R.id.tv_fish_rate);
        edt_deposit = (EditText) view.findViewById(R.id.edt_deposit);
        tv_deposit = (TextView) view.findViewById(R.id.tv_deposit);
        tv_withdraw_currency = (TextView) view.findViewById(R.id.tv_withdraw_currency);
        edt_withdraw = (EditText) view.findViewById(R.id.edt_withdraw);
        tv_withdraw = (TextView) view.findViewById(R.id.tv_withdraw);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_start = (TextView) view.findViewById(R.id.tv_start);
        tv_start.setOnClickListener(this);
        tv_withdraw.setOnClickListener(this);
        tv_deposit.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
    }

    public void initData() {
        dialog = new BlockDialog(mContext, mContext.getString(R.string.loading));
        tv_withdraw_currency.setText("(" + activity.getCurrency() + ")");
        tvCurrency.setText("(" + activity.getCurrency() + ")");
        dialog.show();
        refreshBalance();
    }

    private void refreshBalance() {
        Thread getBalanceThread = new Thread(new BalanceRunnable());
        getBalanceThread.start();
    }

    private void setFishScore() {
        if (fishScoreBean == null) {
            return;
        }
        tv_fish_balance.setText(fishScoreBean.getDicData().get(0).getScore());
        double usdRate = Double.parseDouble(fishUserBalanceBean.getData().getRate());
        double cnyRate = Double.parseDouble(fishUserBalanceBean.getData().getCnyRate());
        double fishScore = Double.parseDouble(fishScoreBean.getDicData().get(0).getScore());
        double exchange = 1 / usdRate * cnyRate * 1000;
        int m = (int)(fishScore / exchange);
        tv_fish_rate.setText("(" + m + activity.getCurrency() + "/" + 1 + ":" +
                (int) (1 / usdRate * cnyRate * 1000) + ")");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_deposit:
                depositMoney = edt_deposit.getText().toString().trim();
                if (TextUtils.isEmpty(depositMoney) || Double.parseDouble(depositMoney) <= 0) {
                    Toast.makeText(mContext, mContext.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (Double.parseDouble(depositMoney) > Double.parseDouble(fishUserBalanceBean.getData().getBalance())) {
                        Toast.makeText(mContext, mContext.getString(R.string.not_enough_balance_please_deposit_first), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.show();
                Thread addFishScoreThread = new Thread(new addFishScoreRunnable());
                addFishScoreThread.start();
                break;
            case R.id.tv_withdraw:
                withdrawMoney = edt_withdraw.getText().toString().trim();
                if (TextUtils.isEmpty(withdrawMoney) || Double.parseDouble(withdrawMoney) <= 0) {
                    Toast.makeText(mContext, mContext.getString(R.string.shurujine), Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (fishScoreBean == null) {
                        Toast.makeText(mContext, "Servers error", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    double usdRate = Double.parseDouble(fishUserBalanceBean.getData().getRate());
                    double cnyRate = Double.parseDouble(fishUserBalanceBean.getData().getCnyRate());
                    double fishScore = Double.parseDouble(fishScoreBean.getDicData().get(0).getScore());
                    double exchange = 1 / usdRate * cnyRate * 1000;
                    int m = (int)(fishScore / exchange);
                    if (Double.parseDouble(withdrawMoney) > m) {
                        Toast.makeText(mContext, mContext.getString(R.string.not_enough_balance_please_deposit_first), Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                dialog.show();
                Thread cutDownFishScoreThread = new Thread(new cutDownFishScoreRunnable());
                cutDownFishScoreThread.start();
                break;
            case R.id.tv_cancel:
                closePopupWindow();
                break;
            case R.id.tv_start:
                if (ApkUtils.isAvilible(mContext, "com.nanyang.catchfish")) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    ComponentName componentName = new ComponentName("com.nanyang.catchfish", "com.nanyang.catchfish.UnityPlayerActivity");
                    intent.setComponent(componentName);
                    String key = s.getUsername() + "&" + WebSiteUrl.WebId + "&" + fishUserBalanceBean.getData().getRate() +
                            "&" + fishUserBalanceBean.getData().getCnyRate() + "&0" + "&" + activity.getUserInfoBean().getUser_id() +
                            "&" + MD5.md5(s.getUsername() + "_" + WebSiteUrl.WebId + WebSiteUrl.WebId);
                    intent.putExtra("LoginKey", key);
                    mContext.startActivity(intent);
                    closePopupWindow();
                } else {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse("https://down-hk01-cn2.k-api.com/happyfish.apk");
                    intent.setData(content_url);
                    mContext.startActivity(intent);
                }
                break;
        }
    }

    private String getCutDownMoney(String money) {
        double usdRate = Double.parseDouble(fishUserBalanceBean.getData().getRate());
        double cnyRate = Double.parseDouble(fishUserBalanceBean.getData().getCnyRate());
        double cutDownMoney = Double.parseDouble(money);
        return String.format("%.2f", cutDownMoney / usdRate * cnyRate);
    }

    private class cutDownFishScoreRunnable implements Runnable {

        @Override
        public void run() {
            try {
                String param = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
                        "&transferType=Withdraw" + "&amount=" + withdrawMoney;
                String userBalanceTransferResult = httpClient.sendPost(userBalanceTransfer, param);
                FishUserBalanceTransferBean fishUserBalanceTransferBean = gson.fromJson(userBalanceTransferResult, FishUserBalanceTransferBean.class);
                handler.sendEmptyMessage(3);
//                String cutDownScoreParam = "userName=" + s.getUsername() + "&currency=1" + "&webId=" + WebSiteUrl.WebId + "&balance=-" +
//                        getCutDownMoney(withdrawMoney) + "&password=" + MD5.md5(s.getUsername() + "_" + WebSiteUrl.WebId + WebSiteUrl.WebId) + "&machineId=ECB727AB-9285-5B08-A2C5-6B263E1D";
//                String fishBalanceTransferResult = httpClient.sendPost(fishBalanceTransfer + cutDownScoreParam, "");
//                FishScoreTransferBean fishScoreTransferBean = gson.fromJson(fishBalanceTransferResult, FishScoreTransferBean.class);
//                int messageId = fishScoreTransferBean.getDicData().get(0).getMessageId();
//                if (messageId != 0 && messageId != 7) {
//                    String param1 = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
//                            "&transferType=Deposit" + "&amount=" + withdrawMoney;
//                    String userBalanceTransferResult1 = httpClient.sendPost(userBalanceTransfer, param1);
//                    Log.d("httpClient1", "run: ");
//                }
//                if (messageId == 0 || messageId == 7) {
//                    handler.sendEmptyMessage(3);
//                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(3);
//                String param1 = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
//                        "&transferType=Deposit" + "&amount=" + withdrawMoney;
//                try {
//                    String userBalanceTransferResult1 = httpClient.sendPost(userBalanceTransfer, param1);
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                Log.d("httpClient1", "run: ");
//                handler.sendEmptyMessage(3);
            }
        }
    }

    private class addFishScoreRunnable implements Runnable {

        @Override
        public void run() {
            try {
                String param = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
                        "&transferType=Deposit" + "&amount=" + depositMoney;
                String userBalanceTransferResult = httpClient.sendPost(userBalanceTransfer, param);
                FishUserBalanceTransferBean fishUserBalanceTransferBean = gson.fromJson(userBalanceTransferResult, FishUserBalanceTransferBean.class);
                handler.sendEmptyMessage(2);
//                String addScoreParam = "userName=" + s.getUsername() + "&currency=1" + "&webId=" + WebSiteUrl.WebId + "&balance=" +
//                        fishUserBalanceTransferBean.getRealAmount() + "&password=" + MD5.md5(s.getUsername() + "_" + WebSiteUrl.WebId + WebSiteUrl.WebId) + "&machineId=ECB727AB-9285-5B08-A2C5-6B263E1D";
//                String fishBalanceTransferResult = httpClient.sendPost(fishBalanceTransfer + addScoreParam, "");
//                FishScoreTransferBean fishScoreTransferBean = gson.fromJson(fishBalanceTransferResult, FishScoreTransferBean.class);
//                int messageId = fishScoreTransferBean.getDicData().get(0).getMessageId();
//                if (messageId != 7 && messageId != 0) {
//                    String paramD = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
//                            "&transferType=Withdraw" + "&amount=" + getCutDownMoney(depositMoney);
//                    String result = httpClient.sendPost(userBalanceTransfer, paramD);
//                    Log.d("result", "run: ");
//                }
//                if (messageId == 7 || messageId == 0) {
//                    handler.sendEmptyMessage(2);
//                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(2);
//                String paramD = "web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id() +
//                        "&transferType=Withdraw" + "&amount=" + getCutDownMoney(depositMoney);
//                try {
//                    String result = httpClient.sendPost(userBalanceTransfer, paramD);
//                    Log.d("result", "run: ");
//                } catch (IOException e1) {
//                    e1.printStackTrace();
//                }
//                handler.sendEmptyMessage(2);
            }
        }
    }

    private class BalanceRunnable implements Runnable {

        @Override
        public void run() {
            try {
                String userBalanceParam = "web_id=" + WebSiteUrl.WebId + "&username=" + s.getUsername() + "&password=" + MD5.md5(s.getPassword());
                String userBalanceResult = httpClient.sendPost(userBalanceUrl, userBalanceParam);
                fishUserBalanceBean = gson.fromJson(userBalanceResult, FishUserBalanceBean.class);
                String fishBalanceParam = "userName=" + s.getUsername() + "_" + WebSiteUrl.WebId;
                String fishBalanceResult = httpClient.sendPost(fishBalanceUrl + fishBalanceParam, "");
                fishScoreBean = gson.fromJson(fishBalanceResult, FishScoreBean.class);
                handler.sendEmptyMessage(1);
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(1);
            }
        }
    }
}
