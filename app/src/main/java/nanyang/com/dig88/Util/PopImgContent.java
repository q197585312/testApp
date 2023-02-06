package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;

import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/1/8.
 */

public class PopImgContent extends BasePopupWindow {
    public PopImgContent(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_img;
    }
}
