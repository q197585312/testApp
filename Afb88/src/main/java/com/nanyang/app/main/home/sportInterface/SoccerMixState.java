package com.nanyang.app.main.home.sportInterface;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
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

public abstract class SoccerMixState extends SoccerState<SoccerMixInfo,SportContract2.View<SoccerMixInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public SoccerMixState(SportContract2.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return isCollection;
    }
    public boolean collection(){
        isCollection=!isCollection;
        initAllData(allData);
        return isCollection;
    }

    @Override
    protected IAdapterHelper<SoccerMixInfo> onSetAdapterHelper() {
        return new BallAdapterHelper<SoccerMixInfo>(getBaseView().getActivityContext()){
            @Override
            public void onConvert(MyRecyclerViewHolder helper, int position, SoccerMixInfo item) {
                super.onConvert(helper, position, item);

            }
        };
    }

    @Override
    protected List<TableSportInfo<SoccerMixInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<SoccerMixInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<SoccerMixInfo> matchList = new ArrayList<>();
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

    private SoccerMixInfo parseMatch(JSONArray matchArray) throws JSONException {
        SoccerMixInfo info = new SoccerMixInfo();
                info.setSocOddsId(matchArray.getString(0));
                info.setSocOddsId_FH(matchArray.getString(1));
                info.setLive(matchArray.getString(2));
                info.setIsLastCall(matchArray.getString(3));
                info.setMatchDate(matchArray.getString(4));
                info.setIsHomeGive(matchArray.getString(5));
                info.setHome(matchArray.getString(6));
                info.setAway(matchArray.getString(7));
                info.setIsX12New(matchArray.getString(8));
                info.setHasX12(matchArray.getString(9));
                info.setIsInetBet(matchArray.getString(10));
                info.setX12_1Odds(matchArray.getString(11));
                info.setX12_XOdds(matchArray.getString(12));
                info.setX12_2Odds(matchArray.getString(13));
                info.setHasHdp(matchArray.getString(14));
                info.setHdp(matchArray.getString(15));
                info.setHdpOdds(matchArray.getString(16));
                info.setHomeHdpOdds(matchArray.getString(17));
                info.setAwayHdpOdds(matchArray.getString(18));
                info.setHasOU(matchArray.getString(19));
                info.setOU(matchArray.getString(20));
                info.setIsHdpNew(matchArray.getString(21));
                info.setIsOUNew(matchArray.getString(22));
                info.setOUOdds(matchArray.getString(23));
                info.setOverOdds(matchArray.getString(24));
                info.setUnderOdds(matchArray.getString(25));
                info.setIsOENew(matchArray.getString(26));
                info.setHasOE(matchArray.getString(27));
                info.setOEOdds(matchArray.getString(28));
                info.setOddOdds(matchArray.getString(29));
                info.setEvenOdds(matchArray.getString(30));
                info.setIsX12New_FH(matchArray.getString(31));
                info.setHasX12_FH(matchArray.getString(32));
                info.setIsInetBet_FH(matchArray.getString(33));
                info.setX12_1Odds_FH(matchArray.getString(34));
                info.setX12_XOdds_FH(matchArray.getString(35));
                info.setX12_2Odds_FH(matchArray.getString(36));
                info.setHasHdp_FH(matchArray.getString(37));
                info.setIsHomeGive_FH(matchArray.getString(38));
                info.setHdp_FH(matchArray.getString(39));
                info.setIsHdpNew_FH(matchArray.getString(40));
                info.setHdpOdds_FH(matchArray.getString(41));
                info.setHomeHdpOdds_FH(matchArray.getString(42));
                info.setAwayHdpOdds_FH(matchArray.getString(43));
                info.setHasOU_FH(matchArray.getString(44));
                info.setOU_FH(matchArray.getString(45));
                info.setIsOUNew_FH(matchArray.getString(46));
                info.setOUOdds_FH(matchArray.getString(47));
                info.setOverOdds_FH(matchArray.getString(48));
                info.setUnderOdds_FH(matchArray.getString(49));
                info.setPreSocOddsId(matchArray.getString(50));
        return info;

    }


    @Override
    protected List<TableSportInfo<SoccerMixInfo>> filterData(List<TableSportInfo<SoccerMixInfo>> allData) {
        if(isCollection())
            return filterCollection(allData);
        else
        return allData;
    }
    private List<TableSportInfo<SoccerMixInfo>> filterCollection(List<TableSportInfo<SoccerMixInfo>> data) {

            List<TableSportInfo<SoccerMixInfo>> moduleDate = new ArrayList<>();
            for (TableSportInfo<SoccerMixInfo> tableModuleBean : data) {
                if (null != localCollectionMap.get( tableModuleBean.getLeagueBean().getModuleTitle())) {
                    List<SoccerMixInfo> moduleCollectionRows = new ArrayList<>();
                    TableSportInfo<SoccerMixInfo> moduleCollection = new TableSportInfo<SoccerMixInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                    Map<String, Boolean> moduleMap = localCollectionMap.get( tableModuleBean.getLeagueBean().getModuleTitle());

                    for (SoccerMixInfo matchBean : tableModuleBean.getRows()) {
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

    @Override
    protected int getIndexSocOddsId() {
        return 0;
    }

    @Override
    protected int getIndexPreSocOddsId() {
        return 50;
    }

    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getActivityContext().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0,  getBaseView().getActivityContext().getString(R.string.Early), "Early"));
        return types;
    }

}
