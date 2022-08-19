package com.nanyang.app.main.home.sport.main;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.Setting.RefreshDataBean;
import com.nanyang.app.main.home.sport.WebSocketManager;
import com.nanyang.app.main.home.sport.additional.AdditionPresenter;
import com.nanyang.app.main.home.sport.dialog.ChooseMatchPop;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.LeagueBean;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.TableSportInfo;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.IObtainDataState;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.ApiManager;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseApplication;
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
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.DeviceUtils;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * sport页面球加载、分页、更新、显示的adapter的逻辑实现
 */

public abstract class SportState<B extends SportInfo, V extends SportContract.View<B>> implements IObtainDataState {
    private String LID = "";


    private int page;
    private List<TableSportInfo<B>> filterData;
    public Map<String, Boolean> leagueSelectedMap = new HashMap<>();

    /**
     * 显示的所有数据
     */
    protected List<TableSportInfo<B>> allData;
    private List<TableSportInfo<B>> newAddTableSport;
    /**
     * 所有数据分页后的当前页面数据
     */
    protected List<TableSportInfo<B>> pageData;

    protected CompositeDisposable mCompositeSubscription;
    protected BasePopupWindow popMenu;
    private SwipeToLoadLayout swipeToLoadLayout;


    protected String TAG = "SportState";

    private boolean isSearch = false;
    private String searchStr = "";

    protected boolean isHide = false;
    private String dbType;
    private String dbId;
    private String currentOpid;
    private String oldOpid;
    private String type;

    public SportState() {
    }

    public int getPageSize() {
        return pageSize;
    }

    private int pageSize = 10;

    public SportAdapterHelper<B> getAdapterHelper() {
        return adapterHelper;
    }

    protected SportAdapterHelper<B> adapterHelper;

    public V getBaseView() {
        return baseView;
    }

    protected Map<String, Map<String, Boolean>> localCollectionMap;

    public void setBaseView(V mBaseView) {

        localCollectionMap = ((SportActivity) mBaseView.getIBaseContext().getBaseActivity()).localCollectionMap;
        this.baseView = mBaseView;
        mCompositeSubscription = new CompositeDisposable();
    }

    public void handleAdapter() {
        if (baseView.getIBaseContext().getBaseActivity() == null)
            return;

        LogUtil.d("additionMap", "onSetAdapterHelper,handleAdapter");
        adapterHelper = onSetAdapterHelper();
        adapterHelper.onSetAdapterItemLayout();
        baseRecyclerAdapter = new BaseRecyclerAdapter<B>(baseView.getIBaseContext().getBaseActivity(), new ArrayList<B>(), adapterHelper.onSetAdapterItemLayout()) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, B item) {
                adapterHelper.onConvert(holder, position, item);
            }
        };
        Map<Boolean, String> additionMap = ((SportActivity) baseView.getIBaseContext().getBaseActivity()).getAdditionMap();
        adapterHelper.bindAdapter(baseRecyclerAdapter);
        adapterHelper.setItemCallBack(onSetItemCallBack());
        adapterHelper.setAdditionMap(additionMap);
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

    SportActivity activity;

    public SportState(V baseView) {
        setBaseView(baseView);
        handleAdapter();
        activity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
        switchTypeAdapter = new BaseRecyclerAdapter<MenuItemInfo<Integer>>(activity, getTypes(), R.layout.item_sport_switch) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<Integer> item) {
                TextView tvGamePic = holder.getTextView(R.id.img_game_pic);
                TextView tv = holder.getView(R.id.tv_game_name);
                TextView tvRedOval = holder.getView(R.id.tv_red_oval);
                if (position == 0) {
                    if (activity.currentFragment.isTop()) {
                        tvRedOval.setVisibility(View.VISIBLE);
                    } else {
                        tvRedOval.setVisibility(View.GONE);
                    }
                } else {
                    tvRedOval.setVisibility(View.GONE);
                }
                int res = item.getRes();
                if (res == R.mipmap.date_day_grey) {
                    tvGamePic.setText(item.getDay());
                    tv.setText(item.getText());
                    tv.setText(tv.getText() + " " + item.getDay());
                } else {
                    tvGamePic.setText("");
                    tv.setText(item.getText());
                }
                if (((SportActivity) baseView.getIBaseContext().getBaseActivity()).dateClickPosition == position) {
                    tvGamePic.setBackgroundResource(item.getParent());
                    holder.getView(R.id.ll_content).setBackgroundColor(ContextCompat.getColor(mContext, R.color.gary1));
                    if (BuildConfig.FLAVOR.equals("usun")) {
                        tv.setTextColor(ContextCompat.getColor(mContext, R.color.green));
                    } else {
                        tv.setTextColor(ContextCompat.getColor(mContext, R.color.google_green));
                    }
                } else {
                    tvGamePic.setBackgroundResource(res);
                    holder.getView(R.id.ll_content).setBackgroundColor(ContextCompat.getColor(mContext, R.color.white));
                    tv.setTextColor(ContextCompat.getColor(mContext, R.color.black_grey));
                }
                TextView tv_game_count = holder.getView(R.id.tv_game_count);
                tv_game_count.setVisibility(View.GONE);

                String dBId = getDbId();
                if (StringUtils.isNull(dBId))
                    return;
                String type = item.getType();
                Log.d("getType", "dbid:" + dBId + ",currentIdBean.getDbid()" + dBId + ".type: " + type + ",jsonObjectNum:" + jsonObjectNum);
                if (activity.sportIdText == R.string.Euro_2020) {
                    dBId = "122";
                }
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
    }

    protected V baseView;

    public BaseRecyclerAdapter<B> getBaseRecyclerAdapter() {
        return baseRecyclerAdapter;
    }

    protected BaseRecyclerAdapter<B> baseRecyclerAdapter;

