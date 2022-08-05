package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopLogout extends BasePopupWindow {
    BaseActivity activity;
    Button btnSure, btnCancel;
    private ImageView img_exit;

    public PopLogout(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_logout;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        img_exit = view.findViewById(R.id.gd__img_exit);
        btnSure = view.findViewById(R.id.gd__btn_pop_deposit_confirm);
        btnCancel = view.findViewById(R.id.gd__btn_pop_deposit_cancel);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        btnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.logout();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        activity = (BaseActivity) context;
    }
}
