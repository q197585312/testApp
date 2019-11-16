package com.nanyang.app.common;

import android.content.ComponentName;
import android.content.Intent;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.load.PersonalInfo;
import com.unkonw.testapp.libs.utils.LogIntervalUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.view.IBaseView;

import cn.finalteam.toolsfinal.ApkUtils;
import io.reactivex.functions.Consumer;
import okhttp3.Request;
import retrofit2.Response;


/**
 * Created by Administrator on 2019/11/16.
 */

public class Gd88Consumer<T> implements Consumer<T> {
    IBaseView baseView;

    public Gd88Consumer(IBaseView baseView) {
        this.baseView = baseView;
    }

    @Override
    public void accept(T t) throws Exception {
        if (t instanceof String) {
            goTransfer(t);
        }else{
            goNoTransfer(t);
        }
    }

    private void goNoTransfer(T t) {
        LogIntervalUtils.logTime("请求数据完成开始解析---goNoTransfer");
        Response responseBodyResponse= (Response) t;
        okhttp3.Response response = responseBodyResponse.raw().priorResponse();
        if (response != null) {
    /*
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
                            String k = findUrlK(response);
//                            String k = url.substring(url.indexOf("k="));
                       if (!StringUtils.isNull(k))
                                baseView.onGetData(k);
                            else {
                                ToastUtils.showShort("not find k!");
                            }*/
            Request request = response.request();
            String url = request.url().toString();
            if (ApkUtils.isAvilible(baseView.getContextActivity(), "gaming178.com.baccaratgame")) {
                Intent intent = new Intent();
                ComponentName comp = new ComponentName("gaming178.com.baccaratgame", "gaming178.com.casinogame.Activity.WelcomeActivity");
                intent.setComponent(comp);
                PersonalInfo info = ((BaseToolbarActivity) baseView.getContextActivity()).getApp().getUser();
                intent.putExtra("username", info.getUserName());
                intent.putExtra("password", info.getPassword());
                intent.putExtra("language", "en");
                intent.putExtra("web_id", "-1");
                intent.putExtra("webUrl", url);
                intent.putExtra("gameType", 3);
                intent.putExtra("homeColor",((BaseToolbarActivity) baseView.getContextActivity()).getHomeColor());
                intent.putExtra("balance", info.getBalance());
                baseView.getContextActivity().startActivity(intent);
            } else {
                ((BaseToolbarActivity) baseView.getContextActivity()).downLoadGd88();
            }
        } else {
            ToastUtils.showShort("not find agent!");
        }
        baseView.hideLoadingDialog();

    }


    private void goTransfer(T t) {
        LogIntervalUtils.logTime("请求完成开始解析数据--goTransfer");
        String Str=t.toString();
        int start = Str.indexOf("TransferBalance");
        int end = Str.indexOf("\" id=\"form1\"");

        String k = "";
        if (start > 0 && end > 0 && end > start) {
//                          http://lapigd.afb333.com/Validate.aspx?us=demoafbai5&k=5a91f23cd1b34f4295ea0860d6cac325
            String url = Str.substring(start, end);
            k = url.substring(url.indexOf("k="));
            baseView.onGetData(k);
        } else if (Str.contains("Transaction not tally")) {
            ToastUtils.showShort("Transaction not tally");
        } else if (Str.contains("Session Expired")) {
            ToastUtils.showShort("Session Expired");
        } else if (Str.contains("Account is LOCKED")) {
            ToastUtils.showShort("Account is LOCKED! Please contact your agent!");
        } else {
            ToastUtils.showShort("Failed");
        }
    }
}

