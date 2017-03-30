package com.nanyang.app.main.center;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.MainActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalCenterFragment extends BaseFragment {


    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.iv_head)
    ImageView headImg;
    @Bind(R.id.tv_logout)
    TextView tv_logout;
    @Bind(R.id.tv_user_name)
    TextView tvUserName;
    @Bind(R.id.tv_balance)
    TextView tvBalance;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    public void initView() {
        super.initView();
        initHead();
        tvBalance.setText(((AfbApplication) mContext.getApplication()).getUser().getBalance());
        tvUserName.setText(((AfbApplication) mContext.getApplication()).getUser().getUserName());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(mLayoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.center_info_head, getString(R.string.Edit_information)));
//        dataList.add(new MenuItemInfo(R.mipmap.center_info_recharge, getString(R.string.Recharge)));
//        dataList.add(new MenuItemInfo(R.mipmap.center_info_withdrawals, getString(R.string.Withdrawals)));
//        dataList.add(new MenuItemInfo(R.mipmap.center_info_order, getString(R.string.My_order)));
//        dataList.add(new MenuItemInfo(R.mipmap.center_info_history, getString(R.string.History_record)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_modify, getString(R.string.change_password)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_language, getString(R.string.choice_language)));
        dataList.add(new MenuItemInfo(R.mipmap.center_info_transfer, getString(R.string.transfer)));
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, dataList, R.layout.center_item_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.tv_text);
                tv.setText(item.getText());
                tv.setCompoundDrawablesWithIntrinsicBounds(item.getRes(), 0, R.mipmap.arrow_right_drop_white, 0);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {

                if (item.getText().equals(getString(R.string.change_password))) {
                    Intent i = getStartIntent(getString(R.string.change_password));
                    i.setClass(mContext, PersonCenterActivity.class);
                    startActivity(i);
                } else if (item.getText().equals(getString(R.string.choice_language))) {
                    Intent i = getStartIntent(getString(R.string.choice_language));
                    i.setClass(mContext, PersonCenterActivity.class);
                    startActivity(i);
                } else if (item.getText().equals(getString(R.string.Edit_information))) {
                    Intent i = getStartIntent(getString(R.string.Modify_Avatar));
                    MainActivity a = (MainActivity) getActivity();
                    i.setClass(a, PersonCenterActivity.class);
                    a.startActivityForResult(i, 0);
                } else if (item.getText().equals(getString(R.string.transfer))) {
                    Intent i = getStartIntent(getString(R.string.transfer));
                    i.setClass(mContext, PersonCenterActivity.class);
                    startActivity(i);
                }
            }
        });
        rvContent.setAdapter(adapter);
    }

    @OnClick({R.id.iv_head, R.id.tv_logout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_head:
                Intent i = getStartIntent(getString(R.string.Modify_Avatar));
                MainActivity a = (MainActivity) getActivity();
                i.setClass(a, PersonCenterActivity.class);
                a.startActivityForResult(i, 0);
                break;
            case R.id.tv_logout:

                break;
        }

    }

    @Override
    public void initHead() {
        super.initHead();
        Bitmap b = BitmapFactory.decodeFile(AfbUtils.nativePath + AfbUtils.headImgName);
        if (b != null) {
            headImg.setImageBitmap(b);
        }
    }

    private Intent getStartIntent(String type) {
        Intent i = new Intent();
        i.putExtra("personCenter", type);
        return i;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
