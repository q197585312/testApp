package com.nanyang.app.main.home.sport.main;

import android.content.ComponentName;
import android.content.Intent;
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
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.nanyang.app.main.MainPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseView;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.ApkUtils;
import cn.finalteam.toolsfinal.logger.Logger;

public class SportActivity extends BaseToolbarActivity<LanguagePresenter> implements ILanguageView<String> {
    private final String GUIDE_KEY = "GUIDE";

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
    @Bind(R.id.iv_add)
    ImageView ivAdd;
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
    LinearLayout ll_header_sport;
    @Bind(R.id.ll_footer_sport)
    LinearLayout ll_footer_sport;

    @Bind(R.id.match_collection_iv)
    ImageView collectionIv;

    @Bind(R.id.main_more)
    RecyclerView reContent;

    private MenuItemInfo oddsType;
    private MenuItemInfo allOdds;
    public MenuItemInfo<String> item;
    private String currentGameType = "";
    public WebSocket webSocket;
    private AfbDrawerViewHolder afbDrawerViewHolder;
    private int sort;
    private SportIdBean typeItem;

    public TextView getIvAllAdd() {
        return ivAllAdd;
    }


    private String currentTag = "";
    private HashMap<String, BaseSportFragment> mapFragment;
    public BaseSportFragment currentFragment;
    private Handler handler = new Handler();

