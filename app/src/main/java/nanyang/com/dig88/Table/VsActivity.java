package nanyang.com.dig88.Table;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.BindString;
import nanyang.com.dig88.Adapter.MyFragmentPagerAdapter;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.ScaleBean;
import nanyang.com.dig88.Entity.VsCellBean;
import nanyang.com.dig88.Entity.VsCellBean1;
import nanyang.com.dig88.Entity.VsTableRowBean;
import nanyang.com.dig88.Entity.VsTableRowBean1;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.Thread.TableHttpHelper;
import nanyang.com.dig88.Table.Thread.ThreadEndT;
import nanyang.com.dig88.Table.entity.MatchBean;
import nanyang.com.dig88.Table.entity.PopMenuItemBean;
import nanyang.com.dig88.Table.entity.VsOtherDataBean;
import nanyang.com.dig88.Table.fragment.BetSingleDoubleFragment;
import nanyang.com.dig88.Table.fragment.CorrectFragment;
import nanyang.com.dig88.Table.fragment.ScaleFragment;
import nanyang.com.dig88.Table.utils.TableAdapterHelper;
import nanyang.com.dig88.Table.utils.TableDataHelper;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.myview.indicator.PagerSlidingTabStrip;
import xs.com.mylibrary.popupwindow.AbsListPopupWindow;

/**
 * Created by Administrator on 2015/10/22.
 */
public class VsActivity extends GameBaseActivity implements View.OnClickListener {
    @Bind(R.id.ballgame_tabs_pstabs)
    PagerSlidingTabStrip ballgameTabsPstabs;
    @Bind(R.id.ballgame_pager_vp)
    ViewPager ballgamePagerVp;
    @Bind(R.id.ballgame_bottom_ll)
    LinearLayout ballgameBottomLl;
    @Bind(R.id.vs_time_tv)
    TextView timeTv;
    @BindColor(R.color.white)
    int white;
    @BindString(R.string.clearance)
    String clearance;
    @BindString(R.string.scaleplate_asianplate)
    String scaleplate_asianplate;
    @BindString(R.string.full_half_goal)
    String single_double;
    @BindString(R.string.correct_score)
    String correct_score;
    @BindColor(R.color.colorPrimaryLight)
    int whiteLight;
    @Bind(R.id.img_left)
    ImageView img_left;
    @Bind(R.id.img_right)
    ImageView img_right;
    @Bind(R.id.tv_choice)
    TextView tv_choice;
    @Bind(R.id.ll_introduce)
    LinearLayout ll_introduce;
    Handler handler=new Handler();
    private List<BaseFragment> fragmentsList;
    private AbsListPopupWindow<PopMenuItemBean> menuPop;
    private MatchBean item;
    private String matchType;
    private ScaleFragment sf;
    private BetSingleDoubleFragment bf;
    private CorrectFragment cf;
    private int betType=0;
    private String url="";
    private TableHttpHelper<String> helper;
    Runnable updateTableRunnable = new Runnable() {
        @Override
        public void run() {
            if (url==null||url.equals("")) {
                return;
            }
            helper.updateData(url+"&t="+ System.currentTimeMillis(), "", TableDataHelper.ModelType.Running);
            handler.postDelayed(this, 30000);// 50是延时时长
        }
    };

