package com.nanyang.app.main.home.sport.europe;

import android.app.Activity;
import android.graphics.Color;

import com.nanyang.app.Utils.BetGoalWindowUtils;
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

    int n = 0;

    @Override
    protected void updateAllDate(List<TableSportInfo<BallInfo>> allData) {


      /*  List<TableSportInfo<BallInfo>> noRepeatAllData = handleRepeatData(allData);
        filterData(noRepeatAllData);*/
        filterData(allData);
        showCurrentData();

        Activity activity = getBaseView().getIBaseContext().getBaseActivity();
        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<BallInfo> ballInfoTableSportInfo = allData.get(i);
            List<BallInfo> rows = ballInfoTableSportInfo.getRows();
            for (int j = 0; j < rows.size(); j++) {
                BallInfo item = rows.get(j);
                int homeTextColor;
                int awayTextColor;
                String isHomeGive = item.getIsHomeGive();
                if (isHomeGive.equals("1")) {
                    homeTextColor = Color.RED;
                    awayTextColor = Color.BLACK;
                } else {
                    homeTextColor = Color.BLACK;
                    awayTextColor = Color.RED;
                }
                String sHome = item.getRunHomeScore();
                String sAway = item.getRunAwayScore();
                if (item.isHomeScoreBigger()) {

                    item.setHomeScoreTextColor(Color.RED);
                    BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 0);
                    item.setHomeScoreBigger(false);
                }
                if (item.isAwayScoreBigger()) {

                    item.setAwayScoreTextColor(Color.RED);
                    BetGoalWindowUtils.showGoalWindow(activity, item.getModuleTitle(), item.getHome(), homeTextColor, item.getAway(), awayTextColor, sHome, sAway, 1);
                    item.setAwayScoreBigger(false);
                }
            }
        }
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
        if (i == 1) {
            data.setRepeatRow(new ArrayList<BallInfo>());
        }
        BallInfo next = rows.get(i);
        next.setRepeatRow(null);
        BallInfo tempData;
    /*    LogUtil.e("Addition", "getModuleTitle:" + temp.getLeagueBean().getModuleTitle() + ",getRowsSize:" + temp.getRows().size());
        LogUtil.e("Addition", "data.getHome:" + data.getHome() + ",getRepeatRowSize:" + (data.getRepeatRow() == null ? "null" : data.getRepeatRow().size()));
        LogUtil.e("Addition", "i:" + i + ",next.getHome:" + next.getHome() + "getRepeatRowSize:" + (next.getRepeatRow() == null ? "null" : next.getRepeatRow().size()));*/
        if (oldHomeName.equals(next.getHome()) && oldAwayName.equals(next.getAway())) {
//            LogUtil.e("Addition", "home,相等了");
            List<BallInfo> repeatRow = data.getRepeatRow();
            if (repeatRow == null || repeatRow.size() == 0) {
/*                LogUtil.e("Addition", "null,第一个 添加自己");*/
                repeatRow = new ArrayList<>();
                repeatRow.add(data);
            }
            next.setModuleId(data.getModuleId());
            next.setModuleTitle(data.getModuleTitle());
            repeatRow.add(next);

            data.setRepeatRow(repeatRow);
            tempData = data;
         /*   LogUtil.e("Addition", "tempData=data,tempData.getRepeatRow().size:" + tempData.getRepeatRow().size());*/

        } else {
       /*     LogUtil.e("Addition", "home,不等了");*/
            temp.getRows().add(data);
            tempData = next;
   /*         LogUtil.e("Addition", "tempData=next,tempData.getRepeatRow().size:" + (tempData.getRepeatRow() == null ? "null" : tempData.getRepeatRow().size()));*/
        }

        if (i == rows.size() - 1) {
            temp.getRows().add(tempData);
          /*  LogUtil.e("Addition", "最后一个了i:" + i + "tempData.getRepeatRow().size:" + (tempData.getRepeatRow() == null ? "null" : tempData.getRepeatRow().size()));
            LogUtil.e("Addition", "最后一个了i:" + i + "temp.getRows().size:" + (temp.getRows() == null ? "null" : temp.getRows().size()));*/
            return temp;
        } else {
           /* LogUtil.e("Addition", "开始下一个i:" + (i + 1) + "temp.getRows().size:" + (tempData.getRepeatRow() == null ? "null" : tempData.getRepeatRow().size()));*/
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
