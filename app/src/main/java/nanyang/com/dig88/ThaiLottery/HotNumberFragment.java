package nanyang.com.dig88.ThaiLottery;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.R;
import nanyang.com.dig88.ThaiLottery.bean.HotNumberBean;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/1/22.
 */

public class HotNumberFragment extends ThaiLotteryBaseFragment {
    @BindView(R.id.lv_hotNumber)
    ListView lv_hotNumber;
    QuickBaseAdapter<HotNumberBean.DataBean> adapter;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_thai_lottery_hot_number;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    public void updataGameState() {
        String url = "http://app.info.dig88api.com/index.php?page=th_hot_submitter";
        String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
        HttpUtils.httpPost(url, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                HotNumberBean hotNumberBean = gson.fromJson(s, HotNumberBean.class);
                initAdapter(hotNumberBean);
            }

            @Override
            public void onRequestFailed(String s) {

            }
        });
    }

    private void initAdapter(HotNumberBean hotNumberBean) {
        adapter = new QuickBaseAdapter<HotNumberBean.DataBean>(mContext, R.layout.item_hot_number, hotNumberBean.getData()) {
            @Override
            protected void convert(ViewHolder helper, HotNumberBean.DataBean item, int position) {
                TextView tv_no = helper.retrieveView(R.id.tv_no);
                TextView tv_game_type = helper.retrieveView(R.id.tv_game_type);
                TextView tv_country = helper.retrieveView(R.id.tv_country);
                TextView tv_number = helper.retrieveView(R.id.tv_number);
                TextView tv_play_type = helper.retrieveView(R.id.tv_play_type);
                TextView tv_odds = helper.retrieveView(R.id.tv_odds);
                tv_no.setText((position + 1) + "");
                tv_game_type.setText(item.getGame_name());
                tv_country.setText(item.getPool());
                tv_number.setText(item.get_$5());
                tv_play_type.setText(item.get_$12());
                tv_odds.setText(item.get_$11());
            }
        };
        lv_hotNumber.setAdapter(adapter);
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
