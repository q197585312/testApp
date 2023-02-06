package nanyang.com.dig88.Lottery4D.popWindow;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Lottery4D.Bean.BetDetailBean;
import nanyang.com.dig88.Lottery4D.Bean.BetResultBean;
import nanyang.com.dig88.Lottery4D.Bean.BetResultContentBean;
import nanyang.com.dig88.Lottery4D.Bean.Lottery4DStatusBean;
import nanyang.com.dig88.Lottery4D.Bean.SpecialBetDateBean;
import nanyang.com.dig88.Lottery4D.Bean.StatusBean;
import nanyang.com.dig88.Lottery4D.Bean.WeekDay;
import nanyang.com.dig88.Lottery4D.Lottery4DActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.Number;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/11/21.
 */

public abstract class PopBet extends BasePopupWindow {
    public int contentType = 1;
    @Bind(R.id.lv_bet_content)
    ListView lv_bet_content;
    @Bind(R.id.tv_sure)
    TextView tv_sure;
    @Bind(R.id.tv_cancel)
    TextView tv_cancel;
    @Bind(R.id.tv_total_amount)
    TextView tv_total_amount;
    @Bind(R.id.ll_bet_result)
    LinearLayout ll_bet_result;
    @Bind(R.id.lv_bet_result)
    ListView lv_bet_result;
    @Bind(R.id.tv_done)
    TextView tv_done;
    @Bind(R.id.ll_bet_content)
    LinearLayout ll_bet_content;
    @Bind(R.id.fl_title)
    FrameLayout fl_title;
    @Bind(R.id.ll_bet_result_title)
    LinearLayout ll_bet_result_title;
    @Bind(R.id.tv_big_total)
    TextView tv_big_total;
    @Bind(R.id.tv_small_total)
    TextView tv_small_total;
    @Bind(R.id.tv_total)
    TextView tv_total;
    @Bind(R.id.tv_bet_sure)
    TextView tv_bet_sure;
    @Bind(R.id.tv_title_abc)
    TextView tv_title_abc;
    @Bind(R.id.tv_title_a)
    TextView tv_title_a;
    @Bind(R.id.tv_a_total)
    TextView tv_a_total;
    @Bind(R.id.tv_abc_total)
    TextView tv_abc_total;
    @Bind(R.id.tv_a_total_title)
    TextView tv_a_total_title;
    @Bind(R.id.tv_abc_total_title)
    TextView tv_abc_total_title;
    Lottery4DActivity activity;
    String poolId;
    List<WeekDay> currentTypeWeekDayList;
    String betUrl = "http://app.info.dig88api.com/index.php?page=4d_lott_submitter";
    String get_bet;
    List<BetResultContentBean> betResultContentBeenList;
    QuickBaseAdapter<BetResultContentBean> betResultAdapter;
    QuickBaseAdapter<BetDetailBean> adapter;
    List<SpecialBetDateBean> specialBetDateBeanList;
    private List<BetDetailBean> beanList;
    public PopBet(Context context, View v, int width, int height) {
        super(context, v, width, height);
        activity = (Lottery4DActivity) context;
        initBetResultAdapter();
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_lottery_4d_bet;
    }

