package gaming178.com.casinogame.Util;

import android.text.TextUtils;
import android.widget.Toast;

import gaming178.com.casinogame.base.BaseActivity;

public class ChangePasswordHelper {
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;
    private BaseActivity baseActivity;
    private String msg;

    public ChangePasswordHelper(String oldPassword, String newPassword, String confirmPassword, BaseActivity baseActivity) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
        this.baseActivity = baseActivity;
    }

    public void changePassword() {
        if (TextUtils.isEmpty(oldPassword)) {
            Toast.makeText(baseActivity, "Please input old password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            Toast.makeText(baseActivity, "Please input new password!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (newPassword.length() < 6) {
                Toast.makeText(baseActivity, "New password is too short!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            Toast.makeText(baseActivity, "Please input confirm new password!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (confirmPassword.length() < 6) {
                Toast.makeText(baseActivity, "Confirm new password is too short!", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(baseActivity, "New password and Confirm new password is different!", Toast.LENGTH_SHORT).show();
            return;
        }
        baseActivity.showBlockDialog();
        new Thread() {
            @Override
            public void run() {
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "changepwd.jsp";
                String param = "oldpass=" + oldPassword + "&newpass1=" + newPassword + "&newpass2=" + confirmPassword;
                if (baseActivity.mAppViewModel.getHttpClient() == null || TextUtils.isEmpty(url) || TextUtils.isEmpty(param)) {
                    return;
                }
                String result = baseActivity.mAppViewModel.getHttpClient().sendPost(url, param);
                if (result.startsWith("Results=ok")) {
                    msg = "Updated sucessful.";
                } else {
                    msg = "Old password error.";
                }
                baseActivity.getHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(baseActivity, msg, Toast.LENGTH_SHORT).show();
                        baseActivity.dismissBlockDialog();
                    }
                });
            }
        }.start();
    }
}
