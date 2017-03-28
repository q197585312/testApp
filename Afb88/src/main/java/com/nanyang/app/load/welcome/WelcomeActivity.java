package com.nanyang.app.load.welcome;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.nanyang.app.R;
import com.nanyang.app.load.login.LoginActivity;
import com.unkonw.testapp.libs.base.BaseActivity;
import com.unkonw.testapp.libs.utils.SystemTool;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.io.File;


public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View {


    private Dialog noticeDialog;
    private ProgressBar mProgressBar;
    private AlertDialog downloadDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        createPresenter(new WelcomePresenter(this));
        try {
            presenter.checkVersion(SystemTool.getPackageInfo(this).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            skipAct(LoginActivity.class);
        }

    }

    @Override
    public void onFailed(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onLoadingApk(int len, long contentLength) {
        mProgressBar.setProgress((int) (len * 100 / contentLength));
    }

    @Override
    public void onLoadError(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onLoadEnd(File file) {
        SystemTool.installApk(mContext, file);
    }


    @Override
    public void onGetData(String data) {
        try {
            if (Float.valueOf(data) > Float.valueOf(SystemTool.getPackageInfo(mContext).versionName)) {
                showUpdateDialog();
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        skipAct(LoginActivity.class);
        finish();

    }

    private void showUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_Theme_AppCompat_Dialog);
        builder.setTitle(R.string.Update);
        builder.setMessage(R.string.download_now);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                noticeDialog.dismiss();
                showDownloadDialog();
            }
        });

        noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.show();
    }

    private void showDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_Theme_AppCompat_Dialog);
        builder.setTitle(R.string.Loading);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.download_progress_layout, null);
        mProgressBar = (ProgressBar) v.findViewById(R.id.updateProgress);
        builder.setView(v);

        downloadDialog = builder.create();
        downloadDialog.setCanceledOnTouchOutside(false);
        downloadDialog.show();
        presenter.updateVersion();
    }

}
