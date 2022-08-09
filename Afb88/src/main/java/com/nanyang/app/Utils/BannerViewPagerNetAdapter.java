package com.nanyang.app.Utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.nanyang.app.R;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;


public class BannerViewPagerNetAdapter extends BaseBannerAdapter<String> {
    @Override
    protected void bindData(BaseViewHolder<String> holder, String data, int position, int pageSize) {
        ImageView imageView = holder.findViewById(R.id.img_banner);
        Glide.with(imageView).load(data).apply(new RequestOptions()
                .transform(new FitCenter(), new RoundedCorners(20)
                )).into(imageView);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.afb_item_banner;
    }
}
