package nanyang.com.dig88.Table;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;

import nanyang.com.dig88.Activity.BaseActivity;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2015/12/22.
 */
public abstract class GameBaseActivity extends BaseActivity {

//    @Override
//    protected void onResume() {
//        super.onResume();
//        updateDigitalGamesMoney();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        removeDigitalMoneyUpdate();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        removeDigitalMoneyUpdate();
//    }

    protected void updateUserInfoEnd() {

    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                updateUserInfoEnd();
            }
        },2000);
        showMoney();
    }
}
