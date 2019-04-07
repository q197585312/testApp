package com.nanyang.app.main.BetCenter.Presenter;

import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.Bean.RunningBean;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.UnsettledFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/4/7.
 */

public class UnsettledPresenter extends BaseRetrofitPresenter<UnsettledFragment> {

    private UnsettledFragment unsettledFragment;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    public UnsettledPresenter(UnsettledFragment iBaseContext) {
        super(iBaseContext);
        unsettledFragment = iBaseContext;
    }

    public void getRunningList(String type){
        BaseParamBean bean = new BaseParamBean("GetTable","wfRunningH50",type);
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + bean.getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);
                JSONArray jsonData = jsonArray.getJSONArray(3);
                JSONArray jsonArray1 = jsonData.getJSONArray(0);
                JSONArray jsonArray2 = jsonArray1.getJSONArray(2);
                List<RunningBean> dataList = new ArrayList<RunningBean>();
                for (int i=0;i<jsonArray2.length();i++){
                    JSONArray ja = jsonArray2.getJSONArray(i);
                    String[] str = new String[ja.length()];
                    for(int j = 0;j<ja.length();j++){
                        str[j] = ja.getString(j);
                    }
                    RunningBean rb = new RunningBean(str);
                    dataList.add(rb);
                }
                unsettledFragment.setRvlist(dataList);
            }
        });
    }
}
