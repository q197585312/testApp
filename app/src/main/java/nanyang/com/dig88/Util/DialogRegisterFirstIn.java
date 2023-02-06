package nanyang.com.dig88.Util;

import android.app.AlertDialog;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;

/**
 * Created by Administrator on 2020/1/16.
 */

public abstract class DialogRegisterFirstIn {
    BaseActivity activity;
    TextView tvIn;
    private Dialog dialog;
    public DialogRegisterFirstIn(BaseActivity context) {
        activity = context;
    }

    public void show() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, xs.com.mylibrary.R.style.dialog);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View v = inflater.inflate(R.layout.dialog_register_first_in, null);
        tvIn = v.findViewById(R.id.tv_in);
        tvIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickIn();
                dialog.dismiss();
            }
        });
        builder.setView(v);
        dialog = builder.create();
        dialog.show();
        dialog.setCancelable(false);
    }
    public abstract void onClickIn();
}
