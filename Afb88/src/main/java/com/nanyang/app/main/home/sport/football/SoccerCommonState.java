package com.nanyang.app.main.home.sport.football;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SoccerCommonInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.SportAdapterHelper;
import com.nanyang.app.main.home.sportInterface.SportContract;
import com.nanyang.app.main.home.sportInterface.SportState;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class SoccerCommonState extends SportState<SoccerCommonInfo, SportContract.View<SoccerCommonInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public SoccerCommonState(SportContract.View baseView) {
        super(baseView);
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean collection() {
        isCollection = !isCollection;
        initAllData(allData);
        return isCollection;
    }

    @Override
    public IAdapterHelper<SoccerCommonInfo> onSetAdapterHelper() {
        SoccerCommonAdapterHelper adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<SoccerCommonInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(SoccerCommonInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, SoccerCommonInfo item, String type, boolean isHf, String odds) {
                IBetHelper helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type,odds,v,  isHf);
            }

            @Override
            public void clickView(View v, SoccerCommonInfo item) {
                switch (v.getId()){
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_right_mark_tv:
                        clickAdd(v,item);
                        break;
                }

            }
        };
    }

    private void clickAdd(View v,SoccerCommonInfo item) {
       getBaseView().clickItemAdd(v,item,"common");
    }

    @Override
    protected IBetHelper onSetBetHelper() {
        return new SoccerCommonBetHelper(getBaseView());
    }

    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?gt=s&b=under&oId=12159615&oId_fh=12159616&isFH=true&isRun=true&odds=4.70



    protected abstract SoccerCommonAdapterHelper onSetCommonAdapterHelper();


    public void collectionItemCommon(SoccerCommonInfo item) {
        String moduleKey = item.getModuleTitle();
        Map<String, Boolean> moduleMap = localCollectionMap.get(moduleKey);
        if (moduleMap == null)
            moduleMap = new HashMap<>();
        String localKey = item.getHome() + "+" + item.getAway();
        Boolean v = moduleMap.get(localKey);
        if (v == null || !v) {
            moduleMap.put(localKey, true);
        } else {
            moduleMap.put(localKey, false);
        }
        localCollectionMap.put(moduleKey, moduleMap);
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    public boolean isItemCollectionCommon(SoccerCommonInfo item) {

        return !(localCollectionMap.get(item.getModuleTitle()) == null || localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
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
    protected List<TableSportInfo<SoccerCommonInfo>> filterChildData (List<TableSportInfo<SoccerCommonInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<SoccerCommonInfo>> filterCollection(List<TableSportInfo<SoccerCommonInfo>> data) {

        List<TableSportInfo<SoccerCommonInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<SoccerCommonInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<SoccerCommonInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<SoccerCommonInfo> moduleCollection = new TableSportInfo<SoccerCommonInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

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

    @Override
    protected int getIndexSocOddsId() {
        return 0;
    }

    @Override
    protected int getIndexPreSocOddsId() {
        return 48;
    }

    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }

    @Override
    public void setHeaderContent(ScrollLayout slHeader) {
        new SoccerHeaderContent().setHeaderContent(getBaseView().getContextActivity(), slHeader);
    }

}
