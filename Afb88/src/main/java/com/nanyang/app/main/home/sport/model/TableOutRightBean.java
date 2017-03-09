package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class TableOutRightBean implements Serializable {
    LeagueBean leagueBean;

    List<OutRightMatchBean> rows;

    public LeagueBean getLeagueBean() {
        return leagueBean;
    }

    public void setLeagueBean(LeagueBean leagueBean) {
        this.leagueBean = leagueBean;
    }

    public List<OutRightMatchBean> getRows() {
        return rows;
    }

    public void setRows(List<OutRightMatchBean> rows) {
        this.rows = rows;
    }

    public TableOutRightBean(LeagueBean leagueBean, List<OutRightMatchBean> rows) {
        this.leagueBean = leagueBean;
        this.rows = rows;
    }
}
