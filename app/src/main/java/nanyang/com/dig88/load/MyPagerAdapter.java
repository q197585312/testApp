package nanyang.com.dig88.load;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

class MyPagerAdapter extends PagerAdapter {
    private final int[] mBitmapIds;
    private final Activity mContext;

    public MyPagerAdapter(int[] bitmapIds, Activity context) {
        this.mBitmapIds = bitmapIds;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        //return viewList==null?0:viewList.size();
        return 3;//ViewPager里的个数
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        loadArrayLocalPictures(position, mBitmapIds, imageView);
        ((ViewPager) container).addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }


    private void loadArrayLocalPictures(int position, int[] localData, ImageView imageView) {
        for (int i = 0; i < localData.length; i++) {
            int resourceId = localData[position % localData.length];
            // 加载图片url
            Glide.with(mContext).load(resourceId).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
        }
    }
}