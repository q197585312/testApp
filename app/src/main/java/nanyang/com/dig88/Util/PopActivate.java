package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/6/12.
 */

public class PopActivate extends BasePopupWindow {
    TextView tvContent, tvSure;

    public PopActivate(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_activate;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        tvContent.setText(context.getString(R.string.need_activate));
        tvSure = (TextView) view.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }
}
