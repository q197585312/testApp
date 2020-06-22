package gaming178.com.mylibrary.popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import gaming178.com.mylibrary.R;

public abstract class BaseListPopupWindow<T> extends AbsListPopupWindow<T> {

	public BaseListPopupWindow(Context context, View v, int width, int height) {
		super(context, v, width, height);

	}

	public BaseListPopupWindow(Context context, View v) {
		super(context, v);

	}

	@Override
	protected int getItemLayoutRes() {
		return R.layout.item_text;
	}

	@Override
	protected int getListViewId() {
		return R.id.list_content_lv;
	}

	@Override
	protected int getContentViewLayoutRes() {
		return R.layout.include_listview;
	}

	@Override
	protected AbsListView getAbListViewRes(View view) {
		ListView plv = (ListView) view
				.findViewById(getListViewId());
		return plv;
	}
}
