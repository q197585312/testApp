package nanyang.com.dig88.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;
import nanyang.com.dig88.Activity.presenter.Scr888ChangePasswordPresenter;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.Scr888ChangePasswordBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/9/3.
 */

public class Scr888ChangePasswordActivity extends BaseActivity<Scr888ChangePasswordPresenter> {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;
    @BindView(R.id.edt_confirm_new_pass)
    EditText edtConfirmNewPass;
    String newPass;
    String confirmNewPass;

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        setTitle(getString(R.string.xiumima));
        createPresenter(new Scr888ChangePasswordPresenter(this));
        Intent intent = getIntent();
        ContentInfoBean contentInfoBean = (ContentInfoBean) intent.getSerializableExtra("ContentInfoBean");
//        tvUsername.setText("918Kiss ID :" + getPw(contentInfoBean.getContent()));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_scr888_change_passwd;
    }

    public void onGetChangeResult(Scr888ChangePasswordBean scr888ChangePasswordBean) {
        String code = scr888ChangePasswordBean.getCode();
        if (code.equals("1")) {
            edtNewPass.setText("");
            edtConfirmNewPass.setText("");
        }
        String toastData = scr888ChangePasswordBean.getMsg();
        ToastUtils.showShort(toastData);
    }

    @OnClick({R.id.btn_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                newPass = edtNewPass.getText().toString().trim();
                confirmNewPass = edtConfirmNewPass.getText().toString().trim();
                if (TextUtils.isEmpty(newPass)) {
                    ToastUtils.showShort(getString(R.string.newmimanull));
                    return;
                }
                if (TextUtils.isEmpty(confirmNewPass)) {
                    ToastUtils.showShort(getString(R.string.errornull));
                    return;
                }
                if (!confirmNewPass.equals(newPass)) {
                    ToastUtils.showShort(getString(R.string.mimaerror));
                    return;
                }
                String param = "&web_id=" + WebSiteUrl.WebId + "&id_user=" + getUserInfoBean().getUser_id()
                        + "&session_id=" + getUserInfoBean().getSession_id() + "&new_pass=" + newPass;
                presenter.changPassword(param);
                break;
        }
    }

    private String getPw(String s) {
        int start = s.indexOf("(") + 1;
        s = s.substring(start, s.length() - 1);
        return s;
    }
}
