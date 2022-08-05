package gaming178.com.casinogame.Popupwindow;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import gaming178.com.baccaratgame.R;
import gaming178.com.casinogame.Util.ChangePasswordHelper;
import gaming178.com.casinogame.base.BaseActivity;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2020/5/21.
 */

public class PopChangePassword extends BasePopupWindow {
    private BaseActivity baseActivity;
    private ImageView img_exit;
    private TextView tvSure;
    private TextView tvName;

    public PopChangePassword(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.gd_pop_change_password;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        baseActivity = (BaseActivity) context;
        img_exit = view.findViewById(R.id.gd__img_exit);
        tvSure = view.findViewById(R.id.gd__tv_ok);
        img_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
            }
        });
        tvName = view.findViewById(R.id.gd_pop_name);
        tvName.setText("User Name: " + baseActivity.mAppViewModel.getUser().getName());
        EditText edt_old_passwrod = (EditText) view.findViewById(R.id.gd__edt_old_passwrod);
        EditText edt_new_passwrod = (EditText) view.findViewById(R.id.gd__edt_new_passwrod);
        EditText edt_confirm_passwrod = (EditText) view.findViewById(R.id.gd__edt_confirm_passwrod);
        tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = edt_old_passwrod.getText().toString();
                String newPassword = edt_new_passwrod.getText().toString();
                String confirmPassword = edt_confirm_passwrod.getText().toString();
                ChangePasswordHelper changePasswordHelper = new ChangePasswordHelper(oldPassword, newPassword, confirmPassword, baseActivity);
                changePasswordHelper.changePassword();
            }
        });
    }
}
