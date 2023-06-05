package com.nanyang.app.main.home.sport.main;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.koushikdutta.async.http.WebSocket;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BR;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.MyGoHomeBroadcastReceiver;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.databinding.SportOthersContentBinding;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.EventShowBall;
import com.nanyang.app.main.home.huayThai.HuayThaiFragment;
import com.nanyang.app.main.home.sport.WebSocketManager;
import com.nanyang.app.main.home.sport.betOrder.IBetOrderView;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.live.LivePlayHelper;
import com.nanyang.app.main.home.sport.live.ViewHolder;
import com.nanyang.app.main.home.sport.model.RunMatchInfo;
import com.nanyang.app.main.home.sportInterface.IRTMatchInfo;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseApplication;
import com.unkonw.testapp.libs.base.OnViewClickListener;
import com.unkonw.testapp.libs.common.ActivityPageManager;
import com.unkonw.testapp.libs.utils.GZipUtil;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.MyFileUtils;
import com.unkonw.testapp.libs.utils.SharePreferenceUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.PopOneBtn;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import cn.finalteam.toolsfinal.DeviceUtils;
import cn.finalteam.toolsfinal.logger.Logger;
import kotlin.Unit;

public class SportActivity extends BaseToolbarActivity<MainPresenter> implements ILanguageView<String>, IBetOrderView {
    private final String GUIDE_KEY = "GUIDE";

    HuayThaiFragment huayThaiFragment = new HuayThaiFragment();
    BaseSportFragment localCurrentFragment;
    @BindView(R.id.web_wv)
    WebView webView;
    @BindView(R.id.iv_title_live_stream)
    ImageView tv_title_live_stream;
    @BindView(R.id.iv_title_live_center)
    ImageView tv_title_live_center;
    @BindView(R.id.ll_back_title_line)
    View ll_back;
    @BindView(R.id.iv_home_menu)
    View iv_home_menu;
    @BindView(R.id.tv_match_play)
    TextView tv_match_play;

    @BindView(R.id.cl_sport_head)
    public View cl_sport_head;
    @BindView(R.id.list_top)
    public View list_top;
    @BindView(R.id.img_top_click_left)
    public ImageView img_top_click_left;
    @BindView(R.id.img_top_click_right)
    public ImageView img_top_click_right;
    @BindView(R.id.ll_line1)
    public View ll_line1;
    @BindView(R.id.ll_line2)
    public View ll_line2;

    @BindView(R.id.right_ll)
    View right_ll;
    @BindView(R.id.drawer_more)
    DrawerLayout drawerLayout;

    @BindView(R.id.rc_sport_list)
    RecyclerView rc_sport_list;
    @BindView(R.id.rc_sport_list_top)
    RecyclerView rc_sport_list_top;
    @BindView(R.id.bet_pop_parent_ll)
    public View bet_pop_parent_ll;


