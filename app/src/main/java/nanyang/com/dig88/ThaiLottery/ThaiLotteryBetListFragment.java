package nanyang.com.dig88.ThaiLottery;

import android.widget.ListView;

import com.google.gson.Gson;

import butterknife.Bind;
import nanyang.com.dig88.R;
import nanyang.com.dig88.ThaiLottery.bean.ThaiLotteryBetListBean;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;

/**
 * Created by Administrator on 2018/8/29.
 */

public class ThaiLotteryBetListFragment extends ThaiLotteryBaseFragment {
    @Bind(R.id.list_result)
    ListView list_result;
    QuickBaseAdapter<ThaiLotteryBetListBean.DataBean> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_thai_lottery_betlist;
    }

    @Override
    public void updataGameState() {
        String url = "http://app.info.dig88api.com/index.php?page=th_bet_list_submitter";
        String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
        HttpUtils.httpPost(url, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                ThaiLotteryBetListBean thaiLotteryBetListBean = new Gson().fromJson(s, ThaiLotteryBetListBean.class);
                initAdapter(thaiLotteryBetListBean);
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    private void initAdapter(ThaiLotteryBetListBean thaiLotteryBetListBean) {
        adapter = new QuickBaseAdapter<ThaiLotteryBetListBean.DataBean>(mContext, R.layout.item_thai_lottery_bet_list, thaiLotteryBetListBean.getData()) {
            @Override
            protected void convert(ViewHolder helper, ThaiLotteryBetListBean.DataBean item, int position) {
                helper.setText(R.id.tv_bianhao, (position + 1) + "");
                helper.setText(R.id.tv_youxi, item.getGame_name());
                helper.setText(R.id.tv_area, getString(R.string.THAILAND));
                helper.setText(R.id.tv_wanfa, item.getPlay_type());
                helper.setText(R.id.tv_haoma, item.getNumber());
                helper.setText(R.id.tv_peilv, item.getRate());
                helper.setText(R.id.tv_youhui, item.getDiscount());
                helper.setText(R.id.tv_betAmount, item.getBet_amount());
                helper.setText(R.id.tv_qihao, item.getPeriod());
                helper.setText(R.id.tv_bet_time, item.getBet_time());
            }
        };
        list_result.setAdapter(adapter);
    }

    @Override
    public String getBetUrl() {
        return null;
    }

    @Override
    public void setProgressVisibility() {

    }

    @Override
    public String getBetUrlParmas() {
        return null;
    }

    @Override
    public String getBetIdProvider() {
        return null;
    }

    @Override
    public void clearBetMoney() {

    }
}
