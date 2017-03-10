package com.nanyang.app.main.home.sport.football;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.ApiSport;
import com.nanyang.app.main.home.sport.SportContract;
import com.nanyang.app.main.home.sport.SportPresenter;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.ResultIndexBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.nanyang.app.main.home.sport.model.VsOtherDataBean;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;


public class FootballPresenter extends SportPresenter<List<MatchBean>, SportContract.View<List<MatchBean>>> {

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

    @Override
    protected String getUrl(String type) {
        if (type.equals("")) {
            type = getType();
        }
        String url;
        switch (type) {
            case "Running":
                if (isOutRight)
                    url = AppConstant.URL_FOOTBALL_OUT_RIGHT + "&ot=t";
                else
                    url = AppConstant.URL_FOOTBALL_RUNNING;
                break;
            case "Today":
                if (isOutRight)
                    url = AppConstant.URL_FOOTBALL_OUT_RIGHT + "&ot=t";
                else {
                    url = AppConstant.URL_FOOTBALL_TODAY;
                    if (isMixParlay) {
                        url = AppConstant.URL_FOOTBALL_TODAY_Mix;
                    }
                }
                break;
            case "Early":
                if (isOutRight)
                    url = AppConstant.URL_FOOTBALL_OUT_RIGHT + "&ot=e";
                else {
                    url = AppConstant.URL_FOOTBALL_EARLY;
                    if (isMixParlay) {
                        url = AppConstant.URL_FOOTBALL_EARLY_Mix;
                    }
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
        return url;
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

        if (isMixParlay)
            isCollection = false;
        filterData = filterCollection(allData);

        return filterData;
    }

    @Override
    protected ResultIndexBean getResultIndexMap(String type) {
        ResultIndexBean resultIndexBean = new ResultIndexBean();
        if (isMixParlay()) {
            resultIndexBean.setPreSocOddsId(50);
        } else {
            resultIndexBean.setPreSocOddsId(48);
        }
        return resultIndexBean;
    }

    @Override
    protected void parseMatchList(List<MatchBean> matchList, JSONArray matchArray) throws JSONException {

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

}
