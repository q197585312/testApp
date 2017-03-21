package com.nanyang.app.main.center;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.nanyang.app.AfbUtils;
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
    private BaseFragment statementFragment = new StatementFragment();
    private BaseFragment changePasswordFragment = new ChangePasswordFragment();
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
        fragments.put(getString(R.string.statement), statementFragment);
        fragments.put(getString(R.string.change_password), changePasswordFragment);
        currentTag = getCurrentTag();
        showFragmentToActivity(fragments.get(currentTag), R.id.framelayout_person, currentTag);
    }

    private String getCurrentTag() {
        return getIntent().getStringExtra("personCenter");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            String imgUri = data.getData().toString();
            String trueImgUrl = null;
            for (int i = 0; i < imgUri.length(); i++) {
                String firstStr = imgUri.charAt(i) + "";
                String nextStr = imgUri.charAt(i + 1) + "";
                if (firstStr.endsWith("/") && nextStr.equals("s")) {
                    trueImgUrl = imgUri.substring(i);
                    break;
                }
            }
            avatarFragment.headBitmap = AfbUtils.toRoundBitmap(BitmapFactory.decodeFile(trueImgUrl));
            avatarFragment.getHeadImg().setImageBitmap(avatarFragment.headBitmap);
        }
    }
}
