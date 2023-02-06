package nanyang.com.dig88.Util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.R;

/**
 * Created by s7528 on 2017/03/16.
 */

public abstract class CalendarPopuWindow extends BasePopupWindow {
    public ListView listView, lv_year;
    public CalendarView calendarView;
    public int currentYear;
    private List<String> data;
    private List<String> yearDataList;
    private TextView tv_exit, tvCancel, tvSure;
    private String yearMonthDay = "";
    private String hourMinute = "";
    private int clickPositon = -1;
    private QuickBaseAdapter<String> adapter;

    public CalendarPopuWindow(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.layout_calendar;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        initListViewData();
        tvCancel = view.findViewById(R.id.tv_cancel);
        tvSure = view.findViewById(R.id.tv_sure);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                getChoiceDateStr(yearMonthDay + " " + hourMinute);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tv_exit = (TextView) view.findViewById(R.id.tv_exit);
        tv_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        listView = (ListView) view.findViewById(R.id.listview_time);
        lv_year = (ListView) view.findViewById(R.id.lv_year);
        calendarView = (CalendarView) view.findViewById(R.id.calender);
        adapter = new QuickBaseAdapter<String>(context, R.layout.item_calendar_tiem, data) {

            @Override
            protected void convert(ViewHolder helper, final String item, final int position) {
                TextView time = helper.retrieveView(R.id.tv_time);
                LinearLayout ll_item = helper.retrieveView(R.id.ll_item);
                if (position == clickPositon) {
                    ll_item.setBackgroundColor(context.getResources().getColor(R.color.title_bg_color));
                    time.setTextColor(Color.WHITE);
                } else {
                    ll_item.setBackgroundColor(Color.WHITE);
                    time.setTextColor(Color.BLACK);
                }
                time.setText(item);
                ll_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        hourMinute = item;
                        clickPositon = position;
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        listView.setAdapter(adapter);
        QuickBaseAdapter<String> yearAdapter = new QuickBaseAdapter<String>(context, R.layout.item_calendar_tiem, yearDataList) {
            @Override
            protected void convert(ViewHolder helper, final String item, int position) {
                TextView time = helper.retrieveView(R.id.tv_time);
                LinearLayout ll_item = helper.retrieveView(R.id.ll_item);
                time.setText(item);
                ll_item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String format = "yyyy";
                        DateFormat df = new SimpleDateFormat(format);
                        try {
                            Date d1 = df.parse(item);
                            long openTime = d1.getTime();
                            calendarView.setDate(openTime);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        lv_year.setAdapter(yearAdapter);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                int m = month + 1;
                String mStr;
                if (m < 10) {
                    mStr = "0" + m;
                } else {
                    mStr = m + "";
                }
                yearMonthDay = year + "-" + mStr + "-" + dayOfMonth;
            }
        });
    }

    public void setYearMonthDay(String yearMonthDay) {
        this.yearMonthDay = yearMonthDay;
    }

    public void setHourMinute(String hourMinute) {
        this.hourMinute = hourMinute;
    }

    private void initListViewData() {
        data = Arrays.asList("00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30",
                "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30",
                "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
                "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30");
        yearDataList = new ArrayList<>();
        currentYear = Integer.parseInt(DateUtils.getCurrentTime("yyyy"));
        for (int i = 1950; i <= currentYear; i++) {
            yearDataList.add(i + "");
        }
    }

    public abstract void getChoiceDateStr(String date);
}
