package gaming178.com.casinogame.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;

import gaming178.com.baccaratgame.R;

public class MyNetWorkBannerAdapter extends BaseBannerAdapter<String> {
    @Override
    protected void bindData(BaseViewHolder<String> holder, String data, int position, int pageSize) {
        ImageView imageView = holder.findViewById(R.id.img_banner);
        Glide.with(imageView).load(data).apply(new RequestOptions()
                .transform(new CenterCrop(), new RoundedCorners(20)
                )).into(imageView);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_banner;
    }
}
