package com.nanyang.app.main.home.sport.dialog;

import android.widget.TextView;

/**
 * Created by Administrator on 2020/1/13.
 */

public class CursorEditView {
    String itemSocId;
    TextView EditView;

    public CursorEditView(String itemSocId, TextView editView) {
        this.itemSocId = itemSocId;
        EditView = editView;
    }

    public String getItemSocId() {
        return itemSocId;
    }

    public TextView getEditView() {
        return EditView;
    }
}
