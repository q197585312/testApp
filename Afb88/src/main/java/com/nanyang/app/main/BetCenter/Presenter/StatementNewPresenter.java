package com.nanyang.app.main.BetCenter.Presenter;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.main.BetCenter.Bean.BaseParamBean;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen2ListDataBean;
import com.nanyang.app.main.BetCenter.Bean.StatementOpen3ListDataBean;
import com.nanyang.app.main.BetCenter.StatementNewFragment;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewPresenter extends BaseRetrofitPresenter<StatementNewFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    private StatementNewFragment statementNewFragment;

    public StatementNewPresenter(StatementNewFragment iBaseContext) {
        super(iBaseContext);
        statementNewFragment = iBaseContext;
    }

    public void getStatementData() {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTT", "wfStatement2H50").getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(1);
                    JSONObject jsonObject = jsonArrayData2.getJSONObject(1);
                    StatementFirstBean statementFirstBean = gson.fromJson(jsonObject.toString(), StatementFirstBean.class);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<StatementListDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        StatementListDataBean bean = new StatementListDataBean(jsonArrayArr.getString(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8));
                        list.add(bean);
                    }
                    statementNewFragment.onGetStatementData(statementFirstBean, handleStatementData(list));
                }
            }
        });
    }

    public void getStatementLastWeekData(String startDate, String endDate) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTableT", "wfStatement2H50", endDate, startDate, "", "").getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = data.replace("&nbsp;", " ");
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData3 = jsonArrayData2.getJSONArray(2);
                    List<StatementListDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData3.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData3.getJSONArray(i);
                        StatementListDataBean bean = new StatementListDataBean(jsonArrayArr.getString(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7));
                        list.add(bean);
                    }
                    statementNewFragment.onGetStatementLastWeekData(handleStatementData(list));
                }
            }
        });
    }

    public void getStatemenOpen1Data(String date) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetTableD", "wfStatement2H50", date, "", "").getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = data.replace("&nbsp;", " ");
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<StatementListDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        StatementListDataBean bean = new StatementListDataBean(jsonArrayArr.getString(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                                jsonArrayArr.getString(10), jsonArrayArr.getString(11), jsonArrayArr.getString(12), jsonArrayArr.getString(13),
                                jsonArrayArr.getString(14), jsonArrayArr.getString(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                                jsonArrayArr.getString(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                                jsonArrayArr.getString(22), jsonArrayArr.getString(23), jsonArrayArr.getString(24), jsonArrayArr.getString(25));
                        list.add(bean);
                    }
                    statementNewFragment.onGetStatementOpen1Data(list);
                }
            }
        });
    }

    public void getStatementOpen2Data(final String id, final String transType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetMatch", "wfRunningH50", id, "", "", -1).getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = data.replace("&nbsp;", " ");
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<StatementOpen2ListDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        StatementOpen2ListDataBean bean = new StatementOpen2ListDataBean(jsonArrayArr.getInt(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                                jsonArrayArr.getInt(10), jsonArrayArr.getInt(11), jsonArrayArr.getString(12), jsonArrayArr.getDouble(13),
                                jsonArrayArr.getInt(14), jsonArrayArr.getInt(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                                jsonArrayArr.getString(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                                jsonArrayArr.getString(22), "");
                        list.add(bean);
                    }
                    statementNewFragment.onGetStatementOpen2Data(list, id, transType);
                }
            }
        });
    }

    public void getStatementOpen3Data(String id, String transType) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(BuildConfig.HOST_AFB + "H50/Pub/pcode.axd?_fm=" + new BaseParamBean("GetMatchDetail", "wfRunningH50", id, transType, "", "", -1).getJson()), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = data.replace("&nbsp;", " ");
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(0);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    List<StatementOpen3ListDataBean> list = new ArrayList<>();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        StatementOpen3ListDataBean bean = new StatementOpen3ListDataBean(jsonArrayArr.getInt(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8), jsonArrayArr.getString(9),
                                jsonArrayArr.getInt(10), jsonArrayArr.getInt(11), jsonArrayArr.getString(12), jsonArrayArr.getDouble(13),
                                jsonArrayArr.getInt(14), jsonArrayArr.getInt(15), jsonArrayArr.getString(16), jsonArrayArr.getString(17),
                                jsonArrayArr.getDouble(18), jsonArrayArr.getString(19), jsonArrayArr.getString(20), jsonArrayArr.getString(21),
                                jsonArrayArr.getString(22), jsonArrayArr.getString(23),"");
                        list.add(bean);
                    }
                    statementNewFragment.onGetStatementOpen3Data(list);
                }
            }
        });
    }

    private List<StatementListDataBean> handleStatementData(List<StatementListDataBean> statementListDataBeanList) {
        List<StatementListDataBean> list = new ArrayList<>();
        for (int i = 0; i < statementListDataBeanList.size(); i++) {
            StatementListDataBean bean = statementListDataBeanList.get(i);
            String index0 = bean.getIndex0();
            String index1 = bean.getIndex1();
            String index2 = bean.getIndex2();
            String index6 = bean.getIndex6();
            if (i == 0) {
                if (index0.equals("2")) {
                    continue;
                }
            } else {
                String lastIndex1 = statementListDataBeanList.get(i - 1).getIndex1();
                if (index0.equals("2") && lastIndex1.equals(index1)) {
                    continue;
                }
            }
            if (index2.equals("0") && index6.equals("0")) {
                continue;
            }
            list.add(bean);
        }
        return list;
    }
}
