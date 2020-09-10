package gaming178.com.casinogame.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLinearLayout;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.ChangePasswordHelper;
import gaming178.com.casinogame.Util.WebSiteUrl;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.ScreenUtil;

/**
 * Created by Administrator on 2018/7/16.
 */

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    TextView tv_username, tv_ok;
    EditText edt_old_passwrod, edt_new_passwrod, edt_confirm_passwrod;
    String oldPassword;
    String newPassword;
    String confirmPassword;

    @Override
    protected int getLayoutRes() {
        return R.layout.gd_activity_change_password;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        tv_username = (TextView) findViewById(R.id.gd__tv_username);
        tv_username.setText("User Name:" + mAppViewModel.getUser().getName());
        tv_ok = (TextView) findViewById(R.id.gd__tv_ok);
        edt_old_passwrod = (EditText) findViewById(R.id.gd__edt_old_passwrod);
        edt_new_passwrod = (EditText) findViewById(R.id.gd__edt_new_passwrod);
        edt_confirm_passwrod = (EditText) findViewById(R.id.gd__edt_confirm_passwrod);
        tv_ok.setOnClickListener(this);
        titleTv.setVisibility(View.VISIBLE);
        titleTv.setText(getString(R.string.katasandi));
        titleTv.post(new Runnable() {
            @Override
            public void run() {
                AutoLinearLayout.LayoutParams layoutParams = (AutoLinearLayout.LayoutParams) titleTv.getLayoutParams();
                layoutParams.rightMargin = ScreenUtil.dip2px(mContext, 80);
                titleTv.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.gd__tv_ok) {
            submit();
        }
    }

    private void submit() {
        oldPassword = edt_old_passwrod.getText().toString();
        newPassword = edt_new_passwrod.getText().toString();
        confirmPassword = edt_confirm_passwrod.getText().toString();
        ChangePasswordHelper changePasswordHelper = new ChangePasswordHelper(oldPassword, newPassword, confirmPassword, this);
        changePasswordHelper.changePassword();
    }
}
