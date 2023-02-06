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
import nanyang.com.dig88.Entity.TransferAccPersonBean;
import nanyang.com.dig88.Fragment.TransferAccListFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/7/8.
 */

public class TransferAccListPresenter extends BaseRetrofitPresenter<TransferAccListFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    TransferAccListFragment transferAccListFragment;

    public TransferAccListPresenter(TransferAccListFragment iBaseContext) {
        super(iBaseContext);
        transferAccListFragment = iBaseContext;
    }

    public void getTransferListData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.TransferListUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                TransferAccPersonBean transferAccPersonBean = gson.fromJson(data, TransferAccPersonBean.class);
                List<TransferAccPersonBean.DataBean> beanList = transferAccPersonBean.getData();
                if (transferAccPersonBean.getData() == null || transferAccPersonBean.getData().size() == 0) {
                    beanList = new ArrayList<>();
                    TransferAccPersonBean.DataBean bean = new TransferAccPersonBean.DataBean();
                    bean.setInsert_time("-1");
                    beanList.add(bean);
                }
                transferAccListFragment.onGetTransferListData(beanList);
            }
        });
    }
}
