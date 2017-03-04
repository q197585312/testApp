package com.nanyang.app.main.home.sport;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.AfbApplication;
import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;
import com.unkonw.testapp.libs.base.BaseFragment;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseSportFragment<T extends SportPresenter> extends BaseFragment<T> {
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
        BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, types, R.layout.text_base) {
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
    public AfbApplication getApp(){
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
    protected  void  clickOddsType(final View textView){

        createPopupWindow(new BasePopupWindow(mContext, textView, LinearLayout.LayoutParams.MATCH_PARENT, 350) {
            @Override
            protected int onSetLayoutRes() {
                return R.layout.popupwindow_choice;
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv_list);
                rv.setPadding(0,0,0,0);
                rv.setLayoutManager(new LinearLayoutManager(mContext));
                List<MenuItemInfo> list = new ArrayList<>();
                list.add(new MenuItemInfo(0, getString(R.string.HK_ODDS),"HK"));//accType=
                list.add(new MenuItemInfo(0, getString(R.string.MY_ODDS),"MY"));
                list.add(new MenuItemInfo(0, getString(R.string.ID_ODDS),"ID"));
                list.add(new MenuItemInfo(0, getString(R.string.EU_ODDS),"EU"));
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
                        ((TextView)textView).setText(item.getText());
                    }
                });
            }
        });
        popWindow.showPopupDownWindow();

    }

    private void switchedOddsType(String type) {
        presenter.switchedOddsType(type);
    }
}
