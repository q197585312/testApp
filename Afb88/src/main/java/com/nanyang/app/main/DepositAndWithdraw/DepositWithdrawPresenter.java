package com.nanyang.app.main.DepositAndWithdraw;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.DepositWithdrawParam;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawHistoryBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawTakeAwayBean;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class DepositWithdrawPresenter extends BaseRetrofitPresenter<DepositWithdrawBaseFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */

    private DepositWithdrawBaseFragment depositWithdrawBaseFragment;

    public DepositWithdrawPresenter(DepositWithdrawBaseFragment iBaseContext) {
        super(iBaseContext);
        depositWithdrawBaseFragment = iBaseContext;
    }

    public void getWithdrawData() {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("WithdrawH5GetTT", "wfTransferH5").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    WithdrawDataBean bean = gson.fromJson(jsonArrayData.get(0).toString(), WithdrawDataBean.class);
                    depositWithdrawBaseFragment.onGetWithdrawData(bean);
                }
            }
        });
    }

    public void submitWithdrawData(String amount) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("WithdrawH5Save", "wfTransferH5", amount, "", "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    WithdrawTakeAwayBean bean = null;
                    if (data.contains("Successful")) {
                        JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                        bean = gson.fromJson(jsonArrayData.get(0).toString(), WithdrawTakeAwayBean.class);
                    } else if (data.contains("Sorry")) {
                        bean = new WithdrawTakeAwayBean();
                        bean.setLblStatus("Sorry, you have already made (deposit or withdraw) request. You can (deposit or withdraw) again after your current request is processed. Thank you.");
                    } else {
                        bean = new WithdrawTakeAwayBean();
                        bean.setLblStatus("Failed");
                    }
                    depositWithdrawBaseFragment.onGetSubmitWithdrawData(bean);
                }
            }
        });
    }

    public void getWithdrawHistoryData(String fromDate, String toDate) {
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + new DepositWithdrawParam("Getdate", "wfTransferH5", fromDate, toDate, "w", "", "").getJson();
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData = jsonArray.getJSONArray(3);
                    JSONArray jsonArray2 = jsonArrayData.getJSONArray(0);
                    JSONArray jsonArray3 = jsonArray2.getJSONArray(2);
                    List<WithdrawHistoryBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArray3.length(); i++) {
                        JSONArray jsonArray1 = jsonArray3.getJSONArray(i);
                        WithdrawHistoryBean bean = new WithdrawHistoryBean(jsonArray1.getString(0), jsonArray1.getString(1), jsonArray1.getString(2), jsonArray1.getString(3));
                        list.add(bean);
                    }
                    depositWithdrawBaseFragment.onGetWithdrawHistoryData(list);
                }
            }
        });
    }
}
