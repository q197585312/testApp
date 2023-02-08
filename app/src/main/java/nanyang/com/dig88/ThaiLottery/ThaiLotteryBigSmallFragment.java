package nanyang.com.dig88.ThaiLottery;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import butterknife.BindView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLotteryBigSmallFragment extends ThaiLotteryBaseFragment implements View.OnClickListener {
    @BindView(R.id.thai_lottery_bigsmall_discount)
    TextView tv_bigsmall_discount;
    @BindView(R.id.thai_lottery_bigsmall_odds)
    TextView tv_bigsmall_odds;
    @BindView(R.id.thai_lottery_big_radiobtn)
    RadioButton radiobtn_big;
    @BindView(R.id.thai_lottery_small_radiobtn)
    RadioButton radiobtn_small;
    @BindView(R.id.thai_lottery_bigsmall_betmoney)
    EditText edt_bigsmall_betmoney;
    @BindView(R.id.thai_lottery_bigsmall_reallymoney)
    TextView tv_bigsmall_reallymoney;
    @BindView(R.id.thai_lottery_bet_ll)
    LinearLayout ll_bet;
    @BindView(R.id.lottery_center_progress_ll)
    LinearLayout lottery_center_progress_ll;
    @BindView(R.id.thai_lottery_big)
    TextView tv_big;
    @BindView(R.id.thai_lottery_small)
    TextView tv_small;
    private String discountBigSmall;
    private String betMoney;
    private String betReallyMoney;
    private String minBigSmall;
    private String maxBigSmall;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        tv_big.setText(getString(R.string.big) + "(50 - 99)");
        tv_small.setText(getString(R.string.small) + "( 00 - 49 )");
        radiobtn_big.setOnClickListener(this);
        radiobtn_small.setOnClickListener(this);
        edt_bigsmall_betmoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                betMoney = initBetMoney(s.toString());
                betReallyMoney = changeBetMoney(countMoney(betMoney, discountBigSmall));
                tv_bigsmall_reallymoney.setText(betReallyMoney);
                setBetMoney();
            }
        });
    }

    @Override
    public void clearBetMoney() {
        edt_bigsmall_betmoney.setText("");
        tv_bigsmall_reallymoney.setText("");
        setBetMoney();
        betMoney = "";
        betReallyMoney = "";
        clearTotalMoney();
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_thai_lottery_bigsmall;
    }

    @Override
    public void updataGameState() {
        clearTotalMoney();
        thaiLottertGameList = getAct().getThaiLotteryDataList();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (thaiLottertGameList.size() != 0) {
                    lottery_center_progress_ll.setVisibility(View.GONE);
                    ll_bet.setVisibility(View.VISIBLE);
                }
            }
        });
        initUi();
    }

    private void initUi() {
        for (int i = 0; i < thaiLottertGameList.size(); i++) {
            DigGameOddsBean oddsBean = thaiLottertGameList.get(i);
            String discount = oddsBean.getDiscount();
            String odds = oddsBean.getFactor();
            switch (oddsBean.getType3()) {
                case "80":
                    discountBigSmall = discount;
                    minBigSmall = oddsBean.getMin_bet();
                    maxBigSmall = oddsBean.getMax_bet();
                    tv_bigsmall_discount.setText(getString(R.string.promotions) + " " + discount + "%");
                    tv_bigsmall_odds.setText(getString(R.string.zodiapeilv) + " " + odds);
                    break;
            }
        }
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGameBigSmallUrl;
    }

    @Override
    public void setProgressVisibility() {

    }

    public void setBetMoney() {
        getAct().total_amount_tv.setText(getString(R.string.zonge) + betMoney);
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + betReallyMoney);
    }

    @Override
    public String getBetUrlParmas() {
        if (!isCanBet()) {
            return getString(R.string.not_enough_balance_please_deposit_first);
        }
        StringBuffer betBuffer = new StringBuffer();
        String betType = null;
        if (!radiobtn_small.isChecked() && !radiobtn_big.isChecked()) {
            return getString(R.string.choice_big_small);
        }
        if (TextUtils.isEmpty(betMoney)) {
            return getString(R.string.shurujine);
        }
        String betMoneyLimit = isRightBetMoney(betMoney, minBigSmall, maxBigSmall);
        if (!betMoneyLimit.equals(RIGHT)) {
            return betMoneyLimit;
        }
        if (radiobtn_big.isChecked() && !radiobtn_small.isChecked()) {
            betType = "1";

        } else if (!radiobtn_big.isChecked() && radiobtn_small.isChecked()) {
            betType = "0";
        }
        betBuffer.append(betType + "#");
        betBuffer.append(betMoney);
        return betBuffer.toString();
    }

    @Override
    public String getBetIdProvider() {
        return thaiLottertGameList.get(0).getType2();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.thai_lottery_big_radiobtn:
                radiobtn_big.setChecked(true);
                radiobtn_small.setChecked(false);
                break;
            case R.id.thai_lottery_small_radiobtn:
                radiobtn_big.setChecked(false);
                radiobtn_small.setChecked(true);
                break;
        }
    }
}
