package com.nanyang.app.main.home.sport.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/11/14.
 */
public class TableSportInfo<T extends SportInfo> implements Serializable {
    LeagueBean leagueBean;

    List<T> rows;

    public LeagueBean getLeagueBean() {
        return leagueBean;
    }

    public void setLeagueBean(LeagueBean leagueBean) {
        this.leagueBean = leagueBean;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public TableSportInfo(LeagueBean leagueBean, List<T> rows) {
        this.leagueBean = leagueBean;
        this.rows = rows;
    }
}
