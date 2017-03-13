package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class TableModuleBean implements Serializable {
    LeagueBean leagueBean;

    List<MatchBean> rows;

    public LeagueBean getLeagueBean() {
        return leagueBean;
    }

    public void setLeagueBean(LeagueBean leagueBean) {
        this.leagueBean = leagueBean;
    }

    public List<MatchBean> getRows() {
        return rows;
    }

    public void setRows(List<MatchBean> rows) {
        this.rows = rows;
    }

    public TableModuleBean(LeagueBean leagueBean, List<MatchBean> rows) {
        this.leagueBean = leagueBean;
        this.rows = rows;
    }
}
