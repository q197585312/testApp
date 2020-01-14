package com.nanyang.app.main.home.sport.dialog;

import android.widget.EditText;

/**
 * Created by Administrator on 2020/1/13.
 */

public class CursorEditView {
    String itemSocId;
    EditText EditView;

    public CursorEditView(String itemSocId, EditText editView) {
        this.itemSocId = itemSocId;
        EditView = editView;
    }

    public String getItemSocId() {
        return itemSocId;
    }

    public EditText getEditView() {
        return EditView;
    }
}
