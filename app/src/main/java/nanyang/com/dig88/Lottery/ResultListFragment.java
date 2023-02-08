package nanyang.com.dig88.Lottery;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.Entity.ResultListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * ���结果列表�
 *
 * @author Administrator
 */
public class ResultListFragment extends LotteryBaseFragment<ResultListBean> {
    @BindView(R.id.list_result)
    ListView list_result;
    @BindView(R.id.nojilu)
    TextView nojilu;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        list_result.setAdapter(adapter);


    }

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.resultlist_fragment;
    }

    /**
     * 请求结果列表
     */
    private void getResultList() {
        NyVolleyJsonThreadHandler<List<ResultListBean>> resultListThread = new NyVolleyJsonThreadHandler<List<ResultListBean>>(mContext) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("user_id", getUserInfo().getUser_id());
                params.put("session_id", getUserInfo().getSession_id());
                Log.w("HttpVolley","Result:"+ getUserInfo().getSession_id());
                params.put("type1", "5");
                if (getAct().getSelectedStateBean().getType2().equals("1")) {
                    params.put("web_id", WebSiteUrl.WedIdResult);
                } else {
                    params.put("web_id", WebSiteUrl.WebId);
                }
                params.put("type2", getAct().getSelectedStateBean().getType2());
                //游戏状态
                return new QuickRequestBean(WebSiteUrl.GetNumberGameResults, params
                        , new TypeToken<NyBaseResponse<List<ResultListBean>>>() {
                }.getType());
            }

            @Override
            public void errorEnd(String obj) {
                super.errorEnd(obj);
                list_result.setVisibility(View.GONE);
                nojilu.setVisibility(View.VISIBLE);
                nojilu.setText(getString(R.string.nojilu));
            }

            @Override
            protected void successEndT(int total, List<ResultListBean> data) {
                if (data == null || data.size() < 1) {
                    list_result.setVisibility(View.GONE);
                    nojilu.setVisibility(View.VISIBLE);
                    nojilu.setText(getString(R.string.nojilu));
                } else {
                    list_result.setVisibility(View.VISIBLE);
                    nojilu.setVisibility(View.GONE);
                    adapter.setList(data);
                }

            }

        };
        resultListThread.startThread(null);
    }


    @Override
    public void updateGameState(List<DigGameOddsBean> oddsList,LotteryStateGameBean bean) {

        getResultList();
    }

    @Override
    public LotteryBaseAdapter<ResultListBean> getAdapter() {
        return new LotteryBaseAdapter<ResultListBean>(mContext, R.layout.item_result_list) {
            @Override
            protected void convert(ViewHolder helper, ResultListBean item, int position) {
                helper.setText(R.id.tv_bianhao, (position + 1) + "");
                helper.setText(R.id.tv_youxi, getString(R.string.caipiaogzuize));
                String name=getAct().getSelectedStateBean().getGame_name();
                if(name.equals("SINGAPORE")){
                    name=getString(R.string.SINGAPORE);
                }else if(name.equals("TAIPEI")){
                    name=getString(R.string.TAIPEI);
                }else if(name.equals("KUALA_LUMPUR")){
                    name=getString(R.string.KUALA_LUMPUR);
                }
                else if(name.equals("HONGKONG")){
                    name=getString(R.string.HONGKONG);
                }
                else if(name.equals("MALAYSIA")){
                    name=getString(R.string.MALAYSIA);
                }
                else if(name.equals("CHINA")){
                    name=getString(R.string.CHINA);
                }
                else if(name.equals("HK4D")){
                    name=getString(R.string.HK4D);
                }
                else if(name.equals("CAMBODIA")){
                    name=getString(R.string.CAMBODIA);
                }else if(name.equals("MACAU")){
                    name=getString(R.string.MACAU);
                }else if(name.equals("CANADA4D")){
                    name=getString(R.string.CANADA4D);
                }
                helper.setText(R.id.tv_area, name);
                helper.setText(R.id.tv_qihao, item.getPeriod());
                helper.setText(R.id.tv_kaijiangtime, item.getOpen_time());
                helper.setText(R.id.tv_result, item.getResult());
            }

            @Override
            protected List<ResultListBean> initList() {
                return new ArrayList<>();
            }

            @Override
            protected void clearListMoney() {

            }

            @Override
            protected void countTotal() {

            }
        };
    }

    @Override
    protected String getSubmitPageUrl() {
        return "";
    }

    @Override
    protected String constructorGetBetStr() {
        return "";
    }
}
