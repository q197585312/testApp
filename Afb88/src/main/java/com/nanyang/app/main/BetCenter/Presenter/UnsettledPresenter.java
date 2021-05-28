package com.nanyang.app.main.BetCenter.Presenter;

import android.os.Handler;

import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.UnsettledFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 47184 on 2019/4/7.
 */

public class UnsettledPresenter extends BaseRetrofitPresenter<UnsettledFragment> {

    private UnsettledFragment unsettledFragment;
    private String type = "W";

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public UnsettledPresenter(UnsettledFragment iBaseContext) {
        super(iBaseContext);
        unsettledFragment = iBaseContext;
    }

    public void getRunningList(String type) {
        this.type = type;
        unsettledFragment.showLoadingDialog();
        settledData();
    }

    private void settledData() {
        BaseParamBean bean = new BaseParamBean("GetTable", "wfRunningH50", type, "1");
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + bean.getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                LogUtil.d("onBaseGetData", "GetTable:" + data);
                JSONArray jsonArray = new JSONArray(data);
                JSONArray jsonData = jsonArray.getJSONArray(3);
                JSONArray jsonArray1 = jsonData.getJSONArray(0);
                JSONArray jsonArray2 = jsonArray1.getJSONArray(2);
                List<RunningBean> dataList = new ArrayList<RunningBean>();
                for (int i = 0; i < jsonArray2.length(); i++) {
                    JSONArray ja = jsonArray2.getJSONArray(i);
                    String[] str = new String[ja.length()];
                    for (int j = 0; j < ja.length(); j++) {
                        str[j] = ja.getString(j);
                    }
                    RunningBean rb = new RunningBean(str);
                    dataList.add(rb);
                }
                unsettledFragment.setRvlist(dataList);
                if (!unsettledFragment.parentHidden) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            settledData();
                        }
                    }, 30000);
                }

            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void getParList(String id, BaseConsumer<String> baseConsumer) {
        BaseParamBean bean = new BaseParamBean("GetMatch", "wfRunningH50", id, "2");
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + bean.getJson()), baseConsumer);
    }

    public void getParList2(String socTransId, String type, BaseConsumer<String> baseConsumer) {
        BaseParamBean bean = new BaseParamBean("GetMatchDetail", "wfRunningH50", socTransId, type, "", "", 0);
        doRetrofitApiOnUiThread(ApiServiceKt.Companion.getInstance().getData(AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + bean.getJson()), baseConsumer);
    }

    public List<StatementOpen2ListDataBean> getBeanList2(JSONArray jsonArray) throws JSONException {
        List<StatementOpen2ListDataBean> list = new ArrayList<>();
        JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
        JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
        JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
        for (int i = 0; i < jsonArrayData4.length(); i++) {
            JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
            StatementOpen2ListDataBean bean = new StatementOpen2ListDataBean(jsonArrayArr.getInt(0), jsonArrayArr.getString(1),
                    jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                    jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                    jsonArrayArr.getInt(10), jsonArrayArr.getInt(11), jsonArrayArr.getString(12), jsonArrayArr.getDouble(13),
                    jsonArrayArr.getInt(14), jsonArrayArr.getInt(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                    jsonArrayArr.getString(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                    jsonArrayArr.getString(22), jsonArrayArr.getString(23));
            list.add(bean);
        }
        return list;
    }

    public List<StatementOpen3ListDataBean> getBeanList3(JSONArray jsonArray) throws JSONException {
        List<StatementOpen3ListDataBean> list = new ArrayList<>();
        JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
        JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
        JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
        for (int i = 0; i < jsonArrayData4.length(); i++) {
            JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
            StatementOpen3ListDataBean bean = new StatementOpen3ListDataBean(jsonArrayArr.getInt(0), jsonArrayArr.getString(1),
                    jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                    jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                    jsonArrayArr.getInt(10), jsonArrayArr.getInt(11), jsonArrayArr.getString(12), jsonArrayArr.getDouble(13),
                    jsonArrayArr.getInt(14), jsonArrayArr.getInt(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                    jsonArrayArr.getDouble(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                    jsonArrayArr.getString(22), jsonArrayArr.getString(23), jsonArrayArr.getString(24));
            list.add(bean);
        }
        return list;
    }
}
