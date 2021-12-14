package com.nanyang.app.main.DepositAndWithdraw;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawDataBean;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawTakeAwayBean;
import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

public class WithdrawFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    @BindView(R.id.tv_bank_name_content)
    TextView tvBankNameContent;
    @BindView(R.id.tv_account_name)
    TextView tvAccountName;
    @BindView(R.id.tv_account_number)
    TextView tvAccountNumber;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_withdraw;
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getWithdrawData();
    }

    @Override
    public void onGetWithdrawData(WithdrawDataBean bean) {
        tvBankNameContent.setText(bean.getBankNameinput());
        tvAccountName.setText(bean.getAccountNameinput());
        tvAccountNumber.setText(bean.getAccountNumberinput());
    }

    @Override
    public void onGetSubmitWithdrawData(WithdrawTakeAwayBean bean) {
        ToastUtils.showShort(bean.getLblStatus());
    }

    @OnClick({R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_submit:
                String amount = edtAmount.getText().toString().trim();
                if (!TextUtils.isEmpty(amount)) {
                    if (Integer.parseInt(amount) > 0) {
                        presenter.submitWithdrawData(amount);
                    }
                }
                break;
        }
    }
}
