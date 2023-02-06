package gaming178.com.casinogame.Chat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;


import java.util.ArrayList;
import java.util.List;

import gaming178.com.baccaratgame.R;
import gaming178.com.mylibrary.myview.indicator.CirclePageIndicator;

/**
 * 
 ****************************************** 
 * @文件名称 : FaceRelativeLayout.java
 * @创建时间 : 2013-1-27 下午02:34:17
 * @文件描述 : 带表情的自定义输入框
 ****************************************** 
 */
public class FaceRelativeLayout extends LinearLayout implements
		OnItemClickListener {

	private Context context;

	/** 表情页的监听事件 */
	private OnCorpusSelectedListener mListener;

	/** 显示表情页的viewpager */
//	private ViewPager vp_face;

	/** 表情页界面集合 */
	private ArrayList<View> pageViews;

	/** 游标显示布局 */
	private LinearLayout layout_point;

	/** 游标点集合 */
//	private ArrayList<ImageView> pointViews;

	/** 表情集合 */
	private List<ChatEmoji> emojis;
	private GridView gvFace;
	private ItemCallBack itemBack;
	/** 输入框 */
	private EditText edtMessage;
	/** 当前表情页 */
	private int current = 0;

	/** 表情数据填充器 */
//	private List<FaceAdapter> faceAdapters;
	private CirclePageIndicator indicator;
	private InputMethodManager inputManager;

	public FaceRelativeLayout(Context context) {
		super(context);
		this.context = context;
	}


	public FaceRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}

	public FaceRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		this.context = context;
	}

	public void setEdtMessage(EditText edtMessage) {
		this.edtMessage = edtMessage;
	}

	public void setOnCorpusSelectedListener(OnCorpusSelectedListener listener) {
		mListener = listener;
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		emojis = FaceConversionUtil.getInstace().emojis;
		onCreate();
	}

	private void onCreate() {
		inputManager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		Init_View();
	}

	/**
	 * 初始化控件
	 */
	private void Init_View() {
		gvFace= (GridView) findViewById(R.id.gd__gv_face_choose);
//		vp_face = (ViewPager) findViewById(R.id.vp_contains);
//		layout_point = (LinearLayout) findViewById(R.id.iv_image);
		FaceAdapter adapter = new FaceAdapter(context, emojis);
		gvFace.setAdapter(adapter);

		gvFace.setOnItemClickListener(this);
		gvFace.setNumColumns(8);
		gvFace.setBackgroundColor(Color.TRANSPARENT);
		gvFace.setHorizontalSpacing(1);
		gvFace.setVerticalSpacing(1);
		gvFace.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
		gvFace.setCacheColorHint(0);
		gvFace.setPadding(5, 0, 5, 0);
		gvFace.setSelector(new ColorDrawable(Color.TRANSPARENT));
		gvFace.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT));
		gvFace.setGravity(Gravity.CENTER);
	}


	/**
	 * 隐藏表情选择框
	 */
/*	public boolean hideFaceView() {
		// 隐藏表情选择框
		if (view.getVisibility() == View.VISIBLE) {
			view.setVisibility(View.GONE);
			return true;
		}
		return false;
	}*/

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		ChatEmoji emoji =  emojis.get(arg2);
		if (emoji.getId() == R.drawable.gd_face_del_icon) {
			int selection = edtMessage.getSelectionStart();
			String text = edtMessage.getText().toString();
			if (selection > 0) {
				String text2 = text.substring(selection - 1);
				if ("]".equals(text2)) {
					int start = text.lastIndexOf("[");
					int end = selection;
					edtMessage.getText().delete(start, end);
					return;
				}
				edtMessage.getText().delete(selection - 1, selection);
			}
		}
		if (!TextUtils.isEmpty(emoji.getCharacter())) {
			if (mListener != null)
				mListener.onCorpusSelected(emoji);
			SpannableString spannableString = FaceConversionUtil.getInstace()
					.addFace(getContext(), emoji.getId(), emoji.getCharacter());
			edtMessage.append(spannableString);
			if(itemBack!=null){
				itemBack.clickItem(emoji);
			}
		}

	}

	public void setItemCallBack(ItemCallBack back){
			this.itemBack=back;
	}
	/**
	 * 表情选择监听
	 *
	 * @author naibo-liao
	 * @时间： 2013-1-15下午04:32:54
	 */
	public interface OnCorpusSelectedListener {

		void onCorpusSelected(ChatEmoji emoji);

		void onCorpusDeleted();
	}
	public interface  ItemCallBack{
		void clickItem(ChatEmoji emoji);
	}
}