    public static SpannableStringBuilder setColorStyle(String str, int[] color, String[] strColors) {

        SpannableStringBuilder style = new SpannableStringBuilder(str);
        for (int i = 0; i < color.length; i++) {
            int start = str.indexOf(strColors[i]);
            int end = start + strColors[i].length();
            style.setSpan(new ForegroundColorSpan(color[i]), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return style;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_vs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setNavigationIcon(null);
        toolbar.setBackgroundResource(R.mipmap.toolbar_bg);
        img_left.setOnClickListener(this);
        img_right.setOnClickListener(this);
        tv_choice.setText(scaleplate_asianplate);
        item=(MatchBean)getIntent().getSerializableExtra(AppConfig.ACTION_KEY_INITENT_DATA);
        matchType=getIntent().getStringExtra(AppConfig.ACTION_KEY_INITENT_STRING);
        betType=getIntent().getIntExtra(AppConfig.ACTION_KEY_INITENT_INT,0);
        titleTv.setBackgroundResource(0);
        titleTv.setTextSize(13);
        titleTv.setText(item.getHome() + "  VS  " + item.getAway());
//        timeTv.setText(item.getMatchDate()+"  |  "+item.getWorkingDate());
        timeTv.setText(item.getMatchDate());
        sf= new ScaleFragment();
        bf=new BetSingleDoubleFragment();
        cf=new CorrectFragment();
        sf.setTitle("    "+scaleplate_asianplate+"    ");
        bf.setTitle("      " + single_double + "      ");
        cf.setTitle("      " + correct_score + "      ");
        if(betType== TableAdapterHelper.ClearanceBet){
            fragmentsList=new ArrayList<>();
            fragmentsList.add(sf);
        }
        else
            fragmentsList= new ArrayList<>(Arrays.asList(sf,bf,cf));
        ballgamePagerVp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentsList));
        ballgameTabsPstabs.setViewPager(ballgamePagerVp);
        ballgameTabsPstabs.setTextColor(white);
        ballgameTabsPstabs.setBackGroundColorRes(R.color.blue_table);
        ballgameTabsPstabs.setSelectedBgColor(-1);
        ballgameTabsPstabs.setVisibility(View.GONE);
        ballgamePagerVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tv_choice.setText(scaleplate_asianplate);
                        ll_introduce.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        tv_choice.setText(single_double);
                        ll_introduce.setVisibility(View.GONE);
                        break;
                    case 2:
                        tv_choice.setText(correct_score);
                        ll_introduce.setVisibility(View.GONE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        getData();
    }

    public MatchBean getItem() {
        return item;
    }

    public int getBetType() {
        return betType;
    }

    public void setBetType(int betType) {
        this.betType = betType;
    }

    /*http://mobilesport.dig88api.com/_view/MoreBet_App.aspx?oId=9070882&home=Arsenal&away=DinamoZagreb&moduleTitle=UEFACHAMPIONSLEAGUE&date=03:45AM&isRun=false&t=1448371159386
    Oid	全场id
    Home	主队名
    Away	客队名
    ModuleTitle	联赛名
    Date	MatchDate(比赛时间)
    IsRun	是否为Running  isRun !=true时,有HalfTimeFullTime(HTFT)数据出现
    */
    private void getData() {
        if(betType==TableAdapterHelper.ClearanceBet){
            initFirstData( item.getOtherDataBean());
        }else {
            helper = new TableHttpHelper<String>(mContext, new View(mContext), new ThreadEndT<String>(null) {
                @Override
                public void endT(String result,int model) {
                    ScaleBean scaleBean = new Gson().fromJson(result, ScaleBean.class);
                    firstFragmentData(scaleBean);
                    secondFragmentData(scaleBean);
                    thirdFragmentData(result);
                }

                @Override
                public void endString(String result,int model) {
                    if(result!=null&&!result.equals("")){
//                        ScaleBean end=   new Gson().fromJson(result,new TypeToken<ScaleBean>() {
//                        }.getType());
//                        if(end!=null)
                            endT(result,model);
                    }
                }

                @Override
                public void endError(String error) {
                    Toast.makeText(mContext,error,Toast.LENGTH_SHORT).show();
                }
            });
            boolean isRunning = false;
            if (matchType.equals("Running"))
                isRunning = true;
            url= WebSiteUrl.SportUrl+"_view/MoreBet_App.aspx?oId=" + item.getHandicapBeans().get(0).getSocOddsId() + "&home=" + StringUtils.URLEncode(item.getHome()) + "&away=" + StringUtils.URLEncode(item.getAway()) + "&moduleTitle=" + StringUtils.URLEncode(item.getLeagueBean().getModuleTitle()) + "&date=" + StringUtils.URLEncode(item.getMatchDate()) + "&isRun=" + isRunning ;
            helper.getData(url+"&t="+System.currentTimeMillis(), "", TableDataHelper.ModelType.Running);
        }
    }

    private void initFirstData(VsOtherDataBean otherDataBean) {
        List<VsTableRowBean> rows=new ArrayList<VsTableRowBean>();
        int socOddsId_hf = 0;int socOddsId=0;
        if(item.getHandicapBeans().get(1).getSocOddsId()!=null&&!item.getHandicapBeans().get(1).getSocOddsId().equals(""))
            socOddsId_hf=Integer.valueOf(item.getHandicapBeans().get(1).getSocOddsId());
        if(item.getHandicapBeans().get(0).getSocOddsId()!=null&&!item.getHandicapBeans().get(0).getSocOddsId().equals(""))
            socOddsId=Integer.valueOf(item.getHandicapBeans().get(0).getSocOddsId());
        rows=Arrays.asList(new VsTableRowBean("1_par",Arrays.asList(new VsCellBean(getString(R.string.h1),"",0),new VsCellBean("",otherDataBean.getX121Odds(),socOddsId),new VsCellBean("",otherDataBean.getX121OddsFH(),socOddsId_hf)),true,false,"1  x  2",getString(R.string.against),getString(R.string.full_time),getString(R.string.half_time)),
                new VsTableRowBean("X_par",Arrays.asList(new VsCellBean(getString(R.string.dx),"",0),new VsCellBean("",otherDataBean.getX12XOdds(),socOddsId),new VsCellBean("",otherDataBean.getX12XOddsFH(),socOddsId_hf))),
                new VsTableRowBean("2_par",Arrays.asList(new VsCellBean(getString(R.string.a2),"",0),new VsCellBean("",otherDataBean.getX122Odds(),socOddsId),new VsCellBean("",otherDataBean.getX122OddsFH(),socOddsId_hf)),true),
                new VsTableRowBean("odd_par",Arrays.asList(new VsCellBean(getString(R.string.odd),"",0),new VsCellBean("",otherDataBean.getOddOdds(),socOddsId),new VsCellBean("","",socOddsId_hf)),true,false,getString(R.string.odd_even),getString(R.string.against),getString(R.string.full_time),getString(R.string.half_time)),
                new VsTableRowBean("even_par",Arrays.asList(new VsCellBean(getString(R.string.even),"",0),new VsCellBean("",otherDataBean.getEvenOdds(),socOddsId),new VsCellBean("","",socOddsId_hf)),true));
        sf.setData(rows);

    }

    private void thirdFragmentData(String result) {
        List<VsTableRowBean1> rows = new ArrayList<VsTableRowBean1>();
        Gson gson = new Gson();
  /*      if (matchType.equals("Running")) {*/

        Map<String, Object> map = new HashMap<String, Object>();
        map = gson.fromJson(result, map.getClass());
        LinkedTreeMap<String, Object> dataMapFT = (LinkedTreeMap) map.get("FT_CS");
        parseRows(rows, dataMapFT, false, getString(R.string.correct_full), setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), getString(R.string.a2));
        LinkedTreeMap<String, Object> dataMapFH = (LinkedTreeMap) map.get("FH_CS");
        parseRows(rows, dataMapFH, true, getString(R.string.correct_half), setColorStyle(getString(R.string.h1), new int[]{getResources().getColor(R.color.red_title)}, new String[]{getString(R.string.h1)}), setColorStyle(getString(R.string.dx), new int[]{getResources().getColor(R.color.blue)}, new String[]{getString(R.string.dx)}), getString(R.string.a2));
        cf.setData(rows);
    }

