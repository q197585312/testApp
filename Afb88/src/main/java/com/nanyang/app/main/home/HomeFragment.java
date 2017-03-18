package com.nanyang.app.main.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.ApiService;
import com.nanyang.app.AppConstant;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.Utils.AutoScrollViewPager;
import com.nanyang.app.Utils.ViewPagerAdapter;
import com.nanyang.app.main.home.Games.GamesActivity;
import com.nanyang.app.main.home.discount.DiscountActivity;
import com.nanyang.app.main.home.poker.PokerCasinoActivity;
import com.nanyang.app.main.home.sport.main.SportActivity;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.api.Api;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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
        initBanner();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.home_sports, getString(R.string.SportBook)));
        dataList.add(new MenuItemInfo(R.mipmap.home_live, getString(R.string.Live_Casino)));
        dataList.add(new MenuItemInfo(R.mipmap.home_games, getString(R.string.Games)));
        dataList.add(new MenuItemInfo(R.mipmap.home_keno, getString(R.string.Keno)));
        dataList.add(new MenuItemInfo(R.mipmap.home_poker, getString(R.string.Poker)));
        dataList.add(new MenuItemInfo(R.mipmap.home_lottery, getString(R.string.Lottery)));
        dataList.add(new MenuItemInfo(R.mipmap.home_roulette, getString(R.string.Roulette)));
        dataList.add(new MenuItemInfo(R.mipmap.home_casino, getString(R.string.Casino)));
        dataList.add(new MenuItemInfo(R.mipmap.home_discount, getString(R.string.Discount)));
        BaseRecyclerAdapter adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, dataList, R.layout.home_item_image_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                ImageView iv = holder.getView(R.id.iv_pic);
                TextView tv = holder.getView(R.id.tv_text);
                iv.setImageResource(item.getRes());
                tv.setText(item.getText());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                if (item.getText().equals(getString(R.string.SportBook))) {
                    popWindow.showPopupCenterWindow();
                } else if (item.getText().equals(getString(R.string.Poker))) {
                    Bundle b = new Bundle();
                    b.putString("activity", "Porker");
                    skipAct(PokerCasinoActivity.class, b);
                } else if (item.getText().equals(getString(R.string.Live_Casino))) {
                    Bundle b = new Bundle();
                    b.putString("activity", "Live");
                    skipAct(PokerCasinoActivity.class, b);
                } else if (item.getText().equals(getString(R.string.Discount))) {
                    skipAct(DiscountActivity.class);
                } else if (item.getText().equals(getString(R.string.Games))) {
                    skipAct(GamesActivity.class);
                }
            }
        });
        rvContent.setAdapter(adapter);
//        rvContent.addItemDecoration(new RecycleViewDivider(mContext,GridLayoutManager.HORIZONTAL,2,getResources().getColor(R.color.green_thick_line)));
        createPopupWindow(new BasePopupWindow(mContext, rvContent, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT) {//创建
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
                List<MenuItemInfo> data = new ArrayList<>();
                MenuItemInfo menuItemInfo = new MenuItemInfo(0, getString(R.string.Today));
                menuItemInfo.setType("Today");
                MenuItemInfo menuItemInfo1 = new MenuItemInfo(0, getString(R.string.Running));
                menuItemInfo1.setType("Running");
                MenuItemInfo menuItemInfo2 = new MenuItemInfo(0, getString(R.string.Early));
                menuItemInfo2.setType("Early");
                MenuItemInfo menuItemInfo3 = new MenuItemInfo(0, getString(R.string.OutRight));
                menuItemInfo3.setType("OutRight");
                MenuItemInfo menuItemInfo4 = new MenuItemInfo(0, getString(R.string.Cancel));
                menuItemInfo4.setType("");
                data.add(menuItemInfo);
                data.add(menuItemInfo1);
                data.add(menuItemInfo2);
                data.add(menuItemInfo3);
                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, data, R.layout.text_base) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        if (item.getText().equals(getString(R.string.Cancel))) {
                            tv.setBackgroundResource(R.color.grey_default_bg);
                            tv.setTextColor(getResources().getColor(R.color.black_grey));

                        } else {
                            tv.setBackgroundResource(R.color.green900);
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
        });
    }

    private List<String> lists;

    private void initViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(lists, inLayout, getActivity());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(viewPager.listener);
    }

    private void initBanner() {
        Disposable d = Api.getService(ApiService.class).getBannerUrl()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> strings) throws Exception {
                        lists = strings;
                        initViewPager();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        subscription.request(Integer.MAX_VALUE);
                    }
                });
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(d);
    }

}
