package com.nanyang.app.main.home.sport.live;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.MainPresenter;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ASUS on 2019/12/30.
 */

public class LiveSelectedHelper {
    public List<MenuItemInfo> getList() {
        return list;
    }

    List<MenuItemInfo> list;
    BaseRecyclerAdapter<MenuItemInfo> baseRecyclerAdapter;

    public HashMap<Boolean, MenuItemInfo> getLinkMap() {
        return linkMap;
    }

    HashMap<Boolean, MenuItemInfo> linkMap;

    public LiveSelectedHelper() {
        list = new ArrayList<>();
        linkMap = new HashMap<>();
        list.add(new MenuItemInfo(R.string.all, (R.string.all)));
        list.add(new MenuItemInfo(R.string.HDP_OU, (R.string.HDP_OU)));
        list.add(new MenuItemInfo(R.string.X1X2, (R.string.X1X2)));
        list.add(new MenuItemInfo(R.string.OE, (R.string.OE)));
        list.add(new MenuItemInfo(R.string.DC, (R.string.DC)));
        list.add(new MenuItemInfo(R.string.CS, (R.string.CS)));
        list.add(new MenuItemInfo(R.string.TG, (R.string.TG)));
        list.add(new MenuItemInfo(R.string.HTFT, (R.string.HTFT)));
        list.add(new MenuItemInfo(R.string.FGLG, (R.string.FGLG)));
        list.add(new MenuItemInfo(R.string.MM_HDP_OU, (R.string.MM_HDP_OU)));
        list.add(new MenuItemInfo(R.string.Home_Away_TG, (R.string.Home_Away_TG)));
        list.add(new MenuItemInfo(R.string.FT15, (R.string.FT15)));
        linkMap.put(true, list.get(0));
    }

    public void putIndex(int index) {
        linkMap.put(true, list.get(index));
    }

    public boolean isPositionSelected(int index) {
        return linkMap.get(true).getRes() == list.get(index).getRes();
    }

    public int getPositionSelected() {
        for (int i = 0; i < list.size(); i++) {
            MenuItemInfo menuItemInfo = list.get(i);
            if (menuItemInfo.getRes() == linkMap.get(true).getRes())
                return i;
        }
        return 0;

    }

    public void iniSelectedHelper(RecyclerView rv_title_list, Context mContext, final MainPresenter.CallBack<MenuItemInfo> back) {
        RecyclerView.Adapter adapter = rv_title_list.getAdapter();
        if (adapter == null) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rv_title_list.setLayoutManager(layoutManager);
            baseRecyclerAdapter= new BaseRecyclerAdapter<MenuItemInfo>(mContext, getList(), R.layout.text_wrap_wrap) {
                @Override
                public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                    TextView textView = holder.getTextView(R.id.item_text_tv);
                    textView.setTextColor(ContextCompat.getColor(mContext, R.color.black_grey));
                    textView.setText(item.getRes());
                    textView.setAllCaps(true);
                    if (isPositionSelected(position)) {
                        textView.setTextColor(ContextCompat.getColor(mContext, R.color.yellow1));
                    }
                }
            };
            baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo>() {
                @Override
                public void onItemClick(View view, MenuItemInfo item, int position) {
                    putIndex(position);
                    baseRecyclerAdapter.notifyDataSetChanged();
                    try {
                        back.onBack(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            rv_title_list.setAdapter(baseRecyclerAdapter);
        }else{
            baseRecyclerAdapter= (BaseRecyclerAdapter<MenuItemInfo>) adapter;
            baseRecyclerAdapter.notifyDataSetChanged();
        }

    }

}
