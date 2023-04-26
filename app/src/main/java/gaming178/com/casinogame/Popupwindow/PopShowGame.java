package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Bean.GameMenuItem;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopShowGame extends BasePopupWindow {
    private TextView tv_exit;
    private RecyclerView rc_game;
    private List<GameMenuItem> pokerList;
    private List<GameMenuItem> slotList;
    private List<GameMenuItem> casinoList;
    private List<GameMenuItem> sportList;
    private List<GameMenuItem> cokList;

    public PopShowGame(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_show_game;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tv_exit = view.findViewById(R.id.tv_exit);
        pokerList = new ArrayList<>();
        slotList = new ArrayList<>();
        casinoList = new ArrayList<>();
        sportList = new ArrayList<>();
        cokList = new ArrayList<>();
        pokerList.add(new GameMenuItem(R.mipmap.ori_poker1, "", ""));
        pokerList.add(new GameMenuItem(R.mipmap.ori_poker2, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot1, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot2, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot3, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot4, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot5, "", ""));
        slotList.add(new GameMenuItem(R.mipmap.ori_slot6, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino1, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino2, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino3, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino4, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino5, "", ""));
        casinoList.add(new GameMenuItem(R.mipmap.ori_casino6, "", ""));
        sportList.add(new GameMenuItem(R.mipmap.ori_sport, "", ""));
        cokList.add(new GameMenuItem(R.mipmap.ori_ayam, "", ""));
        rc_game = view.findViewById(R.id.rc_game);
        rc_game.setLayoutManager(new GridLayoutManager(context,3));
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    public void showContent(int type) {
        switch (type) {
            case 0:
                setAdapter(pokerList);
                break;
            case 1:
                setAdapter(slotList);
                break;
            case 2:
                setAdapter(casinoList);
                break;
            case 3:
                setAdapter(sportList);
                break;
            case 4:
                setAdapter(cokList);
                break;
        }

    }

    private void setAdapter(List<GameMenuItem> list) {
        BaseRecyclerAdapter<GameMenuItem> adapter = new BaseRecyclerAdapter<GameMenuItem>(context, list, R.layout.ori_show_game_item) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, GameMenuItem item) {
                ImageView imageView = holder.getImageView(R.id.img);
                imageView.setImageResource(item.getDrawableRes());
            }
        };
        rc_game.setAdapter(adapter);
    }

}
