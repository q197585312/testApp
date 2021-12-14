package com.nanyang.app.main.DepositAndWithdraw;

import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.R;

import butterknife.BindView;

public class AutoDepositFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.tv_bank_name_content)
    TextView tvBankNameContent;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_account_number)
    TextView tvAccountNumber;
    @BindView(R.id.tv_min_amount)
    TextView tvMinAmount;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_auto_deposit;
    }

    @Override
    public void initView() {
        super.initView();
        tvBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
    }
}
