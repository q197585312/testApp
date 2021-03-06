/*
    ShengDao Android Client, BaseApplication
    Copyright (c) 2014 ShengDao Tech Company Limited
 */

package com.unkonw.testapp.libs.base;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.L;
import com.unkonw.testapp.libs.common.ActivityPageManager;

import solid.ren.skinlibrary.base.SkinBaseApplication;


/**
 * 基础的Application
 **/
public class BaseApplication extends SkinBaseApplication {
    private final String tag = BaseApplication.class.getSimpleName();
    private static DisplayImageOptions options;
    private static BaseApplication instance;
    public static Context myContext;
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

//        LogUtil.setDebug(!isCompleteProject);
//        LogUtil.e(tag, "isDebug: " + !isCompleteProject);
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

        options =new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .resetViewBeforeLoading(true)
                .considerExifParams(true)
                .build();

        //初始化图片下载组件
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheSize(100 * 1024 * 1024)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .memoryCache(new LruMemoryCache(mCacheSize))
                .imageDownloader(new AuthImageDownloader(this))
                .defaultDisplayImageOptions(options)

                .build();
        ImageLoader.getInstance().init(config);
        imageLoader = ImageLoader.getInstance();
        //关闭 打开log  imgelog
        L.writeLogs(false);

    }

    /**
     * 退出应用
     */
    public void exit() {
        ActivityPageManager.getInstance().exit(this);
    }




}
