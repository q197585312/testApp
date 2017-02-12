package com.nanyang.app.main.home.sport;

import android.os.Bundle;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;


public class SportActivity extends BaseToolbarActivity<Presenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);
    }


}
