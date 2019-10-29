package com.nanyang.app.main.home.sport.additional;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.adapter.MyFragmentPagerAdapter;
import com.nanyang.app.main.home.sport.football.SoccerCommonBetHelper;
import com.nanyang.app.main.home.sport.football.SoccerRunningBetHelper;
import com.nanyang.app.main.home.sport.main.BallBetHelper;
import com.nanyang.app.main.home.sport.model.AdditionBean;
import com.nanyang.app.main.home.sport.model.AfbClickResponseBean;
import com.nanyang.app.main.home.sport.model.BallInfo;
import com.nanyang.app.main.home.sport.model.SportInfo;
import com.nanyang.app.main.home.sport.model.VsCellBean;
import com.nanyang.app.main.home.sport.model.VsTableRowBean;
import com.nanyang.app.main.home.sport.superCombo.SuperComboBetHelper;
import com.nanyang.app.main.home.sportInterface.BetView;
import com.nanyang.app.main.home.sportInterface.IBetHelper;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.nanyang.app.AfbUtils.setColorStyle;


/**
 * Created by Administrator on 2015/10/22.
 */
public class VsActivity extends BaseToolbarActivity<VsPresenter> implements BetView<String> {
    @Bind(R.id.vs_time_tv)
    TextView vsTimeTv;
    /*    @Bind(R.id.ballgame_tabs_pstabs)
        PagerSlidingTabStrip ballgameTabsPstabs;*/
    @Bind(R.id.ballgame_pager_vp)
    ViewPager ballgamePagerVp;
    @Bind(R.id.ballgame_bottom_ll)
    LinearLayout ballgameBottomLl;
    //    @Bind(R.id.tv_mix_parlay_order)
//    TextView tvMixParlayOrder;
//    @Bind(R.id.ll_mix_parlay_order)
//    LinearLayout llMixParlayOrder;
    @Bind(R.id.tv_title)
    TextView tvTitle;


    private BallInfo item;
    private String matchType;
    private FirstFragment sf = new FirstFragment();
    private SecondFragment bf = new SecondFragment();
    private ThirdFragment cf = new ThirdFragment();
    private int currentIndex = 0;
    private MenuItemInfo oddsType;

    public String getChildParam() {
        return childParam;
    }

    private String childParam = "";

    public boolean isMixParlay() {
        return isMixParlay;
    }

    private boolean isMixParlay = false;
    private String url = "";
    private ArrayList<BaseVsFragment<VsTableRowBean>> fragmentsList;
    List<List<VsTableRowBean>> datas = new ArrayList<>();

    public MenuItemInfo<String> getType() {
        return type;
    }

    private MenuItemInfo<String> type;

    public IBetHelper getHelper() {
        return helper;
    }

    IBetHelper helper;
    String paramT = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        item = (BallInfo) getIntent().getSerializableExtra(AppConstant.KEY_DATA);
        type = (MenuItemInfo<String>) getIntent().getSerializableExtra(AppConstant.KEY_DATA2);
        if (getIntent().getSerializableExtra(AppConstant.KEY_DATA3) != null) {
            oddsType = (MenuItemInfo) getIntent().getSerializableExtra(AppConstant.KEY_DATA3);
        }
        tvToolbarRight.setVisibility(View.GONE);
        matchType = type.getType();
        switch (matchType) {
            case "Early":
                childParam = "&today=e";
                break;
            case "Today":
            case "Running":
                childParam = "&today=t";
                break;
        }
        isMixParlay = type.getRes() == 1;
        String parent = type.getParent();

        fragmentsList = new ArrayList<BaseVsFragment<VsTableRowBean>>();

        sf.setTitle(getString(R.string.scaleplate_asianplate));
        bf.setTitle(getString(R.string.single_double));
        cf.setTitle(getString(R.string.correct_score));
        createPresenter(new VsPresenter(this));
        if (parent.equals(getString(R.string.SuperCombo))) {
            isMixParlay = true;
            fragmentsList.add(sf);
            initFirstData(item);
            helper = new SuperComboBetHelper(this);
        } else {
            if (isMixParlay) {
                fragmentsList.add(sf);
                initFirstData(item);
            } else {
                fragmentsList.add(sf);
                fragmentsList.add(bf);
                fragmentsList.add(cf);

            }
        }

