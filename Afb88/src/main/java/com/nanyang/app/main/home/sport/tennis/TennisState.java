package com.nanyang.app.main.home.sport.tennis;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.SportAdapterHelper;
import com.nanyang.app.main.home.sportInterface.SportContract2;
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

public abstract class TennisState extends SportState<TennisInfo, SportContract2.View<TennisInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public TennisState(SportContract2.View baseView) {
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
    public IAdapterHelper<TennisInfo> onSetAdapterHelper() {
        return new TennisAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<TennisInfo>(baseRecyclerAdapter) {
            @Override
            public boolean isItemCollection(TennisInfo item) {
                return false;
            }

            @Override
            public void clickOdds(TextView v, TennisInfo item, String type, boolean isHf, String odds) {
                IBetHelper<TennisInfo> helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf);
            }

            @Override
            public void clickView(View v, TennisInfo item) {
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        clickAdd(v, item);
                }
            }

        };
    }

    private void clickAdd(View v, TennisInfo item) {
        getBaseView().clickAdd(v, item, "mix");
    }

    @Override
    protected IBetHelper<TennisInfo> onSetBetHelper() {
        return new TennisBetHelper(getBaseView());
    }


    @Override
    protected List<TableSportInfo<TennisInfo>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<TennisInfo>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<TennisInfo> matchList = new ArrayList<>();
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

    private TennisInfo parseMatch(JSONArray matchArray) throws JSONException {
        TennisInfo info = new TennisInfo();
        info.setSocOddsId(matchArray.getString(0));
        info.setHasRunning(matchArray.getString(1));
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
        info.setIsX12New(matchArray.getString(26));
        info.setHasX12(matchArray.getString(27));
        info.setX12_1Odds(matchArray.getString(28));
        info.setX12_XOdds(matchArray.getString(29));
        info.setX12_2Odds(matchArray.getString(30));
        info.setPreSocOddsId(matchArray.getString(31));
        return info;

    }


    @Override
    protected List<TableSportInfo<TennisInfo>> filterChildData(List<TableSportInfo<TennisInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<TennisInfo>> filterCollection(List<TableSportInfo<TennisInfo>> data) {

        List<TableSportInfo<TennisInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<TennisInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<TennisInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<TennisInfo> moduleCollection = new TableSportInfo<TennisInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

                for (TennisInfo matchBean : tableModuleBean.getRows()) {
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
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }

    @Override
    public void setHeaderContent(ScrollLayout slHeader) {
        new TennisHeaderContent().setHeaderContent(getBaseView().getContextActivity(), slHeader);
    }

    @Override
    public final boolean  mix() {
        return false;
    }
}
