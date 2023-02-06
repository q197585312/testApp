package nanyang.com.dig88.Util;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import nanyang.com.dig88.R;

public class ViewPagerAdapter extends PagerAdapter {
    public LinearLayout layout;
    public int itemTrueAmount;
    private ArrayList<LinearLayout> reusableImgViews = new ArrayList<>();
    private List<Object> lists;
    private Context context;
    private onImgClickListener onImgClickListener;

    public ViewPagerAdapter(List<Object> lists, LinearLayout layout, Context context) {
        super();
        this.lists = lists;
        this.layout = layout;
        itemTrueAmount = lists.size();
        this.context = context;
    }

    public void setOnImgClickListener(onImgClickListener onImgClickListener) {
        this.onImgClickListener = onImgClickListener;
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
        LinearLayout linearLayout;
        if (reusableImgViews.size() == 0) {
            linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.ll_simpledraweeview, null);
        } else {
            linearLayout = reusableImgViews.remove(reusableImgViews.size() - 1);
        }
        if (onImgClickListener != null) {
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onImgClickListener.onClick();
                }
            });
        }
        SimpleDraweeView imgView = linearLayout.findViewById(R.id.iv_game_picture);
        GenericDraweeHierarchy hierarchy = imgView.getHierarchy();
        hierarchy.setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
        Object o = lists.get(getBannerIndexOfPosition(position));
        String url;
        Uri uri;
        if (o instanceof String) {
            url = (String) o;
            imgView.setImageURI(url);
        } else if (o instanceof Uri) {
            uri = (Uri) o;
            imgView.setImageURI(uri);
        }
        container.addView(linearLayout);
        return linearLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        reusableImgViews.add((LinearLayout) object);
    }

    private int getBannerIndexOfPosition(int position) {
        return position % lists.size();
    }

    public interface onImgClickListener {
        void onClick();
    }
}
