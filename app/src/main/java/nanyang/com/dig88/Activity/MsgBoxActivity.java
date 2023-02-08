package nanyang.com.dig88.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import cn.finalteam.toolsfinal.StringUtils;
import gaming178.com.mylibrary.base.QuickBaseAdapter;
import gaming178.com.mylibrary.base.ViewHolder;
import nanyang.com.dig88.Entity.MsgTotalDataBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.CalendarPopuWindow;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.PopMsgBox;
import nanyang.com.dig88.Util.UIUtil;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by 47184 on 2019/3/25.
 */

public class MsgBoxActivity extends BaseActivity {

    @BindView(R.id.msg_box_list)
    ListView listView;
    @BindView(R.id.msg_text_count)
    TextView msgTextCount;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_time)
    TextView tv_end_time;
    @BindView(R.id.btn_submit)
    Button btn_submit;
    Handler handler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    MsgTotalDataBean mtd = (MsgTotalDataBean) msg.obj;
                    updateList(mtd);
                    break;
                case 2:
                    MsgRunnable r = new MsgRunnable();
                    Thread thread = new Thread(r);
                    thread.start();
                    break;
            }
        }
    };
    private MsgTotalDataBean msgTotalDataBean;
    private CalendarPopuWindow calendarPopuWindowStart;
    private CalendarPopuWindow calendarPopuWindowEnd;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_msg_box;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        toolbar.setBackgroundResource(0);
        setleftViewEnable(true);
        setTitle(getString(R.string.msg));
        MsgRunnable r = new MsgRunnable();
        Thread thread = new Thread(r);
        thread.start();
        msgLayout.setVisibility(View.GONE);
        tv_start_time.setText("2015-01-01 00:00");
        tv_end_time.setText(getTime() + " 23:59");
        calendarPopuWindowStart = new CalendarPopuWindow(mContext, tv_start_time, screenWidth / 5 * 4, screenWidth / 5 * 4) {
            @Override
            public void getChoiceDateStr(String date) {
                tv_start_time.setText(date);
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                lv_year.setVisibility(View.GONE);
                String format = "yyyy";
                DateFormat df = new SimpleDateFormat(format);
                try {
                    Date d1 = df.parse("2015");
                    long openTime = d1.getTime();
                    calendarView.setDate(openTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        };
        tv_start_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                calendarPopuWindowStart.showPopupCenterWindowBlack();
            }
        });
        calendarPopuWindowEnd = new CalendarPopuWindow(mContext, tv_end_time, screenWidth / 5 * 4, screenWidth / 5 * 4) {
            @Override
            public void getChoiceDateStr(String date) {
                tv_end_time.setText(date);
            }

            @Override
            protected void initView(View view) {
                super.initView(view);
                lv_year.setVisibility(View.GONE);
            }
        };
        tv_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendarPopuWindowEnd.showPopupCenterWindowBlack();
            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBlockDialog();
                String baseParam = "web_id=" + WebSiteUrl.WebId + "&member_id=" + getUserInfoBean().getUser_id() + "&session_id=" + getUserInfoBean().getSession_id() +
                        "&start_date=" + tv_start_time.getText().toString() + "&end_date=" + tv_end_time.getText().toString() + "&type=1";
                HttpUtils.httpPost(WebSiteUrl.MsgBoxUrl, baseParam, new HttpUtils.RequestCallBack() {
                    @Override
                    public void onRequestSucceed(String s) {
                        dismissBlockDialog();
                        onGetMsgData(s);
                    }

                    @Override
                    public void onRequestFailed(String s) {
                        dismissBlockDialog();
                    }
                });
            }
        });
    }

    public void updateList(final MsgTotalDataBean bean) {
        msgTextCount.setText(getString(R.string.total) + bean.getData().size());
        QuickBaseAdapter<MsgTotalDataBean.DataBean> qb = new QuickBaseAdapter<MsgTotalDataBean.DataBean>(mContext, R.layout.item_msg_box, bean.getData()) {
            @Override
            protected void convert(ViewHolder helper, MsgTotalDataBean.DataBean item, int position) {
                TextView msgBoxSystem = helper.retrieveView(R.id.msg_box_system);
                TextView msgBoxDate = helper.retrieveView(R.id.msg_box_date);
                TextView msgBoxRead = helper.retrieveView(R.id.msg_box_read);
                TextView msgBoxText = helper.retrieveView(R.id.msg_box_text);
                msgBoxSystem.setText(item.getCaption());
                Date date = DateUtils.strToDate(item.getDate_time());
                SimpleDateFormat sdf = new SimpleDateFormat("MMMM-dd", getLocalLanguage().equals("zh") ? Locale.CHINESE : Locale.ENGLISH);
                String time = sdf.format(date);
                msgBoxDate.setText(time);
                if (!StringUtils.isEmpty(item.getStatus()) && item.getStatus().equals("0")) {
                    msgBoxRead.setVisibility(View.VISIBLE);
                } else {
                    msgBoxRead.setVisibility(View.INVISIBLE);
                }
                String newContent = UIUtil.delHTMLTag(Html.fromHtml(item.getContent()).toString());
                msgBoxText.setText(newContent.replace("\r\n", "").replace("\n\n", ""));
            }
        };
        int width = screenWidth / 5 * 4;
        int height = screenHeight / 3 * 2;
        final PopMsgBox popMsgBox = new PopMsgBox(mContext, listView, width, height);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MsgTotalDataBean.DataBean dataBean = bean.getData().get(position);
                if (dataBean.getStatus().equals("0")) {
                    MsgReadRunnable mrr = new MsgReadRunnable();
                    mrr.setMsgId(dataBean.getId_mod_msg_member());
                    Thread t = new Thread(mrr);
                    t.start();
                    TextView tv = (TextView) view.findViewById(R.id.msg_box_read);
                    tv.setVisibility(View.INVISIBLE);
                }
                popMsgBox.setMtdb(dataBean);
                popMsgBox.showPopupCenterWindowBlack();
            }
        });
        listView.setAdapter(qb);
    }

    private void onGetMsgData(String data) {
        if (!data.contains("\"data\":0")) {
            msgTotalDataBean = gson.fromJson(data, MsgTotalDataBean.class);
            for (MsgTotalDataBean.DataBean dataBean : msgTotalDataBean.getData()) {
                String htmlStr = dataBean.getContent();
                String spanned = Html.fromHtml(htmlStr).toString();
                dataBean.setContent(spanned);
            }
        } else {
            msgTotalDataBean = new MsgTotalDataBean();
            msgTotalDataBean.setData(new ArrayList<MsgTotalDataBean.DataBean>());
        }
        handler.sendMessage(handler.obtainMessage(1, msgTotalDataBean));
    }

    private String getTime() {
        SimpleDateFormat myFmt = new SimpleDateFormat("yyyy-MM-dd");
        return myFmt.format(new Date());
    }

    class MsgRunnable implements Runnable {
        @Override
        public void run() {
            UserInfoBean userInfoBean = getUserInfoBean();
            if (userInfoBean == null) {
                return;
            }
            String baseParam = "web_id=" + WebSiteUrl.WebId + "&member_id=" + userInfoBean.getUser_id() + "&session_id=" + userInfoBean.getSession_id() + "&type=";
            try {
                String msgTotalDataResult = httpClient.sendPost(WebSiteUrl.MsgBoxUrl, baseParam + "1");
//                {"status":"0","msg":"success","data":0}
                onGetMsgData(msgTotalDataResult);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class MsgReadRunnable implements Runnable {
        private String msgId;

        @Override
        public void run() {
            UserInfoBean userInfoBean = getUserInfoBean();
            if (userInfoBean == null) {
                return;
            }
            String baseParam = "web_id=" + WebSiteUrl.WebId + "&member_id=" + userInfoBean.getUser_id() + "&session_id=" + userInfoBean.getSession_id() + "&type=2" + "&msg_id=";
            try {
                baseParam += msgId;
                String msgTotalDataResult = httpClient.sendPost(WebSiteUrl.MsgReadUrl, baseParam);
                handler.sendEmptyMessage(2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }
    }
}
