package nanyang.com.dig88.SlotsGame.Pop;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import gaming178.com.mylibrary.allinone.util.AppTool;
import gaming178.com.mylibrary.allinone.util.StringUtils;
import gaming178.com.mylibrary.base.quick.QuickRequestBean;
import gaming178.com.mylibrary.popupwindow.BasePopupWindow;
import nanyang.com.dig88.Activity.BaseActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Base.NyVolleyJsonThreadHandler;
import nanyang.com.dig88.Entity.JokerBalanceBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.HttpUtils;
import nanyang.com.dig88.Util.ListviewItemOnclick;
import nanyang.com.dig88.Util.WebSiteUrl;

/**
 * Created by Administrator on 2018/11/23.
 */

public abstract class PopJokerTranfer extends BasePopupWindow {
    @BindView(R.id.tv_from)
    TextView tv_from;
    @BindView(R.id.tv_to)
    TextView tv_to;
    @BindView(R.id.edt_amount)
    EditText edt_amount;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    @BindView(R.id.tv_enter)
    TextView tv_enter;
    @BindView(R.id.tv_joker123_balance)
    TextView tv_joker123_balance;
    @BindView(R.id.tv_main_balance)
    TextView tv_main_balance;
    @BindView(R.id.ll_is_show)
    LinearLayout llIsShow;
    @BindView(R.id.cb_is_show)
    CheckBox cbIsShow;
    String[] transfer;
    String master;
    String joker;
    BaseActivity activity;
    UserInfoBean u;
    Gson gson = new Gson();
    VipInfoBean info;
    String joker123BalanceUrl = "http://joker123.k-api.com/getbalance.php?";
    String newKenoTransferUrl = "http://joker123.k-api.com/transfer.php?";
    String from_gameid = "1";
    String to_gameid = "11";
    String amount;
    public PopJokerTranfer(Context context, View v, int width, int height) {
        super(context, v, width, height);
    }

    @Override
    protected int getContentViewLayoutRes() {
        return R.layout.pop_newkeno_transfer1;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        ButterKnife.bind(this, view);
        activity = (BaseActivity) context;
        master = context.getString(R.string.list1_master_acc);
        joker = "Joker123";
        transfer = new String[]{master, joker};
        tv_to.setText(joker);
        tv_from.setOnClickListener(new BetOnClickListener());
        tv_to.setOnClickListener(new BetOnClickListener());
        tv_submit.setOnClickListener(new BetOnClickListener());
        tv_enter.setOnClickListener(new BetOnClickListener());
        info = (VipInfoBean) AppTool.getObjectData(context, "vipInfo");
        u = activity.getUserInfoBean();
        llIsShow.setVisibility(View.VISIBLE);
        llIsShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = cbIsShow.isChecked();
                cbIsShow.setChecked(!checked);
            }
        });
        cbIsShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                activity.getApp().setIsShowJokerPop(!isChecked);
            }
        });
    }

    public void initJoker123() {
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername();
        HttpUtils.httpPost(joker123BalanceUrl, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                if (s.contains("code\":-1")) {
                    tv_joker123_balance.setText("0");
                } else {
                    JokerBalanceBean jokerBalanceBean = gson.fromJson(s, JokerBalanceBean.class);
                    tv_joker123_balance.setText(jokerBalanceBean.getCredit() + "");
                }
                activity.hideLoadingDialog();
            }

            @Override
            public void onRequestFailed(String s) {
                activity.dismissBlockDialog();
            }
        });

    }

    private void submit() {
        activity.showLoadingDialog();
        amount = edt_amount.getText().toString().trim();
        String param = "web_id=" + WebSiteUrl.WebId + "&username=" + info.getUsername() + "&amount=" + amount + "&from=" + from_gameid + "&to=" + to_gameid;
        HttpUtils.httpPost(newKenoTransferUrl, param, new HttpUtils.RequestCallBack() {
            @Override
            public void onRequestSucceed(String s) {
                getVipInfo();
                if (s.contains("Succeed") || s.contains("No Error")) {
                    ToastUtils.showShort(context.getString(R.string.Success));
                } else {
                    ToastUtils.showShort(context.getString(R.string.Failed));
                }
            }

            @Override
            public void onRequestFailed(String s) {
                ToastUtils.showShort("Failed");
            }
        });
    }

    //转账后更新帐号余额
    public void getVipInfo() {
        NyVolleyJsonThreadHandler<VipInfoBean> registThread = new NyVolleyJsonThreadHandler<VipInfoBean>(context) {
            @Override
            protected QuickRequestBean getNyRequestBean(HashMap<String, String> params) {
                params.put("web_id", WebSiteUrl.WebId);
                params.put("user_id", u.getUser_id());
                params.put("session_id", u.getSession_id());
                return new QuickRequestBean(WebSiteUrl.MemberInfoSubmitter, params
                        , new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
            }

            @Override
            protected void successEndT(int total, VipInfoBean data) {
                if (data != null) {
                    AppTool.saveObjectData(context, "vipInfo", data);
                    updataAccunt(data);
                }
            }
        };
        registThread.startThread(null);
    }

    public void initUi() {
        updataAccunt(info);
    }

    public abstract void enterGame();

    public void updataAccunt(VipInfoBean data) {
        if (this == null || data == null)
            return;
        try {
            double zhanghuyue = Double.valueOf(data.getBalance().toString());
            String result = StringUtils.floatDecimalFormat(zhanghuyue);
            tv_main_balance.setText(result);
            initJoker123();
            activity.showMoney(result);
        } catch (Exception e) {

        }
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
                                    from_gameid = "11";
                                    to_gameid = "1";
                                    tv_to.setText(transfer[0]);
                                } else {
                                    from_gameid = "1";
                                    to_gameid = "11";
                                    tv_to.setText(transfer[1]);
                                }
                            } else {
                                if (position == 1) {
                                    from_gameid = "1";
                                    to_gameid = "11";
                                    tv_from.setText(transfer[0]);
                                } else {
                                    from_gameid = "11";
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
                case R.id.tv_enter:
                    closePopupWindow();
                    enterGame();
                    break;
            }
        }
    }
}