    private void parseRows(List<VsTableRowBean1> rows, LinkedTreeMap<String, Object> dataMap, boolean isHf, CharSequence s1, CharSequence s2, CharSequence s3, CharSequence s4) {
        if (dataMap == null || dataMap.size() < 1)
            return;
        Double sid = (Double) dataMap.get("oid");
        int oid = sid.intValue();
        Iterator<Map.Entry<String, Object>> iterator = dataMap.entrySet().iterator();
        Integer firstPre = null;
        Integer firstSuffix = null;
        List<Csr> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> next = iterator.next();
            String score = next.getKey();
            String odds = next.getValue().toString();
            if (score.equals("oid") || odds == null)
                continue;
            if (score.equals("AOS")) {
                Csr csr;
                if (isHf)
                    csr = new Csr(isHf, score, odds, "80");
                else
                    csr = new Csr(isHf, score, odds, "70");
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
            Csr csr = new Csr(isHf, score, odds, oddsType);
            list.add(csr);

        }

        int yu = list.size() % 3;
        int ce = list.size() / 3;
        rows.add(new VsTableRowBean1("csr", Arrays.asList(
                new VsCellBean1(list.get(0).getScore(), list.get(0).getOdds(), list.get(0).getType(), oid),
                new VsCellBean1(list.get(1).getScore(), list.get(1).getOdds(), list.get(1).getType(), oid),
                new VsCellBean1(list.get(2).getScore(), list.get(2).getOdds(), list.get(2).getType(), oid)), true, false, s1, s2, s3, s4).setFh(isHf));
        if (yu == 0) {
            if (ce - 1 > 1)
                for (int c = 1; c < ce - 1; c++) {
                    rows.add(new VsTableRowBean1("csr", Arrays.asList(
                            new VsCellBean1(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean1("csr", Arrays.asList(
                    new VsCellBean1(list.get(list.size() - 3).getScore(), list.get(list.size() - 3).getOdds(), list.get(list.size() - 3).getType(), oid),
                    new VsCellBean1(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean1(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid)), true).setFh(isHf));
        } else if (yu == 1) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean1("csr", Arrays.asList(
                            new VsCellBean1(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean1("csr", Arrays.asList(
                    new VsCellBean1(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean1("", "", "", oid),
                    new VsCellBean1("", "", "", oid)), true).setFh(isHf));
        } else if (yu == 2) {
            if (ce > 1)
                for (int c = 1; c < ce; c++) {
                    rows.add(new VsTableRowBean1("csr", Arrays.asList(
                            new VsCellBean1(list.get(c * 3).getScore(), list.get(c * 3).getOdds(), list.get(c * 3).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 1).getScore(), list.get(c * 3 + 1).getOdds(), list.get(c * 3 + 1).getType(), oid),
                            new VsCellBean1(list.get(c * 3 + 2).getScore(), list.get(c * 3 + 2).getOdds(), list.get(c * 3 + 2).getType(), oid))).setFh(isHf));
                }
            rows.add(new VsTableRowBean1("csr", Arrays.asList(
                    new VsCellBean1(list.get(list.size() - 2).getScore(), list.get(list.size() - 2).getOdds(), list.get(list.size() - 2).getType(), oid),
                    new VsCellBean1(list.get(list.size() - 1).getScore(), list.get(list.size() - 1).getOdds(), list.get(list.size() - 1).getType(), oid),
                    new VsCellBean1("", "", "", oid)), true).setFh(isHf));
        }
    }

    private void secondFragmentData(ScaleBean result) {
        List<VsTableRowBean> rows=new ArrayList<VsTableRowBean>();
        rows=Arrays.asList(new VsTableRowBean("htft",Arrays.asList(new VsCellBean(getString(R.string.hh),result.getHTFT().getHH(),"11",result.getHTFT().getOid()),new VsCellBean(getString(R.string.hd),result.getHTFT().getHD(),"10",result.getHTFT().getOid()),new VsCellBean(getString(R.string.ha),result.getHTFT().getHA(),"12",result.getHTFT().getOid())),true,false,getString(R.string.half_full_time),"","",""),
                new VsTableRowBean("htft",Arrays.asList(new VsCellBean(getString(R.string.dh),result.getHTFT().getDH(),"1",result.getHTFT().getOid()),new VsCellBean(getString(R.string.dd),result.getHTFT().getDD(),"0",result.getHTFT().getOid()),new VsCellBean(getString(R.string.da),result.getHTFT().getDA(),"2",result.getHTFT().getOid()))),
                new VsTableRowBean("htft",Arrays.asList(new VsCellBean(getString(R.string.ah),result.getHTFT().getAH(),"21",result.getHTFT().getOid()),new VsCellBean(getString(R.string.ad),result.getHTFT().getAD(),"20",result.getHTFT().getOid()),new VsCellBean(getString(R.string.aa),result.getHTFT().getAA(),"22",result.getHTFT().getOid())),true),
                new VsTableRowBean("fglg",Arrays.asList(new VsCellBean("HF",result.getFGLG().getHF(),"10",result.getFGLG().getOid()),new VsCellBean("HL",result.getFGLG().getHL(),"1",result.getFGLG().getOid()),new VsCellBean("AF",result.getFGLG().getAF(),"20",result.getFGLG().getOid()),
                        new VsCellBean("AL",result.getFGLG().getAL(),"2",result.getFGLG().getOid()),new VsCellBean("NO GOAL",result.getFGLG().getNO_GOAL(),"0",result.getFGLG().getOid())),true,true,getString(R.string.first_last_goal),"","",""),
                new VsTableRowBean("tg",Arrays.asList(new VsCellBean("0~1",result.getTG().getT0_1(),"1",result.getTG().getOid()),new VsCellBean("2~3",result.getTG().getT2_3(),"23",result.getTG().getOid()),new VsCellBean("4~6",result.getTG().getT4_6(),"46",result.getTG().getOid()),
                        new VsCellBean("","",0),new VsCellBean("7 & OVER",result.getTG().getT7_OVER(),"70",result.getTG().getOid())),true,true,getString(R.string.total_goals),"","",""));
        bf.setData(rows);

    }

    private void firstFragmentData(ScaleBean result) {
        List<VsTableRowBean> rows=new ArrayList<VsTableRowBean>();
        rows=Arrays.asList(new VsTableRowBean("1",Arrays.asList(new VsCellBean(getString(R.string.h1),"",result.getFT1x2().getOid()),new VsCellBean("",result.getFT1x2().getF1(),result.getFT1x2().getOid()),new VsCellBean("",result.getFH1x2().getF1(),result.getFH1x2().getOid())),true,false,"1  x  2",getString(R.string.against),getString(R.string.full_time),getString(R.string.half_time)),
                new VsTableRowBean("x",Arrays.asList(new VsCellBean(getString(R.string.dx),"",0),new VsCellBean("",result.getFT1x2().getX(),result.getFT1x2().getOid()),new VsCellBean("",result.getFH1x2().getX(),result.getFH1x2().getOid()))),
                new VsTableRowBean("2",Arrays.asList(new VsCellBean(getString(R.string.a2),"",0),new VsCellBean("",result.getFT1x2().getF2(),result.getFT1x2().getOid()),new VsCellBean("",result.getFH1x2().getF2(),result.getFH1x2().getOid())),true),
                new VsTableRowBean("dc","10",Arrays.asList(new VsCellBean("1X","",0),new VsCellBean("",result.getFTDC().getF1x(),result.getFTDC().getOid()),new VsCellBean("",result.getFHDC().getF1x(),result.getFHDC().getOid())),true,false,getString(R.string.double_chance),getString(R.string.against),getString(R.string.full_time),getString(R.string.half_time)),
                new VsTableRowBean("dc","12",Arrays.asList(new VsCellBean("12","",0),new VsCellBean("",result.getFTDC().getF12(),result.getFTDC().getOid()),new VsCellBean("",result.getFHDC().getF12(),result.getFHDC().getOid()))),
                new VsTableRowBean("dc","2",Arrays.asList(new VsCellBean("X2","",0),new VsCellBean("",result.getFTDC().getFx2(),result.getFTDC().getOid()),new VsCellBean("",result.getFHDC().getFx2(),result.getFHDC().getOid())),true),
                new VsTableRowBean("odd",Arrays.asList(new VsCellBean(getString(R.string.odd),"",0),new VsCellBean("",result.getFTOE().getODD(),result.getFTOE().getOid()),new VsCellBean("",result.getFHOE().getODD(),result.getFHOE().getOid())),true,false,getString(R.string.odd_even),getString(R.string.against),getString(R.string.full_time),getString(R.string.half_time)),
                new VsTableRowBean("even",Arrays.asList(new VsCellBean(getString(R.string.even),"",0),new VsCellBean("",result.getFTOE().getEVEN(),result.getFTOE().getOid()),new VsCellBean("",result.getFHOE().getEVEN(),result.getFHOE().getOid())),true));
        sf.setData(rows);
    }

    public void clickBack(View v) {
        finish();
    }

    public void clickMenu(View v) {
        showBottomMenuPop(ballgameBottomLl);
    }

    private void showBottomMenuPop(View v) {
        menuPop = new AbsListPopupWindow<PopMenuItemBean>(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {
            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.base_gridview_content;
            }

            @Override
            protected void popItemCLick(PopMenuItemBean o, int position) {

            }

            @Override
            protected void convertItem(ViewHolder helper, PopMenuItemBean item, int position) {
                helper.setImageResource(R.id.base_menu_item_iv, item.getDrawableRes());
                helper.setText(R.id.base_menu_item_tv1, item.getTitle());
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
       /* menuPop.setData(Arrays.asList(new PopMenuItemBean(R.drawable.flag_m_grey500, getString(R.string.selected_compared), getString(R.string.all_match)),
                new PopMenuItemBean(R.drawable.layers_m_grey500, getString(R.string.selected_handicap), getString(R.string.hk_handicap)),
                new PopMenuItemBean(R.drawable.slideshow_play_m_grey500, getString(R.string.live_video), ""),
                new PopMenuItemBean(R.drawable.store_m_grey500, getString(R.string.live_score), ""),
                new PopMenuItemBean(R.drawable.schedule_m_grey500, getString(R.string.sort_by_time), ""),
                new PopMenuItemBean(R.drawable.tap_and_play_m_grey500, getString(R.string.end_push), ""),
                new PopMenuItemBean(R.drawable.description_m_grey500, getString(R.string.score_push), "")
        ));*/
        int x = ballgameBottomLl.getLeft();
        int y = ballgameBottomLl.getBottom();
        menuPop.showPopupAtLocation(x, y - 245);
    }

    public void clickNoSettled(View v) {
        AppTool.activiyJump(mContext, BetOrderActivity.class);

    }

    public void clickSettled(View v) {
        AppTool.activiyJump(mContext, BetSettlementFirstActivity.class);
    }

    public void updateTableData() {
        if(betType!=TableAdapterHelper.ClearanceBet) {
            removeTableDataUpdate();
            handler.post(updateTableRunnable);// 打开定时器，执行操作
        }
    }

    public void removeTableDataUpdate() {
        handler.removeCallbacks(updateTableRunnable);// 关闭定时器处理
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTableData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeTableDataUpdate();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_left:
                viewPagerSlide("left");
                break;
            case R.id.img_right:
                viewPagerSlide("right");
                break;
        }
    }

    private void viewPagerSlide(String type){
       int current = ballgamePagerVp.getCurrentItem();
        if (type.equals("left")){
            if (current!=0){
                ballgamePagerVp.setCurrentItem(current-1,true);
            }
        }else {
            if (current!=2){
                ballgamePagerVp.setCurrentItem(current+1,true);
            }
        }
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
}