//    https://ws.afb1188.com/fnOddsGen?wst=wsSocAllGen&g=200&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-27&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&ov=0&mt=0&FAV=&SL=&LSL=undefined
//    https://ws.afb1188.com/fnOddsGen?wst=wsSocAllGen&g=1&ot=t&wd=&pn=1&delay=0&tf=-1&betable=1&lang=en&ia=0&tfDate=2019-03-27&LangCol=C&accType=MY&CTOddsDiff=-0.2&CTSpreadDiff=-1&oddsDiff=0&spreadDiff=0&um=1|1317|22080&LID=&ov=0&mt=0&FAV=&SL=&LSL=undefined


    @Override
    public void refresh() {

        if (!NetWorkUtil.isNetConnected(getBaseView().getIBaseContext().getBaseActivity())) {
            baseView.reLoginPrompt(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.failed_to_connect), new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
            return;
        }
        baseView.getIBaseContext().getBaseActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                baseView.getIBaseContext().showLoadingDialog();
            }
        });

        refreshData();
    }

    private void refreshData() {
        LogUtil.d("Socket", "开始获取数据---------------------");
        String dBId = getDbId();
        sendRefreshData(dBId);
    }

    public String getDbId() {
        return ((BaseSportFragment) baseView).getBallDbid();
    }

    public void sendRefreshData(String dBId) {
        LogUtil.d("Socket", getClass().getSimpleName() + "发送数据：");
        AfbApplication application = (AfbApplication) getBaseView().getIBaseContext().getBaseActivity().getApplication();
        RefreshDataBean refreshDataBean = application.getRefreshDataBean();
        if (refreshDataBean == null)
            return;
        SportActivity sportActivity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
        MenuItemInfo oddsType = sportActivity.getOddsType();
        if (oddsType != null)
            refreshDataBean.setAccType(oddsType.getType());
        if (StringUtils.isNull(dBId))
            return;
        String t = (getStateType().getType().charAt(0) + "").toLowerCase();
        refreshDataBean.setDBID(dBId);
        refreshDataBean.setOt(t);
        refreshDataBean.setOv(sportActivity.getSortType());
        refreshDataBean.setMt(sportActivity.getMarketType().getType());

        refreshDataBean.setTimess(sportActivity.wd);
        if (sportActivity.sportIdText == R.string.Euro_2020) {
            refreshDataBean.setWc("1");
        } else {
            refreshDataBean.setWc("");
        }
        if (sportActivity.wd != null && sportActivity.wd.equals("7"))
            refreshDataBean.setIa(1);
        else {
            refreshDataBean.setIa(0);
        }
        List<RefreshDataBean> list = new ArrayList<>();
        list.add(refreshDataBean);
        String s2 = new Gson().toJson(list);
        String s = "11" + s2;
        WebSocketManager.getInstance().send(s);
    }


    protected void bindMenuAdapter(BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter, List<MenuItemInfo> types) {
        baseRecyclerAdapter.addAllAndClear(types);

    }


    /***
     * 显示数据最终调用的方法
     */
    public void showData() {
        List<B> listData = toMatchList(pageData);
        baseRecyclerAdapter.addAllAndClear(listData);
        List<B> listDataAll = toMatchList(filterData);
        baseView.onGetData(listDataAll);
        Log.d(TAG, "showData: 已经显示数据了");
        baseView.getIBaseContext().hideLoadingDialog();
    }

    public int getTitleContentColor() {
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.ez2888_item);
        } else {
            return Color.WHITE;
        }
    }

    public int getNormalContentColor() {
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            return Color.WHITE;
        } else {
            return ContextCompat.getColor(getBaseView().getIBaseContext().getBaseActivity(), R.color.grey_thick_white);
        }
    }

    private List<B> toMatchList(List<TableSportInfo<B>> pageList) {
        List<B> pageMatch = new ArrayList<>();

        for (int i = 0; i < pageList.size(); i++) {
            TableSportInfo<B> item = pageList.get(i);
            List<B> rows = item.getRows();
            for (int j = 0; j < rows.size(); j++) {
                B cell = rows.get(j);
                cell.setChildCount(rows.size());
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

    protected List<TableSportInfo<B>> updateFirstData(JSONArray dataListArray) throws JSONException {
        LogUtil.d("Socket", "第一次更新数据---------------------开始json解析成对象");
        page = 0;
        return parseJson2TableSports(dataListArray);
    }

    @NonNull
    private List<TableSportInfo<B>> parseJson2TableSports(JSONArray dataListArray) throws JSONException {
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


    public void initAllData(List<TableSportInfo<B>> allData) {
        if (!isHide)
            updateTableDate(allData);
    }

    protected void updateTableDate(List<TableSportInfo<B>> allData) {
        if (allData == null)
            return;
        filterData(allData);
        showCurrentData();
    }

    protected void showCurrentData() {
        this.pageData = pageData(filterData);
        showData();
    }

    protected List<TableSportInfo<B>> pageData(List<TableSportInfo<B>> filterData) {
        List<TableSportInfo<B>> pageList;
       /* if (page * pageSize > filterData.size() - 1) {
            if (page > 0)
                page = 0;
            else
                return new ArrayList<>();
        }

        if (((page + 1) * pageSize) < filterData.size()) {
            pageList = filterData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = filterData.subList(page * pageSize, filterData.size());
        }*/
        pageList = filterData;
        LogUtil.d("current", "page:" + page);

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
            if (tableModuleBean.getLeagueBean().getModuleTitle().toLowerCase().contains(searchStr.toLowerCase())) {
                moduleDate.add(tableModuleBean);
            } else {
                List<B> moduleCollectionRows = new ArrayList<>();
                TableSportInfo<B> moduleCollection = new TableSportInfo<B>(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                for (B matchBean : tableModuleBean.getRows()) {
                    if ((matchBean.getHome() + matchBean.getAway().toLowerCase()).contains(searchStr.toLowerCase())) {
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

    protected void getTableSportList(JSONArray jsonArray, String currentOpid) throws JSONException {
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
                JSONArray dataListArray = jsonArray.getJSONArray(3);
                if (oldOpid != null && oldOpid.equals(currentOpid)) {
                    allData.addAll(parseJson2TableSports(dataListArray));
                    return;
                } else {
                    allData = updateFirstData(dataListArray);
                    return;
                }
            } else {
                allData = updateJsonArray(jsonArray);
                return;
            }

        }
        allData = new ArrayList<>();
    }

    public void handleData(String s) {
        if (isHide)
            return;
        if (s.contains("Session Expired")) {
            baseView.reLoginPrompt("", new SportContract.CallBack() {
                @Override
                public void clickCancel(View v) {
                    refresh();
                }
            });
            return;
        }
        try {
            LogUtil.d("Socket", "原始数据" + s + ",开始删除html代码");
            String updateString = Html.fromHtml(s).toString();
            LogUtil.d("Socket", "string 转json---------------------" + updateString);
            JSONObject object = new JSONObject(updateString);
            LogUtil.d("Socket", "转json完成---------------------");
            dbType = object.optString("dbtype");
            dbId = object.optString("dbid");
            currentOpid = object.optString("opid");
            if (dbId.startsWith(getDbId()) && dbId.toLowerCase().endsWith((getStateType().getType().charAt(0) + "").toLowerCase())) {
                JSONArray jsonArray = object.optJSONArray("data");
                if (jsonArray == null || jsonArray.length() < 1)
                    return;
                getTableSportList(jsonArray, currentOpid);
                oldOpid = currentOpid;
                baseView.getIBaseContext().getBaseActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        BaseActivity baseActivity = baseView.getIBaseContext().getBaseActivity();
                        if (baseActivity != null && baseActivity.isHasAttached()) {
                            LogUtil.d("Socket", "解析完成 开始更新界面---------------------");
                            initAllData(allData);
                            LogUtil.d("Socket", "更新界面完成---------------------");
                        }
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected List<TableSportInfo<B>> updateJsonArray(JSONArray jsonArray) throws JSONException {
        LogUtil.d("Socket", "增删改更新数据---------------------");
        if (jsonArray.length() > 4) {
            boolean needRefresh = false;
            JSONArray deleteArray = jsonArray.getJSONArray(2);
            for (int i = 0; i < deleteArray.length(); i++) {
                delMatch(deleteArray.getString(i));
            }
            JSONArray addArray = jsonArray.getJSONArray(3);
            newAddTableSport = new ArrayList<>();
            if (addArray != null && addArray.length() > 0) {
                LogUtil.d("SocketAdd", "需要添加" + addArray.length() + "个联赛");
                for (int i = 0; i < addArray.length(); i++) {
                    TableSportInfo<B> bTableSportInfo = parseTableSportMatch(addArray.getJSONArray(i), true);
                    LogUtil.d("SocketAdd", "需要添加的第" + i + "个联赛:" + bTableSportInfo.getLeagueBean().getModuleId() + "," + bTableSportInfo.getLeagueBean().getModuleTitle());
                    newAddTableSport.add(bTableSportInfo);
                }
            }

            JSONArray updateArray = jsonArray.getJSONArray(4);
            for (int i = 0; i < updateArray.length(); i++) {
                updateIntegralMatch(updateArray.getJSONArray(i));
            }
            if (jsonArray.length() > 5) {
                JSONArray modifyArray = jsonArray.getJSONArray(5);
                for (int i = 0; i < modifyArray.length(); i++) {
                    modifyMatch(modifyArray.getJSONArray(i));
                }
            }

            if (newAddTableSport.size() > 0) {
                for (TableSportInfo<B> bTableSportInfo : newAddTableSport) {
                    needRefresh = needRefresh || addNewTableSoc(bTableSportInfo);
                }


               /* LogUtil.d("SocketAdd", "排序前比赛数：" + getCountMatch(allData));
                int index = allData.size() / 2;
                B indexB = allData.get(index).getRows().get(0);
                sortList = new ArrayList<>();
                copyAllData = new ArrayList<>();
                copyAllData.addAll(allData);
                ArrayList<B> bs = new ArrayList<>();
                bs.add(indexB);
                sortList.add(new TableSportInfo<B>(allData.get(index).getLeagueBean(), bs));
                copyRemoveAddData(indexB);
                findAndAddHead(sortList);
                findAndAddFoot(sortList);
                allData = sortList;
                LogUtil.d("SocketAdd", "排序后比赛数：" + getCountMatch(allData));*/
            }

            if (needRefresh)
                refreshData();

        } else {
            LID = "";
        }

        return allData;

    }

    private void copyRemoveAddData(B bTableSportInfo) {
        for (int i = 0; i < copyAllData.size(); i++) {
            for (int i1 = 0; i1 < copyAllData.get(i).getRows().size(); i1++) {
                if (bTableSportInfo.getSocOddsId().equals(copyAllData.get(i).getRows().get(i1).getSocOddsId())) {
                    if (copyAllData.get(i).getRows().size() <= 1)
                        copyAllData.remove(i);
                    else {
                        copyAllData.get(i).getRows().remove(i1);
                    }
                    return;
                }
            }

        }

    }

    private int getCountMatch(List<TableSportInfo<B>> allData) {
        int i = 0;
        for (TableSportInfo<B> bTableSportInfo : allData) {
            for (B b : bTableSportInfo.getRows()) {
                i++;
            }
        }
        return i;
    }

    protected void findAndAddHead(List<TableSportInfo<B>> sortList) {
        B indexB = sortList.get(0).getRows().get(0);//第一个
        LeagueBean indexLeague = sortList.get(0).getLeagueBean();

        String preSocOddsId = indexB.getPreSocOddsId();
        boolean found = false;
        for (TableSportInfo<B> bTableSportInfo : copyAllData) {
            for (B b : bTableSportInfo.getRows()) {
                if (preSocOddsId.equals(b.getSocOddsId())) {
                    found = true;
                    if (indexLeague.getModuleId().equals(bTableSportInfo.getLeagueBean().getModuleId())) {
                        sortList.get(0).getRows().add(0, b);
                        copyRemoveAddData(b);
                        break;
                    } else {
                        ArrayList<B> bs = new ArrayList<>();
                        bs.add(b);
                        sortList.add(0, new TableSportInfo<B>(bTableSportInfo.getLeagueBean(), bs));
                        copyRemoveAddData(b);
                        break;
                    }
                }
            }
            if (found)
                break;
        }
        if (found) {
            findAndAddHead(sortList);
        }
    }

    protected void findAndAddFoot(List<TableSportInfo<B>> sortList) {
        TableSportInfo<B> bTableSportInfo1 = sortList.get(sortList.size() - 1);
        B indexB = bTableSportInfo1.getRows().get(bTableSportInfo1.getRows().size() - 1);//最后一个
        LeagueBean indexLeague = bTableSportInfo1.getLeagueBean();
        String socOddsId = indexB.getSocOddsId();
        boolean found = false;
        for (TableSportInfo<B> bTableSportInfo : copyAllData) {
            for (B b : bTableSportInfo.getRows()) {
                if (socOddsId.equals(b.getPreSocOddsId())) {
                    found = true;
                    if (indexLeague == null || indexLeague.getModuleId() == null) {
                        LogUtil.d("addNew", b.getModuleTitle() + " ------------- " + b.getHome() + " ----------" + b.getAway());
                    }
                    if (indexLeague.getModuleId().equals(bTableSportInfo.getLeagueBean().getModuleId())) {
                        sortList.get(sortList.size() - 1).getRows().add(b);
                        copyRemoveAddData(b);
                        break;
                    } else {
                        ArrayList<B> bs = new ArrayList<>();
                        bs.add(b);
                        sortList.add(new TableSportInfo<B>(bTableSportInfo.getLeagueBean(), bs));
                        copyRemoveAddData(b);
                        break;
                    }
                }
            }
            if (found)
                break;
        }
        if (found) {
            findAndAddFoot(sortList);
        }
    }

    List<TableSportInfo<B>> sortList = new ArrayList<>();
    List<TableSportInfo<B>> copyAllData = new ArrayList<>();

    private void modifyMatch(JSONArray jsonArray) {
        if (allData == null) return;
        if (jsonArray.length() > 2) {
            for (int i = 0; i < allData.size(); i++) {
                List<B> rows = allData.get(i).getRows();
                for (int i1 = 0; i1 < rows.size(); i1++) {
                    if (rows.get(i1).getSocOddsId().equals(jsonArray.optString(0))) {
                        modifySoc(i, i1, jsonArray.optJSONArray(1), jsonArray.optJSONArray(2));
                        return;
                    }
                }
            }
            for (int i = 0; i < newAddTableSport.size(); i++) {
                List<B> rows = newAddTableSport.get(i).getRows();
                for (int i1 = 0; i1 < rows.size(); i1++) {
                    if (rows.get(i1).getSocOddsId().equals(jsonArray.optString(0))) {
                        modifyNewAddTableSoc(i, i1, jsonArray.optJSONArray(1), jsonArray.optJSONArray(2));
                        return;
                    }
                }
            }
        }
    }

    private void modifySoc(int i, int i1, JSONArray indexArray, JSONArray dataArray) {
        allData.get(i).getRows().get(i1).setNotify(false);
        boolean isCoherent = true;
        B b = allData.get(i).getRows().get(i1);
        for (int k = 0; k < indexArray.length(); k++) {
            b.setValue(indexArray.optInt(k), dataArray.optString(k));
            if (indexArray.optInt(k) == 0 || indexArray.optInt(k) == 1) {

                if (i1 == 0 && i > 0) {
                    B preMatch = allData.get(i - 1).getRows().get(allData.get(i - 1).getRows().size() - 1);
                    isCoherent = isCoherent && comparedId(preMatch, b);
                } else if (i1 > 0) {
                    B preMatch = allData.get(i).getRows().get(i1 - 1);
                    isCoherent = isCoherent && comparedId(preMatch, b);
                }
            }
        }
        if (!isCoherent) {
            doCoherent(i, i1, b);
        }
    }

    private void doCoherent(int i, int i1, B b) {
        newAddTableSport.add(new TableSportInfo<B>(allData.get(i).getLeagueBean(), new ArrayList<>(Arrays.asList(b))));
        if (allData.get(i).getRows().size() < 2)
            allData.remove(i);
        else {
            allData.get(i).getRows().remove(i1);
        }

    }

    private boolean comparedId(B preMatch, B currentMatch) {
        boolean equals = preMatch.getSocOddsId().equals(currentMatch.getPreSocOddsId());
        if (!equals) {
            LogUtil.d("SocketAdd", "preSocId改变不匹配需要刷新{" + ",他的主队：" + currentMatch.getHome()
                    + ",客队：" + currentMatch.getAway()
                    + ",他前面的主队：" + preMatch.getHome()
                    + ",他前面的客队：" + preMatch.getAway()
            );
        }
        return equals;
    }

    private void modifyNewAddTableSoc(int i, int i1, JSONArray indexArray, JSONArray dataArray) {
        newAddTableSport.get(i).getRows().get(i1).setNotify(false);
        for (int k = 0; k < indexArray.length(); k++) {
            if (indexArray.optInt(k) == 0 || indexArray.optInt(k) == 1)
                newAddTableSport.get(i).getRows().get(i1).setValue(indexArray.optInt(k), dataArray.optString(k));
        }
    }

    private void updateIntegralMatch(JSONArray match) throws JSONException {
        JSONArray socArray = match.optJSONArray(1);
        LogUtil.d("SocketAdd", socArray.length() + "个比赛需要全部更新");
      /*  B b = parseMatch(socArray.getJSONArray(0), false);
        int n = 0;
        if (isNew) {

            for (int i = 0; i < allData.size(); i++) {
                TableSportInfo<B> bTableSportInfo = allData.get(i);
                LeagueBean leagueBean = bTableSportInfo.getLeagueBean();
                if (leagueBean != null && leagueBean.getModuleId().equals(match.optJSONArray(0).optString(0))) {
                    for (int j = 0; j < socArray.length(); j++) {
                        addSoc(i, socArray.optJSONArray(j), true, isNew);
                        LogUtil.d("SocketAdd", "添加了第" + j + "个比赛，isNew：" + isNew);
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
            TableSportInfo<B> bTableSportInfo = parseTableSportMatch(match, true);
            allData.add(n, bTableSportInfo);

        } else {*/
        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<B> bTableSportInfo = allData.get(i);
            LeagueBean leagueBean = bTableSportInfo.getLeagueBean();
            if (leagueBean != null && leagueBean.getModuleId().equals(match.optJSONArray(0).optString(0))) {
                for (int j = 0; j < socArray.length(); j++) {
                    updateSoc(i, socArray.optJSONArray(j), true);
                }
                return;
            }
        }
        for (int i = 0; i < newAddTableSport.size(); i++) {
            TableSportInfo<B> bTableSportInfo = newAddTableSport.get(i);
            LeagueBean leagueBean = bTableSportInfo.getLeagueBean();
            if (leagueBean != null && leagueBean.getModuleId().equals(match.optJSONArray(0).optString(0))) {
                for (int j = 0; j < socArray.length(); j++) {
                    updateNewAddTableSoc(i, socArray.optJSONArray(j), true);

                }
                return;
            }
        }


    }

    private void updateSoc(int i, JSONArray soc, boolean notify) throws JSONException {
        B b = parseMatch(soc, notify);
        List<B> rows = allData.get(i).getRows();
        for (int i1 = 0; i1 < rows.size(); i1++) {
            if (rows.get(i1).getSocOddsId().equals(b.getSocOddsId())) {
                allData.get(i).getRows().set(i1, b);
                LogUtil.d("SocketAdd", "更新了getSocOddsId:" + rows.get(i1).getSocOddsId() + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
                return;
            }
        }
    }

    private void updateNewAddTableSoc(int i, JSONArray soc, boolean notify) throws JSONException {
        B b = parseMatch(soc, notify);
        List<B> rows = newAddTableSport.get(i).getRows();
        for (int i1 = 0; i1 < rows.size(); i1++) {
            if (rows.get(i1).getSocOddsId().equals(b.getSocOddsId())) {
                newAddTableSport.get(i).getRows().set(i1, b);
                LogUtil.d("SocketAdd", "更新了需要新添加的比赛getSocOddsId:" + rows.get(i1).getSocOddsId() + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
                return;
            }
        }
    }


    private boolean addNewTableSoc(TableSportInfo<B> tables) throws JSONException {
  /*      B b = tables.getRows().get(0);
        int n = 0;*/
        boolean needRefresh = false;
        for (B b : tables.getRows()) {
            needRefresh = needRefresh || addNewSingleSoc(b, tables.getLeagueBean());
        }
        /*for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<B> bTableSportInfo = allData.get(i);
            LeagueBean leagueBean = bTableSportInfo.getLeagueBean();
            if (leagueBean != null && leagueBean.getModuleId().equals(tables.getLeagueBean().getModuleId())) {
                boolean needRefresh = false;
                for (int j = 0; j < tables.getRows().size(); j++) {
                    needRefresh = needRefresh || addNewSingleSoc(i, tables.getRows().get(j));
                }
                return needRefresh;
            }
        }

        for (int i = 0; i < allData.size(); i++) {
            TableSportInfo<B> bTableSportInfo = allData.get(i);
            if (bTableSportInfo.getRows().get(bTableSportInfo.getRows().size() - 1).getSocOddsId().equals(b.getPreSocOddsId())) {
                n = i + 1;
                break;
            }
        }
        allData.add(n, tables);
        boolean needRefresh = false;

        List<B> rows = tables.getRows();
        String socOddsId = rows.get(rows.size() - 1).getSocOddsId();
        if (allData.size() > (1 + n) && !socOddsId.equals(allData.get(1 + n).getRows().get(0).getPreSocOddsId())) {
            LogUtil.d("SocketAdd", "第" + n + "位的结尾不等，需要刷新"
                    + ",主队：" + allData.get(1 + n).getRows().get(0).getHome()
                    + ",客队：" + allData.get(1 + n).getRows().get(0).getAway()
                    + ",他的前面得比赛主队：" + rows.get(rows.size() - 1).getHome()
                    + ",他的前面得比赛客队：" + rows.get(rows.size() - 1).getAway()

            );

            needRefresh = true;
        }*/
        return needRefresh;
    }

    private boolean addNewSingleSoc(B b, LeagueBean leagueBean) throws JSONException {

        for (int i = 0; i < allData.size(); i++) {
            List<B> rows = allData.get(i).getRows();

            for (int i1 = 0; i1 < rows.size(); i1++) {

                if (rows.get(i1).getSocOddsId().equals(b.getPreSocOddsId())) {

                    if (leagueBean.getModuleId().equals(allData.get(i).getLeagueBean().getModuleId())) {

                        allData.get(i).getRows().add(i1 + 1, b);
                        LogUtil.d("SocketAdd", "新添加了比赛:同一个联赛：" + leagueBean.getModuleTitle()
                                + ",主队：" + b.getHome()
                                + ",客队：" + b.getAway()
                                + ",他的前面得比赛主队：" + rows.get(i1).getHome()
                                + ",他的前面得比赛客队：" + rows.get(i1).getAway());
                        return false;
                    } else if (allData.size() > (i + 1) && allData.get(i + 1).getLeagueBean().getModuleId().equals(leagueBean.getModuleId()) && allData.get(i).getRows().size() == i1 + 1) {
                        if (allData.get(i + 1).getRows().size() > 0) {
                            String socOddsId = b.getSocOddsId();
                            String preSocOddsId = allData.get(i + 1).getRows().get(0).getPreSocOddsId();
                            if (!socOddsId.equals(preSocOddsId)) {
                                LogUtil.d("SocketAdd", "新添加了比赛:联赛第一位的preSocOddsId错误直接更新：" + leagueBean.getModuleTitle()
                                        + ",主队：" + b.getHome()
                                        + ",客队：" + b.getAway()
                                );
                                return true;
                            }
                        }
                        allData.get(i + 1).getRows().add(0, b);
                        LogUtil.d("SocketAdd", "新添加了比赛:最后一个新加个联赛：" + leagueBean.getModuleTitle()
                                + ",主队：" + b.getHome()
                                + ",客队：" + b.getAway()
                                + ",他的前面得比赛主队：" + rows.get(i1).getHome()
                                + ",他的前面得比赛客队：" + rows.get(i1).getAway());
                        return false;
                    } else if (allData.get(i).getRows().size() == i1 + 1) {
                        allData.add(i + 1, new TableSportInfo<>(leagueBean, new ArrayList<>(Arrays.asList(b))));
                        LogUtil.d("SocketAdd", "新添加了比赛:联赛：" + allData.get(i).getLeagueBean().getModuleTitle() + "最后一个的后面新联赛" + leagueBean.getModuleTitle()
                                + ",主队：" + b.getHome()
                                + ",客队：" + b.getAway()
                                + ",他的前面得比赛主队：" + rows.get(i1).getHome()
                                + ",他的前面得比赛客队：" + rows.get(i1).getAway());
                        return false;
                    } else {
                        LogUtil.d("SocketAdd", "新添加了比赛:错误直接更新：" + leagueBean.getModuleTitle()
                                + ",主队：" + b.getHome()
                                + ",客队：" + b.getAway()
                                + ",他的前面得比赛主队：" + rows.get(i1).getHome()
                                + ",他的前面得比赛客队：" + rows.get(i1).getAway()
                                + ",他的前面得联赛：" + allData.get(i).getLeagueBean().getModuleTitle());
                        return true;
                    }

                }

            }
        }
        boolean needRefresh = false;
        B b2 = allData.get(0).getRows().get(0);
        if (allData.get(0).getLeagueBean().getModuleId().equals(leagueBean.getModuleId())) {
            if (b2.getPreSocOddsId().equals(b.getSocOddsId())) {
                allData.get(0).getRows().add(0, b);
                LogUtil.d("SocketAdd", "新添加了比赛:第一个联赛得头一个：" + leagueBean.getModuleTitle()
                        + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
            } else {
                needRefresh = true;
                LogUtil.d("SocketAdd", "新添加了比赛:错误第一个联赛没对齐：" + leagueBean.getModuleTitle()
                        + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
            }

        } else {
            if (b2.getPreSocOddsId().equals(b.getSocOddsId())) {

                allData.add(0, new TableSportInfo<>(leagueBean, new ArrayList<>(Arrays.asList(b))));
                LogUtil.d("SocketAdd", "新添加了比赛:新加的一个联赛得头一个：" + leagueBean.getModuleTitle()
                        + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
            } else {
                needRefresh = true;
                LogUtil.d("SocketAdd", "新添加了比赛:错误新联赛没对齐：" + leagueBean.getModuleTitle()
                        + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway());
            }
        }
        return needRefresh;


    }

    private boolean addNewSingleSoc(int i, B b) throws JSONException {

        List<B> rows = allData.get(i).getRows();
        for (int i1 = 0; i1 < rows.size(); i1++) {

            if (rows.get(i1).getSocOddsId().equals(b.getPreSocOddsId())) {
                allData.get(i).getRows().add(i1 + 1, b);
                LogUtil.d("SocketAdd", "新添加了比赛:"
                        + ",主队：" + b.getHome()
                        + ",客队：" + b.getAway()
                        + ",他的前面得比赛主队：" + rows.get(i1).getHome()
                        + ",他的前面得比赛客队：" + rows.get(i1).getAway()
                );
                return false;
            }

        }
        boolean needRefresh = false;
        allData.get(i).getRows().add(0, b);
        String s = "新添加了比赛:"
                + ",主队：" + b.getHome()
                + ",客队：" + b.getAway()
                + ",他的添加到联赛得第一位："
                + ",他的PreSocId：" + b.getPreSocOddsId();
        if (i > 0) {
            B b1 = allData.get(i - 1).getRows().get(allData.get(i - 1).getRows().size() - 1);
            String socOddsId = b1.getSocOddsId();
            s = s + ",他的前面比赛的id：" + socOddsId
                    + ",他的前面比赛的主队：" + b1.getHome()
                    + ",他的前面比赛的客队：" + b1.getAway();
            if (!b.getPreSocOddsId().equals(socOddsId)) {
                s = s + ",不匹配需要刷新";
                needRefresh = true;
            }
        } else {
            s = s + ",他是第一位 前面没比赛";
        }
        LogUtil.d("SocketAdd", s);
        return needRefresh;


    }

   /* private void addSoc(int i, JSONArray soc, boolean notify, boolean isNew) throws JSONException {
        B b = parseMatch(soc, notify);
        List<B> rows = allData.get(i).getRows();
        for (int i1 = 0; i1 < rows.size(); i1++) {
            if (!isNew) {
                if (rows.get(i1).getSocOddsId().equals(b.getSocOddsId())) {
                    allData.get(i).getRows().set(i1, b);
                    LogUtil.d("SocketAdd", "更新了getSocOddsId:" + rows.get(i1).getSocOddsId() + ",比赛主队：" + b.getHome() + "，isNew：" + isNew);
                    return;
                }
            } else {
                if (rows.get(i1).getSocOddsId().equals(b.getPreSocOddsId())) {
                    allData.get(i).getRows().add(i1 + 1, b);
                    LogUtil.d("SocketAdd", "添加了getSocOddsId:" + rows.get(i1).getSocOddsId()
                            + ",比赛主队：" + b.getHome()
                            + ",Pre比赛主队：" + rows.get(i1).getHome()
                            + "，isNew：" + isNew);
                    return;
                }
            }
        }
        if (isNew) {
            allData.get(i).getRows().add(0, b);
            LogUtil.d("SocketAdd", "添加了getSocOddsId:" + b.getSocOddsId()
                    + ",比赛主队：" + b.getHome()
                    + ",到第一位"
                    + "，isNew：" + isNew);
            if (i - 1 >= 0) {
                List<B> rows1 = allData.get(i - 1).getRows();
                int size = rows1.size();
                if (size > 0) {
                    String socOddsId = rows1.get(size - 1).getSocOddsId();
                    if (!socOddsId.trim().equals(b.getPreSocOddsId().trim())) {
                        refreshData();
                        LogUtil.d("SocketAdd", "=======================开始刷新，当前getPreSocOddsId:" + b.getPreSocOddsId()
                                + ",比赛主队：" + b.getHome()
                                + ",上一个getSocOddsId：" + rows1.get(size - 1).getSocOddsId()
                                + ",上一个比赛主队：" + rows1.get(size - 1).getHome()
                                + "，isNew：" + isNew);
                    }
                }
            }
        }

    }*/

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
        refresh();
        swipeToLoadLayout.setRefreshing(false);
      /*  this.swipeToLoadLayout = swipeToLoadLayout;
        if (page == 0) {
            refresh();
        } else {
            page--;
            showCurrentData();
            if (page == 0) {
                swipeToLoadLayout.setLoadMoreEnabled(true);
            }
        }
        swipeToLoadLayout.setRefreshing(false);*/
    }

    @Override
    public void onNext(SwipeToLoadLayout swipeToLoadLayout) {
        swipeToLoadLayout.setLoadingMore(false);
    /*    this.swipeToLoadLayout = swipeToLoadLayout;
        if (filterData != null && (page + 1) * pageSize < filterData.size()) {
            page++;
            showCurrentData();
            swipeToLoadLayout.setLoadingMore(false);
        } else {
            swipeToLoadLayout.setLoadingMore(false);
//            swipeToLoadLayout.setLoadMoreEnabled(false);
        }*/
    }

    public BaseRecyclerAdapter<MenuItemInfo<Integer>> getSwitchTypeAdapter() {
        return switchTypeAdapter;
    }

    public BaseRecyclerAdapter<MenuItemInfo<Integer>> switchTypeAdapter;
    JSONObject jsonObjectNum;

    @Override
    public BaseRecyclerAdapter switchTypeAdapter(JSONObject jsonObjectNum) {
        this.jsonObjectNum = jsonObjectNum;
        switchTypeAdapter.notifyDataSetChanged();
        return switchTypeAdapter;
    }


    public void onTypeWayClick(MenuItemInfo item, int position) {
        if (item.getRes() == R.mipmap.sport_game_cup_white || item.getRes() == R.mipmap.sport_cup_yellow) {
            SportActivity sportActivity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
            sportActivity.clickTop();
            sportActivity.stopPopupWindow();
            return;
        }
        runWayItem(item);
        SportActivity sportActivity = (SportActivity) getBaseView().getIBaseContext().getBaseActivity();
        if (item.getRes() == R.mipmap.date_day_grey) {
            sportActivity.setType("Early");
            MenuItemInfo<Integer> early = new MenuItemInfo<>(R.mipmap.date_early_grey, (R.string.Early)
                    , "Early", R.mipmap.date_early_green, item.getDay(), item.getDateParam());
            early.bottomRes = R.mipmap.date_early_white;
            onTypeClick(early, position);
        } else {
            sportActivity.setType(item.getType());
            onTypeClick(item, position);
        }
        sportActivity.dateClickPosition = position;
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
        BaseApplication baseActivity = BaseApplication.getInstance();
        MenuItemInfo<Integer> item1 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d1.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d1.split("-")[1])), R.mipmap.date_day_green, d1.split("-")[2], d1);
        MenuItemInfo<Integer> item2 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d2.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d2.split("-")[1])), R.mipmap.date_day_green, d2.split("-")[2], d2);
        MenuItemInfo<Integer> item3 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d3.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d3.split("-")[1])), R.mipmap.date_day_green, d3.split("-")[2], d3);
        MenuItemInfo<Integer> item4 = new MenuItemInfo<Integer>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d4.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d4.split("-")[1])), R.mipmap.date_day_green, d4.split("-")[2], d4);
        MenuItemInfo<Integer> item5 = new MenuItemInfo<>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d5.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d5.split("-")[1])), R.mipmap.date_day_green, d5.split("-")[2], d5);
        MenuItemInfo<Integer> item6 = new MenuItemInfo<>(R.mipmap.date_day_grey, AfbUtils.getLangMonth(d6.split("-")[1]), baseActivity.getString(AfbUtils.getLangMonth(d6.split("-")[1])), R.mipmap.date_day_green, d6.split("-")[2], d6);
        MenuItemInfo<Integer> itemTop = new MenuItemInfo<>(R.mipmap.sport_cup_yellow, (R.string.Five_Major_Match), "Top", R.mipmap.sport_cup_yellow);
        item1.bottomRes = R.mipmap.date_day_white;
        item2.bottomRes = R.mipmap.date_day_white;
        item3.bottomRes = R.mipmap.date_day_white;
        item4.bottomRes = R.mipmap.date_day_white;
        item5.bottomRes = R.mipmap.date_day_white;
        item6.bottomRes = R.mipmap.date_day_white;
        MenuItemInfo<Integer> itemRunning = new MenuItemInfo<>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green);
        itemRunning.bottomRes = R.mipmap.date_running_white;

        itemRunning.setDateParam("");


        MenuItemInfo<Integer> itemToday = new MenuItemInfo<Integer>(R.mipmap.date_today_grey, (R.string.Today), "Today", R.mipmap.date_today_green);
        itemToday.setDateParam("");
        itemToday.bottomRes = R.mipmap.date_today_white;
        MenuItemInfo<Integer> itemEarly = new MenuItemInfo<Integer>(R.mipmap.date_early_grey,
                R.string.Early_All
                , "Early", R.mipmap.date_early_green, "", "7");
        itemEarly.bottomRes = R.mipmap.date_early_white;

        List<MenuItemInfo<Integer>> types = new ArrayList<>();
        types.add(itemTop);
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

    int errorOddsType = 0;

    @Override
    public void switchOddsType(final String oddsType) {

        Map<String, String> map = new HashMap<>();

        LoginInfo.LanguageWfBean languageWfBean = new LoginInfo.LanguageWfBean("SetAcc", "", AppConstant.wfMain);
        languageWfBean.setAcc(oddsType);
        String authorization = ((BaseToolbarActivity) (getBaseView().getIBaseContext().getBaseActivity())).getApp().getAuthorization();
        Map<String, String> headers = new HashMap<>();
//        headers.put("isios", "true");
        if (!TextUtils.isEmpty(authorization)) {
            headers.put("authorization", authorization);
        }
        String p = AppConstant.getInstance().HOST + "H50/Pub/pcode.axd?_fm=" + languageWfBean.getJson();
        Disposable subscription = ApiServiceKt.Companion.getInstance().getData(p, headers)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        refresh();
                        errorOddsType = 0;
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.getIBaseContext().hideLoadingDialog();
                        errorOddsType++;
                        if (errorOddsType > 5) {
                            return;
                        }
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                switchOddsType(oddsType);
                            }
                        }, 1000);
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


    protected List<List<String>> initHeaderList() {
        List<List<String>> texts = new ArrayList<>();
        List<String> items0 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FT_HDP), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.FT_OU)));
        List<String> items1 = new ArrayList<>(Arrays.asList(getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HT_HDP), getBaseView().getIBaseContext().getBaseActivity().getString(R.string.HT_OU)));
        texts.add(items0);
        texts.add(items1);
        return texts;
    }

    public void createChoosePop(ApiManager mApiWrapper, final View view) {


        LoadMainDataHelper<SelectedLeagueWfBean> dataHelper = new LoadMainDataHelper<>(mApiWrapper, baseView.getIBaseContext().getBaseActivity(), mCompositeSubscription);
        SelectedLeagueWfBean bean = new SelectedLeagueWfBean("selectleague", new LanguageHelper(baseView.getIBaseContext().getBaseActivity()).getLanguage(), "wfSportsH50");
        bean.setDbid(getDbId() + "");
        bean.setDbid2(getDbId() + "");
        bean.setHaspar("0");
        bean.setOT((getStateType().getType().charAt(0) + "").toLowerCase());
        //
        dataHelper.doRetrofitApiOnUiThread(bean, new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);
                JSONArray jsonArray1 = jsonArray.optJSONArray(2);
                HashMap<String, String> numMap = new HashMap<>();
                List<TableSportInfo<B>> listMap = new ArrayList<>();
                for (int i = 0; i < jsonArray1.length(); i++) {
                    JSONArray jsonArray2 = jsonArray1.optJSONArray(i);
                    numMap.put(jsonArray2.optString(0), jsonArray2.optString(2));
                    for (TableSportInfo<B> bTableSportInfo : allData) {
                        if (bTableSportInfo.getLeagueBean().getModuleId().trim().equals(jsonArray2.optString(0).trim())) {
                            listMap.add(bTableSportInfo);
                        }
                    }

                }


                ChooseMatchPop<B, TableSportInfo<B>> pop = new ChooseMatchPop<>(getBaseView().getIBaseContext().getBaseActivity(), view, getStateType().getType());

                pop.setList(listMap, leagueSelectedMap, numMap);
                pop.setBack(new ChooseMatchPop.CallBack() {
                    @Override
                    public void chooseMap(Map<String, Boolean> map) {
                        leagueSelectedMap = map;
                        updateTableDate(allData);
                    }
                });
                baseView.onPopupWindowCreated(pop, Gravity.CENTER);
            }
        });


    }


    static class ViewHolder {
        @BindView(R.id.tv_head_left)
        TextView tvHeadLeft;
        @BindView(R.id.tv_head_right)
        TextView tvHeadRight;
        @BindView(R.id.tv_head_3)
        TextView tvHead3;
        @BindView(R.id.ll_head_parent)
        LinearLayout llHeadParent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    protected String getAllOddsUrl() {
        return "";
    }

    public void initAllOdds(TextView ivAllAdd) {
        ivAllAdd.setVisibility(View.VISIBLE);
        ivAllAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAllOdds((TextView) v);
            }
        });
    }

    private void showAllOdds(final TextView textView) {

        BasePopupWindow basePopupWindow = new BasePopupWindow(getBaseView().getIBaseContext().getBaseActivity(), textView, textView.getWidth() + DeviceUtils.dip2px(activity, 20), LinearLayout.LayoutParams.WRAP_CONTENT) {
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
                List<MenuItemInfo<String>> list = AfbUtils.getMarketsList(baseView.getIBaseContext().getBaseActivity());


                BaseRecyclerAdapter<MenuItemInfo<String>> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(getBaseView().getIBaseContext().getBaseActivity(), list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setText(item.getParent());
                        tv.setAllCaps(true);
                        if (textView.getText().toString().equals(item.getParent())) {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.blue));
                        } else {
                            tv.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
                            tv.setTextColor(ContextCompat.getColor(context, R.color.black));
                        }
                    }
                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                        closePopupWindow();
                        ((SportActivity) baseView.getIBaseContext().getBaseActivity()).setMarketType(item);
                        textView.setText(item.getParent());

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
        notifyCollectionNum();
    }

    private void notifyCollectionNum() {
        int n = 0;
        for (String s : localCollectionMap.keySet()) {
            for (String s1 : localCollectionMap.get(s).keySet()) {
                if (localCollectionMap.get(s).get(s1) != null && localCollectionMap.get(s).get(s1)) {
                    n++;
                }
            }
        }
        if (n < 1) {
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).collectionNumTv.setVisibility(View.GONE);
        } else {
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).collectionNumTv.setVisibility(View.VISIBLE);
            ((SportActivity) getBaseView().getIBaseContext().getBaseActivity()).collectionNumTv.setText("" + n);
        }
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
        notifyCollectionNum();
    }

    public void setIsHide(boolean isHide, AdditionPresenter additionPresenter) {
        this.isHide = isHide;
        if (isHide && additionPresenter != null)
            additionPresenter.stopUpdate();
        else if (!isHide && additionPresenter != null) {
            additionPresenter.startUpdate();
        }
    }

    protected void clickHallBtn(View v, BallInfo item, int position) {

        getBaseView().onWebShow(0, position, item, v);
    }

    public boolean checkWebRtsVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && !StringUtils.isNull(itemBall.getRTSMatchId()) && !itemBall.getRTSMatchId().equals("0"));
    }

    public boolean checkLivePlayVisible(IRTMatchInfo itemBall) {
        return (itemBall != null && (!StringUtils.isNull(itemBall.getTvPathIBC()) && !itemBall.getTvPathIBC().equals("0")));
    }


}
