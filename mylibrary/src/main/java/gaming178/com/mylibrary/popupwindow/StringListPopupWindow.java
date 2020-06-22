package gaming178.com.mylibrary.popupwindow;

import android.content.Context;
import android.view.View;

import gaming178.com.mylibrary.R;
import gaming178.com.mylibrary.base.ViewHolder;


public abstract class StringListPopupWindow extends BaseListPopupWindow<String> {

	public StringListPopupWindow(Context context, View v, int width, int height) {
		super(context, v, width, height);
	}

	@Override
	protected void convertItem(ViewHolder helper, String item, int position) {
		helper.setText(R.id.text_tv1, item);
	}

}
