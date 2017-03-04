package com.nanyang.app.load.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/5 0005.
 */
public class RegisterActivity extends BaseToolbarActivity<RegisterPresenter> implements RegisterContract.View, View.OnFocusChangeListener {


    @Bind(R.id.edt_account)
    EditText edtAccount;
    @Bind(R.id.tv_account_prompt)
    TextView tvAccountPrompt;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.tv_password_prompt)
    TextView tvPasswordPrompt;
    @Bind(R.id.edt_password_repeat)
    EditText edtPasswordRepeat;
    @Bind(R.id.tv_password_repeat_prompt)
    TextView tvPasswordRepeatPrompt;
    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.tv_email_prompt)
    TextView tvEmailPrompt;
    @Bind(R.id.btn_submit)
    Button btnSubmit;


    @Bind(R.id.tv_currency)
    TextView tvCurrency;
    @Bind(R.id.tv_currency_prompt)
    TextView tvCurrencyPorompt;
    @Bind(R.id.edt_contactnum)
    EditText edtContactnum;
    @Bind(R.id.tv_contactnum_prompt)
    TextView tvContactnumPrompt;
    @Bind(R.id.tv_bankname)
    TextView tvBankName;
    @Bind(R.id.tv_bankname_prompt)
    TextView tvBankNamePrompt;
    @Bind(R.id.edt_accountname)
    EditText edtAccountname;
    @Bind(R.id.tv_accountname_prompt)
    TextView tvAccountnamePrompt;
    @Bind(R.id.edt_accountnumber)
    EditText edtAccountnumber;
    @Bind(R.id.tv_accountnumber_prompt)
    TextView tvAccountnumberPrompt;


    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onGetData(String data) {
        Toast.makeText(mContext, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createPresenter(new RegisterPresenter(this));
        initCheck();
    }

    private void initCheck() {
        edtAccount.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);
        edtPasswordRepeat.setOnFocusChangeListener(this);
        edtContactnum.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtAccountname.setOnFocusChangeListener(this);
        edtAccountnumber.setOnFocusChangeListener(this);
    }

    @OnClick({R.id.btn_submit, R.id.tv_currency, R.id.tv_bankname})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                bankName = tvBankName.getText().toString();
                String currencyId = getCurrencyId();
                accountNum = edtAccountnumber.getText().toString();
                RegisterInfo userInfo = new RegisterInfo(account, password, passwordRepeat, currencyId, contactNum, email, bankName, accountName, accountNum);
                if (accountOk && passwordOk && passwordRepeatOk && emailOk && contactNumOk && accountNameOk && !TextUtils.isEmpty(bankName) && !TextUtils.isEmpty(currencyId) && !TextUtils.isEmpty(accountNum)) {
                    presenter.register(userInfo);
                } else {
                    Toast.makeText(mContext, "少了参数", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_currency:
                presenter.initCurrency();
                BaseListPopupWindow<String> popu = new BaseListPopupWindow<String>(mContext, view, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, tvCurrency, tvCurrencyPorompt) {
                    @Override
                    public int getRecyclerViewId() {
                        return R.id.base_rv;
                    }

                    @Override
                    public List<String> getData() {
                        return presenter.currencyList;
                    }
                };
                popu.showPopupDownWindow();
                break;
            case R.id.tv_bankname:
                presenter.initBank();
                BaseListPopupWindow<String> popupWindow = new BaseListPopupWindow<String>(mContext, view, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, tvBankName, tvBankNamePrompt) {
                    @Override
                    public int getRecyclerViewId() {
                        return R.id.base_rv;
                    }

                    @Override
                    public List<String> getData() {
                        return presenter.bankList;
                    }
                };
                popupWindow.showPopupDownWindow();
                break;
        }

    }

    private String getCurrencyId() {
        if (!TextUtils.isEmpty(tvCurrency.getText().toString())) {
            return presenter.currencyMap.get(tvCurrency.getText().toString());
        }
        return null;
    }

    String bankName;
    String account;
    String password;
    String passwordRepeat;
    String email;
    String contactNum;
    String accountNum;
    String accountName;
    boolean accountOk;
    boolean passwordOk;
    boolean passwordRepeatOk;
    boolean emailOk;
    boolean contactNumOk;
    boolean accountNameOk;

    @Override
    public void onFocusChange(View view, boolean b) {
        switch (view.getId()) {
            case R.id.edt_account:
                if (!b) {
                    account = edtAccount.getText().toString();
                    tvAccountPrompt.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(account)) {
                        tvAccountPrompt.setText("OK");
                        accountOk = true;
                        presenter.checkUserName(new RegisterInfo(account));
                    } else {
                        tvAccountPrompt.setText("UserName empty.Please check!");
                        accountOk = false;
                    }
                }
                break;
            case R.id.edt_password:
                if (!b) {
                    password = edtPassword.getText().toString();
                    tvPasswordPrompt.setVisibility(View.VISIBLE);
                    if (TextUtils.isEmpty(password) || password.length() < 6) {
                        tvPasswordPrompt.setText(" Please enter correct password length(6-12)!");
                        passwordOk = false;
                    } else if (password.length() >= 6 && !TextUtils.isEmpty(password)) {
                        tvPasswordPrompt.setText("OK");
                        passwordOk = true;
                    }

                }
                break;
            case R.id.edt_password_repeat:
                if (!b) {
                    passwordRepeat = edtPasswordRepeat.getText().toString();
                    tvPasswordRepeatPrompt.setVisibility(View.VISIBLE);
                    if (!TextUtils.isEmpty(password) && !TextUtils.isEmpty(passwordRepeat) && passwordRepeat.equals(password)) {
                        tvPasswordRepeatPrompt.setText("OK");
                        passwordRepeatOk = true;
                    } else {
                        tvPasswordRepeatPrompt.setText("Please enter correct Verify Password!");
                        passwordRepeatOk = false;
                    }
                }
                break;
            case R.id.edt_email:
                if (!b) {
                    email = edtEmail.getText().toString();
                    tvEmailPrompt.setVisibility(View.VISIBLE);
                    String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
                    if (!TextUtils.isEmpty(email) && StringUtils.matches(email, rex)) {
                        tvEmailPrompt.setText("OK");
                        emailOk = true;
                    } else {
                        tvEmailPrompt.setText("invalid email format.Please check!");
                        emailOk = false;
                    }
                }
                break;
            case R.id.edt_contactnum:
                contactNum = edtContactnum.getText().toString();
                tvContactnumPrompt.setVisibility(View.VISIBLE);
                if (!b) {
                    if (!TextUtils.isEmpty(contactNum)) {
                        tvContactnumPrompt.setText("OK");
                        contactNumOk = true;
                    } else {
                        tvContactnumPrompt.setText("Contact Number empty.Please check!");
                        contactNumOk = false;
                    }
                }
                break;
            case R.id.edt_accountname:
                accountName = edtAccountname.getText().toString();
                tvAccountnamePrompt.setVisibility(View.VISIBLE);
                if (!b) {
                    if (!TextUtils.isEmpty(accountName)) {
                        tvAccountnamePrompt.setText("OK");
                        accountNameOk = true;
                    } else {
                        tvAccountnamePrompt.setText("Account Name empty.Please check!");
                        accountNameOk = false;
                    }
                }
                break;
            case R.id.edt_accountnumber:
                accountNum = edtAccountnumber.getText().toString();
                tvAccountnumberPrompt.setVisibility(View.VISIBLE);
                if (!b) {
                    if (!TextUtils.isEmpty(accountNum)) {
                        tvAccountnumberPrompt.setText("OK");
                    } else {
                        tvAccountnumberPrompt.setText("Account Number empty.Please check!");
                    }
                }
                break;
        }
    }


}
