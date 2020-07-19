package gaming178.com.casinogame.Util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.MenuItemInfo;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;

/**
 * Created by Administrator on 2017/8/28.
 */

public class AfbUtils {
    public static BaseRecyclerAdapter getGamesAdapter(Context mContext, RecyclerView rvContent) {
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 3);//设置为一个3列的纵向网格布局
        rvContent.setLayoutManager(layoutManager);
        List<MenuItemInfo> dataList = new ArrayList<>();
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_sports, mContext.getString(R.string.SportBook), "SportBook"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_live, mContext.getString(R.string.Live_Casino), "Live_Casino"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_financials, mContext.getString(R.string.Financial), "Financial"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_sports, mContext.getString(R.string.Europe_View), "Europe"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_specals4d, mContext.getString(R.string.Specials_4D), "Specials_4D"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_huay_thai, mContext.getString(R.string.Huay_Thai), "Huay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_muay_thai, mContext.getString(R.string.Muay_Thai), "Muay_Thai"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_games, mContext.getString(R.string.E_Sport), "E_Sport"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_keno2, mContext.getString(R.string.Myanmar_Odds), "Myanmar_Odds"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_keno, mContext.getString(R.string.Keno), "Keno"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_poker, mContext.getString(R.string.Poker), "Poker"));
        dataList.add(new MenuItemInfo(R.mipmap.gd_home_lottery, mContext.getString(R.string.Lottery), "Lottery"));

//        dataList.add(new MenuItemInfo(R.mipmap.home_roulette, getString(R.string.Roulette), "Roulette"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_casino, getString(R.string.Casino), "Casino"));
//        dataList.add(new MenuItemInfo(R.mipmap.home_discount, getString(R.string.Discount), "Discount"));

        BaseRecyclerAdapter<MenuItemInfo> adapter = new BaseRecyclerAdapter<MenuItemInfo>(mContext, dataList, R.layout.gd_home_item_image_text) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo item) {
                ImageView iv = holder.getView(R.id.gd__iv_pic);
                TextView tv = holder.getView(R.id.gd__tv_text);
                iv.setImageResource(item.getRes());
                tv.setText(item.getText());
            }
        };
        rvContent.setAdapter(adapter);
        return adapter;
    }
}
