package nanyang.com.dig88.Fragment.Presenter;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.UpdateBirthdayBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Entity.VipInfoBean;
import nanyang.com.dig88.Fragment.BasicFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Table.entity.GameMenuItem;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.DateUtils;
import nanyang.com.dig88.Util.Dig88Utils;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;
import xs.com.mylibrary.allinone.util.StringUtils;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/1.
 */

public class BasicPresenter extends BaseRetrofitPresenter<BasicFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    BasicFragment basicFragment;

    public BasicPresenter(BasicFragment iBaseContext) {
        super(iBaseContext);
        basicFragment = iBaseContext;
    }

    public List<GameMenuItem> getContentData(VipInfoBean vipInfoBean) {
        List<GameMenuItem> list = new ArrayList<>();
        list.add(new GameMenuItem(R.drawable.zhanghaoxiang, baseContext.getString(R.string.zhanghum), vipInfoBean.getUsername()));
        if (!BuildConfig.FLAVOR.equals("q2bet")) {
            if (BuildConfig.FLAVOR.equals("kimsa1")) {
                list.add(new GameMenuItem(R.drawable.huiyuanyue, baseContext.getString(R.string.zhanghuyue1), StringUtils.format2F(vipInfoBean.getBalance()) + "(" + basicFragment.getCurrency() + ")"));
            } else {
                list.add(new GameMenuItem(R.drawable.tuijianxiang, baseContext.getString(R.string.tuijianlianjie), getRef(vipInfoBean)));
            }
        }
        list.add(new GameMenuItem(R.drawable.email, baseContext.getString(R.string.email), vipInfoBean.getEmail()));
        list.add(new GameMenuItem(R.drawable.dianhua, baseContext.getString(R.string.dianhua), vipInfoBean.getTel()));
        if (BuildConfig.FLAVOR.equals("kbet789")) {
            String address = vipInfoBean.getAddress();
            if (TextUtils.isEmpty(address)) {
                address = DateUtils.getCurrentTime("yyyy-MM-dd");
            }
            list.add(new GameMenuItem(R.drawable.cunkuanzhanghuxiang, baseContext.getString(R.string.date_of_birth), address));
        }
        list.add(new GameMenuItem(R.drawable.cunkuanzhanghuxiang, baseContext.getString(R.string.qukuanzh), vipInfoBean.getBank_acc_name2()));
        list.add(new GameMenuItem(R.drawable.qukuancenter, baseContext.getString(R.string.qukuanzho), vipInfoBean.getBank_acc_no2()));
        String bankName = vipInfoBean.getBank_name();
        if (BuildConfig.FLAVOR.equals("onegold77") && !TextUtils.isEmpty(bankName) && bankName.equals("Other")) {
            String address = vipInfoBean.getAddress();
            if (address.contains("#")) {
                bankName = address.split("#")[1];
            }
        }
        list.add(new GameMenuItem(R.drawable.yinghangxiang, baseContext.getString(R.string.Withdrawal_Bank), bankName));
//        initW88AppId(list);
        if (!BuildConfig.FLAVOR.equals("q2bet")) {
            list.add(new GameMenuItem(R.drawable.honglicenter, baseContext.getString(R.string.leijijiang), StringUtils.format2F(vipInfoBean.getTotal_refer())));
            list.add(new GameMenuItem(R.drawable.honglicenter, baseContext.getString(R.string.shengyujiang), StringUtils.format2F(vipInfoBean.getLaster_refer())));
            if (BuildConfig.FLAVOR.equals("kimsa1")) {
                list.add(new GameMenuItem(R.drawable.tuijianxiang, baseContext.getString(R.string.tuijianlianjie), getRef(vipInfoBean)));
            } else {
                list.add(new GameMenuItem(R.drawable.huiyuanyue, baseContext.getString(R.string.zhanghuyue1), StringUtils.format2F(vipInfoBean.getBalance()) + "(" + basicFragment.getCurrency() + ")"));
            }
        }
        list.add(new GameMenuItem(R.drawable.time, baseContext.getString(R.string.Registration_Time), basicFragment.getUserInfo().getOpen_time()));
        list.add(new GameMenuItem(R.drawable.login_time, baseContext.getString(R.string.Last_Login), basicFragment.getUserInfo().getLogin_time()));
        return list;
    }

    private void initW88AppId(List<GameMenuItem> list) {
        if (WebSiteUrl.WebId.equals("995") || WebSiteUrl.WebId.equals("78") || WebSiteUrl.WebId.equals("48") || WebSiteUrl.WebId.equals("98") ||
                WebSiteUrl.WebId.equals("42") || WebSiteUrl.WebId.equals("69") || WebSiteUrl.WebId.equals("22") ||
                WebSiteUrl.WebId.equals("74") || WebSiteUrl.WebId.equals("233") || WebSiteUrl.WebId.equals("36") || WebSiteUrl.WebId.equals("34") ||
                WebSiteUrl.WebId.equals("30") || WebSiteUrl.WebId.equals("19") || WebSiteUrl.WebId.equals("71") ||
                WebSiteUrl.WebId.equals("51") || WebSiteUrl.WebId.equals("21") || WebSiteUrl.WebId.equals("44") ||
                WebSiteUrl.WebId.equals("28") || WebSiteUrl.WebId.equals("29") || WebSiteUrl.WebId.equals("57") ||
                WebSiteUrl.WebId.equals("45") || WebSiteUrl.WebId.equals("35") || WebSiteUrl.WebId.equals("32") || WebSiteUrl.WebId.equals("47")) {
            list.add(new GameMenuItem(R.drawable.qukuancenter, "W88 APP ID", basicFragment.getUserInfo().getUser_id()));
        }
    }

    private String getRef(VipInfoBean vipInfoBean) {
        String ref = WebSiteUrl.Ref + vipInfoBean.getUsername();
        if (BuildConfig.FLAVOR.equals("k9th")) {
            if (basicFragment.getCurrency().equals("IDR") || getLanguage().equals("in")) {
                ref = "http://m.k9id-v2.com/index.php?page=register&ref=" + vipInfoBean.getUsername();
            } else if (basicFragment.getCurrency().equals("USD") || getLanguage().equals("kh")) {
                ref = "http://m.k9winkh.com/index.php?page=register&ref=" + vipInfoBean.getUsername();
            }
        }
//        if (!TextUtils.isEmpty(basicFragment.getApp().getAffiliateId()) && !TextUtils.isEmpty(basicFragment.getApp().getAffiliateWeb())) {
//            String doMain = basicFragment.getApp().getAffiliateWeb();
//            if (doMain.contains(";")) {
//                String[] split = doMain.split(";");
//                ref = "http://" + split[1] + "/?ref=" + vipInfoBean.getUsername();
//            }
//        }
        return ref;
    }

    public void updateBirthday(String birthday) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", basicFragment.getUserInfo().getUser_id());
        p.put("session_id", basicFragment.getUserInfo().getSession_id());
        p.put("dob", birthday);
        p.put("type", "address");
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Dig88Url + "index.php?page=update_mem_info_submitter", p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                UpdateBirthdayBean updateBirthdayBean = gson.fromJson(data, UpdateBirthdayBean.class);
                String errMsg = updateBirthdayBean.getErrMsg();
                if (errMsg.equals("Success") || errMsg.equals("success")) {
                    getVipInfoBean(basicFragment.getUserInfo());
                } else {
                    basicFragment.onUpdateBirthdayFinish(basicFragment.getString(R.string.Failed));
                }
            }

        });

    }

    public void getVipInfoBean(UserInfoBean userInfoBean) {
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", userInfoBean.getUser_id());
        p.put("session_id", userInfoBean.getSession_id());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.MemberInfoSubmitter, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<VipInfoBean> dataBean = gson.fromJson(data, new TypeToken<NyBaseResponse<VipInfoBean>>() {
                }.getType());
                VipInfoBean vipInfoBean = dataBean.getData();
                AppTool.saveObjectData(baseContext.getBaseActivity(), "vipInfo", vipInfoBean);
                basicFragment.onUpdateBirthdayFinish(basicFragment.getString(R.string.Success));
            }
        });
    }

    private String getLanguage() {
        return Dig88Utils.getLanguage(basicFragment.getContext());
    }
}
