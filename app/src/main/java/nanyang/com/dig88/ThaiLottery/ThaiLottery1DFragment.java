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
import nanyang.com.dig88.ThaiLottery.bean.ThaiLottery1DBean;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLottery1DFragment extends ThaiLotteryBaseFragment {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.lottery_center_progress_ll)
    LinearLayout lottery_center_progress_ll;
    QuickBaseAdapter<ThaiLottery1DBean> adapter;
    private List<ThaiLottery1DBean> betBeanList;
    private String discount1DTop;
    private String discount1DBottom;
    private String minBet1DTop;
    private String minBet1DBottom;
    private String maxBet1DTop;
    private String maxBet1DBottom;
    private String odds1DTop;
    private String odds1DBottom;
    private String playtype1DTop;
    private String playtype1DBottom;
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
        TextView thai_lottery_top_discount = (TextView) header.findViewById(R.id.thai_lottery_top_discount);
        TextView thai_lottery_top_odds = (TextView) header.findViewById(R.id.thai_lottery_top_odds);
        TextView thai_lottery_botton_discount = (TextView) header.findViewById(R.id.thai_lottery_botton_discount);
        TextView thai_lottery_botton_odds = (TextView) header.findViewById(R.id.thai_lottery_botton_odds);
        for (int i = 0; i < thaiLottertGameList.size(); i++) {
            DigGameOddsBean oddsBean = thaiLottertGameList.get(i);
            String discount = oddsBean.getDiscount();
            String odds = oddsBean.getFactor();
            switch (oddsBean.getType3()) {
                case "71":
                    discount1DTop = discount;
                    odds1DTop = odds;
                    minBet1DTop = oddsBean.getMin_bet();
                    maxBet1DTop = oddsBean.getMax_bet();
                    playtype1DTop = oddsBean.getType3();
                    thai_lottery_top_discount.setText(getString(R.string.promotions) + " " + discount + "%");
                    thai_lottery_top_odds.setText(getString(R.string.zodiapeilv) + " " + odds);
                    break;
                case "72":
                    discount1DBottom = discount;
                    odds1DBottom = odds;
                    minBet1DBottom = oddsBean.getMin_bet();
                    maxBet1DBottom = oddsBean.getMax_bet();
                    playtype1DBottom = oddsBean.getType3();
                    thai_lottery_botton_discount.setText(getString(R.string.promotions) + " " + discount + "%");
                    thai_lottery_botton_odds.setText(getString(R.string.zodiapeilv) + " " + odds);
                    break;
            }
        }
        initListData();
    }

    private void initListData() {
        betBeanList.clear();
        for (int i = 0; i < 10; i++) {
            betBeanList.add(new ThaiLottery1DBean(playtype1DTop, playtype1DBottom, discount1DTop, discount1DBottom, odds1DTop, odds1DBottom,
                    minBet1DTop, maxBet1DTop, minBet1DBottom, maxBet1DBottom, i + ""));
        }
    }

    private void addListViewHeader() {
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        header = LayoutInflater.from(mContext).inflate(R.layout.header_thai_lottery_1d, null);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
        initAdapter();
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<ThaiLottery1DBean>(mContext, R.layout.item_thai_lottery_1d, betBeanList) {
            @Override
            public View getView(int position, View convertView, ViewGroup paren) {
                ViewHolder holder = new ViewHolder(mContext, paren, R.layout.item_thai_lottery_1d);
                convert(holder, getItem(position), position);
                return holder.getView();
            }

            @Override
            protected void convert(ViewHolder helper, ThaiLottery1DBean item, int position) {
                TextView top_num = helper.retrieveView(R.id.thai_lottery_top_num);
                EditText top_amount = helper.retrieveView(R.id.thai_lottery_top_amount);
                TextView top_really_amount = helper.retrieveView(R.id.thai_lottery_top_really_amount);
                TextView bottom_num = helper.retrieveView(R.id.thai_lottery_bottom_num);
                EditText bottom_amount = helper.retrieveView(R.id.thai_lottery_bottom_amount);
                TextView bottom_really_amount = helper.retrieveView(R.id.thai_lottery_bottom_really_amount);
                top_num.setText(item.getNum());
                bottom_num.setText(item.getNum());
                top_amount.setText(item.getTopAmount());
                top_really_amount.setText(item.getTopReallyAmount());
                bottom_amount.setText(item.getBottomAmount());
                bottom_really_amount.setText(item.getBottomReallyAmount());
                setAmountEditWatcher(top_amount, top_really_amount, TOP, position);
                setAmountEditWatcher(bottom_amount, bottom_really_amount, BOTTOM, position);
            }

            private void setAmountEditWatcher(EditText edt, final TextView tv, final String type, final int positon) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ThaiLottery1DBean item = getList().get(positon);
                        String money = s.toString();
                        String reallyMoney;
                        if (!TextUtils.isEmpty(money)) {
                            if (type.equals(TOP)) {
                                item.setTopAmount(money);
                                reallyMoney = changeBetMoney(countMoney(money, item.getDiscount1DTop()));
                                item.setTopReallyAmount(reallyMoney);
                                tv.setText(reallyMoney);
                            } else {
                                item.setBottomAmount(money);
                                reallyMoney = changeBetMoney(countMoney(money, item.getDiscount1DBottom()));
                                item.setBottomReallyAmount(reallyMoney);
                                tv.setText(reallyMoney);
                            }
                        } else {
                            reallyMoney = "";
                            if (type.equals(TOP)){
                                item.setTopAmount("");
                                item.setTopReallyAmount(reallyMoney);
                                tv.setText(reallyMoney);
                            }else {
                                item.setBottomAmount("");
                                item.setBottomReallyAmount(reallyMoney);
                                tv.setText(reallyMoney);
                            }
                        }
                        setBetMoney();
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
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (thaiLottertGameList.size() != 0) {
                    lottery_center_progress_ll.setVisibility(View.GONE);
                }
            }
        });
        initHeaderView(header);
        adapter.setList(betBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGame1DUrl;
    }

    @Override
    public void setProgressVisibility() {

    }

    @Override
    public String getBetUrlParmas() {
        if (!isCanBet()) {
            return getString(R.string.not_enough_balance_please_deposit_first);
        }
        List<ThaiLottery1DBean> list = adapter.getList();
        if (list == null || list.size() == 0) {
            return getString(R.string.qingchashumu);
        }
        StringBuffer betBuffer = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            ThaiLottery1DBean bean = list.get(i);
            String num = bean.getNum();
            String topAmount = initBetMoney(bean.getTopAmount());
            String bottomAmount = initBetMoney(bean.getBottomAmount());
            String topAmountLimit = isRightBetMoney(topAmount, bean.getMin1DTop(), bean.getMax1DTop());
            String bottomAmountLimit = isRightBetMoney(bottomAmount, bean.getMin1DBottom(), bean.getMax1DBottom());
            if (topAmount.equals("0.000") && bottomAmount.equals("0.000")) {
                continue;
            }
            if (!topAmountLimit.equals(RIGHT)) {
                return topAmountLimit;
            }
            if (!bottomAmountLimit.equals(RIGHT)) {
                return bottomAmountLimit;
            }
            if (!TextUtils.isEmpty(betBuffer.toString())) {
                betBuffer.append("^");
            }
            if (!TextUtils.isEmpty(topAmount)) {
                betBuffer.append(bean.getPlaytype1DTop() + "#");
                betBuffer.append(num + "#");
                betBuffer.append(initBetParmas(topAmount));
            }
            if (!TextUtils.isEmpty(bottomAmount)) {
                if (!TextUtils.isEmpty(topAmount)) {
                    betBuffer.append("^");
                }
                betBuffer.append(bean.getPlaytype1DBottom() + "#");
                betBuffer.append(num + "#");
                betBuffer.append(initBetParmas(bottomAmount));
            }
        }
        if (!TextUtils.isEmpty(betBuffer.toString())) {
            return betBuffer.toString();
        } else {
            return getString(R.string.qingchashumu);
        }
    }

    public void setBetMoney() {
        double money = 0;
        double betMoney = 0;
        List<ThaiLottery1DBean> fastbetBeanList = adapter.getList();
        for (int i = 0; i < fastbetBeanList.size(); i++) {
            ThaiLottery1DBean bean = fastbetBeanList.get(i);
            money += (Double.parseDouble(initBetMoney(bean.getTopAmount())) + Double.parseDouble(initBetMoney(bean.getBottomAmount())));
            betMoney += (Double.parseDouble(initBetMoney(bean.getTopReallyAmount())) +
                    Double.parseDouble(initBetMoney(bean.getBottomReallyAmount())));
        }
        getAct().total_amount_tv.setText(getString(R.string.zonge) + DecimalUtils.decimalFormat(money, "0.000"));
        getAct().bet_total_amount_tv.setText(getString(R.string.zongtouzhue) + DecimalUtils.decimalFormat(betMoney, "0.000"));
    }

    @Override
    public String getBetIdProvider() {
        return thaiLottertGameList.get(0).getType2();
    }
}
