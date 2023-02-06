package nanyang.com.dig88.Table.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.LinkedViewPager.MyPagerAdapter;
import nanyang.com.dig88.LinkedViewPager.ViewPager;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.BallGameActivity;
import nanyang.com.dig88.Table.ClearanceBetActivity;
import nanyang.com.dig88.Table.Imp.IUpdateTableData;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.VsActivity;
import nanyang.com.dig88.Table.adapters.PinnedAdapter;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingParPromptBean;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Table.entity.HandicapBean;
import nanyang.com.dig88.Table.entity.MatchBean;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.popupwindow.BetBasePop;
import nanyang.com.dig88.Util.DeviceUtils;
import nanyang.com.dig88.Util.WebPop;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.ScreenUtil;
import xs.com.mylibrary.allinone.util.TimeUtils;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.myview.mylistview.PullToRefreshLayout;

/**
 * Created by Administrator on 2015/11/26.
 */
public class TableAdapterHelper {

    public static final int ClearanceBet = 11;
    protected boolean isLocalCollection = false;
    int white = 0xffffffff;
    int page = 0;
    int pageSize = 8;
    ArrayList<TableModuleBean> listData;
    int pageTotalCount;
    Context context;
    String modleType;
    ListView exlist;
    PullToRefreshLayout contentSv;
    ViewPager headerPager;
    TextView tv_head;
    int index = 0;
    List<MatchBean> pageallData;
    private ArrayList<TableModuleBean> actualShowData;
    private ArrayList<TableModuleBean> allShowData;
    private Map<String, Map<String, Boolean>> localCollectionMap;
    private IUpdateTableData Iupdate;
    private boolean hasNewDate;
    private PinnedAdapter adapter;
    PullToRefreshLayout.OnRefreshListener onRefreshListener = new PullToRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
            if (page == 0) {
                getNet(modleType);
            } else {
                page--;
                getData();
                if (page == 0) {
                    contentSv.setRefreshStr(context.getString(R.string.pull_down_to_refresh));
                }
            }
            contentSv.refreshFinish(PullToRefreshLayout.DONE);
        }

        @Override
        public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
            if (actualShowData != null && (page + 1) * pageSize < actualShowData.size()) {
                page++;
                getData(0);
                contentSv.setRefreshStr(context.getString(R.string.pull_down_to_load_previous));
            } else {
                contentSv.setCanPullUp(false);
            }
            contentSv.refreshFinish(PullToRefreshLayout.DONE);
        }
    };
    private BetBasePop betPop;
    private ArrayList<ViewPager> vps;
    private MyPagerAdapter<String> headPagerAdapter;
    private TextView countView;
    private TextView betCountView;
    /**
     * 0 普通
     * 11 连串过关
     */
    private int betType;
    private TextView typeMarkView;
    /**
     * 1加载 2 更新
     */
    private int updateType;
    public TableAdapterHelper(Context context, String modleType, ListView exlist, IUpdateTableData Iupdate) {
        this.context = context;
        this.modleType = modleType;
        this.exlist = exlist;
        this.Iupdate = Iupdate;
        initAdapter();
    }

    public ArrayList<TableModuleBean> getAllShowData() {
        return allShowData;
    }

    public void setAllShowData(ArrayList<TableModuleBean> allShowData) {
        this.allShowData = allShowData;
    }

    public boolean isLocalCollection() {
        return isLocalCollection;
    }

    public void setLocalCollection(boolean localCollection) {
        isLocalCollection = localCollection;
    }

    public void changeLocalCollection() {
        isLocalCollection = !isLocalCollection;
        getData();
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getModleType() {
        return modleType;
    }

/*    public  Map<String,Map<String,Map<Integer,BettingInfoBean>>>  getServerParBetList() {
        return ((BaseActivity) context).getApp().getBetDetail();
    }*/

    public void setModleType(String modleType) {
        this.modleType = modleType;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
        if (betType == TableAdapterHelper.ClearanceBet) {

        }
        getLocalList();
    }

    public Map<String, Map<String, Map<Integer, BettingInfoBean>>> getLocalList() {
        return ((BaseActivity) context).getApp().getBetDetail();
    }

    public void setBetType(int betType, TextView countTv) {
        setBetType(betType);
        if (typeMarkView != null)
            if (betType == TableAdapterHelper.ClearanceBet) {
                typeMarkView.setText(context.getString(R.string.mix_parlay));
            } else {
                if (modleType.equals("Running"))
                    typeMarkView.setText(context.getString(R.string.Running));
                else if (modleType.equals("Today")) {
                    typeMarkView.setText(context.getString(R.string.today));
                } else if (modleType.equals("Early")) {
                    typeMarkView.setText(context.getString(R.string.early));
                }
            }
        this.betCountView = countTv;
    }

    public TextView getBetCountView() {
        return betCountView;
    }

    public void setBetCountView(TextView betCountView) {
        this.betCountView = betCountView;
    }

    public void setCountView(TextView countView) {
        this.countView = countView;
    }

    public void setContentSv(PullToRefreshLayout contentSv) {
        this.contentSv = contentSv;
        contentSv.setOnRefreshListener(onRefreshListener);

    }

    public void setHeaderPager(final ViewPager headerPager) {
        this.headerPager = headerPager;
        headPagerAdapter = new MyPagerAdapter<String>(context) {
            @Override
            protected void convert(ViewHolder helper, String o, int position) {
                helper.setText(R.id.header_center_vp_tv, o);
                tv_head = (TextView) helper.getView().findViewById(R.id.header_center_vp_tv);
                tv_head.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.arrow_right_white_double, 0, 0, 0);
                tv_head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (index == 0) {
                            headerPager.setCurrentItem(1, true);
                            for (int i = 0; i < exlist.getChildCount(); i++) {
                                ViewPager viewPager = (ViewPager) exlist.getChildAt(i).findViewById(R.id.module_center_vp);
                                viewPager.setCurrentItem(1, true);
                            }
                        } else {
                            headerPager.setCurrentItem(0, true);
                            for (int i = 0; i < exlist.getChildCount(); i++) {
                                ViewPager viewPager = (ViewPager) exlist.getChildAt(i).findViewById(R.id.module_center_vp);
                                viewPager.setCurrentItem(0, true);
                            }
                        }
                    }
                });
            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.item_header_center_vp;
            }
        };
        headerPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index = position;
                if (position != 0) {
                    tv_head.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.arrow_left_white_double, 0, 0, 0);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        headPagerAdapter.setDatas(new ArrayList<String>(Arrays.asList(context.getString(R.string.full_half_court_hdp), context.getString(R.string.full_half_court_ou))));
        if (headerPager != null)
            headerPager.setAdapter(headPagerAdapter);
    }

    public void setAllListData(ArrayList<TableModuleBean> listData) {
        this.listData = listData;

    }

    public int getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(int pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
        if (countView != null)
            countView.setText(pageTotalCount + "");
    }

    private void getNet(String modelType) {
        Iupdate.getNet(modelType);
    }

    private void updateNet() {
        Iupdate.updateNet(modleType);
    }

    public void getData(int postion) {
        getData();
        exlist.setSelection(postion);
    }

    public void getData() {
        if (listData == null)
            return;
        allShowData = getModuleData();
        if (allShowData == null)
            return;
        showActualData();
    }

    public void showActualData() {
        actualShowData = actualShowDate(allShowData);
        List<TableModuleBean> pageList = paging(actualShowData);
        pageallData = handleDateType(pageList);
        notificationData(pageallData);
    }

    private ArrayList<TableModuleBean> actualShowDate(ArrayList<TableModuleBean> allShowData) {
        ArrayList<TableModuleBean> selectedTableData = allShowData;
        Map<String, Map<String, Boolean>> select = getAct().getListSeleted();
        String selectedModule = getAct().getSelectedModle();
        Map<String, Boolean> selectedData = select.get(selectedModule);
        GameMenuItem dateSeleted = getAct().getDateSelected().get(selectedModule);
        ArrayList<TableModuleBean> secondSelectedData = selectedTableData;
        if (dateSeleted != null && !dateSeleted.getValue().equals("")) {//挑选日期
            secondSelectedData = new ArrayList<>();
            Date date = TimeUtils.format2Date(dateSeleted.getValue(), "yyyy-MM-dd");
            for (TableModuleBean item : selectedTableData) {
                List<MatchBean> mb = new ArrayList<>();
                for (MatchBean bean : item.getRows()) {
                    Date dateMy = TimeUtils.format2Date(bean.getWorkingDate(), "MM/dd/yyyy");
                    if (dateMy == null)
                        continue;
                    if (dateSeleted.getDrawableRes() == 1) {
                        if (dateMy.getTime() - date.getTime() > 0) {
                            mb.add(bean);
                        }
                    } else if (dateSeleted.getDrawableRes() == 0) {
                        if (dateMy.getTime() == date.getTime()) {
                            mb.add(bean);
                        }
                    }
                }
                if (mb.size() > 0) {
                    secondSelectedData.add(new TableModuleBean(item.getLeagueBean(), mb));
                }
            }
        }
        if ((selectedData != null && selectedData.size() > 0)) {
            ArrayList<TableModuleBean> temp = secondSelectedData;
            secondSelectedData = new ArrayList<>();
            for (TableModuleBean tableModuleBean : temp) {
                if (selectedData.get(tableModuleBean.getLeagueBean().getModuleTitle()) != null && selectedData.get(tableModuleBean.getLeagueBean().getModuleTitle())) {
                    secondSelectedData.add(tableModuleBean);
                }
            }
        }
        return secondSelectedData;
    }

    private BallGameActivity getAct() {
        return ((BallGameActivity) context);
    }

    @NonNull
    protected List<MatchBean> handleDateType(List<TableModuleBean> pageList) {
        List<MatchBean> pageallData = new ArrayList<>();

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
                pageallData.add(cell);
            }
        }
        return pageallData;
    }


    protected List<TableModuleBean> paging(ArrayList<TableModuleBean> allShowData) {
        List<TableModuleBean> pageList = null;
        if (((page + 1) * pageSize) < allShowData.size()) {
            pageList = allShowData.subList(page * pageSize, (page + 1) * pageSize);
        } else {
            pageList = allShowData.subList(page * pageSize, allShowData.size());
        }
        return pageList;
    }

    private ArrayList<TableModuleBean> getModuleData() {
        if (isLocalCollection()) {
            ArrayList<TableModuleBean> moduleDate = new ArrayList<>();
            for (TableModuleBean tableModuleBean : listData) {
                if (null != localCollectionMap.get(modleType + "+" + tableModuleBean.getLeagueBean().getModuleTitle())) {
                    List<MatchBean> moduleCollectionRows = new ArrayList<>();
                    TableModuleBean moduleCollection = new TableModuleBean(tableModuleBean.getLeagueBean(), moduleCollectionRows);
                    Map<String, Boolean> moduleMap = localCollectionMap.get(modleType + "+" + tableModuleBean.getLeagueBean().getModuleTitle());

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
                isLocalCollection = false;
                Toast.makeText(context, context.getString(R.string.no_records), Toast.LENGTH_SHORT).show();
            }
        }
        if (!isLocalCollection) {
            return listData;
        }

        return listData;
    }

    public void clear() {
        setPageTotalCount(0);
        adapter.clear();
    }

    private void initAdapter() {
        vps = new ArrayList<>();
        localCollectionMap = ((BaseActivity) context).getApp().getLocalCollectionMap();
        adapter = new PinnedAdapter(context) {

            public String oldAwayName = "";
            public String oldHomeName = "";
            public String oldHomeGive = "";
            public String oldModuleTitle = "";
            public int vpPosition = 0;

            @Override
            protected void convert(final ViewHolder helper, final MatchBean item, final int position) {
                TextView dateTv = helper.retrieveView(R.id.module_match_date_tv);
                TextView timeTv = helper.retrieveView(R.id.module_match_time_tv);
                TextView tv_running_match_bg = helper.retrieveView(R.id.tv_running_match_bg);
                dateTv.setTextAppearance(context, R.style.text_normal);
                dateTv.setPadding(0, 0, 0, 0);

                if (item.getIsRun() != null && item.getIsRun().equals("true") || modleType.equals("Running")) {
                    dateTv.setTextAppearance(context, R.style.text_bold);
                    dateTv.setPadding(0, 0, 10, 0);
                    helper.setVisible(R.id.module_match_live_iv, false);
                    if (item.getRunHomeScore() != null && item.getRunAwayScore() != null && !item.getRunAwayScore().equals("") && !item.getRunHomeScore().equals("")) {
                        String shome = item.getRunHomeScore();
                        String sAway = item.getRunAwayScore();
                        dateTv.setText(shome + "-" + sAway);

                    } else {
                        dateTv.setText("");
                    }
                    if (item.getLive().contains("HT")) {
                        timeTv.setText("HT");
                    } else {
                        int min = 0;
                        int start = 0;
                        try {

                            if (item.getStatus().equals("0")) {
                                timeTv.setText(context.getString(R.string.not_started));
                            } else if (item.getStatus().equals("2")) {
                                min = Integer.valueOf(item.getCurMinute());
                                start = 45;
                                min = min + start;
                                if (min < 130 && min > 0) {
                                    timeTv.setText(min + context.getString(R.string.min));
                                } else {
                                    timeTv.setText("");
                                }
                            } else /*if (item.getStatus().equals("1")) */ {
                                min = Integer.valueOf(item.getCurMinute());
                                if (min < 130 && min > 0) {
                                    timeTv.setText(min + context.getString(R.string.min));
                                } else {
                                    timeTv.setText("");
                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                            timeTv.setText("");
                        }
                    }
                    dateTv.setTextColor(context.getResources().getColor(R.color.red_table_title));
                    timeTv.setTextColor(context.getResources().getColor(R.color.red_table_title));
                } else {
                    helper.setVisible(R.id.module_match_live_iv, true);
                    helper.setBackgroundRes(R.id.module_match_live_iv, R.mipmap.live_green_left_arrow_white);
                    helper.setText(R.id.module_match_live_iv, "");
                    dateTv.setTextColor(context.getResources().getColor(R.color.black));
                    timeTv.setTextColor(context.getResources().getColor(R.color.black));
//                    helper.setText(R.id.module_match_date_tv, item.getMatchDate());

                    helper.setTextSize(R.id.module_match_date_tv, 10);
                    String date = item.getMatchDate().substring(0, 5);
                    helper.setText(R.id.module_match_date_tv, date);

                    String time = item.getMatchDate();
                    if (time.length() > 6) {
                        time = time.substring(time.length() - 7, time.length());
                        time = TimeUtils.dateFormatChange(time, "KK:mmaa", "HH:mm", Locale.ENGLISH);
                    }
                    helper.setText(R.id.module_match_time_tv, time);
                    if (betType == TableAdapterHelper.ClearanceBet && modleType.equals("Early")) {
//                        helper.setText(R.id.module_match_date_tv, item.getWorkingDate().substring(0,5));
                        helper.setText(R.id.module_match_date_tv, item.getMatchDate().substring(0, 5));
                    }
                    if (!item.getLive().equals("")) {
                        helper.setBackgroundRes(R.id.module_match_live_iv, 0);
                        helper.setTextColorRes(R.id.module_match_live_iv, R.color.red_table_title);
                        helper.setTextColorRes(R.id.module_match_date_tv, R.color.red_table_title);
                        helper.setText(R.id.module_match_live_iv, "");
                        helper.setText(R.id.module_match_date_tv, "");
                        if (!item.getLive().equals("")) {
                            String channel = item.getLive();
                            String[] channels = channel.split("%");
                            if (channels != null && channels.length == 1) {
                                helper.setVisibility(R.id.module_match_live_iv, View.GONE);
                                if (channel.trim().length() > 6)
                                    helper.setTextSize(R.id.module_match_date_tv, 8);
                                helper.setText(R.id.module_match_date_tv, channel.trim());
                            } else if (channels != null && channels.length == 2) {
                                helper.setTextSize(R.id.module_match_live_iv, 7);
                                if (channels[1].trim().length() >= 6)
                                    helper.setTextSize(R.id.module_match_date_tv, 8);
                                else {
                                    helper.setTextSize(R.id.module_match_date_tv, 9);
                                }
                                helper.setVisibility(R.id.module_match_live_iv, View.VISIBLE);
                                helper.setText(R.id.module_match_live_iv, channels[0].trim());
                                helper.setText(R.id.module_match_date_tv, channels[1].trim());
                            }
                        }
                    }
                }
                if (position > 0) {
                    oldHomeName = getItem(position - 1).getHome();
                    oldAwayName = getItem(position - 1).getAway();
                    oldHomeGive = getItem(position - 1).getHandicapBeans().get(0).getIsHomeGive();
                    oldModuleTitle = getItem(position - 1).getLeagueBean().getModuleTitle();
                }
                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) && betType != TableAdapterHelper.ClearanceBet && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    helper.setText(R.id.module_match_home_team_tv, "");
                    helper.setText(R.id.module_match_visiting_team_tv, "");
                    if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.INVISIBLE);
                    }
                    helper.setVisibility(R.id.module_right_mark_tv, View.INVISIBLE);
                    helper.setVisibility(R.id.module_match_left1_ll, View.INVISIBLE);
                    helper.setVisibility(R.id.module_match_left2_ll, View.INVISIBLE);
                    if (modleType.equals("Running")) {
                        tv_running_match_bg.setVisibility(View.GONE);
                    }
                } else {
                    if (item.getHandicapBeans().get(0).getIsHomeGive().equals("true")) {
                        helper.setTextColorRes(R.id.module_match_home_team_tv, R.color.red_table_title);
                        helper.setTextColorRes(R.id.module_match_visiting_team_tv, R.color.black_grey);
                    } else {
                        helper.setTextColorRes(R.id.module_match_home_team_tv, R.color.black_grey);
                        helper.setTextColorRes(R.id.module_match_visiting_team_tv, R.color.red_table_title);
                    }
                    String homeRank = item.getHomeRank();
                    String awayRank = item.getAwayRank();
                    String away = item.getAway();
                    if (awayRank != null && !awayRank.equals("")) {
                        away = "[" + awayRank + "]" + away;
                    }
                    String home = item.getHome();
                    if (homeRank != null && !homeRank.equals("")) {
                        home = "[" + homeRank + "]" + home;
                    }
                    helper.setText(R.id.module_match_home_team_tv, home);
                    helper.setText(R.id.module_match_visiting_team_tv, away);
                    helper.setVisibility(R.id.module_right_mark_tv, View.VISIBLE);
                    if (modleType.equals("Running")) {
                        if (!TextUtils.isEmpty(item.getRTSMatchId()) && !item.getRTSMatchId().equals("0")) {
                            tv_running_match_bg.setVisibility(View.VISIBLE);
                        } else {
                            tv_running_match_bg.setVisibility(View.GONE);
                        }
                        tv_running_match_bg.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String lg = AppTool.getAppLanguage(context);
                                String language = "EN-US";
                                if (!TextUtils.isEmpty(lg) && lg.equals("zh")) {
                                    language = "ZH-CN";
                                }
                                String url = "http://mobilesport.dig88api.com/_view/LiveCast.aspx?Id=" + item.getRTSMatchId() + "&Home=" + item.getHome() +
                                        "&Away=" + item.getAway() + "&L=" + language;
                                BaseActivity activity = (BaseActivity) context;
                                int heightPixels = DeviceUtils.getScreenPix(activity).heightPixels;
                                int[] location = new int[2];
                                v.getLocationOnScreen(location);
                                WebPop pop;
                                if (location[1] < heightPixels / 2) {
                                    int popWidth = DeviceUtils.dip2px(activity, 350);
                                    int popHeight = heightPixels - location[1] - v.getHeight() - 60;
                                    pop = new WebPop(activity, v, popWidth, popHeight);
                                    pop.setUrl(url);
                                    int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
                                    int y = location[1] + v.getHeight();
                                    pop.showPopupAtLocation(x, y + 60);
                                } else {
                                    if (exlist.getChildAt(position) != null) {
                                        v = exlist.getChildAt(position);
                                    }
                                    v.getLocationOnScreen(location);
                                    int popWidth = DeviceUtils.dip2px(activity, 350);
                                    int popHeight = location[1] - 60;
                                    pop = new WebPop(activity, v, popWidth, popHeight);
                                    pop.setUrl(url);
                                    int x = (location[0] + v.getWidth() / 2) - popWidth / 2;
                                    int y = 0;
                                    pop.showPopupAtLocation(x, y - 60);
                                }
                            }
                        });
                    } else {
                        tv_running_match_bg.setVisibility(View.GONE);
                    }
                    if (betType != TableAdapterHelper.ClearanceBet) {
                        helper.setVisibility(R.id.module_match_collection_tv, View.VISIBLE);
                    }
                    helper.setVisibility(R.id.module_match_left1_ll, View.VISIBLE);
                    helper.setVisibility(R.id.module_match_left2_ll, View.VISIBLE);
                }
                if (item.getLeagueBean().getModuleTitle().equals(oldModuleTitle) && betType == TableAdapterHelper.ClearanceBet && position != 0 && oldHomeName.equals(item.getHome()) && oldAwayName.equals(item.getAway()) && oldHomeGive.equals(item.getHandicapBeans().get(0).getIsHomeGive())) {
                    helper.setVisibility(R.id.module_right_mark_tv, View.INVISIBLE);
                } else {
                    helper.setVisibility(R.id.module_right_mark_tv, View.VISIBLE);
                }
                helper.setClickLisenter(R.id.module_right_mark_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle b = new Bundle();
                        b.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, item);
                        b.putString(AppConfig.ACTION_KEY_INITENT_STRING, modleType);
                        b.putInt(AppConfig.ACTION_KEY_INITENT_INT, betType);
                        AppTool.activiyJump(context, VsActivity.class, b);
                    }
                });
                if (betType == TableAdapterHelper.ClearanceBet) {
                    helper.setVisibility(R.id.module_match_collection_tv, View.GONE);
                }
