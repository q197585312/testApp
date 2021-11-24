package com.nanyang.app.load.login;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.Pop.PopChoiceLanguage;
import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.main.MainActivity;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BaseListPopupWindow;

import butterknife.BindView;
import butterknife.OnClick;
import cn.finalteam.toolsfinal.AppCacheUtils;


/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class LoginActivity extends BaseToolbarActivity<LoginPresenter> {


    @BindView(R.id.c_video_bg)
    CustomVideoView c_video_bg;
    @BindView(R.id.tv_all_right)
    TextView tv_all_right;
    @BindView(R.id.tv_remember_me)
    TextView tv_remember_me;
    @BindView(R.id.edt_login_username)
    EditText edtLoginUsername;
    @BindView(R.id.edt_login_password)
    EditText edtLoginPassword;
    @BindView(R.id.ll_bottom_btn)
    View llBottomBtn;
    @BindView(R.id.btn_login_login)
    TextView btnLoginLogin;
    @BindView(R.id.btn_desktop)
    TextView btn_desktop;
    //    @BindView(R.id.tv_login_forget)
//    TextView tvLoginForget;
    AfbApplication app;
    @BindView(R.id.cb_login_remember)
    CheckBox cbLoginRemember;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    @BindView(R.id.cb_login_password_eye)
    CheckBox cb_login_password_eye;
    @BindView(R.id.login_language)
    TextView loginLanguage;
    View line_name;
    View line_password;
    ImageView img_name;
    ImageView img_password;
    private PopChoiceLanguage popLanguage;

    private volatile int loginType = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createPresenter(new LoginPresenter(this));
        tv_all_right.setText(String.format(getString(R.string.copyright_2018_afb88_all_rights_reserved), DateUtils.getCurrentDate("yyyy"),
                BuildConfig.FLAVOR.toUpperCase() + " V" + BuildConfig.VERSION_NAME));
        edtLoginPassword.setOnKeyListener(onKeyListener);
        String password = AppCacheUtils.getInstance(this).getString("PASS_WORD") != null ? AppCacheUtils.getInstance(this).getString("PASS_WORD") : "";
        String userName = AppCacheUtils.getInstance(this).getString("USER_NAME") != null ? AppCacheUtils.getInstance(this).getString("USER_NAME") : "";
        edtLoginUsername.setText(userName);
        edtLoginPassword.setText(password);

        if (userName == null || userName.isEmpty()) {
            cbLoginRemember.setChecked(false);
        } else {
            cbLoginRemember.setChecked(true);
        }
        edtLoginUsername.requestFocus();
        cb_login_password_eye.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    edtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                edtLoginPassword.setSelection(edtLoginPassword.getText().length());
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        inputMove(llContainer, llBottomBtn);
        presenter.playVideoRaw(c_video_bg);
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            line_name = findViewById(R.id.line_name);
            line_password = findViewById(R.id.line_password);
            img_name = findViewById(R.id.img_name);
            img_password = findViewById(R.id.img_password);
            edtLoginUsername.setOnFocusChangeListener(new android.view.View.
                    OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        line_name.setBackgroundColor(ContextCompat.getColor(mContext, R.color.login_line_select_bg));
                        img_name.setBackgroundResource(R.mipmap.username_gold);
                    } else {
                        line_name.setBackgroundColor(ContextCompat.getColor(mContext, R.color.login_line_no_select_bg));
                        img_name.setBackgroundResource(R.mipmap.username_gary);
                    }
                }
            });
            edtLoginPassword.setOnFocusChangeListener(new android.view.View.
                    OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        line_password.setBackgroundColor(ContextCompat.getColor(mContext, R.color.login_line_select_bg));
                        img_password.setBackgroundResource(R.mipmap.password_gold);
                    } else {
                        line_password.setBackgroundColor(ContextCompat.getColor(mContext, R.color.login_line_no_select_bg));
                        img_password.setBackgroundResource(R.mipmap.password_gary);
                    }
                }
            });
        }
    }

    private void initAppNullData() {
        getApp().setBetAfbList(null);
        getApp().setListMainPictures(null);
        getApp().setListMainBanners(null);
    }

    @Override
    public void initLanguage() {
        super.initLanguage();
        String language = AfbUtils.getLanguage(this);
        if (TextUtils.isEmpty(language)) {
            AfbUtils.switchLanguage("zh", this);
            LanguageHelper helper = new LanguageHelper(mContext);
            MenuItemInfo<String> languageItem = helper.getLanguageItem();
            loginLanguage.setText(languageItem.getText());
        }
        restart();
    }

    @Override
    public void init() {
        initFromWhere();
        initView();
        initData();
        initLanguage();
    }

    public void onFailed(String error) {
        if (error != null && error.equals(getString(R.string.System_maintenance))) {
            ToastUtils.showMyToast(error);
        } else {
            ToastUtils.showShort(error);
        }
    }


    public void promptMsg(int msgRes) {
        ToastUtils.showShort(msgRes);
    }

    @OnClick({R.id.btn_login_login, R.id.login_language})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                loginType = 0;
                login();
                break;
            case R.id.login_language:
                BaseListPopupWindow<MenuItemInfo<String>> popWindow = new BaseListPopupWindow<MenuItemInfo<String>>(mContext, view, view.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT, (TextView) view) {

                    @Override
                    public void onConvert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                        TextView tv = holder.getView(com.unkonw.testapp.R.id.item_regist_text_tv);
                        tv.setAllCaps(true);
                        tv.setText(item.getText());
                        tv.setGravity(Gravity.LEFT);
                    }

                    @Override
                    protected void clickItem(TextView tv, MenuItemInfo<String> item) {
                        super.clickItem(tv, item);
                        closePopupWindow();
                        tv.setText(item.getText());
                        AfbUtils.switchLanguage(item.getType(), mContext);
                        restart();
                    }
                };
