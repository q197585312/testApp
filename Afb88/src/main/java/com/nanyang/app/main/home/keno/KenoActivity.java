package com.nanyang.app.main.home.keno;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    @Bind(R.id.img_left)
    ImageView img_left;
    @Bind(R.id.img_right)
    ImageView img_right;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.vp_way)
    ViewPager vp_way;
    public final int CHINA = 0;
    public final int CANADA1 = 1;
    public final int CANADA2 = 2;
    public final int SLOVAKIA = 3;
    public final int AUSTRALIA = 4;
    List<TextView> typeTvList;
    KenoDataBean dataBean;
    KenoDataBean.PublicDataBean.CompanyDataBean chinaBean;
    KenoDataBean.PublicDataBean.CompanyDataBean canada1Bean;
    KenoDataBean.PublicDataBean.CompanyDataBean canada2Bean;
    KenoDataBean.PublicDataBean.CompanyDataBean slovakiaBean;
    KenoDataBean.PublicDataBean.CompanyDataBean australiaBean;
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
        updateDataType(CHINA, tv_china);
    }

    private void initAdapter() {
        GridLayoutManager layoutManager1 = new GridLayoutManager(mContext, 7);//设置为一个3列的纵向网格布局
        GridLayoutManager layoutManager2 = new GridLayoutManager(mContext, 7);//设置为一个3列的纵向网格布局
        GridLayoutManager layoutManager3 = new GridLayoutManager(mContext, 7);//设置为一个3列的纵向网格布局
        GridLayoutManager layoutManager4 = new GridLayoutManager(mContext, 7);//设置为一个3列的纵向网格布局
        GridLayoutManager layoutManager5 = new GridLayoutManager(mContext, 7);//设置为一个3列的纵向网格布局
        adapterBigSmall = new BaseRecyclerAdapter<String>(mContext, bigSmallList, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        bigSmallRc.setLayoutManager(layoutManager1);
        bigSmallRc.setAdapter(adapterBigSmall);
        adapterUpDown = new BaseRecyclerAdapter<String>(mContext, upDownList, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        upDownRc.setLayoutManager(layoutManager2);
        upDownRc.setAdapter(adapterUpDown);
        adapterOddEven = new BaseRecyclerAdapter<String>(mContext, oddEvenList, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        oddEvenRc.setLayoutManager(layoutManager3);
        oddEvenRc.setAdapter(adapterOddEven);
        adapterSingleDouble = new BaseRecyclerAdapter<String>(mContext, singleDoubleList, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        singleDoubleRc.setLayoutManager(layoutManager4);
        singleDoubleRc.setAdapter(adapterSingleDouble);
        adapterElement = new BaseRecyclerAdapter<String>(mContext, elementlList, R.layout.item_keno_bet) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tv_content = holder.getView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        elementlRc.setLayoutManager(layoutManager5);
        elementlRc.setAdapter(adapterElement);
    }

    MyViewPagerAdapter myViewPagerAdapter;

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

    private void updateDataType(int type, TextView tv) {
        for (int i = 0; i < typeTvList.size(); i++) {
            TextView t = typeTvList.get(i);
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
        tv_count_down.setText("120");
//        tv_big_odds.setText();
    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.rl_big, R.id.rl_small, R.id.rl_upper, R.id.rl_lower, R.id.rl_odd, R.id.rl_even, R.id.rl_single, R.id.rl_double,
            R.id.rl_set_top, R.id.rl_set_bottom, R.id.rl_mid, R.id.ll_gold, R.id.ll_wood, R.id.ll_water, R.id.ll_fire, R.id.ll_soil,
            R.id.tv_china, R.id.tv_canada1, R.id.tv_canada2, R.id.tv_slovakia, R.id.tv_australia, R.id.ll_result})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_china:
                updateDataType(CHINA, (TextView) view);
                break;
            case R.id.tv_canada1:
                updateDataType(CANADA1, (TextView) view);
                break;
            case R.id.tv_canada2:
                updateDataType(CANADA2, (TextView) view);
                break;
            case R.id.tv_slovakia:
                updateDataType(SLOVAKIA, (TextView) view);
                break;
            case R.id.tv_australia:
                updateDataType(AUSTRALIA, (TextView) view);
                break;
            case R.id.rl_big:
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
                break;
        }
    }
}
