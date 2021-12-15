package com.nanyang.app.main.DepositAndWithdraw;

import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nanyang.app.R;
import com.nanyang.app.Utils.DateUtils;
import com.nanyang.app.main.DepositAndWithdraw.Bean.WithdrawHistoryBean;
import com.nanyang.app.main.home.sport.dialog.CalendarPop;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DepositHistoryFragment extends DepositWithdrawBaseFragment {
    @BindView(R.id.tv_from_date)
    TextView tvFromDate;
    @BindView(R.id.tv_to_date)
    TextView tvToDate;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;
    @BindView(R.id.rc_content)
    RecyclerView rcContent;
    @BindView(R.id.fl_no_data)
    FrameLayout flNoData;
    BaseRecyclerAdapter<WithdrawHistoryBean> adapter;

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

    @Override
    public void onGetDepositHistoryData(List<WithdrawHistoryBean> list) {
        if (list != null && list.size() > 0) {
            flNoData.setVisibility(View.GONE);
            rcContent.setVisibility(View.VISIBLE);
            if (adapter == null) {
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                rcContent.setLayoutManager(manager);
                adapter = new BaseRecyclerAdapter<WithdrawHistoryBean>(mContext, list, R.layout.item_withdraw_history) {
                    @Override
                    public void convert(MyRecyclerViewHolder holder, int position, WithdrawHistoryBean item) {
                        TextView tvWay = holder.getTextView(R.id.tv_way);
                        TextView tvDate = holder.getTextView(R.id.tv_date);
                        TextView tvAmount = holder.getTextView(R.id.tv_amount);
                        TextView tvStatus = holder.getTextView(R.id.tv_status);
                        View viewLine = holder.getView(R.id.view_line);
                        tvWay.setText(item.getDescription());
                        tvDate.setText(item.getPayDate());
                        tvAmount.setText("ATM: " + item.getAmt());
                        String status = item.getStatus();
                        if (status.equals("Pending")) {
                            tvStatus.setTextColor(Color.parseColor("#FFA500"));
                        } else if (status.equals("Rejected")) {
                            tvStatus.setTextColor(Color.RED);
                        } else {
                            tvStatus.setTextColor(Color.parseColor("#4CAF50"));
                        }
                        tvStatus.setText(status);
                        if (position != list.size() - 1) {
                            viewLine.setVisibility(View.VISIBLE);
                        } else {
                            viewLine.setVisibility(View.GONE);
                        }
                    }
                };
                rcContent.setAdapter(adapter);
            } else {
                adapter.setData(list);
            }
        } else {
            flNoData.setVisibility(View.VISIBLE);
            rcContent.setVisibility(View.GONE);
        }
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
                String fromDate = tvFromDate.getText().toString().trim();
                String toDate = tvToDate.getText().toString().trim();
                if (!TextUtils.isEmpty(fromDate) && !TextUtils.isEmpty(toDate)) {
                    presenter.getDepositHistoryData(fromDate, toDate);
                }
                break;
        }
    }
}
