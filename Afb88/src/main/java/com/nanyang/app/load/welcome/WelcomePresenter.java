package com.nanyang.app.load.welcome;


import android.os.Environment;
import android.util.Log;

import com.nanyang.app.ApiService;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.reactivestreams.Subscription;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.unkonw.testapp.libs.api.Api.getService;

class WelcomePresenter extends BaseRetrofitPresenter<String, WelcomeContract.View> implements WelcomeContract.Presenter {
    private File file;

    //构造 （activity implements v, 然后WelcomePresenter(this)构造出来）
    WelcomePresenter(WelcomeContract.View view) {
        super(view);
    }


    @Override
    public void checkVersion(final String versionName) {
        Disposable subscription = mApiWrapper.applySchedulers(getService(ApiService.class).checkVersion())
//                mApiWrapper.checkVersion()
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String response) throws Exception {
                        baseView.onGetData(response);
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
    public void updateVersion(final String version) {
        String path = Environment.getExternalStorageDirectory().getPath();

        file = new File(path, "afb88_test.apk");
        file.deleteOnExit();
        Disposable subscription = getService(ApiService.class).updateVersion().observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
//                mApiWrapper.updateVersion()
                .subscribe(new Consumer<ResponseBody>() {//onNext
                    @Override
                    public void accept(ResponseBody response) throws Exception {
                        long contentLength;

                        InputStream is = response.byteStream();
                        contentLength = response.contentLength();
                        FileOutputStream fos = new FileOutputStream(file);
                        BufferedInputStream bis = new BufferedInputStream(is);
                        byte[] buffer = new byte[1024];
                        int len;

                        while ((len = bis.read(buffer)) != -1) {
                            fos.write(buffer, 0, len);
                            baseView.onLoadingApk(len, contentLength);
                        }
                        fos.flush();
                        fos.close();
                        bis.close();
                        is.close();

                        if (file.exists() && file.length() > 0)
                            baseView.onLoadEnd(file);
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onLoadError(throwable.getMessage());

                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        Log.d("LOAD", "wancheng");
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {

                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);

    }
}