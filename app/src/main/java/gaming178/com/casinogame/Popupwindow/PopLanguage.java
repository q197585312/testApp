package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.casinogame.login.LanguageHelper;
import gaming178.com.casinogame.login.MenuItemInfo;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

public class PopLanguage extends BasePopupWindow {
    RecyclerView rcLg;
    LinearLayout ll_parent;
    BaseRecyclerAdapter<MenuItemInfo<String>> adapter;
    BaseActivity activity;

    public PopLanguage(Context context, View v, int width, int height) {
        super(context, v, width, height);
        activity = (BaseActivity) context;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_language;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ll_parent = view.findViewById(R.id.gd_ll_parent);
        rcLg = view.findViewById(R.id.rc_lg);
        rcLg.setLayoutManager(new LinearLayoutManager(context));
        adapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(context, new LanguageHelper(context).getLanguageItems(), R.layout.item_pop_lg) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                ImageView imageView = holder.getImageView(R.id.img_lg);
                TextView textView = holder.getTextView(R.id.tv_lg);
                imageView.setImageResource(item.getRes());
                textView.setText(item.getSimpleText());
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<MenuItemInfo<String>>() {
            @Override
            public void onItemClick(View view, MenuItemInfo<String> item, int position) {
                AppTool.setAppLanguage(activity, item.getType());
                if (activity instanceof LobbyActivity) {
                    activity.skipAct(LobbyActivity.class);
                    activity.finish();
                } else {
                    activity.recreate();
                }
            }
        });
        rcLg.setAdapter(adapter);
    }

    public void setDarkBg() {
        ll_parent.setBackgroundResource(R.drawable.gd_language_bg_dark);
    }
}
