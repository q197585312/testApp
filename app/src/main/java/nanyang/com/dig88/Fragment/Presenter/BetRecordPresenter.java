package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.Afb2BetRecordBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.IbcBetRecordBean;
import nanyang.com.dig88.Entity.ReportFormBean;
import nanyang.com.dig88.Entity.SboBetRecordBean;
import nanyang.com.dig88.Fragment.BetRecordFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/9/10.
 */

public class BetRecordPresenter extends BaseRetrofitPresenter<BetRecordFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    BetRecordFragment betRecordFragment;

    public BetRecordPresenter(BetRecordFragment iBaseContext) {
        super(iBaseContext);
        betRecordFragment = iBaseContext;
    }

    public List<ContentInfoBean> getContentList() {
        List<ContentInfoBean> list;
        list = Arrays.asList(
                new ContentInfoBean(betRecordFragment.getString(R.string.bet_list), WebSiteUrl.BetRecordList, AppConfig.BetRecord_betList),
                new ContentInfoBean("AFB2 " + betRecordFragment.getString(R.string.sports), WebSiteUrl.AfbRecordList, AppConfig.BetRecord_afb2),
                new ContentInfoBean("AFB " + betRecordFragment.getString(R.string.sports), WebSiteUrl.AfbRecordList, AppConfig.BetRecord_afb),
                new ContentInfoBean("IBC " + betRecordFragment.getString(R.string.sports), WebSiteUrl.SabaRecordList, AppConfig.BetRecord_ibc),
                new ContentInfoBean("SBO " + betRecordFragment.getString(R.string.sports), WebSiteUrl.SboRecordList, AppConfig.BetRecord_sbo)
        );
        return list;
    }

    public void getBetListData(final ContentInfoBean bean) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(bean.getContentId(), getParam(bean.getContentType())), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                switch (bean.getContentType()) {
                    case AppConfig.BetRecord_betList:
                        NyBaseResponse<List<ReportFormBean>> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<List<ReportFormBean>>>() {
                        }.getType());
                        List<ReportFormBean> dataList = orgData.getData();
                        if (dataList == null || dataList.size() == 0) {
                            dataList = new ArrayList<>();
                            ReportFormBean bean = new ReportFormBean();
                            bean.setBet_amount("-1");
                            dataList.add(bean);
                        }
                        betRecordFragment.onGetBetListData(dataList);
                        break;
                    case AppConfig.BetRecord_afb2:
                        Afb2BetRecordBean afb2BetRecordBean = gson.fromJson(data, Afb2BetRecordBean.class);
                        List<Afb2BetRecordBean.DataBean> afb2List = afb2BetRecordBean.getData();
                        if (afb2List == null || afb2List.size() == 0) {
                            afb2List = new ArrayList<>();
                            Afb2BetRecordBean.DataBean dataBean = new Afb2BetRecordBean.DataBean();
                            dataBean.setBet_amount("-1");
                            afb2List.add(dataBean);
                        }
                        betRecordFragment.onGetAfb2Data(afb2List);
                        break;
                    case AppConfig.BetRecord_afb:
                        Afb2BetRecordBean afbBetRecordBean = gson.fromJson(data, Afb2BetRecordBean.class);
                        List<Afb2BetRecordBean.DataBean> afbList = afbBetRecordBean.getData();
                        if (afbList == null || afbList.size() == 0) {
                            afbList = new ArrayList<>();
                            Afb2BetRecordBean.DataBean dataBean = new Afb2BetRecordBean.DataBean();
                            dataBean.setBet_amount("-1");
                            afbList.add(dataBean);
                        }
                        betRecordFragment.onGetAfbData(afbList);
                        break;
                    case AppConfig.BetRecord_ibc:
                        IbcBetRecordBean ibcBetRecordBean = gson.fromJson(data, IbcBetRecordBean.class);
                        List<IbcBetRecordBean.DataBean> ibcList = ibcBetRecordBean.getData();
                        if (ibcList == null || ibcList.size() == 0) {
                            ibcList = new ArrayList<>();
                            IbcBetRecordBean.DataBean dataBean = new IbcBetRecordBean.DataBean();
                            dataBean.setBet_amount("-1");
                            ibcList.add(dataBean);
                        }
                        betRecordFragment.onGetIbcData(ibcList);
                        break;
                    case AppConfig.BetRecord_sbo:
                        SboBetRecordBean sboBetRecordBean = gson.fromJson(data, SboBetRecordBean.class);
                        List<SboBetRecordBean.DataBean> sboList = sboBetRecordBean.getData();
                        if (sboList == null || sboList.size() == 0) {
                            sboList = new ArrayList<>();
                            SboBetRecordBean.DataBean dataBean = new SboBetRecordBean.DataBean();
                            dataBean.setBet_amount("-1");
                            sboList.add(dataBean);
                        }
                        betRecordFragment.onGetSboData(sboList);
                        break;
                }
            }
        });
    }

    private HashMap<String, String> getParam(String type) {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", betRecordFragment.getUserInfo().getUser_id());
        p.put("session_id", betRecordFragment.getUserInfo().getSession_id());
        if (type.equals(AppConfig.BetRecord_afb2)) {
            p.put("method", "afb2");
        }
        return p;
    }
}
