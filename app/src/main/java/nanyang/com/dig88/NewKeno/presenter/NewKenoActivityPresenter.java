package nanyang.com.dig88.NewKeno.presenter;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;
import org.reactivestreams.Subscription;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.NewKeno.NewKenoActivity;
import nanyang.com.dig88.NewKeno.NewKenoCreateAccountBean;
import nanyang.com.dig88.NewKeno.NewKenoLoginBean;
import nanyang.com.dig88.NewKeno.NewKenoUrlBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by Administrator on 2019/12/3.
 */

public class NewKenoActivityPresenter extends BaseRetrofitPresenter<NewKenoActivity> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    NewKenoActivity newKenoActivity;
    private String newkenoCreateUrl;

    public NewKenoActivityPresenter(NewKenoActivity iBaseContext) {
        super(iBaseContext);
        newKenoActivity = iBaseContext;
    }

    public void getBaseUrl() {
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", newKenoActivity.getUserInfoBean().getUser_id());
        p.put("session_id", newKenoActivity.getUserInfoBean().getSession_id());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.NewkenoBaseUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NewKenoUrlBean newKenoUrlBean = new Gson().fromJson(data, NewKenoUrlBean.class);
                String baseUrl = newKenoUrlBean.getData().getNewkeno();
                newKenoActivity.onGetBaseUrl(baseUrl);
            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
                newKenoActivity.onGetBaseUrl(newKenoActivity.getBaseUrl());
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }

    public void loginNewKeno() {
        newkenoCreateUrl = newKenoActivity.getBaseUrl() + "index.php";
        Disposable subscription = getService(ApiService.class).doPostMap(newkenoCreateUrl, getLoginParam(1))
                .flatMap(new Function<String, Flowable<String>>() {
                    @Override
                    public Flowable<String> apply(String s) throws Exception {
                        NewKenoCreateAccountBean newKenoCreateAccountBean = gson.fromJson(s, NewKenoCreateAccountBean.class);
                        if (newKenoCreateAccountBean.getCode().equals("1")) {
                            return getService(ApiService.class).doPostMap(newkenoCreateUrl, getLoginParam(2));
                        } else {
                            Exception exception = new Exception(newKenoActivity.getString(R.string.Login_Failed));
                            throw exception;
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {//onNext
                    @Override
                    public void accept(String str) throws Exception {
                        NewKenoLoginBean newKenoLoginBean = gson.fromJson(str, NewKenoLoginBean.class);
                        if (newKenoLoginBean.getErrMsg().contains("success")) {
                            newKenoActivity.onLoginSuccess();
                        } else {
                            newKenoActivity.onLoginFailed();
                        }
                    }
                }, new Consumer<Throwable>() {//错误
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        baseContext.dismissBlockDialog();
                        newKenoActivity.onLoginFailed();
                    }
                }, new Action() {//完成
                    @Override
                    public void run() throws Exception {
                        baseContext.dismissBlockDialog();
                    }
                }, new Consumer<Subscription>() {//开始绑定
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        baseContext.showBlockDialog();
                        subscription.request(Long.MAX_VALUE);
                    }
                });
        mCompositeSubscription.add(subscription);
    }

    private HashMap<String, String> getLoginParam(int type) {
        UserInfoBean userInfoBean = newKenoActivity.getUserInfoBean();
        HashMap<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("secret", "kg-keno-b7df-4f7a-8ecd-19be");
        p.put("username", newKenoActivity.getUsername());
        if (type == 1) {
            p.put("action", "create");
            p.put("currency", newKenoActivity.getCurrency());
            p.put("agent_id", userInfoBean.getAgent_id());
        } else {
            p.put("action", "login");
            p.put("token", userInfoBean.getSession_id());
            p.put("ip", SharePreferenceUtil.getString(newKenoActivity, "IP") + "-Android");
        }
        return p;
    }
}
