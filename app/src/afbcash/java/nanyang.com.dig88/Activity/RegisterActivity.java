package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Config.AppConfig;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.BankInfoBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.RegistInfo;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;
import nanyang.com.dig88.load.RegisterPresenter;

/**
 * Created by Administrator on 2015/12/19.
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> implements View.OnClickListener {
    @Bind(R.id.register_name)
    EditText register_name;
    @Bind(R.id.register_pwd)
    EditText register_pwd;
    @Bind(R.id.register_again_pwd)
    EditText register_again_pwd;
    @Bind(R.id.choice_bank)  //选择银行
            TextView choice_bank;
    @Bind(R.id.bank_username)
    EditText bank_username;
    @Bind(R.id.bank_account)
    EditText bank_account;
    //比种类
    @Bind(R.id.money_lei)
    TextView money_lei;  //刚开始的选择
    @Bind(R.id.tuijian_person)
    EditText tuijian_person;
    @Bind(R.id.mail)
    EditText mail;
    @Bind(R.id.phone_number)
    EditText phone_number;
    @Bind(R.id.yanzhengma)
    EditText yanzhengma;
    @Bind(R.id.tv_yanzhengma)
    TextView tv_yanzhengma;
    @Bind(R.id.btn_regist)
    Button btn_regist;
    //错误xinx
    @Bind(R.id.register_account_name_cunzai)
    TextView register_account_name_cunzai;
    @Bind(R.id.register_step1_account_name_tip_tv)
    TextView register_step1_account_name_tip_tv;
    @Bind(R.id.register_step1_password_error)
    TextView register_step1_password_error;
    @Bind(R.id.register_step1_password_tip_tv)
    TextView register_step1_password_tip_tv;
    @Bind(R.id.bank_username_error)
    TextView bank_username_error;
    @Bind(R.id.bank_accout_error)
    TextView bank_accout_error;
    @Bind(R.id.mail_error)
    TextView mail_error;
    @Bind(R.id.phone_number_error)
    TextView phone_number_error;
    @Bind(R.id.yanzhengma_error)
    TextView yanzhengma_error;
    @Bind(R.id.ll_bank)
    LinearLayout llBank;
    public List<BankAccountDetailBean> list1 = new ArrayList<BankAccountDetailBean>();  //货币种类(单单银行)
    //转string
    String stregistername;
    String strregisterpasswd;
    String strcomfirpasswd;
    String strusername;
    String straccount;
    String strperson;
    String strmail;
    String strnumber;
    String stryanzhengma;
    String strbankname;
    String strbankid;
    String strbizhong;
    String stringyanzhengma;
    int tag;
    String affiliateId;
    String isNeedSend;
    boolean isNeedBank = false;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setTitle(getString(R.string.register));
        setleftViewEnable(true);
        isNeedSend = getIntent().getStringExtra("isNeedSend");
        createPresenter(new RegisterPresenter(this));
        btn_regist.setOnClickListener(this);
        choice_bank.setOnClickListener(this);
        tv_yanzhengma.setOnClickListener(this);
        money_lei.setOnClickListener(this);
        affiliateId = getApp().getAffiliateId();
        if (getIntent().getExtras() != null) {
            String refName = getIntent().getExtras().getString(AppConfig.ACTION_KEY_INITENT_DATA);
            if (refName != null && refName.length() > 0)
                tuijian_person.setText(refName);
        }
        stringyanzhengma = presenter.createCode();
        tv_yanzhengma.setText(stringyanzhengma);
        String localLanguage = getLocalLanguage();
        switch (localLanguage) {
            case "in":
                isNeedBank = true;
                strbizhong = "11";
                presenter.getAfbcashBank();
                break;
            default:
                isNeedBank = false;
                llBank.setVisibility(View.GONE);
                money_lei.setVisibility(View.VISIBLE);
                if (localLanguage.equals("kh")) {
                    strbizhong = "1";
                    money_lei.setText("USD");
                } else if (localLanguage.equals("th")) {
                    strbizhong = "4";
                    money_lei.setText("THB");
                } else if (localLanguage.equals("vn")) {
                    tuijian_person.setVisibility(View.GONE);
                    strbizhong = "8";
                    money_lei.setText("VND");
                } else {
                    strbizhong = "3";
                    money_lei.setText("MYR");
                }
                break;
        }
        initViewContent();
        initFocusChangeListener();
    }

    private void initViewContent() {
        stregistername = register_name.getText().toString();
        strregisterpasswd = register_pwd.getText().toString();
        strcomfirpasswd = register_again_pwd.getText().toString();
        strusername = bank_username.getText().toString();
        straccount = bank_account.getText().toString();
        strperson = tuijian_person.getText().toString();
        strmail = mail.getText().toString();
        strnumber = phone_number.getText().toString();
        stryanzhengma = yanzhengma.getText().toString();
    }

    private void initFocusChangeListener() {
        register_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 1;
                } else {
                    presenter.checkUser(register_name.getText().toString());
                }
            }
        });
        register_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 2;
                }
            }
        });
        register_again_pwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 3;
                } else {
                    if (register_pwd.getText().toString().equals(register_again_pwd.getText().toString())) {
                        register_step1_password_tip_tv.setVisibility(View.GONE);
                    } else {
                        register_step1_password_tip_tv.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        bank_username.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 4;
                }
            }
        });
        bank_account.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 5;
                }
            }
        });
        mail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 6;
                }
            }
        });
        phone_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 7;
                }
            }
        });
        yanzhengma.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    check();
                    tag = 8;
                }
            }
        });
    }

    public void check() {
        if (tag == 1) {
            stregistername = register_name.getText().toString();
            if (stregistername.length() > 5 && stregistername.length() < 10) {
                register_step1_account_name_tip_tv.setVisibility(View.GONE);
            } else {
                clean();
                register_step1_account_name_tip_tv.setVisibility(View.VISIBLE);
            }
        } else if (tag == 2) {
            strregisterpasswd = register_pwd.getText().toString();
            String regex = ".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]"; //匹配字母和数字组合
            boolean numzimu = strregisterpasswd.matches(regex);
            if (strregisterpasswd.length() > 7 && strregisterpasswd.length() < 21 && numzimu == true) {
                register_step1_password_error.setVisibility(View.GONE);
            } else {
                clean();
                register_step1_password_error.setVisibility(View.VISIBLE);
            }
        } else if (tag == 3) {
            strregisterpasswd = register_pwd.getText().toString();
            strcomfirpasswd = register_again_pwd.getText().toString();

            if (strcomfirpasswd.length() == 0) {
                if (strregisterpasswd.length() > 0) {
                    register_step1_password_tip_tv.setVisibility(View.VISIBLE);
                } else {
                    register_step1_password_tip_tv.setVisibility(View.GONE);
                }
            } else if (!strcomfirpasswd.equals(strregisterpasswd)) {
                clean();
                register_step1_password_tip_tv.setVisibility(View.VISIBLE);
            }
        } else if (tag == 4) {
            strusername = bank_username.getText().toString();
            if (strusername.length() > 1 && strusername.length() < 21) {
                bank_username_error.setVisibility(View.GONE);
            } else {
                clean();
                bank_username_error.setVisibility(View.VISIBLE);
            }
        } else if (tag == 5) {
            straccount = bank_account.getText().toString();
            if (straccount.length() > 7 && straccount.length() < 17) {
                bank_accout_error.setVisibility(View.GONE);
            } else {
                clean();
                bank_accout_error.setVisibility(View.VISIBLE);
            }
        } else if (tag == 6) {
            strmail = mail.getText().toString();
            String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
            boolean youxiang = strmail.matches(rex);
            if (youxiang == true) {
                mail_error.setVisibility(View.GONE);
            } else {
                clean();
                mail_error.setVisibility(View.VISIBLE);
            }
        } else if (tag == 7) {
            strnumber = phone_number.getText().toString();
            if (strnumber.length() > 5 && strnumber.length() < 21) {
                phone_number_error.setVisibility(View.GONE);
            } else {
                clean();
                phone_number_error.setVisibility(View.VISIBLE);
            }
        } else if (tag == 8) {
            stryanzhengma = yanzhengma.getText().toString();
            if (stryanzhengma.equals(stringyanzhengma)) {
                yanzhengma_error.setVisibility(View.GONE);
            } else {
                clean();
                yanzhengma_error.setVisibility(View.VISIBLE);
            }
        }
    }

    public void clean() {
        register_step1_account_name_tip_tv.setVisibility(View.GONE);
        register_step1_password_error.setVisibility(View.GONE);
        register_step1_password_tip_tv.setVisibility(View.GONE);
        bank_username_error.setVisibility(View.GONE);
        bank_accout_error.setVisibility(View.GONE);
        mail_error.setVisibility(View.GONE);
        phone_number_error.setVisibility(View.GONE);
        yanzhengma_error.setVisibility(View.GONE);
        register_account_name_cunzai.setVisibility(View.GONE);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_register;
    }


    public void onGetCheckUserMsg(NyBaseResponse<Object> obj) {
        if (obj.getMsg().equals("-2")) {
            register_account_name_cunzai.setVisibility(View.VISIBLE);
            register_step1_account_name_tip_tv.setVisibility(View.GONE);
        }
    }

    private BaseContentListPopWindow bankPop;
    List<ContentInfoBean> bankList;

    public void onGetBankStateList(final List<BankInfoBean> bankInfoBeanList) {

    }

    public void onGetAccountDetail(List<BankAccountDetailBean> beanList) {
        bankList = new ArrayList<>();
        for (int i = 0; i < beanList.size(); i++) {
            BankAccountDetailBean bean = beanList.get(i);
            String bankName = bean.getBank_name();
            String idModBank = bean.getId_mod_bank();
            bankList.add(new ContentInfoBean(bankName, idModBank));
        }
        bankPop = new BaseContentListPopWindow(mContext, choice_bank, choice_bank.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public List<ContentInfoBean> getContentData() {
                return bankList;
            }

            @Override
            public void onClickItem(int position, ContentInfoBean item) {
                strbankname = item.getContent();
                choice_bank.setText(strbankname);
                strbankid = item.getContentId();
            }
        };
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_regist:
                verification();
                break;
            case R.id.choice_bank: //选择银行
                if (bankPop != null) {
                    bankPop.showPopupDownWindow();
                }
                break;
            case R.id.tv_yanzhengma:
                stringyanzhengma = presenter.createCode();
                tv_yanzhengma.setText(stringyanzhengma);
                break;
        }
    }

    private void verification() {
        initViewContent();
        stregistername = register_name.getText().toString();
        strregisterpasswd = register_pwd.getText().toString();
        strcomfirpasswd = register_again_pwd.getText().toString();
        strusername = bank_username.getText().toString();
        straccount = bank_account.getText().toString();
        strperson = tuijian_person.getText().toString();
        strmail = mail.getText().toString();
        strnumber = phone_number.getText().toString();
        stryanzhengma = yanzhengma.getText().toString();
        String regex = ".*[a-zA-Z].*[0-9]|.*[0-9].*[a-zA-Z]"; //匹配字母和数字组合
        String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
        boolean numzimu = strregisterpasswd.matches(regex);
        boolean youxiang = strmail.matches(rex);
        if (stregistername.length() > 5 && stregistername.length() < 10) {    //账户
            if (strregisterpasswd.length() > 7 && strregisterpasswd.length() < 21 && numzimu == true) { //密码
                if (strcomfirpasswd.length() > 7 && strcomfirpasswd.length() < 21 && strcomfirpasswd.equals(strregisterpasswd)) { //再次输入密码
                    if (!isNeedBank || (strusername.length() > 1 && strusername.length() < 21)) {  //银行账户
                        if (!isNeedBank || straccount.length() > 7 && straccount.length() < 17) {
                            if (youxiang == true) { //电子邮箱
                                if (strnumber.length() > 5 && strnumber.length() < 21) { //电话号码
                                    //该写验证码了
                                    if (stryanzhengma.equals(stringyanzhengma)) {
                                        register();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, getString(R.string.sorryyancuowu), Toast.LENGTH_LONG).show();
                                    }

                                } else {
                                    Toast.makeText(RegisterActivity.this, getString(R.string.sorrtshucuo), Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, getString(R.string.sorryyouxcuo), Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Nomor rekening bank adalah 8 hingga 16 digit", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.sorrychancuo), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.sorrynopipei), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(RegisterActivity.this, getString(R.string.sorrypswcuo), Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(RegisterActivity.this, getString(R.string.sorrynamecuo), Toast.LENGTH_LONG).show();
        }

    }


    public void onGetRegisterData(NyBaseResponse<RegistInfo> obj) {
        if (obj.getMsg().equals("1")) {
            Toast.makeText(RegisterActivity.this, getString(R.string.Register_Success), Toast.LENGTH_LONG).show();
            LoginStatusBean loginStatusBean = new LoginStatusBean();
            RegistInfo data = obj.getData();
            loginStatusBean.setLoginStatus("-11");
            loginStatusBean.setUsername(data.getUsername());
            loginStatusBean.setPassword(data.getPassword());
            if (!TextUtils.isEmpty(isNeedSend)) {
                loginStatusBean.setLoginStatus("2");
            }
            getApp().setRegisterFirstIn(true);
            EventBus.getDefault().postSticky(loginStatusBean);
            finish();
        } else if (obj.getMsg().equals("-1")) {
            dismissBlockDialog();
            Toast.makeText(RegisterActivity.this, R.string.teshuzifu, Toast.LENGTH_LONG).show();
        } else if (obj.getMsg().equals("-2")) {
            dismissBlockDialog();
            Toast.makeText(RegisterActivity.this, R.string.zhanghunouse, Toast.LENGTH_LONG).show();
        } else if (obj.getMsg().equals("-3")) {
            dismissBlockDialog();
            Toast.makeText(RegisterActivity.this, R.string.xinxierror, Toast.LENGTH_LONG).show();
        } else if (obj.getMsg().equals("-4")) {
            dismissBlockDialog();
            Toast.makeText(RegisterActivity.this, R.string.userexist, Toast.LENGTH_LONG).show();
        } else if (obj.getMsg().equals("-5")) {
            dismissBlockDialog();
            Toast.makeText(RegisterActivity.this, R.string.banknoexist, Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    private void register() {
        HashMap<String, String> params = new HashMap<>();
        params.put("web_id", WebSiteUrl.WebId);
        params.put("useracc", stregistername);
        params.put("password", strregisterpasswd);
        params.put("email", strmail);
        params.put("captcha", stryanzhengma);
        params.put("tel", strnumber);
        if (getLocalLanguage().equals("in")) {
            params.put("bank", strbankid);  //银行id
            params.put("bankaccount", strusername);  //银行账户
            params.put("bankno", straccount); //银行账号
        }
        if (!getLocalLanguage().equals("vn")) {
            params.put("referrall_id", strperson); //推荐人
        }
        if (!TextUtils.isEmpty(affiliateId)) {
            params.put("affiliate_id", affiliateId);
        }
        params.put("currency", strbizhong); //币种
        presenter.register(params);
    }

}
