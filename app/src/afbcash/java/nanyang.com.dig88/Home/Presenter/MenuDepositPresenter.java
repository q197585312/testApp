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
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Home.MenuDepositFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/6/28.
 */

public class MenuDepositPresenter extends BaseRetrofitPresenter<MenuDepositFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    MenuDepositFragment menuDepositFragment;

    public MenuDepositPresenter(MenuDepositFragment iBaseContext) {
        super(iBaseContext);
        menuDepositFragment = iBaseContext;
    }

    public void getDepositBank() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> beanList = new ArrayList<>();
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                String currency = menuDepositFragment.getUserInfo().getId_mod_currency();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (menuDepositFragment.getCurrency().equals("USD")) {
                        if (bankAccountDetailBean.getCurrency().equals(currency) && bankAccountDetailBean.getType().equals("2") &&
                                bankAccountDetailBean.getBank_level().equals(menuDepositFragment.getUserInfo().getMem_level()) &&
                                bankAccountDetailBean.getNo().length() > 1) {
                            String id_mod_bank = bankAccountDetailBean.getId_mod_bank();
                            if (!id_mod_bank.equals("751") && !id_mod_bank.equals("1256") && !id_mod_bank.equals("497")) {
                                beanList.add(bankAccountDetailBean);
                            }
                        }
                    } else {
                        if (bankAccountDetailBean.getType().equals("2") && bankAccountDetailBean.getCurrency().equals(currency)) {
                            if (menuDepositFragment.getCurrency().equals("MYR")) {
                                String bankName = bankAccountDetailBean.getBank_name();
                                String no = bankAccountDetailBean.getNo();
                                if (!bankName.contains("PayTrust") && !bankName.contains("918") && !no.equals("0")) {
                                    beanList.add(bankAccountDetailBean);
                                }
                            } else {
                                beanList.add(bankAccountDetailBean);
                            }
                        }
                    }
                }
                menuDepositFragment.onGetDepositBank(beanList);
            }
        });
    }

    public void goDeposit(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Deposit, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                String returnMsg;
                switch (orgData.getMsg()) {
                    case "1":
                        getVipInfoBean(menuDepositFragment.getUserInfo());
                        returnMsg = menuDepositFragment.getString(R.string.cunkuansus);
                        break;
                    default:
                        returnMsg = menuDepositFragment.getString(R.string.tijiaosibai);
                        break;
                }
                menuDepositFragment.onGetDepositResult(returnMsg);
            }
        });
    }

    public void getVipInfoBean(UserInfoBean userInfoBean) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", userInfoBean.getUser_id());
        p.put("session_id", userInfoBean.getSession_id());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.MemberInfoSubmitter, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<VipInfoBean> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
                VipInfoBean vipInfoBean = dataBean.getData();
                AppTool.saveObjectData(baseContext.getBaseActivity(), "vipInfo", vipInfoBean);
                menuDepositFragment.clearAmount();
            }
        });
    }
}
