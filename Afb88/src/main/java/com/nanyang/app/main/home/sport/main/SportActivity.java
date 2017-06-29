package com.nanyang.app.main.home.sport.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.ILanguageView;
import com.nanyang.app.common.LanguagePresenter;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.home.discount.DiscountActivity;
import com.nanyang.app.main.home.gdCasino.PokerCasinoActivity;
import com.nanyang.app.main.home.huayThai.HuayThaiActivity;
import com.nanyang.app.main.home.sport.USFootball.USFootballFragment;
import com.nanyang.app.main.home.sport.badminton.BadmintonFragment;
import com.nanyang.app.main.home.sport.baseball.BaseballFragment;
import com.nanyang.app.main.home.sport.basketball.BasketballFragment;
import com.nanyang.app.main.home.sport.boxing.BoxingFragment;
import com.nanyang.app.main.home.sport.cricket.CricketFragment;
import com.nanyang.app.main.home.sport.cycling.CyclingFragment;
import com.nanyang.app.main.home.sport.darts.DartsFragment;
import com.nanyang.app.main.home.sport.dialog.ChooseLanguagePop;
import com.nanyang.app.main.home.sport.e_sport.ESportFragment;
import com.nanyang.app.main.home.sport.europe.EuropeFragment;
import com.nanyang.app.main.home.sport.financial.FinancialFragment;
import com.nanyang.app.main.home.sport.football.SoccerFragment;
import com.nanyang.app.main.home.sport.formula.FormulaFragment;
import com.nanyang.app.main.home.sport.game4d.Game4dFragment;
import com.nanyang.app.main.home.sport.golf.GolfFragment;
import com.nanyang.app.main.home.sport.handball.HandballFragment;
import com.nanyang.app.main.home.sport.iceHockey.IceHockeyFragment;
import com.nanyang.app.main.home.sport.muayThai.MuayThaiFragment;
import com.nanyang.app.main.home.sport.myanmarOdds.MyanmarFragment;
import com.nanyang.app.main.home.sport.poll.PoolFragment;
import com.nanyang.app.main.home.sport.rugby.RugbyFragment;
import com.nanyang.app.main.home.sport.superCombo.SuperComboFragment;
import com.nanyang.app.main.home.sport.tableTennis.TableTennisFragment;
import com.nanyang.app.main.home.sport.tennis.TennisFragment;
import com.nanyang.app.main.home.sport.volleyball.VolleyballFragment;
import com.nanyang.app.main.home.sport.winterSport.WinterSportFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
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
import cn.finalteam.toolsfinal.logger.Logger;


public class SportActivity extends BaseToolbarActivity<LanguagePresenter> implements ILanguageView<String> {
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
    BaseSportFragment superComboFragment = new SuperComboFragment();
    BaseSportFragment tableTennisFragment = new TableTennisFragment();
    BaseSportFragment formulaFragment = new FormulaFragment();


    @Bind(R.id.iv_add)
    ImageView ivAdd;


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
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.ll_sport_menu_bottom)
    LinearLayout llSportMenuBottom;

    private String currentTag="";
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
        tvToolbarRight1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseLanguagePop pop = new ChooseLanguagePop(SportActivity.this, v, presenter);
                onPopupWindowCreated(pop, Gravity.CENTER);
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
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    String type = "";

    @Override
    public void initData() {
        super.initData();
        tvToolbarLeft.setVisibility(View.VISIBLE);
        tvToolbarLeft.setBackgroundResource(R.mipmap.sport_home_white_24dp);
        tvToolbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameMenus(v);
            }
        });
        item = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        if (item != null) {
            type = item.getType();
            assert tvToolbarTitle != null;
            tvToolbarTitle.setText(item.getText());
        }
        initFragment(item.getParent());

        getApp().setBetParList(null);

    }

    private void initFragment(String parentType) {

        mapFragment = new LinkedHashMap<>();
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        currentGameType = parentType;
        Logger.getDefaultLogger().d("currentGameType:" + currentGameType);
        String localCurrentTag="";
        BaseSportFragment localCurrentFragment;
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
                mapFragment.put(getString(R.string.SuperCombo), superComboFragment);

                localCurrentTag = getString(R.string.Soccer);
                localCurrentFragment = soccerFragment;
                break;
        }
        selectFragmentTag(localCurrentTag,localCurrentFragment);
//        showFragmentToActivity(currentFragment, R.id.fl_content, currentTag);
    }

    private void gameMenus(View v) {
        popWindow = new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_all_game;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                BaseRecyclerAdapter adapter = AfbUtils.getGamesAdapter(mContext, rv);
                adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        switch (item.getType()) {
                            case "SportBook":
                            case "Financial":
                            case "Specials_4D":
                            case "Muay_Thai":
                            case "E_Sport":
                            case "Myanmar_Odds":
                            case "Europe":
                                defaultSkip(item.getType());
           /*             createPopupWindow(getPopupWindow(item.getType()));
                        popWindow.showPopupCenterWindow();*/
                                break;
                            case "Huay_Thai":
                                skipAct(HuayThaiActivity.class);
                                break;
                            case "Live_Casino":
                                Bundle b = new Bundle();
                                b.putString("activity", "Live");
                                skipAct(PokerCasinoActivity.class, b);
                                break;
                            case "Poker":
                                ToastUtils.showShort(R.string.coming_soon);
                                break;
                            case "Discount":
                                skipAct(DiscountActivity.class);
                                break;
                            default:
                                ToastUtils.showShort(R.string.coming_soon);
                        }
                        closePopupWindow();

                    }
                });
            }
        };
        popWindow.showPopupCenterWindow();
    }

    private void defaultSkip(String parentType) {
        if (!parentType.equals(currentGameType)) {
            initFragment(parentType);
        }
    }


    private void selectFragmentTag(String tag,BaseSportFragment localCurrentFragment) {
        if (currentTag.isEmpty()) {
            showFragmentToActivity(localCurrentFragment, R.id.fl_content, currentTag);
        } else if (!currentTag.equals(tag)) {
            hideFragmentToActivity(currentFragment);
            MenuItemInfo stateType = currentFragment.presenter.getStateHelper().getStateType();
            showFragmentToActivity(localCurrentFragment, R.id.fl_content, tag);
            localCurrentFragment.switchParentType(stateType);
            setType(stateType.getType());
        }
        currentFragment =localCurrentFragment;
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
                if (currentFragment.mix(tvMix))
                    tvCollection.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.sport_star_black, 0, 0);
                else {
                    getApp().setBetParList(null);
                }
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
                                tv.setBackgroundResource(R.color.google_green);
                                tv.setText(item.getText());
                                tv.setTextColor(getResources().getColor(R.color.white));
                            }

                        };

                        rv.setAdapter(baseRecyclerAdapter);
                        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                            @Override
                            public void onItemClick(View view, MenuItemInfo item, int position) {
                                selectFragmentTag(item.getText(),mapFragment.get(item.getText()));
                                closePopupWindow();
                            }
                        });
                    }
                });
                popWindow.setTrans(1f);
                popWindow.showPopupDownWindow();
                break;

        }
    }

    @Override
    protected void updateBalanceTv(String allData) {

    }

    @Override
    protected void onBackCLick(View v) {
        Intent intent = new Intent(mContext, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        onBackCLick(flContent);
    }

    @Override
    public void onGetData(String data) {

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
}