    private void initContent() {
        if (contentType == 1) {
            ll_bet_result.setVisibility(View.GONE);
            ll_bet_content.setVisibility(View.VISIBLE);
            fl_title.setBackgroundColor(0xffD9AF22);
            ll_bet_result_title.setVisibility(View.GONE);
            tv_bet_sure.setVisibility(View.VISIBLE);
        } else {
            ll_bet_result.setVisibility(View.VISIBLE);
            ll_bet_content.setVisibility(View.GONE);
            fl_title.setBackgroundColor(0xff138616);
            ll_bet_result_title.setVisibility(View.VISIBLE);
            tv_bet_sure.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    private void initBetResultAdapter() {
        betResultAdapter = new QuickBaseAdapter<BetResultContentBean>(context, R.layout.item_lottery_4d_bet_result, new ArrayList<BetResultContentBean>()) {
            @Override
            protected void convert(ViewHolder helper, BetResultContentBean item, int position) {
                TextView tv_number = helper.retrieveView(R.id.tv_number);
                TextView tv_pool = helper.retrieveView(R.id.tv_pool);
                TextView tv_bet_amount = helper.retrieveView(R.id.tv_bet_amount);
                TextView tv_fail = helper.retrieveView(R.id.tv_fail);
                int contentType = item.getContentType();
                if (contentType == 2) {
                    tv_fail.setVisibility(View.VISIBLE);
                } else {
                    tv_fail.setVisibility(View.GONE);
                }
                tv_number.setText(item.getNumber());
                tv_pool.setText(item.getPoolAndPeriod());
                tv_bet_amount.setText(item.getBetAmount());
            }
        };
        lv_bet_result.setAdapter(betResultAdapter);
    }

    private void submit() {
        activity.showBlockDialog();
        String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + activity.getUserInfoBean().getUser_id() + "&session_id=" + activity.getUserInfoBean().getSession_id() +
                "&from=Android-APP" + "&get_bet=" + get_bet;
        if (!activity.isAbcAndAType()) {
            param += "&id_provider=" + poolId;
        }
        HttpUtils.httpPost(betUrl, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                BetResultBean betResultBean = new Gson().fromJson(s, BetResultBean.class);
                ll_bet_content.setVisibility(View.GONE);
                ll_bet_result.setVisibility(View.VISIBLE);
                List<BetResultBean.DataBean.SuccessBean> successList = betResultBean.getData().getSuccess();
                List<BetResultBean.DataBean.FailureBean> failureList = betResultBean.getData().getFailure();
                betResultContentBeenList = new ArrayList<>();
                for (int i = 0; i < successList.size(); i++) {
                    int contentType = -1;
                    if (i == 0) {
                        contentType = 1;
                    }
                    BetResultBean.DataBean.SuccessBean successBean = successList.get(i);
                    String number = successBean.getNumber();
                    String pools = successBean.getPools();
                    String date = successBean.getDate();
                    String[] dateArr = date.split("/");
                    String newDate = dateArr[1] + "/" + dateArr[0];
                    String wk = "";
                    for (int k = 0; k < currentTypeWeekDayList.size(); k++) {
                        WeekDay weekDay = currentTypeWeekDayList.get(k);
                        String week = weekDay.getWeek();
                        String day = weekDay.getDay();
                        if (day.equals(newDate)) {
                            wk = week;
                        }
                    }
                    String poolAndPeriod = "(" + pools + ")" + date + "(" + wk + ")";
                    String betType = successBean.getBettype();
                    String betAmount = "";
                    if (betType.equals("108")) {
                        betAmount += context.getString(R.string.big) + "@";
                    } else if (betType.equals("109")) {
                        betAmount += context.getString(R.string.small) + "@";
                    } else if (betType.equals("110")) {
                        betAmount += "A@";
                    } else if (betType.equals("111")) {
                        betAmount += "ABC@";
                    }
                    BetResultContentBean betResultContentBean = new BetResultContentBean(number, poolAndPeriod, betAmount + StringUtils.floatDecimalFormat(successBean.getAmount(), "0.00").toString(), contentType);
                    betResultContentBeenList.add(betResultContentBean);
                }
                for (int i = 0; i < failureList.size(); i++) {
                    int contentType = -1;
                    if (i == 0) {
                        contentType = 2;
                    }
                    BetResultBean.DataBean.FailureBean failureBean = failureList.get(i);
                    String number = failureBean.getNumber();
                    String pools = failureBean.getPools();
                    String date = failureBean.getDate();
                    String[] dateArr = date.split("/");
                    String newDate = dateArr[1] + "/" + dateArr[0];
                    String wk = "";
                    for (int k = 0; k < currentTypeWeekDayList.size(); k++) {
                        WeekDay weekDay = currentTypeWeekDayList.get(k);
                        String week = weekDay.getWeek();
                        String day = weekDay.getDay();
                        if (day.equals(newDate)) {
                            wk = week;
                        }
                    }
                    String poolAndPeriod = "(" + pools + ")" + date + "(" + wk + ")";
                    String betType = failureBean.getBettype();
                    String betAmount = "";
                    if (betType.equals("108")) {
                        betAmount += context.getString(R.string.big) + "@";
                    } else if (betType.equals("109")) {
                        betAmount += context.getString(R.string.small) + "@";
                    } else if (betType.equals("110")) {
                        betAmount += "A@";
                    } else if (betType.equals("111")) {
                        betAmount += "ABC@";
                    }
                    BetResultContentBean betResultContentBean = new BetResultContentBean(number, poolAndPeriod, betAmount + StringUtils.floatDecimalFormat(failureBean.getAmount(), "0.00").toString(), contentType);
                    betResultContentBeenList.add(betResultContentBean);
                }
                betResultAdapter.setList(betResultContentBeenList);
                activity.dismissBlockDialog();
                List<Double> total = betResultBean.getData().getTotal();
                tv_big_total.setText(total.get(0) + "");
                tv_small_total.setText(total.get(1) + "");
                tv_a_total.setText(total.get(2) + "");
                tv_abc_total.setText(total.get(3) + "");
                tv_total.setText(total.get(4) + "");
                contentType = 2;
                initContent();
            }

            @Override
            public void onRequestFailed(String s) {
                Toast.makeText(context, "Bet Failed", Toast.LENGTH_SHORT).show();
                activity.dismissBlockDialog();
            }
        });
    }

