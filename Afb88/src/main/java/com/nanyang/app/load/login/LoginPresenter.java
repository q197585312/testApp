package com.nanyang.app.load.login;


import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;

import com.google.gson.Gson;
import com.nanyang.app.ApiServiceKt;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.load.PersonalInfo;
import com.nanyang.app.main.Setting.SettingAllDataBean;
import com.nanyang.app.main.home.LoginResultData;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;

import okhttp3.RequestBody;

class LoginPresenter extends BaseRetrofitPresenter<LoginActivity> {

    public volatile int hasSucceed = 0x00;
    public volatile int loginType = 0;

    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginActivity view) {
        super(view);
        this.baseContext = view;
    }

    public void login(final LoginInfo info) {
        if (checkUserAvailable(info)) {
            final String url_login = AppConstant.getInstance().URL_LOGIN;
            String infoWfmain = info.getWfmainJson("Login", new LanguageHelper(baseContext).getLanguage());
            String url = url_login + "?_fm=" + infoWfmain;


            RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), infoWfmain);

            doRetrofitApiOnDefaultThread(ApiServiceKt.Companion.getInstance().doPostJsonLogin(url_login, body)
                    , new BaseConsumer<LoginResultData>(baseContext) {
                        @Override
                        protected void onBaseGetData(LoginResultData s) throws JSONException {
                            if (s == null)
                                return;
                            ((BaseToolbarActivity) (baseContext.getBaseActivity())).getApp().setAuthorization(s.getAuthorization());

                            if (s.getLoginStatus().equals("true") || s.getLoginStatus().equals("1")) {
                                final MainPresenter switchLanguage = new MainPresenter(baseContext);
                                switchLanguage.getSetting(new MainPresenter.CallBack<SettingAllDataBean>() {
                                                              @Override
                                                              public void onBack(SettingAllDataBean data) throws JSONException {
                                                                  checkLogin(0x01);
                                                              }
                                                          }
                                );
                                String language = new LanguageHelper(baseContext.getBaseActivity()).getLanguage();

                                switchLanguage.loadAllMainData(new LoginInfo.LanguageWfBean("AppGetDate", language, AppConstant.getInstance().wfMain), new MainPresenter.CallBack<String>() {
                                    @Override
                                    public void onBack(String data) {
                                        PersonalInfo personalInfo = new Gson().fromJson(data, PersonalInfo.class);
                                        personalInfo.setPassword(((BaseToolbarActivity) (baseContext.getBaseActivity())).getApp().getUser().getPassword());
                                        personalInfo.setLoginUrl(url);
                                        ((BaseToolbarActivity) (baseContext.getBaseActivity())).getApp().setUser(personalInfo);

                                        checkLogin(0x10);
                                    }
                                });

                            } else {

                                Exception exception1 = new Exception(s.getMessage());
                                onError(exception1);
                            }


                        }

                        @Override
                        protected void onHideDialog() {
                        }

                        @Override
                        protected void onError(final Throwable throwable) {
                            super.onError(throwable);
                            (baseContext.getBaseActivity()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtils.showShort(throwable.getMessage());
                                }
                            });

                        }
                    }
            );
        }
    }

    private void checkLogin(int i) {
        hasSucceed = hasSucceed | i;
        if (hasSucceed == 0x11)
            baseContext.getBaseActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    baseContext.onLanguageSwitchSucceed("");
                }
            });

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

    public void playVideoRaw(final CustomVideoView c_video_bg) {
        MediaController mediaController = new MediaController(baseContext);
        //获取raw.mp4的uri地址
        String uri = "android.resource://" + baseContext.getPackageName() + "/" + R.raw.login;
        c_video_bg.setVideoURI(Uri.parse(uri));
        //让video和mediaController建立关联
//        c_video_bg.setMediaController(mediaController);
        mediaController.setMediaPlayer(c_video_bg);
        //让video获取焦点
        c_video_bg.requestFocus();
        //监听播放完成，
        c_video_bg.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                //重新开始播放
                c_video_bg.start();
            }
        });
    }


}