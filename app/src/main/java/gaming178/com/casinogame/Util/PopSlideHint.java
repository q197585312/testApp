package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

public class PopSlideHint extends BasePopupWindow {
    public PopSlideHint(Context context, View v, int width, int height) {
        super(context, v, width, height);
        baseActivity = (BaseActivity) context;
        initContent();
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_slide_hint;
    }

    BaseActivity baseActivity;
    RelativeLayout rl_parent;
    ImageView img_top;
    ImageView img_left;
    ImageView img_right;
    ImageView img_bottom;
    TextView tv_slide_hint;

    @Override
    protected void initView(View view) {
        super.initView(view);
        rl_parent = view.findViewById(R.id.gd_rl_parent);
        img_top = view.findViewById(R.id.gd_img_top);
        img_left = view.findViewById(R.id.gd_img_left);
        img_right = view.findViewById(R.id.gd_img_right);
        img_bottom = view.findViewById(R.id.gd_img_bottom);
        tv_slide_hint = view.findViewById(R.id.gd_tv_slide_hint);
        rl_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    @Override
    protected void onCloose() {
        super.onCloose();
        int orientation = getOrientation();
        Integer times;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            times = (Integer) AppTool.getObjectData(context, AppConfig.ACTION_KEY_SLIDE_HINT_P);
            if (times == null) {
                times = 1;
            } else {
                times++;
            }
            AppTool.saveObjectData(context, AppConfig.ACTION_KEY_SLIDE_HINT_P, times);
        } else {
            times = (Integer) AppTool.getObjectData(context, AppConfig.ACTION_KEY_SLIDE_HINT_l);
            if (times == null) {
                times = 1;
            } else {
                times++;
            }
            AppTool.saveObjectData(context, AppConfig.ACTION_KEY_SLIDE_HINT_l, times);
        }
    }

    private void initContent() {
        if (baseActivity != null && !baseActivity.isDestroyed() && !baseActivity.isFinishing()) {
            Glide.with(context).asGif().load(R.mipmap.slide_top_gif).diskCacheStrategy(DiskCacheStrategy.NONE).into(img_top);
            Glide.with(context).asGif().load(R.mipmap.slide_left_gif).diskCacheStrategy(DiskCacheStrategy.NONE).into(img_left);
            Glide.with(context).asGif().load(R.mipmap.slide_right_gif).diskCacheStrategy(DiskCacheStrategy.NONE).into(img_right);
            Glide.with(context).asGif().load(R.mipmap.slide_bottom_gif).diskCacheStrategy(DiskCacheStrategy.NONE).into(img_bottom);
        }
    }

    private int getOrientation() {
        return baseActivity.getResources().getConfiguration().orientation;
    }

    public void initOrientation() {
        int orientation = getOrientation();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            img_top.setVisibility(View.VISIBLE);
            img_bottom.setVisibility(View.VISIBLE);
            img_left.setVisibility(View.GONE);
            img_right.setVisibility(View.GONE);
            tv_slide_hint.setText(baseActivity.getString(R.string.slide_top_down));
        } else {
            img_top.setVisibility(View.GONE);
            img_bottom.setVisibility(View.GONE);
            img_left.setVisibility(View.VISIBLE);
            img_right.setVisibility(View.VISIBLE);
            tv_slide_hint.setText(baseActivity.getString(R.string.slide_left_right));
        }
    }
}
