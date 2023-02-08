package nanyang.com.dig88.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.Hjlh6688OnlineWithdrawPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/10/24.
 */

public class Hjlh6688OnlineWithdrawFragment extends BaseFragment<Hjlh6688OnlineWithdrawPresenter> {

    @BindView(R.id.tv_toolbar_title)
    TextView tvToolbarTitle;
    @BindView(R.id.tv_toolbar_right)
    TextView tvToolbarRight;
    @BindView(R.id.img_toolbar_left)
    ImageView imgToolbarLeft;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.edt_open_bank)
    EditText edtOpenBank;
    @BindView(R.id.edt_bank_account)
    EditText edtBankAccount;
    @BindView(R.id.edt_bank_number)
    EditText edtBankNumber;
    @BindView(R.id.edt_tel)
    EditText edtTel;
    @BindView(R.id.edt_person_number)
    EditText edtPersonNumber;
    @BindView(R.id.edt_amount)
    EditText edtAmount;
    VipInfoBean info;
    String openBank;
    String personNumber;
    String amount;
    int minWithdraw = 1;
    int maxWithdraw = 10000;
    boolean isCanClickBank;
    BaseListPopWindow bankNamePop;
    private String ip;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_hjlh6688_online_withdraw;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new Hjlh6688OnlineWithdrawPresenter(this));
        ip = SharePreferenceUtil.getString(mContext, "IP");
        tvToolbarTitle.setText(getString(R.string.Online_Payment));
        tvToolbarRight.setBackgroundResource(R.mipmap.deposit_list);
        tvToolbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ActivityFragmentShow.class);
                intent.putExtra("type", WithdrawListFragment.class.getName());
                startActivity(intent);
            }
        });
        if (isNeedShowBack) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) imgToolbarLeft.getLayoutParams();
            params.width = UIUtil.dip2px(mContext, 25);
            params.height = UIUtil.dip2px(mContext, 25);
            imgToolbarLeft.setLayoutParams(params);
            imgToolbarLeft.setImageResource(R.mipmap.back_new);
            imgToolbarLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().finish();
                }
            });
        }
        initContent();
    }

    private void initContent() {
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        String bankName = info.getBank_name();
        if (!TextUtils.isEmpty(bankName)) {
            tvBankName.setText(info.getBank_name());
            edtBankAccount.setText(info.getBank_acc_name2());
            edtBankNumber.setText(info.getBank_acc_no2());
            edtTel.setText(info.getTel());
            edtBankAccount.setEnabled(false);
            edtBankNumber.setEnabled(false);
            edtTel.setEnabled(false);
            isCanClickBank = false;
        } else {
            isCanClickBank = true;
            presenter.getBankSate();
        }
        presenter.getWithdrawBank();
    }

    public void onGetBindViewingBank(final List<BankInfoBean> bankInfoBeanList) {
        final List<String> bankNameList = new ArrayList<>();
        for (int i = 0; i < bankInfoBeanList.size(); i++) {
            BankInfoBean bankInfoBean = bankInfoBeanList.get(i);
            String name = bankInfoBean.getName();
            bankNameList.add(name);
        }
        bankNamePop = new BaseListPopWindow(mContext, tvBankName, tvBankName.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public List<String> getContentData() {
                return bankNameList;
            }

            @Override
            public void onClickItem(int position, String item) {
                tvBankName.setText(item);
            }
        };
    }

    public void onGetWithdrawBank(List<BankAccountDetailBean> beanList) {
        BankAccountDetailBean bankAccountDetailBean = beanList.get(0);
        String maxWithdrawStr = bankAccountDetailBean.getMax_deposit();
        String minWithdrawStr = bankAccountDetailBean.getMin_deposit();
        if (!TextUtils.isEmpty(minWithdrawStr)) {
            minWithdraw = Integer.parseInt(minWithdrawStr);
        }
        if (!TextUtils.isEmpty(maxWithdrawStr)) {
            maxWithdraw = Integer.parseInt(maxWithdrawStr);
        }
    }

    private void submit() {
        openBank = edtOpenBank.getText().toString();
        if (TextUtils.isEmpty(openBank)) {
            ToastUtils.showShort(getString(R.string.branch_error));
            return;
        }
        personNumber = edtPersonNumber.getText().toString();
        if (TextUtils.isEmpty(personNumber)) {
            ToastUtils.showShort(getString(R.string.card_id_error));
            return;
        }
        amount = edtAmount.getText().toString().trim();
        if (TextUtils.isEmpty(amount)) {
            ToastUtils.showShort(getString(R.string.shurujine));
            return;
        } else if (Integer.parseInt(amount) < minWithdraw) {
            ToastUtils.showShort(getString(R.string.qukuanda) + minWithdraw);
            return;
        } else if (Integer.parseInt(amount) > maxWithdraw) {
            ToastUtils.showShort(getString(R.string.qukuanxiao) + maxWithdraw);
            return;
        }
        String balance = getUserInfo().getMoneyBalance().getBalance();
        if (Double.parseDouble(balance) < Double.parseDouble(amount)) {
            ToastUtils.showShort(getString(R.string.Balance_not_enough));
            return;
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("addressbank", tvBankName.getText().toString());
        p.put("branch", openBank);
        p.put("accountname", edtBankAccount.getText().toString());
        p.put("accountno", edtBankNumber.getText().toString());
        p.put("tel", edtTel.getText().toString());
        p.put("card_id", personNumber);
        p.put("amount", amount);
        presenter.goWithdraw(p);
    }

    public void onGetWithdrawResult(String msg) {
        ToastUtils.showShort(msg);
    }

    public void clearAmount() {
        edtAmount.setText("");
    }

    @OnClick({R.id.btn_submit, R.id.tv_bank_name})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_bank_name:
                if (bankNamePop != null && isCanClickBank) {
                    bankNamePop.showPopupDownWindow();
                }
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }
}
