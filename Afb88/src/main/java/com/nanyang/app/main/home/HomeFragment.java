package com.nanyang.app.main.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbUtils;
import com.nanyang.app.AppConstant;
import com.nanyang.app.BaseToolbarActivity;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.ViewPagerAdapter;
import com.nanyang.app.load.ListMainBanners;
import com.nanyang.app.main.home.discount.DiscountActivity;
import com.nanyang.app.main.home.huayThai.HuayThaiActivity;
import com.nanyang.app.main.home.keno.KenoActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.LogUtil;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HomeFragment extends BaseFragment {

    @Bind(R.id.auto_viewpager)
    AutoScrollViewPager viewPager;
    @Bind(R.id.rv_content)
    RecyclerView rvContent;
    @Bind(R.id.in_layout)
    LinearLayout inLayout;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
        super.initView();
        EventBus.getDefault().register(this);
        BaseRecyclerAdapter adapter = AfbUtils.getGamesAdapter(mContext, rvContent);
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                switch (item.getType()) {
                    case "SportBook":
                        if (getString(R.string.app_name).equals("AP889")) {
                            MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, getString(R.string.Running));
                            menuItemInfo.setType("Running");
                            menuItemInfo.setParent(item.getType());
                            Bundle b1 = new Bundle();
                            b1.putSerializable(AppConstant.KEY_DATA, menuItemInfo);
                            skipAct(SportActivity.class, b1);
                        } else {
                            defaultSkip(item.getType());
                        }
                        break;
                    case "Financial":
                    case "Specials_4D":
                    case "Muay_Thai":
                    case "E_Sport":
                    case "Myanmar_Odds":
                    case "Europe":
                        defaultSkip(item.getType());
           /*             createPopupWindow(getPopupWindow(item.getType()));
                        popWindow.showPopupCenterWindow();*/
                        break;
                    case "Huay_Thai":
                        skipAct(HuayThaiActivity.class);
                        break;
                    case "Live_Casino":
                        Bundle b = new Bundle();
                        b.putString("activity", "Live");
                        ToastUtils.showShort(R.string.coming_soon);
//                        skipAct(PokerCasinoActivity.class, b);
                        break;
                    case "Poker":
                        ToastUtils.showShort(R.string.coming_soon);
                        break;
                    case "Discount":
                        skipAct(DiscountActivity.class);
                        break;
                    case "Keno":
                        skipAct(KenoActivity.class);
                        break;
                    default:
                        ToastUtils.showShort(R.string.coming_soon);
                }


            }
        });


    }


    public void defaultSkip(final String type) {
        ((BaseToolbarActivity) getBaseActivity()).defaultSkip(type);

    }

    @NonNull
    private BasePopupWindow getPopupWindow(final String type) {

        return new BasePopupWindow(mContext, rvContent, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {//创建
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
                rv.setLayoutManager(mLayoutManager);
                List<MenuItemInfo<String>> data = new ArrayList<>();
                MenuItemInfo<String> menuItemInfo = new MenuItemInfo<String>(0, getString(R.string.Today));
                menuItemInfo.setType("Today");
                menuItemInfo.setParent(type);
                MenuItemInfo<String> menuItemInfo1 = new MenuItemInfo<String>(0, getString(R.string.Running));
                menuItemInfo1.setType("Running");
                menuItemInfo1.setParent(type);
                MenuItemInfo<String> menuItemInfo2 = new MenuItemInfo<String>(0, getString(R.string.Early));
                menuItemInfo2.setType("Early");
                menuItemInfo2.setParent(type);
                MenuItemInfo<String> menuItemInfo3 = new MenuItemInfo<String>(0, getString(R.string.OutRight));
                menuItemInfo3.setType("OutRight");
                menuItemInfo3.setParent(type);
                MenuItemInfo<String> menuItemInfo4 = new MenuItemInfo<String>(0, getString(R.string.Cancel));
                menuItemInfo4.setType("");
                if (type.equals("SportBook") || type.equals("E_Sport"))
                    data.add(menuItemInfo3);
                data.add(menuItemInfo);
                data.add(menuItemInfo1);
                data.add(menuItemInfo2);
                data.add(menuItemInfo4);


                BaseRecyclerAdapter<MenuItemInfo<String>> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(mContext, data, R.layout.text_base) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        if (item.getText().equals(getString(R.string.Cancel))) {
                            tv.setBackgroundResource(R.color.grey_default_bg);
                            tv.setTextColor(getResources().getColor(R.color.black_grey));

                        } else {
                            tv.setBackgroundResource(R.color.green_black);
                            tv.setTextColor(getResources().getColor(R.color.white));
                        }
                        tv.setText(item.getText());
                    }
                };
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        if (item.getText().equals(getString(R.string.Cancel))) {
                            closePopupWindow();
                        } else {
                            Bundle b = new Bundle();
                            b.putSerializable(AppConstant.KEY_DATA, item);
                            skipAct(SportActivity.class, b);
                        }
                    }
                });
                rv.setAdapter(baseRecyclerAdapter);
            }
        };
    }


    private void initViewPager(ListMainBanners lists) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(lists.getBannersBeen(), inLayout, getActivity());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPager.listener);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(ListMainBanners data) {
        LogUtil.d(getClass().getSimpleName(),"onEvent------------>"+ data.toString());
        initViewPager(data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
