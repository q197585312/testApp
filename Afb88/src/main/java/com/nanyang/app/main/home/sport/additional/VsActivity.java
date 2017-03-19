package com.nanyang.app.main.home.sport.additional;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.adapter.MyFragmentPagerAdapter;
import com.nanyang.app.main.home.sport.mixparlayList.MixOrderListActivity;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.BettingParPromptBean;
import com.nanyang.app.main.home.sport.model.ScaleBean;
import com.nanyang.app.main.home.sport.model.SoccerMixInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.nanyang.app.main.home.sportInterface.SoccerCommonBetHelper;
import com.nanyang.app.main.home.sportInterface.SoccerMixBetHelper;
import com.nanyang.app.main.home.sportInterface.SoccerRunningBetHelper;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.indicator.PagerSlidingTabStrip;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Administrator on 2015/10/22.
 */
public class VsActivity extends BaseToolbarActivity<VsPresenter> implements BetView<ScaleBean> {
    @Bind(R.id.vs_time_tv)
    TextView vsTimeTv;
    @Bind(R.id.ballgame_tabs_pstabs)
    PagerSlidingTabStrip ballgameTabsPstabs;
    @Bind(R.id.ballgame_pager_vp)
    ViewPager ballgamePagerVp;
    @Bind(R.id.ballgame_bottom_ll)
    LinearLayout ballgameBottomLl;
    @Bind(R.id.tv_mix_parlay_order)
    TextView tvMixParlayOrder;
    @Bind(R.id.ll_mix_parlay_order)
    LinearLayout llMixParlayOrder;


    private BallInfo item;
    private String matchType;
    private ScaleFragment sf = new ScaleFragment();
    private BetSingleDoubleFragment bf = new BetSingleDoubleFragment();
    private CorrectFragment cf = new CorrectFragment();

    public boolean isMixParlay() {
        return isMixParlay;
    }

    private boolean isMixParlay = false;
    private String url = "";
    private ArrayList<BaseVsFragment> fragmentsList;
    List<List<VsTableRowBean>> datas = new ArrayList();

    public MenuItemInfo<String> getType() {
        return type;
    }

    private MenuItemInfo<String> type;

    public IBetHelper getHelper() {
        return helper;
    }

    IBetHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        toolbar.setNavigationIcon(null);
        item = (BallInfo) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        type = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA2);

        matchType = type.getType();
        isMixParlay = type.getRes() == 1;
        String parent = type.getParent();
        String football = getString(R.string.football);
        if (parent.equals(football)) {
            if (isMixParlay) {
                helper = new SoccerMixBetHelper(this);
            } else {
                helper = new SoccerCommonBetHelper(this);
                if (matchType.equals("Running"))
                    helper = new SoccerRunningBetHelper(this);
            }
        }

        assert tvToolbarTitle != null;
        tvToolbarTitle.setBackgroundResource(0);
        tvToolbarTitle.setText(item.getHome() + "  VS  " + item.getAway());
        tvToolbarTitle.setTextSize(12);
        ViewGroup.LayoutParams layoutParams = tvToolbarTitle.getLayoutParams();
        layoutParams.width = Toolbar.LayoutParams.MATCH_PARENT;
        vsTimeTv.setText(item.getMatchDate());

        fragmentsList = new ArrayList<>();

        sf.setTitle(getString(R.string.scaleplate_asianplate));
        bf.setTitle(getString(R.string.single_double));
        cf.setTitle(getString(R.string.correct_score));
        createPresenter(new VsPresenter(this));
        if (isMixParlay) {
            fragmentsList.add(sf);
            initFirstData((SoccerMixInfo) item);
        } else {
            fragmentsList.add(sf);
            fragmentsList.add(bf);
            fragmentsList.add(cf);
            presenter.scale(item, matchType);
        }
        ballgamePagerVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        ballgameTabsPstabs.setViewPager(ballgamePagerVp);
        ballgameTabsPstabs.setTextColorResource(R.color.green_light);
        ballgameTabsPstabs.setBackGroundColorRes(R.color.green_light_white);
        ballgameTabsPstabs.setSelectedBgColor(-1);


        ballgamePagerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseVsFragment baseVsFragment = fragmentsList.get(position);
                baseVsFragment.setData(datas.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public SportInfo getItem() {
        return item;
    }

    public boolean getMixParlay() {
        return isMixParlay;
    }

    public void setMixParlay(boolean mixParlay) {
        this.isMixParlay = mixParlay;
    }

    private void initFirstData(SoccerMixInfo item) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        int socOddsId_hf = 0;
        int socOddsId = 0;
        if (item.getSocOddsId_FH() != null && !item.getSocOddsId_FH().equals(""))
            socOddsId_hf = Integer.valueOf(item.getSocOddsId_FH());
        if (item.getSocOddsId() != null && !item.getSocOddsId().equals(""))
            socOddsId = Integer.valueOf(item.getSocOddsId());
        rows = Arrays.asList(new VsTableRowBean("1_par", Arrays.asList(new VsCellBean(getString(R.string.h1), "", 0), new VsCellBean("", item.getHasX12().equals("0")?"":item.getX12_1Odds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0")?"":item.getX12_1Odds_FH(), socOddsId_hf)), true, false, "1  x  2", getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("X_par", Arrays.asList(new VsCellBean(getString(R.string.dx), "", 0), new VsCellBean("", item.getHasX12().equals("0")?"":item.getX12_XOdds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0")?"":item.getX12_XOdds_FH(), socOddsId_hf))),
                new VsTableRowBean("2_par", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", item.getHasX12().equals("0")?"":item.getX12_2Odds(), socOddsId), new VsCellBean("", item.getHasX12_FH().equals("0")?"":item.getX12_2Odds_FH(), socOddsId_hf)), true),
                new VsTableRowBean("odd_par", Arrays.asList(new VsCellBean(getString(R.string.odd), "", 0), new VsCellBean("", item.getHasOE().equals("0")?"":item.getOddOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true, false, getString(R.string.odd_even), getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("even_par", Arrays.asList(new VsCellBean(getString(R.string.even), "", 0), new VsCellBean("", item.getHasOE().equals("0")?"":item.getEvenOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true));
        datas.add(rows);
        sf.setData(rows);


    }

    private void thirdFragmentData(ScaleBean result) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        rows = Arrays.asList(new VsTableRowBean("csr", Arrays.asList(new VsCellBean("1:0", result.getFTCS().getC1_0(), "10", result.getFTCS().getOid()), new VsCellBean("0:0", result.getFTCS().getC0_0(), "0", result.getFTCS().getOid()), new VsCellBean("0:1", result.getFTCS().getC0_1(), "1", result.getFTCS().getOid())), true, false, getString(R.string.correct_full), getString(R.string.h1), getString(R.string.dx), getString(R.string.a2)),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("2:0", result.getFTCS().getC2_0(), "20", result.getFTCS().getOid()), new VsCellBean("1:1", result.getFTCS().getC1_1(), "11", result.getFTCS().getOid()), new VsCellBean("0:2", result.getFTCS().getC0_2(), "2", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("2:1", result.getFTCS().getC2_1(), "21", result.getFTCS().getOid()), new VsCellBean("2:2", result.getFTCS().getC2_2(), "22", result.getFTCS().getOid()), new VsCellBean("1:2", result.getFTCS().getC1_2(), "12", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:0", result.getFTCS().getC3_0(), "30", result.getFTCS().getOid()), new VsCellBean("3:3", result.getFTCS().getC3_3(), "33", result.getFTCS().getOid()), new VsCellBean("0:3", result.getFTCS().getC0_3(), "3", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:1", result.getFTCS().getC3_1(), "31", result.getFTCS().getOid()), new VsCellBean("4:4", result.getFTCS().getC4_4(), "44", result.getFTCS().getOid()), new VsCellBean("1:3", result.getFTCS().getC1_3(), "13", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:2", result.getFTCS().getC3_2(), "32", result.getFTCS().getOid()), new VsCellBean(getString(R.string.other), result.getFTCS().getAOS(), "70", result.getFTCS().getOid()), new VsCellBean("2:3", result.getFTCS().getC2_3(), "23", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:0", result.getFTCS().getC4_0(), "40", result.getFTCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("0:4", result.getFTCS().getC0_4(), "4", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:1", result.getFTCS().getC4_1(), "41", result.getFTCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("1:4", result.getFTCS().getC1_4(), "14", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:2", result.getFTCS().getC4_2(), "42", result.getFTCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("2:4", result.getFTCS().getC2_4(), "24", result.getFTCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:3", result.getFTCS().getC4_3(), "43", result.getFTCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("3:4", result.getFTCS().getC3_4(), "34", result.getFTCS().getOid())), true),


                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("1:0", result.getFHCS().getC1_0(), "10", result.getFHCS().getOid()), new VsCellBean("0:0", result.getFHCS().getC0_0(), "0", result.getFHCS().getOid()), new VsCellBean("0:1", result.getFHCS().getC0_1(), "1", result.getFHCS().getOid())), true, false, getString(R.string.correct_half), getString(R.string.h1), getString(R.string.dx), getString(R.string.a2)),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("2:0", result.getFHCS().getC2_0(), "20", result.getFHCS().getOid()), new VsCellBean("1:1", result.getFHCS().getC1_1(), "11", result.getFHCS().getOid()), new VsCellBean("0:2", result.getFHCS().getC0_2(), "2", result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("2:1", result.getFHCS().getC2_1(), "21", result.getFHCS().getOid()), new VsCellBean("2:2", result.getFHCS().getC2_2(), "22", result.getFHCS().getOid()), new VsCellBean("1:2", result.getFHCS().getC1_2(), "12", result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:0", result.getFHCS().getC3_0(), "30", result.getFHCS().getOid()), new VsCellBean("3:3", result.getFHCS().getC3_3(), "33", result.getFHCS().getOid()), new VsCellBean("0:3", result.getFHCS().getC0_3(), "3", result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:1", result.getFHCS().getC3_1(), "31", result.getFHCS().getOid()), new VsCellBean("", result.getFHCS().getC4_4(), result.getFHCS().getOid()), new VsCellBean("1:3", result.getFHCS().getC1_3(), "13", result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("3:2", result.getFHCS().getC3_2(), "32", result.getFHCS().getOid()), new VsCellBean(getString(R.string.other), result.getFHCS().getAOS(), "80", result.getFHCS().getOid()), new VsCellBean("2:3", result.getFHCS().getC2_3(), "23", result.getFHCS().getOid())), true),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:0", result.getFHCS().getC4_0(), result.getFHCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("4:0", result.getFHCS().getC0_4(), result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:1", result.getFHCS().getC4_1(), result.getFHCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("4:1", result.getFHCS().getC1_4(), result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:2", result.getFHCS().getC4_2(), result.getFHCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("4:2", result.getFHCS().getC2_4(), result.getFHCS().getOid()))),
                new VsTableRowBean("csr", Arrays.asList(new VsCellBean("4:3", result.getFHCS().getC4_3(), result.getFHCS().getOid()), new VsCellBean("", "", 0), new VsCellBean("4:3", result.getFHCS().getC3_4(), result.getFHCS().getOid())), true));
        datas.add(rows);
        cf.setData(rows);

    }

    private void secondFragmentData(ScaleBean result) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        rows = Arrays.asList(new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.hh), result.getHTFT().getHH(), "11", result.getHTFT().getOid()), new VsCellBean(getString(R.string.hd), result.getHTFT().getHD(), "10", result.getHTFT().getOid()), new VsCellBean(getString(R.string.ha), result.getHTFT().getHA(), "12", result.getHTFT().getOid())), true, false, getString(R.string.half_full_time), "", "", ""),
                new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.dh), result.getHTFT().getDH(), "1", result.getHTFT().getOid()), new VsCellBean(getString(R.string.dd), result.getHTFT().getDD(), "0", result.getHTFT().getOid()), new VsCellBean(getString(R.string.da), result.getHTFT().getDA(), "2", result.getHTFT().getOid()))),
                new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.ah), result.getHTFT().getAH(), "21", result.getHTFT().getOid()), new VsCellBean(getString(R.string.ad), result.getHTFT().getAD(), "20", result.getHTFT().getOid()), new VsCellBean(getString(R.string.aa), result.getHTFT().getAA(), "22", result.getHTFT().getOid())), true),
                new VsTableRowBean("fglg", Arrays.asList(new VsCellBean("HF", result.getFGLG().getHF(), "10", result.getFGLG().getOid()), new VsCellBean("HL", result.getFGLG().getHL(), "1", result.getFGLG().getOid()), new VsCellBean("AF", result.getFGLG().getAF(), "20", result.getFGLG().getOid()),
                        new VsCellBean("AL", result.getFGLG().getAL(), "2", result.getFGLG().getOid()), new VsCellBean("NO GOAL", result.getFGLG().getNO_GOAL(), "0", result.getFGLG().getOid())), true, true, getString(R.string.first_last_goal), "", "", ""),
                new VsTableRowBean("tg", Arrays.asList(new VsCellBean("0~1", result.getTG().getT0_1(), "1", result.getTG().getOid()), new VsCellBean("2~3", result.getTG().getT2_3(), "23", result.getTG().getOid()), new VsCellBean("4~6", result.getTG().getT4_6(), "46", result.getTG().getOid()),
                        new VsCellBean("", "", 0), new VsCellBean("7 & OVER", result.getTG().getT7_OVER(), "70", result.getTG().getOid())), true, true, getString(R.string.total_goals), "", "", ""));
        datas.add(rows);
        bf.setData(rows);


    }

    private void firstFragmentData(ScaleBean result) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        rows = Arrays.asList(new VsTableRowBean("1", Arrays.asList(new VsCellBean(getString(R.string.h1), "", result.getFT1x2().getOid()), new VsCellBean("", result.getFT1x2().getF1(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getF1(), result.getFH1x2().getOid())), true, false, "1  x  2", getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("x", Arrays.asList(new VsCellBean(getString(R.string.dx), "", 0), new VsCellBean("", result.getFT1x2().getX(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getX(), result.getFH1x2().getOid()))),
                new VsTableRowBean("2", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", result.getFT1x2().getF2(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getF2(), result.getFH1x2().getOid())), true),
                new VsTableRowBean("dc", "10", Arrays.asList(new VsCellBean("1X", "", 0), new VsCellBean("", result.getFTDC().getF1x(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getF1x(), result.getFHDC().getOid())), true, false, getString(R.string.double_chance), getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("dc", "12", Arrays.asList(new VsCellBean("12", "", 0), new VsCellBean("", result.getFTDC().getF12(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getF12(), result.getFHDC().getOid()))),
                new VsTableRowBean("dc", "2", Arrays.asList(new VsCellBean("X2", "", 0), new VsCellBean("", result.getFTDC().getFx2(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getFx2(), result.getFHDC().getOid())), true),
                new VsTableRowBean("odd", Arrays.asList(new VsCellBean(getString(R.string.odd), "", 0), new VsCellBean("", result.getFTOE().getODD(), result.getFTOE().getOid()), new VsCellBean("", result.getFHOE().getODD(), result.getFHOE().getOid())), true, false, getString(R.string.odd_even), getString(R.string.against), getString(R.string.full_time), getString(R.string.half_time)),
                new VsTableRowBean("even", Arrays.asList(new VsCellBean(getString(R.string.even), "", 0), new VsCellBean("", result.getFTOE().getEVEN(), result.getFTOE().getOid()), new VsCellBean("", result.getFHOE().getEVEN(), result.getFHOE().getOid())), true));
        datas.add(rows);
        sf.setData(rows);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isMixParlay)
            presenter.scale(item, matchType);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopUpdate();
    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onGetData(ScaleBean data) {
        firstFragmentData(data);
        secondFragmentData(data);
        thirdFragmentData(data);
    }

    @OnClick({R.id.tv_back, R.id.tv_not_settled, R.id.tv_settled,R.id.ll_mix_parlay_order})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
            case R.id.ll_mix_parlay_order:
                Bundle b = new Bundle();
                b.putSerializable(AppConstant.KEY_DATA, type);
                skipAct(MixOrderListActivity.class, b);
                finish();
                break;
            case R.id.tv_not_settled:
//                skipAct(BetOrderActivity.class);
                break;
            case R.id.tv_settled:
//                skipAct(BetSettlementFirstActivity.class);
                break;
        }
    }

    @Override
    public Activity getContextActivity() {
        return this;
    }

    @Override
    public void onUpdateMixSucceed(BettingParPromptBean bean) {
        getApp().setBetParList(bean);
        if (getApp().getBetParList() != null && getApp().getBetParList().getBetPar() != null && getApp().getBetParList().getBetPar().size() > 0) {
            tvMixParlayOrder.setText("" + getApp().getBetParList().getBetPar().size());
            llMixParlayOrder.setVisibility(View.VISIBLE);

        } else {
            tvMixParlayOrder.setText("0");
            llMixParlayOrder.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPopupWindowCreated(BasePopupWindow pop, int center) {
        createPopupWindow(pop);
        popWindow.showPopupGravityWindow(center, 0, 0);
    }


}
