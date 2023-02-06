package gaming178.com.casinogame.Util;

import androidx.viewpager.widget.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * Created by Administrator on 2017/11/17.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private List<View> gridViewsList;

    public MyViewPagerAdapter(List<View> gridViewsList) {
        this.gridViewsList = gridViewsList;
    }

    @Override
    public int getCount() {
        return gridViewsList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(gridViewsList.get(position));//删除页卡
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        container.addView(gridViewsList.get(position));//添加页卡
        return gridViewsList.get(position);
    }

}
