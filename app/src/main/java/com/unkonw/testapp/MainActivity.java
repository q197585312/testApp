package com.unkonw.testapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.orhanobut.logger.Logger;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Logger.d("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserDate userDate = (UserDate) getLastCustomNonConfigurationInstance();
        if(userDate!=null)
            Logger.i(userDate.toString());
        else{
            Logger.i("userDate == null");
        }
    }
    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        UserDate userDate = new UserDate();
        Logger.i(userDate.toString());
        return userDate;
    }
    @Override

    public void onConfigurationChanged(Configuration newConfig) {
        int mCurrentOrientation = getResources().getConfiguration().orientation;
        if ( mCurrentOrientation == Configuration.ORIENTATION_PORTRAIT ) {
            // If current screen is portrait
            setContentView(R.layout.mainP);
            //注意，这里删除了init()，否则又初始化了，状态就丢失
            findViews();
            setListensers();

        } else if ( mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE ) {
            //If current screen is landscape
            setContentView(R.layout.mainL);
            //注意，这里删除了init()，否则又初始化了，状态就丢失
            findViews();
            setListensers();

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logger.d("onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
         Logger.d("onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
         Logger.d("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
         Logger.d("onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
         Logger.d("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
         Logger.d("onDestroy");
    }



}
