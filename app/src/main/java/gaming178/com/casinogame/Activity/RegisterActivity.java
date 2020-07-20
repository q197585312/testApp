package gaming178.com.casinogame.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.baccaratgame.R2;
import gaming178.com.casinogame.Control.GdThreadHander;
import gaming178.com.casinogame.Util.HttpClient;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.entity.CurrencyBean;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.base.RequestBean;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BaseListPopupWindow;

/**
 * Created by Administrator on 2015/12/19.
 */
public class RegisterActivity extends gaming178.com.casinogame.base.BaseActivity {

    private boolean confirmed = false;

    @BindView(R2.id.gd__edt_register_username)
    EditText edtRegisterUsername;
    @BindView(R2.id.gd__tv_register_username_hint)
    TextView tvRegisterUsernameHint;
    @BindView(R2.id.gd__edt_register_password)
    EditText edtRegisterPassword;
    @BindView(R2.id.gd__tv_register_password_hint)
    TextView tvRegisterPasswordHint;
    @BindView(R2.id.gd__edt_register_password_confirm)
    EditText edtRegisterPasswordConfirm;
    @BindView(R2.id.gd__tv_register_password_confirm_hint)
    TextView tvRegisterPasswordConfirmHint;
    @BindView(R2.id.gd__edt_register_telephone)
    EditText edtRegisterTelephone;
    @BindView(R2.id.gd__edt_register_email)
    EditText edtRegisterEmail;
    @BindView(R2.id.gd__tv_register_email_hint)
    TextView tvRegisterEmailHint;
    @BindView(R2.id.gd__tv_register_telephone_hint)
    TextView tvRegisterTelephoneHint;
    @BindView(R2.id.gd__edt_register_full_name)
    EditText edtRegisterFullName;
    @BindView(R2.id.gd__tv_register_choice_bank)
    TextView tvRegisterChoiceBank;
    @BindView(R2.id.gd__tv_register_full_name_hint)
    TextView tvRegisterFullNameHint;
    @BindView(R2.id.gd__tv_register_bank_account_hint)
    TextView tvRegisterBankAccountHint;
    @BindView(R2.id.gd__tv_bizhong)
    TextView tvBizhong;
    @BindView(R2.id.gd__tv_bizhong1)
    TextView tvBizhong1;
    @BindView(R2.id.gd__ll_bizhong)
    LinearLayout llBizhong;
    @BindView(R2.id.gd__edt_register_bank_account)
    EditText edtRegisterBankAccount;
    @BindView(R2.id.gd__edt_register_bank_number)
    EditText edtRegisterBankNumber;
    @BindView(R2.id.gd__edt_register_verify_code)
    EditText edtRegisterVerifyCode;
    @BindView(R2.id.gd__tv_register_verify_code)
    TextView tvRegisterVerifyCode;
    @BindView(R2.id.gd__tv_register_verify_code_hint)
    TextView tvRegisterVerifyCodeHint;
    @BindView(R2.id.gd__btn_register)
    Button btnRegist;


