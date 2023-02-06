package nanyang.com.dig88.Home.Presenter;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nanyang.com.dig88.BuildConfig;
import nanyang.com.dig88.Entity.UserContentBean;
import nanyang.com.dig88.Entity.UserInfoBean;
import nanyang.com.dig88.Fragment.BasicFragment;
import nanyang.com.dig88.Fragment.BetRecordFragment;
import nanyang.com.dig88.Fragment.BonusCenterFragment;
import nanyang.com.dig88.Fragment.BonusTransferFragment;
import nanyang.com.dig88.Fragment.ChangePasswordFragment;
import nanyang.com.dig88.Fragment.CompleteRecordFragment;
import nanyang.com.dig88.Fragment.DepositCenterFragment;
import nanyang.com.dig88.Fragment.RunningWaterFragment;
import nanyang.com.dig88.Fragment.TransferAccFragment;
import nanyang.com.dig88.Fragment.WithdrawCenterFragment;
import nanyang.com.dig88.Home.MenuDepositFragment;
import nanyang.com.dig88.Home.MenuUserFragment;
import nanyang.com.dig88.Home.MenuWithdrawFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;
import xs.com.mylibrary.allinone.util.AppTool;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/1.
 */

public class MenuUserPresenter extends BaseRetrofitPresenter<MenuUserFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    MenuUserFragment menuUserFragment;

    public MenuUserPresenter(MenuUserFragment iBaseContext) {
        super(iBaseContext);
        menuUserFragment = iBaseContext;
    }

    public List<UserContentBean> getContentData() {
        List<UserContentBean> list = new ArrayList<>();
        list.add(new UserContentBean(baseContext.getString(R.string.jibenxinxi), BasicFragment.class.getName()));
        list.add(new UserContentBean(baseContext.getString(R.string.xiumima), ChangePasswordFragment.class.getName()));
        if (BuildConfig.FLAVOR.equals("q2bet") || BuildConfig.FLAVOR.equals("ttwin168") ||
                BuildConfig.FLAVOR.equals("u2bet") || BuildConfig.FLAVOR.equals("mcd88") ||
                BuildConfig.FLAVOR.equals("club988") || BuildConfig.FLAVOR.equals("afbcash") ||
                BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("win3888") ||
                BuildConfig.FLAVOR.equals("khmergaming")) {
            list.add(new UserContentBean(baseContext.getString(R.string.cunkuanzx), DepositCenterFragment.class.getName()));
        } else if (BuildConfig.FLAVOR.equals("k9th") && menuUserFragment.getCurrency().equals("THB")) {
            list.add(new UserContentBean(baseContext.getString(R.string.cunkuanzx), DepositCenterFragment.class.getName()));
        } else {
            if (!BuildConfig.FLAVOR.equals("my2bet")) {
                list.add(new UserContentBean(baseContext.getString(R.string.cunkuanzx), MenuDepositFragment.class.getName()));
            }
        }
        if (BuildConfig.FLAVOR.equals("funplay26") || BuildConfig.FLAVOR.equals("mcd88") ||
                BuildConfig.FLAVOR.equals("onegold77") || BuildConfig.FLAVOR.equals("afbcash") ||
                BuildConfig.FLAVOR.equals("hjlh6688") || BuildConfig.FLAVOR.equals("xslot88")) {
            list.add(new UserContentBean(baseContext.getString(R.string.qukuanzx), WithdrawCenterFragment.class.getName()));
        } else {
            if (!BuildConfig.FLAVOR.equals("my2bet")) {
                list.add(new UserContentBean(baseContext.getString(R.string.qukuanzx), MenuWithdrawFragment.class.getName()));
            }
        }
        list.add(new UserContentBean(baseContext.getString(R.string.transferacc), TransferAccFragment.class.getName()));
        if (!BuildConfig.FLAVOR.equals("jf58")) {
            list.add(new UserContentBean(baseContext.getString(R.string.xiahzujilu), BetRecordFragment.class.getName()));
            list.add(new UserContentBean(baseContext.getString(R.string.wancenjilu), CompleteRecordFragment.class.getName()));
        }
        if (BuildConfig.FLAVOR.equals("fun77")) {
            list.add(new UserContentBean(baseContext.getString(R.string.honglizx), BonusTransferFragment.class.getName()));
        } else {
            list.add(new UserContentBean(baseContext.getString(R.string.honglizx), BonusCenterFragment.class.getName()));
        }
        if (!BuildConfig.FLAVOR.equals("betting234") && !BuildConfig.FLAVOR.equals("istana168")) {
            list.add(new UserContentBean(baseContext.getString(R.string.check_wash_code), RunningWaterFragment.class.getName()));
        }
        if (BuildConfig.FLAVOR.equals("kimsa1")) {
            list.add(new UserContentBean(baseContext.getString(R.string.notification), ""));
        }
        list.add(new UserContentBean(baseContext.getString(R.string.exit_login), ""));
        return list;
    }

    public void logout() {
        UserInfoBean userInfoBean = menuUserFragment.getUserInfo();
        Map<String, String> p = new HashMap<>();
        p.put("web_id", WebSiteUrl.WebId);
        p.put("user_id", userInfoBean.getUser_id());
        p.put("session_id", userInfoBean.getSession_id());
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.Dig88Logout, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                AppTool.saveObjectData(baseContext.getBaseActivity(), "loginInfo", null);
                menuUserFragment.setUserInfo(null);
                menuUserFragment.onLogout();
            }

            @Override
            protected void onAccept() {
//                super.onAccept();
            }
        });
    }
}
