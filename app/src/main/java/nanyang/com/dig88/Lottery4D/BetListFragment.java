package nanyang.com.dig88.Lottery4D;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.Lottery4D.Bean.BetListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/11/16.
 */

public class BetListFragment extends Lottery4DBaseFragment {
    @BindView(R.id.lv_bet_list)
    ListView lv_bet_list;
    @BindView(R.id.tv_amount)
    TextView tv_amount;
    @BindView(R.id.tv_a)
    TextView tv_a;
    @BindView(R.id.tv_abc)
    TextView tv_abc;
    String url = "http://app.info.dig88api.com/index.php?page=4d_betlist_submitter";
    QuickBaseAdapter<BetListBean.DataBean> adapter;
    Lottery4DActivity activity;
    private Map<String, String> poolMap;
    private boolean isOnRefresh = true;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_lottery_4d_bet_list;
    }

    @Override

    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        activity = (Lottery4DActivity) getActivity();
        poolMap = activity.getPoolMap();
        if (activity.isAbcAndAType()) {
            tv_a.setVisibility(View.VISIBLE);
            tv_abc.setVisibility(View.VISIBLE);
        } else {
            tv_a.setVisibility(View.GONE);
            tv_abc.setVisibility(View.GONE);
        }
        initAdapter();
        getBetListData();
    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<BetListBean.DataBean>(mContext, R.layout.item_lottery_4d_bet_list, new ArrayList<BetListBean.DataBean>()) {
            @Override
            protected void convert(ViewHolder helper, BetListBean.DataBean item, int position) {
                LinearLayout ll_content = helper.retrieveView(R.id.ll_content);
                if (position % 2 == 0) {
                    ll_content.setBackgroundColor(0xff595959);
                } else {
                    ll_content.setBackgroundColor(0xff363535);
                }
                TextView tv_number = helper.retrieveView(R.id.tv_number);
                tv_number.setText((position + 1) + "");
                TextView tv_pool = helper.retrieveView(R.id.tv_pool);
                tv_pool.setText(poolMap.get(item.getPool()));
                TextView tv_bet_number = helper.retrieveView(R.id.tv_bet_number);
                tv_bet_number.setText(item.getBet_detail());
                TextView tv_big = helper.retrieveView(R.id.tv_big);
                TextView tv_small = helper.retrieveView(R.id.tv_small);
                TextView tv_a = helper.retrieveView(R.id.tv_a);
                TextView tv_abc = helper.retrieveView(R.id.tv_abc);
                String betType = item.getBettype();
                String amount = StringUtils.floatDecimalFormat(Float.parseFloat(item.getBet_amount()), "0.00").toString();
                if (betType.equals("108")) {
                    tv_big.setText(amount);
                    tv_small.setText("");
                    tv_a.setText("");
                    tv_abc.setText("");
                } else if (betType.equals("109")) {
                    tv_big.setText("");
                    tv_small.setText(amount);
                    tv_a.setText("");
                    tv_abc.setText("");
                } else if (betType.equals("110")) {
                    tv_big.setText("");
                    tv_small.setText("");
                    tv_a.setText(amount);
                    tv_abc.setText("");
                } else if (betType.equals("111")) {
                    tv_big.setText("");
                    tv_small.setText("");
                    tv_a.setText("");
                    tv_abc.setText(amount);
                }
                TextView tv_period = helper.retrieveView(R.id.tv_period);
                String period = item.getPeriod().substring(0, 8);
                String year = period.substring(0, 4);
                String month = period.substring(4, 6);
                String day = period.substring(6, period.length());
                tv_period.setText(day + "/" + month + "/" + year);
                if (activity.isAbcAndAType()) {
                    tv_a.setVisibility(View.VISIBLE);
                    tv_abc.setVisibility(View.VISIBLE);
                } else {
                    tv_a.setVisibility(View.GONE);
                    tv_abc.setVisibility(View.GONE);
                }
            }
        };
        lv_bet_list.setAdapter(adapter);
    }

    private void getBetListData() {
        if (isOnRefresh) {
            isOnRefresh = false;
            String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() +
                    "&session_id=" + getUserInfo().getSession_id();
            HttpUtils.httpPost(url, param, new HttpUtils.RequestCallBack() {
                @Override
                public void onRequestSucceed(String s) {
                    BetListBean betListBean = gson.fromJson(s, BetListBean.class);
                    adapter.setList(betListBean.getData());
                    List<BetListBean.DataBean> data = betListBean.getData();
                    float totalAmount = 0;
                    for (int i = 0; i < data.size(); i++) {
                        BetListBean.DataBean dataBean = data.get(i);
                        float amount = Float.parseFloat(dataBean.getBet_amount());
                        totalAmount += amount;
                    }
                    tv_amount.setText(StringUtils.floatDecimalFormat(totalAmount, "0.00").toString());
                    isOnRefresh = true;
                }

                @Override
                public void onRequestFailed(String s) {

                }
            });
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            getBetListData();
        }
    }
}
