package com.unkonw.testapp.libs.widget;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.unkonw.testapp.R;

public class DialogLoading extends Dialog {

    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context);
        setContentView(R.layout.loading_process_dialog_color);

    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }

}