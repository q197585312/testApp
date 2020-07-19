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
        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(mContext, "Please input old password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(mContext, "Please input new password!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (newPassword.length() < 6) {
                Toast.makeText(mContext, "New password is too short!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(mContext, "Please input confirm new password!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (confirmPassword.length() < 6) {
                Toast.makeText(mContext, "Confirm new password is too short!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(mContext, "New password and Confirm new password is different!", Toast.LENGTH_SHORT).show();
            return;
        }
        showBlockDialog();
        new Thread() {
            @Override
            public void run() {
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "changepwd.jsp";
                String param = "oldpass=" + oldPassword + "&newpass1=" + newPassword + "&newpass2=" + confirmPassword;
                if (mAppViewModel.getHttpClient() == null || TextUtils.isEmpty(url) || TextUtils.isEmpty(param)) {
                    return;
                }
                String result = mAppViewModel.getHttpClient().sendPost(url, param);
                if (result.startsWith("Results=error")) {
                    handler.sendEmptyMessage(0);
                } else if (result.startsWith("Results=ok")) {
                    handler.sendEmptyMessage(1);
                } else {
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dismissBlockDialog();
            if (msg.what == 0) {
                Toast.makeText(mContext, "Old password error.", Toast.LENGTH_SHORT).show();
            } else if (msg.what == 1) {
                Toast.makeText(mContext, "Updated sucessful.", Toast.LENGTH_SHORT).show();
            }
        }
    };
}
