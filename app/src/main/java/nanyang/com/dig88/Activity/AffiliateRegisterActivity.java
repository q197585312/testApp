package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import nanyang.com.dig88.Activity.presenter.AffiliateRegisterPresenter;
import nanyang.com.dig88.Entity.AffiliateDataBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.BaseListPopWindow;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2019/9/29.
 */

public class AffiliateRegisterActivity extends BaseActivity<AffiliateRegisterPresenter> {
    @BindView(R.id.tv_name1)
    TextView tvName1;
    @BindView(R.id.tv_name2)
    TextView tvName2;
    @BindView(R.id.tv_name3)
    TextView tvName3;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.edt_password_confirm)
    EditText edtPasswordConfirm;
    @BindView(R.id.tv_choice)
    TextView tvChoice;
    @BindView(R.id.tv_currency)
    TextView tvCurrency;
    @BindView(R.id.tv_http)
    TextView tvHttp;
    @BindView(R.id.tv_domain)
    TextView tvDomain;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_tel)
    EditText edtTel;
    @BindView(R.id.edt_code)
    EditText edtCode;
    @BindView(R.id.tv_code)
    TextView tvCode;
    String domain;
    String choice;
    String email;
    String tel;
    String name;
    String password;
    String confirmPassword;
    String code;
    private List<ContentInfoBean> selectList;
    private List<ContentInfoBean> domainList;
    private String ip;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_affiliate_register;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        setleftViewEnable(true);
        setTitle(getString(R.string.Affiliate_Signup));
        ip = SharePreferenceUtil.getString(mContext, "IP");
        createPresenter(new AffiliateRegisterPresenter(this));
        tvCode.setText(presenter.createCode());
        presenter.getInitData();
    }

    public void onGetInitData(AffiliateDataBean affiliateDataBean) {
        selectList = presenter.getSelectList(affiliateDataBean);
        domainList = presenter.getDomainList(affiliateDataBean);
    }

    @OnClick({R.id.btn_submit, R.id.tv_choice, R.id.tv_domain, R.id.tv_currency, R.id.tv_name1, R.id.tv_name2, R.id.tv_name3})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();
                break;
            case R.id.tv_choice:
                if (selectList == null || selectList.size() == 0) {
                    return;
                }
                BaseContentListPopWindow popSelect = new BaseContentListPopWindow(mContext, tvChoice, tvChoice.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return selectList;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tvChoice.setText(item.getContent());
                        choice = item.getContentId();
                    }
                };
                popSelect.showPopupDownWindow();
                break;
            case R.id.tv_domain:
                if (domainList == null || domainList.size() == 0) {
                    return;
                }
                BaseContentListPopWindow popDomain = new BaseContentListPopWindow(mContext, tvDomain, tvDomain.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return domainList;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tvDomain.setText(item.getContent());
                        domain = item.getContentId();
                    }
                };
                popDomain.showPopupDownWindow();
                break;
            case R.id.tv_currency:
                BaseListPopWindow popCurrency = new BaseListPopWindow(mContext, tvCurrency, tvCurrency.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<String> getContentData() {
                        return Arrays.asList("USD");
                    }

                    @Override
                    public void onClickItem(int position, String item) {
                        tvCurrency.setText(item);
                    }
                };
                popCurrency.showPopupDownWindow();
                break;
            case R.id.tv_name1:
                showNamePop(tvName1);
                break;
            case R.id.tv_name2:
                showNamePop(tvName2);
                break;
            case R.id.tv_name3:
                showNamePop(tvName3);
                break;
        }
    }

    private void showNamePop(final TextView tv) {
        BaseListPopWindow pop = new BaseListPopWindow(mContext, tv, tv.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
            @Override
            public List<String> getContentData() {
                return Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z");
            }

            @Override
            public void onClickItem(int position, String item) {
                tv.setText(item);
            }
        };
        pop.showPopupDownWindow();
    }

    private void submit() {
        name = tvName1.getText().toString() + tvName2.getText().toString() + tvName3.getText().toString();
        password = edtPassword.getText().toString();
        confirmPassword = edtPasswordConfirm.getText().toString();
        email = edtEmail.getText().toString();
        tel = edtTel.getText().toString();
        code = edtCode.getText().toString();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShort(getString(R.string.enter_password));
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            ToastUtils.showShort(getString(R.string.enter_password));
            return;
        }
        if (!password.equals(confirmPassword)) {
            ToastUtils.showShort(getString(R.string.sorrynopipei));
            return;
        }
        if (TextUtils.isEmpty(choice)) {
            ToastUtils.showShort(getString(R.string.zhucexuan));
            return;
        }
        if (tvCurrency.getText().toString().equals(getString(R.string.zhucebibie))) {
            ToastUtils.showShort(getString(R.string.select_currency));
            return;
        }
        if (TextUtils.isEmpty(domain)) {
            ToastUtils.showShort(getString(R.string.select_domain));
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtils.showShort(getString(R.string.sorryyouxcuo));
            return;
        } else {
            String rex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";  //匹配电子邮箱
            boolean youxiang = email.matches(rex);
            if (!youxiang) {
                ToastUtils.showShort(getString(R.string.sorryyouxcuo));
                return;
            }
        }
        if (TextUtils.isEmpty(tel)) {
            ToastUtils.showShort(getString(R.string.enter_tel));
            return;
        }
        if (TextUtils.isEmpty(code)) {
            ToastUtils.showShort(getString(R.string.sorryyancuowu));
            return;
        } else {
            if (!code.equals(tvCode.getText().toString().trim())) {
                ToastUtils.showShort(getString(R.string.sorryyancuowu));
                return;
            }
        }
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("username", name);
        p.put("password", password);
        p.put("email", email);
        p.put("code", "1234");
        p.put("tel", tel);
        p.put("currency", "1");
        p.put("type", choice);
        p.put("domain", domain);
        p.put("ip", ip);
        presenter.register(p);
    }

}