        if (parent.equals(getString(R.string.football))) {

        } else if (parent.equals(getString(R.string.Myanmar_Odds))) {
            paramT = "&T=MB2";
//            helper = new MyanmarBetHelper(this);
            //http://main55.afb88.com/_view/pgajaxS.axd?T=MB2&oId=12795891&home=%E5%9F%83%E6%96%AF%E8%92%82%E7%89%B9%E6%96%AF&away=%E4%BF%9D%E5%9C%B0%E8%8A%B1%E9%AB%98&moduleTitle=%E5%8D%97%E7%BE%8E%E8%A7%A3%E6%94%BE%E8%80%85%E6%9D%AF&date=08:45AM&lang=eng&isRun=true&_=1495764426627
            //http://a8206d.a36588.com/_view/pgajaxS.axd?T=MB2&oId=12270813&home=Rochdale&away=Millwall&moduleTitle=ENGLISH%20LEAGUE%20ONE&date=03:45AM&lang=EN-US&isRun=false&_=1490092254432
        } else if (parent.equals(getString(R.string.Europe_View))) {
            paramT = "&T=MB2";
//            helper = new EuropeBetHelper(this);
        }
        helper = new SoccerCommonBetHelper(this);
        if (isMixParlay) {
            helper = new BallBetHelper(this) {
                @Override
                protected String getBallG() {
                    return "1";
                }
            };
        } else {
            helper = new SoccerCommonBetHelper(this);
            if (matchType.equals("Running"))
                helper = new SoccerRunningBetHelper(this);
        }
        assert tvToolbarTitle != null;
        tvToolbarTitle.setBackgroundResource(0);
        tvToolbarTitle.setText(item.getHome() + "  VS  " + item.getAway());
        tvToolbarTitle.setTextSize(12);
        ViewGroup.LayoutParams layoutParams = tvToolbarTitle.getLayoutParams();
        layoutParams.width = Toolbar.LayoutParams.MATCH_PARENT;
        vsTimeTv.setText(item.getMatchDate());


        tvTitle.setText(sf.getTitle());
        ballgamePagerVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
//        ballgameTabsPstabs.setViewPager(ballgamePagerVp);
//        ballgameTabsPstabs.setTextColorResource(R.color.green_light);
//        ballgameTabsPstabs.setBackGroundColorRes(R.color.green_light_white);
//        ballgameTabsPstabs.setSelectedBgColor(-1);


        ballgamePagerVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                BaseVsFragment<VsTableRowBean> baseVsFragment = fragmentsList.get(position);
                baseVsFragment.setData(datas.get(position));
                tvTitle.setText(baseVsFragment.getTitle());
                currentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public SportInfo getItem() {
        return item;
    }


