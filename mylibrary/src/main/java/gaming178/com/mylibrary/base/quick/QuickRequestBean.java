package gaming178.com.mylibrary.base.quick;

import java.lang.reflect.Type;
import java.util.HashMap;

import gaming178.com.mylibrary.base.RequestBean;

/**
 * Created by Administrator on 2015/8/28.
 */
public class QuickRequestBean extends RequestBean<HashMap<String, String>> {
    public QuickRequestBean(Method method, String url, HashMap params, Type type) {
        super(method, url, params, type);
    }

    public QuickRequestBean(String url, HashMap params, Type type) {
        super(Method.POST, url, params, type);
    }

    @Override
    public HashMap<String, String> getParams() {
        return super.getParams();
    }
}
