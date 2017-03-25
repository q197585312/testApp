package com.nanyang.app.main.home.sport.main;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.center.PersonCenterActivity;
import com.nanyang.app.main.home.sport.dialog.ChooseMatchPop;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * sport页面球加载、分页、更新、显示的adapter的逻辑实现
 */

public abstract class SportState<B extends SportInfo, V extends SportContract.View<B>> implements IObtainDataState {
    private String LID;
    private int page;
    private List<TableSportInfo<B>> filterData;
    private Map<String, Boolean> leagueSelectedMap = new HashMap<>();
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

    protected CompositeDisposable mCompositeSubscription;
    protected BasePopupWindow popMenu;
    private SwipeToLoadLayout swipeToLoadLayout;


    public int getPageSize() {
        return pageSize;
    }

    private int pageSize = 15;
    /**
     * 更新
     */
    private Disposable updateDisposable;


    SportAdapterHelper<B> adapterHelper;

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


        adapterHelper.setItemCallBack(onSetItemCallBack());

        baseView.setAdapter(baseRecyclerAdapter);
        mCompositeSubscription = new CompositeDisposable();
    }

    protected abstract SportAdapterHelper.ItemCallBack onSetItemCallBack();


    @Override
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.clear();
        }
    }

    public SportState(V baseView) {
        setBaseView(baseView);
    }

    private V baseView;
    protected BaseRecyclerAdapter<B> baseRecyclerAdapter;

    @Override
    public Disposable refresh() {
        Disposable subscribe = getService(ApiService.class).getData(getRefreshUrl()).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
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
                        baseView.checkMix(isMix());
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
        mCompositeSubscription.add(subscribe);
        return subscribe;

    }

    @Override
    public boolean menu(TextView tvMenu) {

        popMenu = new BasePopupWindow(baseView.getContextActivity(), tvMenu, LinearLayout.LayoutParams.MATCH_PARENT, 150) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice_bottom;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                setBottomMenuAdapter(rv_list);
            }
        };
        popMenu.setTrans(1f);
        baseView.onPopupWindowCreated(popMenu, Gravity.BOTTOM);
        return true;
    }

    private void setBottomMenuAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new GridLayoutManager(baseView.getContextActivity(), 4));

        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(baseView.getContextActivity(), new ArrayList<MenuItemInfo>(), R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
                tv.setTextSize(10);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }
        };
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(R.mipmap.menu_group_oval_white, baseView.getContextActivity().getString(R.string.Choose), "Choose"));
        types.add(new MenuItemInfo(R.mipmap.menu_error_white, baseView.getContextActivity().getString(R.string.not_settled), "Not settled"));
        types.add(new MenuItemInfo(R.mipmap.menu_right_oval_white, baseView.getContextActivity().getString(R.string.settled), "Settled"));
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                popMenu.closePopupWindow();
                popMenuItemClick(view, item);

            }
        });
        bindMenuAdapter(baseRecyclerAdapter, types);


        rv_list.setAdapter(baseRecyclerAdapter);
    }

    protected void popMenuItemClick(View view, MenuItemInfo item) {
        switch (item.getType()) {
            case "Choose":
                createChoosePop(view);
                return;
            case "Not settled":
                ((BaseToolbarActivity) baseView.getContextActivity()).skipAct(PersonCenterActivity.class);
                break;
            case "Settled":
                ((BaseToolbarActivity) baseView.getContextActivity()).skipAct(PersonCenterActivity.class);
                break;
        }
    }

    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        baseRecyclerAdapter.addAllAndClear(types);

    }

    private void createChoosePop(View view) {
        ChooseMatchPop<B, TableSportInfo<B>> pop = new ChooseMatchPop<>(getBaseView().getContextActivity(), view);
        pop.setList(allData, leagueSelectedMap);
        pop.setBack(new ChooseMatchPop.CallBack() {
            @Override
            public void chooseMap(Map<String, Boolean> map) {
                leagueSelectedMap = map;
                filterData(allData);
                showCurrentData();
            }
        });
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }

    protected abstract IBetHelper onSetBetHelper();


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

    public void initAllData(List<TableSportInfo<B>> allData) {

        this.allData = allData;
        page = 0;
        updateAllDate(allData);
    }

    private void updateAllDate(List<TableSportInfo<B>> allData) {

        filterData(allData);
        showCurrentData();
    }

    private void showCurrentData() {
        pageData(filterData);
        showData();
    }

    private List<TableSportInfo<B>> pageData(List<TableSportInfo<B>> filterData) {
        List<TableSportInfo<B>> pageList;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }
        this.pageData = pageList;
        return pageList;

    }

    protected final List<TableSportInfo<B>> filterData(List<TableSportInfo<B>> allData) {
        List<TableSportInfo<B>> dateTemp = new ArrayList<>();
        for (TableSportInfo<B> bTableSportInfo : allData) {
            if (leagueSelectedMap.get(bTableSportInfo.getLeagueBean().getModuleId()) == null || leagueSelectedMap.get(bTableSportInfo.getLeagueBean().getModuleId())) {
                dateTemp.add(bTableSportInfo);
            }
        }
        this.filterData = filterChildData(dateTemp);
        if (swipeToLoadLayout != null)
            swipeToLoadLayout.setLoadMoreEnabled(true);
        return filterData;

    }

    protected abstract List<TableSportInfo<B>> filterChildData(List<TableSportInfo<B>> dateTemp);


    @Override
    public Disposable startUpdateData() {
        stopUpdateData();
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
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                    }
                });
        mCompositeSubscription.add(updateDisposable);
        return updateDisposable;

    }


    @Override
    public void stopUpdateData() {
        if (updateDisposable != null) {
            updateDisposable.dispose();
            boolean isDisposed = updateDisposable.isDisposed();
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
        this.swipeToLoadLayout = swipeToLoadLayout;
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
        this.swipeToLoadLayout = swipeToLoadLayout;
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

    @Override
    public void notifyDataChanged() {
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void switchOddsType(String oddsType) {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.URL_ODDS_TYPE + oddsType).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String allData) throws Exception {
                        refresh();
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
    public boolean isMix() {
        return false;
    }

    @Override
    public void clearMix() {
        if (isMix()) {
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
        }
    }

    @Override
    public void setScrollHeaderContent(ScrollLayout slHeader, TextView tvAos) {
        tvAos.setText(R.string.AOS);
        ViewGroup.LayoutParams layoutParams = tvAos.getLayoutParams();
        layoutParams.width = DeviceUtils.dip2px(getBaseView().getContextActivity(), 22);
        List<List<String>> lists = initHeaderList();
        for (int i = 0; i < lists.size(); i++) {
            View childAt = slHeader.getChildAt(i);
            childAt.setVisibility(View.VISIBLE);
            ViewHolder viewHolder = new ViewHolder(childAt);
            List<String> strings = lists.get(i);
            if (strings.size() > 0) {
                viewHolder.tvHeadLeft.setVisibility(View.VISIBLE);
                viewHolder.tvHeadLeft.setText(strings.get(0));
            } else
                viewHolder.tvHeadLeft.setVisibility(View.GONE);
            if (strings.size() > 1) {
                viewHolder.tvHeadRight.setVisibility(View.VISIBLE);
                viewHolder.tvHeadRight.setText(strings.get(1));
            } else
                viewHolder.tvHeadRight.setVisibility(View.GONE);
            if (strings.size() > 2) {
                viewHolder.tvHead3.setVisibility(View.VISIBLE);
                viewHolder.tvHead3.setText(strings.get(2));
            } else
                viewHolder.tvHead3.setVisibility(View.GONE);
        }
    }

    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getContextActivity().getString(R.string.FULL_H_A), getBaseView().getContextActivity().getString(R.string.FULL_O_U)));
        List<String> items1 = new ArrayList<>(Arrays.asList(getBaseView().getContextActivity().getString(R.string.HALF_H_A), getBaseView().getContextActivity().getString(R.string.HALF_O_U)));
        texts.add(items0);
        texts.add(items1);
        return texts;
    }

    static class ViewHolder {
        @Bind(R.id.tv_head_left)
        TextView tvHeadLeft;
        @Bind(R.id.tv_head_right)
        TextView tvHeadRight;
        @Bind(R.id.tv_head_3)
        TextView tvHead3;
        @Bind(R.id.ll_head_parent)
        LinearLayout llHeadParent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
