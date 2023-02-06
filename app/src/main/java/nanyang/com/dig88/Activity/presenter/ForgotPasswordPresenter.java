package nanyang.com.dig88.Activity.presenter;

import com.google.gson.reflect.TypeToken;
import com.unkonw.testapp.libs.base.BaseConsumer;
import com.unkonw.testapp.libs.presenter.BaseRetrofitPresenter;
import com.unkonw.testapp.libs.utils.ToastUtils;

import java.util.HashMap;
import java.util.Random;

import nanyang.com.dig88.Activity.ForgotPasswordActivity;
import nanyang.com.dig88.Base.NyBaseResponse;
import nanyang.com.dig88.R;
import nanyang.com.dig88.Util.ApiService;
import nanyang.com.dig88.Util.WebSiteUrl;

import static com.unkonw.testapp.libs.api.ApiManager.getService;


/**
 * Created by Administrator on 2019/11/5.
 */

public class ForgotPasswordPresenter extends BaseRetrofitPresenter<ForgotPasswordActivity> {
    private static final char[] CHARS = {'0', '1',
            '2', '3', '4', '5', '6', '7', '8', '9',
    };
    /**
     * 使用CompositeSubscription来持有所有的Subscriptions
     *
     * @param iBaseContext
     */
    ForgotPasswordActivity forgotPasswordActivity;
    private Random random = new Random();

    public ForgotPasswordPresenter(ForgotPasswordActivity iBaseContext) {
        super(iBaseContext);
        forgotPasswordActivity = iBaseContext;
    }

    public String createCode() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            buffer.append(CHARS[random.nextInt(CHARS.length)]);
        }
        return buffer.toString();
    }

    public void request(HashMap<String, String> p) {
        doRetrofitApiOnUiThread(getService(ApiService.class).doPostMap(WebSiteUrl.ForgotPasswordUrl, p), new BaseConsumer<String>(baseContext) {
            @Override
            protected void onBaseGetData(String data) {
                NyBaseResponse<Object> orgData = gson.fromJson(data, new TypeToken<NyBaseResponse<Object>>() {
                }.getType());
                switch (orgData.getMsg()) {
                    case "1":
                        ToastUtils.showShort(forgotPasswordActivity.getString(R.string.Success));
                        break;
                    default:
                        ToastUtils.showShort(forgotPasswordActivity.getString(R.string.Forgot_Password_error));
                        break;
                }
            }
        });
    }
}
