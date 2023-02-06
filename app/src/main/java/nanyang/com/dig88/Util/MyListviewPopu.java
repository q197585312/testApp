package nanyang.com.dig88.Util;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;

import nanyang.com.dig88.R;


/**
 * Created by Administrator on 2017/3/16.
 */

public abstract class MyListviewPopu implements ListviewItemOnclick {
    PopupWindow promotionsPou;
    String[] promotions;
    ListView promotionsLv;
    View parent;
    private LinearLayout promotionslayout;
    private Context context;

    public MyListviewPopu(String[] promotions, Context context, View parent) {
        this.promotions = promotions;
        this.context = context;
        this.parent = parent;
    }

    public void showPromotionsPopupWindow() {
        //加载布局
        promotionslayout = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.bank_popwindow, null);
        promotionsPou = new PopupWindow(promotionslayout, parent.getWidth(), WindowManager.LayoutParams.WRAP_CONTENT);
        //找到布局的控件
        promotionsLv = (ListView) promotionslayout.findViewById(R.id.lv_dialog);
        //设置适配器
        promotionsLv.setAdapter(new ArrayAdapter<String>(context,
                R.layout.item_bank_popwindow, R.id.item_tv, promotions));
        // 实例化popupWindow
        //控制键盘是否可以获得焦点
        promotionsPou.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        promotionsPou.setBackgroundDrawable(new BitmapDrawable(null, ""));
        promotionsPou.showAsDropDown(parent, 0, 0);
        //监听
        promotionsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                promotionsPou.dismiss();
                promotionsPou = null;
                setOnItemClik(arg2);
            }
        });
    }


    public void showUp(int hight) {
        //加载布局
        promotionslayout = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.bank_popwindow, null);
        promotionsPou = new PopupWindow(promotionslayout, parent.getWidth(), hight);
        //找到布局的控件
        promotionsLv = (ListView) promotionslayout.findViewById(R.id.lv_dialog);
        //设置适配器
        promotionsLv.setAdapter(new ArrayAdapter<String>(context,
                R.layout.item_bank_popwindow, R.id.item_tv, promotions));
        // 实例化popupWindow
        //控制键盘是否可以获得焦点
        promotionsPou.setFocusable(true);
        //设置popupWindow弹出窗体的背景
        promotionsPou.setBackgroundDrawable(new BitmapDrawable(null, ""));
        //获取需要在其上方显示的控件的位置信息
        //显示在上方
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        //显示在上方
        promotionsPou.showAtLocation(parent, Gravity.NO_GRAVITY, location[0], location[1]-promotionsPou.getHeight());
        //监听
        promotionsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                //关闭popupWindow
                promotionsPou.dismiss();
                promotionsPou = null;
                setOnItemClik(arg2);
            }
        });
    }

}
