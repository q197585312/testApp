package nanyang.com.dig88.ThaiLottery;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.ResultListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2017/7/19.
 */

public class ThaiLotteryResultFragment extends ThaiLotteryBaseFragment {
    @BindView(R.id.list_result)
    ListView list_result;
    QuickBaseAdapter<ResultListBean> adapter;
    List<ResultListBean> resultList;
    String id_provider;

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        resultList = new ArrayList<>();
        initAdapter();
    }

    @Override
    public void clearBetMoney() {

    }

    private void initAdapter() {
        adapter = new QuickBaseAdapter<ResultListBean>(mContext, R.layout.item_thai_lottery_result_list, resultList) {
            @Override
            protected void convert(ViewHolder helper, ResultListBean item, int position) {
                helper.setText(R.id.tv_bianhao, (position + 1) + "");
                helper.setText(R.id.tv_youxi, "THAI");
                helper.setText(R.id.tv_area, getString(R.string.THAILAND));
                helper.setText(R.id.tv_qihao, item.getPeriod());
                helper.setText(R.id.tv_kaijiangtime, item.getOpen_time());
                helper.setText(R.id.tv_result, item.getResult());
            }
        };
        list_result.setAdapter(adapter);
    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_thai_lottery_resultlist;
    }

    @Override
    public void updataGameState() {
        thaiLottertGameList = getAct().getThaiLotteryDataList();
        getResultList();
    }

    private void getResultList() {
        if (thaiLottertGameList != null && thaiLottertGameList.size() > 0) {
            id_provider = thaiLottertGameList.get(0).getType2();
        } else {
            return;
        }
        NyVolleyJsonThreadHandler<List<ResultListBean>> resultListThread = new NyVolleyJsonThreadHandler<List<ResultListBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", getUserInfo().getUser_id());
                params.put("session_id", getUserInfo().getSession_id());
                params.put("id_provider", id_provider);
                //游戏状态
                return new QuickRequestBean(WebSiteUrl.ThaiLotteryGameResultUrl, params
                        , new TypeToken<NyBaseResponse<List<ResultListBean>>>() {
                }.getType());
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
            }

            @Override
            protected void successEndT(int total, List<ResultListBean> data) {
                if (data != null && data.size() > 0) {
                    resultList = data;
                    adapter.setList(resultList);
                    adapter.notifyDataSetChanged();
                }
            }

        };
        resultListThread.startThread(null);
    }

    @Override
    public String getBetUrl() {
        return WebSiteUrl.ThaiLotteryGameResultUrl;
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
}
