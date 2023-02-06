package nanyang.com.dig88.Lottery4D.popWindow;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanyang.com.dig88.R;
import xs.com.mylibrary.base.QuickBaseAdapter;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/11/19.
 */

public abstract class PopSwitchBetType extends BasePopupWindow {
    @Bind(R.id.lv_switch_type)
    ListView lv_switch_type;
    QuickBaseAdapter<String> adapter;
    private TextView tvContent;

    public PopSwitchBetType(Context context, View v, int width, int height) {
        super(context, v, width, height);
        tvContent = (TextView) v;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_lottery_4d_switch_type;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        adapter = new QuickBaseAdapter<String>(context, R.layout.item_switch_type, getData()) {
            @Override
            protected void convert(ViewHolder helper, String item, int position) {
                TextView tv_content = helper.retrieveView(R.id.tv_content);
                tv_content.setText(item);
            }
        };
        lv_switch_type.setAdapter(adapter);
        lv_switch_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvContent.setText(getData().get(position));
                String item = adapter.getList().get(position);
                onSetText(item);
                closePopupWindow();
            }
        });
    }

    public abstract List<String> getData();

    public abstract void onSetText(String text);
}
