package nanyang.com.dig88.ThaiLottery;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.Lottery.LotteryBetResultBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;
import xs.com.mylibrary.base.quick.QuickRequestBean;

/**
 * Created by Administrator on 2017/7/20.
 */

public abstract class ThaiLotteryBaseFragment extends BaseFragment {
    public final String TOP = "TOP";
    public final String INFRONT = "INFRONT";
    public final String BOTTOM = "BOTTOM";
    public final String TOPROLL = "TOPROLL";
    public final String RIGHT = "RIGHT";
    public List<DigGameOddsBean> thaiLottertGameList;
    private boolean isCanBet = true;
    private BlockDialog dialog;

    public abstract void updataGameState();

    public ThaiLotteryActivity getAct() {
        return (ThaiLotteryActivity) getActivity();
    }

    public abstract String getBetUrl();

    public abstract void setProgressVisibility();

    public abstract String getBetUrlParmas();

    public abstract String getBetIdProvider();

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        dialog = new BlockDialog(mContext, getString(R.string.zhengjiazai));
    }

    public Double countMoney(String money, String discount) {
        if (!TextUtils.isEmpty(money) && !TextUtils.isEmpty(discount)) {
            double d = Double.parseDouble(money) * (1 - (Double.parseDouble(discount) * 0.01));
            return d;
        } else {
            return 0.000;
        }
    }

    public String changeBetMoney(double d) {
        return DecimalUtils.decimalFormat(d, "0.000");
    }

    public String initBetMoney(String money) {
        if (TextUtils.isEmpty(money)) {
            return "0.000";
        } else {
            return money;
        }
    }

    public String initBetParmas(String money) {
        if (money.equals("0.000")) {
            return "0";
        } else {
            return money;
        }
    }

    public abstract void clearBetMoney();

    public void clearTotalMoney() {
        getAct().total_amount_tv.setText(getString(R.string.zonge) + "0.000");
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + "0.000");
    }

    public boolean isCanBet() {
        String balance = getUserInfo().getMoneyBalance().getBalance();
        if (!TextUtils.isEmpty(balance)) {
            if (Double.parseDouble(balance) <= 0) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public String isRightBetMoney(String money, String minMoney, String maxMoney) {
        String initMoney = initBetMoney(money);
        double betMoney = Double.parseDouble(initBetMoney(money));
        double betMinMoney = Double.parseDouble(initBetMoney(minMoney));
        double betMaxMoney = Double.parseDouble(initBetMoney(maxMoney));
        if (initMoney.equals("0.000")) {
            return RIGHT;
        }
        if (betMoney < betMinMoney) {
            return getString(R.string.betting_limit_less_than_nominal) + "[" + minMoney + "-" + maxMoney + "]";
        } else if (betMoney > betMaxMoney) {
            return getString(R.string.betting_limit_more_than_nominal) + "[" + minMoney + "-" + maxMoney + "]";
        } else {
            return RIGHT;
        }
    }

    public void betSubmit() {
        String parmas = getBetUrlParmas();
        if (parmas.equals(getString(R.string.not_enough_balance_please_deposit_first))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        } else if (parmas.equals(getString(R.string.qingchashumu))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        } else if (parmas.startsWith(getString(R.string.betting_limit_less_than_nominal)) || parmas.startsWith(getString(R.string.betting_limit_more_than_nominal))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        } else if (parmas.equals(getString(R.string.enter_correct_number))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        } else if (parmas.equals(getString(R.string.choice_big_small))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        } else if (parmas.equals(getString(R.string.shurujine))) {
            Toast.makeText(mContext, parmas, Toast.LENGTH_SHORT).show();
            return;
        }
        NyVolleyJsonThreadHandler<LotteryBetResultBean> betThead = new NyVolleyJsonThreadHandler<LotteryBetResultBean>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfo().getUser_id());
                params.put("session_id", getUserInfo().getSession_id());
                params.put("from", "Android--" + AppTool.getApkInfo(mContext).versionName);
                params.put("id_provider", getBetIdProvider());
                params.put("get_bet", getBetUrlParmas());
                params.put("IP", SharePreferenceUtil.getString(mContext, "IP"));
                return new QuickRequestBean(getBetUrl(), params
                        , new TypeToken<NyBaseResponse<LotteryBetResultBean>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, LotteryBetResultBean data) {
                Toast.makeText(getActivity(), R.string.xiazhusuccess, Toast.LENGTH_SHORT).show();
                clearBetMoney();
                isCanBet = true;
                dialog.dismiss();
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                Toast.makeText(getActivity(), R.string.xiazhucuowu, Toast.LENGTH_SHORT).show();
                isCanBet = true;
                dialog.dismiss();
            }
        };
        if (isCanBet) {
            dialog.show();
            betThead.startThread(null);
            isCanBet = false;
        }
    }
}
