package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.europe.BallState;
import com.nanyang.app.main.home.sport.main.SportAdapterHelper;
import com.nanyang.app.main.home.sport.main.SportContract;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.BallItemCallBack;
import com.nanyang.app.main.home.sportInterface.IAdapterHelper;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BasketballCommonState extends BallState {

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
    public IAdapterHelper<BallInfo> onSetAdapterHelper() {
        BasketballCommonAdapterHelper adapterHelper = onSetCommonAdapterHelper();
        return adapterHelper;
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<BallInfo>(baseRecyclerAdapter) {

            @Override
            public void clickOdds(TextView v, BallInfo item, String type, boolean isHf, String odds, int oid, String sc) {
                IBetHelper helper = getBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type, odds, v, isHf, "");
            }

            @Override
            public void clickView(View v, BallInfo item, int position) {
                switch (v.getId()) {
                    case R.id.module_match_collection_tv:
                        collectionItemCommon(item);
                        break;
                    case R.id.module_League_collection_tv:
                        collectionLeagueCommon(item);
                        break;
                    case R.id.module_right_mark_tv:
                        clickAdd(v, item, position);
                        break;
                }

            }

        };
    }

    @Override
    public IBetHelper onSetBetHelper() {
        return new BasketballCommonBetHelper(getBaseView());
    }

    private void clickAdd(View v, BallInfo item, int position) {
        getBaseView().clickItemAdd(v, item, position);
    }


    //http://a8197c.a36588.com/_Bet/JRecPanel.aspx?gt=s&b=under&oId=12159615&oId_fh=12159616&isFH=true&isRun=true&odds=4.70


    protected BasketballCommonAdapterHelper onSetCommonAdapterHelper() {
        return new BasketballCommonAdapterHelper(getBaseView().getIBaseContext().getBaseActivity());
    }


    public boolean isItemCollectionCommon(BallInfo item) {

        return !(localCollectionMap.get(item.getModuleTitle()) == null || localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }


    @Override
    protected List<TableSportInfo<BallInfo>> filterChildData(List<TableSportInfo<BallInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<BallInfo>> filterCollection(List<TableSportInfo<BallInfo>> data) {

        List<TableSportInfo<BallInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<BallInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<BallInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<BallInfo> moduleCollection = new TableSportInfo<BallInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

                for (BallInfo matchBean : tableModuleBean.getRows()) {
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
    protected List<MenuItemInfo> getTypes() {
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running"));
        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early), "Early"));
//        types.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.OutRight), "OutRight"));
        return types;
    }

    @Override
    protected List<List<String>> initHeaderList() {
        List<List<String>> lists = super.initHeaderList();
        lists.get(0).add(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_O_E));
        lists.get(1).add(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HALF_O_E));
        return lists;
    }


}
