package nanyang.com.dig88.Lottery4D;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Lottery4D.Bean.DateSpecialBean;
import nanyang.com.dig88.Lottery4D.Bean.GameSetBean;
import nanyang.com.dig88.Lottery4D.Bean.Lottery4DStatusBean;
import nanyang.com.dig88.Lottery4D.Bean.PeriodNumberBean;
import nanyang.com.dig88.Lottery4D.Bean.PoolBean;
import nanyang.com.dig88.Lottery4D.Bean.SpecialBetDateBean;
import nanyang.com.dig88.Lottery4D.Bean.StatusBean;
import nanyang.com.dig88.Lottery4D.Bean.WeekDay;
import nanyang.com.dig88.Lottery4D.popWindow.PopBet;
import nanyang.com.dig88.Lottery4D.popWindow.PopSpecialDateSwitch;
import nanyang.com.dig88.Lottery4D.popWindow.PopSwitchBetType;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.Number;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.allinone.util.ThreadPoolUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/11/16.
 */

public class BetFragment extends Lottery4DBaseFragment {
    @Bind(R.id.rg_choice_bet_type)
    RadioGroup rg_choice_bet_type;
    @Bind(R.id.rb_1)
    RadioButton rb_1;
    @Bind(R.id.rb_2)
    RadioButton rb_2;
    @Bind(R.id.rb_3)
    RadioButton rb_3;
    @Bind(R.id.rb_4)
    RadioButton rb_4;
    @Bind(R.id.rb_5)
    RadioButton rb_5;
    @Bind(R.id.rb_6)
    RadioButton rb_6;
    @Bind(R.id.rb_7)
    RadioButton rb_7;
    @Bind(R.id.rb_8)
    RadioButton rb_8;
    @Bind(R.id.ll_special_switch)
    LinearLayout ll_special_switch;
    @Bind(R.id.ll_special_type_1)
    LinearLayout ll_special_type_1;
    @Bind(R.id.ll_special_type_2)
    LinearLayout ll_special_type_2;
    @Bind(R.id.ll_special_type_3)
    LinearLayout ll_special_type_3;
    @Bind(R.id.ll_special_type_4)
    LinearLayout ll_special_type_4;
    @Bind(R.id.ll_special_type_5)
    LinearLayout ll_special_type_5;
    @Bind(R.id.ll_special_type_6)
    LinearLayout ll_special_type_6;
    @Bind(R.id.ll_special_type_7)
    LinearLayout ll_special_type_7;
    @Bind(R.id.ll_special_type_8)
    LinearLayout ll_special_type_8;
    @Bind(R.id.cb_special_type_1)
    CheckBox cb_special_type_1;
    @Bind(R.id.cb_special_type_2)
    CheckBox cb_special_type_2;
    @Bind(R.id.cb_special_type_3)
    CheckBox cb_special_type_3;
    @Bind(R.id.cb_special_type_4)
    CheckBox cb_special_type_4;
    @Bind(R.id.cb_special_type_5)
    CheckBox cb_special_type_5;
    @Bind(R.id.cb_special_type_6)
    CheckBox cb_special_type_6;
    @Bind(R.id.cb_special_type_7)
    CheckBox cb_special_type_7;
    @Bind(R.id.cb_special_type_8)
    CheckBox cb_special_type_8;
    @Bind(R.id.ll_normal)
    LinearLayout ll_normal;
    @Bind(R.id.ll_special)
    LinearLayout ll_special;
    @Bind(R.id.lv_bet)
    ListView lv_bet;
    @Bind(R.id.tv_amount)
    TextView tv_amount;
    QuickBaseAdapter<Lottery4DStatusBean> adapter;
    String currentYear;
    String specialDate214;
    String specialDate215;
    String specialDate216;
    String specialDate217;
    String specialDate218;
    String specialDate219;
    String specialDate220;
    String specialDate221;
    int specialCount214;
    int specialCount215;
    int specialCount216;
    int specialCount217;
    int specialCount218;
    int specialCount219;
    int specialCount220;
    int specialCount221;
    List<PoolBean> currentPoolSizeList;
    Calendar calendar;
    DateSpecialBean dateSpecialBean;
    UpdateDataRunnable updateDataRunnable;
    private List<String> betTypeList;
    private List<String> timeList;
    private List<String> peroidTimeList;
    private String gameSetUrl = "http://app.info.dig88api.com/index.php?page=game_set_submitter";
    private String gameStatusUrl = "http://app.info.dig88api.com/index.php?page=4d_status_submitter";
    private StatusBean statusBean;
    private GameSetBean gameSetBean;
    private String currentBetType;
    private PopBet popBet;
    private Map<String, String> poolMap;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    currentPoolSizeList = new ArrayList<>();
                    poolMap = new HashMap<>();
                    if (statusBean != null && statusBean.getPool_id() != null) {
                        List<String> poolIdList = statusBean.getPool_id();
                        StatusBean.PoolNameBean poolNameBean = statusBean.getPool_name();
                        for (int i = 0; i < poolIdList.size(); i++) {
                            String poolId = poolIdList.get(i);
                            String poolName = "";
                            switch (poolId) {
                                case "214":
                                    poolName = poolNameBean.get_$214();
                                    Lottery4DActivity.Magnum = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "215":
                                    poolName = poolNameBean.get_$215();
                                    Lottery4DActivity.Singapore = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "216":
                                    poolName = poolNameBean.get_$216();
                                    Lottery4DActivity.Toto4D = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "217":
                                    poolName = poolNameBean.get_$217();
                                    Lottery4DActivity.Damacai = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "218":
                                    poolName = poolNameBean.get_$218();
                                    Lottery4DActivity.Sabah = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "219":
                                    poolName = poolNameBean.get_$219();
                                    Lottery4DActivity.Sandakan = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "220":
                                    poolName = poolNameBean.get_$220();
                                    Lottery4DActivity.Sarawak = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                                case "221":
                                    poolName = poolNameBean.get_$221();
                                    Lottery4DActivity.GrandDragon = poolId;
                                    poolMap.put(poolId, poolName);
                                    break;
                            }
                            PoolBean poolBean = new PoolBean(poolId, poolName);
                            currentPoolSizeList.add(poolBean);
                        }
                        Lottery4DActivity activity = (Lottery4DActivity) getActivity();
                        activity.setPoolMap(poolMap);
                    }
                    currentBetType = Lottery4DActivity.Magnum;
                    initPool();
                    initGameContent();
                    break;
            }
        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_4d_bet;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Bangkok"));
        currentYear = DateUtils.getCurrentTime("yyyy");
        rg_choice_bet_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_1:
                        currentBetType = Lottery4DActivity.Magnum;
                        break;
                    case R.id.rb_2:
                        currentBetType = Lottery4DActivity.Singapore;
                        break;
                    case R.id.rb_3:
                        currentBetType = Lottery4DActivity.Toto4D;
                        break;
                    case R.id.rb_4:
                        currentBetType = Lottery4DActivity.Damacai;
                        break;
                    case R.id.rb_5:
                        currentBetType = Lottery4DActivity.Sabah;
                        break;
                    case R.id.rb_6:
                        currentBetType = Lottery4DActivity.Sandakan;
                        break;
                    case R.id.rb_7:
                        currentBetType = Lottery4DActivity.Sarawak;
                        break;
                    case R.id.rb_8:
                        currentBetType = Lottery4DActivity.GrandDragon;
                        break;
                }
                initGameContent();
            }
        });
        if (lottery4DActivity.isAbcAndAType()) {
            ll_normal.setVisibility(View.GONE);
            ll_special.setVisibility(View.VISIBLE);
            rg_choice_bet_type.setVisibility(View.GONE);
            ll_special_switch.setVisibility(View.VISIBLE);
            ll_special_type_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_1, "214");
                }
            });
            ll_special_type_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_2, "215");
                }
            });
            ll_special_type_3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_3, "216");
                }
            });
            ll_special_type_4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_4, "217");
                }
            });
            ll_special_type_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_5, "218");
                }
            });
            ll_special_type_6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_6, "219");
                }
            });
            ll_special_type_7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_7, "220");
                }
            });
            ll_special_type_8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showSpecialDatePop(cb_special_type_8, "221");
                }
            });
            betTypeList = Arrays.asList(Lottery4DActivity.NORMAL, Lottery4DActivity.FR, Lottery4DActivity.BOX, Lottery4DActivity.IBOX);
        } else {
            ll_normal.setVisibility(View.VISIBLE);
            ll_special.setVisibility(View.GONE);
            rg_choice_bet_type.setVisibility(View.VISIBLE);
            ll_special_switch.setVisibility(View.GONE);
            betTypeList = Arrays.asList(Lottery4DActivity.NORMAL, Lottery4DActivity.BOX, Lottery4DActivity.IBOX);
        }
        initAdapter();
        startUpdateData();
    }

    private int getTotalCount() {
        int total = specialCount214 + specialCount215 + specialCount216 + specialCount217 +
                specialCount218 + specialCount219 + specialCount220 + specialCount221;
        return total;
    }

    private void removeSpecialAllPool() {
        specialDate214 = "";
        specialCount214 = 0;
        specialDate215 = "";
        specialCount215 = 0;
        specialDate216 = "";
        specialCount216 = 0;
        specialDate217 = "";
        specialCount217 = 0;
        specialDate218 = "";
        specialCount218 = 0;
        specialDate219 = "";
        specialCount219 = 0;
        specialDate220 = "";
        specialCount220 = 0;
        specialDate221 = "";
        specialCount221 = 0;
        cb_special_type_1.setChecked(false);
        cb_special_type_2.setChecked(false);
        cb_special_type_3.setChecked(false);
        cb_special_type_4.setChecked(false);
        cb_special_type_5.setChecked(false);
        cb_special_type_6.setChecked(false);
        cb_special_type_7.setChecked(false);
        cb_special_type_8.setChecked(false);
    }

    private void showSpecialDatePop(CheckBox checkBox, final String type) {
        if (checkBox.isChecked()) {
            checkBox.setChecked(false);
            switch (type) {
                case "214":
                    specialDate214 = "";
                    specialCount214 = 0;
                    break;
                case "215":
                    specialDate215 = "";
                    specialCount215 = 0;
                    break;
                case "216":
                    specialDate216 = "";
                    specialCount216 = 0;
                    break;
                case "217":
                    specialDate217 = "";
                    specialCount217 = 0;
                    break;
                case "218":
                    specialDate218 = "";
                    specialCount218 = 0;
                    break;
                case "219":
                    specialDate219 = "";
                    specialCount219 = 0;
                    break;
                case "220":
                    specialDate220 = "";
                    specialCount220 = 0;
                    break;
                case "221":
                    specialDate221 = "";
                    specialCount221 = 0;
                    break;
            }
            adapter.operateListView();
        } else {
            checkBox.setChecked(true);
            PopSpecialDateSwitch popSpecialDateSwitch = new PopSpecialDateSwitch(mContext, ll_special_switch, screenWidth / 3 * 2, ViewGroup.LayoutParams.WRAP_CONTENT) {
                @Override
                protected void onCloose() {
                    switch (type) {
                        case "214":
                            specialDate214 = currentDate;
                            specialCount214 = currentCount;
                            break;
                        case "215":
                            specialDate215 = currentDate;
                            specialCount215 = currentCount;
                            break;
                        case "216":
                            specialDate216 = currentDate;
                            specialCount216 = currentCount;
                            break;
                        case "217":
                            specialDate217 = currentDate;
                            specialCount217 = currentCount;
                            break;
                        case "218":
                            specialDate218 = currentDate;
                            specialCount218 = currentCount;
                            break;
                        case "219":
                            specialDate219 = currentDate;
                            specialCount219 = currentCount;
                            break;
                        case "220":
                            specialDate220 = currentDate;
                            specialCount220 = currentCount;
                            break;
                        case "221":
                            specialDate221 = currentDate;
                            specialCount221 = currentCount;
                            break;
                    }
                    adapter.operateListView();
                }
            };
            popSpecialDateSwitch.setContent(getSpecialDate(type));
            popSpecialDateSwitch.showPopupCenterWindowBlack();
        }
    }

    private String getDayOfWeek(String year, String date) {
        String[] dateStr = date.split("/");
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, (Integer.parseInt(dateStr[1]) - 1));
        calendar.set(Calendar.DAY_OF_MONTH, (Integer.parseInt(dateStr[0])));
        int weekIndex = calendar.get(Calendar.DAY_OF_WEEK);
        String week = "";
        switch (weekIndex) {
            case 1:
                week = "Sun";
                break;
            case 2:
                week = "Mon";
                break;
            case 3:
                week = "Tues";
                break;
            case 4:
                week = "Wed";
                break;
            case 5:
                week = "Thur";
                break;
            case 6:
                week = "Fri";
                break;
            case 7:
                week = "Sat";
                break;
        }
        return week;
    }

    private List<String> getSpecialDate(String type) {
        switch (type) {
            case "214":
                return dateSpecialBean.getData().get_$214();
            case "215":
                return dateSpecialBean.getData().get_$215();
            case "216":
                return dateSpecialBean.getData().get_$216();
            case "217":
                return dateSpecialBean.getData().get_$217();
            case "218":
                return dateSpecialBean.getData().get_$218();
            case "219":
                return dateSpecialBean.getData().get_$219();
            case "220":
                return dateSpecialBean.getData().get_$220();
            case "221":
                return dateSpecialBean.getData().get_$221();
        }
        return null;
    }

    private void showPool(LinearLayout linearLayout, RadioButton radioButton) {
        if (lottery4DActivity.isAbcAndAType()) {
            linearLayout.setVisibility(View.VISIBLE);
        } else {
            radioButton.setVisibility(View.VISIBLE);
        }
    }

    private void initPool() {
        for (int i = 0; i < currentPoolSizeList.size(); i++) {
            PoolBean poolBean = currentPoolSizeList.get(i);
            String poolId = poolBean.getPoolId();
            switch (poolId) {
                case "214":
                    showPool(ll_special_type_1, rb_1);
                    break;
                case "215":
                    showPool(ll_special_type_2, rb_2);
                    break;
                case "216":
                    showPool(ll_special_type_3, rb_3);
                    break;
                case "217":
                    showPool(ll_special_type_4, rb_4);
                    break;
                case "218":
                    showPool(ll_special_type_5, rb_5);
                    break;
                case "219":
                    showPool(ll_special_type_6, rb_6);
                    break;
                case "220":
                    showPool(ll_special_type_7, rb_7);
                    break;
                case "221":
                    showPool(ll_special_type_8, rb_8);
                    break;
            }
        }
    }

    private void initGameContent() {
        showBlockDialog();
        currentTypeWeekDayList = new ArrayList<>();
        currentTypeWeekDayList.clear();
        timeList = new ArrayList<>();
        peroidTimeList = new ArrayList<>();
        peroidTimeList.clear();
        timeList.clear();
        String url = "http://app.info.dig88api.com/index.php?page=4d_lottery_period";
        String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + userId + "&session_id=" + sessionId;
        if (!lottery4DActivity.isAbcAndAType()) {
            param += "&pool_id=" + currentBetType;
        }
        HttpUtils.httpPost(url, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                if (lottery4DActivity.isAbcAndAType()) {
                    dateSpecialBean = gson.fromJson(s, DateSpecialBean.class);
                    for (int i = 0; i < dateSpecialBean.getData().get_$221().size(); i++) {
                        String periodNumber = dateSpecialBean.getData().get_$221().get(i);
                        String[] split = periodNumber.split("#");
                        WeekDay weekDay = new WeekDay();
                        weekDay.setDay(split[1]);
                        weekDay.setDayOfMonthOfYear(split[1] + "/" + currentYear);
                        weekDay.setWeek(getDayOfWeek(currentYear, split[1]));
                        currentTypeWeekDayList.add(weekDay);
                    }
                } else {
                    PeriodNumberBean periodNumberBean = gson.fromJson(s, PeriodNumberBean.class);
                    List<String> periodNumberList = periodNumberBean.getData();
                    for (int i = 0; i < periodNumberList.size(); i++) {
                        String periodNumber = periodNumberList.get(i);
                        peroidTimeList.add(periodNumber);
                        String[] split = periodNumber.split("#");
                        timeList.add(split[1]);
                        WeekDay weekDay = new WeekDay();
                        weekDay.setDay(split[1]);
                        weekDay.setDayOfMonthOfYear(split[1] + "/" + currentYear);
                        weekDay.setWeek(getDayOfWeek(currentYear, split[1]));
                        currentTypeWeekDayList.add(weekDay);
                    }
                }
                List<Lottery4DStatusBean> dataList = new ArrayList<>();
                List<GameSetBean.DataBean> data = gameSetBean.getData();
                for (int i = 0; i < 10; i++) {
                    Lottery4DStatusBean bean = new Lottery4DStatusBean();
                    for (int j = 0; j < data.size(); j++) {
                        GameSetBean.DataBean dataBean = data.get(j);
                        if (dataBean.getType2().equals(Lottery4DActivity.Magnum) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit214(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit214(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit214(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit214(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit214(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit214(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit214(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit214(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Singapore) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit215(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit215(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit215(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit215(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit215(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit215(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit215(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit215(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Toto4D) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit216(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit216(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit216(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit216(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit216(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit216(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit216(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit216(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Damacai) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit217(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit217(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit217(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit217(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit217(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit217(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit217(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit217(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Sabah) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit218(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit218(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit218(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit218(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit218(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit218(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit218(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit218(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Sandakan) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit219(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit219(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit219(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit219(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit219(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit219(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit219(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit219(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.Sarawak) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit220(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit220(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit220(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit220(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit220(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit220(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit220(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit220(Double.valueOf(dataBean.getMin_bet()));
                            }
                        } else if (dataBean.getType2().equals(Lottery4DActivity.GrandDragon) && dataBean.getType1().equals("20")) {
                            if (dataBean.getType3().equals("108")) {
                                bean.setBigMaxLimit221(Double.valueOf(dataBean.getMax_bet()));
                                bean.setBigMinLimit221(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("109")) {
                                bean.setSmallMaxLimit221(Double.valueOf(dataBean.getMax_bet()));
                                bean.setSmallMinLimit221(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("110")) {
                                bean.setaMaxLimit221(Double.valueOf(dataBean.getMax_bet()));
                                bean.setaMinLimit221(Double.valueOf(dataBean.getMin_bet()));
                            } else if (dataBean.getType3().equals("111")) {
                                bean.setAbcMaxLimit221(Double.valueOf(dataBean.getMax_bet()));
                                bean.setAbcMinLimit221(Double.valueOf(dataBean.getMin_bet()));
                            }
                        }
                        if (!lottery4DActivity.isAbcAndAType()) {
                            bean.setBetTime(timeList.get(0));
                        } else {
                            bean.setBetTime("");
                        }
                        bean.setBetType(betTypeList.get(0));
                    }
                    dataList.add(bean);
                }
                adapter.setList(dataList);
                dismissBlockDialog();
            }

            @Override
            public void onRequestFailed(String s) {
                dismissBlockDialog();
            }
        });
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<Lottery4DStatusBean>(mContext, R.layout.item_lottery_4d_bet, new ArrayList<Lottery4DStatusBean>()) {
            @Override
            public View getView(int position, View convertView, ViewGroup paren) {
                ViewHolder holder = new ViewHolder(mContext, paren, R.layout.item_lottery_4d_bet);
                convert(holder, getItem(position), position);
                return holder.getView();
            }

            @Override
            protected void convert(ViewHolder helper, Lottery4DStatusBean item, final int position) {
                LinearLayout ll_item = helper.retrieveView(R.id.ll_item);
                if (position % 2 == 0) {
                    ll_item.setBackgroundColor(0xff595959);
                } else {
                    ll_item.setBackgroundColor(0xff363535);
                }
                final TextView tv_bet_amount = helper.retrieveView(R.id.tv_bet_amount);
                tv_bet_amount.setText(item.getTotalBetAmount());
                TextView tv_bet_type = helper.retrieveView(R.id.tv_bet_type);
                tv_bet_type.setText(item.getBetType());
                TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                tv_bet_time.setText(item.getBetTime());
                tv_bet_type.measure(0, 0);
                tv_bet_time.measure(0, 0);
                int with = tv_bet_type.getMeasuredWidth() + tv_bet_type.getPaddingLeft() + tv_bet_type.getPaddingRight();
                if (lottery4DActivity.isAbcAndAType()) {
                    with = with * 2;
                }
                final PopSwitchBetType popBetType = new PopSwitchBetType(mContext, tv_bet_type, with, ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getData() {
                        return betTypeList;
                    }

                    @Override
                    public void onSetText(String text) {
                        getList().get(position).setBetType(text);
                        Lottery4DStatusBean bean = getList().get(position);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                };
                tv_bet_type.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popBetType.showPopupDownWindow();
                    }
                });
                final PopSwitchBetType popBetTime = new PopSwitchBetType(mContext, tv_bet_time, tv_bet_time.getMeasuredWidth() + tv_bet_time.getPaddingLeft() + tv_bet_time.getPaddingRight(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getData() {
                        return timeList;
                    }

                    @Override
                    public void onSetText(String text) {
                        getList().get(position).setBetTime(text);
                        Lottery4DStatusBean bean = getList().get(position);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                };
                tv_bet_time.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popBetTime.showPopupDownWindow();
                    }
                });
                EditText edt_bet_number = helper.retrieveView(R.id.edt_bet_number);
                edt_bet_number.setText(item.getNumber());
                edt_bet_number.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String number = s.toString();
                        List<Lottery4DStatusBean> list = getList();
                        Lottery4DStatusBean bean = list.get(position);
                        bean.setNumber(number);
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        } else {
                            bean.setTotalBetAmount("0.00");
                            tv_bet_amount.setText(bean.getTotalBetAmount());
                            double totalAmount = 0;
                            for (int i = 0; i < list.size(); i++) {
                                Lottery4DStatusBean bean1 = list.get(i);
                                String totalBetAmount1 = bean1.getTotalBetAmount();
                                double amount1 = Double.parseDouble(totalBetAmount1);
                                totalAmount += amount1;
                            }
                            setTotalBetAmount(StringUtils.floatDecimalFormat(Double.valueOf(totalAmount), "0.00").toString());
                        }
                    }
                });
                TextView tv_cancel = helper.retrieveView(R.id.tv_cancel);
                EditText edt_big_amount = helper.retrieveView(R.id.edt_big_amount);
                setEditTextKeyListener(edt_big_amount);
                edt_big_amount.setText(item.getBigBetAmount());
                edt_big_amount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String bigAmount = s.toString();
                        Lottery4DStatusBean bean = getList().get(position);
                        bean.setBigBetAmount(bigAmount);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                });
                EditText edt_small_amount = helper.retrieveView(R.id.edt_small_amount);
                setEditTextKeyListener(edt_small_amount);
                edt_small_amount.setText(item.getSmallBetAmount());
                edt_small_amount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String smallAmount = s.toString();
                        Lottery4DStatusBean bean = getList().get(position);
                        bean.setSmallBetAmount(smallAmount);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                });
                EditText edt_A_amount = helper.retrieveView(R.id.edt_A_amount);
                setEditTextKeyListener(edt_A_amount);
                edt_A_amount.setText(item.getaBetAmount());
                edt_A_amount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String aAmount = s.toString();
                        Lottery4DStatusBean bean = getList().get(position);
                        bean.setaBetAmount(aAmount);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                });
                EditText edt_ABC_amount = helper.retrieveView(R.id.edt_ABC_amount);
                setEditTextKeyListener(edt_ABC_amount);
                edt_ABC_amount.setText(item.getAbcBetAmount());
                edt_ABC_amount.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String abcAmount = s.toString();
                        Lottery4DStatusBean bean = getList().get(position);
                        bean.setAbcBetAmount(abcAmount);
                        String number = bean.getNumber();
                        if (!TextUtils.isEmpty(number) && number.length() == 4) {
                            countAmount(tv_bet_amount, position);
                        }
                    }
                });
                if (lottery4DActivity.isAbcAndAType()) {
                    edt_A_amount.setVisibility(View.VISIBLE);
                    edt_ABC_amount.setVisibility(View.VISIBLE);
                    tv_bet_time.setVisibility(View.GONE);
                } else {
                    edt_A_amount.setVisibility(View.GONE);
                    edt_ABC_amount.setVisibility(View.GONE);
                    tv_bet_time.setVisibility(View.VISIBLE);
                }
                tv_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resetData(tv_bet_amount, position);
                    }
                });

            }

            private void resetData(TextView tvTotal, int position) {
                Lottery4DStatusBean bean = adapter.getList().get(position);
                bean.setNumber("");
                bean.setBigBetAmount("");
                bean.setSmallBetAmount("");
                bean.setaBetAmount("");
                bean.setAbcBetAmount("");
                if (lottery4DActivity.isAbcAndAType()) {
                    bean.setBetTime("");
                } else {
                    bean.setBetTime(timeList.get(0));
                }
                bean.setBetType(betTypeList.get(0));
                bean.setTotalBetAmount("0.00");
                countAmount(tvTotal, position);
                notifyDataSetChanged();
            }

            public void countAmount(TextView tvTotal, int position) {
                List<Lottery4DStatusBean> list = getList();
                Lottery4DStatusBean bean = list.get(position);
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
                double amount = Double.valueOf(bigBetAmount) + Double.valueOf(smallBetAmount) + Double.valueOf(aBetAmount) + Double.valueOf(abcBetAmount);
                String number = bean.getNumber();
                String betType = bean.getBetType();
                String betTime = bean.getBetTime();
                int count = 0;
                if (!TextUtils.isEmpty(betTime)) {
                    boolean isAdd = false;
                    for (int i = timeList.size() - 1; i >= 0; i--) {
                        if (lottery4DActivity.isBetSingleDate()) {
                            if (!betTime.equals(timeList.get(i))) {
                                continue;
                            }
                        }
                        if (betTime.equals(timeList.get(i))) {
                            isAdd = true;
                        }
                        if (isAdd) {
                            count++;
                        }
                    }
                } else {
                    count = getTotalCount();
                }
                String totalBetAmount = "0.00";
                if (betType.equals(Lottery4DActivity.NORMAL)) {
                    totalBetAmount = StringUtils.floatDecimalFormat(Double.valueOf(amount * count), "0.00").toString();
                } else if (betType.equals(Lottery4DActivity.BOX)) {
                    List<Integer> allCombine = Number.getAllCombine(new int[]{Integer.parseInt(number)});
                    int size = allCombine.size();
                    if (number.startsWith("0")) {
                        List<String> permutation = Number.Permutation(number);
                        size = permutation.size();
                    }
                    totalBetAmount = StringUtils.floatDecimalFormat(Double.valueOf(size * count * amount), "0.00").toString();
                } else if (betType.equals(Lottery4DActivity.IBOX)) {
                    totalBetAmount = StringUtils.floatDecimalFormat(Double.valueOf(amount * count), "0.00").toString();
                } else if (betType.equals(Lottery4DActivity.FR)) {
                    totalBetAmount = StringUtils.floatDecimalFormat(Double.valueOf(amount * count * 2), "0.00").toString();
                }
                bean.setTotalBetAmount(totalBetAmount);
                tvTotal.setText(bean.getTotalBetAmount());
                double totalAmount = 0;
                for (int i = 0; i < list.size(); i++) {
                    Lottery4DStatusBean bean1 = list.get(i);
                    String totalBetAmount1 = bean1.getTotalBetAmount();
                    double amount1 = Double.parseDouble(totalBetAmount1);
                    totalAmount += amount1;
                }
                setTotalBetAmount(StringUtils.floatDecimalFormat(Double.valueOf(totalAmount), "0.00").toString());
            }

            @Override
            public void operateListView() {
                for (int i = 0; i < lv_bet.getCount(); i++) {
                    Lottery4DStatusBean bean = adapter.getList().get(i);
                    String number = bean.getNumber();
                    if (!TextUtils.isEmpty(number) && number.length() == 4) {
                        View childAt = lv_bet.getChildAt(i);
                        TextView tvTotalAmount = (TextView) childAt.findViewById(R.id.tv_bet_amount);
                        countAmount(tvTotalAmount, i);
                    }
                }
            }
        };
        lv_bet.setAdapter(adapter);
    }

    @OnClick({R.id.tv_sure, R.id.tv_cancel})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sure:
                submit();
                break;
            case R.id.tv_cancel:
                initGameContent();
                if (lottery4DActivity.isAbcAndAType()) {
                    removeSpecialAllPool();
                }
                break;
        }
    }

    private boolean verify(Lottery4DStatusBean bean, String poolId) {
        double bigMinLimit = 0;
        double bigMaxLimit = 0;
        double smallMinLimit = 0;
        double smallMaxLimit = 0;
        double aMinLimit = 0;
        double aMaxLimit = 0;
        double abcMinLimit = 0;
        double abcMaxLimit = 0;
        switch (poolId) {
            case "214":
                bigMinLimit = bean.getBigMinLimit214();
                bigMaxLimit = bean.getBigMaxLimit214();
                smallMinLimit = bean.getSmallMinLimit214();
                smallMaxLimit = bean.getSmallMaxLimit214();
                aMinLimit = bean.getaMinLimit214();
                aMaxLimit = bean.getaMaxLimit214();
                abcMinLimit = bean.getAbcMinLimit214();
                abcMaxLimit = bean.getAbcMaxLimit214();
                break;
            case "215":
                bigMinLimit = bean.getBigMinLimit215();
                bigMaxLimit = bean.getBigMaxLimit215();
                smallMinLimit = bean.getSmallMinLimit215();
                smallMaxLimit = bean.getSmallMaxLimit215();
                aMinLimit = bean.getaMinLimit215();
                aMaxLimit = bean.getaMaxLimit215();
                abcMinLimit = bean.getAbcMinLimit215();
                abcMaxLimit = bean.getAbcMaxLimit215();
                break;
            case "216":
                bigMinLimit = bean.getBigMinLimit216();
                bigMaxLimit = bean.getBigMaxLimit216();
                smallMinLimit = bean.getSmallMinLimit216();
                smallMaxLimit = bean.getSmallMaxLimit216();
                aMinLimit = bean.getaMinLimit216();
                aMaxLimit = bean.getaMaxLimit216();
                abcMinLimit = bean.getAbcMinLimit216();
                abcMaxLimit = bean.getAbcMaxLimit216();
                break;
            case "217":
                bigMinLimit = bean.getBigMinLimit217();
                bigMaxLimit = bean.getBigMaxLimit217();
                smallMinLimit = bean.getSmallMinLimit217();
                smallMaxLimit = bean.getSmallMaxLimit217();
                aMinLimit = bean.getaMinLimit217();
                aMaxLimit = bean.getaMaxLimit217();
                abcMinLimit = bean.getAbcMinLimit217();
                abcMaxLimit = bean.getAbcMaxLimit217();
                break;
            case "218":
                bigMinLimit = bean.getBigMinLimit218();
                bigMaxLimit = bean.getBigMaxLimit218();
                smallMinLimit = bean.getSmallMinLimit218();
                smallMaxLimit = bean.getSmallMaxLimit218();
                aMinLimit = bean.getaMinLimit218();
                aMaxLimit = bean.getaMaxLimit218();
                abcMinLimit = bean.getAbcMinLimit218();
                abcMaxLimit = bean.getAbcMaxLimit218();
                break;
            case "219":
                bigMinLimit = bean.getBigMinLimit219();
                bigMaxLimit = bean.getBigMaxLimit219();
                smallMinLimit = bean.getSmallMinLimit219();
                smallMaxLimit = bean.getSmallMaxLimit219();
                aMinLimit = bean.getaMinLimit219();
                aMaxLimit = bean.getaMaxLimit219();
                abcMinLimit = bean.getAbcMinLimit219();
                abcMaxLimit = bean.getAbcMaxLimit219();
                break;
            case "220":
                bigMinLimit = bean.getBigMinLimit220();
                bigMaxLimit = bean.getBigMaxLimit220();
                smallMinLimit = bean.getSmallMinLimit220();
                smallMaxLimit = bean.getSmallMaxLimit220();
                aMinLimit = bean.getaMinLimit220();
                aMaxLimit = bean.getaMaxLimit220();
                abcMinLimit = bean.getAbcMinLimit220();
                abcMaxLimit = bean.getAbcMaxLimit220();
                break;
            case "221":
                bigMinLimit = bean.getBigMinLimit221();
                bigMaxLimit = bean.getBigMaxLimit221();
                smallMinLimit = bean.getSmallMinLimit221();
                smallMaxLimit = bean.getSmallMaxLimit221();
                aMinLimit = bean.getaMinLimit221();
                aMaxLimit = bean.getaMaxLimit221();
                abcMinLimit = bean.getAbcMinLimit221();
                abcMaxLimit = bean.getAbcMaxLimit221();
                break;
        }
        String number = bean.getNumber();
        if (!TextUtils.isEmpty(number)) {
            if (number.length() < 4) {
                toastMakeText(getString(R.string.number_must_4));
                return false;
            }
            String bigBetAmount = bean.getBigBetAmount();
            String smallBetAmount = bean.getSmallBetAmount();
            String aBetAmount = bean.getaBetAmount();
            String abcBetAmount = bean.getAbcBetAmount();
            if (TextUtils.isEmpty(bigBetAmount) && TextUtils.isEmpty(smallBetAmount) && TextUtils.isEmpty(aBetAmount) && TextUtils.isEmpty(abcBetAmount)) {
                toastMakeText(getString(R.string.right_amount));
                return false;
            }
            double bigAmount = 0;
            if (!TextUtils.isEmpty(bigBetAmount)) {
                bigAmount = Double.parseDouble(bigBetAmount);
                if (bigAmount < bigMinLimit) {
                    toastMakeText(getString(R.string.betting_limit_less_than_nominal) + "[" + bigMinLimit + "-" + bigMaxLimit + "]");
                    return false;
                } else if (bigAmount > bigMaxLimit) {
                    toastMakeText(getString(R.string.betting_limit_more_than_nominal) + "[" + bigMinLimit + "-" + bigMaxLimit + "]");
                    return false;
                }
            }
            double smallAmount = 0;
            if (!TextUtils.isEmpty(smallBetAmount)) {
                smallAmount = Double.parseDouble(smallBetAmount);
                if (smallAmount < smallMinLimit) {
                    toastMakeText(getString(R.string.betting_limit_less_than_nominal) + "[" + smallMinLimit + "-" + smallMaxLimit + "]");
                    return false;
                } else if (smallAmount > smallMaxLimit) {
                    toastMakeText(getString(R.string.betting_limit_more_than_nominal) + "[" + smallMinLimit + "-" + smallMaxLimit + "]");
                    return false;
                }
            }
            double aAmount = 0;
            if (!TextUtils.isEmpty(aBetAmount)) {
                aAmount = Double.parseDouble(aBetAmount);
                if (aAmount < aMinLimit) {
                    toastMakeText(getString(R.string.betting_limit_less_than_nominal) + "[" + aMinLimit + "-" + aMaxLimit + "]");
                    return false;
                } else if (aAmount > aMaxLimit) {
                    toastMakeText(getString(R.string.betting_limit_more_than_nominal) + "[" + aMinLimit + "-" + aMaxLimit + "]");
                    return false;
                }
            }
            double abcAmount = 0;
            if (!TextUtils.isEmpty(abcBetAmount)) {
                abcAmount = Double.parseDouble(abcBetAmount);
                if (abcAmount < abcMinLimit) {
                    toastMakeText(getString(R.string.betting_limit_less_than_nominal) + "[" + abcMinLimit + "-" + abcMaxLimit + "]");
                    return false;
                } else if (abcAmount > abcMaxLimit) {
                    toastMakeText(getString(R.string.betting_limit_more_than_nominal) + "[" + abcMinLimit + "-" + abcMaxLimit + "]");
                    return false;
                }
            }
        }
        return true;
    }

    private void submit() {
        List<Lottery4DStatusBean> list = adapter.getList();
        for (int i = 0; i < list.size(); i++) {
            Lottery4DStatusBean bean = list.get(i);
            if (lottery4DActivity.isAbcAndAType()) {
                if (specialCount214 > 0) {
                    if (!verify(bean, Lottery4DActivity.Magnum)) {
                        return;
                    }
                }
                if (specialCount215 > 0) {
                    if (!verify(bean, Lottery4DActivity.Singapore)) {
                        return;
                    }
                }
                if (specialCount216 > 0) {
                    if (!verify(bean, Lottery4DActivity.Toto4D)) {
                        return;
                    }
                }
                if (specialCount217 > 0) {
                    if (!verify(bean, Lottery4DActivity.Damacai)) {
                        return;
                    }
                }
                if (specialCount218 > 0) {
                    if (!verify(bean, Lottery4DActivity.Sabah)) {
                        return;
                    }
                }
                if (specialCount219 > 0) {
                    if (!verify(bean, Lottery4DActivity.Sandakan)) {
                        return;
                    }
                }
                if (specialCount220 > 0) {
                    if (!verify(bean, Lottery4DActivity.Sarawak)) {
                        return;
                    }
                }
                if (specialCount221 > 0) {
                    if (!verify(bean, Lottery4DActivity.GrandDragon)) {
                        return;
                    }
                }
            } else {
                if (!verify(bean, currentBetType)) {
                    return;
                }
            }
        }
        if (popBet == null) {
            popBet = new PopBet(mContext, lv_bet, screenWidth / 6 * 5, screenHeight / 5 * 3) {
                @Override
                public void onDone() {
                    initGameContent();
                    removeSpecialAllPool();
                }

                @Override
                protected void onCloose() {
                    if (contentType == 2) {
                        onDone();
                        setTotalBetAmount("0.00");
                    }
                }
            };
        }
        String betTotal = tv_amount.getText().toString().trim();
        if (betTotal.equals("0.00")) {
            return;
        }
        UserInfoBean userInfo = getUserInfo();
        float balance = Float.parseFloat(userInfo.getMoneyBalance().getBalance());
        float betMoney = Float.parseFloat(betTotal);
        if (betMoney > balance) {
            toastMakeText(getString(R.string.not_enough_balance_please_deposit_first));
            return;
        }
        String betTime = list.get(0).getBetTime();
        if (TextUtils.isEmpty(betTime)) {
            List<SpecialBetDateBean> specialBetDateBeenList = new ArrayList<>();
            if (specialCount214 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Magnum, specialDate214, getSpecialDate(Lottery4DActivity.Magnum));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount215 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Singapore, specialDate215, getSpecialDate(Lottery4DActivity.Singapore));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount216 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Toto4D, specialDate216, getSpecialDate(Lottery4DActivity.Toto4D));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount217 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Damacai, specialDate217, getSpecialDate(Lottery4DActivity.Damacai));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount218 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Sabah, specialDate218, getSpecialDate(Lottery4DActivity.Sabah));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount219 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Sandakan, specialDate219, getSpecialDate(Lottery4DActivity.Sandakan));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount220 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.Sarawak, specialDate220, getSpecialDate(Lottery4DActivity.Sarawak));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            if (specialCount221 > 0) {
                SpecialBetDateBean specialBetDateBean = new SpecialBetDateBean(Lottery4DActivity.GrandDragon, specialDate221, getSpecialDate(Lottery4DActivity.GrandDragon));
                specialBetDateBeenList.add(specialBetDateBean);
            }
            popBet.setSpecialBetTime(specialBetDateBeenList);
        } else {
            popBet.setSpecialBetTime(new ArrayList<SpecialBetDateBean>());
        }
        popBet.setData(currentBetType, poolMap, list, peroidTimeList, tv_amount.getText().toString(), currentTypeWeekDayList);
        popBet.showPopupCenterWindowBlack();
    }

    private void startUpdateData() {
        updateDataRunnable = new UpdateDataRunnable();
        handler.post(updateDataRunnable);
    }

    private void stopUpdateData() {
        handler.removeCallbacks(updateDataRunnable);
        updateDataRunnable = null;
    }

    private void setTotalBetAmount(String amount) {
        tv_amount.setText(amount);
    }

    private void setEditTextKeyListener(EditText editText) {
        editText.setKeyListener(new DigitsKeyListener(false, true) {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                return numberChars;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopUpdateData();
    }

    private class UpdateDataRunnable implements Runnable {
        @Override
        public void run() {
            DataRunnable dataRunnable = new DataRunnable();
            ThreadPoolUtils.execute(dataRunnable);
//            handler.postDelayed(this, 20000);
        }
    }

    private class DataRunnable implements Runnable {
        @Override
        public void run() {
            try {
                String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + userId +
                        "&session_id=" + sessionId;
                String statusResult = httpClient.sendPost(gameStatusUrl, param);
                statusBean = gson.fromJson(statusResult, StatusBean.class);
                String gameSetResult = httpClient.sendPost(gameSetUrl, param);
                gameSetBean = gson.fromJson(gameSetResult, GameSetBean.class);
                handler.sendEmptyMessage(1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
