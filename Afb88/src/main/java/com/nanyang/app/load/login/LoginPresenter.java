package com.nanyang.app.load.login;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.SwitchLanguage;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.base.IBaseContext;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.LogUtil;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;

import static com.unkonw.testapp.libs.api.Api.getService;

class LoginPresenter extends BaseRetrofitPresenter<LoginActivity> {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginActivity view) {
        super(view);
    }

    @NonNull
    private String getLanguage() {
        return AfbUtils.getLangParamStr(baseContext.getBaseContext());
    }


    public void login(final LoginInfo info, BaseConsumer<String> baseConsumer) {
        if (checkUserAvailable(info)) {
            //http://www.afb1188.com/W0/Pub/pcode.axd
            final String url_login = AppConstant.getInstance().URL_LOGIN;
            Map<String, String> infoWfmain = info.getWfmain("Login", getLanguage());
            doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(url_login, infoWfmain)

                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            if (s.contains("Maintenance")) {
                                Exception exception = new Exception(((Activity) baseContext).getString(R.string.System_maintenance));
                                throw exception;
                            } else {
                                String regex = "window.location";
                                Pattern p = Pattern.compile(regex);
                                Matcher m = p.matcher(s);
                                if (m.find()) {
                                    return getService(ApiService.class).getData(url_login);
                                }
                                Exception exception1 = new Exception("Server Error");
                                throw exception1;
                            }

                        }
                    }).flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            SwitchLanguage switchLanguage = new SwitchLanguage<IBaseContext>(baseContext);
                            return switchLanguage.switchLanguage(getLanguage(), "MY");
                        }

                    }), baseConsumer);
            return;
        }
    }


    private boolean checkUserAvailable(LoginInfo info) {
        if (info.getTxtUserName().isEmpty()) {
            baseContext.promptMsg(R.string.Account_empty);
            return false;
        }
        if (info.getPassword_password().isEmpty()) {
            baseContext.promptMsg(R.string.Password_empty);
            return false;
        }
        return true;
    }

    public void loadAllImages() {
//        http://www.appgd88.com/api/afb1188.php?app=afb88&lang=EN-CA
        doRetrofitApiOnUiThread(getService(ApiService.class).getAllImagesData(BuildConfig.ImgConfig_URL), new BaseConsumer<AllBannerImagesBean>(baseContext) {
            @Override
            protected void onBaseGetData(AllBannerImagesBean data) {
//                @Subscribe(threadMode = ThreadMode.MainThread)
                LogUtil.d("AllBannerImagesBean", data.toString());
                LoginPresenter.this.baseContext.sendImageEvent(data);

            }
        });
    }
}