package com.nanyang.app.load.welcome;


import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;

public class WelcomePresenter extends BaseRetrofitPresenter<String,WelcomeContract.View,ApiWelcome> implements WelcomeContract.Presenter {
    //构造 （activity implements v, 然后WelcomePresenter(this)构造出来）
    public WelcomePresenter(WelcomeContract.View view) {
        super(view);
    }

    @Override
    public ApiWelcome createRetrofitApi() {
        return new ApiWelcome();
    }


    @Override
    public void checkVersion(final String versionName) {
        Disposable subscription = mApiWrapper.checkVersion().flatMap(new Function<String, Flowable<ResponseBody>>() {
            @Override
            public Flowable<ResponseBody> apply(String s) throws Exception {
                    if(Float.valueOf(s)>Float.valueOf(versionName)){
                        return updateVersion();
                    }
                return null;
            }
        })
                .subscribe(new Consumer<ResponseBody>() {//onNext
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        try {
                            InputStream is = response.byteStream();
                            String path = Util.getSdCardPath();
                            File file = new File(path, "download.jpg");
                            FileOutputStream fos = new FileOutputStream(file);
                            BufferedInputStream bis = new BufferedInputStream(is);
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = bis.read(buffer)) != -1) {
                                fos.write(buffer, 0, len);
                                baseView.onLoadingApk(len, totalLength);
                            }
                            fos.flush();
                            fos.close();
                            bis.close();
                            is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        baseView.onGetData(Str);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onFailed(throwable.getMessage());
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    @Override
    public Flowable<ResponseBody> updateVersion() {
       return mApiWrapper.updateVersion();
    }
}