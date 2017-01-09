package com.unkonw.testapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        } else if ( mCurrentOrientation == Configuration.ORIENTATION_LANDSCAPE ) {
            //If current screen is landscape



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
