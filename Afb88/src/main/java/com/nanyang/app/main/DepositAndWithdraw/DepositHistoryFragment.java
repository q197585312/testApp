package com.nanyang.app.main.DepositAndWithdraw;

import android.view.View;
import android.widget.TextView;

import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.home.sport.dialog.CalendarPop;

import butterknife.BindView;
import butterknife.OnClick;

public class DepositHistoryFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_from_date)
    TextView tvFromDate;
    @BindView(R.id.tv_to_date)
    TextView tvToDate;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    public int onSetLayoutId() {
        return R.layout.fragment_deposit_history;
    }

    @Override
    public void initView() {
        super.initView();
        tvFromDate.setText(DateUtils.getCurrentDate("dd/MM/yyyy"));
        tvToDate.setText(DateUtils.getCurrentDate("dd/MM/yyyy"));
    }

    @OnClick({R.id.tv_from_date, R.id.tv_to_date, R.id.tv_submit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_from_date:
                CalendarPop fromPop = new CalendarPop(mContext, tvFromDate);
                fromPop.setTvContent(tvFromDate);
                fromPop.showPopupDownWindow();
                break;
            case R.id.tv_to_date:
                CalendarPop toPop = new CalendarPop(mContext, tvToDate);
                toPop.setTvContent(tvToDate);
                toPop.showPopupDownWindow();
                break;
            case R.id.tv_submit:
                break;
        }
    }
}
