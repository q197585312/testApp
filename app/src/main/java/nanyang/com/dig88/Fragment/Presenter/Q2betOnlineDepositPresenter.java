package nanyang.com.dig88.Fragment.Presenter;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.util.ArrayList;
import java.util.List;

import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.PromotionBean;
import nanyang.com.dig88.Fragment.Q2betOnlineDepositFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/8/8.
 */

public class Q2betOnlineDepositPresenter extends BaseRetrofitPresenter<Q2betOnlineDepositFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    Q2betOnlineDepositFragment q2betOnlineDepositFragment;

    public Q2betOnlineDepositPresenter(Q2betOnlineDepositFragment iBaseContext) {
        super(iBaseContext);
        q2betOnlineDepositFragment = iBaseContext;
    }

    public List<BankInfoBean> getDepositWayBank() {
        List<BankInfoBean> beanList = new ArrayList<>();
        if (BuildConfig.FLAVOR.equals("ttwin168")) {
            beanList.add(new BankInfoBean("mbb", "MBB"));
            beanList.add(new BankInfoBean("cimb", "CIMB"));
            beanList.add(new BankInfoBean("hlbb", "HLB"));
            beanList.add(new BankInfoBean("pbb", "PBB"));
        } else if (BuildConfig.FLAVOR.equals("club988")) {
            beanList.add(new BankInfoBean("cimb", "CIMB"));
            beanList.add(new BankInfoBean("mbb", "MBB"));
            beanList.add(new BankInfoBean("hlbb", "HLB"));
            beanList.add(new BankInfoBean("pbb", "PBB"));
        } else if (BuildConfig.FLAVOR.equals("mgold1")) {
            beanList.add(new BankInfoBean("KKR", "Kasikorn Bank"));
            beanList.add(new BankInfoBean("BBL", "Bangkok Bank"));
            beanList.add(new BankInfoBean("SCB", "Siam Commercial Bank"));
            beanList.add(new BankInfoBean("KTB", "Krungthai Bank"));
            beanList.add(new BankInfoBean("BOA", "Krungsri Bank"));
            beanList.add(new BankInfoBean("TMB", "TMB Bank Public Company Limited"));
            beanList.add(new BankInfoBean("GSB", "Government Savings Bank"));
            beanList.add(new BankInfoBean("CIMBT", "CIMB THAI"));
            beanList.add(new BankInfoBean("KNK", "Kiatnakin Bank"));
        } else if (BuildConfig.FLAVOR.equals("k9th")) {
            beanList.add(new BankInfoBean("4", "กรุงเทพ"));
            beanList.add(new BankInfoBean("7", "ไทยพานิชย์"));
            beanList.add(new BankInfoBean("10", "กสิกร"));
        } else {
            beanList.add(new BankInfoBean("TCB", "Techcombank"));
            beanList.add(new BankInfoBean("SACOM", "Sacombank"));
            beanList.add(new BankInfoBean("VCB", "Vietcombank"));
            if (BuildConfig.FLAVOR.equals("win3888")) {
                beanList.add(new BankInfoBean("ACB", "ACB - Asia Commercial Bank"));
            } else {
                beanList.add(new BankInfoBean("ACB", "Asia Commercial Bank"));
            }
            beanList.add(new BankInfoBean("DAB", "DongA Bank"));
            beanList.add(new BankInfoBean("VTB", "Vietinbank"));
            beanList.add(new BankInfoBean("BIDV", "Bank for Investment and Development of Vietnam"));
            beanList.add(new BankInfoBean("EXIM", "Eximbank Vietnam"));
        }
        return beanList;
    }

    public void getPromotion() {
        String param = "&web_id=" + WebSiteUrl.WebId + "&lang=" + getLanguage() + "&currency=" + q2betOnlineDepositFragment.getUserInfo().getId_mod_currency();
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.DepositPromotionUrl + param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                //{"code":"0","msg":"No Promotion"}
                List<ContentInfoBean> promotionList = new ArrayList<>();
                if (!data.contains("No Promotion")) {
                    PromotionBean promotionBean = gson.fromJson(data, PromotionBean.class);
                    List<PromotionBean.ListBean> list = promotionBean.getList();
                    for (int i = 0; i < list.size(); i++) {
                        PromotionBean.ListBean listBean = list.get(i);
                        String promosi_content = listBean.getPromosi_group_content();
                        if (promosi_content.contains("deposit") || promosi_content.contains("Deposit")) {
                            promotionList.add(new ContentInfoBean(listBean.getPromosi_title(), listBean.getPromosi_code()));
                        }
                    }
                }
                promotionList.add(new ContentInfoBean("Proceed without promotions", "0"));
                q2betOnlineDepositFragment.onGetPromotionData(promotionList);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public String getLanguage() {
        return Dig88Utils.getLanguage(q2betOnlineDepositFragment.getContext());
    }
}
