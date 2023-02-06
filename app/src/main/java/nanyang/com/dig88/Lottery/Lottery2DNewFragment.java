package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2018/8/31.
 */

public class Lottery2DNewFragment extends LotteryBaseFragment<Object> {
    @Bind(R.id.lottery_discount_2d_tv)
    TextView lotteryDiscount2dTv;
    @Bind(R.id.lottery_odds_2d_tv)
    TextView lotteryOdds2dTv;
    @Bind(R.id.edt_content)
    EditText edt_content;
    @Bind(R.id.tv_type)
    TextView tv_type;
    DigGameOddsBean digGameOddsBean;
    String content;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        if (betType().equals("2DFront")) {
            tv_type.setText("2D FRONT");
        } else {
            tv_type.setText("2D MID");
        }
    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean selectedbean) {
        for (int i = 0; i < oddsList.size(); i++) {
            DigGameOddsBean oddsBean = oddsList.get(i);
            String type;
            if (betType().equals("2DFront")) {
                type = "106";
            } else {
                type = "107";
            }
            if (oddsBean.getType3().equals(type)) {
                digGameOddsBean = oddsBean;
                lotteryDiscount2dTv.setText(getString(R.string.discount) + ":" + oddsBean.getDiscount() + "%");
                lotteryOdds2dTv.setText(getString(R.string.zodiapeilv) + ":" + oddsBean.getFactor());
            }
        }
    }

    public String betStr() {
        content = edt_content.getText().toString().trim();
        StringBuffer sb = new StringBuffer();
        if (!TextUtils.isEmpty(content)) {
            if (content.contains("*") && content.contains("#") && !content.contains("x") && !content.contains(" ")) {
                String[] bet = content.split("\\+");
                for (int i = 0; i < bet.length; i++) {
                    String[] betContent = bet[i].split("#");
                    if (betContent.length != 2) {
                        return getString(R.string.qingchashumu);
                    }
                    String betAmount = betContent[1];
                    if (Double.parseDouble(betAmount) > Double.parseDouble(digGameOddsBean.getMax_bet())) {
                        return getString(R.string.betting_limit_more_than_nominal) + digGameOddsBean.getMax_bet();
                    } else if (Double.parseDouble(betAmount) < Double.parseDouble(digGameOddsBean.getMin_bet())) {
                        return getString(R.string.betting_limit_less_than_nominal) + digGameOddsBean.getMin_bet();
                    }
                    String[] betNum = betContent[0].split("\\*");
                    for (int j = 0; j < betNum.length; j++) {
                        String betNum1 = betNum[j];
                        if (betNum1.length() != 2) {
                            return getString(R.string.qingchashumu);
                        }
                        if (!TextUtils.isEmpty(sb.toString())) {
                            sb.append("^");
                        }
                        sb.append(betNum1 + "#" + betAmount);
                    }
                }
                return sb.toString();
            } else if (content.contains("x") && !content.contains("*") && !content.contains("#") && !content.contains("+")) {
                String[] bet = null;
                if (!content.contains(" ")) {
                    bet = new String[]{content};
                } else {
                    bet = content.split(" ");
                }
                List<String> betList = new ArrayList<>();
                for (int i = 0; i < bet.length; i++) {
                    String betStr = bet[i];
                    if (!TextUtils.isEmpty(betStr)) {
                        betList.add(betStr);
                    }
                }
                for (int i = 0; i < betList.size(); i++) {
                    String betStr = betList.get(i);
                    String[] betArr = betStr.split("x");
                    if (betArr.length != 2) {
                        return getString(R.string.qingchashumu);
                    }
                    String betNum = betArr[0];
                    if (betNum.length() != 2) {
                        return getString(R.string.qingchashumu);
                    }
                    String betAmount = betArr[1];
                    if (Double.parseDouble(betAmount) > Double.parseDouble(digGameOddsBean.getMax_bet())) {
                        return getString(R.string.betting_limit_more_than_nominal) + digGameOddsBean.getMax_bet();
                    } else if (Double.parseDouble(betAmount) < Double.parseDouble(digGameOddsBean.getMin_bet())) {
                        return getString(R.string.betting_limit_less_than_nominal) + digGameOddsBean.getMin_bet();
                    }
                    if (!TextUtils.isEmpty(sb.toString())) {
                        sb.append("^");
                    }
                    sb.append(betNum + "#" + betAmount);
                }
                return sb.toString();
            } else {
                return getString(R.string.qingchashumu);
            }
        } else {
            return getString(R.string.qingchashumu);
        }
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_2d;
    }


    @Override
    public LotteryBaseAdapter getAdapter() {
        return null;
    }

    @Override
    protected String getSubmitPageUrl() {
        if (betType().equals("2DFront")) {
            return "2d_front_submitter";
        } else {
            return "2d_mid_submitter";
        }
    }

    @Override
    protected String constructorGetBetStr() {
        return betStr();
    }

    @Override
    public DigGameOddsBean getDigGameOddsBean() {
        return digGameOddsBean;
    }

    @Override
    public void clear() {
        getAct().setLotteryCount("0.00", "0.00");
        edt_content.setText("");
    }
}
