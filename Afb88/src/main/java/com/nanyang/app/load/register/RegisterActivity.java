package com.nanyang.app.load.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseToolbarActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/2/5 0005.
 */
public class RegisterActivity extends BaseToolbarActivity<RegisterPresenter> implements RegisterContract.View {


    @Bind(R.id.edt_account)
    EditText edtAccount;
    @Bind(R.id.tv_account_prompt)
    TextView tvAccountPrompt;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.tv_password_prompt)
    TextView tvPasswordPrompt;
    @Bind(R.id.edt_password_repeat)
    EditText edtPasswordRepeat;
    @Bind(R.id.tv_password_repeat_prompt)
    TextView tvPasswordRepeatPrompt;
    @Bind(R.id.edt_phone)
    EditText edtPhone;
    @Bind(R.id.tv_phone_prompt)
    TextView tvPhonePrompt;
    @Bind(R.id.edt_email)
    EditText edtEmail;
    @Bind(R.id.tv_email_prompt)
    TextView tvEmailPrompt;
    @Bind(R.id.btn_submit)
    Button btnSubmit;

    @Override
    public void onFailed(String error) {

    }

    @Override
    public void onGetData(String data) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        createPresenter(new RegisterPresenter(this));
    }

    @OnClick(R.id.btn_submit)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
               /* UserInfo userInfo=new UserInfo()
                presenter.register();*/
                break;
        }

    }
}
