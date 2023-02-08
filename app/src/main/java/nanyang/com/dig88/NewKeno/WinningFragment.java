package nanyang.com.dig88.NewKeno;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.LoadMoreListView;
import nanyang.com.dig88.Util.MyListviewPopu;
import nanyang.com.dig88.Util.RefreshAndLoadMoreView;
import nanyang.com.dig88.Util.WebSiteUrl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018/9/13.
 */

public class WinningFragment extends NewKenoBaseFragment {
    @BindView(R.id.mRefreshAndLoadMoreView)
    RefreshAndLoadMoreView mRefreshAndLoadMoreView;
    @BindView(R.id.mLoadMoreListView)
    LoadMoreListView mLoadMoreListView;
    @BindView(R.id.tv_from_date)
    TextView tv_from_date;
    @BindView(R.id.tv_to_date)
    TextView tv_to_date;
    @BindView(R.id.tv_win_type)
    TextView tv_win_type;
    @BindView(R.id.tv_rule)
    TextView tv_rule;
    String rule;
    String winningUrl = "http://kgweb.keno6.com/api/api.php?page=keno_new_win_report_submitter";
    String fromDate;
    String toDate;
    int postType;
    String selectType;
    LoginInfoBean s;
    private int page = 1;
    private Map<Integer, Boolean> stateMap;
    private String dateParam;
    private CalendarPopuWindow calendarPopuWindow;
    private BaseContentListPopWindow rulePop;
    private List<ContentInfoBean> ruleList;
    private List<NewKenoWinningReportBean.DataBean.ResultBean> listData = new ArrayList<>();
    private QuickBaseAdapter<NewKenoWinningReportBean.DataBean.ResultBean> adapter;
    private int dateType = -1;
    private String[] switchType = new String[]{
            "All",
            "Over", "Under", "Odd", "Even", "OverOdd", "OverEven", "UnderOdd", "UnderEven",
            "210-695", "696-763", "764-855", "856-923", "924-1410",
            "Min1-2", "Min3-5", "Min>=6", "Max<=75", "Max76-78", "Max79-80",
            "[1-10]1", "[1-10]2-3", "[1-10]4+", "[1-10]0",
            "[11-20]1", "[11-20]2-3", "[11-20]4+", "[11-20]0",
            "[21-30]1", "[21-30]2-3", "[21-30]4+", "[21-30]0",
            "[31-40]1", "[31-40]2-3", "[31-40]4+", "[31-40]0",
            "[41-50]1", "[41-50]2-3", "[41-50]4+", "[41-50]0",
            "[51-60]1", "[51-60]2-3", "[51-60]4+", "[51-60]0",
            "[61-70]1", "[61-70]2-3", "[61-70]4+", "[61-70]0",
            "[71-80]1", "[71-80]2-3", "[71-80]4+", "[71-80]0",
    };
    private boolean isCanPost = true;
    private FormBody.Builder paramBuilder;
    private int currentMaxPage;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<NewKenoWinningReportBean.DataBean.ResultBean> resultList = (List<NewKenoWinningReportBean.DataBean.ResultBean>) msg.obj;
                    stateMap.clear();
                    for (int i = 0; i < resultList.size(); i++) {
                        stateMap.put(i, false);
                    }
                    adapter.setList(resultList);
                    mRefreshAndLoadMoreView.setRefreshing(false);
                    mLoadMoreListView.onLoadComplete();
                    if (page >= currentMaxPage) {
                        mLoadMoreListView.setHaveMoreData(false);
                    } else {
                        page++;
                    }
                    isCanPost = true;
                    break;
                case 1:
                    mRefreshAndLoadMoreView.setRefreshing(false);
                    mLoadMoreListView.onLoadComplete();
                    mLoadMoreListView.setHaveMoreData(false);
                    isCanPost = true;
                    break;
            }
        }
    };

    @OnClick({R.id.tv_search, R.id.tv_today, R.id.tv_yesterday, R.id.tv_this_week, R.id.tv_last_week, R.id.tv_this_month, R.id.tv_last_month,
            R.id.tv_to_date, R.id.tv_from_date, R.id.tv_win_type, R.id.tv_rule})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_win_type:
                MyListviewPopu popu = new MyListviewPopu(switchType, mContext, v) {
                    @Override
                    public void setOnItemClik(int position) {
                        selectType = switchType[position];
                        tv_win_type.setText(selectType);
                        if (selectType.equals("Min1-2")) {
                            selectType = "1-2";
                        } else if (selectType.equals("Min3-5")) {
                            selectType = "3-5";
                        } else if (selectType.equals("Min>=6")) {
                            selectType = ">=6";
                        } else if (selectType.equals("Max<=75")) {
                            selectType = "<=75";
                        } else if (selectType.equals("Max76-78")) {
                            selectType = "76-78";
                        } else if (selectType.equals("Max79-80")) {
                            selectType = "79-80";
                        } else if (selectType.equals("210-695")) {
                            selectType = "1st";
                        } else if (selectType.equals("696-763")) {
                            selectType = "2nd";
                        } else if (selectType.equals("764-855")) {
                            selectType = "3rd";
                        } else if (selectType.equals("856-923")) {
                            selectType = "4th";
                        } else if (selectType.equals("924-1410")) {
                            selectType = "5th";
                        } else if (selectType.startsWith("[")) {
                            String[] split = selectType.split("]");
                            String s = split[0];
                            String[] split1 = s.split("-");
                            String s1 = split1[1];
                            selectType = "Frequence#" + Integer.parseInt(s1) / 10 + "#" + split[1];
                        } else if (selectType.equals("All")) {
                            selectType = "";
                        }
                    }
                };
                popu.showPromotionsPopupWindow();
                break;
            case R.id.tv_from_date:
                dateType = 1;
                calendarPopuWindow.showPopupCenterWindow();
                break;
            case R.id.tv_to_date:
                calendarPopuWindow.showPopupCenterWindow();
                dateType = 2;
                break;
            case R.id.tv_search:
                postType = 0;
                fromDate = tv_from_date.getText().toString().trim();
                toDate = tv_to_date.getText().toString().trim();
                init();
                break;
            case R.id.tv_today:
                postType = 1;
                dateParam = "today";
                init();
                break;
            case R.id.tv_yesterday:
                postType = 1;
                dateParam = "yesterday";
                init();
                break;
            case R.id.tv_this_week:
                postType = 1;
                dateParam = "this_week";
                init();
                break;
            case R.id.tv_last_week:
                postType = 1;
                dateParam = "last_week";
                init();
                break;
            case R.id.tv_this_month:
                postType = 1;
                dateParam = "this_month";
                init();
                break;
            case R.id.tv_last_month:
                postType = 1;
                dateParam = "last_month";
                init();
                break;
            case R.id.tv_rule:
                rulePop = new BaseContentListPopWindow(mContext, tv_rule, tv_rule.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return ruleList;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tv_rule.setText(item.getContent());
                        rule = item.getContentId();
                        init();
                    }
                };
                rulePop.showPopupDownWindow();
                break;
        }
    }

    private void init() {
        if (isCanPost) {
            isCanPost = false;
            adapter.setList(new ArrayList<NewKenoWinningReportBean.DataBean.ResultBean>());
            mLoadMoreListView.setHaveMoreData(true);
            listData.clear();
            page = 1;
            addData(page);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initRule();
            init();
        }
    }

    private void initRule() {
        rule = newKenoActivity.getRule();
        ruleList = Arrays.asList(new ContentInfoBean("Rule 4", "4"), new ContentInfoBean("Rule 3", "3"), new ContentInfoBean("Rule 2", "2"));
        for (int i = 0; i < ruleList.size(); i++) {
            String contentId = ruleList.get(i).getContentId();
            if (contentId.equals(rule)) {
                tv_rule.setText(ruleList.get(i).getContent());
                break;
            }
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        winningUrl = newKenoActivity.getBaseUrl() + "api.php?page=keno_new_win_report_submitter";
        initRule();
        selectType = "";
        tv_win_type.setText(switchType[0]);
        postType = 1;
        dateParam = "today";
        s = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        calendarPopuWindow = new CalendarPopuWindow(mContext, mLoadMoreListView, screenWith / 5 * 4, screenWith / 5 * 4) {
            @Override
            public void getChoiceDateStr(String date) {
                if (dateType == 1) {
                    tv_from_date.setText(date);
                } else {
                    tv_to_date.setText(date);
                }
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                listView.setVisibility(View.GONE);
            }
        };
        initAdapter();
        String todayDate = DateUtils.getCurrentTime("yyyy-MM-dd");
        tv_from_date.setText(todayDate);
        tv_to_date.setText(todayDate);
        stateMap = new HashMap<>();
        mRefreshAndLoadMoreView.setLoadMoreListView(mLoadMoreListView);
        mLoadMoreListView.setRefreshAndLoadMoreView(mRefreshAndLoadMoreView);
        //设置下拉刷新监听
        mRefreshAndLoadMoreView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mLoadMoreListView.setHaveMoreData(true);
                listData.clear();
                page = 1;
                addData(page);
            }
        });
        //设置加载监听
        mLoadMoreListView.setOnLoadMoreListener(new LoadMoreListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                addData(page);
            }
        });
        addData(page);
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<NewKenoWinningReportBean.DataBean.ResultBean>(mContext, R.layout.item_new_keno_winning, listData) {
            @Override
            protected void convert(ViewHolder helper, NewKenoWinningReportBean.DataBean.ResultBean item, final int position) {
                LinearLayout ll_open_close = helper.retrieveView(R.id.ll_open_close);
                final CheckBox cb_open_close = helper.retrieveView(R.id.cb_open_close);
                TextView tv_position = helper.retrieveView(R.id.tv_position);
                TextView tv_order_number = helper.retrieveView(R.id.tv_order_number);
                TextView tv_bet_time = helper.retrieveView(R.id.tv_bet_time);
                TextView tv_name = helper.retrieveView(R.id.tv_name);
                TextView tv_login_id = helper.retrieveView(R.id.tv_login_id);
                TextView tv_ticket_id = helper.retrieveView(R.id.tv_ticket_id);
                TextView tv_result = helper.retrieveView(R.id.tv_result);
                final LinearLayout ll_detail = helper.retrieveView(R.id.ll_detail);
                TextView tv_type = helper.retrieveView(R.id.tv_type);
                TextView tv_bet = helper.retrieveView(R.id.tv_bet);
                TextView tv_odds = helper.retrieveView(R.id.tv_odds);
                TextView tv_return_amount = helper.retrieveView(R.id.tv_return_amount);
                tv_position.setText(Integer.valueOf((position + 1)).toString());
                tv_bet_time.setText(item.getBet_time());
                tv_name.setText(s.getUsername());
                tv_login_id.setText(item.getId_mod_member());
                tv_order_number.setText(item.getId_mod_bets());
                tv_ticket_id.setText(item.getTicket());
                tv_result.setText(item.getTotal_result());
                tv_type.setText(initBetDetail(item.getBet_detail()));
                tv_bet.setText(item.getAmount());
                tv_odds.setText(item.getFactor());
                String returnAmount = "";
                if (item.getWin_loss().equals("0")) {
                    returnAmount = "-" + item.getAmount();
                    tv_return_amount.setTextColor(Color.RED);
                } else {
                    returnAmount = item.getReturn_amount() + "";
                    tv_return_amount.setTextColor(Color.BLACK);
                }
                tv_return_amount.setText(returnAmount);
                ll_open_close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean state = stateMap.get(position);
                        stateMap.put(position, !state);
                        if (stateMap.get(position)) {
                            ll_detail.setVisibility(View.VISIBLE);
                            cb_open_close.setText("-");
                            cb_open_close.setChecked(true);
                        } else {
                            ll_detail.setVisibility(View.GONE);
                            cb_open_close.setText("+");
                            cb_open_close.setChecked(false);
                        }
                    }
                });
                if (stateMap.get(position)) {
                    ll_detail.setVisibility(View.VISIBLE);
                    cb_open_close.setChecked(true);
                } else {
                    ll_detail.setVisibility(View.GONE);
                    cb_open_close.setChecked(false);
                }
            }
        };
        mLoadMoreListView.setAdapter(adapter);
    }

    public String initBetDetail(String betDetail) {
        if (TextUtils.isEmpty(betDetail)) {
            return "";
        }
        if (betDetail.startsWith("Frequence")) {
            String[] split = betDetail.split("#");
            if (split.length == 3) {
                int number = Integer.parseInt(split[1]);
                int max = number * 10;
                int min = max - 9;
                return "[" + min + "-" + max + "]" + split[2];
            } else {
                return betDetail;
            }
        } else if (betDetail.equals("1st")) {
            return "210-695";
        } else if (betDetail.equals("2nd")) {
            return "696-763";
        } else if (betDetail.equals("3rd")) {
            return "764-855";
        } else if (betDetail.equals("4th")) {
            return "856-923";
        } else if (betDetail.equals("5th")) {
            return "924-1410";
        }
        return betDetail;
    }

    public void addData(int page) {
        isCanPost = false;
        paramBuilder = new FormBody.Builder();
        paramBuilder.add("web_id", WebSiteUrl.WebId);
        paramBuilder.add("username", newKenoActivity.getUsername());
        paramBuilder.add("per_page", "25");
        paramBuilder.add("page_no", page + "");
        paramBuilder.add("rule", rule);
        if (postType == 0) {
            paramBuilder.add("start_date", fromDate);
            paramBuilder.add("end_date", toDate);
            paramBuilder.add("bet_detail", selectType);
            paramBuilder.add("filter", "search");
        } else {
            paramBuilder.add("filter", dateParam);
        }
        UpdateDateRunnable updateDateRunnable = new UpdateDateRunnable();
        Thread dataThread = new Thread(updateDateRunnable);
        dataThread.start();
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_new_keno_winning;
    }

    private class UpdateDateRunnable implements Runnable {
        @Override
        public void run() {
            Request request = new Request.Builder().url(winningUrl).post(paramBuilder.build()).build();
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    handler.sendEmptyMessage(1);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String s = response.body().string();
                    NewKenoWinningReportBean newKenoWinningReportBean = getGson().fromJson(s, NewKenoWinningReportBean.class);
                    if (newKenoWinningReportBean != null && newKenoWinningReportBean.getData() != null && newKenoWinningReportBean.getData().getResult() != null) {
                        currentMaxPage = newKenoWinningReportBean.getData().getTotal_page();
                        List<NewKenoWinningReportBean.DataBean.ResultBean> resultList = newKenoWinningReportBean.getData().getResult();
                        if (currentMaxPage > 0 && page <= currentMaxPage) {
                            listData.addAll(resultList);
                            List<NewKenoWinningReportBean.DataBean.ResultBean> resultList1 = new ArrayList<>();
                            for (int i = 0; i < listData.size(); i++) {
                                NewKenoWinningReportBean.DataBean.ResultBean resultBean = listData.get(i);
                                resultList1.add(resultBean);
                            }
                            Message message = new Message();
                            message.what = 0;
                            message.obj = resultList1;
                            handler.sendMessage(message);
                        } else {
                            handler.sendEmptyMessage(1);
                        }
                    }
                }
            });
        }
    }
}
