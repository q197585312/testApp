package nanyang.com.dig88.Lottery;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/8/22.
 */

public class PopLotteryContent extends BasePopupWindow {
    @Bind(R.id.tv_content)
    TextView tv_content;

    public PopLotteryContent(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_lottery_content;
    }

    public void setData(String content) {
        String str1 = content.replace("^", " ");
        String str2 = str1.replace("#", "x");
        tv_content.setText(str2);
    }

    @OnClick({R.id.btn_submit})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_submit:
                submit();
                break;
        }
    }

    public void submit() {

    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
    }
}
