package com.nanyang.app.load.welcome;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.Been.CheckVersionBean;
import com.nanyang.app.BuildConfig;
import com.nanyang.app.R;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.load.login.LoginActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.SystemTool;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.File;


public class WelcomeActivity extends BaseToolbarActivity<WelcomePresenter> {
    private Dialog noticeDialog;
    private ProgressBar mProgressBar;
    private AlertDialog downloadDialog;
    private long totalLength;
    private File loadFile;
    private String downloadUrl = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        setContentView(R.layout.activity_welcome);
        createPresenter(new WelcomePresenter(this));
        try {
            presenter.checkVersion(new BaseConsumer<CheckVersionBean>(this) {
                @Override
                protected void onBaseGetData(CheckVersionBean checkVersionBean) {
                    onGetData(checkVersionBean);
                }

                @Override
                protected void onError(Throwable throwable) {
                    ((BaseActivity) getBaseActivity()).skipAct(LoginActivity.class);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            ((BaseActivity) getBaseActivity()).skipAct(LoginActivity.class);
        }
    }

    public void onLoadingApk(final int len, final long contentLength) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totalLength += len;
                int progress = (int) (totalLength * 100 / contentLength);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar.setProgress(progress);
            }
        });
    }

    public void onLoadError(final String error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showShort(error);
            }
        });

    }

    public void onLoadEnd(File file) {
        downloadDialog.dismiss();
        this.loadFile = file;
        checkIsAndroidO();
    }
    @Override
    public void startUpdateState() {

    }

    public void checkIsAndroidO() {
        if (Build.VERSION.SDK_INT >= 26) {
            boolean b = getPackageManager().canRequestPackageInstalls();
            if (b) {
                SystemTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
            } else {
                //请求安装未知应用来源的权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, INSTALL_CODE);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},
                        INSTALL_CODE);

            }
        } else {
            SystemTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
        }
    }

    final int READ_CODE = 101;
    final int INSTALL_CODE = 102;
    final int INSTALL_AFB_CODE = 109;

    public void onGetData(CheckVersionBean checkVersionBean) {
        String version = checkVersionBean.getData().getVersion();
        String url = checkVersionBean.getData().getUrl();
        try {
            if (Float.valueOf(version) > Float.valueOf(SystemTool.getPackageInfo(mContext).versionName)) {
                String[] PERMISSIONS_STORAGE = {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE

                };
                this.downloadUrl = url;
                if (requestPermission(PERMISSIONS_STORAGE, READ_CODE)) {
                    showUpdateDialog();
                }
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        presenter.checkInitCheck(getIntent());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.d("onRequestPermissionsResult","权限code："+requestCode);

        switch (requestCode) {
            case READ_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showUpdateDialog();
                } else {
                    ToastUtils.showLong(getString(R.string.no_permission));
                    //  引导用户手动开启安装权限
                }
                break;
            case INSTALL_CODE:
                SystemTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SystemTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                } else {
                    ToastUtils.showLong(getString(R.string.open_install));
                    //  引导用户手动开启安装权限
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                    startActivityForResult(intent, INSTALL_CODE);
                    Uri packageURI = Uri.parse("package:com.nanyang.app");//设置包名，可直接跳转当前软件的设置页面
                    Intent ii = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    startActivityForResult(ii, INSTALL_AFB_CODE);
                }
                break;
            default:
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case INSTALL_AFB_CODE:
                SystemTool.installApk(mContext, loadFile, BuildConfig.APPLICATION_ID);
                break;
        }
    }

    private void showUpdateDialog() {
        if (StringUtils.isNull(downloadUrl))
            return;
        final String url = downloadUrl;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_AlertDialog);
        builder.setTitle(R.string.Update);
        builder.setMessage(R.string.download_now);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                noticeDialog.dismiss();
                showDownloadDialog(url);
            }
        });

        noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.show();
    }

    private void showDownloadDialog(String url) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_AlertDialog);
        builder.setTitle(R.string.Loading);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.download_progress_layout, null);
        mProgressBar = v.findViewById(R.id.updateProgress);
        builder.setView(v);
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();
        presenter.updateVersion(url);
        totalLength = 0;
    }


    public void onLanguageSwitchSucceed(String str) {
        //测试哈提交
        Log.d("doRetrofitApiOnUiThread", "doRetrofitApiOnUiThread: " + AppConstant.wfMain);
        ToastUtils.showShort(getString(R.string.Login_Success));

        defaultSkip("SportBook");
        finish();
    }

 /*   public void defaultSkip(String type) {
        MenuItemInfo<String> menuItemInfo = new MenuItemInfo<>(0, getString(R.string.Today));
        menuItemInfo.setType("Today");
        menuItemInfo.setParent(type);
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
        skipAct(SportActivity.class, b);
    }*/
}