    @BindView(R.id.fl_top_video)
    public View fl_top_video;
    @BindView(R.id.tv_toolbar_left)
    TextView tvToolbarLeft;
    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_toolbar_right1)
    TextView tvToolbarRight1;
    @BindView(R.id.tv_toolbar_right)
    TextView tvToolbarRight;

    @BindView(R.id.iv_home_back)
    ImageView ivHomeBack;
    @BindView(R.id.match_cup_iv)
    ImageView matchCupIv;

    @BindView(R.id.running_iv)
    ImageView runningIv;
    @BindView(R.id.iv_all_add)
    TextView ivAllAdd;
    @BindView(R.id.tv_odds_type)
    public TextView tvOddsType;
    @BindView(R.id.tv_league_major)
    TextView tvLeagueMain;
    @BindView(R.id.iv_sort_time)
    ImageView ivSortTime;
    @BindView(R.id.fl_main)
    public FrameLayout flMain;
    @BindView(R.id.fl_main_content)
    public FrameLayout flContent;
    @BindView(R.id.tv_record)
    TextView tvRecord;

    @BindView(R.id.iv_order_top)
    ImageView ivOrderTop;
    @BindView(R.id.tv_mix)
    TextView tvMix;
    @BindView(R.id.tv_order_count_top)
    TextView tvOrderCount;
    @BindView(R.id.tv_mix_count)
    TextView tvMixCount;

    @BindView(R.id.mix_count)
    TextView MixCount;

    @BindView(R.id.tv_way_run)
    public TextView tvMatchType;
    @BindView(R.id.tv_menu)
    TextView tvMenu;
    @BindView(R.id.ll_sport_menu_bottom)
    public
    LinearLayout llSportMenuBottom;
    /*    @BindView(R.id.fl_top)
        FrameLayout fl_top;*/
    @BindView(R.id.sports_language_tv)
    ImageView sports_language_tv;
    @BindView(R.id.sports_selected_tv)
    TextView getTvSportSelect;
    @BindView(R.id.tv_balance)
    TextView tvBalance;
    @BindView(R.id.tv_waite_count)
    public TextView tvWaiteCount;


    @BindView(R.id.ll_header_sport)
    public LinearLayout ll_header_sport;
    @BindView(R.id.ll_footer_sport)
    public LinearLayout ll_footer_sport;

    @BindView(R.id.match_collection_num_tv)
    public TextView collectionNumTv;
    @BindView(R.id.ll_home_left)
    View ll_home_left;
    @BindView(R.id.match_collection_fl)
    ImageView collectionFl;

    @BindView(R.id.sports_selected)
    RadioButton sports_selected;
    @BindView(R.id.others_selected)
    RadioButton others_selected;
    @BindView(R.id.games_switch_rg)
    RadioGroup games_switch_rg;

    @BindView(R.id.tv_early)
    TextView tvLeftMenuEarly;
    @BindView(R.id.tv_today)
    TextView tvLeftMenuToday;

    public Map<String, Map<String, Boolean>> localCollectionMap = new HashMap<>();


    public MenuItemInfo<String> item;
    private String currentGameType = "";

    public AfbDrawerViewHolder afbDrawerViewHolder;
    private SportIdBean currentIdBean;
    private boolean notClickType = false;
    public LivePlayHelper liveMatchHelper;
    public IRTMatchInfo itemBallAdded;
    private int positionBallAdded;
    public boolean onlyShowOne;
    private boolean isPlay;
    public IRTMatchInfo itemBall;
    private List<SportIdBean> listSport;
    private volatile boolean isOther;
    private int matchRes;
    public int sportIdText;
    public boolean hasBet;


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
    private static final String HAS_BET = "HAS_BET";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            //销毁时不保存fragment的状态
            outState.remove(BUNDLE_FRAGMENTS_KEY);

        }
    }

    @BindView(R.id.tv_top_right_oval)
    TextView tvTopRightOval;

    public void onCreate(Bundle savedInstanceState) {
        hasBet = false;
        if (savedInstanceState != null) {
            //重建时清除 fragment的状态
            savedInstanceState.remove(BUNDLE_FRAGMENTS_KEY);
            hasBet= SharePreferenceUtil.getBoolean(mContext,HAS_BET);
        }
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_sport);
        toolbar.setVisibility(View.GONE);
        myGoHomeBroadcastReceiver = new MyGoHomeBroadcastReceiver(getApp());
        registerReceiver(myGoHomeBroadcastReceiver, new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS));
