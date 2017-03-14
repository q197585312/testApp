package com.nanyang.app.main.home.sportInterface;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * sport页面球加载、分页、更新、显示的adapter的逻辑实现
 */

public abstract class SportState<B extends SportInfo, V extends SportContract2.View<B>> implements IObtainDataState {
    private String LID;
    private int page;
    private List<TableSportInfo<B>> filterData;
    /**
     * 当前显示数据的JsonArray，用来更新
     */
    protected JSONArray dataJsonArray;
    /**
     * 显示的所有数据
     */
    protected List<TableSportInfo<B>> allData;
    /**
     * 所有数据分页后的当前页面数据
     */
    protected List<TableSportInfo<B>> pageData;

    public int getPageSize() {
        return pageSize;
    }

    private int pageSize = 5;
    /**
     * 更新
     */
    private Disposable updateDisposable;



    IAdapterHelper<B> adapterHelper;

    public V getBaseView() {
        return baseView;
    }

    public void setBaseView(V mBaseView) {
        this.baseView = mBaseView;
        adapterHelper = onSetAdapterHelper();
        baseRecyclerAdapter = new BaseRecyclerAdapter<B>(baseView.getContextActivity(), new ArrayList<B>(), adapterHelper.onSetAdapterItemLayout()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, B item) {
                adapterHelper.onConvert(holder, position, item);
            }
        };
        baseView.setAdapter(baseRecyclerAdapter);
    }



    public SportState(V baseView) {
        setBaseView(baseView);
    }

    private V baseView;
    protected BaseRecyclerAdapter<B> baseRecyclerAdapter;

    @Override
    public Disposable refresh() {
        return getService(ApiService.class).getData(getRefreshUrl()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .map(new Function<String, List<TableSportInfo<B>>>() {

                    @Override
                    public List<TableSportInfo<B>> apply(String s) throws Exception {
                        return parseTableModuleBeen(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<TableSportInfo<B>>>() {//onNext
                    @Override
                    public void accept(List<TableSportInfo<B>> allData1) throws Exception {
                        initAllData(allData1);
                        startUpdateData();
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
                    public void accept(Subscription subscription1) throws Exception {
                        baseView.showLoadingDialog();
                        subscription1.request(Long.MAX_VALUE);
                    }
                });
    }

    /***
     * 显示数据最终调用的方法
     */
    public void showData() {
        List<B> listData = toMatchList(pageData);
        baseRecyclerAdapter.addAllAndClear(listData);
        baseView.onGetData(listData);
    }

    private List<B> toMatchList(List<TableSportInfo<B>> pageList) {
        List<B> pageMatch = new ArrayList<>();

        for (int i = 0; i < pageList.size(); i++) {
            TableSportInfo<B> item = pageList.get(i);
            List<B> rows = item.getRows();
            for (int j = 0; j < rows.size(); j++) {
                B cell = rows.get(j);
                if (j == 0) {
                    cell.setType(SportInfo.Type.TITLE);
                } else {
                    cell.setType(SportInfo.Type.ITME);
                }
                cell.setModuleId(item.getLeagueBean().getModuleId());
                cell.setModuleTitle(item.getLeagueBean().getModuleTitle());
                pageMatch.add(cell);
            }
        }
        return pageMatch;
    }

    @Nullable
    private List<TableSportInfo<B>> parseTableModuleBeen(String s) throws JSONException {
        JSONArray jsonArray = new JSONArray(s);
        if (jsonArray.length() > 4) {
            parseLidValue(jsonArray);
            JSONArray dataListArray = jsonArray.getJSONArray(3);
            this.dataJsonArray = dataListArray;
            return updateJsonData(dataListArray);
        }
        return new ArrayList<>();
    }

    protected abstract List<TableSportInfo<B>> updateJsonData(JSONArray dataListArray) throws JSONException;

    /**
     * 解析出下次更新需要的LID对象
     */
    private void parseLidValue(JSONArray jsonArray) throws JSONException {
        JSONArray jsonArrayLID = jsonArray.getJSONArray(0);
        if (jsonArrayLID.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
            synchronized (this) {
                LID = jsonArrayLID.getString(1);
            }
        }
    }

    protected abstract String getRefreshUrl();

    void initAllData(List<TableSportInfo<B>> allData) {
        this.allData = allData;
        page = 0;
        updateAllDate(allData);

    }

    private void updateAllDate(List<TableSportInfo<B>> allData) {
        this.filterData = filterData(allData);
        showCurrentData();
    }

    private void showCurrentData() {
        this.pageData = pageData(filterData);
        showData();
    }

    private List<TableSportInfo<B>> pageData(List<TableSportInfo<B>> filterData) {
        List<TableSportInfo<B>> pageList;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }
        return pageList;

    }

    protected abstract List<TableSportInfo<B>> filterData(List<TableSportInfo<B>> allData);


    @Override
    public Disposable startUpdateData() {
        updateDisposable = Flowable.interval(20, 20, TimeUnit.SECONDS).flatMap(new Function<Long, Publisher<String>>() {
            @Override
            public Publisher<String> apply(Long aLong) throws Exception {
                if (LID != null && LID.length() > 0)
                    return getService(ApiService.class).getData(getRefreshUrl() + "&LID=" + LID);
                else
                    return getService(ApiService.class).getData(getRefreshUrl());
            }
        }).observeOn(Schedulers.io()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .map(new Function<String, List<TableSportInfo<B>>>() {

                    @Override
                    public List<TableSportInfo<B>> apply(String s) throws Exception {
                        if (LID != null && LID.length() > 0)
                            return updateJsonArray(s);
                        else
                            return parseTableModuleBeen(s);
                    }
                }).observeOn(AndroidSchedulers.mainThread())

                .subscribe(new Consumer<List<TableSportInfo<B>>>() {//onNext
                    @Override
                    public void accept(List<TableSportInfo<B>> allData) throws Exception {
                        if (allData != null && allData.size() > 0) {
                            updateAllDate(allData);
                        }


                    }
                });
        return updateDisposable;

    }


    @Override
    public void stopUpdateData() {
        if (updateDisposable != null) {
            updateDisposable.dispose();
           boolean isDisposed= updateDisposable.isDisposed();
            updateDisposable = null;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected List<TableSportInfo<B>> updateJsonArray(String updateString) {
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


                if (added) {//可以添加数据
                    for (int i = 0; i < dataJsonArray.length(); i++) {
                        JSONArray jsonArray3 = dataJsonArray.getJSONArray(i);
                        if (jsonArray3.length() > 1) {
                            JSONArray LeagueArray = jsonArray3.getJSONArray(0);
                            JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                            JSONArray matchAdd = addMap.get(LeagueArray.getString(0));

                            if (matchAdd != null) {//插入到已有联赛内
                                JSONArray Array = new JSONArray();//修改后的联赛
                                for (int j = 0; j < matchAdd.length(); j++) {//遍历要添加的比赛
                                    JSONArray jsonArray1 = matchAdd.getJSONArray(j);
                                    String preId = jsonArray1.getString(getIndexPreSocOddsId());

                                    if (preId == null || preId.equals("")) {//没有PreId加到最前面
                                        Array.put(jsonArray1);//先加
                                        for (int k = 0; k < LeagueMatchArray.length(); k++) {
                                            Array.put(LeagueMatchArray.getJSONArray(k));
                                        }
                                    } else {
                                        boolean addIn = false;
                                        for (int k = 0; k < LeagueMatchArray.length(); k++) {
                                            String id = LeagueMatchArray.getJSONArray(k).getString(getIndexSocOddsId());
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
                                dataJsonArray.put(array);
                            }
                        }
                    }
                }
                for (int i = 0; i < dataJsonArray.length(); i++) {
                    JSONArray jsonArray3 = dataJsonArray.getJSONArray(i);
                    if (jsonArray3.length() > 1) {
                        JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                        for (int j = 0; j < LeagueMatchArray.length(); j++) {
                            String sid = LeagueMatchArray.getJSONArray(j).getString(getIndexSocOddsId());


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
                            dataJsonArray.remove(i);
                        }
                    }
                }
                if (added || deleted || modified) {
                    return updateJsonData(dataJsonArray);
                }

            }

            return new ArrayList<>();
        } catch (JSONException je) {
            je.printStackTrace();
            return new ArrayList<>();
        }

    }

    protected abstract int getIndexSocOddsId();

    protected abstract int getIndexPreSocOddsId();

    @Override
    public void onPrevious(SwipeToLoadLayout swipeToLoadLayout) {
        if (page == 0) {
            refresh();
        } else {
            page--;
            showCurrentData();
            if (page == 0) {
                swipeToLoadLayout.setLoadMoreEnabled(true);
            }
        }
        swipeToLoadLayout.setRefreshing(false);
    }

    @Override
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


    @Override
    public BaseRecyclerAdapter switchTypeAdapter() {

        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getContextActivity(), getTypes(), R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                onTypeClick(item);

            }
        });
        return baseRecyclerAdapter;
    }

    protected abstract void onTypeClick(MenuItemInfo item);

    protected abstract List<MenuItemInfo> getTypes();


}
