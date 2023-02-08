package nanyang.com.dig88.Login;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.ForgotPasswordActivity;
import nanyang.com.dig88.Activity.RegisterActivity;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/10/19.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {
    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.tv_version_number)
    TextView tvVersionNumber;
    @BindView(R.id.tv_ibet567_version_number)
    TextView tvIbet567VersionNumber;
    @BindView(R.id.rl_facebook)
    RelativeLayout rlFacebook;
    @BindView(R.id.tv_home_register)
    TextView tvHomeRegister;
    @BindView(R.id.rl_input)
    RelativeLayout rlInput;
    @BindView(R.id.img_logo)
    ImageView imgLogo;
    @BindView(R.id.ll_edt_content)
    LinearLayout llEdtContent;
    @BindView(R.id.ll_code)
    LinearLayout llCode;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    @BindView(R.id.tv_affiliate_name)
    TextView tvAffiliateName;
    @BindView(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    private String userName;
    private String password;
    private String code;
    private PackageInfo packageInfo;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setleftViewEnable(true);
        EventBus.getDefault().register(this);
        createPresenter(new LoginPresenter(this));
        presenter.getAffiliateName(tvAffiliateName);
        LoginInfoBean loginInfoBean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        if (loginInfoBean != null) {
            edtPassword.setText(loginInfoBean.getPassword());
            edtUsername.setText(loginInfoBean.getUsername());
        }
        packageInfo = AppTool.getApkInfo(mContext);
        tvVersionNumber.setText(getString(R.string.version) + ":" + packageInfo.versionName);
        tvIbet567VersionNumber.setText("V: " + packageInfo.versionName);
        LoginStatusBean loginStatusBean = (LoginStatusBean) getIntent().getSerializableExtra("LoginStatusBean");
        if (loginStatusBean != null && !TextUtils.isEmpty(loginStatusBean.getUsername())) {
            presenter.login(new LoginInfoBean(loginStatusBean.getUsername(), loginStatusBean.getPassword(), WebSiteUrl.WebId));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void onLoginSuccess() {
        ToastUtils.showShort(getString(R.string.Login_Success));
        EventBus.getDefault().post(new LoginStatusBean("1"));
        finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvenBusMessage(LoginStatusBean loginStatusBean) {
        if (loginStatusBean.getLoginStatus().equals("-11")) {
            presenter.login(new LoginInfoBean(loginStatusBean.getUsername(), loginStatusBean.getPassword(), WebSiteUrl.WebId));
        }
    }

    @OnClick({R.id.tv_login, R.id.tv_home_register, R.id.tv_code, R.id.rl_facebook, R.id.tv_forgot_password})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                if (!isLoginInfoNull()) {
                    presenter.login(new LoginInfoBean(userName, password, WebSiteUrl.WebId));
                }
                break;
            case R.id.tv_home_register:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
            case R.id.tv_code:
                tvCode.setText(presenter.createCode());
                break;
            case R.id.rl_facebook:
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;
        }
    }


    protected boolean isLoginInfoNull() {
        userName = edtUsername.getText().toString().trim();
        password = edtPassword.getText().toString().trim();
        code = edtCode.getText().toString().trim();
        if (StringUtils.isNull(mContext, userName, mContext.getString(R.string.username_cannot_null))) {
            return true;
        }
        if (StringUtils.isNull(mContext, password, mContext.getString(R.string.password_cannot_null))) {
            return true;
        }
        return false;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
