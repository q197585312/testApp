package com.nanyang.app.main.home.sport.europe;

import com.nanyang.app.main.home.sport.main.AfbParseHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/3.
 */

public abstract class BallState extends SportState<BallInfo, SportContract.View<BallInfo>> {
    public BallState(SportContract.View<BallInfo> baseView) {
        super(baseView);
    }

    @Override
    protected BallInfo parseMatch(JSONArray matchArray, boolean notify) throws JSONException {
        return new AfbParseHelper<>().parseJsonArray(matchArray, notify);
    }

    @Override
    protected void updateAllDate(List<TableSportInfo<BallInfo>> allData) {
        List<TableSportInfo<BallInfo>> noRepeatAllData = handleRepeatData(allData);
        filterData(noRepeatAllData);
        showCurrentData();
    }

    private TableSportInfo<BallInfo> findRepeat(TableSportInfo<BallInfo> bTableSportInfo) {
        TableSportInfo<BallInfo> temp = new TableSportInfo<>(bTableSportInfo.getLeagueBean(), new ArrayList<BallInfo>());
        List<BallInfo> rows = bTableSportInfo.getRows();
        BallInfo b = rows.get(0);
        if (bTableSportInfo.getRows().size() <= 1) {
            return bTableSportInfo;
        }
        return doFindRepeat(temp, b, 1, rows);

    }

    private TableSportInfo<BallInfo> doFindRepeat(TableSportInfo<BallInfo> temp, BallInfo data, int i, List<BallInfo> rows) {
        String oldAwayName = data.getAway();
        String oldHomeName = data.getHome();
        String oldHomeGive = data.getIsHomeGive();
        BallInfo next = rows.get(i);
        BallInfo tempData;
        if (oldHomeName.equals(next.getHome()) && oldAwayName.equals(next.getAway()) ) {
            List<BallInfo> list = data.getRepeatRow();
            list.add(next);
            data.setRepeatRow(list);
            tempData = data;
        } else {
            temp.getRows().add(data);
            tempData = next;
        }

        if (i == rows.size() - 1) {
            temp.getRows().add(tempData);
            return temp;
        } else {
            return doFindRepeat(temp, tempData, i + 1, rows);
        }
    }

    protected List<TableSportInfo<BallInfo>> handleRepeatData(List<TableSportInfo<BallInfo>> allData) {
        List<TableSportInfo<BallInfo>> noRepeatList = new ArrayList<>();
        for (TableSportInfo<BallInfo> ballInfoTableSportInfo : allData) {
            noRepeatList.add(findRepeat(ballInfoTableSportInfo));
        }
        return noRepeatList;
    }
}
