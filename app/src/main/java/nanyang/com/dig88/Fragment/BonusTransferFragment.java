package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import nanyang.com.dig88.Adapter.DividendRecordListAdapter;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.DividendCenterBean;
import nanyang.com.dig88.Entity.DividendCenterListBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Util.BlockDialog;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.base.ViewHolder;
import xs.com.mylibrary.popupwindow.AbsListPopupWindow;

/**
 * Created by Administrator on 2015/12/21. (红利中心1)
 */
public class BonusTransferFragment extends BaseFragment implements View.OnClickListener {
    private static final int SUBMIT_LIST_SUCCESS = 0;
    private static final int SUBMIT_LIST_ERROR = 1;
    private static final int SUBMIT_LIST_NODATA = 2;
    private static final int SUBMIT_SUCCESS = 3;
    private static final int SUBMIT_USER_ERROR = 4;
    private static final int SUBMIT_LIMIT_ERROR = 5;
    private static final int SUBMIT_REPEAT_ERROR = 6;
    public BlockDialog dialog;
    public List<DividendCenterListBean> listData = new ArrayList<DividendCenterListBean>();
    @Bind(R.id.tv_leixing)
    TextView tv_leixing;
    @Bind(R.id.et_jine)
    EditText et_jine;
    @Bind(R.id.btn_tijiao)
    Button btn_tijiao;
    @Bind(R.id.lv_hongli_center)
    ListView lv_hongli_center;
    DividendRecordListAdapter dividendRecordListAdapter;
    String stringjine;
    HttpClient httpClient;
    private BonusList bonusList = null;
    private Thread threadBonusList = null;
    private Bonus bonus = null;
    private Thread threadBonus = null;
    private ArrayList<GameMenuItem> bonusData;
    private GameMenuItem bonusSelectedItem;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUBMIT_LIST_SUCCESS:
                    dismissBlockDialog();
                    dividendRecordListAdapter = new DividendRecordListAdapter(getActivity(), listData);
                    lv_hongli_center.setAdapter(dividendRecordListAdapter);
                    break;
                case SUBMIT_LIST_ERROR:
                    dismissBlockDialog();
                    break;
                case SUBMIT_SUCCESS:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.shenqingsuccess, Toast.LENGTH_LONG).show();
                    break;
                case SUBMIT_USER_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.huiyuanbupipei, Toast.LENGTH_LONG).show();
                    break;
                case SUBMIT_REPEAT_ERROR:
                    dismissBlockDialog();

                    Toast.makeText(getActivity(), R.string.chongfu, Toast.LENGTH_LONG).show();
                    break;
                case SUBMIT_LIMIT_ERROR:
                    dismissBlockDialog();
                    if ("4".equals(bonusSelectedItem.getValue())) {
                        Toast.makeText(getActivity(), R.string.withdraw_bonus_error, Toast.LENGTH_LONG).show();
                    } else
                        Toast.makeText(getActivity(), R.string.chongfu, Toast.LENGTH_LONG).show();
                    break;

                default:

                    break;
            }

        }
    };

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_dividend_center_item;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        httpClient = new HttpClient("");
        et_jine.setKeyListener(new DigitsKeyListener(false, true) {
            @Override
            protected char[] getAcceptedChars() {
                char[] numberChars = {'.', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                return numberChars;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        AppTool.setAppLanguage(mContext, "");
        bonusData = new ArrayList<>();
        bonusData.add(new GameMenuItem(0, getString(R.string.zhuankuanhl), "5"));
        if (!BuildConfig.FLAVOR.equals("fun77") && !BuildConfig.FLAVOR.equals("jf58") && !BuildConfig.FLAVOR.equals("villabetting")) {
            bonusData.add(new GameMenuItem(0, getString(R.string.withdraw_bonus), "4"));
        }
        bonusSelectedItem = bonusData.get(0);
        getAct().setTitle(getString(R.string.honglizx));
        dialog = new BlockDialog(getActivity(), getString(R.string.zhengjiazai));
        getAct().setleftViewEnable(true);
        btn_tijiao.setOnClickListener(this);
        tv_leixing.setOnClickListener(this);
        showBlockDialog();
        bonusList = new BonusList();
        threadBonusList = new Thread(bonusList);
        threadBonusList.start();
    }

    private void yanzheng() {
        stringjine = et_jine.getText().toString().trim();
        if (stringjine.length() != 0) {
            double min = 50;
            if (BuildConfig.FLAVOR.equals("fun77")) {
                min = 5;
            } else if (BuildConfig.FLAVOR.equals("gasia88")) {
                if (bonusSelectedItem.getValue().equals("5")) {
                    min = 0.1;
                }
            } else if (BuildConfig.FLAVOR.equals("asap888")) {
                min = 0.1;
            } else if (BuildConfig.FLAVOR.equals("dig88") || BuildConfig.FLAVOR.equals("mmbet")) {
                min = 1;
            }else if (BuildConfig.FLAVOR.equals("istana168") || BuildConfig.FLAVOR.equals("betting234")) {
                min = 20;
            }
            if (Double.parseDouble(stringjine) < min) {
                Toast.makeText(getActivity(), getString(R.string.transfer_bouns_error) + " "+min, Toast.LENGTH_LONG).show();
                return;
            }
            getAct().setDialog(new BlockDialog(getActivity(), getString(R.string.zhengjiazai)));
            showBlockDialog();
            bonus = new Bonus();
            threadBonus = new Thread(bonus);
            threadBonus.start();
        } else {
            Toast.makeText(getActivity(), R.string.shurujine, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_tijiao:
                yanzheng();
                break;
            case R.id.tv_leixing:
                showPop(view);
                break;

        }
    }

    private void showPop(View v) {
        AbsListPopupWindow<GameMenuItem> pop = new AbsListPopupWindow<GameMenuItem>(mContext, v) {
            @Override
            protected void popItemCLick(GameMenuItem gameMenuItem, int position) {
                bonusSelectedItem = gameMenuItem;
                tv_leixing.setText(bonusSelectedItem.getTitle());
            }

            @Override
            protected void convertItem(ViewHolder helper, GameMenuItem item, int position) {
                helper.setText(R.id.item_tv, item.getTitle());
            }

            @Override
            protected int getItemLayoutRes() {
                return R.layout.item_bank_popwindow;
            }

            @Override
            protected int getListViewId() {
                return R.id.lv_dialog;
            }

            @Override
            protected int getContentViewLayoutRes() {
                return R.layout.bank_popwindow;
            }
        };
        pop.setData(bonusData);
        pop.showPopupDownWindow();
    }

    public class BonusList implements Runnable {
        public void run() {
            try {
                String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
                String returnVaule = httpClient.sendPost(WebSiteUrl.BonusList, params);
                Gson gson = new Gson();
                NyBaseResponse<List<DividendCenterListBean>> orgData;
                orgData = gson.fromJson(returnVaule, new TypeToken<NyBaseResponse<List<DividendCenterListBean>>>() {
                }.getType());
                List<DividendCenterListBean> data = orgData.getData();
                if (data == null || data.size() == 0) {
                    DividendCenterListBean dividendCenterListBean = new DividendCenterListBean();
                    dividendCenterListBean.setTime("-1");
                    listData.add(dividendCenterListBean);
                } else {
                    for (int i = 0; i < data.size(); i++) {
                        listData.add(data.get(i));
                    }
                }
                handler.sendEmptyMessage(SUBMIT_LIST_SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class Bonus implements Runnable {
        public void run() {
            try {
                String params = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" + getUserInfo().getSession_id();
                params += "&amount=" + stringjine;
                params += "&bonus_type=" + bonusSelectedItem.getValue();
                String returnVaule = httpClient.sendPost(WebSiteUrl.Bonus, params);
                Gson gson = new Gson();
                NyBaseResponse<DividendCenterBean> orgData;
                orgData = gson.fromJson(returnVaule, new TypeToken<NyBaseResponse<DividendCenterBean>>() {
                }.getType());
                switch (orgData.getMsg()) {
                    case "1":
                        handler.sendEmptyMessage(SUBMIT_SUCCESS);
                        break;
                    case "-1":
                        handler.sendEmptyMessage(SUBMIT_LIMIT_ERROR);
                        break;
                    case "-2":
                        handler.sendEmptyMessage(SUBMIT_REPEAT_ERROR);
                        break;
                    case "-5":
                        handler.sendEmptyMessage(SUBMIT_USER_ERROR);
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
