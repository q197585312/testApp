package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.Gd88Utils;
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
        imgBanner = view.findViewById(R.id.img_banner);
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
