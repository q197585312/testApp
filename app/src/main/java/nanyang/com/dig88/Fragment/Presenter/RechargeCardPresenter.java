package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.HashMap;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Fragment.RechargeCardFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/2.
 */

public class RechargeCardPresenter extends BaseRetrofitPresenter<RechargeCardFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    RechargeCardFragment rechargeCardFragment;

    public RechargeCardPresenter(RechargeCardFragment iBaseContext) {
        super(iBaseContext);
        rechargeCardFragment = iBaseContext;
    }

    public void goRechargeCard(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.rechargeCardUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                String returnMsg;
                switch (orgData.getMsg()) {
                    case "1":
                        rechargeCardFragment.clear();
                        returnMsg = rechargeCardFragment.getString(R.string.recharge_card_succeed);
                        break;
                    default:
                        returnMsg = rechargeCardFragment.getString(R.string.recharge_card_fail);
                        break;
                }
                rechargeCardFragment.onGetResultMsg(returnMsg);
            }
        });
    }
}
