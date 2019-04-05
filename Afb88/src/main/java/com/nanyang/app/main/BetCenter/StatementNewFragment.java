package com.nanyang.app.main.BetCenter;

import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.R;
import com.nanyang.app.main.BetCenter.Bean.StatementFirstBean;
import com.nanyang.app.main.BetCenter.Bean.StatementListDataBean;
import com.nanyang.app.main.BetCenter.Presenter.StatementNewPresenter;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/4/4.
 */

public class StatementNewFragment extends BaseFragment<StatementNewPresenter> {
    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_statement_new;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new StatementNewPresenter(this));
        getStatementData();
        statementListDataBeanList = new ArrayList<>();
    }

    StatementFirstBean personalInfo;
    List<StatementListDataBean> statementListDataBeanList;

    private void getStatementData() {
        presenter.getStatementDate(new BaseConsumer<String>(mContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                String updateString = AfbUtils.delHTMLTag(data);
                JSONArray jsonArray = new JSONArray(updateString);
                if (jsonArray.length() > 3) {
                    JSONArray jsonArrayData1 = jsonArray.getJSONArray(3);
                    JSONArray jsonArrayData2 = jsonArrayData1.getJSONArray(1);
                    JSONObject jsonObject = jsonArrayData2.getJSONObject(1);
                    personalInfo = gson.fromJson(jsonObject.toString(), StatementFirstBean.class);
                    JSONArray jsonArrayData4 = jsonArrayData2.getJSONArray(2);
                    statementListDataBeanList.clear();
                    for (int i = 0; i < jsonArrayData4.length(); i++) {
                        JSONArray jsonArrayArr = jsonArrayData4.getJSONArray(i);
                        StatementListDataBean bean = new StatementListDataBean(jsonArrayArr.getString(0), jsonArrayArr.getString(1),
                                jsonArrayArr.getString(2), jsonArrayArr.getString(3), jsonArrayArr.getString(4), jsonArrayArr.getString(5),
                                jsonArrayArr.getString(6), jsonArrayArr.getString(7), jsonArrayArr.getString(8));
                        statementListDataBeanList.add(bean);
                    }
                }
            }
        });
    }

    @Override
    public void refreshData() {
        getStatementData();
    }

    @Override
    public void initView() {
        super.initView();
    }
}
