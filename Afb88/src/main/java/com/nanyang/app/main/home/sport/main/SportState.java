package com.nanyang.app.main.home.sport.main;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.center.PersonCenterActivity;
import com.nanyang.app.main.home.sport.dialog.ChooseMatchPop;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.NetWorkUtil;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.training.ScrollLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * sport页面球加载、分页、更新、显示的adapter的逻辑实现
 */

public abstract class SportState<B extends SportInfo, V extends SportContract.View<B>> implements IObtainDataState {
    private String LID = "";

    private int page;
    private List<TableSportInfo<B>> filterData;
    private Map<String, Boolean> leagueSelectedMap = new HashMap<>();

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
    protected MenuItemInfo param = null;


    public int getPageSize() {
        return pageSize;
    }

    private int pageSize = 15;

    public SportAdapterHelper<B> getAdapterHelper() {
        return adapterHelper;
    }

    private SportAdapterHelper<B> adapterHelper;

    public V getBaseView() {
        return baseView;
    }

    public void setBaseView(V mBaseView) {
        this.baseView = mBaseView;
        adapterHelper = onSetAdapterHelper();
        baseRecyclerAdapter = new BaseRecyclerAdapter<B>(baseView.getIBaseContext().getBaseActivity(), new ArrayList<B>(), adapterHelper.onSetAdapterItemLayout()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, B item) {
                adapterHelper.onConvert(holder, position, item);
            }
        };
        adapterHelper.bindAdapter(baseRecyclerAdapter);

        adapterHelper.setItemCallBack(onSetItemCallBack());

