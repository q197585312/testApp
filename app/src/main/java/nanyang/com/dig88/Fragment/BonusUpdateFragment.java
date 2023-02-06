package nanyang.com.dig88.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.adapter.BaseRecyclerAdapter;
import com.unkonw.testapp.libs.adapter.MyRecyclerViewHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.BonusUpdateStatuBean;
import nanyang.com.dig88.Entity.ContentInfoBean;
import nanyang.com.dig88.Entity.UpdateBounsInfoBean;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.BaseContentListPopWindow;
import nanyang.com.dig88.Util.HttpClient;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

/**
 * Created by Administrator on 2015/12/21. (申请红利)
 */
public class BonusUpdateFragment extends BaseFragment implements View.OnClickListener {
    private static final int SUBMIT_SUCCESS = 0;
    private static final int SUBMIT_ERROR = 1;
    private static final int SUBMIT_NODATA = 2;
    private static final int SUBMIT_REPEAT = 3;
    @Bind(R.id.rc_content)
    RecyclerView rcContent;
    @Bind(R.id.tv_game_type)
    TextView tv_game_type;
    @Bind(R.id.btn_queren)
    Button btn_queren;
    @Bind(R.id.tv_hint)
    TextView tv_hint;
    String stringgametype;
    String gametype;
    List<ContentInfoBean> nameList;
    BaseRecyclerAdapter<UpdateBounsInfoBean> adapter;
    List<UpdateBounsInfoBean> updateBounsInfoBeenList;
    HttpClient httpClient;
    Gson gson = new Gson();
    Map<String, String> bounsStutusMap;
    private BonusUpdate bonusUpdate = null;
    private Thread threadBonusUpdate = null;
    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SUBMIT_SUCCESS:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.update_bonus_was_successful, Toast.LENGTH_LONG).show();
