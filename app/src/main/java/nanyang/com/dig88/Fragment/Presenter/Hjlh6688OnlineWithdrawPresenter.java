package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Fragment.Hjlh6688OnlineWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/10/24.
 */

public class Hjlh6688OnlineWithdrawPresenter extends BaseRetrofitPresenter<Hjlh6688OnlineWithdrawFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    Hjlh6688OnlineWithdrawFragment onlineWithdrawFragment;

    public Hjlh6688OnlineWithdrawPresenter(Hjlh6688OnlineWithdrawFragment iBaseContext) {
        super(iBaseContext);
        onlineWithdrawFragment = iBaseContext;
    }

    public void getWithdrawBank() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> beanList = new ArrayList<>();
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                String currency = onlineWithdrawFragment.getUserInfo().getId_mod_currency();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (bankAccountDetailBean.getType().equals("2") && bankAccountDetailBean.getCurrency().equals(currency)) {
                        beanList.add(bankAccountDetailBean);
                    }
                }
                onlineWithdrawFragment.onGetWithdrawBank(beanList);
            }
        });
    }

    public void getBankSate() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankStatus, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankInfoBean>> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankInfoBean>>>() {
                }.getType());
                List<BankInfoBean> bankInfoBeanList = new ArrayList<>();
                for (int i = 0; i < dataBean.getData().size(); i++) {
                    BankInfoBean bankInfoBean = dataBean.getData().get(i);
                    String online = bankInfoBean.getOnline();
                    if (online.equals("0")) {
                        bankInfoBeanList.add(bankInfoBean);
                    }
                }
                onlineWithdrawFragment.onGetBindingBank(bankInfoBeanList);
            }
        });
    }

    public void goWithdraw(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Withdraw, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                String returnMsg;
                switch (orgData.getMsg()) {
                    case "1":
                        onlineWithdrawFragment.clearAmount();
                        returnMsg = onlineWithdrawFragment.getString(R.string.qukuansucces);
                        break;
                    case "-6":
                        returnMsg = onlineWithdrawFragment.getString(R.string.Balance_not_enough);
                        break;
                    default:
                        returnMsg = onlineWithdrawFragment.getString(R.string.withdraw_failed);
                        break;
                }
                onlineWithdrawFragment.onGetWithdrawResult(returnMsg);
            }
        });
    }
}