        baseView.setAdapter(baseRecyclerAdapter);
        mCompositeSubscription = new CompositeDisposable();

    }

    /**
     * 设置item中的子View 点击时间 响应     back.clickOdds(markTv, item, type, isHf, f);
     *
     * @return
     */
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

    public BaseRecyclerAdapter<B> getBaseRecyclerAdapter() {
        return baseRecyclerAdapter;
    }

    protected BaseRecyclerAdapter<B> baseRecyclerAdapter;

    @Override
    public void refresh() {
        if (param == null) {

            param = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getAllOddsType();
            setParam(param);
        }
        String url = getUrlString();


        Disposable subscribe = getService(ApiService.class).getData(url).subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                .map(new Function<String, List<TableSportInfo<B>>>() {

                    @Override
                    public List<TableSportInfo<B>> apply(String s) throws Exception {
                        return getTableSportInfos(s);

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
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.getIBaseContext().hideLoadingDialog();
                        baseView.reLoginPrompt(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                            @Override
                            public void clickCancel(View v) {
                                refresh();
                            }
                        });
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription1) throws Exception {

                        if (!NetWorkUtil.isNetConnected(getBaseView().getIBaseContext().getBaseActivity())) {
                            subscription1.cancel();
                            baseView.reLoginPrompt(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                                @Override
                                public void clickCancel(View v) {
                                    refresh();
                                }
                            });
                        } else {
                            baseView.getIBaseContext().showLoadingDialog();
                            subscription1.request(Long.MAX_VALUE);
                        }

                    }
                });
        mCompositeSubscription.add(subscribe);

    }

    private String getUrlString() {
        String url = getRefreshUrl();
        if (BuildConfig.FLAVOR.equals("afb1188")) {
            MenuItemInfo oddtype = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getOddsType();
            if (oddtype != null)
                url = url + "&accType=" + oddtype.getType();
        }
        return url;
    }

    @Override
    public boolean menu(TextView tvMenu) {

        popMenu = new BasePopupWindow(baseView.getIBaseContext().getBaseActivity(), tvMenu, LinearLayout.LayoutParams.MATCH_PARENT, 150) {
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
        rv_list.setLayoutManager(new GridLayoutManager(baseView.getIBaseContext().getBaseActivity(), 4));

        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(baseView.getIBaseContext().getBaseActivity(), new ArrayList<MenuItemInfo>(), R.layout.text_base_item) {
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
        types.add(new MenuItemInfo(R.mipmap.menu_group_oval_white, baseView.getIBaseContext().getBaseActivity().getString(R.string.Choose), "Choose"));
        types.add(new MenuItemInfo(R.mipmap.menu_error_white, baseView.getIBaseContext().getBaseActivity().getString(R.string.bet_list), "Not settled"));
        types.add(new MenuItemInfo(R.mipmap.menu_right_oval_white, baseView.getIBaseContext().getBaseActivity().getString(R.string.statement), "Settled"));
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
        Bundle b = new Bundle();
        switch (item.getType()) {
            case "Choose":
                createChoosePop(view);
                return;
            case "Not settled":
                b.putString(AppConstant.KEY_STRING, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.stake));
                ((BaseToolbarActivity) baseView.getIBaseContext()).skipAct(PersonCenterActivity.class, b);
                break;
            case "Settled":
                b.putString(AppConstant.KEY_STRING, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.statement));
                ((BaseToolbarActivity) baseView.getIBaseContext()).skipAct(PersonCenterActivity.class, b);
                break;
        }
    }

    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        baseRecyclerAdapter.addAllAndClear(types);

    }

    private void createChoosePop(View view) {
        ChooseMatchPop<B, TableSportInfo<B>> pop = new ChooseMatchPop<>(getBaseView().getIBaseContext().getBaseActivity(), view);
        pop.setList(allData, leagueSelectedMap);
        pop.setBack(new ChooseMatchPop.CallBack() {
            @Override
            public void chooseMap(Map<String, Boolean> map) {
                leagueSelectedMap = map;
                updateAllDate(allData);
            }
        });
        baseView.onPopupWindowCreated(pop, Gravity.CENTER);
    }


    /***
     * 显示数据最终调用的方法
     */
    public void showData() {
        List<B> listData = toMatchList(pageData);
        baseRecyclerAdapter.addAllAndClear(listData);
        List<B> listDataAll = toMatchList(filterData);
        baseView.onGetData(listDataAll);
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
    protected List<TableSportInfo<B>> parseTableModuleBeen(String s) throws JSONException {
        s = Html.fromHtml(s).toString();
        if (s.contains("Session Expired")) {
            baseView.reLoginPrompt("", new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
        } else {
            JSONArray jsonArray = new JSONArray(s);
            if (jsonArray.length() > 4) {
                JSONArray dataListArray = jsonArray.getJSONArray(3);
                return updateJsonData(dataListArray);
            }
        }
        return new ArrayList<>();
    }

    protected List<TableSportInfo<B>> updateJsonData(JSONArray dataListArray) throws JSONException {
        ArrayList<TableSportInfo<B>> tableModules = new ArrayList<>();
        if (dataListArray.length() > 0) {
            for (int i = 0; i < dataListArray.length(); i++) {
                JSONArray jsonArray3 = dataListArray.getJSONArray(i);
                TableSportInfo<B> bTableSportInfo = parseTableSportMatch(jsonArray3, false);
                if (bTableSportInfo != null && bTableSportInfo.getRows() != null && bTableSportInfo.getRows().size() > 0)
                    tableModules.add(bTableSportInfo);
            }
        }
        return tableModules;
    }

    protected TableSportInfo<B> parseTableSportMatch(JSONArray jsonArray3, boolean notify) throws JSONException {
        LeagueBean leagueBean = null;
        List<B> matchList = new ArrayList<>();
        if (jsonArray3.length() > 1) {
            JSONArray LeagueArray = jsonArray3.getJSONArray(0);
            if (LeagueArray.length() > 1) {
                leagueBean = new LeagueBean(LeagueArray.get(0).toString(), LeagueArray.getString(1));
                JSONArray LeagueMatchArray = jsonArray3.getJSONArray(1);
                if (LeagueMatchArray.length() > 0) {
                    for (int j = 0; j < LeagueMatchArray.length(); j++) {
                        JSONArray matchArray = LeagueMatchArray.getJSONArray(j);
                        matchList.add(parseMatch(matchArray, notify));
                    }
                }
            }

            return new TableSportInfo<>(leagueBean, matchList);

        }
        return null;
    }

    protected abstract String getRefreshUrl();

    public void initAllData(List<TableSportInfo<B>> allData) {
        page = 0;
        updateAllDate(allData);
    }

    protected void updateAllDate(List<TableSportInfo<B>> allData) {

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
        if (allData == null)
            return dateTemp;
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

    private List<TableSportInfo<B>> getTableSportInfos(String s) throws JSONException {
        String updateString = Html.fromHtml(s).toString();
        JSONArray jsonArray = new JSONArray(updateString);
        if (jsonArray.length() >= 5) {
            JSONArray jsonArrayLID = jsonArray.getJSONArray(0);
            if (jsonArrayLID.length() > 0) {//  [1,'c0d90d91d4ca5b3d','t',0,0,1,0,1,-1,'eng']
                synchronized (this) {
                    LID = jsonArrayLID.getString(1);
                }
            } else {
                LID = "";
            }//解析 下一个pid
            if (jsonArrayLID.optInt(0) == 1) {
                this.allData = parseTableModuleBeen(s);
            } else {
                this.allData = updateJsonArray(s);
            }
        }
        return allData;
    }


    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            Flowable<String> flowable = null;
            String url = getUrlString();
            if (LID.length() > 0) {
                flowable = getService(ApiService.class).getData(url + "&LID=" + LID);
            } else
                flowable = getService(ApiService.class).getData(url);
            Disposable subscribe = flowable
                    .subscribeOn(Schedulers.io()).observeOn(Schedulers.io())
                    .takeWhile(new Predicate<String>() {
                        @Override
                        public boolean test(String s) throws Exception {
                            return NetWorkUtil.isNetConnected(baseView.getIBaseContext().getBaseActivity());
                        }
                    })
                    .map(new Function<String, List<TableSportInfo<B>>>() {
                        @Override
                        public List<TableSportInfo<B>> apply(String s) throws Exception {
                            return getTableSportInfos(s);
                        }

                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<List<TableSportInfo<B>>>() {//onNext
                                   @Override
                                   public void accept(List<TableSportInfo<B>> allData) throws Exception {
                                       if (allData != null) {
                                           updateAllDate(allData);
                                       }
                                   }
                               }, new Consumer<Throwable>() {//错误
                                   @Override
                                   public void accept(Throwable throwable) throws Exception {
                                       baseView.onFailed(throwable.getMessage());
                                   }
                               }, new Action() {//完成
                                   @Override
                                   public void run() throws Exception {

                                   }
                               }, new Consumer<Subscription>() {//开始绑定
                                   @Override
                                   public void accept(Subscription subscription1) throws Exception {

                                       subscription1.request(Long.MAX_VALUE);
                                   }
                               }
                    );
            mCompositeSubscription.add(subscribe);

            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };
    Handler updateHandler = new Handler();

    @Override
    public void startUpdateData() {
        stopUpdateData();
        updateHandler.postDelayed(dataUpdateRunnable, 20000);// 打开定时器，执行操作
    }


    @Override
    public void stopUpdateData() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected List<TableSportInfo<B>> updateJsonArray(String updateString) throws JSONException {

        updateString = Html.fromHtml(updateString).toString();
        LogUtil.d("UpdateData", updateString);
        JSONArray jsonArray = new JSONArray(updateString);
        if (jsonArray.length() > 4) {
            JSONArray deleteArray = jsonArray.getJSONArray(2);
            for (int i = 0; i < deleteArray.length(); i++) {
                delMatch(deleteArray.getString(i));
            }
            JSONArray addArray = jsonArray.getJSONArray(3);
            for (int i = 0; i < addArray.length(); i++) {
                addMatch(addArray.getJSONArray(i), true);
            }
            JSONArray updateArray = jsonArray.getJSONArray(4);
            for (int i = 0; i < updateArray.length(); i++) {
                addMatch(updateArray.getJSONArray(i), false);
            }
            if (jsonArray.length() > 5) {
                JSONArray modifyArray = jsonArray.getJSONArray(5);
                for (int i = 0; i < modifyArray.length(); i++) {
                    modifyMatch(modifyArray.getJSONArray(i));
                }
            }
        } else {
            LID = "";
        }

        return allData;

    }

    private void modifyMatch(JSONArray jsonArray) {
        if (jsonArray.length() > 2) {
            for (int i = 0; i < allData.size(); i++) {
                List<B> rows = allData.get(i).getRows();
                for (int i1 = 0; i1 < rows.size(); i1++) {
                    if (rows.get(i1).getSocOddsId().equals(jsonArray.optString(0))) {
                        modifySoc(i, i1, jsonArray.optJSONArray(1), jsonArray.optJSONArray(2));
                    }
                }
            }
        }
    }

    private void modifySoc(int i, int i1, JSONArray indexArray, JSONArray dataArray) {
        allData.get(i).getRows().get(i1).setNotify(false);
        for (int k = 0; k < indexArray.length(); k++) {
            allData.get(i).getRows().get(i1).setValue(indexArray.optInt(k), dataArray.optString(k));
        }
    }

    private void addMatch(JSONArray match, boolean isNew) throws JSONException {
        JSONArray socArray = match.optJSONArray(1);
        B b = parseMatch(socArray.getJSONArray(0), false);
        int n = 0;
        if (isNew) {
            LID = "";
        }
        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<B> bTableSportInfo = allData.get(i);
            LeagueBean leagueBean = bTableSportInfo.getLeagueBean();
            if (leagueBean != null && leagueBean.getModuleId().equals(match.optJSONArray(0).optString(0))) {
                for (int j = 0; j < socArray.length(); j++) {
                    addSoc(i, socArray.optJSONArray(j), true, isNew);
                }
                return;
            }
        }
        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<B> bTableSportInfo = allData.get(i);
            if (bTableSportInfo.getRows().get(bTableSportInfo.getRows().size() - 1).getSocOddsId().equals(b.getPreSocOddsId())) {
                n = i + 1;
                break;
            }
        }
        allData.add(n, parseTableSportMatch(match, true));
    }

    private void addSoc(int i, JSONArray soc, boolean notify, boolean isNew) throws JSONException {
        B b = parseMatch(soc, notify);
        List<B> rows = allData.get(i).getRows();
        for (int i1 = 0; i1 < rows.size(); i1++) {
            if (isNew) {
                if (rows.get(i1).getSocOddsId().equals(b.getPreSocOddsId())) {
                    allData.get(i).getRows().add(i1 + 1, b);
                    return;
                }
            } else {
                if (rows.get(i1).getSocOddsId().equals(b.getSocOddsId())) {
                    allData.get(i).getRows().set(i1, b);
                    return;
                }
            }
        }
        allData.get(i).getRows().add(0, b);
    }

    private void delMatch(String sid) throws JSONException {
        for (int i = 0; i < allData.size(); i++) {
            List<B> rows = allData.get(i).getRows();
            if (rows != null && rows.size() > 0) {
                for (int j = 0; j < rows.size(); j++) {
                    if (rows.get(j).getSocOddsId().equals(sid)) {
                        allData.get(i).getRows().remove(j);
                        if (allData.get(i).getRows().size() == 0) {
                            allData.remove(i);
                        }
                    }
                }
            }
        }
    }


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

        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getIBaseContext().getBaseActivity(), getTypes(), R.layout.text_base_item) {
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
    public void switchOddsType(final String oddsType) {

        Map<String, String> map = new HashMap<>();

        LoginInfo.LanguageWfBean languageWfBean = new LoginInfo.LanguageWfBean("");
        languageWfBean.setAccType(oddsType);
        map.put("_fm", languageWfBean.getJson());
        Disposable subscription = getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, map)


                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                       return getService(ApiService.class).getData(AppConstant.getInstance().URL_ODDS_TYPE + oddsType);
                    }
                })
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {

                        refresh();
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.getIBaseContext().hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.getIBaseContext().showLoadingDialog();
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
            Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().URL_SOCCER_REMOVE_MIX).subscribeOn(Schedulers.io())
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
                            getBaseView().getIBaseContext().hideLoadingDialog();
                        }
                    }, new Action() {//完成
                        @Override
                        public void run() throws Exception {
                            getBaseView().getIBaseContext().hideLoadingDialog();
                        }
                    }, new Consumer<Subscription>() {//开始绑定
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            getBaseView().getIBaseContext().showLoadingDialog();
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
        layoutParams.width = DeviceUtils.dip2px(getBaseView().getIBaseContext().getBaseActivity(), 24);
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
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_H_A), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FULL_O_U)));
        List<String> items1 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HALF_H_A), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HALF_O_U)));
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

    public void setParam(MenuItemInfo mt) {
        this.param = mt;
        switchAllOdds(mt.getType());

    }

    public void switchAllOdds(String oddsType) {
        if (!getAllOddsUrl().isEmpty()) {
            Disposable subscription = getService(ApiService.class).getData(getAllOddsUrl() + oddsType).subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String allData) throws Exception {
                        }
                    }, new Consumer<Throwable>() {//错误
                        @Override
                        public void accept(Throwable throwable) throws Exception {

                            baseView.getIBaseContext().hideLoadingDialog();
                        }
                    }, new Action() {//完成
                        @Override
                        public void run() throws Exception {

                        }
                    }, new Consumer<Subscription>() {//开始绑定
                        @Override
                        public void accept(Subscription subscription) throws Exception {
                            baseView.getIBaseContext().showLoadingDialog();
                            subscription.request(Long.MAX_VALUE);
                        }
                    });
            mCompositeSubscription.add(subscription);

        }
    }

    protected String getAllOddsUrl() {
        return "";
    }

    public void initAllOdds(TextView ivAllAdd) {
        if (!getAllOddsUrl().isEmpty()) {
            ivAllAdd.setVisibility(View.VISIBLE);
            ivAllAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAllOdds((TextView) v);
                }
            });

        } else {
            ivAllAdd.setVisibility(View.GONE);
        }
    }

    private void showAllOdds(final TextView textView) {

        BasePopupWindow basePopupWindow = new BasePopupWindow(getBaseView().getIBaseContext().getBaseActivity(), textView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                rv.setPadding(0, 0, 0, 0);
                rv.setLayoutManager(new LinearLayoutManager(getBaseView().getIBaseContext().getBaseActivity()));
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.All_Markets), "&mt=0"));//accType=
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Main_Markets), "&mt=1"));
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Other_Bet_Markets), "&mt=2"));

                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getIBaseContext().getBaseActivity(), list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setPadding(0, 0, 0, 0);
                        tv.setText(item.getText());
                        tv.setBackgroundResource(R.color.black_grey);


                    }
                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        closePopupWindow();
                        ((SportActivity) baseView.getIBaseContext()).setAllOdds(item);
                        textView.setText(item.getText());
                        if (item.getText().equals(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.All_Markets))) {
                            ((BaseToolbarActivity) baseView.getIBaseContext()).dynamicAddView(textView, "drawableLeft", R.mipmap.add_green);
//                            textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.add_green, 0, 0, 0);
                        } else {
                            ((BaseToolbarActivity) baseView.getIBaseContext()).dynamicAddView(textView, "drawableLeft", R.mipmap.sport_delete_green);
//                            textView.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.sport_delete_green, 0, 0, 0);
                        }
                        setParam(item);
                        if (!getAllOddsUrl().isEmpty()) {
                            refresh();
                        }

                    }
                });
                rv.setAdapter(baseRecyclerAdapter);
            }
        };
        basePopupWindow.setTrans(1f);
        baseView.onPopupWindowCreated(basePopupWindow, -2);

    }


    protected abstract B parseMatch(JSONArray matchArray, boolean notify) throws JSONException;

    IBetHelper betHelper;

    public IBetHelper getBetHelper() {
        if (betHelper == null)
            betHelper = onSetBetHelper();
        return betHelper;
    }

    public abstract IBetHelper onSetBetHelper();
}
