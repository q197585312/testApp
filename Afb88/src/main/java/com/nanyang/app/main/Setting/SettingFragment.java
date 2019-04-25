package com.nanyang.app.main.Setting;

import android.support.v7.widget.RecyclerView;

import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.R;
import com.nanyang.app.main.BaseMoreFragment;

import butterknife.Bind;

/**
 * Created by Administrator on 2019/4/25.
 */

public class SettingFragment extends BaseMoreFragment {
    @Bind(R.id.person_center_view)
    RecyclerView rcContent;
    BaseToolbarActivity aty;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initData() {
        super.initData();
        setBackTitle(getString(R.string.setting));
        aty = (BaseToolbarActivity) getActivity();
    }
}
