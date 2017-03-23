package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.main.SportState;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
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

public abstract class BasketballMixState extends SportState<BasketballMixInfo, SportContract.View<BasketballMixInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public BasketballMixState(SportContract.View baseView) {
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
    public IAdapterHelper<BasketballMixInfo> onSetAdapterHelper() {
        return new BasketballMixAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BasketballMixInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(BasketballMixInfo item) {
                return false;
            }

            @Override
            public void clickOdds(TextView v, BasketballMixInfo item, String type, boolean isHf, String odds) {
                IBetHelper<BasketballMixInfo> helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf);
            }

            @Override
            public void clickView(View v, BasketballMixInfo item) {

            }

            @Override
            public ScrollLayout onSetHeaderFollower() {
                  return getBaseView().onSetScrollHeader();
            }

        };
    }


    @Override
    protected IBetHelper<BasketballMixInfo> onSetBetHelper() {
        return new BasketballMixBetHelper(getBaseView());
    }


    @Override
    protected List<TableSportInfo<BasketballMixInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<BasketballMixInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<BasketballMixInfo> matchList = new ArrayList<>();
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

    private BasketballMixInfo parseMatch(JSONArray matchArray) throws JSONException {
        BasketballMixInfo info = new BasketballMixInfo();
        info.setSocOddsId(matchArray.opt(0)==null?"":matchArray.getString(0));
        info.setHasRunning(matchArray.opt(1)==null?"":matchArray.getString(1));
        info.setLive(matchArray.opt(2)==null?"":matchArray.getString(2));
        info.setIsLastCall(matchArray.opt(3)==null?"":matchArray.getString(3));
        info.setMatchDate(matchArray.opt(4)==null?"":matchArray.getString(4));
        info.setIsHomeGive(matchArray.opt(5)==null?"":matchArray.getString(5));
        info.setHome(matchArray.opt(6)==null?"":matchArray.getString(6));
        info.setAway(matchArray.opt(7)==null?"":matchArray.getString(7));
        info.setIsInetBet(matchArray.opt(8)==null?"":matchArray.getString(8));
        info.setHasHdp(matchArray.opt(9)==null?"":matchArray.getString(9));
        info.setHdp(matchArray.opt(10)==null?"":matchArray.getString(10));
        info.setHdpOdds(matchArray.opt(11)==null?"":matchArray.getString(11));
        info.setHomeHdpOdds(matchArray.opt(12)==null?"":matchArray.getString(12));
        info.setAwayHdpOdds(matchArray.opt(13)==null?"":matchArray.getString(13));
        info.setHasOU(matchArray.opt(14)==null?"":matchArray.getString(14));
        info.setOU(matchArray.opt(15)==null?"":matchArray.getString(15));
        info.setIsHdpNew(matchArray.opt(16)==null?"":matchArray.getString(16));
        info.setIsOUNew(matchArray.opt(17)==null?"":matchArray.getString(17));
        info.setOUOdds(matchArray.opt(18)==null?"":matchArray.getString(18));
        info.setOverOdds(matchArray.opt(19)==null?"":matchArray.getString(19));
        info.setUnderOdds(matchArray.opt(20)==null?"":matchArray.getString(20));
        info.setIsOENew(matchArray.opt(21)==null?"":matchArray.getString(21));
        info.setHasOE(matchArray.opt(22)==null?"":matchArray.getString(22));
        info.setOEOdds(matchArray.opt(23)==null?"":matchArray.getString(23));
        info.setOddOdds(matchArray.opt(24)==null?"":matchArray.getString(24));
        info.setEvenOdds(matchArray.opt(25)==null?"":matchArray.getString(25));
        info.setIsX12New(matchArray.opt(26)==null?"":matchArray.getString(26));
        info.setHasX12(matchArray.opt(27)==null?"":matchArray.getString(27));
        info.setX12_1Odds(matchArray.opt(28)==null?"":matchArray.getString(28));
        info.setX12_XOdds(matchArray.opt(29)==null?"":matchArray.getString(29));
        info.setX12_2Odds(matchArray.opt(30)==null?"":matchArray.getString(30));
        info.setPreSocOddsId(matchArray.opt(31)==null?"":matchArray.getString(31));
        return info;

    }


    @Override
    protected List<TableSportInfo<BasketballMixInfo>> filterChildData(List<TableSportInfo<BasketballMixInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<BasketballMixInfo>> filterCollection(List<TableSportInfo<BasketballMixInfo>> data) {

        List<TableSportInfo<BasketballMixInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<BasketballMixInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<BasketballMixInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<BasketballMixInfo> moduleCollection = new TableSportInfo<BasketballMixInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

                for (BasketballMixInfo matchBean : tableModuleBean.getRows()) {
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
        return 31;
    }

    @Override
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(1, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(1, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        return types;
    }

    @Override
    public boolean mix() {
        clearMix();
        return false;
    }

    @Override
    public boolean isMix() {
        return true;
    }
    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).set(1,getBaseView().getContextActivity().getString(R.string.FULL_O_E));
        lists.get(1).set(1,getBaseView().getContextActivity().getString(R.string.HALF_O_E));
        return lists;
    }

    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        super.setScrollHeaderContent(slHeader, tvAos);
        tvAos.setVisibility(View.GONE);
        slHeader.getLayoutParams().width= DeviceUtils.dip2px(getBaseView().getContextActivity(),140);
    }
}