//        presenter.getStateHelper().switchOddsType(item.getType());
        updateMixOrderCount();
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            tvRecord.setTextColor(Color.WHITE);
            tvMix.setTextColor(Color.WHITE);
            tvMenu.setTextColor(Color.WHITE);
            tvMatchType.setTextColor(Color.WHITE);
            list_top.setVisibility(View.GONE);
        } else if (BuildConfig.FLAVOR.equals("usun")) {
//            fl_top.setBackgroundResource(R.drawable.green_black_shadow_bottom);
            img_top_click_left.setBackgroundResource(R.drawable.green_black_shadow_bottom);
            img_top_click_right.setBackgroundResource(R.drawable.green_black_shadow_bottom);
            ll_line1.setBackgroundColor(Color.parseColor("#7C2600"));
            tvBalance.setTextColor(Color.parseColor("#E0D620"));
            llSportMenuBottom.setBackgroundResource(R.drawable.usun_bottom_bg);
        }
        img_top_click_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rc_sport_list_top.scrollBy(-80, 0);
            }
        });
        img_top_click_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rc_sport_list_top.scrollBy(80, 0);
            }
        });

    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type = "";
    AfbApplication app;
    public ViewHolder videoHolder;
    MyGestureDetector gestureDetector;


    class MyGestureDetector extends GestureDetector {
        public boolean isIgnore() {
            return ignore;
        }

        public void setIgnore(boolean ignore) {
            this.ignore = ignore;
        }

        boolean ignore = false;

        public MyGestureDetector(Context context, OnGestureListener listener) {
            super(context, listener);
        }
    }

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
            int bottomRes = R.mipmap.date_running_white;
            dateClickPosition = 1;
            String day = "", dateParam = "";
            switch (type) {
                case "Early":
                    res = R.mipmap.date_early_grey;
                    dateClickPosition = 2;
                    dateParam = "7";
                    bottomRes = R.mipmap.date_early_white;
                    break;
                case "Today":
                    dateClickPosition = 1;
                    res = R.mipmap.date_today_grey;
                    bottomRes = R.mipmap.date_today_white;
                    break;
            }
            MenuItemInfo<Integer> integerMenuItemInfo = new MenuItemInfo<>(res, item.getText(), type, res, day, dateParam);
            integerMenuItemInfo.bottomRes = bottomRes;
            runWayItem(integerMenuItemInfo);

        }
        videoHolder = new ViewHolder(fl_top_video);
        liveMatchHelper = new LivePlayHelper(videoHolder, this);

        initLeftMenu();
        right_ll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.d("MotionEvent", v.getId());
                ll_home_left.setVisibility(View.GONE);
                return false;
            }
        });
        gestureDetector = new MyGestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if (!gestureDetector.isIgnore())
                    ll_home_left.setVisibility(View.GONE);
                return super.onSingleTapUp(e);
            }

        });

        initOtherContent();
    }

    public void clickOthers(View view) {
        viewModel.loadOtherType(3);
    }

    public void clickSlots(View view) {
        viewModel.loadOtherType(2);
    }

    public void clickCasino(View view) {
        viewModel.loadOtherType(1);
    }

    SportOtherViewModel viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(BaseApplication.getInstance()).create(SportOtherViewModel.class);

    private void initOtherContent() {
//        mBinding = DataBindingUtil.inflate(inflater,layoutId,null,false )

        SportOthersContentBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(this), R.layout.sport_others_content, null, false);
        inflate.setVariable(BR.viewModel, viewModel);
        inflate.setVariable(BR.viewClick, new OnViewClickListener() {
            @Override
            public void onClick(@NotNull View v) {
                viewModel.getShowContent().setValue(false);
            }
        });
        viewModel.setOnItemClicked(item -> {
            String g = item.getG();
            if (g.equals("COCK FIGHT") || g.equals("KENO") || g.equals("LOTTERY")) {
                g = "1";
            }
            if (getApp().updateOtherMap().containsKey(g)) {
                (getBaseActivity().presenter).clickGdGameItem(g);
            }

            return Unit.INSTANCE;
        });

        flMain.addView(inflate.getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        viewModel.loadOtherType(0);
        viewModel.getShowContent().observe(this,aBoolean -> {
            System.out.println("getShowContent.status:" + aBoolean);
            if(aBoolean){
                inflate.getRoot().setVisibility(View.VISIBLE);
            }else{
                inflate.getRoot().setVisibility(View.GONE);
            }
        });
    }

    BaseRecyclerAdapter<SportIdBean> leftSportAdapter;
    BaseRecyclerAdapter<SportIdBean> topSportAdapter;

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
                String num = numMap.get(item.getKey());

                if (StringUtils.isNull(num) || Integer.parseInt(num) < 1) {
                    tvGameCount.setVisibility(View.INVISIBLE);
                } else {
                    tvGameCount.setText(num);
                    tvGameCount.setVisibility(View.VISIBLE);

                }
                if (type.equals("Running") && item.getTextRes() == R.string.Soccer_Runing) {
                    tvGameName.setTextColor(ContextCompat.getColor(mContext, R.color.google_green));
                    llContent.setBackgroundColor(ContextCompat.getColor(mContext, R.color.gary1));
                }
            }
        };
        topSportAdapter = new BaseRecyclerAdapter<SportIdBean>(mContext, new ArrayList<SportIdBean>(), R.layout.item_sport_top) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, final SportIdBean item) {
                TextView tvGameName = holder.getView(R.id.tv_game_name);
                ImageView img_game_pic = holder.getView(R.id.img_game_pic);
                tvGameName.setText(item.getTextRes());
                img_game_pic.setImageResource(item.picSmall);
                ViewGroup.LayoutParams layoutParamsTv = tvGameName.getLayoutParams();
                layoutParamsTv.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                tvGameName.setLayoutParams(layoutParamsTv);
                LinearLayout ll_content = holder.getView(R.id.ll_content);
                ViewGroup.LayoutParams layoutParams = ll_content.getLayoutParams();
                layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
                ll_content.setLayoutParams(layoutParams);
            }
        };
        rc_sport_list.setAdapter(leftSportAdapter);
        rc_sport_list_top.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        rc_sport_list_top.setAdapter(topSportAdapter);
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
        topSportAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SportIdBean>() {
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
        Iterator<SportIdBean> iterator = getApp().othersMap.values().iterator();
        final List<SportIdBean> listOther = new ArrayList<>();
        while (iterator.hasNext()) {
            SportIdBean next = iterator.next();
            String tem = next.getId();

            String Id = AfbUtils.changeId(tem);
            if (app.H5MainChoose.length() <= 1 || Arrays.asList(app.H5MainChoose.split(",")).contains(Id))
                listOther.add(next);
       /*     String enable = enableMap.get(next.getId());
            if (enable != null && enable.equals("True")) {
                listOther.add(next);
            }*/
        }
        isOther = true;
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
                if (!isHasAttached())
                    return;
                if (StringUtils.isNull(s))
                    return;
                if (s.equals("3"))
                    return;
                if (currentFragment == null || !currentFragment.isVisible())
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
//            tvOrderCount.setVisibility(View.VISIBLE);
            ivOrderTop.setImageResource(R.mipmap.sport_shopping_top_white);
            tvMixCount.setText("" + getApp().getMixBetList().size());
            MixCount.setText("" + getApp().getMixBetList().size());
            tvOrderCount.setText("" + getApp().getMixBetList().size());
            MixCount.setVisibility(View.VISIBLE);
        } else {
            ivOrderTop.setImageResource(R.mipmap.sport_bottom_teb_shopping_white);
            tvMixCount.setVisibility(View.GONE);
            tvOrderCount.setVisibility(View.GONE);
            tvMixCount.setText("0");
            MixCount.setText("0");
            MixCount.setVisibility(View.GONE);
            tvOrderCount.setText("0");
            if (betPop != null && betPop.v.getVisibility() == View.VISIBLE)
                betPop.closePopupWindow();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        betPop.closePopupWindow();
        BetGoalWindowUtils.clear();
        liveMatchHelper.onDestroy();
        unregisterReceiver(myGoHomeBroadcastReceiver);
        EventBus.getDefault().unregister(this);

    }

    private void initFragment(String parentType) {
        SportIdBean sportIdBean = getApp().getSportByType(parentType);
        if (sportIdBean == null)
            sportIdBean = getApp().getSportByG("1");
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
            currentFragment.refreshType();
        }
        changeFragmentInit(tag, localCurrentFragment);
