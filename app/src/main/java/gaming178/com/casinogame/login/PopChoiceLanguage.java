package gaming178.com.casinogame.login;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.casinogame.adapter.BaseRecyclerAdapter;
import gaming178.com.casinogame.adapter.MyRecyclerViewHolder;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/4/3.
 */

public abstract class PopChoiceLanguage<T> extends BasePopupWindow {
    public PopChoiceLanguage(Context context, View v, int width, int height) {
        super(context, v, width, height);
        initData();
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_popupwindow_language_select;
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public RecyclerView recyclerView;
    public TextView tv_title;
    public LinearLayout ll_arrow;

    @Override
    protected void initView(View view) {
        super.initView(view);
        recyclerView = (RecyclerView) view.findViewById(R.id.gd__base_rv);
        tv_title = (TextView) view.findViewById(R.id.gd__tv_title);
        ll_arrow = (LinearLayout) view.findViewById(R.id.ll_arrow);
        BaseActivity activity = (BaseActivity) context;
        if (!BuildConfig.FLAVOR.isEmpty() && !BuildConfig.FLAVOR.equals("gd88") && !BuildConfig.FLAVOR.equals("liga365")) {
            if (tv_title != null && activity instanceof LobbyActivity) {
                tv_title.setText(context.getString(R.string.member_center));
            }
        }
        if (ll_arrow != null && activity instanceof LoginActivity) {
            ll_arrow.setVisibility(View.GONE);
        }
    }

    BaseRecyclerAdapter<T> adapter;

    public void initData() {
        adapter = new BaseRecyclerAdapter<T>(context, new ArrayList<T>(), onSetRcItemLayout()) {

            @Override
            public void convert(MyRecyclerViewHolder holder, int position, T item) {
                onConvert(holder, position, item);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<T>() {
            @Override
            public void onItemClick(View view, T item, int position) {
                onClickItem(item, position);
                closePopupWindow();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter);

    }

    protected abstract int onSetRcItemLayout();

    public void setData(List<T> data) {
        adapter.addAllAndClear(data);
    }

    public abstract void onConvert(MyRecyclerViewHolder holder, int position, T item);

    public abstract void onClickItem(T item, int position);
}
