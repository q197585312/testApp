package com.unkonw.testapp.libs.base;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.unkonw.testapp.libs.common.ActivityPageManager;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.AutoUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;
import com.unkonw.testapp.libs.widget.DialogLoading;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

//import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

/**
 * Created by Administrator on 2016/12/15 0015.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseContext {
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
//    protected CompositeDisposable mCompositeSubscription;


    public T presenter;
    /**
     * 加载对话框
     */
    protected DialogLoading loading;
    protected BasePopupWindow popWindow;
    private Map<Boolean, String> additionMap = new HashMap<>();

    public boolean isHasAttached() {
        return hasAttached;
    }

    private boolean hasAttached = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        //全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        // 设置不能横屏
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        mContext = this;
        //Activity管理
        ActivityPageManager.getInstance().addActivity(this);
        Log.d(getClass().getSimpleName(), "onCreate: ");

    }

    @Override
    public void setContentView(int layoutResID) {
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            AutoUtils.setSize(this, false, 720, 1280);
        } else {
            AutoUtils.setSize(this, false, 1280, 720);
        }

        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(view);

    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(getClass().getSimpleName(), "onAttachedToWindow: " + getClass().getSimpleName());
        hasAttached = true;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        hasAttached = false;
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
        initData();
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
    public void initView() {
        ButterKnife.bind(this);
    }

    /**
     * 绑定事件
     */
    public void initData() {
    }


    /**
     * 创建相应的 presenter
     */
    public void createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    public BasePopupWindow createPopupWindow(BasePopupWindow basePopupWindow) {
        if (popWindow != null) {
            popWindow.closePopupWindow();
        }
        this.popWindow = basePopupWindow;
        return popWindow;
    }

    public void stopPopupWindow() {
        if (popWindow != null) {
            popWindow.closePopupWindow();
            popWindow = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPopupWindow();
        //Acitvity 释放子view资源
        ActivityPageManager.unbindReferences(mContentView);
        ActivityPageManager.getInstance().removeActivity(this);
        mContentView = null;
        //一旦调用了 CompositeSubscription.unsubscribe()，这个CompositeSubscription对象就不可用了,
        // 如果还想使用CompositeSubscription，就必须在创建一个新的对象了。
//        if(mCompositeSubscription != null){
//            mCompositeSubscription.dispose();
//        }
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
    public void showFragmentToActivity(@NonNull Fragment fragment, int frameId) {
        showFragmentToActivity(fragment, frameId, null);
    }

    protected void showFragmentToActivity(@NonNull Fragment fragment, int frameId, String tag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!fragment.isAdded()) {
            transaction.add(frameId, fragment, tag);
        }
        transaction.show(fragment);
        transaction.commit();
    }

    public void hideFragmentToActivity(@NonNull Fragment fragment) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    public void removeFragmentToActivity(@NonNull Fragment fragment) {
        checkNotNull(fragment);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (fragment.isAdded()) {
            transaction.remove(fragment);
        }
        transaction.commit();
    }

    public void removeAllFromActivity() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < count; ++i) {
            fragmentManager.popBackStack();
        }
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
        if (isFinishing() || !hasAttached) {
            loading.dismiss();
            return;
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    /**
     * 跳转到对应activity
     */
    public void skipFullNameActivity(Bundle bundle, String fullName) {
        if (fullName != null && fullName.length() > 0) {
            try {
                skipAct(Class.forName(fullName), bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bundle);
        intent.putExtra("fromWhere", getClass().getSimpleName());
        intent.setFlags(flags);
        startActivity(intent);
    }

    public String getMsgIntent(String type) {
        return getIntent().getStringExtra(type);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (newConfig.fontScale != 1)//非默认值
            getResources();
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {//非默认值
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();//设置默认
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }


    public BaseActivity<T> getBaseActivity() {
        return this;
    }

    public IBaseContext getIBaseContext() {
        return this;
    }

    public Map<Boolean, String> getAdditionMap() {
        return additionMap;
    }
}
