package com.nanyang.app.main.home.sport.football;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.ApiSport;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.nanyang.app.main.home.sport.model.VsOtherDataBean;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;


public class
FootballPresenter extends SportPresenter<List<MatchBean>, SportContract.View<List<MatchBean>>> {
    private List<TableModuleBean> allData;
    private int page;
    private final int pageSize = 15;
    private List<TableModuleBean> filterData;
    private List<TableModuleBean> pageData;


    public boolean isCollection() {
        return isCollection;
    }

    private boolean isCollection = false;

    private Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();


    FootballPresenter(SportContract.View<List<MatchBean>> view) {
        super(view);
    }

    @Override
    public ApiSport createRetrofitApi() {
        return new ApiSport() {
            @Override
            public Flowable<String> getData(String url) {
                return applySchedulers(getService(ApiService.class).getData(url));
            }
        };
    }


    public boolean isItemCollection(MatchBean item) {
        return !(localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }

    public void collectionItem(MatchBean item) {
        String moduleKey = getType() + "+" + item.getLeagueBean().getModuleTitle();
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
    }

    @Override
    public void menu() {
    }

    @Override
    public void collection() {
        if (isMixParlay) {
            ToastUtils.showShort("MixParlay Has No Collection");
            return;
        }
        isCollection = !isCollection;
        filterData(allData);
        showCurrentData();
    }

    @Override
    public void mixParlay() {
        if (type.equals("Running")) {
            ToastUtils.showShort("Running Has No MixParlay");
            return;
        }
        isMixParlay = !isMixParlay;
        clearMixOrder();
        refresh("");
    }

    private void clearMixOrder() {

        if (((FootballFragment) baseView).getApp().getBetDetail() != null && ((FootballFragment) baseView).getApp().getBetDetail().size() > 0) {
            Flowable.create(new FlowableOnSubscribe<BettingParPromptBean>() {
                @Override
                public void subscribe(FlowableEmitter<BettingParPromptBean> e) throws Exception {
                    Iterator<Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>>> it = ((FootballFragment) baseView).getApp().getBetDetail().entrySet().iterator();
                    BettingParPromptBean data = null;
                    while (it.hasNext()) {
                        Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>> keyItem = it.next();
                        Iterator<Map.Entry<String, Map<Integer, BettingInfoBean>>> itt = keyItem.getValue().entrySet().iterator();
                        while (itt.hasNext()) {
                            Iterator<Map.Entry<Integer, BettingInfoBean>> ittt = itt.next().getValue().entrySet().iterator();
                            while (ittt.hasNext()) {
                                BettingInfoBean item = ittt.next().getValue();
                                if (item != null) {
                                    data = removeBetItem(item);
                                }
                            }
                        }
                    }
                    e.onNext(data);
                }
            }, BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).subscribe(new Consumer<BettingParPromptBean>() {
                                                                                                                  @Override
                                                                                                                  public void accept(BettingParPromptBean o) throws Exception {
                                                                                                                      baseView.onUpdateMixSucceed(o, null, null);
                                                                                                                  }
                                                                                                              }
            );


        }
    }


    @Override
    public void refresh(String type) {
        if (type.equals("")) {
            type = getType();
        }
        String url;
        switch (type) {
            case "Running":
                url = AppConstant.URL_FOOTBALL_RUNNING;
                break;
            case "Today":
                url = AppConstant.URL_FOOTBALL_TODAY;
                if (isMixParlay) {
                    url = AppConstant.URL_FOOTBALL_TODAY_Mix;
                }
                break;
            default:
                url = AppConstant.URL_FOOTBALL_EARLY;
                if (isMixParlay) {
                    url = AppConstant.URL_FOOTBALL_EARLY_Mix;
                }
                break;
        }
        setType(type);
        Disposable subscription = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .map(new Function<String, List<TableModuleBean>>() {

                    @Override
                    public List<TableModuleBean> apply(String s) throws Exception {
                        return parseTableModuleBeen(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TableModuleBean>>() {//onNext
                    @Override
                    public void accept(List<TableModuleBean> allData) throws Exception {
                        initAllData(allData);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

    }

    private void initAllData(List<TableModuleBean> allData) {
        this.allData = allData;
        this.page = 0;
        this.filterData = filterData(allData);
        showCurrentData();

    }

    @NonNull
    private List<MatchBean> toMatchList(List<TableModuleBean> pageList) {
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

    private List<TableModuleBean> pageData(List<TableModuleBean> filterData) {
        List<TableModuleBean> pageList;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }
        return pageList;

    }

    /**
     * 选择收藏
     *
     * @param data 挑选前的数据
     * @return 收藏的数据
     */
    private List<TableModuleBean> filterCollection(List<TableModuleBean> data) {
        if (isCollection) {
            ArrayList<TableModuleBean> moduleDate = new ArrayList<>();
            for (TableModuleBean tableModuleBean : data) {
                if (null != localCollectionMap.get(getType() + "+" + tableModuleBean.getLeagueBean().getModuleTitle())) {
                    List<MatchBean> moduleCollectionRows = new ArrayList<>();
                    TableModuleBean moduleCollection = new TableModuleBean(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                    Map<String, Boolean> moduleMap = localCollectionMap.get(getType() + "+" + tableModuleBean.getLeagueBean().getModuleTitle());

                    for (MatchBean matchBean : tableModuleBean.getRows()) {
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
        }
        return data;
    }

    private List<TableModuleBean> filterData(List<TableModuleBean> allData) {//按照条件 筛选data
        filterData = allData;
        if (isMixParlay)
            isCollection = false;
        if (isCollection)
            filterData = filterCollection(allData);
        return filterData;
    }

    @Nullable
    private List<TableModuleBean> parseTableModuleBeen(String s) throws JSONException {
        JSONArray jsonArray = new JSONArray(s);
        ArrayList<TableModuleBean> tableModules = new ArrayList<>();
        if (jsonArray.length() > 4) {

            JSONArray jsonArray2 = jsonArray.getJSONArray(3);
            if (jsonArray2.length() > 0) {

                for (int i = 0; i < jsonArray2.length(); i++) {
                    LeagueBean leagueBean;
                    List<MatchBean> matchList = new ArrayList<>();
                    JSONArray jsonArray3 = jsonArray2.getJSONArray(i);
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
                                if (isMixParlay()) {
                                    if (matchArray.length() > 50) {
                                        MatchBean aTrue = new MatchBean("",
                                                "",
                                                matchArray.get(6).toString(),
                                                matchArray.get(7).toString(),
                                                matchArray.get(0).toString(),
                                                matchArray.get(4).toString(),
                                                "",
                                                matchArray.get(7).toString()
                                                , matchArray.get(6).toString(),
                                                new ArrayList<>(Arrays.asList(new HandicapBean(
                                                                matchArray.get(5).toString(),
                                                                matchArray.get(15).toString(),
                                                                matchArray.get(17).toString(),
                                                                matchArray.get(18).toString(),
                                                                matchArray.get(20).toString(),
                                                                matchArray.get(24).toString(),
                                                                matchArray.get(25).toString(),
                                                                matchArray.get(0).toString(),
                                                                matchArray.get(10).toString(),
                                                                matchArray.get(21).toString(),
                                                                matchArray.get(22).toString(),
                                                                matchArray.get(14).toString(),
                                                                matchArray.get(19).toString()
                                                        ),
                                                        new HandicapBean(
                                                                matchArray.get(38).toString(),
                                                                matchArray.get(39).toString(),
                                                                matchArray.get(42).toString(),
                                                                matchArray.get(43).toString(),
                                                                matchArray.get(45).toString(),
                                                                matchArray.get(48).toString(),
                                                                matchArray.get(49).toString(),
                                                                matchArray.get(1).toString(),
                                                                matchArray.get(33).toString(),
                                                                matchArray.get(40).toString(),
                                                                matchArray.get(46).toString(),
                                                                matchArray.get(37).toString(),
                                                                matchArray.get(44).toString()))),
                                                "",
                                                "",
                                                new VsOtherDataBean(
                                                        matchArray.get(9).toString(),
                                                        matchArray.get(32).toString(),
                                                        matchArray.get(8).toString(),
                                                        matchArray.get(31).toString(),
                                                        matchArray.get(11).toString(),
                                                        matchArray.get(34).toString(),
                                                        matchArray.get(13).toString(),
                                                        matchArray.get(36).toString(),
                                                        matchArray.get(12).toString(),
                                                        matchArray.get(35).toString(),
                                                        matchArray.get(30).toString(),
                                                        matchArray.get(27).toString(),
                                                        matchArray.get(26).toString(),
                                                        matchArray.get(29).toString(),
                                                        matchArray.get(28).toString()
                                                ),
                                                "",
                                                "",
                                                "",
                                                matchArray.get(2).toString(),
                                                "");
                                        matchList.add(aTrue);
                                    }

                                } else {
                                    if (matchArray.length() > 63) {
                                        MatchBean aTrue = new MatchBean(matchArray.get(15).toString(), matchArray.get(19).toString(), matchArray.get(3).toString(), matchArray.get(4).toString(), matchArray.get(0).toString(), matchArray.get(8).toString(), matchArray.get(47).toString(), matchArray.get(20).toString()
                                                , matchArray.get(16).toString(),
                                                new ArrayList<>(Arrays.asList(new HandicapBean(
                                                                matchArray.get(14).toString(),
                                                                matchArray.get(22).toString(),
                                                                matchArray.get(23).toString(),
                                                                matchArray.get(24).toString(),
                                                                matchArray.get(26).toString(),
                                                                matchArray.get(29).toString(),
                                                                matchArray.get(30).toString(),
                                                                matchArray.get(0).toString(),
                                                                matchArray.get(11).toString(),
                                                                matchArray.get(59).toString(),
                                                                matchArray.get(60).toString(),
                                                                matchArray.get(12).toString(),
                                                                matchArray.get(25).toString()),
                                                        new HandicapBean(
                                                                matchArray.get(34).toString(),
                                                                matchArray.get(33).toString(),
                                                                matchArray.get(35).toString(),
                                                                matchArray.get(36).toString(),
                                                                matchArray.get(40).toString(),
                                                                matchArray.get(43).toString(),
                                                                matchArray.get(44).toString(),
                                                                matchArray.get(1).toString(),
                                                                matchArray.get(38).toString(),
                                                                matchArray.get(61).toString(),
                                                                matchArray.get(62).toString(),
                                                                matchArray.get(32).toString(),
                                                                matchArray.get(39).toString()))),
                                                matchArray.get(17).toString(), matchArray.get(21).toString()
                                                , new VsOtherDataBean(
                                                matchArray.get(49).toString(),
                                                matchArray.get(53).toString(),
                                                matchArray.get(57).toString(),
                                                matchArray.get(58).toString(),
                                                matchArray.get(50).toString(),
                                                matchArray.get(54).toString(),
                                                matchArray.get(51).toString(),
                                                matchArray.get(55).toString(),
                                                matchArray.get(52).toString(),
                                                matchArray.get(56).toString()),
                                                matchArray.get(10).toString(),
                                                matchArray.get(27).toString(),
                                                matchArray.get(28).toString(),
                                                matchArray.get(2).toString(),
                                                matchArray.get(9).toString());
                                        matchList.add(aTrue);
                                    }
                                }
                            }
                        } else {
                            continue;
                        }
                        tableModules.add(new TableModuleBean(leagueBean, matchList));
                    }

                }
            }
        }
        return tableModules;
    }


    private void showCurrentData() {
        pageData = pageData(filterData);
        baseView.onPageData(page, toMatchList(pageData), getType());
    }

    void onPrevious(SwipeToLoadLayout swipeToLoadLayout) {
        if (page == 0) {
            refresh("");
        } else {
            page--;
            showCurrentData();
            if (page == 0) {
                swipeToLoadLayout.setLoadMoreEnabled(true);
            }
        }
        swipeToLoadLayout.setRefreshing(false);
    }


    void onNext(SwipeToLoadLayout swipeToLoadLayout) {
        if (filterData != null && (page + 1) * pageSize < filterData.size()) {
            page++;
            showCurrentData();
        } else {
            swipeToLoadLayout.setLoadMoreEnabled(false);
        }
        swipeToLoadLayout.setLoadingMore(false);
    }

    @Override
    public void onRightMarkClick(Bundle b) {
        baseView.onRightMarkClick(b);
    }
}
