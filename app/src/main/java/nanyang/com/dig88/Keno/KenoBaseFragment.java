package nanyang.com.dig88.Keno;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;
import gaming178.com.mylibrary.base.AdapterViewContent;
import gaming178.com.mylibrary.base.QuickAdapterImp;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.myview.mylistview.HorizontalListView;
import nanyang.com.dig88.Entity.DigGameOddsBean;
import nanyang.com.dig88.Entity.LotteryStateGameBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2016/6/25.
 */
public abstract class KenoBaseFragment extends BaseFragment{
    protected AdapterViewContent<String> resultContent;
    protected AdapterViewContent<String> headerContent;
    protected AdapterViewContent<String[]> totalContent;
    protected KenoHomeActivity act;
    protected ArrayList<DigGameOddsBean> selectedOddsList;
    protected LotteryStateGameBean selectedStateBean;
    protected DigGameOddsBean upDownBean;
    protected DigGameOddsBean evensOddsBean;
    protected DigGameOddsBean elementBean;
    protected DigGameOddsBean combinationBean;
    protected DigGameOddsBean evenOddBean;
    protected DigGameOddsBean bigSmallBean;
    protected ArrayList<KenoHeaderResultBean> headerList;
    protected KenoBetPop pop;
    @BindView(R.id.btn_bet_big)
    LinearLayout btnBetBig;
    @BindView(R.id.btn_bet_tie)
    LinearLayout btnBetTie;
    @BindView(R.id.btn_bet_small)
    LinearLayout btnBetSmall;
    @BindView(R.id.btn_bet_odd)
    LinearLayout btnBetOdd;
    @BindView(R.id.btn_bet_even)
    LinearLayout btnBetEven;
    @BindView(R.id.btn_bet_small_even)
    LinearLayout btnBetSmallEven;
    @BindView(R.id.btn_bet_small_odd)
    LinearLayout btnBetSmallOdd;
    @BindView(R.id.btn_bet_big_even)
    LinearLayout btnBetBigEven;
    @BindView(R.id.btn_bet_big_odd)
    LinearLayout btnBetBigOdd;
    @BindView(R.id.btn_bet_wood)
    LinearLayout btnBetWood;
    @BindView(R.id.btn_bet_water)
    LinearLayout btnBetWater;
    @BindView(R.id.btn_bet_fire)
    LinearLayout btnBetFire;
    @BindView(R.id.btn_bet_earth)
    LinearLayout btnBetEarth;
    @BindView(R.id.btn_bet_gold)
    LinearLayout btnBetGold;
    @BindView(R.id.tv_keno_big_odds)
    TextView tvKenoBigOdds;
    @BindView(R.id.tv_keno_tie_odds)
    TextView tvKenoTieOdds;
    @BindView(R.id.tv_keno_small_odds)
    TextView tvKenoSmallOdds;
    @BindView(R.id.tv_keno_odd_odds)
    TextView tvKenoOddOdds;
    @BindView(R.id.tv_keno_even_odds)
    TextView tvKenoEvenOdds;
    @BindView(R.id.tv_keno_seven_odds)
    TextView tvKenoSevenOdds;
    @BindView(R.id.tv_keno_sodd_odds)
    TextView tvKenoSoddOdds;
    @BindView(R.id.tv_keno_beven_odds)
    TextView tvKenoBevenOdds;
    @BindView(R.id.tv_keno_bodd_odds)
    TextView tvKenoBoddOdds;
    @BindView(R.id.tv_keno_gold_odds)
    TextView tvKenoGoldOdds;
    @BindView(R.id.tv_keno_wood_odds)
    TextView tvKenoWoodOdds;
    @BindView(R.id.tv_keno_water_odds)
    TextView tvKenoWaterOdds;
    @BindView(R.id.tv_keno_fire_odds)
    TextView tvKenoFireOdds;
    @BindView(R.id.tv_keno_earth_odds)
    TextView tvKenoEarthOdds;
    @BindView(R.id.fl_keno_progress_parent)
    FrameLayout flKenoProgressParent;
    @BindView(R.id.tv_keno_timer)
    TextView tvKenoTimer;
    @BindView(R.id.tv_keno_series_number)
    TextView tvKenoSeriesNumber;
    @BindView(R.id.tv_keno_country)
    TextView tvKenoCountry;
    @BindView(R.id.tv_enter)
    TextView tvEnter;
    @BindView(R.id.fgv_keno_result_header)
    GridView fgvKenoResultHeader;
    @BindView(R.id.fgv_keno_result)
    GridView fgvKenoResult;
    @BindView(R.id.hlst_keno_content)
    HorizontalListView hlstKenoContent;
    private String[] resultStrings;
    private List<String[]> totalListStrings;

