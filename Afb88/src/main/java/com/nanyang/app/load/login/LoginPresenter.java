package com.nanyang.app.load.login;


import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.MediaController;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.R;
import com.nanyang.app.common.LanguageHelper;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import static com.unkonw.testapp.libs.api.Api.getService;

class LoginPresenter extends BaseRetrofitPresenter<LoginActivity> {
    //构造 （activity implements v, 然后LoginPresenter(this)构造出来）
    LoginPresenter(LoginActivity view) {
        super(view);
    }

    public void login(final LoginInfo info, BaseConsumer<String> baseConsumer) {
        if (checkUserAvailable(info)) {
            //http://www.afb1188.com/W0/Pub/pcode.axd
            final String url_login = AppConstant.getInstance().URL_LOGIN;
            String infoWfmain = info.getWfmainJson("Login", new LanguageHelper(baseContext).getLanguage());
            String url = url_login + "?_fm=" + infoWfmain;
            doRetrofitApiOnUiThread(getService(ApiService.class).getData(url)
                    , baseConsumer);
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
    public void playVideoRaw(final CustomVideoView c_video_bg) {
        MediaController mediaController = new MediaController(baseContext);
        //获取raw.mp4的uri地址
        String uri = "android.resource://" + baseContext.getPackageName() + "/" + R.raw.login;
        c_video_bg.setVideoURI(Uri.parse(uri));
        //让video和mediaController建立关联
        c_video_bg.setMediaController(mediaController);
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