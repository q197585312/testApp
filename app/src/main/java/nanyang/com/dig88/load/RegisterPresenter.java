package nanyang.com.dig88.load;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import nanyang.com.dig88.Activity.RegisterActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.RegistInfo;
import nanyang.com.dig88.Entity.SendTelBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/6/21.
 */

public class RegisterPresenter extends BaseRetrofitPresenter<RegisterActivity> {
    private static final char[] CHARS = {'0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9',
    };
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    RegisterActivity registerActivity;
    private Random random = new Random();

    public RegisterPresenter(RegisterActivity iBaseContext) {
        super(iBaseContext);
        registerActivity = iBaseContext;
    }

    public void checkUser(String username) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("username", username);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.CheckUser, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                registerActivity.onGetCheckUserMsg(dataBean);
            }
        });
    }

    public void getDepositBank(final String currency) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                List<String> idModBankList = new ArrayList<>();
                List<BankInfoBean> beanList = new ArrayList<>();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (bankAccountDetailBean.getType().equals("2") && bankAccountDetailBean.getCurrency().equals(currency)) {
                        String account = bankAccountDetailBean.getAccount();
                        String no = bankAccountDetailBean.getNo();
                        String idModBank = bankAccountDetailBean.getId_mod_bank();
                        if (!account.equals("000000") && !no.equals("000000")) {
                            if (!idModBankList.contains(idModBank)) {
                                idModBankList.add(idModBank);
                                BankInfoBean bankInfoBean = new BankInfoBean();
                                bankInfoBean.setId_mod_bank(bankAccountDetailBean.getId_mod_bank());
                                bankInfoBean.setName(bankAccountDetailBean.getBank_name());
                                beanList.add(bankInfoBean);
                            }
                        }
                    }
                }
                registerActivity.onGetBankStateList(beanList);
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
                if (BuildConfig.FLAVOR.equals("coin365bet")) {
                    for (int i = 0; i < dataBean.getData().size(); i++) {
                        BankInfoBean bankInfoBean = dataBean.getData().get(i);
                        if (i > 0) {
                            bankInfoBeanList.add(bankInfoBean);
                        }
                    }
                } else if (BuildConfig.FLAVOR.equals("henbet")) {
                    for (int i = 0; i < dataBean.getData().size(); i++) {
                        BankInfoBean bankInfoBean = dataBean.getData().get(i);
                        if (!bankInfoBean.getId_mod_bank().equals("2427")) {
                            bankInfoBeanList.add(bankInfoBean);
                        }
                    }
                } else if (BuildConfig.FLAVOR.equals("fafa191")) {
                    for (int i = 0; i < dataBean.getData().size(); i++) {
                        BankInfoBean bankInfoBean = dataBean.getData().get(i);
                        if (i < 10 || bankInfoBean.getId_mod_bank().equals("2548")) {
                            bankInfoBeanList.add(bankInfoBean);
                        }
                    }
                } else if (BuildConfig.FLAVOR.equals("dewancash")) {
                    for (int i = 0; i < dataBean.getData().size(); i++) {
                        BankInfoBean bankInfoBean = dataBean.getData().get(i);
                        if (!bankInfoBean.getId_mod_bank().equals("2876") && !bankInfoBean.getId_mod_bank().equals("2877")) {
                            bankInfoBeanList.add(bankInfoBean);
                        }
                    }
                } else {
                    bankInfoBeanList = dataBean.getData();
                }
                registerActivity.onGetBankStateList(bankInfoBeanList);
            }
        });
    }

    public void getBankAcc(BaseConsumer<String> baseConsumer) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), baseConsumer);
    }

    public void getAccountDetail(String affiliateId) {
        String url = WebSiteUrl.GetBankAccount;
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        if (!TextUtils.isEmpty(affiliateId)) {
            p.put("affiliate_id", affiliateId);
            url = WebSiteUrl.GetAffiliateBankAccount;
        }
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> bankAccountDetailBeanList = dataBean.getData();
                registerActivity.onGetAccountDetail(bankAccountDetailBeanList);
            }
        });
    }

    public List<BankAccountDetailBean> filterBankDetail(List<BankAccountDetailBean> bankAccountDetailBeanList) {
        List<String> list = new ArrayList<>();
        List<BankAccountDetailBean> bankAccountDetailBeanList1 = new ArrayList<>();
        for (int i = 0; i < bankAccountDetailBeanList.size(); i++) {
            BankAccountDetailBean bankAccountDetailBean = bankAccountDetailBeanList.get(i);
            String currency = bankAccountDetailBean.getCurrency();
            if (!list.contains(currency) && !currency.equals("18")) {
                list.add(currency);
                bankAccountDetailBeanList1.add(bankAccountDetailBean);
            }
        }
        return bankAccountDetailBeanList1;
    }

    public void register(HashMap<String, String> p) {
        p.put("ip", SharePreferenceUtil.getString(registerActivity, "IP"));
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Register, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<RegistInfo> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<RegistInfo>>() {
                }.getType());
                registerActivity.onGetRegisterData(dataBean);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });
    }

    public String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public List<String> getDayData() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else {
                list.add(i + "");
            }
        }
        return list;
    }

    public List<String> getMonthData() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            if (i < 10) {
                list.add("0" + i);
            } else {
                list.add(i + "");
            }
        }
        return list;
    }

    public List<String> getYearData() {
        List<String> list = new ArrayList<>();
        for (int i = 1950; i <= 2000; i++) {
            list.add(i + "");
        }
        return list;
    }

    public void getAfbcashBank() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.GetBankAccount, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<List<BankAccountDetailBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<BankAccountDetailBean>>>() {
                }.getType());
                List<BankAccountDetailBean> beanList = new ArrayList<>();
                List<BankAccountDetailBean> detailBeanList = orgData.getData();
                for (int i = 0; i < detailBeanList.size(); i++) {
                    BankAccountDetailBean bankAccountDetailBean = detailBeanList.get(i);
                    if (bankAccountDetailBean.getType().equals("2") && bankAccountDetailBean.getCurrency().equals("11")) {
                        beanList.add(bankAccountDetailBean);
                    }
                }
                registerActivity.onGetAccountDetail(beanList);
            }
        });
    }

    //http://app.info.dig88api.com/index.php?page=send_sms_submitter&tel=066717205&method=send
    public void sendTel(String tel) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("tel", tel);
        p.put("method", "send");
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.SendTelUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                SendTelBean sendTelBean = gson.fromJson(data, SendTelBean.class);
                registerActivity.onGetSendTel(sendTelBean);//066717205
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });
    }

    public void verificationCode(String tel, String code) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("tel", tel);
        p.put("verified_number", code);
        p.put("method", "verify");
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.SendTelUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                SendTelBean sendTelBean = gson.fromJson(data, SendTelBean.class);
                registerActivity.onGetVerificationCode(sendTelBean);
            }
        });
    }

    public void getFastRegisterCode(BaseConsumer<String> baseConsumer) {
        String p = "&web_id=" + WebSiteUrl.WebId + "&length=5";
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.RegisterVerifyCodeUrl + p), baseConsumer);
    }
}
