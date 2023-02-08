package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.R;


/**
 * Created by Administrator on 2019/6/21.
 */

public abstract class BaseListPopWindow extends BasePopupWindow {
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    BaseRecyclerAdapter<String> adapter;

    public BaseListPopWindow(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_base_list_popwindow;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        adapter = new BaseRecyclerAdapter<String>(context, getContentData(), R.layout.item_bank_popwindow) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView tvContent = holder.getView(R.id.item_tv);
                tvContent.setText(item);
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, String item, int position) {
                onClickItem(position, item);
                closePopupWindow();
            }
        });
        rcContent.setLayoutManager(new LinearLayoutManager(context));
        rcContent.setAdapter(adapter);
    }

    public abstract List<String> getContentData();

    public abstract void onClickItem(int position, String item);
}
