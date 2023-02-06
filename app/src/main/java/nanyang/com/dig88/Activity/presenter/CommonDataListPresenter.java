package nanyang.com.dig88.Activity.presenter;

import android.view.View;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Activity.CommonDataListActivity;
import nanyang.com.dig88.Entity.AutoPromotionBean;
import nanyang.com.dig88.Entity.PromotionListBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.ApiManager.getService;


/**
 * Created by 47184 on 2019/7/3.
 */

public class CommonDataListPresenter extends BaseRetrofitPresenter<CommonDataListActivity> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    CommonDataListActivity commonDataListActivity;

    public CommonDataListPresenter(CommonDataListActivity iBaseContext) {
        super(iBaseContext);
        commonDataListActivity = iBaseContext;
    }

    public void getPromotionData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.promotionDataUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                PromotionListBean promotionListBean = gson.fromJson(data, PromotionListBean.class);
                if (promotionListBean != null && promotionListBean.getData() != null && promotionListBean.getData().size() > 0) {
                    commonDataListActivity.onGetPromotionData(promotionListBean);
                } else {
                    PromotionListBean promotionListBean1 = new PromotionListBean();
                    PromotionListBean.DataBean dataBean = new PromotionListBean.DataBean();
                    dataBean.setBonus_amount("-1");
                    List<PromotionListBean.DataBean> beanList = new ArrayList<>();
                    beanList.add(dataBean);
                    promotionListBean1.setData(beanList);
                    commonDataListActivity.onGetPromotionData(promotionListBean1);
                }
            }
        });
    }

    public void getAutoPromotionData(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.autoPromotionDataUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                AutoPromotionBean autoPromotionBean = gson.fromJson(data, AutoPromotionBean.class);
                if (autoPromotionBean != null && autoPromotionBean.getData() != null && autoPromotionBean.getData().size() > 0) {
                    commonDataListActivity.onGetAutoPromotionData(autoPromotionBean);
                } else {
                    AutoPromotionBean autoPromotionBean1 = new AutoPromotionBean();
                    AutoPromotionBean.DataBean dataBean = new AutoPromotionBean.DataBean();
                    dataBean.setType2("-1");
                    List<AutoPromotionBean.DataBean> beanList = new ArrayList<>();
                    beanList.add(dataBean);
                    autoPromotionBean1.setData(beanList);
                    commonDataListActivity.onGetAutoPromotionData(autoPromotionBean1);
                }
            }
        });
    }
}
