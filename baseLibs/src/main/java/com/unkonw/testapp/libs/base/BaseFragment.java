package com.unkonw.testapp.libs.base;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.presenter.IBasePresenter;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;


public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseContext {
    public BaseActivity mContext;
    public View mContentView = null;
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     */
    public CompositeDisposable mCompositeSubscription;
    public boolean parentHidden;

    public T getPresenter() {
        return presenter;
    }

    /**
     * Api类的包装 对象
     */
    /*    public Api mApiWrapper;*/

    public T presenter;
    protected BasePopupWindow popWindow;
    private String title;
    public Gson gson;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getBaseActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater
                .inflate(onSetLayoutId(), container, false);
        ButterKnife.bind(this, mContentView);
        if (gson == null) {
            gson = new Gson();
        }
        initView();

        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
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
            LogUtil.d("createPresenter",getClass().getSimpleName());
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
        Log.d(getClass().getSimpleName(), "initData: " + getClass().getSimpleName());
    }

    public void showToast(String content) {
        mContext.showToast(content);
    }


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
    public void onDestroyView() {
        super.onDestroyView();
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


    public IBaseContext getIBaseContext() {
        return this;
    }

    public void initWaitData() {
        LogUtil.d("waitNumber", getClass().getSimpleName() + ":initWaitData");
    }

    public void refreshData() {

    }

    public void refreshData(String item) {

    }

    public void setParentHidden(boolean hidden) {
        this.parentHidden = hidden;
    }

    public void showChoosePic(Uri uri){

    }

}
