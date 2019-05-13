package com.nanyang.app.main.home.huayThai;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.main.BaseMoreFragment;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HuayThaiFragment extends BaseMoreFragment<HuayThaiPresenter> {
    @Bind(R.id.tv_grade_date)
    TextView tvGradeDate;
    @Bind(R.id.layout_bet1)
    LinearLayout layoutBet1;
    EditText bet1Itembet1;
    EditText bet1Itembet2;
    TextView bet1ItembetSucceed;
    @Bind(R.id.layout_bet2)
    LinearLayout layoutBet2;
    EditText bet2Itembet1;
    EditText bet2Itembet2;
    TextView bet2ItembetSucceed;
    @Bind(R.id.layout_bet3)
    LinearLayout layoutBet3;
    EditText bet3Itembet1;
    EditText bet3Itembet2;
    TextView bet3ItembetSucceed;
    @Bind(R.id.layout_bet4)
    LinearLayout layoutBet4;
    EditText bet4Itembet1;
    EditText bet4Itembet2;
    TextView bet4ItembetSucceed;
    @Bind(R.id.layout_bet5)
    LinearLayout layoutBet5;
    EditText bet5Itembet1;
    EditText bet5Itembet2;
    TextView bet5ItembetSucceed;
    @Bind(R.id.layout_bet6)
    LinearLayout layoutBet6;
    EditText bet6Itembet1;
    EditText bet6Itembet2;
    TextView bet6ItembetSucceed;
    @Bind(R.id.layout_bet7)
    LinearLayout layoutBet7;
    EditText bet7Itembet1;
    EditText bet7Itembet2;
    TextView bet7ItembetSucceed;
    @Bind(R.id.layout_bet8)
    LinearLayout layoutBet8;
    EditText bet8Itembet1;
    EditText bet8Itembet2;
    TextView bet8ItembetSucceed;
    @Bind(R.id.layout_bet9)
    LinearLayout layoutBet9;
    EditText bet9Itembet1;
    EditText bet9Itembet2;
    TextView bet9ItembetSucceed;
    @Bind(R.id.layout_bet10)
    LinearLayout layoutBet10;
    EditText bet10Itembet1;
    EditText bet10Itembet2;
    TextView bet10tembetSucceed;
    @Bind(R.id.tv_submit)
    TextView tvSubmit;
    @Bind(R.id.tv_refresh)
    TextView tvRefresh;

    @Bind(R.id.tv_limit_num)
    TextView tvLimitNun;
    @Bind(R.id.layout_introduce)
    LinearLayout layoutIntroduce;
    TextView tvIntroduceNmae;
    TextView tvIntroduceContent;
    TextView tvIntroduceWay;


    public List<HuayThaiIntroduceBean> introduceData = new ArrayList<>();
    public LayoutInflater inflater;

    String type;
    MenuItemInfo<String> info;
    private BaseListPopupWindow<HuayDrawDateInfo.DicAllBean> popDate;
    private HuayDrawDateInfo.DicAllBean selectDic;

    public void setInfo(MenuItemInfo<String> info) {

        this.info = info;
        this.type = info.getType();
        if (isAdded()) {
            refresh();

        }

    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new HuayThaiPresenter(this));
        inflater = LayoutInflater.from(mContext);
        refresh();
    }


    private void refresh() {
        presenter.refresh(type);
        initIntroduceData(type);
        setBackTitle(info.getText());
    }


    private void loadIntroduceData() {
        if (introduceData.size() != 0) {
            layoutIntroduce.removeAllViews();
            for (int i = 0; i < introduceData.size(); i++) {
                HuayThaiIntroduceBean introduceBean = introduceData.get(i);
                View layout = inflater.inflate(R.layout.layout_thaithousand_introduce, null);
                tvIntroduceNmae = (TextView) layout.findViewById(R.id.tv_Introduce_name);
                tvIntroduceNmae.setText(introduceBean.getName());
                tvIntroduceContent = (TextView) layout.findViewById(R.id.tv_Introduce_content);
                tvIntroduceContent.setText(introduceBean.getIntroduce());
                tvIntroduceWay = (TextView) layout.findViewById(R.id.tv_Introduce_way);
                tvIntroduceWay.setText(introduceBean.getWay());
                layoutIntroduce.addView(layout);
            }
        }
    }

    private void initIntroduceData(String type) {
        if (type.equals("33_18")) {
            tvLimitNun.setText(R.string.Number_1_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize1D), getString(R.string.description_top_1d), getString(R.string.pay3)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize_1d), getString(R.string.description_bottom_1d), getString(R.string.pay4)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix1), getString(R.string.description_fix1), getString(R.string.pay9)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix2), getString(R.string.description_fix2), getString(R.string.pay9)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix3), getString(R.string.description_fix3), getString(R.string.pay9)));
        } else if (type.equals("33_19")) {
            tvLimitNun.setText(R.string.Number_2_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize2D), getString(R.string.description_top_2d), getString(R.string.pay80)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize2D), getString(R.string.description_bottom_2d), getString(R.string.pay80)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Roll_2D), getString(R.string.description_roll_2d), getString(R.string.pay20)));
        } else if (type.equals("33_20")) {
            tvLimitNun.setText(R.string.Number_3_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_3d), getString(R.string.straight_prediction), getString(R.string.pay550)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.In_front_3d), getString(R.string.description_in_front_3d), getString(R.string.pay250)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize_3d), getString(R.string.description_bottom_3d), getString(R.string.pay250)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Roll_3d), getString(R.string.description_roll_3d), getString(R.string.pay250)));
        }
        loadIntroduceData();
    }


    private void checkAllBet() {
        checkBet(layoutBet1, type);
        checkBet(layoutBet2, type);
        checkBet(layoutBet3, type);
        checkBet(layoutBet4, type);
        checkBet(layoutBet5, type);
        checkBet(layoutBet6, type);
        checkBet(layoutBet7, type);
        checkBet(layoutBet8, type);
        checkBet(layoutBet9, type);
        checkBet(layoutBet10, type);

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_thai_thousand;
    }

    @OnClick({R.id.tv_grade_date, R.id.tv_refresh, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_grade_date:
                if (popDate != null)
                    popDate.showPopupDownWindow();
                break;
            case R.id.tv_refresh:
                refresh();
                break;
            case R.id.tv_submit:
                checkAllBet();
                break;
        }
    }

    private void getParamBet(LinearLayout layout) {
        if (info == null || StringUtils.isNull(selectDic.getValue()))
            return;
        EditText betNumber = (EditText) layout.findViewById(R.id.item_bet1);
        EditText betAmt = (EditText) layout.findViewById(R.id.item_bet2);
        TextView item_bet_succeed = (TextView) layout.findViewById(R.id.item_bet_succeed);
        presenter.submitBet(info.getParent(), betNumber.getText().toString().trim(), betAmt.getText().toString().trim(), selectDic.getValue(), item_bet_succeed);

    }

    public void checkBet(LinearLayout layout, String type) {
        EditText bet1 = (EditText) layout.findViewById(R.id.item_bet1);
        EditText bet2 = (EditText) layout.findViewById(R.id.item_bet2);
        TextView bet3 = (TextView) layout.findViewById(R.id.item_bet_succeed);
        int le = bet1.getText().toString().trim().length();
        int le2 = bet2.getText().toString().trim().length();
        bet3.setTextColor(Color.RED);
        if (le > 0) {
            if (type.equals("33_18")) {
                if (le == 1) {
                    bet3.setText("");
                    if (le2 > 0) {
                        getParamBet(layout);
                    }
                } else {
                    bet3.setText("Not a valid 1 digit number!");
                }
            } else if (type.equals("33_19")) {
                if (le == 2) {
                    bet3.setText("");
                    if (le2 > 0) {
                        getParamBet(layout);
                    }
                } else {
                    bet3.setText("Not a valid 2 digit number!");
                }
            } else {
                if (le == 3) {
                    bet3.setText("");
                    if (le2 > 0) {
                        getParamBet(layout);
                    }
                } else {
                    bet3.setText("Not a valid 3 digit number!");
                }
            }
        }
    }


    public void onGetData(HuayDrawDateInfo data) {
        selectDic = data.getDicAll().get(0);
        popDate = new BaseListPopupWindow<HuayDrawDateInfo.DicAllBean>(mContext, tvGradeDate, tvGradeDate.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, tvGradeDate) {
            @Override
            public int getRecyclerViewId() {
                return R.id.base_rv;
            }

            @Override
            protected void convertTv(TextView tv, HuayDrawDateInfo.DicAllBean item) {
                tv.setText(item.getDesc());
                selectDic = item;
            }
        };
        popDate.setData(data.getDicAll());
        tvGradeDate.setText(data.getDicAll().get(0).getDesc());
    }


}