    private boolean userNameConfirmed;
    private boolean passwordConfirmed;
    private boolean passwordRepeatConfirmed;
    private String useNameStr;
    private String passwordStr;
    private String passwordRepeatStr;
    private String emailStr;
    private boolean emailConfirmed;
    private boolean telephoneConfirmed;
    private String telephoneStr;
    private boolean fullNameConfirmed;
    private String fullNameStr;
    private boolean bankAccountConfirmed;
    private String bankAccountStr;
    private String hintStr = "UserName empty.Please check!";
    private boolean verifyCodeConfirmed;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initFocusChangeListener();
        createVerifyCode();
        setLayout.setVisibility(View.GONE);
        titleTv.setText(getString(R.string.register));
        if (BuildConfig.FLAVOR.equals("mejaemas")) {
            tvRegisterChoiceBank.setVisibility(View.GONE);
            edtRegisterBankAccount.setVisibility(View.GONE);
            edtRegisterBankNumber.setVisibility(View.GONE);
        }
        getBank();
    }

    HttpClient httpClient;
    List<CurrencyBean> currencyBeanList;

    private void getBank() {
        httpClient = new HttpClient(WebSiteUrl.INDEX, mAppViewModel.getCookie());
        currencyBeanList = new ArrayList<>();
        new Thread() {
            @Override
            public void run() {
                String getDomainInformUrl = "http://www.grjl25.com/getDomainInform.jsp?";
                String httpResult = httpClient.getHttpClient(getDomainInformUrl + "labelid=" + BuildConfig.Labelid, null);
                WebSiteUrl.setNormal(httpResult);
                String url = httpResult + "getBank.jsp";
                String result = httpClient.sendPost(url, "");
                String[] bankArr = result.split("#");
                for (int i = 0; i < bankArr.length; i++) {
                    if (i == 0) {
                        continue;
                    }
                    CurrencyBean bean = new CurrencyBean(i + "", bankArr[i]);
                    currencyBeanList.add(bean);
                }
            }
        }.start();
    }

    private void initFocusChangeListener() {
        edtRegisterUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus)
                    userNameConfirmed = checkUserName();
            }
        });
        edtRegisterPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    passwordConfirmed = checkPassword();
                    if (passwordConfirmed)
                        passwordRepeatConfirmed = checkPasswordRepeat();
                }
            }
        });
        edtRegisterPasswordConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    passwordRepeatConfirmed = checkPasswordRepeat();
                }
            }
        });
        edtRegisterEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    emailConfirmed = checkEmail();
                }
            }
        });
        edtRegisterTelephone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    telephoneConfirmed = checkTelephone();
                }
            }
        });
        edtRegisterFullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    fullNameConfirmed = checkFullName();
                }
            }
        });
        edtRegisterVerifyCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    verifyCodeConfirmed = checkVerifyCode();
                }
            }
        });

    }

    private boolean checkFullName() {
        fullNameStr = edtRegisterFullName.getText().toString().trim();
        tvRegisterFullNameHint.setVisibility(View.VISIBLE);

        if (fullNameStr.isEmpty()) {
            tvRegisterFullNameHint.setText(" Full name empty.Please check!");
            hintStr = " Full name empty.Please check!";
            return false;
        } else {
            hintStr = "";
            tvRegisterFullNameHint.setText("OK");
        }
        return true;
    }

    private boolean checkTelephone() {
        telephoneStr = edtRegisterTelephone.getText().toString().trim();
        tvRegisterTelephoneHint.setVisibility(View.VISIBLE);

        if (telephoneStr.isEmpty()) {
            tvRegisterTelephoneHint.setText(" Contact Number empty.Please check!");
            hintStr = " Contact Number empty.Please check!";
            return false;
        } else {
            hintStr = "";
            tvRegisterTelephoneHint.setText("OK");
        }
        return true;
    }

    private boolean checkEmail() {
        String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
        emailStr = edtRegisterEmail.getText().toString().trim();
        tvRegisterEmailHint.setVisibility(View.VISIBLE);
        if (!StringUtils.matches(emailStr, rex)) {
            tvRegisterEmailHint.setText(" invalid email format.Please check!");
            hintStr = "invalid email format.Please check!";
            return false;
        } else {
            hintStr = "";
            tvRegisterEmailHint.setText("OK");
        }
        return true;
    }

    private boolean checkPasswordRepeat() {
        passwordRepeatStr = edtRegisterPasswordConfirm.getText().toString().trim();
        tvRegisterPasswordConfirmHint.setVisibility(View.VISIBLE);
        if (passwordRepeatStr.length() < 6 || !passwordConfirmed) {
            tvRegisterPasswordConfirmHint.setText(" Please enter correct Verify Password !");
            hintStr = " Please enter correct Verify Password !";
            return false;
        } else if (!passwordRepeatStr.equals(passwordStr)) {
            tvRegisterPasswordConfirmHint.setText(" Two passwords not match!");
            hintStr = " Two passwords not match!";
        } else {
            tvRegisterPasswordConfirmHint.setText("OK");
            hintStr = "";
        }
        return true;
    }

    private boolean checkPassword() {
        passwordStr = edtRegisterPassword.getText().toString().trim();
        tvRegisterPasswordHint.setVisibility(View.VISIBLE);
        if (passwordStr.length() < 6) {
            tvRegisterPasswordHint.setText(" Please enter correct password length(6-12)!");
            hintStr = " Please enter correct password length(6-12)!";
            return false;
        } else {
            tvRegisterPasswordHint.setText("OK");
            hintStr = "";
        }
        return true;

    }

    private boolean checkUserName() {
        useNameStr = edtRegisterUsername.getText().toString().trim();
        tvRegisterUsernameHint.setVisibility(View.VISIBLE);
        if (useNameStr.isEmpty()) {
            tvRegisterUsernameHint.setText("UserName empty.Please check!");
            hintStr = "UserName empty.Please check!";
            return false;
        } else if (useNameStr.length() < 4) {
            tvRegisterUsernameHint.setText("UserName must be at least 4 character!");
            hintStr = "UserName must be at least 4 character!";
            return false;
        } else if (!isUserNameOK()) {
            tvRegisterUsernameHint.setText("Username cannot contain special characters!");
            hintStr = "Username cannot contain special characters!";
            return false;
        } else {
            hintStr = "";
            tvRegisterUsernameHint.setText("Username is available.");
        }
        return true;

    }

    private boolean isUserNameOK() {
        String matches = "[A-Za-z0-9_\\-#]+";
        boolean b = useNameStr.matches(matches);
        return b;
    }

    private boolean checkVerifyCode() {
        String code = edtRegisterVerifyCode.getText().toString().trim();
        String code2 = tvRegisterVerifyCode.getText().toString().trim();
        tvRegisterVerifyCodeHint.setVisibility(View.VISIBLE);
        if (!code.equals(code2)) {
            tvRegisterVerifyCodeHint.setText(" check code input error!");
            hintStr = " check code input error!";
            return false;
        } else {
            tvRegisterVerifyCodeHint.setText("OK");
            hintStr = "";
        }
        return true;

    }

    /**
     * 验证输入并注册
     */
    private void checkAndRegister() {
        if (checkUserName() && checkPassword() && checkPasswordRepeat() && checkTelephone() && checkEmail() && checkFullName() && checkVerifyCode())
            register();
        else
            Toast.makeText(mContext, hintStr, Toast.LENGTH_LONG).show();
    }

    /**
     * 注册请求
     */


    /**
     * 随机验证码
     *
     * @return
     */
    private void createVerifyCode() {
        Random random = new Random();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            buffer.append(random.nextInt(10));
        }
        tvRegisterVerifyCode.setText(buffer.toString());
    }

    /*http://113.130.125.210/RajaCasino88/register2.jsp
    ?username=RAGA05&Password=111111&VerifyPassword=111111&ContactNumber=12345552&Email=testaa@11.com&FullName=GDCGG&selectMemBank=BCA&memAccountName=ADAD&memAccountNumber=132525225*/
    public void clickRegister(View view) {
        useNameStr = edtRegisterUsername.getText().toString().trim();
        checkAndRegister();
    }

    public void register() {
        GdThreadHander threadHander = new GdThreadHander(mContext) {
            @Override
            protected RequestBean<String> getRequestBean() {
                String params = constructParams();
                return new RequestBean<String>(WebSiteUrl.Register_Url, params);
            }

            @Override
            public void successEnd(String obj) {
                dismissBlockDialog();
                finish();
            }

            @Override
            public void errorEnd(String obj) {
                dismissBlockDialog();
                if (obj.startsWith("Results=error#1")) {
                    Toast.makeText(mContext, "Username already exists. ", Toast.LENGTH_LONG).show();
                } else if (obj.startsWith("Results=error")) {
                    Toast.makeText(mContext, "Register fail. ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(mContext, "Net Error. ", Toast.LENGTH_LONG).show();
                }
            }
        };
        showBlockDialog();
        threadHander.startThread(null);
    }


    private String constructParams() {
        //username=RAGA05&Password=111111&VerifyPassword=111111&ContactNumber=12345552&Email=testaa@11.com&FullName=GDCGG&selectMemBank=BCA&memAccountName=ADAD&memAccountNumber=132525225
        StringBuilder builder = new StringBuilder();
        builder.append("username=");
        builder.append(edtRegisterUsername.getText().toString().trim());
        builder.append("&");
        builder.append("Password=");
        builder.append(edtRegisterPassword.getText().toString().trim());
        builder.append("&");
        builder.append("VerifyPassword=");
        builder.append(edtRegisterPasswordConfirm.getText().toString().trim());
        builder.append("&");
        builder.append("ContactNumber=");
        builder.append(edtRegisterTelephone.getText().toString().trim());
        builder.append("&");
        builder.append("Email=");
        builder.append(edtRegisterEmail.getText().toString().trim());
        builder.append("&");
        builder.append("FullName=");
        builder.append(edtRegisterFullName.getText().toString().trim());
        if (!BuildConfig.FLAVOR.equals("mejaemas")) {
            builder.append("&");
            builder.append("selectMemBank=");
            builder.append(tvRegisterChoiceBank.getText().toString().trim());
            builder.append("&");
            builder.append("memAccountName=");
            builder.append(edtRegisterBankAccount.getText().toString().trim());
            builder.append("&");
            builder.append("memAccountNumber=");
            builder.append(edtRegisterBankNumber.getText().toString().trim());
        }
        return builder.toString();
    }

    public void clickChoiceBank(View view) {
        BaseListPopupWindow<CurrencyBean> pop = new BaseListPopupWindow<CurrencyBean>(mContext, view, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT) {

            @Override
            protected void popItemCLick(CurrencyBean currencyBean, int position) {
                tvRegisterChoiceBank.setText(currencyBean.getCurrencyName());

            }

            @Override
            protected void convertItem(ViewHolder helper, CurrencyBean item, int position) {
                helper.setText(R.id.text_tv1, item.getCurrencyName());
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                getAbListViewRes(view).setBackgroundResource(R.drawable.rectangle_white_graystroke_radius0);
            }
        };
        pop.setData(currencyBeanList);
        pop.showPopupDownWindow();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_register;
    }

    public void clickBack(View view) {
        finish();
    }
}
