package com.nanyang.app.main.home.sport.e_sport;

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
import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by Administrator on 2017/3/3.
 */

public class SportEPresenter extends SportPresenter<List<MatchBean>, SportContract.View<List<MatchBean>>> {

    public SportEPresenter(SportContract.View<List<MatchBean>> view) {
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

    @Override
    protected String getUrl(String type) {
        if (type.equals("")) {
            type = getType();
        }
        String url;
        switch (type) {
            case "Running":
                url = AppConstant.URL_E_SPORT_RUNNING;
                break;
            case "Today":
                url = AppConstant.URL_E_SPORT_TODAY;
                break;
            default:
                url = AppConstant.URL_E_SPORT_EARLY;
                break;
        }
        setType(type);
        return url;
    }

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
    protected List<TableModuleBean> filterData(List<TableModuleBean> allData) {
        List<TableModuleBean> filterData = allData;
        if (isMixParlay)
            isCollection = false;
        if (isCollection)
            filterData = filterCollection(allData);
        return filterData;
    }

    @Override
    protected ResultIndexBean getResultIndexMap(String type) {
        ResultIndexBean resultIndexBean = new ResultIndexBean();
            resultIndexBean.setPreSocOddsId(31);
        return resultIndexBean;
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
            bean1.setSocOddsId(matchArray.get(0).toString());
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
    public void menu() {

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

}
