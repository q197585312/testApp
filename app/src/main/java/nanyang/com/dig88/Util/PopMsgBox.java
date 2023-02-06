package nanyang.com.dig88.Util;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import nanyang.com.dig88.Entity.MsgTotalDataBean;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by 47184 on 2019/3/25.
 */
public class PopMsgBox extends BasePopupWindow {

    MsgTotalDataBean.DataBean mtdb;
    private TextView title;
    private TextView appname;
    private TextView date;
    private TextView name;
    private TextView content;
    private WebView webView;

    public PopMsgBox(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_msg_box;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        title = (TextView) view.findViewById(R.id.pop_msg_box_title);
        appname = (TextView) view.findViewById(R.id.pop_msg_box_app_name);
        date = (TextView) view.findViewById(R.id.pop_msg_box_date);
        name = (TextView) view.findViewById(R.id.pop_msg_box_name);
        content = (TextView) view.findViewById(R.id.pop_msg_box_content);
        webView = (WebView) view.findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
    }

    public MsgTotalDataBean.DataBean getMtdb() {
        return mtdb;
    }

    public void setMtdb(MsgTotalDataBean.DataBean mtdb) {
        this.mtdb = mtdb;
        title.setText(mtdb.getCaption());
//        appname.setText(context.getString(R.string.app_name));
        date.setText(mtdb.getDate_time());
        name.setText(mtdb.getCaption());
        String contentStr = mtdb.getContent();
        content.setText(contentStr);
//        if (contentStr.contains("#ffffff")) {
//            webView.setBackgroundColor(Color.BLACK);
//        } else {
//            webView.setBackgroundColor(Color.WHITE);
//        }
        webView.loadData(contentStr, "text/html; charset=UTF-8", null);
    }
}
