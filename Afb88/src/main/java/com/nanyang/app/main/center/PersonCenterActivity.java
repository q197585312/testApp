package com.nanyang.app.main.center;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.center.Stake.StakeFragment;
import com.nanyang.app.main.center.Statement.StatementFragment;
import com.nanyang.app.main.center.changeLanguage.ChangeLanguageFragment;
import com.nanyang.app.main.center.transferMoney.TransferMoneyFragment;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/4.
 */

public class PersonCenterActivity extends BaseToolbarActivity {
    private BaseFragment avatarFragment = new FragmentAvatar();
    private BaseFragment statementFragment = new StatementFragment();
    private BaseFragment changePasswordFragment = new ChangePasswordFragment();
    private BaseFragment stakeFragment = new StakeFragment();
    private BaseFragment changeLanguageFragment = new ChangeLanguageFragment();
    private BaseFragment transferMoneyFragment = new TransferMoneyFragment();
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
        fragments.put(getString(R.string.stake), stakeFragment);
        fragments.put(getString(R.string.choice_language), changeLanguageFragment);
        fragments.put(getString(R.string.transfer), transferMoneyFragment);
        currentTag = getCurrentTag();
        showFragmentToActivity(fragments.get(currentTag), R.id.framelayout_person, currentTag);
    }

    private String getCurrentTag() {
        return getIntent().getStringExtra("personCenter");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && data != null) {
            String imgUri = data.getData().toString();
            Bitmap b = ImageLoader.getInstance().loadImageSync(imgUri);
            if (b != null) {
                Bitmap circleBitmap = AfbUtils.toRoundBitmap(AfbUtils.compressImage(b));
                avatarFragment.headBitmap = circleBitmap;
                avatarFragment.getHeadImg().setImageBitmap(avatarFragment.headBitmap);
            }
        }
    }
}
