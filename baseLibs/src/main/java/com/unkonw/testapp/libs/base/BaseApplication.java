/*
    ShengDao Android Client, BaseApplication
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.unkonw.testapp.libs.base;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.L;
import com.unkonw.testapp.R;
import com.unkonw.testapp.libs.common.ActivityPageManager;
import com.unkonw.testapp.libs.common.AuthImageDownloader;

import org.jetbrains.annotations.NotNull;


/**
 * 基础的Application
 **/
public class BaseApplication extends Application implements IViewModelStoreOwner{
    private final String tag = BaseApplication.class.getSimpleName();
    private static DisplayImageOptions options;
    private static BaseApplication instance;
    public static Context myContext;
    private ViewModelStore mAppViewModelStore;
    private ViewModelProvider.AndroidViewModelFactory mFactory;
    /*
     * 是否完成  整个项目
     */
    public static boolean isCompleteProject = false;

    public static ImageLoader imageLoader;
    public static final boolean ISDEBUG = false;


    public static BaseApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        myContext = this;
        init();

    }

    /**
     * 初始化
     */
    private void init() {
        mAppViewModelStore = new ViewModelStore();
        mFactory = ViewModelProvider.AndroidViewModelFactory.getInstance(this);
        initImageLoader();
//        CrashHandler.create(this);
//        JPushInterface.init(this); // 初始化JPush
//        JPushInterface.setDebugMode(true);  // 设置日志,发布时请关闭日志

    }


    private void initImageLoader() {

        //获取系统分配给每个应用程序的最大内存，每个应用系统分配32M
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int mCacheSize = maxMemory / 8;
        //给LruCache分配1/8 4M


        options = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .imageScaleType(ImageScaleType.EXACTLY)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(getApplicationContext())
                .defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .imageDownloader(new AuthImageDownloader(this))
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        //初始化图片下载组件
     /*   ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .imageDownloader(new AuthImageDownloader(this))
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(mCacheSize))
                .defaultDisplayImageOptions(options)
                .build();*/

        ImageLoader.getInstance().init(config);
        //关闭 打开log  imgelog
        L.writeLogs(false);

    }

    /**
     * 退出应用
     */
    public void exit() {
        ActivityPageManager.getInstance().exit(this);
    }


    @NotNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }

    @NotNull
    @Override
    public ViewModelProvider.Factory getViewModelFactory() {
        return mFactory;
    }
}
