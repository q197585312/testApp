package nanyang.com.dig88.Fragment.Presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;

import org.json.JSONException;

import java.util.HashMap;

import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.Fragment.ChangePasswordFragment;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.Api.getService;

/**
 * Created by 47184 on 2019/7/1.
 */

public class ChangPasswordPresenter extends BaseRetrofitPresenter<ChangePasswordFragment> {
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    ChangePasswordFragment changePasswordFragment;

    public ChangPasswordPresenter(ChangePasswordFragment iBaseContext) {
        super(iBaseContext);
        changePasswordFragment = iBaseContext;
    }

    public void changPassword(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.ChangeUserPassword, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) throws JSONException {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                String returnMsg;
                switch (orgData.getMsg()) {
                    case "1":
                        changePasswordFragment.clearContent();
                        returnMsg = changePasswordFragment.getString(R.string.xiumisucess);
                        break;
                    case "-1":
                        returnMsg = changePasswordFragment.getString(R.string.mimaerrorx);
                        break;
                    case "-2":
                        returnMsg = changePasswordFragment.getString(R.string.oldmimaerror);
                        break;
                    case "-3":
                        returnMsg = changePasswordFragment.getString(R.string.twomimacuo);
                        break;
                    default:
                        returnMsg = changePasswordFragment.getString(R.string.mimaerrorx);
                        break;
                }
                changePasswordFragment.onGetChangeResult(returnMsg);
            }
        });
    }
}
