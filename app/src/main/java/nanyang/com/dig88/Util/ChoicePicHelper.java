package nanyang.com.dig88.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.gson.Gson;

import gaming178.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.UploadBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.BaseFragment;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/6/12.
 */

public class ChoicePicHelper {
    private static BlockDialog blockDialog;

    private static void initDialog(Context context) {
        blockDialog = new BlockDialog(context, context.getString(R.string.zhengjiazai));
    }

    public static void goChoicePic(Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, 0);
    }

    public static void uploadPic(final BaseActivity activity, final BaseFragment baseFragment, final String url, final UserInfoBean userInfoBean) {
        initDialog(activity);
        blockDialog.show();
        baseFragment.isUploading = true;
        new Thread() {
            @Override
            public void run() {
                String strImg = ImageBase64.imageToBase64(url);
                String newS = strImg.replace("+", "%2B");
                VipInfoBean info = (VipInfoBean) AppTool.getObjectData(activity, "vipInfo");
                String p = "web_id=" + WebSiteUrl.WebId + "&user_id=" + userInfoBean.getUser_id() + "&session_id=" +
                        userInfoBean.getSession_id() + "&username=" + info.getUsername() + "&data_img=" + newS;
                HttpUtils.httpPost("http://app.info.dig88api.com/index.php?page=upload_img", p, new HttpUtils.RequestCallBack() {
                    @Override
                    public void onRequestSucceed(String s) {
                        if (s.contains("success")) {
                            UploadBean uploadBean = new Gson().fromJson(s, UploadBean.class);
                            baseFragment.setReceipt(uploadBean.getUrl());
                            Toast.makeText(activity, activity.getString(R.string.Upload_success), Toast.LENGTH_SHORT).show();
                        } else {
                            UploadBean uploadBean = new Gson().fromJson(s, UploadBean.class);
                            baseFragment.setReceipt("");
                            Toast.makeText(activity, uploadBean.getMsg(), Toast.LENGTH_SHORT).show();
                        }
                        blockDialog.dismiss();
                        baseFragment.isUploading = false;
                    }

                    @Override
                    public void onRequestFailed(String s) {
                        baseFragment.setReceipt("");
                        Toast.makeText(activity, activity.getString(R.string.Upload_failed), Toast.LENGTH_SHORT).show();
                        blockDialog.dismiss();
                        baseFragment.isUploading = false;
                    }
                });
            }
        }.start();
    }

}