    public void setData(String poolType, Map<String, String> poolMap, List<Lottery4DStatusBean> list, List<String> timeList, String totalAmount, List<WeekDay> currentTypeWeekDayList) {
        if (activity.isAbcAndAType()) {
            tv_title_a.setVisibility(View.VISIBLE);
            tv_title_abc.setVisibility(View.VISIBLE);
            tv_a_total.setVisibility(View.VISIBLE);
            tv_abc_total.setVisibility(View.VISIBLE);
            tv_a_total_title.setVisibility(View.VISIBLE);
            tv_abc_total_title.setVisibility(View.VISIBLE);
        } else {
            tv_title_a.setVisibility(View.GONE);
            tv_title_abc.setVisibility(View.GONE);
            tv_a_total.setVisibility(View.GONE);
            tv_abc_total.setVisibility(View.GONE);
            tv_a_total_title.setVisibility(View.GONE);
            tv_abc_total_title.setVisibility(View.GONE);
        }
        contentType = 1;
        initContent();
        StringBuffer sb = new StringBuffer();
        get_bet = "";
        beanList = new ArrayList<>();
        poolId = poolType;
        this.currentTypeWeekDayList = currentTypeWeekDayList;
        for (int i = 0; i < list.size(); i++) {
            Lottery4DStatusBean bean = list.get(i);
            String number = bean.getNumber();
            if (TextUtils.isEmpty(number)) {
                continue;
            }
            String betType = bean.getBetType();
            String timeType = bean.getBetTime();
            String bigBetAmount = bean.getBigBetAmount();
            String smallBetAmount = bean.getSmallBetAmount();
            String aBetAmount = bean.getaBetAmount();
            String abcBetAmount = bean.getAbcBetAmount();
            if (TextUtils.isEmpty(bigBetAmount)) {
                bigBetAmount = "0";
            }
            if (TextUtils.isEmpty(smallBetAmount)) {
                smallBetAmount = "0";
            }
            if (TextUtils.isEmpty(aBetAmount)) {
                aBetAmount = "0";
            }
            if (TextUtils.isEmpty(abcBetAmount)) {
                abcBetAmount = "0";
            }
            if (!TextUtils.isEmpty(timeType)) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(poolId, timeType, timeList);
                specialBetDateBeanList.add(specialBetDateBean);
            }
            if (betType.equals(Lottery4DActivity.NORMAL)) {
                for (int k = 0; k < specialBetDateBeanList.size(); k++) {
                    SpecialBetDateBean specialBetDateBean = specialBetDateBeanList.get(k);
                    String betPoolId = specialBetDateBean.getBetPoolId();
                    String betDate = specialBetDateBean.getBetDate();
                    List<String> dateList = specialBetDateBean.getDateList();
                    D:
                    for (int l = 0; l < dateList.size(); l++) {
                        String dateStr = dateList.get(l);
                        if (activity.isBetSingleDate()) {
                            if (!dateStr.contains(betDate)) {
                                continue;
                            }
                        }
                        String[] split1 = dateStr.split("#");
                        BetDetailBean betDetailBean = new BetDetailBean(poolMap.get(betPoolId), number, bigBetAmount, bigBetAmount, smallBetAmount, smallBetAmount, split1[1]);
                        betDetailBean.setBetAAmount(aBetAmount);
                        betDetailBean.setBetABCAmount(abcBetAmount);
                        betDetailBean.setShowAAmount(aBetAmount);
                        betDetailBean.setShowABCAmount(abcBetAmount);
                        beanList.add(betDetailBean);
                        String date = dateList.get(l);
                        String[] split = date.split("#");
                        String period = split[0];
                        if (!TextUtils.isEmpty(bigBetAmount) && !bigBetAmount.equals("0")) {
                            if (!TextUtils.isEmpty(sb.toString())) {
                                sb.append("^");
                            }
                            sb.append(number + "#" + bigBetAmount + "#" + "108" + "#" + betPoolId + "#" + period + "#" + "0" + "#" + "1");
                        }
                        if (!TextUtils.isEmpty(smallBetAmount) && !smallBetAmount.equals("0")) {
                            if (!TextUtils.isEmpty(sb.toString())) {
                                sb.append("^");
                            }
                            sb.append(number + "#" + smallBetAmount + "#" + "109" + "#" + betPoolId + "#" + period + "#" + "0" + "#" + "1");
                        }
                        if (!TextUtils.isEmpty(aBetAmount) && !aBetAmount.equals("0")) {
                            if (!TextUtils.isEmpty(sb.toString())) {
                                sb.append("^");
                            }
                            sb.append(number + "#" + aBetAmount + "#" + "110" + "#" + betPoolId + "#" + period + "#" + "0" + "#" + "1");
                        }
                        if (!TextUtils.isEmpty(abcBetAmount) && !abcBetAmount.equals("0")) {
                            if (!TextUtils.isEmpty(sb.toString())) {
                                sb.append("^");
                            }
                            sb.append(number + "#" + abcBetAmount + "#" + "111" + "#" + betPoolId + "#" + period + "#" + "0" + "#" + "1");
                        }
                        if (dateStr.contains(betDate)) {
                            break D;
                        }
                    }
                }
            } else if (betType.equals(Lottery4DActivity.BOX)) {
                List<String> allCombine = Number.Permutation(number);
                for (int n = 0; n < allCombine.size(); n++) {
                    String number1 = allCombine.get(n) + "";
                    for (int k = 0; k < specialBetDateBeanList.size(); k++) {
                        SpecialBetDateBean specialBetDateBean = specialBetDateBeanList.get(k);
                        String betPoolId = specialBetDateBean.getBetPoolId();
                        String betDate = specialBetDateBean.getBetDate();
                        List<String> dateList = specialBetDateBean.getDateList();
                        B:
                        for (int m = 0; m < dateList.size(); m++) {
                            String dateStr = dateList.get(m);
                            if (activity.isBetSingleDate()) {
                                if (!dateStr.contains(betDate)) {
                                    continue;
                                }
                            }
                            String[] split1 = dateStr.split("#");
                            BetDetailBean betDetailBean = new BetDetailBean(poolMap.get(betPoolId), number1, bigBetAmount, bigBetAmount, smallBetAmount, smallBetAmount, split1[1]);
                            betDetailBean.setBetAAmount(aBetAmount);
                            betDetailBean.setBetABCAmount(abcBetAmount);
                            betDetailBean.setShowAAmount(aBetAmount);
                            betDetailBean.setShowABCAmount(abcBetAmount);
                            beanList.add(betDetailBean);
                            String date = dateList.get(m);
                            String[] split = date.split("#");
                            String period = split[0];
                            if (!TextUtils.isEmpty(bigBetAmount) && !bigBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + bigBetAmount + "#" + "108" + "#" + betPoolId + "#" + period + "#" + "1" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(smallBetAmount) && !smallBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + smallBetAmount + "#" + "109" + "#" + betPoolId + "#" + period + "#" + "1" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(aBetAmount) && !aBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + aBetAmount + "#" + "110" + "#" + betPoolId + "#" + period + "#" + "1" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(abcBetAmount) && !abcBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + abcBetAmount + "#" + "111" + "#" + betPoolId + "#" + period + "#" + "1" + "#" + allCombine.size());
                            }
                            if (dateStr.contains(betDate)) {
                                break B;
                            }
                        }
                    }
                }
            } else if (betType.equals(Lottery4DActivity.IBOX)) {
                List<String> allCombine = Number.Permutation(number);
                String showBigAmount = StringUtils.floatDecimalFormat(Double.parseDouble(bigBetAmount) / allCombine.size(), "0.00").toString();
                String showSmallAmount = StringUtils.floatDecimalFormat(Double.parseDouble(smallBetAmount) / allCombine.size(), "0.00").toString();
                String showAAmount = StringUtils.floatDecimalFormat(Double.parseDouble(aBetAmount) / allCombine.size(), "0.00").toString();
                String showABCAmount = StringUtils.floatDecimalFormat(Double.parseDouble(abcBetAmount) / allCombine.size(), "0.00").toString();
                for (int n = 0; n < allCombine.size(); n++) {
                    String number1 = allCombine.get(n) + "";
                    for (int k = 0; k < specialBetDateBeanList.size(); k++) {
                        SpecialBetDateBean specialBetDateBean = specialBetDateBeanList.get(k);
                        String betPoolId = specialBetDateBean.getBetPoolId();
                        String betDate = specialBetDateBean.getBetDate();
                        List<String> dateList = specialBetDateBean.getDateList();
                        B:
                        for (int m = 0; m < dateList.size(); m++) {
                            String dateStr = dateList.get(m);
                            if (activity.isBetSingleDate()) {
                                if (!dateStr.contains(betDate)) {
                                    continue;
                                }
                            }
                            String[] split1 = dateStr.split("#");
                            BetDetailBean betDetailBean = new BetDetailBean(poolMap.get(betPoolId), number1, showBigAmount, bigBetAmount, showSmallAmount, smallBetAmount, split1[1]);
                            betDetailBean.setBetAAmount(aBetAmount);
                            betDetailBean.setBetABCAmount(abcBetAmount);
                            betDetailBean.setShowAAmount(showAAmount);
                            betDetailBean.setShowABCAmount(showABCAmount);
                            beanList.add(betDetailBean);
                            String date = dateList.get(m);
                            String[] split = date.split("#");
                            String period = split[0];
                            if (!TextUtils.isEmpty(bigBetAmount) && !bigBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + bigBetAmount + "#" + "108" + "#" + betPoolId + "#" + period + "#" + "2" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(smallBetAmount) && !smallBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + smallBetAmount + "#" + "109" + "#" + betPoolId + "#" + period + "#" + "2" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(aBetAmount) && !aBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + aBetAmount + "#" + "110" + "#" + betPoolId + "#" + period + "#" + "2" + "#" + allCombine.size());
                            }
                            if (!TextUtils.isEmpty(abcBetAmount) && !abcBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + abcBetAmount + "#" + "111" + "#" + betPoolId + "#" + period + "#" + "2" + "#" + allCombine.size());
                            }
                            if (dateStr.contains(betDate)) {
                                break B;
                            }
                        }
                    }
                }
            } else if (betType.equals(Lottery4DActivity.FR)) {
                List<String> frNumberList = getFrNumber(number);
                for (int k = 0; k < frNumberList.size(); k++) {
                    String number1 = frNumberList.get(k);
                    for (int l = 0; l < specialBetDateBeanList.size(); l++) {
                        SpecialBetDateBean specialBetDateBean = specialBetDateBeanList.get(l);
                        String betPoolId = specialBetDateBean.getBetPoolId();
                        String betDate = specialBetDateBean.getBetDate();
                        List<String> dateList = specialBetDateBean.getDateList();
                        B:
                        for (int m = 0; m < dateList.size(); m++) {
                            String dateStr = dateList.get(m);
                            if (activity.isBetSingleDate()) {
                                if (!dateStr.contains(betDate)) {
                                    continue;
                                }
                            }
                            String[] split1 = dateStr.split("#");
                            BetDetailBean betDetailBean = new BetDetailBean(poolMap.get(betPoolId), number1, bigBetAmount, bigBetAmount, smallBetAmount, smallBetAmount, split1[1]);
                            betDetailBean.setBetAAmount(aBetAmount);
                            betDetailBean.setBetABCAmount(abcBetAmount);
                            betDetailBean.setShowAAmount(aBetAmount);
                            betDetailBean.setShowABCAmount(abcBetAmount);
                            beanList.add(betDetailBean);
                            String date = dateList.get(m);
                            String[] split = date.split("#");
                            String period = split[0];
                            if (!TextUtils.isEmpty(bigBetAmount) && !bigBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + bigBetAmount + "#" + "108" + "#" + betPoolId + "#" + period + "#" + "3" + "#" + frNumberList.size());
                            }
                            if (!TextUtils.isEmpty(smallBetAmount) && !smallBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + smallBetAmount + "#" + "109" + "#" + betPoolId + "#" + period + "#" + "3" + "#" + frNumberList.size());
                            }
                            if (!TextUtils.isEmpty(aBetAmount) && !aBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + aBetAmount + "#" + "110" + "#" + betPoolId + "#" + period + "#" + "3" + "#" + frNumberList.size());
                            }
                            if (!TextUtils.isEmpty(abcBetAmount) && !abcBetAmount.equals("0")) {
                                if (!TextUtils.isEmpty(sb.toString())) {
                                    sb.append("^");
                                }
                                sb.append(number1 + "#" + abcBetAmount + "#" + "111" + "#" + betPoolId + "#" + period + "#" + "3" + "#" + frNumberList.size());
                            }
                            if (dateStr.contains(betDate)) {
                                break B;
                            }
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(timeType)) {
                specialBetDateBeanList.clear();
            }
        }
        get_bet = sb.toString();
        initAdapter(totalAmount);
    }

    private List<String> getFrNumber(String number) {
        List<String> list = new ArrayList<>();
        list.add(number);
        StringBuffer sb = new StringBuffer(number);
        String reverseNumber = sb.reverse().toString();
        list.add(reverseNumber);
        return list;
    }

    private void initAdapter(String totalAmount) {
        if (TextUtils.isEmpty(totalAmount)) {
            totalAmount = "0.00";
        }
        tv_total_amount.setText(totalAmount);
        if (adapter == null) {
            adapter = new QuickBaseAdapter<BetDetailBean>(context, R.layout.item_lottery_4d_bet_detail, beanList) {
                @Override
                protected void convert(ViewHolder helper, BetDetailBean item, int position) {
                    LinearLayout ll_content = helper.retrieveView(R.id.ll_content);
                    if (position % 2 == 0) {
                        ll_content.setBackgroundColor(0xff595959);
                    } else {
                        ll_content.setBackgroundColor(0xff363535);
                    }
                    TextView tv_position = helper.retrieveView(R.id.tv_position);
                    tv_position.setText((position + 1) + "");
                    TextView tv_pool = helper.retrieveView(R.id.tv_pool);
                    tv_pool.setText(item.getBetPool());
                    TextView tv_number = helper.retrieveView(R.id.tv_number);
                    tv_number.setText(item.getBetNumber());
                    TextView tv_big = helper.retrieveView(R.id.tv_big);
                    tv_big.setText(item.getShowBigAmount());
                    TextView tv_small = helper.retrieveView(R.id.tv_small);
                    tv_small.setText(item.getShowSmallAmount());
                    TextView tv_a = helper.retrieveView(R.id.tv_a);
                    TextView tv_abc = helper.retrieveView(R.id.tv_abc);
                    TextView tv_period = helper.retrieveView(R.id.tv_period);
                    tv_period.setText(item.getPeriod());
                    if (activity.isAbcAndAType()) {
                        tv_a.setVisibility(View.VISIBLE);
                        tv_abc.setVisibility(View.VISIBLE);
                        tv_a.setText(item.getShowAAmount());
                        tv_abc.setText(item.getShowABCAmount());
                    } else {
                        tv_a.setVisibility(View.GONE);
                        tv_abc.setVisibility(View.GONE);
                    }
                }
            };
            lv_bet_content.setAdapter(adapter);
        } else {
            adapter.setList(beanList);
        }
    }

    public abstract void onDone();

    public void setSpecialBetTime(List<SpecialBetDateBean> specialBetDateBeanList) {
        this.specialBetDateBeanList = specialBetDateBeanList;
    }
}