//                popWindow.setData(presenter.currencyList);
                popWindow.setTrans(1f);
                popWindow.setData(new LanguageHelper(mContext).getLanguageItems());
                popWindow.showPopupDownWindow();
                break;
        }
    }

    public void loginView2(View v) {
        loginType = 1;
        login();
    }

    protected void restart() {
        super.restart();
        edtLoginUsername.setHint(getString(R.string.Account));
        edtLoginPassword.setHint(getString(R.string.Password));
        if (BuildConfig.FLAVOR.equals("ez2888")) {
            btnLoginLogin.setText(getString(R.string.Login));
            btn_desktop.setText(getString(R.string.desktop));
        } else {
            btnLoginLogin.setText(getString(R.string.view1));
            btn_desktop.setText(getString(R.string.view2));
        }
        tv_remember_me.setText(getString(R.string.remember_me));
        loginLanguage.setText(getString(R.string.language_switch));

    }

    private View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                loginType = 0;
                login();
                return true;
            }
            return false;
        }
    };

    private void login() {
        initAppNullData();
        String us = edtLoginUsername.getText().toString();
        String k = edtLoginPassword.getText().toString();//"a7c7366ecd6041489d08ecb9ac1f39c9"
        if (!TextUtils.isEmpty(us) && !TextUtils.isEmpty(k)) {
            presenter.login(new LoginInfo(us, k));
        } else {
            ToastUtils.showShort(getString(R.string.enter_username_or_password));
        }
    }

    public void onLanguageSwitchSucceed(String str) {
        ToastUtils.showShort(R.string.Login_Success);
        app = (AfbApplication) getApplication();
        String username = edtLoginUsername.getText().toString();
        String password = edtLoginPassword.getText().toString();
        app.getUser().setLoginName(username);
        app.getUser().setPassword(password);
        if (cbLoginRemember.isChecked()) {
            AppCacheUtils.getInstance(this).put("PASS_WORD", password);
            AppCacheUtils.getInstance(this).put("USER_NAME", username);
        } else {
            AppCacheUtils.getInstance(this).put("PASS_WORD", "");
            AppCacheUtils.getInstance(this).put("USER_NAME", "");
        }

        AppConstant.IS_AGENT = false;
        AppConstant.wfMain = "wfMainH50";
        if (loginType == 0) {
            Log.d("doRetrofitApiOnUiThread", ": " + AppConstant.wfMain);
            skipAct(MainActivity.class);
            finish();
        } else {
            Log.d("doRetrofitApiOnUiThread", ": " + AppConstant.wfMain);
            Bundle bundle = new Bundle();
            bundle.putInt(AppConstant.KEY_INT, loginType);
            skipAct(MainActivity.class, bundle);
            finish();

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        c_video_bg.start();
        edtLoginUsername.setCursorVisible(true);// 再次点击显示光标
        edtLoginUsername.setFocusable(true);
        edtLoginUsername.setFocusableInTouchMode(true);
        edtLoginUsername.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) edtLoginUsername.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.showSoftInput(edtLoginUsername, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        c_video_bg.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();


    }


    @Override
    public void startUpdateState() {
//        super.startUpdateState();
    }
}
