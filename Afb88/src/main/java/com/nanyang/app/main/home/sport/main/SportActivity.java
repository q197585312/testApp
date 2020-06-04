package com.nanyang.app.main.home.sport.main;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.MyGoHomeBroadcastReceiver;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.huayThai.HuayThaiFragment;
import com.nanyang.app.main.home.sport.WebSocketManager;
import com.nanyang.app.main.home.sport.allRunning.AllRunningFragment;
import com.nanyang.app.main.home.sport.betOrder.IBetOrderView;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.live.LivePlayHelper;
import com.nanyang.app.main.home.sport.live.ViewHolder;
import com.nanyang.app.main.home.sport.model.RunMatchInfo;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.GZipUtil;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.MyFileUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.logger.Logger;

public class SportActivity extends BaseToolbarActivity<MainPresenter> implements ILanguageView<String>, IBetOrderView {
    private final String GUIDE_KEY = "GUIDE";

    HuayThaiFragment huayThaiFragment = new HuayThaiFragment();
    BaseSportFragment localCurrentFragment;
    @Bind(R.id.web_wv)
    WebView webView;
    @Bind(R.id.iv_title_live_stream)
    ImageView tv_title_live_stream;
    @Bind(R.id.iv_title_live_center)
    ImageView tv_title_live_center;
    @Bind(R.id.ll_back_title_line)
    View ll_back;
    @Bind(R.id.iv_home_menu)
    View iv_home_menu;
    @Bind(R.id.tv_match_play)
    TextView tv_match_play;
    @Bind(R.id.tv_run_match_title)
    TextView tv_run_match_title;
    @Bind(R.id.ll_line1)
    public View ll_line1;
    @Bind(R.id.ll_line2)
    public View ll_line2;
    @Bind(R.id.ll_line3)
    public View ll_line3;
    @Bind(R.id.right_ll)
    View right_ll;
    @Bind(R.id.drawer_more)
    DrawerLayout drawerLayout;

    @Bind(R.id.rc_sport_list)
    RecyclerView rc_sport_list;
    @Bind(R.id.bet_pop_parent_ll)
    public View bet_pop_parent_ll;

