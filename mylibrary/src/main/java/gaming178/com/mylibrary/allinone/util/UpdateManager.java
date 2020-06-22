package gaming178.com.mylibrary.allinone.util;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import gaming178.com.mylibrary.R;


/**
 * @ProjectName: [LNH_BASIC]
 * @Package: [com.lnh.util.UpdateManager.java]
 * @ClassName: [UpdateManager]
 * @Description: [版本更新工具类]
 * @Author: lnh-xiaohong
 * @CreateDate: [2015-7-23 下午3:59:04]
 * @UpdateUser: [Administrator]
 * @UpdateDate: [2015-7-23 下午3:59:04]
 * @UpdateRemark: [说明本次修改内容]
 * @Version: [v1.0]
 */
public class UpdateManager {
    private Context mContext;

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel;
    }

    public String getLater() {
        return later;
    }

    public void setLater(String later) {
        this.later = later;
    }

    public String getLoadTitle() {
        return loadTitle;
    }

    public void setLoadTitle(String loadTitle) {
        this.loadTitle = loadTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

    private String updateMsg = "亲，有新版本，快下载吧！"; // 下载消息提示
    private String title = "版本更新";
    private String loadTitle = "软件更新";
    private String update = "更新";
    private String later = "以后再说";
    private String cancel = "取消";
    private Dialog noticeDialog; // 下载提示对话框
    private Dialog downloadDialog; // 下载进度对话框
    private ProgressBar mProgressBar; // 进度条
    private Boolean interceptFlag = false; // 标记用户是否在下载过程中取消下载
    private Thread downloadApkThread = null; // 下载线程
    private String apkUrl = "https://update.apps.khmergaming.net/androidDig.apk"; // apk的URL地址

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    @SuppressLint("SdCardPath")
    private final String savePath = "/sdcard/dig/"; // 下载的apk存放的路径
    private final String saveFileName = savePath + "dig.apk"; // 下载的apk文件
    private int progress = 0; // 下载进度
    private final int DOWNLOAD_ING = 1; // 标记正在下载
    private final int DOWNLOAD_OVER = 2; // 标记下载完成
    @SuppressLint("HandlerLeak")
    private Handler mhandler = new Handler() { // 更新UI的handler

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case DOWNLOAD_ING:
                    // 更新进度条
                    mProgressBar.setProgress(progress);
                    break;
                case DOWNLOAD_OVER:
                    downloadDialog.dismiss();
                    installApk();
                    // 安装
                    break;
                default:
                    break;
            }
        }
    };

    /*
     * 构造方法
     */
    public UpdateManager(Context context) {
        this.mContext = context;
    }

    /*
     * 检查是否有需要更新，具体比较版本xml
     */
    public void checkUpdate(String url) {
        // 到服务器检查软件是否有新版本
        // 如果有则
        this.apkUrl = url;
        showNoticeDialog();
    }

    /*
     * 显示版本更新对话框
     */
    private void showNoticeDialog() {
        Builder builder = new Builder(mContext, R.style.Base_Theme_AppCompat_Dialog_Alert);
        builder.setTitle(title);
        builder.setMessage(updateMsg);
        builder.setPositiveButton(update, new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                noticeDialog.dismiss();
                showDownloadDialog();
            }
        });
        if (cancel != null && !cancel.equals(""))
            builder.setNegativeButton(later, new OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    noticeDialog.dismiss();
                    checkCancel();
                }
            });
        noticeDialog = builder.create();
        noticeDialog.setCanceledOnTouchOutside(false);
        noticeDialog.show();
    }

    public void checkCancel() {


    }

    /*
     * 弹出下载进度对话框
     */
    private void showDownloadDialog() {
        Builder builder = new Builder(mContext, R.style.Base_Theme_AppCompat_Dialog_Alert);
        builder.setTitle(loadTitle);
        final LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.download_progress_layout, null);
        mProgressBar = (ProgressBar) v.findViewById(R.id.updateProgress);
        builder.setView(v);
        if (cancel != null && !cancel.equals("")) {
            builder.setNegativeButton(cancel, new OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    downloadDialog.dismiss();
                    interceptFlag = true;
                }
            });
        }
        downloadDialog = builder.create();
        downloadDialog.show();
        downloadDialog.setCancelable(false);
        downloadLatestVersionApk();
    }

    /*
     * 下载最新的apk文件
     */
    private void downloadLatestVersionApk() {
        downloadApkThread = new Thread(downloadApkRunnable);
        downloadApkThread.start();
    }

    // 匿名内部类，apk文件下载线程
    private Runnable downloadApkRunnable = new Runnable() {

        public void run() {
            try {
                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.connect();
                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();
                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                File apkFile = new File(saveFileName);
                FileOutputStream out = new FileOutputStream(apkFile);
                int count = 0;
                int readnum = 0;
                byte[] buffer = new byte[1024];
                do {
                    readnum = is.read(buffer);
                    count += readnum;
                    progress = (int) (((float) count / length) * 100);
                    mhandler.sendEmptyMessage(DOWNLOAD_ING);
                    if (readnum <= 0) {
                        // 下载结束
                        mhandler.sendEmptyMessage(DOWNLOAD_OVER);
                        break;
                    }
                    out.write(buffer, 0, readnum);
                } while (!interceptFlag);
                is.close();
                out.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                downloadDialog.dismiss();
            } catch (IOException e) {
                e.printStackTrace();
                downloadDialog.dismiss();
            }
        }
    };

    /*
     * 安装下载的apk文件
     */
    private void installApk() {
        File file = new File(saveFileName);
        if (!file.exists() || iEnd == null) {
            return;
        }
    /*	Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		mContext.startActivity(intent);*/
        iEnd.onLoadEnd(file);
    }

    public void setOnLoadEnd(ILoad iEnd) {
        this.iEnd = iEnd;
    }

    protected ILoad iEnd;

    public interface ILoad {
        void onLoadEnd(File file);
    }
}