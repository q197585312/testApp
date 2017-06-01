package com.nanyang.app.main.home.huayThai;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AppConstant;
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
    MenuItemInfo<String> info;
    private BaseListPopupWindow<HuayDrawDateInfo.DicAllBean> popDate;
    private HuayDrawDateInfo.DicAllBean selectDic;

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

    }

    private void refresh() {
        presenter.refresh(AppConstant.getInstance().HOST + info.getType());
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
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize1D), getString(R.string.description_top_1d), getString(R.string.pay3)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize_1d), getString(R.string.description_bottom_1d), getString(R.string.pay4)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix1), getString(R.string.description_fix1), getString(R.string.pay9)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix2), getString(R.string.description_fix2), getString(R.string.pay9)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_Fix3), getString(R.string.description_fix3), getString(R.string.pay9)));
        } else if (type.equals(getString(R.string.game2d))) {
            tvLimitNun.setText(R.string.Number_2_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize2D), getString(R.string.description_top_2d), getString(R.string.pay80)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize2D), getString(R.string.description_bottom_2d), getString(R.string.pay80)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Roll_2D), getString(R.string.description_roll_2d), getString(R.string.pay20)));
        } else if (type.equals(getString(R.string.game3d))) {
            tvLimitNun.setText(R.string.Number_3_digit_);
            introduceData.clear();
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Prize_3d), getString(R.string.straight_prediction), getString(R.string.pay550)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.In_front_3d), getString(R.string.description_in_front_3d), getString(R.string.pay250)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Bottom_Prize_3d), getString(R.string.description_bottom_3d), getString(R.string.pay250)));
            introduceData.add(new HuayThaiIntroduceBean(getString(R.string.Top_Roll_3d), getString(R.string.description_roll_3d), getString(R.string.pay250)));
        }
        loadIntroduceData();
    }


    private boolean checkVisiable(String type) {
        return checkBet(layoutBet1, type)
                && checkBet(layoutBet2, type)
                && checkBet(layoutBet3, type)
                && checkBet(layoutBet4, type)
                && checkBet(layoutBet5, type)
                && checkBet(layoutBet6, type)
                && checkBet(layoutBet7, type)
                && checkBet(layoutBet8, type)
                && checkBet(layoutBet9, type)
                && checkBet(layoutBet10, type);

    }

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_thai_thousand;
    }


    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onResultData(ResultBean s) {
        if (s != null && s.getDicAll().size() > 0) {
            for (int i = 0; i < s.getDicAll().size(); i++) {
                switch (i) {
                    case 0:
                        ((TextView) layoutBet1.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(0).getNumber());
                        break;
                    case 1:
                        ((TextView) layoutBet2.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(1).getNumber());
                        break;
                    case 2:
                        ((TextView) layoutBet3.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(2).getNumber());
                        break;
                    case 3:
                        ((TextView) layoutBet4.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(3).getNumber());
                        break;
                    case 4:
                        ((TextView) layoutBet5.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(4).getNumber());
                        break;
                    case 5:
                        ((TextView) layoutBet6.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(5).getNumber());
                        break;
                    case 6:
                        ((TextView) layoutBet7.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(6).getNumber());
                        break;
                    case 7:
                        ((TextView) layoutBet8.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(7).getNumber());
                        break;
                    case 8:
                        ((TextView) layoutBet9.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(8).getNumber());
                        break;
                    case 9:
                        ((TextView) layoutBet10.findViewById(R.id.item_bet_succeed)).setText(s.getDicAll().get(9).getNumber());
                        break;


                }
            }
        }
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
                if (checkVisiable(info.getText())) {
                    presenter.submitBet(AppConstant.getInstance().HOST + info.getParent(), getParam());
                }
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
        if (selectDic != null)
            bean.setLstDraw(selectDic.getValue());
        return bean;
    }

    public boolean checkBet(LinearLayout layout, String type) {
        EditText bet1 = (EditText) layout.findViewById(R.id.item_bet1);

        TextView bet3 = (TextView) layout.findViewById(R.id.item_bet_succeed);
        int le = bet1.getText().toString().trim().length();
        if (le > 0) {
            if (type.equals(getString(R.string.game1d))) {
                if (le == 1) {
                    bet3.setText("");
                    return true;
                } else {

                    bet3.setText("Not a valid 1 digit number!");
                    return false;
                }
            } else if (type.equals(getString(R.string.game2d))) {
                if (le == 2) {
                    bet3.setText("");
                    return true;
                } else {
                    bet3.setText("Not a valid 2 digit number!");
                    return false;
                }
            } else {
                if (le == 3) {
                    bet3.setText("");
                    return true;
                } else {
                    bet3.setText("Not a valid 3 digit number!");
                    return false;
                }
            }
        }
        return true;
    }


    @Override
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