package nanyang.com.dig88.Table;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.view.indicator.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindString;
import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.TimeUtils;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.AbsListPopupWindow;
import nanyang.com.dig88.Adapter.MyFragmentPagerAdapter;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.SerializableMap;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Imp.IClickListener;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.BettingInfoBean;
import nanyang.com.dig88.Table.entity.BettingParPromptBean;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Table.entity.PopMenuItemBean;
import nanyang.com.dig88.Table.entity.TableModuleBean;
import nanyang.com.dig88.Table.fragment.BasketballGamesFragment;
import nanyang.com.dig88.Table.fragment.GlqGamesFragment;
import nanyang.com.dig88.Table.fragment.OtherGamesFragment;
import nanyang.com.dig88.Table.fragment.SoccerGameFragment;
import nanyang.com.dig88.Table.fragment.TableBaseFragment;
import nanyang.com.dig88.Table.fragment.TennisGamesFragment;
import nanyang.com.dig88.Table.utils.TableAdapterHelper;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by xToney on 2015/10/22.
 */
public class BallGameActivity extends GameBaseActivity implements IClickListener {
    public static final int SELECTED_LEAGUE = 0x000000A;
    private static final int COLLECTION_CODE = 8;
    protected boolean isRightChecked = false;
    @BindString(R.string.football)
    String football;
    @BindString(R.string.basketball)
    String basketball;
    @BindString(R.string.tennis)
    String tennis;
    @BindString(R.string.other)
    String other;
    @BindString(R.string.rugby)
    String rugby;
    @BindView(R.id.ballgame_tabs_pstabs)
    PagerSlidingTabStrip ballgameTabsPstabs;
    @BindView(R.id.ballgame_pager_vp)
    ViewPager ballgamePagerVp;
    @BindView(R.id.ballgame_bottom_ll)
    LinearLayout ballgameBottomLl;
    @BindView(R.id.ballgame_clearance_rb)
    CheckBox clearanceRb;
    @BindView(R.id.ballgame_menu_btn)
    TextView menuBtn;
    @BindView(R.id.ballgame_collection_btn)
    TextView collectionBtn;
    @BindView(R.id.ballgame_refresh_btn)
    TextView refreshBtn;
    @BindColor(R.color.white)
    int white;
    @BindString(R.string.not_settled)
    String notSettled;
    @BindString(R.string.settled)
    String settled;
    AbsListPopupWindow<GameMenuItem> pop;
    Handler updateHandler = new Handler();
    private List<TableBaseFragment> fragmentsList;
    private TableHttpHelper helper;
    private TableBaseFragment indexFragment;
    Runnable dataUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            if (indexFragment.getDataHelper() != null) {
                indexFragment.getDataHelper().updateNet(indexFragment.getAdapterHelper().getModleType());
            }
            updateHandler.postDelayed(this, 20000);// 50是延时时长
        }
    };
    private String modleType;
    private Map<String, Map<String, Boolean>> listSeleted = new HashMap<>();
    private HashMap<String, GameMenuItem> dateSelected = new HashMap<>();
    private int bootomHeight = 150;
    private String selectedModle;
    private int betType;

    public String getSelectedModle() {
        return selectedModle;
    }

    public Map<String, Map<String, Boolean>> getListSeleted() {
        return listSeleted;
    }

    public HashMap<String, GameMenuItem> getDateSelected() {
        return dateSelected;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_ballgame_tab;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        modleType = getIntent().getStringExtra(AppConfig.ACTION_KEY_INITENT_STRING);
        selectedModle = modleType + "+" + getBetType();
        if (modleType.equals("Running"))
            setTitle(getString(R.string.Running));
        else if (modleType.equals("Today")) {
            setTitle(getString(R.string.today));
        } else if (modleType.equals("Early")) {
            setTitle(getString(R.string.early));

        }
        SoccerGameFragment footFragment = new SoccerGameFragment();
        footFragment.setDataType(modleType);
        BasketballGamesFragment basketFragment = new BasketballGamesFragment();
        TennisGamesFragment tennisFragemt = new TennisGamesFragment();
        OtherGamesFragment otherFragment = new OtherGamesFragment();
        GlqGamesFragment gnq = new GlqGamesFragment();
        fragmentsList = new ArrayList<>();
        footFragment.setTitle(football);
        basketFragment.setTitle(basketball);
        tennisFragemt.setTitle(tennis);
        gnq.setTitle(rugby);
        otherFragment.setTitle(other);
        fragmentsList.add(footFragment);
//        fragmentsList.add(basketFragment);
//        fragmentsList.add(tennisFragemt);
//        fragmentsList.add(gnq);
//        fragmentsList.add(otherFragment);
        ballgamePagerVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        ballgamePagerVp.setPageMargin(pageMargin);
        ballgameTabsPstabs.setTextColor(white);
        ballgameTabsPstabs.setViewPager(ballgamePagerVp);
        ballgameTabsPstabs.setBackGroundColorRes(R.color.blue_table);
        ballgameTabsPstabs.setSelectedBgColor(R.color.blue_table);
        ballgameTabsPstabs.setVisibility(View.GONE);
        ballgamePagerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                indexFragmentChange(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        initPop();
        indexFragmentChange(0);
        handleClearanceChecked();
        clearanceRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                changeClearance();
            }
        });
    }

    private void initPop() {
        pop = new AbsListPopupWindow<GameMenuItem>(mContext, rightTv, 300, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.popupwindow_game_tab_meau_pop;
            }

            @Override
            protected void popItemCLick(GameMenuItem gameMenuItem, int position) {
                closePopupWindow();
                indexFragment.setDataType(gameMenuItem.getValue());
                setTitle(gameMenuItem.getTitle());
                if (modleType != gameMenuItem.getValue()) {
                    clearClearance();
                }
                modleType = gameMenuItem.getValue();
                checkRunning();
                handleClearanceChecked();
                indexFragment.getData("");
            }

            @Override
            protected void convertItem(ViewHolder helper, GameMenuItem item, int position) {
                TextView t = helper.retrieveView(R.id.text_tv1);
                t.setTextColor(getResources().getColor(R.color.black_title));
                t.setTextSize(12);
                t.setCompoundDrawablesWithIntrinsicBounds(item.getDrawableRes(), 0, 0, 0);
                helper.setText(R.id.text_tv1, item.getTitle());
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_text;
            }

            @Override
            protected int getListViewId() {
                return R.id.game_tab_meau_pop_lv;
            }

            @Override
            protected void onCloose() {
                super.onCloose();
                isRightChecked = false;
            }
        };
    }

    protected void removeBetItem(final BettingInfoBean item) {

        final TableHttpHelper<BettingParPromptBean> deleteHttpHelper = new TableHttpHelper<>(mContext, null, new ThreadEndT<BettingParPromptBean>(new TypeToken<BettingParPromptBean>() {
        }.getType()) {
            @Override
            public void endT(BettingParPromptBean result, int model) {
                if (result == null || result.getBetPar() == null || result.getBetPar().size() < 1) {
                    dismissBlockDialog();
                    getApp().setBetDetail(null);
                    getApp().setBetParList(result);
                    indexFragment.getAdapterHelper().countBet();

                }
            }

            @Override
            public void endString(String result, int model) {
            }

            @Override
            public void endError(String error) {
                dismissBlockDialog();
            }
        });
        String ParUrl = "";

        for (BettingParPromptBean.BetParBean aitem : getApp().getBetParList().getBetPar()) {
            if (item.getHome().equals(aitem.getHome()) && item.getAway().equals(aitem.getAway())) {
                ParUrl = aitem.getParUrl();
                break;
            }
        }
        if (!ParUrl.equals("")) {
            if (item.getIsFH() == 0)
                deleteHttpHelper.getData(ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId(), "", 0);
            else {
                deleteHttpHelper.getData(ParUrl + "&isBP=1&RemoveId=" + item.getSocOddsId_FH(), "", 0);
            }
        } else {
            dismissBlockDialog();
        }
    }

    private void clearClearance() {
        if (getApp().getBetDetail() != null && getApp().getBetDetail().size() > 0) {
            setDialog(new BlockDialog(mContext, getResources().getString(R.string.loading)));
            showBlockDialog();
            Iterator<Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>>> it = getApp().getBetDetail().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Map<String, Map<Integer, BettingInfoBean>>> keyItem = it.next();
                Iterator<Map.Entry<String, Map<Integer, BettingInfoBean>>> itt = keyItem.getValue().entrySet().iterator();
                while (itt.hasNext()) {
                    Iterator<Map.Entry<Integer, BettingInfoBean>> ittt = itt.next().getValue().entrySet().iterator();
                    while (ittt.hasNext()) {
                        BettingInfoBean item = ittt.next().getValue();
                        if (item != null) {
                            removeBetItem(item);
                        }
                    }
                }
            }
        }
    }

    private void indexFragmentChange(int position) {
        indexFragment = fragmentsList.get(position);
        indexFragment.setModelClickLisenter(this);
    }

    /**
     * 重新数据类型 重置下注类型
     *
     * @param title
     */
    public void setTitle(String title) {
        if (title.equals(getString(R.string.Running))) {
            clearanceRb.setEnabled(false);
        } else {
            clearanceRb.setEnabled(true);
        }
        clearanceRb.setText(R.string.mix_parlay);
        titleTv.setText(title);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initToolBar() {
        super.initToolBar();
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        titleTv.setText(football);
        titleTv.setBackgroundResource(0);
    }

    private void showBottomMenuPop(View v) {
        AbsListPopupWindow<PopMenuItemBean> menuPop = new AbsListPopupWindow<PopMenuItemBean>(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.popupwindow_bottom_gridview_content;
            }

            @Override
            protected void popItemCLick(PopMenuItemBean o, int position) {
                if (o.getTitle().equals(getString(R.string.selected_compared))) {
                    Bundle b = new Bundle();
                    List<TableModuleBean> listData = indexFragment.getAdapterHelper().getAllShowData();
                    if (listData == null || listData.size() == 0) {
                        Toast.makeText(mContext, getString(R.string.no_data), Toast.LENGTH_SHORT).show();
                        return;
                    }
//                    b.putSerializable(AppConfig.ACTION_KEY_INITENT_DATA, (Serializable) listData);
                    if (listSeleted.get(selectedModle) == null)
                        listSeleted.put(selectedModle, new HashMap<String, Boolean>());
                    for (TableModuleBean tableModuleBean : listData) {
                        Boolean v = listSeleted.get(selectedModle).get(tableModuleBean.getLeagueBean().getModuleTitle());
                        if (v == null)
                            v = true;
                        listSeleted.get(selectedModle).put(tableModuleBean.getLeagueBean().getModuleTitle(), v);
                    }
                    SerializableMap<String, Boolean> ser = new SerializableMap<>();
                    ser.setMap(listSeleted.get(selectedModle));
                    b.putSerializable(AppConfig.ACTION_KEY_INITENT_OTHER, ser);
                    AppTool.activiyJumpForResult(BallGameActivity.this, SelectLeagueActivity.class, b, SELECTED_LEAGUE);
                } else if (o.getTitle().equals(notSettled)) {
                    AppTool.activiyJump(mContext, BetOrderActivity.class);
                } else if (o.getTitle().equals(settled)) {
                    AppTool.activiyJump(mContext, BetSettlementFirstActivity.class);
                } else if (o.getTitle().equals(getString(R.string.select_date))) {
                    if (modleType.equals("Early"))
                        showDateChoicePrompt();
                }
            }

            @Override
            protected void onCloose() {
                super.onCloose();
                menuBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_black_menu_black, 0, 0);
            }

            @Override
            protected void convertItem(ViewHolder helper, PopMenuItemBean item, int position) {
                helper.setImageResource(R.id.base_menu_item_iv, item.getDrawableRes());
                helper.setText(R.id.base_menu_item_tv1, item.getTitle());
                helper.setText(R.id.base_menu_item_tv2, item.getMark());
                CheckBox cb = helper.retrieveView(R.id.base_menu_item_cb);
                if (item.getTitle().equals(getString(R.string.sort_by_time)) || (item.getTitle()).equals(getString(R.string.end_push)) || (item.getTitle()).equals(getString(R.string.score_push))) {
                    cb.setVisibility(View.VISIBLE);
                } else {
                    cb.setVisibility(View.GONE);
                }

            }


            @Override
            protected void initView(View view) {
                super.initView(view);
                View gv = view.findViewById(R.id.gridview_content_gv);
//                new BounceAnimation(gv).animate();
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.base_menu_item;
            }

            @Override
            protected int getListViewId() {
                return R.id.gridview_content_gv;
            }
        };

        if (indexFragment.getAdapterHelper() != null && indexFragment.getAdapterHelper().getModleType() != null && indexFragment.getAdapterHelper().getModleType().equals("Early"))
            menuPop.setData(Arrays.asList(new PopMenuItemBean(R.mipmap.oval_black_flag_black, getString(R.string.selected_compared), ""),
                    new PopMenuItemBean(R.mipmap.oval_black_p_black, getString(R.string.not_settled), ""),
                    new PopMenuItemBean(R.mipmap.oval_black_hook_balck, getString(R.string.settled), ""),
                    new PopMenuItemBean(R.mipmap.oval_black_date_balck, getString(R.string.select_date), "")));
        else {
            menuPop.setData(Arrays.asList(new PopMenuItemBean(R.mipmap.oval_black_flag_black, getString(R.string.selected_compared), ""),
                    new PopMenuItemBean(R.mipmap.oval_black_p_black, getString(R.string.not_settled), ""),
                    new PopMenuItemBean(R.mipmap.oval_black_hook_balck, getString(R.string.settled), "")
            ));
        }
        bootomHeight = ballgameBottomLl.getHeight();
        menuPop.showPopupGravityWindow(Gravity.BOTTOM, 0, bootomHeight);
        menuBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_yellow_menu_yellow, 0, 0);
    }

    public void changeClearance() {
        if (indexFragment != null) {
//            collectionBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.star_outline_grey, 0, 0);
            indexFragment.getAdapterHelper().setLocalCollection(false);
            clearClearance();
            if (getBetType() == 0) {
                setBetType(TableAdapterHelper.ClearanceBet);
                handleClearanceChecked();
            } else {
                setBetType(0);
                handleClearanceChecked();
            }
            indexFragment.getData("");
        }
    }

    protected void handleClearanceChecked() {
        selectedModle = modleType + "+" + getBetType();
        if (getBetType() == 0) {
            clearanceRb.setChecked(false);
            clearanceRb.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_black_msg_order_black, 0, 0);
            if (pop != null)
                pop.setData(Arrays.asList(new GameMenuItem(R.drawable.menu_small_grey500, getString(R.string.early), "Early"), new GameMenuItem(R.drawable.menu_small_grey500, getString(R.string.today), "Today"), new GameMenuItem(R.drawable.menu_small_grey500, getString(R.string.Running), "Running")));
        } else if (getBetType() == TableAdapterHelper.ClearanceBet) {

            clearanceRb.setChecked(true);
            clearanceRb.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_yellow_msg_order_yellow, 0, 0);
            if (pop != null)
                pop.setData(Arrays.asList(new GameMenuItem(R.drawable.menu_small_grey500, getString(R.string.early), "Early"), new GameMenuItem(R.drawable.menu_small_grey500, getString(R.string.today), "Today")));
        }
        if (indexFragment != null)
            indexFragment.notifyBetTypeChange();
    }

    public void clickCollection(View v) {
        if (indexFragment.getAdapterHelper().getBetType() != TableAdapterHelper.ClearanceBet) {

            indexFragment.getAdapterHelper().setPage(0);
            indexFragment.getAdapterHelper().changeLocalCollection();
            if (indexFragment.getAdapterHelper().isLocalCollection()) {
                collectionBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_yellow_star_yellow, 0, 0);
            } else {
                collectionBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_black_star_black, 0, 0);
            }
        } else {
            Toast.makeText(mContext, getString(R.string.clearance) + " " + getString(R.string.no_records), Toast.LENGTH_SHORT).show();
        }
