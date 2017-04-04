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
    private long totleLength;

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
    public void onLoadingApk(final int len, final long contentLength) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                totleLength+=len;
                Log.d("LOAD", "contentLength:" + contentLength + "\n" +
                        "length:" + totleLength);
                mProgressBar.setProgress((int) (totleLength * 100 / contentLength));
            }
        });

    }

    @Override
    public void onLoadError(String error) {
        ToastUtils.showShort(error);
    }

    @Override
    public void onLoadEnd(File file) {
        downloadDialog.dismiss();
        SystemTool.installApk(mContext, file);
    }


    @Override
    public void onGetData(String data) {
        try {
            if (Float.valueOf(data) > Float.valueOf(SystemTool.getPackageInfo(mContext).versionName)) {
                showUpdateDialog(data);
                return;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        skipAct(LoginActivity.class);
        finish();

    }

    private void showUpdateDialog(final String version) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_AlertDialog);
        builder.setTitle(R.string.Update);
        builder.setMessage(R.string.download_now);
        builder.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                noticeDialog.dismiss();
                showDownloadDialog(version);
            }
        });

        noticeDialog = builder.create();
        noticeDialog.setCancelable(false);
        noticeDialog.show();
    }

    private void showDownloadDialog(String version) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext, R.style.Base_AlertDialog);
        builder.setTitle(R.string.Loading);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.download_progress_layout, null);
        mProgressBar = (ProgressBar) v.findViewById(R.id.updateProgress);
        builder.setView(v);
        downloadDialog = builder.create();
        downloadDialog.setCancelable(false);
        downloadDialog.show();
        presenter.updateVersion(version);
        totleLength=0;
    }

}
