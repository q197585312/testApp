package com.nanyang.app.main.home.keno;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.MyViewPagerAdapter;
import com.nanyang.app.main.home.keno.bean.KenoDataBean;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/11/16.
 */

public class KenoActivity extends BaseToolbarActivity<KenoContract.Presenter> implements KenoContract.View {
    @Bind(R.id.tv_china)
    TextView tv_china;
    @Bind(R.id.tv_canada1)
    TextView tv_canada1;
    @Bind(R.id.tv_canada2)
    TextView tv_canada2;
    @Bind(R.id.tv_slovakia)
    TextView tv_slovakia;
    @Bind(R.id.tv_australia)
    TextView tv_australia;
    @Bind(R.id.tv_draw_num)
    TextView tv_draw_num;
    @Bind(R.id.tv_draw_time)
    TextView tv_draw_time;
    @Bind(R.id.tv_count_down)
    TextView tv_count_down;
    @Bind(R.id.ll_result)
    LinearLayout ll_result;
    @Bind(R.id.tv_big_odds)
    TextView tv_big_odds;
    @Bind(R.id.tv_small_odds)
    TextView tv_small_odds;
    @Bind(R.id.tv_up_odds)
    TextView tv_up_odds;
    @Bind(R.id.tv_down_odds)
    TextView tv_down_odds;
    @Bind(R.id.tv_odd_odds)
    TextView tv_odd_odds;
    @Bind(R.id.tv_even_odds)
    TextView tv_even_odds;
    @Bind(R.id.tv_single_odds)
    TextView tv_single_odds;
    @Bind(R.id.tv_double_odds)
    TextView tv_double_odds;
    @Bind(R.id.tv_gold_odds)
    TextView tv_gold_odds;
    @Bind(R.id.tv_wood_odds)
    TextView tv_wood_odds;
    @Bind(R.id.tv_water_odds)
    TextView tv_water_odds;
    @Bind(R.id.tv_fire_odds)
    TextView tv_fire_odds;
    @Bind(R.id.tv_soil_odds)
    TextView tv_soil_odds;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.vp_way)
    ViewPager vp_way;
    @Bind(R.id.tv_tie_top_odds)
    TextView tv_tie_top_odds;
    @Bind(R.id.tv_mid_odds)
    TextView tv_mid_odds;
    @Bind(R.id.tv_tie_bottom_odds)
    TextView tv_tie_bottom_odds;
    @Bind(R.id.ll_bet)
    LinearLayout ll_bet;
    @Bind(R.id.img_result)
    ImageView img_result;
    @Bind(R.id.tv_result)
    TextView tv_result;
    @Bind(R.id.ll_bet1)
    LinearLayout ll_bet1;
    @Bind(R.id.ll_drawing_close)
    LinearLayout ll_drawing_close;
    @Bind(R.id.tv_drawing_close)
    TextView tv_drawing_close;
    private CountDownTimer timer;
    private PopuKenoResult popuKenoResult;
    private PopuKenoResultAnimation popuKenoResultAnimation;
    public final int CHINA = 0;
    public final int CANADA1 = 1;
    public final int CANADA2 = 2;
    public final int SLOVAKIA = 3;
    public final int AUSTRALIA = 4;
    public int currentType = 0;
    MyViewPagerAdapter myViewPagerAdapter;
    List<TextView> typeTvList;
    KenoDataBean dataBean;
    KenoDataBean.PublicDataBean.CompanyDataBean chinaBean;
    KenoDataBean.PublicDataBean.CompanyDataBean canada1Bean;
    KenoDataBean.PublicDataBean.CompanyDataBean canada2Bean;
    KenoDataBean.PublicDataBean.CompanyDataBean slovakiaBean;
    KenoDataBean.PublicDataBean.CompanyDataBean australiaBean;
    List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> chinaBetIdBean;
    List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> canada1BetIdBean;
    List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> canada2BetIdBean;
    List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> slovakiaBetIdBean;
    List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> australiaBetIdBean;
    BaseRecyclerAdapter<String> adapterBigSmall;
    BaseRecyclerAdapter<String> adapterUpDown;
    BaseRecyclerAdapter<String> adapterOddEven;
    BaseRecyclerAdapter<String> adapterSingleDouble;
    BaseRecyclerAdapter<String> adapterElement;
    List<String> bigSmallList;
    List<String> upDownList;
    List<String> oddEvenList;
    List<String> singleDoubleList;
    List<String> elementlList;
    RecyclerView bigSmallRc;
    RecyclerView upDownRc;
    RecyclerView oddEvenRc;
    RecyclerView singleDoubleRc;
    RecyclerView elementlRc;
    LayoutInflater inflater;

    @Override
    public void onGetData(KenoDataBean data) {
        if (data == null) {
            ToastUtils.showShort("data null");
            return;
        }
        dataBean = data;
        chinaBean = data.getPublicData().get(0).getCompanyData().get(0);
        canada1Bean = data.getPublicData().get(0).getCompanyData().get(1);
        canada2Bean = data.getPublicData().get(0).getCompanyData().get(2);
        slovakiaBean = data.getPublicData().get(0).getCompanyData().get(3);
        australiaBean = data.getPublicData().get(0).getCompanyData().get(4);
        chinaBetIdBean = chinaBean.getBet_id();
        canada1BetIdBean = canada1Bean.getBet_id();
        canada2BetIdBean = canada2Bean.getBet_id();
        slovakiaBetIdBean = slovakiaBean.getBet_id();
        australiaBetIdBean = australiaBean.getBet_id();
        updateDataType(currentType);
    }

    private BaseRecyclerAdapter<String> getBetRcAdapter(List<String> list, final String type) {
        return new BaseRecyclerAdapter<String>(mContext, list, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
                if (item.equals("B")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_red_ball);
                } else if (item.equals("S")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_blue_ball);
                } else if (item.equals("U")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_blue_ball);
                } else if (item.equals("D")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_green_ball);
                } else if (item.equals("O")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_blue_ball);
                } else if (item.equals("T")) {
                    switch (type) {
                        case upDown:
                            tv_content.setBackgroundResource(R.mipmap.keno_red_ball);
                            break;
                        case oddEven:
                            tv_content.setBackgroundResource(R.mipmap.keno_green_ball);
                            break;
                        default:
                            tv_content.setBackgroundResource(R.mipmap.keno_red_ball);
                            break;
                    }
                } else if (item.equals("E")) {
                    switch (type) {
                        case oddEven:
                        case singleDouble:
                            tv_content.setBackgroundResource(R.mipmap.keno_red_ball);
                            break;
                        case elementl:
                            tv_content.setBackgroundResource(R.mipmap.keno_brown_ball);
                            break;
                        default:
                            tv_content.setBackgroundResource(R.mipmap.keno_green_ball);
                            break;
                    }
                } else if (item.equals("A")) {
                    tv_content.setText("W");
                    tv_content.setBackgroundResource(R.mipmap.keno_green_ball);
                } else if (item.equals("F")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_red_ball);
                } else if (item.equals("W")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_blue_ball);
                } else if (item.equals("G")) {
                    tv_content.setBackgroundResource(R.mipmap.keno_yellow_ball);
                }

            }
        };
    }

    private final String bigSmall = "bigSmall";
    private final String upDown = "upDown";
    private final String oddEven = "oddEven";
    private final String singleDouble = "singleDouble";
    private final String elementl = "elementl";

    private void initAdapter() {
        adapterBigSmall = getBetRcAdapter(bigSmallList, bigSmall);
        adapterUpDown = getBetRcAdapter(upDownList, upDown);
        adapterOddEven = getBetRcAdapter(oddEvenList, oddEven);
        adapterSingleDouble = getBetRcAdapter(singleDoubleList, singleDouble);
        adapterElement = getBetRcAdapter(elementlList, elementl);
        bigSmallRc.setAdapter(adapterBigSmall);
        upDownRc.setAdapter(adapterUpDown);
        oddEvenRc.setAdapter(adapterOddEven);
        singleDoubleRc.setAdapter(adapterSingleDouble);
        elementlRc.setAdapter(adapterElement);
        setLayoutManager(bigSmallRc);
        setLayoutManager(upDownRc);
        setLayoutManager(oddEvenRc);
        setLayoutManager(singleDoubleRc);
        setLayoutManager(elementlRc);
    }

    private void setLayoutManager(RecyclerView rc) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 7);
        rc.setLayoutManager(layoutManager);
    }

    private void initViewPager() {
        bigSmallRc = (RecyclerView) inflater.inflate(R.layout.item_rc, null);
        upDownRc = (RecyclerView) inflater.inflate(R.layout.item_rc, null);
        oddEvenRc = (RecyclerView) inflater.inflate(R.layout.item_rc, null);
        singleDoubleRc = (RecyclerView) inflater.inflate(R.layout.item_rc, null);
        elementlRc = (RecyclerView) inflater.inflate(R.layout.item_rc, null);
        List<RecyclerView> rcList = Arrays.asList(bigSmallRc, upDownRc, oddEvenRc, singleDoubleRc, elementlRc);
        myViewPagerAdapter = new MyViewPagerAdapter(rcList);
        vp_way.setAdapter(myViewPagerAdapter);
    }

    @Override
    public void onFailed(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        presenter.stopRefreshData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keno);
        createPresenter(new KenoPresenter(this));
        presenter.getKenoData();
    }

    @Override
    public void initView() {
        super.initView();
        inflater = LayoutInflater.from(mContext);
        bigSmallList = new ArrayList<>();
        upDownList = new ArrayList<>();
        oddEvenList = new ArrayList<>();
        elementlList = new ArrayList<>();
        singleDoubleList = new ArrayList<>();
        typeTvList = Arrays.asList(tv_china, tv_canada1, tv_canada2, tv_slovakia, tv_australia);
        initViewPager();
        initAdapter();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        popuKenoResultAnimation = new PopuKenoResultAnimation(mContext, ll_result,
                LinearLayout.LayoutParams.MATCH_PARENT, (ll_bet.getHeight() + ll_bet1.getHeight() + AfbUtils.dp2px(mContext, 5)));
        popuKenoResultAnimation.setResultAnimationFinish(new PopuKenoResultAnimation.ResultAnimationFinish() {
            @Override
            public void OnResultAnimationFinish() {
                isNeedInitCountDown = true;
                presenter.getKenoData();
                isCanChangeBet = true;
                ll_drawing_close.setVisibility(View.GONE);
                ll_drawing_close.setClickable(false);
            }
        });
    }

    TextView tv;

    private void updateDataType(int type) {
//        presenter.getBetStatu("cn=CHINA" + "&b=KEN"+"&wd="+"2017-11-23|14:58:30"+
//                "&draw="+chinaBean.getDraw_value()+"&id="+chinaBean.getOdds_id()+"&dt="+chinaBean.getMatchDate_value()+
//                "&t=1"+"&v=1.95");
        for (int i = 0; i < typeTvList.size(); i++) {
            TextView t = typeTvList.get(i);
            switch (type) {
                case CHINA:
                    tv = tv_china;
                    break;
                case CANADA1:
                    tv = tv_canada1;
                    break;
                case CANADA2:
                    tv = tv_canada2;
                    break;
                case SLOVAKIA:
                    tv = tv_slovakia;
                    break;
                case AUSTRALIA:
                    tv = tv_australia;
                    break;
            }
            if (tv.equals(t)) {
                t.setBackgroundResource(R.mipmap.keno_tab_select_bg);
                t.setTextColor(Color.WHITE);
            } else {
                t.setBackgroundResource(0);
                t.setTextColor(Color.BLACK);
            }
        }
        switch (type) {
            case CHINA:
                initUi(chinaBean);
                initRcData(CHINA);
                break;
            case CANADA1:
                initUi(canada1Bean);
                initRcData(CANADA1);
                break;
            case CANADA2:
                initUi(canada2Bean);
                initRcData(CANADA2);
                break;
            case SLOVAKIA:
                initUi(slovakiaBean);
                initRcData(SLOVAKIA);
                break;
            case AUSTRALIA:
                initUi(australiaBean);
                initRcData(AUSTRALIA);
                break;
        }
    }

    private void initRcData(int type) {
        char[] chinaBigSmallBetArr = chinaBean.getHistory_id_1().toCharArray();
        char[] chinaUpDownBetArr = chinaBean.getHistory_id_2().toCharArray();
        char[] chinaOddEvenBetArr = chinaBean.getHistory_id_3().toCharArray();
        char[] chinaSingleDoubleBetArr = chinaBean.getHistory_id_4().toCharArray();
        char[] chinaElementBetArr = chinaBean.getHistory_id_5().toCharArray();
        char[] canada1BigSmallBetArr = canada1Bean.getHistory_id_1().toCharArray();
        char[] canada1UpDownBetArr = canada1Bean.getHistory_id_2().toCharArray();
        char[] canada1OddEvenBetArr = canada1Bean.getHistory_id_3().toCharArray();
        char[] canada1SingleDoubleBetArr = canada1Bean.getHistory_id_4().toCharArray();
        char[] canada1ElementBetArr = canada1Bean.getHistory_id_5().toCharArray();
        char[] canada2BigSmallBetArr = canada2Bean.getHistory_id_1().toCharArray();
        char[] canada2UpDownBetArr = canada2Bean.getHistory_id_2().toCharArray();
        char[] canada2OddEvenBetArr = canada2Bean.getHistory_id_3().toCharArray();
        char[] canada2SingleDoubleBetArr = canada2Bean.getHistory_id_4().toCharArray();
        char[] canada2ElementBetArr = canada2Bean.getHistory_id_5().toCharArray();
        char[] slovakiaBigSmallBetArr = slovakiaBean.getHistory_id_1().toCharArray();
        char[] slovakiaUpDownBetArr = slovakiaBean.getHistory_id_2().toCharArray();
        char[] slovakiaOddEvenBetArr = slovakiaBean.getHistory_id_3().toCharArray();
        char[] slovakiaSingleDoubleBetArr = slovakiaBean.getHistory_id_4().toCharArray();
        char[] slovakiaElementBetArr = slovakiaBean.getHistory_id_5().toCharArray();
        char[] australiaBigSmallBetArr = australiaBean.getHistory_id_1().toCharArray();
        char[] australiaUpDownBetArr = australiaBean.getHistory_id_2().toCharArray();
        char[] australiaOddEvenBetArr = australiaBean.getHistory_id_3().toCharArray();
        char[] australiaSingleDoubleBetArr = australiaBean.getHistory_id_4().toCharArray();
        char[] australiaElementBetArr = australiaBean.getHistory_id_5().toCharArray();
        switch (type) {
            case CHINA:
                parseList(chinaBigSmallBetArr, bigSmallList);
                parseList(chinaUpDownBetArr, upDownList);
                parseList(chinaOddEvenBetArr, oddEvenList);
                parseList(chinaSingleDoubleBetArr, singleDoubleList);
                parseList(chinaElementBetArr, elementlList);
                break;
            case CANADA1:
                parseList(canada1BigSmallBetArr, bigSmallList);
                parseList(canada1UpDownBetArr, upDownList);
                parseList(canada1OddEvenBetArr, oddEvenList);
                parseList(canada1SingleDoubleBetArr, singleDoubleList);
                parseList(canada1ElementBetArr, elementlList);
                break;
            case CANADA2:
                parseList(canada2BigSmallBetArr, bigSmallList);
                parseList(canada2UpDownBetArr, upDownList);
                parseList(canada2OddEvenBetArr, oddEvenList);
                parseList(canada2SingleDoubleBetArr, singleDoubleList);
                parseList(canada2ElementBetArr, elementlList);
                break;
            case SLOVAKIA:
                parseList(slovakiaBigSmallBetArr, bigSmallList);
                parseList(slovakiaUpDownBetArr, upDownList);
                parseList(slovakiaOddEvenBetArr, oddEvenList);
                parseList(slovakiaSingleDoubleBetArr, singleDoubleList);
                parseList(slovakiaElementBetArr, elementlList);
                break;
            case AUSTRALIA:
                parseList(australiaBigSmallBetArr, bigSmallList);
                parseList(australiaUpDownBetArr, upDownList);
                parseList(australiaOddEvenBetArr, oddEvenList);
                parseList(australiaSingleDoubleBetArr, singleDoubleList);
                parseList(australiaElementBetArr, elementlList);
                break;
        }
        adapterBigSmall.setData(bigSmallList);
        adapterUpDown.setData(upDownList);
        adapterOddEven.setData(oddEvenList);
        adapterSingleDouble.setData(singleDoubleList);
        adapterElement.setData(elementlList);
    }

    private void parseList(char[] arr, List<String> list) {
        list.clear();
        for (int k = 0; k < 5; k++) {
            for (int j = 0; j < arr.length; j++) {
                if (j % 5 == k) {
                    list.add(arr[j] + "");
                }
            }
        }
    }

    private void initUi(KenoDataBean.PublicDataBean.CompanyDataBean bean) {
        String drawNum = bean.getDraw_value();
        if (TextUtils.isEmpty(drawNum)) {
            drawNum = bean.getDraw2_value();
        }
        tv_draw_num.setText(drawNum);
        String drawTime = bean.getMatchDate_value();
        if (TextUtils.isEmpty(drawTime)) {
            drawTime = bean.getMatchDate2_value();
        }
        tv_draw_time.setText(drawTime);
        initCountDown(bean);
        List<KenoDataBean.PublicDataBean.CompanyDataBean.BetIdBean> betIdBeanList = bean.getBet_id();
        for (int i = 0; i < betIdBeanList.size(); i++) {
            String betId = betIdBeanList.get(i).getId();
            String odds = betIdBeanList.get(i).getValue();
            switch (betId) {
                case "1":
                    tv_big_odds.setText(odds);
                    break;
                case "15":
                    tv_tie_top_odds.setText(odds);
                    break;
                case "2":
                    tv_small_odds.setText(odds);
                    break;
                case "7":
                    tv_up_odds.setText(odds);
                    break;
                case "8":
                    tv_mid_odds.setText(odds);
                    break;
                case "9":
                    tv_down_odds.setText(odds);
                    break;
                case "3":
                    tv_odd_odds.setText(odds);
                    break;
                case "16":
                    tv_tie_bottom_odds.setText(odds);
                    break;
                case "4":
                    tv_even_odds.setText(odds);
                    break;
                case "5":
                    tv_single_odds.setText(odds);
                    break;
                case "6":
                    tv_double_odds.setText(odds);
                    break;
                case "10":
                    tv_gold_odds.setText(odds);
                    break;
                case "11":
                    tv_wood_odds.setText(odds);
                    break;
                case "12":
                    tv_water_odds.setText(odds);
                    break;
                case "13":
                    tv_fire_odds.setText(odds);
                    break;
                case "14":
                    tv_soil_odds.setText(odds);
                    break;
            }
        }
    }

    public boolean isNeedInitCountDown = true;//是否需要初始化倒计时
    public boolean isCountDown = false;//是否在倒计时
    public boolean isCanChangeBet = true;//是否可以切换下注类型

    private void initCountDown(KenoDataBean.PublicDataBean.CompanyDataBean bean) {
        if (bean.getResult_id() == null || bean.getResult_id().size() == 0) {
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            tv_drawing_close.setText("CLOSE");
            if (ll_drawing_close.getVisibility() == View.GONE) {
                ll_drawing_close.setVisibility(View.VISIBLE);
                ll_drawing_close.setClickable(true);
            }
            tv_count_down.setText("0");
            return;
        }
        long countDownTime = AfbUtils.diffTime(bean.getClosing_date());
        if (isNeedInitCountDown) {
            isNeedInitCountDown = false;
            if (timer != null) {
                timer.cancel();
                timer = null;
            }
            isCountDown = true;
            timer = new CountDownTimer(countDownTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    if (tv_count_down != null) {
                        tv_count_down.setText(millisUntilFinished / 1000 + "");
                    }
                    ll_drawing_close.setVisibility(View.GONE);
                    ll_drawing_close.setClickable(false);
                }

                @Override
                public void onFinish() {
                    tv_drawing_close.setText("DRAWING...");
                    if (ll_drawing_close.getVisibility() == View.GONE) {
                        ll_drawing_close.setVisibility(View.VISIBLE);
                        ll_drawing_close.setClickable(true);
                    }
                    isCountDown = false;
                    tv_count_down.setText("0");
                }
            };
            timer.start();
        }
        if (bean.getDraw2_value().equals(bean.getResult_id().get(0).getId()) &&
                !isCountDown && !popuKenoResultAnimation.isShowing()) {
            isCanChangeBet = false;
            presenter.stopRefreshData();
            popuKenoResultAnimation.showPopupDownWindowWihte(0, 0);
            popuKenoResultAnimation.startAction(getResultList(bean));
        }
    }


    private List<String> getResultList(KenoDataBean.PublicDataBean.CompanyDataBean bean) {
        String result = bean.getResult_id().get(0).getValue().split("\\|")[0];
        List<String> list = new ArrayList<>();
        String[] resultArr = result.split(" ");
        for (int i = 0; i < resultArr.length; i++) {
            list.add(resultArr[i]);
        }
        return list;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.rl_big, R.id.rl_small, R.id.rl_upper, R.id.rl_lower, R.id.rl_odd, R.id.rl_even, R.id.rl_single, R.id.rl_double,
            R.id.rl_set_top, R.id.rl_set_bottom, R.id.rl_mid, R.id.ll_gold, R.id.ll_wood, R.id.ll_water, R.id.ll_fire, R.id.ll_soil,
            R.id.tv_china, R.id.tv_canada1, R.id.tv_canada2, R.id.tv_slovakia, R.id.tv_australia, R.id.ll_result, R.id.img_left,
            R.id.img_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_china:
                changBetType(CHINA);
                break;
            case R.id.tv_canada1:
                changBetType(CANADA1);
                break;
            case R.id.tv_canada2:
                changBetType(CANADA2);
                break;
            case R.id.tv_slovakia:
                changBetType(SLOVAKIA);
                break;
            case R.id.tv_australia:
                changBetType(AUSTRALIA);
                break;
            case R.id.rl_big:
                ToastUtils.showShort("aaa");
                break;
            case R.id.rl_small:
                break;
            case R.id.rl_upper:
                break;
            case R.id.rl_lower:
                break;
            case R.id.rl_odd:
                break;
            case R.id.rl_even:
                break;
            case R.id.rl_single:
                break;
            case R.id.rl_double:
                break;
            case R.id.rl_set_top:
                break;
            case R.id.rl_mid:
                break;
            case R.id.rl_set_bottom:
                break;
            case R.id.ll_gold:
                break;
            case R.id.ll_wood:
                break;
            case R.id.ll_water:
                break;
            case R.id.ll_fire:
                break;
            case R.id.ll_soil:
                break;
            case R.id.ll_result:
                //点击出历史结果
                int h = ll_bet.getHeight() - findViewById(R.id.rl_single).getHeight() - findViewById(R.id.ll_gold).getHeight()
                        - AfbUtils.dp2px(mContext, 12);
                popuKenoResult = new PopuKenoResult(getCurrentTypeData().getResult_id(), mContext, ll_result,
                        LinearLayout.LayoutParams.MATCH_PARENT, h);
                popuKenoResult.showPopupDownWindowWihte(0, 0);
                if (popuKenoResult.isShowing()) {
                    ll_result.setBackgroundColor(Color.WHITE);
                    img_result.setBackgroundResource(R.mipmap.keno_result_green);
                    tv_result.setTextColor(0xff3BAB5C);
                }
                break;
            case R.id.img_left:
                if (vp_way.getCurrentItem() == 0) {
                    return;
                } else {
                    vp_way.setCurrentItem(vp_way.getCurrentItem() - 1, true);
                }
                break;
            case R.id.img_right:
                if (vp_way.getCurrentItem() == myViewPagerAdapter.getCount() - 1) {
                    return;
                } else {
                    vp_way.setCurrentItem(vp_way.getCurrentItem() + 1, true);
                }
                break;
        }
    }

    private void changBetType(int type) {
        if (isCanChangeBet) {
            currentType = type;
            isNeedInitCountDown = true;
            updateDataType(currentType);
            vp_way.setCurrentItem(0, false);
        }
    }

    private KenoDataBean.PublicDataBean.CompanyDataBean getCurrentTypeData() {
        switch (currentType) {
            case CHINA:
                return chinaBean;
            case CANADA1:
                return canada1Bean;
            case CANADA2:
                return canada2Bean;
            case SLOVAKIA:
                return slovakiaBean;
            case AUSTRALIA:
                return australiaBean;
            default:
                return chinaBean;
        }
    }
}
