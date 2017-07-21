package com.nanyang.app.main.center.Statement;

import com.nanyang.app.main.center.model.StatementListBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class SummaryBean {

    /**
     * Date : Last week summary
     * Stake : 2,200
     * ParamsUrl : AccMatchWL_App.aspx?userName=demoafbAi1&to=2017/07/14&from=2017/07/08
     * ValidAmount : 2,200
     * WL : -171.00
     * Com : 1.60
     * Settled : 0.00
     * CurrBalance : -536.67
     */

    private List<StatementListBean> summary;

    public List<StatementListBean> getSummary() {
        return summary;
    }

    public void setSummary(List<StatementListBean> summary) {
        this.summary = summary;
    }


}
