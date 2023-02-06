package nanyang.com.dig88.load;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cn.finalteam.toolsfinal.StringUtils;
import nanyang.com.dig88.Activity.MainTabActivity;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.IpBean;
import nanyang.com.dig88.Entity.UpdateAppInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.MCPTool;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.ScreenUtil;
import xs.com.mylibrary.allinone.util.SharePreferenceUtil;
import xs.com.mylibrary.allinone.util.UpdateManager;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by ASUS on 2019/3/7.
 */

public class WelcomePresenter extends BaseRetrofitPresenter<WelcomeActivity> {
    private int version = Build.VERSION.SDK_INT;
    private Timer timer = new Timer();
    private TimerTask task;

    public WelcomePresenter(WelcomeActivity baseActivity) {
        super(baseActivity);
    }

    public void handleAffiliateId() {
        baseContext.getApp().setAffiliateId("");
        if (!StringUtils.isEmpty(BuildConfig.AFFILIATE_PACKAGE)) {
            String packagePath = getPackagePath(baseContext);
            File file = new File(packagePath);
            String s = readApk(file);
            if (!TextUtils.isEmpty(s)) {
                String[] split = s.split("-");
                String affiliateId = split[0];
                String webId = split[1];
                WebSiteUrl.WebId = webId;
                baseContext.getApp().setAffiliateId(affiliateId);
            }
        }
    }

    public void handleNewVersion() {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("lang", "cn");
        doRetrofitApiOnUiThread(getService(ApiService.class).checkVersion(WebSiteUrl.Dig88VersionSubmitter, p), new BaseConsumer<UpdateAppInfoBean>(baseContext) {
            @Override
            protected void onBaseGetData(UpdateAppInfoBean dataBean) throws JSONException, Exception {
                UpdateAppInfoBean.DataBean data = dataBean.getData();
                float serverVersion = Float.valueOf(data.getVersionAndroid());
                PackageInfo packageInfo = AppTool.getApkInfo(baseContext.getBaseActivity());
                float localVersion = Float.valueOf(packageInfo.versionName);
                if (data.getUrlAndroid() != null && AppTool.getApkInfo(baseContext.getBaseActivity()) != null && serverVersion > localVersion) {
                    if (version >= 23) {
                        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                        StrictMode.setVmPolicy(builder.build());
                        int REQUEST_EXTERNAL_STORAGE = 1;
                        String[] PERMISSIONS_STORAGE = {
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        };
                        int permission = ActivityCompat.checkSelfPermission(baseContext.getBaseActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

                        if (permission != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(
                                    baseContext.getBaseActivity(),
                                    PERMISSIONS_STORAGE,
                                    REQUEST_EXTERNAL_STORAGE
                            );
                        }
                    }
                    updateNewVersion(localVersion, serverVersion);
                } else {
                    goMain();
                }

            }

            @Override
            protected void onError(Throwable throwable) {
                super.onError(throwable);
            }
        });

    }

    private void goMain() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AppTool.activiyJump(baseContext, MainTabActivity.class);
                baseContext.finish();
            }
        }, 4000);
    }

    public void getNetIp() {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.getIpUrl), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String obj) throws JSONException {
                SharePreferenceUtil.setValue(baseContext.getBaseActivity(), "IP", "125.84.184.217");
                if (obj != null && obj.contains("cip") && obj.contains("cid") && obj.contains("cname")) {
                    int start = obj.indexOf("{");
                    int end = obj.indexOf("}") + 1;
                    if (end > start) {
                        String ip = obj.substring(start, end);
                        Gson gson = new Gson();
                        IpBean ipBean = gson.fromJson(ip, IpBean.class);
                        SharePreferenceUtil.setValue(baseContext.getBaseActivity(), "IP", ipBean.getCip());
                    }
                    Log.d("onBaseGetData", "onBaseGetData: " + obj);
                }
            }
        });
    }

    private void updateNewVersion(float localVersion, float serverVersion) {
        UpdateManager updateManager = new UpdateManager(baseContext);
        updateManager.setApkUrl(WebSiteUrl.DownLoadAppUrl);
        updateManager.setCancel(baseContext.getString(R.string.cancel));
        updateManager.setTitle(baseContext.getString(R.string.version_update));
        updateManager.setUpdate(baseContext.getString(R.string.update));
        updateManager.setUpdateMsg(baseContext.getString(R.string.update_msg) +
                "(" + baseContext.getString(R.string.current_version) + ":" + localVersion + "," +
                baseContext.getString(R.string.new_version) + ":" + serverVersion + ")");
        updateManager.setLoadTitle(baseContext.getString(R.string.app_update));
        updateManager.setLater(baseContext.getString(R.string.later));
        String affiliateId = baseContext.getApp().getAffiliateId();
        if (!TextUtils.isEmpty(affiliateId)) {
            //http://down-hk01-cn2.k-api.com:8080/api/az/getApk?comment=51003-2&fileName=android_afbcash_release.apk&packgeName=afbcash
            String downloadUrl = "http://down-hk01-cn2.k-api.com:8080/api/az/getApk?comment=" + affiliateId + "-" + WebSiteUrl.WebId;
            downloadUrl += "&fileName=android_" + BuildConfig.AFFILIATE_PACKAGE + "_release.apk" + "&packgeName=" + BuildConfig.AFFILIATE_PACKAGE;
            updateManager.checkUpdate(downloadUrl);
        } else {
            updateManager.checkUpdate(WebSiteUrl.DownLoadAppUrl);
        }

    }

    public String getPackagePath(Context context) {
        if (context != null) {
            return context.getPackageCodePath();
        }
        return null;
    }

    public String readApk(File file) {
        byte[] bytes = null;
        try {
            RandomAccessFile accessFile = new RandomAccessFile(file, "r");
            long index = accessFile.length();

            bytes = new byte[2];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            int contentLength = MCPTool.stream2Short(bytes, 0);

            bytes = new byte[contentLength];
            index = index - bytes.length;
            accessFile.seek(index);
            accessFile.readFully(bytes);

            return new String(bytes, "utf-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void initViewPager(ViewGroup mViewPagerContainer, ViewPager mViewPager, int[] drawableIds) {

        mViewPager.setClipChildren(false);
        mViewPagerContainer.setClipChildren(false);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                ScreenUtil.getScreenWidthPix(baseContext) * 7 / 10,
                ScreenUtil.getScreenHeightPix(baseContext) * 7 / 10);

        mViewPager.setLayoutParams(params);
        //为ViewPager设置PagerAdapter
        MyPagerAdapter myPagerAdapter = new MyPagerAdapter(drawableIds, baseContext);

        mViewPager.setAdapter(myPagerAdapter);
        mViewPager.setPageTransformer(true, new RotationPageTransformer());
        mViewPager.setOffscreenPageLimit(2);//设置预加载的数量，这里设置了2,会预加载中心item左边两个Item和右边两个Item
        //设置每页之间的左右间隔
        mViewPager.setPageMargin(0);
        startTask(mViewPager);
    }

    private void startTask(final ViewPager mViewPager) {
        if (task == null) {
            task = new TimerTask() {

                @Override
                public void run() {
                    baseContext.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mViewPager.setCurrentItem((mViewPager.getCurrentItem() + 1) % 3);
                        }
                    });
                }
            };
            timer.schedule(task, 2000, 2000);
        }
    }
}

