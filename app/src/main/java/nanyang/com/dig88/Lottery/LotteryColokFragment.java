package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.Lottery234BetBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/2/24.
 */
public class LotteryColokFragment extends LotteryBaseFragment<ColokBean> {
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.lottery_center_progress_ll)
    View progressView;
    private HashMap<Integer, Integer> selectedMap = new HashMap<>();
    private String period;
    private String gameName;

    @Override
    public void setProgressVisibility(boolean isVisibility) {
        super.setProgressVisibility(isVisibility);
        if(isVisibility){
            progressView.setVisibility(View.VISIBLE);
        }else{
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

    }

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList,LotteryStateGameBean selectedBean) {
        initGameState(oddsList, selectedBean);
    }
  /*  7：COLOK ANGKA
    8：COLOK MACAU
    9：COLOK NAGA
    10：COLOK JITU*/

    protected void initGameState(List<DigGameOddsBean> oddsList,LotteryStateGameBean bean) {
        period = bean.getPeriod();
        gameName = bean.getGame_name();
        List<LotteryLimitBean> limitBeanList = new ArrayList<>(Arrays.asList(new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean()));
        for (DigGameOddsBean oddsBean : oddsList) {
            if (oddsBean.getType3().equals("7")) {
                adapter.getList().get(0).setDiscount1(oddsBean.getDiscount());
                adapter.getList().get(0).setKei(oddsBean.getKei());
                adapter.getList().get(0).setOdds1(oddsBean.getFactor());
                adapter.getList().get(0).setOdds2(oddsBean.getFactor2());
                adapter.getList().get(0).setOdds3(oddsBean.getFactor3());
                adapter.getList().get(0).setOdds4(oddsBean.getFactor4());
                limitBeanList.set(0, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "Angka"));
            } else if (oddsBean.getType3().equals("8")) {
                adapter.getList().get(1).setDiscount1(oddsBean.getDiscount());
                adapter.getList().get(1).setOdds1(oddsBean.getFactor2());
                adapter.getList().get(1).setKei(oddsBean.getKei());
                limitBeanList.set(1, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "Macau"));
            } else if (oddsBean.getType3().equals("9")) {
                adapter.getList().get(1).setDiscount2(oddsBean.getDiscount());
                adapter.getList().get(1).setOdds2(oddsBean.getFactor3());
                adapter.getList().get(1).setKei(oddsBean.getKei());
                adapter.getList().get(1).setLimit2(new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "Naga"));
            } else if (oddsBean.getType3().equals("10")) {
                adapter.getList().get(2).setDiscount1(oddsBean.getDiscount());
                adapter.getList().get(2).setKei(oddsBean.getKei());
                adapter.getList().get(2).setOdds1(oddsBean.getFactor());
                limitBeanList.set(2, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), "Jitu"));
            }
        }
        adapter.setLimitList(limitBeanList);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected String getSubmitPageUrl() {
        return "colok_submitter";
    }

    /**
     * 下注类型：Angka#1#10^
     * 0~9 （Angka）
     * 12,13,14,15......21,23,24....... （Macau：下注任意两位数，号码的两位数字不可相同，  如：11,22...）
     * 112,113......122,123......（Naga：下注任意三位数，号码的三位数字不可相同，如：111,222,333...）
     * 01,02,03,04,11,12,13,14,21......（Jitu：第一位数字：买的号码，第二位数字：号码出现的位置）
     */
    @Override
    protected String constructorGetBetStr() {
        StringBuilder builder = new StringBuilder();
        int i = 0;
        List<String> submitStr = new ArrayList<>();
        for (ColokBean combinationBean : adapter.getList()) {
            int j = 0;
            for (Lottery234BetBean lottery234BetBean : adapter.getList().get(i).getBetList()) {
                LotteryLimitBean limit = adapter.getLimitList().get(i);
                if (i < 2) {

                    if (!lottery234BetBean.getCount0().equals("")) {
                        if (!submitStr.contains(limit.getTypeKey() + "#" + lottery234BetBean.getNumber0() + "#" + lottery234BetBean.getMoney0())) {
                            builder.append("^");
                            builder.append(limit.getTypeKey());
                            builder.append("#");
                            builder.append(lottery234BetBean.getNumber0());
                            builder.append("#");
                            builder.append(lottery234BetBean.getMoney0());
                            submitStr.add(limit.getTypeKey() + "#" + lottery234BetBean.getNumber0() + "#" + lottery234BetBean.getMoney0());
                        }
                    }
                    if (!lottery234BetBean.getCount1().equals("")) {
                        if (!submitStr.contains(limit.getTypeKey() + "#" + lottery234BetBean.getNumber1() + "#" + lottery234BetBean.getMoney1())) {
                            if (i == 1)
                                limit = adapter.getList().get(1).getLimit2();
                            builder.append("^");
                            builder.append(limit.getTypeKey());
                            builder.append("#");
                            builder.append(lottery234BetBean.getNumber1());
                            builder.append("#");
                            builder.append(lottery234BetBean.getMoney1());
                            submitStr.add(limit.getTypeKey() + "#" + lottery234BetBean.getNumber1() + "#" + lottery234BetBean.getMoney1());
                        }
                    }
                } else {
                    if (!lottery234BetBean.getCount0().equals("")) {
                        if (!submitStr.contains(limit.getTypeKey() + "#" + lottery234BetBean.getNumber0() + selectedMap.get(j) + "#" + lottery234BetBean.getMoney0())) {
                            builder.append("^");
                            builder.append(limit.getTypeKey());
                            builder.append("#");
                            builder.append(lottery234BetBean.getNumber0());
                            if (selectedMap.containsKey(j)){
                                builder.append(selectedMap.get(j));
                            }
                            builder.append("#");
                            builder.append(lottery234BetBean.getMoney0());
                            submitStr.add(limit.getTypeKey() + "#" + lottery234BetBean.getNumber0() + selectedMap.get(j) + "#" + lottery234BetBean.getMoney0());
                        }
                    }
                }
                j++;
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
    public LotteryBaseAdapter<ColokBean> getAdapter() {
        return new LotteryBaseAdapter<ColokBean>(mContext, R.layout.item_colok_lottery) {

            @Override
            protected void convert(ViewHolder holder, ColokBean item, final int position) {
                LinearLayout contentLl = holder.retrieveView(R.id.colok_content_ll);
                if (position == 0) {
                    holder.setVisible(R.id.colok_header_ll2, false);
                    holder.setText(R.id.colok_header_tv11, item.getColokTitle1());
                    holder.setText(R.id.colok_header_tv12, getString(R.string.discount) + ":" + item.getDiscount1()+"%");
                    holder.setText(R.id.colok_header_key_tv, "Kei:" + item.getKei());
                    holder.setText(R.id.colok_header_tv13, getString(R.string.odds) + "1:" + item.getOdds1());
                    holder.setText(R.id.colok_header_tv14, getString(R.string.odds) + "2:" + item.getOdds2());
                    holder.setText(R.id.colok_header_tv15, getString(R.string.odds) + "3:" + item.getOdds3());
                    holder.setText(R.id.colok_header_tv16, getString(R.string.odds) + "4:" + item.getOdds4());
                    int index = 0;
                    for (Lottery234BetBean lottery234BetBean : item.getBetList()) {
                        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_colok_angka_content_include, null);
                        bindData(position, lottery234BetBean, contentView, index);
                        contentLl.addView(contentView);
                        index++;
                    }
                } else if (position == 1) {
                    holder.setVisible(R.id.colok_header_ll2, true);
                    holder.setText(R.id.colok_header_tv11, item.getColokTitle1());
                    holder.setText(R.id.colok_header_tv12, getString(R.string.discount) + ":" + item.getDiscount1()+"%");
                    holder.setText(R.id.colok_header_tv13, getString(R.string.odds) + ":" + item.getOdds1());
                    holder.setText(R.id.colok_header_tv21, item.getColokTitle2());
                    holder.setText(R.id.colok_header_tv22, getString(R.string.discount) + ":" + item.getDiscount2() + "%");
                    holder.setText(R.id.colok_header_tv23, getString(R.string.odds) + ":" + item.getOdds2());
                    int index = 0;
                    for (Lottery234BetBean lottery234BetBean : item.getBetList()) {
                        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_colok_macau_nang_content_include, null);
                        bindData(position, lottery234BetBean, contentView, index);
                        contentLl.addView(contentView);
                        index++;
                    }
                } else if (position == 2) {
                    holder.setVisible(R.id.colok_header_ll2, false);
                    holder.setVisible(R.id.colok_header_location_tv, true);
                    holder.setVisible(R.id.colok_header_number_title_tv2, false);
                    holder.setVisible(R.id.colok_header_money_title_tv2, false);
                    holder.setVisible(R.id.colok_header_count_title_tv2, false);
                    holder.setText(R.id.colok_header_tv11, item.getColokTitle1());
                    holder.setText(R.id.colok_header_tv12, getString(R.string.discount) + ":" + item.getDiscount1()+"%");
                    holder.setText(R.id.colok_header_key_tv, "Kei:" + item.getKei());
                    holder.setText(R.id.colok_header_tv13, getString(R.string.odds) + ":" + item.getOdds1());
                    int index = 0;
                    for (Lottery234BetBean lottery234BetBean : item.getBetList()) {
                        View contentView = LayoutInflater.from(mContext).inflate(R.layout.item_colok_jitu_content_include, null);
                        bindData(position, lottery234BetBean, contentView, index);
                        contentLl.addView(contentView);
                        index++;
                    }
                }
            }

            private void bindData(int position, Lottery234BetBean lottery234BetBean, View contentView, int childIndex) {
                EditText edt2 = (EditText) contentView.findViewById(R.id.colok_content_edt2);
                TextView tv3 = (TextView) contentView.findViewById(R.id.colok_content_edt3);
                if (position == 0) {
                    TextView edt1 = (TextView) contentView.findViewById(R.id.colok_content_edt1);
                    edt1.setText(lottery234BetBean.getNumber0());
                    edt2.setText(lottery234BetBean.getMoney0());
                    tv3.setText(lottery234BetBean.getCount0());
                    TextView edt4 = (TextView) contentView.findViewById(R.id.colok_content_edt4);
                    edt4.setText(lottery234BetBean.getNumber1());
                    EditText edt5 = (EditText) contentView.findViewById(R.id.colok_content_edt5);
                    edt5.setText(lottery234BetBean.getMoney1());
                    TextView tv6 = (TextView) contentView.findViewById(R.id.colok_content_edt6);
                    tv6.setText(lottery234BetBean.getCount1());
                    setTextWatcher(edt2, position, tv3, childIndex, 1);
                    setTextWatcher(edt5, position, tv6, childIndex, 4);
                } else if (position == 1) {
                    EditText edt1 = (EditText) contentView.findViewById(R.id.colok_content_edt1);
                    edt1.setText(lottery234BetBean.getNumber0());
                    edt2.setText(lottery234BetBean.getMoney0());
                    tv3.setText(lottery234BetBean.getCount0());
                    EditText edt4 = (EditText) contentView.findViewById(R.id.colok_content_edt4);
                    edt4.setText(lottery234BetBean.getNumber1());
                    EditText edt5 = (EditText) contentView.findViewById(R.id.colok_content_edt5);
                    edt5.setText(lottery234BetBean.getMoney1());
                    TextView tv6 = (TextView) contentView.findViewById(R.id.colok_content_edt6);
                    tv6.setText(lottery234BetBean.getCount1());
                    setTextWatcher(edt1, position, tv3, childIndex, 0);
                    setTextWatcher(edt2, position, tv3, childIndex, 1);
                    setTextWatcher(edt4, position, tv6, childIndex, 3);
                    setTextWatcher(edt5, position, tv6, childIndex, 4);

                } else if (position == 2) {
                    EditText edt1 = (EditText) contentView.findViewById(R.id.colok_content_edt1);
                    edt1.setText(lottery234BetBean.getNumber0());
                    edt2.setText(lottery234BetBean.getMoney0());
                    tv3.setText(lottery234BetBean.getCount0());
                    setRadioListener(contentView, childIndex);
                    setTextWatcher(edt1, position, tv3, childIndex, 0);
                    setTextWatcher(edt2, position, tv3, childIndex, 1);
                }


            }

            private void setRadioListener(View contentView, final int childIndex) {
                RadioGroup rg = (RadioGroup) contentView.findViewById(R.id.colok_location_rg);
                if (selectedMap.containsKey(childIndex)) {
                    switch (selectedMap.get(childIndex)) {
                        case 1:
                            rg.check(R.id.colok_location_rb1);
                            break;
                        case 2:
                            rg.check(R.id.colok_location_rb2);
                            break;
                        case 3:
                            rg.check(R.id.colok_location_rb3);
                            break;
                        case 4:
                            rg.check(R.id.colok_location_rb4);
                            break;
                        default:
                            break;
                    }
                }
                rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    String count = "";
                    int selected = 0;

                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        LotteryLimitBean limit = getLimitList().get(2);
                        switch (checkedId) {
                            case R.id.colok_location_rb1:
                                selected = 1;
                                break;
                            case R.id.colok_location_rb2:
                                selected = 2;
                                break;
                            case R.id.colok_location_rb3:
                                selected = 3;
                                break;
                            case R.id.colok_location_rb4:
                                selected = 4;
                                break;
                            default:
                                break;
                        }
                        selectedMap.put(childIndex, selected);
                        if (limit != null && !getList().get(2).getBetList().get(childIndex).getMoney0().equals("") && !getList().get(2).getBetList().get(childIndex).getNumber0().equals(""))
                            count = countMoney(2, getList().get(2).getBetList().get(childIndex).getMoney0(), limit, childIndex, getList().get(2).getDiscount1(),getList().get(2).getKei());

                    }
                });
            }

            private void setTextWatcher(final EditText edt, final int position, final TextView countTv, final int childIndex, final int childId) {
                edt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String str=s.toString();
                        String change=str;
                        if(position==1&&childId!=1&&childId!=4){
                           if(str.length()>1){
                               StringBuilder b=new StringBuilder();
                              Map<String,Integer> diffMap= StringUtils.countDifferentCharMap(str);
                               Iterator<Map.Entry<String, Integer>> it = diffMap.entrySet().iterator();
                               while(it.hasNext()){
                                   Map.Entry<String, Integer> next = it.next();
                                   String  k=next.getKey();
                                   b.append(k);
                               }
                               change=b.toString();
                           }
                            if(!change.equals(str)) {
                                edt.setText(change);
                                edt.setSelection(change.length());
                            }
                        }
                        changeEdt(change, position, countTv, childIndex, childId);
                    }
                });

            }

            private void changeEdt(String s, int position, TextView tv, int childIndex, int childId) {
                String count = "";
                String kei=getList().get(position).getKei();
                LotteryLimitBean limit = getLimitList().get(position);
                String discount=getList().get(position).getDiscount1();
                if (position == 1 && childId > 2) {
                    limit = getList().get(1).getLimit2();
                    discount=getList().get(position).getDiscount2();
                }
                if (childId == 0) {
                    getList().get(position).getBetList().get(childIndex).setNumber0(s);
                } else if (childId == 1) {
                    getList().get(position).getBetList().get(childIndex).setMoney0(s);
                } else if (childId == 3) {
                    getList().get(position).getBetList().get(childIndex).setNumber1(s);
                } else if (childId == 4) {
                    getList().get(position).getBetList().get(childIndex).setMoney1(s);
                }
                if (childId < 2) {
                    if (limit != null && !getList().get(position).getBetList().get(childIndex).getMoney0().equals("") && !getList().get(position).getBetList().get(childIndex).getNumber0().equals("")) {
                        if(position==1&&getList().get(position).getBetList().get(childIndex).getNumber0().length()<2){
                            count="";
                        }else
                            count = countMoney(position, getList().get(position).getBetList().get(childIndex).getMoney0(), limit, childIndex, discount,kei);
                    }
                    getList().get(position).getBetList().get(childIndex).setCount0(count);

                } else {
                    if (limit != null && !getList().get(position).getBetList().get(childIndex).getMoney1().equals("") && !getList().get(position).getBetList().get(childIndex).getNumber1().equals("")) {
                        if(position==1&&getList().get(position).getBetList().get(childIndex).getNumber1().length()<3){
                            count="";
                        }else
                            count = countMoney(position, getList().get(position).getBetList().get(childIndex).getMoney1(), limit, childIndex, discount,kei);
                    }
                    getList().get(position).getBetList().get(childIndex).setCount1(count);
                }
                tv.setText(count);
                countTotal();
            }


            @Override
            protected List<ColokBean> initList() {
                return new ArrayList<>(Arrays.asList(new ColokBean(1, new ArrayList<Lottery234BetBean>(Arrays.asList(new Lottery234BetBean("0", "5", "", "", "", "","",""), new Lottery234BetBean("1", "6", "", "", "", "","",""), new Lottery234BetBean("2", "7", "", "", "", "","",""), new Lottery234BetBean("3", "8", "", "", "", "","",""), new Lottery234BetBean("4", "9", "", "", "", "","",""))), "COLOK ANGKA", "", "", "", "", "", "", "", "")
                        , new ColokBean(2, new ArrayList<Lottery234BetBean>(Arrays.asList(new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean())), "COLOK MACAU", "COLOK NAGA", "", "", "", "", "", "", "")
                        , new ColokBean(3, new ArrayList<Lottery234BetBean>(Arrays.asList(new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean(), new Lottery234BetBean())), "COLOK JITU", "", "", "", "", "", "", "", "")));
            }

            @Override
            protected void clearListMoney() {
                for(int i=0;i<getCount();i++){
                    for(int j=0;j<getList().get(i).getBetList().size();j++){
                        if(i==0) {
                            getList().get(i).getBetList().get(j).setMoney0("");
                            getList().get(i).getBetList().get(j).setMoney1("");
                            getList().get(i).getBetList().get(j).setCount0("");
                            getList().get(i).getBetList().get(j).setCount1("");
                        }
                        else {
                            getList().get(i).getBetList().get(j).setMoney0("");
                            getList().get(i).getBetList().get(j).setMoney1("");
                            getList().get(i).getBetList().get(j).setCount0("");
                            getList().get(i).getBetList().get(j).setCount1("");
                            getList().get(i).getBetList().get(j).setNumber0("");
                            getList().get(i).getBetList().get(j).setNumber1("");
                        }
                    }

                }
                notifyDataSetChanged();
            }


            @Override
            protected void countTotal() {
                float totalMoney = 0f;
                float totalBet = 0f;
                for (ColokBean bean : getList()) {
                    for (Lottery234BetBean lottery234BetBean : bean.getBetList()) {
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
                    LotteryCountBean countBean = new LotteryCountBean( DecimalUtils.decimalFormat(totalBet, "0.00"),  DecimalUtils.decimalFormat(totalMoney, "0.00"));

                    iCountLotteryBet.updateTotal(countBean);

                }
            }
        };
    }
}
