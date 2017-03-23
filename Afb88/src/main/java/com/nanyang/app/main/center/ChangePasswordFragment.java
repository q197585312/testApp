package com.nanyang.app.main.center;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.ApiService;
import com.nanyang.app.R;
import com.nanyang.app.main.center.model.ChangePasswordBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import butterknife.Bind;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/3/21.
 */

public class ChangePasswordFragment extends BaseFragment {
    @Bind(R.id.tv_account_name)
    TextView tv_accName;
    @Bind(R.id.et_old_password)
    EditText et_oldPassword;
    @Bind(R.id.et_new_password)
    EditText et_newPassword;
    @Bind(R.id.et_sure_password)
    EditText et_surePasswrod;
    @Bind(R.id.tv_submit)
    TextView tv_submit;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_change_password;
    }

    @Override
    public void initView() {
        super.initView();
        AfbApplication app = (AfbApplication) getActivity().getApplication();
        tv_accName.setText(app.getUserName());


    }

    @Override
    public void initData() {
        super.initData();
    }

    @OnClick({R.id.tv_submit})

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                changePassword();
                break;
        }
    }

    private void changePassword() {
        String oldPasswrod = et_oldPassword.getText().toString();
        String newPasswrod = et_newPassword.getText().toString();
        String surePasswrod = et_surePasswrod.getText().toString();
        ChangePasswordBean bean = new ChangePasswordBean(oldPasswrod, newPasswrod, surePasswrod);
        Disposable d = Api.getService(ApiService.class).changePasswrod(bean.getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        hideLoadingDialog();
                        ToastUtils.showShort(isPasswordChangeSucceed(s));
                        et_newPassword.setText("");
                        et_oldPassword.setText("");
                        et_surePasswrod.setText("");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        hideLoadingDialog();
                        ToastUtils.showShort(throwable.toString());
                        et_newPassword.setText("");
                        et_oldPassword.setText("");
                        et_surePasswrod.setText("");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(d);
    }

    private String isPasswordChangeSucceed(String password) {
        String sign = "<span id=\"lblStatus\" class=\"";
        int firstIndex = password.indexOf(sign) + sign.length();
        int lastIndex = -1;
        for (int i = firstIndex; i < password.length(); i++) {
            String first = password.charAt(i) + "";
            String next = password.charAt(i + 1) + "";
            if (first.equals("<") && next.equals("/")) {
                lastIndex = i;
                break;
            }
        }
        String msg = password.substring(firstIndex, lastIndex);
        int trueIndex = -1;
        for (int i = 0; i < msg.length(); i++) {
            String first = msg.charAt(i) + "";
            String next = msg.charAt(i + 1) + "";
            if (first.equals("\"") && next.equals(">")) {
                trueIndex = i + 2;
                break;
            }
        }
        String trueMsg = msg.substring(trueIndex);
        return trueMsg;
    }
}
