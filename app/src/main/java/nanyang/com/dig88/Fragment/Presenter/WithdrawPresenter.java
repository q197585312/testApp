package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.WithDrawListBean;
import nanyang.com.dig88.Fragment.WithdrawListFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/2.
 */

public class WithdrawPresenter extends BaseRetrofitPresenter<WithdrawListFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    WithdrawListFragment withdrawListFragment;

    public WithdrawPresenter(WithdrawListFragment iBaseContext) {
        super(iBaseContext);
        withdrawListFragment = iBaseContext;
    }

    public void getWithdrawData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.WithdrawList, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<WithDrawListBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<WithDrawListBean>>>() {
                }.getType());
                List<WithDrawListBean> dataList;
                switch (orgData.getMsg()) {
                    case "1":
                        dataList = orgData.getData();
                        if (dataList != null && dataList.size() > 0) {
                            withdrawListFragment.onGetWithdrawData(dataList);
                        } else {
                            dataList = new ArrayList<>();
                            WithDrawListBean withDrawListBean = new WithDrawListBean();
                            withDrawListBean.setAmount("-1");
                            dataList.add(withDrawListBean);
                            withdrawListFragment.onGetWithdrawData(dataList);
                        }
                        break;
                    default:
                        dataList = new ArrayList<>();
                        WithDrawListBean withDrawListBean = new WithDrawListBean();
                        withDrawListBean.setAmount("-1");
                        dataList.add(withDrawListBean);
                        withdrawListFragment.onGetWithdrawData(dataList);
                        break;
                }
            }
        });
    }
}
