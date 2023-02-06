package nanyang.com.dig88.Activity.presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import nanyang.com.dig88.Activity.AffiliateRegisterActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.AffiliateDataBean;
import nanyang.com.dig88.Entity.AffiliateRegisterResultBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.ApiManager.getService;

/**
 * Created by Administrator on 2019/9/29.
 */

public class AffiliateRegisterPresenter extends BaseRetrofitPresenter<AffiliateRegisterActivity> {
    private static final char[] CHARS = {'0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9',
    };
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    AffiliateRegisterActivity activity;
    private Random random = new Random();

    public AffiliateRegisterPresenter(AffiliateRegisterActivity iBaseContext) {
        super(iBaseContext);
        activity = iBaseContext;
    }

    public void getInitData() {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("http", "1");
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.AffiliateGetDataUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                AffiliateDataBean affiliateDataBean = gson.fromJson(data, AffiliateDataBean.class);
                activity.onGetInitData(affiliateDataBean);
            }
        });
    }

    public void register(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.AffiliateRegisterUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                AffiliateRegisterResultBean bean = gson.fromJson(data, AffiliateRegisterResultBean.class);
                String returnMsg;
                switch (bean.getMsg()) {
                    case "1":
                        activity.finish();
                        returnMsg = activity.getString(R.string.Success);
                        break;
                    default:
                        returnMsg = bean.getError();
                        break;
                }
                ToastUtils.showShort(returnMsg);
            }
        });
    }

    public List<ContentInfoBean> getSelectList(AffiliateDataBean affiliateDataBean) {
        AffiliateDataBean.DataBean data = affiliateDataBean.getData();
        AffiliateDataBean.DataBean.DataSetBean data_set = data.getData_set();
        List<ContentInfoBean> list = new ArrayList<>();
        String only_win = data_set.getOnly_win();
        String turnover = data_set.getTurnover();
        String win_loss = data_set.getWin_loss();
        list.addAll(parseData(only_win, "Only Win", "ow_"));
        list.addAll(parseData(turnover, "Turnover Commision", "to_"));
        list.addAll(parseData(win_loss, "Win/Loss Sharing", "wl_"));
        return list;
    }

    private List<ContentInfoBean> parseData(String data, String typeName, String typeId) {
        List<ContentInfoBean> list = new ArrayList<>();
        String[] winLossSplit = data.split("-");
        for (int i = 0; i < winLossSplit.length; i++) {
            String s = winLossSplit[i];
            if (!s.equals("0")) {
                list.add(new ContentInfoBean(typeName + "(" + s + "%)", typeId + s));
            }
        }
        return list;
    }

    public List<ContentInfoBean> getDomainList(AffiliateDataBean affiliateDataBean) {
        List<ContentInfoBean> list = new ArrayList<>();
        AffiliateDataBean.DataBean data = affiliateDataBean.getData();
        List<AffiliateDataBean.DataBean.DomainSetBean> domain_set = data.getDomain_set();
        for (int i = 0; i < domain_set.size(); i++) {
            AffiliateDataBean.DataBean.DomainSetBean domainSetBean = domain_set.get(i);
            String domain = domainSetBean.getDomain();
            String price = domainSetBean.getPrice();
            list.add(new ContentInfoBean(domain + " (" + price + "$/" + activity.getString(R.string.month) + ")", domain));
        }
        return list;
    }

    public String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }
}
