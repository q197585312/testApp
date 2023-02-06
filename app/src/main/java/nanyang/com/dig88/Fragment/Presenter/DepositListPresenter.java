package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.DepositListBean;
import nanyang.com.dig88.Fragment.DepositListFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/2.
 */

public class DepositListPresenter extends BaseRetrofitPresenter<DepositListFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    DepositListFragment depositListFragment;

    public DepositListPresenter(DepositListFragment iBaseContext) {
        super(iBaseContext);
        depositListFragment = iBaseContext;
    }

    public void getDepositListData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.DepositList, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<DepositListBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<DepositListBean>>>() {
                }.getType());
                List<DepositListBean> dataList;
                switch (orgData.getMsg()) {
                    case "1":
                        dataList = orgData.getData();
                        if (dataList != null && dataList.size() > 0) {
                            depositListFragment.onGetDepositData(dataList);
                        } else {
                            dataList = new ArrayList<>();
                            DepositListBean withDrawListBean = new DepositListBean();
                            withDrawListBean.setAmount("-1");
                            dataList.add(withDrawListBean);
                            depositListFragment.onGetDepositData(dataList);
                        }
                        break;
                    default:
                        dataList = new ArrayList<>();
                        DepositListBean withDrawListBean = new DepositListBean();
                        withDrawListBean.setAmount("-1");
                        dataList.add(withDrawListBean);
                        depositListFragment.onGetDepositData(dataList);
                        break;
                }
            }
        });
    }
}
