package nanyang.com.dig88.Login;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.SharePreferenceUtil;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.AffNameBean;
import nanyang.com.dig88.Entity.LoginInfoBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/6/20.
 */

public class LoginPresenter extends BaseRetrofitPresenter<LoginActivity> {
    private static final char[] CHARS = {'0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9',
    };
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    LoginActivity loginActivity;
    LoginInfoBean loginBean;
    private Random random = new Random();

    public LoginPresenter(LoginActivity iBaseContext) {
        super(iBaseContext);
        loginActivity = iBaseContext;
    }

    public void login(LoginInfoBean loginInfoBean) {
        loginBean = loginInfoBean;
        Map<String, String> p = new HashMap<>();
        p.put("web_id", loginInfoBean.getWebId());
        p.put("username", loginInfoBean.getUsername());
        p.put("password", loginInfoBean.getPassword());
        String ip = SharePreferenceUtil.getString(baseContext, "IP");
        if (ip != null && ip.length() > 1) {
            p.put("IP", ip + "-android/app");
        }
        String lang = AppTool.getAppLanguage(baseContext);
        if (!TextUtils.isEmpty(lang)) {
            if (lang.equals("zh")) {
                loginInfoBean.setLang("ZH-CN");
            } else {
                loginInfoBean.setLang("EN-US");
            }
        } else {
            loginInfoBean.setLang("EN-US");
        }
        p.put("lang", loginInfoBean.getLang());
        p.put("from", "Android");
        Disposable subscription = getService(ApiService.class).doPostMap(WebSiteUrl.Dig88Login, p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        NyBaseResponse<UserInfoBean> dataBean = gson.fromJson(s, new TypeToken<NyBaseResponse<UserInfoBean>>() {
                        }.getType());
                        String code = dataBean.getCode().trim();
                        String msg = dataBean.getMsg().trim();
                        if (code.equals("1")) {
                            UserInfoBean userInfoBean = dataBean.getData();
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            userInfoBean.setLogin_time(sdf.format(d));
                            userInfoBean.setSet(dataBean.getSet());
                            ((BaseActivity) baseContext).setUserInfoBean(userInfoBean);
                            AppTool.saveObjectData(((BaseActivity) baseContext).getApplicationContext(), "loginInfo", loginBean);
                            Map<String, String> param = new HashMap<>();
                            param.put("web_id", WebSiteUrl.WebId);
                            param.put("user_id", userInfoBean.getUser_id());
                            param.put("session_id", userInfoBean.getSession_id());
                            loginActivity.onLoginSuccess();
                        } else if (msg.equals("-2")) {
                            String errorStr = baseContext.getBaseActivity().getString(R.string.username_error) + " " + loginActivity.getString(R.string.or) + " " + baseContext.getBaseActivity().getString(R.string.password_error);
                            Exception exception = new Exception(errorStr);
                            throw exception;
                        } else if (msg.equals("-4")) {
                            String errorStr = baseContext.getBaseActivity().getString(R.string.later_1_minute);
                            Exception exception = new Exception(errorStr);
                            throw exception;
                        } else {
                            Exception exception = new Exception(loginActivity.getString(R.string.Login_Failed));
                            throw exception;
                        }
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                        ToastUtils.showShort(throwable.getMessage());
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    public void facebookLogin(String token) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("accessToken", token);
        String ip = SharePreferenceUtil.getString(baseContext, "IP");
        if (ip != null && ip.length() > 1) {
            p.put("IP", ip + "-android/app");
        }
        Disposable subscription = getService(ApiService.class).doPostMap(WebSiteUrl.Dig88FacebookLogin, p)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String s) throws Exception {
                        NyBaseResponse<UserInfoBean> dataBean = gson.fromJson(s, new TypeToken<NyBaseResponse<UserInfoBean>>() {
                        }.getType());
                        String code = dataBean.getCode().trim();
                        if (code.equals("1")) {
                            UserInfoBean userInfoBean = dataBean.getData();
                            Date d = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            userInfoBean.setLogin_time(sdf.format(d));
                            ((BaseActivity) baseContext).setUserInfoBean(userInfoBean);
                            LoginInfoBean loginInfoBean = new LoginInfoBean(userInfoBean.getUsername(), "", "");
                            AppTool.saveObjectData(((BaseActivity) baseContext).getApplicationContext(), "loginInfo", loginInfoBean);
                            Map<String, String> param = new HashMap<>();
                            param.put("web_id", WebSiteUrl.WebId);
                            param.put("user_id", userInfoBean.getUser_id());
                            param.put("session_id", userInfoBean.getSession_id());
                            loginActivity.onLoginSuccess();
                        } else if (code.equals("-2")) {
                            String errorStr = baseContext.getBaseActivity().getString(R.string.username_error) + " " + loginActivity.getString(R.string.or) + " " + baseContext.getBaseActivity().getString(R.string.password_error);
                            Exception exception = new Exception(errorStr);
                            throw exception;
                        } else {
                            Exception exception = new Exception(loginActivity.getString(R.string.Login_Failed));
                            throw exception;
                        }
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.hideLoadingDialog();
                        ToastUtils.showShort(throwable.getMessage());
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Dig88FacebookLogin, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {

            }
        });
    }

    public String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public void getAffiliateName(final TextView tv) {
//        String affiliateId = loginActivity.getApp().getAffiliateId();
//        if (!TextUtils.isEmpty(BuildConfig.AFFILIATE_PACKAGE)) {
//            if (!TextUtils.isEmpty(affiliateId)) {
//                HashMap<String, String> p = new HashMap<>();
//                p.put("web_id", WebSiteUrl.WebId);
//                p.put("aff_id", affiliateId);
//                doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.AffiliateNameUrl, p), new BaseConsumer<String>(baseContext) {
//                    @Override
//                    protected void onBaseGetData(String data) throws JSONException {
//                        AffNameBean affNameBean = gson.fromJson(data, AffNameBean.class);
//                        tv.setVisibility(View.VISIBLE);
//                        tv.setText(affNameBean.getData().getAff_name());
//                    }
//                });
//            }
//        }
    }
}
