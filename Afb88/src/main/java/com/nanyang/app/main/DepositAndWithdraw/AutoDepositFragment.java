package com.nanyang.app.main.DepositAndWithdraw;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
    @BindView(R.id.tv_result_bank_name)
    TextView tvResultBankName;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.ll_result)
    LinearLayout llResult;
    @BindView(R.id.tv_final_amount)
    TextView tvFinalAmount;
    @BindView(R.id.tv_time_limit)
    TextView tvTimeLimit;
    @BindView(R.id.tv_result_bank_name_content)
    TextView tvResultBankNameContent;
    @BindView(R.id.tv_result_account_name)
    TextView tvResultAccountName;
    @BindView(R.id.tv_result_account_number)
    TextView tvResultAccountNumber;
    private List<AutoDepositBean> list;
    private AutoDepositBean currentAutoDepositBean;
    private int count;
    private Handler handler = new Handler();
    private boolean isCanCountDown = true;
    private boolean isNeedSendExpired = true;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_auto_deposit;
    }

    @Override
    public void initView() {
        super.initView();
        tvBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
        tvResultBankName.setText(getString(R.string.to) + ": " + getString(R.string.bank_name));
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
    public void onGetSubmitAutoDepositData(String finalAmount, String depReqId) {
        isNeedSendExpired = true;
        tvFinalAmount.setText(finalAmount);
        tvResultBankNameContent.setText(tvBankNameContent.getText().toString());
        tvResultAccountName.setText(tvAccountName.getText().toString());
        tvResultAccountNumber.setText(tvAccountNumber.getText().toString());
        count = 900;
        new Thread() {
            @Override
            public void run() {
                while (count > 0 && isCanCountDown) {
                    count--;
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (tvTimeLimit != null) {
                                int sec = count % 60;
                                int min = count / 60;
                                tvTimeLimit.setText(((min < 10) ? "0" + min : min) + " : " + ((sec < 10) ? "0" + sec : sec));
                                if (count % 8 == 0) {
                                    presenter.checkAutoDeposit(finalAmount, depReqId, 2);
                                }
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.d("kkkaaak", "count: " + count);
                }
                if (isNeedSendExpired) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("kkkaaak", "sendExpired: " + count);
                            onGetSubmitAutoDepositErrorData("Your deposit request is expired. Please submit another deposit request.");
                            presenter.expiredAutoDeposit(depReqId);
                        }
                    });
                }
            }
        }.start();
        llResult.setVisibility(View.VISIBLE);
        llResult.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    @Override
    public void onGetCheckData() {
        count = 0;
        isNeedSendExpired = false;
        llResult.setVisibility(View.GONE);
        scrollview.fullScroll(ScrollView.FOCUS_UP);
    }

    @Override
    public void onGetSubmitAutoDepositErrorData(String msg) {
        ToastUtils.showLong(msg);
    }

    @Override
    public void onGetExpireData() {
        llResult.setVisibility(View.GONE);
        scrollview.fullScroll(ScrollView.FOCUS_UP);
    }

    @OnClick({R.id.tv_bank_name_content, R.id.tv_submit, R.id.tv_copy})
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
            case R.id.tv_copy:
                ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData mClipData = ClipData.newPlainText("Label", tvResultAccountNumber.getText().toString().trim());
                cm.setPrimaryClip(mClipData);
                ToastUtils.showLong("Copy Successful");
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isCanCountDown = false;
        handler.removeCallbacksAndMessages(null);
    }
}
