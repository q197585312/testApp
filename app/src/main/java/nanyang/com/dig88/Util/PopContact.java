package nanyang.com.dig88.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/8/8.
 */

public class PopContact extends BasePopupWindow {
    @Bind(R.id.tv_and)
    TextView tv_and;

    public PopContact(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_scr;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        tv_and.setText(" & ");
    }

    @OnClick({R.id.tv_link1, R.id.tv_link2, R.id.tv_live_chat,R.id.tv_tel1,R.id.tv_tel2})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_link1:
                start("http://dol.918kiss.com/");
                break;
            case R.id.tv_link2:
                start("http://dm.918kiss.com/");
                break;
            case R.id.tv_live_chat:
                start("https://secure.livechatinc.com/licence/9655365/v2/open_chat.cgi?");
                break;
            case R.id.tv_tel1:
                start("https://api.whatsapp.com/send?phone=60132679999&text=I%20want%20create%20918Kiss%20Account.");
                break;
            case R.id.tv_tel2:
                start("https://api.whatsapp.com/send?phone=6282260029999&text=Saya%20ingin%20mendaftar%20akun%20918Kiss.");
                break;
        }
    }

    private void start(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
