package com.nanyang.app.main.home.sport.basketball;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
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
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/3/10.
 */

public abstract class BasketballMixState extends SportState<BasketballMixInfo, SportContract2.View<BasketballMixInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public BasketballMixState(SportContract2.View baseView) {
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
                switch (v.getId()) {
                    case R.id.module_right_mark_tv:
                        clickAdd(v, item);
                }
            }

        };
    }

    private void clickAdd(View v, BasketballMixInfo item) {
        getBaseView().clickAdd(v, item, "mix");
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
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(0, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        return types;
    }

    @Override
    public void setHeaderContent(ScrollLayout slHeader) {
        new BasketballHeaderContent().setHeaderContent(getBaseView().getContextActivity(), slHeader);
    }

    @Override
    public boolean mix() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.URL_SOCCER_REMOVE_MIX).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
//                    mApiWrapper.goMain()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        getBaseView().onUpdateMixSucceed(null);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getBaseView().onFailed(throwable.getMessage());
                        getBaseView().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        getBaseView().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        getBaseView().showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
        return false;
    }
}