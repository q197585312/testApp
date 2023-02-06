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

import butterknife.Bind;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.ThaiLottery.bean.ThaiLottery2DBean;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;

import static android.R.id.list;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLottery2DFragment extends ThaiLotteryBaseFragment {
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @Bind(R.id.lottery_center_progress_ll)
    LinearLayout lottery_center_progress_ll;
    QuickBaseAdapter<ThaiLottery2DBean> adapter;
    private List<ThaiLottery2DBean> betBeanList;
    private String discount2DTop;
    private String discount2DBottom;
    private String discount2DTopRoll;
    private String minBet2DTop;
    private String minBet2DBottom;
    private String minBet2DTopRoll;
    private String maxBet2DTop;
    private String maxBet2DBottom;
    private String maxBet2DTopRoll;
    private String odds2DTop;
    private String odds2DBottom;
    private String odds2DTopRoll;
    private View header;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        betBeanList = new ArrayList<>();
        addListViewHeader();
    }

    private void addListViewHeader() {
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        header = LayoutInflater.from(mContext).inflate(R.layout.header_thai_lottery_2d, null);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
        initAdapter();
    }

    private void initHeaderView() {
        TextView lottery_2d_topdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_topdiscount);
        TextView lottery_2d_bottomdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_bottomdiscount);
        TextView lottery_2d_toprolldiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_toprolldiscount);
        TextView lottery_2d_top_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_top_odds);
        TextView lottery_2d_bottom_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_bottom_odds);
        TextView lottery_2d_toproll_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_2d_toproll_odds);
        for (int i = 0; i < thaiLottertGameList.size(); i++) {
            DigGameOddsBean oddsBean = thaiLottertGameList.get(i);
            String discount = oddsBean.getDiscount();
            String odds = oddsBean.getFactor();
            switch (oddsBean.getType3()) {
                case "73":
                    discount2DTop = discount;
                    odds2DTop = odds;
                    minBet2DTop = oddsBean.getMin_bet();
                    maxBet2DTop = oddsBean.getMax_bet();
                    lottery_2d_topdiscount.setText(discount + "%");
                    lottery_2d_top_odds.setText(odds);
                    break;
                case "74":
                    discount2DBottom = discount;
                    odds2DBottom = odds;
                    minBet2DBottom = oddsBean.getMin_bet();
                    maxBet2DBottom = oddsBean.getMax_bet();
                    lottery_2d_bottomdiscount.setText(discount + "%");
                    lottery_2d_bottom_odds.setText(odds);
                    break;
                case "75":
                    discount2DTopRoll = discount;
                    odds2DTopRoll = odds;
                    minBet2DTopRoll = oddsBean.getMin_bet();
                    maxBet2DTopRoll = oddsBean.getMax_bet();
                    lottery_2d_toprolldiscount.setText(discount + "%");
                    lottery_2d_toproll_odds.setText(odds);
                    break;
            }
        }
        initListData();
    }

    @Override
    public void clearBetMoney() {
        initListData();
        adapter.setList(betBeanList);
        adapter.notifyDataSetChanged();
        clearTotalMoney();
    }

    private void initListData() {
        betBeanList.clear();
        for (int i = 0; i < 10; i++) {
            betBeanList.add(new ThaiLottery2DBean(discount2DTop, discount2DBottom, discount2DTopRoll, minBet2DTop, minBet2DBottom, minBet2DTopRoll,
                    maxBet2DTop, maxBet2DBottom, maxBet2DTopRoll, odds2DTop, odds2DBottom, odds2DTopRoll));
        }
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<ThaiLottery2DBean>(mContext, R.layout.item_thai_lottery_2d, betBeanList) {
            @Override
            public View getView(int position, View convertView, ViewGroup paren) {
                ViewHolder holder = new ViewHolder(mContext, paren, R.layout.item_thai_lottery_2d);
                convert(holder, getItem(position), position);
                return holder.getView();
            }

            @Override
            protected void convert(ViewHolder helper, ThaiLottery2DBean item, int position) {
                EditText lottery_2d_num = helper.retrieveView(R.id.edt_thai_lottery_2d_num);
                EditText lottery_2d_top_betmoney = helper.retrieveView(R.id.edt_thai_lottery_2d_top_betmoney);
                EditText lottery_2d_bottom_betmoney = helper.retrieveView(R.id.edt_thai_lottery_2d_bottom_betmoney);
                EditText lottery_2d_toproll_betmoney = helper.retrieveView(R.id.edt_thai_lottery_2d_toproll_betmoney);
                TextView lottery_2d_reality_betmoney = helper.retrieveView(R.id.tv_thai_lottery_2d_reality_betmoney);
                lottery_2d_num.setText(item.getNum());
                lottery_2d_top_betmoney.setText(item.getTopAmount());
                lottery_2d_bottom_betmoney.setText(item.getBottomAmount());
                lottery_2d_toproll_betmoney.setText(item.getTopRollAmount());
                lottery_2d_reality_betmoney.setText(item.getReallyAmount());
                setEditNumWacher(position, lottery_2d_num, lottery_2d_reality_betmoney, lottery_2d_top_betmoney, lottery_2d_bottom_betmoney, lottery_2d_toproll_betmoney);
                setEditMoneyWacher(position, lottery_2d_top_betmoney, lottery_2d_reality_betmoney, TOP);
                setEditMoneyWacher(position, lottery_2d_bottom_betmoney, lottery_2d_reality_betmoney, BOTTOM);
                setEditMoneyWacher(position, lottery_2d_toproll_betmoney, lottery_2d_reality_betmoney, TOPROLL);
                setMoneyChangeWatcher(lottery_2d_reality_betmoney);
            }
            private void setMoneyChangeWatcher(TextView tv){
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
            private void setEditNumWacher(final int position, EditText edt, final TextView tv, final EditText top, final EditText bottom, final EditText topRoll) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLottery2DBean item = getList().get(position);
                        String num = s.toString();
                        if (!TextUtils.isEmpty(num)) {
                            getList().get(position).setReallyAmount(countTotalMoney(item));
                            tv.setText(countTotalMoney(item));
                        } else {
                            top.setText("");
                            bottom.setText("");
                            topRoll.setText("");
                            item.setBottomAmount("");
                            item.setTopAmount("");
                            item.setTopRollAmount("");
                            item.setReallyAmount("");
                            item.setBottomReallyAmount("");
                            item.setTopRollReallyAmount("");
                            item.setTopReallyAmount("");
                            tv.setText("");
                        }
                        getList().get(position).setNum(num);
//                        setBetMoney();
                    }
                });
            }

            private String countTotalMoney(ThaiLottery2DBean bean) {
                String topAmount = bean.getTopAmount();
                String bottomAmount = bean.getBottomAmount();
                String topRollAmount = bean.getTopRollAmount();
                double topReallyAmount = countMoney(topAmount, bean.getDiscount2DTop());
                double bottomReallyAmount = countMoney(bottomAmount, bean.getDiscount2DBottom());
                double topRollReallyAmount = countMoney(topRollAmount, bean.getDiscount2DTopRoll());
                return changeBetMoney(topReallyAmount + bottomReallyAmount + topRollReallyAmount);
            }

            private void setEditMoneyWacher(final int position, EditText edt, final TextView tv, final String type) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLottery2DBean item = getList().get(position);
                        String num = getList().get(position).getNum();
                        String money = s.toString();
                        if (type.equals(TOP)) {
                            getList().get(position).setTopAmount(money);
                        } else if (type.equals(BOTTOM)) {
                            getList().get(position).setBottomAmount(money);
                        } else if (type.equals(TOPROLL)) {
                            getList().get(position).setTopRollAmount(money);
                        }
                        if (!TextUtils.isEmpty(num)) {
                            getList().get(position).setReallyAmount(countTotalMoney(item));
                            tv.setText(countTotalMoney(item));
                        }
