package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.Gd88Utils;
import gaming178.com.casinogame.Util.UIUtil;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopImg extends BasePopupWindow {
    BaseActivity activity;
    private View viewParent;
    private ImageView imgBanner;
    private String loadUrl;
    private String goUrl;
    private ImageView img_exit;
    private FrameLayout fl_parent;

    public PopImg(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_img;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        activity = (BaseActivity) context;
        viewParent = view.findViewById(R.id.view_parent);
        fl_parent = view.findViewById(R.id.fl_parent);
        imgBanner = view.findViewById(R.id.img_banner);
        img_exit = view.findViewById(R.id.img_exit);
        if (BuildConfig.FLAVOR.equals("kasino365")) {
            fl_parent.post(new Runnable() {
                @Override
                public void run() {
                    LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) fl_parent.getLayoutParams();
                    lp.leftMargin = UIUtil.dip2px(context, 60);
                    lp.rightMargin = UIUtil.dip2px(context, 60);
                    fl_parent.setLayoutParams(lp);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) img_exit.getLayoutParams();
                    layoutParams.topMargin = UIUtil.dip2px(context, 70);
                    img_exit.setLayoutParams(layoutParams);
                    img_exit.setVisibility(View.VISIBLE);
                }
            });
        }
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        imgBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(getGoUrl())) {
                    Gd88Utils.goBrowser(context, getGoUrl());
                } else {
                    closePopupWindow();
                }
            }
        });
        viewParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
    }

    public void initImg() {
        if (BuildConfig.FLAVOR.equals("kasino365") || BuildConfig.FLAVOR.equals("mainkasino")) {
            Glide.with(context).asGif().load(getLoadUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imgBanner);
        } else {
            Glide.with(context).load(getLoadUrl()).diskCacheStrategy(DiskCacheStrategy.NONE).into(imgBanner);
        }
    }

    public String getLoadUrl() {
        return loadUrl;
    }

    public void setLoadUrl(String loadUrl) {
        this.loadUrl = loadUrl;
        initImg();
    }

    public String getGoUrl() {
        return goUrl;
    }

    public void setGoUrl(String goUrl) {
        this.goUrl = goUrl;
    }
}
