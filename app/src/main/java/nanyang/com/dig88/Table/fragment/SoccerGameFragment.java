package nanyang.com.dig88.Table.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import butterknife.Bind;
import nanyang.com.dig88.Activity.DigApp;
import nanyang.com.dig88.LinkedViewPager.ViewPager;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Table.entity.LeagueBean;
import nanyang.com.dig88.Table.entity.MatchBean;
import nanyang.com.dig88.Table.entity.TableBallgameBean;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import xs.com.mylibrary.allinone.util.TimeUtils;
import xs.com.mylibrary.base.AdapterViewContent;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.myview.mylistview.PinnedSectionListView;
import xs.com.mylibrary.myview.mylistview.PullToRefreshLayout;
import xs.com.mylibrary.popupwindow.AbsListPopupWindow;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2015/11/2.
 */
public class SoccerGameFragment extends TableBaseFragment {


    @Bind(R.id.ballgame_table_refresh_view)
    PullToRefreshLayout contentSv;
    @Bind(R.id.ballgame_table_content_explv)
    PinnedSectionListView exlist;
    @Bind(R.id.ballgame_center_vp)
    ViewPager headerPager;
    @Bind(R.id.toolbar)
    View tool;
    @Bind(R.id.table_number_tv)
    TextView numberTv;
    @Bind(R.id.ballgame_table_bet_count_tv)
    TextView betCount;
    @Bind(R.id.table_match_mark_tv)
    TextView markTv;
    @Bind(R.id.ll_choice_type)
    LinearLayout ll_choice_type;
    @Bind(R.id.iv_all_add)
    TextView iv_all_add;
    @Bind(R.id.ll_countryMarket)
    LinearLayout ll_countryMarket;
    @Bind(R.id.tv_title)
    TextView tv_title;
    DigApp digApp;
    @Bind(R.id.img_word_cup)
    ImageView img_word_cup;
    BasePopupWindow countryMarket;
    List<String> countryMarketList;
    BasePopupWindow marketsPopu;
    List<String> marketsList;
    private String title;
    private AdapterViewContent<TableBallgameBean<MatchBean>> content;
    private LinkedHashMap<LeagueBean, LinkedHashMap<String, MatchBean>> dataContent;
    private String url;
    private ArrayList<TableModuleBean> tableData;
    private Handler handler = new Handler();
    private AbsListPopupWindow<GameMenuItem> pop;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_ballgame_layout1;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    protected void initBallData() {
        getData("");
    }

    @Override
    protected void getNetTableData(TableDataHelper dataHelper, String params) {
        if (dataHelper != null) {
            adapterHelper.clear();
            dataHelper.setView(contentSv);
            dataHelper.setBetType(getAct().getBetType());
            dataHelper.getData(dataType, params, adapterHelper.getModleType());
        }
    }

    @Override
    protected void endList(ArrayList<TableModuleBean> result) {
        if (getAct() == null) {
            return;
        }
        if (result != null) {

            this.tableData = result;
            adapterHelper.setBetType(getAct().getBetType(), betCount);
            if (result.size() < 1) {
                if (adapterHelper != null) {
                    adapterHelper.clear();

                }
                Toast.makeText(mContext, R.string.no_data, Toast.LENGTH_LONG).show();
            } else
                handleResult(result);

        } else {
            Toast.makeText(mContext, R.string.no_data, Toast.LENGTH_LONG).show();
            if (adapterHelper != null)
                adapterHelper.clear();

        }
    }

