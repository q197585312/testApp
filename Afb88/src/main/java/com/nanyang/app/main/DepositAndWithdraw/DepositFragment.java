package com.nanyang.app.main.DepositAndWithdraw;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;

import butterknife.BindView;

public class DepositFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.tv_pay_method)
    TextView tvPayMethod;
    @BindView(R.id.tv_bank_name_content)
    TextView tvBankNameContent;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_account_number)
    TextView tvAccountNumber;
    @BindView(R.id.ll_upload)
    LinearLayout llUpload;
    @BindView(R.id.tv_no_img)
    TextView tvNoImg;
    @BindView(R.id.edt_remark)
    EditText edtRemark;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;


    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_deposit;
    }

    @Override
    public void initView() {
        super.initView();
        tvBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
    }
}
