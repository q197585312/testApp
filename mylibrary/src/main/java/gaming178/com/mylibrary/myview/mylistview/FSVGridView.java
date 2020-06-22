package gaming178.com.mylibrary.myview.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ProjectName: [LNH_BASIC]
 * @Package: [com.lnh.ui.FSVGridView.java]
 * @ClassName: [FSVGridView]
 * @Description: [自定义GridView适应ScrollView]
 * @Author: lnh-xiaohong
 * @CreateDate: [2015-6-19 上午10:36:56]
 * @UpdateUser: [XiaoHong-LNH]
 * @UpdateDate: [2015-6-19 上午10:36:56]
 * @UpdateRemark: [说明本次修改内容]
 * @Version: [v1.0]
 */
public class FSVGridView extends GridView {
	private boolean haveScrollbar = false;

	public FSVGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FSVGridView(Context context) {
		super(context);
	}

	public FSVGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * 设置是否有ScrollBar，当要在ScollView中显示时，应当设置为false。 默认为 true
	 */
	public void setHaveScrollbar(boolean haveScrollbar) {
		this.haveScrollbar = haveScrollbar;
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		if (haveScrollbar == false) {
			int expandSpec = MeasureSpec.makeMeasureSpec(
					Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
			super.onMeasure(widthMeasureSpec, expandSpec);
		} else {
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		}
	}

}