//        Bundle bundle = new Bundle();
//        bundle.putString(AppConfig.ACTION_KEY_INITENT_STRING, indexFragment.getDataType());
//        AppTool.activiyJumpForResult(this, GameCollectionActivity.class, bundle, COLLECTION_CODE);
    }

    public void clickRefresh(View v) {
        indexFragment.getData("");
    }

    public void clickMenu(View v) {
        showBottomMenuPop(ballgameBottomLl);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == SELECTED_LEAGUE) {
                SerializableMap<String, Boolean> seletedMap = (SerializableMap<String, Boolean>) data.getSerializableExtra(AppConfig.ACTION_KEY_INITENT_OTHER);
                listSeleted.put(selectedModle, seletedMap.getMap());
                updateFromSelected();
            }
        }
//        if (requestCode == COLLECTION_CODE) {
//            indexFragment.getData("");
//        }
    }

    private void updateFromSelected() {
        indexFragment.updateFromSelected();
    }

    public void setParentEnable(boolean flag) {
        if (flag)
            refreshBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_black_refresh_black, 0, 0);
        else {
            refreshBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.oval_yellow_refresh_yellow, 0, 0);
        }
        clearanceRb.setEnabled(flag);
        refreshBtn.setClickable(flag);
        menuBtn.setClickable(flag);
        collectionBtn.setClickable(flag);
        checkRunning();
    }

    protected void checkRunning() {
        if (modleType.equals("Running")) {
            clearanceRb.setEnabled(false);
            clearanceRb.setChecked(false);
            setBetType(0);
        }
    }

    @Override
    protected void updateUserInfoEnd() {
        super.updateUserInfoEnd();
        if (indexFragment.getDataHelper() != null)
            indexFragment.getDataHelper().updateNet(indexFragment.getAdapterHelper().getModleType());

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTableData();
    }

    @Override
    public void onStop() {
        super.onStop();
        handleClearanceChecked();
        removeTableUpdate();
    }

    public void updateTableData() {
        removeTableUpdate();
        updateHandler.post(dataUpdateRunnable);// 打开定时器，执行操作
    }

    public void removeTableUpdate() {
        updateHandler.removeCallbacks(dataUpdateRunnable);// 关闭定时器处理
    }

    @Override
    public void onclick(View v) {

        if (isRightChecked)
            pop.closePopupWindow();
        else {
            pop.setView(v);
            pop.showPopupDownWindow(0, 15);
        }
        isRightChecked = !isRightChecked;
    }

    public void changeMarket() {
        indexFragment.setDataType(modleType);
        clearClearance();
        checkRunning();
        handleClearanceChecked();
        indexFragment.getData("");
    }

    private void showDateChoicePrompt() {
        AbsListPopupWindow<GameMenuItem> popChoice = new AbsListPopupWindow<GameMenuItem>(mContext, new View(mContext), 800, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.popupwindow_choice_list;
            }

            @Override
            protected void popItemCLick(GameMenuItem item, int position) {
                closePopupWindow();
                getEarlyDayData(item);
            }

            @Override
            protected void convertItem(ViewHolder helper, GameMenuItem item, int position) {
                helper.setText(R.id.item_text_tv, item.getTitle());

            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_text_base_date;
            }

            @Override
            protected int getListViewId() {
                return R.id.popup_choice_lv;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                ListView lv = (ListView) view.findViewById(R.id.popup_choice_lv);
                View header = LayoutInflater.from(mContext).inflate(R.layout.item_text, null);
                TextView t = (TextView) header.findViewById(R.id.text_tv1);
                t.setPadding(10, 40, 10, 40);
                t.setText("Select Time");
                lv.addHeaderView(t);
            }
        };
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
        String dv = TimeUtils.dateFormat(TimeUtils.getAddDayDate(firstDate, 6), "yyyy-MM-dd");
        String d6 = getString(R.string.six_day);
        GameMenuItem item0 = new GameMenuItem(0, getString(R.string.all), "");
        GameMenuItem item1 = new GameMenuItem(0, d1, d1);
        GameMenuItem item2 = new GameMenuItem(0, d2, d2);
        GameMenuItem item3 = new GameMenuItem(0, d3, d3);
        GameMenuItem item4 = new GameMenuItem(0, d4, d4);
        GameMenuItem item5 = new GameMenuItem(0, d5, d5);
        GameMenuItem item6 = new GameMenuItem(1, d6, dv);
        popChoice.setData(new ArrayList<GameMenuItem>(Arrays.asList(item0, item1, item2, item3, item4, item5, item6)));
        popChoice.showPopupCenterWindow();

    }

    private void getEarlyDayData(GameMenuItem item) {
        dateSelected.put(selectedModle, item);
        indexFragment.updateFromSelected();
    }

}