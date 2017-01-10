package com.unkonw.testapp.base;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.unkonw.testapp.utils.ToastUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.ResponseBody;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity {
    protected AppCompatActivity mContext;
    /**
     * 页面布局的 根view
     */
    protected View mContentView;
    /**
     * 来自哪个 页面
     */
    protected String fromWhere;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    protected CompositeDisposable mCompositeSubscription;

    /**
     * Api类的包装 对象
     */
//    protected ApiWrapper mApiWrapper;
    public T presenter;
    /**
     * 加载对话框
     */
    protected DialogLoading loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置不能横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mContext = this;
        //Activity管理
        ActivityPageManager.getInstance().addActivity(this);
    }
    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);
    }
    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        mContentView = view;
        //初始化页面
        init();
    }
    /**
     * 初始化页面
     */
    public void init() {
        initFromWhere();
        initView();
        bindEvent();
    }
    protected void initFromWhere() {
        if (null != getIntent().getExtras()) {
            if (getIntent().getExtras().containsKey("fromWhere")) {
                fromWhere = getIntent().getExtras().getString("fromWhere").toString();
            }
        }
    }
    public String getFromWhere() {
        return fromWhere;
    }
    /**
     * 初始化view
     */
    public abstract void initView();
    /**
     * 绑定事件
     */
    public abstract void bindEvent();


    /**
     * 初始化 Api  更具需要初始化
     */
    public void initApi() {
        //创建 CompositeSubscription 对象 使用CompositeSubscription来持有所有的Subscriptions，然后在onDestroy()或者onDestroyView()里取消所有的订阅。
        mCompositeSubscription = new CompositeDisposable();
        // 构建 ApiWrapper 对象
//        mApiWrapper = new ApiWrapper();
    }

    /**
     * 创建观察者  这里对观察着 过滤一次，过滤出我们想要的信息，错误的信息toast
     *
     * @param onNext
     * @param <T>
     * @return
     */

    protected <T> Subscriber newMySubscriber(final SimpleMyCallBack onNext) {
        return new Subscriber<T>() {


            @Override
            public void onError(Throwable e) {
                if (e instanceof Api.APIException) {
                    Api.APIException exception = (Api.APIException) e;
                    ToastUtils.showShort(exception.message);
                } else if (e instanceof HttpException) {
                    if (e instanceof HttpException) {
                        ResponseBody body = ((HttpException) e).response().errorBody();
                        try {
                            String json = body.string();
                            Gson gson = new Gson();
                            HttpExceptionBean mHttpExceptionBean = gson.fromJson(json, HttpExceptionBean.class);
                            if (mHttpExceptionBean != null && mHttpExceptionBean.getMessage() != null) {
                                ToastUtils.showShort(mHttpExceptionBean.getMessage());
                                onNext.onError(mHttpExceptionBean);
                            }
                        } catch (IOException IOe) {
                            IOe.printStackTrace();
                        }
                    }
                }
//                e.printStackTrace();
                hideLoadingDialog();
            }

            @Override
            public void onComplete() {
                hideLoadingDialog();
                onNext.onCompleted();
            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(T t) {
                if (!mCompositeSubscription.isDisposed()) {
                    onNext.onNext(t);
                }
            }

        };
    }
    public CompositeDisposable getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        return mCompositeSubscription;
    }
/*    public Api getApiWrapper() {
        if (mApiWrapper == null) {
            mApiWrapper = new ApiWrapper();
        }
        return mApiWrapper;
    }*/
    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Acitvity 释放子view资源
        ActivityPageManager.unbindReferences(mContentView);
        ActivityPageManager.getInstance().removeActivity(this);
        mContentView = null;
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
        if(mCompositeSubscription != null){
            mCompositeSubscription.dispose();
        }
        //解绑 presenter
        if (presenter != null) {
            presenter.unSubscribe();
        }
    }

    /**
     * 将 Fragment添加到Acitvtiy
     *
     * @param fragment
     * @param frameId
     */
    protected void addFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }


    /**
     * 显示一个Toast信息
     */
    public void showToast(String content) {
        if (content != null) {
            ToastUtils.showShort(content);
        }
    }

    public void showLoadingDialog() {
        if (loading == null) {
            loading = new DialogLoading(this);
        }
        loading.show();
    }

    public void hideLoadingDialog() {
        if (loading != null) {
            loading.dismiss();
        }

    }

    /**
     * 跳转页面
     *
     * @param clazz
     */
    public void skipAct(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.setFlags(flags);
        startActivity(intent);
    }
}
