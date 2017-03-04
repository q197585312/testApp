package com.nanyang.app.main.center;

import android.os.Bundle;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PersonCenterActivity extends BaseToolbarActivity {
    private BaseFragment avatarFragment = new FragmetnAvatar();
    private Map<String, BaseFragment> fragments;
    private String currentTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_center);
    }

    @Override
    public void initData() {
        super.initData();
        fragments = new HashMap<>();
        fragments.put(getString(R.string.Modify_Avatar), avatarFragment);
        currentTag = getCurrentTag();
        showFragmentToActivity(fragments.get(currentTag), R.id.framelayout_person, currentTag);
    }

    private String getCurrentTag() {
        return getIntent().getStringExtra("personCenter");
    }

}
