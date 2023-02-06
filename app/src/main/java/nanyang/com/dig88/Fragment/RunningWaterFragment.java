package nanyang.com.dig88.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.RunningWaterGameBean;
import nanyang.com.dig88.Entity.WashWaterBean;
import nanyang.com.dig88.Fragment.Presenter.RunningWaterPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.NestGridView;
import nanyang.com.dig88.Util.NestListView;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2017/10/24.
 */

public class RunningWaterFragment extends BaseFragment<RunningWaterPresenter> {
    @Bind(R.id.tv_start_time)
    TextView tvStartTime;
    @Bind(R.id.tv_end_time)
    TextView tvEndTime;
    @Bind(R.id.lv_result)
    NestListView lvResult;
    @Bind(R.id.gv_content)
    NestGridView gvContent;
    @Bind(R.id.cb_all)
    CheckBox cbAll;
    @Bind(R.id.cb_casino)
    CheckBox cbCasino;
    @Bind(R.id.cb_sports)
    CheckBox cbSports;
    @Bind(R.id.cb_slots)
    CheckBox cbSlots;
    @Bind(R.id.cb_lottery)
    CheckBox cbLottery;
    List<RunningWaterGameBean> gamesContentList;
    QuickBaseAdapter<WashWaterBean.DataBean> searchAdapter;
    LinkedHashMap<Integer, Boolean> gameSwitchStatusMap;
    CalendarPopuWindow startTimePop;
    CalendarPopuWindow endTimePop;
    private QuickBaseAdapter<RunningWaterGameBean> gameContentAdapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_runingwater;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new RunningWaterPresenter(this));
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            getAct().setTitle(getString(R.string.check_wash_code1));
        } else {
            getAct().setTitle(getString(R.string.check_wash_code));
        }
        gamesContentList = presenter.getContentData();
        initListData();
        initCheckBoxListener();
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            cbSlots.setVisibility(View.GONE);
            cbLottery.setVisibility(View.GONE);
        } else if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            cbLottery.setVisibility(View.GONE);
        }
        tvStartTime.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 00:00");
        tvEndTime.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 23:59");
    }

    public void removeCheckBoxListener() {
        cbAll.setOnCheckedChangeListener(null);
        cbCasino.setOnCheckedChangeListener(null);
        cbSports.setOnCheckedChangeListener(null);
        cbSlots.setOnCheckedChangeListener(null);
        cbLottery.setOnCheckedChangeListener(null);
    }

    private void initCheckBoxListener() {
        cbAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectAll(isChecked);
            }
        });
        cbCasino.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectGame(1, isChecked, cbCasino);
            }
        });
        cbSports.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectGame(2, isChecked, cbSports);
            }
        });
        cbSlots.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectGame(3, isChecked, cbSlots);
            }
        });
        cbLottery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                selectGame(4, isChecked, cbLottery);
            }
        });
    }

    @OnClick({R.id.tv_end_time, R.id.tv_start_time, R.id.btn_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_start_time:
                startTimePop = new CalendarPopuWindow(mContext, v, screenWidth / 10 * 9, screenWidth / 10 * 9) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tvStartTime.setText(date);
                    }
                };
                String s = tvStartTime.getText().toString();
                String[] split = s.split(" ");
                startTimePop.setYearMonthDay(split[0]);
                startTimePop.setHourMinute(split[1]);
                startTimePop.showPopupCenterWindowBlack();
                break;
            case R.id.tv_end_time:
                endTimePop = new CalendarPopuWindow(mContext, v, screenWidth / 10 * 9, screenWidth / 10 * 9) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tvEndTime.setText(date);
                    }
                };
                String s1 = tvEndTime.getText().toString();
                String[] split1 = s1.split(" ");
                endTimePop.setYearMonthDay(split1[0]);
                endTimePop.setHourMinute(split1[1]);
                endTimePop.showPopupCenterWindowBlack();
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    private void initSearchListView(List<WashWaterBean.DataBean> list) {
        if (searchAdapter == null) {
            searchAdapter = new QuickBaseAdapter<WashWaterBean.DataBean>(mContext, R.layout.item_runingwater, list) {
                @Override
                protected void convert(ViewHolder helper, WashWaterBean.DataBean item, int position) {
                    TextView tv_id = helper.retrieveView(R.id.tv_id);
                    tv_id.setText(position + 1 + "");
                    TextView tv_start_time = helper.retrieveView(R.id.tv_start_time);
                    tv_start_time.setText(getDateParam(1).replace(" ", "\n"));
                    TextView tv_end_time = helper.retrieveView(R.id.tv_end_time);
                    tv_end_time.setText(getDateParam(2).replace(" ", "\n"));
                    TextView tv_provider = helper.retrieveView(R.id.tv_provider);
                    String localLanguage = getLocalLanguage();
                    if (localLanguage.equals("zh") || BuildConfig.FLAVOR.equals("ibet567")|| BuildConfig.FLAVOR.equals("henbet")) {
                        tv_provider.setText(item.getP_cn_name());
                    } else {
                        tv_provider.setText(item.getP_name());
                    }
                    TextView tv_washcode = helper.retrieveView(R.id.tv_washcode);
                    String totalAmount = item.getTotal_amount();
                    if (TextUtils.isEmpty(totalAmount)) {
                        totalAmount = "0";
                    }
                    tv_washcode.setText(StringUtils.floatDecimalFormat(Double.parseDouble(totalAmount), "0.00").toString());
                    if (position == getCount() - 1) {
                        tv_id.setVisibility(View.INVISIBLE);
                        tv_start_time.setVisibility(View.INVISIBLE);
                        tv_end_time.setVisibility(View.INVISIBLE);
                    } else {
                        tv_id.setVisibility(View.VISIBLE);
                        tv_start_time.setVisibility(View.VISIBLE);
                        tv_end_time.setVisibility(View.VISIBLE);
                    }
                }
            };
            lvResult.setAdapter(searchAdapter);
        } else {
            searchAdapter.setList(list);
        }
    }

    private void submit() {
        String startDate = getDateParam(1);
        String endData = getDateParam(2);
        if (TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endData)) {
            ToastUtils.showShort(getString(R.string.Please_select_time));
            return;
        }
        if (startDate.equals(getString(R.string.start_time)) || endData.equals(getString(R.string.end_time)) || startDate.equals(" ") || endData.equals(" ")) {
            ToastUtils.showShort(getString(R.string.Please_select_time));
            return;
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("game", getGameParam());
        p.put("date_start", startDate);
        p.put("date_end", endData);
        presenter.getSearchData(p);
    }

    public void onGetSearchResult(WashWaterBean bean) {
        if (cbAll.isChecked()) {
            cbAll.setChecked(false);
        } else {
            selectAll(false);
        }
        List<WashWaterBean.DataBean> data = bean.getData();
        if (bean.getData() != null) {
            WashWaterBean.DataBean b = new WashWaterBean.DataBean();
            b.setP_name(getString(R.string.total));
            b.setTotal_amount(bean.getTotal() + "");
            data.add(b);
            initSearchListView(data);
        }
    }

    private String getDateParam(int type) {
        if (type == 1) {
            return tvStartTime.getText().toString();
        } else {
            return tvEndTime.getText().toString();
        }
    }

    private void initListData() {
        gameSwitchStatusMap = new LinkedHashMap<>();
        for (int i = 0; i < gamesContentList.size(); i++) {
            RunningWaterGameBean runningWaterGameBean = gamesContentList.get(i);
            int value = runningWaterGameBean.getValue();
            gameSwitchStatusMap.put(value, false);
        }
        gameContentAdapter = new QuickBaseAdapter<RunningWaterGameBean>(mContext, R.layout.item_runingwater_games, gamesContentList) {

            @Override
            protected void convert(ViewHolder helper, final RunningWaterGameBean item, int position) {
                TextView textView = helper.getTextView(R.id.game_title);
                LinearLayout ll_parent = helper.retrieveView(R.id.runingwater_game_ll);
                boolean isSelect = gameSwitchStatusMap.get(item.getValue());
                if (isSelect) {
                    textView.setTextColor(Color.WHITE);
                    ll_parent.setBackgroundResource(R.drawable.home_login_bg);
                } else {
                    textView.setTextColor(Color.BLACK);
                    ll_parent.setBackgroundResource(0);
                }
                ll_parent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int value = item.getValue();
                        boolean status = gameSwitchStatusMap.get(value);
                        boolean statusNew = !status;
                        gameSwitchStatusMap.put(value, statusNew);
                        TextView textView = v.findViewById(R.id.game_title);
                        LinearLayout ll_parent = v.findViewById(R.id.runingwater_game_ll);
                        if (statusNew) {
                            textView.setTextColor(Color.WHITE);
                            ll_parent.setBackgroundResource(R.drawable.home_login_bg);
                        } else {
                            textView.setTextColor(Color.BLACK);
                            ll_parent.setBackgroundResource(0);
                        }
                    }
                });
                int gameNumber = position + 1;
                String gameNumberStr;
                if (gameNumber < 10) {
                    gameNumberStr = "0" + gameNumber;
                } else {
                    gameNumberStr = gameNumber + "";
                }
                textView.setText(gameNumberStr + "." + item.getTitle());
            }
        };
        gvContent.setAdapter(gameContentAdapter);
    }

    public void selectAll(boolean b) {
        if (b) {
            removeCheckBoxListener();
        }
        cbCasino.setChecked(false);
        cbSports.setChecked(false);
        cbSlots.setChecked(false);
        cbLottery.setChecked(false);
        if (b) {
            cbAll.setChecked(true);
            initCheckBoxListener();
        }
        Iterator<Map.Entry<Integer, Boolean>> entries = gameSwitchStatusMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Boolean> entry = entries.next();
            int gameValue = entry.getKey();
            gameSwitchStatusMap.put(gameValue, b);
        }
        gameContentAdapter.notifyDataSetChanged();
    }

    public void selectGame(int gameType, boolean b, CheckBox box) {
        if (cbAll.isChecked()) {
            selectAll(false);
            removeCheckBoxListener();
            cbAll.setChecked(false);
            box.setChecked(true);
            initCheckBoxListener();
        }
        for (int i = 0; i < gamesContentList.size(); i++) {
            RunningWaterGameBean bean = gamesContentList.get(i);
            int type = bean.getType();
            int value = bean.getValue();
            if (type == gameType) {
                gameSwitchStatusMap.put(value, b);
            }
        }
        gameContentAdapter.notifyDataSetChanged();
    }

    public String getGameParam() {
        String str = "";
        Iterator<Map.Entry<Integer, Boolean>> entries = gameSwitchStatusMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Integer, Boolean> entry = entries.next();
            boolean status = entry.getValue();
            int gameValue = entry.getKey();
            if (status) {
                str += gameValue + "#";
            }
        }
        return str.length() > 0 ? str.substring(0, str.length() - 1) : str;
    }
}
