package nanyang.com.dig88.Activity.presenter;

import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;
import nanyang.com.dig88.Activity.Scr888ChangePasswordActivity;
import nanyang.com.dig88.Entity.Scr888ChangePasswordBean;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.ApiManager.getService;


/**
 * Created by Administrator on 2019/9/3.
 */

public class Scr888ChangePasswordPresenter extends BaseRetrofitPresenter<Scr888ChangePasswordActivity> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    Scr888ChangePasswordActivity scr888ChangePasswordActivity;

    public Scr888ChangePasswordPresenter(Scr888ChangePasswordActivity iBaseContext) {
        super(iBaseContext);
        scr888ChangePasswordActivity = iBaseContext;
    }

    public void changPassword(String p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).getData(WebSiteUrl.Scr888changePasswordUrl + p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                Scr888ChangePasswordBean scr888ChangePasswordBean = gson.fromJson(data, Scr888ChangePasswordBean.class);
                scr888ChangePasswordActivity.onGetChangeResult(scr888ChangePasswordBean);
            }
        });
    }
}