//                    ((MainTabActivity) mContext).changeFragment(new BonusCenterFragment(), true);
                    break;
                case SUBMIT_ERROR:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.shibai, Toast.LENGTH_LONG).show();
                    break;
                case SUBMIT_REPEAT:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.chongfu1, Toast.LENGTH_LONG).show();
                    break;
                case SUBMIT_NODATA:
                    dismissBlockDialog();
                    Toast.makeText(getActivity(), R.string.nohongli, Toast.LENGTH_LONG).show();
                    break;
                case 8:
                    int position = (int) msg.obj;
                    adapter.notifyItemChanged(position);
                    break;
                default:
                    break;
            }

        }
    };
    Runnable startGetData = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < updateBounsInfoBeenList.size(); i++) {
                UpdateBounsInfoBean updateBounsInfoBean = updateBounsInfoBeenList.get(i);
                String gameType = updateBounsInfoBean.getGameType();
                request(gameType);
            }
        }
    };
    private Handler handler = new Handler();

    @Override
    protected int getFragmentLayoutRes() {
        return R.layout.activity_apply_dividend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        AppTool.setAppLanguage(mContext, "");
        getAct().setTitle(mContext.getString(R.string.shengqinghl));
        getAct().setleftViewEnable(true);
        tv_game_type.setOnClickListener(this);
        btn_queren.setOnClickListener(this);
        if (BuildConfig.FLAVOR.equals("jf58")) {
            tv_hint.setVisibility(View.VISIBLE);
        }
        bounsStutusMap = new HashMap<>();
        updateBounsInfoBeenList = new ArrayList<>();
        if (BuildConfig.FLAVOR.equals("ibet567")) {
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("DG99 " + getString(R.string.live_entertainment), "21"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("Sexy " + getString(R.string.live_entertainment), "24"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("SA " + getString(R.string.live_entertainment), "23"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.Cockfight), "22"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.Keno), "36"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.ongdo_poker), "37"));
        } else if (BuildConfig.FLAVOR.equals("hjlh6688")) {
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.khmergaming), "1"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("AG " + getString(R.string.live_entertainment), "16"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("BestGamer " + getString(R.string.game), "14"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("HABA " + getString(R.string.game), "18"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("PP " + getString(R.string.game), "32"));
        } else {
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.khmergaming), "1"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("GD " + getString(R.string.live_entertainment), "3"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("DG99 " + getString(R.string.live_entertainment), "21"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("WM " + getString(R.string.live_entertainment), "27"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("Sexy " + getString(R.string.live_entertainment), "24"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("AG " + getString(R.string.live_entertainment), "16"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("All Bet " + getString(R.string.live_entertainment), "20"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("Gold " + getString(R.string.live_entertainment), "11"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("SA " + getString(R.string.live_entertainment), "23"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("W88 " + getString(R.string.live_entertainment), "9"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("EVO " + getString(R.string.live_entertainment), "33"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("AFB " + getString(R.string.sports), "4"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("AFB2 " + getString(R.string.sports), "38"));
            String sabaName = "SABA " + getString(R.string.sports);
            if (BuildConfig.FLAVOR.equals("kimsa1")) {
                sabaName = "IBC "+ getString(R.string.sports);
            }
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(sabaName, "17"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("SBO " + getString(R.string.sports), "19"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("BestGamer " + getString(R.string.game), "14"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("HABA " + getString(R.string.game), "18"));
//            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("W88 " + getString(R.string.game), "9"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("MG " + getString(R.string.game), "5"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("PT " + getString(R.string.game), "25"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("Joker " + getString(R.string.game), "28"));
//            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("SA " + getString(R.string.game), "23"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("PP " + getString(R.string.game), "32"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean("RTG " + getString(R.string.game), "31"));
            updateBounsInfoBeenList.add(new UpdateBounsInfoBean(getString(R.string.FFYL_Poker), "34"));
        }
        nameList = new ArrayList<>();
        for (int i = 0; i < updateBounsInfoBeenList.size(); i++) {
            nameList.add(new ContentInfoBean(updateBounsInfoBeenList.get(i).getGameName(), updateBounsInfoBeenList.get(i).getGameType()));
        }
        getBonusData();
        initAdapter();
    }

    private void initAdapter() {
        adapter = new BaseRecyclerAdapter<UpdateBounsInfoBean>(mContext, updateBounsInfoBeenList, R.layout.item_bonus) {
            @Override
            public void convert(MyRecyclerViewHolder holder, int position, UpdateBounsInfoBean item) {
                TextView tvStar = holder.getView(R.id.tv_star);
                TextView tvName = holder.getView(R.id.tv_name);
                ProgressBar loading = holder.getView(R.id.loading);
                tvName.setText(item.getGameName());
                String isHaveBonus = bounsStutusMap.get(item.getGameType());
                if (TextUtils.isEmpty(isHaveBonus)) {
                    tvStar.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.VISIBLE);
                } else {
                    loading.setVisibility(View.INVISIBLE);
                    if (isHaveBonus.equals("1")) {
                        tvStar.setVisibility(View.VISIBLE);
                    } else {
                        tvStar.setVisibility(View.INVISIBLE);
                    }
                }
            }
        };
        rcContent.setLayoutManager(new GridLayoutManager(mContext, 2));
        rcContent.setAdapter(adapter);
    }

    private void getBonusData() {
        httpClient = new HttpClient("");
        HandlerThread thread = new HandlerThread("BonusUpdateThread");
        thread.start();
        handler = new Handler(thread.getLooper());
        handler.post(startGetData);
    }

    private void request(String gameType) {
        try {
            if (getUserInfo() == null || getUserInfo().getUser_id() == null) {
                return;
            }
            String param = "web_id=" + WebSiteUrl.WebId + "&user_id=" + getUserInfo().getUser_id() + "&session_id=" +
                    getUserInfo().getSession_id() + "&game_type=";
            String result = httpClient.sendPost(WebSiteUrl.BonusCheckUrl, param + gameType);
            BonusUpdateStatuBean b = gson.fromJson(result, BonusUpdateStatuBean.class);
            Log.d("getBonusData", "gameType: " + gameType + "---result: " + result);
            if (b.getMsg().equals("1") && b.getCode().equals("1")) {
                String bonus_count_all = b.getBonus_count_all();
                String bonus_count = b.getBonus_count();
                if (!TextUtils.isEmpty(bonus_count_all) && !TextUtils.isEmpty(bonus_count)) {
                    if (Integer.parseInt(bonus_count_all) == 0 && Integer.parseInt(bonus_count) > 0) {
                        bounsStutusMap.put(gameType, "1");
                    } else {
                        bounsStutusMap.put(gameType, "0");
                    }
                } else {
                    bounsStutusMap.put(gameType, "0");
                }
                for (int i = 0; i < updateBounsInfoBeenList.size(); i++) {
                    UpdateBounsInfoBean updateBounsInfoBean = updateBounsInfoBeenList.get(i);
                    String gameType1 = updateBounsInfoBean.getGameType();
                    if (gameType1.equals(gameType)) {
                        mhandler.sendMessage(mhandler.obtainMessage(8, i));
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void yanzheng() {
        stringgametype = tv_game_type.getText().toString().trim();
        if (!stringgametype.equals(getString(R.string.qingxyxm))) {
            showBlockDialog();
            bonusUpdate = new BonusUpdate();
            threadBonusUpdate = new Thread(bonusUpdate);
            threadBonusUpdate.start();
        } else {
            Toast.makeText(getActivity(), R.string.qingyxtype, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_game_type:
                BaseContentListPopWindow popWindow = new BaseContentListPopWindow(mContext, tv_game_type, tv_game_type.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT) {
                    @Override
                    public List<ContentInfoBean> getContentData() {
                        return nameList;
                    }

                    @Override
                    public void onClickItem(int position, ContentInfoBean item) {
                        tv_game_type.setText(item.getContent());
                        gametype = item.getContentId();
                    }
                };
                popWindow.showPopupDownWindow();
                break;
            case R.id.btn_queren:
                yanzheng();
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(startGetData);
    }

    public class BonusUpdate implements Runnable {
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
                    params += "&type=" + gametype;
                    String returnVaule = httpClient.sendPost(WebSiteUrl.BonusUpdate, params);
                    Gson gson = new Gson();
                    NyBaseResponse<String> orgData;
                    orgData = gson.fromJson(returnVaule, new TypeToken<NyBaseResponse<String>>() {
                    }.getType());
                    switch (orgData.getMsg()) {
                        case "1":
                            mhandler.sendEmptyMessage(SUBMIT_SUCCESS);
                            break;
                        case "-1":
                            mhandler.sendEmptyMessage(SUBMIT_ERROR);
                            break;
                        case "-2":
                            mhandler.sendEmptyMessage(SUBMIT_REPEAT);
                            break;
                        case "-3":
                            mhandler.sendEmptyMessage(SUBMIT_NODATA);
                            break;
                        case "-5":
                            mhandler.sendEmptyMessage(SUBMIT_ERROR);
                            break;
                        default:
                            mhandler.sendEmptyMessage(SUBMIT_ERROR);
                            break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
