package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.Presenter.ChangPasswordPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21.
 */
public class ChangePasswordFragment extends BaseFragment<ChangPasswordPresenter> {
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.edt_old_pass)
    EditText edtOldPass;
    @BindView(R.id.edt_new_pass)
    EditText edtNewPass;
    @BindView(R.id.edt_confirm_new_pass)
    EditText edtConfirmNewPass;
    String oldPass;
    String newPass;
    String confirmNewPass;

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_xiugai_passwd;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        getAct().setleftViewEnable(true);
        getAct().setTitle(getString(R.string.xiumima));
        createPresenter(new ChangPasswordPresenter(this));
        VipInfoBean info = (VipInfoBean) AppTool.getObjectData(mContext, "vipInfo");
        tvUsername.setText(info.getUsername());
    }

    public void onGetChangeResult(String msg) {
        ToastUtils.showShort(msg);
    }

    public void clearContent() {
        edtConfirmNewPass.setText("");
        edtNewPass.setText("");
        edtOldPass.setText("");
    }

    @OnClick({R.id.btn_submit})
    public void OnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                verification();
                break;
        }
    }

    private void verification() {
        oldPass = edtOldPass.getText().toString().trim();
        newPass = edtNewPass.getText().toString().trim();
        confirmNewPass = edtConfirmNewPass.getText().toString().trim();
        if (TextUtils.isEmpty(oldPass)) {
            ToastUtils.showShort(getString(R.string.oldmimanull));
            return;
        }
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
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("OldPassword", oldPass);
        p.put("NewPassword", newPass);
        p.put("ConfirmPassword", confirmNewPass);
        presenter.changPassword(p);
    }
}
