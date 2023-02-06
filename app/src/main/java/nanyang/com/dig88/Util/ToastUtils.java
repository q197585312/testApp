package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2019/3/21.
 */

public class ToastUtils {
    public static void MyToast(Context context) {
        Toast toast = new Toast(context);
        View v = LayoutInflater.from(context).inflate(R.layout.toast_view, null);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, Gravity.CENTER_VERTICAL, 0);
        toast.setView(v);
        toast.show();
    }
}
