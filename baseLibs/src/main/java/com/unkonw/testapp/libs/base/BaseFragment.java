package com.unkonw.testapp.libs.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.SystemTool;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import solid.ren.skinlibrary.base.SkinBaseFragment;


public abstract class BaseFragment<T extends IBasePresenter> extends SkinBaseFragment {
    public BaseActivity mContext;
    public View mContentView = null;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    public CompositeDisposable mCompositeSubscription;
    /**
     * Api类的包装 对象
     */
/*    public Api mApiWrapper;*/

    public T presenter;
    protected BasePopupWindow popWindow;
    private String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SystemTool.switchLanguage(SystemTool.getLanguage(mContext), mContext);
        View mContentView = inflater
                .inflate(onSetLayoutId(), container, false);
        ButterKnife.bind(this, mContentView);
        initView();
        initData();
        return mContentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //解绑 presenter
        if (presenter != null) {
            presenter.unSubscribe();
        }

    }

    /**
     * 创建相应的 presenter
     */
    public T createPresenter(T presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
        return presenter;

    }

    public BaseActivity getBaseActivity() {
        return (BaseActivity) this.getActivity();
    }
/*    *//**
     * 初始化 Api  更具需要初始化
     *//*
    public void initApi() {
        mCompositeSubscription  = mContext.getCompositeSubscription();
    }*/

    /**
     * 设置布局文件
     *
     * @return 返回布局文件资源Id
     */
    public abstract int onSetLayoutId();

    public void initView() {
    }

    public void initData() {
    }

    public void showToast(String content) {
        mContext.showToast(content);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void showLoadingDialog() {
        mContext.showLoadingDialog();
    }

    public void hideLoadingDialog() {
        mContext.hideLoadingDialog();
    }

    public void skipAct(Class clazz) {
        mContext.skipAct(clazz);
    }

    public void skipAct(Class clazz, Bundle bundle) {
        mContext.skipAct(clazz, bundle);
    }

    public void skipAct(Class clazz, Bundle bundle, int flags) {
        mContext.skipAct(clazz, bundle, flags);
    }

    public BasePopupWindow createPopupWindow(BasePopupWindow popupWindow) {
        return this.popWindow = mContext.createPopupWindow(popupWindow);
    }

    @Override
    public void onStop() {
        super.onStop();
        mContext.stopPopupWindow();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void initHead() {

    }

    public Bitmap headBitmap;

    public ImageView getHeadImg() {
        return new ImageView(mContext);
    }

    public Bitmap getheadBitmap() {
        return headBitmap;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void refreshData(String type) {

    }


    public Activity getContextActivity() {
        return getBaseActivity();
    }
}
