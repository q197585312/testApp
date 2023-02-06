package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Adapter.ApplyDividendListAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.ApplyDividendListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import nanyang.com.dig88.Util.BlockDialog;

/**
 * Created by Administrator on 2015/12/21. (申请红利列表)
 */
public class BonusApplyListFragment extends BaseFragment implements View.OnClickListener {
    private static final int SUBMIT_SUCCESS = 0;
    private static final int SUBMIT_ERROR = 1;
    private static final int SUBMIT_NODATA = 2;
    @Bind(R.id.tv_start_time)
    TextView tv_start_time;
    @Bind(R.id.tv_end_time)
    TextView tv_end_time;
    @Bind(R.id.btn_queren)
    Button btn_queren;
    @Bind(R.id.lv_apply_deposit)
    ListView lv_apply_deposit;
    ApplyDividendListAdapter applyDividendListAdapter;
    String starttime;
    String endtime;
    private List<ApplyDividendListBean> listData = new ArrayList<ApplyDividendListBean>();

    private BonusRequestList bonusRequestList = null;
    private Thread threadBonusRequestList = null;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUBMIT_SUCCESS:
                    applyDividendListAdapter = new ApplyDividendListAdapter(getActivity(), listData);
                    lv_apply_deposit.setAdapter(applyDividendListAdapter);
                    break;
            }
            dismissBlockDialog();
        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_apply_dividend_list;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        getAct().setTitle(getString(R.string.shengqingtitle));
        getAct().setleftViewEnable(true);
        btn_queren.setOnClickListener(this);
        tv_start_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CalendarPopuWindow calendarPopuWindow = new CalendarPopuWindow(mContext, btn_queren, ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight / 2) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tv_start_time.setText(date.trim());
                    }

                };
                calendarPopuWindow.showPopupCenterWindowBlack();
            }
        });
        tv_end_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CalendarPopuWindow calendarPopuWindow = new CalendarPopuWindow(mContext, btn_queren, ViewGroup.LayoutParams.WRAP_CONTENT, screenHeight / 2) {
                    @Override
                    public void getChoiceDateStr(String date) {
                        tv_end_time.setText(date.trim());
                    }

                };
                calendarPopuWindow.showPopupCenterWindowBlack();
            }
        });
        tv_start_time.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 00:00");
        tv_end_time.setText(DateUtils.getCurrentTime("yyyy-MM-dd") + " 23:59");
        yanzheng();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_queren:
                yanzheng();
                break;
        }
    }

    /**
     * 验证
     */
    private void yanzheng() {
        starttime = tv_start_time.getText().toString().trim();
        endtime = tv_end_time.getText().toString().trim();
        if ((starttime.length() != 0) && endtime.length() != 0) {
            //  applydividend();
            getAct().setDialog(new BlockDialog(getActivity(), getString(R.string.zhengjiazai)));
            showBlockDialog();
            bonusRequestList = new BonusRequestList();
            threadBonusRequestList = new Thread(bonusRequestList);
            threadBonusRequestList.start();
        } else {
            Toast.makeText(getActivity(), R.string.qingxshijian, Toast.LENGTH_LONG).show();
        }
    }

    public class BonusRequestList implements Runnable {
        public void run() {
            try {
                HttpClient httpClient = null;
                if (getApp().getHttpClient() == null) {
                    httpClient = new HttpClient(WebSiteUrl.AutonumberStatus, "");
                    if (httpClient.connect("POST") == false) {
                        return;
                    } else {
                        httpClient.getSessionId();
                    }
                } else {
                    httpClient = getApp().getHttpClient();
                }
                if (httpClient != null) {
                    String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
                    params += "&date_start=" + starttime;
                    params += "&date_end=" + endtime;
                    String returnVaule = httpClient.sendPost(WebSiteUrl.BonusRequestList, params);
                    Gson gson = new Gson();
                    NyBaseResponse<List<ApplyDividendListBean>> orgData;
                    orgData = gson.fromJson(returnVaule, new TypeToken<NyBaseResponse<List<ApplyDividendListBean>>>() {
                    }.getType());
                    listData.clear();
                    List<ApplyDividendListBean> data = orgData.getData();
                    if (data == null || data.size() == 0) {
                        ApplyDividendListBean applyDividendListBean = new ApplyDividendListBean();
                        applyDividendListBean.setEnd_time("-1");
                        listData.add(applyDividendListBean);
                    } else {
                        listData = data;
                    }
                    handler.sendEmptyMessage(SUBMIT_SUCCESS);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
