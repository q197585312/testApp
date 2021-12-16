package com.nanyang.app.main.DepositAndWithdraw;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.DepositAndWithdraw.Bean.AutoDepositBean;
import com.nanyang.app.main.DepositAndWithdraw.Pop.PopAutoBankName;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private List<AutoDepositBean> list;
    private AutoDepositBean currentAutoDepositBean;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_auto_deposit;
    }

    @Override
    public void initView() {
        super.initView();
        tvBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
    }

    @Override
    public void initData() {
        super.initData();
        presenter.getAutoDepositData();
    }

    @Override
    public void onGetAutoDepositData(List<AutoDepositBean> list) {
        this.list = list;
        if (list != null && list.size() > 0) {
            initUI(list.get(0));
        }
    }

    private void initUI(AutoDepositBean bean) {
        currentAutoDepositBean = bean;
        tvBankNameContent.setText(bean.getBankName());
        tvAccountName.setText(bean.getBankAccountName());
        tvAccountNumber.setText(bean.getBankAccountNum());
        tvMinAmount.setText(bean.getMinDepositAmt() + "");
    }

    @Override
    public void onGetSubmitAutoDepositData(String msg) {
        ToastUtils.showLong(msg);
        if (msg.contains("successful")) {
            edtAmount.setText("");
        }
    }

    @OnClick({R.id.tv_bank_name_content, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_bank_name_content:
                PopAutoBankName popBankName = new PopAutoBankName(mContext, tvBankNameContent) {
                    @Override
                    public void setBankData(AutoDepositBean bean) {
                        initUI(bean);
                    }
                };
                if (list != null && list.size() > 0) {
                    popBankName.setData(list, tvBankNameContent.getText().toString().trim());
                    popBankName.showPopupDownWindowNoBlack();
                }
                break;
            case R.id.tv_submit:
                String amountStr = edtAmount.getText().toString().trim();
                int minDepositAmt = currentAutoDepositBean.getMinDepositAmt();
                if (!TextUtils.isEmpty(amountStr)) {
                    int amount = Integer.parseInt(amountStr);
                    if (amount >= minDepositAmt) {
                        String accName = tvAccountName.getText().toString().trim();
                        String accNumber = tvAccountNumber.getText().toString().trim();
                        String lstBank = currentAutoDepositBean.getBankId() + "";
                        presenter.submitAutoDeposit(amountStr, accName, accNumber, lstBank, minDepositAmt + "");
                    } else {
                        ToastUtils.showLong("Min Deposit Amount: " + minDepositAmt);
                    }
                } else {
                    ToastUtils.showLong("Min Deposit Amount: " + minDepositAmt);
                }
                break;
        }
    }

}
