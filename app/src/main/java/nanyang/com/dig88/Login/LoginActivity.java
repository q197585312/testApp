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

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


import butterknife.Bind;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Activity.ForgotPasswordActivity;
import nanyang.com.dig88.Activity.RegisterActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Home.Bean.LoginStatusBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;

/**
 * Created by Administrator on 2015/10/19.
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {
    @Bind(R.id.edt_username)
    EditText edtUsername;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.tv_version_number)
    TextView tvVersionNumber;
    @Bind(R.id.tv_ibet567_version_number)
    TextView tvIbet567VersionNumber;
    @Bind(R.id.rl_facebook)
    RelativeLayout rlFacebook;
    @Bind(R.id.tv_home_register)
    TextView tvHomeRegister;
    @Bind(R.id.rl_input)
    RelativeLayout rlInput;
    @Bind(R.id.img_logo)
    ImageView imgLogo;
    @Bind(R.id.ll_edt_content)
    LinearLayout llEdtContent;
    @Bind(R.id.ll_code)
    LinearLayout llCode;
    @Bind(R.id.edt_code)
    EditText edtCode;
    @Bind(R.id.tv_code)
    TextView tvCode;
    @Bind(R.id.tv_affiliate_name)
    TextView tvAffiliateName;
    @Bind(R.id.tv_forgot_password)
    TextView tvForgotPassword;
    CallbackManager callbackManager;
    private String userName;
    private String password;
    private String code;
    private PackageInfo packageInfo;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setleftViewEnable(true);
        EventBus.getDefault().register(this);
        callbackManager = CallbackManager.Factory.create();
        createPresenter(new LoginPresenter(this));
        presenter.getAffiliateName(tvAffiliateName);
        if (BuildConfig.FLAVOR.equals("fun168")) {
            imgLogo.setVisibility(View.INVISIBLE);
        }
        if (BuildConfig.FLAVOR.equals("va2888") || BuildConfig.FLAVOR.equals("lemacau999") || BuildConfig.FLAVOR.equals("my2bet")) {
            tvHomeRegister.setVisibility(View.GONE);
        }
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            tvVersionNumber.setVisibility(View.GONE);
            tvIbet567VersionNumber.setVisibility(View.VISIBLE);
            rlFacebook.setVisibility(View.VISIBLE);
            Intent intent = getIntent();
            String isFacebookLogin = intent.getStringExtra("isFacebookLogin");
            if (!TextUtils.isEmpty(isFacebookLogin)) {
                facebookLogin();
            }
        }
        if (BuildConfig.FLAVOR.equals("asap888")) {
            tvForgotPassword.setText(getString(R.string.Forgot_Password) + "?");
            tvForgotPassword.setVisibility(View.VISIBLE);
        }
        LoginInfoBean loginInfoBean = (LoginInfoBean) AppTool.getObjectData(mContext, "loginInfo");
        if (loginInfoBean != null) {
            edtPassword.setText(loginInfoBean.getPassword());
            edtUsername.setText(loginInfoBean.getUsername());
        }
        packageInfo = AppTool.getApkInfo(mContext);
        tvVersionNumber.setText(getString(R.string.version) + ":" + packageInfo.versionName);
        tvIbet567VersionNumber.setText("V: " + packageInfo.versionName);
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                presenter.facebookLogin(loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                ToastUtils.showShort("Login Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                ToastUtils.showShort("Login Error");
            }
        });
        LoginStatusBean loginStatusBean = (LoginStatusBean) getIntent().getSerializableExtra("LoginStatusBean");
        if (loginStatusBean != null && !TextUtils.isEmpty(loginStatusBean.getUsername())) {
            presenter.login(new LoginInfoBean(loginStatusBean.getUsername(), loginStatusBean.getPassword(), WebSiteUrl.WebId));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BuildConfig.FLAVOR.equals("kbet3") || BuildConfig.FLAVOR.equals("ibet567") || BuildConfig.FLAVOR.equals("jf58") || BuildConfig.FLAVOR.equals("kimsa1")) {
            LinearLayout.LayoutParams rlParams = (LinearLayout.LayoutParams) rlInput.getLayoutParams();
            RelativeLayout.LayoutParams llEdtContentParams = (RelativeLayout.LayoutParams) llEdtContent.getLayoutParams();
            rlParams.height = UIUtil.dip2px(mContext, 273);
            llEdtContentParams.height = UIUtil.dip2px(mContext, 228);
            rlInput.setLayoutParams(rlParams);
            llEdtContent.setLayoutParams(llEdtContentParams);
            llCode.setVisibility(View.VISIBLE);
            tvCode.setText(presenter.createCode());
        }
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
                facebookLogin();
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(mContext, ForgotPasswordActivity.class));
                break;
        }
    }

    public void facebookLogin() {
        LoginManager.getInstance().logOut();
        LoginManager.getInstance().logInWithPublishPermissions(LoginActivity.this, null);
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
        if (BuildConfig.FLAVOR.equals("kbet3") || BuildConfig.FLAVOR.equals("ibet567") || BuildConfig.FLAVOR.equals("jf58") || BuildConfig.FLAVOR.equals("kimsa1")) {
            if (TextUtils.isEmpty(code)) {
                ToastUtils.showShort(getString(R.string.sorryyancuowu));
                return true;
            } else {
                if (!code.equals(tvCode.getText().toString().trim())) {
                    ToastUtils.showShort(getString(R.string.sorryyancuowu));
                    return true;
                }
            }
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
