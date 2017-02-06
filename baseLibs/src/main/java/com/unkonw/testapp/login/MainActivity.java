package com.unkonw.testapp.login;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.unkonw.testapp.R;

import butterknife.ButterKnife;
import cn.finalteam.toolsfinal.logger.Logger;


public class MainActivity extends AppCompatActivity {

//    @Bind(R.id.activity_main)
    RelativeLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        UserDate userDate = (UserDate) getLastCustomNonConfigurationInstance();
        if (userDate != null)
            Logger.getDefaultLogger().d(userDate.toString());
        else {
            Logger.getDefaultLogger().d("userDate == null");
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        UserDate userDate = new UserDate();
        Logger.getDefaultLogger().d(userDate.toString());
        return userDate;
    }

    @Override

    public void onConfigurationChanged(Configuration newConfig) {
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if (mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            // If current screen is portrait

        } else if (mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            //If current screen is landscape


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.getDefaultLogger().d("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.getDefaultLogger().d("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.getDefaultLogger().d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.getDefaultLogger().d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.getDefaultLogger().d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logger.getDefaultLogger().d("onDestroy");
    }


}
