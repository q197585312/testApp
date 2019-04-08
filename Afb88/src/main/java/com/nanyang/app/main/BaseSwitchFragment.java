package com.nanyang.app.main;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.base.BaseFragment;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class BaseSwitchFragment<P extends BaseSwitchPresenter> extends BaseFragment<P> {
    ImageView imgBack;
    LinearLayout llBackTitle;
    BaseToolbarActivity mainActivity;
    TextView tvBackTitle;

    @Override
    public void initData() {
        super.initData();
        mainActivity = (BaseToolbarActivity) getBaseActivity();
        setCurrentFragmentTitle();
    }

    @Override
    public void initView() {
        super.initView();
        llBackTitle = mContentView.findViewById(R.id.ll_back_title);
        imgBack = mContentView.findViewById(R.id.img_back);
        tvBackTitle = mContentView.findViewById(R.id.tv_title);
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

    public void setBackTitle(String title) {
        tvBackTitle.setText(title);
    }

    public void setCurrentFragmentTitle() {
        setToolbarVisibility(false);
        setBackTitleVisibility(true);
    }

    public void back() {
        mainActivity.switchFragment(mainActivity.getFirstShowFragment());
    }

    public String switchType = "";

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public void showContent() {

    }
}
