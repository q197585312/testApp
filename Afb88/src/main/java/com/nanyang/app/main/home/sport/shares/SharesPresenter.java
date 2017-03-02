package com.nanyang.app.main.home.sport.shares;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.ApiSport;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.football.FootballFragment;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.ResultIndexBean;
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


public class SharesPresenter extends SportPresenter<List<MatchBean>, SportContract.View<List<MatchBean>>> {
    private List<TableModuleBean> allData;
    private int page;
    final int pageSize = 15;
    private List<TableModuleBean> filterData;
    private List<TableModuleBean> pageData;

    public boolean isMixParlay() {
        return isMixParlay;
    }

    @Override
    protected String getUrl(String type) {
        return null;
    }


    public boolean isCollection() {
        return isCollection;
    }

    private boolean isCollection = false;
    private boolean isMixParlay = false;
    private Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();
    private String type;

    SharesPresenter(SportContract.View<List<MatchBean>> view) {
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


    boolean isItemCollection(MatchBean item) {
        return !(localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(getType() + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }

    void collectionItem(MatchBean item) {
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
        isCollection = !isCollection;
        filterData(allData);
        showCurrentData();
    }

    @Override
    public void mixParlay() {

            ToastUtils.showShort(" Has No MixParlay");

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
                url = AppConstant.URL_SHARES_RUNNING;
                break;
            case "Today":
                url = AppConstant.URL_SHARES_TODAY;
                break;
            default:
                url = AppConstant.URL_SHARES_EARLY;
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
    @Override
    protected List<TableModuleBean> filterData(List<TableModuleBean> allData) {//按照条件 筛选data
        filterData = allData;
        if (isCollection)
            filterData = filterCollection(allData);
        return filterData;
    }

    @Override
    protected ResultIndexBean getResultIndexMap(String type) {
        return null;
    }


    @Override
    protected void parseMatchList(List<MatchBean> matchList, JSONArray matchArray) throws JSONException {
        if (matchArray.length() > 30) {

            MatchBean aTrue=new MatchBean();
            HandicapBean bean1=new HandicapBean();
            VsOtherDataBean otherDataBean=new VsOtherDataBean();
            aTrue.setKey( matchArray.get(0).toString());
            aTrue.setLive( matchArray.get(2).toString());
            aTrue.setMatchDate( matchArray.get(4).toString());

            bean1.setIsHomeGive(matchArray.get(5).toString());
            aTrue.setHome( matchArray.get(6).toString());
            aTrue.setAway( matchArray.get(7).toString());
            bean1.setIsInetBet(matchArray.get(8).toString());
            bean1.setHasHdp(matchArray.get(9).toString());
            bean1.setHdp(matchArray.get(10).toString());
            bean1.setHomeHdpOdds(matchArray.get(12).toString());
            bean1.setAwayHdpOdds(matchArray.get(13).toString());
            bean1.setHasOu(matchArray.get(14).toString());
            bean1.setOU(matchArray.get(15).toString());
            bean1.setIsHdpNew(matchArray.get(16).toString());
            bean1.setIsOUNew(matchArray.get(17).toString());
            bean1.setOverOdds(matchArray.get(19).toString());
            bean1.setUnderOdds(matchArray.get(20).toString());
            otherDataBean.setIsOENew(matchArray.get(21).toString());
            otherDataBean.setHasOE(matchArray.get(22).toString());
            otherDataBean.setOEOdds(matchArray.get(23).toString());
            otherDataBean.setOddOdds(matchArray.get(24).toString());
            otherDataBean.setEvenOdds(matchArray.get(25).toString());
            otherDataBean.setIsX12New(matchArray.get(26).toString());
            otherDataBean.setHasX12(matchArray.get(27).toString());
            otherDataBean.setX121Odds(matchArray.get(28).toString());
            otherDataBean.setX12XOdds(matchArray.get(29).toString());
            otherDataBean.setX122Odds(matchArray.get(30).toString());
            aTrue.setHandicapBeans(new ArrayList<>(Arrays.asList(bean1)));
            aTrue.setOtherDataBean(otherDataBean);

            matchList.add(aTrue);
        }

    }




    public void onPrevious(SwipeToLoadLayout swipeToLoadLayout) {
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


    public void onNext(SwipeToLoadLayout swipeToLoadLayout) {
        if (filterData != null && (page + 1) * pageSize < filterData.size()) {
            page++;
            showCurrentData();
        } else {
            swipeToLoadLayout.setLoadMoreEnabled(false);
        }
        swipeToLoadLayout.setLoadingMore(false);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
