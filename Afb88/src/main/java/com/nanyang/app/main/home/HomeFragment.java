package com.nanyang.app.main.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.SportIdBean;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.StringUtils;
import com.nanyang.app.Utils.ViewPagerAdapter;
import com.nanyang.app.common.LanguageHelper;
import com.nanyang.app.load.login.LoginInfo;
import com.nanyang.app.load.welcome.AllBannerImagesBean;
import com.nanyang.app.main.BaseSwitchFragment;
import com.nanyang.app.main.MainActivity;
import com.nanyang.app.main.MainPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.TimeUtils;
import com.unkonw.testapp.libs.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Locale;

import butterknife.Bind;

public class HomeFragment extends BaseSwitchFragment {

    @Bind(R.id.auto_viewpager)
    AutoScrollViewPager viewPager;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.in_layout)
    LinearLayout inLayout;
    private JSONObject jsonObjectNum;
    private BaseRecyclerAdapter<AllBannerImagesBean.MainBannersBean> adapter;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_home;
    }


    @Override
    public void initData() {
        super.initData();
        initViewPager(((AfbApplication) getBaseActivity().getApplication()).getListMainBanners());
        initContent(((AfbApplication) getBaseActivity().getApplication()).getListMainPictures());
        String language = new LanguageHelper(mContext).getLanguage();
        ((MainActivity) getBaseActivity()).presenter.loadAllMainData(new LoginInfo.LanguageWfBean("Getmenu", language, "wfMainH50"), new MainPresenter.CallBack<String>() {

            @Override
            public void onBack(String data) {
                LogUtil.d("onBack", data);
                try {
                    jsonObjectNum = new JSONObject(data);
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }


    private void initViewPager(List<AllBannerImagesBean.BannersBean> lists) {
        if (lists == null)
            return;
        ViewPagerAdapter adapter = new ViewPagerAdapter(lists, inLayout, getActivity());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPager.listener);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (viewPager != null)
            viewPager.stopTask();
    }

    @Override
    public void onResume() {
        super.onResume();
        updateTimer();
        initHomeToolBar();
    }

    private void initHomeToolBar() {
        ((BaseToolbarActivity) getBaseActivity()).getToolbar().setNavigationIcon(null);
        ((BaseToolbarActivity) getBaseActivity()).getToolbar().setTitle(null);
        ((BaseToolbarActivity) getBaseActivity()).tvToolbarLeft.setVisibility(View.VISIBLE);
        ((BaseToolbarActivity) getBaseActivity()).tvToolbarRight.setVisibility(View.VISIBLE);
        ((BaseToolbarActivity) getBaseActivity()).tvToolbarLeft.setBackgroundResource(R.mipmap.left_logo);
        ((BaseToolbarActivity) getBaseActivity()).llRight.setVisibility(View.VISIBLE);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initHomeToolBar();
        }
    }

    private void initContent(List<AllBannerImagesBean.MainBannersBean> data) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);


        adapter = new BaseRecyclerAdapter<AllBannerImagesBean.MainBannersBean>(mContext, data, R.layout.home_sport_item_image_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, AllBannerImagesBean.MainBannersBean item) {
                TextView tv = holder.getView(R.id.tv_text);
                TextView tv_num = holder.getView(R.id.tv_num);
                holder.setImageByUrl(R.id.iv_pic, item.getImg());
                SportIdBean sportIdBean = AfbUtils.getSportByG(item.getG());
                if (sportIdBean != null && sportIdBean.getTextRes() > 0) {
                    tv.setText(getString(sportIdBean.getTextRes()));//M_RAm1
                    int textColor = sportIdBean.getTextColor();
                    tv.setTextColor(textColor);
                } else {
                    tv.setText("gd88Casino");
                }
                tv_num.setVisibility(View.GONE);
                if (jsonObjectNum != null) {
                    if (!StringUtils.isNull(jsonObjectNum.optString("M_RAm" + item.getDbid()))) {
                        tv_num.setText(jsonObjectNum.optString("M_RAm" + item.getDbid()));
                        tv_num.setVisibility(View.VISIBLE);
                    } else if (!StringUtils.isNull(jsonObjectNum.optString("M_TAm" + item.getDbid()))) {
                        tv_num.setText(jsonObjectNum.optString("M_TAm" + item.getDbid()));
                        tv_num.setVisibility(View.VISIBLE);
                    } else if (!StringUtils.isNull(jsonObjectNum.optString("M_EAm" + item.getDbid()))) {
                        tv_num.setText(jsonObjectNum.optString("M_EAm" + item.getDbid()));
                        tv_num.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        rvContent.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<AllBannerImagesBean.MainBannersBean>() {
            @Override
            public void onItemClick(View view, AllBannerImagesBean.MainBannersBean item, int position) {
                SportIdBean sportIdBean = AfbUtils.getSportByG(item.getG());
                MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, getString(R.string.running));
                menuItemInfo.setType("Running");
                if (sportIdBean == null) {
                    ToastUtils.showShort(R.string.coming_soon);
                    return;
                }
                menuItemInfo.setParent(sportIdBean.getType());
                Bundle b1 = new Bundle();
                b1.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
                skipAct(sportIdBean.getCls(), b1);

            }
        });
    }

    void updateTimer() {
        updateHandler.post(timeUpdateRunnable);
    }

    @Override
    public void onStop() {
        super.onStop();
        updateHandler.removeCallbacks(timeUpdateRunnable);
    }

    Runnable timeUpdateRunnable = new Runnable() {
        @Override
        public void run() {
            String currentTime = "HK: " + TimeUtils.getTime("dd MM yyyy hh:mm:ss aa z", Locale.ENGLISH);
            ((BaseToolbarActivity) getBaseActivity()).tvTime.setText(currentTime);
            updateHandler.postDelayed(this, 1000);
        }
    };
    Handler updateHandler = new Handler();
}
