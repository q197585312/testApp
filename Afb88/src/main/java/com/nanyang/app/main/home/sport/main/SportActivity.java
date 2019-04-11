package com.nanyang.app.main.home.sport.main;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
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
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.AfbDrawerViewHolder;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.BaseView;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    TextView tvOddsType;
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
    @Bind(R.id.tv_mix)
    TextView tvMix;
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
    @Bind(R.id.main_more)
    RecyclerView reContent;

    private MenuItemInfo oddsType;
    private MenuItemInfo allOdds;
    private MenuItemInfo<String> item;
    private String currentGameType = "";
    public WebSocket webSocket;
    private AfbDrawerViewHolder afbDrawerViewHolder;

    public TextView getIvAllAdd() {
        return ivAllAdd;
    }


    private String currentTag = "";
    private HashMap<String, BaseSportFragment> mapFragment;
    public BaseSportFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        createPresenter(new LanguagePresenter(this));
        toolbar.setVisibility(View.GONE);
        oddsType = new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY");
        allOdds = new MenuItemInfo(0, getString(R.string.All_Markets), "&mt=0");
        presenter.switchOddsType("MY", new BaseConsumer<String>(this) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {

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
    }

    public void updateMixOrderCount() {
        if (getApp().getBetParList() != null && getApp().getBetParList().getList() != null && getApp().getBetParList().getList().size() > 0) {
            tvMixCount.setVisibility(View.VISIBLE);
            tvMixCount.setText("" + getApp().getBetParList().getList().size());
        } else {
            tvMixCount.setVisibility(View.GONE);
            tvMixCount.setText("0");
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webSocket != null)
            webSocket.close();
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


    private void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment) {
        afbDrawerViewHolder.switchFragment(localCurrentFragment);
        if (currentTag.isEmpty()) {

        } else if (!currentTag.equals(tag)) {
            MenuItemInfo stateType = currentFragment.presenter.getStateHelper().getStateType();
            localCurrentFragment.switchParentType(stateType);
            setType(stateType.getType());
        }
        currentFragment = localCurrentFragment;
        currentTag = tag;
        tvSportSelect.setText(tag);
        sportTitleTv.setText(getString(R.string.sport_match) + " > " + currentTag);

    }

    @Override
    protected void updateBalanceTv(String allData) {
        tvBalance.setText(allData);
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

//    public void getMoneyMsg(final TransferMoneyBean transferMoneyBean, final String data) {
//        popWindow.closePopupWindow();
//        TransferMoneyPop pop = new TransferMoneyPop(mContext, ivAdd) {
//            @Override
//            public void initMsgData(TextView tv_balance, TextView tv_casino_balance, EditText edt_amount) {
//                TransferMoneyBean.DicAllBean bean = transferMoneyBean.getDicAll().get(0);
//                tv_balance.setText(isStartWithTag(bean.getCredit()));
//                tv_casino_balance.setText(isStartWithTag(bean.getGdBalance()));
//            }
//
//            @Override
//            public void setOnCancelListener() {
//                startApp(data);
//            }
//
//            @Override
//            public void setOnSureListener(final String money) {
//                if (!TextUtils.isEmpty(money) && Integer.parseInt(money) != 0) {
//                    presenter.gamesGDTransferMonet(money, new BaseConsumer<String>(SportActivity.this) {
//                        @Override
//                        protected void onBaseGetData(String data) {
//                            onGetTransferMoneyData(0, money, data);
//                        }
//                    });
//                    closePopupWindow();
//                } else {
//                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
//                }
//            }
//
//        };
//        pop.showPopupCenterWindow();
//    }

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

    public void clickSportSelect(View view) {
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

                final BaseRecyclerAdapter<SportIdBean> baseRecyclerAdapter = new BaseRecyclerAdapter<SportIdBean>(mContext, new ArrayList<SportIdBean>(), R.layout.text_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, SportIdBean item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setBackgroundResource(R.color.white);
                        tv.setText(item.getTextRes());
                        tv.setTextColor(getResources().getColor(R.color.google_green));
                    }
                };

                rv.setAdapter(baseRecyclerAdapter);
                baseRecyclerAdapter.addAllAndClear(listSport);
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<SportIdBean>() {
                    @Override
                    public void onItemClick(View view, SportIdBean item, int position) {
                        selectFragmentTag(getString(item.getTextRes()), item.getBaseFragment());
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
        popWindow.setTrans(1f);
        popWindow.showPopupWindowUpCenter(view, AfbUtils.dp2px(mContext, 300), AfbUtils.dp2px(mContext, 200));


    }

    public void clickSportWayRun(View view) {
        currentFragment.toolbarRightClick(view);
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

}
