package nanyang.com.dig88.Util;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import com.google.gson.Gson;

import butterknife.OnClick;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Activity.ActivityFragmentShow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Entity.TransferScrDataBean;
import nanyang.com.dig88.Fragment.TransferAccFragment;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/4/25 0025.
 */

public class Pop918Kiss extends BasePopupWindow {

    BaseActivity activity;

    public Pop918Kiss(Context context, View v, int width, int height) {
        super(context, v, width, height);
        activity = (BaseActivity) context;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_918kiss;
    }

    @OnClick({R.id.kiss918_download, R.id.kiss918_transferacc, R.id.kiss918_playnow})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.kiss918_download:
                String downloadUrl = "http://tm.d.918kiss.com/";
                openUrl(downloadUrl);
                break;
            case R.id.kiss918_transferacc:
                Intent intent = new Intent(context, ActivityFragmentShow.class);
                intent.putExtra("type", TransferAccFragment.class.getName());
                context.startActivity(intent);
                break;
            case R.id.kiss918_playnow:
                if (ApkUtils.isAvilible(context, "org.scr888.scr888")) {
                    activity.showBlockDialog();
                    String scrUrl = "http://app.info.dig88api.com/index.php?page=get_scr888_balance_submitter";//余额接口
                    String requestUrl = scrUrl + "&web_id=" + WebSiteUrl.WebId + "&id_user=" + activity.getUserInfoBean().getUser_id()
                            + "&session_id=" + activity.getUserInfoBean().getSession_id() + "&scr888=1";
                    HttpUtils.httpGet(requestUrl, new HttpUtils.RequestCallBack() {
                        @SuppressLint("WrongConstant")
                        @Override
                        public void onRequestSucceed(String s) {
                            activity.dismissBlockDialog();
                            Gson gson = new Gson();
                            TransferScrDataBean bean = gson.fromJson(s, TransferScrDataBean.class);
                            if (ApkUtils.isAvilible(context, "org.scr888.scr888")) {
                                Intent i = new Intent(Intent.ACTION_MAIN);
                                i.setComponent(new ComponentName("org.scr888.scr888", "org.cocos2dx.lua.AppActivity"));
                                i.setFlags(101);
                                //{"code":"0","msg":"1","data":{"scr888_id":"01969498442","scr888_pwd":"6673PKvy","scr888_balance":"0"}}
                                i.putExtra("account", bean.getData().getScr888_id());
                                i.putExtra("password", bean.getData().getScr888_pwd());
                                context.startActivity(i);
                            }
                        }

                        @Override
                        public void onRequestFailed(String s) {

                        }
                    });
                }
                break;
        }
        closePopupWindow();
    }

    public void openUrl(String gameUrl) {
        if (gameUrl.startsWith("http")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri content_url = Uri.parse(gameUrl);
            intent.setData(content_url);
            context.startActivity(intent);
        }
    }

}
