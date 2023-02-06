package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import nanyang.com.dig88.Entity.LanguageBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by 47184 on 2019/3/25.
 */
public abstract class PopLanguage extends BasePopupWindow {

    ListView listView;
    List<GameMenuItem> list;

    public PopLanguage(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_language;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        listView = (ListView) view.findViewById(R.id.language_list);
    }

    public void setListView(List<GameMenuItem> dataList) {
        list = dataList;
        QuickBaseAdapter adapter = new QuickBaseAdapter<GameMenuItem>(context, R.layout.item_language, dataList) {
            @Override
            protected void convert(ViewHolder helper, GameMenuItem item, int position) {
                ImageView img = helper.retrieveView(R.id.language_img);
                LinearLayout ll = helper.retrieveView(R.id.language_item_layout);
                if (position % 2 == 0) {
                    ll.setBackgroundColor(0xff343434);
                } else {
                    ll.setBackgroundColor(0xff585858);
                }
                TextView name = helper.getTextView(R.id.language_name);
                name.setText(item.getTitle());
                img.setImageResource(item.getDrawableRes());
            }
        };
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GameMenuItem languageBean = list.get(position);
                success(languageBean);
                closePopupWindow();
            }
        });
        listView.setAdapter(adapter);
    }

    public abstract void success(GameMenuItem bean);

}
