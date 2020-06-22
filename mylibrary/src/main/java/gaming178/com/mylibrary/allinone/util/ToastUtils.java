package gaming178.com.mylibrary.allinone.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import gaming178.com.mylibrary.R;

/**
 * Created by Administrator on 2016/9/20.
 */

public class ToastUtils {
    public  static void showBetSuccessToast(Context context, String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.def_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(msg);
        final Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, 1000);
    }

    public  static void showToast(Context context, String msg){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.def_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(msg);

        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public  static void showToast(Context context, String msg,int color ){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.def_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(msg);
        chapterNameTV.setTextColor(color);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public  static void showWinningToast(Context context, String msg, int color){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.win_toast, null);
        TextView chapterNameTV = (TextView) view.findViewById(R.id.chapterName);
        chapterNameTV.setText(msg);
        chapterNameTV.setTextColor(color);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }

    public static void showBackToast(Context context, String msg1, String msg2) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.normal_toast, null);
        TextView tv_content1 = (TextView) view.findViewById(R.id.tv_content1);
        TextView tv_content2 = (TextView) view.findViewById(R.id.tv_content2);
        tv_content1.setText(msg1);
        tv_content2.setText(msg2);
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(view);
        toast.show();
    }
}
