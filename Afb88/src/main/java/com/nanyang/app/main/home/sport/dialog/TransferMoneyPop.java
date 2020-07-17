package com.nanyang.app.main.home.sport.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyang.app.R;
import com.unkonw.testapp.libs.widget.BasePopupWindow;

import butterknife.BindView;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by Administrator on 2015/10/27.
 */
public abstract class TransferMoneyPop extends BasePopupWindow {
    Context context;
    @BindView(R.id.tv_money_balance)
    TextView tv_balance;
    @BindView(R.id.tv_casino_balance)
    TextView tv_casino_balance;
    @BindView(R.id.bet_amount_edt)
    EditText edt_amount;
    @BindView(R.id.tv_cancel_btn)
    TextView tv_cancel;
    @BindView(R.id.tv_sure_btn)
    TextView tv_sure;

    public TransferMoneyPop(Context context, View v) {
        this(context, v, DeviceUtils.dip2px(context, 320), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public TransferMoneyPop(Context mContext, View v, int width, int height) {
        super(mContext, v, width, height);
        this.context = mContext;
        this.v = v;
    }


    @Override
    protected int onSetLayoutRes() {
        return R.layout.popupwindow_transfer_money;
    }


    @Override
    protected void initView(View view) {
        super.initView(view);
        initMsgData(tv_balance, tv_casino_balance, edt_amount);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closePopupWindow();
                setOnCancelListener();
            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setOnSureListener(edt_amount.getText().toString());
            }
        });
    }

    public abstract void initMsgData(TextView tv_balance, TextView tv_casino_balance, EditText edt_amount);

    public abstract void setOnCancelListener();

    public abstract void setOnSureListener(String money);

}
