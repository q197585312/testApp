package com.nanyang.app.main.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.BaseMoreFragment;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 47184 on 2019/4/5.
 */

public class MessageFragment extends BaseMoreFragment<MessagePresenter> {

    @Bind(R.id.message_list)
    RecyclerView rv;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_messages;
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new MessagePresenter(this));

        LinearLayoutManager ll = new LinearLayoutManager(mContext);
        rv.setLayoutManager(ll);
        final BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(mContext, new ArrayList<String>(), R.layout.item_messages) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, String item) {
                TextView textView = holder.getTextView(R.id.message_text);
                textView.setText(item);
            }
        };
        rv.setAdapter(adapter);
        setBackTitle(getString(R.string.messages));
        presenter.getMessages(new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);
                if (jsonArray.length() > 2) {
                    JSONArray jsonArray1 = jsonArray.optJSONArray(2);
                    List<String> datas = new ArrayList<String>();
                    if (jsonArray1.length() > 0) {
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            String string = jsonArray1.optJSONArray(i).getString(1);
                            datas.add(string);
                        }
                    }
                    adapter.addAllAndClear(datas);
                } else {
                    ArrayList<String> list = new ArrayList<>();
                    list.add("No Message");
                    adapter.addAllAndClear(list);
                }

            }
        });
    }

}
