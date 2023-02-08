package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Activity.presenter.ForgotPasswordPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/11/5.
 */

public class ForgotPasswordActivity extends BaseActivity<ForgotPasswordPresenter> {

    @BindView(R.id.edt_username)
    EditText edtUsername;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    String username;
    String email;
    String code;
    private String ip;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forgot_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new ForgotPasswordPresenter(this));
        setleftViewEnable(true);
        setTitle(getString(R.string.Forgot_Password));
        tvCode.setText(presenter.createCode());
        ip = SharePreferenceUtil.getString(mContext, "IP");
    }

    private void submit() {
        username = edtUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            ToastUtils.showShort(getString(R.string.username_cannot_null));
            return;
        }
        email = edtEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            ToastUtils.showShort(getString(R.string.sorryyouxcuo));
            return;
        } else {
            String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
            boolean matches = email.matches(rex);
            if (!matches) {
                ToastUtils.showShort(getString(R.string.sorryyouxcuo));
                return;
            }
        }
        code = edtCode.getText().toString();
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort(getString(R.string.sorryyancuowu));
            return;
        } else {
            if (!code.equals(tvCode.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.sorryyancuowu));
                return;
            }
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("domain", "app");
        p.put("useracc", username);
        p.put("email", email);
        p.put("captcha", code);
        p.put("method", "reset_pw");
        p.put("IP", ip);
        presenter.request(p);
    }

    @OnClick({R.id.btn_submit, R.id.tv_code})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_code:
                tvCode.setText(presenter.createCode());
                break;
            case R.id.btn_submit:
                submit();
                break;
        }
    }
}
