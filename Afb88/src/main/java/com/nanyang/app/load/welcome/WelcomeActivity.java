package com.nanyang.app.load.welcome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.nanyang.app.AppConstant;
import com.nanyang.app.Been.CheckVersionBean;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.utils.SystemTool;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.File;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> {
    private Dialog noticeDialog;
    private ProgressBar mProgressBar;
    private AlertDialog downloadDialog;
    private long totalLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        SystemTool.installApk(mContext, file);
    }

    public void onGetData(CheckVersionBean checkVersionBean) {
        String version = checkVersionBean.getData().getVersion();
        String url = checkVersionBean.getData().getUrl();
        try {
            if (Float.valueOf(version) > Float.valueOf(SystemTool.getPackageInfo(mContext).versionName)) {
                showUpdateDialog(url);
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        presenter.checkInitCheck(getIntent());
    }

    private void showUpdateDialog(final String url) {
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

    public void defaultSkip(String type) {
        MenuItemInfo<String> menuItemInfo = new MenuItemInfo<>(0, getString(R.string.Today));
        menuItemInfo.setType("Today");
        menuItemInfo.setParent(type);
        Bundle b = new Bundle();
        b.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
        skipAct(SportActivity.class, b);
    }
}
