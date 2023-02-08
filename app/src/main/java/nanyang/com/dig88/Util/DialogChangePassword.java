package nanyang.com.dig88.Util;

import android.app.*;
import android.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2020/1/16.
 */

public class DialogChangePassword {
    EditText edt_old_pass;
    EditText edtNewPass;
    EditText edtConfirmNewPass;
    View view_line;
    Button btn_submit;
    BaseActivity activity;
    String newPass;
    String confirmNewPass;
    private Dialog dialog;
    public DialogChangePassword(BaseActivity context) {
        activity = context;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, gaming178.com.mylibrary.R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.activity_xiugai_passwd, null);
        edt_old_pass = v.findViewById(R.id.edt_old_pass);
        edtNewPass = v.findViewById(R.id.edt_new_pass);
        edtConfirmNewPass = v.findViewById(R.id.edt_old_pass);
        edt_old_pass = v.findViewById(R.id.edt_confirm_new_pass);
        view_line = v.findViewById(R.id.view_line);
        btn_submit = v.findViewById(R.id.btn_submit);
        edt_old_pass.setVisibility(View.GONE);
        view_line.setVisibility(View.GONE);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newPass = edtNewPass.getText().toString().trim();
                confirmNewPass = edtConfirmNewPass.getText().toString().trim();
                if (TextUtils.isEmpty(newPass)) {
                    com.unkonw.testapp.libs.utils.ToastUtils.showShort(activity.getString(R.string.newmimanull));
                    return;
                }
                if (TextUtils.isEmpty(confirmNewPass)) {
                    com.unkonw.testapp.libs.utils.ToastUtils.showShort(activity.getString(R.string.errornull));
                    return;
                }
                if (!confirmNewPass.equals(newPass)) {
                    com.unkonw.testapp.libs.utils.ToastUtils.showShort(activity.getString(R.string.mimaerror));
                    return;
                }
                String p = "web_id=" + WebSiteUrl.WebId + "&user_id=" + activity.getUserInfoBean().getUser_id() + "&session_id=" + activity.getUserInfoBean().getSession_id() +
                        "&OldPassword=123456" + "&NewPassword=" + newPass + "&ConfirmPassword=" + confirmNewPass;
                HttpUtils.httpPost(WebSiteUrl.ChangeUserPassword, p, new HttpUtils.RequestCallBack() {
                    @Override
                    public void onRequestSucceed(String data) {
                        NyBaseResponse<Object> orgData = new Gson().fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                        }.getType());
                        String returnMsg;
                        switch (orgData.getMsg()) {
                            case "1":
                                returnMsg = activity.getString(R.string.xiumisucess);
                                dialog.dismiss();
                                break;
                            case "-1":
                                returnMsg = activity.getString(R.string.mimaerrorx);
                                break;
                            case "-2":
                                returnMsg = activity.getString(R.string.oldmimaerror);
                                break;
                            case "-3":
                                returnMsg = activity.getString(R.string.twomimacuo);
                                break;
                            default:
                                returnMsg = activity.getString(R.string.mimaerrorx);
                                break;
                        }
                        com.unkonw.testapp.libs.utils.ToastUtils.showShort(returnMsg);
                    }

                    @Override
                    public void onRequestFailed(String s) {

                    }
                });
            }
        });
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }
}
