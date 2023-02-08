package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/2/24.
 */
public class LotteryCombinationFragment extends LotteryBaseFragment<CombinationBean> {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.lottery_center_progress_ll)
    View progressView;
    private HashMap<Integer, Integer> selectedMap = new HashMap<>();
    private String period;
    private String gameName;
    private String discountBigSmall = "";
    private String oddsBigSmall = "";
    private String discountOddEven = "";
    private String oddsOddEven = "";
    private String discountCom = "";
    private String oddsCom = "";
    private String bigSmallKei;
    private String oddEvenKei;
    private String comKei;

    @Override
    public void setProgressVisibility(boolean isVisibility) {
        super.setProgressVisibility(isVisibility);
        if (isVisibility) {
            progressView.setVisibility(View.VISIBLE);
        } else {
            progressView.setVisibility(View.GONE);
        }
    }

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
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setAdapter(adapter);
    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean bean) {
        if (bean != null)
            initGameState(oddsList, bean);
    }

    protected void initGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean bean) {
        period = bean.getPeriod();
        gameName = bean.getGame_name();
        List<LotteryLimitBean> limitBeanList = new ArrayList<>(Arrays.asList(new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean()));
        for (DigGameOddsBean oddsBean : oddsList) {
            if (oddsBean.getType3().equals("4")) {
                adapter.getList().get(0).setDiscount(oddsBean.getDiscount());
                adapter.getList().get(0).setOdds(oddsBean.getFactor());
                adapter.getList().get(0).setKei(oddsBean.getKei());
                adapter.getList().get(0).setKei2(oddsBean.getKei2());

                discountBigSmall = oddsBean.getDiscount();
                oddsBigSmall = oddsBean.getFactor();
                bigSmallKei = oddsBean.getKei();
                Log.d("shang", "initGameState: " + bigSmallKei);
                limitBeanList.set(0, new LotteryLimitBean(period, gameName, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "BigSmall"));
            } else if (oddsBean.getType3().equals("5")) {
                adapter.getList().get(1).setDiscount(oddsBean.getDiscount());
                adapter.getList().get(1).setOdds(oddsBean.getFactor());
                adapter.getList().get(1).setKei(oddsBean.getKei());
                adapter.getList().get(1).setKei2(oddsBean.getKei2());
                discountOddEven = oddsBean.getDiscount();
                oddsOddEven = oddsBean.getFactor();
                oddEvenKei = oddsBean.getKei();
                Log.d("shang", "initGameState: " + oddEvenKei);
                limitBeanList.set(1, new LotteryLimitBean(period, gameName, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "OddEven"));
            } else if (oddsBean.getType3().equals("6")) {
                adapter.getList().get(2).setDiscount(oddsBean.getDiscount());
                adapter.getList().get(2).setOdds(oddsBean.getFactor());
                adapter.getList().get(2).setKei(oddsBean.getKei2());
                adapter.getList().get(2).setKei2(oddsBean.getKei());
                adapter.getList().get(2).setKei3(oddsBean.getKei4());
                adapter.getList().get(2).setKei4(oddsBean.getKei3());
                discountCom = oddsBean.getDiscount();
                comKei = oddsBean.getKei();
                Log.d("shang", "initGameState: " + comKei);
                oddsCom = oddsBean.getFactor();
                limitBeanList.set(2, new LotteryLimitBean(period, gameName, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "BSOE"));
            }
        }
        adapter.setLimitList(limitBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected String getSubmitPageUrl() {
        return "bsoe_submitter";
    }

    @Override
    protected String constructorGetBetStr() {
        StringBuilder builder = new StringBuilder();
//        playtype#bettype#amount^playtype#bettype#amount
//        BigSmall#1#10^
        List<String> submitStr = new ArrayList<>();
        int i = 0;
        for (CombinationBean combinationBean : adapter.getList()) {
            if (!selectedMap.containsKey(i) || combinationBean.getMoney().equals("")) {
                i++;
                continue;
            }
            int bettype = selectedMap.get(i);
            String playtype = adapter.getLimitList().get(i).getTypeKey();
            if (!submitStr.contains(playtype + "#" + bettype + "#" + combinationBean.getMoney())) {
                builder.append("^");
                builder.append(playtype);
                builder.append("#");
                builder.append(bettype);
                builder.append("#");
                builder.append(combinationBean.getMoney());
                submitStr.add(playtype + "#" + bettype + "#" + combinationBean.getMoney());
            }
            i++;
        }
        if (builder.length() < 1) {
            return "";
        }
        builder.deleteCharAt(0);
        return builder.toString();
    }

    @Override
    public LotteryBaseAdapter<CombinationBean> getAdapter() {
        return new LotteryBaseAdapter<CombinationBean>(mContext, R.layout.item_lottery_combination) {
            private void setClickSelectedMap(final ViewHolder holder, RadioButton rbt, final int position, final int i) {
                rbt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String count = "";
                        selectedMap.put(position, i);
                        setCheckState(holder, position);
                        String s = getList().get(position).getMoney();
                        if (!s.equals(""))
                            count = countMoney(position, s, getLimitList().get(position), 10, getList().get(position).getDiscount(), getList().get(position).getKei());
                        getList().get(position).setCount(count);
                        holder.setText(R.id.combination_count_item_tv, count);
                        countTotal();
                    }
                });
            }

            private void checkedPosition(final ViewHolder holder, final int position) {

                EditText moneyEdt = holder.retrieveView(R.id.combination_money_item_edt);
                moneyEdt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String kei = "0";
                        String count = "";
                        getList().get(position).setMoney(s.toString());
                        int selected = -1;
                        if (selectedMap.containsKey(position)) {
                            selected = selectedMap.get(position);
                        }
                        if (selected == 0) {
                            kei =getList().get(position).getKei();
                        } else if (selected == 1) {
                            kei = getList().get(position).getKei2();
                        } else if (selected == 2) {
                            kei =getList().get(position).getKei3();
                        } else if (selected == 3) {
                            kei =getList().get(position).getKei4();
                        }
                        if (selected >= 0 && !s.toString().trim().equals(""))
                            count = countMoney(position, s.toString(), getLimitList().get(position), 10, getList().get(position).getDiscount(), kei);
                        getList().get(position).setCount(count);
                        holder.setText(R.id.combination_count_item_tv, count);
                        countTotal();
                    }
                });

                setCheckState(holder, position);
                RadioButton rbt1 = holder.retrieveView(R.id.combination_select_item_rtn1);
                RadioButton rbt2 = holder.retrieveView(R.id.combination_select_item_rtn2);
                RadioButton rbt3 = holder.retrieveView(R.id.combination_select_item_rtn3);
                RadioButton rbt4 = holder.retrieveView(R.id.combination_select_item_rtn4);
                int firstType = 0;
                int secondType = 1;
                if (position < 2) {
                    firstType = 1 - firstType;
                    secondType = 1 - secondType;
                }
                setClickSelectedMap(holder, rbt1, position, firstType);
                setClickSelectedMap(holder, rbt2, position, secondType);
                setClickSelectedMap(holder, rbt3, position, 2);
                setClickSelectedMap(holder, rbt4, position, 3);

            }

            private void setCheckState(ViewHolder holder, int position) {
                int checkedState = -1;
                if (selectedMap.containsKey(position))
                    checkedState = selectedMap.get(position);
                RadioButton rbt1 = holder.retrieveView(R.id.combination_select_item_rtn1);
                RadioButton rbt2 = holder.retrieveView(R.id.combination_select_item_rtn2);
                RadioButton rbt3 = holder.retrieveView(R.id.combination_select_item_rtn3);
                RadioButton rbt4 = holder.retrieveView(R.id.combination_select_item_rtn4);
                rbt1.setChecked(false);
                rbt2.setChecked(false);
                rbt3.setChecked(false);
                rbt4.setChecked(false);

                if (checkedState == 0) {
                    if (position < 2) {
                        rbt2.setChecked(true);
                    } else
                        rbt1.setChecked(true);
                } else if (checkedState == 1) {
                    if (position < 2) {
                        rbt1.setChecked(true);
                    } else
                        rbt2.setChecked(true);
                } else if (checkedState == 2) {
                    rbt3.setChecked(true);
                } else if (checkedState == 3) {
                    rbt4.setChecked(true);
                }

            }

            @Override
            protected void convert(ViewHolder holder, CombinationBean item, final int position) {
                holder.setText(R.id.combination_title_item_tv, item.getTitle());
                holder.setText(R.id.combination_odds_item_tv, getString(R.string.discount) + ":" + item.getDiscount() + "%");
                holder.setText(R.id.combination_discount_item_tv, getString(R.string.odds) + ":" + item.getOdds());
                checkedPosition(holder, position);
                if (item.getList().size() < 3) {
                    holder.setVisible(R.id.combination_select_item_tv3, false);
                    holder.setVisible(R.id.combination_select_item_tv4, false);
                    holder.setVisible(R.id.combination_item_fl3, false);
                    holder.setVisible(R.id.combination_item_fl4, false);
                } else {
                    holder.setVisible(R.id.combination_item_fl3, true);
                    holder.setVisible(R.id.combination_item_fl4, true);
                    holder.setVisible(R.id.combination_select_item_tv3, true);
                    holder.setVisible(R.id.combination_select_item_tv4, true);
                    holder.setText(R.id.combination_select_item_tv3, item.getList().get(2).getName());
                    holder.setText(R.id.combination_select_item_tv4, item.getList().get(3).getName());

                }
                holder.setText(R.id.combination_select_item_tv1, item.getList().get(0).getName());
                holder.setText(R.id.combination_select_item_tv2, item.getList().get(1).getName());
                holder.setText(R.id.combination_money_item_edt, item.getMoney());
                holder.setText(R.id.combination_count_item_tv, item.getCount());
            }

            @Override
            protected List<CombinationBean> initList() {
                Log.d("shangpeisheng", "bigSmallKei: " + bigSmallKei);
                Log.d("shangpeisheng", "discountBigSmall: " + discountBigSmall);
                return new ArrayList<>(Arrays.asList(new CombinationBean(new ArrayList<>(Arrays.asList(new SelectedItemBean(getString(R.string.big)), new SelectedItemBean(getString(R.string.small)))), discountBigSmall, oddsBigSmall, getString(R.string.big_small), "", "", bigSmallKei),
                        new CombinationBean(new ArrayList<>(Arrays.asList(new SelectedItemBean(getString(R.string.odd_lottery)), new SelectedItemBean(getString(R.string.even_lottery)))), discountOddEven, oddsOddEven, getString(R.string.even_odd), "", "", oddEvenKei),
                        new CombinationBean(new ArrayList<>(Arrays.asList(new SelectedItemBean(getString(R.string.small_even)), new SelectedItemBean(getString(R.string.small_odd)), new SelectedItemBean(getString(R.string.big_even)), new SelectedItemBean(getString(R.string.big_odd)))), discountCom, oddsCom, getString(R.string.zhuhe), "", "", comKei))
                );
            }

            @Override
            protected void clearListMoney() {
                for (int i = 0; i < getCount(); i++) {
                    getList().get(i).setCount("");
                    getList().get(i).setMoney("");
                }
                notifyDataSetChanged();
            }


            @Override
            protected void countTotal() {
                float totalMoney = 0f;
                float totalBet = 0f;
                for (CombinationBean bean : getList()) {
                    String count0 = bean.getCount();
                    String money0 = bean.getMoney();
                    if (!count0.equals("")) {
                        totalBet = totalBet + Float.valueOf(count0);
                        totalMoney = totalMoney + Float.valueOf(money0);
                    }
                }
                LotteryCountBean countBean = new LotteryCountBean(DecimalUtils.decimalFormat(totalBet, "0.00"), DecimalUtils.decimalFormat(totalMoney, "0.00"));
                iCountLotteryBet.updateTotal(countBean);
            }
        };
    }
}
