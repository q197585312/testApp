package nanyang.com.dig88.NewKeno.pop;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Entity.BankAccountDetailBean;
import nanyang.com.dig88.Entity.NewKenoBalanceBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.NewKeno.NewKenoActivity;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.ListviewItemOnclick;
import nanyang.com.dig88.Util.MyListviewPopu;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;
import xs.com.mylibrary.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2018/11/23.
 */

public class PopTranfer extends BasePopupWindow {
    @Bind(R.id.tv_from)
    TextView tv_from;
    @Bind(R.id.tv_to)
    TextView tv_to;
    @Bind(R.id.edt_amount)
    EditText edt_amount;
    @Bind(R.id.tv_submit)
    TextView tv_submit;
    @Bind(R.id.tv_cancel)
    TextView tv_cancel;
    @Bind(R.id.tv_balance)
    TextView tv_balance;
    @Bind(R.id.tv_main_balance)
    TextView tv_main_balance;
    String[] transfer;
    String master;
    String newKeno;
    NewKenoActivity activity;
    HttpClient httpClient;
    Gson gson;
    VipInfoBean info;
    String baseUrl;
    NewKenoBalanceBean newKenoBalanceBean;
    VipInfoBean vipInfoBean;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if (newKenoBalanceBean != null) {
                        String balance = StringUtils.floatDecimalFormat(Double.valueOf(newKenoBalanceBean.getBalance()), "0.00").toString();
                        tv_balance.setText(balance);
                    }
                    if (vipInfoBean != null) {
                        tv_main_balance.setText(StringUtils.floatDecimalFormat(Double.valueOf(vipInfoBean.getBalance()), "0.00").toString());
                    }
                    break;
            }
        }
    };
    String newKenoTransferUrl = "http://app.info.dig88api.com/index.php?page=transfer_keno_submitter";
    String from_gameid = "1";
    String to_gameid = "17";
    String amount;

    public PopTranfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_newkeno_transfer;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        httpClient = new HttpClient("");
        gson = new Gson();
        activity = (NewKenoActivity) context;
        master = context.getString(R.string.list1_master_acc);
        newKeno = "NewKeno";
        transfer = new String[]{master, newKeno};
        tv_from.setOnClickListener(new BetOnClickListener());
        tv_to.setOnClickListener(new BetOnClickListener());
        tv_submit.setOnClickListener(new BetOnClickListener());
        tv_cancel.setOnClickListener(new BetOnClickListener());
        info = (VipInfoBean) AppTool.getObjectData(context, "vipInfo");
    }

    public void initNewKeno(String balanceUrl) {
        if (TextUtils.isEmpty(baseUrl)) {
            baseUrl = balanceUrl;
        }
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    String param = "username=" + WebSiteUrl.WebId + "s" + info.getUsername() + "&secret=kg-keno-b7df-4f7a-8ecd-19be&from=android";
                    String s = httpClient.sendPost(baseUrl + param, "");
                    newKenoBalanceBean = gson.fromJson(s, NewKenoBalanceBean.class);
                    String p = "web_id=" + WebSiteUrl.WebId + "&user_id=" + activity.getUserInfoBean().getUser_id() + "&session_id=" + activity.getUserInfoBean().getSession_id();
                    Thread.sleep(1000);
                    String m = httpClient.sendPost(WebSiteUrl.MemberInfoSubmitter, p);
                    NyBaseResponse<VipInfoBean> orgData = gson.fromJson(m, new TypeToken<NyBaseResponse<VipInfoBean>>() {
                    }.getType());
                    vipInfoBean = orgData.getData();
                    if (vipInfoBean != null) {
                        AppTool.saveObjectData(context, "vipInfo", vipInfoBean);
                    }
                    handler.sendEmptyMessage(1);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void submit() {
        activity.showBlockDialog();
        amount = edt_amount.getText().toString().trim();
        String param = "web_id=" + WebSiteUrl.WebId + "&session_id=" + activity.getUserInfoBean().getSession_id() + "&from_gameid=" + from_gameid +
                "&to_gameid=" + to_gameid + "&amt=" + amount + "&id_user=" + activity.getUserInfoBean().getUser_id();
        HttpUtils.httpPost(newKenoTransferUrl, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                if (s.contains("Success")) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
                    edt_amount.setText("");
                    initNewKeno(baseUrl);
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                }
                activity.dismissBlockDialog();
            }

            @Override
            public void onRequestFailed(String s) {
                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                activity.dismissBlockDialog();
            }
        });
    }

    private void cancel() {
        edt_amount.setText("");
        closePopupWindow();
    }

    public abstract class Mydialog extends Dialog implements ListviewItemOnclick {

        public Mydialog(@NonNull Context context, int theme, View v) {
            super(context, theme);
            LinearLayout promotionslayout = (LinearLayout) LayoutInflater.from(context).inflate(
                    R.layout.bank_popwindow, null);
            ListView promotionsLv = (ListView) promotionslayout.findViewById(R.id.lv_dialog);
            promotionsLv.setAdapter(new ArrayAdapter<String>(context,
                    R.layout.item_bank_popwindow, R.id.item_tv, transfer));
            setContentView(promotionslayout);
            Window window = getWindow();
            //设置窗口的属性，以便设设置
            WindowManager.LayoutParams wlp = window.getAttributes();
            int notificationBar = Resources.getSystem().getDimensionPixelSize(Resources.getSystem().getIdentifier("status_bar_height", "dimen", "android"));
            int[] location = new int[2];
            v.getLocationOnScreen(location); //获取在当前窗体内的绝对坐标
            wlp.x = 0;//对 dialog 设置 x 轴坐标
            wlp.y = location[1] + v.getHeight() - notificationBar; //对dialog设置y轴坐标
            wlp.gravity = Gravity.TOP;
            wlp.width = v.getWidth();
            window.setAttributes(wlp);
            promotionsLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    //关闭popupWindow
                    setOnItemClik(arg2);
                    dismiss();
                }
            });
        }
    }

    public class BetOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(final View v) {
            switch (v.getId()) {
                case R.id.tv_from:
                case R.id.tv_to:
                    Mydialog mydialog = new Mydialog(activity, R.style.MyDialog, v) {
                        @Override
                        public void setOnItemClik(int position) {
                            TextView tv = (TextView) v;
                            tv.setText(transfer[position]);
                            if (v.getId() == R.id.tv_from) {
                                if (position == 1) {
                                    from_gameid = "17";
                                    to_gameid = "1";
                                    tv_to.setText(transfer[0]);
                                } else {
                                    from_gameid = "1";
                                    to_gameid = "17";
                                    tv_to.setText(transfer[1]);
                                }
                            } else {
                                if (position == 1) {
                                    from_gameid = "1";
                                    to_gameid = "17";
                                    tv_from.setText(transfer[0]);
                                } else {
                                    from_gameid = "17";
                                    to_gameid = "1";
                                    tv_from.setText(transfer[1]);
                                }
                            }
                        }
                    };
                    mydialog.show();
                    break;
                case R.id.tv_submit:
                    submit();
                    break;
                case R.id.tv_cancel:
                    cancel();
                    break;
            }
        }
    }
}
