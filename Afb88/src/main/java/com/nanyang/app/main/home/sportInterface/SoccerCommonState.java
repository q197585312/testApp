package com.nanyang.app.main.home.sportInterface;

import android.support.annotation.NonNull;

import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SoccerCommonState extends SoccerState<SportContract.View, TableSportInfo<SoccerCommonInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;

    @Override
    public void showData() {
           listData=toMatchList(pageData);
    }




    protected List<SoccerCommonInfo> toMatchList(List<TableSportInfo<SoccerCommonInfo>> pageList) {
        List<MatchBean> pageMatch = new ArrayList<>();

        for (int i = 0; i < pageList.size(); i++) {
            TableModuleBean item = pageList.get(i);
            List<MatchBean> items = item.getRows();
            for (int j = 0; j < items.size(); j++) {
                MatchBean cell = item.getRows().get(j);
                if (j == 0) {
                    cell.setType(MatchBean.Type.TITLE);
                } else {
                    cell.setType(MatchBean.Type.ITME);
                }
                cell.setLeagueBean(item.getLeagueBean());
                pageMatch.add(cell);
            }
        }
        return pageMatch;
    }
    @Override
    public boolean isCollection() {
        return isCollection;
    }
    public void clickCollection(){
        isCollection=!isCollection;
        initAllData(allData);
    }
    @Override
    protected List<TableSportInfo<SoccerCommonInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<SoccerCommonInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<SoccerCommonInfo> matchList = new ArrayList<>();
                JSONArray jsonArray3 = dataListArray.getJSONArray(i);
                if (jsonArray3.length() > 1) {
                    JSONArray LeagueArray = jsonArray3.getJSONArray(0);
                    if (LeagueArray.length() > 1) {
                        leagueBean = new LeagueBean(LeagueArray.get(0).toString(), LeagueArray.getString(1));
                    } else {
                        continue;
                    }
                    JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                    if (LeagueMatchArray.length() > 0) {
                        for (int j = 0; j < LeagueMatchArray.length(); j++) {
                            JSONArray matchArray = LeagueMatchArray.getJSONArray(j);
                            matchList.add(parseMatch(matchArray));
                        }
                    } else {
                        continue;
                    }
                    tableModules.add(new TableSportInfo<>(leagueBean, matchList));
                }

            }
        }
        return tableModules;
    }

    private SoccerCommonInfo parseMatch(JSONArray matchArray) throws JSONException {
        SoccerCommonInfo info = new SoccerCommonInfo();
        info.setSocOddsId(matchArray.getString(0));
        info.setSocOddsId_FH(matchArray.getString(1));
        info.setLive(matchArray.getString(2));
        info.setHomeId(matchArray.getString(3));
        info.setAwayId(matchArray.getString(4));
        info.setIsInFavourite(matchArray.getString(5));
        info.setScoreNew(matchArray.getString(6));
        info.setIsLastCall(matchArray.getString(7));
        info.setMatchDate(matchArray.getString(8));
        info.setStatus(matchArray.getString(9));
        info.setCurMinute(matchArray.getString(10));
        info.setIsInetBet(matchArray.getString(11));
        info.setHasHdp(matchArray.getString(12));
        info.setHdpOdds(matchArray.getString(13));
        info.setIsHomeGive(matchArray.getString(14));
        info.setHomeRank(matchArray.getString(15));
        info.setHome(matchArray.getString(16));
        info.setRCHome(matchArray.getString(17));
        info.setRTSMatchId(matchArray.getString(18));
        info.setAwayRank(matchArray.getString(19));
        info.setAway(matchArray.getString(20));
        info.setRCAway(matchArray.getString(21));
        info.setHdp(matchArray.getString(22));
        info.setHomeHdpOdds(matchArray.getString(23));
        info.setAwayHdpOdds(matchArray.getString(24));
        info.setHasOU(matchArray.getString(25));
        info.setOU(matchArray.getString(26));
        info.setRunHomeScore(matchArray.getString(27));
        info.setRunAwayScore(matchArray.getString(28));
        info.setOverOdds(matchArray.getString(29));
        info.setUnderOdds(matchArray.getString(30));
        info.setOUOdds(matchArray.getString(31));
        info.setHasHdp_FH(matchArray.getString(32));
        info.setHdp_FH(matchArray.getString(33));
        info.setIsHomeGive_FH(matchArray.getString(34));
        info.setHomeHdpOdds_FH(matchArray.getString(35));
        info.setAwayHdpOdds_FH(matchArray.getString(36));
        info.setHdpOdds_FH(matchArray.getString(37));
        info.setIsInetBet_FH(matchArray.getString(38));
        info.setHasOU_FH(matchArray.getString(39));
        info.setOU_FH(matchArray.getString(40));
        info.setRunHomeScore_FH(matchArray.getString(41));
        info.setRunAwayScore_FH(matchArray.getString(42));
        info.setOverOdds_FH(matchArray.getString(43));
        info.setUnderOdds_FH(matchArray.getString(44));
        info.setOUOdds_FH(matchArray.getString(45));
        info.setStatsId(matchArray.getString(46));
        info.setWorkingDate(matchArray.getString(47));
        info.setPreSocOddsId(matchArray.getString(48));
        info.setHasX12(matchArray.getString(49));
        info.setX12_1Odds(matchArray.getString(50));
        info.setX12_2Odds(matchArray.getString(51));
        info.setX12_XOdds(matchArray.getString(52));
        info.setHasX12_FH(matchArray.getString(53));
        info.setX12_1Odds_FH(matchArray.getString(54));
        info.setX12_2Odds_FH(matchArray.getString(55));
        info.setX12_XOdds_FH(matchArray.getString(56));
        info.setIsX12New(matchArray.getString(57));
        info.setIsX12New_FH(matchArray.getString(58));
        info.setIsHdpNew(matchArray.getString(59));
        info.setIsOUNew(matchArray.getString(60));
        info.setIsHdpNew_FH(matchArray.getString(61));
        info.setIsOUNew_FH(matchArray.getString(62));
        info.setFirstOdds(matchArray.getString(63));
        return info;

    }

    @Override
    protected String getRefreshUrl() {
        return AppConstant.URL_FOOTBALL_RUNNING;
    }

    @Override
    protected List<TableSportInfo<SoccerCommonInfo>> filterData(List<TableSportInfo<SoccerCommonInfo>> allData) {
        if(isCollection())
            return filterCollection(allData);
        else
        return allData;
    }
    private List<TableSportInfo<SoccerCommonInfo>> filterCollection(List<TableSportInfo<SoccerCommonInfo>> data) {

            List<TableSportInfo<SoccerCommonInfo>> moduleDate = new ArrayList<>();
            for (TableSportInfo<SoccerCommonInfo> tableModuleBean : data) {
                if (null != localCollectionMap.get( tableModuleBean.getLeagueBean().getModuleTitle())) {
                    List<SoccerCommonInfo> moduleCollectionRows = new ArrayList<>();
                    TableSportInfo<SoccerCommonInfo> moduleCollection = new TableSportInfo<SoccerCommonInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                    Map<String, Boolean> moduleMap = localCollectionMap.get( tableModuleBean.getLeagueBean().getModuleTitle());

                    for (SoccerCommonInfo matchBean : tableModuleBean.getRows()) {
                        if (moduleMap.get(matchBean.getHome() + "+" + matchBean.getAway()) != null && moduleMap.get(matchBean.getHome() + "+" + matchBean.getAway())) {
                            moduleCollectionRows.add(matchBean);
                        }
                    }
                    moduleCollection.setRows(moduleCollectionRows);
                    moduleDate.add(moduleCollection);
                }
            }
            if (moduleDate.size() > 0)
                return moduleDate;
            else {
                isCollection = false;
                ToastUtils.showShort(R.string.no_records);
            }

        return moduleDate;
    }


}
