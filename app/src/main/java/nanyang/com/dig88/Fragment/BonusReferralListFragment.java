package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Adapter.RecommendListAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.RecommendedListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2015/12/21. (被推荐列表)
 */
public class BonusReferralListFragment extends BaseFragment {
    private static final int SUBMIT_SUCCESS = 0;
    private static final int SUBMIT_ERROR = 1;
    private static final int SUBMIT_NODATA = 2;
    public BlockDialog dialog;
    @Bind(R.id.lv_deposit)
    ListView lv_deposit;
    RecommendListAdapter recommendListAdapter;
    private List<RecommendedListBean> listData = new ArrayList<RecommendedListBean>();
    private ReferralList referralList = null;
    private Thread threadReferralList = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUBMIT_SUCCESS:
                    dismissBlockDialog();
                    recommendListAdapter = new RecommendListAdapter(getActivity(), listData);
                    lv_deposit.setAdapter(recommendListAdapter);
                    break;
                case SUBMIT_ERROR:
                    dismissBlockDialog();
                    break;
            }

        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_tuijianren_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        getAct().setTitle(getString(R.string.beituijianlist));
        getAct().setleftViewEnable(true);
        dialog = new BlockDialog(getActivity(), getString(R.string.zhengjiazai));
        getAct().setDialog(new BlockDialog(getActivity(), getString(R.string.zhengjiazai)));
        showBlockDialog();
        referralList = new ReferralList();
        threadReferralList = new Thread(referralList);
        threadReferralList.start();
    }

    public class ReferralList implements Runnable {
        public void run() {
            try {
                HttpClient httpClient = null;
                if (getApp().getHttpClient() == null) {
                    httpClient = new HttpClient(WebSiteUrl.AutonumberStatus, "");
                    if (httpClient.connect("POST") == false) {
                        return;
                    } else {
                        httpClient.getSessionId();
                    }
                } else {
                    httpClient = getApp().getHttpClient();
                }
                if (httpClient != null) {
                    String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
                    String returnVaule = httpClient.sendPost(WebSiteUrl.BonusReferralList, params);
                    Gson gson = new Gson();
                    NyBaseResponse<List<RecommendedListBean>> orgData;
                    orgData = gson.fromJson(returnVaule, new TypeToken<NyBaseResponse<List<RecommendedListBean>>>() {
                    }.getType());
                    List<RecommendedListBean> data = orgData.getData();
                    if (data == null || data.size() == 0) {
                        RecommendedListBean recommendedListBean = new RecommendedListBean();
                        recommendedListBean.setUsername("-1");
                        listData.add(recommendedListBean);
                    } else {
                        for (int i = 0; i < data.size(); i++) {
                            listData.add(data.get(i));
                        }
                    }
                    handler.sendEmptyMessage(SUBMIT_SUCCESS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
