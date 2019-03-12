package com.nanyang.app.main.home.sport.main;

import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guoqi.highlightview.Guide;
import com.guoqi.highlightview.GuideBuilder;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.nanyang.app.main.home.sport.USFootball.USFootballFragment;
import com.nanyang.app.main.home.sport.badminton.BadmintonFragment;
import com.nanyang.app.main.home.sport.baseball.BaseballFragment;
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.nanyang.app.main.home.sport.boxing.BoxingFragment;
import com.nanyang.app.main.home.sport.cricket.CricketFragment;
import com.nanyang.app.main.home.sport.cycling.CyclingFragment;
import com.nanyang.app.main.home.sport.darts.DartsFragment;
import com.nanyang.app.main.home.sport.dialog.ChooseLanguagePop;
import com.nanyang.app.main.home.sport.dialog.TransferMoneyPop;
import com.nanyang.app.main.home.sport.e_sport.ESportFragment;
import com.nanyang.app.main.home.sport.europe.EuropeFragment;
import com.nanyang.app.main.home.sport.financial.FinancialFragment;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.formula.FormulaFragment;
import com.nanyang.app.main.home.sport.game4d.Game4dFragment;
import com.nanyang.app.main.home.sport.golf.GolfFragment;
import com.nanyang.app.main.home.sport.handball.HandballFragment;
import com.nanyang.app.main.home.sport.iceHockey.IceHockeyFragment;
import com.nanyang.app.main.home.sport.main.component.Base2PicComponent;
import com.nanyang.app.main.home.sport.main.component.Left2PicComponent;
import com.nanyang.app.main.home.sport.main.component.Right2PicComponent;
import com.nanyang.app.main.home.sport.muayThai.MuayThaiFragment;
import com.nanyang.app.main.home.sport.myanmarOdds.MyanmarFragment;
import com.nanyang.app.main.home.sport.poll.PoolFragment;
import com.nanyang.app.main.home.sport.rugby.RugbyFragment;
import com.nanyang.app.main.home.sport.tableTennis.TableTennisFragment;
import com.nanyang.app.main.home.sport.tennis.TennisFragment;
import com.nanyang.app.main.home.sport.volleyball.VolleyballFragment;
import com.nanyang.app.main.home.sport.winterSport.WinterSportFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseView;
import com.unkonw.testapp.libs.utils.SharePreferenceUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.ApkUtils;
import cn.finalteam.toolsfinal.logger.Logger;

public class SportActivity extends BaseToolbarActivity<LanguagePresenter> implements ILanguageView<String> {
    private final String GUIDE_KEY = "GUIDE";
    BaseSportFragment soccerFragment = new SoccerFragment();
    BaseSportFragment basketballFragment = new BasketballFragment();
    BaseSportFragment tennisFragment = new TennisFragment();
    BaseSportFragment financialFragment = new FinancialFragment();
    BaseSportFragment game4dFragment = new Game4dFragment();
    BaseSportFragment eSportFragment = new ESportFragment();
    BaseSportFragment muayThaiFragment = new MuayThaiFragment();
    BaseSportFragment myanmarFragment = new MyanmarFragment();
    BaseSportFragment europeFragment = new EuropeFragment();
    BaseSportFragment usFootballFragment = new USFootballFragment();
    BaseSportFragment baseballFragment = new BaseballFragment();
    BaseSportFragment iceHockeyFragment = new IceHockeyFragment();
    BaseSportFragment poolFragment = new PoolFragment();
    BaseSportFragment rugbyFragment = new RugbyFragment();
    BaseSportFragment dartsFragment = new DartsFragment();
    BaseSportFragment boxingFragment = new BoxingFragment();
    BaseSportFragment golfFragment = new GolfFragment();
    BaseSportFragment badmintonFragment = new BadmintonFragment();
    BaseSportFragment volleyballFragment = new VolleyballFragment();
    BaseSportFragment cricketFragment = new CricketFragment();
    BaseSportFragment handballFragment = new HandballFragment();
    BaseSportFragment cyclingFragment = new CyclingFragment();
    BaseSportFragment winterSportFragment = new WinterSportFragment();
    //    BaseSportFragment superComboFragment = new SuperComboFragment();
    BaseSportFragment tableTennisFragment = new TableTennisFragment();
    BaseSportFragment formulaFragment = new FormulaFragment();
    BaseSportFragment localCurrentFragment;

    @Bind(R.id.iv_add)
    ImageView ivAdd;
    @Bind(R.id.ll_choice)
    LinearLayout llChoice;

    private MenuItemInfo oddsType;
    private MenuItemInfo allOdds;
    private MenuItemInfo<String> item;
    private String currentGameType = "";

    public TextView getIvAllAdd() {
        return ivAllAdd;
    }

