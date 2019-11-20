package com.nanyang.app.main.home.sport.main;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
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
import com.nanyang.app.Utils.BetGoalWindowUtils;
import com.nanyang.app.Utils.MyGoHomeBroadcastReceiver;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.IGetRefreshMenu;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.nanyang.app.main.home.huayThai.HuayThaiFragment;
import com.nanyang.app.main.home.sport.WebSocketManager;
import com.nanyang.app.main.home.sport.allRunning.AllRunningFragment;
import com.nanyang.app.main.home.sport.dialog.BetPop;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.GZipUtil;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.MyFileUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.logger.Logger;

public class SportActivity extends BaseToolbarActivity<MainPresenter> implements ILanguageView<String>, IGetRefreshMenu {
    private final String GUIDE_KEY = "GUIDE";

    HuayThaiFragment huayThaiFragment = new HuayThaiFragment();
    BaseSportFragment localCurrentFragment;
    @Bind(R.id.drawer_more)
    DrawerLayout drawerLayout;

    @Bind(R.id.sport_header_ll)
    public
    View sportHeaderLl;
    @Bind(R.id.tv_toolbar_left)
    TextView tvToolbarLeft;
    @Bind(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @Bind(R.id.tv_toolbar_right1)
    TextView tvToolbarRight1;
    @Bind(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
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
    @Bind(R.id.match_collection_fl)
    View collectionFl;

    @Bind(R.id.main_more)
    RecyclerView reContent;


    public MenuItemInfo<String> item;
    private String currentGameType = "";

    private AfbDrawerViewHolder afbDrawerViewHolder;
    private SportIdBean currentIdBean;
    private boolean notClickType = false;
    private boolean stopCloseWindow;


    public TextView getIvAllAdd() {
        return ivAllAdd;
    }

    private String currentTag = "";
    private HashMap<String, BaseSportFragment> mapFragment;
    public BaseSportFragment currentFragment;
    private Handler handler = new Handler();
    MyGoHomeBroadcastReceiver myGoHomeBroadcastReceiver;
    public String wd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLanguage();
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
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
        if (getApp().getOddsType() != null)
            presenter.switchOddsType(getApp().getOddsType().getType());
        else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    presenter.switchOddsType(getApp().getOddsType().getType());
                }
            }, 200);
        }
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
            dateClickPosition = 0;
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

        }

        tvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afbDrawerViewHolder.goRecord();
            }
        });

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
        updateMixOrderCount();
        SportPresenter sportPresenter = currentFragment.presenter;
        if ((sportPresenter.getStateHelper()).getAdapterHelper() instanceof BallAdapterHelper) {
            BallAdapterHelper adapterHelper = (BallAdapterHelper) (sportPresenter.getStateHelper()).getAdapterHelper();
            adapterHelper.getBaseRecyclerAdapter().notifyDataSetChanged();
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
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        BetGoalWindowUtils.clear();

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
        initSportFragment(sportIdBean);

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
        afbDrawerViewHolder.initDefaultFragment(localCurrentFragment);
        deleteHeadAndFoot();
        sportTitleTv.setText(getString(R.string.sport_match) + " > " + tag);
        afbDrawerViewHolder.switchFragment(localCurrentFragment);
        currentFragment = localCurrentFragment;
        currentTag = tag;
        tvSportSelect.setText(tag);
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

//    public void loginGD() {
//        if (ApkUtils.isAvilible(this, "gaming178.com.baccaratgame")) {
//            presenter.skipGd88(new BaseView<SportActivity, String>(this) {
//                @Override
//                public void onGetData(String data) {
//                    SportActivity.this.onGetData(data);
//                }
//            });
//        } else {
//            Intent intent = new Intent();
//            intent.setAction("android.intent.action.VIEW");
//            Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
//            intent.setData(content_url);
//            startActivity(intent);
//        }
//    }


    @Override
    public void onBackCLick(View v) {
        afbDrawerViewHolder.isBack(false);
        tvSportSelect.setText(currentIdBean.getTextRes());
        tvSportSelect.setCompoundDrawablesWithIntrinsicBounds(0, currentIdBean.getSportPic(), 0, 0);
    }


    @Override
    public void onGetData(final String data) {
//        presenter.getTransferMoneyData(new BaseConsumer<TransferMoneyBean>(this) {
//            @Override
//            protected void onBaseGetData(TransferMoneyBean transferMoneyBean) {
//                getMoneyMsg(transferMoneyBean, data);
//            }
//        });
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


    private void startApp(String data) {
        if (data.length() > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("gameType", 3);
            bundle.putString("web_id", "-1");
            bundle.putString("k", data);
            bundle.putString("us", getApp().getUser().getLoginName());
            bundle.putString("lang", AfbUtils.getLanguage(mContext));
            bundle.putInt("homeColor", getHomeColor());
            try {
//                AfbUtils.appJump(mContext, "gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity", bundle);
                Intent intent = new Intent(Intent.ACTION_MAIN);
                ComponentName comp = new ComponentName("gaming178.com.baccaratgame",
                        "gaming178.com.casinogame.Activity.WelcomeActivity");
                intent.setComponent(comp);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                startActivityForResult(intent, 7);
            } catch (Exception e) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
                intent.setData(content_url);
                startActivity(intent);
            }
        }
    }

    private SpannableStringBuilder isStartWithTag(String str) {
        if (str.startsWith("<SPAN")) {
            String needStr = AfbUtils.delHTMLTag(str);
            if (needStr.startsWith("-")) {
                return AfbUtils.handleStringTextColor(needStr, Color.RED);
            }
            return new SpannableStringBuilder(needStr);
        } else {
            if (str.startsWith("-")) {
                return AfbUtils.handleStringTextColor(str, Color.RED);
            } else {
                return new SpannableStringBuilder(str);
            }
        }
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
//        test();
    }


    public void clickCollectionStar(View view) {
        currentFragment.collection(view);
    }

    public void clickBets(View view) {
        Bundle b = new Bundle();
        b.putString(AppConstant.KEY_STRING, getString(R.string.stake));
//        skipAct(PersonCenterActivity.class, b);
    }


    @Override
    protected void onResume() {
        super.onResume();
        createWebSocket();
        startRefreshMenu();
        if (popWindow instanceof BetPop && popWindow.isShowing()) {
            ((BetPop) popWindow).updateOdds(0);
        }
    }

    @Override
    protected void onStop() {
        setStopCloseWindow(false);
        super.onStop();
        if (popWindow instanceof BetPop && popWindow.isShowing()) {
            ((BetPop) popWindow).stopUpdateOdds();
        }
        stopRefreshMenu();
        WebSocketManager.getInstance().stopUpdateData();

    }

    public void clickSportSelect(final View view) {
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), AppConstant.wfMain), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    createPopupWindow(new BasePopupWindow(mContext, view, AfbUtils.dp2px(mContext, 200), AfbUtils.dp2px(mContext, 300)) {
                        @Override
                        protected int onSetLayoutRes() {
                            return R.layout.sport_game_switch_bottom_popupwindow;
                        }

                        @Override
                        protected void initView(View view) {
                            super.initView(view);
                            RecyclerView rv = view.findViewById(R.id.game_list_rcv);
                            RadioGroup games_switch_rg = view.findViewById(R.id.games_switch_rg);
                            final RadioButton sports_selected = view.findViewById(R.id.sports_selected);
                            final RadioButton others_selected = view.findViewById(R.id.others_selected);
                            rv.setLayoutManager(new LinearLayoutManager(mContext));
                            final List<SportIdBean> listSport = new ArrayList<>();
                            final List<SportIdBean> listOther = new ArrayList<>();
                            Iterator<SportIdBean> iterator = AfbUtils.sportMap.values().iterator();
                            while (iterator.hasNext()) {
                                listSport.add(iterator.next());
                            }
                            Iterator<SportIdBean> iterator1 = AfbUtils.othersMap.values().iterator();
                            while (iterator1.hasNext()) {
                                listOther.add(iterator1.next());
                            }
                            final BaseRecyclerAdapter<SportIdBean> baseRecyclerAdapter = new BaseRecyclerAdapter<SportIdBean>(mContext, new ArrayList<SportIdBean>(), R.layout.item_sport_switch) {
                                @Override
                                public void convert(MyRecyclerViewHolder holder, int position, SportIdBean item) {
                                    LinearLayout llContent = holder.getView(R.id.ll_content);
                                    TextView imgGamePic = holder.getView(R.id.img_game_pic);
                                    TextView tvGameName = holder.getView(R.id.tv_game_name);
                                    TextView tvGameCount = holder.getView(R.id.tv_game_count);
                                    llContent.setBackgroundColor(Color.WHITE);
                                    imgGamePic.setBackgroundResource(item.getSportPic());
                                    tvGameName.setText(item.getTextRes());
                                    tvGameName.setTextColor(item.getTextColor());
                                    tvGameCount.setText("0");
                                    MenuItemInfo stateType = currentFragment.getPresenter().getStateHelper().getStateType();
                                    String type = stateType.getType();
                                    if (type.equals("Running")) {
                                        if (jsonObjectNum != null) {
                                            if (!StringUtils.isNull(jsonObjectNum.optString("M_RAm" + item.getDbid()))) {
                                                tvGameCount.setText(jsonObjectNum.optString("M_RAm" + item.getDbid()));
                                            }
                                        }
                                    } else if (type.equals("Today")) {
                                        if (!StringUtils.isNull(jsonObjectNum.optString("M_TAm" + item.getDbid()))) {
                                            tvGameCount.setText(jsonObjectNum.optString("M_TAm" + item.getDbid()));
                                        }
                                    } else {
                                        if (!StringUtils.isNull(jsonObjectNum.optString("M_EAm" + item.getDbid()))) {
                                            tvGameCount.setText(jsonObjectNum.optString("M_EAm" + item.getDbid()));
                                        }
                                    }
                                    String trim = tvGameCount.getText().toString().trim();
                                    if (TextUtils.isEmpty(trim) || Integer.parseInt(trim) < 1) {
                                        tvGameCount.setVisibility(View.INVISIBLE);
                                    } else {
                                        tvGameCount.setVisibility(View.VISIBLE);
                                    }
                                    if (item.getTextColor() != Color.RED) {
                                        if (tvSportSelect.getText().toString().equals(getString(item.getTextRes()))) {
                                            tvGameName.setTextColor(ContextCompat.getColor(mContext, R.color.google_green));
                                            llContent.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                                        }
                                    } else {
                                        if (tvSportSelect.getText().toString().equals(getString(item.getTextRes()))) {
                                            llContent.setBackgroundColor(ContextCompat.getColor(context, R.color.gary1));
                                        }
                                    }
                                }
                            };

                            rv.setAdapter(baseRecyclerAdapter);
                            baseRecyclerAdapter.addAllAndClear(listSport);
                            baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SportIdBean>() {
                                @Override
                                public void onItemClick(View view, SportIdBean item, int position) {
                                    initSportFragment(item);
                                    closePopupWindow();
                                }
                            });

                            games_switch_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(RadioGroup radioGroup, @IdRes int id) {
                                    sports_selected.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    others_selected.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                                    switch (id) {
                                        case R.id.sports_selected:
                                            baseRecyclerAdapter.addAllAndClear(listSport);
                                            sports_selected.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_blue);
                                            break;
                                        case R.id.others_selected:
                                            baseRecyclerAdapter.addAllAndClear(listOther);
                                            others_selected.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, R.drawable.line_blue);
                                            break;
                                    }
                                }
                            });
                        }
                    });
                    popWindow.showPopupWindowUpCenter(view, AfbUtils.dp2px(mContext, 300 + 9), AfbUtils.dp2px(mContext, 200));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void clickAllRunning(View view) {
        setType("Running");
        dateClickPosition = 0;
        MenuItemInfo<Integer> item = new MenuItemInfo<Integer>(R.mipmap.date_running_green, getBaseActivity().getString(R.string.running), "Running", R.mipmap.date_running_green);
        runWayItem(item);
        SportIdBean sportIdBean = AfbUtils.sportMap.get("1,9,21,29,51,182");
        selectFragmentTag(getString(sportIdBean.getTextRes()), sportIdBean.getBaseFragment());
    }

    private void initSportFragment(SportIdBean item) {
        tvSportSelect.setCompoundDrawablesWithIntrinsicBounds(0, item.getSportPic(), 0, 0);
        tvSportSelect.setText(getString(item.getTextRes()));
        notClickType = false;
        if (!item.getDbid().startsWith("33"))
            currentIdBean = item;
        if (item.getDbid().equals("0")) {
            setType("Running");
            dateClickPosition = 0;
            runWayItem(new MenuItemInfo<Integer>(R.mipmap.date_running_green, getBaseActivity().getString(R.string.running), "Running", R.mipmap.date_running_green));
        } else if (item.getDbid().startsWith("33")) {
            MenuItemInfo<String> stringMenuItemInfo = new MenuItemInfo<>(R.mipmap.thai_thousand_1d, getString(R.string.game1d), item.getDbid(), "1");
            if (item.getDbid().equals("33_19")) {
                stringMenuItemInfo = new MenuItemInfo<String>(R.mipmap.thai_thousand_2d, getString(R.string.game2d), item.getDbid(), "2");
            } else if (item.getDbid().equals("33_20")) {
                stringMenuItemInfo = new MenuItemInfo<String>(R.mipmap.thai_thousand_3d, getString(R.string.game3d), item.getDbid(), "3");
            }
            notClickType = true;
            huayThaiFragment.setInfo(stringMenuItemInfo);
//            deleteHeadAndFoot();
            afbDrawerViewHolder.switchFragment(huayThaiFragment);
            return;
        }
        selectFragmentTag(getString(item.getTextRes()), item.getBaseFragment());

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
                                    final CheckBox checkBox = view.findViewById(R.id.cb_sort_time);
                                    checkBox.setChecked(getApp().getSort() == 1);
                                    final View ll_sort = view.findViewById(R.id.ll_sort);
                                    setChooseTypeAdapter(rv_list, tvMatchType, jsonObjectNum);
                                    ll_sort.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            changeTimeSort();
                                            checkBox.setChecked(getApp().getSort() == 1);
                                        }
                                    });
                                }
                            });
                    popWindow.showPopupWindowUpCenter(view, AfbUtils.dp2px(mContext, 356 + 9), AfbUtils.getScreenWidth(mContext) / 2);
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

    public int dateClickPosition = 0;

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
        MenuItemInfo oddsType = getOddsType();
        if (oddsType != null) {
            tvOddsType.setText(oddsType.getText());
           /* if (oddsType.getType().equals("HK")) {
                tvOddsType.setText(R.string.HK_ODDS);
            } else if (oddsType.getType().equals("MY")) {
                tvOddsType.setText(R.string.MY_ODDS);
            } else if (oddsType.getType().equals("EU")) {
                tvOddsType.setText(R.string.EU_ODDS);
            } else if (oddsType.getType().equals("ID")) {
                tvOddsType.setText(R.string.ID_ODDS);
            }*/

        }
        MenuItemInfo<String> allOddsType = getMarketType();
        if (allOddsType != null) {
            ivAllAdd.setText(allOddsType.getParent());
           /* if (allOddsType.getType().equals("0")) {
                ivAllAdd.setText(R.string.All_Markets);
            } else if (oddsType.getType().equals("1")) {
                ivAllAdd.setText(R.string.Main_Markets);
            } else if (oddsType.getType().equals("2")) {
                ivAllAdd.setText(R.string.Other_Bet_Markets);
            }*/
        }
    }

    public void clickMajor(View view) {
        TextView tx = (TextView) view;

        currentFragment.checkMajor(tx);
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

    private void startRefreshMenu() {
        stopRefreshMenu();
        handler.postDelayed(refreshMenuRunnable, 1500);
    }

    private void stopRefreshMenu() {
        if (handler != null) {
            handler.removeCallbacks(refreshMenuRunnable);
            handler.removeCallbacksAndMessages(null);
        }
    }

    private List<String> lastWaitDataBeanList;

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
        stopRefreshMenu();
        String s = tvWaiteCount.getText().toString();
        if (TextUtils.isEmpty(s)) {
            s = "0";
        }
        int parseInt = Integer.parseInt(s);
        parseInt += waitNumber;
        tvWaiteCount.setText(parseInt + "");
        tvWaiteCount.setVisibility(View.VISIBLE);
        startRefreshMenu();
    }


    private Runnable refreshMenuRunnable = new Runnable() {
        @Override
        public void run() {
            LinkedHashMap<String, String> menuParamMap = new LinkedHashMap<>();
            menuParamMap.put("ACT", "Getmenu");
            menuParamMap.put("PT", AppConstant.wfMain);
            String type = currentFragment.presenter.getStateHelper().getStateType().getType();
            String ot;
            if (type.equals("Running")) {
                ot = "r";
            } else if (type.equals("Today")) {
                ot = "t";
            } else {
                ot = "e";
            }
            menuParamMap.put("ot", ot);
            presenter.refreshMenu(menuParamMap);
            handler.postDelayed(this, 10000);
        }
    };

    public void runWayItem(MenuItemInfo item) {
        AfbUtils.switchLanguage(AfbUtils.getLanguage(mContext), mContext);
        if (item.getRes() == R.mipmap.date_day_grey) {
            tvMatchType.setText(item.getDay());
        } else {
            tvMatchType.setText(item.getText());
        }
        tvMatchType.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
        wd = item.getDateParam();
    }

    public void clickSortByTime(View view) {
        changeTimeSort();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


}
