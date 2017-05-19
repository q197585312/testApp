package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BasketballCommonState extends SportState<BasketballCommonInfo, SportContract.View<BasketballCommonInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public BasketballCommonState(SportContract.View baseView) {
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
    public IAdapterHelper<BasketballCommonInfo> onSetAdapterHelper() {
        BasketballCommonAdapterHelper adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BasketballCommonInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(BasketballCommonInfo item) {
                return isItemCollectionCommon(item);
            }

            @Override
            public void clickOdds(TextView v, BasketballCommonInfo item, String type, boolean isHf, String odds) {
                IBetHelper helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type,odds,v,  isHf,"");
            }

            @Override
            public void clickView(View v, BasketballCommonInfo item,int position) {
                switch (v.getId()){
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_right_mark_tv:
                        clickAdd(v,item);
                        break;
                }

            }

            @Override
            public ScrollLayout onSetHeaderFollower() {
                return getBaseView().onSetScrollHeader();
            }
        };
    }

    private void clickAdd(View v,BasketballCommonInfo item) {
       getBaseView().clickItemAdd(v,item,"common");
    }

    @Override
    protected IBetHelper onSetBetHelper() {
        return new BasketballCommonBetHelper(getBaseView());
    }

    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?gt=s&b=under&oId=12159615&oId_fh=12159616&isFH=true&isRun=true&odds=4.70



    protected abstract BasketballCommonAdapterHelper onSetCommonAdapterHelper();


    public void collectionItemCommon(BasketballCommonInfo item) {
        String moduleKey = item.getModuleTitle().toString();
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

    public boolean isItemCollectionCommon(BasketballCommonInfo item) {

        return !(localCollectionMap.get(item.getModuleTitle()) == null || localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }

    @Override
    protected List<TableSportInfo<BasketballCommonInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<BasketballCommonInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<BasketballCommonInfo> matchList = new ArrayList<>();
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

    private BasketballCommonInfo parseMatch(JSONArray matchArray) throws JSONException {
        BasketballCommonInfo info = new BasketballCommonInfo();
                info.setSocOddsId(matchArray.getString(0));
                info.setSocOddsId_FH(matchArray.getString(1));
                info.setLive(matchArray.getString(2));
                info.setIsLastCall(matchArray.getString(3));
                info.setMatchDate(matchArray.getString(4));
                info.setIsHomeGive(matchArray.getString(5));
                info.setHome(matchArray.getString(6));
                info.setAway(matchArray.getString(7));
                info.setIsInetBet(matchArray.getString(8));
                info.setHasHdp(matchArray.getString(9));
                info.setHdp(matchArray.getString(10));
                info.setHdpOdds(matchArray.getString(11));
                info.setHomeHdpOdds(matchArray.getString(12));
                info.setAwayHdpOdds(matchArray.getString(13));
                info.setHasOU(matchArray.getString(14));
                info.setOU(matchArray.getString(15));
                info.setIsHdpNew(matchArray.getString(16));
                info.setIsOUNew(matchArray.getString(17));
                info.setOUOdds(matchArray.getString(18));
                info.setOverOdds(matchArray.getString(19));
                info.setUnderOdds(matchArray.getString(20));
                info.setIsOENew(matchArray.getString(21));
                info.setHasOE(matchArray.getString(22));
                info.setOEOdds(matchArray.getString(23));
                info.setOddOdds(matchArray.getString(24));
                info.setEvenOdds(matchArray.getString(25));
                info.setIsInetBet_FH(matchArray.getString(26));
                info.setHasHdp_FH(matchArray.getString(27));
                info.setIsHomeGive_FH(matchArray.getString(28));
                info.setHdp_FH(matchArray.getString(29));
                info.setIsHdpNew_FH(matchArray.getString(30));
                info.setHdpOdds_FH(matchArray.getString(31));
                info.setHomeHdpOdds_FH(matchArray.getString(32));
                info.setAwayHdpOdds_FH(matchArray.getString(33));
                info.setHasOU_FH(matchArray.getString(34));
                info.setOU_FH(matchArray.getString(35));
                info.setIsOUNew_FH(matchArray.getString(36));
                info.setOUOdds_FH(matchArray.getString(37));
                info.setOverOdds_FH(matchArray.getString(38));
                info.setUnderOdds_FH(matchArray.getString(39));
                info.setIsOENew_FH(matchArray.getString(40));
                info.setHasOE_FH(matchArray.getString(41));
                info.setOEOdds_FH(matchArray.getString(42));
                info.setOddOdds_FH(matchArray.getString(43));
                info.setEvenOdds_FH(matchArray.getString(44));
                info.setIsX12New(matchArray.getString(45));
                info.setHasX12(matchArray.getString(46));
                info.setX12_1Odds(matchArray.getString(47));
                info.setX12_XOdds(matchArray.getString(48));
                info.setX12_2Odds(matchArray.getString(49));
                info.setPreSocOddsId(matchArray.getString(50));
                info.setIsX12New_FH(matchArray.getString(51));
                info.setHasX12_FH(matchArray.getString(52));
                info.setX12_1Odds_FH(matchArray.getString(53));
                info.setX12_XOdds_FH(matchArray.getString(54));
                info.setX12_2Odds_FH(matchArray.getString(55));
        return info;

    }

 @Override
    protected List<TableSportInfo<BasketballCommonInfo>> filterChildData (List<TableSportInfo<BasketballCommonInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<BasketballCommonInfo>> filterCollection(List<TableSportInfo<BasketballCommonInfo>> data) {

        List<TableSportInfo<BasketballCommonInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<BasketballCommonInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<BasketballCommonInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<BasketballCommonInfo> moduleCollection = new TableSportInfo<BasketballCommonInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

                for (BasketballCommonInfo matchBean : tableModuleBean.getRows()) {
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
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).add(getBaseView().getContextActivity().getString(R.string.FULL_O_E));
        lists.get(1).add(getBaseView().getContextActivity().getString(R.string.HALF_O_E));
        return lists;
    }

    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        super.setScrollHeaderContent(slHeader, tvAos);
        tvAos.setVisibility(View.GONE);
        slHeader.getLayoutParams().width= DeviceUtils.dip2px(getBaseView().getContextActivity(),180);
    }
}