    @Bind(R.id.fl_top_video)
    public View fl_top_video;
    @Bind(R.id.tv_toolbar_left)
    TextView tvToolbarLeft;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.tv_toolbar_right1)
    TextView tvToolbarRight1;
    @Bind(R.id.tv_toolbar_right)
    TextView tvToolbarRight;

    @Bind(R.id.iv_home_back)
    ImageView ivHomeBack;
    @Bind(R.id.match_cup_iv)
    ImageView matchCupIv;

    @Bind(R.id.running_iv)
    ImageView runningIv;
    @Bind(R.id.iv_all_add)
    TextView ivAllAdd;
    @Bind(R.id.tv_odds_type)
    public TextView tvOddsType;
    @Bind(R.id.tv_league_major)
    TextView tvLeagueMain;
    @Bind(R.id.iv_sort_time)
    ImageView ivSortTime;
    @Bind(R.id.fl_main_content)
    FrameLayout flContent;
    @Bind(R.id.tv_record)
    TextView tvRecord;
    @Bind(R.id.tv_sport_select)
    TextView tvSportSelect;
    @Bind(R.id.iv_order_top)
    ImageView ivOrderTop;
    @Bind(R.id.tv_mix)
    TextView tvMix;
    @Bind(R.id.tv_order_count_top)
    TextView tvOrderCount;
    @Bind(R.id.tv_mix_count)
    TextView tvMixCount;

    @Bind(R.id.tv_way_run)
    public TextView tvMatchType;
    @Bind(R.id.tv_menu)
    TextView tvMenu;
    @Bind(R.id.ll_sport_menu_bottom)
    public
    LinearLayout llSportMenuBottom;
    @Bind(R.id.sports_language_tv)
    TextView sports_language_tv;
    @Bind(R.id.sports_selected_tv)
    TextView getTvSportSelect;
    @Bind(R.id.sport_title_tv)
    TextView sportTitleTv;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_waite_count)
    TextView tvWaiteCount;
    @Bind(R.id.iv_delete_search)
    ImageView ivDeleteSearch;
    @Bind(R.id.edt_match_search_content)
    EditText edtSearchContent;

    @Bind(R.id.ll_header_sport)
    public LinearLayout ll_header_sport;
    @Bind(R.id.ll_footer_sport)
    public LinearLayout ll_footer_sport;

    @Bind(R.id.match_collection_num_tv)
    public TextView collectionNumTv;
    @Bind(R.id.ll_home_left)
    View ll_home_left;
    @Bind(R.id.match_collection_fl)
    View collectionFl;

    @Bind(R.id.sports_selected)
    RadioButton sports_selected;
    @Bind(R.id.others_selected)
    RadioButton others_selected;
    @Bind(R.id.games_switch_rg)
    RadioGroup games_switch_rg;


    public MenuItemInfo<String> item;
    private String currentGameType = "";

    public AfbDrawerViewHolder afbDrawerViewHolder;
    private SportIdBean currentIdBean;
    private boolean notClickType = false;
    public LivePlayHelper liveMatchHelper;
    private IRTMatchInfo itemBallAdded;
    private int positionBallAdded;
    private boolean onlyShowOne;
    private boolean isPlay;
    public IRTMatchInfo itemBall;


    public TextView getIvAllAdd() {
        return ivAllAdd;
    }

    private String currentTag = "";
    private HashMap<String, BaseSportFragment> mapFragment;
    public BaseSportFragment currentFragment;
    private Handler handler = new Handler();
    MyGoHomeBroadcastReceiver myGoHomeBroadcastReceiver;
    public String wd = "";
    private static final String BUNDLE_FRAGMENTS_KEY = "android:support:fragments";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            //销毁时不保存fragment的状态
            outState.remove(BUNDLE_FRAGMENTS_KEY);
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            //重建时清除 fragment的状态
            savedInstanceState.remove(BUNDLE_FRAGMENTS_KEY);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        toolbar.setVisibility(View.GONE);

        edtSearchContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = editable.toString().trim();
                if (TextUtils.isEmpty(s)) {
                    edtSearchContent.setCursorVisible(false);
                } else {
                    edtSearchContent.setCursorVisible(true);
                }
                if (!StringUtils.isNull(s)) {
                    ivDeleteSearch.setVisibility(View.VISIBLE);
                    currentFragment.searchMatch(true, s);
                } else {
                    ivDeleteSearch.setVisibility(View.GONE);
                    currentFragment.searchMatch(false, "");
                }
                currentFragment.checkBg(collectionFl, currentFragment.presenter.getStateHelper().isCollection(), R.mipmap.sport_game_star_yellow_open, R.mipmap.sport_game_star_yellow);

            }
        });
        myGoHomeBroadcastReceiver = new MyGoHomeBroadcastReceiver(getApp());
        registerReceiver(myGoHomeBroadcastReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
//        presenter.getStateHelper().switchOddsType(item.getType());
        updateMixOrderCount();

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type = "";
    AfbApplication app;
    ViewHolder viewHolder;

    @Override
    public void initData() {
        super.initData();
        createPresenter(new MainPresenter(this));
        app = (AfbApplication) getApplication();
        item = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA);

        afbDrawerViewHolder = new AfbDrawerViewHolder(drawerLayout, this, R.id.fl_main_content);

        if (item != null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());

            initFragment(item.getParent());
            int res = R.mipmap.date_running_green;
            dateClickPosition = 1;
            String day = "", dateParam = "";
            switch (type) {
                case "Early":
                    res = R.mipmap.date_early_grey;
                    dateClickPosition = 2;
                    dateParam = "7";
                    break;
                case "Today":
                    dateClickPosition = 1;
                    res = R.mipmap.date_today_grey;
                    break;
            }
            runWayItem(new MenuItemInfo<>(res, item.getText(), type, res, day, dateParam));
//            currentFragment.presenter.getStateHelper().switchOddsType(getApp().getOddsType().getType());
            viewHolder = new ViewHolder(fl_top_video);
            liveMatchHelper = new LivePlayHelper(viewHolder, this);

        }
        tvRecord.setText(R.string.TabMyBets);
        tvMix.setText(R.string.bet_slip);
        tvMenu.setText(R.string.more);

        initLeftMenu();
        right_ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.d("MotionEvent", v.getId());
                ll_home_left.setVisibility(View.GONE);
                return false;
            }
        });
        MenuItemInfo<String> languageItem = new LanguageHelper(mContext).getLanguageItem();
        sports_language_tv.setText(languageItem.getText());
    }

    BaseRecyclerAdapter<SportIdBean> leftSportAdapter;

    private void initLeftMenu() {

        leftSportAdapter = new BaseRecyclerAdapter<SportIdBean>(mContext, new ArrayList<SportIdBean>(), R.layout.item_sport_switch) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final SportIdBean item) {
                LinearLayout llContent = holder.getView(R.id.ll_content);
                final TextView imgGamePic = holder.getView(R.id.img_game_pic);
                TextView tvGameName = holder.getView(R.id.tv_game_name);
                TextView tvGameCount = holder.getView(R.id.tv_game_count);

                llContent.setBackgroundColor(Color.WHITE);
                imgGamePic.setBackgroundResource(item.getSportPic());
                tvGameName.setText(item.getTextRes());
                tvGameName.setTextColor(item.getTextColor());
                tvGameCount.setText("0");
                String num = numMap.get(item.getDbid());

                if (StringUtils.isNull(num) || Integer.parseInt(num) < 1) {
                    tvGameCount.setVisibility(View.INVISIBLE);
                } else {
                    tvGameCount.setText(num);
                    tvGameCount.setVisibility(View.VISIBLE);
                    if (item.getTextRes() == R.string.Soccer_Runing && !currentFragment.presenter.getStateHelper().getStateType().getType().toLowerCase().startsWith("r")) {
                        tvGameCount.setVisibility(View.INVISIBLE);
                    }
                }
                if (item.getTextColor() != Color.RED) {
                    if (tvSportSelect.getText().toString().equals(getString(item.getTextRes()))) {
                        tvGameName.setTextColor(ContextCompat.getColor(mContext, R.color.google_green));
                        llContent.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gary1));
                    }
                } else {
                    if (tvSportSelect.getText().toString().equals(getString(item.getTextRes()))) {
                        llContent.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gary1));
                    }
                }
            }
        };
        rc_sport_list.setAdapter(leftSportAdapter);
        leftSportAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SportIdBean>() {
            @Override
            public void onItemClick(View view, SportIdBean item, int position) {
                String g = item.getId();
                if (getApp().updateOtherMap().containsKey(g)) {
                    (getBaseActivity().presenter).clickGdGameItem(g);
                    return;
                } else {
                    initSportFragment(item, null);
                    tvLeagueMain.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                }
                ll_home_left.setVisibility(View.GONE);
            }
        });

        games_switch_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                changeSportRadioButton(id);
            }
        });

    }

    private void changeSportRadioButton(@IdRes int id) {
        switch (id) {
            case R.id.sports_selected:
                loadLeftMenuSports();
                break;
            case R.id.others_selected:
                loadLeftMenuOthers();
                break;
        }
    }

    private void loadLeftMenuOthers() {
        if (AppConstant.IS_AGENT)
            return;
        Map<String, String> enableMap = getApp().updateOtherMap();
        Iterator<SportIdBean> iterator = AfbUtils.othersMap.values().iterator();
        final List<SportIdBean> listOther = new ArrayList<>();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next();
            listOther.add(next);
       /*     String enable = enableMap.get(next.getId());
            if (enable != null && enable.equals("True")) {
                listOther.add(next);
            }*/
        }
        leftSportAdapter.addAllAndClear(listOther);
    }


    private void createWebSocket() {
        WebSocketManager.getInstance().createWebSocket(new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                LogUtil.d("Socket", "连接完成，currentFragment：" + currentFragment + "State:" + currentFragment.presenter.getStateHelper());
                WebSocketManager.getInstance().startUpdateData();
                currentFragment.presenter.getStateHelper().refresh();

            }
        }, new WebSocket.StringCallback() {
            @Override
            public void onStringAvailable(final String s) {
                if (StringUtils.isNull(s))
                    return;
                if (s.equals("3"))
                    return;
                LogUtil.d("Socket", "---获得服务器返回数据-----------");
                String s1 = GZipUtil.uncompressToString(s.getBytes());
                currentFragment.presenter.getStateHelper().handleData(s1);
            }
        }, this);
    }

    void test() {
        String s1 = MyFileUtils.readFileFromAssets(mContext, "jsonTableListTest.txt");
        LogUtil.d("Socket", "---解密后得数据-----------" + s1 + " ---");
        currentFragment.presenter.getStateHelper().handleData(s1);
    }


    public void updateMixOrder() {
        if (currentFragment.isVisible()) {
            updateMixOrderCount();
            SportPresenter sportPresenter = currentFragment.presenter;
            if ((sportPresenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
                BallAdapterHelper adapterHelper = (BallAdapterHelper) (sportPresenter.getStateHelper()).getAdapterHelper();
                adapterHelper.getBaseRecyclerAdapter().notifyDataSetChanged();
            }
        }

    }

    private void updateMixOrderCount() {
        if (getApp().getMixBetList() != null && getApp().getMixBetList().size() > 0) {
            tvMixCount.setVisibility(View.VISIBLE);
            tvOrderCount.setVisibility(View.VISIBLE);
            ivOrderTop.setImageResource(R.mipmap.sport_shopping_top_white);
            tvMixCount.setText("" + getApp().getMixBetList().size());
            tvOrderCount.setText("" + getApp().getMixBetList().size());
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_bottom_teb_shopping, 0, 0);
            tvMix.setTextColor(ContextCompat.getColor(mContext, R.color.grey_dark));
        } else {
            tvMix.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_bottom_teb_shopping_null, 0, 0);
            ivOrderTop.setImageResource(R.mipmap.sport_shopping_top_gray);
            tvMixCount.setVisibility(View.GONE);
            tvOrderCount.setVisibility(View.GONE);
            tvMixCount.setText("0");
            tvOrderCount.setText("0");
            tvMix.setTextColor(ContextCompat.getColor(mContext, R.color.grey_light));
            if (betPop != null && betPop.v.getVisibility() == View.VISIBLE)
                betPop.closePopupWindow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BetGoalWindowUtils.clear();
        liveMatchHelper.onDestroy();
        unregisterReceiver(myGoHomeBroadcastReceiver);

    }

    private void initFragment(String parentType) {
        SportIdBean sportIdBean = AfbUtils.getSportByType(parentType);
        if (sportIdBean == null)
            sportIdBean = AfbUtils.getSportByG("1");
        if (sportIdBean == null)
            sportIdBean = new SportIdBean("1", "1", R.string.Soccer, "SportBook", SportActivity.class, new SoccerFragment(), Color.BLACK, R.mipmap.football);
        currentGameType = sportIdBean.getType();
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        localCurrentFragment = sportIdBean.getBaseFragment();
        Logger.getDefaultLogger().d("localCurrentFragment:" + localCurrentFragment);
        initSportFragment(sportIdBean, null);

    }


    @Override
    public void defaultSkip(String parentType) {
        if (!parentType.equals(currentGameType)) {
            initFragment(parentType);
        }
    }


    public void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment) {

        if (currentTag.isEmpty()) {

        } else if (!currentTag.equals(tag)) {
            MenuItemInfo stateType = currentFragment.presenter.getStateHelper().getStateType();
            setType(stateType.getType());
        } else {
            currentFragment.onResume();
        }
        changeFragmentInit(tag, localCurrentFragment);
//        sportTitleTv.setText(getString(R.string.sport_match) + " > " + currentTag);
    }

    private void changeFragmentInit(String tag, BaseSportFragment localCurrentFragment) {
        afbDrawerViewHolder.initDefaultFragment(localCurrentFragment);
        deleteHeadAndFoot();
        sportTitleTv.setText(getString(R.string.sport_match) + " > ");
        getTvSportSelect.setText(tag);
        afbDrawerViewHolder.switchFragment(localCurrentFragment);
        currentFragment = localCurrentFragment;
        currentTag = tag;
        tvSportSelect.setText(tag);
    }

    public void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment, String type) {

        if (currentTag.isEmpty()) {
        } else {
            setType(type);
        }
        changeFragmentInit(tag, localCurrentFragment);
//        sportTitleTv.setText(getString(R.string.sport_match) + " > " + currentTag);
    }

    private void deleteHeadAndFoot() {
        ll_footer_sport.removeAllViews();
        ll_header_sport.removeAllViews();
    }


    @Override
    protected void updateBalanceTv(String allData) {
        String s = AfbUtils.addComma(allData, AppConstant.IS_AGENT);

        tvBalance.setText(getApp().getUser().getCurCode2() + ": " + s);
    }


    @Override
    public void onBackCLick(View v) {
        afbDrawerViewHolder.isBack(false);
        tvSportSelect.setText(currentIdBean.getTextRes());
        tvSportSelect.setCompoundDrawablesWithIntrinsicBounds(0, currentIdBean.getSportPic(), 0, 0);
    }


    @Override
    public void onGetData(final String data) {

    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onLanguageSwitchSucceed(String str) {
        recreate();
        popWindow.closePopupWindow();
    }


    public void setOddsType(MenuItemInfo oddsType) {
        getApp().setOddsType(oddsType);
    }

    public MenuItemInfo getOddsType() {
        return getApp().getOddsType();
    }

    public void setMarketType(MenuItemInfo allOdds) {
        getApp().setMarketType(allOdds);
    }

    public MenuItemInfo<String> getMarketType() {
        return getApp().getMarketType();
    }

    public int getSortType() {
        return getApp().getSort();
    }


    @Override
    public void againLogin(String gameType) {
        presenter.login(new LoginInfo(app.getUser().getLoginName(), app.getUser().getPassword()), new BaseConsumer<String>(this) {
            @Override
            protected void onBaseGetData(String data) {
                onLanguageSwitchSucceed(data);
            }
        });
    }

    public void clickCup(View view) {
        currentFragment.presenter.getStateHelper().createChoosePop(presenter.mApiWrapper, view);
    }


    public void clickCollectionStar(View view) {
        currentFragment.collection(view);
    }

    public void clickBets(View view) {
        afbDrawerViewHolder.goRecord();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (AppConstant.IS_AGENT) {
            others_selected.setVisibility(View.GONE);
            loadLeftMenuSports();
        } else {
            others_selected.setVisibility(View.VISIBLE);
        }
        if (getApp().getOddsType() != null)
            presenter.switchOddsType(getApp().getOddsType().getType());
        else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    MenuItemInfo oddsType = getApp().getOddsType();
                    if (oddsType == null)
                        oddsType = new MenuItemInfo(0, (R.string.MY_ODDS), "MY");
                    presenter.switchOddsType(oddsType.getType());
                }
            }, 1000);
        }
        createWebSocket();

        startRefreshMenu();
        if (getBetContent().v.getVisibility() == View.VISIBLE)
            getBetContent().updateOdds(0);


    }

    @Override
    protected void onPause() {
        super.onPause();
        if (liveMatchHelper != null)
            liveMatchHelper.onPausePlay();
    }

    @Override
    protected void onStop() {
        setStopCloseWindow(false);
        super.onStop();
        if (getBetContent().v.getVisibility() == View.VISIBLE)
            getBetContent().stopUpdateOdds();

        stopRefreshMenu();
        WebSocketManager.getInstance().stopUpdateData();
        closeTv(fl_top_video);
    }

    Map<String, String> numMap = new HashMap<>();

    public void clickLeftMenu(final View view) {

        boolean hasGone = ll_home_left.getVisibility() == View.GONE;
        if (hasGone) {
            int checkedRadioButtonId = games_switch_rg.getCheckedRadioButtonId();
            changeSportRadioButton(checkedRadioButtonId);
        }
        ll_home_left.setVisibility(hasGone ? View.VISIBLE : View.GONE);
    }

    private void loadLeftMenuSports() {
        LogUtil.d("type", "--->>" + type);
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), AppConstant.wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    RecyclerView rv = rc_sport_list;
                    rv.setLayoutManager(new LinearLayoutManager(mContext));
                    final List<SportIdBean> listSport = new ArrayList<>();

                    MenuItemInfo stateType = currentFragment.getPresenter().getStateHelper().getStateType();
                    String type = stateType.getType();
                    String typeStr;
                    LogUtil.d("type", "--->>" + type);
                    if (type.equals("Running")) {
                        typeStr = "M_RAm";
                    } else if (type.equals("Today")) {
                        typeStr = "M_TAm";

                    } else {
                        typeStr = "M_EAm";
                    }

                    Iterator<SportIdBean> iterator = AfbUtils.sportMap.values().iterator();
                    while (iterator.hasNext()) {
                        SportIdBean next = iterator.next();
                        String num = jsonObjectNum.optString(typeStr + next.getDbid());
                        numMap.put(next.getDbid(), num);
                        if (next.getId().equals("1,9,21,29,51,182") || (next.getId().equals("1") && next.getTextRes() == R.string.Soccer_Runing) || (!StringUtils.isNull(num) && Integer.valueOf(num) > 0))
                            listSport.add(next);
                    }
              /*    Iterator<SportIdBean> iterator1 = AfbUtils.othersMap.values().iterator();
                    while (iterator1.hasNext()) {
                        SportIdBean next = iterator1.next();
                        if (!StringUtils.isNull(jsonObjectNum.optString(typeStr + next.getDbid())))
                            listSport.add(next);
                    }*/
                    leftSportAdapter.addAllAndClear(listSport);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickAllRunning(View view) {
        setType("Running");
        dateClickPosition = 1;
        MenuItemInfo<Integer> item = new MenuItemInfo<Integer>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green);
        runWayItem(item);
        SportIdBean sportIdBean = AfbUtils.sportMap.get("1,9,21,29,51,182");
        selectFragmentTag(getString(sportIdBean.getTextRes()), sportIdBean.getBaseFragment());
    }

    private void initSportFragment(SportIdBean item, IRTMatchInfo itemBall) {
        tvSportSelect.setCompoundDrawablesWithIntrinsicBounds(0, item.getSportPic(), 0, 0);
        tvSportSelect.setText(getString(item.getTextRes()));
        notClickType = false;

        if (!item.getDbid().startsWith("33"))
            currentIdBean = item;
        if (item.getTextRes() == R.string.Soccer_Runing) {
            setType("Running");
            dateClickPosition = 1;
            runWayItem(new MenuItemInfo<Integer>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green));
            selectFragmentTag(getString(R.string.Soccer), item.getBaseFragment(), "Running");
            return;
        } else if (item.getDbid().equals("1") && item.getTextRes() == R.string.Soccer_Runing) {
            setType("Running");
            dateClickPosition = 1;
            runWayItem(new MenuItemInfo<Integer>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green));
        } else if (item.getDbid().startsWith("33")) {
            MenuItemInfo<String> stringMenuItemInfo = new MenuItemInfo<>(R.mipmap.thai_thousand_1d, (R.string.game1d), item.getDbid(), "1");
            if (item.getDbid().equals("33_19")) {
                stringMenuItemInfo = new MenuItemInfo<String>(R.mipmap.thai_thousand_2d, (R.string.game2d), item.getDbid(), "2");
            } else if (item.getDbid().equals("33_20")) {
                stringMenuItemInfo = new MenuItemInfo<String>(R.mipmap.thai_thousand_3d, (R.string.game3d), item.getDbid(), "3");
            }
            notClickType = true;
            huayThaiFragment.setInfo(stringMenuItemInfo);
//            deleteHeadAndFoot();
            afbDrawerViewHolder.switchFragment(huayThaiFragment);
            return;
        }
        selectFragmentTag(getString(item.getTextRes()), item.getBaseFragment());
        if (itemBall != null) {
            this.itemBall = itemBall;
        }
    }

    public void clickSportWayRun(final View view) {

        if (currentFragment != null && currentFragment instanceof AllRunningFragment || notClickType) {
            return;
        }

        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), AppConstant.wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    createPopupWindow(
                            new BasePopupWindow(mContext, tvMatchType, AfbUtils.getScreenWidth(mContext) / 2, AfbUtils.dp2px(mContext, 356)) {
                                @Override
                                protected int onSetLayoutRes() {
                                    return R.layout.popupwindow_choice_ball_type;
                                }

                                @Override
                                protected void initView(View view) {
                                    super.initView(view);

                                    RecyclerView rv_list = view.findViewById(R.id.rv_list);
                                    View iv_home_back = view.findViewById(R.id.iv_home_back);
                                    final CheckBox checkBox = view.findViewById(R.id.cb_sort_time);
                                    checkBox.setChecked(getApp().getSort() == 1);
                                    final View ll_sort = view.findViewById(R.id.ll_sort);
                                    setChooseTypeAdapter(rv_list, tvMatchType, jsonObjectNum);
                                    ll_sort.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            changeTimeSort();
                                            checkBox.setChecked(getApp().getSort() == 1);
                                            closePopupWindow();
                                        }
                                    });
                                    iv_home_back.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            finish();
                                        }
                                    });
                                }
                            });
                    popWindow.showPopupWindowUpCenter(view, AfbUtils.dp2px(mContext, 356 + 15), AfbUtils.getScreenWidth(mContext) / 2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
    }

    private void changeTimeSort() {
        getApp().setSort(1 - getApp().getSort());
        currentFragment.getPresenter().getStateHelper().refresh();
        ivSortTime.setImageResource(getApp().getSort() == 0 ? R.mipmap.sport_game_clock_white : R.mipmap.sport_game_clock_exit_white);
    }

    public int dateClickPosition = 1;

    private void setChooseTypeAdapter(RecyclerView rv_list, TextView textView, JSONObject jsonObjectNum) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(currentFragment.presenter.getStateHelper().switchTypeAdapter(textView, jsonObjectNum));
    }

    public void clickMoreMenu(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    public void clickOrder(View view) {
        currentFragment.clickOrder();
    }

    public void clickBack(View view) {

        finish();
    }

    public void clickOddsType(View view) {
        currentFragment.clickOddsType((TextView) view);
    }

    public void rememberLastOdds() {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(mContext), mContext);
        MenuItemInfo oddsType = getOddsType();
        if (oddsType != null) {
            tvOddsType.setText(oddsType.getText());

        }
        MenuItemInfo<String> allOddsType = getMarketType();
        if (allOddsType != null) {
            ivAllAdd.setText(allOddsType.getParent());
        }
    }

    public void clickTop(View view) {
        clickTop();
    }

    public void clickTop() {
        currentFragment.checkTop(tvLeagueMain);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return afbDrawerViewHolder.onKeyDown(keyCode, event);
    }


    public void clickDeleteSearch(View view) {
        currentFragment.searchMatch(false, "");
        edtSearchContent.setText("");
    }

    public void clickOrderTop(View view) {
        currentFragment.clickOrder();
    }

    public void startRefreshMenu() {
        stopRefreshMenu();
        super.startRefreshMenu();
    }


    public void onGetRefreshMenu(List<String> beanList) {
        int waitSize = beanList.size();
        if (waitSize > 0) {
            tvWaiteCount.setVisibility(View.VISIBLE);
            tvWaiteCount.setText(waitSize + "");
        } else {
            tvWaiteCount.setVisibility(View.GONE);
        }

        if (lastWaitDataBeanList == null) {
            lastWaitDataBeanList = beanList;
        } else {
            for (int i = 0; i < lastWaitDataBeanList.size(); i++) {
                String waitNum = lastWaitDataBeanList.get(i);
                Log.d("onGetRefreshMenu", "lastWaitDataBeanList: " + lastWaitDataBeanList.toString());
                Log.d("onGetRefreshMenu", "beanList: " + beanList.toString());
                if (!beanList.contains(waitNum)) {
                    String accType = tvOddsType.getText().toString().trim();
                    BetGoalWindowUtils.showBetWindow(accType, waitNum, this, true);
                }
            }
            lastWaitDataBeanList = beanList;
        }
    }

    public void onAddWaiteCount(int waitNumber) {
        LogUtil.d("waitNumber", "waitNumber" + waitNumber);
        stopRefreshMenu();
        String s = tvWaiteCount.getText().toString();
        if (TextUtils.isEmpty(s)) {
            s = "0";
        }
        int parseInt = Integer.parseInt(s);
        parseInt += waitNumber;
        tvWaiteCount.setText(parseInt + "");
        tvWaiteCount.setVisibility(View.VISIBLE);
        BaseSwitchFragment indexFragment = afbDrawerViewHolder.getIndexFragment();
        if (indexFragment != null) {
            indexFragment.initWaitData();
        }
        startRefreshMenu();
    }


    public void runWayItem(MenuItemInfo item) {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(mContext), mContext);
        if (item.getRes() == R.mipmap.date_day_grey) {
            LogUtil.d("tvMatchType", item.getDay());
            tvMatchType.setText(item.getDay());
        } else {
            LogUtil.d("tvMatchType", item.getText());
            tvMatchType.setText(item.getText());
        }

        tvMatchType.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
        wd = item.getDateParam();
    }

    public void clickSortByTime(View view) {
        changeTimeSort();
    }


    @Override
    public String getOtType() {
        String ot = "Running";
        if (isHasAttached() && currentFragment != null && currentFragment.isAdded()) {
            String type = currentFragment.presenter.getStateHelper().getStateType().getType();
            if (!StringUtils.isNull(type))
                ot = type;
        }
        return ot;
    }


    public void closeTv(View view) {
        fl_top_video.setVisibility(View.GONE);
        this.onlyShowOne = false;
        liveMatchHelper.onStopPlay();
        liveMatchHelper.onDestroy();
        if (currentFragment.isVisible()) {
            ll_line1.setVisibility(View.VISIBLE);
            ll_line2.setVisibility(View.VISIBLE);
            ll_line3.setVisibility(View.VISIBLE);
            ll_header_sport.setVisibility(View.VISIBLE);
            llSportMenuBottom.setVisibility(View.VISIBLE);
            if ((currentFragment.getPresenter().getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
                BallAdapterHelper adapterHelper = (BallAdapterHelper) currentFragment.getPresenter().getStateHelper().getAdapterHelper();
                adapterHelper.setOnlyShowAdded(onlyShowOne);
                if (getBetContent() != null)
                    getBetContent().ll_bet_title.setVisibility(onlyShowOne ? View.GONE : View.VISIBLE);
            }
        }
    }

    public void clickRunMatchPlay(final IRTMatchInfo itemBall, int positionBall, boolean onlyOne) {
        if (itemBall != null && currentFragment.isVisible()) {
            if (!(currentFragment.presenter.getStateHelper().getStateType().getType().charAt(0) + "").toLowerCase().startsWith("r")) {
                closeTv(ll_header_sport);
                return;
            }
            if (!liveMatchHelper.checkLivePlayVisible(itemBall) && !liveMatchHelper.checkWebRtsVisible(itemBall)) {
                return;
            }
            if (itemBallAdded != null && itemBall.getSocOddsId().equals(itemBallAdded.getSocOddsId()) && onlyShowOne == onlyOne && fl_top_video.getVisibility() == View.VISIBLE)
                return;
            this.itemBallAdded = itemBall;
            this.positionBallAdded = positionBall;
            this.onlyShowOne = onlyOne;

            if (onlyShowOne) {
                tv_match_play.setText(R.string.All_Match);
                llSportMenuBottom.setVisibility(View.GONE);
            } else {
                tv_match_play.setText(R.string.Match);
                llSportMenuBottom.setVisibility(View.VISIBLE);
            }
            currentFragment.presenter.getStateHelper().getAdapterHelper().additionMap.put(true, "");
            currentFragment.clickItemAdd(fl_top_video, itemBall, positionBall);
            ll_line1.setVisibility(View.GONE);
            ll_line2.setVisibility(View.GONE);
            ll_line3.setVisibility(View.GONE);
            ll_header_sport.setVisibility(View.GONE);
            currentFragment.presenter.getStateHelper().getAdapterHelper().setOnlyShowAdded(onlyOne);
            if (getBetContent() != null)
                getBetContent().ll_bet_title.setVisibility(onlyShowOne ? View.GONE : View.VISIBLE);


            if (itemBall instanceof RunMatchInfo && !currentFragment.getBallDbid().equals(((RunMatchInfo) itemBall).getDbid())) {
                if (AfbUtils.getSportByDbid(((RunMatchInfo) itemBall).getDbid()) != null)
                    initSportFragment(AfbUtils.getSportByDbid(((RunMatchInfo) itemBall).getDbid()), itemBall);
            } else {
                liveMatchHelper.openRunMatch(itemBall);
                presenter.loadAllRunMatches(fl_top_video, handler, new MainPresenter.CallBack<List<RunMatchInfo>>() {
                    @Override
                    public void onBack(List<RunMatchInfo> data) throws JSONException {
                        if (itemBallAdded != null)
                            updateRunMatch(data, itemBallAdded);
                        popWindowRun = new BaseListPopupWindow<RunMatchInfo>(mContext, tv_run_match_title, tv_run_match_title.getWidth(), AfbUtils.dp2px(mContext, 200), tv_run_match_title) {
                            public int getItemLayoutRes() {
                                return R.layout.sport_paly_run_match_info;
                            }

                            @Override
                            public void onConvert(MyRecyclerViewHolder holder, int position, RunMatchInfo item) {
                                TextView tv = holder.getView(R.id.text_tv);
                                ImageView play_iv = holder.getView(R.id.play_iv);
                                ImageView ball_iv = holder.getView(R.id.ball_iv);
                                SportIdBean sportByDbid = AfbUtils.getSportByDbid(item.getDbid());
                                if (sportByDbid != null) {
                                    ball_iv.setImageResource(sportByDbid.getSportPic());
                                }
                                tv.setText(item.getHome() + " -vs- " + item.getAway());
                                if (item.getIsRun().equals("True") || item.getIsRun().equals("1")) {
                                    play_iv.setImageResource(R.mipmap.play_green);
                                } else {
                                    play_iv.setImageResource(R.mipmap.play_grey);
                                }

                            }

                            @Override
                            protected void clickItem(TextView tv, RunMatchInfo item) {
                                super.clickItem(tv, item);
                                closePopupWindow();
                                if (item.getIsRun().equals("True") || item.getIsRun().equals("1")) {
                                    tv.setText(item.getHome() + "  " + item.getHomeSocre() + " - " + item.getAwaySocre() + "  " + item.getAway());
                                    clickRunMatchPlay(item, positionBallAdded, onlyShowOne);
                                }
                            }
                        };
//                popWindow.setData(presenter.currencyList);
                        popWindowRun.setTrans(1f);
                        popWindowRun.setData(data);

                    }
                });
            }
        }
    }

    private void updateRunMatch(List<RunMatchInfo> data, IRTMatchInfo itemBallAdded) {
        for (RunMatchInfo runMatchInfo : data) {
            if (runMatchInfo.getSocOddsId().equals(itemBallAdded.getSocOddsId())) {
                this.itemBallAdded = runMatchInfo;
                String home = runMatchInfo.getHome();
                String away = runMatchInfo.getAway();
                String isRun = runMatchInfo.getIsRun();
                String homeSocre = runMatchInfo.getHomeSocre();
                String awaySocre = runMatchInfo.getAwaySocre();


                tv_run_match_title.setText(home + "  " + homeSocre + " - " + awaySocre + "  " + away);
            }
        }
    }


    @Override
    public void recreate() {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(mContext), mContext);
        super.recreate();
        super.recreate();

    }

    BetPop betPop;

    @Override
    public BetPop getBetContent() {
        if (betPop == null)
            betPop = new BetPop(mContext, bet_pop_parent_ll);
        return betPop;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        //你的代码
        super.onConfigurationChanged(newConfig);
        if (fl_top_video.getVisibility() == View.VISIBLE && currentFragment != null && currentFragment.isVisible()) {
            configurationChanged(newConfig);
        }
    }

    private void configurationChanged(Configuration newConfig) {
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fl_top_video.getLayoutParams();
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {//切换为横屏
            lp.height = DeviceUtils.getScreenPix(mContext).heightPixels;
            LogUtil.d("height", "ORIENTATION_LANDSCAPE  lp.height:" + lp.height);
            fl_top_video.setLayoutParams(lp);
            otherVisible(View.GONE);
        } else {
            lp.height = DeviceUtils.getScreenPix(mContext).widthPixels * 9 / 16;
            LogUtil.d("height", "ORIENTATION_PORTRAIT  lp.height:" + lp.height);
            fl_top_video.setLayoutParams(lp);
            otherVisible(View.VISIBLE);
        }
    }

    private void otherVisible(int visible) {
        ll_back.setVisibility(visible);
        viewHolder.tv_run_match_title.setVisibility(visible);
    }

    public void onBetSuccess(String betResult) {
        super.onBetSuccess(betResult);
        if (betPop != null)
            betPop.closePopupWindow();
    }

    public void clickEarly(View view) {
        if (currentFragment != null && currentFragment.isVisible()) {
            MenuItemInfo<Integer> itemEarly = new MenuItemInfo<Integer>(R.mipmap.date_early_grey,
                    R.string.Early_All
                    , "Early", R.mipmap.date_early_green, "", "7");
            currentFragment.presenter.getStateHelper().onTypeWayClick(itemEarly, 3);
            loadLeftMenuSports();
            games_switch_rg.check(R.id.sports_selected);
        }
    }

    public void clickToday(View view) {
        if (currentFragment != null && currentFragment.isVisible()) {
            MenuItemInfo<Integer> itemToday = new MenuItemInfo<>(R.mipmap.date_today_grey, (R.string.Today), "Today", R.mipmap.date_today_green);
            itemToday.setDateParam("");
            currentFragment.presenter.getStateHelper().onTypeWayClick(itemToday, 2);
            loadLeftMenuSports();
            games_switch_rg.check(R.id.sports_selected);

        }
    }

    public void clickTopLeft(View view) {
        clickTop();
        ll_home_left.setVisibility(View.GONE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ll_home_left.getVisibility() == View.VISIBLE) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:


                    int x1 = (int) ev.getX();
                    int y1 = (int) ev.getY();
                    int left = right_ll.getLeft();
                    int right = right_ll.getRight();

                    int topSport = llSportMenuBottom.getTop();

                    int[] location = new int[2];
                    iv_home_menu.getLocationOnScreen(location);
                    int x = location[0];
                    int y = location[1];
                    int leftIv = x;
                    int topIv = y;
                    int rightIv = iv_home_menu.getWidth() + x;
                    int bottomIv = iv_home_menu.getHeight() + y;
                    boolean contains = containsPoint(x1, y1, leftIv, rightIv, topIv, bottomIv);
                    if (!contains && (y1 > topSport || (left < x1 && x1 < right))) {
                        ll_home_left.setVisibility(View.GONE);
                        if (currentFragment.isVisible() && currentFragment.getPresenter().getStateHelper().baseRecyclerAdapter != null) {
                            currentFragment.getPresenter().getStateHelper().baseRecyclerAdapter.notifyDataSetChanged();
                        }
                    }

                    LogUtil.d("dispatchTouchEvent",
                            "x1:" + x1 +
                                    "y1:" + y1 +
                                    "leftIv:" + leftIv +
                                    "rightIv:" + rightIv +
                                    "topIv:" + topIv +
                                    "bottomIv:" + bottomIv
                    );

                    break;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private boolean containsPoint(int x1, int y1, int leftIv, int rightIv, int topIv, int bottomIv) {
        if (x1 > leftIv && x1 < rightIv && y1 > topIv && y1 < bottomIv)
            return true;
        return false;
    }


    public void clickPcType(View view) {
        presenter.goWebPc(AppConstant.getInstance().URL_PC);
    }

    public void onChangeMatchClick(View view) {
        clickRunMatchPlay(itemBallAdded, positionBallAdded, !onlyShowOne);
    }

    public void clickCLosePlay(View view) {
        closeTv(view);
    }

    public void clickRunTitle(final View view) {
        showRunMatchesPop();
    }

    BaseListPopupWindow<RunMatchInfo> popWindowRun;

    private void showRunMatchesPop() {
        if (popWindowRun != null)
            popWindowRun.showPopupDownWindow();
    }

    public void clickLanguage(View view) {
        BaseListPopupWindow<MenuItemInfo<String>> popWindow = new BaseListPopupWindow<MenuItemInfo<String>>(mContext, view, AfbUtils.dp2px(mContext, 200), AfbUtils.dp2px(mContext, 300), (TextView) view) {

            @Override
            public void onConvert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                TextView tv = holder.getView(com.unkonw.testapp.R.id.item_regist_text_tv);
                tv.setAllCaps(true);
                tv.setText(item.getText());
                tv.setGravity(Gravity.CENTER);

            }

            @Override
            protected void clickItem(TextView tv, MenuItemInfo<String> item) {
                super.clickItem(tv, item);
                closePopupWindow();
                tv.setText(item.getText());
                AfbUtils.switchLanguage(item.getType(), mContext);
                presenter.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
                    @Override
                    public void onBack(SettingAllDataBean data) throws JSONException {
                        onLanguageSwitchSucceed("");
                    }
                });
            }
        };
//                popWindow.setData(presenter.currencyList);
        popWindow.setTrans(1f);
        popWindow.setData(new LanguageHelper(mContext).getLanguageItems());
        popWindow.showPopupDownWindow();

    }
}