//        sportTitleTv.setText(getString(R.string.sport_match) + " > " + currentTag);
    }

    private void changeFragmentInit(String tag, BaseSportFragment localCurrentFragment) {
        afbDrawerViewHolder.initDefaultFragment(localCurrentFragment);
        deleteHeadAndFoot();
        getTvSportSelect.setText(tag);
        afbDrawerViewHolder.switchFragment(localCurrentFragment);
        if (currentFragment != localCurrentFragment) {
            currentFragment = localCurrentFragment;
        } else {
            currentFragment.refreshType();
        }
        currentTag = tag;
    }

    public void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment, String type) {

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

        tvBalance.setText(getApp().getUser().getCurCode2().replace("MYR", getString(R.string.MYR)) + ": " + s);
    }


    @Override
    public void onBackCLick(View v) {
        afbDrawerViewHolder.isBack(false);
    }

    @Override
    public void finish() {
        super.finish();
        if (AppConstant.IS_AGENT) {
            ActivityPageManager.getInstance().finishAllActivity();
        }
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


    public void clickCup(View view) {
        viewModel.getShowContent().setValue(false);
        currentFragment.presenter.getStateHelper().createChoosePop(presenter.mApiWrapper, view);
    }


    public void clickCollectionStar(View view) {
        viewModel.getShowContent().setValue(false);
        currentFragment.collection((ImageView) view);
    }

    public void clickBets(View view) {
        viewModel.getShowContent().setValue(false);
        afbDrawerViewHolder.goRecord();
    }


    @Override
    protected void onResume() {
        super.onResume();
        refreshAllText();
        if (matchRes != 0 && AppConstant.IS_AGENT) {
            tvMatchType.setText(matchRes);
        }
        if (AppConstant.IS_AGENT) {
            others_selected.setVisibility(View.GONE);
        } else {
            others_selected.setVisibility(View.VISIBLE);
        }
        if (!isOther)
            loadLeftMenuSports();
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
        if (getBetContent().v.getVisibility() == View.VISIBLE) {
            getBetContent().updateOdds(0);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(EventShowBall event) {

        if (isOther = false) {
            if (AppConstant.IS_AGENT || getApp().getShowBall() == 1) {
                leftSportAdapter.addAllAndClear(listSport);
            } else {
                leftSportAdapter.clearItems(true);
            }
        }
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (liveMatchHelper != null)
            liveMatchHelper.onStopPlay();

        getBetContent().stopUpdateOdds();
        stopRefreshMenu();
        WebSocketManager.getInstance().stopUpdateData();
    }

    Map<String, String> numMap = new HashMap<>();

    public void clickLeftMenu(final View view) {
        viewModel.getShowContent().setValue(false);
        boolean hasGone = ll_home_left.getVisibility() == View.GONE;
        if (hasGone) {
            int checkedRadioButtonId = games_switch_rg.getCheckedRadioButtonId();
            changeSportRadioButton(checkedRadioButtonId);
        }
        ll_home_left.setVisibility(hasGone ? View.VISIBLE : View.GONE);
    }

    private void loadLeftMenuSports() {
        LogUtil.d("type", "--->>" + type);
        isOther = false;
        if (listSport != null) {
            if (AppConstant.IS_AGENT || getApp().getShowBall() == 1) {
                leftSportAdapter.addAllAndClear(listSport);
            }

            topSportAdapter.addAllAndClear(listSport);
        }

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

                    Iterator<SportIdBean> iterator = getApp().sportMap.values().iterator();
                    while (iterator.hasNext()) {
                        SportIdBean next = iterator.next();

                        String num = jsonObjectNum.optString(typeStr + next.getKey());
                        if (next.getTextRes() == R.string.Soccer_Runing || next.getTextRes() == R.string.all_running) {
                            num = jsonObjectNum.optString("M_RAm" + next.getDbid());
                        }

                        numMap.put(next.getKey(), num);
                        if (next.getId().equals("1,9,21,29,51,182") || (next.getId().equals("1") && next.getTextRes() == R.string.Euro_2020) || (next.getId().equals("1") && next.getTextRes() == R.string.Soccer_Runing) || (!StringUtils.isNull(num) && Integer.valueOf(num) > 0)) {
                            if (type.equals("Running") && next.getTextRes() == R.string.Soccer) {

                            } else {
                                listSport.add(next);
                            }
                        }

                    }
              /*    Iterator<SportIdBean> iterator1 = AfbUtils.othersMap.values().iterator();
                    while (iterator1.hasNext()) {
                        SportIdBean next = iterator1.next();
                        if (!StringUtils.isNull(jsonObjectNum.optString(typeStr + next.getDbid())))
                            listSport.add(next);
                    }*/
                    SportActivity.this.listSport = listSport;
                    if (!isOther) {
                        leftSportAdapter.clearItems(false);
                        if (AppConstant.IS_AGENT || getApp().getShowBall() == 1) {
                            leftSportAdapter.addAllAndClear(listSport);
                        }
                        topSportAdapter.addAllAndClear(listSport);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickAllRunning(View view) {
        viewModel.getShowContent().setValue(false);
        setType("Running");
        dateClickPosition = 1;
        MenuItemInfo<Integer> item = new MenuItemInfo<Integer>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green);
        item.bottomRes = R.mipmap.date_running_white;
        runWayItem(item);
        SportIdBean sportIdBean = getApp().sportMap.get("1,9,21,29,51,182");
        sportIdText = sportIdBean.getTextRes();
        selectFragmentTag(getString(sportIdBean.getTextRes()), sportIdBean.getBaseFragment());
    }

    private void initSportFragment(SportIdBean item, IRTMatchInfo itemBall) {
        notClickType = false;

        if (!item.getDbid().startsWith("33"))
            currentIdBean = item;
        MenuItemInfo<Integer> running = new MenuItemInfo<>(R.mipmap.date_running_green, (R.string.running), "Running", R.mipmap.date_running_green);
        running.bottomRes = R.mipmap.date_running_white;

        if (item.getTextRes() == R.string.Soccer_Runing) {
            sportIdText = item.getTextRes();
            LogUtil.d("Soccer_Runing", "点击1");
            setType("Running");
            dateClickPosition = 1;

            runWayItem(running);
            selectFragmentTag(getString(R.string.Soccer), item.getBaseFragment(), "Running");

            return;
        } else if (item.getDbid().equals("1") && item.getTextRes() == R.string.Euro_2020) {
            if (StringUtils.isNull(numMap.get(item.getKey())) || Integer.parseInt(Objects.requireNonNull(numMap.get(item.getKey()))) == 0) {
                setType("Early");
                MenuItemInfo<Integer> early = new MenuItemInfo<>(R.mipmap.date_early_green, (R.string.Early), "Early", R.mipmap.date_early_green);
                early.bottomRes = R.mipmap.date_early_white;
                dateClickPosition = 3;
                runWayItem(early);
            }
            sportIdText = item.getTextRes();
            selectFragmentTag(getString(R.string.Euro_2020), item.getBaseFragment(), type);
            return;

        } else if (item.getDbid().equals("1") && item.getTextRes() == R.string.Soccer_Runing) {
            LogUtil.d("Soccer_Runing", "点击2");
            sportIdText = item.getTextRes();
            setType("Running");
            dateClickPosition = 1;
            runWayItem(running);

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
        sportIdText = item.getTextRes();
        selectFragmentTag(getString(item.getTextRes()), item.getBaseFragment());
        if (itemBall != null) {
            this.itemBall = itemBall;
        }
    }

    public void clickSportWayRun(final View view) {
        viewModel.getShowContent().setValue(false);
        if (currentFragment != null && currentFragment instanceof BaseAllFragment || notClickType) {
            return;
        }
        createPopupWindow(typePop);
        typePop.showPopupWindowUpCenter(view, AfbUtils.dp2px(mContext, 356 + 15), AfbUtils.getScreenWidth(mContext) / 2);
        loadTypeData();
    }

    public void loadTypeData() {
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), AppConstant.wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    typePop.updateNumber(jsonObjectNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });
        if (!isOther)
            loadLeftMenuSports();
    }

    TypePop typePop;

    public void initMatchTypePop() {
        typePop = new TypePop(mContext, tvMatchType, AfbUtils.getScreenWidth(mContext) / 2, AfbUtils.dp2px(mContext, 356));
    }

    class TypePop extends BasePopupWindow {

        public TypePop(Context context, View v, int width, int height) {
            super(context, v, width, height);

        }

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
            rv_list.setLayoutManager(new LinearLayoutManager(mContext));
            rv_list.setAdapter(currentFragment.presenter.getStateHelper().getSwitchTypeAdapter());
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

        public void updateNumber(JSONObject jsonObjectNum) {
            currentFragment.presenter.getStateHelper().switchTypeAdapter(jsonObjectNum);
        }
    }


    private void changeTimeSort() {
        getApp().setSort(1 - getApp().getSort());
        currentFragment.getPresenter().getStateHelper().refresh();
        ivSortTime.setImageResource(getApp().getSort() == 0 ? R.mipmap.sport_game_clock_white : R.mipmap.sport_game_clock_exit_white);
    }

    public int dateClickPosition = 1;


    public void clickMoreMenu(View view) {
        viewModel.getShowContent().setValue(false);
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    public void clickOrder(View view) {
        viewModel.getShowContent().setValue(false);
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

    public void refreshAllText() {
        rememberLastOdds();
        TextView tv_pc_type = findViewById(R.id.tv_pc_type);
        tv_pc_type.setText(R.string.view2);
        tvLeagueMain.setText(R.string.Five_Major_Match);
        tvRecord.setText(R.string.TabMyBets);
        tvMix.setText(R.string.bet_slip);
        getTvSportSelect.setText(sportIdText);
        tvMenu.setText(R.string.menu);
    }

    public void clickTop(View view) {
        clickTop();
    }

    public void clickTop() {
        viewModel.getShowContent().setValue(false);
        currentFragment.checkTop(tvLeagueMain, tvTopRightOval);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return afbDrawerViewHolder.onKeyDown(keyCode, event);
    }

    public void clickOrderTop(View view) {
        currentFragment.clickOrder();
    }

    public void startRefreshMenu() {
        stopRefreshMenu();
        super.startRefreshMenu();
    }


    public void onGetRefreshMenu(List<String> beanList) {
        this.waitSize = beanList.size();
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
            matchRes = R.string.Early_All;
            tvMatchType.setText(item.getType() + " " + item.getDay());
        } else {
            LogUtil.d("tvMatchType", item.getText());
            matchRes = item.getText();
            tvMatchType.setText(item.getText());
        }
        setLeftMenuTypeState(item);
        tvMatchType.setCompoundDrawablesWithIntrinsicBounds(0, item.bottomRes, 0, 0);
        wd = item.getDateParam();
    }

    private void setLeftMenuTypeState(MenuItemInfo item) {
        String type = item.getType();
        if (type.equals("Early")) {
            tvLeftMenuEarly.setTextColor(ContextCompat.getColor(mContext, R.color.sport_text_menu));
            tvLeftMenuToday.setTextColor(Color.WHITE);
        } else if (type.equals("Today")) {
            tvLeftMenuEarly.setTextColor(Color.WHITE);
            tvLeftMenuToday.setTextColor(ContextCompat.getColor(mContext, R.color.sport_text_menu));
        } else {
            tvLeftMenuEarly.setTextColor(Color.WHITE);
            tvLeftMenuToday.setTextColor(Color.WHITE);
        }
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
        closeTv();
        resumeCurrent();
        currentFragment.presenter.getStateHelper().getAdapterHelper().additionMap.put(true, "");
        currentFragment.presenter.getStateHelper().getAdapterHelper().getBaseRecyclerAdapter().notifyDataSetChanged();
    }

    public void closeTv() {
        fl_top_video.setVisibility(View.GONE);
        webView.loadUrl("");
        webView.onPause();
        liveMatchHelper.onStopPlay();

    }

    public void resumeCurrent() {
        if (currentFragment.isVisible()) {
            cl_sport_head.setVisibility(View.VISIBLE);
            if (!BuildConfig.FLAVOR.equals("ez2888")) {
                list_top.setVisibility(View.VISIBLE);
            }
            ll_line1.setVisibility(View.VISIBLE);
            ll_line2.setVisibility(View.VISIBLE);

            ll_header_sport.setVisibility(View.VISIBLE);
            llSportMenuBottom.setVisibility(View.VISIBLE);
            onlyShowOne = false;
        }
    }


    public void clickRunMatchPlay(final IRTMatchInfo itemBall, int positionBall, boolean onlyOne) {
        if (itemBall != null && currentFragment.isVisible()) {
            if (onlyOne && !hasBet) {
                PopOneBtn popOneBtn = new PopOneBtn(this, tv_match_play) {
                    @Override
                    protected void initView(@NotNull View view) {
                        super.initView(view);
                        chooseMessage.setText(R.string.placing_a_bet);
                    }

                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_base_one_btn;

                    }

                    @Override
                    protected void clickSure(@Nullable View v) {
                        super.clickSure(v);
                        clickCLosePlay(v);
                    }
                };
                popOneBtn.showPopupCenterWindow();

                return;
            }
            if (!(currentFragment.presenter.getStateHelper().getStateType().getType().charAt(0) + "").toLowerCase().startsWith("r")) {
//                closeTv(ll_header_sport);
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
            list_top.setVisibility(View.GONE);
            collectionNumTv.setVisibility(View.GONE);
            ll_header_sport.setVisibility(View.GONE);
            LogUtil.d("xiao",
                    "onlyShowOne:" + onlyShowOne +
                            ",fl_top_video:" + (fl_top_video.getVisibility() == View.VISIBLE));
            if (itemBall instanceof RunMatchInfo && !currentFragment.getBallDbid().equals(((RunMatchInfo) itemBall).getDbid())) {
                if (getApp().getSportByDbid(((RunMatchInfo) itemBall).getDbid()) != null)
                    initSportFragment(getApp().getSportByDbid(((RunMatchInfo) itemBall).getDbid()), itemBall);
            } else {
                liveMatchHelper.openRunMatch(itemBall, presenter);
                presenter.loadAllRunMatches(fl_top_video, handler, new MainPresenter.CallBack<List<RunMatchInfo>>() {
                    @Override
                    public void onBack(List<RunMatchInfo> data) throws JSONException {
                        if (itemBallAdded != null)
                            updateRunMatch(data, itemBallAdded);
                        popWindowRun = new BaseListPopupWindow<RunMatchInfo>(mContext, videoHolder.fl_run_match_title, videoHolder.fl_run_match_title.getWidth(), AfbUtils.dp2px(mContext, 200), videoHolder.tv_run_match_home) {
                            public int getItemLayoutRes() {
                                return R.layout.sport_paly_run_match_info;
                            }

                            @Override
                            public void onConvert(MyRecyclerViewHolder holder, int position, RunMatchInfo item) {
                                TextView tv = holder.getView(R.id.text_tv);
                                ImageView play_iv = holder.getView(R.id.play_iv);
                                ImageView ball_iv = holder.getView(R.id.ball_iv);
                                SportIdBean sportByDbid = getApp().getSportByDbid(item.getDbid());
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
                                    videoHolder.tv_run_match_home.setText(item.getHome());
                                    videoHolder.tv_run_match_away.setText(item.getAway());
                                    videoHolder.tv_run_match_home_score.setText(item.getRunHomeScore());
                                    videoHolder.tv_run_match_away_score.setText(item.getRunAwayScore());
                                    clickRunMatchPlay(item, positionBallAdded, onlyShowOne);
                                }
                            }
                        };
//                        popWindow.setData(presenter.currencyList);
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
                String homeSocre = runMatchInfo.getRunHomeScore();
                String awaySocre = runMatchInfo.getRunAwayScore();
                videoHolder.tv_run_match_home.setText(home);
                videoHolder.tv_run_match_away.setText(away);
                videoHolder.tv_run_match_away_score.setText(awaySocre);
                videoHolder.tv_run_match_home_score.setText(homeSocre);
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
            lp.height = DeviceUtils.getScreenPix(mContext).widthPixels * 41 / 64;
            LogUtil.d("height", "ORIENTATION_PORTRAIT  lp.height:" + lp.height);
            fl_top_video.setLayoutParams(lp);
            otherVisible(View.VISIBLE);
        }
    }

    private void otherVisible(int visible) {
        ll_back.setVisibility(visible);
        videoHolder.fl_run_match_title.setVisibility(visible);
    }

    public void onBetSuccess(String betResult) {
        hasBet = true;
        super.onBetSuccess(betResult);
        if (betPop != null)
            betPop.closePopupWindow();
    }

    public void clickEarly(View view) {
        if (currentFragment != null && currentFragment.isVisible()) {
            MenuItemInfo<Integer> itemEarly = new MenuItemInfo<Integer>(R.mipmap.date_early_grey,
                    R.string.Early_All
                    , "Early", R.mipmap.date_early_green, "", "7");
            itemEarly.bottomRes = R.mipmap.date_early_white;
            currentFragment.presenter.getStateHelper().onTypeWayClick(itemEarly, 3);
            loadLeftMenuSports();
            games_switch_rg.check(R.id.sports_selected);
        }
    }

    public void clickToday(View view) {
        if (currentFragment != null && currentFragment.isVisible()) {
            MenuItemInfo<Integer> itemToday = new MenuItemInfo<>(R.mipmap.date_today_grey, (R.string.Today), "Today", R.mipmap.date_today_green);
            itemToday.bottomRes = R.mipmap.date_today_white;
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
            if (presenter.isTouchInView(ev, llSportMenuBottom) || (presenter.isTouchInView(ev, right_ll) && !presenter.isTouchInView(ev, iv_home_menu)))
                gestureDetector.onTouchEvent(ev);
        }

        return super.dispatchTouchEvent(ev);
    }


    public void clickPcType(View view) {
        viewModel.getShowContent().setValue(false);
        presenter.goWebPc(BuildConfig.PC_URL);
    }

    public void onChangeMatchClick(View view) {

        liveMatchHelper.refreshPlay();
/*        viewModel.getShowContent().setValue(false);
        onlyShowOne = !onlyShowOne;
        if (!onlyShowOne)
            closeTv(view);
        else
            clickRunMatchPlay(itemBallAdded, positionBallAdded, true);*/

    }

    public void clickCLosePlay(View view) {
        closeTv(view);
    }

    public void clickRunTitle(final View view) {
        viewModel.getShowContent().setValue(false);
        showRunMatchesPop();
    }

    BaseListPopupWindow<RunMatchInfo> popWindowRun;

    private void showRunMatchesPop() {
        if (popWindowRun != null)
            popWindowRun.showPopupDownWindow();
    }

    public void clickLanguage(View view) {
        BaseListPopupWindow<MenuItemInfo<String>> popWindow = new BaseListPopupWindow<MenuItemInfo<String>>(mContext, view, AfbUtils.dp2px(mContext, 200), AfbUtils.dp2px(mContext, 300)) {

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
                SharePreferenceUtil.setValue(mContext.getApplicationContext(),HAS_BET,hasBet);
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


    public void clickAllContracted(View view) {
        viewModel.getShowContent().setValue(false);
        showLoadingDialog();
        boolean contracted = currentFragment.getPresenter().getStateHelper().getAdapterHelper().clickAllContracted();

        if (contracted) {
            ((ImageView) view).setImageResource(R.mipmap.delete_all_yellow);
        } else {
            ((ImageView) view).setImageResource(R.mipmap.add_all_yellow);
        }
        currentFragment.rvContent.post(new Runnable() {
            @Override
            public void run() {
                hideLoadingDialog();
            }
        });

    }
}
