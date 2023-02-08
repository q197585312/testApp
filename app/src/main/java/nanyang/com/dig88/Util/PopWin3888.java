package nanyang.com.dig88.Util;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.OnClick;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2020/3/12.
 */

public class PopWin3888 extends BasePopupWindow {
    TextView tvContent;

    public PopWin3888(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_win3888;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        tvContent = view.findViewById(R.id.tv_content);
        String content = "THÔNG  BÁO quý khách  khuyến mãi sẽ có những  thay đổi.\n" +
                "\n" +
                "-Hoàn trả có một số thay đổi quý khách vui lòng truy cập tham khảo ạ.\n" +
                "\n" +
                "xin quý khách có thể truy cập website thường xuyên để biết thêm thông tin.\n" +
                "\n" +
                "khách  hàng có thể liên hệ bộ phận CSKH để được tư vấn và giải đáp.\n" +
                "\n" +
                "Xin chân thành cảm ơn!";
        tvContent.setText(Dig88Utils.handleStringColor(content));
    }

    @OnClick({R.id.tv_exit, R.id.tv_close})
    public void onClick() {
        closePopupWindow();
    }
}