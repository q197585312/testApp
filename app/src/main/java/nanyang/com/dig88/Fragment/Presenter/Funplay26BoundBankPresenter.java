package nanyang.com.dig88.Fragment.Presenter;

import android.text.TextUtils;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Funplay26BountBankFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/8/8.
 */

public class Funplay26BoundBankPresenter extends BaseRetrofitPresenter<Funplay26BountBankFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    Funplay26BountBankFragment funplay26BountBankFragment;

    public Funplay26BoundBankPresenter(Funplay26BountBankFragment iBaseContext) {
        super(iBaseContext);
        funplay26BountBankFragment = iBaseContext;
    }

    public List<ContentInfoBean> getMcdBank() {
        List<ContentInfoBean> list = new ArrayList<>();
        list.add(new ContentInfoBean("Maybank", "1498"));
        list.add(new ContentInfoBean("Hong Leong Bank", "1499"));
        list.add(new ContentInfoBean("CIMB Bank", "1500"));
        list.add(new ContentInfoBean("Public Bank", "1501"));
        list.add(new ContentInfoBean("Ambank", "1602"));
        list.add(new ContentInfoBean("RHB BANK", "1603"));
        list.add(new ContentInfoBean("BANK SIMPANAN NASIONAL", "1604"));
        list.add(new ContentInfoBean("AFFIN BANK", "1648"));
        list.add(new ContentInfoBean("ALLIANCE BANK", "1649"));
        list.add(new ContentInfoBean("BANK ISLAM", "1650"));
        list.add(new ContentInfoBean("STANDARD CHARTERED", "1651"));
        list.add(new ContentInfoBean("OCBC BANK", "1652"));
        list.add(new ContentInfoBean("Muamalat Bank", "1868"));
        list.add(new ContentInfoBean("Agrobank", "1869"));
        list.add(new ContentInfoBean("Bank Rakyat", "1870"));
        list.add(new ContentInfoBean("K2Pay", "1884"));
        list.add(new ContentInfoBean("Maybank MASNI", "2064"));
        return list;
    }

    public void getBankSate() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankStatus, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankInfoBean>> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankInfoBean>>>() {
                }.getType());
                List<ContentInfoBean> bankInfoBeanList = new ArrayList<>();
                for (int i = 0; i < dataBean.getData().size(); i++) {
                    BankInfoBean bankInfoBean = dataBean.getData().get(i);
                    String online = bankInfoBean.getOnline();
                    if (online.equals("0")) {
                        String name = bankInfoBean.getName();
                        bankInfoBeanList.add(new ContentInfoBean(getRealBankName(name), bankInfoBean.getId_mod_bank()));
                    }
                }
                funplay26BountBankFragment.onGetBankState(bankInfoBeanList);
            }
        });
    }

    public String getRealBankName(String name) {
        switch (name) {
            case "Kbank":
                name = "Kasikorn Bank";
                break;
            case "Scb":
                name = "Siam Commercial Bank";
                break;
            case "Bbl":
                name = "Bangkok Bank";
                break;
            case "Ktb":
                name = "Krungthai Bank";
                break;
            case "Tmb":
                name = "TMB Bank";
                break;
            case "Bay":
                name = "Bank of Ayudhya";
                break;
            case "Gsb":
                name = "Government Savings Bank";
                break;
            case "Tbank":
                name = "Thanachart Bank";
                break;
            case "Baac":
                name = "Bank for Agriculture and Agricultural Co-operatives";
                break;
            case "Uob":
                name = "UOB Bank";
                break;
        }
        return name;
    }

    public void oneGold777SetBankName(final TextView tv, final String id) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> beanList = new ArrayList<>();
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                List<String> bankNameList = new ArrayList<>();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (!bankNameList.contains(bankAccountDetailBean.getBank_name())) {
                        bankNameList.add(bankAccountDetailBean.getBank_name());
                        beanList.add(bankAccountDetailBean);
                    }
                }
                for (int i = 0; i < beanList.size(); i++) {
                    if (beanList.get(i).getId_mod_bank().equals(id)) {
                        tv.setText(beanList.get(i).getBank_name());
                        break;
                    }
                }
            }
        });
    }

    public void getDepositBank() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<String> idModBankList = new ArrayList<String>();
                List<BankAccountDetailBean> beanList = new ArrayList<>();
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                String currency = funplay26BountBankFragment.getUserInfo().getId_mod_currency();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    String type = bankAccountDetailBean.getType();
                    if (type.equals("1") && bankAccountDetailBean.getCurrency().equals(currency)) {
                        String id_mod_bank = bankAccountDetailBean.getId_mod_bank();
                        if (!idModBankList.contains(id_mod_bank)) {
                            idModBankList.add(id_mod_bank);
                            beanList.add(bankAccountDetailBean);
                        }
                    }
                }
                funplay26BountBankFragment.onGetDepositBank(beanList);
            }
        });
    }

    public void goBindingBank(HashMap<String, String> p) {
        String url = WebSiteUrl.Dig88Url + "index.php?page=update_bank_account_submitter";
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                switch (orgData.getMsg()) {
                    case "1":
                        getVipInfoBean(funplay26BountBankFragment.getUserInfo());
                        break;
                    default:
                        funplay26BountBankFragment.onBoundFailed();
                        break;
                }
            }
        });
    }

    public void goBindingBank1(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.BindBankUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                switch (orgData.getMsg()) {
                    case "1":
                        getVipInfoBean(funplay26BountBankFragment.getUserInfo());
                        break;
                    case "-2":
                        funplay26BountBankFragment.onBoundFailed("Bank Account Number Already exists");
                        break;
                    default:
                        funplay26BountBankFragment.onBoundFailed();
                        break;
                }
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
                funplay26BountBankFragment.onBoundSuccess();
            }
        });
    }

    public String getLanguage() {
        String lg = AppTool.getAppLanguage(baseContext.getContext());
        if (TextUtils.isEmpty(lg)) {
            return "en";
        } else {
            switch (lg) {
                case "en":
                    return "en";
                case "th":
                    return "th";
                case "vn":
                    return "vn";
                case "zh":
                    return "cn";
                case "in":
                    return "id";
                case "kr":
                    return "kr";
                case "my":
                    return "ma";
                case "kh":
                    return "ca";
                default:
                    return "en";
            }
        }
    }
}