    private void initFirstData(BallInfo item) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        int socOddsId_hf = 0;
        int socOddsId = 0;
        if (item.getSocOddsId_FH() != null && !item.getSocOddsId_FH().equals(""))
            socOddsId_hf = Integer.valueOf(item.getSocOddsId_FH());
        if (item.getSocOddsId() != null && !item.getSocOddsId().equals(""))
            socOddsId = Integer.valueOf(item.getSocOddsId());
        rows = Arrays.asList(new VsTableRowBean("1_par", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_1Odds(), socOddsId), new VsCellBean("", item.getX12_1Odds_FH(), socOddsId_hf)), true, false, setColorStyle("1  x  2", new int[]{getResources().getColor(R.color.red_title), getResources().getColor(R.color.blue)}, new String[]{"1", "x"}), "", "", ""),
                new VsTableRowBean("X_par", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_XOdds(), socOddsId), new VsCellBean("", item.getX12_XOdds_FH(), socOddsId_hf))),
                new VsTableRowBean("2_par", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", item.getHasX12().equals("0") ? "" : item.getX12_2Odds(), socOddsId), new VsCellBean("", item.getX12_2Odds_FH(), socOddsId_hf)), true),
                new VsTableRowBean("odd_par", Arrays.asList(new VsCellBean(getString(R.string.ODD), "", 0), new VsCellBean("", item.getHasOE().equals("0") ? "" : item.getOddOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true, false, getString(R.string.odd_even), "", "", ""),
                new VsTableRowBean("even_par", Arrays.asList(new VsCellBean(getString(R.string.EVEN), "", 0), new VsCellBean("", item.getHasOE().equals("0") ? "" : item.getEvenOdds(), socOddsId), new VsCellBean("", "", socOddsId_hf)), true));
        datas.add(rows);
        sf.setData(rows);
    }

    public static class Csr implements Serializable {
        boolean isFH;
        String score;
        String odds;
        String type;

        public Csr(boolean isFH, String score, String odds, String type) {
            this.isFH = isFH;
            this.score = score;
            this.odds = odds;
            this.type = type;
        }

        public boolean isFH() {
            return isFH;
        }

        public void setFH(boolean FH) {
            isFH = FH;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getOdds() {
            return odds;
        }

        public void setOdds(String odds) {
            this.odds = odds;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    private void parseRows(List<VsTableRowBean> rows, LinkedTreeMap<String, Object> dataMap, boolean isHf, CharSequence s1, CharSequence s2, CharSequence s3, CharSequence s4) {
        if (dataMap == null || dataMap.size() < 1)
            return;
        Double sid = (Double) dataMap.get("oid");
        int oid = sid.intValue();
        Iterator<Map.Entry<String, Object>> iterator = dataMap.entrySet().iterator();
        Integer firstPre = null;
        Integer firstSuffix = null;
        List<VsActivity.Csr> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            String score = next.getKey();
            String odds = next.getValue().toString();
            if (score.equals("oid") || odds == null)
                continue;
            if (score.equals("AOS")) {
                VsActivity.Csr csr;
                if (isHf)
                    csr = new VsActivity.Csr(isHf, score, odds, "80");
                else
                    csr = new VsActivity.Csr(isHf, score, odds, "70");
                list.add(csr);
                continue;
            }

            String[] split = score.split(":");
            if (split.length < 2)
                continue;
            if (firstPre == null || firstSuffix == null) {
                firstPre = Integer.valueOf(split[0]);
                firstSuffix = Integer.valueOf(split[1]);
            }
            int pre = 0;
            int suffix = 0;
            if (firstPre != null || firstSuffix != null) {
                pre = Integer.valueOf(split[0]) - firstPre;
                suffix = Integer.valueOf(split[1]) - firstSuffix;
            }
            String oddsType = "";
            if (pre > 0) {
                oddsType = pre + "" + suffix;
            } else {
                oddsType = suffix + "";
            }
            VsActivity.Csr csr = new VsActivity.Csr(isHf, score, odds, oddsType);
            list.add(csr);

        }

        int yu = list.size() % 3;
        int ce = list.size() / 3;
        rows.add(new VsTableRowBean("csr", Arrays.asList(
                new VsCellBean(list.get(0).getScore(), list.get(0).getOdds(), list.get(0).getType(), oid),
                new VsCellBean(list.get(1).getScore(), list.get(1).getOdds(), list.get(1).getType(), oid),
                new VsCellBean(list.get(2).getScore(), list.get(2).getOdds(), list.get(2).getType(), oid)), true, false, s1, s2, s3, s4).setFh(isHf));
        if (yu == 0) {
            if (ce - 1 > 1)
                for (int c = 1; c < ce - 1; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 3).getScore(), list.get(list.size() - 3).getOdds(), list.get(list.size() - 3).getType(), oid),
                    new VsCellBean(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid)), true).setFh(isHf));
        } else if (yu == 1) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean("", "", "", oid),
                    new VsCellBean("", "", "", oid)), true).setFh(isHf));
        } else if (yu == 2) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean("csr", Arrays.asList(
                            new VsCellBean(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean("csr", Arrays.asList(
                    new VsCellBean(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean("", "", "", oid)), true).setFh(isHf));
        }
    }

    private void thirdFragmentData(String str) {
        List<VsTableRowBean> rows = new ArrayList<VsTableRowBean>();
        Gson gson = new Gson();
  /*      if (matchType.equals("Running")) {*/

        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(str, map.getClass());
        LinkedTreeMap<String, Object> dataMapFT = (LinkedTreeMap<String, Object>) map.get("FT_CS");
        parseRows(rows, dataMapFT, false, getString(R.string.correct_full), setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), getString(R.string.a2));
        LinkedTreeMap<String, Object> dataMapFH = (LinkedTreeMap<String, Object>) map.get("FH_CS");
        parseRows(rows, dataMapFH, true, getString(R.string.correct_half), setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), getString(R.string.a2));
        datas.add(rows);
        cf.setData(rows);

    }

    private void secondFragmentData(AdditionBean result) {
        List<VsTableRowBean> rows;
        rows = new ArrayList<>(Arrays.asList(new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.hh), result.getHTFT().getHH(), /*"&sc=" +*/ "11", result.getHTFT().getOid()), new VsCellBean(getString(R.string.hd), result.getHTFT().getHD(), /*"&sc=" +*/ "10", result.getHTFT().getOid()), new VsCellBean(getString(R.string.ha), result.getHTFT().getHA(), /*"&sc=" +*/ "12", result.getHTFT().getOid())), true, false, getString(R.string.half_full_time), "", "", ""),
                new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.dh), result.getHTFT().getDH(), /*"&sc=" +*/ "1", result.getHTFT().getOid()), new VsCellBean(getString(R.string.dd), result.getHTFT().getDD(), /*"&sc=" +*/ "0", result.getHTFT().getOid()), new VsCellBean(getString(R.string.da), result.getHTFT().getDA(), /*"&sc=" +*/ "2", result.getHTFT().getOid()))),
                new VsTableRowBean("htft", Arrays.asList(new VsCellBean(getString(R.string.ah), result.getHTFT().getAH(), /*"&sc=" +*/ "21", result.getHTFT().getOid()), new VsCellBean(getString(R.string.ad), result.getHTFT().getAD(), /*"&sc=" +*/ "20", result.getHTFT().getOid()), new VsCellBean(getString(R.string.aa), result.getHTFT().getAA(), /*"&sc=" +*/ "22", result.getHTFT().getOid())), true),
                new VsTableRowBean("fglg", Arrays.asList(new VsCellBean("HF", result.getFGLG().getHF(), /*"&sc=" +*/ "10", result.getFGLG().getOid()), new VsCellBean("HL", result.getFGLG().getHL(), /*"&sc=" +*/ "1", result.getFGLG().getOid()), new VsCellBean("AF", result.getFGLG().getAF(), /*"&sc=" +*/ "20", result.getFGLG().getOid()),
                        new VsCellBean("AL", result.getFGLG().getAL(), /*"&sc=" +*/ "2", result.getFGLG().getOid()), new VsCellBean("NO GOAL", result.getFGLG().getNO_GOAL(), /*"&sc=" +*/ "0", result.getFGLG().getOid())), true, true, getString(R.string.first_last_goal), "", "", ""),
                new VsTableRowBean("tg", Arrays.asList(new VsCellBean("0~1", result.getTG().getT0_1(), /*"&sc=" +*/ "1", result.getTG().getOid()), new VsCellBean("2~3", result.getTG().getT2_3(), /*"&sc=" +*/ "23", result.getTG().getOid()), new VsCellBean("4~6", result.getTG().getT4_6(), /*"&sc=" +*/ "46", result.getTG().getOid()),
                        new VsCellBean("", "", 0), new VsCellBean("7 & OVER", result.getTG().getT7_OVER(), /*"&sc=" +*/ "70", result.getTG().getOid())), true, true, getString(R.string.total_goals), "", "", "")
        ));
        if (result.getHOMETEAMTG() != null) {
            rows.add(new VsTableRowBean("", Arrays.asList(new VsCellBean(result.getHOMETEAMTG().getFT_OU() + "      " + getString(R.string.O), result.getHOMETEAMTG().getFT_O(), "over", "", result.getHOMETEAMTG().getOid()), new VsCellBean("" + getString(R.string.U), result.getHOMETEAMTG().getFT_U(), "under", "", result.getHOMETEAMTG().getOid()), new VsCellBean(result.getHOMETEAMTG().getFH_OU() + "      " + "FH." + getString(R.string.O), result.getHOMETEAMTG().getFH_O(), "over", "", result.getHOMETEAMTG().getOid_FH()),
                    new VsCellBean("FH." + getString(R.string.U), result.getHOMETEAMTG().getFH_U(), "under", "", result.getHOMETEAMTG().getOid_FH())), true, true, getString(R.string.HOME_TEAM_TOTAL_GOALS), "", "", ""));

        }
        if (result.getAWAYTEAMTG() != null) {
            rows.add(new VsTableRowBean("", Arrays.asList(new VsCellBean(result.getAWAYTEAMTG().getFT_OU() + "      " + getString(R.string.O), result.getAWAYTEAMTG().getFT_O(), "over", "", result.getAWAYTEAMTG().getOid()), new VsCellBean("" + getString(R.string.U), result.getAWAYTEAMTG().getFT_U(), "under", "", result.getAWAYTEAMTG().getOid()), new VsCellBean(result.getAWAYTEAMTG().getFH_OU() + "      " + "FH." + getString(R.string.O), result.getAWAYTEAMTG().getFH_O(), "over", "", result.getAWAYTEAMTG().getOid_FH()),
                    new VsCellBean("FH." + getString(R.string.U), result.getAWAYTEAMTG().getFH_U(), "under", "", result.getAWAYTEAMTG().getOid_FH())), true, true, getString(R.string.AWAY_TEAM_TOTAL_GOALS), "", "", "")
            );
        }
        datas.add(rows);
        bf.setData(rows);


    }

    private void firstFragmentData(AdditionBean result) {
        List<VsTableRowBean> rows;
        rows = new ArrayList<>(Arrays.asList(new VsTableRowBean("1", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), "", result.getFT1x2().getOid()), new VsCellBean("", result.getFT1x2().getF1(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getF1(), result.getFH1x2().getOid())), true, false, setColorStyle("1  x  2", new int[]{getResources().getColor(R.color.red_title), getResources().getColor(R.color.blue)}, new String[]{"1", "x"}), "", "", ""),
                new VsTableRowBean("X", Arrays.asList(new VsCellBean(setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), "", 0), new VsCellBean("", result.getFT1x2().getX(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getX(), result.getFH1x2().getOid()))),
                new VsTableRowBean("2", Arrays.asList(new VsCellBean(getString(R.string.a2), "", 0), new VsCellBean("", result.getFT1x2().getF2(), result.getFT1x2().getOid()), new VsCellBean("", result.getFH1x2().getF2(), result.getFH1x2().getOid())), true),
                new VsTableRowBean("dc", "10", Arrays.asList(new VsCellBean(setColorStyle("1X", new int[]{getResources().getColor(R.color.red_title), getResources().getColor(R.color.blue)}, new String[]{"1", "X"}), "", 0), new VsCellBean("", result.getFTDC().getF1x(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getF1x(), result.getFHDC().getOid())), true, false, getString(R.string.double_chance), "", "", ""),
                new VsTableRowBean("dc", "12", Arrays.asList(new VsCellBean(setColorStyle("12", new int[]{getResources().getColor(R.color.red_title)}, new String[]{"1"}), "", 0), new VsCellBean("", result.getFTDC().getF12(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getF12(), result.getFHDC().getOid()))),
                new VsTableRowBean("dc", "2", Arrays.asList(new VsCellBean(setColorStyle("X2", new int[]{getResources().getColor(R.color.blue)}, new String[]{"X"}), "", 0), new VsCellBean("", result.getFTDC().getFx2(), result.getFTDC().getOid()), new VsCellBean("", result.getFHDC().getFx2(), result.getFHDC().getOid())), true),
                new VsTableRowBean("odd", Arrays.asList(new VsCellBean(getString(R.string.ODD), "", 0), new VsCellBean("", result.getFTOE().getODD(), result.getFTOE().getOid()), new VsCellBean("", result.getFHOE().getODD(), result.getFHOE().getOid())), true, false, getString(R.string.odd_even), "", "", ""),
                new VsTableRowBean("even", Arrays.asList(new VsCellBean(getString(R.string.EVEN), "", 0), new VsCellBean("", result.getFTOE().getEVEN(), result.getFTOE().getOid()), new VsCellBean("", result.getFHOE().getEVEN(), result.getFHOE().getOid())), true)
        ));
        if (result.getFT15MINSHANDICAP_OVER_UNDER_0() != null) {
            rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_0().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_0().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_0().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(00:00-15:00)", "", "", ""));
            rows.add(new VsTableRowBean("under", Arrays.asList(new VsCellBean(getString(R.string.under), "", 0), new VsCellBean("", "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_0().getU(), result.getFT15MINSHANDICAP_OVER_UNDER_0().getOid())), true));
        }
        if (result.getFT15MINSHANDICAP_OVER_UNDER_15() != null) {
            rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_15().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_15().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_15().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(15:01-30:00)", "", "", ""));
            rows.add(new VsTableRowBean("under", Arrays.asList(new VsCellBean(getString(R.string.under), "", 0), new VsCellBean("", "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_15().getU(), result.getFT15MINSHANDICAP_OVER_UNDER_15().getOid())), true));
        }
        if (result.getFT15MINSHANDICAP_OVER_UNDER_30_N() != null) {
            rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_30_N().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_30_N().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_30_N().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(30:01-45:00)", "", "", ""));
            rows.add(new VsTableRowBean("under", Arrays.asList(new VsCellBean(getString(R.string.under), "", 0), new VsCellBean("", "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_30_N().getU(), result.getFT15MINSHANDICAP_OVER_UNDER_30_N().getOid())), true));
        }
        if (result.getFT15MINSHANDICAP_OVER_UNDER_45() != null) {
            rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_45().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_45().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_45().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(45:01-60:00)", "", "", ""));
            rows.add(new VsTableRowBean("under", Arrays.asList(new VsCellBean(getString(R.string.under), "", 0), new VsCellBean("", "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_45().getU(), result.getFT15MINSHANDICAP_OVER_UNDER_45().getOid())), true));
        }
        if (result.getFT15MINSHANDICAP_OVER_UNDER_60() != null) {
            rows.add(new VsTableRowBean("over", Arrays.asList(new VsCellBean(getString(R.string.over), "", 0), new VsCellBean(result.getFT15MINSHANDICAP_OVER_UNDER_60().getFT_OU(), "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_60().getO(), result.getFT15MINSHANDICAP_OVER_UNDER_60().getOid())), true, false, getString(R.string.FT15MINSHANDICAP_OVER_UNDER) + "(60:01-75:00)", "", "", ""));
            rows.add(new VsTableRowBean("under", Arrays.asList(new VsCellBean(getString(R.string.under), "", 0), new VsCellBean("", "", 0), new VsCellBean("", result.getFT15MINSHANDICAP_OVER_UNDER_60().getU(), result.getFT15MINSHANDICAP_OVER_UNDER_60().getOid())), true));
        }
        datas.add(rows);
        sf.setData(rows);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!isMixParlay)
            presenter.scale(oddsType != null ? oddsType.getType() : "", item, matchType);
        onBetEnd();
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
    public void onGetData(String data) {
        AdditionBean bean = new Gson().fromJson(data, AdditionBean.class);
        firstFragmentData(bean);
        secondFragmentData(bean);
        thirdFragmentData(data);
    }

    @OnClick({R.id.tv_back, R.id.tv_not_settled, R.id.tv_settled, /*R.id.ll_mix_parlay_order,*/ R.id.iv_lift_nav, R.id.iv_right_nav})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_back:
                finish();
                break;
//            case R.id.ll_mix_parlay_order:
//                if (getApp().getBetAfbList() == null || getApp().getBetAfbList().gettList() == null || getApp().getBetAfbList().gettList().size() < 1)
//                    return;
//                if (getApp().getBetAfbList().getList().size() == 1) {
//                    String refreshOddsUrl = getApp().getRefreshCurrentOddsUrl();
//                    helper.getRefreshOdds(refreshOddsUrl);
//                } else if (getApp().getBetAfbList().getList().size() > 1) {
//                    Bundle b = new Bundle();
//                    b.putSerializable(AppConstant.KEY_DATA, type);
//                    skipAct(MixOrderListActivity.class, b);
//                    finish();
//                }

//                break;
            case R.id.tv_not_settled:
                Bundle bundle = new Bundle();
                bundle.putString(AppConstant.KEY_STRING, getString(R.string.stake));
//                skipAct(PersonCenterActivity.class, bundle);
                break;
            case R.id.tv_settled:
                Bundle bundle2 = new Bundle();
                bundle2.putString(AppConstant.KEY_STRING, getString(R.string.statement));
//                skipAct(PersonCenterActivity.class, bundle2);
                break;
            case R.id.iv_lift_nav:
                if (currentIndex > 0)
                    ballgamePagerVp.setCurrentItem(currentIndex - 1);
                break;
            case R.id.iv_right_nav:
                if (currentIndex < fragmentsList.size() - 1)
                    ballgamePagerVp.setCurrentItem(currentIndex + 1);
                break;
        }
    }


    @Override
    public void onUpdateMixSucceed(AfbClickResponseBean bean) {
        BaseVsFragment<VsTableRowBean> baseVsFragment = fragmentsList.get(currentIndex);
        baseVsFragment.getAdapter().notifyDataSetChanged();
        getApp().setBetAfbList(bean);
        onBetEnd();

    }

    private void onBetEnd() {
//        if (getApp().getBetParList() != null && getApp().getBetParList().gettList() != null && getApp().getBetParList().gettList().size() > 0) {
//            tvMixParlayOrder.setText("" + getApp().getBetParList().getList().size());
//            llMixParlayOrder.setVisibility(View.VISIBLE);
//
//        } else {
//            tvMixParlayOrder.setText("0");
//            llMixParlayOrder.setVisibility(View.GONE);
//        }
    }

    @Override
    public void onBetSuccess(String betResult) {
        super.onBetSuccess(betResult);
        onBetEnd();
    }
}
