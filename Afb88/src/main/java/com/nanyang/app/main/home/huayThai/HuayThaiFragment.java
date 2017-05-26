package com.nanyang.app.main.home.huayThai;

import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HuayThaiFragment extends BaseFragment<HuayThaiPresenter> implements HuayThaiContract.View {
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
    HuayThaiGamesActivity huayThaiGamesActivity;

    String type;
    MenuItemInfo info;
    private BaseListPopupWindow<HuayDrawDateInfo.DicAllBean> popDate;

    @Override
    public void initData() {
        super.initData();
        huayThaiGamesActivity = (HuayThaiGamesActivity) getActivity();
        info = huayThaiGamesActivity.info;
        type = info.getText();
        createPresenter(new HuayThaiPresenter(this));
        inflater = LayoutInflater.from(mContext);
        refresh();
        initIntroduceData(type);
        initBetListener(type);
    }

    private void refresh() {
        presenter.refresh(info.getType());
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
        if (type.equals(getString(R.string.game1d))) {
            tvLimitNun.setText(R.string.Number_1_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean("1D最高奖", "赌上1号预测 其中3个号码能在3D内的抽奖。 假如 抽奖是312，那是顾客能赢的奖品.", "一付三块"));
            introduceData.add(new HuayThaiIntroduceBean("1D 底部奖", "赌上1号预测 其中2个号码能在2D内的最后抽奖品.", "一付四块"));
            introduceData.add(new HuayThaiIntroduceBean("1D 最高奖固定 一", "顾客可以赌上个单号预测上3D奖品的第一数字。比如，结果数字是 132，然后顾客将获得奖金.", "一付九块"));
            introduceData.add(new HuayThaiIntroduceBean("1D 最高奖固定 二", "顾客可以赌上个单号预测上3D奖品的第二数字。比如，结果数字是 132，然后顾客将获得奖金.", "一付九块"));
            introduceData.add(new HuayThaiIntroduceBean("1D 最高奖固定 三", "顾客可以赌上个单号预测上3D奖品的第二数字。比如，结果数字是 132，然后顾客将获得奖金.", "一付九块"));
        } else if (type.equals(getString(R.string.game2d))) {
            tvLimitNun.setText(R.string.Number_2_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean("2D最高奖", "1 x 2D 高数字抽奖品 。顾客需要直顺序相同的秩序就会拿到奖金.", "一付八十块"));
            introduceData.add(new HuayThaiIntroduceBean("2D 底部奖", "1 x 2D 底数字抽奖品。顾客需要直顺序相同的秩序就会拿到奖金.", "一付八十块"));
            introduceData.add(new HuayThaiIntroduceBean("2D上辊", "顾客可以赌上3D抽奖的两个后面的号码 （` 00 ）。结果数字是132 那就是 12，13，21，23，31，32 的6 个总数结果。 任何一个落入2D最高奖金支付.", "一付十二块"));
        } else if (type.equals(getString(R.string.game3d))) {
            tvLimitNun.setText(R.string.Number_3_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean("3D最高奖", "直数预测", "一付五百五十块"));
            introduceData.add(new HuayThaiIntroduceBean("3D前面奖", "2x最后的抽奖总数。顾客可以能买中1个数字在2个数字之内.（3位数）", "一付两百五十块"));
            introduceData.add(new HuayThaiIntroduceBean("3D底部奖", "2x最后的抽奖总数。顾客可以能买中1个数字在2个数字之内.（3位数）", "一付两百五十块"));
            introduceData.add(new HuayThaiIntroduceBean("3D 上辊奖", "随机预测。可能的结果123,132,213,231,312,321共6条结果。掉进最高奖3D将被支付.", "一付一百二十五块"));
        }
        loadIntroduceData();
    }



    private void initBetListener(String type) {
        initBet(layoutBet1, bet1Itembet1, bet1Itembet2, bet1ItembetSucceed, type);
        initBet(layoutBet2, bet2Itembet1, bet2Itembet2, bet2ItembetSucceed, type);
        initBet(layoutBet3, bet3Itembet1, bet3Itembet2, bet3ItembetSucceed, type);
        initBet(layoutBet4, bet4Itembet1, bet4Itembet2, bet4ItembetSucceed, type);
        initBet(layoutBet5, bet5Itembet1, bet5Itembet2, bet5ItembetSucceed, type);
        initBet(layoutBet6, bet6Itembet1, bet6Itembet2, bet6ItembetSucceed, type);
        initBet(layoutBet7, bet7Itembet1, bet7Itembet2, bet7ItembetSucceed, type);
        initBet(layoutBet8, bet8Itembet1, bet8Itembet2, bet8ItembetSucceed, type);
        initBet(layoutBet9, bet9Itembet1, bet9Itembet2, bet9ItembetSucceed, type);
        initBet(layoutBet10, bet10Itembet1, bet10Itembet2, bet10tembetSucceed, type);
        layoutBet1.findViewById(R.id.item_atm).setVisibility(View.VISIBLE);
    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_thai_thousand;
    }


    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }




    @OnClick({R.id.tv_grade_date, R.id.tv_refresh, R.id.tv_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_grade_date:
                popDate.showPopupDownWindow();
                break;
            case R.id.tv_refresh:
                refresh();
                break;
            case R.id.tv_submit:
                presenter.submitBet(getParam());
                break;
        }
    }

    private HuayThaiBetSubmitBean getParam() {
        HuayThaiBetSubmitBean bean = new HuayThaiBetSubmitBean();
        bet1Itembet1 = (EditText) layoutBet1.findViewById(R.id.item_bet1);
        bet1Itembet2 = (EditText) layoutBet1.findViewById(R.id.item_bet2);
        bean.setTxtNumber(bet1Itembet1.getText().toString());
        bean.setTxtAmt(bet1Itembet2.getText().toString());
        bet2Itembet1 = (EditText) layoutBet2.findViewById(R.id.item_bet1);
        bet2Itembet2 = (EditText) layoutBet2.findViewById(R.id.item_bet2);
        bean.setTxtNumber2(bet2Itembet1.getText().toString());
        bean.setTxtAmt2(bet2Itembet2.getText().toString());
        bet3Itembet1 = (EditText) layoutBet3.findViewById(R.id.item_bet1);
        bet3Itembet2 = (EditText) layoutBet3.findViewById(R.id.item_bet2);
        bean.setTxtNumber3(bet3Itembet1.getText().toString());
        bean.setTxtAmt3(bet3Itembet2.getText().toString());
        bet4Itembet1 = (EditText) layoutBet4.findViewById(R.id.item_bet1);
        bet4Itembet2 = (EditText) layoutBet4.findViewById(R.id.item_bet2);
        bean.setTxtNumber4(bet4Itembet1.getText().toString());
        bean.setTxtAmt4(bet4Itembet2.getText().toString());
        bet5Itembet1 = (EditText) layoutBet5.findViewById(R.id.item_bet1);
        bet5Itembet2 = (EditText) layoutBet5.findViewById(R.id.item_bet2);
        bean.setTxtNumber5(bet5Itembet1.getText().toString());
        bean.setTxtAmt5(bet5Itembet2.getText().toString());
        bet6Itembet1 = (EditText) layoutBet6.findViewById(R.id.item_bet1);
        bet6Itembet2 = (EditText) layoutBet6.findViewById(R.id.item_bet2);
        bean.setTxtNumber6(bet6Itembet1.getText().toString());
        bean.setTxtAmt6(bet6Itembet2.getText().toString());
        bet7Itembet1 = (EditText) layoutBet7.findViewById(R.id.item_bet1);
        bet7Itembet2 = (EditText) layoutBet7.findViewById(R.id.item_bet2);
        bean.setTxtNumber7(bet7Itembet1.getText().toString());
        bean.setTxtAmt7(bet7Itembet2.getText().toString());
        bet8Itembet1 = (EditText) layoutBet8.findViewById(R.id.item_bet1);
        bet8Itembet2 = (EditText) layoutBet8.findViewById(R.id.item_bet2);
        bean.setTxtNumber8(bet8Itembet1.getText().toString());
        bean.setTxtAmt8(bet8Itembet2.getText().toString());
        bet9Itembet1 = (EditText) layoutBet9.findViewById(R.id.item_bet1);
        bet9Itembet2 = (EditText) layoutBet9.findViewById(R.id.item_bet2);
        bean.setTxtNumber9(bet9Itembet1.getText().toString());
        bean.setTxtAmt9(bet9Itembet2.getText().toString());
        bet10Itembet1 = (EditText) layoutBet10.findViewById(R.id.item_bet1);
        bet10Itembet2 = (EditText) layoutBet10.findViewById(R.id.item_bet2);
        bean.setTxtNumber10(bet10Itembet1.getText().toString());
        bean.setTxtAmt10(bet10Itembet2.getText().toString());
        bean.setLstDraw("12101468");
        return bean;
    }

    public void initBet(LinearLayout layout, EditText bet1, EditText bet2, TextView bet3, String type) {
        bet1 = (EditText) layout.findViewById(R.id.item_bet1);
        bet2 = (EditText) layout.findViewById(R.id.item_bet2);
        bet3 = (TextView) layout.findViewById(R.id.item_bet_succeed);
        final EditText finalBet = bet1;
        if (type.equals(getString(R.string.game1d))) {
            bet1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        } else if (type.equals(getString(R.string.game2d))) {
            bet1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
            bet1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        if (finalBet.getText().toString().length() < 2) {
                            ToastUtils.showShort("Not a valid 2 digit number!");
                        }
                    }
                }
            });
        } else if (type.equals(getString(R.string.game3d))) {
            bet1.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
            bet1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if (!b) {
                        if (finalBet.getText().toString().length() < 3) {
                            ToastUtils.showShort("Not a valid 3 digit number!");
                        }
                    }
                }
            });
        }
        bet1.setText(null);
        bet2.setText(null);
    }

    @Override
    public void onGetData(HuayDrawDateInfo data) {
        popDate = new BaseListPopupWindow<HuayDrawDateInfo.DicAllBean>(mContext, tvGradeDate, tvGradeDate.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, tvGradeDate) {
            @Override
            public int getRecyclerViewId() {
                return R.id.base_rv;
            }

            @Override
            protected void convertTv(TextView tv, HuayDrawDateInfo.DicAllBean item) {
                tv.setText(item.getDesc());
            }
        };
        popDate.setData(data.getDicAll());
        tvGradeDate.setText(data.getDicAll().get(0).getDesc());
    }
}