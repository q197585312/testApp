package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Fragment.Presenter.RechargeCardPresenter;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2015/12/21. (取款列表)
 */
public class RechargeCardFragment extends BaseFragment<RechargeCardPresenter> {
    @BindView(R.id.edt_recharge_card_num)
    EditText edtRechargeCardNum;
    @BindView(R.id.edt_recharge_card_passwrod)
    EditText edtRechargeCardPassword;
    private String rechargeCardNum;
    private String rechargeCardPassword;
    private String ip;


    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.fragment_recharge_card;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        createPresenter(new RechargeCardPresenter(this));
        getAct().setTitle(getString(R.string.recharge_card));
        getAct().setleftViewEnable(true);
        ip = SharePreferenceUtil.getString(mContext, "IP");
    }

    public void onGetResultMsg(String msg) {
        ToastUtils.showShort(msg);
    }

    public void clear() {
        edtRechargeCardNum.setText("");
        edtRechargeCardPassword.setText("");
    }

    @OnClick({R.id.btn_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                isCanSubmit();
                break;
        }
    }

    private void isCanSubmit() {
        rechargeCardNum = edtRechargeCardNum.getText().toString();
        rechargeCardPassword = edtRechargeCardPassword.getText().toString();
        if (TextUtils.isEmpty(rechargeCardNum)) {
            Toast.makeText(mContext, getString(R.string.recharge_card_num_error), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(rechargeCardPassword)) {
            Toast.makeText(mContext, getString(R.string.recharge_card_passwrod_error), Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", getUserInfo().getUser_id());
        p.put("session_id", getUserInfo().getSession_id());
        p.put("card_no", rechargeCardNum);
        p.put("card_pass", rechargeCardPassword);
        p.put("ip", ip);
        presenter.goRechargeCard(p);
    }
}
