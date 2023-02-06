package nanyang.com.dig88.Util;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/3/26.
 */

public class PopPtchangePassword extends BasePopupWindow {
    BaseActivity activity;
    TextView tvTitle, tvPtAccount;
    EditText edtPassword;
    Button btnSubmit;
    String ptChangePasswordUrl = "http://ptapi.khmergaming.com/api/password.php";
    String password;
    VipInfoBean info;
    public PopPtchangePassword(Context context, View v, int width, int height, final OkhttpUtils.Result result) {
        super(context, v, width, height);
        activity = (BaseActivity) context;
        initUi();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = edtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(activity, activity.getString(R.string.password_cannot_null), Toast.LENGTH_SHORT).show();
                    return;
                }
                ptChangePasswordUrl += "?web_id=" + WebSiteUrl.WebId + "&session_id=" + activity.getUserInfoBean().getSession_id()
                        + "&id_mod_member=" + activity.getUserInfoBean().getUser_id() +
                        "&password=" + password + "&username=" + info.getUsername();
                activity.showBlockDialog();
                OkhttpUtils.getRequest(ptChangePasswordUrl, result);
            }
        });
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_pt_change_password;
    }

    private void initUi() {
        info = (VipInfoBean) AppTool.getObjectData(activity, "vipInfo");
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvPtAccount = (TextView) view.findViewById(R.id.tv_pt_account);
        edtPassword = (EditText) view.findViewById(R.id.edt_content);
        btnSubmit = (Button) view.findViewById(R.id.btn_submit);
        tvTitle.setText(activity.getString(R.string.xiumima) + "(PT Slots)");
        tvPtAccount.setText(WebSiteUrl.WebId + "s" + info.getUsername());
    }

    public void clearContent() {
        edtPassword.setText("");
    }
}
