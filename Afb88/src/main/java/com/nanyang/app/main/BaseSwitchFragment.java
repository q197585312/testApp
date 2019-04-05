package com.nanyang.app.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class BaseSwitchFragment extends BaseFragment<BaseSwitchPresenter> {
    ImageView imgBack;
    LinearLayout llBackTitle;
    MainActivity mainActivity;
    TextView title;

    @Override
    public void initData() {
        super.initData();
        createPresenter(new BaseSwitchPresenter(this));
        mainActivity = (MainActivity) getBaseActivity();
    }

    @Override
    public void initView() {
        super.initView();
        llBackTitle = mContentView.findViewById(R.id.ll_back_title);
        imgBack = mContentView.findViewById(R.id.img_back);
        title = mContentView.findViewById(R.id.title);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    public void setToolbarVisibility(boolean b) {
        mainActivity.setToolbarVisibility(b);
    }

    public void setBackTitleVisibility(boolean b) {
        if (llBackTitle == null) {
            return;
        }
        if (b) {
            llBackTitle.setVisibility(View.VISIBLE);
        } else {
            llBackTitle.setVisibility(View.GONE);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            setCurrentFragmentTitle();
        }
    }

    public void setTitle(String title){
        this.title.setText(title);
    }

    public void setCurrentFragmentTitle() {
        setToolbarVisibility(false);
        setBackTitleVisibility(true);
    }

    public void back() {
        mainActivity.switchFragment(mainActivity.homeFragment);
    }

    public String switchType = "";

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public void showContent() {

    }
}
