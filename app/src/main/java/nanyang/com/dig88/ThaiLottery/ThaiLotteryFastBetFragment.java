package nanyang.com.dig88.ThaiLottery;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.ThaiLottery.bean.ThaiLotteryFastbetBean;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLotteryFastBetFragment extends ThaiLotteryBaseFragment {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.lottery_center_progress_ll)
    LinearLayout lottery_center_progress_ll;
    QuickBaseAdapter<ThaiLotteryFastbetBean> adapter;
    private List<ThaiLotteryFastbetBean> betBeanList;
    private String discount1DTop;
    private String discount1DBottom;
    private String discount2DTop;
    private String discount2DBottom;
    private String discount2DTopRoll;
    private String discount3DTop;
    private String discount3DBottom;
    private String discount3DInfront;
    private String discount3DTopRoll;
    private String minBet1DTop;
    private String minBet1DBottom;
    private String minBet2DTop;
    private String minBet2DBottom;
    private String minBet2DTopRoll;
    private String minBet3DTop;
    private String minBet3DBottom;
    private String minBet3DInfront;
    private String minBet3DTopRoll;
    private String maxBet1DTop;
    private String maxBet1DBottom;
    private String maxBet2DTop;
    private String maxBet2DBottom;
    private String maxBet2DTopRoll;
    private String maxBet3DTop;
    private String maxBet3DBottom;
    private String maxBet3DInfront;
    private String maxBet3DTopRoll;
    private View header;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_list_content;
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        betBeanList = new ArrayList<>();
        addListViewHeader();
    }

    private void initHeaderView(View header) {
        TextView tv_1d_topdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_1d_topdiscount);
        TextView tv_1d_bottomdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_1d_bottomdiscount);
        TextView tv_2d_topdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_2d_topdiscount);
        TextView tv_2d_bottomdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_2d_bottomdiscount);
        TextView tv_2d_toprolldiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_2d_toprolldiscount);
        TextView tv_3d_topdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_3d_topdiscount);
        TextView tv_3d_bottomdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_3d_bottomdiscount);
        TextView tv_3d_infrontdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_3d_infrontdiscount);
        TextView tv_3d_toprolldiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_fastbet_3d_toprolldiscount);
        for (int i = 0; i < thaiLottertGameList.size(); i++) {
            DigGameOddsBean oddsBean = thaiLottertGameList.get(i);
            String discount = oddsBean.getDiscount();
            switch (oddsBean.getType3()) {
                case "71":
                    discount1DTop = discount;
                    minBet1DTop = oddsBean.getMin_bet();
                    maxBet1DTop = oddsBean.getMax_bet();
                    tv_1d_topdiscount.setText(discount + "%");
                    break;
                case "72":
                    discount1DBottom = discount;
                    minBet1DBottom = oddsBean.getMin_bet();
                    maxBet1DBottom = oddsBean.getMax_bet();
                    tv_1d_bottomdiscount.setText(discount + "%");
                    break;
                case "73":
                    discount2DTop = discount;
                    minBet2DTop = oddsBean.getMin_bet();
                    maxBet2DTop = oddsBean.getMax_bet();
                    tv_2d_topdiscount.setText(discount + "%");
                    break;
                case "74":
                    discount2DBottom = discount;
                    minBet2DBottom = oddsBean.getMin_bet();
                    maxBet2DBottom = oddsBean.getMax_bet();
                    tv_2d_bottomdiscount.setText(discount + "%");
                    break;
                case "75":
                    discount2DTopRoll = discount;
                    minBet2DTopRoll = oddsBean.getMin_bet();
                    maxBet2DTopRoll = oddsBean.getMax_bet();
                    tv_2d_toprolldiscount.setText(discount + "%");
                    break;
                case "76":
                    minBet3DTop = oddsBean.getMin_bet();
                    maxBet3DTop = oddsBean.getMax_bet();
                    discount3DTop = discount;
                    tv_3d_topdiscount.setText(discount + "%");
                    break;
                case "77":
                    minBet3DBottom = oddsBean.getMin_bet();
                    maxBet3DBottom = oddsBean.getMax_bet();
                    discount3DBottom = discount;
                    tv_3d_bottomdiscount.setText(discount + "%");
                    break;
                case "78":
                    discount3DTopRoll = discount;
                    minBet3DTopRoll = oddsBean.getMin_bet();
                    maxBet3DTopRoll = oddsBean.getMax_bet();
                    tv_3d_toprolldiscount.setText(discount + "%");
                    break;
                case "79":
                    minBet3DInfront = oddsBean.getMin_bet();
                    maxBet3DInfront = oddsBean.getMax_bet();
                    discount3DInfront = discount;
                    tv_3d_infrontdiscount.setText(discount + "%");
                    break;
            }
        }
        initListData();
    }

    private void initListData() {
        betBeanList.clear();
        for (int i = 0; i < 10; i++) {
            betBeanList.add(new ThaiLotteryFastbetBean(discount1DTop, discount1DBottom, discount2DTop, discount2DBottom,
                    discount2DTopRoll, discount3DTop, discount3DBottom, discount3DInfront, discount3DTopRoll
                    , minBet1DTop, minBet1DBottom, minBet2DTop, minBet2DBottom, minBet2DTopRoll, minBet3DTop, minBet3DBottom, minBet3DInfront, minBet3DTopRoll,
                    maxBet1DTop, maxBet1DBottom, maxBet2DTop, maxBet2DBottom, maxBet2DTopRoll, maxBet3DTop, maxBet3DBottom, maxBet3DInfront, maxBet3DTopRoll));
        }
    }

    public void addListViewHeader() {
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        header = LayoutInflater.from(mContext).inflate(R.layout.header_thai_lottery_fastbet, null);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
        initAdapter();
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<ThaiLotteryFastbetBean>(mContext, R.layout.item_thai_lottery_fastbet, betBeanList) {
            @Override
            public View getView(int position, View convertView, ViewGroup paren) {
                ViewHolder holder = new ViewHolder(mContext, paren, R.layout.item_thai_lottery_fastbet);
                convert(holder, getItem(position), position);
                return holder.getView();
            }

            @Override
            protected void convert(ViewHolder helper, final ThaiLotteryFastbetBean item, final int position) {
                EditText edt__num = helper.retrieveView(R.id.edt_thai_lottery_fastbet_num);
                TextView tv_reality_betmoney = helper.retrieveView(R.id.tv_thai_lottery_fastbet_reality_betmoney);
                final EditText edt_top_betmoney = helper.retrieveView(R.id.edt_thai_lottery_fastbet_top_betmoney);
                setEditWatcher(edt_top_betmoney, TOP, position, item, tv_reality_betmoney);
                final EditText edt_infront_betmoney = helper.retrieveView(R.id.edt_thai_lottery_fastbet_infront_betmoney);
                setEditWatcher(edt_infront_betmoney, INFRONT, position, item, tv_reality_betmoney);
                final EditText edt_bottom_betmoney = helper.retrieveView(R.id.edt_thai_lottery_fastbet_bottom_betmoney);
                setEditWatcher(edt_bottom_betmoney, BOTTOM, position, item, tv_reality_betmoney);
                final EditText edt_toproll_betmoney = helper.retrieveView(R.id.edt_thai_lottery_fastbet_toproll_betmoney);
                setEditWatcher(edt_toproll_betmoney, TOPROLL, position, item, tv_reality_betmoney);
                setNumWatcher(edt__num, position, edt_top_betmoney, edt_bottom_betmoney, edt_toproll_betmoney, edt_infront_betmoney, tv_reality_betmoney);
                edt__num.setText(item.getNum());
                edt_top_betmoney.setText(item.getTopAmount());
                edt_infront_betmoney.setText(item.getInfrontAmount());
                edt_bottom_betmoney.setText(item.getBottomAmount());
                edt_toproll_betmoney.setText(item.getTopRollAmount());
                tv_reality_betmoney.setText(item.getReallyAmount());
                setMoneyChangeWatcher(tv_reality_betmoney);
            }

            private void setMoneyChangeWatcher(TextView tv) {
                tv.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {
                        setBetMoney();
                    }
                });
            }

            private void setNumWatcher(EditText num, final int position, final EditText edt_top_betmoney, final EditText edt_bottom_betmoney, final EditText edt_toproll_betmoney, final EditText edt_infront_betmoney, final TextView tv) {
                num.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLotteryFastbetBean item = getList().get(position);
                        String num = s.toString();
                        if (!TextUtils.isEmpty(num)) {
                            if (num.length() == 1) {
                                edt_top_betmoney.setEnabled(true);
                                edt_bottom_betmoney.setEnabled(true);
                                edt_infront_betmoney.setEnabled(false);
                                edt_toproll_betmoney.setEnabled(false);
                                edt_infront_betmoney.setText("");
                                edt_toproll_betmoney.setText("");
                                item.setInfrontAmount("");
                                item.setTopRollAmount("");
                                getList().get(position).setReallyAmount(countTotalMoney(item, 1));
                                tv.setText(countTotalMoney(item, 1));
                            } else if (num.length() == 2) {
                                edt_infront_betmoney.setEnabled(false);
                                edt_top_betmoney.setEnabled(true);
                                edt_bottom_betmoney.setEnabled(true);
                                edt_toproll_betmoney.setEnabled(true);
                                edt_infront_betmoney.setText("");
                                item.setInfrontAmount("");
                                getList().get(position).setReallyAmount(countTotalMoney(item, 2));
                                tv.setText(countTotalMoney(item, 2));
                            } else {
                                edt_infront_betmoney.setEnabled(true);
                                edt_top_betmoney.setEnabled(true);
                                edt_bottom_betmoney.setEnabled(true);
                                edt_toproll_betmoney.setEnabled(true);
                                getList().get(position).setReallyAmount(countTotalMoney(item, 3));
                                tv.setText(countTotalMoney(item, 3));
                            }
                        } else {
                            edt_infront_betmoney.setEnabled(true);
                            edt_top_betmoney.setEnabled(true);
                            edt_bottom_betmoney.setEnabled(true);
                            edt_toproll_betmoney.setEnabled(true);
                            edt_infront_betmoney.setText("");
                            edt_top_betmoney.setText("");
                            edt_bottom_betmoney.setText("");
                            edt_toproll_betmoney.setText("");
                            item.setInfrontAmount("");
                            item.setBottomAmount("");
                            item.setTopAmount("");
                            item.setTopRollAmount("");
                            item.setReallyAmount("");
                            tv.setText("");
                        }
                        getList().get(position).setNum(num);
//                        setBetMoney();
                    }
                });
            }

            private void setEditWatcher(EditText edt, final String type, final int position, final ThaiLotteryFastbetBean betBean, final TextView tv) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLotteryFastbetBean item = getList().get(position);
                        String num = item.getNum();
                        String money = s.toString();
                        int moneyLength = num.length();
                        if (type.equals(TOP)) {
                            getList().get(position).setTopAmount(money);
                        } else if (type.equals(BOTTOM)) {
                            getList().get(position).setBottomAmount(money);
                        } else if (type.equals(TOPROLL)) {
                            getList().get(position).setTopRollAmount(money);
                        } else if (type.equals(INFRONT)) {
                            getList().get(position).setInfrontAmount(money);
                        }
                        if (!TextUtils.isEmpty(num)) {
                            if (moneyLength == 1) {
                                getList().get(position).setReallyAmount(countTotalMoney(item, 1));
                                tv.setText(countTotalMoney(item, 1));
                            } else if (moneyLength == 2) {
                                getList().get(position).setReallyAmount(countTotalMoney(item, 2));
                                tv.setText(countTotalMoney(item, 2));
                            } else if (moneyLength == 3) {
                                getList().get(position).setReallyAmount(countTotalMoney(item, 3));
                                tv.setText(countTotalMoney(item, 3));
                            }
                        }
                    }
                });
            }

            private String countTotalMoney(ThaiLotteryFastbetBean bean, int type) {
                String topAmount = bean.getTopAmount();
                String infrontAmoutn = bean.getInfrontAmount();
                String bottomAmount = bean.getBottomAmount();
                String topRollAmount = bean.getTopRollAmount();
                if (type == 1) {
                    double topReallyAmount = countMoney(topAmount, bean.getDiscount1DTop());
                    double bottomReallyAmount = countMoney(bottomAmount, bean.getDiscount1DBottom());
                    return changeBetMoney(topReallyAmount + bottomReallyAmount);
                } else if (type == 2) {
                    double topReallyAmount = countMoney(topAmount, bean.getDiscount2DTop());
                    double bottomReallyAmount = countMoney(bottomAmount, bean.getDiscount2DBottom());
                    double topRollReallyAmount = countMoney(topRollAmount, bean.getDiscount2DTopRoll());
                    return changeBetMoney(topReallyAmount + bottomReallyAmount + topRollReallyAmount);
                } else {
                    double topReallyAmount = countMoney(topAmount, bean.getDiscount3DTop());
                    double bottomReallyAmount = countMoney(bottomAmount, bean.getDiscount3DBottom());
                    double topRollReallyAmount = countMoney(topRollAmount, bean.getDiscount3DTopRoll());
                    double inforntReallyAmount = countMoney(infrontAmoutn, bean.getDiscount3DInfront());
                    return changeBetMoney(topReallyAmount + bottomReallyAmount + topRollReallyAmount + inforntReallyAmount);
                }
            }
        };
        listContentPtrlv.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void clearBetMoney() {
        initListData();
        adapter.setList(betBeanList);
        adapter.notifyDataSetChanged();
        clearTotalMoney();
    }

    @Override
    public void updataGameState() {
        clearTotalMoney();
        thaiLottertGameList = getAct().getThaiLotteryDataList();
        initHeaderView(header);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (thaiLottertGameList.size() != 0) {
                    lottery_center_progress_ll.setVisibility(View.GONE);
                }
            }
        });
        adapter.setList(betBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGameFastBetUrl;
    }

    @Override
    public void setProgressVisibility() {

    }

    public void setBetMoney() {
        double money = 0;
        double betMoney = 0;
        List<ThaiLotteryFastbetBean> fastbetBeanList = adapter.getList();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLotteryFastbetBean bean = fastbetBeanList.get(i);
            money += (Double.parseDouble(initBetMoney(bean.getTopAmount())) + Double.parseDouble(initBetMoney(bean.getBottomAmount())) +
                    Double.parseDouble(initBetMoney(bean.getTopRollAmount())) + Double.parseDouble(initBetMoney(bean.getInfrontAmount())));
            betMoney += Double.parseDouble(initBetMoney(bean.getReallyAmount()));
        }
        getAct().total_amount_tv.setText(getString(R.string.zonge) + DecimalUtils.decimalFormat(money, "0.000"));
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + DecimalUtils.decimalFormat(betMoney, "0.000"));
    }


    @Override
    public String getBetUrlParmas() {
        if (!isCanBet()) {
            return getString(R.string.not_enough_balance_please_deposit_first);
        }
        List<ThaiLotteryFastbetBean> fastbetBeanList = adapter.getList();
        if (fastbetBeanList == null || fastbetBeanList.size() == 0) {
            return getString(R.string.qingchashumu);
        }
        StringBuffer betBuffer = new StringBuffer();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLotteryFastbetBean bean = fastbetBeanList.get(i);
            String num = bean.getNum();
            if (!TextUtils.isEmpty(num)) {
                String topAmount = initBetMoney(bean.getTopAmount());
                String infrontAmount = initBetMoney(bean.getInfrontAmount());
                String bottomAmount = initBetMoney(bean.getBottomAmount());
                String topRollAmount = initBetMoney(bean.getTopRollAmount());
                if (topAmount.equals("0.000") && infrontAmount.equals("0.000") && bottomAmount.equals("0.000") && topRollAmount.equals("0.000")) {
                    return getString(R.string.qingchashumu);
                }
                if (num.length() == 1) {
                    String topAmount1DLimit = isRightBetMoney(topAmount, bean.getMinBet1DTop(), bean.getMaxBet1DTop());
                    String bottomAmount1DLimit = isRightBetMoney(bottomAmount, bean.getMinBet1DBottom(), bean.getMaxBet1DBottom());
                    if (!topAmount1DLimit.equals(RIGHT)) {
                        return topAmount1DLimit;
                    }
                    if (!bottomAmount1DLimit.equals(RIGHT)) {
                        return bottomAmount1DLimit;
                    }
                } else if (num.length() == 2) {
                    String topAmount2DLimit = isRightBetMoney(topAmount, bean.getMinBet2DTop(), bean.getMaxBet2DTop());
                    String bottomAmount2DLimit = isRightBetMoney(bottomAmount, bean.getMinBet2DBottom(), bean.getMaxBet2DBottom());
                    String topRollAmount2DLimit = isRightBetMoney(topRollAmount, bean.getMinBet2DTopRoll(), bean.getMaxBet2DTopRoll());
                    if (!topAmount2DLimit.equals(RIGHT)) {
                        return topAmount2DLimit;
                    }
                    if (!bottomAmount2DLimit.equals(RIGHT)) {
                        return bottomAmount2DLimit;
                    }
                    if (!topRollAmount2DLimit.equals(RIGHT)) {
                        return topRollAmount2DLimit;
                    }
                } else {
                    String topAmount3DLimit = isRightBetMoney(topAmount, bean.getMinBet3DTop(), bean.getMaxBet3DTop());
                    String bottomAmount3DLimit = isRightBetMoney(bottomAmount, bean.getMinBet3DBottom(), bean.getMaxBet3DBottom());
                    String topRollAmount3DLimit = isRightBetMoney(topRollAmount, bean.getMinBet3DTopRoll(), bean.getMaxBet3DTopRoll());
                    String InfrontAmount3DLimit = isRightBetMoney(infrontAmount, bean.getMinBet3DInfront(), bean.getMaxBet3DInfront());
                    if (!topAmount3DLimit.equals(RIGHT)) {
                        return topAmount3DLimit;
                    }
                    if (!bottomAmount3DLimit.equals(RIGHT)) {
                        return bottomAmount3DLimit;
                    }
                    if (!topRollAmount3DLimit.equals(RIGHT)) {
                        return topRollAmount3DLimit;
                    }
                    if (!InfrontAmount3DLimit.equals(RIGHT)) {
                        return InfrontAmount3DLimit;
                    }
                }
                if (!TextUtils.isEmpty(betBuffer.toString())) {
                    betBuffer.append("^");
                }
                betBuffer.append(num + "#");
                betBuffer.append(initBetParmas(topAmount) + "#");
                betBuffer.append(initBetParmas(infrontAmount) + "#");
                betBuffer.append(initBetParmas(bottomAmount) + "#");
                betBuffer.append(initBetParmas(topRollAmount));
            }
        }
        if (TextUtils.isEmpty(betBuffer.toString())) {
            return getString(R.string.qingchashumu);
        } else {
            return betBuffer.toString();
        }
    }

    @Override
    public String getBetIdProvider() {
        return thaiLottertGameList.get(0).getType2();
    }
}
