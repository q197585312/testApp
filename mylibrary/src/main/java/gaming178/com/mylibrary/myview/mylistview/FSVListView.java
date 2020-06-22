package gaming178.com.mylibrary.myview.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @ProjectName: [LNH_BASIC]
 * @Package: [com.lnh.ui.FSVListView.java]
 * @ClassName: [FSVListView]
 * @Description: [自定义适合ScrollView的ListView]
 * @Author: lnh-xiaohong
 * @CreateDate: [2015-6-18 下午1:43:27]
 * @UpdateUser: [XiaoHong-LNH]
 * @UpdateDate: [2015-6-18 下午1:43:27]
 * @UpdateRemark: [说明本次修改内容]
 * @Version: [v1.0]
 */
public class FSVListView extends ListView {
	public FSVListView(Context context) {
		super(context);
	}

	public FSVListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FSVListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	/**
	 * 重写该方法，达到使ListView适应ScrollView的效果
	 */
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}