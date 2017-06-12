package com.nanyang.app.main.home.sport.football;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
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

public abstract class SoccerMixState extends SportState<SoccerMixInfo, SportContract.View<SoccerMixInfo>> {

    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private boolean isCollection;


    public SoccerMixState(SportContract.View baseView) {
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
    public IAdapterHelper<SoccerMixInfo> onSetAdapterHelper() {
         return new SoccerMixAdapterHelper(getBaseView().getContextActivity());
    }

    @Override
    protected SportAdapterHelper.ItemCallBack onSetItemCallBack() {
        return new BallItemCallBack<SoccerMixInfo>(baseRecyclerAdapter) {
            @Override
            public ScrollLayout onSetHeaderFollower() {
                return getBaseView().onSetScrollHeader();
            }
            @Override
            public boolean isItemCollection(SoccerMixInfo item) {
                return false;
            }

            @Override
            public void clickOdds(TextView v, SoccerMixInfo item, String type, boolean isHf, String odds) {
                IBetHelper<SoccerMixInfo> helper = onSetBetHelper();
                helper.setCompositeSubscription(mCompositeSubscription);
                helper.clickOdds(item, type,odds,v,  isHf,"");
            }

            @Override
            public void clickView(View v, SoccerMixInfo item,int position) {
                switch (v.getId()){
                    case R.id.module_right_mark_tv:
                        clickAdd(v,item);
                }
            }

        };
    }

    protected void clickAdd(View v, SoccerMixInfo item) {
        getBaseView().clickItemAdd(v,item,"mix");
    }

    @Override
    protected IBetHelper<SoccerMixInfo> onSetBetHelper() {
        return new SoccerMixBetHelper(getBaseView());
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
    protected List<TableSportInfo<SoccerMixInfo>> filterChildData(List<TableSportInfo<SoccerMixInfo>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private List<TableSportInfo<SoccerMixInfo>> filterCollection(List<TableSportInfo<SoccerMixInfo>> data) {

        List<TableSportInfo<SoccerMixInfo>> moduleDate = new ArrayList<>();
        for (TableSportInfo<SoccerMixInfo> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<SoccerMixInfo> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<SoccerMixInfo> moduleCollection = new TableSportInfo<SoccerMixInfo>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

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
        types.add(new MenuItemInfo(1, getBaseView().getContextActivity().getString(R.string.Today), "Today"));
        types.add(new MenuItemInfo(1, getBaseView().getContextActivity().getString(R.string.Early), "Early"));
        return types;
    }


    @Override
    public boolean isMix() {
        return true;
    }
    @Override
    public boolean mix() {
        Disposable subscription =getService(ApiService.class).getData(AppConstant.getInstance().URL_SOCCER_REMOVE_MIX).subscribeOn(Schedulers.io())
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