//                        setBetMoney();
                    }
                });
            }
        };
        listContentPtrlv.setAdapter(adapter);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_list_content;
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
                }
            }
        });
        initHeaderView();
        adapter.setList(betBeanList);
        adapter.notifyDataSetChanged();
    }

    public void setBetMoney() {
        double money = 0;
        double betMoney = 0;
        List<ThaiLottery2DBean> fastbetBeanList = adapter.getList();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLottery2DBean bean = fastbetBeanList.get(i);
            money += (Double.parseDouble(initBetMoney(bean.getTopAmount())) + Double.parseDouble(initBetMoney(bean.getBottomAmount())) +
                    Double.parseDouble(initBetMoney(bean.getTopRollAmount())));
            betMoney += Double.parseDouble(initBetMoney(bean.getReallyAmount()));
        }
        getAct().total_amount_tv.setText(getString(R.string.zonge) + DecimalUtils.decimalFormat(money, "0.000"));
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + DecimalUtils.decimalFormat(betMoney, "0.000"));
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGame2DUrl;
    }

    @Override
    public void setProgressVisibility() {

    }

    @Override
    public String getBetUrlParmas() {
        if (!isCanBet()) {
            return getString(R.string.not_enough_balance_please_deposit_first);
        }
        List<ThaiLottery2DBean> fastbetBeanList = adapter.getList();
        if (fastbetBeanList == null || fastbetBeanList.size() == 0) {
            return getString(R.string.qingchashumu);
        }
        StringBuffer betBuffer = new StringBuffer();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLottery2DBean bean = fastbetBeanList.get(i);
            String num = bean.getNum();
            if (!TextUtils.isEmpty(num)) {
                if (num.length() == 2) {
                    String topAmount = initBetMoney(bean.getTopAmount());
                    String bottomAmount = initBetMoney(bean.getBottomAmount());
                    String topRollAmount = initBetMoney(bean.getTopRollAmount());
                    if (topAmount.equals("0.000") && bottomAmount.equals("0.000") && topRollAmount.equals("0.000")) {
                        return getString(R.string.qingchashumu);
                    }
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
                    if (!TextUtils.isEmpty(betBuffer.toString())) {
                        betBuffer.append("^");
                    }
                    betBuffer.append(num + "#");
                    betBuffer.append(initBetParmas(topAmount) + "#");
                    betBuffer.append(initBetParmas(bottomAmount) + "#");
                    betBuffer.append(initBetParmas(topRollAmount));
                } else {
                    return getString(R.string.enter_correct_number);
                }
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
