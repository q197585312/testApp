package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.Funplay26BoundBankPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2019/9/27.
 */

public class Funplay26BountBankFragment extends BaseFragment<Funplay26BoundBankPresenter> {
    @Bind(R.id.tv_choice_bank)
    TextView tvChoiceBank;
    @Bind(R.id.edt_bank_account)
    EditText edtBankAccount;
    @Bind(R.id.edt_bank_number)
    EditText edtBankNumber;
    @Bind(R.id.edt_code)
    EditText edtCode;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.ll_line_tel)
    LinearLayout llLineTel;
    @Bind(R.id.edt_line)
    EditText edtLine;
    @Bind(R.id.edt_tel)
    EditText edtTel;
    String addressbank;
    String accountname;
    String accountno;
    String password;
    List<ContentInfoBean> bankList;
    private VipInfoBean info;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_funplay26_bound_bank;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new Funplay26BoundBankPresenter(this));
        bankList = new ArrayList<>();
        info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        presenter.getDepositBank();
        if (!TextUtils.isEmpty(info.getAddress())) {
            edtCode.setVisibility(View.GONE);
            btnSubmit.setVisibility(View.GONE);
            tvChoiceBank.setEnabled(false);
            edtBankAccount.setEnabled(false);
            edtBankNumber.setEnabled(false);
            tvChoiceBank.setText(info.getAddress());
            edtBankAccount.setText(info.getBank_acc_name2());
            edtBankNumber.setText(info.getBank_acc_no2());
        }
    }

    public void onGetBankState(List<ContentInfoBean> beanList) {
        bankList = beanList;
    }

    public void onGetDepositBank(final List<BankAccountDetailBean> beanList) {
        bankList.clear();
        for (int i = 0; i < beanList.size(); i++) {
            BankAccountDetailBean bean = beanList.get(i);
            bankList.add(new ContentInfoBean(bean.getBank_name(), bean.getId_mod_bank()));
        }
    }

    public void onBoundSuccess() {
        ToastUtils.showShort(getString(R.string.Success));
        getActivity().finish();
    }

    public void onBoundFailed() {
        ToastUtils.showShort(getString(R.string.Failed));
    }

    public void onBoundFailed(String msg) {
        ToastUtils.showShort(msg);
    }

    @OnClick({R.id.btn_submit, R.id.tv_choice_bank})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                HashMap<String, String> p = new HashMap<>();
                if (TextUtils.isEmpty(addressbank) && !BuildConfig.FLAVOR.equals("mgold1")) {
                    ToastUtils.showShort(getString(R.string.qingxuanyh));
                    return;
                }
                accountname = edtBankAccount.getText().toString();
                accountno = edtBankNumber.getText().toString();
                p.put("web_id", WebSiteUrl.WebId);
                p.put("user_id", getUserInfo().getUser_id());
                p.put("session_id", getUserInfo().getSession_id());
                if (BuildConfig.FLAVOR.equals("mcd88") || BuildConfig.FLAVOR.equals("xslot88")) {
                    p.put("captcha", "1243");
                    p.put("bank", addressbank);
                    p.put("bankaccount", accountname);
                    p.put("bankno", accountno);
                    p.put("currency", getUserInfo().getId_mod_currency());
                    presenter.goBindingBank1(p);
                } else if (BuildConfig.FLAVOR.equals("mgold1")) {
                    p.put("full_name", accountname);
                    p.put("bankaccount", accountname);
                    p.put("captcha", "1243");
                    p.put("currency", getUserInfo().getId_mod_currency());
                    p.put("email", edtLine.getText().toString());
                    p.put("email_type", "line");
                    p.put("tel", edtTel.getText().toString());
                    presenter.goBindingBank1(p);
                } else {
                    password = edtCode.getText().toString();
                    p.put("addressbank", addressbank);
                    p.put("accountname", accountname);
                    p.put("accountno", accountno);
                    p.put("password", password);
                    presenter.goBindingBank(p);
                }
                break;
            case R.id.tv_choice_bank:
                if (bankList.size() > 0) {
                    BaseContentListPopWindow pop = new BaseContentListPopWindow(mContext, tvChoiceBank, tvChoiceBank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                        @Override
                        public List<ContentInfoBean> getContentData() {
                            return bankList;
                        }

                        @Override
                        public void onClickItem(int position, ContentInfoBean item) {
                            addressbank = item.getContentId();
                            if (BuildConfig.FLAVOR.equals("afbcash")) {
                                addressbank = item.getContent();
                            }
                            tvChoiceBank.setText(item.getContent());
                        }
                    };
                    pop.showPopupDownWindow();
                }
                break;
        }
    }
}