    private void handleResult(ArrayList<TableModuleBean> result) {
        Map<String, Map<String, Boolean>> select = getAct().getListSeleted();
        String selectedModule = getAct().getSelectedModle();
        Map<String, Boolean> selectedData = select.get(selectedModule);
        GameMenuItem dateSeleted = getAct().getDateSelected().get(selectedModule);

        if (dateSeleted == null && selectedModule.equals("Early+0")) {
            Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"), Locale.CHINESE);    //获取东八区时间
/*
            SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String curDate = s.format(c.getTime());  //当前日期*/
            Calendar day = Calendar.getInstance();
            day.set(Calendar.YEAR, c.get(Calendar.YEAR));
            day.set(Calendar.MONTH, c.get(Calendar.MONTH));
            day.set(Calendar.DATE, c.get(Calendar.DATE));
            day.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY));
            day.set(Calendar.MINUTE, c.get(Calendar.MINUTE));
            day.set(Calendar.SECOND, c.get(Calendar.SECOND));
            String h12 = TimeUtils.dateFormat(day.getTime(), "yyyy-MM-dd") + " 12:00:00";
            String now = TimeUtils.dateFormat(day.getTime(), "yyyy-MM-dd HH:mm:ss");
            long dif = TimeUtils.diffTime(now, h12, "yyyy-MM-dd HH:mm:ss");
            Date firstDate = day.getTime();
            if (dif < 0)
                firstDate = TimeUtils.getAddDayDate(firstDate, -1);
            String d1 = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 1), "yyyy-MM-dd");
            GameMenuItem item1 = new GameMenuItem(0, d1, d1);
            getAct().getDateSelected().put(selectedModule, item1);
            dateSeleted = item1;
        }
        if ((selectedData != null && selectedData.size() > 0) || dateSeleted != null) {
            adapterHelper.setAllListData(result);
            updateFromSelected();
        } else {
            showAllData(result);
        }
    }

    private void showAllData(ArrayList<TableModuleBean> result) {
        adapterHelper.setAllListData(result);
        adapterHelper.getData();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        digApp = getApp();
        tool.setVisibility(View.GONE);
        contentSv.setLoadStr(getString(R.string.pull_up_to_load_next_page));
        contentSv.setRefreshStr(getString(R.string.pull_down_to_refresh));
        contentSv.setReleaseToLoad(getString(R.string.release_to_load_app));
        contentSv.setReleaseToRefresh(getString(R.string.release_to_refresh_app));
        if (headerPager != null && contentSv != null && numberTv != null) {
            adapterHelper.setHeaderPager(headerPager);
            adapterHelper.setTypeMarkView(markTv);
            adapterHelper.setContentSv(contentSv);
            adapterHelper.setCountView(numberTv);
        }
        if (markTv != null) {
            markTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IClickLisenter.onclick(v);
                }
            });
        }
        ll_choice_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IClickLisenter.onclick(v);
            }
        });
        initCountryMarket();
        initCountryMarketPop();
        ll_countryMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryMarket.closePopupWindow();
                countryMarket.showPopupDownWindow();
            }
        });
        initMarketsPopu();
        iv_all_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marketsPopu.closePopupWindow();
                marketsPopu.showPopupDownWindow();
            }
        });
        img_word_cup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (digApp.getDataType().equals("0")){
                    digApp.setDataType("1");
                }else{
                    digApp.setDataType("0");
                }
                getAct().changeMarket();
            }
        });
    }

    private void initCountryMarket() {
        switch (getApp().getCountryMarket()) {
            case "HK":
                tv_title.setText(getString(R.string.football) + " (" + getString(R.string.HK_market) + ")");
                break;
            case "MY":
                tv_title.setText(getString(R.string.football) + " (" + getString(R.string.MY_market) + ")");
                break;
            case "ID":
                tv_title.setText(getString(R.string.football) + " (" + getString(R.string.ID_market) + ")");
                break;
            default:
                tv_title.setText(getString(R.string.football) + " (" + getString(R.string.EU_market) + ")");
                break;
        }
        switch (getApp().getMarket()) {
            case "0":
                iv_all_add.setText(getString(R.string.All_Markets));
                break;
            case "1":
                iv_all_add.setText(getString(R.string.main_Markets));
                break;
            default:
                iv_all_add.setText(getString(R.string.more_Markets));
                break;
        }
    }

    private void initCountryMarketPop() {
        countryMarketList = Arrays.asList(getString(R.string.HK_market), getString(R.string.MY_market),
                getString(R.string.ID_market), getString(R.string.EU_market));
        countryMarket = new BasePopupWindow(mContext, ll_countryMarket, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.pop_country_market;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                ListView mListView = (ListView) view.findViewById(R.id.lv_country_market);
                QuickBaseAdapter<String> adapter = new QuickBaseAdapter<String>(context, R.layout.item_country_market, countryMarketList) {
                    @Override
                    protected void convert(ViewHolder helper, String item, int position) {
                        TextView tv_content = helper.retrieveView(R.id.tv_content);
                        tv_content.setText(item);
                    }
                };
                mListView.setAdapter(adapter);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String currentCountry = countryMarketList.get(position);
                        tv_title.setText(getString(R.string.football) + " (" + currentCountry + ")");
                        if (currentCountry.equals(getString(R.string.HK_market))) {
                            digApp.setCountryMarket("HK");
                        } else if (currentCountry.equals(getString(R.string.MY_market))) {
                            digApp.setCountryMarket("MY");
                        } else if (currentCountry.equals(getString(R.string.ID_market))) {
                            digApp.setCountryMarket("ID");
                        } else {
                            digApp.setCountryMarket("EU");
                        }
                        digApp.setMarket("0");
                        iv_all_add.setText(getString(R.string.All_Markets));
                        countryMarket.closePopupWindow();
                        getAct().changeMarket();
                    }
                });
            }
        };
    }

    private void initMarketsPopu() {
        marketsList = Arrays.asList(getString(R.string.All_Markets), getString(R.string.main_Markets), getString(R.string.more_Markets));
        marketsPopu = new BasePopupWindow(mContext, iv_all_add, 300, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.popupwindow_game_tab_markets_pop;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                ListView lv = (ListView) view.findViewById(R.id.game_tab_meau_pop_lv);
                QuickBaseAdapter<String> adapter = new QuickBaseAdapter<String>(mContext, R.layout.item_text, marketsList) {
                    @Override
                    protected void convert(ViewHolder helper, String item, int position) {
                        helper.setText(R.id.text_tv1, item);
                    }
                };
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String type = marketsList.get(position);
                        if (type.equals(getString(R.string.All_Markets))) {
                            digApp.setMarket("0");
                        } else if (type.equals(getString(R.string.main_Markets))) {
                            digApp.setMarket("1");
                        } else {
                            digApp.setMarket("2");
                        }
                        marketsPopu.closePopupWindow();
                        iv_all_add.setText(type);
                        getAct().changeMarket();
                    }
                });
                lv.setAdapter(adapter);
            }
        };
    }

    @Override
    public void updateFromSelected() {
        adapterHelper.getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        clearanceBetNotify();
    }

    private void clearanceBetNotify() {
        if (isAdded()) {
            adapterHelper.setBetType(getAct().getBetType(), betCount);
            adapterHelper.notificationData();
        }
    }

    @Override
    public void notifyBetTypeChange() {
        clearanceBetNotify();
    }

    @Override
    protected ListView getListView() {
        return exlist;
    }


}
