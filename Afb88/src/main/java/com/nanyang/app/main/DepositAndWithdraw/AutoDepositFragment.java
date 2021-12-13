package com.nanyang.app.main.DepositAndWithdraw;

import android.widget.TextView;

import com.nanyang.app.R;

import butterknife.BindView;

public class AutoDepositFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;

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
