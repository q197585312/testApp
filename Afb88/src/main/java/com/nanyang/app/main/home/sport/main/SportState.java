package com.nanyang.app.main.home.sport.main;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.koushikdutta.async.callback.CompletedCallback;
import com.koushikdutta.async.callback.WritableCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.login.LoginInfo;
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
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.swipetoloadlayout.SwipeToLoadLayout;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    private String LID = "";
    protected Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();

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

    protected WebSocket webSocketBase;

    protected String TAG = "SportState";
    private DataUpdateRunnable dataUpdateRunnable;
    private boolean isSearch = false;
    private String searchStr = "";

    protected boolean isHide = false;


    public int getPageSize() {
        return pageSize;
    }

    private int pageSize = 15;

    public SportAdapterHelper<B> getAdapterHelper() {
        return adapterHelper;
    }

    protected SportAdapterHelper<B> adapterHelper;

    public V getBaseView() {
        return baseView;
    }

    public void setBaseView(V mBaseView) {
        this.baseView = mBaseView;
        mCompositeSubscription = new CompositeDisposable();
    }

    public void handleAdapter() {
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
        handleAdapter();
    }

    protected V baseView;

    public BaseRecyclerAdapter<B> getBaseRecyclerAdapter() {
        return baseRecyclerAdapter;
    }

    protected BaseRecyclerAdapter<B> baseRecyclerAdapter;

    //    https://www.afb1188.com/H50/Pub/pcode.axd?_fm=%7B%22ACT%22%3A%22Getmenu%22%2C%22ot%22%3A%22t%22%2C%22pgLable%22%3A%220.5025137446223212%22%2C%22vsn%22%3A%224.0.12%22%2C%22PT%22%3A%22wfMainH50%22%7D&_db=%7B%7D
//    https://ws.afb1188.com/fnOddsGen?wst=wsSocAllGen&g=200&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-27&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&ov=0&mt=0&FAV=&SL=&LSL=undefined
//    https://www.afb1188.com/H50/Pub/pcode.axd?_fm={"ACT":"Getmenu","ot":"t","pgLable":"0.5025137446223212","vsn":"4.0.12","PT":"wfMainH50"}&_db={}
//    https://ws.afb1188.com/fnOddsGen?wst=wsSocAllGen&g=1&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-27&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&ov=0&mt=0&FAV=&SL=&LSL=undefined
    @Override
    public void refresh() {
        if (webSocketBase != null && webSocketBase.isOpen()) {
            webSocketBase.close();
        }
        if (isHide)
            return;
        if (!NetWorkUtil.isNetConnected(getBaseView().getIBaseContext().getBaseActivity())) {
            baseView.reLoginPrompt(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
            return;
        }
        updateHandler.post(new Runnable() {
            @Override
            public void run() {
                baseView.getIBaseContext().showLoadingDialog();
            }
        });
        final String url = getUrlString();
        AsyncHttpClient.getDefaultInstance().websocket(url, null, new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, final WebSocket webSocket) {
                Log.d("Socket", "onCompleted-----------" + url);
                if (ex != null) {
                    Log.e(TAG, "Exception----------------" + ex.getLocalizedMessage());
                    ex.printStackTrace();
                    return;
                }

                webSocket.setStringCallback(new WebSocket.StringCallback() {
                    @Override
                    public void onStringAvailable(String s) {
                        Log.d("Socket", "onStringAvailable-----------" + s);
                        if (s.equals("3"))
                            return;
                        try {
                            allData = getTableSportInfos(s);
                            updateHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    baseView.getIBaseContext().hideLoadingDialog();
                                    if (baseView.getIBaseContext().getBaseActivity() != null && baseView.getIBaseContext().getBaseActivity().isHasAttached()) {
//                                        baseView.checkMix(isMix());
                                        initAllData(allData);
                                    }
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                webSocket.setPingCallback(new WebSocket.PingCallback() {
                    @Override
                    public void onPingReceived(String s) {
                        Log.d("Socket", "onPongCallback" + s);
                    }
                });
                webSocket.setPongCallback(new WebSocket.PongCallback() {
                    @Override
                    public void onPongReceived(String s) {
                        Log.d("Socket", "onPongReceived" + s);
                    }
                });
                webSocket.setClosedCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "onClosedCallback出错");
                            return;
                        }
                        Log.d("Socket", "onClosedCallback");
                    }
                });

                webSocket.setEndCallback(new CompletedCallback() {
                    @Override
                    public void onCompleted(Exception ex) {
                        if (ex != null) {
                            Log.d("Socket", "setEndCallback出错");
                            return;
                        }
                        Log.d("Socket", "setEndCallback");
                    }
                });
                webSocketBase = webSocket;
                startUpdateData();
                webSocket.setWriteableCallback(new WritableCallback() {
                    @Override
                    public void onWriteable() {
                        Log.d("Socket", "WritableCallback");

                    }
                });

            }
        });

    }

    protected String getUrlString() {
        String url = getRefreshUrl();
        if (BuildConfig.FLAVOR.equals("afb1188")) {
            MenuItemInfo oddtype = ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getOddsType();
            if (oddtype != null)
                url = url + "&accType=" + oddtype.getType();
        }
        url = url + "&CTOddsDiff=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getCTOddsDiff()
                + "&CTSpreadDiff=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getCTSpreadDiff()
                + "&oddsDiff=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getOddsDiff()
                + "&spreadDiff=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getSpreadDiff()
                + "&betable=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getBetable()
                + "&tfDate=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getTfDate()
                + "&LangCol=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getLangCol()
                + "&um=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getUm()
                + "&delay=" + ((BaseToolbarActivity) baseView.getIBaseContext().getBaseActivity()).getApp().getUser().getDelay()
                + "&pn=1"
                + "&tp=1"
                + "&ov=" + ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getSortType()
                + "&mt=" + ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).getAllOddsType().getType();
        int HH = Integer.parseInt(DateUtils.getCurrentDate("HH"));
        url += "&tf=-1";
