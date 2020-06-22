package gaming178.com.mylibrary.base.soap;

import java.lang.reflect.Type;
import java.util.List;

import gaming178.com.mylibrary.base.RequestBean;

/**
 * Created by Administrator on 2015/8/28.
 */
public class SoapRequestBean extends RequestBean<List<SoapParamsBean>> {
    String spaceName;
    String methodName;

    public SoapRequestBean(String spaceName, String methodName, String url, List<SoapParamsBean> params, Type type) {
        super(Method.SOAP, url, params, type);
        this.spaceName = spaceName;
        this.methodName = methodName;
    }

    public String getSpaceName() {
        return spaceName;
    }

    public void setSpaceName(String spaceName) {
        this.spaceName = spaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public List<SoapParamsBean> getParams() {
        return super.getParams();
    }

    @Override
    public void setParams(List<SoapParamsBean> params) {
        super.setParams(params);
    }
}
