package com.nanyang.app.main.home.sport;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.main.home.sport.dialog.BetBasePop;
import com.nanyang.app.main.home.sport.model.BettingPromptBean;
import com.nanyang.app.main.home.sport.model.MenuListInfo;
import com.nanyang.app.myView.LinkedViewPager.MyPagerAdapter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.utils.ToastUtils;
import com.unkonw.testapp.libs.utils.ViewHolder;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSportFragment<T extends SportPresenter> extends BaseFragment<T> {
    protected TextView tvMenu;
    private BetBasePop betPop;

    @Override
    public void initData() {
        super.initData();
    }

    public void toolbarRightClick(View v) {

        createPopupWindow(
                new BasePopupWindow(mContext, v, LinearLayout.LayoutParams.MATCH_PARENT, 300) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_ball_type;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setChooseTypeAdapter(rv_list);
                    }
                });
        popWindow.showPopupDownWindow();
    }

    private void setChooseTypeAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(0, getString(R.string.Today), "Today"));
        if (!presenter.isMixParlay()) {
            types.add(new MenuItemInfo(0, getString(R.string.Running), "Running"));
        }
        types.add(new MenuItemInfo(0, getString(R.string.Early), "Early"));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {
                presenter.refresh(item.getType());
                popWindow.closePopupWindow();
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    public AfbApplication getApp() {
        return (AfbApplication) getActivity().getApplication();
    }

    public boolean mixParlayCLick(TextView buttonView) {
        return false;
    }

    public boolean collectionClick(TextView tvCollection) {
        return false;
    }

    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (hidden) {// 不在最前端界面显示
            presenter.stopUpdate();
        } else {// 重新显示到最前端中
            presenter.refresh("");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.refresh("");
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.stopUpdate();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.stopUpdate();
    }

    //盘口选择
    protected void clickOddsType(final View textView) {

        createPopupWindow(new BasePopupWindow(mContext, textView, LinearLayout.LayoutParams.MATCH_PARENT, 350) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                rv.setPadding(0, 0, 0, 0);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getString(R.string.HK_ODDS), "HK"));//accType=
                list.add(new MenuItemInfo(0, getString(R.string.MY_ODDS), "MY"));
                list.add(new MenuItemInfo(0, getString(R.string.ID_ODDS), "ID"));
                list.add(new MenuItemInfo(0, getString(R.string.EU_ODDS), "EU"));
                BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, list, R.layout.text_base_item) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                        TextView tv = holder.getView(R.id.item_text_tv);
                        tv.setPadding(0, 0, 0, 0);
                        tv.setText(item.getText());
                        tv.setBackgroundResource(R.color.black_grey);
                    }

                };
                rv.setAdapter(baseRecyclerAdapter);
                baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                    @Override
                    public void onItemClick(View view, MenuItemInfo item, int position) {
                        switchedOddsType(item.getType());
                        closePopupWindow();
                        ((TextView) textView).setText(item.getText());
                    }
                });
            }
        });
        popWindow.showPopupDownWindow();

    }

    private void switchedOddsType(String type) {
        presenter.switchedOddsType(type);
    }

    public void menuClick(TextView tvMenu) {
        this.tvMenu = tvMenu;
        createPopupWindow(
                new BasePopupWindow(mContext, tvMenu, LinearLayout.LayoutParams.MATCH_PARENT, 150) {
                    @Override
                    protected int onSetLayoutRes() {
                        return R.layout.popupwindow_choice_bottom;
                    }

                    @Override
                    protected void initView(View view) {
                        super.initView(view);
                        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
                        setBottomMenuAdapter(rv_list);
                    }
                });
        popWindow.setTrans(1f);
        popWindow.showPopupDownWindow();
    }

    private void setBottomMenuAdapter(RecyclerView rv_list) {
        rv_list.setLayoutManager(new GridLayoutManager(mContext, 3));
        List<MenuItemInfo> types = new ArrayList<>();
        types.add(new MenuItemInfo(R.mipmap.menu_group_oval_white, getString(R.string.Choose), "Choose"));
        types.add(new MenuItemInfo(R.mipmap.menu_error_white, getString(R.string.not_settled), "Not settled"));
        types.add(new MenuItemInfo(R.mipmap.menu_right_oval_white, getString(R.string.settled), "Settled"));
        types.add(new MenuItemInfo(R.mipmap.menu_trophy_white, getString(R.string.OutRight), "OutRight"));
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                TextView tv = holder.getView(R.id.item_text_tv);
                tv.setCompoundDrawablesWithIntrinsicBounds(0, item.getRes(), 0, 0);
                tv.setTextSize(10);
                tv.setPadding(0, 0, 0, 0);
                tv.setText(item.getText());
            }

        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
            @Override
            public void onItemClick(View view, MenuItemInfo item, int position) {

                popWindow.closePopupWindow();
                if(item.getType().equals("OutRight")){
                    presenter.changeOutRight();
                }
            }
        });
        rv_list.setAdapter(baseRecyclerAdapter);
    }

    @NonNull
    protected MyPagerAdapter<MenuListInfo> headerAdapter() {
        return new MyPagerAdapter<MenuListInfo>(mContext) {
            @Override
            protected void convert(ViewHolder helper, MenuListInfo list, int position) {
                LinearLayout ll_head_parent = helper.getView(R.id.ll_head_parent);
                for (int i = 0; i < list.getList().size(); i++) {
                    MenuItemInfo item = list.getList().get(i);
                    TextView tv = (TextView) ll_head_parent.getChildAt(i);
                    tv.setText(item.getText());
                    if (item.getRes() == 0)
                        tv.setVisibility(View.GONE);
                    else
                        tv.setVisibility(View.VISIBLE);
                }

            }

            @Override
            protected int getPageLayoutRes() {
                return R.layout.sport_head_vp_item;
            }
        };
    }


    public void onGetBetSucceed(BettingPromptBean allData) {
        betPop.setBetData(allData, presenter);
        betPop.showPopupCenterWindow();
    }



    public void onCreatePopupWindow(BetBasePop betPop) {
        this.betPop = betPop;
        createPopupWindow(betPop);
    }
    public void onBetSucceed(String allData) {
        ToastUtils.showShort(allData);
        betPop.closePopupWindow();
    }
}
