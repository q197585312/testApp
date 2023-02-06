package nanyang.com.dig88.Lottery4D.popWindow;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanyang.com.dig88.Lottery4D.Lottery4DActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ListviewItemOnclick;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/2/25.
 */

public class PopSpecialDateSwitch extends BasePopupWindow {
    public String currentDate;
    public int currentCount;
    Lottery4DActivity activity;
    @Bind(R.id.tv_choice_date)
    TextView tv_choice_date;
    List<String> dateList;
    public PopSpecialDateSwitch(Context context, View v, int width, int height) {
        super(context, v, width, height);
        activity = (Lottery4DActivity) context;
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_specoal_date_switch;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this.view);
    }

    public void setContent(List<String> list) {
        dateList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String date = list.get(i);
            String[] split = date.split("#");
            dateList.add(split[1]);
        }
        currentDate = dateList.get(0);
        currentCount = 1;
        tv_choice_date.setText(currentDate);
    }

    @OnClick({R.id.tv_exit, R.id.tv_choice_date})
    public void OnClick(View v) {
        switch (v.getId()) {
            case R.id.tv_exit:
                closePopupWindow();
                break;
            case R.id.tv_choice_date:
                Mydialog mydialog = new Mydialog(context, R.style.MyDialog, v) {
                    @Override
                    public void setOnItemClik(int position) {
                        currentDate = dateList.get(position);
                        tv_choice_date.setText(currentDate);
                        currentCount = 0;
                        for (int i = 0; i < dateList.size(); i++) {
                            if (activity.isBetSingleDate()) {
                                if (!currentDate.equals(dateList.get(i))) {
                                    continue;
                                }
                            }
                            currentCount++;
                            if (currentDate.equals(dateList.get(i))) {
                                break;
                            }
                        }
                    }
                };
                mydialog.show();
                break;
        }
    }

    @Override
    public boolean isNeedHide() {
        return false;
    }

    public abstract class Mydialog extends Dialog implements ListviewItemOnclick {

        public Mydialog(@NonNull Context context, int theme, View v) {
            super(context, theme);
            LinearLayout promotionslayout = (LinearLayout) LayoutInflater.from(context).inflate(
                    R.layout.bank_popwindow, null);
            ListView promotionsLv = (ListView) promotionslayout.findViewById(R.id.lv_dialog);
            promotionsLv.setAdapter(new ArrayAdapter<String>(context,
                    R.layout.item_bank_popwindow, R.id.item_tv, dateList));
            setContentView(promotionslayout);
            Window window = getWindow();
            //设置窗口的属性，以便设设置
            WindowManager.LayoutParams wlp = window.getAttributes();
            int notificationBar = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            int[] location = new int[2];
            v.getLocationOnScreen(location); //获取在当前窗体内的绝对坐标
            wlp.x = 0;//对 dialog 设置 x 轴坐标
            wlp.y = location[1] + v.getHeight() - notificationBar; //对dialog设置y轴坐标
            wlp.gravity = Gravity.TOP;
            wlp.width = v.getWidth();
            window.setAttributes(wlp);
            promotionsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    //关闭popupWindow
                    setOnItemClik(arg2);
                    dismiss();
                }
            });
        }
    }
}