//        if (HH >= 11) {
//            url += "&tf=-1";
//        }
        url = url + "&wd=" + ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).wd;
        Log.d("getUrlString", "HH: " + HH);
        Log.d("getUrlString", "url: " + url);
        return url;
    }

    @Override
    public boolean menu(View tvMenu) {

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
        }
    }

    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        baseRecyclerAdapter.addAllAndClear(types);

    }

    public void createChoosePop(View view) {
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

    public int getTitleContentColor() {
        return Color.WHITE;
    }

    public int getNormalContentColor() {
        return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.grey_white);
    }

    private List<B> toMatchList(List<TableSportInfo<B>> pageList) {
        List<B> pageMatch = new ArrayList<>();

        for (int i = 0; i < pageList.size(); i++) {
            TableSportInfo<B> item = pageList.get(i);
            List<B> rows = item.getRows();
            for (int j = 0; j < rows.size(); j++) {
                B cell = rows.get(j);
                if (j == 0) {
                    cell.setContentColor(getTitleContentColor());
                    cell.setType(SportInfo.Type.TITLE);
                } else {
                    if (rows.get(j - 1).getContentColor() == getTitleContentColor()) {
                        cell.setContentColor(getNormalContentColor());
                    } else {
                        cell.setContentColor(getTitleContentColor());
                    }
                    cell.setType(SportInfo.Type.ITME);
                }
                cell.setModuleId(item.getLeagueBean().getModuleId());
                cell.setModuleTitle(item.getLeagueBean().getModuleTitle());
                if (item.getLeagueBean().getModuleId() == null || item.getLeagueBean().getModuleTitle() == null)
                    LogUtil.e("xxxx", "null---" + item.toString());
                pageMatch.add(cell);
                Map<String, Boolean> moduleMap = localCollectionMap.get(cell.getModuleTitle().toString());
                if (moduleMap == null)
                    moduleMap = new HashMap<>();
                if (moduleMap.get(cell.getHome() + "+" + cell.getAway()) == null) {
                    moduleMap.put(cell.getHome() + "+" + cell.getAway(), false);
                    localCollectionMap.put(cell.getModuleTitle().toString(), moduleMap);
                }
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
        if (!isHide)
            updateAllDate(allData);
    }

    protected void updateAllDate(List<TableSportInfo<B>> allData) {
        filterData(allData);
        showCurrentData();
    }

    protected void showCurrentData() {
        this.pageData = pageData(filterData);
        showData();
    }

    protected List<TableSportInfo<B>> pageData(List<TableSportInfo<B>> filterData) {
        List<TableSportInfo<B>> pageList;
        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }

        return pageList;

    }

    protected final List<TableSportInfo<B>> filterData(List<TableSportInfo<B>> allData) {
        List<TableSportInfo<B>> dateTemp = new ArrayList<>();
        if (allData == null)
            return dateTemp;
        if (isSearch) {
            this.filterData = filterSearchData(allData);
        } else {
            for (TableSportInfo<B> bTableSportInfo : allData) {
                if (leagueSelectedMap.get(bTableSportInfo.getLeagueBean().getModuleId()) == null || leagueSelectedMap.get(bTableSportInfo.getLeagueBean().getModuleId())) {
                    dateTemp.add(bTableSportInfo);
                }
            }
            this.filterData = filterChildData(dateTemp);
        }
        if (swipeToLoadLayout != null)
            swipeToLoadLayout.setLoadMoreEnabled(true);
        return filterData;
    }

    private List<TableSportInfo<B>> filterSearchData(List<TableSportInfo<B>> data) {
        if (StringUtils.isNull(searchStr))
            return data;
        List<TableSportInfo<B>> moduleDate = new ArrayList<>();
        for (TableSportInfo<B> tableModuleBean : data) {
            if (tableModuleBean.getLeagueBean().getModuleTitle().contains(searchStr)) {
                moduleDate.add(tableModuleBean);
            } else {
                List<B> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<B> moduleCollection = new TableSportInfo<B>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                for (B matchBean : tableModuleBean.getRows()) {
                    if ((matchBean.getHome() + matchBean.getAway()).contains(searchStr)) {
                        moduleCollectionRows.add(matchBean);
                    }
                }
                moduleCollection.setRows(moduleCollectionRows);
                if (moduleCollectionRows.size() > 0)
                    moduleDate.add(moduleCollection);
            }
        }

        return moduleDate;
    }


    protected List<TableSportInfo<B>> filterChildData(List<TableSportInfo<B>> allData) {
        if (isCollection())
            return filterCollection(allData);
        else
            return allData;
    }

    private boolean isCollection;

    public boolean isCollection() {
        return isCollection;
    }

    public boolean collection() {
        isCollection = !isCollection;
        isSearch = false;
        initAllData(allData);
        return isCollection;
    }

    public void setSearch(boolean isSearch, String searchStr) {
        isCollection = false;
        this.isSearch = isSearch;
        this.searchStr = searchStr;
        initAllData(allData);
    }

    private List<TableSportInfo<B>> filterCollection(List<TableSportInfo<B>> data) {

        List<TableSportInfo<B>> moduleDate = new ArrayList<>();
        for (TableSportInfo<B> tableModuleBean : data) {
            if (null != localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                List<B> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<B> moduleCollection = new TableSportInfo<B>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                Map<String, Boolean> moduleMap = localCollectionMap.get(tableModuleBean.getLeagueBean().getModuleTitle());

                for (B matchBean : tableModuleBean.getRows()) {
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

    protected List<TableSportInfo<B>> getTableSportInfos(String s) throws JSONException {
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


    class DataUpdateRunnable implements Runnable {
        public DataUpdateRunnable(WebSocket webSocket) {
            this.webSocket = webSocket;
        }

        WebSocket webSocket;

        @Override
        public void run() {
            String cmd = "1";
            if (webSocket.isOpen()) {
                webSocket.send(cmd);
                Log.d("Socket", "发送了：" + cmd);
            }
            updateHandler.postDelayed(this, 30000);
        }
    }


    public Handler updateHandler = new Handler();

    @Override
    public void startUpdateData() {
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
        dataUpdateRunnable = new DataUpdateRunnable(webSocketBase);
        updateHandler.post(dataUpdateRunnable);// 打开定时器，执行操作
    }


    @Override
    public void stopUpdateData() {
        if (mCompositeSubscription != null)
            mCompositeSubscription.clear();
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
        if (webSocketBase != null)
            webSocketBase.close();
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

    public BaseRecyclerAdapter<MenuItemInfo<Integer>> switchTypeAdapter;

    @Override
    public BaseRecyclerAdapter switchTypeAdapter(final TextView textView, final JSONObject jsonObjectNum) {
        final SportActivity sportActivity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
        switchTypeAdapter = new BaseRecyclerAdapter<MenuItemInfo<Integer>>(getBaseView().getIBaseContext().getBaseActivity(), getTypes(), R.layout.item_sport_switch) {

            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<Integer> item) {
                TextView tvGamePic = holder.getTextView(R.id.img_game_pic);
                TextView tv = holder.getView(R.id.tv_game_name);
                String day = item.getDay();
                if (!TextUtils.isEmpty(day)) {
                    tvGamePic.setText(item.getDay());
                    tv.setText(item.getText() + " " + item.getDay());
                } else {
                    tv.setText(item.getText());
                }
                if (sportActivity.dateClickPositon == position) {
                    tvGamePic.setBackgroundResource(item.getParent());
                    holder.getView(R.id.ll_content).setBackgroundColor(ContextCompat.getColor(mContext, R.color.gary1));
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.google_green));
                } else {
                    tvGamePic.setBackgroundResource(item.getRes());
                    holder.getView(R.id.ll_content).setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.black_grey));
                }
                TextView tv_game_count = holder.getView(R.id.tv_game_count);
                tv_game_count.setVisibility(View.GONE);

                String dbid = ((BaseSportFragment) baseView).getBallDbid();
                if (StringUtils.isNull(dbid))
                    return;
                SportIdBean sportIdBean = AfbUtils.getSportByDbid(dbid);
                if (sportIdBean == null || StringUtils.isNull(sportIdBean.getDbid()))
                    return;
                String type = item.getType();
                Log.d("getType", "dbid:" + dbid + ",currentIdBean.getDbid()" + sportIdBean.getDbid() + ".type: " + type + ",jsonObjectNum:" + jsonObjectNum + ",currentIdBean:" + sportIdBean);
                String dBId = sportIdBean.getDbid();
                String runningStr = "M_RAm" + dBId;
                String todayStr = "M_TAm" + dBId;
                String earlyStr = "M_EAm" + dBId;
                String numGame = "";
                if (jsonObjectNum != null) {
                    if (type.equals("Running")) {
                        if (!TextUtils.isEmpty(jsonObjectNum.optString(runningStr))) {
                            numGame = jsonObjectNum.optString(runningStr);
                        }
                    } else if (type.equals("Today")) {
                        if (!TextUtils.isEmpty(jsonObjectNum.optString(todayStr))) {
                            numGame = jsonObjectNum.optString(todayStr);
                        }
                    } else if (type.equals("Early")) {
                        if (!TextUtils.isEmpty(jsonObjectNum.optString(earlyStr))) {
                            numGame = jsonObjectNum.optString(earlyStr);
                        }
                    } else {
                        numGame = "";
                    }
                } else {
                    numGame = "";
                }
                if (!TextUtils.isEmpty(numGame)) {
                    tv_game_count.setVisibility(View.VISIBLE);
                    tv_game_count.setText(numGame);
                } else {
                    tv_game_count.setVisibility(View.GONE);
                    tv_game_count.setText("");
                }
            }
        };
        switchTypeAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                onTypeWayClick(item, position);
            }
        });
        return switchTypeAdapter;
    }


    protected void onTypeWayClick(MenuItemInfo item, int position) {
        runWayItem(item);
        SportActivity sportActivity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
        if (item.getRes() == R.mipmap.date_day_grey) {
            sportActivity.setType("Early");
            onTypeClick(new MenuItemInfo<Integer>(R.mipmap.date_early_grey, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early)
                    , "Early", R.mipmap.date_early_green, item.getDay(), item.getDateParam()), position);
        } else {
            sportActivity.setType(item.getType());
            onTypeClick(item, position);
        }

        sportActivity.dateClickPositon = position;
        sportActivity.stopPopupWindow();


    }

    public void runWayItem(MenuItemInfo item) {
        ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).runWayItem(item);
    }

    protected abstract void onTypeClick(MenuItemInfo item, int position);

    protected List<MenuItemInfo<Integer>> getTypes() {
        String h12 = TimeUtils.dateFormat(new Date(), "yyyy-MM-dd") + " 12:00:00";
        String now = TimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss");
        long dif = TimeUtils.diffTime(now, h12, "yyyy-MM-dd HH:mm:ss");
        Date firstDate = new Date();
        if (dif < 0)
            firstDate = TimeUtils.getAddDayDate(firstDate, -1);
        String d1 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 1), "yyyy-MM-dd");
        String d2 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 2), "yyyy-MM-dd");
        String d3 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 3), "yyyy-MM-dd");
        String d4 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 4), "yyyy-MM-dd");
        String d5 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 5), "yyyy-MM-dd");
        String d6 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 6), "yyyy-MM-dd");
        Context context = getBaseView().getIBaseContext().getBaseActivity();
        MenuItemInfo<Integer> item1 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d1.split("-")[1]), AfbUtils.getLangMonth(context, d1.split("-")[1]), R.mipmap.date_day_green, d1.split("-")[2], d1);
        MenuItemInfo<Integer> item2 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d2.split("-")[1]), AfbUtils.getLangMonth(context, d2.split("-")[1]), R.mipmap.date_day_green, d2.split("-")[2], d2);
        MenuItemInfo<Integer> item3 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d3.split("-")[1]), AfbUtils.getLangMonth(context, d3.split("-")[1]), R.mipmap.date_day_green, d3.split("-")[2], d3);
        MenuItemInfo<Integer> item4 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d4.split("-")[1]), AfbUtils.getLangMonth(context, d4.split("-")[1]), R.mipmap.date_day_green, d4.split("-")[2], d4);
        MenuItemInfo<Integer> item5 = new MenuItemInfo<>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d5.split("-")[1]), AfbUtils.getLangMonth(context, d5.split("-")[1]), R.mipmap.date_day_green, d5.split("-")[2], d5);
        MenuItemInfo<Integer> item6 = new MenuItemInfo<>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(context, d6.split("-")[1]), AfbUtils.getLangMonth(context, d6.split("-")[1]), R.mipmap.date_day_green, d6.split("-")[2], d6);
        MenuItemInfo<Integer> itemRunning = new MenuItemInfo<Integer>(R.mipmap.date_running_green, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.running), "Running", R.mipmap.date_running_green);
        itemRunning.setDateParam("");
        MenuItemInfo<Integer> itemToday = new MenuItemInfo<Integer>(R.mipmap.date_today_grey, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Today), "Today", R.mipmap.date_today_green);
        itemToday.setDateParam("");
        MenuItemInfo<Integer> itemEarly = new MenuItemInfo<Integer>(R.mipmap.date_early_grey, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Early)
                + "(" + getBaseView().getIBaseContext().getBaseActivity().getString(R.string.all) + ")"
                , "Early", R.mipmap.date_early_green, "", "7");

        List<MenuItemInfo<Integer>> types = new ArrayList<>();
        types.add(itemRunning);
        types.add(itemToday);
        types.add(itemEarly);
        types.addAll(Arrays.asList(
                item1,
                item2,
                item3,
                item4,
                item5,
                item6

        ));
        return types;
    }

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

        }
    }

    private void showAllOdds(final TextView textView) {

        BasePopupWindow basePopupWindow = new BasePopupWindow(getBaseView().getIBaseContext().getBaseActivity(), textView, textView.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = view.findViewById(R.id.rv_list);
                rv.setPadding(0, 0, 0, 0);
                rv.setLayoutManager(new LinearLayoutManager(getBaseView().getIBaseContext().getBaseActivity()));
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.All_Markets), "0"));//accType=
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Main_Markets), "1"));
                list.add(new MenuItemInfo(0, getBaseView().getIBaseContext().getBaseActivity().getString(R.string.Other_Bet_Markets), "2"));

                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(getBaseView().getIBaseContext().getBaseActivity(), list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setText(item.getText());
                        if (textView.getText().toString().equals(item.getText())) {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.blue));
                        } else {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.black));
                        }
                    }
                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        closePopupWindow();
                        ((SportActivity) baseView.getIBaseContext().getBaseActivity()).setAllOdds(item);
                        textView.setText(item.getText());

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

    public boolean isItemCollectionCommon(B item) {
        return !(localCollectionMap.get(item.getModuleTitle()) == null || localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(item.getModuleTitle()).get(item.getHome() + "+" + item.getAway()));
    }

    public boolean isLeagueCollectionCommon(B item) {
        Map<String, Boolean> stringBooleanMap = localCollectionMap.get(item.getModuleTitle().toString());
        return !stringBooleanMap.containsValue(false);
    }

    public void collectionLeagueCommon(B item) {
        boolean isCollection = isLeagueCollectionCommon(item);
        for (String s : localCollectionMap.get(item.getModuleTitle().toString()).keySet()) {
            localCollectionMap.get(item.getModuleTitle().toString()).put(s, !isCollection);
        }
        baseRecyclerAdapter.notifyDataSetChanged();
    }


    public void collectionItemCommon(B item) {
        String moduleKey = item.getModuleTitle().toString();
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
        baseRecyclerAdapter.notifyDataSetChanged();
    }

    public void setIsHide(boolean isHide) {
        this.isHide = isHide;
    }
}
