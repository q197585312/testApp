package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.DecimalUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshBase;
import gaming178.com.mylibrary.pulltorefresh.library.PullToRefreshListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryCountBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.Lunar;

/**
 * Created by Administrator on 2016/2/24.
 */
public class LotteryShioFragment extends LotteryBaseFragment<ZodiacBean> {
    public Map<String, String> zodiacBetType;
    @BindView(R.id.list_content_ptrlv)
    PullToRefreshListView listContentPtrlv;
    @BindView(R.id.lottery_center_progress_ll)
    View progressView;
    String[] currentZodiacBetType = new String[12];
    private String period;
    private String gameName;
    private String baseZodiacId[] = new String[]{
            "20","21", "22", "11", "12", "13", "14", "15", "16", "17", "18", "19"
    };

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
        zodiacBetType = new HashMap<>();
        zodiacBetType.put(getString(R.string.shu), "TIKUS");
        zodiacBetType.put(getString(R.string.niu), "KERBAU");
        zodiacBetType.put(getString(R.string.hu), "HARIMAU");
        zodiacBetType.put(getString(R.string.tu), "KELINCI");
        zodiacBetType.put(getString(R.string.lon), "NAGA");
        zodiacBetType.put(getString(R.string.she), "ULAR");
        zodiacBetType.put(getString(R.string.ma), "KUDA");
        zodiacBetType.put(getString(R.string.yang), "KAMBING");
        zodiacBetType.put(getString(R.string.hou), "MONYET");
        zodiacBetType.put(getString(R.string.ji), "AYAM");
        zodiacBetType.put(getString(R.string.gou), "ANJING");
        zodiacBetType.put(getString(R.string.zhu), "BABI");
        super.initData(savedInstanceState);
        initListContent();
    }

    private void initListContent() {
        listContentPtrlv.setMode(PullToRefreshBase.Mode.DISABLED);
        View header = LayoutInflater.from(mContext).inflate(R.layout.header_zodiac_lottery, null);
        listContentPtrlv.getRefreshableView().addHeaderView(header);
        listContentPtrlv.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        listContentPtrlv.setAdapter(adapter);
    }

    /*2016年顺序 猴 MONYET：21 羊 KAMBING:22  马 KUDA：11  蛇 ULAR：12  龙 NAGA：13  兔 KELINCI：14  虎 HARIMAU：15  牛 KERBAU：16  鼠 TIKUS：17  猪 BABI：18  狗 ANJING：19  鸡 AYAM：20 */
    //2017年顺序 猴 MONYET：22 羊 KAMBING:11  马 KUDA：12  蛇 ULAR：13  龙 NAGA：14  兔 KELINCI：15  虎 HARIMAU：16  牛 KERBAU：17  鼠 TIKUS：18  猪 BABI：19  狗 ANJING：20  鸡 AYAM：21

    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList, LotteryStateGameBean bean) {
        period = bean.getPeriod();
        gameName = bean.getGame_name();
        List<LotteryLimitBean> limitBeanList = new ArrayList<>(Arrays.asList(new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean(), new LotteryLimitBean()));
        for (int i = 0; i < baseZodiacId.length; i++) {
            A:
            for (DigGameOddsBean oddsBean : oddsList) {
                if (oddsBean.getType3().equals(baseZodiacId[i])) {
                    updateAdapterBetInfo(limitBeanList, oddsBean, i, currentZodiacBetType[i]);
                    break A;
                }
            }
        }
        adapter.setLimitList(limitBeanList);
        adapter.notifyDataSetChanged();
    }

    protected void updateAdapterBetInfo(List<LotteryLimitBean> limitBeanList, DigGameOddsBean oddsBean, int position, String type) {
        adapter.getList().get(position).setDiscount(oddsBean.getDiscount());
        adapter.getList().get(position).setOdds(oddsBean.getFactor());
        adapter.getList().get(position).setKei(oddsBean.getKei());
        limitBeanList.set(position, new LotteryLimitBean(gameName, period, oddsBean.getMax_bet(), oddsBean.getMin_bet(), oddsBean.getMax_total(), type));
    }


    @Override
    protected String getSubmitPageUrl() {
        return "shio_submitter";
    }

    @Override
    protected String constructorGetBetStr() {
        StringBuilder builder = new StringBuilder();
        List<String> submitStr = new ArrayList<>();
        int i = 0;
        for (ZodiacBean ZodiacBean : adapter.getList()) {
            String betype = adapter.getLimitList().get(i).getTypeKey();
            if (!submitStr.contains(betype + "#" + ZodiacBean.getMoney())) {
                builder.append("^");
                builder.append(betype);
                builder.append("#");
                builder.append(ZodiacBean.getMoney());
                submitStr.add(betype + "#" + ZodiacBean.getMoney());
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
    public LotteryBaseAdapter<ZodiacBean> getAdapter() {
        return new LotteryBaseAdapter<ZodiacBean>(mContext, R.layout.item_zodiac_lottery) {


            List<String> shioList = new ArrayList<>();
/*十二生肖 猴MONYET：21  羊 KAMBING:22  马 KUDA：11  蛇 ULAR：12  龙 NAGA：13  兔 KELINCI：14  虎 HARIMAU：15  牛 KERBAU：16  鼠 TIKUS：17  猪 BABI：18  狗 ANJING：19  鸡 AYAM：20 */
            String[] zodiacArr = new String[]{
                    getString(R.string.shu), getString(R.string.niu), getString(R.string.hu), getString(R.string.tu),
                    getString(R.string.lon), getString(R.string.she), getString(R.string.ma), getString(R.string.yang),
                    getString(R.string.hou), getString(R.string.ji), getString(R.string.gou), getString(R.string.zhu)
            };

            @Override
            protected void convert(ViewHolder holder, final ZodiacBean item, final int position) {
                holder.setText(R.id.zodiac_name_tv, item.getName());
                holder.setText(R.id.zodiac_discount_tv, item.getDiscount() + "%");
                holder.setText(R.id.zodiac_kei_tv, item.getKei());
                holder.setText(R.id.zodiac_odds_tv, item.getOdds());
                holder.setText(R.id.zodiac_count_tv, item.getCount());
                holder.setText(R.id.zodiac_money_edt, item.getMoney());
                final TextView countTv = holder.retrieveView(R.id.zodiac_count_tv);
                EditText moneyEdt = holder.retrieveView(R.id.zodiac_money_edt);
                moneyEdt.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String count = "";
                        getList().get(position).setMoney(s.toString());
                        count = countMoney(position, s.toString(), getLimitList().get(position), 4, getList().get(position).getDiscount(), item.getKei());
                        getList().get(position).setCount(count);
                        countTv.setText(count);
                        countTotal();
                    }
                });

            }

            @Override
            protected List<ZodiacBean> initList() {
                Calendar today = Calendar.getInstance();
                today.setTime(new java.util.Date());// 加载当前日期
                Lunar lunar = new Lunar(today, mContext);
                String currentShio = lunar.animalsYear();
                if (shioList.size() == 0) {
                    int currentIndex = -1;
                    for (int i = 0; i < zodiacArr.length; i++) {
                        if (currentShio.equals(zodiacArr[i])) {
                            currentIndex = i;
                        }
                    }
                    if (currentIndex != 0 && currentIndex != zodiacArr.length - 1) {
                        for (int i = currentIndex; i >= 0; i--) {
                            shioList.add(zodiacArr[i]);
                        }
                        for (int i = zodiacArr.length - 1; i > currentIndex; i--) {
                            shioList.add(zodiacArr[i]);
                        }
                    } else if (currentIndex == 0) {
                        shioList.add(zodiacArr[currentIndex]);
                        for (int i = zodiacArr.length - 1; i > 0; i--) {
                            shioList.add(zodiacArr[i]);
                        }
                    } else if (currentIndex == zodiacArr.length - 1) {
                        shioList.add(zodiacArr[currentIndex]);
                        for (int i = currentIndex - 1; i >= 0; i--) {
                            shioList.add(zodiacArr[i]);
                        }
                    }
                    for (int i = 0; i < shioList.size(); i++) {
                        currentZodiacBetType[i] = zodiacBetType.get(shioList.get(i));
                    }
                }
                return new ArrayList<>(Arrays.asList(new ZodiacBean(shioList.get(0), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(1), "12", "0", "10.12", "", ""),
                        new ZodiacBean(shioList.get(2), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(3), "12", "0", "10.12", "", ""),
                        new ZodiacBean(shioList.get(4), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(5), "12", "0", "10.12", "", ""),
                        new ZodiacBean(shioList.get(6), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(7), "12", "0", "10.12", "", ""),
                        new ZodiacBean(shioList.get(8), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(9), "12", "0", "10.12", "", ""),
                        new ZodiacBean(shioList.get(10), "12", "0", "10.12", "", ""), new ZodiacBean(shioList.get(11), "12", "0", "10.12", "", ""))
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
                for (ZodiacBean bean : getList()) {
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
