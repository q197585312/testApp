package com.nanyang.app.main.message;

import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.MenuItemInfo;
import com.nanyang.app.R;
import com.nanyang.app.common.MainPresenter;
import com.nanyang.app.main.BaseMoreFragment;
import com.nanyang.app.main.BetCenter.HtmlTagHandler;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by 47184 on 2019/4/5.
 */

public class MessageFragment extends BaseMoreFragment<MessagePresenter> {

    @BindView(R.id.message_list)
    RecyclerView rv;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_messages;
    }

    @Override
    public void showContent() {
        super.showContent();
        setBackTitle(getString(R.string.messages));
    }

    @Override
    public void initData() {
        super.initData();
        createPresenter(new MessagePresenter(this));

        LinearLayoutManager ll = new LinearLayoutManager(mContext);
        rv.setLayoutManager(ll);
        final BaseRecyclerAdapter<MenuItemInfo<String>> adapter = new BaseRecyclerAdapter<MenuItemInfo<String>>(mContext, new ArrayList<MenuItemInfo<String>>(), R.layout.item_messages) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, MenuItemInfo<String> item) {
                TextView textView = holder.getTextView(R.id.message_text);
                TextView date = holder.getTextView(R.id.message_date);
                textView.setText(HtmlTagHandler.spanFontHtml(item.getType()));
                date.setText(item.getParent());
            }
        };
        rv.setAdapter(adapter);
        presenter.getMessages(new MainPresenter.CallBack<String>() {
            @Override
            public void onBack(String data) throws JSONException {
                JSONArray jsonArray = new JSONArray(data);

                if (jsonArray.length() > 2) {
                    JSONArray jsonArray1 = jsonArray.optJSONArray(2);
                    List<MenuItemInfo<String>> datas = new ArrayList<>();
                    if (jsonArray1.length() > 0) {
                        for (int i = 0; i < jsonArray1.length(); i++) {
                            String string = jsonArray1.optJSONArray(i).optString(1);
                            MenuItemInfo<String> dataItem = new MenuItemInfo<String>(0, jsonArray1.optJSONArray(i).optInt(0), string, jsonArray1.optJSONArray(i).optString(2));
                    /*          MenuItemInfo(int res, int text, String type, P parent) {
                          ;*/
                            datas.add(dataItem);
                        }
                    }
                    adapter.addAllAndClear(datas);
                } else {
                    ArrayList<MenuItemInfo<String>> list = new ArrayList<>();
                    list.add( new MenuItemInfo<String>(0, 0, "No Message", ""));
                    adapter.addAllAndClear(list);
                }

            }
        });
    }

}