    @Bind(R.id.iv_all_add)
    TextView ivAllAdd;
    @Bind(R.id.fl_content)
    FrameLayout flContent;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;
    @Bind(R.id.tv_collection)
    TextView tvCollection;
    @Bind(R.id.tv_menu)
    TextView tvMenu;
    @Bind(R.id.tv_mix)
    TextView tvMix;
    @Bind(R.id.tv_mix_count)
    TextView tvMixCount;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_sport_menu_bottom)
    LinearLayout llSportMenuBottom;

    private String currentTag = "";
    private HashMap<String, BaseSportFragment> mapFragment;
    private BaseSportFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sport);
        ButterKnife.bind(this);
        createPresenter(new LanguagePresenter(this));
        assert tvToolbarRight != null;
        tvToolbarRight.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_list_layer, 0);
        tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_white_language, 0);
        tvToolbarRight1.setVisibility(View.VISIBLE);
        if (getString(R.string.app_name).equals("AP889")) {
            String lg = AfbUtils.getLanguage(mContext);
            switch (lg) {
                case "zh":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_zh_flag, 0);
                    break;
                case "en":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_en_flag, 0);
                    break;
                case "th":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_th_flag, 0);
                    break;
                case "ko":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_ko_flag, 0);
                    break;
                case "vi":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_vi_flag, 0);
                    break;
                case "tr":
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_tr_flag, 0);
                    break;
                default:
                    tvToolbarRight1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.lang_en_flag, 0);
                    break;
            }
        }

        tvToolbarRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseLanguagePop pop = new ChooseLanguagePop(SportActivity.this, v, presenter);
                onPopupWindowCreated(pop, Gravity.CENTER);
                if (getString(R.string.app_name).equals("AP889")) {
                    pop.setShowTv(tvToolbarRight1);
                }
            }
        });
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentFragment.toolbarRightClick(v);
            }
        });
        tvToolbarTitle.setBackgroundResource(0);
        oddsType = new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY");
        allOdds = new MenuItemInfo(0, getString(R.string.All_Markets), "&mt=0");
        tvToolbarLeft.setVisibility(View.VISIBLE);
        initGuide();
        presenter.switchOddsType("MY");
    }


    private void initGuide() {
        boolean hasGuide = SharePreferenceUtil.getBoolean(mContext, GUIDE_KEY);
        if (!hasGuide) {
            tvToolbarLeft.post(new Runnable() {
                @Override
                public void run() {
                    showGuideView();
                }
            });
        }

    }

    public void showGuideView() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(tvToolbarLeft)
                .setAlpha(200)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideViewLanguage();
            }
        });
        Base2PicComponent base2PicComponent = new Left2PicComponent();
        base2PicComponent.setAnchorView(tvToolbarLeft);
        base2PicComponent.setPicRes(R.mipmap.arrow_line_guide_left, R.mipmap.guide_home_choice);
        base2PicComponent.setContentMarginLeftRight(20);
        builder.addComponent(base2PicComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(mContext);
    }

    public void showGuideViewLanguage() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(tvToolbarRight1)
                .setAlpha(200)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideViewGameType();

            }
        });
        Base2PicComponent base2PicComponent = new Right2PicComponent();
        base2PicComponent.setAnchorView(tvToolbarRight1);
        base2PicComponent.setPicRes(R.mipmap.arrow_line_guide_right, R.mipmap.guide_languge_choice);
        builder.addComponent(base2PicComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(mContext);
    }


    private void showGuideViewGameType() {
        TextView gameTypeTv;
        if (getString(R.string.app_name).equals("AP889")) {
            gameTypeTv = tvToolbarTitle;
        } else {
            gameTypeTv = tvToolbarRight;
        }
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(gameTypeTv)
                .setAlpha(200)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideViewGameChoice();

            }
        });
        Base2PicComponent base2PicComponent = new Right2PicComponent();
        base2PicComponent.setAnchorView(gameTypeTv);
        base2PicComponent.setPicRes(R.mipmap.arrow_line_guide_right, R.mipmap.guide_game_type);
        builder.addComponent(base2PicComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(mContext);
    }

    private void showGuideViewGameChoice() {
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(llChoice)
                .setAlpha(200)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                showGuideViewGameHandicap();

            }
        });
        Base2PicComponent base2PicComponent = new Right2PicComponent();
        base2PicComponent.setAnchorView(llChoice);
        base2PicComponent.setPicRes(R.mipmap.arrow_line_guide_right, R.mipmap.guide_game_choice);
        builder.addComponent(base2PicComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(mContext);
    }

    private void showGuideViewGameHandicap() {
        View llOddsType = localCurrentFragment.llOddsType;
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(llOddsType)
                .setAlpha(200)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                endGuide();
            }
        });
        Base2PicComponent base2PicComponent = new Right2PicComponent();
        base2PicComponent.setAnchorView(llOddsType);
        base2PicComponent.setPicRes(R.mipmap.arrow_line_guide_right, R.mipmap.guide_odds_type);
        base2PicComponent.setContentMarginLeftRight(0);
        builder.addComponent(base2PicComponent);
        Guide guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(false);
        guide.show(mContext);
    }

    private void endGuide() {
        SharePreferenceUtil.setValue(mContext, GUIDE_KEY, true);
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
        if (item != null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());
            if (getString(R.string.app_name).equals("AP889")) {
                Toolbar.LayoutParams params = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT);
                params.gravity = Gravity.CENTER;
                tvToolbarTitle.setLayoutParams(params);
                tvToolbarTitle.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.sport_odds_type_arrow_oval_green, 0);
                tvToolbarTitle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        currentFragment.toolbarRightClick(v);
                    }
                });
                tvToolbarRight.setVisibility(View.GONE);
            }
        }
        initFragment(item.getParent());

        getApp().setBetParList(null);

    }

    private void initFragment(String parentType) {

        mapFragment = new LinkedHashMap<>();
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        currentGameType = parentType;
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        String localCurrentTag = "";
        switch (parentType) {
            case "Financial":
                mapFragment.put(getString(R.string.Financial), financialFragment);
                localCurrentTag = getString(R.string.Financial);
                localCurrentFragment = financialFragment;
                break;
            case "Specials_4D":
                mapFragment.put(getString(R.string.Specials_4D), game4dFragment);
                localCurrentTag = getString(R.string.Specials_4D);
                localCurrentFragment = game4dFragment;

                break;
            case "Muay_Thai":
                mapFragment.put(getString(R.string.Muay_Thai), muayThaiFragment);
                localCurrentTag = getString(R.string.Muay_Thai);
                localCurrentFragment = muayThaiFragment;
                break;
            case "E_Sport":
                mapFragment.put(getString(R.string.E_Sport), eSportFragment);
                localCurrentTag = getString(R.string.E_Sport);
                localCurrentFragment = eSportFragment;
                break;
            case "Myanmar_Odds":
                mapFragment.put(getString(R.string.Myanmar_Odds), myanmarFragment);
                localCurrentTag = getString(R.string.Myanmar_Odds);
                localCurrentFragment = myanmarFragment;
                break;
            case "Europe":
                mapFragment.put(getString(R.string.Europe_View), europeFragment);
                localCurrentTag = getString(R.string.Europe_View);
                localCurrentFragment = europeFragment;
                break;
            default:
                mapFragment.put(getString(R.string.Soccer), soccerFragment);
                mapFragment.put(getString(R.string.Basketball), basketballFragment);
                mapFragment.put(getString(R.string.Tennis), tennisFragment);
                mapFragment.put(getString(R.string.US_Football), usFootballFragment);
                mapFragment.put(getString(R.string.Baseball), baseballFragment);
                mapFragment.put(getString(R.string.IceHockey), iceHockeyFragment);
                mapFragment.put(getString(R.string.Pool), poolFragment);
                mapFragment.put(getString(R.string.Rugby), rugbyFragment);
                mapFragment.put(getString(R.string.Darts), dartsFragment);
                mapFragment.put(getString(R.string.Boxing), boxingFragment);
                mapFragment.put(getString(R.string.Formula1), formulaFragment);
                mapFragment.put(getString(R.string.Golf), golfFragment);
                mapFragment.put(getString(R.string.Badminton), badmintonFragment);
                mapFragment.put(getString(R.string.Table_Tennis), tableTennisFragment);
                mapFragment.put(getString(R.string.Volleyball), volleyballFragment);
                mapFragment.put(getString(R.string.Cricket), cricketFragment);
                mapFragment.put(getString(R.string.Handball), handballFragment);
                mapFragment.put(getString(R.string.Cycling), cyclingFragment);
                mapFragment.put(getString(R.string.WinterSport), winterSportFragment);
                mapFragment.put(getString(R.string.E_Sport), eSportFragment);
                mapFragment.put(getString(R.string.Myanmar_Odds), myanmarFragment);
                mapFragment.put(getString(R.string.Europe_View), europeFragment);
//                mapFragment.put(getString(R.string.SuperCombo), superComboFragment);
                localCurrentTag = getString(R.string.Soccer);
                localCurrentFragment = soccerFragment;
                break;
        }
        selectFragmentTag(localCurrentTag, localCurrentFragment);

//        showFragmentToActivity(currentFragment, R.id.fl_content, currentTag);
    }


    @Override
    public void defaultSkip(String parentType) {
        if (!parentType.equals(currentGameType)) {
            initFragment(parentType);
        }
    }


    private void selectFragmentTag(String tag, BaseSportFragment localCurrentFragment) {
        if (currentTag.isEmpty()) {
            showFragmentToActivity(localCurrentFragment, R.id.fl_content, currentTag);
        } else if (!currentTag.equals(tag)) {
            hideFragmentToActivity(currentFragment);
            MenuItemInfo stateType = currentFragment.presenter.getStateHelper().getStateType();
            showFragmentToActivity(localCurrentFragment, R.id.fl_content, tag);
            localCurrentFragment.switchParentType(stateType);
            setType(stateType.getType());
        }
        currentFragment = localCurrentFragment;
        currentTag = tag;
        tvTitle.setText(tag);
    }

    @OnClick({R.id.tv_refresh, R.id.tv_collection, R.id.tv_menu, R.id.tv_mix, R.id.iv_add})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_refresh:
                currentFragment.refresh();
                break;
            case R.id.tv_collection:
                currentFragment.collection(tvCollection);
                break;
            case R.id.tv_menu:
                currentFragment.menu(tvMenu);
                break;
            case R.id.tv_mix:
                currentFragment.clickOrder();
              /*  if (currentFragment.mix(tvMix))
                    tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                else {
                    getApp().setBetParList(null);
                }*/
                break;
            case R.id.iv_add:
                createPopupWindow(new BasePopupWindow(mContext, view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_game;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                        rv.setLayoutManager(new GridLayoutManager(mContext, 3));
                        List<MenuItemInfo> list = new ArrayList<>();
                        Iterator<String> iterator = mapFragment.keySet().iterator();
                        while (iterator.hasNext()) {
                            list.add(new MenuItemInfo(0, iterator.next()));
                        }

                        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base) {
                            @Override
                            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                                TextView tv = holder.getView(R.id.item_text_tv);
//                                tv.setBackgroundResource(R.color.google_green);
                                dynamicAddView(tv, "background", R.color.google_green);

                                //aaa
                                tv.setText(item.getText());
                                tv.setTextColor(getResources().getColor(R.color.white));
                            }

                        };

                        rv.setAdapter(baseRecyclerAdapter);
                        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                            @Override
                            public void onItemClick(View view, MenuItemInfo item, int position) {
                                selectFragmentTag(item.getText(), mapFragment.get(item.getText()));
                                closePopupWindow();
                            }
                        });
                        TextView tvJumpCasino = (TextView) view.findViewById(R.id.tv_jump_casino);
                        tvJumpCasino.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                loginGD();
                            }
                        });
                    }
                });
                popWindow.setTrans(1f);
                popWindow.showPopupDownWindow();
                break;
        }
    }

    private void loginGD() {
        if (ApkUtils.isAvilible(this, "gaming178.com.baccaratgame")) {
            presenter.skipGd88(new IBaseView<String>() {
                @Override
                public void onGetData(String data) {
                    SportActivity.this.onGetData(data);
                }
                @Override
                public void onFailed(String error) {

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
    protected void updateBalanceTv(String allData) {

    }

    @Override
    protected void onBackCLick(View v) {
        if (AppConstant.getInstance().IS_AGENT) {
            finish();
        } else {
            Intent intent = new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {
        onBackCLick(flContent);
    }

    @Override
    public void onGetData(final String data) {
        presenter.getTransferMoneyData(new BaseConsumer<TransferMoneyBean>(this) {
            @Override
            protected void onBaseGetData(TransferMoneyBean transferMoneyBean) {
                getMoneyMsg(transferMoneyBean, data);
            }
        });
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

    public void getMoneyMsg(final TransferMoneyBean transferMoneyBean, final String data) {
        popWindow.closePopupWindow();
        TransferMoneyPop pop = new TransferMoneyPop(mContext, ivAdd) {
            @Override
            public void initMsgData(TextView tv_balance, TextView tv_casino_balance, EditText edt_amount) {
                TransferMoneyBean.DicAllBean bean = transferMoneyBean.getDicAll().get(0);
                tv_balance.setText(isStartWithTag(bean.getCredit()));
                tv_casino_balance.setText(isStartWithTag(bean.getGdBalance()));
            }

            @Override
            public void setOnCancelListener() {
                startApp(data);
            }

            @Override
            public void setOnSureListener(final String money) {
                if (!TextUtils.isEmpty(money) && Integer.parseInt(money) != 0) {
                    presenter.gamesGDTransferMonet(money, new BaseConsumer<String>(SportActivity.this) {
                        @Override
                        protected void onBaseGetData(String data) {
                            onGetTransferMoneyData(0, money, data);
                        }
                    });
                    closePopupWindow();
                } else {
                    ToastUtils.showShort(getString(R.string.Input_the_amount_please));
                }
            }

        };
        pop.showPopupCenterWindow();
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
            bundle.putString("us", getApp().getUser().getUserName());
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
        presenter.login(new LoginInfo(app.getUser().getUserName(), app.getUser().getPassword()), new BaseConsumer<String>(this) {
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
}
