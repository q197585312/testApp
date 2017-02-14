package com.nanyang.app.main.home.sport;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanyang.app.ApiService;
import com.nanyang.app.main.home.sport.model.HandicapBean;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.nanyang.app.main.home.sport.model.VsOtherDataBean;
import com.unkonw.testapp.libs.api.Api;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class
FootballPresenter extends SportPresenter<List<MatchBean> , ApiSport> {
    private List<TableModuleBean> allData;
    private int page;
    final  int pageSize=15;
    private List<TableModuleBean> filterData;
    private List<TableModuleBean> pageData;

    public FootballPresenter(SportContract.View<List<MatchBean>> view) {
        super(view);
    }

    @Override
    public ApiSport createRetrofitApi() {
        return new ApiSport() {
            @Override
            Flowable<String> refresh() {
                return applySchedulers(getService(ApiService.class).goRefresh());
            }
        };
    }

    //  String regex=".*timerRun2\\('(.*?)'.*timerToday2\\('(.*?)'.*?";
    @Override
    public void refresh() {
        Disposable subscription = mApiWrapper.refresh()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
//                        String regex = ".*timerRun2\\('(.*?)'.*?";
                        String regex = ".*timerToday2\\('(.*?)'.*?";
                        Pattern p = Pattern.compile(regex);
                        Matcher m = p.matcher(s);
                        if (m.find()) {
                            String url = "http://a8197c.a36588.com/_view/" + m.group(1) + "&LID=&_=1486612091203";
                            return Api.getService(ApiService.class).goFootball(url);
                        }
                        return null;
                    }
                })
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
        this.allData=allData;
        this.page=0;
        this.filterData=filterData(allData);
        this.pageData=pageData(filterData);
//        baseView.onGetData(toMatchList(allData));
        baseView.onPageData(page, toMatchList(pageData));
    }
    @NonNull
    protected List<MatchBean> toMatchList(List<TableModuleBean> pageList) {
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
        List<TableModuleBean> pageList ;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }
        return pageList;

    }

    private List<TableModuleBean> filterData(List<TableModuleBean> allData) {//按照条件 筛选data
        return allData;
    }

    @Nullable
    public List<TableModuleBean> parseTableModuleBeen(String s) throws JSONException {
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
                                if (matchArray.length() > 63) {


                                    MatchBean aTrue = new MatchBean(matchArray.get(15).toString(), matchArray.get(19).toString(), matchArray.get(3).toString(), matchArray.get(4).toString(), matchArray.get(0).toString(), matchArray.get(8).toString(), matchArray.get(47).toString(), matchArray.get(20).toString()
                                            , matchArray.get(16).toString(),
                                            new ArrayList<>(Arrays.asList(new HandicapBean(matchArray.get(14).toString(), matchArray.get(22).toString(), matchArray.get(23).toString(), matchArray.get(24).toString(), matchArray.get(26).toString(), matchArray.get(29).toString(), matchArray.get(30).toString(), matchArray.get(0).toString(), matchArray.get(11).toString(), matchArray.get(59).toString(), matchArray.get(60).toString()
                                                            , matchArray.get(12).toString(), matchArray.get(25).toString()),
                                                    new HandicapBean(matchArray.get(34).toString(), matchArray.get(33).toString(), matchArray.get(35).toString(), matchArray.get(36).toString(), matchArray.get(40).toString(), matchArray.get(43).toString(), matchArray.get(44).toString(), matchArray.get(1).toString(), matchArray.get(38).toString(), matchArray.get(61).toString(), matchArray.get(62).toString(), matchArray.get(32).toString(), matchArray.get(39).toString()))), "true", matchArray.get(17).toString(), matchArray.get(21).toString()
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

    @Override
    public void collection() {
    }

    @Override
    public void menu() {
    }

    @Override
    public void mix() {
    }

}
