package com.unkonw.testapp.base;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

public class DialogLoading extends Dialog {

    private TextView loadingLabel;

    public DialogLoading(Context context) {
        super(context);

    }

    public void setDialogLabel(String label) {
        loadingLabel.setText(label);
    }

}