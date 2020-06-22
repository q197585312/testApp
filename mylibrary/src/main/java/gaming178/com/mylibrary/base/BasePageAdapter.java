package gaming178.com.mylibrary.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/10/21.
 */
public abstract class BasePageAdapter<T> extends PagerAdapter {
    List<T> datas;
    Context context;

    public BasePageAdapter(Context context, List<T> datas) {
        this.datas = datas;
        this.context = context;
    }

    public BasePageAdapter(Context context) {
        this.context = context;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    // PagerAdapter只缓存三张要显示的图片，如果滑动的图片超出了缓存的范围，就会调用这个方法，将图片销毁
    @Override
    public void destroyItem(ViewGroup parent, int position, Object object) {
        parent.removeView((View) object);
    }

    /**
     * 当要显示的图片可以进行缓存的时候，会调用这个方法进行显示图片的初始化，我们将要显示的ImageView加入到ViewGroup中，然后作为返回值返回即可
     */

    @Override
    public Object instantiateItem(final ViewGroup parent, final int position) {
        final View convertView = LayoutInflater.from(context).inflate(getPageLayoutRes(), null);
        final ViewHolder helper = get(context, convertView, parent);
                convert(helper, datas.get(position), position);
                parent.addView(convertView);
        return helper.getView();
    }

    protected abstract void convert(ViewHolder helper, T t, int position);

    /**
     * @param context
     * @param convertView
     * @param parent
     * @return
     */
    public ViewHolder get(Context context, View convertView, ViewGroup parent) {
        return ViewHolder.newHolder(context, convertView, parent);
    }
    protected abstract int getPageLayoutRes();

    @Override
    public CharSequence getPageTitle(int position) {
        return "title";
    }
}