    public String wd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        createPresenter(new LanguagePresenter(this));
        toolbar.setVisibility(View.GONE);
        oddsType = new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY");
        allOdds = new MenuItemInfo(0, getString(R.string.All_Markets), "0");
        presenter.switchOddsType("MY", new BaseConsumer<String>(this) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {

            }
        });
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
                currentFragment.searchMatch(true, s);
                if (!StringUtils.isNull(s))
                    ivDeleteSearch.setVisibility(View.VISIBLE);
                else
                    ivDeleteSearch.setVisibility(View.GONE);
                currentFragment.checkBg(collectionIv, currentFragment.presenter.getStateHelper().isCollection(), R.mipmap.sport_game_star_yellow_open, R.mipmap.sport_game_star_yellow);

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

    @Override
    public void initData() {
        super.initData();
        app = (AfbApplication) getApplication();
        item = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA);

        afbDrawerViewHolder = new AfbDrawerViewHolder(drawerLayout, this, R.id.fl_main_content);

        if (item != null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());
            initFragment(item.getParent());
        }
        getApp().setBetParList(null);
        tvRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afbDrawerViewHolder.goRecord();
            }
        });
        startRefreshMenu();
    }

    public void updateMixOrderCount() {
        if (getApp().getBetParList() != null && getApp().getBetParList().getList() != null && getApp().getBetParList().getList().size() > 0) {
            tvMixCount.setVisibility(View.VISIBLE);
            tvOrderCount.setVisibility(View.VISIBLE);
            ivOrderTop.setImageResource(R.mipmap.sport_shopping_top_white);
            tvMixCount.setText("" + getApp().getBetParList().getList().size());
            tvOrderCount.setText("" + getApp().getBetParList().getList().size());
        } else {
            ivOrderTop.setImageResource(R.mipmap.sport_shopping_top_gray);
            tvMixCount.setVisibility(View.GONE);
            tvOrderCount.setVisibility(View.GONE);
            tvMixCount.setText("0");
            tvOrderCount.setText("0");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null)
            webSocket.close();
        BetGoalWindowUtils.clear();
        stopRefreshMenu();
    }

    private void initFragment(String parentType) {
        SportIdBean sportIdBean = AfbUtils.identificationSportByType(parentType);
        currentGameType = parentType;
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        localCurrentFragment = sportIdBean.getBaseFragment();
        Logger.getDefaultLogger().d("localCurrentFragment:" + localCurrentFragment);
        String localCurrentTag = getString(sportIdBean.getTextRes());
        selectFragmentTag(localCurrentTag, localCurrentFragment);
        afbDrawerViewHolder.initDefaultFragment(localCurrentFragment);
    }


    @Override
    public void defaultSkip(String parentType) {
        if (!parentType.equals(currentGameType)) {
            initFragment(parentType);
        }
    }


    public void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment) {
        ll_footer_sport.removeAllViews();

        ll_header_sport.removeAllViews();
        afbDrawerViewHolder.switchFragment(localCurrentFragment);
        currentFragment = localCurrentFragment;
        sportTitleTv.setText(getString(R.string.sport_match) + " > " + tag);
        if (currentTag.isEmpty()) {
            return;
        } else if (!currentTag.equals(tag)) {
            MenuItemInfo stateType = currentFragment.presenter.getStateHelper().getStateType();
            localCurrentFragment.switchParentType(stateType);
            setType(stateType.getType());
        } else {
            currentFragment.onResume();
        }
        currentTag = tag;
        tvSportSelect.setText(tag);
//        sportTitleTv.setText(getString(R.string.sport_match) + " > " + currentTag);

    }

    @Override
    protected void updateBalanceTv(String allData) {
        tvBalance.setText(getApp().getUser().getCurCode2() + ": " + allData);
    }

    private void loginGD() {
        if (ApkUtils.isAvilible(this, "gaming178.com.baccaratgame")) {
            presenter.skipGd88(new BaseView<SportActivity, String>(this) {
                @Override
                public void onGetData(String data) {
                    SportActivity.this.onGetData(data);
                }
            });
        } else {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(AppConstant.DownLoadDig88AppUrl);
            intent.setData(content_url);
            startActivity(intent);
        }
    }


    @Override
    public void onBackCLick(View v) {
        afbDrawerViewHolder.isBack(false);
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

    public void onGetTransferMoneyData(int type, String getBackStr, String data) {
        if (getBackStr.contains("not allowed")) {
            ToastUtils.showShort(getBackStr);
        } else {
            ToastUtils.showShort(getBackStr);
            startApp(data);
        }
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
            String needStr = Html.fromHtml(str).toString();
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
        this.oddsType = oddsType;
    }

    public MenuItemInfo getOddsType() {
        return oddsType;
    }

    public void setAllOdds(MenuItemInfo allOdds) {
        this.allOdds = allOdds;
    }

    public MenuItemInfo getAllOddsType() {
        return allOdds;
    }

    public int getSortType() {
        return sort;
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

    @Override
    public void onLoginAgainFinish(String gameType) {
        switchSkipAct(gameType);
    }

    public void clickCup(View view) {
        currentFragment.presenter.getStateHelper().createChoosePop(view);
    }

    public void clickCollectionStar(View view) {
        currentFragment.collection(view);
    }

    public void clickRunning(View view) {

    }

    public void clickBets(View view) {
        Bundle b = new Bundle();
        b.putString(AppConstant.KEY_STRING, getString(R.string.stake));
//        skipAct(PersonCenterActivity.class, b);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void clickSportSelect(final View view) {
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), "wfMainH50"), new MainPresenter.CallBack<String>() {
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

                                    tvSportSelect.setCompoundDrawablesWithIntrinsicBounds(0, item.getSportPic(), 0, 0);
                                    tvSportSelect.setText(getString(item.getTextRes()));
                                    if (item.getId().equals("1,9,21,29,51,182")) {
                                        initAllRunning("1");
                                    } else if (item.getDbid().equals("999")) {
//                                        initOutRight(item);
                                    } else {
                                        if (typeItem.getDbid().equals("999")) {
                                            String ot = ((OutRightState) typeItem.getBaseFragment().getPresenter().getStateHelper()).getOt();
                                            if (ot.equals("e"))
                                                item.getBaseFragment().setSwitchType("Early");
                                            else
                                                item.getBaseFragment().setSwitchType("Today");
                                        }
                                        selectFragmentTag(getString(item.getTextRes()), item.getBaseFragment());

                                    }
                                    SportActivity.this.typeItem = item;
                                    closePopupWindow();
                                }
                            });
      /*          TextView tvJumpCasino = (TextView) view.findViewById(R.id.tv_jump_casino);
                tvJumpCasino.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loginGD();
                    }
                });*/
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

    public void initAllRunning(String allRunningG) {
        SportIdBean sportIdBean = AfbUtils.getSportFromOtherAndSportByG(allRunningG);
        Log.d("initAllRunning", "allRunningG: " + allRunningG + ",sportIdBean:" + sportIdBean);
        if (sportIdBean == null)
            return;
        selectFragmentTag(getString(sportIdBean.getTextRes()), sportIdBean.getBaseFragment());
        addHeadAndFoot(allRunningG);
    }

    public void initOutRight(SportIdBean sportIdBean) {

//        String sportIdBean = AfbUtils.getOutRightGById(sportIdBean);
//        Log.d("initAllRunning", "allRunningG: " + outRightId + ",sportIdBean:" + sportIdBean);
        if (sportIdBean == null)
            return;
        selectFragmentTag(getString(sportIdBean.getTextRes()), sportIdBean.getBaseFragment());
        setType("OutRight");
        currentFragment.switchType("OutRight");
    }

    public void addHeadAndFoot(String allRunningG) {
        List<String> all = Arrays.asList("1", "9", "21", "29", "14", "5");
        boolean addHead = true;
        for (int i = 0; i < all.size(); i++) {
            String s = all.get(i);
            SportIdBean sportIdIndex = AfbUtils.getSportFromOtherAndSportByG(s);
            initAddView(addHead, sportIdIndex);
            if (s.equals(allRunningG)) {
                addHead = false;
            }
        }
    }

    private void initAddView(boolean addHead, final SportIdBean sportIdBean) {
        if (sportIdBean == null)
            return;
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.sport_selected_layout_base, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View sportView = inflate.findViewById(R.id.ll_sport);
        TextView sportName = inflate.findViewById(R.id.tv_sport_name);
        ImageView sportPic = inflate.findViewById(R.id.iv_sport_picture);
        sportName.setText(sportIdBean.getTextRes());
        sportPic.setImageResource(sportIdBean.getSportPic());
        sportName.setTextColor(sportIdBean.getTextColor());

        sportView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAllRunning(sportIdBean.getId());
            }
        });
        if (addHead)
            ll_header_sport.addView(inflate);
        else
            ll_footer_sport.addView(inflate);
    }

    public void clickSportWayRun(final View view) {
        final TextView textView = view.findViewById(R.id.tv_way_run);
        presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", new LanguageHelper(mContext).getLanguage(), "wfMainH50"), new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) {
                try {
                    final JSONObject jsonObjectNum = new JSONObject(data);
                    createPopupWindow(
                            new BasePopupWindow(mContext, textView, AfbUtils.getScreenWidth(mContext) / 2, AfbUtils.dp2px(mContext, 356)) {
                                @Override
                                protected int onSetLayoutRes() {
                                    return R.layout.popupwindow_choice_ball_type;
                                }

                                @Override
                                protected void initView(View view) {
                                    super.initView(view);
                                    RecyclerView rv_list = view.findViewById(R.id.rv_list);
                                    final CheckBox checkBox = view.findViewById(R.id.cb_sort_time);
                                    final View ll_sort = view.findViewById(R.id.ll_sort);
                                    setChooseTypeAdapter(rv_list, textView, jsonObjectNum);
                                    ll_sort.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            sort = 1 - sort;
                                            checkBox.setChecked(sort == 1);
                                            currentFragment.refresh();
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

    public int dateClickPositon = 0;

    private void setChooseTypeAdapter(RecyclerView rv_list, TextView textView, JSONObject jsonObjectNum) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.setAdapter(currentFragment.presenter.getStateHelper().switchTypeAdapter(textView, jsonObjectNum));
    }

    public void clickMoreMenu(View view) {
        drawerLayout.openDrawer(Gravity.RIGHT);
//        currentFragment.menu(view);
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
        if (oddsType != null)
            tvOddsType.setText(oddsType.getText());
        MenuItemInfo allOddsType = getAllOddsType();
        if (allOddsType != null) {
            ivAllAdd.setText(allOddsType.getText());
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
            menuParamMap.put("PT", "wfMainH50");
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
            handler.postDelayed(this, 5000);
        }
    };
}
