package nanyang.com.dig88.Fragment.Presenter;

import android.text.TextUtils;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import java.util.ArrayList;
import java.util.List;

import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.PromotionBean;
import nanyang.com.dig88.Fragment.Mcd88OnlineDepositFragment;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/8/8.
 */

public class Mcd88OnlineDepositPresenter extends BaseRetrofitPresenter<Mcd88OnlineDepositFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    Mcd88OnlineDepositFragment mcd88OnlineDepositFragment;

    public Mcd88OnlineDepositPresenter(Mcd88OnlineDepositFragment iBaseContext) {
        super(iBaseContext);
        mcd88OnlineDepositFragment = iBaseContext;
    }

    public void getPromotion() {
        String param = "&web_id=" + WebSiteUrl.WebId + "&lang=" + getLang() + "&currency=" + mcd88OnlineDepositFragment.getUserInfo().getId_mod_currency();
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.DepositPromotionUrl + param), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                PromotionBean promotionBean = gson.fromJson(data, PromotionBean.class);
                List<PromotionBean.ListBean> list = promotionBean.getList();
                List<ContentInfoBean> promotionList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    PromotionBean.ListBean listBean = list.get(i);
                    String promosi_content = listBean.getPromosi_group_content();
                    if (promosi_content.contains("deposit") && mcd88OnlineDepositFragment.getUserInfo().getMem_level().equals(listBean.getRemark2())) {
                        promotionList.add(new ContentInfoBean(listBean.getPromosi_title(), listBean.getPromosi_code()));
                    }
                }
                promotionList.add(new ContentInfoBean("Proceed without promotions", "0"));
                mcd88OnlineDepositFragment.onGetPromotionData(promotionList);
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public String getLang() {
        String lang = "en";
        String localLanguage = mcd88OnlineDepositFragment.getLocalLanguage();
        if (!TextUtils.isEmpty(localLanguage)) {
            switch (localLanguage) {
                case "zh":
                    lang = "cn";
                    break;
                case "en":
                    lang = "en";
                    break;
                case "kh":
                    lang = "kh";
                    break;
                case "kr":
                    lang = "kr";
                    break;
                case "ms":
                    lang = "ma";
                    break;
                case "th":
                    lang = "th";
                    break;
                case "vn":
                    lang = "vn";
                    break;
                case "in":
                    lang = "id";
                    break;
            }
        } else {
            lang = "en";
        }
        return lang;
    }
}