    public void progressVisibility(boolean b){
        if(getAct().isFinishing()||!isAdded())
            return;
        if(b){
            flKenoProgressParent.setVisibility(View.VISIBLE);
        }else{
            flKenoProgressParent.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        act=(KenoHomeActivity) getActivity();
        resultContent = new AdapterViewContent<>(mContext, fgvKenoResult);
        headerContent = new AdapterViewContent<>(mContext, fgvKenoResultHeader);
        final int pid5= ScreenUtil.dip2px(mContext,3);
        final int pid10=ScreenUtil.dip2px(mContext,6);
        resultContent.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv = helper.retrieveView(R.id.text_tv1);

                tv.setPadding(pid5, pid10, pid5, pid10);
                helper.setText(R.id.text_tv1, item);
                helper.setBackgroundRes(R.id.text_tv1, R.color.white);
                convertResult(helper, item, position);

            }
        });
        headerContent.setBaseAdapter(new QuickAdapterImp<String>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_text;
            }

            @Override
            public void convert(ViewHolder helper, String item, int position) {
                TextView tv = helper.retrieveView(R.id.text_tv1);

                tv.setPadding(pid5, pid10, pid5, pid10);
                helper.setText(R.id.text_tv1, item);
                helper.setTextColorRes(R.id.text_tv1, R.color.white);
                helper.setBackgroundRes(R.id.text_tv1, R.color.black);
                convertHeader(helper, item, position);
            }
        });
        totalContent = new AdapterViewContent<String[]>(mContext, hlstKenoContent);
        totalContent.setBaseAdapter(new QuickAdapterImp<String[]>() {
            @Override
            public int getBaseItemResource() {
                return R.layout.item_keno_total_result;
            }

            @Override
            public void convert(ViewHolder helper, String[] item, int position) {
                int m = position % 2;
                LinearLayout parent = helper.retrieveView(R.id.view_keno_total);
                for (int i = 0; i < item.length; i++) {
                    TextView tv = (TextView) parent.getChildAt(i);
                    tv.setText(item[i]);
                    if (0 == m) {
                        tv.setTextColor(getResources().getColor(R.color.orange_word));
                    } else {
                        tv.setTextColor(getResources().getColor(R.color.black_title));
                    }
                }

            }
        });
        initClick();
        updateOddsUI();
        updateResultUI();
    }

    protected void initClick() {
        flKenoProgressParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment();

            }
        });
        btnBetBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBig(v);
            }
        });
        btnBetSmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSmall(v);
            }
        });
        btnBetTie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTie(v);
            }
        });
        btnBetBig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBig(v);
            }
        });
        btnBetEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEven(v);
            }
        });
        btnBetOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOdd(v);
            }
        });
        btnBetBigEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBigEven(v);
            }
        });
        btnBetBigOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBigOdd(v);
            }
        });
        btnBetEarth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEarth(v);
            }
        });
        btnBetGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGold(v);
            }
        });
        btnBetFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickFire(v);
            }
        });
        btnBetWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickWater(v);
            }
        });
        btnBetWood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickWood(v);
            }
        });
        btnBetSmallEven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSmallEven(v);
            }
        });
        btnBetSmallOdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickSmallOdd(v);
            }
        });

    }

    protected void changeFragment() {
        getAct().changeFragment();
    }

    protected abstract void convertHeader(ViewHolder helper, String item, int position);

    protected abstract void convertResult(ViewHolder helper, String item, int position);

    @Override
    public KenoHomeActivity getAct(){
        return act;
    }
    protected void showBetPop(View view,KenoBetBean betBean) {
        pop= new KenoBetPop(mContext,view,800, LinearLayout.LayoutParams.WRAP_CONTENT);
        pop.setBetBean(betBean);
        pop.showPopupCenterWindow();
    }

    public void updateOddsUI(){
        if(getAct().isFinishing()||!isAdded())
            return;
        this.selectedOddsList=getAct().getSelectedOddsList();
        this.selectedStateBean=getAct().getSelectedStateBean();
        this.upDownBean=getAct().getUpDownBean();
        this.evensOddsBean=getAct().getEvensOddsBean();
        this.elementBean=getAct().getElementBean();
        this.combinationBean=getAct().getCombinationBean();
        this.evenOddBean=getAct().getEvenOddBean();
        this.bigSmallBean=getAct().getBigSmallBean();

        if(selectedOddsList==null||selectedStateBean==null)
            return;
        if(tvKenoTimer.getText().toString().equals("00:00"))
            progressVisibility(true);
        else
            progressVisibility(false);
        tvKenoCountry.setText(selectedStateBean.getGame_name());
        String period = selectedStateBean.getPeriod();
        tvKenoSeriesNumber.setText(period);
        tvKenoBigOdds.setText(getAct().getBigSmallBean().getFactor());
        tvKenoTieOdds.setText(getAct().getBigSmallBean().getFactor3());
        tvKenoSmallOdds.setText(getAct().getBigSmallBean().getFactor());
        tvKenoEvenOdds.setText(getAct().getEvenOddBean().getFactor());
        tvKenoOddOdds.setText(getAct().getEvenOddBean().getFactor());
        tvKenoBevenOdds.setText(getAct().getCombinationBean().getFactor());
        tvKenoSevenOdds.setText(getAct().getCombinationBean().getFactor());
        tvKenoBoddOdds.setText(getAct().getCombinationBean().getFactor());
        tvKenoSoddOdds.setText(getAct().getCombinationBean().getFactor());
        tvKenoWoodOdds.setText(getAct().getElementBean().getFactor2());
        tvKenoFireOdds.setText(getAct().getElementBean().getFactor2());
        tvKenoGoldOdds.setText(getAct().getElementBean().getFactor3());
        tvKenoEarthOdds.setText(getAct().getElementBean().getFactor3());
        tvKenoWaterOdds.setText(getAct().getElementBean().getFactor());
    }

    public void setTimerText(String s) {
        if(getAct().isFinishing()||!isAdded()||tvKenoTimer==null)
            return;
        tvKenoTimer.setText(s);
    }

    public void updateResultUI() {
        if(getAct().isFinishing()||!isAdded())
            return;
        this.headerList=getAct().getHeaderList();
        resultStrings=getAct().getResultStrings();
        totalListStrings=getAct().getTotalListStrings();
        if(resultStrings==null||totalListStrings==null||resultStrings.length<1||totalListStrings.size()<1)
            return;
        resultContent.setData(new ArrayList<>(Arrays.asList(resultStrings)));
        headerContent.setData(new ArrayList<>(Arrays.asList(totalListStrings.get(0)[0],totalListStrings.get(0)[1],totalListStrings.get(0)[2],totalListStrings.get(0)[3],totalListStrings.get(0)[5])));
        totalContent.setData(totalListStrings);
    }
    public void clickEarth(View view) {
        if(selectedStateBean==null||elementBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.element5),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Earth",getString(R.string.earth),elementBean.getMax_bet(),elementBean.getMin_bet(),elementBean.getMax_total(),elementBean.getDiscount(),elementBean.getFactor3());
        showBetPop(view,betBean);
    }
    public void clickFire(View view) {
        if(selectedStateBean==null||elementBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.element5),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"Fire",getString(R.string.fire),elementBean.getMax_bet(),elementBean.getMin_bet(),elementBean.getMax_total(),elementBean.getDiscount(),elementBean.getFactor2());
        showBetPop(view,betBean);
    }

    public void clickWater(View view) {
        if(selectedStateBean==null||elementBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.element5),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Water",getString(R.string.water),elementBean.getMax_bet(),elementBean.getMin_bet(),elementBean.getMax_total(),elementBean.getDiscount(),elementBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickWood(View view) {
        if(selectedStateBean==null||elementBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.element5),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Wood",getString(R.string.wood),elementBean.getMax_bet(),elementBean.getMin_bet(),elementBean.getMax_total(),elementBean.getDiscount(),elementBean.getFactor2());
        showBetPop(view,betBean);
    }

    public void clickGold(View view) {
        if(selectedStateBean==null||elementBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.element5),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Gold",getString(R.string.Gold),elementBean.getMax_bet(),elementBean.getMin_bet(),elementBean.getMax_total(),elementBean.getDiscount(),elementBean.getFactor3());
        showBetPop(view,betBean);
    }

    public void clickBigOdd(View view) {
        if(selectedStateBean==null||combinationBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.COMBINATION),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "BigOdd",getString(R.string.big_odd),combinationBean.getMax_bet(),combinationBean.getMin_bet(),combinationBean.getMax_total(),combinationBean.getDiscount(),combinationBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickBigEven(View view) {
        if(selectedStateBean==null||combinationBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.COMBINATION),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "BigEven",getString(R.string.big_even),combinationBean.getMax_bet(),combinationBean.getMin_bet(),combinationBean.getMax_total(),combinationBean.getDiscount(),combinationBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickSmallOdd(View view) {
        if(selectedStateBean==null||combinationBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.COMBINATION),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"SmallOdd",getString(R.string.small_odd),combinationBean.getMax_bet(),combinationBean.getMin_bet(),combinationBean.getMax_total(),combinationBean.getDiscount(),combinationBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickSmallEven(View view) {
        if(selectedStateBean==null||combinationBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.COMBINATION),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"SmallEven",getString(R.string.small_even),combinationBean.getMax_bet(),combinationBean.getMin_bet(),combinationBean.getMax_total(),combinationBean.getDiscount(),combinationBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickEven(View view) {
        if(selectedStateBean==null||evenOddBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.EVEN_ODD),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Even",getString(R.string.even),evenOddBean.getMax_bet(),evenOddBean.getMin_bet(),evenOddBean.getMax_total(),evenOddBean.getDiscount(),evenOddBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickOdd(View view) {
        if(selectedStateBean==null||evenOddBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.EVEN_ODD),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(), "Odd",getString(R.string.odd),evenOddBean.getMax_bet(),evenOddBean.getMin_bet(),evenOddBean.getMax_total(),evenOddBean.getDiscount(),evenOddBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickSmall(View view) {
        if(selectedStateBean==null||bigSmallBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.BIG_SMALL),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"Small",getString(R.string.small),bigSmallBean.getMax_bet(),bigSmallBean.getMin_bet(),bigSmallBean.getMax_total(),bigSmallBean.getDiscount(),bigSmallBean.getFactor());
        showBetPop(view,betBean);
    }

    public void clickTie(View view) {
        if(selectedStateBean==null||bigSmallBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.BIG_SMALL),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"BigSmallTie",getString(R.string.tie),bigSmallBean.getMax_bet(),bigSmallBean.getMin_bet(),bigSmallBean.getMax_total(),bigSmallBean.getDiscount(),bigSmallBean.getFactor3());
        showBetPop(view,betBean);
    }

    public void clickBig(View view) {
        if(selectedStateBean==null||bigSmallBean==null)
            return;
        KenoBetBean betBean=new KenoBetBean(getString(R.string.BIG_SMALL),selectedStateBean.getType2(),selectedStateBean.getGame_name(),selectedStateBean.getPeriod(),"Big",getString(R.string.big),bigSmallBean.getMax_bet(),bigSmallBean.getMin_bet(),bigSmallBean.getMax_total(),bigSmallBean.getDiscount(),bigSmallBean.getFactor());
        showBetPop(view,betBean);
    }
}
