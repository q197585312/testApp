package com.nanyang.app.main.home.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.main.BaseSwitchFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 47184 on 2019/4/5.
 */

public class MessageFragment extends BaseSwitchFragment {

    @Bind(R.id.message_list)
    RecyclerView rv;
    private List<String> list;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_messages;
    }

    @Override
    public void initData() {
        super.initData();
        setCurrentFragmentTitle();
        list = new ArrayList<>();
        list.add("No Message");
        LinearLayoutManager ll = new LinearLayoutManager(mContext);
        rv.setLayoutManager(ll);
        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(mContext, list,R.layout.item_messages) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView textView = holder.getTextView(R.id.message_text);
                textView.setText(item);
            }
        };
        rv.setAdapter(adapter);
    }

    @Override
    public void initView() {
        super.initView();
        setTitle(getString(R.string.messages));
    }
}
