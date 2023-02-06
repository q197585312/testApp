package nanyang.com.dig88.LinkedViewPager;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import xs.com.mylibrary.base.ViewHolder;

public abstract class MyPagerAdapter<T> extends PagerAdapter {
	List<T> datas;
	Context context;
	private  int parentPosition;
	private Object extraData;

	public MyPagerAdapter(Context context,List<T> datas) {
		super();
		this.context=context;
		this.datas = datas;
	}

	public MyPagerAdapter(Context context) {
		this.context = context;
	}

	public int getParentPosition() {
		return parentPosition;
	}

	public void setParentPosition(int parentPosition) {
		this.parentPosition = parentPosition;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	}
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return super.getItemPosition(object);
	}

	@Override
	public void destroyItem(View arg0, int arg1, Object arg2) {
		// TODO Auto-generated method stub
		((ViewGroup)arg0).removeView((View) arg2);
	}

	@Override
	public Object instantiateItem(View parent, int position) {
		final View convertView = LayoutInflater.from(context).inflate(getPageLayoutRes(), null);
		final ViewHolder helper = get(context, convertView, (ViewGroup)parent);
		convert(helper, datas.get(position), position);
		((ViewGroup)parent).addView(convertView);
		return helper.getView();
	}

	@Override
	public void restoreState(Parcelable arg0, ClassLoader arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parcelable saveState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startUpdate(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void finishUpdate(View arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public int getCount() {
		return datas == null ? 0 : datas.size();
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

	public <E>E getExtraData(){
		return (E) extraData;
	}

	public <E> void setExtraData(E data){
		this.extraData=data;
	}
}