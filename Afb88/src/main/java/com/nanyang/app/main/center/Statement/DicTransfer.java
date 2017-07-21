package com.nanyang.app.main.center.Statement;

import com.nanyang.app.main.center.model.StatementTransferBean;

import java.util.List;

/**
 * Created by Administrator on 2017/7/21.
 */

public class DicTransfer {
    public List<StatementTransferBean> getDicTransfer() {
        return dicTransfer;
    }

    public void setDicTransfer(List<StatementTransferBean> dicTransfer) {
        this.dicTransfer = dicTransfer;
    }

    private List<StatementTransferBean> dicTransfer;


}
