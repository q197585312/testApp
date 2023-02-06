package nanyang.com.dig88.Wheel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Calendar;

import nanyang.com.dig88.R;

public class SelectBirthday extends PopupWindow implements OnClickListener {

	public static String age;
	public    Activity ttContext=null;
	Time time = new Time("GMT+8");
	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;
	String shijian;
	private View mMenuView;
	private ViewFlipper viewfipper;
	private Button btn_submit, btn_cancel;
	private  DateNumericAdapter monthAdapter, dayAdapter, yearAdapter;
	private WheelView year, month, day;
	private int mCurYear = 80, mCurMonth = 5, mCurDay = 14;
	private String[] dateType;
	public SelectBirthday(Activity context) {
		super(context);
		ttContext = context;
		sharedPreferences=ttContext.getSharedPreferences("datetime",Context.MODE_PRIVATE);
		editor=sharedPreferences.edit();
		int year1 = time.year;
		int month1 = time.month;
		int day1 = time.monthDay;
		String year2=String.valueOf(year1);
		String month2=String.valueOf(month1);
		String day2=String.valueOf(day1);
//		this.age =year2+"-"+month2+"-"+day2;
		this.age ="2015-12-26";
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.datepick, null);
		viewfipper = new ViewFlipper(context);
		viewfipper.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));
		year = (WheelView) mMenuView.findViewById(R.id.year);
		month = (WheelView) mMenuView.findViewById(R.id.month);
		day = (WheelView) mMenuView.findViewById(R.id.day);
		btn_submit = (Button) mMenuView.findViewById(R.id.submit);
		btn_cancel = (Button) mMenuView.findViewById(R.id.cancel);
		btn_submit.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		Calendar calendar = Calendar.getInstance();
		OnWheelChangedListener listener = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updateDays(year, month, day);

			}
		};
		int curYear = calendar.get(Calendar.YEAR);
		if (age != null && age.contains("-")) {
			String str[] = age.split("-");
			mCurYear = 100 - (curYear - Integer.parseInt(str[0]));
			mCurMonth = Integer.parseInt(str[1]) - 1;
			mCurDay = Integer.parseInt(str[2]) - 1;
			;
		}
		dateType = ttContext.getResources().getStringArray(R.array.date);
		monthAdapter = new DateNumericAdapter(context, 1, 12, 5);
		monthAdapter.setTextType(dateType[1]);
		month.setViewAdapter(monthAdapter);
		month.setCurrentItem(mCurMonth);
		month.addChangingListener(listener);
		// year
		yearAdapter = new DateNumericAdapter(context, curYear - 100, curYear+100,
				100 - 20);
		yearAdapter.setTextType(dateType[0]);
		year.setViewAdapter(yearAdapter);
		year.setCurrentItem(mCurYear);
		year.addChangingListener(listener);
		// day
		updateDays(year, month, day);
		day.setCurrentItem(mCurDay);
		updateDays(year, month, day);
		day.addChangingListener(listener);
		viewfipper.addView(mMenuView);
		viewfipper.setFlipInterval(6000000);
		this.setContentView(viewfipper);
		this.setWidth(LayoutParams.FILL_PARENT);
		this.setHeight(LayoutParams.WRAP_CONTENT);
		this.setFocusable(true);
		ColorDrawable dw = new ColorDrawable(0x00000000);
		this.setBackgroundDrawable(dw);
		this.update();

	}
	@Override
	public void showAtLocation(View parent, int gravity, int x, int y) {
		super.showAtLocation(parent, gravity, x, y);
		viewfipper.startFlipping();
	}

	public  void updateDays(WheelView year, WheelView month, WheelView day) {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR) + year.getCurrentItem());
		calendar.set(Calendar.MONTH, month.getCurrentItem());
		int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		dayAdapter = new DateNumericAdapter(ttContext, 1, maxDays,
				calendar.get(Calendar.DAY_OF_MONTH) - 1);
		dayAdapter.setTextType(dateType[2]);
		day.setViewAdapter(dayAdapter);
		int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		day.setCurrentItem(curDay - 1, true);
		int years = calendar.get(Calendar.YEAR) - 100;
		age = years + "-" + (month.getCurrentItem() + 1) + "-"
				+ (day.getCurrentItem() + 1);


	}

	public void onClick(View v) {
		this.dismiss();
		switch (v.getId()) {
			case R.id.cancel:
				this.dismiss();
				updateDays(year, month, day);
				break;
			case R.id.submit:
				this.dismiss();
				updateDays(year, month, day);
				editor.putString("datetime", age);
				editor.commit();


				break;
			default:
				break;
		}
	}

	/**
	 * Adapter for numeric wheels. Highlights the current value.
	 */
	private class DateNumericAdapter extends NumericWheelAdapter {
		// Index of current item
		int currentItem;
		// Index of item to be highlighted
		int currentValue;

		/**
		 * Constructor
		 */
		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(24);
		}

		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			view.setTypeface(Typeface.SANS_SERIF);
		}

		public CharSequence getItemText(int index) {
			currentItem = index;
			return super.getItemText(index);
		}

	}


}
