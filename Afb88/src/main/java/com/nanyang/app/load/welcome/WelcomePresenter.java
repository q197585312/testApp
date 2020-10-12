package com.nanyang.app.load.welcome;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nanyang.app.AfbApplication;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.Been.CheckVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.LoadMainDataHelper;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Iterator;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

import static com.unkonw.testapp.libs.api.ApiManager.getService;

class WelcomePresenter extends BaseRetrofitPresenter<WelcomeActivity> {
    private File file;
    LanguageHelper helper;
    //构造 （activity implements v, 然后WelcomePresenter(this)构造出来）
    WelcomePresenter(WelcomeActivity view) {
        super(view);
        helper = new LanguageHelper(baseContext.getBaseActivity());
    }


    public void checkVersion(BaseConsumer<CheckVersionBean> baseConsumer) {
        doRetrofitApiOnUiThread(getService(ApiService.class).checkVersion(BuildConfig.CHECK_VERSION), baseConsumer);
    }


    public void updateVersion(final String url) {
        String path = Environment.getExternalStorageDirectory().getPath();
        file = new File(path, "afb88.apk");
        file.deleteOnExit();
        doRetrofitApiOnDefaultThread(getService(ApiService.class).updateVersion(url), new BaseConsumer<ResponseBody>(baseContext) {
            @Override
            protected void onBaseGetData(ResponseBody response) throws Exception {
                long contentLength;
                InputStream is = response.byteStream();
                contentLength = response.contentLength();
                FileOutputStream fos = new FileOutputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is);
                byte[] buffer = new byte[1024];
                int len;
                while ((len = bis.read(buffer)) != -1) {
                    WelcomePresenter.this.baseContext.onLoadingApk(len, contentLength);
                    fos.write(buffer, 0, len);
                }
                fos.flush();
                fos.close();
                bis.close();
                is.close();
                Thread.sleep(1500);
                if (file.exists() && file.length() > 0)
                    WelcomePresenter.this.baseContext.onLoadEnd(file);
            }

            @Override
            protected void onAccept() {
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                WelcomePresenter.this.baseContext.onLoadError(throwable.getMessage());
            }
        });

    }

    public void checkInitCheck(Intent intent) {
        Bundle extras = intent.getExtras();
        if (intent == null || extras == null || extras.getString("companyKey") == null) {
            ((BaseActivity) baseContext.getBaseActivity()).skipAct(LoginActivity.class);
            baseContext.getBaseActivity().finish();

        } else if (extras.getString("companyKey").equals("gd88")) {
            String loginUrl = extras.getString("loginUrl");
            skipMain(loginUrl);
        } else {
            String companyKey = extras.getString("companyKey");
            String userName = extras.getString("userName");
            String us = extras.getString("us");
            String language = extras.getString("lang");
            String webId = extras.getString("webId");
            String currencyName = extras.getString("currencyName");
            String oddsType = extras.getString("oddsType");
            Log.d(getClass().getSimpleName(), "checkInitCheck: " + language);
            helper.switchLanguage(language);
            String language1 = helper.getLanguage();
            skipLogin(companyKey, userName, us, language1, webId, currencyName, oddsType);
        }
    }

    public void skipLogin(String companyKey, final String userName, final String us, final String language, final String webId, final String currencyName, final String oddsType) {

        String ckAccUrl = AppConstant.getInstance().HOST + "Public/ckAcc.ashx";
        CompanyKeyBean info = new CompanyKeyBean(companyKey, userName);
        Gson gson = new Gson();
        String obj = gson.toJson(info);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj);

        doRetrofitApiOnUiThread(getService(ApiService.class).doPostJson(ckAccUrl, body)
                , new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(final String data) {
                        CkAccResponseBean bean = new Gson().fromJson(data, new TypeToken<CkAccResponseBean>() {
                        }.getType());
                        if (bean != null && !bean.getError().equals("1")) {
                            final String url_login = AppConstant.getInstance().HOST + "Public/validate.aspx?us=" + webId + "s" + userName + "&k=" + bean.getToken() + "&device=m&oddsstyle=" + oddsType + "&oddsmode=Double&lang=" + language + "&currencyName=" + currencyName
                                    + "&sk=H50";
                            skipMain(url_login);
                        }
                    }
                });
    }

    private void skipMain(final String url_login) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(url_login), new BaseConsumer<String>(baseContext) {
                    @Override
                    protected void onBaseGetData(String data) throws Exception {
                        if (!StringUtils.isNull(data) && data.contains("wfMain")) {
                            onSkipSucceeded(url_login);
                        }
                    }

                    @Override
                    protected void onError(Throwable throwable) {
                        super.onError(throwable);
                    }
                }
        );
    }


    private void onSkipSucceeded(String url_login) {
        AppConstant.IS_AGENT = true;
        AppConstant.wfMain = "wfMainH501";

        MainPresenter switchLanguage = new MainPresenter(baseContext);
        AfbApplication app = (AfbApplication) baseContext.getBaseActivity().getApplication();
        String us = StringUtils.findGroup(url_login, "^.*us=([^&]+)&.*?", 1);
        app.getUser().setLoginName(us);
        app.getUser().setPassword("");
        Iterator<MenuItemInfo<String>> iterator = helper.getLanguageItems().iterator();
        MenuItemInfo<String> en = new MenuItemInfo<>(R.mipmap.lang_en_flag, (R.string.language_en), "en", "EN-US");
        while (iterator.hasNext()){
            MenuItemInfo<String> next = iterator.next();
            if(url_login.contains(next.getParent())){
                en=next;
                break;
            }
        }
        helper.switchLanguage(en.getType());
        switchLanguage.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
            @Override
            public void onBack(SettingAllDataBean data) throws JSONException {
                String language = helper.getLanguage();
                LoadMainDataHelper helper = new LoadMainDataHelper(mApiWrapper, baseContext.getBaseActivity(), mCompositeSubscription);
                helper.doRetrofitApiOnUiThread(new LoginInfo.LanguageWfBean("AppGetDate", language, AppConstant.wfMain), new MainPresenter.CallBack<String>() {
                    @Override
                    public void onBack(String data) {
                        PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                        personalInfo.setPassword(((AfbApplication) baseContext.getBaseActivity().getApplication()).getUser().getPassword());
                        personalInfo.setLoginUrl(url_login);
                        ((AfbApplication) baseContext.getBaseActivity().getApplication()).setUser(personalInfo);
                        WelcomePresenter.this.baseContext.onLanguageSwitchSucceed(data);
                    }
                });
            }
        });
    }

}
