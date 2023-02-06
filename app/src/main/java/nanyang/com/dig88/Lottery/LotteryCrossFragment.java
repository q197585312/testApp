package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.DecimalUtils;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import xs.com.mylibrary.pulltorefresh.library.PullToRefreshListView;


/**
 * 2 3 4D
 *
 * @author Administrator
 */
public class LotteryCrossFragment extends LotteryBaseFragment<CrossBean> {


    @Bind(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;

    TextView lotteryDiscount2dTv;
    TextView lotteryDiscount3dTv;
    TextView lotteryDiscount4dTv;
    TextView lotteryOdds2dTv;
    TextView lotteryOdds3dTv;
    TextView lotteryOdds4dTv;
    @Bind(R.id.lottery_center_progress_ll)
    View progressView;
    private String discount2D;
    private String discount3D;
    private String discount4D;
    private String kei2D;
    private String kei3D;
    private String kei4D;
    private TextView lotteryHeaderTitleTv5;
    private TextView lotteryHeaderTitleTv4;
    private TextView lotteryHeaderTitleTv3;
    private TextView lotteryHeaderTitleTv2;

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
        listContentPtrlv.setAdapter(adapter);
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        addHeader();
        lotteryHeaderTitleTv5.setVisibility(View.GONE);
        lotteryHeaderTitleTv2.setText(getString(R.string.twodjine));
        lotteryHeaderTitleTv3.setText(getString(R.string.threedjine));
        lotteryHeaderTitleTv4.setText(getString(R.string.fourdjine));
    }