//                if (item.getIsInFavourite().equals("true")) {
//                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
//                } else {
//                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
//
//                }
                if (localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()) == null || localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway()) == null || !localCollectionMap.get(modleType + "+" + item.getLeagueBean().getModuleTitle()).get(item.getHome() + "+" + item.getAway())) {
                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
                } else {
                    helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
                }
                if (item.getRCHome() == null || item.getRCHome().equals("0") || item.getRCHome().equals("0")) {
                    helper.setVisible(R.id.module_match_home_rea_card_tv, false);
                } else {
                    helper.setVisible(R.id.module_match_home_rea_card_tv, true);
                    if (item.getRCHome().equals("1"))
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card1);
                    else if (item.getRCHome().equals("2"))
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card2);
                    else
                        helper.setBackgroundRes(R.id.module_match_home_rea_card_tv, R.drawable.rectangle_red_card3);
                }
                if (item.getRCAway() == null || item.getRCAway().equals("0") || item.getRCAway().equals("")) {
                    helper.setVisible(R.id.module_match_away_rea_card_tv, false);
                } else {
                    helper.setVisible(R.id.module_match_away_rea_card_tv, true);
                    if (item.getRCAway().equals("1"))
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card1);
                    else if (item.getRCAway().equals("2"))
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card2);
                    else
                        helper.setBackgroundRes(R.id.module_match_away_rea_card_tv, R.drawable.rectangle_red_card3);
                }
                final TextView ct = helper.retrieveView(R.id.module_match_collection_tv);
                helper.setClickLisenter(R.id.module_match_collection_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //收藏 取消收藏
                        /*添加我的最爱:
                        添加我的最爱:
                        http://mobilesport.dig88api.com/_View/Favourite.aspx?id=29278,139575,55712&IsAdd=True&ot=t
                        消除我的最爱:
                        http://mobilesport.dig88api.com/_View/Favourite.aspx?id=29278,139575,55712&IsAdd=False&ot=t
                        Id组成	联赛id,主队id,客队id                        Ot	t (today)	 e(Early)	r(Running)
                            */
                        clickLocalCollection(getItem(position));
//                        TableHttpHelper<String> helper1 = new TableHttpHelper<String>(context, ct, new ThreadEndT<String>(new TypeToken<String>() {
//                        }.getType()) {
//                            @Override
//                            public void endT(String result) {
//                                helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_solid_yellow);
//                                getItem(position).setIsInFavourite("true");
//                            }
//
//                            @Override
//                            public void endString(String result) {
//                                helper.setBackgroundRes(R.id.module_match_collection_tv, R.mipmap.star_outline_grey);
//                                getItem(position).setIsInFavourite("false");
//
//                            }
//
//                            @Override
//                            public void endError(String error) {
//
//                            }
//                        });
//                        if (item.getIsInFavourite().equals("true")) {
//                            helper1.updateData(WebSiteUrl.SportUrl + "_View/Favourite.aspx?id=" + item.getLeagueBean().getModuleId() + "," + item.getHomeId() + "," + item.getAwayId() + "&IsAdd=False&ot=" +
//                                    modleType.substring(0, 1).toLowerCase(), "");
//                        } else {
//                            helper1.getData(WebSiteUrl.SportUrl + "_View/Favourite.aspx?id=" + item.getLeagueBean().getModuleId() + "," + item.getHomeId() + "," + item.getAwayId() + "&IsAdd=True&ot=" +
//                                    modleType.substring(0, 1).toLowerCase(), "");
//                        }
                    }

                });
                ViewPager vp = helper.retrieveView(R.id.module_center_vp);
                handleViewPager(vp, item, position);
                vps.add(headerPager);
                if (!vps.contains(vp)) {
                    vps.add(vp);
                }
                if (item.getType() == MatchBean.Type.ITME) {
                    helper.setVisible(R.id.module_match_title_tv, false);
                    helper.setVisible(R.id.module_match_head_v, false);

                } else {
                    helper.setVisible(R.id.module_match_title_tv, true);
                    helper.setVisible(R.id.module_match_head_v, true);
                    helper.setText(android.R.id.text1, item.getLeagueBean().getModuleTitle());
                    if (position == 0) {
                        helper.setVisible(R.id.module_match_head_v, false);
                    }
                }
            }


            protected void handleViewPager(final ViewPager centerVp, final MatchBean datas, int position) {
                centerVp.setOnTouchListener(new View.OnTouchListener() {
                    private float mDX, mDY, mLX, mLY;

                    @Override
                    public boolean onTouch(View v, MotionEvent ev) {
                        switch (ev.getAction()) {
                            case MotionEvent.ACTION_DOWN:
                                mDX = mDY = 0f;
                                mLX = ev.getX();
                                mLY = ev.getY();
                                break;
                            case MotionEvent.ACTION_MOVE:
                                final float curX = ev.getX();
                                final float curY = ev.getY();
                                mDX += Math.abs(curX - mLX);
                                mDY += Math.abs(curY - mLY);
                                mLX = curX;
                                mLY = curY;

                                if (mDX > mDY) {
                                    contentSv.setCanPullDown(false);
                                    contentSv.setCanPullUp(false);
                                    contentSv.setEnabled(false);
                                    if (centerVp.getParent() != null) {
                                        centerVp.getParent().requestDisallowInterceptTouchEvent(true);
                                    }
                                    return false;
                                }
                                break;
                            case MotionEvent.ACTION_UP:
                                centerVp.getParent().requestDisallowInterceptTouchEvent(false);
                                contentSv.setCanPullDown(true);
                                contentSv.setCanPullUp(true);
                                contentSv.setEnabled(true);
                                break;
                        }
                        return false;
                    }

                });
//                if(centerVp.getAdapter()!=null){
//                    MyPagerAdapter<HandicapBean> recycledAdapter= (MyPagerAdapter<HandicapBean>) centerVp.getAdapter();
//                    recycledAdapter.setExtraData(datas);
//                    recycledAdapter.setParentPosition(position);
//                    recycledAdapter.setDatas(datas.getHandicapBeans());
//                    recycledAdapter.notifyDataSetChanged();
//                }else{
                MyPagerAdapter<HandicapBean> contentPageAdapter = new MyPagerAdapter<HandicapBean>(context) {

                    @Override
                    protected void convert(ViewHolder helper, final HandicapBean handicapBean, final int position) {
                        final MatchBean bean = getExtraData();
                        if (handicapBean.getHasHdp().equals("false")) {
                            helper.setText(R.id.viewpager_match_visit_hdp_tv, "");
                            helper.setText(R.id.viewpager_match_home_hdp_tv, "");
                            helper.setText(R.id.viewpager_match_home_hdpodds_tv, "");
                            helper.setText(R.id.viewpager_match_visit_hdpodds_tv, "");
                        } else {
                            String hdpS = handicapBean.getHdp();
                            hdpS = hdpS.replace("-", "/");

                            if (handicapBean.getIsHomeGive().equals("true")) {
                                helper.setText(R.id.viewpager_match_visit_hdp_tv, "");
                                helper.setText(R.id.viewpager_match_home_hdp_tv, hdpS);

                            } else {
                                helper.setText(R.id.viewpager_match_home_hdp_tv, "");
                                helper.setText(R.id.viewpager_match_visit_hdp_tv, hdpS);
                            }
                            boolean isAnmiation = false;
                            if (handicapBean.getIsHdpNew().equals("true"))
                                isAnmiation = true;
                            setValue(helper, R.id.viewpager_match_home_hdpodds_tv, handicapBean.getHomeHdpOdds(), isAnmiation);
                            TextView home_hdpodds_tv = helper.retrieveView(R.id.viewpager_match_home_hdpodds_tv);
                            TextView viewpager_match_home_hdp_tv = helper.retrieveView(R.id.viewpager_match_home_hdp_tv);
                            if (!TextUtils.isEmpty(handicapBean.getHomeHdpOdds()) && handicapBean.getHomeHdpOdds().startsWith("-")) {
                                helper.setTextColor(R.id.viewpager_match_home_hdpodds_tv, 0xffb64632);
                            } else {
                                helper.setTextColor(R.id.viewpager_match_home_hdpodds_tv, 0xff333333);
                            }
                            clickBet(home_hdpodds_tv, handicapBean, bean, position, handicapBean.getHomeHdpOdds(), "home", handicapBean.getHdp());
                            if (betType != TableAdapterHelper.ClearanceBet) {
                                clickBet(viewpager_match_home_hdp_tv, handicapBean, bean, position, handicapBean.getHomeHdpOdds(), "home", handicapBean.getHdp());
                            }
                            if (handicapBean.getIsHdpNew().equals("true"))
                                isAnmiation = true;
                            setValue(helper, R.id.viewpager_match_visit_hdpodds_tv, handicapBean.getAwayHdpOdds(), isAnmiation);

                            TextView awayHdpodds_tv = helper.retrieveView(R.id.viewpager_match_visit_hdpodds_tv);
                            TextView viewpager_match_visit_hdp_tv = helper.retrieveView(R.id.viewpager_match_visit_hdp_tv);
                            if (!TextUtils.isEmpty(handicapBean.getAwayHdpOdds()) && handicapBean.getAwayHdpOdds().startsWith("-")) {
                                helper.setTextColor(R.id.viewpager_match_visit_hdpodds_tv, 0xffb64632);
                            } else {
                                helper.setTextColor(R.id.viewpager_match_visit_hdpodds_tv, 0xff333333);
                            }
                            clickBet(awayHdpodds_tv, handicapBean, bean, position, handicapBean.getAwayHdpOdds(), "away", handicapBean.getHdp());
                            if (betType != TableAdapterHelper.ClearanceBet) {
                                clickBet(viewpager_match_visit_hdp_tv, handicapBean, bean, position, handicapBean.getAwayHdpOdds(), "away", handicapBean.getHdp());
                            }
                        }

                        adapter.getItem(getParentPosition()).getHandicapBeans().get(position).setIsHdpNew("false");

                        if (handicapBean.getHasOu().equals("false")) {
                            helper.setText(R.id.viewpager_match_ou_tv, "");
                            helper.setText(R.id.viewpager_match_ou2_tv, "");
                            helper.setText(R.id.viewpager_match_underodds_tv, "");
                            helper.setText(R.id.viewpager_match_overodds_tv, "");
                        } else {
                            String ou = handicapBean.getOU();
                            ou = ou.replace("-", "/");
                            helper.setText(R.id.viewpager_match_ou_tv, ou);
                            helper.setText(R.id.viewpager_match_ou2_tv, "");
                            boolean isAnmiation = false;
                            if (handicapBean.getIsOUNew().equals("true"))
                                isAnmiation = true;
                            setValue(helper, R.id.viewpager_match_underodds_tv, handicapBean.getUnderOdds(), isAnmiation);
                            TextView underodds_tv = helper.retrieveView(R.id.viewpager_match_underodds_tv);
                            TextView viewpager_match_ou2_tv = helper.retrieveView(R.id.viewpager_match_ou2_tv);
                            if (!TextUtils.isEmpty(handicapBean.getUnderOdds()) && handicapBean.getUnderOdds().startsWith("-")) {
                                helper.setTextColor(R.id.viewpager_match_underodds_tv, 0xffb64632);
                            } else {
                                helper.setTextColor(R.id.viewpager_match_underodds_tv, 0xff333333);
                            }
                            clickBet(underodds_tv, handicapBean, bean, position, handicapBean.getUnderOdds(), "under", handicapBean.getOU());
                            if (betType != TableAdapterHelper.ClearanceBet) {
                                clickBet(viewpager_match_ou2_tv, handicapBean, bean, position, handicapBean.getUnderOdds(), "under", handicapBean.getOU());
                            }
                            if (handicapBean.getIsOUNew().equals("true"))
                                isAnmiation = true;
                            setValue(helper, R.id.viewpager_match_overodds_tv, handicapBean.getOverOdds(), isAnmiation);
                            TextView overoddsTv = helper.retrieveView(R.id.viewpager_match_overodds_tv);
                            TextView viewpager_match_ou_tv = helper.retrieveView(R.id.viewpager_match_ou_tv);
                            if (!TextUtils.isEmpty(handicapBean.getOverOdds()) && handicapBean.getOverOdds().startsWith("-")) {
                                helper.setTextColor(R.id.viewpager_match_overodds_tv, 0xffb64632);
                            } else {
                                helper.setTextColor(R.id.viewpager_match_overodds_tv, 0xff333333);
                            }
                            clickBet(overoddsTv, handicapBean, bean, position, handicapBean.getOverOdds(), "over", handicapBean.getOU());
                            if (betType != TableAdapterHelper.ClearanceBet) {
                                clickBet(viewpager_match_ou_tv, handicapBean, bean, position, handicapBean.getOverOdds(), "over", handicapBean.getOU());
                            }
                        }

                        adapter.getItem(getParentPosition()).getHandicapBeans().get(position).setIsOUNew("false");
                        notifyClearanceBet(adapter.getItem(getParentPosition()), position, helper);
                    }

                    protected void clickBet(final TextView tv, final HandicapBean handicapBean, final MatchBean bean, final int position, final String overOdds, final String over, final String ou) {
                        tv.setOnTouchListener(new View.OnTouchListener() {
                            boolean isClick = true;
                            private float mDX, mDY, mLX, mLY;

                            @Override
                            public boolean onTouch(View v, MotionEvent ev) {
                                switch (ev.getAction()) {
                                    case MotionEvent.ACTION_DOWN:
                                        isClick = true;
                                        mDX = mDY = 0f;
                                        mLX = ev.getX();
                                        mLY = ev.getY();
                                        break;
                                    case MotionEvent.ACTION_MOVE:
                                        final float curX = ev.getX();
                                        final float curY = ev.getY();
                                        mDX += Math.abs(curX - mLX);
                                        mDY += Math.abs(curY - mLY);
                                        mLX = curX;
                                        mLY = curY;
                                        int dis = ScreenUtil.dip2px(context, 2);
                                        if (ScreenUtil.getScreenWidthPix(context) >= 1920) {
                                            dis = dis + 5;
                                        }
                                        if (mDX > dis || mDY > dis) {
                                            isClick = false;
                                            tv.setEnabled(false);
                                        }
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        tv.setEnabled(true);
                                        if (isClick) {
                                            tv.getParent().requestDisallowInterceptTouchEvent(true);
                                            if ("false".equals(handicapBean.getIsInetBet())) {
                                                Toast.makeText(context, R.string.not_allowed_to_bet, Toast.LENGTH_LONG).show();
                                            } else {
                                                if (betType == 0) {
                                                    if (modleType.equals("Running") && !TextUtils.isEmpty(datas.getRTSMatchId())
                                                            && !datas.getRTSMatchId().equals("0")) {
                                                        String lg = AppTool.getAppLanguage(context);
                                                        String language = "EN-US";
                                                        if (!TextUtils.isEmpty(lg) && lg.equals("zh")) {
                                                            language = "ZH-CN";
                                                        }
                                                        String url = "http://mobilesport.dig88api.com/_view/LiveCast.aspx?Id=" + datas.getRTSMatchId() + "&Home=" + datas.getHome() +
                                                                "&Away=" + datas.getAway() + "&L=" + language;
                                                        showBetPop(v, bean, position, overOdds, over, ou, url);
                                                    } else {
                                                        showBetPop(v, bean, position, overOdds, over, ou, "");
                                                    }
                                                } else if (betType == ClearanceBet) {
                                                    handleClearanceBet(v, adapter.getItem(getParentPosition()), position, over, ou, overOdds, handicapBean.getIsHomeGive());
                                                }

                                            }
                                        }
                                        tv.getParent().requestDisallowInterceptTouchEvent(false);
                                        break;
                                }

                                return isClick;
                            }
                        });

                    }

                    private void setValue(final ViewHolder helper, final int id, String f, boolean isAnimation) {
                        if (f.equals("")) {
                            helper.setText(id, "");
                            return;
                        }
                        helper.setText(id, f);
                        helper.setTextColor(id, context.getResources().getColor(R.color.black_grey));


                        if (isAnimation && updateType != 1) {

                            helper.setAnimation(id, getAnimation(new Animation.AnimationListener() {
                                @Override
                                public void onAnimationStart(Animation animation) {
                                    helper.setBackgroundColorRes(id, R.color.listView_viewpager_text_flicker_color);
                                }

                                @Override
                                public void onAnimationEnd(Animation animation) {
                                    helper.setBackgroundColorRes(id, R.color.listView_bg);
                                }

                                @Override
                                public void onAnimationRepeat(Animation animation) {
                                    helper.setBackgroundColorRes(id, R.color.listView_viewpager_text_flicker_color);
                                }
                            }));

                        }
                    }

                    private void showBetPop(View v, MatchBean bean, int position, String odds, String type, String ou, String webUrl) {

                        betPop = new BetBasePop(context, v, DeviceUtils.getScreenWidth((Activity)context)/5*4, LinearLayout.LayoutParams.WRAP_CONTENT);
                        //String b,String sc, String oId, String odds, boolean isRunning, String oId_fh
                        if (position == 0) {
                            BettingInfoBean info = new BettingInfoBean("s", type, "", ou, odds,
                                    bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", "", 0, modleType.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("true"));
                            betPop.getData(info);
                        } else {
                            BettingInfoBean info = new BettingInfoBean("s", type, "", ou, odds,
                                    bean.getHome(), bean.getAway(), bean.getLeagueBean().getModuleTitle(), bean.getHandicapBeans().get(0).getSocOddsId() + "", bean.getHandicapBeans().get(1).getSocOddsId(), 1, modleType.equals("Running"), bean.getHandicapBeans().get(0).getIsHomeGive().equals("true"));
                            betPop.getData(info);
                            betPop.setBetSelectionType(context.getString(R.string.half_time));
                        }
                        if (modleType.equals("Running") && !TextUtils.isEmpty(webUrl)) {
                            betPop.initWebView(webUrl);
                        }
                        betPop.showPopupCenterWindowBlack();
                    }


                    @Override
                    protected int getPageLayoutRes() {
                        return R.layout.item_table_module_center_viewpager;
                    }
                };
                contentPageAdapter.setExtraData(datas);
                contentPageAdapter.setParentPosition(position);
                contentPageAdapter.setDatas(datas.getHandicapBeans());
                centerVp.setAdapter(contentPageAdapter);
//                }
                centerVp.setFollowViewPagers(vps);

                centerVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        if (vpPosition != position) {
                            vpPosition = position;
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {
                    }
                });
                centerVp.setCurrentItem(vpPosition);
            }
        };

        exlist.setAdapter(adapter);
    }

    private void notifyClearanceBet(MatchBean item, int position, ViewHolder helper) {
        if (getLocalList() == null || item == null)
            return;
        Map<String, Map<Integer, BettingInfoBean>> keyMap = getLocalList().get(item.getHome() + "+" + item.getAway());
        if (keyMap == null)
            return;
        Map<Integer, BettingInfoBean> positionMap = keyMap.get(item.getKey());
        if (positionMap == null)
            return;
        BettingInfoBean modelInfo = positionMap.get(position);
        if (modelInfo == null)
            return;
        String model = modelInfo.getB();

        if (model.equals("away")) {
            helper.setBackgroundRes(R.id.viewpager_match_visit_hdpodds_tv, R.drawable.rectangle_green);
            helper.setTextColor(R.id.viewpager_match_visit_hdpodds_tv, white);

        } else if (model.equals("home")) {
            helper.setBackgroundRes(R.id.viewpager_match_home_hdpodds_tv, R.drawable.rectangle_green);
            helper.setTextColor(R.id.viewpager_match_home_hdpodds_tv, white);
        } else if (model.equals("over")) {
            helper.setBackgroundRes(R.id.viewpager_match_overodds_tv, R.drawable.rectangle_green);
            helper.setTextColor(R.id.viewpager_match_overodds_tv, white);
        } else if (model.equals("under")) {
            helper.setBackgroundRes(R.id.viewpager_match_underodds_tv, R.drawable.rectangle_green);
            helper.setTextColor(R.id.viewpager_match_underodds_tv, white);
        }
    }

    private void handleClearanceBet(View v, final MatchBean item, final int position, String modle, final String hdp, final String odds, final String ishoneGive) {
        if (item != null) {
            final String recordModel = modle;
            BettingDataHelper bettingDataHelper = new BettingDataHelper(context, null, new ThreadEndT<BettingParPromptBean>(new TypeToken<BettingParPromptBean>() {
            }.getType()) {
                @Override
                public void endT(BettingParPromptBean result, int model) {
                    if (result != null)
                        ((BaseActivity) context).getApp().setBetParList(result);
                    clickBackground(recordModel, hdp, odds, item, position, ishoneGive);
                    countBet();
                }

                @Override
                public void endString(String result, int model) {

                }

                @Override
                public void endError(String error) {
                    if (error != null && !error.equals(""))
                        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                    countBet();
                    adapter.notifyDataSetChanged();
                }
            });
            modle = modle + "_par";

            final String SocOddsId = item.getHandicapBeans().get(0).getSocOddsId();
            String SocOddsId_FH = "";
            if (position == 1)
                SocOddsId_FH = item.getHandicapBeans().get(1).getSocOddsId();
            BettingInfoBean m1 = new BettingInfoBean("", modle, "", hdp, odds, item.getHome(), item.getAway(), item.getLeagueBean().getModuleTitle(),
                    SocOddsId, SocOddsId_FH, position, modleType.equals("Running"), ishoneGive.equals("true"));
            bettingDataHelper.getData(m1);
            ((TextView) v).setBackgroundResource(R.drawable.rectangle_green);
            ((TextView) v).setTextColor(white);

        } else {
            ((BaseActivity) context).getApp().setBetDetail(null);
            countBet();
            adapter.notifyDataSetChanged();
        }

    }

    protected void clickBackground(String recordModel, String hdp, String odds, MatchBean item, int position, String ishoneGive) {
        BettingInfoBean modlemap = new BettingInfoBean("s", recordModel, "", hdp, odds, item.getHome(), item.getAway(), item.getLeagueBean().getModuleTitle(),
                item.getHandicapBeans().get(0).getSocOddsId(), item.getHandicapBeans().get(1).getSocOddsId(), position, modleType.equals("Running"), ishoneGive.equals("true"));
        Map<Integer, BettingInfoBean> positionMap = new HashMap<>();
        positionMap.put(position, modlemap);
        Map<String, Map<Integer, BettingInfoBean>> keyMap = new HashMap<>();
        keyMap.put(item.getKey(), positionMap);
        getLocalList().put(item.getHome() + "+" + item.getAway(), keyMap);
        adapter.notifyDataSetChanged();
    }

    public void countBet() {
        betCountView.setVisibility(View.VISIBLE);
        Map<String, Map<String, Map<Integer, BettingInfoBean>>> result = getLocalList();
        if (betCountView != null && result != null) {
            if (result.size() == 0) {
                betCountView.setVisibility(View.GONE);
            } else {
                betCountView.setText(result.size() + "");
                betCountView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppTool.activiyJump(context, ClearanceBetActivity.class);
                    }
                });
            }
        } else
            betCountView.setVisibility(View.GONE);
    }

    public void notificationData(List<MatchBean> pagedata) {
        setPageTotalCount(pagedata.size());
        adapter.setDatas(pagedata);
    }

    public void notificationData() {
        if (betType == TableAdapterHelper.ClearanceBet) {
            countBet();
        } else {
            if (betCountView != null)
                betCountView.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
    }

    private Animation getAnimation(Animation.AnimationListener listener) {
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        //设置动画时间
        animation.setDuration(1000);
        animation.setRepeatCount(3);
        animation.setAnimationListener(listener);
        return animation;
    }


    public void setTypeMarkView(TextView typeMarkView) {
        this.typeMarkView = typeMarkView;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
        if (updateType == 1) {
            setPage(0);
        }
    }

    public void setHasNewDate(boolean hasNewDate) {
        this.hasNewDate = hasNewDate;
    }

    public void clickLocalCollection(MatchBean bean) {
        String moduleKey = modleType + "+" + bean.getLeagueBean().getModuleTitle();
        Map<String, Boolean> moduleMap = localCollectionMap.get(moduleKey);
        if (moduleMap == null)
            moduleMap = new HashMap<>();
        String localKey = bean.getHome() + "+" + bean.getAway();
        Boolean v = moduleMap.get(localKey);
        if (v == null || !v) {
            moduleMap.put(localKey, true);
        } else {
            moduleMap.put(localKey, false);
        }
        localCollectionMap.put(moduleKey, moduleMap);
        notificationData();
    }

}
