package nanyang.com.dig88.NewKeno;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.R;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/9/14.
 */

public class PopNewKenoPrint extends BasePopupWindow {
    @Bind(R.id.tv_ticket_id)
    TextView tv_ticket_id;
    @Bind(R.id.tv_date)
    TextView tv_date;
    @Bind(R.id.tv_account)
    TextView tv_account;
    @Bind(R.id.tv_ticket_id1)
    TextView tv_ticket_id1;
    @Bind(R.id.tv_type)
    TextView tv_type;
    @Bind(R.id.tv_bet_points)
    TextView tv_bet_points;
    @Bind(R.id.tv_win_lose)
    TextView tv_win_lose;
    public PopNewKenoPrint(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    private void initData(String dataStr) {
        String[] splitStr = dataStr.split("#");
        tv_ticket_id.setText("Ticket Id :" + splitStr[splitStr.length - 1]);
        tv_date.setText("Date :" + splitStr[1]);
        tv_account.setText("Account :" + splitStr[splitStr.length - 5]);
        tv_ticket_id1.setText("TicketID :" + splitStr[0]);
        tv_type.setText("Type :" + splitStr[splitStr.length - 4]);
        tv_bet_points.setText("Bet Points :" + splitStr[splitStr.length - 3]);
        tv_win_lose.setText("WinLose :" + splitStr[splitStr.length - 2]);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_new_keno_print;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
    }


    public void setData(String dataStr) {
        initData(dataStr);
    }
}
