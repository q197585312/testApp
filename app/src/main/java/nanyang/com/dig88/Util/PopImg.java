package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/1/30.
 */

public class PopImg extends BasePopupWindow implements View.OnClickListener {
    private ImageView img;
    private TextView tv_exit;

    public PopImg(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_net_picture;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        img = (ImageView) view.findViewById(R.id.img_content);
        tv_exit = (TextView) view.findViewById(R.id.tv_exit);
        img.setOnClickListener(this);
        tv_exit.setOnClickListener(this);
    }

    public void setImg(int res) {
        img.setBackgroundResource(res);
    }

    @Override
    public void onClick(View v) {
        closePopupWindow();
    }
}
