package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.Lottery234BetBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;

/**
 * 2 3 4D
 *
 * @author Administrator
 */
public class Lottery234DFragment extends LotteryBaseFragment<Lottery234BetBean> {


    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;

    TextView lotteryDiscount2dTv;
    TextView lotteryDiscount3dTv;
    TextView lotteryDiscount4dTv;
    TextView lotteryOdds2dTv;
    TextView lotteryOdds3dTv;
    TextView lotteryOdds4dTv;
    @BindView(R.id.lottery_center_progress_ll)
    View progressView;
    private BasePopupWindow edtPop;
    private String discount2D;
    private String discount3D;
    private String discount4D;
    private String kei2D;
    private String kei3D;
    private String kei4D;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_list_content;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initListContent();
    }

    private void initListContent() {
        listContentPtrlv.setAdapter(adapter);
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        addHeader();
    }

    private void addHeader() {
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_2d3d4d_lottery, null);
        lotteryDiscount2dTv= (TextView) header.findViewById(R.id.lottery_discount_2d_tv);
        lotteryDiscount3dTv= (TextView) header.findViewById(R.id.lottery_discount_3d_tv);
        lotteryDiscount4dTv= (TextView) header.findViewById(R.id.lottery_discount_4d_tv);
        lotteryOdds2dTv= (TextView) header.findViewById(R.id.lottery_odds_2d_tv);
        lotteryOdds3dTv= (TextView) header.findViewById(R.id.lottery_odds_3d_tv);
        lotteryOdds4dTv= (TextView) header.findViewById(R.id.lottery_odds_4d_tv);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList,LotteryStateGameBean selectedbean) {
        if(isAdded()) {
            String gameName = selectedbean.getGame_name();
            String period = selectedbean.getPeriod();
            List<LotteryLimitBean> limitBeanList = new ArrayList<>(Arrays.asList(new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean()));
            for (DigGameOddsBean oddsBean : oddsList) {
                if (oddsBean.getType3().equals("1")) {
                    lotteryDiscount2dTv.setText(getString(R.string.discount2d) + ":" + oddsBean.getDiscount() + "%");
                    lotteryOdds2dTv.setText(getString(R.string.odds2d) + ":" + oddsBean.getFactor());
                    discount2D = oddsBean.getDiscount();
                    kei2D = oddsBean.getKei();
                    Log.d("shang", "updateGameState:2d "+kei2D);
                    limitBeanList.set(0, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "2D"));
                } else if (oddsBean.getType3().equals("2")) {
                    lotteryDiscount3dTv.setText("3D:" + oddsBean.getDiscount() + "%");
                    lotteryOdds3dTv.setText( "3D:" + oddsBean.getFactor());
                    discount3D = oddsBean.getDiscount();
                    kei3D = oddsBean.getKei();
                    Log.d("shang", "updateGameState:3d "+kei3D);
                    limitBeanList.set(1, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "3D"));
                } else if (oddsBean.getType3().equals("3")) {
                    lotteryDiscount4dTv.setText( "4D:" + oddsBean.getDiscount() + "%");
                    lotteryOdds4dTv.setText("4D:" + oddsBean.getFactor());
                    discount4D = oddsBean.getDiscount();
                    kei4D = oddsBean.getKei();
                    Log.d("shang", "updateGameState:4d "+kei4D);
                    limitBeanList.set(2, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "4D"));
                }
            }

            adapter.setLimitList(limitBeanList);
        }
    }

    @Override
    protected String getSubmitPageUrl() {
        return "4d_submitter";
    }

    @Override
    protected synchronized String constructorGetBetStr() {
        StringBuilder builder = new StringBuilder();
        List<String> submitSet = new ArrayList<>();
        for (Lottery234BetBean lottery234BetBean : adapter.getList()) {
            if (!lottery234BetBean.getCount0().trim().equals("")) {
                if (!submitSet.contains(lottery234BetBean.getNumber0().trim() + "#" + lottery234BetBean.getMoney0().trim())) {
                    builder.append("^");
                    builder.append(lottery234BetBean.getNumber0());
                    builder.append("#");
                    builder.append(lottery234BetBean.getMoney0());
                    submitSet.add(lottery234BetBean.getNumber0().trim()  + "#" + lottery234BetBean.getMoney0().trim() );
                }
            }
            if (!lottery234BetBean.getCount1().trim().equals("")) {
                if (!submitSet.contains(lottery234BetBean.getNumber1().trim()  + "#" + lottery234BetBean.getMoney1().trim() )) {
                    builder.append("^");
                    builder.append(lottery234BetBean.getNumber1());
                    builder.append("#");
                    builder.append(lottery234BetBean.getMoney1());
                    submitSet.add(lottery234BetBean.getNumber1().trim()  + "#" + lottery234BetBean.getMoney1().trim() );
                }
            }
        }
        if (builder.length() < 1) {
            return "";
        }
        builder.deleteCharAt(0);
        return builder.toString();
    }

    @Override
    public LotteryBaseAdapter<Lottery234BetBean> getAdapter() {
        return new LotteryBaseAdapter<Lottery234BetBean>(mContext, R.layout.item_lottery_grid_content) {
            @Override
            protected List<Lottery234BetBean> initList() {
                return new ArrayList<>(Arrays.asList(new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean()));
            }

            @Override
            protected void clearListMoney() {
                   setList(initList());
            }

            @Override
            protected void convert(final ViewHolder holder, Lottery234BetBean item, final int position) {
                EditText number0Edt = holder.retrieveView(R.id.lottery_number0_tv);
                EditText money0Edt = holder.retrieveView(R.id.lottery_money0_tv);
                TextView count0Tv = holder.retrieveView(R.id.lottery_count0_tv);
                EditText number1Edt = holder.retrieveView(R.id.lottery_number1_tv);
                EditText money1Edt = holder.retrieveView(R.id.lottery_money1_tv);
                TextView count1Tv = holder.retrieveView(R.id.lottery_count1_tv);
                setTextWatcher(number0Edt, "number", position, count0Tv, 0);
                setTextWatcher(number1Edt, "number", position, count1Tv, 1);
                setTextWatcher(money0Edt, "money", position, count0Tv, 0);
                setTextWatcher(money1Edt, "money", position, count1Tv, 1);
                money0Edt.setText(item.getMoney0());
                number0Edt.setText(item.getNumber0());
                money1Edt.setText(item.getMoney1());
                number1Edt.setText(item.getNumber1());
                count0Tv.setText(item.getCount0());
                count1Tv.setText(item.getCount1());


            }

            private void setTextWatcher(EditText edt, final String type, final int position, final TextView countTv, final int positionId) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        changeEdt(s, position, countTv, positionId, type);
                    }
                });
            }

            protected void changeEdt(Editable s, int position, TextView countTv, int positionId, String type) {
                LotteryLimitBean limit;
                String count = "";
                if (positionId == 0) {
                    if (type.equals("number")) {
                        getList().get(position).setNumber0(s.toString());
                    } else {
                        getList().get(position).setMoney0(s.toString());
                    }
                    limit = getBetLimit(getList().get(position).getNumber0());
                    if (limit != null && getList().get(position).getMoney0().length() > 0) {
                        count = countMoney(position, getList().get(position).getMoney0(), limit, positionId, getDiscount(getList().get(position).getNumber0()),getKei(getList().get(position).getNumber0()));
                    }
                    getList().get(position).setCount0(count);
                } else {
                    if (type.equals("number")) {
                        getList().get(position).setNumber1(s.toString());
                    } else {
                        getList().get(position).setMoney1(s.toString());
                    }
                    limit = getBetLimit(getList().get(position).getNumber1());
                    if (limit != null && getList().get(position).getMoney1().length() > 0)
                        count = countMoney(position, getList().get(position).getMoney1(), limit, positionId, getDiscount(getList().get(position).getNumber1()),getKei(getList().get(position).getNumber0()));
                    getList().get(position).setCount1(count);
                }
                countTv.setText(count);
                countTotal();

            }

            private String getDiscount(String money) {
                if (money.length() == 2)
                    return discount2D;
                else if (money.length() == 3) {
                    return discount3D;
                } else if (money.length() == 4) {
                    return discount4D;
                }
                return "0";
            }
            private String getKei(String money){
                if (money.length() == 2)
                    return kei2D;
                else if (money.length() == 3) {
                    return kei3D;
                } else if (money.length() == 4) {
                    return kei4D;
                }
                return "0";
            }

            @Override
            protected void countTotal() {
                float totalMoney = 0f;
                float totalBet = 0f;
                for (Lottery234BetBean lottery234BetBean : getList()) {
                    String count0 = lottery234BetBean.getCount0();
                    String money0 = lottery234BetBean.getMoney0();
                    String count1 = lottery234BetBean.getCount1();
                    String money1 = lottery234BetBean.getMoney1();
                    if (!count0.equals("")) {
                        totalBet = totalBet + Float.valueOf(count0);
                        totalMoney = totalMoney + Float.valueOf(money0);
                    }
                    if (!count1.equals("")) {
                        totalBet = totalBet + Float.valueOf(count1);
                        totalMoney = totalMoney + Float.valueOf(money1);
                    }
                }
                LotteryCountBean countBean = new LotteryCountBean(DecimalUtils.decimalFormat(totalBet, "0.00"),  DecimalUtils.decimalFormat(totalMoney, "0.00"));
                iCountLotteryBet.updateTotal(countBean);
            }


            private LotteryLimitBean getBetLimit(String s) {
                LotteryLimitBean bean = null;
                if (s.toString().length() == 2)
                    bean = getLimitList().get(0);
                else if (s.toString().length() == 3)
                    bean = getLimitList().get(1);
                else if (s.toString().length() == 4)
                    bean = getLimitList().get(2);
                return bean;
            }
        };
    }

    @Override
    public void setProgressVisibility(boolean isVisibility) {
        super.setProgressVisibility(isVisibility);
        if(isVisibility){
            progressView.setVisibility(View.VISIBLE);
        }else{
            progressView.setVisibility(View.GONE);
        }
    }
}
