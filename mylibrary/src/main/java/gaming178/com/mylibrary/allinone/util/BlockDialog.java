package gaming178.com.mylibrary.allinone.util;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import gaming178.com.mylibrary.R;


public class BlockDialog extends Dialog {
    private static int default_width = 150; // 默认宽度
    private static int default_height = 80;// 默认高度
    private String msg;

    // 设置默认高度为160，宽度120，并且可根据屏幕像素密度自动进行大小调整
    public BlockDialog(Context context) {
        this(context, default_width, default_height,
                R.layout.layout_block_dialog_demo, R.style.Theme_dialog);
        this.setCanceledOnTouchOutside(false);
    }

    public BlockDialog(Context context, String message) {
        this(context, default_width, default_height,
                R.layout.layout_block_dialog_demo, R.style.Theme_dialog);
        this.setCanceledOnTouchOutside(false);
        this.msg=message;
        TextView tv = (TextView) findViewById(R.id.progress_dialog_tv);
        if (!TextUtils.isEmpty(message)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(message);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        this.msg=msg;
        TextView tv = (TextView) findViewById(R.id.progress_dialog_tv);
        if (!TextUtils.isEmpty(msg)) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(msg);
        } else {
            tv.setVisibility(View.GONE);
        }
    }

    public void setOutCancle( boolean outCancle) {
        this.setCanceledOnTouchOutside(outCancle);// 设置点击周围会不会消失, false 不消失
    }

    public BlockDialog(Context context, int width, int height, int layout,
                       int style) {
        super(context, style);

        setContentView(layout);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        float density = ScreenUtil.getDisplayMetrics(context).density;
        params.width = (int) (width * density);
        params.height = (int) (height * density);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }

}
