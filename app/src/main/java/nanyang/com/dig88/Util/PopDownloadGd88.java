package nanyang.com.dig88.Util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2019/2/19.
 */

public class PopDownloadGd88 extends BasePopupWindow {
    @Bind(R.id.tv_content)
    TextView tv_content;
    private String downloadUrl;

    public PopDownloadGd88(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_download_gd88;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
    }

    public void setContent(String content) {
        tv_content.setText(content);
    }

    public void setDownLoadUrl(String downLoadUrl) {
        this.downloadUrl = downLoadUrl;
    }

    @OnClick({R.id.tv_download})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_download:
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(downloadUrl);
                intent.setData(content_url);
                context.startActivity(intent);
                closePopupWindow();
                break;
        }
    }
}
