package com.unkonw.testapp.training;

import android.content.Context;
import android.content.res.Configuration;

/**
 * Created by Administrator on 2017/1/11 0011.
 */

public class ConfigurationManager {
    public ConfigurationManager(Context context) {
        this.context = context;
    }

    Context context;

   public android.content.res.Configuration getConfiguration(){
       return context.getResources().getConfiguration();
   }
    public void getInfo(){
        Configuration configuration=getConfiguration();
        int countryCode=configuration.mcc;//国家代码
//获取网络码
        int networkCode=configuration.mnc;
//判断横竖屏
        if(configuration.orientation== Configuration.ORIENTATION_PORTRAIT){

        } else {

        }
    }
}
