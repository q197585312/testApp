package com.nanyang.app.main.home.sport;


import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingInfoBean;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.MatchBean;
import com.nanyang.app.main.home.sport.model.ResultIndexBean;
import com.nanyang.app.main.home.sport.model.TableModuleBean;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.finalteam.toolsfinal.logger.Logger;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

public abstract class SportPresenter<T, V extends SportContract.View<T>> extends BaseRetrofitPresenter<T, V> implements SportContract.Presenter {
    private Disposable updateSubscription;
    private String LID;
    protected List<TableModuleBean> allData;
    protected int page;
    private final int pageSize = 15;
    private List<TableModuleBean> filterData;
    protected List<TableModuleBean> pageData;
    private Map<String, JSONArray> matchArrayMap = new HashMap<>();
    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();

    public SportPresenter(V view) {
        super(view);
    }
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）


    @Override
    public void refresh(String type) {
        String url = getUrl(type);
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
                        startUpdate();
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
        page = 0;
        updateAllDate(allData);

    }

    public void addMixParlayBet(BettingInfoBean info, final Map<String, Map<Integer, BettingInfoBean>> keyMap, final MatchBean item) {

        StringBuilder builder = getBetUrl(info);

        //http://a8197c.a36588.com/_bet/JRecPanel.aspx?g=2&b=home_par&oId=12036347&odds=17.6
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).addMixParlayBet(builder.toString()))

                .subscribe(new Consumer<BettingParPromptBean>() {//onNext
                    @Override
                    public void accept(BettingParPromptBean allData) throws Exception {
                        baseView.onUpdateMixSucceed(allData, keyMap, item);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onAddMixFailed(throwable.getMessage());
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



    @NonNull
    private StringBuilder getBetUrl(BettingInfoBean info) {
        //%@/_Bet/JRecPanel.aspx?g=2&b=%@&oId=%@&odds=%f
        StringBuilder builder = new StringBuilder();
        //"http://mobilesport.dig88api.com/_bet/JRecPanel.aspx?"
        builder.append(AppConstant.HOST + "_bet/JRecPanel.aspx?");
        if (info.getGt() != null && !info.getGt().equals(""))
            builder.append("gt=" + info.getGt());
        if (info.getB().equals("1") || info.getB().equals("X") || info.getB().equals("2") || info.getB().equals("odd") || info.getB().equals("even"))
            builder.append("&g=5");
        else if (info.getB().equals("X_par") || info.getB().equals("2_par") || info.getB().equals("1_par") || info.getB().equals("under_par") || info.getB().equals("over_par") || info.getB().equals("home_par") ||
                info.getB().equals("away_par") || info.getB().equals("odd_par") || info.getB().equals("even_par"))
            builder.append("&g=2");
        builder.append("&b=" + info.getB());
        if (info.getSc() != null && !info.getSc().equals(""))
            builder.append("&sc=" + info.getSc());
        builder.append("&oId=" + info.getSocOddsId());
        builder.append("&odds=" + info.getOdds());
        if (info.isRunning())
            builder.append("&isRun=true");
        if (info.getIsFH() == 1 && info.getSocOddsId_FH() != null && !info.getSocOddsId_FH().equals(""))
            builder.append("&isFH=true&oId_fh=" + info.getSocOddsId_FH());
        return builder;
    }

    public void getBetPopupData(BettingInfoBean info) {
        StringBuilder betUrl = getBetUrl(info);
        Flowable<BettingPromptBean> flowable = getService(ApiService.class).getBetData(betUrl.toString());
        Disposable subscription = mApiWrapper.applySchedulers(flowable)
                .subscribe(new Consumer<BettingPromptBean>() {//onNext
                    @Override
                    public void accept(BettingPromptBean allData) throws Exception {
                        baseView.onGetBetSucceed(allData);
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

    @Override
    public void bet(String s) {
        Flowable<String> flowable = getService(ApiService.class).getData(s);
        Disposable subscription = mApiWrapper.applySchedulers(flowable)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        baseView.onBetSucceed(allData);
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

    public BettingParPromptBean removeBetItem(final BettingInfoBean item) {

        String ParUrl = "";

        for (BettingParPromptBean.BetParBean aitem : ((BaseToolbarActivity) baseView).getApp().getBetParList().getBetPar()) {
            if (item.getHome().equals(aitem.getHome()) && item.getAway().equals(aitem.getAway())) {
                ParUrl = aitem.getParUrl();
                break;
            }
        }
        String url;
        if (!ParUrl.equals("")) {
            if (item.getIsFH() == 0)
                url = ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId();
            else {
                url = ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId_FH();
            }
            try {
                return getService(ApiService.class).removeMixOrder(url).execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;

    }

    protected String type;

    public synchronized String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    protected boolean isMixParlay = false;

    public boolean isMixParlay() {
        return isMixParlay;
    }

    public void setMixParlay(boolean mixParlay) {
        isMixParlay = mixParlay;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public boolean isCollection = false;


    public void countBet() {
        baseView.onCountBet();
    }

    public void createPopupWindow(BetBasePop betPop) {
        baseView.onCreatePopupWindow(betPop);
    }

    protected String getUpdateUrl() {

        return getUrl(type) + "&LID=" + LID;


    }

    protected abstract String getUrl(String type);

    private List<TableModuleBean> pageData(List<TableModuleBean> filterData) {
        List<TableModuleBean> pageList;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }
        return pageList;

    }

    protected void updateAllDate(List<TableModuleBean> allData) {
        this.allData = allData;
        this.filterData = filterData(allData);
        showCurrentData();
    }

    protected void showCurrentData() {
        pageData = pageData(filterData);
        baseView.onPageData(page, (T) toMatchList(pageData), getType());
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

    protected abstract List<TableModuleBean> filterData(List<TableModuleBean> allData);

    void stopUpdate() {
        if (updateSubscription != null) {
            updateSubscription.dispose();
            Logger.getDefaultLogger().d(getClass().getSimpleName(), "stopUpdate---->");
            updateSubscription = null;
        }
    }

    public void startUpdate() {
        Flowable<String> updateFlowable = Flowable.interval(20, 20, TimeUnit.SECONDS).flatMap(new Function<Long, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Long aLong) throws Exception {
                if (LID != null && LID.length() > 0)
                    return getService(ApiService.class).getData(getUpdateUrl());
                else
                    return getService(ApiService.class).getData(getUrl(getType()));
            }
        });
        if (updateSubscription != null) {
            updateSubscription.dispose();
            updateSubscription = null;
        }
        updateSubscription = updateFlowable.observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .map(new Function<String, List<TableModuleBean>>() {

                    @Override
                    public List<TableModuleBean> apply(String s) throws Exception {
                        if (LID != null && LID.length() > 0)
                            return updateJsonArray(s);
                        else
                            return parseTableModuleBeen(s);
                    }
                }).observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<List<TableModuleBean>>() {//onNext
                    @Override
                    public void accept(List<TableModuleBean> allData) throws Exception {
                        if (allData != null && allData.size() > 0) {
                            updateAllDate(allData);
                        }


                    }
                });
        Logger.getDefaultLogger().d(getClass().getSimpleName(), "startUpdate---->");
        mCompositeSubscription.add(updateSubscription);
    }
    /*
    * -(void)updateRunningData:(NSArray *)arrdata
{
    NSArray * arr0=arrdata[0];//数据信息  [1, '7fe88ca994a22f12', 't', 0, 0, 1, 0, 1, -1, 'eng'],
    _RequestKey=arr0[1];


    for (int i=0;i<[arrdata[2] count];i++) {//删除
        NSString * SocOddsId=arrdata[2][i];
        NSLog(@"删除的id~~~~~%@",SocOddsId);
        for (NSArray *  arrForone in _arrAllfootballdata) {
            BOOL isbreak=NO;
            for (FootballOneRowInfo * footballOneRowInfo in arrForone[1]) {
                if ([footballOneRowInfo.SocOddsId integerValue]== [SocOddsId integerValue]) {
                    if ([arrForone[1] count]==1) {//section只有一个cell
                        [_arrAllfootballdata removeObject:arrForone];
                        isbreak=YES;
                        break;
                    }else{
                        [arrForone[1] removeObject:footballOneRowInfo];
                        isbreak=YES;
                        break;
                    }
                }
            }
            if (isbreak) {
                break;
            }
        }
    }

    //增加
    for (NSArray * one in arrdata[3]) {
        //[31568, '英格兰超级联赛 - 上半场结束前受伤延长补时', 23, 0],  23代表索引？
        NSArray * arrLiansaiInfo=one[0];//联赛信息
        NSArray * arrDataInfo=one[1];
        BOOL isInLiansai=NO;
        for (NSArray *  arrForone in _arrAllfootballdata) {
            NSArray * arrLiansaiInfoExsit=arrForone[0];//联赛信息
            if ([arrLiansaiInfo[0] integerValue]==[arrLiansaiInfoExsit[0] integerValue]) {//存在的联赛
                NSLog(@"存在的联赛中插入");
                isInLiansai=YES;
                for (NSArray * OneInone in arrDataInfo) {//准备插入
                    BOOL isAdd=NO;
                    FootballOneRowInfo * footballOneRowInfoNew=[[FootballOneRowInfo alloc]init];
                    [footballOneRowInfoNew setupWithArray:OneInone];

                    for (int i=0;i<[arrForone[1] count];i++) {
                        FootballOneRowInfo * footballOneRowInfo=arrForone[1][i];
                        NSLog(@"2~~~~~~~");
                        if ([footballOneRowInfo.SocOddsId integerValue]==[footballOneRowInfoNew.PreSocOddsId integerValue]) {
                            NSLog(@"3~~~~~~~");
                            if (i+1==[arrForone[1] count]) {
                                [arrForone[1] addObject:footballOneRowInfoNew];
                            }else{
                                [arrForone[1] insertObject:footballOneRowInfoNew atIndex:i+1];
                            }

                            isAdd=YES;
                            break;
                        }
                    }
                    if (!isAdd) {
                        NSLog(@"4~~~~~~~");
                        isAdd=YES;
                        [arrForone[1] insertObject:footballOneRowInfoNew atIndex:0];
                    }

                }

            }
        }

        //新增联赛
        if (!isInLiansai) {//新增联赛
            NSLog(@"新增联赛。。。。。。。%@",arrLiansaiInfo);

            //保存联赛和小组赛信息
            NSMutableArray * arrOneSection=[NSMutableArray array];
            for (NSArray * OneInone in arrDataInfo) {//联赛中的 小组赛信息

                FootballOneRowInfo * footballOneRowInfo=[[FootballOneRowInfo alloc]init];
                [footballOneRowInfo setupWithArray:OneInone];
                [arrOneSection addObject:footballOneRowInfo];
            }
            //联赛和小组赛信息
            NSArray * arrForone=[NSArray arrayWithObjects:arrLiansaiInfo, arrOneSection,nil];

            //插入
            BOOL hasAdd=NO;
            for (int i=0;i<[_arrAllfootballdata count];i++) {
                FootballOneRowInfo * footballOneRowInfoExist=[_arrAllfootballdata[i][1] lastObject];//联赛中的最后一场
                FootballOneRowInfo * footballOneRowInfoNew=arrOneSection[0];//新增联赛的第一场
                NSLog(@"1~~~~~~~");
                if ([footballOneRowInfoExist.SocOddsId integerValue]==[footballOneRowInfoNew.PreSocOddsId integerValue]) {//

                    if (i+1==[_arrAllfootballdata count]) {
                        [_arrAllfootballdata addObject:arrForone];
                    }else{
                        [_arrAllfootballdata insertObject:arrForone atIndex:i+1];
                    }
                    NSLog(@"2~~~~~~~");
                    hasAdd=YES;
                    break;
                }
            }
            if (!hasAdd) {
                NSLog(@"3~~~~~~~");
                hasAdd=YES;
                [_arrAllfootballdata insertObject:arrForone atIndex:0];
            }
        }
    }

    //计算count
    _matchCount=0;
    for (NSArray *  arrForone in _arrAllfootballdata) {

        for (int i=0;i<[arrForone[1] count];i++) {
            _matchCount++;

            FootballOneRowInfo * footballOneRowInfo=[arrForone[1] objectAtIndex:i];
            footballOneRowInfo.isShowLeftview=YES;
            if (i==0) {
                continue;
            }
            //判断 isShowLeftview
            FootballOneRowInfo * footballOneRowInfolast=[arrForone[1] objectAtIndex:i-1];
            if ([footballOneRowInfo.Home isEqualToString:footballOneRowInfolast.Home]&&[footballOneRowInfo.Away isEqualToString:footballOneRowInfolast.Away]&&[footballOneRowInfo.IsHomeGive integerValue]== [footballOneRowInfolast.IsHomeGive integerValue]) {
                footballOneRowInfo.isShowLeftview=NO;
            }
        }
    }
}*/
    /* for (NSArray * arr in arrdata[5]) {//更新数据
        NSString * SocOddsId=arr[0];
        NSArray  * arrIndex =arr[1];
        NSArray  * arrData  =arr[2];
        for (NSArray *  arrForone in _arrAllfootballdata) {
            for (FootballOneRowInfo * footballOneRowInfo in arrForone[1]) {
                if ([footballOneRowInfo.SocOddsId integerValue]== [SocOddsId integerValue]) {
                    for (int i=0; i<arrIndex.count; i++) {
                        [footballOneRowInfo.arrSelfDataFrom replaceObjectAtIndex:[arrIndex[i] integerValue] withObject:arrData[i]];
                    }
                    [footballOneRowInfo setupWithArray:footballOneRowInfo.arrSelfDataFrom];
                }
            }
        }
    }
* */

    /**
     * [[0,'d7d815f8db95cb7a','r',0,0,1,0,1,-1,'eng'],
     * [],
     * [],删除
     * [
     * [
     * [445614,'南美洲球会杯 - 角球',13,0],
     * [[12099517,0,'0 - 0' ,173010,114739,0,0,0,'08:45AM' ,2,3,1,1,5.7,1,'','卡利体育会 第十四角球' ,0,0,'','盧捷諾體育會\t第十四角球' ,0,0,3.5,18.1,0,-1,0,0,52.6,0.2,0,0,0,0,0.2,1000,0,0,0,0,0,0,1000,0.2,0,0,"02/28/2017" ,12099253,0,0,0,0,0,0,0,0,0,0,1,0,0,0,1]]
     * ]
     * ],
     * [],
     * [[12095508,[60],[0]],[12095475,[13,23,24],[3.3,2.5,32.2]],[12098443,[59],[0]],[12098151,[13,23,24,29,30,31,59,60],[-2.5,27,2.7,24.3,2.9,-2.9,1,1]],[12098550,[29,30,31],[12.8,6.6,-6.6]],[12098552,[60],[0]],[12099109,[29,30,31],[10.6,7.6,-7.6]],[12099253,[6,29,30,31],[0,8,10.2,9.8]],[12098270,[29,30,31,48,50,57],[8.4,10,10,12099517,32,1]],[12099101,[29,30,31,51,52],[10.2,8.2,-8.2,2.94,1.82]],[12099341,[29,30,31],[7.5,10.9,9.1]],[12098671,[29,30,31,60],[7.5,10.9,9.1,1]],[12099340,[13,23,24,60],[-6.7,12,6.9,0]],[12099136,[13,23,24,29,30,31,51,52],[9.6,8,10.6,7.9,10.5,9.5,1.68,2.82]],[12099342,[13,23,24,60],[-6.8,11.9,7,0]],[12098182,[29,30,31],[10,8,-8]],[12098185,[29,30,31],[11.4,6.7,-6.7]],[12098189,[6,29,30,31],[0,10.5,7.5,-7.5]]]]
     *
     * @param updateString 更新数据
     * @return 修改后的json 数据  没修改返回null
     * @throws JSONException
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private List<TableModuleBean> updateJsonArray(String updateString) {
        try {


            LogUtil.d("UpdateData", updateString);
            JSONArray jsonArray = new JSONArray(updateString);
            boolean modified = false;
            boolean deleted = false;
            boolean added = false;
            if (jsonArray.length() > 5) {
                parseLidValue(jsonArray);
                JSONArray modifyArray = jsonArray.getJSONArray(5);

                if (modifyArray.length() > 0) {
                    modified = true;
                }
                JSONArray deleteArray = jsonArray.getJSONArray(2);
                List<String> deleteData = new ArrayList<>();
                for (int i = 0; i < deleteArray.length(); i++) {
                    deleteData.add(deleteArray.getString(i));
                    deleted = true;
                }

                JSONArray addArray = jsonArray.getJSONArray(3);
                if (addArray.length() > 0) {
                    added = true;
                }
                Map<String, JSONArray> addMap = new HashMap<>();
                Map<JSONArray, JSONArray> addMapLeague = new HashMap<>();

                for (int i = 0; i < addArray.length(); i++) {
                    JSONArray array = addArray.getJSONArray(i);
                    JSONArray league = array.getJSONArray(0);
                    String leagueKey = league.getString(0);
                    JSONArray matchArry = array.getJSONArray(1);
                    addMap.put(leagueKey, matchArry);
                    addMapLeague.put(matchArry, league);
                }

                JSONArray dataListArray = matchArrayMap.get(getType());
                ResultIndexBean indexBean = getResultIndexMap(getType());
                if (added) {//可以添加数据
                    for (int i = 0; i < dataListArray.length(); i++) {
                        JSONArray jsonArray3 = dataListArray.getJSONArray(i);
                        if (jsonArray3.length() > 1) {
                            JSONArray LeagueArray = jsonArray3.getJSONArray(0);
                            JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                            JSONArray matchAdd = addMap.get(LeagueArray.getString(0));

                            if (matchAdd != null) {//插入到已有联赛内
                                JSONArray Array = new JSONArray();//修改后的联赛
                                for (int j = 0; j < matchAdd.length(); j++) {//遍历要添加的比赛
                                    JSONArray jsonArray1 = matchAdd.getJSONArray(j);
                                    String preId = jsonArray1.getString(indexBean.getPreSocOddsId());

                                    if (preId == null || preId.equals("")) {//没有PreId加到最前面
                                        Array.put(jsonArray1);//先加
                                        for (int k = 0; k < LeagueMatchArray.length(); k++) {
                                            Array.put(LeagueMatchArray.getJSONArray(k));
                                        }
                                    } else {
                                        boolean addIn = false;
                                        for (int k = 0; k < LeagueMatchArray.length(); k++) {
                                            String id = LeagueMatchArray.getJSONArray(k).getString(indexBean.getSocOddsId());
                                            Array.put(LeagueMatchArray.getJSONArray(k));
                                            if (preId.equals(id)) {
                                                Array.put(jsonArray1);
                                                addIn = true;
                                            }
                                        }
                                        if (!addIn) {
                                            Array = new JSONArray();
                                            Array.put(jsonArray1);
                                            for (int k = 0; k < LeagueMatchArray.length(); k++) {
                                                Array.put(LeagueMatchArray.getJSONArray(k));
                                            }
                                        }
                                    }
                                }
                                jsonArray3.put(1, Array);//替换联赛数据
                                addMap.remove(LeagueArray.getString(0));
                            }
                        }
                    }
                    Iterator<Map.Entry<String, JSONArray>> iterator = addMap.entrySet().iterator();
                    if (iterator.hasNext()) {
                        Map.Entry<String, JSONArray> next = iterator.next();
                        for (int i = 0; i < addArray.length(); i++) {
                            JSONArray array = addArray.getJSONArray(i);
                            JSONArray league = array.getJSONArray(0);
                            String leagueKey = league.getString(0);
                            if (leagueKey.equals(next.getKey())) {
                                dataListArray.put(array);
                            }
                        }
                    }
                }
                for (int i = 0; i < dataListArray.length(); i++) {
                    JSONArray jsonArray3 = dataListArray.getJSONArray(i);
                    if (jsonArray3.length() > 1) {
                        JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                        for (int j = 0; j < LeagueMatchArray.length(); j++) {
                            String sid = LeagueMatchArray.getJSONArray(j).getString(indexBean.getSocOddsId());


                            for (int k = 0; k < modifyArray.length(); k++) {
                                JSONArray jsonArray1 = modifyArray.getJSONArray(k);
                                String modifyId = jsonArray1.getString(0);
                                JSONArray modifyIndex = jsonArray1.getJSONArray(1);
                                JSONArray modifyData = jsonArray1.getJSONArray(2);
                                if (modifyId.equals(sid)) {
                                    for (int l = 0; l < modifyIndex.length(); l++) {
                                        LeagueMatchArray.getJSONArray(j).put(modifyIndex.getInt(l), modifyData.getString(l));
                                    }
                                }
                            }
                            if (deleteData.contains(sid)) {
                                LeagueMatchArray.remove(j);
                            }
                        }
                        if (LeagueMatchArray.length() < 1) {
                            dataListArray.remove(i);
                        }
                    }
                }
                if (added || deleted || modified) {
                    return updateJsonData(dataListArray);
                }

            }

            return new ArrayList<>();
        } catch (JSONException je) {
            je.printStackTrace();
            return new ArrayList<>();
        }

    }

    private void parseLidValue(JSONArray jsonArray) throws JSONException {
        JSONArray jsonArrayLID = jsonArray.getJSONArray(0);
        if (jsonArrayLID.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
            LID = jsonArrayLID.getString(1);
        }
    }

    protected abstract ResultIndexBean getResultIndexMap(String type);

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
            swipeToLoadLayout.setLoadingMore(false);
        } else {
            swipeToLoadLayout.setLoadingMore(false);
            swipeToLoadLayout.setLoadMoreEnabled(false);
        }

    }


    @Nullable
    protected List<TableModuleBean> parseTableModuleBeen(String s) throws JSONException {

        JSONArray jsonArray = new JSONArray(s);

        if (jsonArray.length() > 4) {
            parseLidValue(jsonArray);
            JSONArray dataListArray = jsonArray.getJSONArray(3);
            return updateJsonData(dataListArray);
        }
        return new ArrayList<>();
    }

    private ArrayList<TableModuleBean> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableModuleBean> tableModules = new ArrayList<>();
        matchArrayMap.put(getType(), dataListArray);
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                LeagueBean leagueBean;
                List<MatchBean> matchList = new ArrayList<>();
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
                            parseMatchList(matchList, matchArray);
                        }
                    } else {
                        continue;
                    }
                    tableModules.add(new TableModuleBean(leagueBean, matchList));
                }

            }
        }
        return tableModules;
    }

    public void onRightMarkClick(Bundle b) {
        baseView.onRightMarkClick(b);
    }

    protected abstract void parseMatchList(List<MatchBean> matchList, JSONArray matchArray) throws JSONException;


    public void switchedOddsType(String oddsType) {
        Flowable<String> flowable = getService(ApiService.class).getData(AppConstant.URL_ODDS_TYPE + oddsType);
        Disposable subscription = mApiWrapper.applySchedulers(flowable)
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        refresh(getType());
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
}