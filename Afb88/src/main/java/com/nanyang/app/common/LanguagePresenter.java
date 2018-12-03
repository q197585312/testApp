package com.nanyang.app.common;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.main.center.model.TransferMoneyBean;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.reactivestreams.Subscription;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;
import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2017/4/19.
 */

public class LanguagePresenter extends BaseRetrofitPresenter<String, ILanguageView<String>> {
    SwitchLanguage switchLanguage;

    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param view
     */
    public LanguagePresenter(ILanguageView<String> view) {
        super(view);
        switchLanguage = new SwitchLanguage(view, mCompositeSubscription);
    }
    public void switchLanguage(String lang) {
        switchLanguage.switchLanguage(lang);
    }
    public void skipGd88() {
        Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().HOST+"_View/LiveDealerGDC.aspx")


                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String Str) throws Exception {
                        int start = Str.indexOf("TransferBalance");
                        int end = Str.indexOf("\" id=\"form1\"");

                        String k = "";
                        if (start > 0 && end > 0 && end > start) {
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
                            String url = Str.substring(start, end);
                            k = url.substring(url.indexOf("k="));
                            baseView.onGetData(k);
                        } else if (Str.contains("Transaction not tally")) {
                            ToastUtils.showShort("Transaction not tally");
                        } else if (Str.contains("Session Expired")) {
                            ToastUtils.showShort("Session Expired");
                        } else if (Str.contains("Account is LOCKED")) {
                            ToastUtils.showShort("Account is LOCKED! Please contact your agent!");
                        } else {
                            ToastUtils.showShort("Failed");
                        }

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
    public void getTransferMoneyData(final String data) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).getTransferMoneyData(AppConstant.getInstance().URL_TRANSFER_MONEY_DATA))
                .subscribe(new Consumer<TransferMoneyBean>() {
                    @Override
                    public void accept(TransferMoneyBean transferMoneyBean) throws Exception {
                        baseView.getMoneyMsg(transferMoneyBean,data);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.hideLoadingDialog();
                        Log.d(TAG, "accept: " + throwable.toString());

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseView.showLoadingDialog();
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(d);
    }
    public void gamesGDTransferMonet(String egLimit, final String data) {
        Disposable d = mApiWrapper.applySchedulers(Api.getService(ApiService.class).gamesGDTransferMoney(AppConstant.getInstance().URL_TRANSFER_MONEY_GD_GAMES, egLimit))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        baseView.onGetTransferMoneyData(0,s,data);
                        baseView.hideLoadingDialog();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseView.onGetTransferMoneyData(-1,throwable.toString(),data);
                        baseView.hideLoadingDialog();
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                        baseView.showLoadingDialog();
                    }
                });
        mCompositeSubscription.add(d);
    }
    @NonNull
    private String getLanguage() {
        String lag = AfbUtils.getLanguage((Activity) baseView);
        String lang;
        switch (lag) {
            case "zh":
                lang = "ZH-CN";
                break;
            case "en":
                lang = "EN-US";
                break;
            case "th":
                lang = "TH-TH";
                break;
            case "ko":
                lang = "EN-TT";
                break;
            case "vi":
                lang = "EN-IE";
                break;
            case "tr":
                lang = "UR-PK";
                break;

            default:
                lang = "EN-US";
                break;
        }
        return lang;
    }
    public void login(final LoginInfo info, final String gameType) {
        if(BuildConfig.FLAVOR.equals("afb1188")){
            Disposable subscription = getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, info.getWfmain("Login",getLanguage()))

                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String regex = "window.location";
                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(s);
                            if (m.find()) {
                                return getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN);
                            }
                            Exception exception1 = new Exception("Server Error");
                            throw exception1;

                        }
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {

                            //#	Result	Protocol	Host	URL	Body	Caching	Content-Type	Process	Comments	Custom

                            /*    SwitchLanguage switchLanguage = new SwitchLanguage(baseView, mCompositeSubscription);
                                switchLanguage.switchLanguage(lang);*/
                            baseView.onLanguageSwitchSucceed("");
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
            return;
        }

        Disposable subscription = getService(ApiService.class).getData(AppConstant.getInstance().URL_LOGIN)
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String substring1 = s.substring(s.indexOf("id=\"__VIEWSTATE\" value=\"") + 24);
                            String __VIEWSTATE = substring1.substring(0, substring1.indexOf("\""));
                            String substring2 = s.substring(s.indexOf("id=\"__EVENTVALIDATION\" value=\"") + 30);
                            String __EVENTVALIDATION = substring2.substring(0, substring2.indexOf("\""));
                            info.set__VIEWSTATE(__VIEWSTATE);
                            info.set__EVENTVALIDATION(__EVENTVALIDATION);

                            return getService(ApiService.class).doPostMap(AppConstant.getInstance().URL_LOGIN, info.getMap());

                        }
                    })
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String s) throws Exception {
                            String regex = ".*<script language='javascript'>window.open\\('(.*?)'.*?";
                            Pattern p = Pattern.compile(regex);
                            Matcher m = p.matcher(s);
                            if (m.find()) {

                                String url = m.group(1);
                                if (url.contains("Maintenance")) {
                                    Exception exception = new Exception(((Activity) baseView).getString(R.string.System_maintenance));
                                    throw exception;
                                } else {
//                                http://a0096f.panda88.org/Public/validate.aspx?us=demoafbai5&k=1a56b037cee84f08acd00cce8be54ca1&r=841903858&lang=EN-US
                                    String host = url.substring(0, url.indexOf("/", 9));
                                    AppConstant.getInstance().setHost(host + "/");
                                    Log.d("OKHttp", url);
                                    return getService(ApiService.class).getData(url);
                                }
                            }
                            Exception exception1 = new Exception("Server Error");
                            throw exception1;

                        }
                    })
                    //http://main55.afb88.com/_bet/panel.aspx
                    .flatMap(new Function<String, Flowable<String>>() {
                        @Override
                        public Flowable<String> apply(String str) throws Exception {
                            if (str.contains("window.open(")) {
                                return getService(ApiService.class).getData(AppConstant.getInstance().URL_PANEL);
                            }
                            Exception exception1 = new Exception("Login Failed");
                            throw exception1;


                        }
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {//onNext
                        @Override
                        public void accept(String str) throws Exception {
                            String lag = AfbUtils.getLanguage((Activity) baseView);
                            String lang;
                            switch (lag) {
                                case "zh":
                                    lang = "ZH-CN";
                                    break;
                                case "en":
                                    lang = "EN-US";
                                    break;
                                case "th":
                                    lang = "TH-TH";
                                    break;
                                case "ko":
                                    lang = "EN-TT";
                                    break;
                                case "vi":
                                    lang = "EN-IE";
                                    break;
                                case "tr":
                                    lang = "UR-PK";
                                    break;

                                default:
                                    lang = "EN-US";
                                    break;
                            }
                            SwitchLanguage switchLanguage = new SwitchLanguage(baseView, mCompositeSubscription);
                            switchLanguage.switchLanguage(lang);

                        }
                    }, new Consumer<Throwable>() {//错误
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            baseView.onFailed(throwable.getMessage());
                            baseView.onLoginAgainFinish(gameType);
                            baseView.hideLoadingDialog();
                        }
                    }, new Action() {//完成
                        @Override
                        public void run() throws Exception {
                            baseView.onLoginAgainFinish(gameType);
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
}
