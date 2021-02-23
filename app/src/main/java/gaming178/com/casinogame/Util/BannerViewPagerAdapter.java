package gaming178.com.casinogame.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerViewPagerAdapter extends PagerAdapter {
    private ArrayList<ImageView> reusableImgViews = new ArrayList<>();
    private List<String> lists;
    public LinearLayout layout;
    public int itemTrueAmount;
    private Context context;

    public BannerViewPagerAdapter(List<String> lists, LinearLayout layout, Context context) {
        super();
        this.lists = lists;
        this.layout = layout;
        itemTrueAmount = lists.size();
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgView;
        if (reusableImgViews.size() == 0) {
            imgView = new ImageView(context);
            imgView.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            imgView = reusableImgViews.remove(reusableImgViews.size() - 1);
        }

        String url = lists.get(getBannerIndexOfPosition(position));
        ImageLoader.getInstance().displayImage(url, imgView, displayImageOptions);
        container.addView(imgView);
        return imgView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        reusableImgViews.add((ImageView) object);
    }

    private int getBannerIndexOfPosition(int position) {
        return position % lists.size();
    }

    private DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
            .cacheInMemory(true).cacheOnDisk(true)
            .bitmapConfig(Bitmap.Config.RGB_565)
            .resetViewBeforeLoading(true)
            .considerExifParams(true)
            .build();
}
