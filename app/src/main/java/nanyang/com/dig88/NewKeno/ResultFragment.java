package nanyang.com.dig88.NewKeno;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.LoadMoreListView;
import nanyang.com.dig88.Util.RefreshAndLoadMoreView;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/9/13.
 */

public class ResultFragment extends NewKenoBaseFragment {
    @BindView(R.id.mRefreshAndLoadMoreView)
    RefreshAndLoadMoreView mRefreshAndLoadMoreView;
    @BindView(R.id.mLoadMoreListView)
    LoadMoreListView mLoadMoreListView;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_rule)
    TextView tv_rule;
    String rule;
    String resultUrl = "http://kgweb.keno6.com/api/api.php?page=keno_new_result_submitter";
    String param;
    private BaseContentListPopWindow rulePop;
    private List<ContentInfoBean> ruleList;
    private int page = 1;
    private List<NewKenoResultListBean.DataBean.ResultBean> listData = new ArrayList<>();
    private QuickBaseAdapter<NewKenoResultListBean.DataBean.ResultBean> adapter;
    private Map<Integer, Boolean> stateMap;
    private HttpClient httpClient;
    private String dateParam;
    private CalendarPopuWindow calendarPopuWindow;
    private int currentMaxPage;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    List<NewKenoResultListBean.DataBean.ResultBean> resultList = (List<NewKenoResultListBean.DataBean.ResultBean>) msg.obj;
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
                    break;
                case 1:
                    mRefreshAndLoadMoreView.setRefreshing(false);
                    mLoadMoreListView.onLoadComplete();
                    mLoadMoreListView.setHaveMoreData(false);
                    break;
            }
        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_new_keno_result;
    }

    @OnClick({R.id.tv_search, R.id.tv_today, R.id.tv_yesterday, R.id.tv_date, R.id.tv_rule})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_search:
                dateParam = tv_date.getText().toString().trim();
                init();
                break;
            case R.id.tv_today:
                dateParam = DateUtils.getCurrentTime("yyyy-MM-dd");
                init();
                break;
            case R.id.tv_yesterday:
                dateParam = DateUtils.getYesterdayTime("yyyy-MM-dd");
                init();
                break;
            case R.id.tv_date:
                calendarPopuWindow.showPopupCenterWindow();
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
        adapter.setList(new ArrayList<NewKenoResultListBean.DataBean.ResultBean>());
        mLoadMoreListView.setHaveMoreData(true);
        listData.clear();
        page = 1;
        addData(page);
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
        resultUrl = newKenoActivity.getBaseUrl() + "api.php?page=keno_new_result_submitter";
        initRule();
        calendarPopuWindow = new CalendarPopuWindow(mContext, tv_date, screenWith / 5 * 4, screenWith / 5 * 4) {
            @Override
            public void getChoiceDateStr(String date) {
                tv_date.setText(date.trim());
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                listView.setVisibility(View.GONE);
            }
        };
        dateParam = DateUtils.getCurrentTime("yyyy-MM-dd");
        tv_date.setText(dateParam);
        httpClient = new HttpClient("");
        stateMap = new HashMap<>();
        adapter = new QuickBaseAdapter<NewKenoResultListBean.DataBean.ResultBean>(mContext, R.layout.item_new_keno_result, listData) {
            @Override
            protected void convert(ViewHolder helper, NewKenoResultListBean.DataBean.ResultBean item, final int position) {
                LinearLayout ll_open_close = helper.retrieveView(R.id.ll_open_close);
                final CheckBox cb_open_close = helper.retrieveView(R.id.cb_open_close);
                TextView tv_position = helper.retrieveView(R.id.tv_position);
                TextView tv_date = helper.retrieveView(R.id.tv_date);
                TextView tv_number = helper.retrieveView(R.id.tv_number);
                TextView tv_result = helper.retrieveView(R.id.tv_result);
                final LinearLayout ll_detail = helper.retrieveView(R.id.ll_detail);
                TextView tv_detail = helper.retrieveView(R.id.tv_detail);
                TextView tv_over_under = helper.retrieveView(R.id.tv_over_under);
                TextView tv_odd_even = helper.retrieveView(R.id.tv_odd_even);
                TextView tv_range = helper.retrieveView(R.id.tv_range);
                TextView tv_1_10 = helper.retrieveView(R.id.tv_1_10);
                TextView tv_11_20 = helper.retrieveView(R.id.tv_11_20);
                TextView tv_21_30 = helper.retrieveView(R.id.tv_21_30);
                TextView tv_31_40 = helper.retrieveView(R.id.tv_31_40);
                TextView tv_41_50 = helper.retrieveView(R.id.tv_41_50);
                TextView tv_51_60 = helper.retrieveView(R.id.tv_51_60);
                TextView tv_61_70 = helper.retrieveView(R.id.tv_61_70);
                TextView tv_71_80 = helper.retrieveView(R.id.tv_71_80);
                TextView tv_smallest_number = helper.retrieveView(R.id.tv_smallest_number);
                TextView tv_biggest_number = helper.retrieveView(R.id.tv_biggest_number);
                tv_position.setText(Integer.valueOf((position + 1)).toString());
                tv_date.setText(item.getOpen_time().split(" ")[0]);
                tv_number.setText(item.getTitle());
                tv_result.setText(item.getTotal_result());
                tv_detail.setText(item.getDetail());
                tv_over_under.setText(item.getOu());
                tv_odd_even.setText(item.getOe());
                tv_range.setText(item.getRange());
                tv_1_10.setText(item.getF1());
                tv_11_20.setText(item.getF2());
                tv_21_30.setText(item.getF3());
                tv_31_40.setText(item.getF4());
                tv_41_50.setText(item.getF5());
                tv_51_60.setText(item.getF6());
                tv_61_70.setText(item.getF7());
                tv_71_80.setText(item.getF8());
                tv_smallest_number.setText(item.getSmallest());
                tv_biggest_number.setText(item.getBiggest());
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

    public void addData(int page) {
        param = "web_id=" + WebSiteUrl.WebId + "&username=" + newKenoActivity.getUsername() + "&per_page=25" + "&date=" + dateParam + "&page_no=" + page + "&rule=" + rule;
        UpdateDateRunnable updateDateRunnable = new UpdateDateRunnable();
        Thread dataThread = new Thread(updateDateRunnable);
        dataThread.start();
    }

    private class UpdateDateRunnable implements Runnable {
        @Override
        public void run() {
            try {
                String s = httpClient.sendPost(resultUrl, param);
                NewKenoResultListBean newKenoResultListBean = getGson().fromJson(s, NewKenoResultListBean.class);
                if (newKenoResultListBean != null && newKenoResultListBean.getData() != null && newKenoResultListBean.getData().getResult() != null) {
                    currentMaxPage = newKenoResultListBean.getData().getTotal_page();
                    List<NewKenoResultListBean.DataBean.ResultBean> resultList = newKenoResultListBean.getData().getResult();
                    if (currentMaxPage > 0 && page <= currentMaxPage) {
                        listData.addAll(resultList);
                        List<NewKenoResultListBean.DataBean.ResultBean> resultList1 = new ArrayList<>();
                        for (int i = 0; i < listData.size(); i++) {
                            resultList1.add(listData.get(i));
                        }
                        Message message = new Message();
                        message.what = 0;
                        message.obj = resultList1;
                        handler.sendMessage(message);
                    } else {
                        handler.sendEmptyMessage(1);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(1);
            }
        }
    }

}