    private void addHeader() {
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_2d3d4d_lottery, null);
        lotteryDiscount2dTv = (TextView) header.findViewById(R.id.lottery_discount_2d_tv);
        lotteryDiscount3dTv = (TextView) header.findViewById(R.id.lottery_discount_3d_tv);
        lotteryDiscount4dTv = (TextView) header.findViewById(R.id.lottery_discount_4d_tv);
        lotteryOdds2dTv = (TextView) header.findViewById(R.id.lottery_odds_2d_tv);
        lotteryOdds3dTv = (TextView) header.findViewById(R.id.lottery_odds_3d_tv);
        lotteryOdds4dTv = (TextView) header.findViewById(R.id.lottery_odds_4d_tv);
        lotteryHeaderTitleTv5 = (TextView) header.findViewById(R.id.lottery_header_title_tv5);
        lotteryHeaderTitleTv4 = (TextView) header.findViewById(R.id.lottery_header_title_tv4);
        lotteryHeaderTitleTv3 = (TextView) header.findViewById(R.id.lottery_header_title_tv3);
        lotteryHeaderTitleTv2 = (TextView) header.findViewById(R.id.lottery_header_title_tv2);
        listContentPtrlv.getRefreshableView().addHeaderView(header);

    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean bean) {
        if (bean == null)
            return;
        String gameName = bean.getGame_name();
        String period = bean.getPeriod();
        List<LotteryLimitBean> limitBeanList = new ArrayList<>(Arrays.asList(new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean()));
        for (DigGameOddsBean oddsBean : oddsList) {
            if (oddsBean.getType3().equals("1")) {
                lotteryDiscount2dTv.setText(getString(R.string.discount2d) + ":" + oddsBean.getDiscount() + "%");
                lotteryOdds2dTv.setText(getString(R.string.odds2d) + ":" + oddsBean.getFactor());
                discount2D = oddsBean.getDiscount();
                kei2D = oddsBean.getKei();
                limitBeanList.set(0, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "2D"));
            } else if (oddsBean.getType3().equals("2")) {
                lotteryDiscount3dTv.setText("3D:" + oddsBean.getDiscount());
                lotteryOdds3dTv.setText("3D:" + oddsBean.getFactor());
                discount3D = oddsBean.getDiscount();
                kei3D = oddsBean.getKei();
                limitBeanList.set(1, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "3D"));
            } else if (oddsBean.getType3().equals("3")) {
                lotteryDiscount4dTv.setText("4D:" + oddsBean.getDiscount());
                lotteryOdds4dTv.setText("4D:" + oddsBean.getFactor());
                discount4D = oddsBean.getDiscount();
                kei4D = oddsBean.getKei();
                limitBeanList.set(2, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "4D"));
            }
        }

        adapter.setLimitList(limitBeanList);
    }

    @Override
    protected String getSubmitPageUrl() {
        return "multi_submitter";
    }

    //123#10#30#0^12#10#0#0
    @Override
    protected String constructorGetBetStr() {
        StringBuilder builder = new StringBuilder();
        List<String> submitStr = new ArrayList<>();
        for (CrossBean bean : adapter.getList()) {
            String money2d = "0";
            String money3d = "0";
            String money4d = "0";
            if (!bean.getMoney2d().equals(""))
                money2d = bean.getMoney2d();
            if (!bean.getMoney3d().equals(""))
                money3d = bean.getMoney3d();
            if (!bean.getMoney4d().equals(""))
                money4d = bean.getMoney4d();
            String number = bean.getNumber();
            if (number.length() > 1) {
                if (!submitStr.contains(number + "#" + money2d + "#" + money3d + "#" + money4d)) {
                    builder.append("^").append(number).append("#").append(money2d).append("#").append(money3d).append("#").append(money4d);
                    submitStr.add(number + "#" + money2d + "#" + money3d + "#" + money4d);
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
    public LotteryBaseAdapter<CrossBean> getAdapter() {
        return new LotteryBaseAdapter<CrossBean>(mContext, R.layout.item_cross_lottery) {
            public int selectionPosition = -1;
            Map<Integer, Integer> focusMap = new HashMap<>();

            @Override
            protected List<CrossBean> initList() {
                return new ArrayList<>(Arrays.asList(new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean(), new CrossBean()));
            }

            @Override
            protected void clearListMoney() {
                for (int i = 0; i < getCount(); i++) {
                    getList().get(i).setCount("");
                    getList().get(i).setMoney2d("");
                    getList().get(i).setMoney3d("");
                    getList().get(i).setMoney4d("");
                    getList().get(i).setNumber("");
                }
                notifyDataSetChanged();
            }

            @Override
            protected void convert(final ViewHolder holder, CrossBean item, final int position) {
                EditText numberEdt = holder.retrieveView(R.id.cross_number_item_edt);
                if (BuildConfig.FLAVOR.equals("villabetting")) {
                    InputFilter[] filters = {new InputFilter.LengthFilter(5)};
                    numberEdt.setFilters(filters);
                } else {
                    InputFilter[] filters = {new InputFilter.LengthFilter(4)};
                    numberEdt.setFilters(filters);
                }
                EditText money2dEdt = holder.retrieveView(R.id.cross_money2d_item_edt);
                EditText money3dEdt = holder.retrieveView(R.id.cross_money3d_item_edt);
                EditText money4dEdt = holder.retrieveView(R.id.cross_money4d_item_edt);
                TextView countTv = holder.retrieveView(R.id.cross_count_item_edt);
                setTextWatcher(numberEdt, position, holder, 0);
                setTextWatcher(money2dEdt, position, holder, 1);
                setTextWatcher(money3dEdt, position, holder, 2);
                setTextWatcher(money4dEdt, position, holder, 3);
                numberEdt.setText(item.getNumber());
                money2dEdt.setText(item.getMoney2d());
                money3dEdt.setText(item.getMoney3d());
                money4dEdt.setText(item.getMoney4d());
                countTv.setText(item.getCount());
                setEditEnable(item.getNumber(), holder, position);
            }

            private void setEditEnable(String number, ViewHolder holder, int position) {
                holder.setEnable(R.id.cross_money2d_item_edt, true);
                holder.setEnable(R.id.cross_money3d_item_edt, true);
                holder.setEnable(R.id.cross_money4d_item_edt, true);
                Map<String, Integer> difMap = StringUtils.countDifferentCharMap(number);
                if (number.length() == 1) {
                    getList().get(position).setMoney3d("");
                    getList().get(position).setMoney4d("");
                    getList().get(position).setMoney2d("");
                    holder.setText(R.id.cross_money3d_item_edt, "");
                    holder.setText(R.id.cross_money4d_item_edt, "");
                    holder.setText(R.id.cross_money2d_item_edt, "");
                } else if (number.length() == 1) {
                    holder.setEnable(R.id.cross_money3d_item_edt, false);
                    holder.setEnable(R.id.cross_money4d_item_edt, false);
                    getList().get(position).setMoney3d("");
                    getList().get(position).setMoney4d("");
                    getList().get(position).setMoney2d("");
                    holder.setText(R.id.cross_money3d_item_edt, "");
                    holder.setText(R.id.cross_money4d_item_edt, "");
                    holder.setText(R.id.cross_money2d_item_edt, "");
                } else if (number.length() == 2) {

                    if (difMap.size() == 2) {
                        getList().get(position).setMultiple2d(2);
                    } else {
                        getList().get(position).setMultiple2d(1);
                    }

                    holder.setEnable(R.id.cross_money3d_item_edt, false);
                    holder.setEnable(R.id.cross_money4d_item_edt, false);
                    getList().get(position).setMoney3d("");
                    getList().get(position).setMoney4d("");
                    holder.setText(R.id.cross_money3d_item_edt, "");
                    holder.setText(R.id.cross_money4d_item_edt, "");
                } else if (number.length() == 3) {
                    if (difMap.size() == 3) {
                        getList().get(position).setMultiple2d(6);
                        getList().get(position).setMultiple3d(6);
                    } else if (difMap.size() == 2) {
                        getList().get(position).setMultiple2d(3);
                        getList().get(position).setMultiple3d(3);
                    } else if (difMap.size() == 1) {
                        getList().get(position).setMultiple2d(1);
                        getList().get(position).setMultiple3d(1);

                    }
                    holder.setEnable(R.id.cross_money4d_item_edt, false);
                    getList().get(position).setMoney4d("");
                    holder.setText(R.id.cross_money4d_item_edt, "");
                }
                if (number.length() == 4) {
                    if (difMap.size() == 4) {
                        getList().get(position).setMultiple2d(12);
                        getList().get(position).setMultiple3d(24);
                        getList().get(position).setMultiple4d(24);
                    } else if (difMap.size() == 3) {
                        getList().get(position).setMultiple2d(7);
                        getList().get(position).setMultiple3d(12);
                        getList().get(position).setMultiple4d(12);
                    } else if (difMap.size() == 2) {
                        Iterator<Map.Entry<String, Integer>> it = difMap.entrySet().iterator();
                        int repetCount = 2;
                        while (it.hasNext()) {
                            repetCount = it.next().getValue();
                            break;
                        }
                        if (repetCount != 2) {
                            getList().get(position).setMultiple2d(3);
                            getList().get(position).setMultiple3d(4);
                            getList().get(position).setMultiple4d(4);
                        } else {
                            getList().get(position).setMultiple2d(4);
                            getList().get(position).setMultiple3d(6);
                            getList().get(position).setMultiple4d(6);
                        }

                    } else if (difMap.size() == 1) {
                        getList().get(position).setMultiple2d(1);
                        getList().get(position).setMultiple3d(1);
                        getList().get(position).setMultiple4d(1);

                    }
                } else if (number.length() == 5) {
                    if (difMap.size() == 5) {
                        getList().get(position).setMultiple2d(20);
                        getList().get(position).setMultiple3d(60);
                        getList().get(position).setMultiple4d(120);
                    } else if (difMap.size() == 4) {
                        getList().get(position).setMultiple2d(13);
                        getList().get(position).setMultiple3d(33);
                        getList().get(position).setMultiple4d(60);
                    } else if (difMap.size() == 3) {
                        Iterator<Map.Entry<String, Integer>> it = difMap.entrySet().iterator();
                        List<Integer> valueList = new ArrayList<>();
                        while (it.hasNext()) {
                            valueList.add(it.next().getValue());
                        }
                        int firstValue = valueList.get(0);
                        int nextValue = valueList.get(1);
                        int lastValue = valueList.get(2);
                        if (firstValue == 3 || nextValue == 3 || lastValue == 3) {
                            getList().get(position).setMultiple2d(7);
                            getList().get(position).setMultiple3d(13);
                            getList().get(position).setMultiple4d(20);
                        } else {
                            getList().get(position).setMultiple2d(8);
                            getList().get(position).setMultiple3d(18);
                            getList().get(position).setMultiple4d(30);
                        }
                    } else if (difMap.size() == 2) {
                        Iterator<Map.Entry<String, Integer>> it = difMap.entrySet().iterator();
                        List<Integer> valueList = new ArrayList<>();
                        while (it.hasNext()) {
                            valueList.add(it.next().getValue());
                        }
                        int firstValue = valueList.get(0);
                        int lastValue = valueList.get(1);
                        if ((firstValue == 2 && lastValue == 3) || (firstValue == 3 && lastValue == 2)) {
                            getList().get(position).setMultiple2d(4);
                            getList().get(position).setMultiple3d(7);
                            getList().get(position).setMultiple4d(10);
                        } else {
                            getList().get(position).setMultiple2d(3);
                            getList().get(position).setMultiple3d(4);
                            getList().get(position).setMultiple4d(5);
                        }
                    } else if (difMap.size() == 1) {
                        getList().get(position).setMultiple2d(1);
                        getList().get(position).setMultiple3d(1);
                        getList().get(position).setMultiple4d(1);

                    }
                }
            }

            private void setTextWatcher(EditText edt, final int position, final ViewHolder holder, final int positionId) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        changeEdt(s.toString(), position, holder, positionId);
                    }
                });
         /*       edt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        selectionPosition = position;
                        int x1=0;
                        if(type.equals("number")){
                            x1=1;
                        }
                        else{
                            x1=2;
                        }
                        int f=positionId*10+x1;
                        focusMap.put(selectionPosition, f);
                    }
                });*/
            }

            protected void changeEdt(String s, int position, ViewHolder holder, int positionId) {
                Log.d("afterTextChanged", "changeEdt: " + "posotion" + position + "----s=" + s.toString() + "---positionID" + positionId);
                String count = "";
                String count2d = "";
                String count3d = "";
                String count4d = "";
                if (positionId == 0) {
                    getList().get(position).setNumber(s);
                    setEditEnable(s, holder, position);
                } else if (positionId == 1) {
                    getList().get(position).setMoney2d(s);

                } else if (positionId == 2) {
                    getList().get(position).setMoney3d(s);
                } else if (positionId == 3) {
                    getList().get(position).setMoney4d(s);
                }
                String number = getList().get(position).getNumber();
                if (number.length() > 1) {
                    LotteryLimitBean limit2d = getLimitList().get(0);
                    count2d = countMoney(position, getList().get(position).getMoney2d(), limit2d, positionId, discount2D, kei2D);
                }
                if (number.length() > 2) {
                    LotteryLimitBean limit3d = getLimitList().get(1);
                    count3d = countMoney(position, getList().get(position).getMoney3d(), limit3d, positionId, discount3D, kei3D);
                }
                if (number.length() > 3) {
                    LotteryLimitBean limit4d = getLimitList().get(2);
                    count4d = countMoney(position, getList().get(position).getMoney4d(), limit4d, positionId, discount4D, kei4D);
                }
                double C2d = 0;
                if (!count2d.equals(""))
                    C2d = DecimalUtils.mul(Double.valueOf(count2d), getList().get(position).getMultiple2d());
                double C3d = 0;
                if (!count3d.equals(""))
                    C3d = DecimalUtils.mul(Double.valueOf(count3d), getList().get(position).getMultiple3d());
                double C4d = 0;
                if (!count4d.equals(""))
                    C4d = DecimalUtils.mul(Double.valueOf(count4d), getList().get(position).getMultiple4d());
                double countSingleTotal = DecimalUtils.addArray(C2d, C3d, C4d);
                if (countSingleTotal != 0)
                    count = DecimalUtils.decimalFormat(countSingleTotal, "0.000");
                getList().get(position).setCount(count);
                holder.setText(R.id.cross_count_item_edt, count);
                countTotal();

            }

            @Override
            protected void countTotal() {
                float totalMoney = 0f;
                float totalBet = 0f;
                for (CrossBean crossBean : getList()) {

                    if (!crossBean.getCount().equals("")) {
                        String money2d = "0";
                        String money3d = "0";
                        String money4d = "0";
                        String count = crossBean.getCount();
                        if (!crossBean.getMoney2d().equals("")) {
                            money2d = crossBean.getMoney2d();

                        }
                        if (!crossBean.getMoney3d().equals("")) {
                            money3d = crossBean.getMoney3d();
                        }
                        if (!crossBean.getMoney4d().equals("")) {
                            money4d = crossBean.getMoney4d();
                        }
                        totalMoney += Float.valueOf(money2d) * crossBean.getMultiple2d() + Float.valueOf(money3d) * crossBean.getMultiple3d() + Float.valueOf(money4d) * crossBean.getMultiple4d();
                        totalBet += Float.valueOf(count);

                    }
                }
                LotteryCountBean countBean = new LotteryCountBean(DecimalUtils.decimalFormat(totalBet, "0.00"), DecimalUtils.decimalFormat(totalMoney, "0.00"));
                iCountLotteryBet.updateTotal(countBean);
            }

        };
    }
}
