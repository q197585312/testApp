package gaming178.com.mylibrary.base;

import java.lang.reflect.Type;

public class RequestBean<A> {
    String url = "";
    Method method;
    private A params;
    private Type responeType;

    public RequestBean(Method method, String url,
                       A params, Type responeType) {
        super();
        this.method = method;
        this.url = url;
        this.params = params;
        this.responeType = responeType;

    }
    public RequestBean(String url,
                       A params) {
        this(Method.POST,url,params,null);
    }

    public RequestBean() {

    }


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public A getParams() {
        return params;
    }

    public void setParams(A params) {
        this.params = params;
    }

    public Type getResponeType() {
        return responeType;
    }

    public void setResponeType(Type responeType) {
        this.responeType = responeType;
    }

    public enum Method {
        GET, POST, PUT, SOAP
    }
}
