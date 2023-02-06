package nanyang.com.dig88.Home.Presenter;

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
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/6/28.
 */

public class MenuWithdrawPresenter extends BaseRetrofitPresenter<MenuWithdrawFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    MenuWithdrawFragment menuWithdrawFragment;

    public MenuWithdrawPresenter(MenuWithdrawFragment iBaseContext) {
        super(iBaseContext);
        menuWithdrawFragment = iBaseContext;
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
                String currency = menuWithdrawFragment.getUserInfo().getId_mod_currency();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (menuWithdrawFragment.getCurrency().equals("USD")) {
                        if (bankAccountDetailBean.getCurrency().equals(currency) && bankAccountDetailBean.getType().equals("2") &&
                                bankAccountDetailBean.getBank_level().equals(menuWithdrawFragment.getUserInfo().getMem_level()) &&
                                bankAccountDetailBean.getNo().length() > 1) {
                            String id_mod_bank = bankAccountDetailBean.getId_mod_bank();
                            if (!id_mod_bank.equals("751") && !id_mod_bank.equals("1256") && !id_mod_bank.equals("497")) {
                                beanList.add(bankAccountDetailBean);
                            }
                        }
                    } else {
                        if (bankAccountDetailBean.getType().equals("2") && bankAccountDetailBean.getCurrency().equals(currency)) {
                            beanList.add(bankAccountDetailBean);
                        }
                    }
                }
                menuWithdrawFragment.onGetWithdrawBank(beanList);
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
                        menuWithdrawFragment.clearAmount();
                        returnMsg = menuWithdrawFragment.getString(R.string.qukuansucces);
                        break;
                    case "-2":
                        returnMsg = "Withdraw code incorrect";
                        break;
                    case "-6":
                        returnMsg = menuWithdrawFragment.getString(R.string.Balance_not_enough);
                        break;
                    default:
                        returnMsg = menuWithdrawFragment.getString(R.string.withdraw_failed);
                        break;
                }
                menuWithdrawFragment.onGetWithdrawResult(returnMsg);
            }
        });
    }
}
