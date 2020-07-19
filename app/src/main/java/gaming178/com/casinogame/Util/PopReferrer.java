package gaming178.com.casinogame.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import gaming178.com.baccaratgame.BuildConfig;
import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Activity.LobbyActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/7/19.
 */

public class PopReferrer extends BasePopupWindow {
    public PopReferrer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    TextView tvContent, tv_open, tv_copy;

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_referrer;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    tvContent.setText((String) msg.obj);
                    break;
            }
        }
    };

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvContent = (TextView) view.findViewById(R.id.gd__tv_content);
        tv_open = (TextView) view.findViewById(R.id.gd__tv_open);
        tv_copy = (TextView) view.findViewById(R.id.gd__tv_copy);
        tv_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = tvContent.getText().toString();
                if (!TextUtils.isEmpty(address)) {
                    startWeb(address);
                    closePopupWindow();
                }
            }
        });
        tv_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = tvContent.getText().toString();
                if (!TextUtils.isEmpty(address)) {
                    ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cm.setText(address);
                    Toast.makeText(context, context.getString(R.string.Copy_Success), Toast.LENGTH_SHORT).show();
                }
            }
        });
        new Thread() {
            @Override
            public void run() {
                String url = WebSiteUrl.HEADER + WebSiteUrl.PROJECT + "getRefPlayer.jsp";
                String param = "labelid=" + BuildConfig.Labelid;
                LobbyActivity a = (LobbyActivity) context;
                if (a.mAppViewModel.getHttpClient() != null) {
                    String s = a.mAppViewModel.getHttpClient().sendPost(url, param);
                    handler.sendMessage(handler.obtainMessage(1, s));
                }
            }
        }.start();
    }

    private void startWeb(String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }
}
