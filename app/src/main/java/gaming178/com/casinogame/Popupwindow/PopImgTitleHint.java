package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopImgTitleHint extends BasePopupWindow {
    BaseActivity activity;
    private FrameLayout fl_parent;

    public PopImgTitleHint(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_img_title_hint;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        activity = (BaseActivity) context;
        fl_parent = view.findViewById(R.id.gd_fl_parent);
        fl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }
}
