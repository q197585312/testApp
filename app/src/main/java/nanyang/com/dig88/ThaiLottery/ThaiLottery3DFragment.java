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
import nanyang.com.dig88.ThaiLottery.bean.ThaiLottery3DBean;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLottery3DFragment extends ThaiLotteryBaseFragment {
    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @Bind(R.id.lottery_center_progress_ll)
    LinearLayout lottery_center_progress_ll;
    QuickBaseAdapter<ThaiLottery3DBean> adapter;
    private List<ThaiLottery3DBean> betBeanList;
    private String discount3DTop;
    private String discount3DInfront;
    private String discount3DBottom;
    private String discount3DTopRoll;
    private String minBet3DTop;
    private String minBet3DInfront;
    private String minBet3DBottom;
    private String minBet3DTopRoll;
    private String maxBet3DInfront;
    private String maxBet3DTop;
    private String maxBet3DBottom;
    private String maxBet3DTopRoll;
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
        header = LayoutInflater.from(mContext).inflate(R.layout.header_thai_lottery_3d, null);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
        initAdapter();
    }

    private void initHeaderView() {
        TextView lottery_3d_topdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_topdiscount);
        TextView lottery_3d_bottomdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_bottomdiscount);
        TextView lottery_3d_infrontdiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_infrontdiscount);
        TextView lottery_3d_toprolldiscount = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_toprolldiscount);
        TextView lottery_3d_top_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_top_odds);
        TextView lottery_3d_bottom_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_bottom_odds);
        TextView lottery_3d_infront_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_infront_odds);
        TextView lottery_3d_toproll_odds = (TextView) header.findViewById(R.id.tv_thai_lottery_3d_toproll_odds);
        for (int i = 0; i < thaiLottertGameList.size(); i++) {
            DigGameOddsBean oddsBean = thaiLottertGameList.get(i);
            String discount = oddsBean.getDiscount();
            String odds = oddsBean.getFactor();
            switch (oddsBean.getType3()) {
                case "76":
                    discount3DTop = discount;
                    minBet3DTop = oddsBean.getMin_bet();
                    maxBet3DTop = oddsBean.getMax_bet();
                    lottery_3d_topdiscount.setText(discount + "%");
                    lottery_3d_top_odds.setText(odds);
                    break;
                case "77":
                    discount3DBottom = discount;
                    minBet3DBottom = oddsBean.getMin_bet();
                    maxBet3DBottom = oddsBean.getMax_bet();
                    lottery_3d_bottomdiscount.setText(discount + "%");
                    lottery_3d_bottom_odds.setText(odds);
                    break;
                case "78":
                    discount3DTopRoll = discount;
                    minBet3DTopRoll = oddsBean.getMin_bet();
                    maxBet3DTopRoll = oddsBean.getMax_bet();
                    lottery_3d_toprolldiscount.setText(discount + "%");
                    lottery_3d_toproll_odds.setText(odds);
                    break;
                case "79":
                    discount3DInfront = discount;
                    minBet3DInfront = oddsBean.getMin_bet();
                    maxBet3DInfront = oddsBean.getMax_bet();
                    lottery_3d_infrontdiscount.setText(discount + "%");
                    lottery_3d_infront_odds.setText(odds);
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
            betBeanList.add(new ThaiLottery3DBean(discount3DTop, discount3DInfront, discount3DBottom, discount3DTopRoll,
                    minBet3DTop, minBet3DInfront, minBet3DBottom, minBet3DTopRoll,
                    maxBet3DInfront, maxBet3DTop, maxBet3DBottom, maxBet3DTopRoll));
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<ThaiLottery3DBean>(mContext, R.layout.item_thai_lottery_3d, betBeanList) {
            @Override
            public View getView(int position, View convertView, ViewGroup paren) {
                ViewHolder holder = new ViewHolder(mContext, paren, R.layout.item_thai_lottery_3d);
                convert(holder, getItem(position), position);
                return holder.getView();
            }

            @Override
            protected void convert(ViewHolder helper, ThaiLottery3DBean item, int position) {
                EditText lottery_3d_num = helper.retrieveView(R.id.edt_thai_lottery_3d_num);
                EditText lottery_3d_top_betmoney = helper.retrieveView(R.id.edt_thai_lottery_3d_top_betmoney);
                EditText lottery_3d_infront_betmoney = helper.retrieveView(R.id.edt_thai_lottery_3d_infront_betmoney);
                EditText lottery_3d_bottom_betmoney = helper.retrieveView(R.id.edt_thai_lottery_3d_bottom_betmoney);
                EditText lottery_3d_toproll_betmoney = helper.retrieveView(R.id.edt_thai_lottery_3d_toproll_betmoney);
                TextView lottery_3d_reality_betmoney = helper.retrieveView(R.id.tv_thai_lottery_3d_reality_betmoney);
                lottery_3d_num.setText(item.getNum());
                lottery_3d_top_betmoney.setText(item.getTopAmount());
                lottery_3d_infront_betmoney.setText(item.getInfrontAmount());
                lottery_3d_bottom_betmoney.setText(item.getBottomAmount());
                lottery_3d_toproll_betmoney.setText(item.getTopRollAmount());
                lottery_3d_reality_betmoney.setText(item.getReallyAmount());
                setEditNumWacher(position, lottery_3d_num, lottery_3d_top_betmoney, lottery_3d_infront_betmoney, lottery_3d_bottom_betmoney, lottery_3d_toproll_betmoney, lottery_3d_reality_betmoney);
                setEditMoneyWacher(position, lottery_3d_top_betmoney, lottery_3d_reality_betmoney, TOP);
                setEditMoneyWacher(position, lottery_3d_infront_betmoney, lottery_3d_reality_betmoney, INFRONT);
                setEditMoneyWacher(position, lottery_3d_bottom_betmoney, lottery_3d_reality_betmoney, BOTTOM);
                setEditMoneyWacher(position, lottery_3d_toproll_betmoney, lottery_3d_reality_betmoney, TOPROLL);
                setMoneyChangeWatcher(lottery_3d_reality_betmoney);
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

            private void setEditNumWacher(final int position, EditText edt, final EditText top, final EditText infront, final EditText bottom, final EditText topRoll, final TextView tv) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLottery3DBean item = getList().get(position);
                        String num = s.toString();
                        if (!TextUtils.isEmpty(num)) {
                            getList().get(position).setReallyAmount(countTotalMoney(item));
                            tv.setText(countTotalMoney(item));
                        } else {
                            top.setText("");
                            infront.setText("");
                            bottom.setText("");
                            topRoll.setText("");
                            item.setBottomAmount("");
                            item.setTopAmount("");
                            item.setInfrontAmount("");
                            item.setTopRollAmount("");
                            item.setReallyAmount("");
                            item.setBottomReallyAmount("");
                            item.setTopRollReallyAmount("");
                            item.setTopReallyAmount("");
                            item.setInfrontReallyAmount("");
                            tv.setText("");
                        }
                        getList().get(position).setNum(num);
//                        setBetMoney();
                    }
                });
            }

            private String countTotalMoney(ThaiLottery3DBean bean) {
                String topAmount = bean.getTopAmount();
                String infrontAmoutn = bean.getInfrontAmount();
                String bottomAmount = bean.getBottomAmount();
                String topRollAmount = bean.getTopRollAmount();
                double topReallyAmount = countMoney(topAmount, bean.getDiscount3DTop());
                double bottomReallyAmount = countMoney(bottomAmount, bean.getDiscount3DBottom());
                double topRollReallyAmount = countMoney(topRollAmount, bean.getDiscount3DTopRoll());
                double inforntReallyAmount = countMoney(infrontAmoutn, bean.getDiscount3DInfront());
                return changeBetMoney(topReallyAmount + bottomReallyAmount + topRollReallyAmount + inforntReallyAmount);
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
                        ThaiLottery3DBean item = getList().get(position);
                        String num = getList().get(position).getNum();
                        String money = s.toString();
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
        List<ThaiLottery3DBean> fastbetBeanList = adapter.getList();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLottery3DBean bean = fastbetBeanList.get(i);
            money += (Double.parseDouble(initBetMoney(bean.getTopAmount())) + Double.parseDouble(initBetMoney(bean.getBottomAmount())) +
                    Double.parseDouble(initBetMoney(bean.getTopRollAmount())) + Double.parseDouble(initBetMoney(bean.getInfrontAmount())));
            betMoney += Double.parseDouble(initBetMoney(bean.getReallyAmount()));
        }
        getAct().total_amount_tv.setText(getString(R.string.zonge) + DecimalUtils.decimalFormat(money, "0.000"));
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + DecimalUtils.decimalFormat(betMoney, "0.000"));
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGame3DUrl;
    }

    @Override
    public void setProgressVisibility() {

    }

    @Override
    public String getBetUrlParmas() {
        if (!isCanBet()) {
            return getString(R.string.not_enough_balance_please_deposit_first);
        }
        List<ThaiLottery3DBean> fastbetBeanList = adapter.getList();
        if (fastbetBeanList == null || fastbetBeanList.size() == 0) {
            return getString(R.string.qingchashumu);
        }
        StringBuffer betBuffer = new StringBuffer();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLottery3DBean bean = fastbetBeanList.get(i);
            String num = bean.getNum();
            if (!TextUtils.isEmpty(num)) {
                if (num.length() == 3) {
                    String topAmount = initBetMoney(bean.getTopAmount());
                    String infrontAmount = initBetMoney(bean.getInfrontAmount());
                    String bottomAmount = initBetMoney(bean.getBottomAmount());
                    String topRollAmount = initBetMoney(bean.getTopRollAmount());
                    if (topAmount.equals("0.000") && infrontAmount.equals("0.000") && bottomAmount.equals("0.000") && topRollAmount.equals("0.000")) {
                        return getString(R.string.qingchashumu);
                    }
                    String topAmount3DLimit = isRightBetMoney(topAmount, bean.getMinBet3DTop(), bean.getMaxBet3DTop());
                    String infrontAmount3DLimit = isRightBetMoney(infrontAmount, bean.getMinBet3DInfront(), bean.getMaxBet3DInfront());
                    String bottomAmount3DLimit = isRightBetMoney(bottomAmount, bean.getMinBet3DBottom(), bean.getMaxBet3DBottom());
                    String topRollAmount3DLimit = isRightBetMoney(topRollAmount, bean.getMinBet3DTopRoll(), bean.getMaxBet3DTopRoll());
                    if (!topAmount3DLimit.equals(RIGHT)) {
                        return topAmount3DLimit;
                    }
                    if (!infrontAmount3DLimit.equals(RIGHT)) {
                        return infrontAmount3DLimit;
                    }
                    if (!bottomAmount3DLimit.equals(RIGHT)) {
                        return bottomAmount3DLimit;
                    }
                    if (!topRollAmount3DLimit.equals(RIGHT)) {
                        return topRollAmount3DLimit;
                    }
                    if (!TextUtils.isEmpty(betBuffer.toString())) {
                        betBuffer.append("^");
                    }
                    betBuffer.append(num + "#");
                    betBuffer.append(initBetParmas(topAmount) + "#");
                    betBuffer.append(initBetParmas(infrontAmount) + "#");
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
